package com.adcolony.sdk;

import com.adcolony.sdk.ab;
import java.util.Date;
import org.json.JSONObject;

class s extends ab {
    static final x a = new x("adcolony_fatal_reports", "3.3.5", "Production");
    static final String b = "sourceFile";
    static final String c = "lineNumber";
    static final String d = "methodName";
    static final String e = "stackTrace";
    static final String f = "isAdActive";
    static final String g = "activeAdId";
    static final String h = "active_creative_ad_id";
    static final String i = "listOfCachedAds";
    static final String j = "listOfCreativeAdIds";
    static final String k = "adCacheSize";
    /* access modifiers changed from: private */
    public JSONObject p;

    s() {
    }

    /* access modifiers changed from: package-private */
    public s a(JSONObject jSONObject) {
        a aVar = new a();
        aVar.a(jSONObject);
        aVar.a(y.b(jSONObject, "message"));
        try {
            aVar.a(new Date(Long.parseLong(y.b(jSONObject, "timestamp"))));
        } catch (NumberFormatException unused) {
        }
        aVar.a(a);
        aVar.a(-1);
        return (s) aVar.a();
    }

    /* access modifiers changed from: package-private */
    public JSONObject a() {
        return this.p;
    }

    private class a extends ab.a {
        a() {
            this.b = new s();
        }

        /* access modifiers changed from: package-private */
        public a a(JSONObject jSONObject) {
            JSONObject unused = ((s) this.b).p = jSONObject;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ab.a a(Date date) {
            y.a(((s) this.b).p, "timestamp", ab.l.format(date));
            return super.a(date);
        }
    }
}
