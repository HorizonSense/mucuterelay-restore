package com.mucheng.mucute.relay;

import com.mucheng.mucute.relay.address.MuCuteAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.PacketDirection;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockChannelInitializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00032\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b\u0018J*\u0010\u0019\u001a\u00020\u00172\u001b\u0010\u0015\u001a\u0017\u0012\b\u0012\u00060\u001aR\u00020\u0010\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b\u0018H\u0000¢\u0006\u0002\b\u001bJ\u0006\u0010\u001c\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\u00020\t8F¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lcom/mucheng/mucute/relay/MuCuteRelay;", "", "localAddress", "Lcom/mucheng/mucute/relay/address/MuCuteAddress;", "advertisement", "Lorg/cloudburstmc/protocol/bedrock/BedrockPong;", "<init>", "(Lcom/mucheng/mucute/relay/address/MuCuteAddress;Lorg/cloudburstmc/protocol/bedrock/BedrockPong;)V", "isRunning", "", "isRunning$annotations", "()V", "()Z", "channelFuture", "Lio/netty/channel/ChannelFuture;", "muCuteRelaySession", "Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "remoteAddress", "eventLoopGroup", "Lio/netty/channel/EventLoopGroup;", "capture", "onSessionCreated", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "connectToServer", "Lcom/mucheng/mucute/relay/MuCuteRelaySession$ClientSession;", "connectToServer$MuCuteRelay", "disconnect", "Companion", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nMuCuteRelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MuCuteRelay.kt\ncom/mucheng/mucute/relay/MuCuteRelay\n+ 2 MuCuteAddress.kt\ncom/mucheng/mucute/relay/address/MuCuteAddressKt\n*L\n1#1,152:1\n8#2:153\n8#2:154\n*S KotlinDebug\n*F\n+ 1 MuCuteRelay.kt\ncom/mucheng/mucute/relay/MuCuteRelay\n*L\n96#1:153\n134#1:154\n*E\n"})
public final class MuCuteRelay {
   @NotNull
   public static final com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.Companion Companion = new com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final MuCuteAddress localAddress;
   @NotNull
   private final BedrockPong advertisement;
   @Nullable
   private ChannelFuture channelFuture;
   @Nullable
   private MuCuteRelaySession muCuteRelaySession;
   @Nullable
   private MuCuteAddress remoteAddress;
   @NotNull
   private final EventLoopGroup eventLoopGroup;
   @NotNull
   private static final BedrockCodec DefaultCodec;
   @NotNull
   private static final BedrockPong DefaultAdvertisement;

   public MuCuteRelay(@NotNull MuCuteAddress localAddress, @NotNull BedrockPong advertisement) {
      Intrinsics.checkNotNullParameter(localAddress, "localAddress");
      Intrinsics.checkNotNullParameter(advertisement, "advertisement");
      super();
      this.localAddress = localAddress;
      this.advertisement = advertisement;
      this.eventLoopGroup = (EventLoopGroup)(new NioEventLoopGroup());
   }

   // $FF: synthetic method
   public MuCuteRelay(MuCuteAddress var1, BedrockPong var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = new MuCuteAddress("0.0.0.0", 19132);
      }

      if ((var3 & 2) != 0) {
         var2 = DefaultAdvertisement;
      }

