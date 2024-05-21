package com.applovin.impl.a;

import android.net.Uri;
import com.applovin.impl.a.j;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class a extends g {
    private final String a;
    private final String b;
    private final f c;
    private final long d;
    private final j e;
    private final b f;
    private final Set<g> g;
    private final Set<g> h;

    /* renamed from: com.applovin.impl.a.a$a  reason: collision with other inner class name */
    public static class C0001a {
        /* access modifiers changed from: private */
        public JSONObject a;
        /* access modifiers changed from: private */
        public JSONObject b;
        /* access modifiers changed from: private */
        public com.applovin.impl.sdk.ad.b c;
        /* access modifiers changed from: private */
        public j d;
        /* access modifiers changed from: private */
        public long e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public String g;
        /* access modifiers changed from: private */
        public f h;
        /* access modifiers changed from: private */
        public j i;
        /* access modifiers changed from: private */
        public b j;
        /* access modifiers changed from: private */
        public Set<g> k;
        /* access modifiers changed from: private */
        public Set<g> l;

        private C0001a() {
        }

        public C0001a a(long j2) {
            this.e = j2;
            return this;
        }

        public C0001a a(b bVar) {
            this.j = bVar;
            return this;
        }

        public C0001a a(f fVar) {
            this.h = fVar;
            return this;
        }

        public C0001a a(j jVar) {
            this.i = jVar;
            return this;
        }

        public C0001a a(com.applovin.impl.sdk.ad.b bVar) {
            this.c = bVar;
            return this;
        }

        public C0001a a(j jVar) {
            if (jVar != null) {
                this.d = jVar;
                return this;
            }
            throw new IllegalArgumentException("No sdk specified.");
        }

        public C0001a a(String str) {
            this.f = str;
            return this;
        }

        public C0001a a(Set<g> set) {
            this.k = set;
            return this;
        }

        public C0001a a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject;
                return this;
            }
            throw new IllegalArgumentException("No ad object specified.");
        }

        public a a() {
            return new a(this);
        }

        public C0001a b(String str) {
            this.g = str;
            return this;
        }

        public C0001a b(Set<g> set) {
            this.l = set;
            return this;
        }

        public C0001a b(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.b = jSONObject;
                return this;
            }
            throw new IllegalArgumentException("No full ad response specified.");
        }
    }

    private enum b {
        VIDEO,
        COMPANION_AD
    }

    public enum c {
        IMPRESSION,
        VIDEO_CLICK,
        COMPANION_CLICK,
        VIDEO,
        COMPANION,
        ERROR
    }

    private a(C0001a aVar) {
        super(aVar.a, aVar.b, aVar.c, aVar.d);
        this.a = aVar.f;
        this.c = aVar.h;
        this.b = aVar.g;
        this.e = aVar.i;
        this.f = aVar.j;
        this.g = aVar.k;
        this.h = aVar.l;
        this.d = aVar.e;
    }

    private Set<g> a(b bVar, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return Collections.emptySet();
        }
        Map<String, Set<g>> map = null;
        if (bVar == b.VIDEO && this.e != null) {
            map = this.e.e();
        } else if (bVar == b.COMPANION_AD && this.f != null) {
            map = this.f.d();
        }
        HashSet hashSet = new HashSet();
        if (map != null && !map.isEmpty()) {
            for (String str : strArr) {
                if (map.containsKey(str)) {
                    hashSet.addAll(map.get(str));
                }
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static C0001a aA() {
        return new C0001a();
    }

    private String aB() {
        String stringFromAdObject = getStringFromAdObject("vimp_url", (String) null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    private j.a aC() {
        j.a[] values = j.a.values();
        int intValue = ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.eR)).intValue();
        return (intValue < 0 || intValue >= values.length) ? j.a.UNSPECIFIED : values[intValue];
    }

    private Set<g> aD() {
        return this.e != null ? this.e.d() : Collections.emptySet();
    }

    private Set<g> aE() {
        return this.f != null ? this.f.c() : Collections.emptySet();
    }

    public j a() {
        return this.e;
    }

    public Set<g> a(c cVar, String str) {
        return a(cVar, new String[]{str});
    }

    public Set<g> a(c cVar, String[] strArr) {
        p v = this.sdk.v();
        v.a("VastAd", "Retrieving trackers of type '" + cVar + "' and events '" + strArr + "'...");
        if (cVar == c.IMPRESSION) {
            return this.g;
        }
        if (cVar == c.VIDEO_CLICK) {
            return aD();
        }
        if (cVar == c.COMPANION_CLICK) {
            return aE();
        }
        if (cVar == c.VIDEO) {
            return a(b.VIDEO, strArr);
        }
        if (cVar == c.COMPANION) {
            return a(b.COMPANION_AD, strArr);
        }
        if (cVar == c.ERROR) {
            return this.h;
        }
        p v2 = this.sdk.v();
        v2.d("VastAd", "Failed to retrieve trackers of invalid type '" + cVar + "' and events '" + strArr + "'");
        return Collections.emptySet();
    }

    public void a(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("html_template", str);
            }
        } catch (Throwable unused) {
        }
    }

    public List<com.applovin.impl.sdk.c.a> am() {
        return n.a("vimp_urls", this.adObject, getClCode(), aB(), this.sdk);
    }

    public boolean az() {
        return getBooleanFromAdObject("cache_video", true);
    }

    public boolean b() {
        k c2 = c();
        return c2 != null && c2.c();
    }

    public k c() {
        if (this.e != null) {
            return this.e.a(aC());
        }
        return null;
    }

    public b d() {
        return this.f;
    }

    public Uri e() {
        k c2 = c();
        if (c2 != null) {
            return c2.b();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a) || !super.equals(obj)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a == null ? aVar.a != null : !this.a.equals(aVar.a)) {
            return false;
        }
        if (this.b == null ? aVar.b != null : !this.b.equals(aVar.b)) {
            return false;
        }
        if (this.c == null ? aVar.c != null : !this.c.equals(aVar.c)) {
            return false;
        }
        if (this.e == null ? aVar.e != null : !this.e.equals(aVar.e)) {
            return false;
        }
        if (this.f == null ? aVar.f != null : !this.f.equals(aVar.f)) {
            return false;
        }
        if (this.g == null ? aVar.g == null : this.g.equals(aVar.g)) {
            return this.h != null ? this.h.equals(aVar.h) : aVar.h == null;
        }
        return false;
    }

    public List<String> f() {
        return d.a(getStringFromAdObject("vast_resource_cache_prefix", (String) null));
    }

    public Uri g() {
        if (this.e != null) {
            return this.e.c();
        }
        return null;
    }

    public long getCreatedAtMillis() {
        return this.d;
    }

    public Uri h() {
        return g();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r2.e.a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasVideoUrl() {
        /*
            r2 = this;
            com.applovin.impl.a.j r0 = r2.e
            r1 = 0
            if (r0 == 0) goto L_0x0014
            com.applovin.impl.a.j r0 = r2.e
            java.util.List r0 = r0.a()
            if (r0 == 0) goto L_0x0014
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0014
            r1 = 1
        L_0x0014:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.a.a.hasVideoUrl():boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((super.hashCode() * 31) + (this.a != null ? this.a.hashCode() : 0)) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31) + (this.f != null ? this.f.hashCode() : 0)) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31;
        if (this.h != null) {
            i = this.h.hashCode();
        }
        return hashCode + i;
    }

    public boolean i() {
        return getBooleanFromAdObject("vast_fire_click_trackers_on_html_clicks", false);
    }

    public String j() {
        return getStringFromAdObject("html_template", "");
    }

    public Uri k() {
        String stringFromAdObject = getStringFromAdObject("html_template_url", (String) null);
        if (k.b(stringFromAdObject)) {
            return Uri.parse(stringFromAdObject);
        }
        return null;
    }

    public boolean l() {
        return getBooleanFromAdObject("cache_companion_ad", true);
    }

    public String toString() {
        return "VastAd{title='" + this.a + '\'' + ", adDescription='" + this.b + '\'' + ", systemInfo=" + this.c + ", videoCreative=" + this.e + ", companionAd=" + this.f + ", impressionTrackers=" + this.g + ", errorTrackers=" + this.h + '}';
    }

    public boolean v() {
        return g() != null;
    }
}
