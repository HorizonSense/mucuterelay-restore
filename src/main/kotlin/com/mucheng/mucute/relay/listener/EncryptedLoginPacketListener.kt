package com.mucheng.mucute.relay.listener

import com.google.gson.JsonParser
import com.mucheng.mucute.relay.MuCuteRelaySession
import com.mucheng.mucute.relay.util.AuthUtils
import com.mucheng.mucute.relay.util.JWTUtils
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm
import org.cloudburstmc.protocol.bedrock.packet.*
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils
import java.security.KeyPair
import java.security.PublicKey
import java.util.*
import javax.crypto.SecretKey

open class EncryptedLoginPacketListener : MuCuteRelayPacketListener {
    private var keyPair: KeyPair = EncryptionUtils.createKeyPair()
    private var loginPacket: LoginPacket? = null
    var muCuteRelaySession: MuCuteRelaySession? = null

    fun getKeyPair(): KeyPair = keyPair
    fun setKeyPair(value: KeyPair) { keyPair = value }
    fun getLoginPacket(): LoginPacket? = loginPacket
    fun setLoginPacket(value: LoginPacket?) { loginPacket = value }
    fun getMuCuteRelaySession(): MuCuteRelaySession {
        return muCuteRelaySession ?: error("muCuteRelaySession not initialized")
    }
    fun setMuCuteRelaySession(value: MuCuteRelaySession) { muCuteRelaySession = value }

    override fun beforeClientBound(packet: BedrockPacket): Boolean {
        if (packet is LoginPacket) {
            var newChain: Any? = null
            for (it in packet.chain) {
                val chainBody = JWTUtils.jwtPayload(it)
                if (chainBody != null && chainBody.has("extraData")) {
                    chainBody.addProperty(
                        "identityPublicKey",
                        Base64.getEncoder().withoutPadding().encodeToString(keyPair.public.encoded)
                    )
                    val json = AuthUtils.gson.toJson(chainBody)
                    newChain = JWTUtils.signJWT(json, keyPair)
                }
            }
            packet.chain.clear()
            packet.chain.add(newChain as String?)
            loginPacket = packet
            connectServer()
            return true
        }
        return false
    }

    override fun beforeServerBound(packet: BedrockPacket): Boolean {
        if (packet is NetworkSettingsPacket) {
            val threshold = packet.compressionThreshold
            val client = getMuCuteRelaySession().client
            if (threshold > 0) {
                client?.setCompression(packet.compressionAlgorithm)
                println("Compression threshold set to $threshold")
            } else {
                client?.setCompression(PacketCompressionAlgorithm.NONE)
                println("Compression threshold set to 0")
            }
            val login = loginPacket
            if (login != null) {
                getMuCuteRelaySession().serverBoundImmediately(login)
            } else {
                getMuCuteRelaySession().server.disconnect("LoginPacket is null")
            }
            return true
        } else if (packet is ServerToClientHandshakePacket) {
            val jwt = packet.jwt
            val jwtSplit = jwt.split(".")
            val headerObject = JsonParser.parseString(
                String(JWTUtils.base64Decode(jwtSplit[0]), Charsets.UTF_8)
            ).asJsonObject
            val payloadObject = JsonParser.parseString(
                String(JWTUtils.base64Decode(jwtSplit[1]), Charsets.UTF_8)
            ).asJsonObject
            val serverKey = EncryptionUtils.parseKey(headerObject.get("x5u").asString)
            val key: SecretKey = EncryptionUtils.getSecretKey(
                keyPair.private,
                serverKey as PublicKey,
                JWTUtils.base64Decode(payloadObject.get("salt").asString)
            )
            val client = getMuCuteRelaySession().client
            client?.enableEncryption(key)
            println("Encryption enabled")
            getMuCuteRelaySession().serverBoundImmediately(ClientToServerHandshakePacket())
            return true
        }
        return false
    }

    protected fun connectServer() {
        getMuCuteRelaySession().muCuteRelay.connectToServer { _ ->
            println("Connected to server")
            val packet = RequestNetworkSettingsPacket()
            packet.protocolVersion = getMuCuteRelaySession().server.codec.protocolVersion
            getMuCuteRelaySession().serverBoundImmediately(packet)
        }
    }

    override fun afterClientBound(packet: BedrockPacket) {}
    override fun afterServerBound(packet: BedrockPacket) {}
    override fun onDisconnect(reason: String) {}
}
