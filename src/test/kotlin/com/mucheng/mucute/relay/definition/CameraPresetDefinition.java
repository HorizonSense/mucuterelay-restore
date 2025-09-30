package com.mucheng.mucute.relay.definition;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPreset;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\t\u0010\n\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lcom/mucheng/mucute/relay/definition/CameraPresetDefinition;", "Lorg/cloudburstmc/protocol/common/NamedDefinition;", "identifier", "", "runtimeId", "", "<init>", "(Ljava/lang/String;I)V", "getRuntimeId", "getIdentifier", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "MuCuteRelay"}
)
public final class CameraPresetDefinition implements NamedDefinition {
   @NotNull
   public static final CameraPresetDefinition.Companion Companion = new CameraPresetDefinition.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String identifier;
   private final int runtimeId;

   public CameraPresetDefinition(@NotNull String identifier, int runtimeId) {
      Intrinsics.checkNotNullParameter(identifier, "identifier");
      super();
      this.identifier = identifier;
      this.runtimeId = runtimeId;
   }

   public int getRuntimeId() {
      return this.runtimeId;
   }

   @NotNull
   public String getIdentifier() {
      return this.identifier;
   }

   private final String component1() {
      return this.identifier;
   }

   private final int component2() {
      return this.runtimeId;
   }

   @NotNull
   public final CameraPresetDefinition copy(@NotNull String identifier, int runtimeId) {
      Intrinsics.checkNotNullParameter(identifier, "identifier");
      return new CameraPresetDefinition(identifier, runtimeId);
   }

   // $FF: synthetic method
   public static CameraPresetDefinition copy$default(CameraPresetDefinition var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.identifier;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.runtimeId;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "CameraPresetDefinition(identifier=" + this.identifier + ", runtimeId=" + this.runtimeId + ")";
   }

   public int hashCode() {
      int result = this.identifier.hashCode();
      result = result * 31 + Integer.hashCode(this.runtimeId);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof CameraPresetDefinition)) {
         return false;
      } else {
         CameraPresetDefinition var2 = (CameraPresetDefinition)other;
         if (!Intrinsics.areEqual(this.identifier, var2.identifier)) {
            return false;
         } else {
            return this.runtimeId == var2.runtimeId;
         }
      }
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"},
      d2 = {"Lcom/mucheng/mucute/relay/definition/CameraPresetDefinition$Companion;", "", "<init>", "()V", "fromCameraPreset", "Lcom/mucheng/mucute/relay/definition/CameraPresetDefinition;", "preset", "Lorg/cloudburstmc/protocol/bedrock/data/camera/CameraPreset;", "index", "", "MuCuteRelay"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final CameraPresetDefinition fromCameraPreset(@NotNull CameraPreset preset, int index) {
         Intrinsics.checkNotNullParameter(preset, "preset");
         String var10002 = preset.getIdentifier();
         Intrinsics.checkNotNullExpressionValue(var10002, "getIdentifier(...)");
         return new CameraPresetDefinition(var10002, index);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
