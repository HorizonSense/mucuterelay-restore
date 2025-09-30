package com.mucheng.mucute.relay.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.FormBody.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001e"},
   d2 = {"Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "", "appId", "", "deviceType", "allowDirectTitleTokenFetch", "", "xalRedirect", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "getDeviceType", "getAllowDirectTitleTokenFetch", "()Z", "getXalRedirect", "refreshToken", "Lkotlin/Pair;", "token", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nAuthUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/XboxDeviceInfo\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,298:1\n1#2:299\n8634#3,2:300\n8894#3,4:302\n*S KotlinDebug\n*F\n+ 1 AuthUtils.kt\ncom/mucheng/mucute/relay/util/XboxDeviceInfo\n*L\n179#1:300,2\n179#1:302,4\n*E\n"})
public final class XboxDeviceInfo {
   @NotNull
   public static final XboxDeviceInfo.Companion Companion = new XboxDeviceInfo.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String appId;
   @NotNull
   private final String deviceType;
   private final boolean allowDirectTitleTokenFetch;
   @NotNull
   private final String xalRedirect;
   @NotNull
   private static final XboxDeviceInfo DEVICE_ANDROID = new XboxDeviceInfo("0000000048183522", "Android", false, "ms-xal-0000000048183522://auth");
   @NotNull
   private static final XboxDeviceInfo DEVICE_IOS = new XboxDeviceInfo("000000004c17c01a", "iOS", false, "ms-xal-000000004c17c01a://auth");
   @NotNull
   private static final XboxDeviceInfo DEVICE_NINTENDO = new XboxDeviceInfo("00000000441cc96b", "Nintendo", true, (String)null, 8, (DefaultConstructorMarker)null);
   @NotNull
   private static final Map<String, XboxDeviceInfo> devices;

   public XboxDeviceInfo(@NotNull String appId, @NotNull String deviceType, boolean allowDirectTitleTokenFetch, @NotNull String xalRedirect) {
      Intrinsics.checkNotNullParameter(appId, "appId");
      Intrinsics.checkNotNullParameter(deviceType, "deviceType");
      Intrinsics.checkNotNullParameter(xalRedirect, "xalRedirect");
      super();
      this.appId = appId;
      this.deviceType = deviceType;
      this.allowDirectTitleTokenFetch = allowDirectTitleTokenFetch;
      this.xalRedirect = xalRedirect;
   }

   // $FF: synthetic method
   public XboxDeviceInfo(String var1, String var2, boolean var3, String var4, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = "";
      }

