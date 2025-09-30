package com.mucheng.mucute.relay.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mucheng.mucute.relay.MuCuteRelaySession;
import com.mucheng.mucute.relay.util.AuthUtils;
import com.mucheng.mucute.relay.util.JWTUtilsKt;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerToClientHandshakePacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0004R\u001a\u0010\u0004\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001d"},
   d2 = {"Lcom/mucheng/mucute/relay/listener/EncryptedLoginPacketListener;", "Lcom/mucheng/mucute/relay/listener/MuCuteRelayPacketListener;", "<init>", "()V", "keyPair", "Ljava/security/KeyPair;", "getKeyPair", "()Ljava/security/KeyPair;", "setKeyPair", "(Ljava/security/KeyPair;)V", "loginPacket", "Lorg/cloudburstmc/protocol/bedrock/packet/LoginPacket;", "getLoginPacket", "()Lorg/cloudburstmc/protocol/bedrock/packet/LoginPacket;", "setLoginPacket", "(Lorg/cloudburstmc/protocol/bedrock/packet/LoginPacket;)V", "muCuteRelaySession", "Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "getMuCuteRelaySession", "()Lcom/mucheng/mucute/relay/MuCuteRelaySession;", "setMuCuteRelaySession", "(Lcom/mucheng/mucute/relay/MuCuteRelaySession;)V", "beforeClientBound", "", "packet", "Lorg/cloudburstmc/protocol/bedrock/packet/BedrockPacket;", "beforeServerBound", "connectServer", "", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nEncryptedLoginPacketListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EncryptedLoginPacketListener.kt\ncom/mucheng/mucute/relay/listener/EncryptedLoginPacketListener\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,89:1\n1863#2,2:90\n1#3:92\n*S KotlinDebug\n*F\n+ 1 EncryptedLoginPacketListener.kt\ncom/mucheng/mucute/relay/listener/EncryptedLoginPacketListener\n*L\n26#1:90,2\n*E\n"})
public class EncryptedLoginPacketListener implements MuCuteRelayPacketListener {
   @NotNull
   private KeyPair keyPair;
   @Nullable
   private LoginPacket loginPacket;
   public MuCuteRelaySession muCuteRelaySession;

   public EncryptedLoginPacketListener() {
      KeyPair var10001 = EncryptionUtils.createKeyPair();
      Intrinsics.checkNotNullExpressionValue(var10001, "createKeyPair(...)");
      this.keyPair = var10001;
   }

   @NotNull
   protected final KeyPair getKeyPair() {
      return this.keyPair;
   }

   protected final void setKeyPair(@NotNull KeyPair var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.keyPair = var1;
   }

   @Nullable
   protected final LoginPacket getLoginPacket() {
      return this.loginPacket;
   }

   protected final void setLoginPacket(@Nullable LoginPacket var1) {
      this.loginPacket = var1;
   }

   @NotNull
   public final MuCuteRelaySession getMuCuteRelaySession() {
      MuCuteRelaySession var10000 = this.muCuteRelaySession;
      if (var10000 != null) {
         return var10000;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("muCuteRelaySession");
         return null;
      }
   }

   public final void setMuCuteRelaySession(@NotNull MuCuteRelaySession var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.muCuteRelaySession = var1;
   }

