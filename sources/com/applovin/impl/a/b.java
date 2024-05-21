package com.applovin.impl.a;

import android.net.Uri;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class b {
    private int a;
    private int b;
    private Uri c;
    private e d;
    private Set<g> e = new HashSet();
    private Map<String, Set<g>> f = new HashMap();

    private b() {
    }

    public static b a(o oVar, b bVar, c cVar, j jVar) {
        o b2;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (jVar != null) {
            if (bVar == null) {
                try {
                    bVar = new b();
                } catch (Throwable th) {
                    jVar.v().b("VastCompanionAd", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (bVar.a == 0 && bVar.b == 0) {
                int a2 = k.a(oVar.b().get(AvidJSONUtil.KEY_WIDTH));
                int a3 = k.a(oVar.b().get(AvidJSONUtil.KEY_HEIGHT));
                if (a2 > 0 && a3 > 0) {
                    bVar.a = a2;
                    bVar.b = a3;
                }
            }
            bVar.d = e.a(oVar, bVar.d, jVar);
            if (bVar.c == null && (b2 = oVar.b("CompanionClickThrough")) != null) {
                String c2 = b2.c();
                if (k.b(c2)) {
                    bVar.c = Uri.parse(c2);
                }
            }
            i.a(oVar.a("CompanionClickTracking"), bVar.e, cVar, jVar);
            i.a(oVar, bVar.f, cVar, jVar);
            return bVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public Uri a() {
        return this.c;
    }

    public e b() {
        return this.d;
    }

    public Set<g> c() {
        return this.e;
    }

    public Map<String, Set<g>> d() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != bVar.a || this.b != bVar.b) {
            return false;
        }
        if (this.c == null ? bVar.c != null : !this.c.equals(bVar.c)) {
            return false;
        }
        if (this.d == null ? bVar.d != null : !this.d.equals(bVar.d)) {
            return false;
        }
        if (this.e == null ? bVar.e == null : this.e.equals(bVar.e)) {
            return this.f != null ? this.f.equals(bVar.f) : bVar.f == null;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.a * 31) + this.b) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastCompanionAd{width=" + this.a + ", height=" + this.b + ", destinationUri=" + this.c + ", nonVideoResource=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
