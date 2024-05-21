package com.tapjoy.internal;

public final class as {

    public static final class a implements av {
        private final at a;

        public a(at atVar) {
            this.a = atVar;
        }

        public final Object a(Object obj) {
            ar a2;
            Object a3;
            synchronized (this.a) {
                a2 = this.a.a(obj, false);
            }
            if (a2 == null) {
                return null;
            }
            synchronized (a2) {
                a3 = a2.a();
            }
            return a3;
        }

        public final void a(Object obj, Object obj2) {
            ar a2;
            synchronized (this.a) {
                a2 = this.a.a(obj, true);
            }
            synchronized (a2) {
                a2.a(obj2);
            }
        }
    }
}
