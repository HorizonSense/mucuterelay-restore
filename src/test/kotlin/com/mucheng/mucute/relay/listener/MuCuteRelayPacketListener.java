package com.mucheng.mucute.relay.listener;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"},
   d2 = {"Lcom/mucheng/mucute/relay/listener/MuCuteRelayPacketListener;", "", "beforeClientBound", "", "packet", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "beforeServerBound", "afterClientBound", "", "afterServerBound", "onDisconnect", "reason", "", "MuCuteRelay"}
)
public interface MuCuteRelayPacketListener {
   boolean beforeClientBound(@NotNull BedrockPacket var1);

   boolean beforeServerBound(@NotNull BedrockPacket var1);

   void afterClientBound(@NotNull BedrockPacket var1);

   void afterServerBound(@NotNull BedrockPacket var1);

   void onDisconnect(@NotNull String var1);

   @Metadata(
      mv = {2, 1, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean beforeClientBound(@NotNull MuCuteRelayPacketListener $this, @NotNull BedrockPacket packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
         return false;
      }

      public static boolean beforeServerBound(@NotNull MuCuteRelayPacketListener $this, @NotNull BedrockPacket packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
         return false;
      }

      public static void afterClientBound(@NotNull MuCuteRelayPacketListener $this, @NotNull BedrockPacket packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
      }

      public static void afterServerBound(@NotNull MuCuteRelayPacketListener $this, @NotNull BedrockPacket packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
      }

      public static void onDisconnect(@NotNull MuCuteRelayPacketListener $this, @NotNull String reason) {
         Intrinsics.checkNotNullParameter(reason, "reason");
      }
   }
}
