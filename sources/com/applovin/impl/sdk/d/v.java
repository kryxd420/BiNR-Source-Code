package com.applovin.impl.sdk.d;

import com.applovin.impl.a.a;
import com.applovin.impl.a.b;
import com.applovin.impl.a.c;
import com.applovin.impl.a.d;
import com.applovin.impl.a.f;
import com.applovin.impl.a.g;
import com.applovin.impl.a.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdType;
import java.util.HashSet;
import java.util.Set;

class v extends a {
    private c a;
    private final AppLovinAdLoadListener c;

    v(c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super("TaskRenderVastAd", jVar);
        if (cVar != null) {
            this.c = appLovinAdLoadListener;
            this.a = cVar;
            return;
        }
        throw new IllegalArgumentException("No context specified.");
    }

    private void a(d dVar, Throwable th) {
        a("Failed to render valid VAST ad", th);
        i.a(this.a, this.c, dVar, -6, this.b);
    }

    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.v;
    }

    public void run() {
        a("Rendering VAST ad...");
        int size = this.a.b().size();
        HashSet hashSet = new HashSet(size);
        HashSet hashSet2 = new HashSet(size);
        String str = "";
        com.applovin.impl.a.j jVar = null;
        b bVar = null;
        String str2 = "";
        f fVar = null;
        for (o next : this.a.b()) {
            o c2 = next.c(i.a(next) ? "Wrapper" : "InLine");
            if (c2 != null) {
                o c3 = c2.c("AdSystem");
                if (c3 != null) {
                    fVar = f.a(c3, fVar, this.b);
                }
                str2 = i.a(c2, "AdTitle", str2);
                str = i.a(c2, "Description", str);
                i.a(c2.a("Impression"), (Set<g>) hashSet, this.a, this.b);
                i.a(c2.a("Error"), (Set<g>) hashSet2, this.a, this.b);
                o b = c2.b("Creatives");
                if (b != null) {
                    for (o next2 : b.d()) {
                        o b2 = next2.b("Linear");
                        if (b2 != null) {
                            jVar = com.applovin.impl.a.j.a(b2, jVar, this.a, this.b);
                        } else {
                            o c4 = next2.c("CompanionAds");
                            if (c4 != null) {
                                o c5 = c4.c("Companion");
                                if (c5 != null) {
                                    bVar = b.a(c5, bVar, this.a, this.b);
                                }
                            } else {
                                d("Received and will skip rendering for an unidentified creative: " + next2);
                            }
                        }
                    }
                }
            } else {
                d("Did not find wrapper or inline response for node: " + next);
            }
        }
        try {
            a a2 = a.aA().a(this.b).a(this.a.c()).b(this.a.d()).a(this.a.e()).a(this.a.f()).a(str2).b(str).a(fVar).a(jVar).a(bVar).a((Set<g>) hashSet).b((Set<g>) hashSet2).a();
            d a3 = i.a(a2);
            if (a3 == null) {
                h hVar = new h(a2, this.b, this.c);
                q.a aVar = q.a.CACHING_OTHER;
                if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.aZ)).booleanValue()) {
                    if (a2.getType() == AppLovinAdType.REGULAR) {
                        aVar = q.a.CACHING_INTERSTITIAL;
                    } else if (a2.getType() == AppLovinAdType.INCENTIVIZED) {
                        aVar = q.a.CACHING_INCENTIVIZED;
                    }
                }
                this.b.D().a((a) hVar, aVar);
                return;
            }
            a(a3, (Throwable) null);
        } catch (Throwable th) {
            a(d.GENERAL_WRAPPER_ERROR, th);
            this.b.F().a(a());
        }
    }
}
