package com.moat.analytics.mobile.tjy;

import android.view.View;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.reflect.Method;
import java.util.Map;

class be implements bc {
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;

    static {
        a aVar;
        a aVar2;
        a aVar3;
        a aVar4;
        a a2 = a.a();
        a a3 = a.a();
        a a4 = a.a();
        a a5 = a.a();
        Class<ReactiveVideoTracker> cls = ReactiveVideoTracker.class;
        try {
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, View.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{MoatAdEvent.class});
            aVar = a.a(method);
            try {
                aVar4 = a.a(method2);
                try {
                    aVar3 = a.a(method3);
                } catch (NoSuchMethodException e) {
                    e = e;
                    aVar3 = a4;
                    com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                    aVar2 = a5;
                    a = aVar;
                    b = aVar4;
                    c = aVar3;
                    d = aVar2;
                }
            } catch (NoSuchMethodException e2) {
                e = e2;
                aVar4 = a3;
                aVar3 = a4;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                aVar2 = a5;
                a = aVar;
                b = aVar4;
                c = aVar3;
                d = aVar2;
            }
            try {
                aVar2 = a.a(method4);
            } catch (NoSuchMethodException e3) {
                e = e3;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                aVar2 = a5;
                a = aVar;
                b = aVar4;
                c = aVar3;
                d = aVar2;
            }
        } catch (NoSuchMethodException e4) {
            e = e4;
            aVar = a2;
            aVar4 = a3;
            aVar3 = a4;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            aVar2 = a5;
            a = aVar;
            b = aVar4;
            c = aVar3;
            d = aVar2;
        }
        a = aVar;
        b = aVar4;
        c = aVar3;
        d = aVar2;
    }

    be() {
    }

    public Class a() {
        return ReactiveVideoTracker.class;
    }
}
