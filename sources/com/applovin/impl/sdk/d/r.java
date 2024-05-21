package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class r extends a implements AppLovinAdLoadListener {
    private final JSONObject a;
    private final d c;
    private final b d;
    private final AppLovinAdLoadListener e;

    public r(JSONObject jSONObject, d dVar, b bVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super("TaskProcessAdResponse", jVar);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (dVar != null) {
            this.a = jSONObject;
            this.c = dVar;
            this.d = bVar;
            this.e = appLovinAdLoadListener;
        } else {
            throw new IllegalArgumentException("No zone specified");
        }
    }

    private void a(int i) {
        n.a(this.e, this.c, i, this.b);
    }

    private void a(AppLovinAd appLovinAd) {
        try {
            if (this.e != null) {
                this.e.adReceived(appLovinAd);
            }
        } catch (Throwable th) {
            a("Unable process a ad received notification", th);
        }
    }

    private void a(JSONObject jSONObject) {
        String a2 = g.a(jSONObject, "type", "undefined", this.b);
        if ("applovin".equalsIgnoreCase(a2)) {
            a("Starting task for AppLovin ad...");
            this.b.D().a((a) new t(jSONObject, this.a, this.d, this, this.b));
        } else if ("vast".equalsIgnoreCase(a2)) {
            a("Starting task for VAST ad...");
            this.b.D().a((a) s.a(jSONObject, this.a, this.d, this, this.b));
        } else {
            c("Unable to process ad of unknown type: " + a2);
            failedToReceiveAd(-800);
        }
    }

    private void f() {
        a(-6);
    }

    public i a() {
        return i.q;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        a(appLovinAd);
    }

    public void failedToReceiveAd(int i) {
        f();
    }

    public void run() {
        try {
            a("Processing ad response...");
            JSONArray jSONArray = this.a.getJSONArray("ads");
            if (jSONArray.length() > 0) {
                a("Processing ad...");
                a(jSONArray.getJSONObject(0));
                return;
            }
            c("No ads were returned from the server");
            n.a(this.c.a(), this.a, this.b);
            a(204);
        } catch (Throwable th) {
            a("Encountered error while processing ad response", th);
            f();
            this.b.F().a(a());
        }
    }
}
