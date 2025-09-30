package com.mucheng.mucute.relay.listener;

import MuCuteRelay;
import com.mucheng.mucute.relay.MuCuteRelaySession;
import com.mucheng.mucute.relay.definition.Definitions;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.v291.Bedrock_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.Bedrock_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.Bedrock_v332;
import org.cloudburstmc.protocol.bedrock.codec.v340.Bedrock_v340;
import org.cloudburstmc.protocol.bedrock.codec.v354.Bedrock_v354;
import org.cloudburstmc.protocol.bedrock.codec.v361.Bedrock_v361;
import org.cloudburstmc.protocol.bedrock.codec.v388.Bedrock_v388;
import org.cloudburstmc.protocol.bedrock.codec.v389.Bedrock_v389;
import org.cloudburstmc.protocol.bedrock.codec.v390.Bedrock_v390;
import org.cloudburstmc.protocol.bedrock.codec.v407.Bedrock_v407;
import org.cloudburstmc.protocol.bedrock.codec.v408.Bedrock_v408;
import org.cloudburstmc.protocol.bedrock.codec.v419.Bedrock_v419;
import org.cloudburstmc.protocol.bedrock.codec.v422.Bedrock_v422;
import org.cloudburstmc.protocol.bedrock.codec.v428.Bedrock_v428;
import org.cloudburstmc.protocol.bedrock.codec.v431.Bedrock_v431;
import org.cloudburstmc.protocol.bedrock.codec.v440.Bedrock_v440;
import org.cloudburstmc.protocol.bedrock.codec.v448.Bedrock_v448;
import org.cloudburstmc.protocol.bedrock.codec.v465.Bedrock_v465;
import org.cloudburstmc.protocol.bedrock.codec.v471.Bedrock_v471;
import org.cloudburstmc.protocol.bedrock.codec.v475.Bedrock_v475;
import org.cloudburstmc.protocol.bedrock.codec.v486.Bedrock_v486;
import org.cloudburstmc.protocol.bedrock.codec.v503.Bedrock_v503;
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527;
import org.cloudburstmc.protocol.bedrock.codec.v534.Bedrock_v534;
import org.cloudburstmc.protocol.bedrock.codec.v544.Bedrock_v544;
import org.cloudburstmc.protocol.bedrock.codec.v545.Bedrock_v545;
import org.cloudburstmc.protocol.bedrock.codec.v554.Bedrock_v554;
import org.cloudburstmc.protocol.bedrock.codec.v557.Bedrock_v557;
import org.cloudburstmc.protocol.bedrock.codec.v560.Bedrock_v560;
import org.cloudburstmc.protocol.bedrock.codec.v567.Bedrock_v567;
import org.cloudburstmc.protocol.bedrock.codec.v568.Bedrock_v568;
import org.cloudburstmc.protocol.bedrock.codec.v575.Bedrock_v575;
import org.cloudburstmc.protocol.bedrock.codec.v582.Bedrock_v582;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;
import org.cloudburstmc.protocol.bedrock.codec.v594.Bedrock_v594;
import org.cloudburstmc.protocol.bedrock.codec.v618.Bedrock_v618;
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622;
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630;
import org.cloudburstmc.protocol.bedrock.codec.v649.Bedrock_v649;
import org.cloudburstmc.protocol.bedrock.codec.v662.Bedrock_v662;
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671;
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685;
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686;
import org.cloudburstmc.protocol.bedrock.codec.v712.Bedrock_v712;
import org.cloudburstmc.protocol.bedrock.codec.v729.Bedrock_v729;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.InventoryContentSerializer_v729;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.InventorySlotSerializer_v729;
import org.cloudburstmc.protocol.bedrock.codec.v748.Bedrock_v748;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"},
   d2 = {"Lcom/mucheng/mucute/relay/listener/AutoCodecPacketListener;", "Lcom/mucheng/mucute/relay/listener/MuCuteRelayPacketListener;", "muCuteRelaySession", "Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "patchCodec", "", "<init>", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession;Z)V", "getMuCuteRelaySession", "()Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "getPatchCodec", "()Z", "patchCodecIfNeeded", "Lorg/cloudburstmc/protocol/bedrock/codec/BedrockCodec;", "codec", "beforeClientBound", "packet", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "Companion", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nAutoCodecPacketListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AutoCodecPacketListener.kt\ncom/mucheng/mucute/relay/listener/AutoCodecPacketListener\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,147:1\n8634#2,2:148\n8894#2,4:150\n*S KotlinDebug\n*F\n+ 1 AutoCodecPacketListener.kt\ncom/mucheng/mucute/relay/listener/AutoCodecPacketListener\n*L\n88#1:148,2\n88#1:150,4\n*E\n"})
