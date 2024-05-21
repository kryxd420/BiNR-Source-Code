package com.applovin.impl.sdk.c;

import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.j;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    private final j a;
    private final Map<String, Long> b = new HashMap();

    public h(j jVar) {
        if (jVar != null) {
            this.a = jVar;
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private void e() {
        try {
            this.a.a(d.i, c().toString());
        } catch (Throwable th) {
            this.a.v().b("GlobalStatsManager", "Unable to save stats", th);
        }
    }

    public long a(g gVar) {
        return a(gVar, 1);
    }

    /* access modifiers changed from: package-private */
    public long a(g gVar, long j) {
        long longValue;
        synchronized (this.b) {
            Long l = this.b.get(gVar.a());
            if (l == null) {
                l = 0L;
            }
            longValue = l.longValue() + j;
            this.b.put(gVar.a(), Long.valueOf(longValue));
        }
        e();
        return longValue;
    }

    public void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        e();
    }

    public long b(g gVar) {
        long longValue;
        synchronized (this.b) {
            Long l = this.b.get(gVar.a());
            if (l == null) {
                l = 0L;
            }
            longValue = l.longValue();
        }
        return longValue;
    }

    public void b() {
        synchronized (this.b) {
            for (g a2 : g.b()) {
                this.b.remove(a2.a());
            }
            e();
        }
    }

    public void b(g gVar, long j) {
        synchronized (this.b) {
            this.b.put(gVar.a(), Long.valueOf(j));
        }
        e();
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Map.Entry next : this.b.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
        }
        return jSONObject;
    }

    public void c(g gVar) {
        synchronized (this.b) {
            this.b.remove(gVar.a());
        }
        e();
    }

    public void d() {
        try {
            JSONObject jSONObject = new JSONObject((String) this.a.b(d.i, "{}"));
            synchronized (this.b) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String next = keys.next();
                        this.b.put(next, Long.valueOf(jSONObject.getLong(next)));
                    } catch (JSONException unused) {
                    }
                }
            }
        } catch (Throwable th) {
            this.a.v().b("GlobalStatsManager", "Unable to load stats", th);
        }
    }
}
