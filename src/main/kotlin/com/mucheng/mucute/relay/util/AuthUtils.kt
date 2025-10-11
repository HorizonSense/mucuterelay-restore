package com.mucheng.mucute.relay.util

import coelho.msftauth.api.xbox.*
import com.google.gson.*
import com.mucheng.mucute.relay.MuCuteRelay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.Reader
import java.security.KeyPair
import java.security.PublicKey
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.jvm.internal.Ref


val deviceKey: XboxDeviceKey = XboxDeviceKey()
val gson: Gson = GsonBuilder().setPrettyPrinting().create()

fun fetchChain(identityToken: String, keyPair: KeyPair): List<String> {
    val publicKey = keyPair.public
    val rawChain = JsonParser.parseReader(fetchRawChain(identityToken, publicKey)).asJsonObject
    val chains = rawChain["chain"].asJsonArray
    val first = chains[0].asString
    val payload = base64Decode(first.split(".")[0])
    val identityPubKey = JsonParser.parseString(String(payload, Charsets.UTF_8)).asJsonObject
    val cert = JsonObject()
    cert.addProperty("certificateAuthority", true)
    cert.addProperty("exp", (Instant.now().epochSecond + TimeUnit.HOURS.toSeconds(6)).toInt())
    cert.addProperty("nbf", (Instant.now().epochSecond - TimeUnit.HOURS.toSeconds(6)).toInt())
    cert.addProperty("identityPublicKey", identityPubKey["x5u"].asString)
    val jwt = signJWT(gson.toJson(cert), keyPair, false, 4)
    val result = mutableListOf(jwt)
    for (it in chains) result.add(it.asString)
    return result
}

fun fetchRawChain(identityToken: String, publicKey: PublicKey): Reader {
    val obj = JsonObject()
    obj.addProperty("identityPublicKey", Base64.getEncoder().withoutPadding().encodeToString(publicKey.encoded))
    val json = gson.toJson(obj)
    val request = Request.Builder()
        .url("https://multiplayer.minecraft.net/authentication")
        .post(json.toRequestBody("application/json".toMediaType()))
        .header("Client-Version", MuCuteRelay.DefaultCodec.minecraftVersion)
        .header("Authorization", identityToken)
        .build()
    val response = HttpUtils.client.newCall(request).execute()
    if (response.code != 200) throw AssertionError("Http code ${response.code}")
    return response.body!!.charStream()
}

@Throws(Exception::class)
fun fetchIdentityToken(accessToken: String, deviceInfo: XboxDeviceInfo): XboxIdentityToken {
    val userToken = Ref.ObjectRef<XboxToken?>()
    val userRequestThread = thread(start = true) {
        userToken.element = XboxUserAuthRequest(
            "http://auth.xboxlive.com",
            "JWT",
            "RPS",
            "user.auth.xboxlive.com",
            "t=$accessToken"
        ).request(HttpUtils.client)
    }
    val deviceToken = XboxDeviceAuthRequest(
        "http://auth.xboxlive.com",
        "JWT",
        deviceInfo.deviceType,
        "0.0.0.0",
        deviceKey
    ).request(HttpUtils.client) as XboxDeviceToken
    val titleToken: XboxToken = if (deviceInfo.allowDirectTitleTokenFetch) {
        XboxTitleAuthRequest(
            "http://auth.xboxlive.com",
            "JWT",
            "RPS",
            "user.auth.xboxlive.com",
            "t=$accessToken",
            deviceToken.token,
            deviceKey
        ).request(HttpUtils.client) as XboxToken
    } else {
        val device = XboxDevice(deviceKey, deviceToken)
        val sisuQuery = XboxSISUAuthenticateRequest.Query("phone")
        val sisuRequest = XboxSISUAuthenticateRequest(
            deviceInfo.appId,
            device,
            "service::user.auth.xboxlive.com::MBI_SSL",
            sisuQuery,
            deviceInfo.xalRedirect,
            "RETAIL"
        ).request(HttpUtils.client) as XboxSISUAuthenticate
        val sisuToken = XboxSISUAuthorizeRequest(
            "t=$accessToken",
            deviceInfo.appId,
            device,
            "RETAIL",
            sisuRequest.sessionId,
            "user.auth.xboxlive.com",
            "http://xboxlive.com"
        ).request(HttpUtils.client) as XboxSISUAuthorize
        if (sisuToken.status != 200) {
            val did = deviceToken.displayClaims["xdi"]!!.asJsonObject["did"].asString
            var sign = deviceKey.sign("/proxy?sessionid=${sisuRequest.sessionId}", null, "POST", null)
            sign = sign.replace("+", "%2B").replace("=", "%3D")
            val url = sisuToken.webPage.split("#")[0] +
                    "&did=0x$did&redirect=${deviceInfo.xalRedirect}" +
                    "&sid=${sisuRequest.sessionId}&sig=$sign&state=${sisuQuery.state}"
            throw XboxGamerTagException(url)
        }
        sisuToken.titleToken
    }
    if (userRequestThread.isAlive) userRequestThread.join()
    if (userToken.element == null) throw IllegalStateException("failed to fetch xbox user token")
    val xstsToken = XboxXSTSAuthRequest(
        "https://multiplayer.minecraft.net/",
        "JWT",
        "RETAIL",
        listOf(userToken.element),
        titleToken,
        XboxDevice(deviceKey, deviceToken)
    ).request(HttpUtils.client) as XboxToken
    return XboxIdentityToken(
        xstsToken.toIdentityToken(),
        Instant.parse(xstsToken.notAfter).epochSecond
    )
}