package com.mucheng.mucute.relay.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"},
   d2 = {"Lcom/mucheng/mucute/relay/util/HttpUtils;", "", "<init>", "()V", "DEFAULT_AGENT", "", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "getSystemProxyConfig", "Ljava/net/Proxy;", "MuCuteRelay"}
)
@SourceDebugExtension({"SMAP\nHttpUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUtils.kt\ncom/mucheng/mucute/relay/util/HttpUtils\n+ 2 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder\n*L\n1#1,35:1\n578#2:36\n*S KotlinDebug\n*F\n+ 1 HttpUtils.kt\ncom/mucheng/mucute/relay/util/HttpUtils\n*L\n12#1:36\n*E\n"})
public final class HttpUtils {
   @NotNull
   public static final HttpUtils INSTANCE = new HttpUtils();
   @NotNull
   private static final String DEFAULT_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1788.0";
   @NotNull
   private static final OkHttpClient client;

   private HttpUtils() {
   }

   @NotNull
   public final OkHttpClient getClient() {
      return client;
   }

   private final Proxy getSystemProxyConfig() {
      String var10000 = System.getProperty("http.proxyHost");
      Proxy var7;
      if (var10000 == null) {
         var7 = Proxy.NO_PROXY;
         Intrinsics.checkNotNullExpressionValue(var7, "NO_PROXY");
         return var7;
      } else {
         String proxyHost = var10000;
         var10000 = System.getProperty("http.proxyPort");
         if (var10000 == null) {
            var7 = Proxy.NO_PROXY;
            Intrinsics.checkNotNullExpressionValue(var7, "NO_PROXY");
            return var7;
         } else {
            String proxyPort = var10000;

            Proxy var3;
            try {
               var3 = new Proxy(Type.HTTP, (SocketAddress)(new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort))));
            } catch (Throwable var6) {
               var6.printStackTrace();
               Proxy var5 = Proxy.NO_PROXY;
               Intrinsics.checkNotNull(var5);
               var3 = var5;
            }

            return var3;
         }
      }
   }

   static {
      Builder this_$iv = (new Builder()).proxy(INSTANCE.getSystemProxyConfig());
      int var1 = false;
      client = this_$iv.addNetworkInterceptor((Interceptor)(new HttpUtils$special$$inlined$-addNetworkInterceptor$1())).build();
   }
}
