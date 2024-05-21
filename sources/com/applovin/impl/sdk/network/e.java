package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private String a;
    private String b;
    private Map<String, String> c;
    private Map<String, String> d;
    private boolean e;
    private int f;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Map<String, String> c;
        /* access modifiers changed from: private */
        public Map<String, String> d;
        /* access modifiers changed from: private */
        public boolean e;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.c = map;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public e a() {
            return new e(this);
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a b(Map<String, String> map) {
            this.d = map;
            return this;
        }
    }

    private e(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = 0;
    }

    e(JSONObject jSONObject, j jVar) throws Exception {
        String str;
        Map<String, String> map;
        String string = jSONObject.getString("targetUrl");
        int i = jSONObject.getInt("attemptNumber");
        Map<String, String> a2 = g.a(jSONObject, "parameters") ? g.a(jSONObject.getJSONObject("parameters")) : Collections.EMPTY_MAP;
        if (((Boolean) jVar.a(b.dE)).booleanValue()) {
            str = g.a(jSONObject, "backupUrl", "", jVar);
            if (!g.a(jSONObject, "requestBody")) {
                map = Collections.EMPTY_MAP;
                this.a = string;
                this.b = str;
                this.c = a2;
                this.d = map;
                this.e = jSONObject.optBoolean("isEncodingEnabled", false);
                this.f = i;
            }
        } else {
            str = jSONObject.getString("backupUrl");
        }
        map = g.a(jSONObject.getJSONObject("requestBody"));
        this.a = string;
        this.b = str;
        this.c = a2;
        this.d = map;
        this.e = jSONObject.optBoolean("isEncodingEnabled", false);
        this.f = i;
    }

    public static a j() {
        return new a();
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (this.a == null ? eVar.a != null : !this.a.equals(eVar.a)) {
            return false;
        }
        if (this.b == null ? eVar.b != null : !this.b.equals(eVar.b)) {
            return false;
        }
        if (this.c == null ? eVar.c != null : !this.c.equals(eVar.c)) {
            return false;
        }
        if (this.d == null ? eVar.d == null : this.d.equals(eVar.d)) {
            return this.f == eVar.f && this.e == eVar.e;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.f++;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        HashMap hashMap = new HashMap();
        if (this.c != null) {
            hashMap.putAll(this.c);
        }
        hashMap.put("postback_ts", String.valueOf(System.currentTimeMillis()));
        this.c = hashMap;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f * 31) + (this.a != null ? this.a.hashCode() : 0)) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return (hashCode + i + (this.e ? 1 : 0)) * 31;
    }

    /* access modifiers changed from: package-private */
    public JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("targetUrl", this.a);
        jSONObject.put("backupUrl", this.b);
        jSONObject.put("isEncodingEnabled", this.e);
        jSONObject.put("attemptNumber", this.f);
        if (this.c != null) {
            jSONObject.put("parameters", new JSONObject(this.c));
        }
        if (this.d != null) {
            jSONObject.put("requestBody", new JSONObject(this.d));
        }
        return jSONObject;
    }

    public String toString() {
        return "PostbackRequest{targetUrl='" + this.a + '\'' + ", backupUrl='" + this.b + '\'' + ", attemptNumber=" + this.f + ", isEncodingEnabled=" + this.e + '}';
    }
}
