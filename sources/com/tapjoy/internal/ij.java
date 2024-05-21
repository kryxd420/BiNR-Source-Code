package com.tapjoy.internal;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public final class ij {
    private final byte[] a = new byte[256];
    private ByteBuffer b;
    private ii c;
    private int d = 0;

    public final ij a(byte[] bArr) {
        if (bArr != null) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.b = null;
            Arrays.fill(this.a, (byte) 0);
            this.c = new ii();
            this.d = 0;
            this.b = wrap.asReadOnlyBuffer();
            this.b.position(0);
            this.b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }

    public final ii a() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (h()) {
            return this.c;
        } else {
            d();
            if (!h()) {
                b();
                if (this.c.c < 0) {
                    this.c.b = 1;
                }
            }
            return this.c;
        }
    }

    private void b() {
        boolean z = false;
        while (!z && !h() && this.c.c <= Integer.MAX_VALUE) {
            int g = g();
            if (g == 33) {
                int g2 = g();
                if (g2 == 1) {
                    e();
                } else if (g2 != 249) {
                    switch (g2) {
                        case 254:
                            e();
                            break;
                        case 255:
                            f();
                            String str = "";
                            for (int i = 0; i < 11; i++) {
                                str = str + ((char) this.a[i]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                e();
                                break;
                            } else {
                                c();
                                break;
                            }
                        default:
                            e();
                            break;
                    }
                } else {
                    this.c.d = new ih();
                    g();
                    int g3 = g();
                    this.c.d.g = (g3 & 28) >> 2;
                    if (this.c.d.g == 0) {
                        this.c.d.g = 1;
                    }
                    this.c.d.f = (g3 & 1) != 0;
                    short s = this.b.getShort();
                    if (s < 2) {
                        s = 10;
                    }
                    this.c.d.i = s * 10;
                    this.c.d.h = g();
                    g();
                }
            } else if (g == 44) {
                if (this.c.d == null) {
                    this.c.d = new ih();
                }
                this.c.d.a = this.b.getShort();
                this.c.d.b = this.b.getShort();
                this.c.d.c = this.b.getShort();
                this.c.d.d = this.b.getShort();
                int g4 = g();
                boolean z2 = (g4 & 128) != 0;
                int pow = (int) Math.pow(2.0d, (double) ((g4 & 7) + 1));
                this.c.d.e = (g4 & 64) != 0;
                if (z2) {
                    this.c.d.k = a(pow);
                } else {
                    this.c.d.k = null;
                }
                this.c.d.j = this.b.position();
                g();
                e();
                if (!h()) {
                    this.c.c++;
                    this.c.e.add(this.c.d);
                }
            } else if (g != 59) {
                this.c.b = 1;
            } else {
                z = true;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
            r3 = this;
        L_0x0000:
            r3.f()
            byte[] r0 = r3.a
            r1 = 0
            byte r0 = r0[r1]
            r1 = 1
            if (r0 != r1) goto L_0x002a
            byte[] r0 = r3.a
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte[] r1 = r3.a
            r2 = 2
            byte r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            com.tapjoy.internal.ii r2 = r3.c
            int r1 = r1 << 8
            r0 = r0 | r1
            r2.m = r0
            com.tapjoy.internal.ii r0 = r3.c
            int r0 = r0.m
            if (r0 != 0) goto L_0x002a
            com.tapjoy.internal.ii r0 = r3.c
            r1 = -1
            r0.m = r1
        L_0x002a:
            int r0 = r3.d
            if (r0 <= 0) goto L_0x0034
            boolean r0 = r3.h()
            if (r0 == 0) goto L_0x0000
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ij.c():void");
    }

    private void d() {
        boolean z = false;
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) g());
        }
        if (!str.startsWith("GIF")) {
            this.c.b = 1;
            return;
        }
        this.c.f = this.b.getShort();
        this.c.g = this.b.getShort();
        int g = g();
        ii iiVar = this.c;
        if ((g & 128) != 0) {
            z = true;
        }
        iiVar.h = z;
        this.c.i = 2 << (g & 7);
        this.c.j = g();
        this.c.k = g();
        if (this.c.h && !h()) {
            this.c.a = a(this.c.i);
            this.c.l = this.c.a[this.c.j];
        }
    }

    private int[] a(int i) {
        int[] iArr;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                try {
                    int i5 = i4 + 1;
                    int i6 = i5 + 1;
                    int i7 = i2 + 1;
                    iArr[i2] = ((bArr[i3] & 255) << 16) | -16777216 | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                    i3 = i6;
                    i2 = i7;
                } catch (BufferUnderflowException e) {
                    e = e;
                    new Object[1][0] = e;
                    this.c.b = 1;
                    return iArr;
                }
            }
        } catch (BufferUnderflowException e2) {
            e = e2;
            iArr = null;
            new Object[1][0] = e;
            this.c.b = 1;
            return iArr;
        }
        return iArr;
    }

    private void e() {
        int g;
        do {
            try {
                g = g();
                this.b.position(this.b.position() + g);
            } catch (IllegalArgumentException unused) {
                return;
            }
        } while (g > 0);
    }

    private int f() {
        this.d = g();
        if (this.d <= 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.d) {
            try {
                i2 = this.d - i;
                this.b.get(this.a, i, i2);
                i += i2;
            } catch (Exception e) {
                Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.d), e};
                this.c.b = 1;
                return i;
            }
        }
        return i;
    }

    private int g() {
        try {
            return this.b.get() & 255;
        } catch (Exception unused) {
            this.c.b = 1;
            return 0;
        }
    }

    private boolean h() {
        return this.c.b != 0;
    }
}
