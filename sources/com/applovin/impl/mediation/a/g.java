package com.applovin.impl.mediation.a;

import com.applovin.impl.sdk.j;
import org.json.JSONObject;

public class g extends e {
    public g(JSONObject jSONObject, JSONObject jSONObject2, j jVar) {
        super(jSONObject, jSONObject2, jVar);
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return a("max_signal_length", 2048);
    }

    public boolean b() {
        return b("only_collect_signal_when_initialized", (Boolean) false);
    }

    public String toString() {
        return "SignalProviderSpec{specObject=" + s() + '}';
    }
}
