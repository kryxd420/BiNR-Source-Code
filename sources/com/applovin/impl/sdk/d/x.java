package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.a.e;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class x extends z {
    /* access modifiers changed from: private */
    public final g a;

    public x(g gVar, j jVar) {
        super("TaskReportReward", jVar);
        this.a = gVar;
    }

    public i a() {
        return i.w;
    }

    public void run() {
        String b = e.a().b(this.a);
        if (b != null) {
            HashMap hashMap = new HashMap(6);
            hashMap.put("result", b);
            hashMap.put("zone_id", this.a.getAdZone().a());
            hashMap.put("fire_percent", Integer.valueOf(this.a.ad()));
            String clCode = this.a.getClCode();
            if (!k.b(clCode)) {
                clCode = "NO_CLCODE";
            }
            hashMap.put("clcode", clCode);
            String k = this.b.k();
            if (((Boolean) this.b.a(b.eg)).booleanValue() && k.b(k)) {
                hashMap.put("cuid", k);
            }
            Map<String, String> a2 = e.a().a(this.a);
            if (a2 != null) {
                hashMap.put("params", a2);
            }
            a("2.0/cr", new JSONObject(hashMap), ((Integer) this.b.a(b.bD)).intValue(), new a.b<JSONObject>() {
                public void a(int i) {
                    x xVar = x.this;
                    xVar.d("Failed to report reward for ad: " + x.this.a.getAdIdNumber() + " - error code: " + i);
                }

                public void a(JSONObject jSONObject, int i) {
                    x xVar = x.this;
                    xVar.a("Reported reward successfully for ad: " + x.this.a.getAdIdNumber());
                }
            });
            return;
        }
        d("No reward result was found for ad: " + this.a);
    }
}
