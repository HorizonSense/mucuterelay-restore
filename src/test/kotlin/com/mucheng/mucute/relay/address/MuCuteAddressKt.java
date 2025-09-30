package com.mucheng.mucute.relay.address;

import java.net.InetSocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0002*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"},
   d2 = {"inetSocketAddress", "Ljava/net/InetSocketAddress;", "Lcom/mucheng/mucute/relay/address/MuCuteAddress;", "getInetSocketAddress", "(Lcom/mucheng/mucute/relay/address/MuCuteAddress;)Ljava/net/InetSocketAddress;", "muCuteAddress", "getMuCuteAddress", "(Ljava/net/InetSocketAddress;)Lcom/mucheng/mucute/relay/address/MuCuteAddress;", "MuCuteRelay"}
)
public final class MuCuteAddressKt {
   @NotNull
   public static final InetSocketAddress getInetSocketAddress(@NotNull MuCuteAddress $this$inetSocketAddress) {
      Intrinsics.checkNotNullParameter($this$inetSocketAddress, "<this>");
      int $i$f$getInetSocketAddress = false;
      return new InetSocketAddress($this$inetSocketAddress.getHostName(), $this$inetSocketAddress.getPort());
   }

   @NotNull
   public static final MuCuteAddress getMuCuteAddress(@NotNull InetSocketAddress $this$muCuteAddress) {
      Intrinsics.checkNotNullParameter($this$muCuteAddress, "<this>");
      int $i$f$getMuCuteAddress = false;
      String var10002 = $this$muCuteAddress.getHostName();
      Intrinsics.checkNotNullExpressionValue(var10002, "getHostName(...)");
      return new MuCuteAddress(var10002, $this$muCuteAddress.getPort());
   }
}
