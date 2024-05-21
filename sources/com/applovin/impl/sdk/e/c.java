package com.applovin.impl.sdk.e;

import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.t;

public class c implements t.a {
    private final l a;

    private c(long j, j jVar, final Runnable runnable) {
        final t R = jVar.R();
        this.a = l.a(j, jVar, new Runnable() {
            public void run() {
                R.b((t.a) c.this);
                c.this.a();
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        R.a((t.a) this);
    }

    public static c a(long j, j jVar, Runnable runnable) {
        return new c(j, jVar, runnable);
    }

    public void a() {
        this.a.d();
    }

    public void b() {
        this.a.b();
    }

    public void c() {
        this.a.c();
    }
}
