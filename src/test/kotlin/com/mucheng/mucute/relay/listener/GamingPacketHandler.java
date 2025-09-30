package com.mucheng.mucute.relay.listener;

import com.mucheng.mucute.relay.MuCuteRelaySession;
import com.mucheng.mucute.relay.definition.CameraPresetDefinition;
import com.mucheng.mucute.relay.definition.Definitions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPreset;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.CameraPresetsPacket;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"},
   d2 = {"Lcom/mucheng/mucute/relay/listener/GamingPacketHandler;", "Lcom/mucheng/mucute/relay/listener/MuCuteRelayPacketListener;", "muCuteRelaySession", "Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "<init>", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession;)V", "getMuCuteRelaySession", "()Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "beforeServerBound", "", "packet", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "MuCuteRelay"}
)
public final class GamingPacketHandler implements MuCuteRelayPacketListener {
   @NotNull
   private final MuCuteRelaySession muCuteRelaySession;

   public GamingPacketHandler(@NotNull MuCuteRelaySession muCuteRelaySession) {
      Intrinsics.checkNotNullParameter(muCuteRelaySession, "muCuteRelaySession");
      super();
      this.muCuteRelaySession = muCuteRelaySession;
   }

   @NotNull
   public final MuCuteRelaySession getMuCuteRelaySession() {
      return this.muCuteRelaySession;
   }

   public boolean beforeServerBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      MuCuteRelaySession.ClientSession var10000;
      if (packet instanceof StartGamePacket) {
         Definitions.INSTANCE.setItemDefinitions((DefinitionRegistry)SimpleDefinitionRegistry.builder().addAll((Collection)((StartGamePacket)packet).getItemDefinitions()).build());
         var10000 = this.muCuteRelaySession.getClient();
         Intrinsics.checkNotNull(var10000);
         var10000.getPeer().getCodecHelper().setItemDefinitions(Definitions.INSTANCE.getItemDefinitions());
         this.muCuteRelaySession.getServer().getPeer().getCodecHelper().setItemDefinitions(Definitions.INSTANCE.getItemDefinitions());
         if (((StartGamePacket)packet).isBlockNetworkIdsHashed()) {
            var10000 = this.muCuteRelaySession.getClient();
            Intrinsics.checkNotNull(var10000);
            var10000.getPeer().getCodecHelper().setBlockDefinitions(Definitions.INSTANCE.getBlockDefinitionsHashed());
            this.muCuteRelaySession.getServer().getPeer().getCodecHelper().setBlockDefinitions(Definitions.INSTANCE.getBlockDefinitionsHashed());
         } else {
            var10000 = this.muCuteRelaySession.getClient();
            Intrinsics.checkNotNull(var10000);
            var10000.getPeer().getCodecHelper().setBlockDefinitions(Definitions.INSTANCE.getBlockDefinitions());
            this.muCuteRelaySession.getServer().getPeer().getCodecHelper().setBlockDefinitions(Definitions.INSTANCE.getBlockDefinitions());
         }
      }

      if (packet instanceof CameraPresetsPacket) {
         SimpleDefinitionRegistry.Builder var11 = SimpleDefinitionRegistry.builder();
         int var3 = ((CameraPresetsPacket)packet).getPresets().size();
         SimpleDefinitionRegistry.Builder var9 = var11;
         ArrayList var4 = new ArrayList(var3);

         for(int var5 = 0; var5 < var3; ++var5) {
            int var8 = false;
            CameraPresetDefinition.Companion var12 = CameraPresetDefinition.Companion;
            Object var10001 = ((CameraPresetsPacket)packet).getPresets().get(var5);
            Intrinsics.checkNotNullExpressionValue(var10001, "get(...)");
            var4.add(var12.fromCameraPreset((CameraPreset)var10001, var5));
         }

         SimpleDefinitionRegistry cameraDefinitions = var9.addAll((Collection)((List)var4)).build();
         var10000 = this.muCuteRelaySession.getClient();
         Intrinsics.checkNotNull(var10000);
         var10000.getPeer().getCodecHelper().setCameraPresetDefinitions((DefinitionRegistry)cameraDefinitions);
         this.muCuteRelaySession.getServer().getPeer().getCodecHelper().setCameraPresetDefinitions((DefinitionRegistry)cameraDefinitions);
      }

      return false;
   }

   public boolean beforeClientBound(@NotNull BedrockPacket packet) {
      return MuCuteRelayPacketListener.DefaultImpls.beforeClientBound(this, packet);
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
}
