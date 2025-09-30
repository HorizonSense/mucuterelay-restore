package com.mucheng.mucute.relay.listener;

import com.mucheng.mucute.relay.MuCuteRelaySession;
import com.mucheng.mucute.relay.util.AuthUtils;
import com.mucheng.mucute.relay.util.IXboxIdentityTokenCache;
import com.mucheng.mucute.relay.util.JWTUtilsKt;
import com.mucheng.mucute.relay.util.XboxDeviceInfo;
import com.mucheng.mucute.relay.util.XboxIdentityToken;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00148BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006!"},
   d2 = {"Lcom/mucheng/mucute/relay/listener/XboxLoginPacketListener;", "Lcom/mucheng/mucute/relay/listener/EncryptedLoginPacketListener;", "accessToken", "Lkotlin/Function0;", "", "deviceInfo", "Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "<init>", "(Lkotlin/jvm/functions/Function0;Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;)V", "getAccessToken", "()Lkotlin/jvm/functions/Function0;", "getDeviceInfo", "()Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "tokenCache", "Lcom/mucheng/mucute/relay/util/IXboxIdentityTokenCache;", "getTokenCache", "()Lcom/mucheng/mucute/relay/util/IXboxIdentityTokenCache;", "setTokenCache", "(Lcom/mucheng/mucute/relay/util/IXboxIdentityTokenCache;)V", "identityToken", "Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "getIdentityToken", "()Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "chain", "", "getChain", "()Ljava/util/List;", "forceFetchChain", "", "beforeClientBound", "", "packet", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "MuCuteRelay"}
)
public final class XboxLoginPacketListener extends EncryptedLoginPacketListener {
   @NotNull
   private final Function0<String> accessToken;
   @NotNull
   private final XboxDeviceInfo deviceInfo;
   @Nullable
   private IXboxIdentityTokenCache tokenCache;
   @NotNull
   private XboxIdentityToken identityToken;

   public XboxLoginPacketListener(@NotNull Function0<String> accessToken, @NotNull XboxDeviceInfo deviceInfo) {
      Intrinsics.checkNotNullParameter(accessToken, "accessToken");
      Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
      super();
      this.accessToken = accessToken;
      this.deviceInfo = deviceInfo;
      this.identityToken = new XboxIdentityToken("", 0L);
   }

   @NotNull
   public final Function0<String> getAccessToken() {
      return this.accessToken;
   }

   @NotNull
   public final XboxDeviceInfo getDeviceInfo() {
      return this.deviceInfo;
   }

   @Nullable
   public final IXboxIdentityTokenCache getTokenCache() {
      return this.tokenCache;
   }

   public final void setTokenCache(@Nullable IXboxIdentityTokenCache var1) {
      this.tokenCache = var1;
   }

   private final XboxIdentityToken getIdentityToken() {
      if (this.identityToken.getNotAfter() < System.currentTimeMillis() / (long)1000) {
         XboxLoginPacketListener var7;
         XboxIdentityToken var8;
         label23: {
            IXboxIdentityTokenCache var10001 = this.tokenCache;
            XboxIdentityToken var1;
            boolean var3;
            if (var10001 != null) {
               var8 = var10001.checkCache(this.deviceInfo);
               if (var8 != null) {
                  var1 = var8;
                  var3 = false;
                  System.out.println("Token cache hit");
                  var7 = this;
                  var8 = var1;
                  break label23;
               }
            }

            var1 = AuthUtils.fetchIdentityToken((String)this.accessToken.invoke(), this.deviceInfo);
            var3 = false;
            IXboxIdentityTokenCache var10000 = this.tokenCache;
            if (var10000 != null) {
               IXboxIdentityTokenCache cache = var10000;
               int var5 = false;
               System.out.println("Saving token cache");
               cache.cache(this.deviceInfo, var1);
            }

            var7 = this;
            var8 = var1;
         }

         var7.identityToken = var8;
      }

      return this.identityToken;
   }

   private final List<String> getChain() {
      return AuthUtils.fetchChain(this.getIdentityToken().getToken(), this.getKeyPair());
   }

   public final void forceFetchChain() {
      this.getChain();
   }

   public boolean beforeClientBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      if (packet instanceof LoginPacket) {
         try {
            ((LoginPacket)packet).getChain().clear();
            ((LoginPacket)packet).getChain().addAll((Collection)this.getChain());
            LoginPacket var8 = (LoginPacket)packet;
            String var10001 = ((LoginPacket)packet).getExtra();
            Intrinsics.checkNotNullExpressionValue(var10001, "getExtra(...)");
            CharSequence var9 = (CharSequence)var10001;
            char[] var2 = new char[]{'.'};
            var8.setExtra(JWTUtilsKt.signJWT((String)StringsKt.split$default(var9, var2, false, 0, 6, (Object)null).get(1), this.getKeyPair(), true));
            System.out.println("Login success");
         } catch (Throwable var7) {
            MuCuteRelaySession var10000 = this.getMuCuteRelaySession();
            DisconnectPacket var3 = new DisconnectPacket();
            MuCuteRelaySession var6 = var10000;
            int var5 = false;
            var3.setKickMessage(var7.toString());
            var6.clientBound((BedrockPacket)var3);
            System.out.println("Login failed: " + var7);
         }

         this.setLoginPacket((LoginPacket)packet);
         this.connectServer();
         return true;
      } else {
         return false;
      }
   }
}
