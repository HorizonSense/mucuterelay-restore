package com.mucheng.mucute.relay.listener

import com.google.gson.JsonParser
import com.mucheng.mucute.relay.MuCuteRelaySession
import com.mucheng.mucute.relay.util.base64Decode
import com.mucheng.mucute.relay.util.gson
import com.mucheng.mucute.relay.util.jwtPayload
import com.mucheng.mucute.relay.util.signJWT
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm
import org.cloudburstmc.protocol.bedrock.packet.*
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils
import java.security.KeyPair
import java.security.PublicKey
import java.util.*
import javax.crypto.SecretKey

open class EncryptedLoginPacketListener : MuCuteRelayPacketListener {
    open var keyPair: KeyPair = EncryptionUtils.createKeyPair()
    open var loginPacket: LoginPacket? = null
    var muCuteRelaySession: MuCuteRelaySession? = null


    override fun beforeClientBound(packet: BedrockPacket): Boolean {
        if (packet is LoginPacket) {
            var newChain: Any? = null
            for (it in packet.chain) {
                val chainBody = jwtPayload(it)
                if (chainBody != null && chainBody.has("extraData")) {
                    chainBody.addProperty(
                        "identityPublicKey",
                        Base64.getEncoder().withoutPadding().encodeToString(keyPair.public.encoded)
                    )
                    val json = gson.toJson(chainBody)
                    newChain = signJWT(json, keyPair)
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

            if (threshold > 0) {
                muCuteRelaySession?.client!!.setCompression(packet.compressionAlgorithm)
                println("Compression threshold set to $threshold")
            } else {
                muCuteRelaySession?.client!!.setCompression(PacketCompressionAlgorithm.NONE)
                println("Compression threshold set to 0")
            }
            val login = loginPacket
            if (login != null) {
                muCuteRelaySession!!.serverBoundImmediately(login)
            } else {
                muCuteRelaySession!!.server.disconnect("LoginPacket is null")
            }
            return true
        } else if (packet is ServerToClientHandshakePacket) {
            val jwt = packet.jwt
            val jwtSplit = jwt.split(".")
            val headerObject = JsonParser.parseString(
                String(base64Decode(jwtSplit[0]), Charsets.UTF_8)
            ).asJsonObject
            val payloadObject = JsonParser.parseString(
                String(base64Decode(jwtSplit[1]), Charsets.UTF_8)
            ).asJsonObject
            val serverKey = EncryptionUtils.parseKey(headerObject.get("x5u").asString)
            val key: SecretKey = EncryptionUtils.getSecretKey(
                keyPair.private,
                serverKey as PublicKey,
                base64Decode(payloadObject.get("salt").asString)
            )
            val client = muCuteRelaySession!!.client
            client?.enableEncryption(key)
            println("Encryption enabled")
            muCuteRelaySession!!.serverBoundImmediately(ClientToServerHandshakePacket())
            return true
        }
        return false
    }

    protected fun connectServer() {
        muCuteRelaySession!!.muCuteRelay.connectToServer { _ ->
            println("Connected to server")
            val packet = RequestNetworkSettingsPacket()
            packet.protocolVersion = muCuteRelaySession!!.server.codec.protocolVersion
            muCuteRelaySession!!.serverBoundImmediately(packet)
        }
    }
}