public final class AutoCodecPacketListener implements MuCuteRelayPacketListener {
   @NotNull
   public static final AutoCodecPacketListener.Companion Companion = new AutoCodecPacketListener.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final MuCuteRelaySession muCuteRelaySession;
   private final boolean patchCodec;
   @NotNull
   private static final Lazy<Map<Integer, BedrockCodec>> protocols$delegate = LazyKt.lazy(AutoCodecPacketListener::protocols_delegate$lambda$2);

   public AutoCodecPacketListener(@NotNull MuCuteRelaySession muCuteRelaySession, boolean patchCodec) {
      Intrinsics.checkNotNullParameter(muCuteRelaySession, "muCuteRelaySession");
      super();
      this.muCuteRelaySession = muCuteRelaySession;
      this.patchCodec = patchCodec;
   }

   // $FF: synthetic method
   public AutoCodecPacketListener(MuCuteRelaySession var1, boolean var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = true;
      }

      this(var1, var2);
   }

   @NotNull
   public final MuCuteRelaySession getMuCuteRelaySession() {
      return this.muCuteRelaySession;
   }

   public final boolean getPatchCodec() {
      return this.patchCodec;
   }

   private final BedrockCodec patchCodecIfNeeded(BedrockCodec codec) {
      BedrockCodec var10000;
      if (this.patchCodec && codec.getProtocolVersion() > 729) {
         BedrockCodec var2 = codec.toBuilder().updateSerializer(InventoryContentPacket.class, (BedrockPacketSerializer)InventoryContentSerializer_v729.INSTANCE).updateSerializer(InventorySlotPacket.class, (BedrockPacketSerializer)InventorySlotSerializer_v729.INSTANCE).build();
         Intrinsics.checkNotNull(var2);
         var10000 = var2;
      } else {
         var10000 = codec;
      }

      return var10000;
   }

   public boolean beforeClientBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      if (packet instanceof RequestNetworkSettingsPacket) {
         int protocolVersion = ((RequestNetworkSettingsPacket)packet).getProtocolVersion();
         BedrockCodec bedrockCodec = this.patchCodecIfNeeded(Companion.pickProtocolCodec(protocolVersion));
         this.muCuteRelaySession.getServer().setCodec(bedrockCodec);
         BedrockCodecHelper var4 = this.muCuteRelaySession.getServer().getPeer().getCodecHelper();
         int var6 = false;
         var4.setItemDefinitions(Definitions.INSTANCE.getItemDefinitions());
         var4.setBlockDefinitions(Definitions.INSTANCE.getBlockDefinitions());
         var4.setCameraPresetDefinitions(Definitions.INSTANCE.getCameraPresetDefinitions());
         var4.setEncodingSettings(EncodingSettings.builder().maxListSize(Integer.MAX_VALUE).maxByteArraySize(Integer.MAX_VALUE).maxNetworkNBTSize(Integer.MAX_VALUE).maxItemNBTSize(Integer.MAX_VALUE).maxStringLength(Integer.MAX_VALUE).build());
         NetworkSettingsPacket networkSettingsPacket = new NetworkSettingsPacket();
         networkSettingsPacket.setCompressionThreshold(0);
         networkSettingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
         this.muCuteRelaySession.clientBoundImmediately((BedrockPacket)networkSettingsPacket);
         this.muCuteRelaySession.getServer().setCompression(PacketCompressionAlgorithm.ZLIB);
         return true;
      } else {
         return false;
      }
   }

   public boolean beforeServerBound(@NotNull BedrockPacket packet) {
      return MuCuteRelayPacketListener.DefaultImpls.beforeServerBound(this, packet);
   }

   public void afterClientBound(@NotNull BedrockPacket packet) {
      MuCuteRelayPacketListener.DefaultImpls.afterClientBound(this, packet);
   }

   public void afterServerBound(@NotNull BedrockPacket packet) {
      MuCuteRelayPacketListener.DefaultImpls.afterServerBound(this, packet);
   }

   public void onDisconnect(@NotNull String reason) {
      MuCuteRelayPacketListener.DefaultImpls.onDisconnect(this, reason);
   }

   private static final Map protocols_delegate$lambda$2() {
      BedrockCodec[] var0 = new BedrockCodec[]{Bedrock_v291.CODEC, Bedrock_v313.CODEC, Bedrock_v332.CODEC, Bedrock_v340.CODEC, Bedrock_v354.CODEC, Bedrock_v361.CODEC, Bedrock_v388.CODEC, Bedrock_v389.CODEC, Bedrock_v390.CODEC, Bedrock_v407.CODEC, Bedrock_v408.CODEC, Bedrock_v419.CODEC, Bedrock_v422.CODEC, Bedrock_v428.CODEC, Bedrock_v431.CODEC, Bedrock_v440.CODEC, Bedrock_v448.CODEC, Bedrock_v465.CODEC, Bedrock_v471.CODEC, Bedrock_v475.CODEC, Bedrock_v486.CODEC, Bedrock_v503.CODEC, Bedrock_v527.CODEC, Bedrock_v534.CODEC, Bedrock_v544.CODEC, Bedrock_v545.CODEC, Bedrock_v554.CODEC, Bedrock_v557.CODEC, Bedrock_v560.CODEC, Bedrock_v567.CODEC, Bedrock_v568.CODEC, Bedrock_v575.CODEC, Bedrock_v582.CODEC, Bedrock_v589.CODEC, Bedrock_v594.CODEC, Bedrock_v618.CODEC, Bedrock_v622.CODEC, Bedrock_v630.CODEC, Bedrock_v649.CODEC, Bedrock_v662.CODEC, Bedrock_v671.CODEC, Bedrock_v685.CODEC, Bedrock_v686.CODEC, Bedrock_v712.CODEC, Bedrock_v729.CODEC, Bedrock_v748.CODEC, Bedrock_v766.CODEC, Bedrock_v776.CODEC};
      int $i$f$associateBy = false;
      int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(var0.length), 16);
      BedrockCodec[] $this$associateByTo$iv$iv = var0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(capacity$iv));
      int $i$f$associateByTo = false;
      int var6 = 0;

      for(int var7 = var0.length; var6 < var7; ++var6) {
         Object element$iv$iv = $this$associateByTo$iv$iv[var6];
         int var10 = false;
         destination$iv$iv.put(element$iv$iv.getProtocolVersion(), element$iv$iv);
      }

      return destination$iv$iv;
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0006H\u0002R/\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u000f"},
      d2 = {"Lcom/mucheng/mucute/relay/listener/AutoCodecPacketListener$Companion;", "", "<init>", "()V", "protocols", "", "", "Lorg/cloudburstmc/protocol/bedrock/codec/BedrockCodec;", "kotlin.jvm.PlatformType", "getProtocols", "()Ljava/util/Map;", "protocols$delegate", "Lkotlin/Lazy;", "pickProtocolCodec", "protocolVersion", "MuCuteRelay"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final Map<Integer, BedrockCodec> getProtocols() {
         Lazy var1 = AutoCodecPacketListener.protocols$delegate;
         return (Map)var1.getValue();
      }

      private final BedrockCodec pickProtocolCodec(int protocolVersion) {
         BedrockCodec bedrockCodec = MuCuteRelay.Companion.getDefaultCodec();

         BedrockCodec codec;
         for(Iterator var3 = this.getProtocols().entrySet().iterator(); var3.hasNext(); bedrockCodec = codec) {
            Entry var4 = (Entry)var3.next();
            int version = ((Number)var4.getKey()).intValue();
            codec = (BedrockCodec)var4.getValue();
            if (version > protocolVersion) {
               break;
            }
         }

         BedrockCodec var10000 = bedrockCodec.toBuilder().build();
         Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
         return var10000;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
