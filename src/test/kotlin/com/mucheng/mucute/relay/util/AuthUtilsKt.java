package com.mucheng.mucute.relay.util;

import coelho.msftauth.api.xbox.XboxDevice;
import coelho.msftauth.api.xbox.XboxDeviceAuthRequest;
import coelho.msftauth.api.xbox.XboxDeviceKey;
import coelho.msftauth.api.xbox.XboxDeviceToken;
import coelho.msftauth.api.xbox.XboxSISUAuthenticate;
import coelho.msftauth.api.xbox.XboxSISUAuthenticateRequest;
import coelho.msftauth.api.xbox.XboxSISUAuthorize;
import coelho.msftauth.api.xbox.XboxSISUAuthorizeRequest;
import coelho.msftauth.api.xbox.XboxTitleAuthRequest;
import coelho.msftauth.api.xbox.XboxToken;
import coelho.msftauth.api.xbox.XboxUserAuthRequest;
import coelho.msftauth.api.xbox.XboxXSTSAuthRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import MuCuteRelay;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.PublicKey;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Request.Builder;
import okhttp3.RequestBody.Companion;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"},
   d2 = {"deviceKey", "Lcoelho/msftauth/api/xbox/XboxDeviceKey;", "getDeviceKey", "()Lcoelho/msftauth/api/xbox/XboxDeviceKey;", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "fetchChain", "", "", "identityToken", "keyPair", "Ljava/security/KeyPair;", "fetchRawChain", "Ljava/io/Reader;", "publicKey", "Ljava/security/PublicKey;", "fetchIdentityToken", "Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "accessToken", "deviceInfo", "Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nAuthUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/AuthUtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,298:1\n1557#2:299\n1628#2,3:300\n1#3:303\n*S KotlinDebug\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/AuthUtilsKt\n*L\n43#1:299\n43#1:300,3\n*E\n"})
public final class AuthUtilsKt {
   @NotNull
   private static final XboxDeviceKey deviceKey = new XboxDeviceKey();
   @NotNull
   private static final Gson gson;

   @NotNull
   public static final XboxDeviceKey getDeviceKey() {
      return deviceKey;
   }

   @NotNull
   public static final Gson getGson() {
      return gson;
   }

   @NotNull
   public static final List<String> fetchChain(@NotNull String identityToken, @NotNull KeyPair keyPair) {
      Intrinsics.checkNotNullParameter(identityToken, "identityToken");
      Intrinsics.checkNotNullParameter(keyPair, "keyPair");
      PublicKey var10001 = keyPair.getPublic();
      Intrinsics.checkNotNullExpressionValue(var10001, "getPublic(...)");
      JsonObject rawChain = JsonParser.parseReader(fetchRawChain(identityToken, var10001)).getAsJsonObject();
      JsonArray chains = rawChain.get("chain").getAsJsonArray();
      String var10000 = chains.get(0).getAsString();
      Intrinsics.checkNotNullExpressionValue(var10000, "getAsString(...)");
      CharSequence var23 = (CharSequence)var10000;
      String[] var5 = new String[]{"."};
      byte[] var18 = JWTUtilsKt.base64Decode((String)StringsKt.split$default(var23, var5, false, 0, 6, (Object)null).get(0));
      Charset var6 = Charsets.UTF_8;
      JsonObject identityPubKey = JsonParser.parseString(new String(var18, var6)).getAsJsonObject();
      Gson var24 = gson;
      JsonObject var7 = new JsonObject();
      Gson var16 = var24;
      int var9 = false;
      var7.addProperty("certificateAuthority", true);
      var7.addProperty("exp", (Number)(int)(Instant.now().getEpochSecond() + TimeUnit.HOURS.toSeconds(6L)));
      var7.addProperty("nbf", (Number)(int)(Instant.now().getEpochSecond() - TimeUnit.HOURS.toSeconds(6L)));
      var7.addProperty("identityPublicKey", identityPubKey.get("x5u").getAsString());
      var10000 = var16.toJson((JsonElement)var7);
      Intrinsics.checkNotNullExpressionValue(var10000, "toJson(...)");
      String jwt = JWTUtilsKt.signJWT$default(var10000, keyPair, false, 4, (Object)null);
      String[] var21 = new String[]{jwt};
      List list = CollectionsKt.mutableListOf(var21);
      Intrinsics.checkNotNull(chains);
      Iterable $this$map$iv = (Iterable)chains;
      int $i$f$map = false;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = false;
      Iterator var12 = $this$map$iv.iterator();

      while(var12.hasNext()) {
         Object item$iv$iv = var12.next();
         JsonElement it = (JsonElement)item$iv$iv;
         int var15 = false;
         destination$iv$iv.add(it.getAsString());
      }

      list.addAll((Collection)((List)destination$iv$iv));
      return list;
   }

   @NotNull
   public static final Reader fetchRawChain(@NotNull String identityToken, @NotNull PublicKey publicKey) {
      Intrinsics.checkNotNullParameter(identityToken, "identityToken");
      Intrinsics.checkNotNullParameter(publicKey, "publicKey");
      JsonObject var3 = new JsonObject();
      int var5 = false;
      var3.addProperty("identityPublicKey", Base64.getEncoder().withoutPadding().encodeToString(publicKey.getEncoded()));
      Builder var10000 = (new Builder()).url("https://multiplayer.minecraft.net/authentication");
      Companion var10001 = RequestBody.Companion;
      String var10002 = gson.toJson((JsonElement)var3);
      Intrinsics.checkNotNullExpressionValue(var10002, "toJson(...)");
      var10000 = var10000.post(var10001.create(var10002, MediaType.Companion.get("application/json")));
      var10002 = MuCuteRelay.Companion.getDefaultCodec().getMinecraftVersion();
      Intrinsics.checkNotNullExpressionValue(var10002, "getMinecraftVersion(...)");
      Request request = var10000.header("Client-Version", var10002).header("Authorization", identityToken).build();
      Response response = HttpUtils.INSTANCE.getClient().newCall(request).execute();
      var5 = response.code() == 200;
      if (_Assertions.ENABLED && !var5) {
         int var6 = false;
         String var9 = "Http code " + response.code();
         throw new AssertionError(var9);
      } else {
         ResponseBody var8 = response.body();
         Intrinsics.checkNotNull(var8);
         return var8.charStream();
      }
   }

   @NotNull
   public static final XboxIdentityToken fetchIdentityToken(@NotNull String accessToken, @NotNull XboxDeviceInfo deviceInfo) {
      Intrinsics.checkNotNullParameter(accessToken, "accessToken");
      Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
      ObjectRef userToken = new ObjectRef();
      Thread userRequestThread = ThreadsKt.thread$default(false, false, (ClassLoader)null, (String)null, 0, AuthUtils::fetchIdentityToken$lambda$4, 31, (Object)null);
      XboxDeviceToken deviceToken = (XboxDeviceToken)(new XboxDeviceAuthRequest("http://auth.xboxlive.com", "JWT", deviceInfo.getDeviceType(), "0.0.0.0", deviceKey)).request(HttpUtils.INSTANCE.getClient());
      XboxToken var10000;
      if (deviceInfo.getAllowDirectTitleTokenFetch()) {
         var10000 = (XboxToken)(new XboxTitleAuthRequest("http://auth.xboxlive.com", "JWT", "RPS", "user.auth.xboxlive.com", "t=" + accessToken, deviceToken.getToken(), deviceKey)).request(HttpUtils.INSTANCE.getClient());
      } else {
         XboxDevice device = new XboxDevice(deviceKey, deviceToken);
         XboxSISUAuthenticateRequest.Query sisuQuery = new XboxSISUAuthenticateRequest.Query("phone");
         XboxSISUAuthenticate sisuRequest = (XboxSISUAuthenticate)(new XboxSISUAuthenticateRequest(deviceInfo.getAppId(), device, "service::user.auth.xboxlive.com::MBI_SSL", sisuQuery, deviceInfo.getXalRedirect(), "RETAIL")).request(HttpUtils.INSTANCE.getClient());
         XboxSISUAuthorize sisuToken = (XboxSISUAuthorize)(new XboxSISUAuthorizeRequest("t=" + accessToken, deviceInfo.getAppId(), device, "RETAIL", sisuRequest.getSessionId(), "user.auth.xboxlive.com", "http://xboxlive.com")).request(HttpUtils.INSTANCE.getClient());
         if (sisuToken.getStatus() != 200) {
            Object var15 = deviceToken.getDisplayClaims().get("xdi");
            Intrinsics.checkNotNull(var15);
            String did = ((JsonElement)var15).getAsJsonObject().get("did").getAsString();
            String var16 = deviceKey.sign("/proxy?sessionid=" + sisuRequest.getSessionId(), (String)null, "POST", (byte[])null);
            Intrinsics.checkNotNullExpressionValue(var16, "sign(...)");
            String sign = StringsKt.replace$default(StringsKt.replace$default(var16, "+", "%2B", false, 4, (Object)null), "=", "%3D", false, 4, (Object)null);
            var16 = sisuToken.getWebPage();
            Intrinsics.checkNotNullExpressionValue(var16, "getWebPage(...)");
            CharSequence var17 = (CharSequence)var16;
            String[] var13 = new String[]{"#"};
            String url = StringsKt.split$default(var17, var13, false, 0, 6, (Object)null).get(0) + "&did=0x" + did + "&redirect=" + deviceInfo.getXalRedirect() + "&sid=" + sisuRequest.getSessionId() + "&sig=" + sign + "&state=" + sisuQuery.getState();
            throw new XboxGamerTagException(url);
         }

         var10000 = sisuToken.getTitleToken();
      }

      XboxToken titleToken = var10000;
      if (userRequestThread.isAlive()) {
         userRequestThread.join();
      }

      if (userToken.element == null) {
         throw new IllegalStateException("failed to fetch xbox user token".toString());
      } else {
         XboxToken xstsToken = (XboxToken)(new XboxXSTSAuthRequest("https://multiplayer.minecraft.net/", "JWT", "RETAIL", CollectionsKt.listOf(userToken.element), titleToken, new XboxDevice(deviceKey, deviceToken))).request(HttpUtils.INSTANCE.getClient());
         String var10002 = xstsToken.toIdentityToken();
         Intrinsics.checkNotNullExpressionValue(var10002, "toIdentityToken(...)");
         return new XboxIdentityToken(var10002, Instant.parse((CharSequence)xstsToken.getNotAfter()).getEpochSecond());
      }
   }

   private static final Unit fetchIdentityToken$lambda$4(ObjectRef $userToken, String $accessToken) {
      $userToken.element = (new XboxUserAuthRequest("http://auth.xboxlive.com", "JWT", "RPS", "user.auth.xboxlive.com", "t=" + $accessToken)).request(HttpUtils.INSTANCE.getClient());
      return Unit.INSTANCE;
   }

   static {
      Gson var10000 = (new GsonBuilder()).setPrettyPrinting().create();
      Intrinsics.checkNotNullExpressionValue(var10000, "create(...)");
      gson = var10000;
   }
}
