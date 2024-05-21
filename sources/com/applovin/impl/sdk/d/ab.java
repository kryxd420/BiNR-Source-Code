package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.a.e;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.a;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ab extends z {
    private final g a;
    private final AppLovinAdRewardListener c;
    private final Object d = new Object();
    private volatile boolean e = false;

    public ab(g gVar, AppLovinAdRewardListener appLovinAdRewardListener, j jVar) {
        super("TaskValidateReward", jVar);
        this.a = gVar;
        this.c = appLovinAdRewardListener;
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        if (!f()) {
            String str = "network_timeout";
            if (i < 400 || i > 500) {
                this.c.validationRequestFailed(this.a, i);
            } else {
                this.c.userRewardRejected(this.a, new HashMap(0));
                str = "rejected";
            }
            e.a().a(this.a, str);
        }
    }

    private void a(String str, Map<String, String> map) {
        if (!f()) {
            e a2 = e.a();
            a2.a(this.a, str);
            a2.a(this.a, map);
            if (str.equals("accepted")) {
                this.c.userRewardVerified(this.a, map);
            } else if (str.equals("quota_exceeded")) {
                this.c.userOverQuota(this.a, map);
            } else if (str.equals("rejected")) {
                this.c.userRewardRejected(this.a, map);
            } else {
                this.c.validationRequestFailed(this.a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r3 = new java.util.HashMap(0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONObject r3) {
        /*
            r2 = this;
            boolean r0 = r2.f()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            org.json.JSONObject r0 = com.applovin.impl.sdk.e.f.a((org.json.JSONObject) r3)     // Catch:{ JSONException -> 0x0035 }
            com.applovin.impl.sdk.j r1 = r2.b     // Catch:{ JSONException -> 0x0035 }
            com.applovin.impl.sdk.e.f.d(r0, r1)     // Catch:{ JSONException -> 0x0035 }
            com.applovin.impl.sdk.j r1 = r2.b     // Catch:{ JSONException -> 0x0035 }
            com.applovin.impl.sdk.e.f.c(r3, r1)     // Catch:{ JSONException -> 0x0035 }
            java.lang.String r3 = "params"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x0022 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Throwable -> 0x0022 }
            java.util.Map r3 = com.applovin.impl.sdk.e.g.a((org.json.JSONObject) r3)     // Catch:{ Throwable -> 0x0022 }
            goto L_0x0028
        L_0x0022:
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ JSONException -> 0x0035 }
            r1 = 0
            r3.<init>(r1)     // Catch:{ JSONException -> 0x0035 }
        L_0x0028:
            java.lang.String r1 = "result"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Throwable -> 0x002f }
            goto L_0x0031
        L_0x002f:
            java.lang.String r0 = "network_timeout"
        L_0x0031:
            r2.a((java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r3)     // Catch:{ JSONException -> 0x0035 }
            goto L_0x003b
        L_0x0035:
            r3 = move-exception
            java.lang.String r0 = "Unable to parse API response"
            r2.a(r0, r3)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.d.ab.a(org.json.JSONObject):void");
    }

    private boolean f() {
        boolean z;
        synchronized (this.d) {
            z = this.e;
        }
        return z;
    }

    public i a() {
        return i.z;
    }

    public void a(boolean z) {
        synchronized (this.d) {
            this.e = z;
        }
    }

    public void run() {
        String clCode = this.a.getClCode();
        HashMap hashMap = new HashMap(3);
        hashMap.put("zone_id", this.a.getAdZone().a());
        if (k.b(clCode)) {
            hashMap.put("clcode", clCode);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        String k = this.b.k();
        if (((Boolean) this.b.a(b.eg)).booleanValue() && k.b(k)) {
            hashMap.put("cuid", k);
        }
        a("2.0/vr", new JSONObject(hashMap), ((Integer) this.b.a(b.bC)).intValue(), new a.b<JSONObject>() {
            public void a(int i) {
                ab.this.a(i);
            }

            public void a(JSONObject jSONObject, int i) {
                ab.this.a(jSONObject);
            }
        });
    }
}
