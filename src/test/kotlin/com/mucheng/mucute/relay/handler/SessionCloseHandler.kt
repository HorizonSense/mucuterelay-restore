package com.mucheng.mucute.relay.handler

import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler
import kotlin.jvm.internal.Intrinsics

@Metadata(
    mv = [2, 1, 0],
    k = 1,
    xi = 48,
    d1 = ["\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"],
    d2 = ["Lcom/mucheng/mucute/relay/handler/SessionCloseHandler;", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacketHandler;", "onSessionClose", "Lkotlin/Function1;", "", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "onDisconnect", "reason", "MuCuteRelay"]
)
class SessionCloseHandler(onSessionClose: Function1<in String?, Unit?>) : BedrockPacketHandler {
    private val onSessionClose: Function1<String?, Unit?>

    init {
        Intrinsics.checkNotNullParameter(onSessionClose, "onSessionClose")
        super()
        this.onSessionClose = onSessionClose
    }

    override fun onDisconnect(reason: String) {
        Intrinsics.checkNotNullParameter(reason, "reason")
        this.onSessionClose.invoke(reason)
    }
}
