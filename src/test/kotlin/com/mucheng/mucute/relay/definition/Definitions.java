package com.mucheng.mucute.relay.definition;

import java.io.Closeable;
import java.io.InputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\nR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\b\"\u0004\b\u0015\u0010\n¨\u0006\u001b"},
   d2 = {"Lcom/mucheng/mucute/relay/definition/Definitions;", "", "<init>", "()V", "itemDefinitions", "Lorg/cloudburstmc/protocol/common/DefinitionRegistry;", "Lorg/cloudburstmc/protocol/bedrock/data/definitions/ItemDefinition;", "getItemDefinitions", "()Lorg/cloudburstmc/protocol/common/DefinitionRegistry;", "setItemDefinitions", "(Lorg/cloudburstmc/protocol/common/DefinitionRegistry;)V", "blockDefinitions", "Lorg/cloudburstmc/protocol/bedrock/data/definitions/BlockDefinition;", "getBlockDefinitions", "setBlockDefinitions", "cameraPresetDefinitions", "Lorg/cloudburstmc/protocol/common/NamedDefinition;", "getCameraPresetDefinitions", "setCameraPresetDefinitions", "blockDefinitionsHashed", "getBlockDefinitionsHashed", "setBlockDefinitionsHashed", "loadBlockPalette", "", "loadGzipNBT", "stream", "Ljava/io/InputStream;", "MuCuteRelay"}
)
public final class Definitions {
   @NotNull
   public static final Definitions INSTANCE = new Definitions();
   @NotNull
   private static DefinitionRegistry<ItemDefinition> itemDefinitions;
   @NotNull
   private static DefinitionRegistry<BlockDefinition> blockDefinitions;
   @NotNull
   private static DefinitionRegistry<NamedDefinition> cameraPresetDefinitions;
   @NotNull
   private static DefinitionRegistry<BlockDefinition> blockDefinitionsHashed;

   private Definitions() {
   }

   @NotNull
   public final DefinitionRegistry<ItemDefinition> getItemDefinitions() {
      return itemDefinitions;
   }

   public final void setItemDefinitions(@NotNull DefinitionRegistry<ItemDefinition> var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      itemDefinitions = var1;
   }

   @NotNull
   public final DefinitionRegistry<BlockDefinition> getBlockDefinitions() {
      return blockDefinitions;
   }

   public final void setBlockDefinitions(@NotNull DefinitionRegistry<BlockDefinition> var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      blockDefinitions = var1;
   }

   @NotNull
   public final DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions() {
      return cameraPresetDefinitions;
   }

   public final void setCameraPresetDefinitions(@NotNull DefinitionRegistry<NamedDefinition> var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      cameraPresetDefinitions = var1;
   }

   @NotNull
   public final DefinitionRegistry<BlockDefinition> getBlockDefinitionsHashed() {
      return blockDefinitionsHashed;
   }

   public final void setBlockDefinitionsHashed(@NotNull DefinitionRegistry<BlockDefinition> var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      blockDefinitionsHashed = var1;
   }

   public final void loadBlockPalette() {
      InputStream var10000 = Definitions.class.getClassLoader().getResourceAsStream("nbt/block_palette.nbt");
      if (var10000 != null) {
         InputStream it = var10000;
         int var2 = false;
         Object tag = INSTANCE.loadGzipNBT(it);
         if (tag instanceof NbtMap) {
            Definitions var4 = INSTANCE;
            List var10002 = ((NbtMap)tag).getList("blocks", NbtType.COMPOUND);
            Intrinsics.checkNotNullExpressionValue(var10002, "getList(...)");
            blockDefinitions = (DefinitionRegistry)(new NbtBlockDefinitionRegistry(var10002, false));
            var4 = INSTANCE;
            var10002 = ((NbtMap)tag).getList("blocks", NbtType.COMPOUND);
            Intrinsics.checkNotNullExpressionValue(var10002, "getList(...)");
            blockDefinitionsHashed = (DefinitionRegistry)(new NbtBlockDefinitionRegistry(var10002, true));
         }
      }

   }

   private final Object loadGzipNBT(InputStream stream) {
      Closeable var2 = (Closeable)NbtUtils.createGZIPReader(stream);
      Throwable var3 = null;

      Object var6;
      try {
         NBTInputStream nbtInputStream = (NBTInputStream)var2;
         int var5 = false;
         Object var10000 = nbtInputStream.readTag();
         Intrinsics.checkNotNullExpressionValue(var10000, "readTag(...)");
         var6 = var10000;
      } catch (Throwable var9) {
         var3 = var9;
         throw var9;
      } finally {
         CloseableKt.closeFinally(var2, var3);
      }

      return var6;
   }

   static {
      SimpleDefinitionRegistry var10000 = SimpleDefinitionRegistry.builder().build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      itemDefinitions = (DefinitionRegistry)var10000;
      var10000 = SimpleDefinitionRegistry.builder().build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      blockDefinitions = (DefinitionRegistry)var10000;
      var10000 = SimpleDefinitionRegistry.builder().build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      cameraPresetDefinitions = (DefinitionRegistry)var10000;
      var10000 = SimpleDefinitionRegistry.builder().build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      blockDefinitionsHashed = (DefinitionRegistry)var10000;
   }
}
