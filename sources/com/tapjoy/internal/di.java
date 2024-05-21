package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.Context;

public final class di {
    @SuppressLint({"StaticFieldLeak"})
    private static di b = new di();
    public Context a;

    private di() {
    }

    public static di a() {
        return b;
    }
}
