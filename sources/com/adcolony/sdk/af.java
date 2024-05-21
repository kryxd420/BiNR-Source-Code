package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import org.json.JSONException;
import org.json.JSONObject;

class af {
    private String a;
    private JSONObject b;

    af(JSONObject jSONObject) {
        try {
            this.b = jSONObject;
            this.a = jSONObject.getString("m_type");
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i) {
        try {
            this.a = str;
            this.b = new JSONObject();
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i, String str2) {
        try {
            this.a = str;
            this.b = y.a(str2);
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i, JSONObject jSONObject) {
        try {
            this.a = str;
            this.b = jSONObject == null ? new JSONObject() : jSONObject;
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    /* access modifiers changed from: package-private */
    public af a() {
        return a((JSONObject) null);
    }

    /* access modifiers changed from: package-private */
    public af a(String str) {
        return a(y.a(str));
    }

    /* access modifiers changed from: package-private */
    public af a(JSONObject jSONObject) {
        try {
            af afVar = new af("reply", this.b.getInt("m_origin"), jSONObject);
            afVar.b.put("m_id", this.b.getInt("m_id"));
            return afVar;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCMessage's createReply(): ").a(e.toString()).a(aa.h);
            return new af("JSONException", 0);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a.a(this.a, this.b);
    }

    /* access modifiers changed from: package-private */
    public JSONObject c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void b(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.a = str;
    }
}
