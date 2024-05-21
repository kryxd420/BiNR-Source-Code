package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class ho {
    protected static a a;
    private static ho b;

    public abstract void a(a aVar);

    public abstract boolean b();

    public static class a {
        public final String a;
        public final String b;
        public final long c = SystemClock.elapsedRealtime();
        public final fm d = new fm(60000);

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    protected static void a(ho hoVar) {
        synchronized (ho.class) {
            b = hoVar;
            a aVar = a;
            if (aVar != null) {
                a = null;
                hoVar.a(aVar);
            }
        }
    }

    public static void a(String str, String str2) {
        synchronized (ho.class) {
            a aVar = new a(str, str2);
            if (b != null) {
                a = null;
                b.a(aVar);
            } else {
                a = aVar;
            }
        }
    }

    public static boolean c() {
        if (b != null && b.b()) {
            return true;
        }
        a aVar = a;
        if (aVar == null || aVar.d.a()) {
            return false;
        }
        return true;
    }
}
