package com.applovin.impl.sdk;

import java.util.Map;

public class l {
    private final String a;
    private final Map<String, String> b;
    private final long c;
    private final String d;

    public l(String str, Map<String, String> map, long j, String str2) {
        this.a = str;
        this.b = map;
        this.c = j;
        this.d = str2;
    }

    public String a() {
        return this.a;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.c != lVar.c) {
            return false;
        }
        if (this.a == null ? lVar.a != null : !this.a.equals(lVar.a)) {
            return false;
        }
        if (this.b == null ? lVar.b != null : !this.b.equals(lVar.b)) {
            return false;
        }
        if (this.d != null) {
            return this.d.equals(lVar.d);
        }
        if (lVar.d == null) {
            return true;
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Event{eventType='" + this.a + '\'' + ", parameters=" + this.b + ", creationTsMillis=" + this.c + ", uniqueIdentifier='" + this.d + '\'' + '}';
    }
}
