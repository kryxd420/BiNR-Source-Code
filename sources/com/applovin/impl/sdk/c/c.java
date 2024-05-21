package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.d.w;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final p b;
    private final Object c = new Object();
    private final b d = new b();

    private static class a {
        private final String a;
        private final String b;
        private final String c;
        private final long d;
        private final Map<String, Long> e;

        private a(String str, String str2, String str3) {
            this.e = new HashMap();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = System.currentTimeMillis();
        }

        private JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pk", this.a);
            jSONObject.put("ts", this.d);
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("sk1", this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("sk2", this.c);
            }
            for (Map.Entry next : this.e.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
            return jSONObject;
        }

        /* access modifiers changed from: private */
        public String b() throws JSONException {
            return a().toString();
        }

        /* access modifiers changed from: package-private */
        public void a(String str, long j) {
            this.e.put(str, Long.valueOf(j));
        }

        public String toString() {
            return "AdEventStats{pk='" + this.a + '\'' + ", size=" + this.e.size() + '}';
        }
    }

    private class b extends LinkedHashMap<String, a> {
        private b() {
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry<String, a> entry) {
            return size() > ((Integer) c.this.a.a(com.applovin.impl.sdk.b.b.eE)).intValue();
        }
    }

    public c(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            this.b = jVar.v();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private a a(AppLovinAdBase appLovinAdBase) {
        a aVar;
        synchronized (this.c) {
            String primaryKey = appLovinAdBase.getPrimaryKey();
            aVar = (a) this.d.get(primaryKey);
            if (aVar == null) {
                aVar = new a(primaryKey, appLovinAdBase.getSecondaryKey1(), appLovinAdBase.getSecondaryKey2());
                this.d.put(primaryKey, aVar);
            }
        }
        return aVar;
    }

    private void a(JSONObject jSONObject) {
        AnonymousClass1 r0 = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.a).a(c()).c(d()).a(f.c(this.a)).b("POST").a(jSONObject).b(((Integer) this.a.a(com.applovin.impl.sdk.b.b.eC)).intValue()).a(((Integer) this.a.a(com.applovin.impl.sdk.b.b.eD)).intValue()).a(), this.a) {
            public void a(int i) {
                p a2 = c.this.b;
                a2.d("AdEventStatsManager", "Failed to submitted ad stats: " + i);
            }

            public void a(JSONObject jSONObject, int i) {
                p a2 = c.this.b;
                a2.a("AdEventStatsManager", "Ad stats submitted: " + i);
            }
        };
        r0.a(com.applovin.impl.sdk.b.b.aF);
        r0.b(com.applovin.impl.sdk.b.b.aG);
        this.a.D().a((com.applovin.impl.sdk.d.a) r0, q.a.BACKGROUND);
    }

    private String c() {
        return f.a("2.0/s", this.a);
    }

    private String d() {
        return f.b("2.0/s", this.a);
    }

    private void e() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.d.size());
            for (a aVar : this.d.values()) {
                try {
                    String a2 = aVar.b();
                    if (a2 != null) {
                        hashSet.add(a2);
                    }
                } catch (JSONException e) {
                    p pVar = this.b;
                    pVar.b("AdEventStatsManager", "Failed to serialize " + aVar, e);
                }
            }
        }
        this.a.a(d.n, hashSet);
    }

    public void a() {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eB)).booleanValue()) {
            Set<String> set = (Set) this.a.b(d.n, new HashSet(0));
            this.a.b(d.n);
            if (set == null || set.isEmpty()) {
                this.b.a("AdEventStatsManager", "No serialized ad events found");
                return;
            }
            p pVar = this.b;
            pVar.a("AdEventStatsManager", "De-serializing " + set.size() + " stat ad events");
            JSONArray jSONArray = new JSONArray();
            for (String str : set) {
                try {
                    jSONArray.put(new JSONObject(str));
                } catch (JSONException e) {
                    p pVar2 = this.b;
                    pVar2.b("AdEventStatsManager", "Failed to parse: " + str, e);
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stats", jSONArray);
                a(jSONObject);
            } catch (JSONException e2) {
                this.b.b("AdEventStatsManager", "Failed to create stats to submit", e2);
            }
        }
    }

    public void a(b bVar, long j, AppLovinAdBase appLovinAdBase) {
        if (appLovinAdBase == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (bVar == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eB)).booleanValue()) {
            synchronized (this.c) {
                a(appLovinAdBase).a(((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eF)).booleanValue() ? bVar.b() : bVar.a(), j);
            }
            e();
        }
    }

    public void b() {
        synchronized (this.c) {
            this.d.clear();
        }
    }
}
