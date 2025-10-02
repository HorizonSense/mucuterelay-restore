package com.mucheng.mucute.relay.listener

import com.mucheng.mucute.relay.MuCuteRelay
import com.mucheng.mucute.relay.MuCuteRelaySession
import com.mucheng.mucute.relay.definition.Definitions
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec
import org.cloudburstmc.protocol.bedrock.codec.v291.Bedrock_v291
import org.cloudburstmc.protocol.bedrock.codec.v313.Bedrock_v313
import org.cloudburstmc.protocol.bedrock.codec.v332.Bedrock_v332
import org.cloudburstmc.protocol.bedrock.codec.v340.Bedrock_v340
import org.cloudburstmc.protocol.bedrock.codec.v354.Bedrock_v354
import org.cloudburstmc.protocol.bedrock.codec.v361.Bedrock_v361
import org.cloudburstmc.protocol.bedrock.codec.v388.Bedrock_v388
import org.cloudburstmc.protocol.bedrock.codec.v389.Bedrock_v389
import org.cloudburstmc.protocol.bedrock.codec.v390.Bedrock_v390
import org.cloudburstmc.protocol.bedrock.codec.v407.Bedrock_v407
import org.cloudburstmc.protocol.bedrock.codec.v408.Bedrock_v408
import org.cloudburstmc.protocol.bedrock.codec.v419.Bedrock_v419
import org.cloudburstmc.protocol.bedrock.codec.v422.Bedrock_v422
import org.cloudburstmc.protocol.bedrock.codec.v428.Bedrock_v428
import org.cloudburstmc.protocol.bedrock.codec.v431.Bedrock_v431
import org.cloudburstmc.protocol.bedrock.codec.v440.Bedrock_v440
import org.cloudburstmc.protocol.bedrock.codec.v448.Bedrock_v448
import org.cloudburstmc.protocol.bedrock.codec.v465.Bedrock_v465
import org.cloudburstmc.protocol.bedrock.codec.v471.Bedrock_v471
import org.cloudburstmc.protocol.bedrock.codec.v475.Bedrock_v475
import org.cloudburstmc.protocol.bedrock.codec.v486.Bedrock_v486
import org.cloudburstmc.protocol.bedrock.codec.v503.Bedrock_v503
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527
import org.cloudburstmc.protocol.bedrock.codec.v534.Bedrock_v534
import org.cloudburstmc.protocol.bedrock.codec.v544.Bedrock_v544
import org.cloudburstmc.protocol.bedrock.codec.v545.Bedrock_v545
import org.cloudburstmc.protocol.bedrock.codec.v554.Bedrock_v554
import org.cloudburstmc.protocol.bedrock.codec.v557.Bedrock_v557
import org.cloudburstmc.protocol.bedrock.codec.v560.Bedrock_v560
import org.cloudburstmc.protocol.bedrock.codec.v567.Bedrock_v567
import org.cloudburstmc.protocol.bedrock.codec.v568.Bedrock_v568
import org.cloudburstmc.protocol.bedrock.codec.v575.Bedrock_v575
import org.cloudburstmc.protocol.bedrock.codec.v582.Bedrock_v582
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589
import org.cloudburstmc.protocol.bedrock.codec.v594.Bedrock_v594
import org.cloudburstmc.protocol.bedrock.codec.v618.Bedrock_v618
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630
import org.cloudburstmc.protocol.bedrock.codec.v649.Bedrock_v649
import org.cloudburstmc.protocol.bedrock.codec.v662.Bedrock_v662
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686
import org.cloudburstmc.protocol.bedrock.codec.v712.Bedrock_v712
import org.cloudburstmc.protocol.bedrock.codec.v729.Bedrock_v729
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.InventoryContentSerializer_v729
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.InventorySlotSerializer_v729
import org.cloudburstmc.protocol.bedrock.codec.v748.Bedrock_v748
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm
import org.cloudburstmc.protocol.bedrock.packet.*

