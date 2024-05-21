package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.network.f;
import com.applovin.sdk.AppLovinPostbackListener;
import org.json.JSONObject;

public class j extends a {
    private final f a;
    /* access modifiers changed from: private */
    public final AppLovinPostbackListener c;
    private final q.a d;

    public j(f fVar, q.a aVar, com.applovin.impl.sdk.j jVar, AppLovinPostbackListener appLovinPostbackListener) {
        super("TaskDispatchPostback", jVar);
        if (fVar != null) {
            this.a = fVar;
            this.c = appLovinPostbackListener;
            this.d = aVar;
            return;
        }
        throw new IllegalArgumentException("No request specified");
    }

    public i a() {
        return i.c;
    }

    public void run() {
        final String a2 = this.a.a();
        if (!k.b(a2)) {
            b("Requested URL is not valid; nothing to do...");
            if (this.c != null) {
                this.c.onPostbackFailure(a2, -900);
                return;
            }
            return;
        }
        AnonymousClass1 r1 = new w<JSONObject>(this.a, b()) {
            public void a(int i) {
                d("Failed to dispatch postback. Error code: " + i + " URL: " + a2);
                if (j.this.c != null) {
                    j.this.c.onPostbackFailure(a2, i);
                }
            }

            public void a(JSONObject jSONObject, int i) {
                a("Successfully dispatched postback to URL: " + a2);
                if (j.this.c != null) {
                    j.this.c.onPostbackSuccess(a2);
                }
            }
        };
        r1.a(this.d);
        b().D().a((a) r1);
    }
}
