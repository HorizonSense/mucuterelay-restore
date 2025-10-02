package com.mucheng.mucute.relay.util

import com.mucheng.mucute.relay.MuCuteRelay
import com.mucheng.mucute.relay.MuCuteRelaySession
import com.mucheng.mucute.relay.address.MuCuteAddress
import org.cloudburstmc.protocol.bedrock.BedrockPong

object MinecraftRelays {
    fun captureMuCuteRelay(
        advertisement: BedrockPong? = null,
        localAddress: MuCuteAddress? = null,
        remoteAddress: MuCuteAddress,
        onSessionCreated: (MuCuteRelaySession) -> Unit
    ): MuCuteRelay {
        val adv = advertisement ?: MuCuteRelay.DefaultAdvertisement
        val local = localAddress ?: MuCuteAddress("0.0.0.0", 19132)
        return MuCuteRelay(local, adv).capture(remoteAddress, onSessionCreated)
    }
}