package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.j;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b<T> {
    private String a;
    private Map<String, String> b;
    private String c;
    private final JSONObject d;
    private String e;
    private final T f;
    private final boolean g;
    private final int h;
    private int i;
    private final int j;
    private final int k;
    private final boolean l;

    public static class a<T> {
        String a;
        String b;
        String c;
        Map<String, String> d;
        JSONObject e;
        T f;
        boolean g = true;
        int h = 1;
        int i;
        int j;
        boolean k;

        public a(j jVar) {
            this.i = ((Integer) jVar.a(com.applovin.impl.sdk.b.b.dG)).intValue();
            this.j = ((Integer) jVar.a(com.applovin.impl.sdk.b.b.dF)).intValue();
            this.k = ((Boolean) jVar.a(com.applovin.impl.sdk.b.b.eV)).booleanValue();
            this.d = new HashMap();
        }

        public a<T> a(int i2) {
            this.h = i2;
            return this;
        }

        public a<T> a(T t) {
            this.f = t;
            return this;
        }

        public a<T> a(String str) {
            this.b = str;
            return this;
        }

        public a<T> a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public a<T> a(JSONObject jSONObject) {
            this.e = jSONObject;
            return this;
        }

        public a<T> a(boolean z) {
            this.k = z;
            return this;
        }

        public b<T> a() {
            return new b<>(this);
        }

        public a<T> b(int i2) {
            this.i = i2;
            return this;
        }

        public a<T> b(String str) {
            this.a = str;
            return this;
        }

        public a<T> c(int i2) {
            this.j = i2;
            return this;
        }

        public a<T> c(String str) {
            this.c = str;
            return this;
        }
    }

    protected b(a<T> aVar) {
        this.a = aVar.b;
        this.b = aVar.d;
        this.c = aVar.a;
        this.d = aVar.e;
        this.e = aVar.c;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.h;
        this.j = aVar.i;
        this.k = aVar.j;
        this.l = aVar.k;
    }

    public static <T> a<T> a(j jVar) {
        return new a<>(jVar);
    }

    public String a() {
        return this.a;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public void a(String str) {
        this.a = str;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public JSONObject d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a == null ? bVar.a != null : !this.a.equals(bVar.a)) {
            return false;
        }
        if (this.b == null ? bVar.b != null : !this.b.equals(bVar.b)) {
            return false;
        }
        if (this.e == null ? bVar.e != null : !this.e.equals(bVar.e)) {
            return false;
        }
        if (this.c == null ? bVar.c != null : !this.c.equals(bVar.c)) {
            return false;
        }
        if (this.d == null ? bVar.d != null : !this.d.equals(bVar.d)) {
            return false;
        }
        if (this.f == null ? bVar.f == null : this.f.equals(bVar.f)) {
            return this.g == bVar.g && this.h == bVar.h && this.i == bVar.i && this.j == bVar.j && this.k == bVar.k && this.l == bVar.l;
        }
        return false;
    }

    public T f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public int h() {
        return this.h - this.i;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = ((((((super.hashCode() * 31) + (this.a != null ? this.a.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.f != null) {
            i2 = this.f.hashCode();
        }
        int i3 = ((((((((((((hashCode + i2) * 31) + (this.g ? 1 : 0)) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + (this.l ? 1 : 0);
        if (this.b != null) {
            i3 = (i3 * 31) + this.b.hashCode();
        }
        if (this.d == null) {
            return i3;
        }
        char[] charArray = this.d.toString().toCharArray();
        Arrays.sort(charArray);
        return (i3 * 31) + new String(charArray).hashCode();
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public boolean l() {
        return this.l;
    }

    public String toString() {
        return "HttpRequest {endpoint=" + this.a + ", backupEndpoint=" + this.e + ", httpMethod=" + this.c + ", body=" + this.d + ", emptyResponse=" + this.f + ", requiresResponse=" + this.g + ", initialRetryAttempts=" + this.h + ", retryAttemptsLeft=" + this.i + ", timeoutMillis=" + this.j + ", retryDelayMillis=" + this.k + ", encodingEnabled=" + this.l + '}';
    }
}
