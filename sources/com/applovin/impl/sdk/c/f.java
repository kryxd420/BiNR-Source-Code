package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private final List<a> a;
    private final Object b = new Object();
    private final j c;
    private final p d;

    private static class a {
        private final Long a;
        private final String b;
        private final String c;
        private final String d;

        private a(String str, Throwable th) {
            this.b = str;
            this.a = Long.valueOf(System.currentTimeMillis());
            String str2 = null;
            this.c = th != null ? th.getClass().getName() : null;
            this.d = th != null ? th.getMessage() : str2;
        }

        private a(JSONObject jSONObject) throws JSONException {
            this.b = jSONObject.getString("ms");
            this.a = Long.valueOf(jSONObject.getLong("ts"));
            JSONObject optJSONObject = jSONObject.optJSONObject("ex");
            String str = null;
            this.c = optJSONObject != null ? optJSONObject.getString("nm") : null;
            this.d = optJSONObject != null ? optJSONObject.getString("rn") : str;
        }

        /* access modifiers changed from: private */
        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ms", this.b);
            jSONObject.put("ts", this.a);
            if (!TextUtils.isEmpty(this.c)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("nm", this.c);
                if (!TextUtils.isEmpty(this.d)) {
                    jSONObject2.put("rn", this.d);
                }
                jSONObject.put("ex", jSONObject2);
            }
            return jSONObject;
        }

        public String toString() {
            return "ErrorLog{timestampMillis=" + this.a + ",message='" + this.b + '\'' + ",throwableName='" + this.c + '\'' + ",throwableReason='" + this.d + '\'' + '}';
        }
    }

    public f(j jVar) {
        this.c = jVar;
        this.d = jVar.v();
        this.a = new ArrayList();
    }

    private void d() {
        JSONArray jSONArray = new JSONArray();
        synchronized (this.b) {
            for (a next : this.a) {
                try {
                    jSONArray.put(next.a());
                } catch (JSONException e) {
                    this.d.a("ErrorManager", false, "Failed to convert error log into json.", e);
                    this.a.remove(next);
                }
            }
        }
        this.c.a(d.j, jSONArray.toString());
    }

    public JSONArray a() {
        JSONArray jSONArray;
        synchronized (this.b) {
            jSONArray = new JSONArray();
            for (a a2 : this.a) {
                try {
                    jSONArray.put(a2.a());
                } catch (JSONException e) {
                    this.d.a("ErrorManager", false, "Failed to convert error log into json.", e);
                }
            }
        }
        return jSONArray;
    }

    public void a(String str, Throwable th) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                if (this.a.size() < ((Integer) this.c.a(b.eI)).intValue()) {
                    this.a.add(new a(str, th));
                    d();
                }
            }
        }
    }

    public void b() {
        String str = (String) this.c.b(d.j, null);
        if (str != null) {
            synchronized (this.b) {
                try {
                    this.a.clear();
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            this.a.add(new a(jSONArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            this.d.a("ErrorManager", false, "Failed to convert error json into a log.", e);
                        }
                    }
                } catch (JSONException e2) {
                    this.d.b("ErrorManager", "Unable to convert String to json.", e2);
                }
            }
        }
    }

    public void c() {
        synchronized (this.b) {
            this.a.clear();
            this.c.b(d.j);
        }
    }
}
