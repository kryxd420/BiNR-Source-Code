package com.applovin.impl.mediation.c;

import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import org.json.JSONObject;

public class b extends f {
    public static String a(j jVar) {
        return f.a((String) jVar.a(a.a), "1.0/mediate", jVar);
    }

    public static void a(JSONObject jSONObject, j jVar) {
        if (g.a(jSONObject, "signal_providers")) {
            jVar.a(d.q, jSONObject.toString());
            jVar.v().b("MediationConnectionUtils", "Updated signal provider(s)");
        }
    }

    public static String b(j jVar) {
        return f.a((String) jVar.a(a.b), "1.0/mediate", jVar);
    }

    public static void b(JSONObject jSONObject, j jVar) {
        if (g.a(jSONObject, "auto_init_adapters")) {
            jVar.a(d.r, jSONObject.toString());
            jVar.v().b("MediationConnectionUtils", "Updated auto-init adapter(s)");
        }
    }
}
