package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;

public class e {
    private a a;
    private Uri b;
    private String c;

    public enum a {
        UNSPECIFIED,
        STATIC,
        IFRAME,
        HTML
    }

    private e() {
    }

    static e a(o oVar, e eVar, j jVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (jVar != null) {
            if (eVar == null) {
                try {
                    eVar = new e();
                } catch (Throwable th) {
                    jVar.v().b("VastNonVideoResource", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (eVar.b == null && !k.b(eVar.c)) {
                String a2 = a(oVar, "StaticResource");
                if (URLUtil.isValidUrl(a2)) {
                    eVar.b = Uri.parse(a2);
                    eVar.a = a.STATIC;
                    return eVar;
                }
                String a3 = a(oVar, "IFrameResource");
                if (k.b(a3)) {
                    eVar.a = a.IFRAME;
                    if (URLUtil.isValidUrl(a3)) {
                        eVar.b = Uri.parse(a3);
                    } else {
                        eVar.c = a3;
                    }
                    return eVar;
                }
                String a4 = a(oVar, "HTMLResource");
                if (k.b(a4)) {
                    eVar.a = a.HTML;
                    if (URLUtil.isValidUrl(a4)) {
                        eVar.b = Uri.parse(a4);
                    } else {
                        eVar.c = a4;
                    }
                }
            }
            return eVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    private static String a(o oVar, String str) {
        o b2 = oVar.b(str);
        if (b2 != null) {
            return b2.c();
        }
        return null;
    }

    public a a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public void a(String str) {
        this.c = str;
    }

    public Uri b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (this.a != eVar.a) {
            return false;
        }
        if (this.b == null ? eVar.b == null : this.b.equals(eVar.b)) {
            return this.c != null ? this.c.equals(eVar.c) : eVar.c == null;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastNonVideoResource{type=" + this.a + ", resourceUri=" + this.b + ", resourceContents='" + this.c + '\'' + '}';
    }
}
