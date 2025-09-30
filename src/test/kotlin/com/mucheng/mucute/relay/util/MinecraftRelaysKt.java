package com.mucheng.mucute.relay.util;

import MuCuteRelay;
import com.mucheng.mucute.relay.MuCuteRelaySession;
import com.mucheng.mucute.relay.address.MuCuteAddress;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¨\u0006\f"},
   d2 = {"captureMuCuteRelay", "Lcom/mucheng/mucute/relay/MuCuteRelay;", "advertisement", "Lorg/cloudburstmc/protocol/bedrock/BedrockPong;", "localAddress", "Lcom/mucheng/mucute/relay/address/MuCuteAddress;", "remoteAddress", "onSessionCreated", "Lkotlin/Function1;", "Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "", "Lkotlin/ExtensionFunctionType;", "MuCuteRelay"}
)
public final class MinecraftRelaysKt {
   @NotNull
   public static final MuCuteRelay captureMuCuteRelay(@NotNull BedrockPong advertisement, @NotNull MuCuteAddress localAddress, @NotNull MuCuteAddress remoteAddress, @NotNull Function1<? super MuCuteRelaySession, Unit> onSessionCreated) {
      Intrinsics.checkNotNullParameter(advertisement, "advertisement");
      Intrinsics.checkNotNullParameter(localAddress, "localAddress");
      Intrinsics.checkNotNullParameter(remoteAddress, "remoteAddress");
      Intrinsics.checkNotNullParameter(onSessionCreated, "onSessionCreated");
      return (new MuCuteRelay(localAddress, advertisement)).capture(remoteAddress, onSessionCreated);
   }

   // $FF: synthetic method
   public static MuCuteRelay captureMuCuteRelay$default(BedrockPong var0, MuCuteAddress var1, MuCuteAddress var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var0 = MuCuteRelay.Companion.getDefaultAdvertisement();
      }

      if ((var4 & 2) != 0) {
         var1 = new MuCuteAddress("0.0.0.0", 19132);
      }

      return captureMuCuteRelay(var0, var1, var2, var3);
   }
}
