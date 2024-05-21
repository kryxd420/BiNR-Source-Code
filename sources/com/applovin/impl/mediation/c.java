package com.applovin.impl.mediation;

import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;

public class c {
    private final j a;
    /* access modifiers changed from: private */
    public final p b;
    /* access modifiers changed from: private */
    public final a c;
    private com.applovin.impl.sdk.e.c d;

    public interface a {
        void c(com.applovin.impl.mediation.a.c cVar);
    }

    c(j jVar, a aVar) {
        this.a = jVar;
        this.b = jVar.v();
        this.c = aVar;
    }

    public void a() {
        this.b.a("AdHiddenCallbackTimeoutManager", "Cancelling timeout");
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }

    public void a(final com.applovin.impl.mediation.a.c cVar, long j) {
        p pVar = this.b;
        pVar.a("AdHiddenCallbackTimeoutManager", "Scheduling in " + j + "ms...");
        this.d = com.applovin.impl.sdk.e.c.a(j, this.a, new Runnable() {
            public void run() {
                c.this.b.a("AdHiddenCallbackTimeoutManager", "Timing out...");
                c.this.c.c(cVar);
            }
        });
    }
}
