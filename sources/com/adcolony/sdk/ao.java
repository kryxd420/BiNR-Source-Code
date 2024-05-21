package com.adcolony.sdk;

import android.app.Activity;
import com.adcolony.sdk.aa;
import com.adcolony.sdk.aw;
import com.tapdaq.sdk.TapdaqError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import org.json.JSONObject;

class ao implements Runnable {
    private final long a = 30000;
    private final long b = 5000;
    private final int c = 17;
    private final int d = TapdaqError.HYPRMX_FAILED_TO_LOAD_AD;
    private final int e = 1000;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private boolean n = true;
    private boolean o = true;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    /* access modifiers changed from: private */
    public boolean u;
    private boolean v;

    ao() {
    }

    public void a() {
        a.a("SessionInfo.stopped", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = ao.this.u = true;
            }
        });
    }

    public void run() {
        while (true) {
            if (this.s) {
                break;
            }
            this.i = System.currentTimeMillis();
            a.f();
            if (this.g >= 30000) {
                new aa.a().a("Ending session due to excessive suspend time: ").a((double) this.g).a(aa.d);
                break;
            }
            if (!this.n) {
                if (this.p && !this.o) {
                    this.p = false;
                    f();
                }
                if (!this.t && a.d() && a.c().isFinishing()) {
                    this.t = true;
                    this.k = 0;
                }
                if (this.t) {
                    this.k += this.h;
                    if (this.k > 5000) {
                        new aa.a().a("Ending session due to excessive time between an ").a("Activity finishing and an onResume() event.").a(aa.d);
                        break;
                    }
                }
                this.g += this.h;
            } else {
                if (this.p && this.o) {
                    this.p = false;
                    this.t = false;
                    g();
                }
                this.g = 0;
            }
            this.h = 17;
            a(this.h);
            this.j = System.currentTimeMillis() - this.i;
            if (this.j > 0 && this.j < 6000) {
                this.f += this.j;
            }
            l a2 = a.a();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.m > 15000 && a2.B()) {
                a2.k().g();
                this.m = currentTimeMillis;
            }
            if (a.d() && currentTimeMillis - this.l > 1000) {
                this.l = currentTimeMillis;
                String c2 = a2.d.c();
                if (!c2.equals(a2.v())) {
                    a2.a(c2);
                    JSONObject a3 = y.a();
                    y.a(a3, "network_type", a2.v());
                    new af("Network.on_status_change", 1, a3).b();
                }
            }
        }
        new aa.a().a("AdColony session ending, releasing Activity reference.").a(aa.c);
        a.a().b(true);
        a.a((Activity) null);
        this.r = true;
        this.v = true;
        b();
        aw.a aVar = new aw.a(10.0d);
        while (!this.u && !aVar.b() && this.v) {
            a.f();
            a(100);
        }
        new aa.a().a("SessionInfo.stopped message received, ending ADC.update_module() spam.").a(aa.d);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (!this.q) {
            if (this.r) {
                a.a().b(false);
                this.r = false;
            }
            this.f = 0;
            this.g = 0;
            this.q = true;
            this.n = true;
            this.u = false;
            new Thread(this).start();
            if (z) {
                JSONObject a2 = y.a();
                y.a(a2, "id", aw.e());
                new af("SessionInfo.on_start", 1, a2).b();
            }
            if (AdColony.a.isShutdown()) {
                AdColony.a = Executors.newSingleThreadExecutor();
            }
            ac.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.q = false;
        this.n = false;
        if (ac.l != null) {
            ac.l.a();
        }
        ac.b();
        JSONObject a2 = y.a();
        double d2 = (double) this.f;
        Double.isNaN(d2);
        y.a(a2, "session_length", d2 / 1000.0d);
        new af("SessionInfo.on_stop", 1, a2).b();
        a.f();
        AdColony.a.shutdown();
        new aa.a().a("SESSION STOP").a(aa.d);
    }

    private void f() {
        b(false);
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        ArrayList<ai> c2 = a.a().q().c();
        synchronized (c2) {
            Iterator<ai> it = c2.iterator();
            while (it.hasNext()) {
                JSONObject a2 = y.a();
                y.a(a2, "from_window_focus", z);
                new af("SessionInfo.on_pause", it.next().a(), a2).b();
            }
        }
        this.o = true;
        a.f();
    }

    private void g() {
        c(false);
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z) {
        ArrayList<ai> c2 = a.a().q().c();
        synchronized (c2) {
            Iterator<ai> it = c2.iterator();
            while (it.hasNext()) {
                JSONObject a2 = y.a();
                y.a(a2, "from_window_focus", z);
                new af("SessionInfo.on_resume", it.next().a(), a2).b();
            }
        }
        ac.a();
        this.o = false;
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z) {
        this.n = z;
    }

    /* access modifiers changed from: package-private */
    public void e(boolean z) {
        this.p = z;
    }

    /* access modifiers changed from: package-private */
    public void f(boolean z) {
        this.v = z;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.q;
    }

    private void a(long j2) {
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
        }
    }
}
