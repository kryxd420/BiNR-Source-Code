package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

@fx
public class TapjoyNative {
    @fx
    public static Object createPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        return gg.a().a(context, str, tJPlacementListener);
    }
}