      this(var1, var2);
   }

   public final boolean isRunning() {
      return this.channelFuture != null;
   }

   /** @deprecated */
   // $FF: synthetic method
   public static void isRunning$annotations() {
   }

   @NotNull
   public final com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay capture(@NotNull MuCuteAddress remoteAddress, @NotNull final Function1<? super MuCuteRelaySession, Unit> onSessionCreated) {
      Intrinsics.checkNotNullParameter(remoteAddress, "remoteAddress");
      Intrinsics.checkNotNullParameter(onSessionCreated, "onSessionCreated");
      if (this.isRunning()) {
         return this;
      } else {
         this.remoteAddress = remoteAddress;
         this.advertisement.ipv4Port(this.localAddress.getPort()).ipv6Port(this.localAddress.getPort());
         ServerBootstrap var10000 = ((ServerBootstrap)((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).group(this.eventLoopGroup).channelFactory((ChannelFactory)RakChannelFactory.server(NioDatagramChannel.class))).option(RakChannelOption.RAK_ADVERTISEMENT, this.advertisement.toByteBuf())).option(RakChannelOption.RAK_GUID, Random.Default.nextLong())).childHandler((ChannelHandler)(new BedrockChannelInitializer<MuCuteRelaySession.ServerSession>() {
            protected MuCuteRelaySession.ServerSession createSession0(BedrockPeer peer, int subClientId) {
               Intrinsics.checkNotNullParameter(peer, "peer");
               MuCuteRelaySession var3 = new MuCuteRelaySession(peer, subClientId, com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.this);
               com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay var4 = com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.this;
               Function1 var5 = onSessionCreated;
               int var7 = false;
               var4.muCuteRelaySession = var3;
               var5.invoke(var3);
               return var3.getServer();
            }

            protected void initSession(MuCuteRelaySession.ServerSession session) {
               Intrinsics.checkNotNullParameter(session, "session");
            }

            protected void preInitChannel(Channel channel) {
               Intrinsics.checkNotNullParameter(channel, "channel");
               channel.attr(PacketDirection.ATTRIBUTE).set(PacketDirection.CLIENT_BOUND);
               super.preInitChannel(channel);
            }
         }));
         MuCuteAddress $this$inetSocketAddress$iv = this.localAddress;
         int $i$f$getInetSocketAddress = false;
         ChannelFuture var6 = ((ServerBootstrap)var10000.localAddress((SocketAddress)(new InetSocketAddress($this$inetSocketAddress$iv.getHostName(), $this$inetSocketAddress$iv.getPort())))).bind().awaitUninterruptibly();
         int var5 = false;
         var6.channel().pipeline().remove("rak-server-rate-limiter");
         this.channelFuture = var6;
         return this;
      }
   }

   // $FF: synthetic method
   public static com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay capture$default(com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay var0, MuCuteAddress var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = new MuCuteAddress("geo.hivebedrock.network", 19132);
      }

      return var0.capture(var1, var2);
   }

   public final void connectToServer$MuCuteRelay(@NotNull final Function1<? super MuCuteRelaySession.ClientSession, Unit> onSessionCreated) {
      Intrinsics.checkNotNullParameter(onSessionCreated, "onSessionCreated");
      long clientGUID = Random.Default.nextLong();
      Bootstrap var10000 = (Bootstrap)((Bootstrap)(new Bootstrap()).group(this.eventLoopGroup)).channelFactory((ChannelFactory)RakChannelFactory.client(NioDatagramChannel.class));
      ChannelOption var10001 = RakChannelOption.RAK_PROTOCOL_VERSION;
      MuCuteRelaySession var10002 = this.muCuteRelaySession;
      Intrinsics.checkNotNull(var10002);
      var10000 = (Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)var10000.option(var10001, var10002.getServer().getCodec().getRaknetProtocolVersion())).option(RakChannelOption.RAK_GUID, clientGUID)).option(RakChannelOption.RAK_REMOTE_GUID, clientGUID)).option(RakChannelOption.RAK_CONNECT_TIMEOUT, 200000L)).handler((ChannelHandler)(new BedrockChannelInitializer<MuCuteRelaySession.ClientSession>() {
         protected MuCuteRelaySession.ClientSession createSession0(BedrockPeer peer, int subClientId) {
            Intrinsics.checkNotNullParameter(peer, "peer");
            MuCuteRelaySession var10002 = com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.this.muCuteRelaySession;
            Intrinsics.checkNotNull(var10002);
            return var10002.new ClientSession(peer, subClientId);
         }

         protected void initSession(MuCuteRelaySession.ClientSession clientSession) {
            Intrinsics.checkNotNullParameter(clientSession, "clientSession");
            MuCuteRelaySession var10000 = com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.this.muCuteRelaySession;
            Intrinsics.checkNotNull(var10000);
            var10000.setClient$MuCuteRelay(clientSession);
            onSessionCreated.invoke(clientSession);
         }

         protected void preInitChannel(Channel channel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            channel.attr(PacketDirection.ATTRIBUTE).set(PacketDirection.SERVER_BOUND);
            super.preInitChannel(channel);
         }
      }));
      MuCuteAddress var6 = this.remoteAddress;
      Intrinsics.checkNotNull(var6);
      MuCuteAddress $this$inetSocketAddress$iv = var6;
      int $i$f$getInetSocketAddress = false;
      var10000.remoteAddress((SocketAddress)(new InetSocketAddress($this$inetSocketAddress$iv.getHostName(), $this$inetSocketAddress$iv.getPort()))).connect().awaitUninterruptibly();
   }

   public final void disconnect() {
      if (this.isRunning()) {
         ChannelFuture var10000 = this.channelFuture;
         if (var10000 != null) {
            Channel var4 = var10000.channel();
            if (var4 != null) {
               Channel var1 = var4;
               int var3 = false;
               var1.close().awaitUninterruptibly();
               var1.parent().close().awaitUninterruptibly();
            }
         }

         this.channelFuture = null;
         this.muCuteRelaySession = null;
      }
   }

   public MuCuteRelay() {
      this((MuCuteAddress)null, (BedrockPong)null, 3, (DefaultConstructorMarker)null);
   }

   static {
      BedrockCodec var10000 = Bedrock_v776.CODEC;
      Intrinsics.checkNotNullExpressionValue(var10000, "CODEC");
      DefaultCodec = var10000;
      BedrockPong var0 = (new BedrockPong()).edition("MCPE").gameType("Survival").version(DefaultCodec.getMinecraftVersion()).protocolVersion(DefaultCodec.getProtocolVersion()).motd("MuCuteRelay").playerCount(0).maximumPlayerCount(20).subMotd("t.me/mucuteclient").nintendoLimited(false);
      Intrinsics.checkNotNullExpressionValue(var0, "nintendoLimited(...)");
      DefaultAdvertisement = var0;
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"},
      d2 = {"Lcom/mucheng/mucute/relay/MuCuteRelay$Companion;", "", "<init>", "()V", "DefaultCodec", "Lorg/cloudburstmc/protocol/bedrock/codec/BedrockCodec;", "getDefaultCodec", "()Lorg/cloudburstmc/protocol/bedrock/codec/BedrockCodec;", "DefaultAdvertisement", "Lorg/cloudburstmc/protocol/bedrock/BedrockPong;", "getDefaultAdvertisement", "()Lorg/cloudburstmc/protocol/bedrock/BedrockPong;", "MuCuteRelay"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final BedrockCodec getDefaultCodec() {
         return com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.DefaultCodec;
      }

      @NotNull
      public final BedrockPong getDefaultAdvertisement() {
         return com.mucheng.mucute.relay.com.mucheng.mucute.relay.MuCuteRelay.DefaultAdvertisement;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
