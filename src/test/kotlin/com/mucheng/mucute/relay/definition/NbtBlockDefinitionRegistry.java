package com.mucheng.mucute.relay.definition;

import com.mucheng.mucute.relay.util.BlockPaletteUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0011B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lcom/mucheng/mucute/relay/definition/NbtBlockDefinitionRegistry;", "Lorg/cloudburstmc/protocol/common/DefinitionRegistry;", "Lorg/cloudburstmc/protocol/bedrock/data/definitions/BlockDefinition;", "definitions", "", "Lorg/cloudburstmc/nbt/NbtMap;", "hashed", "", "<init>", "(Ljava/util/List;Z)V", "Lit/unimi/dsi/fastutil/ints/Int2ObjectOpenHashMap;", "Lcom/mucheng/mucute/relay/definition/NbtBlockDefinitionRegistry$NbtBlockDefinition;", "getDefinition", "runtimeId", "", "isRegistered", "com/mucheng/mucute/relay/definition", "NbtBlockDefinition", "MuCuteRelay"}
)
public final class NbtBlockDefinitionRegistry implements DefinitionRegistry<BlockDefinition> {
   @NotNull
   private final Int2ObjectOpenHashMap<NbtBlockDefinitionRegistry.NbtBlockDefinition> definitions;

   public NbtBlockDefinitionRegistry(@NotNull List<? extends NbtMap> definitions, boolean hashed) {
      Intrinsics.checkNotNullParameter(definitions, "definitions");
      super();
      this.definitions = new Int2ObjectOpenHashMap();
      int counter = 0;
      Iterator var4 = definitions.iterator();

      while(var4.hasNext()) {
         NbtMap definition = (NbtMap)var4.next();
         int runtimeId = hashed ? BlockPaletteUtils.INSTANCE.createHash(definition) : counter++;
         this.definitions.put(runtimeId, new NbtBlockDefinitionRegistry.NbtBlockDefinition(runtimeId, definition));
      }

   }

   @Nullable
   public BlockDefinition getDefinition(int runtimeId) {
      return (BlockDefinition)this.definitions.get(runtimeId);
   }

   public boolean isRegistered(@Nullable BlockDefinition definition) {
      return definition != null && Intrinsics.areEqual(this.definitions.get(definition.getRuntimeId()), definition);
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\u0004H\u0016J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\n¨\u0006\u0016"},
      d2 = {"Lcom/mucheng/mucute/relay/definition/NbtBlockDefinitionRegistry$NbtBlockDefinition;", "Lorg/cloudburstmc/protocol/bedrock/data/definitions/BlockDefinition;", "Ljava/lang/Record;", "runtimeId", "", "tag", "Lorg/cloudburstmc/nbt/NbtMap;", "<init>", "(ILorg/cloudburstmc/nbt/NbtMap;)V", "()I", "()Lorg/cloudburstmc/nbt/NbtMap;", "getRuntimeId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "MuCuteRelay"}
   )
   public static record NbtBlockDefinition(int runtimeId, NbtMap tag) implements BlockDefinition {
      public NbtBlockDefinition(int runtimeId, @NotNull NbtMap tag) {
         Intrinsics.checkNotNullParameter(tag, "tag");
         super();
         this.runtimeId = runtimeId;
         this.tag = tag;
      }

      public final int runtimeId() {
         return this.runtimeId;
      }

      @NotNull
      public final NbtMap tag() {
         return this.tag;
      }

      public int getRuntimeId() {
         return this.runtimeId;
      }

      public final int component1() {
         return this.runtimeId;
      }

      @NotNull
      public final NbtMap component2() {
         return this.tag;
      }

      @NotNull
      public final NbtBlockDefinitionRegistry.NbtBlockDefinition copy(int runtimeId, @NotNull NbtMap tag) {
         Intrinsics.checkNotNullParameter(tag, "tag");
         return new NbtBlockDefinitionRegistry.NbtBlockDefinition(runtimeId, tag);
      }

      // $FF: synthetic method
      public static NbtBlockDefinitionRegistry.NbtBlockDefinition copy$default(NbtBlockDefinitionRegistry.NbtBlockDefinition var0, int var1, NbtMap var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.runtimeId;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.tag;
         }

         return var0.copy(var1, var2);
      }

      @NotNull
      public String toString() {
         return "NbtBlockDefinition(runtimeId=" + this.runtimeId + ", tag=" + this.tag + ")";
      }

      public int hashCode() {
         int result = Integer.hashCode(this.runtimeId);
         result = result * 31 + this.tag.hashCode();
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof NbtBlockDefinitionRegistry.NbtBlockDefinition)) {
            return false;
         } else {
            NbtBlockDefinitionRegistry.NbtBlockDefinition var2 = (NbtBlockDefinitionRegistry.NbtBlockDefinition)other;
            if (this.runtimeId != var2.runtimeId) {
               return false;
            } else {
               return Intrinsics.areEqual(this.tag, var2.tag);
            }
         }
      }
   }
}
