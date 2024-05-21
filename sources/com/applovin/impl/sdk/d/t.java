package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.a;
import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import org.json.JSONObject;

class t extends a {
    private final JSONObject a;
    private final JSONObject c;
    private final AppLovinAdLoadListener d;
    private final b e;

    t(JSONObject jSONObject, JSONObject jSONObject2, b bVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super("TaskRenderAppLovinAd", jVar);
        this.a = jSONObject;
        this.c = jSONObject2;
        this.e = bVar;
        this.d = appLovinAdLoadListener;
    }

    public i a() {
        return i.t;
    }

    public void run() {
        a("Rendering ad...");
        a aVar = new a(this.a, this.c, this.e, this.b);
        boolean booleanValue = g.a(this.a, "gs_load_immediately", (Boolean) false, this.b).booleanValue();
        boolean booleanValue2 = g.a(this.a, "vs_load_immediately", (Boolean) true, this.b).booleanValue();
        d dVar = new d(aVar, this.b, this.d);
        dVar.a(booleanValue2);
        dVar.b(booleanValue);
        q.a aVar2 = q.a.CACHING_OTHER;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.aZ)).booleanValue()) {
            if (aVar.getSize() == AppLovinAdSize.INTERSTITIAL && aVar.getType() == AppLovinAdType.REGULAR) {
                aVar2 = q.a.CACHING_INTERSTITIAL;
            } else if (aVar.getSize() == AppLovinAdSize.INTERSTITIAL && aVar.getType() == AppLovinAdType.INCENTIVIZED) {
                aVar2 = q.a.CACHING_INCENTIVIZED;
            }
        }
        this.b.D().a((a) dVar, aVar2);
    }
}
