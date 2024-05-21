package com.tapjoy.internal;

import android.content.Context;
import android.os.Handler;

public final class cn {
    private static co a = new co();

    public static String a() {
        return "1.1.0-tapjoy";
    }

    public static boolean b() {
        return a.a;
    }

    public static boolean a(String str, Context context) {
        co coVar = a;
        Context applicationContext = context.getApplicationContext();
        co.b(str);
        ds.a((Object) applicationContext, "Application Context cannot be null");
        if (!(co.a("1.1.0-tapjoy") == co.a(str))) {
            return false;
        }
        if (!coVar.a) {
            coVar.a = true;
            dk a2 = dk.a();
            a2.b = new cs(new Handler(), applicationContext, new cp(), a2);
            dh.a().a = applicationContext.getApplicationContext();
            dp.a(applicationContext);
            di.a().a = applicationContext != null ? applicationContext.getApplicationContext() : null;
        }
        return true;
    }
}
