package com.applovin.impl.sdk.d;

import com.applovin.impl.a.c;
import com.applovin.impl.a.d;
import com.applovin.impl.a.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.b;
import com.applovin.sdk.AppLovinAdLoadListener;

class y extends a {
    /* access modifiers changed from: private */
    public c a;
    /* access modifiers changed from: private */
    public final AppLovinAdLoadListener c;

    y(c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super("TaskResolveVastWrapper", jVar);
        this.c = appLovinAdLoadListener;
        this.a = cVar;
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        d("Failed to resolve VAST wrapper due to error code " + i);
        if (i == -103) {
            n.a(this.c, this.a.g(), i, this.b);
        } else {
            i.a(this.a, this.c, i == -102 ? d.TIMED_OUT : d.GENERAL_WRAPPER_ERROR, i, this.b);
        }
    }

    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.x;
    }

    public void run() {
        String a2 = i.a(this.a);
        if (k.b(a2)) {
            a("Resolving VAST ad with depth " + this.a.a() + " at " + a2);
            try {
                this.b.D().a((a) new w<o>(b.a(this.b).a(a2).b("GET").a(o.a).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.eS)).intValue()).b(((Integer) this.b.a(com.applovin.impl.sdk.b.b.eT)).intValue()).a(false).a(), this.b) {
                    public void a(int i) {
                        d("Unable to resolve VAST wrapper. Server returned " + i);
                        y.this.a(i);
                    }

                    public void a(o oVar, int i) {
                        this.b.D().a((a) s.a(oVar, y.this.a, y.this.c, y.this.b));
                    }
                });
            } catch (Throwable th) {
                a("Unable to resolve VAST wrapper", th);
                a(-1);
                this.b.F().a(a());
            }
        } else {
            d("Resolving VAST failed. Could not find resolution URL");
            a(-1);
        }
    }
}
