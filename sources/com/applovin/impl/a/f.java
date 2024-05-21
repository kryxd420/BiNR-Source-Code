package com.applovin.impl.a;

import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.o;
import com.applovin.impl.sdk.j;
import com.unity3d.ads.metadata.MediationMetaData;

public class f {
    private String a;
    private String b;

    private f() {
    }

    public static f a(o oVar, f fVar, j jVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (jVar != null) {
            if (fVar == null) {
                try {
                    fVar = new f();
                } catch (Throwable th) {
                    jVar.v().b("VastSystemInfo", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (!k.b(fVar.a)) {
                String c = oVar.c();
                if (k.b(c)) {
                    fVar.a = c;
                }
            }
            if (!k.b(fVar.b)) {
                String str = oVar.b().get(MediationMetaData.KEY_VERSION);
                if (k.b(str)) {
                    fVar.b = str;
                }
            }
            return fVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.a == null ? fVar.a == null : this.a.equals(fVar.a)) {
            return this.b != null ? this.b.equals(fVar.b) : fVar.b == null;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastSystemInfo{name='" + this.a + '\'' + ", version='" + this.b + '\'' + '}';
    }
}
