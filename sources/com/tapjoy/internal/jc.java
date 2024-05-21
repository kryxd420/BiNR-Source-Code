package com.tapjoy.internal;

final class jc {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    jc f;
    jc g;

    jc() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    jc(jc jcVar) {
        this(jcVar.a, jcVar.b, jcVar.c);
        jcVar.d = true;
    }

    jc(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    public final jc a() {
        jc jcVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return jcVar;
    }

    public final jc a(jc jcVar) {
        jcVar.g = this;
        jcVar.f = this.f;
        this.f.g = jcVar;
        this.f = jcVar;
        return jcVar;
    }

    public final void a(jc jcVar, int i) {
        if (jcVar.e) {
            if (jcVar.c + i > 8192) {
                if (jcVar.d) {
                    throw new IllegalArgumentException();
                } else if ((jcVar.c + i) - jcVar.b <= 8192) {
                    System.arraycopy(jcVar.a, jcVar.b, jcVar.a, 0, jcVar.c - jcVar.b);
                    jcVar.c -= jcVar.b;
                    jcVar.b = 0;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.a, this.b, jcVar.a, jcVar.c, i);
            jcVar.c += i;
            this.b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
