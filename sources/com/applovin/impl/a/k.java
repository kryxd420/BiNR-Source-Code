package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.Locale;

public class k {
    private Uri a;
    private Uri b;
    private a c;
    private String d;
    private int e;
    private int f;
    private int g;

    public enum a {
        Progressive,
        Streaming
    }

    private k() {
    }

    private static a a(String str) {
        if (com.applovin.impl.sdk.e.k.b(str)) {
            if ("progressive".equalsIgnoreCase(str)) {
                return a.Progressive;
            }
            if ("streaming".equalsIgnoreCase(str)) {
                return a.Streaming;
            }
        }
        return a.Progressive;
    }

    public static k a(o oVar, j jVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (jVar != null) {
            try {
                String c2 = oVar.c();
                if (URLUtil.isValidUrl(c2)) {
                    Uri parse = Uri.parse(c2);
                    k kVar = new k();
                    kVar.a = parse;
                    kVar.b = parse;
                    kVar.g = com.applovin.impl.sdk.e.k.a(oVar.b().get("bitrate"));
                    kVar.c = a(oVar.b().get("delivery"));
                    kVar.f = com.applovin.impl.sdk.e.k.a(oVar.b().get(AvidJSONUtil.KEY_HEIGHT));
                    kVar.e = com.applovin.impl.sdk.e.k.a(oVar.b().get(AvidJSONUtil.KEY_WIDTH));
                    kVar.d = oVar.b().get("type").toLowerCase(Locale.ENGLISH);
                    return kVar;
                }
                jVar.v().d("VastVideoFile", "Unable to create video file. Could not find URL.");
                return null;
            } catch (Throwable th) {
                jVar.v().b("VastVideoFile", "Error occurred while initializing", th);
                return null;
            }
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public Uri a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public Uri b() {
        return this.b;
    }

    public boolean c() {
        return this.c == a.Streaming;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.e != kVar.e || this.f != kVar.f || this.g != kVar.g) {
            return false;
        }
        if (this.a == null ? kVar.a != null : !this.a.equals(kVar.a)) {
            return false;
        }
        if (this.b == null ? kVar.b != null : !this.b.equals(kVar.b)) {
            return false;
        }
        if (this.c != kVar.c) {
            return false;
        }
        return this.d != null ? this.d.equals(kVar.d) : kVar.d == null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.e) * 31) + this.f) * 31) + this.g;
    }

    public String toString() {
        return "VastVideoFile{sourceVideoUri=" + this.a + ", videoUri=" + this.b + ", deliveryType=" + this.c + ", fileType='" + this.d + '\'' + ", width=" + this.e + ", height=" + this.f + ", bitrate=" + this.g + '}';
    }
}
