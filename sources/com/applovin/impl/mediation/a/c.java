package com.applovin.impl.mediation.a;

import com.applovin.impl.mediation.h;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.j;
import org.json.JSONObject;

public class c extends a {
    private c(c cVar, h hVar) {
        super(cVar.s(), cVar.r(), hVar, cVar.b);
    }

    public c(JSONObject jSONObject, JSONObject jSONObject2, j jVar) {
        super(jSONObject, jSONObject2, (h) null, jVar);
    }

    public a a(h hVar) {
        return new c(this, hVar);
    }

    public long h() {
        long b = b("ad_expiration_ms", -1);
        return b >= 0 ? b : a("ad_expiration_ms", ((Long) this.b.a(a.L)).longValue());
    }

    public long i() {
        long b = b("ad_hidden_timeout_ms", -1);
        return b >= 0 ? b : a("ad_hidden_timeout_ms", ((Long) this.b.a(a.M)).longValue());
    }

    public boolean j() {
        if (b("schedule_ad_hidden_on_ad_dismiss", (Boolean) false)) {
            return true;
        }
        return a("schedule_ad_hidden_on_ad_dismiss", (Boolean) this.b.a(a.O));
    }

    public long k() {
        long b = b("ad_hidden_on_ad_dismiss_callback_delay_ms", -1);
        return b >= 0 ? b : a("ad_hidden_on_ad_dismiss_callback_delay_ms", ((Long) this.b.a(a.P)).longValue());
    }

    public String toString() {
        return "MediatedFullscreenAd{format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + t() + "', adapterName='" + u() + "', isTesting=" + v() + ", isRefreshEnabled=" + z() + ", getAdRefreshMillis=" + A() + '}';
    }
}
