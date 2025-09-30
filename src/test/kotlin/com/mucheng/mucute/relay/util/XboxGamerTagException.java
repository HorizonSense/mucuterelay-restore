package com.mucheng.mucute.relay.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lcom/mucheng/mucute/relay/util/XboxGamerTagException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "sisuStartUrl", "", "<init>", "(Ljava/lang/String;)V", "getSisuStartUrl", "()Ljava/lang/String;", "MuCuteRelay"}
)
public final class XboxGamerTagException extends IllegalStateException {
   @NotNull
   private final String sisuStartUrl;

   public XboxGamerTagException(@NotNull String sisuStartUrl) {
      Intrinsics.checkNotNullParameter(sisuStartUrl, "sisuStartUrl");
      super("Have you registered a Xbox GamerTag? You can register it here: " + sisuStartUrl);
      this.sisuStartUrl = sisuStartUrl;
   }

   @NotNull
   public final String getSisuStartUrl() {
      return this.sisuStartUrl;
   }
}
