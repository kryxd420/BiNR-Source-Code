package com.tapjoy.internal;

import android.text.TextUtils;

public final class ds {
    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void a(dc dcVar) {
        if (dcVar.e) {
            throw new IllegalStateException("AdSession is finished");
        }
    }

    public static void b(dc dcVar) {
        if (dcVar.d) {
            a(dcVar);
            return;
        }
        throw new IllegalStateException("AdSession is not started");
    }
}