      this(var1, var2, var3, var4);
   }

   @NotNull
   public final String getAppId() {
      return this.appId;
   }

   @NotNull
   public final String getDeviceType() {
      return this.deviceType;
   }

   public final boolean getAllowDirectTitleTokenFetch() {
      return this.allowDirectTitleTokenFetch;
   }

   @NotNull
   public final String getXalRedirect() {
      return this.xalRedirect;
   }

   @NotNull
   public final Pair<String, String> refreshToken(@NotNull String token) {
      Intrinsics.checkNotNullParameter(token, "token");
      Builder form = new Builder((Charset)null, 1, (DefaultConstructorMarker)null);
      form.add("client_id", this.appId);
      form.add("redirect_uri", "https://login.live.com/oauth20_desktop.srf");
      CharSequence var10000 = (CharSequence)token;
      String[] var3 = new String[]{"\n"};
      String var7 = (String)StringsKt.split$default(var10000, var3, false, 0, 6, (Object)null).get(0);
      int var4 = StringsKt.lastIndexOf$default((CharSequence)token, '.', 0, false, 6, (Object)null) + 1;
      String var12 = var7.substring(var4);
      Intrinsics.checkNotNullExpressionValue(var12, "substring(...)");
      if (var12.length() == 36) {
         form.add("grant_type", "authorization_code");
         form.add("code", token);
      } else {
         form.add("scope", "service::user.auth.xboxlive.com::MBI_SSL");
         form.add("grant_type", "refresh_token");
         form.add("refresh_token", token);
      }

      Request request = (new okhttp3.Request.Builder()).url("https://login.live.com/oauth20_token.srf").header("Content-Type", "application/x-www-form-urlencoded").post((RequestBody)form.build()).build();
      Response response = HttpUtils.INSTANCE.getClient().newCall(request).execute();
      boolean var5 = response.code() == 200;
      if (_Assertions.ENABLED && !var5) {
         int var6 = false;
         String var11 = "Http code " + response.code();
         throw new AssertionError(var11);
      } else {
         ResponseBody var13 = response.body();
         Intrinsics.checkNotNull(var13);
         JsonObject body = JsonParser.parseReader(var13.charStream()).getAsJsonObject();
         if (body.has("access_token") && body.has("refresh_token")) {
            return TuplesKt.to(body.get("access_token").getAsString(), body.get("refresh_token").getAsString());
         } else if (body.has("error")) {
            throw new RuntimeException("error occur whilst refreshing token: " + body.get("error").getAsString());
         } else {
            throw new RuntimeException("error occur whilst refreshing token");
         }
      }
   }

   @NotNull
   public final String component1() {
      return this.appId;
   }

   @NotNull
   public final String component2() {
      return this.deviceType;
   }

   public final boolean component3() {
      return this.allowDirectTitleTokenFetch;
   }

   @NotNull
   public final String component4() {
      return this.xalRedirect;
   }

   @NotNull
   public final XboxDeviceInfo copy(@NotNull String appId, @NotNull String deviceType, boolean allowDirectTitleTokenFetch, @NotNull String xalRedirect) {
      Intrinsics.checkNotNullParameter(appId, "appId");
      Intrinsics.checkNotNullParameter(deviceType, "deviceType");
      Intrinsics.checkNotNullParameter(xalRedirect, "xalRedirect");
      return new XboxDeviceInfo(appId, deviceType, allowDirectTitleTokenFetch, xalRedirect);
   }

   // $FF: synthetic method
   public static XboxDeviceInfo copy$default(XboxDeviceInfo var0, String var1, String var2, boolean var3, String var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.appId;
      }

      if ((var5 & 2) != 0) {
         var2 = var0.deviceType;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.allowDirectTitleTokenFetch;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.xalRedirect;
      }

      return var0.copy(var1, var2, var3, var4);
   }

   @NotNull
   public String toString() {
      return "XboxDeviceInfo(appId=" + this.appId + ", deviceType=" + this.deviceType + ", allowDirectTitleTokenFetch=" + this.allowDirectTitleTokenFetch + ", xalRedirect=" + this.xalRedirect + ")";
   }

   public int hashCode() {
      int result = this.appId.hashCode();
      result = result * 31 + this.deviceType.hashCode();
      result = result * 31 + Boolean.hashCode(this.allowDirectTitleTokenFetch);
      result = result * 31 + this.xalRedirect.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof XboxDeviceInfo)) {
         return false;
      } else {
         XboxDeviceInfo var2 = (XboxDeviceInfo)other;
         if (!Intrinsics.areEqual(this.appId, var2.appId)) {
            return false;
         } else if (!Intrinsics.areEqual(this.deviceType, var2.deviceType)) {
            return false;
         } else if (this.allowDirectTitleTokenFetch != var2.allowDirectTitleTokenFetch) {
            return false;
         } else {
            return Intrinsics.areEqual(this.xalRedirect, var2.xalRedirect);
         }
      }
   }

   static {
      XboxDeviceInfo[] var0 = new XboxDeviceInfo[]{DEVICE_ANDROID, DEVICE_IOS, DEVICE_NINTENDO};
      int $i$f$associateBy = false;
      int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(var0.length), 16);
      XboxDeviceInfo[] $this$associateByTo$iv$iv = var0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(capacity$iv));
      int $i$f$associateByTo = false;
      int var6 = 0;

      for(int var7 = var0.length; var6 < var7; ++var6) {
         Object element$iv$iv = $this$associateByTo$iv$iv[var6];
         int var10 = false;
         destination$iv$iv.put(element$iv$iv.deviceType, element$iv$iv);
      }

      devices = destination$iv$iv;
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"},
      d2 = {"Lcom/mucheng/mucute/relay/util/XboxDeviceInfo$Companion;", "", "<init>", "()V", "DEVICE_ANDROID", "Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "getDEVICE_ANDROID", "()Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "DEVICE_IOS", "getDEVICE_IOS", "DEVICE_NINTENDO", "getDEVICE_NINTENDO", "devices", "", "", "getDevices", "()Ljava/util/Map;", "MuCuteRelay"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final XboxDeviceInfo getDEVICE_ANDROID() {
         return XboxDeviceInfo.DEVICE_ANDROID;
      }

      @NotNull
      public final XboxDeviceInfo getDEVICE_IOS() {
         return XboxDeviceInfo.DEVICE_IOS;
      }

      @NotNull
      public final XboxDeviceInfo getDEVICE_NINTENDO() {
         return XboxDeviceInfo.DEVICE_NINTENDO;
      }

      @NotNull
      public final Map<String, XboxDeviceInfo> getDevices() {
         return XboxDeviceInfo.devices;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
