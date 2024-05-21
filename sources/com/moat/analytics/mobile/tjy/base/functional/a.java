package com.moat.analytics.mobile.tjy.base.functional;

import java.util.NoSuchElementException;

public final class a {
    private static final a a = new a();
    private final Object b;

    private a() {
        this.b = null;
    }

    private a(Object obj) {
        if (obj != null) {
            this.b = obj;
            return;
        }
        throw new NullPointerException("Optional of null value.");
    }

    public static a a() {
        return a;
    }

    public static a a(Object obj) {
        return new a(obj);
    }

    public static a b(Object obj) {
        return obj == null ? a() : a(obj);
    }

    public final Object b() {
        if (this.b != null) {
            return this.b;
        }
        throw new NoSuchElementException("No value present");
    }

    public final Object c(Object obj) {
        return this.b != null ? this.b : obj;
    }

    public final boolean c() {
        return this.b != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b == aVar.b) {
            return true;
        }
        if (this.b == null || aVar.b == null) {
            return false;
        }
        return this.b.equals(aVar.b);
    }

    public final int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }

    public final String toString() {
        if (this.b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.b});
    }
}
