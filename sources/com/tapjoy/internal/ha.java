package com.tapjoy.internal;

import android.os.Looper;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;

public final class ha {
    public static boolean a;

    public static void a(String str) {
        if (a) {
            aa.a(4, TMMediationNetworks.TAPJOY_NAME, str, (Throwable) null);
        }
    }

    public static void a(String str, Object... objArr) {
        if (a) {
            aa.a(4, TMMediationNetworks.TAPJOY_NAME, str, objArr);
        }
    }

    public static void b(String str) {
        if (a) {
            aa.a(6, TMMediationNetworks.TAPJOY_NAME, str, (Throwable) null);
        }
    }

    public static void b(String str, Object... objArr) {
        if (a) {
            aa.a(TMMediationNetworks.TAPJOY_NAME, str, objArr);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (a) {
            aa.a(TMMediationNetworks.TAPJOY_NAME, "{}: {} {}", str, str2, str3);
        }
    }

    public static boolean a(Object obj, String str) {
        if (obj != null) {
            return true;
        }
        if (!a) {
            return false;
        }
        b(str);
        return false;
    }

    public static boolean a(boolean z, String str) {
        if (!a || z) {
            return z;
        }
        b(str);
        throw new IllegalStateException(str);
    }

    public static boolean c(String str) {
        boolean z = Looper.myLooper() == Looper.getMainLooper();
        return a(z, str + ": Must be called on the main/ui thread");
    }
}
