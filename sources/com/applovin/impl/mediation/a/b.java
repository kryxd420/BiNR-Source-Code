package com.applovin.impl.mediation.a;

import android.view.View;
import com.applovin.impl.mediation.h;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAdFormat;
import org.json.JSONObject;

public class b extends a {
    private b(b bVar, h hVar) {
        super(bVar.s(), bVar.r(), hVar, bVar.b);
    }

    public b(JSONObject jSONObject, JSONObject jSONObject2, j jVar) {
        super(jSONObject, jSONObject2, (h) null, jVar);
    }

    public a a(h hVar) {
        return new b(this, hVar);
    }

    public int h() {
        return a("ad_view_width", ((Integer) this.b.a(a.t)).intValue());
    }

    public int i() {
        return a("ad_view_height", ((Integer) this.b.a(a.u)).intValue());
    }

    public View j() {
        if (!isReady() || this.a == null) {
            return null;
        }
        View a = this.a.a();
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Ad-view based ad is missing an ad view");
    }

    public long k() {
        return b("viewability_imp_delay_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.b.ci)).longValue());
    }

    public int l() {
        return a("viewability_min_width", ((Integer) this.b.a(getFormat() == MaxAdFormat.BANNER ? com.applovin.impl.sdk.b.b.cj : getFormat() == MaxAdFormat.MREC ? com.applovin.impl.sdk.b.b.cl : com.applovin.impl.sdk.b.b.cn)).intValue());
    }

    public int m() {
        return a("viewability_min_height", ((Integer) this.b.a(getFormat() == MaxAdFormat.BANNER ? com.applovin.impl.sdk.b.b.ck : getFormat() == MaxAdFormat.MREC ? com.applovin.impl.sdk.b.b.cm : com.applovin.impl.sdk.b.b.co)).intValue());
    }

    public float n() {
        return a("viewability_min_alpha", ((Float) this.b.a(com.applovin.impl.sdk.b.b.cp)).floatValue() / 100.0f);
    }

    public int o() {
        return a("viewability_min_pixels", -1);
    }

    public boolean p() {
        return o() >= 0;
    }

    public long q() {
        return b("viewability_timer_min_visible_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.b.cq)).longValue());
    }

    public String toString() {
        return "MediatedAdViewAd{width=" + h() + ", height=" + i() + ", format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + t() + "', adapterName='" + u() + "', isTesting=" + v() + ", isRefreshEnabled=" + z() + ", getAdRefreshMillis=" + A() + '}';
    }
}
