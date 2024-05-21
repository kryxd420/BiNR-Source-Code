package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

abstract class gg {
    private static final gg a;
    private static gg b;

    public abstract Object a(Context context, String str, TJPlacementListener tJPlacementListener);

    gg() {
    }

    static {
        gh ghVar = new gh();
        a = ghVar;
        b = ghVar;
    }

    static gg a() {
        return b;
    }
}
