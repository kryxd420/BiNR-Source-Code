package com.tapjoy.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class de {
    private final boolean a = false;
    private final Float b = null;
    private final boolean c = false;
    private final dd d;

    private de(dd ddVar) {
        this.d = ddVar;
    }

    public static de a(dd ddVar) {
        ds.a((Object) ddVar, "Position is null");
        return new de(ddVar);
    }

    /* access modifiers changed from: package-private */
    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("skippable", this.a);
            if (this.a) {
                jSONObject.put("skipOffset", this.b);
            }
            jSONObject.put("autoPlay", this.c);
            jSONObject.put("position", this.d);
        } catch (JSONException e) {
            dq.a("VastProperties: JSON error", e);
        }
        return jSONObject;
    }
}
