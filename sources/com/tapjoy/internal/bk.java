package com.tapjoy.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public final class bk implements bo {
    private final StringWriter a = new StringWriter();
    private final bw b = new bw(this.a);

    public final String toString() {
        try {
            this.b.a.flush();
            return this.a.toString();
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final void a(Writer writer) {
        try {
            this.b.a.flush();
            writer.write(this.a.toString());
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a() {
        try {
            this.b.a();
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk b() {
        try {
            this.b.b();
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk c() {
        try {
            this.b.c();
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk d() {
        try {
            this.b.d();
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(String str) {
        try {
            this.b.a(str);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(bo boVar) {
        try {
            this.b.a(boVar);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk b(String str) {
        try {
            this.b.b(str);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(long j) {
        try {
            this.b.a(j);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(Number number) {
        try {
            this.b.a(number);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    private bk b(Object obj) {
        try {
            this.b.a(obj);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(Collection collection) {
        try {
            this.b.a(collection);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public final bk a(Map map) {
        try {
            this.b.a(map);
            return this;
        } catch (IOException e) {
            throw jv.a(e);
        }
    }

    public static String a(Object obj) {
        return new bk().b(obj).toString();
    }
}
