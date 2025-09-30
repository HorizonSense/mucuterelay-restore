package com.mucheng.mucute.relay;

import com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay;
import com.mucheng.mucute.relay.handler.SessionCloseHandler;
import com.mucheng.mucute.relay.listener.MuCuteRelayPacketListener;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.PlatformDependent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.cloudburstmc.protocol.bedrock.BedrockClientSession;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.netty.BedrockPacketWrapper;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler;
import org.cloudburstmc.protocol.bedrock.packet.UnknownPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002'(B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fJ\u000e\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fJ\u000e\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fJ\u000e\u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00060\rR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR0\u0010\u0012\u001a\b\u0018\u00010\u0011R\u00020\u00002\f\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u0000@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR \u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"},
   d2 = {"Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "", "peer", "Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;", "subClientId", "", "muCuteRelay", "Lcom/mucheng/mucute/relay/MuCuteRelay;", "<init>", "(Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;ILcom/mucheng/mucute/relay/MuCuteRelay;)V", "getMuCuteRelay", "()Lcom/mucheng/mucute/relay/MuCuteRelay;", "server", "Lcom/mucheng/mucute/relay/MuCuteRelaySession$ServerSession;", "getServer", "()Lcom/mucheng/mucute/relay/MuCuteRelaySession$ServerSession;", "value", "Lcom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession;", "client", "getClient", "()Lcom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession;", "setClient$MuCuteRelay", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession;)V", "listeners", "", "Lcom/mucheng/mucute/relay/listener/MuCuteRelayPacketListener;", "getListeners", "()Ljava/util/List;", "packetQueue", "Ljava/util/Queue;", "Lkotlin/Pair;", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "", "clientBound", "", "packet", "clientBoundImmediately", "serverBound", "serverBoundImmediately", "ServerSession", "ClientSession", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nMuCuteRelaySession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MuCuteRelaySession.kt\ncom/mucheng/mucute/relay/MuCuteRelaySession\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,173:1\n1#2:174\n*E\n"})
public final class MuCuteRelaySession {
   @NotNull
   private final com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay muCuteRelay;
   @NotNull
   private final MuCuteRelaySession.ServerSession server;
   @Nullable
   private MuCuteRelaySession.ClientSession client;
   @NotNull
   private final List<MuCuteRelayPacketListener> listeners;
   @NotNull
   private final Queue<Pair<BedrockPacket, Boolean>> packetQueue;

   public MuCuteRelaySession(@NotNull BedrockPeer peer, int subClientId, @NotNull com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay muCuteRelay) {
      Intrinsics.checkNotNullParameter(peer, "peer");
      Intrinsics.checkNotNullParameter(muCuteRelay, "muCuteRelay");
      super();
      this.muCuteRelay = muCuteRelay;
      this.server = new MuCuteRelaySession.ServerSession(peer, subClientId);
      this.listeners = (List)(new ArrayList());
      Queue var10001 = PlatformDependent.newMpscQueue();
      Intrinsics.checkNotNullExpressionValue(var10001, "newMpscQueue(...)");
      this.packetQueue = var10001;
   }

   @NotNull
   public final MuCuteRelay getMuCuteRelay() {
      return this.muCuteRelay;
   }

   @NotNull
   public final MuCuteRelaySession.ServerSession getServer() {
      return this.server;
   }

   @Nullable
   public final MuCuteRelaySession.ClientSession getClient() {
      return this.client;
   }

   public final void setClient$MuCuteRelay(@Nullable MuCuteRelaySession.ClientSession value) {
      if (value != null) {
         MuCuteRelaySession.ClientSession it = value;
         int var3 = false;
         value.setCodec(this.server.getCodec());
         value.getPeer().getCodecHelper().setBlockDefinitions(this.server.getPeer().getCodecHelper().getBlockDefinitions());
         value.getPeer().getCodecHelper().setItemDefinitions(this.server.getPeer().getCodecHelper().getItemDefinitions());
         value.getPeer().getCodecHelper().setCameraPresetDefinitions(this.server.getPeer().getCodecHelper().getCameraPresetDefinitions());
         value.getPeer().getCodecHelper().setEncodingSettings(this.server.getPeer().getCodecHelper().getEncodingSettings());
         Object var4 = null;

         while(true) {
            Object var5 = this.packetQueue.poll();
            Pair packetPair = (Pair)var5;
            int var7 = false;
            if (var5 == null) {
               break;
            }

            if ((Boolean)packetPair.getSecond()) {
               it.sendPacketImmediately((BedrockPacket)packetPair.getFirst());
            } else {
               it.sendPacket((BedrockPacket)packetPair.getFirst());
            }
         }
      }

      this.client = value;
   }

   @NotNull
   public final List<MuCuteRelayPacketListener> getListeners() {
      return this.listeners;
   }

   public final void clientBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      this.server.sendPacket(packet);
   }

   public final void clientBoundImmediately(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      this.server.sendPacketImmediately(packet);
   }

   public final void serverBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      if (this.client != null) {
         MuCuteRelaySession.ClientSession var10000 = this.client;
         Intrinsics.checkNotNull(var10000);
         var10000.sendPacket(packet);
      } else {
         this.packetQueue.add(TuplesKt.to(packet, false));
      }

   }

   public final void serverBoundImmediately(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      if (this.client != null) {
         MuCuteRelaySession.ClientSession var10000 = this.client;
         Intrinsics.checkNotNull(var10000);
         var10000.sendPacketImmediately(packet);
      } else {
         this.packetQueue.add(TuplesKt.to(packet, true));
      }

   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\f"},
      d2 = {"Lcom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession;", "Lorg/cloudburstmc/protocol/bedrock/BedrockClientSession;", "peer", "Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;", "subClientId", "", "<init>", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession;Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;I)V", "onPacket", "", "wrapper", "Lorg/cloudburstmc/protocol/bedrock/netty/BedrockPacketWrapper;", "MuCuteRelay"}
   )
   @SourceDebugExtension({"SMAP\nMuCuteRelaySession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MuCuteRelaySession.kt\ncom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,173:1\n1863#2,2:174\n1863#2,2:176\n1863#2,2:178\n*S KotlinDebug\n*F\n+ 1 MuCuteRelaySession.kt\ncom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession\n*L\n143#1:174,2\n162#1:176,2\n131#1:178,2\n*E\n"})
   public final class ClientSession extends BedrockClientSession {
      public ClientSession(@NotNull BedrockPeer peer, int subClientId) {
         Intrinsics.checkNotNullParameter(peer, "peer");
         super(peer, subClientId);
         this.packetHandler = (BedrockPacketHandler)(new SessionCloseHandler(MuCuteRelaySession.ClientSession::_init_$lambda$1));
      }

      protected void onPacket(@NotNull BedrockPacketWrapper wrapper) {
         Intrinsics.checkNotNullParameter(wrapper, "wrapper");
         Iterable $this$forEach$iv = (Iterable) MuCuteRelaySession.this.getListeners();
         int $i$f$forEach = false;
         Iterator var4 = $this$forEach$iv.iterator();

         BedrockPacket var10001;
         while(var4.hasNext()) {
            Object element$iv = var4.next();
            MuCuteRelayPacketListener listener = (MuCuteRelayPacketListener)element$iv;
            boolean var7 = false;

            try {
               var10001 = wrapper.getPacket();
               Intrinsics.checkNotNullExpressionValue(var10001, "getPacket(...)");
               if (listener.beforeServerBound(var10001)) {
                  return;
               }
            } catch (Throwable var12) {
               System.out.println("Before server bound error: " + ExceptionsKt.stackTraceToString(var12));
            }
         }

         ByteBuf var10000 = wrapper.getPacketBuffer().retainedSlice().skipBytes(wrapper.getHeaderLength());
         Intrinsics.checkNotNullExpressionValue(var10000, "skipBytes(...)");
         ByteBuf buffer = var10000;
         UnknownPacket unknownPacket = new UnknownPacket();
         unknownPacket.setPayload(buffer);
         unknownPacket.setPacketId(wrapper.getPacketId());
         MuCuteRelaySession.this.clientBound((BedrockPacket)unknownPacket);
         Iterable $this$forEach$ivx = (Iterable) MuCuteRelaySession.this.getListeners();
         int $i$f$forEachx = false;
         Iterator var17 = $this$forEach$ivx.iterator();

         while(var17.hasNext()) {
            Object element$ivx = var17.next();
            MuCuteRelayPacketListener listenerx = (MuCuteRelayPacketListener)element$ivx;
            boolean var9 = false;

            try {
               var10001 = wrapper.getPacket();
               Intrinsics.checkNotNullExpressionValue(var10001, "getPacket(...)");
               listenerx.afterServerBound(var10001);
            } catch (Throwable var11) {
               System.out.println("After server bound error: " + ExceptionsKt.stackTraceToString(var11));
            }
         }

      }

      private static final Unit _init_$lambda$1(MuCuteRelaySession this$0, String it) {
         Intrinsics.checkNotNullParameter(it, "it");
         System.out.println("Server disconnect: " + it);

         try {
            this$0.getServer().disconnect();
            Iterable $this$forEach$iv = (Iterable)this$0.getListeners();
            int $i$f$forEach = false;
            Iterator var4 = $this$forEach$iv.iterator();

            while(var4.hasNext()) {
               Object element$iv = var4.next();
               MuCuteRelayPacketListener listener = (MuCuteRelayPacketListener)element$iv;
               boolean var7 = false;

               try {
                  listener.onDisconnect(it);
               } catch (Throwable var9) {
               }
            }
         } catch (Throwable var10) {
         }

         return Unit.INSTANCE;
      }
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\f"},
      d2 = {"Lcom/mucheng/mucute/relay/MuCuteRelaySession$ServerSession;", "Lorg/cloudburstmc/protocol/bedrock/BedrockServerSession;", "peer", "Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;", "subClientId", "", "<init>", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession;Lorg/cloudburstmc/protocol/bedrock/BedrockPeer;I)V", "onPacket", "", "wrapper", "Lorg/cloudburstmc/protocol/bedrock/netty/BedrockPacketWrapper;", "MuCuteRelay"}
   )
   @SourceDebugExtension({"SMAP\nMuCuteRelaySession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MuCuteRelaySession.kt\ncom/mucheng/mucute/relay/MuCuteRelaySession$ServerSession\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,173:1\n1863#2,2:174\n1863#2,2:176\n1863#2,2:178\n*S KotlinDebug\n*F\n+ 1 MuCuteRelaySession.kt\ncom/mucheng/mucute/relay/MuCuteRelaySession$ServerSession\n*L\n93#1:174,2\n112#1:176,2\n81#1:178,2\n*E\n"})
   public final class ServerSession extends BedrockServerSession {
      public ServerSession(@NotNull BedrockPeer peer, int subClientId) {
         Intrinsics.checkNotNullParameter(peer, "peer");
         super(peer, subClientId);
         this.packetHandler = (BedrockPacketHandler)(new SessionCloseHandler(MuCuteRelaySession.ServerSession::_init_$lambda$1));
      }

      protected void onPacket(@NotNull BedrockPacketWrapper wrapper) {
         Intrinsics.checkNotNullParameter(wrapper, "wrapper");
         Iterable $this$forEach$iv = (Iterable) MuCuteRelaySession.this.getListeners();
         int $i$f$forEach = false;
         Iterator var4 = $this$forEach$iv.iterator();

         BedrockPacket var10001;
         while(var4.hasNext()) {
            Object element$iv = var4.next();
            MuCuteRelayPacketListener listener = (MuCuteRelayPacketListener)element$iv;
            boolean var7 = false;

            try {
               var10001 = wrapper.getPacket();
               Intrinsics.checkNotNullExpressionValue(var10001, "getPacket(...)");
               if (listener.beforeClientBound(var10001)) {
                  return;
               }
            } catch (Throwable var12) {
               System.out.println("Before client bound error: " + ExceptionsKt.stackTraceToString(var12));
            }
         }

         ByteBuf var10000 = wrapper.getPacketBuffer().retainedSlice().skipBytes(wrapper.getHeaderLength());
         Intrinsics.checkNotNullExpressionValue(var10000, "skipBytes(...)");
         ByteBuf buffer = var10000;
         UnknownPacket unknownPacket = new UnknownPacket();
         unknownPacket.setPayload(buffer);
         unknownPacket.setPacketId(wrapper.getPacketId());
         MuCuteRelaySession.this.serverBound((BedrockPacket)unknownPacket);
         Iterable $this$forEach$ivx = (Iterable) MuCuteRelaySession.this.getListeners();
         int $i$f$forEachx = false;
         Iterator var17 = $this$forEach$ivx.iterator();

         while(var17.hasNext()) {
            Object element$ivx = var17.next();
            MuCuteRelayPacketListener listenerx = (MuCuteRelayPacketListener)element$ivx;
            boolean var9 = false;

            try {
               var10001 = wrapper.getPacket();
               Intrinsics.checkNotNullExpressionValue(var10001, "getPacket(...)");
               listenerx.afterClientBound(var10001);
            } catch (Throwable var11) {
               System.out.println("After client bound error: " + ExceptionsKt.stackTraceToString(var11));
            }
         }

      }

      private static final Unit _init_$lambda$1(MuCuteRelaySession this$0, String it) {
         Intrinsics.checkNotNullParameter(it, "it");
         System.out.println("Client disconnect: " + it);

         try {
            MuCuteRelaySession.ClientSession var10000 = this$0.getClient();
            if (var10000 != null) {
               var10000.disconnect();
            }

            Iterable $this$forEach$iv = (Iterable)this$0.getListeners();
            int $i$f$forEach = false;
            Iterator var4 = $this$forEach$iv.iterator();

            while(var4.hasNext()) {
               Object element$iv = var4.next();
               MuCuteRelayPacketListener listener = (MuCuteRelayPacketListener)element$iv;
               boolean var7 = false;

               try {
                  listener.onDisconnect(it);
               } catch (Throwable var9) {
               }
            }
         } catch (Throwable var10) {
         }

         return Unit.INSTANCE;
      }
   }
}
