package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public class he implements gs {
    private static final he a = new he() {
        public final void a(String str) {
        }

        public final void a(String str, gp gpVar) {
        }

        public final void a(String str, String str2, gp gpVar) {
        }

        public final void b(String str) {
        }

        public final void c(String str) {
        }

        public final void d(String str) {
        }
    };
    /* access modifiers changed from: private */
    public final gs b;
    private final bd c;

    /* synthetic */ he(byte b2) {
        this();
    }

    public static he a(gs gsVar) {
        if (!(!(gsVar instanceof he))) {
            throw new IllegalArgumentException();
        } else if (gsVar != null) {
            return new he(gsVar);
        } else {
            return a;
        }
    }

    private he() {
        this.b = null;
        this.c = null;
    }

    private he(gs gsVar) {
        Handler handler;
        this.b = gsVar;
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            jt.a(myLooper);
            handler = myLooper == Looper.getMainLooper() ? v.a() : new Handler(myLooper);
        } else {
            handler = null;
        }
        if (handler != null) {
            this.c = v.a(handler);
            new Object[1][0] = handler.getLooper();
        } else if (Thread.currentThread() == gv.b()) {
            this.c = gv.a;
        } else {
            this.c = v.a(v.a());
        }
    }

    public void a(final String str) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.a(str);
            }
        });
    }

    public void b(final String str) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.b(str);
            }
        });
    }

    public void c(final String str) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.c(str);
            }
        });
    }

    public void d(final String str) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.d(str);
            }
        });
    }

    public void a(final String str, final gp gpVar) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.a(str, gpVar);
            }
        });
    }

    public void a(final String str, final String str2, final gp gpVar) {
        this.c.a(new Runnable() {
            public final void run() {
                he.this.b.a(str, str2, gpVar);
            }
        });
    }
}
