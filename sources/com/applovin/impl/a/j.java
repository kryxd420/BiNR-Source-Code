package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.d;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class j {
    private List<k> a = Collections.EMPTY_LIST;
    private List<String> b = Collections.EMPTY_LIST;
    private int c;
    private Uri d;
    private final Set<g> e = new HashSet();
    private final Map<String, Set<g>> f = new HashMap();

    public enum a {
        UNSPECIFIED,
        LOW,
        MEDIUM,
        HIGH
    }

    private j() {
    }

    private j(c cVar) {
        this.b = cVar.h();
    }

    private static int a(String str, com.applovin.impl.sdk.j jVar) {
        try {
            List<String> a2 = d.a(str, ":");
            if (a2.size() == 3) {
                return (int) (TimeUnit.HOURS.toSeconds((long) k.a(a2.get(0))) + TimeUnit.MINUTES.toSeconds((long) k.a(a2.get(1))) + ((long) k.a(a2.get(2))));
            }
        } catch (Throwable unused) {
            p v = jVar.v();
            v.d("VastVideoCreative", "Unable to parse duration from \"" + str + "\"");
        }
        return 0;
    }

    public static j a(o oVar, j jVar, c cVar, com.applovin.impl.sdk.j jVar2) {
        o b2;
        List<k> a2;
        o b3;
        int a3;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (cVar == null) {
            throw new IllegalArgumentException("No context specified.");
        } else if (jVar2 != null) {
            if (jVar == null) {
                try {
                    jVar = new j(cVar);
                } catch (Throwable th) {
                    jVar2.v().b("VastVideoCreative", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (jVar.c == 0 && (b3 = oVar.b("Duration")) != null && (a3 = a(b3.c(), jVar2)) > 0) {
                jVar.c = a3;
            }
            o b4 = oVar.b("MediaFiles");
            if (!(b4 == null || (a2 = a(b4, jVar2)) == null || a2.size() <= 0)) {
                if (jVar.a != null) {
                    a2.addAll(jVar.a);
                }
                jVar.a = a2;
            }
            o b5 = oVar.b("VideoClicks");
            if (b5 != null) {
                if (jVar.d == null && (b2 = b5.b("ClickThrough")) != null) {
                    String c2 = b2.c();
                    if (k.b(c2)) {
                        jVar.d = Uri.parse(c2);
                    }
                }
                i.a(b5.a("ClickTracking"), jVar.e, cVar, jVar2);
            }
            i.a(oVar, jVar.f, cVar, jVar2);
            return jVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    private static List<k> a(o oVar, com.applovin.impl.sdk.j jVar) {
        List<o> a2 = oVar.a("MediaFile");
        ArrayList arrayList = new ArrayList(a2.size());
        List<String> a3 = d.a((String) jVar.a(b.eP));
        List<String> a4 = d.a((String) jVar.a(b.eO));
        for (o a5 : a2) {
            k a6 = k.a(a5, jVar);
            if (a6 != null) {
                try {
                    String d2 = a6.d();
                    if (!k.b(d2) || a3.contains(d2)) {
                        if (((Boolean) jVar.a(b.eQ)).booleanValue()) {
                            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(a6.b().toString());
                            if (k.b(fileExtensionFromUrl) && !a4.contains(fileExtensionFromUrl)) {
                            }
                        }
                        p v = jVar.v();
                        v.c("VastVideoCreative", "Video file not supported: " + a6);
                    }
                    arrayList.add(a6);
                } catch (Throwable th) {
                    p v2 = jVar.v();
                    v2.b("VastVideoCreative", "Failed to validate vidoe file: " + a6, th);
                }
            }
        }
        return arrayList;
    }

    public k a(a aVar) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList(3);
        for (String next : this.b) {
            for (k next2 : this.a) {
                String d2 = next2.d();
                if (k.b(d2) && next.equalsIgnoreCase(d2)) {
                    arrayList.add(next2);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = this.a;
        }
        Collections.sort(arrayList, new Comparator<k>() {
            /* renamed from: a */
            public int compare(k kVar, k kVar2) {
                return Integer.compare(kVar.e(), kVar2.e());
            }
        });
        return (k) arrayList.get(aVar == a.LOW ? 0 : aVar == a.MEDIUM ? arrayList.size() / 2 : arrayList.size() - 1);
    }

    public List<k> a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public Uri c() {
        return this.d;
    }

    public Set<g> d() {
        return this.e;
    }

    public Map<String, Set<g>> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (this.c != jVar.c) {
            return false;
        }
        if (this.a == null ? jVar.a != null : !this.a.equals(jVar.a)) {
            return false;
        }
        if (this.d == null ? jVar.d != null : !this.d.equals(jVar.d)) {
            return false;
        }
        if (this.e == null ? jVar.e == null : this.e.equals(jVar.e)) {
            return this.f != null ? this.f.equals(jVar.f) : jVar.f == null;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((this.a != null ? this.a.hashCode() : 0) * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastVideoCreative{videoFiles=" + this.a + ", durationSeconds=" + this.c + ", destinationUri=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
