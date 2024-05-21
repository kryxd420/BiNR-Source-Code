package com.moat.analytics.mobile.vng;

import android.os.Handler;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class w {
    private static w g;
    /* access modifiers changed from: private */
    public static final Queue<c> h = new ConcurrentLinkedQueue();
    volatile d a = d.OFF;
    volatile boolean b = false;
    volatile boolean c = false;
    volatile int d = 200;
    private long e = TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
    /* access modifiers changed from: private */
    public long f = 60000;
    /* access modifiers changed from: private */
    public Handler i;
    /* access modifiers changed from: private */
    public final AtomicBoolean j = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public volatile long k = 0;
    /* access modifiers changed from: private */
    public final AtomicInteger l = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final AtomicBoolean m = new AtomicBoolean(false);

    private class a implements Runnable {
        private final Handler b;
        private final String c;
        /* access modifiers changed from: private */
        public final e d;

        private a(String str, Handler handler, e eVar) {
            this.d = eVar;
            this.b = handler;
            this.c = "https://z.moatads.com/" + str + "/android/" + "3f2ae9c1894282b5e0222f0d06bbf457191f816f".substring(0, 7) + "/status.json";
        }

        private void a() {
            String b2 = b();
            final l lVar = new l(b2);
            w.this.b = lVar.a();
            w.this.c = lVar.b();
            w.this.d = lVar.c();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        a.this.d.a(lVar);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
            long unused = w.this.k = System.currentTimeMillis();
            w.this.m.compareAndSet(true, false);
            if (b2 != null) {
                w.this.l.set(0);
            } else if (w.this.l.incrementAndGet() < 10) {
                w.this.a(w.this.f);
            }
        }

        private String b() {
            try {
                return q.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.2.0").b();
            } catch (Exception unused) {
                return null;
            }
        }

        public void run() {
            try {
                a();
            } catch (Exception e) {
                m.a(e);
            }
            this.b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    interface b {
        void b();

        void c();
    }

    private class c {
        final Long a;
        final b b;

        c(Long l, b bVar) {
            this.a = l;
            this.b = bVar;
        }
    }

    enum d {
        OFF,
        ON
    }

    interface e {
        void a(l lVar);
    }

    private w() {
        try {
            this.i = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            if (g == null) {
                g = new w();
            }
            wVar = g;
        }
        return wVar;
    }

    /* access modifiers changed from: private */
    public void a(final long j2) {
        if (this.m.compareAndSet(false, true)) {
            p.a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread() {
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new a("VNG", handler, new e() {
                        public void a(l lVar) {
                            synchronized (w.h) {
                                boolean z = ((k) MoatAnalytics.getInstance()).a;
                                if (w.this.a != lVar.d() || (w.this.a == d.OFF && z)) {
                                    w.this.a = lVar.d();
                                    if (w.this.a == d.OFF && z) {
                                        w.this.a = d.ON;
                                    }
                                    if (w.this.a == d.ON) {
                                        p.a(3, "OnOff", (Object) this, "Moat enabled - Version 2.2.0");
                                    }
                                    for (c cVar : w.h) {
                                        if (w.this.a == d.ON) {
                                            cVar.b.b();
                                        } else {
                                            cVar.b.c();
                                        }
                                    }
                                }
                                while (!w.h.isEmpty()) {
                                    w.h.remove();
                                }
                            }
                        }
                    }), j2);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        synchronized (h) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = h.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((c) it.next()).a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (h.size() >= 15) {
                for (int i2 = 0; i2 < 5; i2++) {
                    h.remove();
                }
            }
        }
    }

    private void e() {
        if (this.j.compareAndSet(false, true)) {
            this.i.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (w.h.size() > 0) {
                            w.this.d();
                            w.this.i.postDelayed(this, 60000);
                            return;
                        }
                        w.this.j.compareAndSet(true, false);
                        w.this.i.removeCallbacks(this);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 60000);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (this.a == d.ON) {
            bVar.b();
            return;
        }
        d();
        h.add(new c(Long.valueOf(System.currentTimeMillis()), bVar));
        e();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (System.currentTimeMillis() - this.k > this.e) {
            a(0);
        }
    }
}
