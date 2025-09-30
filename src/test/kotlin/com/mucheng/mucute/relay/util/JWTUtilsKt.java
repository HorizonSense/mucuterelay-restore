package com.mucheng.mucute.relay.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyPair;
import java.security.Signature;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jws.EcdsaUsingShaAlgorithm;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a \u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003¨\u0006\u000f"},
   d2 = {"jwtPayload", "Lcom/google/gson/JsonObject;", "jwt", "", "signJWT", "payload", "keyPair", "Ljava/security/KeyPair;", "base64Encoded", "", "signBytes", "dataToSign", "", "base64Decode", "b64", "MuCuteRelay"}
)
public final class JWTUtilsKt {
   @Nullable
   public static final JsonObject jwtPayload(@NotNull String jwt) {
      Intrinsics.checkNotNullParameter(jwt, "jwt");
      CharSequence var10000 = (CharSequence)jwt;
      char[] var2 = new char[]{'.'};
      List parts = StringsKt.split$default(var10000, var2, false, 0, 6, (Object)null);
      if (parts.size() != 3) {
         return null;
      } else {
         JsonElement result = JsonParser.parseReader((Reader)(new InputStreamReader((InputStream)(new ByteArrayInputStream(base64Decode((String)parts.get(1)))), Charsets.UTF_8)));
         return result.isJsonObject() ? result.getAsJsonObject() : null;
      }
   }

   @NotNull
   public static final String signJWT(@NotNull String payload, @NotNull KeyPair keyPair, boolean base64Encoded) {
      Intrinsics.checkNotNullParameter(payload, "payload");
      Intrinsics.checkNotNullParameter(keyPair, "keyPair");
      JsonObject var4 = new JsonObject();
      int var6 = false;
      var4.addProperty("alg", "ES384");
      var4.addProperty("x5u", Base64.getEncoder().withoutPadding().encodeToString(keyPair.getPublic().getEncoded()));
      Encoder var10000 = Base64.getUrlEncoder().withoutPadding();
      String var10001 = AuthUtils.getGson().toJson((JsonElement)var4);
      Intrinsics.checkNotNullExpressionValue(var10001, "toJson(...)");
      String encodedPayload = var10001;
      byte[] var10 = encodedPayload.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var10, "getBytes(...)");
      String header = var10000.encodeToString(var10);
      String var8;
      if (base64Encoded) {
         var8 = payload;
      } else {
         var10000 = Base64.getUrlEncoder().withoutPadding();
         var10 = payload.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var10, "getBytes(...)");
         var8 = var10000.encodeToString(var10);
      }

      encodedPayload = var8;
      byte[] var11 = (header + "." + encodedPayload).getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var11, "getBytes(...)");
      String sign = signBytes(var11, keyPair);
      return header + "." + encodedPayload + "." + sign;
   }

   // $FF: synthetic method
   public static String signJWT$default(String var0, KeyPair var1, boolean var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = false;
      }

      return signJWT(var0, var1, var2);
   }

   private static final String signBytes(byte[] dataToSign, KeyPair keyPair) {
      Signature signature = Signature.getInstance("SHA384withECDSA");
      signature.initSign(keyPair.getPrivate());
      signature.update(dataToSign);
      byte[] signatureBytes = EcdsaUsingShaAlgorithm.convertDerToConcatenated(signature.sign(), 48);
      String var10000 = Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
      Intrinsics.checkNotNullExpressionValue(var10000, "encodeToString(...)");
      return var10000;
   }

   @NotNull
   public static final byte[] base64Decode(@NotNull String b64) {
      Intrinsics.checkNotNullParameter(b64, "b64");
      byte[] var10000 = Base64.getDecoder().decode(StringsKt.replace$default(StringsKt.replace$default(b64, '-', '+', false, 4, (Object)null), '_', '/', false, 4, (Object)null));
      Intrinsics.checkNotNullExpressionValue(var10000, "decode(...)");
      return var10000;
   }
}
