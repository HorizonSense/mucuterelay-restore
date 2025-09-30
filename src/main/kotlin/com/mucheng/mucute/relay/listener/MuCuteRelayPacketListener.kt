package com.mucheng.mucute.relay.listener

import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket

interface MuCuteRelayPacketListener {
    fun beforeClientBound(packet: BedrockPacket): Boolean = false

    fun beforeServerBound(packet: BedrockPacket): Boolean = false

    fun afterClientBound(packet: BedrockPacket) {}

    fun afterServerBound(packet: BedrockPacket) {}

    fun onDisconnect(reason: String) {}
}
