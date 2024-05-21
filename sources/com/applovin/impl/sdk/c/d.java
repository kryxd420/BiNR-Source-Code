package com.applovin.impl.sdk.c;

import android.app.Activity;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAd;

public class d {
    private final AppLovinAdBase a;
    private final long b;
    private final c c;
    private final h d;
    private final j e;
    private final Object f = new Object();
    private long g;
    private long h;
    private long i;
    private long j;
    private boolean k;

    public d(AppLovinAd appLovinAd, j jVar) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (jVar != null) {
            this.c = jVar.Q();
            this.d = jVar.E();
            this.e = jVar;
            if (appLovinAd instanceof AppLovinAdBase) {
                this.a = (AppLovinAdBase) appLovinAd;
                this.b = this.a.getCreatedAtMillis();
                this.c.a(b.a, (long) this.a.getSource().ordinal(), this.a);
                return;
            }
            this.a = null;
            this.b = 0;
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    public static void a(long j2, AppLovinAdBase appLovinAdBase, j jVar) {
        if (appLovinAdBase != null && jVar != null) {
            jVar.Q().a(b.b, j2, appLovinAdBase);
        }
    }

    public static void a(AppLovinAdBase appLovinAdBase, j jVar) {
        if (appLovinAdBase != null && jVar != null) {
            jVar.Q().a(b.c, appLovinAdBase.getFetchLatencyMillis(), appLovinAdBase);
            jVar.Q().a(b.d, appLovinAdBase.getFetchResponseSize(), appLovinAdBase);
        }
    }

    private void a(b bVar) {
        synchronized (this.f) {
            if (this.g > 0) {
                this.c.a(bVar, System.currentTimeMillis() - this.g, this.a);
            }
        }
    }

    public static void a(e eVar, AppLovinAdBase appLovinAdBase, j jVar) {
        if (appLovinAdBase != null && jVar != null && eVar != null) {
            jVar.Q().a(b.e, eVar.c(), appLovinAdBase);
            jVar.Q().a(b.f, eVar.d(), appLovinAdBase);
            jVar.Q().a(b.v, eVar.g(), appLovinAdBase);
            jVar.Q().a(b.w, eVar.h(), appLovinAdBase);
            jVar.Q().a(b.z, eVar.b() ? 1 : 0, appLovinAdBase);
        }
    }

    public void a() {
        this.c.a(b.j, this.d.a(g.b), this.a);
        this.c.a(b.i, this.d.a(g.d), this.a);
        synchronized (this.f) {
            long j2 = 0;
            if (this.b > 0) {
                this.g = System.currentTimeMillis();
                this.c.a(b.h, this.g - this.e.A(), this.a);
                this.c.a(b.g, this.g - this.b, this.a);
                this.c.a(b.p, f.a(this.e.x(), this.e) ? 1 : 0, this.a);
                Activity a2 = this.e.T().a();
                if (e.h() && a2 != null && a2.isInMultiWindowMode()) {
                    j2 = 1;
                }
                this.c.a(b.A, j2, this.a);
            }
        }
    }

    public void a(long j2) {
        this.c.a(b.r, j2, this.a);
    }

    public void b() {
        synchronized (this.f) {
            if (this.h < 1) {
                this.h = System.currentTimeMillis();
                if (this.g > 0) {
                    this.c.a(b.m, this.h - this.g, this.a);
                }
            }
        }
    }

    public void b(long j2) {
        this.c.a(b.q, j2, this.a);
    }

    public void c() {
        a(b.k);
    }

    public void c(long j2) {
        this.c.a(b.s, j2, this.a);
    }

    public void d() {
        a(b.n);
    }

    public void d(long j2) {
        synchronized (this.f) {
            if (this.i < 1) {
                this.i = j2;
                this.c.a(b.t, j2, this.a);
            }
        }
    }

    public void e() {
        a(b.o);
    }

    public void e(long j2) {
        synchronized (this.f) {
            if (!this.k) {
                this.k = true;
                this.c.a(b.x, j2, this.a);
            }
        }
    }

    public void f() {
        a(b.l);
    }

    public void g() {
        this.c.a(b.u, 1, this.a);
    }

    public void h() {
        synchronized (this.f) {
            if (this.j < 1) {
                this.j = System.currentTimeMillis();
                if (this.g > 0) {
                    this.c.a(b.y, this.j - this.g, this.a);
                }
            }
        }
    }
}
