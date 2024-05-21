package com.applovin.impl.mediation.a;

import android.content.Context;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinPrivacySettings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private final JSONObject a;
    protected final j b;
    private final JSONObject c;
    private final Object d = new Object();
    private final Object e = new Object();

    public e(JSONObject jSONObject, JSONObject jSONObject2, j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No full response specified");
        } else if (jSONObject != null) {
            this.b = jVar;
            this.a = jSONObject2;
            this.c = jSONObject;
        } else {
            throw new IllegalArgumentException("No spec object specified");
        }
    }

    private List<String> a(List<String> list, Map<String, String> map) {
        p v = this.b.v();
        v.a("MediationAdapterSpec", "Replacing postback macros for postbacks: " + list);
        Map<String, String> a2 = a();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            for (String next2 : a2.keySet()) {
                next = next.replace(next2, g(a2.get(next2)));
            }
            for (String next3 : map.keySet()) {
                next = next.replace(next3, map.get(next3));
            }
            arrayList.add(next);
        }
        p v2 = this.b.v();
        v2.a("MediationAdapterSpec", "Finished replacing macros for postbacks: " + arrayList);
        return arrayList;
    }

    private Map<String, String> a() {
        try {
            return g.a(new JSONObject((String) this.b.a(a.h)));
        } catch (JSONException unused) {
            return Collections.EMPTY_MAP;
        }
    }

    private List<String> e(String str) {
        try {
            return g.b(a(str, new JSONArray()));
        } catch (JSONException unused) {
            return Collections.EMPTY_LIST;
        }
    }

    private List<String> f(String str) {
        try {
            return g.b(b(str, new JSONArray()));
        } catch (JSONException unused) {
            return Collections.EMPTY_LIST;
        }
    }

    private String g(String str) {
        String b2 = b(str, "");
        return k.b(b2) ? b2 : a(str, "");
    }

    public long A() {
        long b2 = b("ad_refresh_ms", -1);
        return b2 >= 0 ? b2 : a("ad_refresh_ms", ((Long) this.b.a(a.v)).longValue());
    }

    public long B() {
        long b2 = b("fullscreen_display_delay_ms", -1);
        return b2 >= 0 ? b2 : ((Long) this.b.a(a.D)).longValue();
    }

    public long C() {
        long b2 = b("init_completion_delay_ms", -1);
        return b2 >= 0 ? b2 : ((Long) this.b.a(a.p)).longValue();
    }

    public boolean D() {
        return b("destroy_on_load_failure") ? b("destroy_on_load_failure", (Boolean) false) : a("destroy_on_load_failure", (Boolean) this.b.a(a.Q));
    }

    /* access modifiers changed from: protected */
    public float a(String str, float f) {
        float a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, f, this.b);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int a(String str, int i) {
        int a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, i, this.b);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public long a(String str, long j) {
        long a2;
        synchronized (this.e) {
            a2 = g.a(this.a, str, j, this.b);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public String a(String str, String str2) {
        String a2;
        synchronized (this.e) {
            a2 = g.a(this.a, str, str2, this.b);
        }
        return a2;
    }

    public List<String> a(String str, Map<String, String> map) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (b(str)) {
            return a(f(str), map);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public JSONArray a(String str, JSONArray jSONArray) {
        JSONArray a2;
        synchronized (this.e) {
            a2 = g.a(this.a, str, jSONArray, this.b);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public JSONObject a(String str, JSONObject jSONObject) {
        JSONObject a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, jSONObject, this.b);
        }
        return a2;
    }

    public boolean a(Context context) {
        return b("huc") ? b("huc", (Boolean) false) : a("huc", Boolean.valueOf(AppLovinPrivacySettings.hasUserConsent(context)));
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        boolean has;
        synchronized (this.e) {
            has = this.a.has(str);
        }
        return has;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, Boolean bool) {
        boolean booleanValue;
        synchronized (this.e) {
            booleanValue = g.a(this.a, str, bool, this.b).booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public long b(String str, long j) {
        long a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, j, this.b);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public String b(String str, String str2) {
        String a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, str2, this.b);
        }
        return a2;
    }

    public List<String> b(String str, Map<String, String> map) {
        List<String> list;
        if (str != null) {
            boolean a2 = a(str);
            boolean b2 = b(str);
            if (!a2 && !b2) {
                return null;
            }
            if (!b2 || !a2) {
                if (b2) {
                    list = f(str);
                    return a(list, map);
                }
            } else if (!d(str)) {
                List<String> f = f(str);
                list = e(str);
                list.addAll(f);
                return a(list, map);
            }
            list = e(str);
            return a(list, map);
        }
        throw new IllegalArgumentException("No key specified");
    }

    /* access modifiers changed from: protected */
    public JSONArray b(String str, JSONArray jSONArray) {
        JSONArray a2;
        synchronized (this.d) {
            a2 = g.a(this.c, str, jSONArray, this.b);
        }
        return a2;
    }

    public boolean b(Context context) {
        return b("aru") ? b("aru", (Boolean) false) : a("aru", Boolean.valueOf(AppLovinPrivacySettings.isAgeRestrictedUser(context)));
    }

    /* access modifiers changed from: protected */
    public boolean b(String str) {
        boolean has;
        synchronized (this.d) {
            has = this.c.has(str);
        }
        return has;
    }

    /* access modifiers changed from: protected */
    public boolean b(String str, Boolean bool) {
        boolean booleanValue;
        synchronized (this.d) {
            booleanValue = g.a(this.c, str, bool, this.b).booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public Object c(String str) {
        Object opt;
        synchronized (this.d) {
            opt = this.c.opt(str);
        }
        return opt;
    }

    /* access modifiers changed from: protected */
    public void c(String str, long j) {
        synchronized (this.d) {
            g.b(this.c, str, j, this.b);
        }
    }

    public boolean d(String str) {
        return a("fire_in_succession_" + str, (Boolean) true);
    }

    /* access modifiers changed from: protected */
    public JSONObject r() {
        JSONObject jSONObject;
        synchronized (this.e) {
            jSONObject = this.a;
        }
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public JSONObject s() {
        JSONObject jSONObject;
        synchronized (this.d) {
            jSONObject = this.c;
        }
        return jSONObject;
    }

    public String t() {
        return b("class", (String) null);
    }

    public String toString() {
        return "MediationAdapterSpec{adapterClass='" + t() + "', adapterName='" + u() + "', isTesting=" + v() + ", isRefreshEnabled=" + z() + ", getAdRefreshMillis=" + A() + '}';
    }

    public String u() {
        return b("name", (String) null);
    }

    public boolean v() {
        return b("is_testing") ? b("is_testing", (Boolean) false) : a("is_testing", (Boolean) this.b.a(a.E));
    }

    public boolean w() {
        return b("run_on_ui_thread", (Boolean) this.b.a(a.r));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = a("server_parameters", (org.json.JSONObject) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle x() {
        /*
            r2 = this;
            java.lang.String r0 = "server_parameters"
            java.lang.Object r0 = r2.c(r0)
            boolean r0 = r0 instanceof org.json.JSONObject
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = "server_parameters"
            r1 = 0
            org.json.JSONObject r0 = r2.a((java.lang.String) r0, (org.json.JSONObject) r1)
            if (r0 == 0) goto L_0x0018
            android.os.Bundle r0 = com.applovin.impl.sdk.e.g.b((org.json.JSONObject) r0)
            return r0
        L_0x0018:
            android.os.Bundle r0 = android.os.Bundle.EMPTY
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.mediation.a.e.x():android.os.Bundle");
    }

    public long y() {
        return b("adapter_timeout_ms", ((Long) this.b.a(a.s)).longValue());
    }

    public boolean z() {
        return A() >= 0;
    }
}
