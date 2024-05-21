package com.applovin.impl.sdk.d;

import com.applovin.impl.a.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.e.p;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

abstract class s extends a {
    private final AppLovinAdLoadListener a;
    private final a c;

    private static final class a extends com.applovin.impl.a.c {
        a(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, j jVar) {
            super(jSONObject, jSONObject2, bVar, jVar);
        }

        /* access modifiers changed from: package-private */
        public void a(o oVar) {
            if (oVar != null) {
                this.a.add(oVar);
                return;
            }
            throw new IllegalArgumentException("No aggregated vast response specified");
        }
    }

    private static final class b extends s {
        private final JSONObject a;

        b(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
            super(cVar, appLovinAdLoadListener, jVar);
            if (appLovinAdLoadListener != null) {
                this.a = cVar.c();
                return;
            }
            throw new IllegalArgumentException("No callback specified.");
        }

        public i a() {
            return i.r;
        }

        public void run() {
            d dVar;
            a("Processing SDK JSON response...");
            String a2 = g.a(this.a, "xml", (String) null, this.b);
            if (!k.b(a2)) {
                d("No VAST response received.");
                dVar = d.NO_WRAPPER_RESPONSE;
            } else if (a2.length() < ((Integer) this.b.a(com.applovin.impl.sdk.b.b.eL)).intValue()) {
                try {
                    a(p.a(a2, this.b));
                    return;
                } catch (Throwable th) {
                    a("Unable to parse VAST response", th);
                    a(d.XML_PARSING);
                    this.b.F().a(a());
                    return;
                }
            } else {
                d("VAST response is over max length");
                dVar = d.XML_PARSING;
            }
            a(dVar);
        }
    }

    private static final class c extends s {
        private final o a;

        c(o oVar, com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
            super(cVar, appLovinAdLoadListener, jVar);
            if (oVar == null) {
                throw new IllegalArgumentException("No response specified.");
            } else if (cVar == null) {
                throw new IllegalArgumentException("No context specified.");
            } else if (appLovinAdLoadListener != null) {
                this.a = oVar;
            } else {
                throw new IllegalArgumentException("No callback specified.");
            }
        }

        public i a() {
            return i.s;
        }

        public void run() {
            a("Processing VAST Wrapper response...");
            a(this.a);
        }
    }

    s(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super("TaskProcessVastResponse", jVar);
        if (cVar != null) {
            this.a = appLovinAdLoadListener;
            this.c = (a) cVar;
            return;
        }
        throw new IllegalArgumentException("No context specified.");
    }

    public static s a(o oVar, com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        return new c(oVar, cVar, appLovinAdLoadListener, jVar);
    }

    public static s a(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        return new b(new a(jSONObject, jSONObject2, bVar, jVar), appLovinAdLoadListener, jVar);
    }

    /* access modifiers changed from: package-private */
    public void a(d dVar) {
        d("Failed to process VAST response due to VAST error code " + dVar);
        com.applovin.impl.a.i.a((com.applovin.impl.a.c) this.c, this.a, dVar, -6, this.b);
    }

    /* access modifiers changed from: package-private */
    public void a(o oVar) {
        d dVar;
        a aVar;
        int a2 = this.c.a();
        a("Finished parsing XML at depth " + a2);
        this.c.a(oVar);
        if (com.applovin.impl.a.i.a(oVar)) {
            int intValue = ((Integer) this.b.a(com.applovin.impl.sdk.b.b.eM)).intValue();
            if (a2 < intValue) {
                a("VAST response is wrapper. Resolving...");
                aVar = new y(this.c, this.a, this.b);
            } else {
                d("Reached beyond max wrapper depth of " + intValue);
                dVar = d.WRAPPER_LIMIT_REACHED;
                a(dVar);
                return;
            }
        } else if (com.applovin.impl.a.i.b(oVar)) {
            a("VAST response is inline. Rendering ad...");
            aVar = new v(this.c, this.a, this.b);
        } else {
            d("VAST response is an error");
            dVar = d.NO_WRAPPER_RESPONSE;
            a(dVar);
            return;
        }
        this.b.D().a(aVar);
    }
}
