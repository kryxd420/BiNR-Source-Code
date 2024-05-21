package com.applovin.impl.mediation.a;

import com.applovin.impl.mediation.h;

public class f {
    private final g a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    public interface a {
        void a(f fVar);
    }

    private f(g gVar, h hVar, String str, String str2) {
        this.a = gVar;
        this.e = str2;
        if (str != null) {
            this.d = str.substring(0, Math.min(str.length(), gVar.a()));
        } else {
            this.d = null;
        }
        if (hVar != null) {
            this.b = hVar.e();
            this.c = hVar.f();
            return;
        }
        this.b = null;
        this.c = null;
    }

    public static f a(g gVar, h hVar, String str) {
        if (gVar == null) {
            throw new IllegalArgumentException("No spec specified");
        } else if (hVar != null) {
            return new f(gVar, hVar, str, (String) null);
        } else {
            throw new IllegalArgumentException("No adapterWrapper specified");
        }
    }

    public static f a(g gVar, String str) {
        return b(gVar, (h) null, str);
    }

    public static f b(g gVar, h hVar, String str) {
        if (gVar != null) {
            return new f(gVar, hVar, (String) null, str);
        }
        throw new IllegalArgumentException("No spec specified");
    }

    public g a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SignalCollectionResult{mSignalProviderSpec=");
        sb.append(this.a);
        sb.append(", mSdkVersion='");
        sb.append(this.b);
        sb.append('\'');
        sb.append(", mAdapterVersion='");
        sb.append(this.c);
        sb.append('\'');
        sb.append(", mSignalDataLength='");
        sb.append(this.d != null ? this.d.length() : 0);
        sb.append('\'');
        sb.append(", mErrorMessage=");
        sb.append(this.e);
        sb.append('}');
        return sb.toString();
    }
}
