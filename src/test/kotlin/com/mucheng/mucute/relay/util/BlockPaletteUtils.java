package com.mucheng.mucute.relay.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lcom/mucheng/mucute/relay/util/BlockPaletteUtils;", "", "<init>", "()V", "createHash", "", "block", "Lorg/cloudburstmc/nbt/NbtMap;", "FNV1_32_INIT", "FNV1_PRIME_32", "fnv1a_32", "data", "", "MuCuteRelay"}
)
public final class BlockPaletteUtils {
   @NotNull
   public static final BlockPaletteUtils INSTANCE = new BlockPaletteUtils();
   private static final int FNV1_32_INIT = -2128831035;
   private static final int FNV1_PRIME_32 = 16777619;

   private BlockPaletteUtils() {
   }

   public final int createHash(@NotNull NbtMap block) {
      Intrinsics.checkNotNullParameter(block, "block");
      if (Intrinsics.areEqual(block.getString("name"), "minecraft:unknown")) {
         return -2;
      } else {
         TreeMap states = new TreeMap((Map)block.getCompound("states"));
         NbtMapBuilder statesBuilder = NbtMap.builder();
         statesBuilder.putAll((Map)states);
         NbtMap tag = NbtMap.builder().putString("name", block.getString("name")).putCompound("states", statesBuilder.build()).build();
         Object bytes = null;
         Closeable var6 = (Closeable)(new ByteArrayOutputStream());
         Throwable var7 = null;

         byte[] bytes;
         try {
            ByteArrayOutputStream stream = (ByteArrayOutputStream)var6;
            int var9 = false;
            Closeable var10 = (Closeable)NbtUtils.createWriterLE((OutputStream)stream);
            Throwable var11 = null;

            try {
               NBTOutputStream outputStream = (NBTOutputStream)var10;
               int var13 = false;
               outputStream.writeTag(tag);
               bytes = stream.toByteArray();
               Unit var28 = Unit.INSTANCE;
            } catch (Throwable var22) {
               var11 = var22;
               throw var22;
            } finally {
               CloseableKt.closeFinally(var10, var11);
            }

            Unit var27 = Unit.INSTANCE;
         } catch (Throwable var24) {
            var7 = var24;
            throw var24;
         } finally {
            CloseableKt.closeFinally(var6, var7);
         }

         return this.fnv1a_32(bytes);
      }
   }

   private final int fnv1a_32(byte[] data) {
      int hash = -2128831035;
      int var3 = 0;

      for(int var4 = data.length; var3 < var4; ++var3) {
         byte datum = data[var3];
         hash ^= datum & 255;
         hash *= 16777619;
      }

      return hash;
   }
}
