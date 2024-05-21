package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.b.e;

public class g {
    public static Boolean a(Context context) {
        return a(d.f, context);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.applovin.impl.sdk.b.d, com.applovin.impl.sdk.b.d<java.lang.Boolean>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean a(com.applovin.impl.sdk.b.d<java.lang.Boolean> r1, android.content.Context r2) {
        /*
            r0 = 0
            java.lang.Object r1 = com.applovin.impl.sdk.b.e.b(r1, r0, (android.content.Context) r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.g.a(com.applovin.impl.sdk.b.d, android.content.Context):java.lang.Boolean");
    }

    private static boolean a(d<Boolean> dVar, Boolean bool, Context context) {
        Boolean a = a(dVar, context);
        e.a(dVar, bool, context);
        return a == null || a != bool;
    }

    public static boolean a(boolean z, Context context) {
        return a(d.f, Boolean.valueOf(z), context);
    }

    public static Boolean b(Context context) {
        return a(d.g, context);
    }

    public static boolean b(boolean z, Context context) {
        return a(d.g, Boolean.valueOf(z), context);
    }
}
