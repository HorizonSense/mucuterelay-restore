package com.mucheng.mucute.relay.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.jose4j.jws.EcdsaUsingShaAlgorithm
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.security.KeyPair
import java.security.Signature
import java.util.*

object JWTUtils {
    fun jwtPayload(jwt: String): JsonObject? {
        val parts = jwt.split('.')
        if (parts.size != 3) return null
        val payloadBytes = base64Decode(parts[1])
        val result = JsonParser.parseReader(InputStreamReader(ByteArrayInputStream(payloadBytes), Charsets.UTF_8))
        return if (result.isJsonObject) result.asJsonObject else null
    }

    fun signJWT(payload: String, keyPair: KeyPair, base64Encoded: Boolean): String {
        val headerObj = JsonObject().apply {
            addProperty("alg", "ES384")
            addProperty("x5u", Base64.getEncoder().withoutPadding().encodeToString(keyPair.public.encoded))
        }
        val header = Base64.getUrlEncoder().withoutPadding()
            .encodeToString(AuthUtils.gson.toJson(headerObj).toByteArray(Charsets.UTF_8))
        val encodedPayload = if (base64Encoded) payload else
            Base64.getUrlEncoder().withoutPadding().encodeToString(payload.toByteArray(Charsets.UTF_8))
        val dataToSign = "$header.$encodedPayload".toByteArray(Charsets.UTF_8)
        val sign = signBytes(dataToSign, keyPair)
        return "$header.$encodedPayload.$sign"
    }

    fun signJWT(
    payload: String,
    keyPair: KeyPair,
    base64Encoded: Boolean = false,
    mask: Int = 0
    ): String = signJWT(payload, keyPair, if ((mask and 4) != 0) false else base64Encoded)

    private fun signBytes(dataToSign: ByteArray, keyPair: KeyPair): String {
        val signature = Signature.getInstance("SHA384withECDSA")
        signature.initSign(keyPair.private)
        signature.update(dataToSign)
        val signatureBytes = EcdsaUsingShaAlgorithm.convertDerToConcatenated(signature.sign(), 48)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes)
    }

    fun base64Decode(b64: String): ByteArray {
        val fixed = b64.replace('-', '+').replace('_', '/')
        return Base64.getDecoder().decode(fixed)
    }
}