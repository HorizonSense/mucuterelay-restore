package com.mucheng.mucute.relay.listener

import com.mucheng.mucute.relay.util.AuthUtils
import com.mucheng.mucute.relay.util.IXboxIdentityTokenCache
import com.mucheng.mucute.relay.util.JWTUtils.signJWT
import com.mucheng.mucute.relay.util.XboxDeviceInfo
import com.mucheng.mucute.relay.util.XboxIdentityToken
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket
import kotlin.jvm.functions.Function0

class XboxLoginPacketListener(
    val accessToken: Function0<String>,
    val deviceInfo: XboxDeviceInfo
) : EncryptedLoginPacketListener() {
    var tokenCache: IXboxIdentityTokenCache? = null
    private var identityToken: XboxIdentityToken = XboxIdentityToken("", 0L)

    private fun getIdentityToken(): XboxIdentityToken {
        if (identityToken.notAfter < System.currentTimeMillis() / 1000) {
            val cacheToken = tokenCache?.checkCache(deviceInfo)
            if (cacheToken != null) {
                println("Token cache hit")
                identityToken = cacheToken
            } else {
                val fetched = AuthUtils.fetchIdentityToken(accessToken.invoke(), deviceInfo)
                tokenCache?.let {
                    println("Saving token cache")
                    it.cache(deviceInfo, fetched)
                }
                identityToken = fetched
            }
        }
        return identityToken
    }

    private fun getChain(): List<String> {
        return AuthUtils.fetchChain(getIdentityToken().token, keyPair)
    }

    fun forceFetchChain() {
        getChain()
    }

    override fun beforeClientBound(packet: BedrockPacket): Boolean {
        if (packet is LoginPacket) {
            try {
                packet.chain.clear()
                packet.chain.addAll(getChain())
                packet.extra = signJWT(packet.extra.split('.')[1], keyPair, base64Encoded = true)
                println("Login success")
            } catch (e: Throwable) {
                val disconnect = DisconnectPacket()
                disconnect.kickMessage = e.toString()
                muCuteRelaySession?.clientBound(disconnect)
                println("Login failed: $e")
            }
            loginPacket = packet
            connectServer()
            return true
        }
        return false
    }
}
