package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.reflect.Method;
import java.util.Map;

class ag implements bc {
    private static final a a;
    private static final a b;

    static {
        a a2 = a.a();
        a a3 = a.a();
        try {
            Method method = NativeDisplayTracker.class.getMethod("track", new Class[]{Map.class});
            Method method2 = NativeDisplayTracker.class.getMethod("stopTracking", new Class[0]);
            a a4 = a.a(method);
            try {
                a3 = a.a(method2);
                a2 = a4;
            } catch (NoSuchMethodException e) {
                a aVar = a4;
                e = e;
                a2 = aVar;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                a = a2;
                b = a3;
            }
        } catch (NoSuchMethodException e2) {
            e = e2;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            a = a2;
            b = a3;
        }
        a = a2;
        b = a3;
    }

    ag() {
    }

    public Class a() {
        return NativeDisplayTracker.class;
    }
}
