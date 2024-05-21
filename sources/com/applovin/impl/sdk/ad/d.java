package com.applovin.impl.sdk.ad;

import android.text.TextUtils;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class d {
    private static final Map<String, d> a = new HashMap();
    private static final Object b = new Object();
    private j c;
    private p d;
    private JSONObject e;
    private final String f;
    private String g;
    private AppLovinAdSize h;
    private AppLovinAdType i;

    private d(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str, j jVar) {
        if (!TextUtils.isEmpty(str) || !(appLovinAdType == null || appLovinAdSize == null)) {
            this.c = jVar;
            this.d = jVar != null ? jVar.v() : null;
            this.h = appLovinAdSize;
            this.i = appLovinAdType;
            if (!TextUtils.isEmpty(str)) {
                this.f = str.toLowerCase(Locale.ENGLISH);
                this.g = str.toLowerCase(Locale.ENGLISH);
                return;
            }
            this.f = (appLovinAdSize.getLabel() + "_" + appLovinAdType.getLabel()).toLowerCase(Locale.ENGLISH);
            return;
        }
        throw new IllegalArgumentException("No zone identifier or type or size specified");
    }

    public static d a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, j jVar) {
        return a(appLovinAdSize, appLovinAdType, (String) null, jVar);
    }

    public static d a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str, j jVar) {
        d dVar = new d(appLovinAdSize, appLovinAdType, str, jVar);
        synchronized (b) {
            String str2 = dVar.f;
            if (a.containsKey(str2)) {
                dVar = a.get(str2);
            } else {
                a.put(str2, dVar);
            }
        }
        return dVar;
    }

    public static d a(String str, j jVar) {
        return a((AppLovinAdSize) null, (AppLovinAdType) null, str, jVar);
    }

    public static d a(String str, JSONObject jSONObject, j jVar) {
        d a2 = a(str, jVar);
        a2.e = jSONObject;
        return a2;
    }

    private <ST> b<ST> a(String str, b<ST> bVar) {
        return this.c.a(str + this.f, bVar);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.applovin.impl.sdk.b.b<java.lang.String>, com.applovin.impl.sdk.b.b] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(com.applovin.impl.sdk.b.b<java.lang.String> r2, com.applovin.sdk.AppLovinAdSize r3) {
        /*
            r1 = this;
            com.applovin.impl.sdk.j r0 = r1.c
            java.lang.Object r2 = r0.a(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.util.Locale r0 = java.util.Locale.ENGLISH
            java.lang.String r2 = r2.toUpperCase(r0)
            java.lang.String r3 = r3.getLabel()
            boolean r2 = r2.contains(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.d.a(com.applovin.impl.sdk.b.b, com.applovin.sdk.AppLovinAdSize):boolean");
    }

    public static d b(String str, j jVar) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, str, jVar);
    }

    public static Collection<d> b(j jVar) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(8);
        Collections.addAll(linkedHashSet, new d[]{c(jVar), d(jVar), e(jVar), f(jVar), g(jVar), h(jVar)});
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public static d c(j jVar) {
        return a(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, jVar);
    }

    public static d c(String str, j jVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, str, jVar);
    }

    public static d d(j jVar) {
        return a(AppLovinAdSize.MREC, AppLovinAdType.REGULAR, jVar);
    }

    public static d e(j jVar) {
        return a(AppLovinAdSize.LEADER, AppLovinAdType.REGULAR, jVar);
    }

    public static d f(j jVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, jVar);
    }

    public static d g(j jVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, jVar);
    }

    public static d h(j jVar) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, jVar);
    }

    private boolean m() {
        try {
            if (!TextUtils.isEmpty(this.g)) {
                return true;
            }
            return AppLovinAdType.INCENTIVIZED.equals(c()) ? ((Boolean) this.c.a(b.aR)).booleanValue() : a(b.aQ, b());
        } catch (Throwable th) {
            this.d.b("AdZone", "Unable to safely test preload merge capability", th);
            return false;
        }
    }

    public String a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.c = jVar;
        this.d = jVar.v();
    }

    public AppLovinAdSize b() {
        if (this.h == null && g.a(this.e, "ad_size")) {
            this.h = AppLovinAdSize.fromString(g.a(this.e, "ad_size", (String) null, this.c));
        }
        return this.h;
    }

    public AppLovinAdType c() {
        if (this.i == null && g.a(this.e, "ad_type")) {
            this.i = new AppLovinAdType(g.a(this.e, "ad_type", (String) null, this.c));
        }
        return this.i;
    }

    public boolean d() {
        return AppLovinAdSize.NATIVE.equals(b()) && AppLovinAdType.NATIVE.equals(c());
    }

    public int e() {
        if (g.a(this.e, "capacity")) {
            return g.a(this.e, "capacity", 0, this.c);
        }
        if (!TextUtils.isEmpty(this.g)) {
            return d() ? ((Integer) this.c.a(b.bg)).intValue() : ((Integer) this.c.a(b.bf)).intValue();
        }
        return ((Integer) this.c.a(a("preload_capacity_", b.aU))).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f.equalsIgnoreCase(((d) obj).f);
    }

    public int f() {
        if (g.a(this.e, "extended_capacity")) {
            return g.a(this.e, "extended_capacity", 0, this.c);
        }
        if (TextUtils.isEmpty(this.g)) {
            return ((Integer) this.c.a(a("extended_preload_capacity_", b.ba))).intValue();
        } else if (d()) {
            return 0;
        } else {
            return ((Integer) this.c.a(b.bh)).intValue();
        }
    }

    public int g() {
        return g.a(this.e, "preload_count", 0, this.c);
    }

    public boolean h() {
        j jVar;
        b bVar;
        Boolean bool;
        if (g.a(this.e, "refresh_enabled")) {
            bool = g.a(this.e, "refresh_enabled", (Boolean) false, this.c);
        } else {
            if (AppLovinAdSize.BANNER.equals(b())) {
                jVar = this.c;
                bVar = b.cs;
            } else if (AppLovinAdSize.MREC.equals(b())) {
                jVar = this.c;
                bVar = b.cu;
            } else if (!AppLovinAdSize.LEADER.equals(b())) {
                return false;
            } else {
                jVar = this.c;
                bVar = b.cw;
            }
            bool = (Boolean) jVar.a(bVar);
        }
        return bool.booleanValue();
    }

    public int hashCode() {
        return this.f.hashCode();
    }

    public long i() {
        if (g.a(this.e, "refresh_seconds")) {
            return (long) g.a(this.e, "refresh_seconds", 0, this.c);
        }
        if (AppLovinAdSize.BANNER.equals(b())) {
            return ((Long) this.c.a(b.ct)).longValue();
        }
        if (AppLovinAdSize.MREC.equals(b())) {
            return ((Long) this.c.a(b.cv)).longValue();
        }
        if (AppLovinAdSize.LEADER.equals(b())) {
            return ((Long) this.c.a(b.cx)).longValue();
        }
        return -1;
    }

    public boolean j() {
        if (!((Boolean) this.c.a(b.aP)).booleanValue() || !m()) {
            return false;
        }
        if (TextUtils.isEmpty(this.g)) {
            b a2 = a("preload_merge_init_tasks_", (b) null);
            return a2 != null && ((Boolean) this.c.a(a2)).booleanValue() && e() > 0;
        } else if (this.e != null && g() == 0) {
            return false;
        } else {
            String upperCase = ((String) this.c.a(b.aQ)).toUpperCase(Locale.ENGLISH);
            return (upperCase.contains(AppLovinAdSize.INTERSTITIAL.getLabel()) || upperCase.contains(AppLovinAdSize.BANNER.getLabel()) || upperCase.contains(AppLovinAdSize.MREC.getLabel()) || upperCase.contains(AppLovinAdSize.LEADER.getLabel())) ? ((Boolean) this.c.a(b.bo)).booleanValue() : this.c.P().a(this) && g() > 0 && ((Boolean) this.c.a(b.dU)).booleanValue();
        }
    }

    public boolean k() {
        return g.a(this.e, "wrapped_ads_enabled") ? g.a(this.e, "wrapped_ads_enabled", (Boolean) false, this.c).booleanValue() : b() != null ? this.c.b((b) b.cc).contains(b().getLabel()) : ((Boolean) this.c.a(b.cb)).booleanValue();
    }

    public boolean l() {
        return b(this.c).contains(this);
    }

    public String toString() {
        return "AdZone{identifier=" + this.f + ", zoneObject=" + this.e + '}';
    }
}
