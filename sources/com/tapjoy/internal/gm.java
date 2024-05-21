package com.tapjoy.internal;

public final class gm {
    public static final gm a = new gm(0, 0, 0, 0.0d);
    public final long b;
    public final long c;
    public final double d;
    public long e;
    private final long f;

    public gm(long j, long j2, long j3, double d2) {
        this.f = j;
        this.b = j2;
        this.c = j3;
        this.d = d2;
        this.e = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gm gmVar = (gm) obj;
        return this.f == gmVar.f && this.b == gmVar.b && this.c == gmVar.c && this.d == gmVar.d && this.e == gmVar.e;
    }
}
