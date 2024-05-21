package com.adcolony.sdk;

import com.adcolony.sdk.aa;
import org.json.JSONObject;

class m implements ah {
    m() {
        a.a("CustomMessage.controller_send", (ah) this);
    }

    public void a(af afVar) {
        JSONObject c = afVar.c();
        final String b = y.b(c, "type");
        final String b2 = y.b(c, "message");
        aw.a((Runnable) new Runnable() {
            public void run() {
                new aa.a().a("Received custom message ").a(b2).a(" of type ").a(b).a(aa.d);
                try {
                    AdColonyCustomMessageListener adColonyCustomMessageListener = a.a().z().get(b);
                    if (adColonyCustomMessageListener != null) {
                        adColonyCustomMessageListener.onAdColonyCustomMessage(new AdColonyCustomMessage(b, b2));
                    }
                } catch (RuntimeException unused) {
                }
            }
        });
    }
}
