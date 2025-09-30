package com.mucheng.mucute.relay.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"},
   d2 = {"Lcom/mucheng/mucute/relay/util/XboxIdentityTokenCacheFileSystem;", "Lcom/mucheng/mucute/relay/util/IXboxIdentityTokenCache;", "cacheFile", "Ljava/io/File;", "identifier", "", "<init>", "(Ljava/io/File;Ljava/lang/String;)V", "getCacheFile", "()Ljava/io/File;", "getIdentifier", "()Ljava/lang/String;", "cache", "", "device", "Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "token", "Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "checkCache", "removeExpired", "json", "Lcom/google/gson/JsonObject;", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nAuthUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/XboxIdentityTokenCacheFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,298:1\n1863#2:299\n1863#2,2:300\n1863#2,2:302\n1864#2:304\n*S KotlinDebug\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/XboxIdentityTokenCacheFileSystem\n*L\n277#1:299\n283#1:300,2\n292#1:302,2\n277#1:304\n*E\n"})
public final class XboxIdentityTokenCacheFileSystem implements IXboxIdentityTokenCache {
   @NotNull
   private final File cacheFile;
   @NotNull
   private final String identifier;

   public XboxIdentityTokenCacheFileSystem(@NotNull File cacheFile, @NotNull String identifier) {
      Intrinsics.checkNotNullParameter(cacheFile, "cacheFile");
      Intrinsics.checkNotNullParameter(identifier, "identifier");
      super();
      this.cacheFile = cacheFile;
      this.identifier = identifier;
   }

   @NotNull
   public final File getCacheFile() {
      return this.cacheFile;
   }

   @NotNull
   public String getIdentifier() {
      return this.identifier;
   }

   public void cache(@NotNull XboxDeviceInfo device, @NotNull XboxIdentityToken token) {
      Intrinsics.checkNotNullParameter(device, "device");
      Intrinsics.checkNotNullParameter(token, "token");
      JsonObject var5;
      JsonObject var10000;
      if (!this.cacheFile.exists()) {
         var10000 = null;
      } else {
         try {
            File var12 = this.cacheFile;
            Charset var6 = Charsets.UTF_8;
            var5 = JsonParser.parseReader((Reader)(new InputStreamReader((InputStream)(new FileInputStream(var12)), var6))).getAsJsonObject();
         } catch (Throwable var11) {
            System.out.println("Load config: " + var11);
            var5 = null;
         }

         var10000 = var5;
      }

      if (var10000 == null) {
         var10000 = new JsonObject();
      }

      JsonObject json = var10000;
      if (json.has(this.getIdentifier())) {
         JsonElement identifierElement = json.get(this.getIdentifier());
         var10000 = identifierElement.isJsonObject() ? identifierElement.getAsJsonObject() : new JsonObject();
      } else {
         var10000 = new JsonObject();
      }

      JsonObject identifierJson = var10000;
      String var10001 = device.getDeviceType();
      var5 = new JsonObject();
      String var9 = var10001;
      int var7 = false;
      var5.addProperty("token", token.getToken());
      var5.addProperty("expires", (Number)token.getNotAfter());
      Unit var10 = Unit.INSTANCE;
      identifierJson.add(var9, (JsonElement)var5);
      json.add(this.getIdentifier(), (JsonElement)identifierJson);
      this.removeExpired(json);
      File var14 = this.cacheFile;
      var10001 = AuthUtils.getGson().toJson((JsonElement)json);
      Intrinsics.checkNotNullExpressionValue(var10001, "toJson(...)");
      FilesKt.writeText(var14, var10001, Charsets.UTF_8);
   }

   @Nullable
   public XboxIdentityToken checkCache(@NotNull XboxDeviceInfo device) {
      Intrinsics.checkNotNullParameter(device, "device");
      if (!this.cacheFile.exists()) {
         return null;
      } else {
         JsonObject deviceJson;
         try {
            File var8 = this.cacheFile;
            Charset var5 = Charsets.UTF_8;
            deviceJson = JsonParser.parseReader((Reader)(new InputStreamReader((InputStream)(new FileInputStream(var8)), var5))).getAsJsonObject();
         } catch (Throwable var7) {
            System.out.println("Load config: " + var7);
            deviceJson = null;
         }

         if (deviceJson == null) {
            return null;
         } else {
            JsonObject json = deviceJson;

            try {
               if (json.has(this.getIdentifier())) {
                  JsonObject identifierJson = json.get(this.getIdentifier()).getAsJsonObject();
                  if (identifierJson.has(device.getDeviceType())) {
                     deviceJson = identifierJson.get(device.getDeviceType()).getAsJsonObject();
                     if (deviceJson.get("expires").getAsLong() >= Instant.now().getEpochSecond() && deviceJson.has("token")) {
                        String var10002 = deviceJson.get("token").getAsString();
                        Intrinsics.checkNotNullExpressionValue(var10002, "getAsString(...)");
                        return new XboxIdentityToken(var10002, deviceJson.get("expires").getAsLong());
                     } else {
                        identifierJson.remove(device.getDeviceType());
                        this.removeExpired(json);
                        File var10000 = this.cacheFile;
                        String var10001 = AuthUtils.getGson().toJson((JsonElement)json);
                        Intrinsics.checkNotNullExpressionValue(var10001, "toJson(...)");
                        FilesKt.writeText(var10000, var10001, Charsets.UTF_8);
                        return null;
                     }
                  } else {
                     return null;
                  }
               } else {
                  return null;
               }
            } catch (Throwable var6) {
               System.out.println("Check cache: " + var6);
               return null;
            }
         }
      }
   }

   private final void removeExpired(JsonObject json) {
      List toRemove = (List)(new ArrayList());
      long epoch = Instant.now().getEpochSecond();
      Set var10000 = json.entrySet();
      Intrinsics.checkNotNullExpressionValue(var10000, "entrySet(...)");
      Iterable $this$forEach$iv = (Iterable)var10000;
      int $i$f$forEach = false;
      Iterator var7 = $this$forEach$iv.iterator();

      while(var7.hasNext()) {
         Object element$iv = var7.next();
         Entry var9 = (Entry)element$iv;
         int var10 = false;
         Intrinsics.checkNotNull(var9);
         JsonElement value = (JsonElement)var9.getValue();
         if (value.isJsonObject()) {
            JsonObject identifierJson = value.getAsJsonObject();
            toRemove.clear();
            var10000 = identifierJson.entrySet();
            Intrinsics.checkNotNullExpressionValue(var10000, "entrySet(...)");
            Iterable $this$forEach$iv = (Iterable)var10000;
            int $i$f$forEach = false;
            Iterator var15 = $this$forEach$iv.iterator();

            Object element$iv;
            boolean var18;
            while(var15.hasNext()) {
               element$iv = var15.next();
               Entry var17 = (Entry)element$iv;
               var18 = false;
               Intrinsics.checkNotNull(var17);
               String key = (String)var17.getKey();
               JsonElement value = (JsonElement)var17.getValue();
               if (value.isJsonObject()) {
                  JsonObject deviceJson = value.getAsJsonObject();
                  if (deviceJson.get("expires").getAsLong() < epoch) {
                     Intrinsics.checkNotNull(key);
                     toRemove.add(key);
                  }
               }
            }

            $this$forEach$iv = (Iterable)toRemove;
            $i$f$forEach = false;
            var15 = $this$forEach$iv.iterator();

            while(var15.hasNext()) {
               element$iv = var15.next();
               String it = (String)element$iv;
               var18 = false;
               identifierJson.remove(it);
            }
         }
      }

   }
}
