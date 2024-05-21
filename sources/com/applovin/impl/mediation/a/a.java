package com.applovin.impl.mediation.a;

import android.os.SystemClock;
import com.applovin.impl.mediation.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import org.json.JSONObject;

public abstract class a extends e implements MaxAd {
    protected h a;

    protected a(JSONObject jSONObject, JSONObject jSONObject2, h hVar, j jVar) {
        super(jSONObject, jSONObject2, jVar);
        this.a = hVar;
    }

    private long h() {
        return b("load_started_time_ms", 0);
    }

    public abstract a a(h hVar);

    public boolean a() {
        return b("is_backup", (Boolean) false);
    }

    public h b() {
        return this.a;
    }

    public String c() {
        return b("bid_response", (String) null);
    }

    public String d() {
        return b("third_party_ad_placement_id", (String) null);
    }

    public long e() {
        if (h() > 0) {
            return SystemClock.elapsedRealtime() - h();
        }
        return -1;
    }

    public void f() {
        c("load_started_time_ms", SystemClock.elapsedRealtime());
    }

    public void g() {
        this.a = null;
    }

    public String getAdUnitId() {
        return a("ad_unit_id", (String) null);
    }

    public MaxAdFormat getFormat() {
        return n.c(a("ad_format", (String) null));
    }

    public boolean isReady() {
        return this.a != null && this.a.c() && this.a.d();
    }
}
