package com.applovin.impl.mediation.b;

import android.app.Activity;
import android.graphics.Point;
import android.text.TextUtils;
import com.applovin.impl.mediation.f;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.c.h;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.w;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.k;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends a {
    private final String a;
    private final MaxAdFormat c;
    private final f d;
    private final Activity e;
    private final MaxAdListener f;
    private JSONArray g;

    public c(String str, MaxAdFormat maxAdFormat, f fVar, Activity activity, j jVar, MaxAdListener maxAdListener) {
        super("TaskFetchMediatedAd " + str, jVar);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No ad unit ID specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (maxAdListener != null) {
            this.a = str;
            this.c = maxAdFormat;
            this.d = fVar;
            this.e = activity;
            this.f = maxAdListener;
        } else {
            throw new IllegalArgumentException("No listener specified");
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        boolean z = i != 204;
        p v = this.b.v();
        String c2 = c();
        Boolean valueOf = Boolean.valueOf(z);
        v.a(c2, valueOf, "Unable to fetch " + this.a + " ad: server returned " + i);
        b(i);
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
    public void a(JSONObject jSONObject) {
        try {
            com.applovin.impl.sdk.e.f.d(jSONObject, this.b);
            com.applovin.impl.sdk.e.f.c(jSONObject, this.b);
            com.applovin.impl.sdk.e.f.f(jSONObject, this.b);
            com.applovin.impl.mediation.c.b.a(jSONObject, this.b);
            com.applovin.impl.mediation.c.b.b(jSONObject, this.b);
            this.b.h();
            f b = b(jSONObject);
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.a.g)).booleanValue()) {
                this.b.D().a((a) b);
                return;
            }
            this.b.D().a((a) b, com.applovin.impl.mediation.c.c.a(this.c, this.b));
        } catch (Throwable th) {
            a("Unable to process mediated ad response", th);
            b(-800);
        }
    }

    private f b(JSONObject jSONObject) {
        return new f(this.a, this.c, jSONObject, this.d, this.e, this.b, this.f);
    }

    private void b(int i) {
        com.applovin.impl.sdk.e.h.a(this.f, this.a, i, this.b);
    }

    private void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("loaded", new JSONArray(this.b.a(this.e).getLoadedAdapterClassnames()));
            jSONObject2.put("failed", new JSONArray(this.b.a(this.e).getFailedAdapterClassnames()));
            jSONObject.put("classname_info", jSONObject2);
            jSONObject.put("initialized_adapter_classnames", new JSONArray(this.b.a(this.e).getInitializedAdapterNames()));
        } catch (Exception e2) {
            a("Failed to populate adapter classnames", e2);
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        if (this.g != null) {
            jSONObject.put("signal_data", this.g);
        }
    }

    private void e(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("ad_unit_id", this.a);
        jSONObject2.put("ad_format", com.applovin.impl.mediation.c.c.a(this.c));
        if (this.d != null && ((Boolean) this.b.a(com.applovin.impl.sdk.b.a.f)).booleanValue()) {
            jSONObject2.put("extra_parameters", com.applovin.impl.sdk.e.g.a((Map<String, ?>) com.applovin.impl.sdk.e.g.a(this.d.a())));
        }
        if (((Boolean) this.b.a(b.ac)).booleanValue()) {
            jSONObject2.put("n", String.valueOf(com.applovin.impl.sdk.f.a(this.b.t()).b(this.a)));
        }
        jSONObject.put("ad_info", jSONObject2);
    }

    private String f() {
        return com.applovin.impl.mediation.c.b.a(this.b);
    }

    private void f(JSONObject jSONObject) throws JSONException {
        k H = this.b.H();
        k.d a2 = H.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("brand", a2.d);
        jSONObject2.put("brand_name", a2.e);
        jSONObject2.put("hardware", a2.f);
        jSONObject2.put("api_level", a2.h);
        jSONObject2.put("carrier", a2.j);
        jSONObject2.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, a2.i);
        jSONObject2.put("locale", a2.k);
        jSONObject2.put("model", a2.a);
        jSONObject2.put("os", a2.b);
        jSONObject2.put(TapjoyConstants.TJC_PLATFORM, a2.c);
        jSONObject2.put("revision", a2.g);
        jSONObject2.put("orientation_lock", a2.l);
        jSONObject2.put("tz_offset", a2.o);
        jSONObject2.put("wvvc", a2.p);
        jSONObject2.put("adns", (double) a2.m);
        jSONObject2.put("adnsd", a2.n);
        jSONObject2.put("sim", com.applovin.impl.sdk.e.k.a(a2.u));
        jSONObject2.put("gy", com.applovin.impl.sdk.e.k.a(a2.v));
        jSONObject2.put("tv", com.applovin.impl.sdk.e.k.a(a2.w));
        jSONObject2.put("fs", a2.y);
        jSONObject2.put("adr", com.applovin.impl.sdk.e.k.a(a2.q));
        jSONObject2.put(AvidVideoPlaybackListenerImpl.VOLUME, a2.s);
        jSONObject2.put("network", com.applovin.impl.sdk.e.f.d(this.b));
        if (com.applovin.impl.sdk.e.k.b(a2.t)) {
            jSONObject2.put("ua", a2.t);
        }
        if (com.applovin.impl.sdk.e.k.b(a2.x)) {
            jSONObject2.put("so", a2.x);
        }
        k.c cVar = a2.r;
        if (cVar != null) {
            jSONObject2.put("act", cVar.a);
            jSONObject2.put("acm", cVar.b);
        }
        Boolean bool = a2.z;
        if (bool != null) {
            jSONObject2.put("huc", bool.toString());
        }
        Boolean bool2 = a2.A;
        if (bool2 != null) {
            jSONObject2.put("aru", bool2.toString());
        }
        Point a3 = e.a(d());
        jSONObject2.put("dx", Integer.toString(a3.x));
        jSONObject2.put("dy", Integer.toString(a3.y));
        g(jSONObject2);
        jSONObject.put("device_info", jSONObject2);
        k.b c2 = H.c();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", c2.c);
        jSONObject3.put("installer_name", c2.d);
        jSONObject3.put("app_name", c2.a);
        jSONObject3.put(TapjoyConstants.TJC_APP_VERSION_NAME, c2.b);
        jSONObject3.put("installed_at", c2.f);
        jSONObject3.put("tg", c2.e);
        jSONObject3.put("api_did", this.b.a(b.T));
        jSONObject3.put("sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("build", 116);
        jSONObject3.put("test_ads", this.b.l().isTestAdsEnabled());
        jSONObject3.put("first_install", String.valueOf(this.b.B()));
        String k = this.b.k();
        if (((Boolean) this.b.a(b.ef)).booleanValue() && com.applovin.impl.sdk.e.k.b(k)) {
            jSONObject3.put("cuid", k);
        }
        String str = (String) this.b.a(b.ei);
        if (com.applovin.impl.sdk.e.k.b(str)) {
            jSONObject3.put("plugin_version", str);
        }
        jSONObject.put("app_info", jSONObject3);
    }

    private String g() {
        return com.applovin.impl.mediation.c.b.b(this.b);
    }

    private void g(JSONObject jSONObject) {
        try {
            k.a d2 = this.b.H().d();
            String str = d2.b;
            if (com.applovin.impl.sdk.e.k.b(str)) {
                jSONObject.put("idfa", str);
            }
            jSONObject.put("dnt", d2.a);
        } catch (Throwable th) {
            a("Failed to populate advertising info", th);
        }
    }

    private JSONObject h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        e(jSONObject);
        f(jSONObject);
        d(jSONObject);
        c(jSONObject);
        jSONObject.put("sc", com.applovin.impl.sdk.e.k.e((String) this.b.a(b.X)));
        jSONObject.put("sc2", com.applovin.impl.sdk.e.k.e((String) this.b.a(b.Y)));
        jSONObject.put("server_installed_at", com.applovin.impl.sdk.e.k.e((String) this.b.a(b.Z)));
        String str = (String) this.b.a(d.s);
        if (com.applovin.impl.sdk.e.k.b(str)) {
            jSONObject.put("persisted_data", com.applovin.impl.sdk.e.k.e(str));
        }
        if (((Boolean) this.b.a(b.eA)).booleanValue()) {
            h(jSONObject);
        }
        if (this.b.i()) {
            jSONObject.put("pnr", Boolean.toString(this.b.j()));
        }
        jSONObject.put("mediation_provider", this.b.n());
        return jSONObject;
    }

    private void h(JSONObject jSONObject) {
        try {
            h E = this.b.E();
            jSONObject.put("li", String.valueOf(E.b(g.b)));
            jSONObject.put("si", String.valueOf(E.b(g.d)));
            jSONObject.put("pf", String.valueOf(E.b(g.h)));
            jSONObject.put("mpf", String.valueOf(E.b(g.r)));
            jSONObject.put("gpf", String.valueOf(E.b(g.i)));
        } catch (Throwable th) {
            a("Failed to populate ad serving info", th);
        }
    }

    public i a() {
        return i.C;
    }

    public void a(JSONArray jSONArray) {
        this.g = jSONArray;
    }

    public void run() {
        a("Fetching next ad for ad unit id: " + this.a + " and format: " + this.c);
        h E = this.b.E();
        E.a(g.q);
        if (E.b(g.c) == 0) {
            E.b(g.c, System.currentTimeMillis());
        }
        try {
            JSONObject h = h();
            HashMap hashMap = new HashMap();
            hashMap.put("rid", UUID.randomUUID().toString());
            if (h.has("huc")) {
                hashMap.put("huc", String.valueOf(com.applovin.impl.sdk.e.g.a(h, "huc", (Boolean) false, this.b)));
            }
            if (h.has("aru")) {
                hashMap.put("aru", String.valueOf(com.applovin.impl.sdk.e.g.a(h, "aru", (Boolean) false, this.b)));
            }
            if (!((Boolean) this.b.a(b.eV)).booleanValue()) {
                hashMap.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.t());
            }
            a(E);
            AnonymousClass1 r2 = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).b("POST").a(f()).c(g()).a((Map<String, String>) hashMap).a(h).a(new JSONObject()).b(((Long) this.b.a(com.applovin.impl.sdk.b.a.e)).intValue()).a(((Integer) this.b.a(b.dN)).intValue()).c(((Long) this.b.a(com.applovin.impl.sdk.b.a.d)).intValue()).a(), this.b) {
                public void a(int i) {
                    c.this.a(i);
                }

                public void a(JSONObject jSONObject, int i) {
                    if (i == 200) {
                        com.applovin.impl.sdk.e.g.b(jSONObject, "ad_fetch_latency_millis", this.d.a(), this.b);
                        com.applovin.impl.sdk.e.g.b(jSONObject, "ad_fetch_response_size", this.d.b(), this.b);
                        c.this.a(jSONObject);
                        return;
                    }
                    c.this.a(i);
                }
            };
            r2.a(com.applovin.impl.sdk.b.a.a);
            r2.b(com.applovin.impl.sdk.b.a.b);
            this.b.D().a((a) r2);
        } catch (Throwable th) {
            a("Unable to fetch ad " + this.a, th);
            a(0);
            this.b.F().a(a());
        }
    }
}
