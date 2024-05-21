package com.applovin.impl.mediation.a;

import com.applovin.impl.mediation.h;
import com.applovin.impl.sdk.j;
import org.json.JSONObject;

public class d extends a {
    private d(d dVar, h hVar) {
        super(dVar.s(), dVar.r(), hVar, dVar.b);
    }

    public d(JSONObject jSONObject, JSONObject jSONObject2, j jVar) {
        super(jSONObject, jSONObject2, (h) null, jVar);
    }

    public a a(h hVar) {
        return new d(this, hVar);
    }

    public String toString() {
        return "MediatedNativeAd{format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + t() + "', adapterName='" + u() + "', isTesting=" + v() + ", isRefreshEnabled=" + z() + ", getAdRefreshMillis=" + A() + '}';
    }
}
