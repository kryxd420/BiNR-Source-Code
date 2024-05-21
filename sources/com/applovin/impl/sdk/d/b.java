package com.applovin.impl.sdk.d;

import android.text.TextUtils;
import com.applovin.impl.sdk.b.c;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.k;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinSdk;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class b extends a {
    b(j jVar) {
        super("TaskApiSubmitData", jVar);
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        try {
            this.b.I().c();
            JSONObject a = f.a(jSONObject);
            c w = this.b.w();
            w.a((com.applovin.impl.sdk.b.b<?>) com.applovin.impl.sdk.b.b.T, (Object) a.getString("device_id"));
            w.a((com.applovin.impl.sdk.b.b<?>) com.applovin.impl.sdk.b.b.V, (Object) a.getString("device_token"));
            w.a((com.applovin.impl.sdk.b.b<?>) com.applovin.impl.sdk.b.b.U, (Object) a.getString("publisher_id"));
            w.a();
            f.d(a, this.b);
            this.b.h();
            f.e(a, this.b);
            String a2 = g.a(a, "latest_version", "", this.b);
            if (e(a2)) {
                p v = this.b.v();
                v.e("ALSdk", "Please integrate the latest version of the AppLovin SDK (" + a2 + "). Not doing so can negatively impact your eCPMs!");
            }
            this.b.E().b();
            this.b.F().b();
        } catch (Throwable th) {
            a("Unable to parse API response", th);
        }
    }

    private void b(JSONObject jSONObject) throws JSONException {
        k H = this.b.H();
        k.b c = H.c();
        k.d a = H.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", a.a);
        jSONObject2.put("os", a.b);
        jSONObject2.put("brand", a.d);
        jSONObject2.put("brand_name", a.e);
        jSONObject2.put("hardware", a.f);
        jSONObject2.put("sdk_version", a.h);
        jSONObject2.put("revision", a.g);
        jSONObject2.put("adns", (double) a.m);
        jSONObject2.put("adnsd", a.n);
        jSONObject2.put("gy", com.applovin.impl.sdk.e.k.a(a.v));
        jSONObject2.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, a.i);
        jSONObject2.put("carrier", a.j);
        jSONObject2.put("orientation_lock", a.l);
        jSONObject2.put("tz_offset", a.o);
        jSONObject2.put("adr", com.applovin.impl.sdk.e.k.a(a.q));
        jSONObject2.put("wvvc", a.p);
        jSONObject2.put(AvidVideoPlaybackListenerImpl.VOLUME, a.s);
        jSONObject2.put("type", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        jSONObject2.put("sim", com.applovin.impl.sdk.e.k.a(a.u));
        jSONObject2.put("tv", com.applovin.impl.sdk.e.k.a(a.w));
        jSONObject2.put("fs", a.y);
        g(jSONObject2);
        String k = this.b.k();
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ef)).booleanValue() && com.applovin.impl.sdk.e.k.b(k)) {
            jSONObject2.put("cuid", k);
        }
        Boolean bool = a.z;
        if (bool != null) {
            jSONObject2.put("huc", bool.toString());
        }
        Boolean bool2 = a.A;
        if (bool2 != null) {
            jSONObject2.put("aru", bool2.toString());
        }
        k.c cVar = a.r;
        if (cVar != null) {
            jSONObject2.put("act", cVar.a);
            jSONObject2.put("acm", cVar.b);
        }
        String str = a.t;
        if (com.applovin.impl.sdk.e.k.b(str)) {
            jSONObject2.put("ua", com.applovin.impl.sdk.e.k.e(str));
        }
        String str2 = a.x;
        if (!TextUtils.isEmpty(str2)) {
            jSONObject2.put("so", com.applovin.impl.sdk.e.k.e(str2));
        }
        Locale locale = a.k;
        if (locale != null) {
            jSONObject2.put("locale", com.applovin.impl.sdk.e.k.e(locale.toString()));
        }
        jSONObject.put("device_info", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", c.c);
        jSONObject3.put("installer_name", c.d);
        jSONObject3.put("app_name", c.a);
        jSONObject3.put(TapjoyConstants.TJC_APP_VERSION_NAME, c.b);
        jSONObject3.put("installed_at", c.f);
        jSONObject3.put("tg", c.e);
        jSONObject3.put("applovin_sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("first_install", String.valueOf(this.b.B()));
        String str3 = (String) this.b.a(com.applovin.impl.sdk.b.b.ei);
        if (com.applovin.impl.sdk.e.k.b(str3)) {
            jSONObject3.put("plugin_version", str3);
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ef)).booleanValue() && com.applovin.impl.sdk.e.k.b(k)) {
            jSONObject3.put("cuid", k);
        }
        jSONObject.put("app_info", jSONObject3);
    }

    private void c(JSONObject jSONObject) throws JSONException {
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eA)).booleanValue()) {
            jSONObject.put("stats", this.b.E().c());
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ag)).booleanValue()) {
            JSONObject b = com.applovin.impl.sdk.network.c.b(d());
            if (b.length() > 0) {
                jSONObject.put("network_response_codes", b);
            }
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ah)).booleanValue()) {
                com.applovin.impl.sdk.network.c.a(d());
            }
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONArray a;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eH)).booleanValue() && (a = this.b.I().a()) != null && a.length() > 0) {
            jSONObject.put("errors", a);
        }
    }

    private void e(JSONObject jSONObject) throws JSONException {
        JSONArray a;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eG)).booleanValue() && (a = this.b.F().a()) != null && a.length() > 0) {
            jSONObject.put("tasks", a);
        }
    }

    private boolean e(String str) {
        try {
            if (com.applovin.impl.sdk.e.k.b(str) && !AppLovinSdk.VERSION.equals(str)) {
                List<String> a = d.a(str, "\\.");
                List<String> a2 = d.a(AppLovinSdk.VERSION, "\\.");
                if (a.size() == 3 && a2.size() == 3) {
                    for (int i = 0; i < 3; i++) {
                        int parseInt = Integer.parseInt(a2.get(i));
                        int parseInt2 = Integer.parseInt(a.get(i));
                        if (parseInt < parseInt2) {
                            return true;
                        }
                        if (parseInt > parseInt2) {
                            return false;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            a("Encountered exception while checking if current version is outdated", th);
        }
        return false;
    }

    private void f(JSONObject jSONObject) {
        AnonymousClass1 r0 = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(f.a("2.0/device", this.b)).c(f.b("2.0/device", this.b)).a(f.c(this.b)).b("POST").a(jSONObject).a(new JSONObject()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dP)).intValue()).a(), this.b) {
            public void a(int i) {
                f.a(i, this.b);
            }

            public void a(JSONObject jSONObject, int i) {
                b.this.a(jSONObject);
            }
        };
        r0.a(com.applovin.impl.sdk.b.b.aF);
        r0.b(com.applovin.impl.sdk.b.b.aG);
        this.b.D().a((a) r0);
    }

    private void g(JSONObject jSONObject) {
        try {
            k.a d = this.b.H().d();
            String str = d.b;
            if (com.applovin.impl.sdk.e.k.b(str)) {
                jSONObject.put("idfa", str);
            }
            jSONObject.put("dnt", Boolean.toString(d.a));
        } catch (Throwable th) {
            a("Failed to populate advertising info", th);
        }
    }

    public i a() {
        return i.h;
    }

    public void run() {
        try {
            b("Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            c(jSONObject);
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
        } catch (JSONException e) {
            a("Unable to build JSON message with collected data", e);
            this.b.F().a(a());
        }
    }
}
