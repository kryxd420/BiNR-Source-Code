package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.charset.Charset;

public final class iv implements iw, ix, Cloneable {
    private static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    jc a;
    long b;

    public final iw a() {
        return this;
    }

    public final void close() {
    }

    public final void flush() {
    }

    public final boolean b() {
        return this.b == 0;
    }

    public final void a(long j) {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public final byte c() {
        if (this.b != 0) {
            jc jcVar = this.a;
            int i = jcVar.b;
            int i2 = jcVar.c;
            int i3 = i + 1;
            byte b2 = jcVar.a[i];
            this.b--;
            if (i3 == i2) {
                this.a = jcVar.a();
                jd.a(jcVar);
            } else {
                jcVar.b = i3;
            }
            return b2;
        }
        throw new IllegalStateException("size == 0");
    }

    public final int d() {
        if (this.b >= 4) {
            jc jcVar = this.a;
            int i = jcVar.b;
            int i2 = jcVar.c;
            if (i2 - i < 4) {
                return ((c() & 255) << 24) | ((c() & 255) << 16) | ((c() & 255) << 8) | (c() & 255);
            }
            byte[] bArr = jcVar.a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            byte b2 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            int i5 = i4 + 1;
            byte b3 = b2 | ((bArr[i4] & 255) << 8);
            int i6 = i5 + 1;
            byte b4 = b3 | (bArr[i5] & 255);
            this.b -= 4;
            if (i6 == i2) {
                this.a = jcVar.a();
                jd.a(jcVar);
            } else {
                jcVar.b = i6;
            }
            return b4;
        }
        throw new IllegalStateException("size < 4: " + this.b);
    }

    public final int e() {
        return ji.a(d());
    }

    public final iy b(long j) {
        return new iy(g(j));
    }

    public final String c(long j) {
        Charset charset = ji.a;
        ji.a(this.b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            jc jcVar = this.a;
            if (((long) jcVar.b) + j > ((long) jcVar.c)) {
                return new String(g(j), charset);
            }
            String str = new String(jcVar.a, jcVar.b, (int) j, charset);
            jcVar.b = (int) (((long) jcVar.b) + j);
            this.b -= j;
            if (jcVar.b == jcVar.c) {
                this.a = jcVar.a();
                jd.a(jcVar);
            }
            return str;
        }
    }

    public final byte[] g() {
        try {
            return g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    private byte[] g(long j) {
        ji.a(this.b, 0, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[((int) j)];
            a(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    private void a(byte[] bArr) {
        int i;
        int i2 = 0;
        while (i2 < bArr.length) {
            int length = bArr.length - i2;
            ji.a((long) bArr.length, (long) i2, (long) length);
            jc jcVar = this.a;
            if (jcVar == null) {
                i = -1;
            } else {
                i = Math.min(length, jcVar.c - jcVar.b);
                System.arraycopy(jcVar.a, jcVar.b, bArr, i2, i);
                jcVar.b += i;
                this.b -= (long) i;
                if (jcVar.b == jcVar.c) {
                    this.a = jcVar.a();
                    jd.a(jcVar);
                }
            }
            if (i != -1) {
                i2 += i;
            } else {
                throw new EOFException();
            }
        }
    }

    public final void d(long j) {
        while (j > 0) {
            if (this.a != null) {
                int min = (int) Math.min(j, (long) (this.a.c - this.a.b));
                long j2 = (long) min;
                this.b -= j2;
                j -= j2;
                this.a.b += min;
                if (this.a.b == this.a.c) {
                    jc jcVar = this.a;
                    this.a = jcVar.a();
                    jd.a(jcVar);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    /* renamed from: a */
    public final iv b(iy iyVar) {
        if (iyVar != null) {
            iyVar.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    /* renamed from: a */
    public final iv b(String str) {
        char c2;
        char charAt;
        int length = str.length();
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (length < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + length + " < 0");
        } else if (length <= str.length()) {
            int i = 0;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 < 128) {
                    jc c3 = c(1);
                    byte[] bArr = c3.a;
                    int i2 = c3.c - i;
                    int min = Math.min(length, 8192 - i2);
                    int i3 = i + 1;
                    bArr[i + i2] = (byte) charAt2;
                    while (true) {
                        i = i3;
                        if (i >= min || (charAt = str.charAt(i)) >= 128) {
                            int i4 = (i2 + i) - c3.c;
                            c3.c += i4;
                            this.b += (long) i4;
                        } else {
                            i3 = i + 1;
                            bArr[i + i2] = (byte) charAt;
                        }
                    }
                    int i42 = (i2 + i) - c3.c;
                    c3.c += i42;
                    this.b += (long) i42;
                } else if (charAt2 < 2048) {
                    e((charAt2 >> 6) | 192);
                    e((int) (charAt2 & '?') | 128);
                    i++;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    e((charAt2 >> 12) | 224);
                    e(((charAt2 >> 6) & 63) | 128);
                    e((int) (charAt2 & '?') | 128);
                    i++;
                } else {
                    int i5 = i + 1;
                    if (i5 < length) {
                        c2 = str.charAt(i5);
                    } else {
                        c2 = 0;
                    }
                    if (charAt2 > 56319 || c2 < 56320 || c2 > 57343) {
                        e(63);
                        i = i5;
                    } else {
                        int i6 = (((charAt2 & 10239) << 10) | (9215 & c2)) + 0;
                        e((i6 >> 18) | 240);
                        e(((i6 >> 12) & 63) | 128);
                        e(((i6 >> 6) & 63) | 128);
                        e((i6 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException("endIndex > string.length: " + length + " > " + str.length());
        }
    }

    public final iv a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            ji.a((long) bArr.length, 0, j);
            int i3 = i2 + 0;
            while (i < i3) {
                jc c2 = c(1);
                int min = Math.min(i3 - i, 8192 - c2.c);
                System.arraycopy(bArr, i, c2.a, c2.c, min);
                i += min;
                c2.c += min;
            }
            this.b += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public final iv e(int i) {
        jc c2 = c(1);
        byte[] bArr = c2.a;
        int i2 = c2.c;
        c2.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    /* renamed from: b */
    public final iv d(int i) {
        int a2 = ji.a(i);
        jc c2 = c(4);
        byte[] bArr = c2.a;
        int i2 = c2.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((a2 >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((a2 >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((a2 >>> 8) & 255);
        bArr[i5] = (byte) (a2 & 255);
        c2.c = i5 + 1;
        this.b += 4;
        return this;
    }

    /* renamed from: e */
    public final iv f(long j) {
        long a2 = ji.a(j);
        jc c2 = c(8);
        byte[] bArr = c2.a;
        int i = c2.c;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((a2 >>> 56) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((a2 >>> 48) & 255));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((a2 >>> 40) & 255));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) ((a2 >>> 32) & 255));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) ((a2 >>> 24) & 255));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) ((a2 >>> 16) & 255));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) ((a2 >>> 8) & 255));
        bArr[i8] = (byte) ((int) (a2 & 255));
        c2.c = i8 + 1;
        this.b += 8;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final jc c(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = jd.a();
            jc jcVar = this.a;
            jc jcVar2 = this.a;
            jc jcVar3 = this.a;
            jcVar2.g = jcVar3;
            jcVar.f = jcVar3;
            return jcVar3;
        } else {
            jc jcVar4 = this.a.g;
            return (jcVar4.c + i > 8192 || !jcVar4.e) ? jcVar4.a(jd.a()) : jcVar4;
        }
    }

    public final void a(iv ivVar, long j) {
        jc jcVar;
        if (ivVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (ivVar != this) {
            ji.a(ivVar.b, 0, j);
            while (j > 0) {
                int i = 0;
                if (j < ((long) (ivVar.a.c - ivVar.a.b))) {
                    jc jcVar2 = this.a != null ? this.a.g : null;
                    if (jcVar2 != null && jcVar2.e) {
                        if ((((long) jcVar2.c) + j) - ((long) (jcVar2.d ? 0 : jcVar2.b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            ivVar.a.a(jcVar2, (int) j);
                            ivVar.b -= j;
                            this.b += j;
                            return;
                        }
                    }
                    jc jcVar3 = ivVar.a;
                    int i2 = (int) j;
                    if (i2 <= 0 || i2 > jcVar3.c - jcVar3.b) {
                        throw new IllegalArgumentException();
                    }
                    if (i2 >= 1024) {
                        jcVar = new jc(jcVar3);
                    } else {
                        jcVar = jd.a();
                        System.arraycopy(jcVar3.a, jcVar3.b, jcVar.a, 0, i2);
                    }
                    jcVar.c = jcVar.b + i2;
                    jcVar3.b += i2;
                    jcVar3.g.a(jcVar);
                    ivVar.a = jcVar;
                }
                jc jcVar4 = ivVar.a;
                long j2 = (long) (jcVar4.c - jcVar4.b);
                ivVar.a = jcVar4.a();
                if (this.a == null) {
                    this.a = jcVar4;
                    jc jcVar5 = this.a;
                    jc jcVar6 = this.a;
                    jc jcVar7 = this.a;
                    jcVar6.g = jcVar7;
                    jcVar5.f = jcVar7;
                } else {
                    jc a2 = this.a.g.a(jcVar4);
                    if (a2.g == a2) {
                        throw new IllegalStateException();
                    } else if (a2.g.e) {
                        int i3 = a2.c - a2.b;
                        int i4 = 8192 - a2.g.c;
                        if (!a2.g.d) {
                            i = a2.g.b;
                        }
                        if (i3 <= i4 + i) {
                            a2.a(a2.g, i3);
                            a2.a();
                            jd.a(a2);
                        }
                    }
                }
                ivVar.b -= j2;
                this.b += j2;
                j -= j2;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    public final long b(iv ivVar, long j) {
        if (ivVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.b == 0) {
            return -1;
        } else {
            if (j > this.b) {
                j = this.b;
            }
            ivVar.a(this, j);
            return j;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iv)) {
            return false;
        }
        iv ivVar = (iv) obj;
        if (this.b != ivVar.b) {
            return false;
        }
        long j = 0;
        if (this.b == 0) {
            return true;
        }
        jc jcVar = this.a;
        jc jcVar2 = ivVar.a;
        int i = jcVar.b;
        int i2 = jcVar2.b;
        while (j < this.b) {
            long min = (long) Math.min(jcVar.c - i, jcVar2.c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (((long) i5) < min) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (jcVar.a[i4] != jcVar2.a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == jcVar.c) {
                jcVar = jcVar.f;
                i = jcVar.b;
            } else {
                i = i4;
            }
            if (i3 == jcVar2.c) {
                jcVar2 = jcVar2.f;
                i2 = jcVar2.b;
            } else {
                i2 = i3;
            }
            j += min;
        }
        return true;
    }

    public final int hashCode() {
        jc jcVar = this.a;
        if (jcVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = jcVar.c;
            for (int i3 = jcVar.b; i3 < i2; i3++) {
                i = (i * 31) + jcVar.a[i3];
            }
            jcVar = jcVar.f;
        } while (jcVar != this.a);
        return i;
    }

    /* renamed from: h */
    public final iv clone() {
        iv ivVar = new iv();
        if (this.b == 0) {
            return ivVar;
        }
        ivVar.a = new jc(this.a);
        jc jcVar = ivVar.a;
        jc jcVar2 = ivVar.a;
        jc jcVar3 = ivVar.a;
        jcVar2.g = jcVar3;
        jcVar.f = jcVar3;
        jc jcVar4 = this.a;
        while (true) {
            jcVar4 = jcVar4.f;
            if (jcVar4 != this.a) {
                ivVar.a.g.a(new jc(jcVar4));
            } else {
                ivVar.b = this.b;
                return ivVar;
            }
        }
    }

    public final long f() {
        long j;
        if (this.b >= 8) {
            jc jcVar = this.a;
            int i = jcVar.b;
            int i2 = jcVar.c;
            if (i2 - i < 8) {
                j = ((((long) d()) & 4294967295L) << 32) | (4294967295L & ((long) d()));
            } else {
                byte[] bArr = jcVar.a;
                int i3 = i + 1;
                int i4 = i3 + 1;
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = i7 + 1;
                long j2 = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i4]) & 255) << 40) | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16);
                int i9 = i8 + 1;
                int i10 = i9 + 1;
                long j3 = (((long) bArr[i9]) & 255) | ((((long) bArr[i8]) & 255) << 8) | j2;
                this.b -= 8;
                if (i10 == i2) {
                    this.a = jcVar.a();
                    jd.a(jcVar);
                } else {
                    jcVar.b = i10;
                }
                j = j3;
            }
            return ji.a(j);
        }
        throw new IllegalStateException("size < 8: " + this.b);
    }

    public final String toString() {
        iy iyVar;
        if (this.b <= 2147483647L) {
            int i = (int) this.b;
            if (i == 0) {
                iyVar = iy.b;
            } else {
                iyVar = new je(this, i);
            }
            return iyVar.toString();
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }
}
