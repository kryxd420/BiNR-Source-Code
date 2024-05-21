package com.applovin.impl.sdk.ad;

import android.text.TextUtils;
import android.util.Base64;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private final j a;
    private final String b;

    public enum a {
        UNSPECIFIED("UNSPECIFIED"),
        REGULAR("REGULAR"),
        AD_RESPONSE_JSON("AD_RESPONSE_JSON");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    public c(String str, j jVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Identifier is empty");
        } else if (jVar != null) {
            this.b = str;
            this.a = jVar;
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    private String a(b<String> bVar) {
        for (String next : this.a.b((b) bVar)) {
            if (this.b.startsWith(next)) {
                return next;
            }
        }
        return null;
    }

    public String a() {
        return this.b;
    }

    public a b() {
        return a(b.aK) != null ? a.REGULAR : a(b.aL) != null ? a.AD_RESPONSE_JSON : a.UNSPECIFIED;
    }

    public String c() {
        String a2 = a(b.aK);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String a3 = a(b.aL);
        if (!TextUtils.isEmpty(a3)) {
            return a3;
        }
        return null;
    }

    public JSONObject d() {
        if (b() != a.AD_RESPONSE_JSON) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(Base64.decode(this.b.substring(c().length()), 0), "UTF-8"));
            p v = this.a.v();
            v.a("AdToken", "Decoded token into ad response: " + jSONObject);
            return jSONObject;
        } catch (JSONException e) {
            p v2 = this.a.v();
            v2.b("AdToken", "Unable to decode token '" + this.b + "' into JSON", e);
            return null;
        } catch (Throwable th) {
            p v3 = this.a.v();
            v3.b("AdToken", "Unable to process ad response from token '" + this.b + "'", th);
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.b != null ? this.b.equals(cVar.b) : cVar.b == null;
    }

    public int hashCode() {
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }

    public String toString() {
        String a2 = k.a(32, this.b);
        return "AdToken{id=" + a2 + ", type=" + b() + '}';
    }
}