class AutoCodecPacketListener(
    val muCuteRelaySession: MuCuteRelaySession,
    val patchCodec: Boolean = true
) : MuCuteRelayPacketListener {
    companion object {
        private val protocols: Lazy<Map<Int, BedrockCodec>> = lazy(LazyThreadSafetyMode.PUBLICATION) {
            val arr = arrayOf(
                Bedrock_v291.CODEC, Bedrock_v313.CODEC, Bedrock_v332.CODEC, Bedrock_v340.CODEC, Bedrock_v354.CODEC,
                Bedrock_v361.CODEC, Bedrock_v388.CODEC, Bedrock_v389.CODEC, Bedrock_v390.CODEC, Bedrock_v407.CODEC,
                Bedrock_v408.CODEC, Bedrock_v419.CODEC, Bedrock_v422.CODEC, Bedrock_v428.CODEC, Bedrock_v431.CODEC,
                Bedrock_v440.CODEC, Bedrock_v448.CODEC, Bedrock_v465.CODEC, Bedrock_v471.CODEC, Bedrock_v475.CODEC,
                Bedrock_v486.CODEC, Bedrock_v503.CODEC, Bedrock_v527.CODEC, Bedrock_v534.CODEC, Bedrock_v544.CODEC,
                Bedrock_v545.CODEC, Bedrock_v554.CODEC, Bedrock_v557.CODEC, Bedrock_v560.CODEC, Bedrock_v567.CODEC,
                Bedrock_v568.CODEC, Bedrock_v575.CODEC, Bedrock_v582.CODEC, Bedrock_v589.CODEC, Bedrock_v594.CODEC,
                Bedrock_v618.CODEC, Bedrock_v622.CODEC, Bedrock_v630.CODEC, Bedrock_v649.CODEC, Bedrock_v662.CODEC,
                Bedrock_v671.CODEC, Bedrock_v685.CODEC, Bedrock_v686.CODEC, Bedrock_v712.CODEC, Bedrock_v729.CODEC,
                Bedrock_v748.CODEC, Bedrock_v766.CODEC, Bedrock_v776.CODEC
            )
            val map = LinkedHashMap<Int, BedrockCodec>(arr.size)
            for (codec in arr) {
                map[codec.protocolVersion] = codec
            }
            map
        }
        fun pickProtocolCodec(protocolVersion: Int): BedrockCodec {
            var bedrockCodec = MuCuteRelay.DefaultCodec
            for ((version, codec) in protocols.value) {
                if (version > protocolVersion) break
                bedrockCodec = codec
            }
            return bedrockCodec.toBuilder().build()
        }
    }
    
    private fun patchCodecIfNeeded(codec: BedrockCodec): BedrockCodec {
        return if (patchCodec && codec.protocolVersion > 729) {
            codec.toBuilder()
                .updateSerializer(InventoryContentPacket::class.java, InventoryContentSerializer_v729.INSTANCE)
                .updateSerializer(InventorySlotPacket::class.java, InventorySlotSerializer_v729.INSTANCE)
                .build()
        } else codec
    }
    override fun beforeClientBound(packet: BedrockPacket): Boolean {
        if (packet is RequestNetworkSettingsPacket) {
            val protocolVersion = packet.protocolVersion
            val bedrockCodec = patchCodecIfNeeded(pickProtocolCodec(protocolVersion))
            muCuteRelaySession.server.codec = bedrockCodec
            muCuteRelaySession.server.peer.codecHelper.apply {
                itemDefinitions = Definitions.itemDefinitions
                blockDefinitions = Definitions.blockDefinitions
                cameraPresetDefinitions = cameraPresetDefinitions
                encodingSettings = EncodingSettings.builder()
                    .maxListSize(Int.MAX_VALUE)
                    .maxByteArraySize(Int.MAX_VALUE)
                    .maxNetworkNBTSize(Int.MAX_VALUE)
                    .maxItemNBTSize(Int.MAX_VALUE)
                    .maxStringLength(Int.MAX_VALUE)
                    .build()
            }
            val networkSettingsPacket = NetworkSettingsPacket().apply {
                compressionThreshold = 0
                compressionAlgorithm = PacketCompressionAlgorithm.ZLIB
            }
            muCuteRelaySession.clientBoundImmediately(networkSettingsPacket)
            muCuteRelaySession.server.setCompression(PacketCompressionAlgorithm.ZLIB)
            return true
        }
        return false
    }
}