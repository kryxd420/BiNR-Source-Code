package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.network.a;
import com.applovin.impl.sdk.network.b;
import org.json.JSONObject;

abstract class z extends a {
    z(String str, j jVar) {
        super(str, jVar);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject, int i, final a.b bVar) {
        AnonymousClass1 r4 = new w<JSONObject>(b.a(this.b).a(f.a(str, this.b)).c(f.b(str, this.b)).a(f.c(this.b)).b("POST").a(jSONObject).a(new JSONObject()).a(i).a(), this.b) {
            public void a(int i) {
                bVar.a(i);
            }

            public void a(JSONObject jSONObject, int i) {
                bVar.a(jSONObject, i);
            }
        };
        r4.a(com.applovin.impl.sdk.b.b.aF);
        r4.b(com.applovin.impl.sdk.b.b.aG);
        this.b.D().a((a) r4);
    }
}
