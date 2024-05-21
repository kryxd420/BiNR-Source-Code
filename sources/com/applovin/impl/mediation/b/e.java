package com.applovin.impl.mediation.b;

import android.app.Activity;
import com.applovin.impl.mediation.a.b;
import com.applovin.impl.mediation.a.c;
import com.applovin.impl.mediation.a.d;
import com.applovin.impl.mediation.f;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxErrorCodes;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends a {
    private final String a;
    private final JSONObject c;
    private final JSONObject d;
    private final f e;
    private final MaxAdListener f;
    private final Activity g;

    e(String str, f fVar, JSONObject jSONObject, JSONObject jSONObject2, j jVar, Activity activity, MaxAdListener maxAdListener) {
        super("TaskLoadAdapterAd " + str, jVar);
        this.c = jSONObject;
        this.d = jSONObject2;
        this.a = str;
        this.e = fVar;
        this.g = activity;
        this.f = maxAdListener;
    }

    private com.applovin.impl.mediation.a.a f() throws JSONException {
        String string = this.d.getString("ad_format");
        MaxAdFormat c2 = n.c(string);
        if (c2 == MaxAdFormat.BANNER || c2 == MaxAdFormat.MREC || c2 == MaxAdFormat.LEADER) {
            return new b(this.c, this.d, this.b);
        }
        if (c2 == MaxAdFormat.NATIVE) {
            return new d(this.c, this.d, this.b);
        }
        if (c2 == MaxAdFormat.INTERSTITIAL || c2 == MaxAdFormat.REWARDED) {
            return new c(this.c, this.d, this.b);
        }
        throw new IllegalArgumentException("Unsupported ad format: " + string);
    }

    public i a() {
        return i.D;
    }

    public void run() {
        try {
            this.b.a(this.g).loadThirdPartyMediatedAd(this.a, f(), this.g, this.f);
        } catch (Throwable th) {
            a("Unable to process adapter ad", th);
            this.b.F().a(a());
            h.a(this.f, this.a, (int) MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, this.b);
        }
    }
}
