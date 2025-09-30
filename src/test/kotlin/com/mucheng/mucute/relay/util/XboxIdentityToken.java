package com.mucheng.mucute.relay.util;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"},
   d2 = {"Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "", "token", "", "notAfter", "", "<init>", "(Ljava/lang/String;J)V", "getToken", "()Ljava/lang/String;", "getNotAfter", "()J", "expired", "", "getExpired", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "MuCuteRelay"}
)
public final class XboxIdentityToken {
   @NotNull
   private final String token;
   private final long notAfter;

   public XboxIdentityToken(@NotNull String token, long notAfter) {
      Intrinsics.checkNotNullParameter(token, "token");
      super();
      this.token = token;
      this.notAfter = notAfter;
   }

   @NotNull
   public final String getToken() {
      return this.token;
   }

   public final long getNotAfter() {
      return this.notAfter;
   }

   public final boolean getExpired() {
      return this.notAfter < Instant.now().getEpochSecond();
   }

   @NotNull
   public final String component1() {
      return this.token;
   }

   public final long component2() {
      return this.notAfter;
   }

   @NotNull
   public final XboxIdentityToken copy(@NotNull String token, long notAfter) {
      Intrinsics.checkNotNullParameter(token, "token");
      return new XboxIdentityToken(token, notAfter);
   }

   // $FF: synthetic method
   public static XboxIdentityToken copy$default(XboxIdentityToken var0, String var1, long var2, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.token;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.notAfter;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "XboxIdentityToken(token=" + this.token + ", notAfter=" + this.notAfter + ")";
   }

   public int hashCode() {
      int result = this.token.hashCode();
      result = result * 31 + Long.hashCode(this.notAfter);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof XboxIdentityToken)) {
         return false;
      } else {
         XboxIdentityToken var2 = (XboxIdentityToken)other;
         if (!Intrinsics.areEqual(this.token, var2.token)) {
            return false;
         } else {
            return this.notAfter == var2.notAfter;
         }
      }
   }
}
