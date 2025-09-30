package com.mucheng.mucute.relay.definition;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"},
   d2 = {"Lcom/mucheng/mucute/relay/definition/DataEntry;", "", "name", "", "id", "", "<init>", "(Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "MuCuteRelay"}
)
public final class DataEntry {
   @NotNull
   private final String name;
   private final int id;

   public DataEntry(@NotNull String name, int id) {
      Intrinsics.checkNotNullParameter(name, "name");
      super();
      this.name = name;
      this.id = id;
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final int getId() {
      return this.id;
   }

   @NotNull
   public final String component1() {
      return this.name;
   }

   public final int component2() {
      return this.id;
   }

   @NotNull
   public final DataEntry copy(@NotNull String name, int id) {
      Intrinsics.checkNotNullParameter(name, "name");
      return new DataEntry(name, id);
   }

   // $FF: synthetic method
   public static DataEntry copy$default(DataEntry var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.name;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.id;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "DataEntry(name=" + this.name + ", id=" + this.id + ")";
   }

   public int hashCode() {
      int result = this.name.hashCode();
      result = result * 31 + Integer.hashCode(this.id);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof DataEntry)) {
         return false;
      } else {
         DataEntry var2 = (DataEntry)other;
         if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else {
            return this.id == var2.id;
         }
      }
   }
}