   public boolean beforeClientBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      if (packet instanceof LoginPacket) {
         Object newChain = null;
         List var10000 = ((LoginPacket)packet).getChain();
         Intrinsics.checkNotNullExpressionValue(var10000, "getChain(...)");
         Iterable $this$forEach$iv = (Iterable)var10000;
         int $i$f$forEach = false;
         Iterator var5 = $this$forEach$iv.iterator();

         while(var5.hasNext()) {
            Object element$iv = var5.next();
            String it = (String)element$iv;
            int var8 = false;
            Intrinsics.checkNotNull(it);
            JsonObject var10 = JWTUtilsKt.jwtPayload(it);
            if (var10 != null) {
               JsonObject chainBody = var10;
               if (chainBody.has("extraData")) {
                  chainBody.addProperty("identityPublicKey", Base64.getEncoder().withoutPadding().encodeToString(this.keyPair.getPublic().getEncoded()));
                  String var11 = AuthUtils.getGson().toJson((JsonElement)chainBody);
                  Intrinsics.checkNotNullExpressionValue(var11, "toJson(...)");
                  newChain = JWTUtilsKt.signJWT$default(var11, this.keyPair, false, 4, (Object)null);
               }
            }
         }

         ((LoginPacket)packet).getChain().clear();
         ((LoginPacket)packet).getChain().add(newChain);
         this.loginPacket = (LoginPacket)packet;
         this.connectServer();
         return true;
      } else {
         return false;
      }
   }

   public boolean beforeServerBound(@NotNull BedrockPacket packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      MuCuteRelaySession.ClientSession var13;
      if (packet instanceof NetworkSettingsPacket) {
         int threshold = ((NetworkSettingsPacket)packet).getCompressionThreshold();
         if (threshold > 0) {
            var13 = this.getMuCuteRelaySession().getClient();
            Intrinsics.checkNotNull(var13);
            var13.setCompression(((NetworkSettingsPacket)packet).getCompressionAlgorithm());
            System.out.println("Compression threshold set to " + threshold);
         } else {
            var13 = this.getMuCuteRelaySession().getClient();
            Intrinsics.checkNotNull(var13);
            var13.setCompression(PacketCompressionAlgorithm.NONE);
            System.out.println("Compression threshold set to 0");
         }

         LoginPacket var14 = this.loginPacket;
         if (var14 != null) {
            LoginPacket it = var14;
            int var7 = false;
            this.getMuCuteRelaySession().serverBoundImmediately((BedrockPacket)it);
         } else {
            this.getMuCuteRelaySession().getServer().disconnect("LoginPacket is null");
         }

         return true;
      } else if (packet instanceof ServerToClientHandshakePacket) {
         String var10000 = ((ServerToClientHandshakePacket)packet).getJwt();
         Intrinsics.checkNotNullExpressionValue(var10000, "getJwt(...)");
         CharSequence var11 = (CharSequence)var10000;
         String[] var3 = new String[]{"."};
         List jwtSplit = StringsKt.split$default(var11, var3, false, 0, 6, (Object)null);
         JsonObject headerObject = JsonParser.parseString(new String(JWTUtilsKt.base64Decode((String)jwtSplit.get(0)), Charsets.UTF_8)).getAsJsonObject();
         JsonObject payloadObject = JsonParser.parseString(new String(JWTUtilsKt.base64Decode((String)jwtSplit.get(1)), Charsets.UTF_8)).getAsJsonObject();
         ECPublicKey serverKey = EncryptionUtils.parseKey(headerObject.get("x5u").getAsString());
         PrivateKey var12 = this.keyPair.getPrivate();
         PublicKey var10001 = (PublicKey)serverKey;
         String var10002 = payloadObject.get("salt").getAsString();
         Intrinsics.checkNotNullExpressionValue(var10002, "getAsString(...)");
         SecretKey key = EncryptionUtils.getSecretKey(var12, var10001, JWTUtilsKt.base64Decode(var10002));
         var13 = this.getMuCuteRelaySession().getClient();
         Intrinsics.checkNotNull(var13);
         var13.enableEncryption(key);
         System.out.println("Encryption enabled");
         this.getMuCuteRelaySession().serverBoundImmediately((BedrockPacket)(new ClientToServerHandshakePacket()));
         return true;
      } else {
         return false;
      }
   }

   protected final void connectServer() {
      this.getMuCuteRelaySession().getMuCuteRelay().connectToServer$MuCuteRelay(EncryptedLoginPacketListener::connectServer$lambda$2);
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

   private static final Unit connectServer$lambda$2(EncryptedLoginPacketListener this$0, MuCuteRelaySession.ClientSession $this$connectToServer) {
      Intrinsics.checkNotNullParameter($this$connectToServer, "$this$connectToServer");
      System.out.println("Connected to server");
      RequestNetworkSettingsPacket packet = new RequestNetworkSettingsPacket();
      packet.setProtocolVersion(this$0.getMuCuteRelaySession().getServer().getCodec().getProtocolVersion());
      this$0.getMuCuteRelaySession().serverBoundImmediately((BedrockPacket)packet);
      return Unit.INSTANCE;
   }
}
