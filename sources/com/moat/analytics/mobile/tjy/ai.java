package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.reflect.Method;
import java.util.Map;

class ai implements bc {
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;
    private static final a e;

    static {
        a aVar;
        a aVar2;
        a aVar3;
        a aVar4;
        a a2 = a.a();
        a a3 = a.a();
        a a4 = a.a();
        a a5 = a.a();
        a a6 = a.a();
        Class<NativeVideoTracker> cls = NativeVideoTracker.class;
        try {
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, MediaPlayer.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            Method method5 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            aVar = a.a(method);
            try {
                aVar4 = a.a(method2);
                try {
                    aVar3 = a.a(method3);
                } catch (NoSuchMethodException e2) {
                    e = e2;
                    aVar3 = a4;
                    aVar2 = a6;
                    com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                    a = aVar;
                    b = aVar4;
                    c = aVar3;
                    d = aVar2;
                    e = a5;
                }
                try {
                    aVar2 = a.a(method4);
                    try {
                        a5 = a.a(method5);
                    } catch (NoSuchMethodException e3) {
                        e = e3;
                        com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                        a = aVar;
                        b = aVar4;
                        c = aVar3;
                        d = aVar2;
                        e = a5;
                    }
                } catch (NoSuchMethodException e4) {
                    e = e4;
                    aVar2 = a6;
                    com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                    a = aVar;
                    b = aVar4;
                    c = aVar3;
                    d = aVar2;
                    e = a5;
                }
            } catch (NoSuchMethodException e5) {
                e = e5;
                aVar4 = a3;
                aVar3 = a4;
                aVar2 = a6;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                a = aVar;
                b = aVar4;
                c = aVar3;
                d = aVar2;
                e = a5;
            }
        } catch (NoSuchMethodException e6) {
            e = e6;
            aVar = a2;
            aVar4 = a3;
            aVar3 = a4;
            aVar2 = a6;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            a = aVar;
            b = aVar4;
            c = aVar3;
            d = aVar2;
            e = a5;
        }
        a = aVar;
        b = aVar4;
        c = aVar3;
        d = aVar2;
        e = a5;
    }

    ai() {
    }

    public Class a() {
        return NativeVideoTracker.class;
    }
}
