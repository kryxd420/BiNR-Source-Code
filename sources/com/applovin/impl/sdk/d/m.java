package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class m extends a {
    private final d a;
    private final AppLovinAdLoadListener c;
    private boolean d;

    public m(d dVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        this(dVar, appLovinAdLoadListener, "TaskFetchNextAd", jVar);
    }

    m(d dVar, AppLovinAdLoadListener appLovinAdLoadListener, String str, j jVar) {
        super(str, jVar);
        this.d = false;
        this.a = dVar;
        this.c = appLovinAdLoadListener;
    }

    private void a(h hVar) {
        long b = hVar.b(g.c);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b > TimeUnit.MINUTES.toMillis((long) ((Integer) this.b.a(b.dX)).intValue())) {
            hVar.b(g.c, currentTimeMillis);
            hVar.c(g.d);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i) {
        boolean z = i != 204;
        p v = b().v();
        String c2 = c();
        Boolean valueOf = Boolean.valueOf(z);
        v.a(c2, valueOf, "Unable to fetch " + this.a + " ad: server returned " + i);
        try {
            a(i);
        } catch (Throwable th) {
            b().v().c(c(), "Unable process a failure to recieve an ad", th);
        }
    }

    /* access modifiers changed from: private */
    public void b(JSONObject jSONObject) {
        f.d(jSONObject, this.b);
        f.c(jSONObject, this.b);
        this.b.h();
        f.e(jSONObject, this.b);
        a a2 = a(jSONObject);
        if (((Boolean) this.b.a(b.eZ)).booleanValue()) {
            this.b.D().a(a2);
        } else {
            this.b.D().a(a2, q.a.MAIN);
        }
    }

    public i a() {
        return i.n;
    }

    /* access modifiers changed from: protected */
    public a a(JSONObject jSONObject) {
        return new r(jSONObject, this.a, g(), this.c, this.b);
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (this.c == null) {
            return;
        }
        if (this.c instanceof com.applovin.impl.sdk.m) {
            ((com.applovin.impl.sdk.m) this.c).a(this.a, i);
        } else {
            this.c.failedToReceiveAd(i);
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> f() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("zone_id", k.e(this.a.a()));
        if (this.a.b() != null) {
            hashMap.put("size", this.a.b().getLabel());
        }
        if (this.a.c() != null) {
            hashMap.put("require", this.a.c().getLabel());
        }
        if (((Boolean) this.b.a(b.ac)).booleanValue()) {
            hashMap.put("n", String.valueOf(com.applovin.impl.sdk.f.a(this.b.t()).b(this.a.a())));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public com.applovin.impl.sdk.ad.b g() {
        return this.a.l() ? com.applovin.impl.sdk.ad.b.APPLOVIN_PRIMARY_ZONE : com.applovin.impl.sdk.ad.b.APPLOVIN_CUSTOM_ZONE;
    }

    /* access modifiers changed from: protected */
    public String h() {
        return f.e(this.b);
    }

    /* access modifiers changed from: protected */
    public String i() {
        return f.f(this.b);
    }

    public void run() {
        StringBuilder sb;
        String str;
        if (this.d) {
            sb = new StringBuilder();
            str = "Preloading next ad of zone: ";
        } else {
            sb = new StringBuilder();
            str = "Fetching next ad of zone: ";
        }
        sb.append(str);
        sb.append(this.a);
        a(sb.toString());
        h E = this.b.E();
        E.a(g.a);
        if (E.b(g.c) == 0) {
            E.b(g.c, System.currentTimeMillis());
        }
        try {
            Map<String, String> a2 = this.b.H().a(f(), this.d, false);
            a(E);
            AnonymousClass1 r2 = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(h()).a(a2).c(i()).b("GET").a(new JSONObject()).a(((Integer) this.b.a(b.dN)).intValue()).b(((Integer) this.b.a(b.dM)).intValue()).a(), this.b) {
                public void a(int i) {
                    m.this.b(i);
                }

                public void a(JSONObject jSONObject, int i) {
                    if (i == 200) {
                        com.applovin.impl.sdk.e.g.b(jSONObject, "ad_fetch_latency_millis", this.d.a(), this.b);
                        com.applovin.impl.sdk.e.g.b(jSONObject, "ad_fetch_response_size", this.d.b(), this.b);
                        m.this.b(jSONObject);
                        return;
                    }
                    m.this.b(i);
                }
            };
            r2.a(b.aD);
            r2.b(b.aE);
            this.b.D().a((a) r2);
        } catch (Throwable th) {
            a("Unable to fetch ad " + this.a, th);
            b(0);
            this.b.F().a(a());
        }
    }
}
