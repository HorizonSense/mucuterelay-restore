package com.mucheng.mucute.relay.address;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"},
   d2 = {"Lcom/mucheng/mucute/relay/address/MuCuteAddress;", "", "hostName", "", "port", "", "<init>", "(Ljava/lang/String;I)V", "getHostName", "()Ljava/lang/String;", "getPort", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "MuCuteRelay"}
)
public final class MuCuteAddress {
   @NotNull
   private final String hostName;
   private final int port;

   public MuCuteAddress(@NotNull String hostName, int port) {
      Intrinsics.checkNotNullParameter(hostName, "hostName");
      super();
      this.hostName = hostName;
      this.port = port;
   }

   @NotNull
   public final String getHostName() {
      return this.hostName;
   }

   public final int getPort() {
      return this.port;
   }

   @NotNull
   public final String component1() {
      return this.hostName;
   }

   public final int component2() {
      return this.port;
   }

   @NotNull
   public final MuCuteAddress copy(@NotNull String hostName, int port) {
      Intrinsics.checkNotNullParameter(hostName, "hostName");
      return new MuCuteAddress(hostName, port);
   }

   // $FF: synthetic method
   public static MuCuteAddress copy$default(MuCuteAddress var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.hostName;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.port;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "MuCuteAddress(hostName=" + this.hostName + ", port=" + this.port + ")";
   }

   public int hashCode() {
      int result = this.hostName.hashCode();
      result = result * 31 + Integer.hashCode(this.port);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MuCuteAddress)) {
         return false;
      } else {
         MuCuteAddress var2 = (MuCuteAddress)other;
         if (!Intrinsics.areEqual(this.hostName, var2.hostName)) {
            return false;
         } else {
            return this.port == var2.port;
         }
      }
   }
}
