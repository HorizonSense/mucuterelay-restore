package com.mucheng.mucute.relay.listener

import com.mucheng.mucute.relay.MuCuteRelaySession
import com.mucheng.mucute.relay.definition.CameraPresetDefinition
import com.mucheng.mucute.relay.definition.Definitions
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.CameraPresetsPacket
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket
import org.cloudburstmc.protocol.common.NamedDefinition
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry

@Suppress("DEPRECATION")
class GamingPacketHandler(
    val muCuteRelaySession: MuCuteRelaySession
) : MuCuteRelayPacketListener {
    override fun beforeServerBound(packet: BedrockPacket): Boolean {
        if (packet is StartGamePacket) {
            Definitions.itemDefinitions = SimpleDefinitionRegistry.builder<ItemDefinition>()
                .addAll(packet.itemDefinitions)
                .build()

            val client = muCuteRelaySession.client
            client?.peer?.codecHelper?.itemDefinitions = Definitions.itemDefinitions
            muCuteRelaySession.server.peer.codecHelper.itemDefinitions = Definitions.itemDefinitions
            if (packet.isBlockNetworkIdsHashed) {
                client?.peer?.codecHelper?.blockDefinitions = Definitions.blockDefinitionsHashed
                muCuteRelaySession.server.peer.codecHelper.blockDefinitions = Definitions.blockDefinitionsHashed
            } else {
                client?.peer?.codecHelper?.blockDefinitions = Definitions.blockDefinitions
                muCuteRelaySession.server.peer.codecHelper.blockDefinitions = Definitions.blockDefinitions
            }
        }

        if (packet is CameraPresetsPacket) {
            val cameraDefinitions =
                SimpleDefinitionRegistry.builder<NamedDefinition>()
                    .addAll(List(packet.presets.size) {
                        CameraPresetDefinition.fromCameraPreset(packet.presets[it], it)
                    })
                    .build()

            val client = muCuteRelaySession.client
            client?.peer?.codecHelper?.cameraPresetDefinitions = cameraDefinitions
            muCuteRelaySession.server.peer.codecHelper.cameraPresetDefinitions = cameraDefinitions
        }
        return false
    }

    override fun beforeClientBound(packet: BedrockPacket): Boolean {
        return false
    }

    override fun afterClientBound(packet: BedrockPacket) {}
    override fun afterServerBound(packet: BedrockPacket) {}
    override fun onDisconnect(reason: String) {}
}