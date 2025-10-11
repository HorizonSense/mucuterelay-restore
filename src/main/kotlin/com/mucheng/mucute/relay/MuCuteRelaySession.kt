package com.mucheng.mucute.relay

import com.mucheng.mucute.relay.handler.SessionCloseHandler
import com.mucheng.mucute.relay.listener.MuCuteRelayPacketListener
import io.netty.util.internal.PlatformDependent
import org.cloudburstmc.protocol.bedrock.BedrockClientSession
import org.cloudburstmc.protocol.bedrock.BedrockPeer
import org.cloudburstmc.protocol.bedrock.BedrockServerSession
import org.cloudburstmc.protocol.bedrock.netty.BedrockPacketWrapper
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.UnknownPacket
import java.util.*

class MuCuteRelaySession(peer: BedrockPeer, subClientId: Int, val muCuteRelay: MuCuteRelay) {
    val server = ServerSession(peer, subClientId)
    val listeners: MutableList<MuCuteRelayPacketListener> = ArrayList()
    private val packetQueue: Queue<Pair<BedrockPacket, Boolean>> = PlatformDependent.newMpscQueue()

    var client: ClientSession? = null
        internal set(value) {
            value?.apply {
                codec = server.codec
                peer.codecHelper.apply {
                    blockDefinitions = server.peer.codecHelper.blockDefinitions
                    itemDefinitions = server.peer.codecHelper.itemDefinitions
                    cameraPresetDefinitions = server.peer.codecHelper.cameraPresetDefinitions
                    encodingSettings = server.peer.codecHelper.encodingSettings
                }
                packetQueue.forEach { (packet, immediate) ->
                    if (immediate) sendPacketImmediately(packet) else sendPacket(packet)
                }
                packetQueue.clear()
            }
            field = value
        }

    fun clientBound(packet: BedrockPacket) = server.sendPacket(packet)
    fun clientBoundImmediately(packet: BedrockPacket) = server.sendPacketImmediately(packet)

    fun serverBound(packet: BedrockPacket) =
        client?.sendPacket(packet) ?: packetQueue.add(packet to false)

    fun serverBoundImmediately(packet: BedrockPacket) =
        client?.sendPacketImmediately(packet) ?: packetQueue.add(packet to true)

    private fun createDisconnectHandler(isServer: Boolean) = SessionCloseHandler { reason ->
        println("${if (isServer) "Server" else "Client"} disconnect: $reason")
        runCatching {
            (if (isServer) server else client)?.disconnect()
            listeners.forEach { it.onDisconnect(reason) }
        }
    }

    private fun processPacket(wrapper: BedrockPacketWrapper,
                              beforeFunc: (MuCuteRelayPacketListener) -> Boolean,
                              sendFunc: (BedrockPacket) -> Unit,
                              afterFunc: (MuCuteRelayPacketListener) -> Unit) {
        listeners.forEach { it ->
            runCatching { if (beforeFunc(it)) return }
                .onFailure { println("Before packet error: ${it.stackTraceToString()}") }
        }

        UnknownPacket().apply {
            payload = wrapper.packetBuffer.retainedSlice().skipBytes(wrapper.headerLength)
            packetId = wrapper.packetId
        }.let(sendFunc)

        listeners.forEach { it ->
            runCatching { afterFunc(it) }
                .onFailure { println("After packet error: ${it.stackTraceToString()}") }
        }
    }

    inner class ServerSession(peer: BedrockPeer, subClientId: Int) : BedrockServerSession(peer, subClientId) {
        init { packetHandler = createDisconnectHandler(false) }

        override fun onPacket(wrapper: BedrockPacketWrapper) = processPacket(
            wrapper,
            { it.beforeClientBound(wrapper.packet) },
            ::serverBound,
            { it.afterClientBound(wrapper.packet) }
        )
    }

    inner class ClientSession(peer: BedrockPeer, subClientId: Int) : BedrockClientSession(peer, subClientId) {
        init { packetHandler = createDisconnectHandler(true) }

        override fun onPacket(wrapper: BedrockPacketWrapper) = processPacket(
            wrapper,
            { it.beforeServerBound(wrapper.packet) },
            ::clientBound,
            { it.afterServerBound(wrapper.packet) }
        )
    }
}
