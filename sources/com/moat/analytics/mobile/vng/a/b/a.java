package com.moat.analytics.mobile.vng.a.b;

import java.util.NoSuchElementException;

public final class a<T> {
    private static final a<?> a = new a<>();
    private final T b;

    private a() {
        this.b = null;
    }

    private a(T t) {
        if (t != null) {
            this.b = t;
            return;
        }
        throw new NullPointerException("Optional of null value.");
    }

    public static <T> a<T> a() {
        return a;
    }

    public static <T> a<T> a(T t) {
        return new a<>(t);
    }

    public static <T> a<T> b(T t) {
        return t == null ? a() : a(t);
    }

    public T b() {
        if (this.b != null) {
            return this.b;
        }
        throw new NoSuchElementException("No value present");
    }

    public T c(T t) {
        return this.b != null ? this.b : t;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b != aVar.b) {
            return (this.b == null || aVar.b == null || !this.b.equals(aVar.b)) ? false : true;
        }
        return true;
    }

    public int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }

    public String toString() {
        if (this.b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.b});
    }
}
