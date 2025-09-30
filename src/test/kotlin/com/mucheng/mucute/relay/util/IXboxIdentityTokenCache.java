package com.mucheng.mucute.relay.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"},
   d2 = {"Lcom/mucheng/mucute/relay/util/IXboxIdentityTokenCache;", "", "identifier", "", "getIdentifier", "()Ljava/lang/String;", "cache", "", "device", "Lcom/mucheng/mucute/relay/util/XboxDeviceInfo;", "token", "Lcom/mucheng/mucute/relay/util/XboxIdentityToken;", "checkCache", "MuCuteRelay"}
)
public interface IXboxIdentityTokenCache {
   @NotNull
   String getIdentifier();

   void cache(@NotNull XboxDeviceInfo var1, @NotNull XboxIdentityToken var2);

   @Nullable
   XboxIdentityToken checkCache(@NotNull XboxDeviceInfo var1);
}
