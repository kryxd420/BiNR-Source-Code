package com.applovin.impl.sdk;

import com.applovin.impl.sdk.e.l;
import com.applovin.impl.sdk.t;

public class b implements t.a {
    private final j a;
    /* access modifiers changed from: private */
    public final a b;
    private l c;
    private final Object d = new Object();
    private long e;

    public interface a {
        void onAdExpired();
    }

    public b(j jVar, a aVar) {
        this.a = jVar;
        this.b = aVar;
    }

    private void d() {
        if (this.c != null) {
            this.c.d();
            this.c = null;
        }
    }

    public void a() {
        synchronized (this.d) {
            d();
            this.a.R().b((t.a) this);
        }
    }

    public void a(long j) {
        synchronized (this.d) {
            a();
            this.e = System.currentTimeMillis() + j;
            this.a.R().a((t.a) this);
            this.c = l.a(j, this.a, new Runnable() {
                public void run() {
                    b.this.a();
                    b.this.b.onAdExpired();
                }
            });
        }
    }

    public void b() {
        synchronized (this.d) {
            d();
        }
    }

    public void c() {
        boolean z;
        synchronized (this.d) {
            long currentTimeMillis = this.e - System.currentTimeMillis();
            if (currentTimeMillis <= 0) {
                a();
                z = true;
            } else {
                a(currentTimeMillis);
                z = false;
            }
        }
        if (z) {
            this.b.onAdExpired();
        }
    }
}
