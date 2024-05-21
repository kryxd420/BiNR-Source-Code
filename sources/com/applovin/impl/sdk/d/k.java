package com.applovin.impl.sdk.d;

import android.os.Build;
import android.text.TextUtils;
import com.applovin.impl.mediation.c.b;
import com.applovin.impl.mediation.c.c;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.g;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.k;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class k extends a {
    private static int a;
    private final AtomicBoolean c = new AtomicBoolean();

    private class a extends a {
        public a(j jVar) {
            super("TaskTimeoutFetchBasicSettings", jVar, true);
        }

        public i a() {
            return i.g;
        }

        public void run() {
            d("Timing out fetch basic settings...");
            k.this.a(new JSONObject());
        }
    }

    public k(j jVar) {
        super("TaskFetchBasicSettings", jVar, true);
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        if (this.c.compareAndSet(false, true)) {
            f.d(jSONObject, this.b);
            f.c(jSONObject, this.b);
            b.a(jSONObject, this.b);
            b.b(jSONObject, this.b);
            b("Executing initialize SDK...");
            this.b.D().a((a) new p(this.b));
            f.e(jSONObject, this.b);
            f.f(jSONObject, this.b);
            b("Finished executing initialize SDK");
        }
    }

    private String g() {
        return f.a((String) this.b.a(com.applovin.impl.sdk.b.b.aB), "4.0/i", b());
    }

    private String h() {
        return f.a((String) this.b.a(com.applovin.impl.sdk.b.b.aC), "4.0/i", b());
    }

    public i a() {
        return i.d;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> f() {
        HashMap hashMap = new HashMap();
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put("build", String.valueOf(116));
        int i = a + 1;
        a = i;
        hashMap.put("init_count", String.valueOf(i));
        hashMap.put("server_installed_at", com.applovin.impl.sdk.e.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        n.a("first_install", Boolean.valueOf(this.b.B()), (Map<String, String>) hashMap);
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
            hashMap.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.t());
        }
        Boolean a2 = g.a(d());
        if (a2 != null) {
            hashMap.put("huc", a2.toString());
        }
        Boolean b = g.b(d());
        if (b != null) {
            hashMap.put("aru", b.toString());
        }
        String str = (String) this.b.a(com.applovin.impl.sdk.b.b.ei);
        if (com.applovin.impl.sdk.e.k.b(str)) {
            hashMap.put("plugin_version", com.applovin.impl.sdk.e.k.e(str));
        }
        String n = this.b.n();
        if (com.applovin.impl.sdk.e.k.b(n)) {
            hashMap.put("mediation_provider", com.applovin.impl.sdk.e.k.e(n));
        }
        c.a a3 = c.a();
        hashMap.put("installed_mediation_adapter_classnames", com.applovin.impl.sdk.e.k.e(TextUtils.join(",", a3.a())));
        hashMap.put("uninstalled_mediation_adapter_classnames", com.applovin.impl.sdk.e.k.e(TextUtils.join(",", a3.b())));
        k.b c2 = this.b.H().c();
        hashMap.put("package_name", com.applovin.impl.sdk.e.k.e(c2.c));
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, com.applovin.impl.sdk.e.k.e(c2.b));
        hashMap.put(TapjoyConstants.TJC_PLATFORM, com.applovin.impl.sdk.e.k.e(this.b.H().b()));
        hashMap.put("os", com.applovin.impl.sdk.e.k.e(Build.VERSION.RELEASE));
        hashMap.put("tg", this.b.a(d.d));
        return hashMap;
    }

    public void run() {
        com.applovin.impl.sdk.network.b<T> a2 = com.applovin.impl.sdk.network.b.a(this.b).a(g()).c(h()).a(f()).b("GET").a(new JSONObject()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dS)).intValue()).c(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dT)).intValue()).b(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dR)).intValue()).a();
        this.b.D().a((a) new a(this.b), q.a.TIMEOUT, ((long) ((Integer) this.b.a(com.applovin.impl.sdk.b.b.dR)).intValue()) + 250);
        AnonymousClass1 r1 = new w<JSONObject>(a2, this.b, e()) {
            public void a(int i) {
                d("Unable to fetch basic SDK settings: server returned " + i);
                k.this.a(new JSONObject());
            }

            public void a(JSONObject jSONObject, int i) {
                k.this.a(jSONObject);
            }
        };
        r1.a(com.applovin.impl.sdk.b.b.aD);
        r1.b(com.applovin.impl.sdk.b.b.aE);
        this.b.D().a((a) r1);
    }
}
