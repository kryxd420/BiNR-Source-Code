package com.tapjoy.internal;

import android.os.Build;
import android.view.View;
import android.view.ViewParent;

public final class dt {
    public static float a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean c(View view) {
        return (Build.VERSION.SDK_INT < 19 || view.isAttachedToWindow()) && view.getVisibility() == 0 && view.getAlpha() != 0.0f;
    }

    public static boolean b(View view) {
        if ((Build.VERSION.SDK_INT >= 19 && !view.isAttachedToWindow()) || !view.isShown()) {
            return false;
        }
        while (view != null) {
            if (view.getAlpha() == 0.0f) {
                return false;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return true;
    }
}
