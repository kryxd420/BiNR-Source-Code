package com.applovin.impl.mediation.b;

import android.app.Activity;
import com.applovin.impl.mediation.a.e;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends com.applovin.impl.sdk.d.a {
    /* access modifiers changed from: private */
    public final Activity a;

    public a(Activity activity, j jVar) {
        super("TaskAutoInitAdapters", jVar);
        if (activity != null) {
            this.a = activity;
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    public i a() {
        return i.A;
    }

    public void run() {
        String str;
        String str2 = (String) this.b.a(d.r);
        if (k.b(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                JSONArray a2 = g.a(jSONObject, "auto_init_adapters", (JSONArray) null, this.b);
                if (a2.length() > 0) {
                    a("Auto-initing " + a2.length() + " adapter(s)...");
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(((Integer) this.b.a(com.applovin.impl.sdk.b.a.q)).intValue());
                    for (int i = 0; i < a2.length(); i++) {
                        final e eVar = new e(a2.getJSONObject(i), jSONObject, this.b);
                        newFixedThreadPool.execute(new Runnable() {
                            public void run() {
                                a aVar = a.this;
                                aVar.a("Auto-initing adapter: " + eVar);
                                a.this.b.a(a.this.a).initializeAdapter(eVar, a.this.a);
                            }
                        });
                    }
                    return;
                }
                d("No auto-init adapters found");
            } catch (JSONException e) {
                th = e;
                str = "Failed to parse auto-init adapters JSON";
                a(str, th);
            } catch (Throwable th) {
                th = th;
                str = "Failed to auto-init adapters";
                a(str, th);
            }
        } else {
            a("No auto-init adapters provided", (Throwable) null);
        }
    }
}
