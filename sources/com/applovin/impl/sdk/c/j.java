package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.p;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private final com.applovin.impl.sdk.j a;
    private final p b;
    private final Object c = new Object();
    private final Map<String, b> d = new HashMap();

    private static class a {
        static final String a = a("tk");
        static final String b = a("tc");
        static final String c = a("ec");
        static final String d = a("dm");
        static final String e = a("dv");
        static final String f = a("dh");
        static final String g = a("dl");
        private static final Set<String> h = new HashSet(7);

        private static String a(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("No key name specified");
            } else if (!h.contains(str)) {
                h.add(str);
                return str;
            } else {
                throw new IllegalArgumentException("Key has already been used: " + str);
            }
        }
    }

    private static class b {
        private final String a;
        private int b = 0;
        private int c = 0;
        private double d = 0.0d;
        private double e = 0.0d;
        private Long f = null;
        private Long g = null;

        b(String str) {
            this.a = str;
        }

        b(JSONObject jSONObject) throws JSONException {
            this.a = jSONObject.getString(a.a);
            this.b = jSONObject.getInt(a.b);
            this.c = jSONObject.getInt(a.c);
            this.d = jSONObject.getDouble(a.d);
            this.e = jSONObject.getDouble(a.e);
            this.f = Long.valueOf(jSONObject.optLong(a.f));
            this.g = Long.valueOf(jSONObject.optLong(a.g));
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public void a(long j) {
            int i = this.b;
            double d2 = this.d;
            double d3 = this.e;
            this.b++;
            double d4 = (double) i;
            Double.isNaN(d4);
            double d5 = (double) j;
            Double.isNaN(d5);
            double d6 = (double) this.b;
            Double.isNaN(d6);
            this.d = ((d2 * d4) + d5) / d6;
            double d7 = (double) this.b;
            Double.isNaN(d4);
            Double.isNaN(d7);
            double d8 = d4 / d7;
            Double.isNaN(d5);
            double pow = Math.pow(d2 - d5, 2.0d);
            double d9 = (double) this.b;
            Double.isNaN(d9);
            this.e = d8 * (d3 + (pow / d9));
            if (this.f == null || j > this.f.longValue()) {
                this.f = Long.valueOf(j);
            }
            if (this.g == null || j < this.g.longValue()) {
                this.g = Long.valueOf(j);
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.c++;
        }

        /* access modifiers changed from: package-private */
        public JSONObject c() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(a.a, this.a);
            jSONObject.put(a.b, this.b);
            jSONObject.put(a.c, this.c);
            jSONObject.put(a.d, this.d);
            jSONObject.put(a.e, this.e);
            jSONObject.put(a.f, this.f);
            jSONObject.put(a.g, this.g);
            return jSONObject;
        }

        public String toString() {
            try {
                return "TaskStats{n='" + this.a + '\'' + ", stats=" + c().toString() + '}';
            } catch (JSONException unused) {
                return "TaskStats{n='" + this.a + '\'' + ", count=" + this.b + '}';
            }
        }
    }

    public j(com.applovin.impl.sdk.j jVar) {
        this.a = jVar;
        this.b = jVar.v();
        c();
    }

    private b b(i iVar) {
        b bVar;
        synchronized (this.c) {
            String a2 = iVar.a();
            bVar = this.d.get(a2);
            if (bVar == null) {
                bVar = new b(a2);
                this.d.put(a2, bVar);
            }
        }
        return bVar;
    }

    private void c() {
        Set<String> set = (Set) this.a.a(d.k);
        if (set != null) {
            synchronized (this.c) {
                try {
                    for (String jSONObject : set) {
                        b bVar = new b(new JSONObject(jSONObject));
                        this.d.put(bVar.a(), bVar);
                    }
                } catch (JSONException e) {
                    this.b.b("TaskStatsManager", "Failed to convert stats json.", e);
                }
            }
        }
    }

    private void d() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.d.size());
            for (b next : this.d.values()) {
                try {
                    hashSet.add(next.c().toString());
                } catch (JSONException e) {
                    p pVar = this.b;
                    pVar.b("TaskStatsManager", "Failed to serialize " + next, e);
                }
            }
        }
        this.a.a(d.k, hashSet);
    }

    public JSONArray a() {
        JSONArray jSONArray;
        synchronized (this.c) {
            jSONArray = new JSONArray();
            for (b next : this.d.values()) {
                try {
                    jSONArray.put(next.c());
                } catch (JSONException e) {
                    p pVar = this.b;
                    pVar.b("TaskStatsManager", "Failed to serialize " + next, e);
                }
            }
        }
        return jSONArray;
    }

    public void a(i iVar) {
        a(iVar, false, 0);
    }

    public void a(i iVar, long j) {
        if (iVar == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eG)).booleanValue()) {
            synchronized (this.c) {
                b(iVar).a(j);
                d();
            }
        }
    }

    public void a(i iVar, boolean z, long j) {
        if (iVar == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eG)).booleanValue()) {
            synchronized (this.c) {
                b b2 = b(iVar);
                b2.b();
                if (z) {
                    b2.a(j);
                }
                d();
            }
        }
    }

    public void b() {
        synchronized (this.c) {
            this.d.clear();
            this.a.b(d.k);
        }
    }
}
