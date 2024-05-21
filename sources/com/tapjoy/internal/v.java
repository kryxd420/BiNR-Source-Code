package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public final class v {
    private static Handler a;

    public static synchronized Handler a() {
        Handler handler;
        synchronized (v.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    public static bd a(final Handler handler) {
        return new bd() {
            public final boolean a(Runnable runnable) {
                return handler.post(runnable);
            }
        };
    }
}
