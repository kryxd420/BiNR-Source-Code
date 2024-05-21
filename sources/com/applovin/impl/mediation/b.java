package com.applovin.impl.mediation;

import com.applovin.impl.mediation.a;
import com.applovin.impl.mediation.c;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdkUtils;

public class b implements a.C0002a, c.a {
    private final a a;
    private final c b;
    /* access modifiers changed from: private */
    public final MaxAdListener c;

    public b(j jVar, MaxAdListener maxAdListener) {
        this.c = maxAdListener;
        this.a = new a(jVar);
        this.b = new c(jVar, this);
    }

    public void a(final com.applovin.impl.mediation.a.c cVar) {
        AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
            public void run() {
                b.this.c.onAdHidden(cVar);
            }
        }, cVar.k());
    }

    public void a(MaxAd maxAd) {
        this.b.a();
        this.a.a();
    }

    public void b(com.applovin.impl.mediation.a.c cVar) {
        long i = cVar.i();
        if (i >= 0) {
            this.b.a(cVar, i);
        }
        if (cVar.j()) {
            this.a.a(cVar, this);
        }
    }

    public void c(com.applovin.impl.mediation.a.c cVar) {
        this.c.onAdHidden(cVar);
    }
}
