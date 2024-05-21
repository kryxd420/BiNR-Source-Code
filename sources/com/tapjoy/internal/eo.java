package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;

public final class eo {
    final ix a;
    private long b = 0;
    private long c = Long.MAX_VALUE;
    private int d;
    private int e = 2;
    private int f = -1;
    private long g = -1;
    private ek h;

    public eo(ix ixVar) {
        this.a = ixVar;
    }

    public final long a() {
        if (this.e == 2) {
            int i = this.d + 1;
            this.d = i;
            if (i <= 65) {
                long j = this.g;
                this.g = -1;
                this.e = 6;
                return j;
            }
            throw new IOException("Wire recursion limit exceeded");
        }
        throw new IllegalStateException("Unexpected call to beginMessage()");
    }

    public final void a(long j) {
        if (this.e == 6) {
            int i = this.d - 1;
            this.d = i;
            if (i < 0 || this.g != -1) {
                throw new IllegalStateException("No corresponding call to beginMessage()");
            } else if (this.b == this.c || this.d == 0) {
                this.c = j;
            } else {
                throw new IOException("Expected to end at " + this.c + " but was " + this.b);
            }
        } else {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
    }

    public final int b() {
        if (this.e == 7) {
            this.e = 2;
            return this.f;
        } else if (this.e == 6) {
            while (this.b < this.c && !this.a.b()) {
                int i = i();
                if (i != 0) {
                    this.f = i >> 3;
                    int i2 = i & 7;
                    switch (i2) {
                        case 0:
                            this.h = ek.VARINT;
                            this.e = 0;
                            return this.f;
                        case 1:
                            this.h = ek.FIXED64;
                            this.e = 1;
                            return this.f;
                        case 2:
                            this.h = ek.LENGTH_DELIMITED;
                            this.e = 2;
                            int i3 = i();
                            if (i3 < 0) {
                                throw new ProtocolException("Negative length: " + i3);
                            } else if (this.g == -1) {
                                this.g = this.c;
                                this.c = this.b + ((long) i3);
                                if (this.c <= this.g) {
                                    return this.f;
                                }
                                throw new EOFException();
                            } else {
                                throw new IllegalStateException();
                            }
                        case 3:
                            a(this.f);
                        case 4:
                            throw new ProtocolException("Unexpected end group");
                        case 5:
                            this.h = ek.FIXED32;
                            this.e = 5;
                            return this.f;
                        default:
                            throw new ProtocolException("Unexpected field encoding: " + i2);
                    }
                } else {
                    throw new ProtocolException("Unexpected tag 0");
                }
            }
            return -1;
        } else {
            throw new IllegalStateException("Unexpected call to nextTag()");
        }
    }

    public final ek c() {
        return this.h;
    }

    private void a(int i) {
        while (this.b < this.c && !this.a.b()) {
            int i2 = i();
            if (i2 != 0) {
                int i3 = i2 >> 3;
                int i4 = i2 & 7;
                switch (i4) {
                    case 0:
                        this.e = 0;
                        e();
                        break;
                    case 1:
                        this.e = 1;
                        g();
                        break;
                    case 2:
                        long i5 = (long) i();
                        this.b += i5;
                        this.a.d(i5);
                        break;
                    case 3:
                        a(i3);
                        break;
                    case 4:
                        if (i3 != i) {
                            throw new ProtocolException("Unexpected end group");
                        }
                        return;
                    case 5:
                        this.e = 5;
                        f();
                        break;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i4);
                }
            } else {
                throw new ProtocolException("Unexpected tag 0");
            }
        }
        throw new EOFException();
    }

    public final int d() {
        if (this.e == 0 || this.e == 2) {
            int i = i();
            b(0);
            return i;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    private int i() {
        this.b++;
        byte c2 = this.a.c();
        if (c2 >= 0) {
            return c2;
        }
        byte b2 = c2 & Byte.MAX_VALUE;
        this.b++;
        byte c3 = this.a.c();
        if (c3 >= 0) {
            return b2 | (c3 << 7);
        }
        byte b3 = b2 | ((c3 & Byte.MAX_VALUE) << 7);
        this.b++;
        byte c4 = this.a.c();
        if (c4 >= 0) {
            return b3 | (c4 << 14);
        }
        byte b4 = b3 | ((c4 & Byte.MAX_VALUE) << 14);
        this.b++;
        byte c5 = this.a.c();
        if (c5 >= 0) {
            return b4 | (c5 << 21);
        }
        byte b5 = b4 | ((c5 & Byte.MAX_VALUE) << 21);
        this.b++;
        byte c6 = this.a.c();
        byte b6 = b5 | (c6 << 28);
        if (c6 >= 0) {
            return b6;
        }
        for (int i = 0; i < 5; i++) {
            this.b++;
            if (this.a.c() >= 0) {
                return b6;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    public final long e() {
        if (this.e == 0 || this.e == 2) {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                this.b++;
                byte c2 = this.a.c();
                j |= ((long) (c2 & Byte.MAX_VALUE)) << i;
                if ((c2 & 128) == 0) {
                    b(0);
                    return j;
                }
            }
            throw new ProtocolException("WireInput encountered a malformed varint");
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    public final int f() {
        if (this.e == 5 || this.e == 2) {
            this.a.a(4);
            this.b += 4;
            int e2 = this.a.e();
            b(5);
            return e2;
        }
        throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.e);
    }

    public final long g() {
        if (this.e == 1 || this.e == 2) {
            this.a.a(8);
            this.b += 8;
            long f2 = this.a.f();
            b(1);
            return f2;
        }
        throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.e);
    }

    private void b(int i) {
        if (this.e == i) {
            this.e = 6;
        } else if (this.b > this.c) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        } else if (this.b == this.c) {
            this.c = this.g;
            this.g = -1;
            this.e = 6;
        } else {
            this.e = 7;
        }
    }

    /* access modifiers changed from: package-private */
    public final long h() {
        if (this.e == 2) {
            long j = this.c - this.b;
            this.a.a(j);
            this.e = 6;
            this.b = this.c;
            this.c = this.g;
            this.g = -1;
            return j;
        }
        throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.e);
    }
}
