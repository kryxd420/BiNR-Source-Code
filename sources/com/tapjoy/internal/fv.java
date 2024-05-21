package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;

public final class fv {
    private static final gd a = new gd() {
        /* access modifiers changed from: protected */
        public final /* bridge */ /* synthetic */ String a(Object obj) {
            return "InsufficientCurrency";
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "InsufficientCurrency", true, tJPlacementListener);
        }
    };

    public static void a() {
        a.c((Object) null);
    }
}
