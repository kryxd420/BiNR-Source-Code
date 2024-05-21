package com.tapjoy.internal;

import java.util.Arrays;

final class je extends iy {
    final transient byte[][] f;
    final transient int[] g;

    je(iv ivVar, int i) {
        super((byte[]) null);
        ji.a(ivVar.b, 0, (long) i);
        int i2 = 0;
        jc jcVar = ivVar.a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (jcVar.c != jcVar.b) {
                i3 += jcVar.c - jcVar.b;
                i4++;
                jcVar = jcVar.f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.f = new byte[i4][];
        this.g = new int[(i4 * 2)];
        jc jcVar2 = ivVar.a;
        int i5 = 0;
        while (i2 < i) {
            this.f[i5] = jcVar2.a;
            i2 += jcVar2.c - jcVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            this.g[i5] = i2;
            this.g[this.f.length + i5] = jcVar2.b;
            jcVar2.d = true;
            i5++;
            jcVar2 = jcVar2.f;
        }
    }

    public final String a() {
        return e().a();
    }

    public final String b() {
        return e().b();
    }

    public final iy a(int i, int i2) {
        return e().a(i, i2);
    }

    public final byte a(int i) {
        int i2;
        ji.a((long) this.g[this.f.length - 1], (long) i, 1);
        int b = b(i);
        if (b == 0) {
            i2 = 0;
        } else {
            i2 = this.g[b - 1];
        }
        return this.f[b][(i - i2) + this.g[this.f.length + b]];
    }

    private int b(int i) {
        int binarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public final int c() {
        return this.g[this.f.length - 1];
    }

    public final byte[] d() {
        byte[] bArr = new byte[this.g[this.f.length - 1]];
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            System.arraycopy(this.f[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public final void a(iv ivVar) {
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            jc jcVar = new jc(this.f[i], i3, (i3 + i4) - i2);
            if (ivVar.a == null) {
                jcVar.g = jcVar;
                jcVar.f = jcVar;
                ivVar.a = jcVar;
            } else {
                ivVar.a.g.a(jcVar);
            }
            i++;
            i2 = i4;
        }
        ivVar.b += (long) i2;
    }

    public final boolean a(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i < 0 || i > c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b = b(i);
        while (i3 > 0) {
            if (b == 0) {
                i4 = 0;
            } else {
                i4 = this.g[b - 1];
            }
            int min = Math.min(i3, ((this.g[b] - i4) + i4) - i);
            if (!ji.a(this.f[b], (i - i4) + this.g[this.f.length + b], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    private iy e() {
        return new iy(d());
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r11) {
        /*
            r10 = this;
            r0 = 1
            if (r11 != r10) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r11 instanceof com.tapjoy.internal.iy
            r2 = 0
            if (r1 == 0) goto L_0x005f
            com.tapjoy.internal.iy r11 = (com.tapjoy.internal.iy) r11
            int r1 = r11.c()
            int r3 = r10.c()
            if (r1 != r3) goto L_0x005f
            int r1 = r10.c()
            int r3 = r10.c()
            int r3 = r3 - r1
            if (r3 >= 0) goto L_0x0022
        L_0x0020:
            r11 = 0
            goto L_0x005c
        L_0x0022:
            int r3 = r10.b(r2)
            r4 = 0
            r5 = 0
        L_0x0028:
            if (r1 <= 0) goto L_0x005b
            if (r3 != 0) goto L_0x002e
            r6 = 0
            goto L_0x0034
        L_0x002e:
            int[] r6 = r10.g
            int r7 = r3 + -1
            r6 = r6[r7]
        L_0x0034:
            int[] r7 = r10.g
            r7 = r7[r3]
            int r7 = r7 - r6
            int r7 = r7 + r6
            int r7 = r7 - r4
            int r7 = java.lang.Math.min(r1, r7)
            int[] r8 = r10.g
            byte[][] r9 = r10.f
            int r9 = r9.length
            int r9 = r9 + r3
            r8 = r8[r9]
            int r6 = r4 - r6
            int r6 = r6 + r8
            byte[][] r8 = r10.f
            r8 = r8[r3]
            boolean r6 = r11.a(r5, r8, r6, r7)
            if (r6 != 0) goto L_0x0055
            goto L_0x0020
        L_0x0055:
            int r4 = r4 + r7
            int r5 = r5 + r7
            int r1 = r1 - r7
            int r3 = r3 + 1
            goto L_0x0028
        L_0x005b:
            r11 = 1
        L_0x005c:
            if (r11 == 0) goto L_0x005f
            return r0
        L_0x005f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.je.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int length = this.f.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < length) {
            byte[] bArr = this.f[i2];
            int i5 = this.g[length + i2];
            int i6 = this.g[i2];
            int i7 = (i6 - i3) + i5;
            while (i5 < i7) {
                i4 = (i4 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i3 = i6;
        }
        this.d = i4;
        return i4;
    }

    public final String toString() {
        return e().toString();
    }
}
