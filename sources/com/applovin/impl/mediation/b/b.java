package com.applovin.impl.mediation.b;

import android.app.Activity;
import com.applovin.impl.mediation.a.f;
import com.applovin.impl.mediation.a.g;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends com.applovin.impl.sdk.d.a {
    private static String a;
    /* access modifiers changed from: private */
    public final Activity c;
    private final a d;

    public interface a {
        void a(JSONArray jSONArray);
    }

    static {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a("APPLOVIN_NETWORK", "com.applovin.mediation.adapters.AppLovinMediationAdapter"));
            a("FACEBOOK_NETWORK", "com.applovin.mediation.adapters.FacebookMediationAdapter").put("run_on_ui_thread", false);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("signal_providers", jSONArray);
            a = jSONObject.toString();
        } catch (JSONException unused) {
        }
    }

    public b(Activity activity, j jVar, a aVar) {
        super("TaskCollectSignals", jVar);
        if (activity != null) {
            this.c = activity;
            this.d = aVar;
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.applovin.impl.sdk.b.b<java.lang.Integer>, com.applovin.impl.sdk.b.b] */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r4 = ((java.lang.Integer) r2.b.a(r4)).intValue();
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r3, com.applovin.impl.sdk.b.b<java.lang.Integer> r4) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0009
            java.lang.String r3 = ""
            return r3
        L_0x0009:
            com.applovin.impl.sdk.j r0 = r2.b
            java.lang.Object r4 = r0.a(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r4 <= 0) goto L_0x0025
            r0 = 0
            int r1 = r3.length()
            int r4 = java.lang.Math.min(r1, r4)
            java.lang.String r3 = r3.substring(r0, r4)
            return r3
        L_0x0025:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.mediation.b.b.a(java.lang.String, com.applovin.impl.sdk.b.b):java.lang.String");
    }

    private static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        jSONObject.put("class", str2);
        jSONObject.put("adapter_timeout_ms", 30000);
        jSONObject.put("max_signal_length", 32768);
        jSONObject.put("scode", "");
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public void a(final g gVar, final f.a aVar) {
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                b.this.b.a(b.this.c).collectSignal(gVar, b.this.c, aVar);
            }
        };
        if (gVar.w()) {
            a("Running signal collection for " + gVar + " on the main thread");
            this.c.runOnUiThread(r0);
            return;
        }
        a("Running signal collection for " + gVar + " on the background thread");
        r0.run();
    }

    private void a(Collection<f> collection) {
        String str;
        String a2;
        JSONArray jSONArray = new JSONArray();
        for (f next : collection) {
            try {
                JSONObject jSONObject = new JSONObject();
                g a3 = next.a();
                jSONObject.put("name", a3.u());
                jSONObject.put("class", a3.t());
                jSONObject.put(TapjoyConstants.TJC_ADAPTER_VERSION, a(next.c(), com.applovin.impl.sdk.b.a.m));
                jSONObject.put("sdk_version", a(next.b(), com.applovin.impl.sdk.b.a.n));
                JSONObject jSONObject2 = new JSONObject();
                if (k.b(next.e())) {
                    str = "error_message";
                    a2 = next.e();
                } else {
                    str = "signal";
                    a2 = a(next.d(), com.applovin.impl.sdk.b.a.o);
                }
                jSONObject2.put(str, a2);
                jSONObject.put(TJAdUnitConstants.String.DATA, jSONObject2);
                jSONArray.put(jSONObject);
                a("Collected signal from " + a3);
            } catch (JSONException e) {
                a("Failed to create signal data", e);
            }
        }
        a(jSONArray);
    }

    private void a(JSONArray jSONArray) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(jSONArray);
        }
    }

    private void b(String str, Throwable th) {
        a("No signals collected: " + str, th);
        a(new JSONArray());
    }

    public i a() {
        return i.B;
    }

    public void run() {
        String str;
        String str2 = (String) this.b.b(d.q, a);
        if (k.b(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                JSONArray a2 = com.applovin.impl.sdk.e.g.a(jSONObject, "signal_providers", (JSONArray) null, this.b);
                if (a2.length() > 0) {
                    a("Collecting signals from " + a2.length() + " signal providers(s)...");
                    List a3 = com.applovin.impl.sdk.e.d.a(a2.length());
                    AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                    CountDownLatch countDownLatch = new CountDownLatch(a2.length());
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(((Integer) this.b.a(com.applovin.impl.sdk.b.a.j)).intValue());
                    for (int i = 0; i < a2.length(); i++) {
                        final g gVar = new g(a2.getJSONObject(i), jSONObject, this.b);
                        final AtomicBoolean atomicBoolean2 = atomicBoolean;
                        final List list = a3;
                        final CountDownLatch countDownLatch2 = countDownLatch;
                        newFixedThreadPool.execute(new Runnable() {
                            public void run() {
                                b.this.a(gVar, (f.a) new f.a() {
                                    public void a(f fVar) {
                                        if (atomicBoolean2.get() && fVar != null) {
                                            list.add(fVar);
                                        }
                                        countDownLatch2.countDown();
                                    }
                                });
                            }
                        });
                    }
                    countDownLatch.await(((Long) this.b.a(com.applovin.impl.sdk.b.a.l)).longValue(), TimeUnit.MILLISECONDS);
                    atomicBoolean.set(false);
                    if (((Boolean) this.b.a(com.applovin.impl.sdk.b.a.k)).booleanValue()) {
                        newFixedThreadPool.shutdown();
                    }
                    a((Collection<f>) a3);
                    return;
                }
                b("No signal providers found", (Throwable) null);
            } catch (JSONException e) {
                th = e;
                str = "Failed to parse signals JSON";
                b(str, th);
            } catch (InterruptedException e2) {
                th = e2;
                str = "Failed to wait for signals";
                b(str, th);
            } catch (Throwable th) {
                th = th;
                str = "Failed to collect signals";
                b(str, th);
            }
        } else {
            b("No signal providers provided", (Throwable) null);
        }
    }
}
