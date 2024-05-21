package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.os.Build;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ig {
    private static final String d = "ig";
    int a;
    int b;
    ii c;
    private int[] e;
    private final int[] f;
    private ByteBuffer g;
    private byte[] h;
    @Nullable
    private byte[] i;
    private int j;
    private int k;
    private ij l;
    private short[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private int[] q;
    private a r;
    private Bitmap s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;

    interface a {
        @Nonnull
        Bitmap a(int i, int i2, Bitmap.Config config);

        byte[] a(int i);

        int[] b(int i);
    }

    ig(a aVar, ii iiVar, ByteBuffer byteBuffer) {
        this(aVar, iiVar, byteBuffer, (byte) 0);
    }

    private ig(a aVar, ii iiVar, ByteBuffer byteBuffer, byte b2) {
        this(aVar);
        b(iiVar, byteBuffer);
    }

    private ig(a aVar) {
        this.f = new int[256];
        this.j = 0;
        this.k = 0;
        this.r = aVar;
        this.c = new ii();
    }

    ig() {
        this(new il());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v49, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0222 A[LOOP:4: B:114:0x0220->B:115:0x0222, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.graphics.Bitmap a() {
        /*
            r35 = this;
            r1 = r35
            monitor-enter(r35)
            com.tapjoy.internal.ii r0 = r1.c     // Catch:{ all -> 0x03fa }
            int r0 = r0.c     // Catch:{ all -> 0x03fa }
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 <= 0) goto L_0x0010
            int r0 = r1.a     // Catch:{ all -> 0x03fa }
            if (r0 >= 0) goto L_0x0026
        L_0x0010:
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x03fa }
            com.tapjoy.internal.ii r5 = r1.c     // Catch:{ all -> 0x03fa }
            int r5 = r5.c     // Catch:{ all -> 0x03fa }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03fa }
            r0[r3] = r5     // Catch:{ all -> 0x03fa }
            int r5 = r1.a     // Catch:{ all -> 0x03fa }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03fa }
            r0[r4] = r5     // Catch:{ all -> 0x03fa }
            r1.u = r4     // Catch:{ all -> 0x03fa }
        L_0x0026:
            int r0 = r1.u     // Catch:{ all -> 0x03fa }
            r5 = 0
            if (r0 == r4) goto L_0x03ec
            int r0 = r1.u     // Catch:{ all -> 0x03fa }
            if (r0 != r2) goto L_0x0031
            goto L_0x03ec
        L_0x0031:
            r1.u = r3     // Catch:{ all -> 0x03fa }
            com.tapjoy.internal.ii r0 = r1.c     // Catch:{ all -> 0x03fa }
            java.util.List r0 = r0.e     // Catch:{ all -> 0x03fa }
            int r6 = r1.a     // Catch:{ all -> 0x03fa }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x03fa }
            com.tapjoy.internal.ih r0 = (com.tapjoy.internal.ih) r0     // Catch:{ all -> 0x03fa }
            int r6 = r1.a     // Catch:{ all -> 0x03fa }
            int r6 = r6 - r4
            if (r6 < 0) goto L_0x004f
            com.tapjoy.internal.ii r7 = r1.c     // Catch:{ all -> 0x03fa }
            java.util.List r7 = r7.e     // Catch:{ all -> 0x03fa }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x03fa }
            com.tapjoy.internal.ih r6 = (com.tapjoy.internal.ih) r6     // Catch:{ all -> 0x03fa }
            goto L_0x0050
        L_0x004f:
            r6 = r5
        L_0x0050:
            int[] r7 = r0.k     // Catch:{ all -> 0x03fa }
            if (r7 == 0) goto L_0x0057
            int[] r7 = r0.k     // Catch:{ all -> 0x03fa }
            goto L_0x005b
        L_0x0057:
            com.tapjoy.internal.ii r7 = r1.c     // Catch:{ all -> 0x03fa }
            int[] r7 = r7.a     // Catch:{ all -> 0x03fa }
        L_0x005b:
            r1.e = r7     // Catch:{ all -> 0x03fa }
            int[] r7 = r1.e     // Catch:{ all -> 0x03fa }
            if (r7 != 0) goto L_0x006f
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ all -> 0x03fa }
            int r2 = r1.a     // Catch:{ all -> 0x03fa }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x03fa }
            r0[r3] = r2     // Catch:{ all -> 0x03fa }
            r1.u = r4     // Catch:{ all -> 0x03fa }
            monitor-exit(r35)
            return r5
        L_0x006f:
            boolean r5 = r0.f     // Catch:{ all -> 0x03fa }
            if (r5 == 0) goto L_0x0087
            int[] r5 = r1.e     // Catch:{ all -> 0x03fa }
            int[] r7 = r1.f     // Catch:{ all -> 0x03fa }
            int[] r8 = r1.e     // Catch:{ all -> 0x03fa }
            int r8 = r8.length     // Catch:{ all -> 0x03fa }
            java.lang.System.arraycopy(r5, r3, r7, r3, r8)     // Catch:{ all -> 0x03fa }
            int[] r5 = r1.f     // Catch:{ all -> 0x03fa }
            r1.e = r5     // Catch:{ all -> 0x03fa }
            int[] r5 = r1.e     // Catch:{ all -> 0x03fa }
            int r7 = r0.h     // Catch:{ all -> 0x03fa }
            r5[r7] = r3     // Catch:{ all -> 0x03fa }
        L_0x0087:
            int[] r5 = r1.q     // Catch:{ all -> 0x03fa }
            if (r6 != 0) goto L_0x008e
            java.util.Arrays.fill(r5, r3)     // Catch:{ all -> 0x03fa }
        L_0x008e:
            r7 = 3
            if (r6 == 0) goto L_0x00eb
            int r8 = r6.g     // Catch:{ all -> 0x03fa }
            if (r8 <= 0) goto L_0x00eb
            int r8 = r6.g     // Catch:{ all -> 0x03fa }
            if (r8 != r2) goto L_0x00b9
            boolean r8 = r0.f     // Catch:{ all -> 0x03fa }
            if (r8 != 0) goto L_0x00ae
            com.tapjoy.internal.ii r8 = r1.c     // Catch:{ all -> 0x03fa }
            int r8 = r8.l     // Catch:{ all -> 0x03fa }
            int[] r9 = r0.k     // Catch:{ all -> 0x03fa }
            if (r9 == 0) goto L_0x00b5
            com.tapjoy.internal.ii r9 = r1.c     // Catch:{ all -> 0x03fa }
            int r9 = r9.j     // Catch:{ all -> 0x03fa }
            int r10 = r0.h     // Catch:{ all -> 0x03fa }
            if (r9 != r10) goto L_0x00b5
            goto L_0x00b4
        L_0x00ae:
            int r8 = r1.a     // Catch:{ all -> 0x03fa }
            if (r8 != 0) goto L_0x00b4
            r1.y = r4     // Catch:{ all -> 0x03fa }
        L_0x00b4:
            r8 = 0
        L_0x00b5:
            r1.a(r5, r6, r8)     // Catch:{ all -> 0x03fa }
            goto L_0x00eb
        L_0x00b9:
            int r8 = r6.g     // Catch:{ all -> 0x03fa }
            if (r8 != r7) goto L_0x00eb
            android.graphics.Bitmap r8 = r1.s     // Catch:{ all -> 0x03fa }
            if (r8 != 0) goto L_0x00c5
            r1.a(r5, r6, r3)     // Catch:{ all -> 0x03fa }
            goto L_0x00eb
        L_0x00c5:
            int r8 = r6.d     // Catch:{ all -> 0x03fa }
            int r9 = r1.v     // Catch:{ all -> 0x03fa }
            int r15 = r8 / r9
            int r8 = r6.b     // Catch:{ all -> 0x03fa }
            int r9 = r1.v     // Catch:{ all -> 0x03fa }
            int r13 = r8 / r9
            int r8 = r6.c     // Catch:{ all -> 0x03fa }
            int r9 = r1.v     // Catch:{ all -> 0x03fa }
            int r14 = r8 / r9
            int r6 = r6.a     // Catch:{ all -> 0x03fa }
            int r8 = r1.v     // Catch:{ all -> 0x03fa }
            int r12 = r6 / r8
            int r6 = r1.x     // Catch:{ all -> 0x03fa }
            int r6 = r6 * r13
            int r10 = r6 + r12
            android.graphics.Bitmap r8 = r1.s     // Catch:{ all -> 0x03fa }
            int r11 = r1.x     // Catch:{ all -> 0x03fa }
            r9 = r5
            r8.getPixels(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x03fa }
        L_0x00eb:
            r1.j = r3     // Catch:{ all -> 0x03fa }
            r1.k = r3     // Catch:{ all -> 0x03fa }
            if (r0 == 0) goto L_0x00f8
            java.nio.ByteBuffer r6 = r1.g     // Catch:{ all -> 0x03fa }
            int r8 = r0.j     // Catch:{ all -> 0x03fa }
            r6.position(r8)     // Catch:{ all -> 0x03fa }
        L_0x00f8:
            if (r0 != 0) goto L_0x0103
            com.tapjoy.internal.ii r6 = r1.c     // Catch:{ all -> 0x03fa }
            int r6 = r6.f     // Catch:{ all -> 0x03fa }
            com.tapjoy.internal.ii r8 = r1.c     // Catch:{ all -> 0x03fa }
            int r8 = r8.g     // Catch:{ all -> 0x03fa }
            goto L_0x0107
        L_0x0103:
            int r6 = r0.c     // Catch:{ all -> 0x03fa }
            int r8 = r0.d     // Catch:{ all -> 0x03fa }
        L_0x0107:
            int r6 = r6 * r8
            byte[] r8 = r1.p     // Catch:{ all -> 0x03fa }
            if (r8 == 0) goto L_0x0112
            byte[] r8 = r1.p     // Catch:{ all -> 0x03fa }
            int r8 = r8.length     // Catch:{ all -> 0x03fa }
            if (r8 >= r6) goto L_0x011a
        L_0x0112:
            com.tapjoy.internal.ig$a r8 = r1.r     // Catch:{ all -> 0x03fa }
            byte[] r8 = r8.a(r6)     // Catch:{ all -> 0x03fa }
            r1.p = r8     // Catch:{ all -> 0x03fa }
        L_0x011a:
            short[] r8 = r1.m     // Catch:{ all -> 0x03fa }
            r9 = 4096(0x1000, float:5.74E-42)
            if (r8 != 0) goto L_0x0124
            short[] r8 = new short[r9]     // Catch:{ all -> 0x03fa }
            r1.m = r8     // Catch:{ all -> 0x03fa }
        L_0x0124:
            byte[] r8 = r1.n     // Catch:{ all -> 0x03fa }
            if (r8 != 0) goto L_0x012c
            byte[] r8 = new byte[r9]     // Catch:{ all -> 0x03fa }
            r1.n = r8     // Catch:{ all -> 0x03fa }
        L_0x012c:
            byte[] r8 = r1.o     // Catch:{ all -> 0x03fa }
            if (r8 != 0) goto L_0x0136
            r8 = 4097(0x1001, float:5.741E-42)
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x03fa }
            r1.o = r8     // Catch:{ all -> 0x03fa }
        L_0x0136:
            int r8 = r35.c()     // Catch:{ all -> 0x03fa }
            int r10 = r4 << r8
            int r11 = r10 + 1
            int r12 = r10 + 2
            int r8 = r8 + r4
            int r13 = r4 << r8
            int r13 = r13 - r4
            r14 = 0
        L_0x0145:
            if (r14 >= r10) goto L_0x0154
            short[] r15 = r1.m     // Catch:{ all -> 0x03fa }
            r15[r14] = r3     // Catch:{ all -> 0x03fa }
            byte[] r15 = r1.n     // Catch:{ all -> 0x03fa }
            byte r2 = (byte) r14     // Catch:{ all -> 0x03fa }
            r15[r14] = r2     // Catch:{ all -> 0x03fa }
            int r14 = r14 + 1
            r2 = 2
            goto L_0x0145
        L_0x0154:
            r2 = -1
            r22 = r8
            r20 = r12
            r21 = r13
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r23 = -1
            r24 = 0
            r25 = 0
        L_0x016b:
            r26 = 8
            if (r14 >= r6) goto L_0x0269
            if (r15 != 0) goto L_0x017d
            int r15 = r35.d()     // Catch:{ all -> 0x03fa }
            if (r15 > 0) goto L_0x017b
            r1.u = r7     // Catch:{ all -> 0x03fa }
            goto L_0x0269
        L_0x017b:
            r17 = 0
        L_0x017d:
            byte[] r4 = r1.h     // Catch:{ all -> 0x03fa }
            byte r4 = r4[r17]     // Catch:{ all -> 0x03fa }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r18
            int r16 = r16 + r4
            int r18 = r18 + 8
            int r17 = r17 + 1
            int r15 = r15 + r2
            r4 = r18
            r3 = r20
            r9 = r23
            r27 = r24
            r18 = r14
            r14 = r22
        L_0x0198:
            if (r4 < r14) goto L_0x0254
            r2 = r16 & r21
            int r16 = r16 >> r14
            int r4 = r4 - r14
            if (r2 != r10) goto L_0x01a8
            r14 = r8
            r3 = r12
            r21 = r13
            r2 = -1
            r9 = -1
            goto L_0x0198
        L_0x01a8:
            if (r2 <= r3) goto L_0x01ae
            r1.u = r7     // Catch:{ all -> 0x03fa }
            goto L_0x023c
        L_0x01ae:
            if (r2 == r11) goto L_0x023c
            r7 = -1
            if (r9 != r7) goto L_0x01c5
            byte[] r9 = r1.o     // Catch:{ all -> 0x03fa }
            int r20 = r25 + 1
            byte[] r7 = r1.n     // Catch:{ all -> 0x03fa }
            byte r7 = r7[r2]     // Catch:{ all -> 0x03fa }
            r9[r25] = r7     // Catch:{ all -> 0x03fa }
            r9 = r2
            r27 = r9
            r25 = r20
        L_0x01c2:
            r2 = -1
            r7 = 3
            goto L_0x0198
        L_0x01c5:
            if (r2 < r3) goto L_0x01d4
            byte[] r7 = r1.o     // Catch:{ all -> 0x03fa }
            int r20 = r25 + 1
            r28 = r2
            r2 = r27
            byte r2 = (byte) r2     // Catch:{ all -> 0x03fa }
            r7[r25] = r2     // Catch:{ all -> 0x03fa }
            r2 = r9
            goto L_0x01d8
        L_0x01d4:
            r28 = r2
            r20 = r25
        L_0x01d8:
            if (r2 < r10) goto L_0x01ef
            byte[] r7 = r1.o     // Catch:{ all -> 0x03fa }
            int r22 = r20 + 1
            r29 = r4
            byte[] r4 = r1.n     // Catch:{ all -> 0x03fa }
            byte r4 = r4[r2]     // Catch:{ all -> 0x03fa }
            r7[r20] = r4     // Catch:{ all -> 0x03fa }
            short[] r4 = r1.m     // Catch:{ all -> 0x03fa }
            short r2 = r4[r2]     // Catch:{ all -> 0x03fa }
            r20 = r22
            r4 = r29
            goto L_0x01d8
        L_0x01ef:
            r29 = r4
            byte[] r4 = r1.n     // Catch:{ all -> 0x03fa }
            byte r2 = r4[r2]     // Catch:{ all -> 0x03fa }
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r4 = r1.o     // Catch:{ all -> 0x03fa }
            int r7 = r20 + 1
            r30 = r7
            byte r7 = (byte) r2     // Catch:{ all -> 0x03fa }
            r4[r20] = r7     // Catch:{ all -> 0x03fa }
            r4 = 4096(0x1000, float:5.74E-42)
            if (r3 >= r4) goto L_0x021c
            short[] r4 = r1.m     // Catch:{ all -> 0x03fa }
            short r9 = (short) r9     // Catch:{ all -> 0x03fa }
            r4[r3] = r9     // Catch:{ all -> 0x03fa }
            byte[] r4 = r1.n     // Catch:{ all -> 0x03fa }
            r4[r3] = r7     // Catch:{ all -> 0x03fa }
            int r3 = r3 + 1
            r4 = r3 & r21
            if (r4 != 0) goto L_0x021c
            r7 = 4096(0x1000, float:5.74E-42)
            if (r3 >= r7) goto L_0x021e
            int r14 = r14 + 1
            int r21 = r21 + r3
            goto L_0x021e
        L_0x021c:
            r7 = 4096(0x1000, float:5.74E-42)
        L_0x021e:
            r25 = r30
        L_0x0220:
            if (r25 <= 0) goto L_0x0235
            byte[] r4 = r1.p     // Catch:{ all -> 0x03fa }
            int r9 = r19 + 1
            byte[] r7 = r1.o     // Catch:{ all -> 0x03fa }
            int r25 = r25 + -1
            byte r7 = r7[r25]     // Catch:{ all -> 0x03fa }
            r4[r19] = r7     // Catch:{ all -> 0x03fa }
            int r18 = r18 + 1
            r19 = r9
            r7 = 4096(0x1000, float:5.74E-42)
            goto L_0x0220
        L_0x0235:
            r27 = r2
            r9 = r28
            r4 = r29
            goto L_0x01c2
        L_0x023c:
            r29 = r4
            r2 = r27
            r24 = r2
            r20 = r3
            r23 = r9
            r22 = r14
            r14 = r18
            r18 = r29
            r2 = -1
            r3 = 0
            r4 = 1
            r7 = 3
            r9 = 4096(0x1000, float:5.74E-42)
            goto L_0x016b
        L_0x0254:
            r2 = r27
            r24 = r2
            r20 = r3
            r23 = r9
            r22 = r14
            r14 = r18
            r2 = -1
            r3 = 0
            r9 = 4096(0x1000, float:5.74E-42)
            r18 = r4
            r4 = 1
            goto L_0x016b
        L_0x0269:
            r2 = r19
        L_0x026b:
            if (r2 >= r6) goto L_0x0275
            byte[] r3 = r1.p     // Catch:{ all -> 0x03fa }
            r4 = 0
            r3[r2] = r4     // Catch:{ all -> 0x03fa }
            int r2 = r2 + 1
            goto L_0x026b
        L_0x0275:
            int r2 = r0.d     // Catch:{ all -> 0x03fa }
            int r3 = r1.v     // Catch:{ all -> 0x03fa }
            int r2 = r2 / r3
            int r3 = r0.b     // Catch:{ all -> 0x03fa }
            int r4 = r1.v     // Catch:{ all -> 0x03fa }
            int r3 = r3 / r4
            int r4 = r0.c     // Catch:{ all -> 0x03fa }
            int r6 = r1.v     // Catch:{ all -> 0x03fa }
            int r4 = r4 / r6
            int r6 = r0.a     // Catch:{ all -> 0x03fa }
            int r7 = r1.v     // Catch:{ all -> 0x03fa }
            int r6 = r6 / r7
            int r7 = r1.a     // Catch:{ all -> 0x03fa }
            if (r7 != 0) goto L_0x028f
            r7 = 1
            goto L_0x0290
        L_0x028f:
            r7 = 0
        L_0x0290:
            r8 = 0
            r9 = 0
            r10 = 1
            r11 = 8
        L_0x0295:
            if (r8 >= r2) goto L_0x03b2
            boolean r12 = r0.e     // Catch:{ all -> 0x03fa }
            if (r12 == 0) goto L_0x02ae
            r12 = 4
            if (r9 < r2) goto L_0x02ab
            int r10 = r10 + 1
            switch(r10) {
                case 2: goto L_0x02aa;
                case 3: goto L_0x02a7;
                case 4: goto L_0x02a4;
                default: goto L_0x02a3;
            }     // Catch:{ all -> 0x03fa }
        L_0x02a3:
            goto L_0x02ab
        L_0x02a4:
            r9 = 1
            r11 = 2
            goto L_0x02ab
        L_0x02a7:
            r9 = 2
            r11 = 4
            goto L_0x02ab
        L_0x02aa:
            r9 = 4
        L_0x02ab:
            int r12 = r9 + r11
            goto L_0x02b0
        L_0x02ae:
            r12 = r9
            r9 = r8
        L_0x02b0:
            int r9 = r9 + r3
            int r13 = r1.w     // Catch:{ all -> 0x03fa }
            if (r9 >= r13) goto L_0x039d
            int r13 = r1.x     // Catch:{ all -> 0x03fa }
            int r9 = r9 * r13
            int r13 = r9 + r6
            int r14 = r13 + r4
            int r15 = r1.x     // Catch:{ all -> 0x03fa }
            int r15 = r15 + r9
            if (r15 >= r14) goto L_0x02c5
            int r14 = r1.x     // Catch:{ all -> 0x03fa }
            int r14 = r14 + r9
        L_0x02c5:
            int r9 = r1.v     // Catch:{ all -> 0x03fa }
            int r9 = r9 * r8
            int r15 = r0.c     // Catch:{ all -> 0x03fa }
            int r9 = r9 * r15
            int r15 = r14 - r13
            r31 = r2
            int r2 = r1.v     // Catch:{ all -> 0x03fa }
            int r15 = r15 * r2
            int r15 = r15 + r9
        L_0x02d6:
            if (r13 >= r14) goto L_0x039f
            int r2 = r1.v     // Catch:{ all -> 0x03fa }
            r32 = r3
            r3 = 1
            if (r2 != r3) goto L_0x02ef
            byte[] r2 = r1.p     // Catch:{ all -> 0x03fa }
            byte r2 = r2[r9]     // Catch:{ all -> 0x03fa }
            r2 = r2 & 255(0xff, float:3.57E-43)
            int[] r3 = r1.e     // Catch:{ all -> 0x03fa }
            r3 = r3[r2]     // Catch:{ all -> 0x03fa }
            r33 = r4
            r34 = r6
            goto L_0x0382
        L_0x02ef:
            int r2 = r0.c     // Catch:{ all -> 0x03fa }
            r33 = r4
            r3 = r9
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
        L_0x02fe:
            int r4 = r1.v     // Catch:{ all -> 0x03fa }
            int r4 = r4 + r9
            if (r3 >= r4) goto L_0x0335
            byte[] r4 = r1.p     // Catch:{ all -> 0x03fa }
            int r4 = r4.length     // Catch:{ all -> 0x03fa }
            if (r3 >= r4) goto L_0x0335
            if (r3 >= r15) goto L_0x0335
            byte[] r4 = r1.p     // Catch:{ all -> 0x03fa }
            byte r4 = r4[r3]     // Catch:{ all -> 0x03fa }
            r4 = r4 & 255(0xff, float:3.57E-43)
            r34 = r6
            int[] r6 = r1.e     // Catch:{ all -> 0x03fa }
            r4 = r6[r4]     // Catch:{ all -> 0x03fa }
            if (r4 == 0) goto L_0x0330
            int r6 = r4 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r16 = r16 + r6
            int r6 = r4 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r17 = r17 + r6
            int r6 = r4 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r18 = r18 + r6
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r19 = r19 + r4
            int r20 = r20 + 1
        L_0x0330:
            int r3 = r3 + 1
            r6 = r34
            goto L_0x02fe
        L_0x0335:
            r34 = r6
            int r2 = r2 + r9
            r3 = r2
        L_0x0339:
            int r4 = r1.v     // Catch:{ all -> 0x03fa }
            int r4 = r4 + r2
            if (r3 >= r4) goto L_0x036c
            byte[] r4 = r1.p     // Catch:{ all -> 0x03fa }
            int r4 = r4.length     // Catch:{ all -> 0x03fa }
            if (r3 >= r4) goto L_0x036c
            if (r3 >= r15) goto L_0x036c
            byte[] r4 = r1.p     // Catch:{ all -> 0x03fa }
            byte r4 = r4[r3]     // Catch:{ all -> 0x03fa }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int[] r6 = r1.e     // Catch:{ all -> 0x03fa }
            r4 = r6[r4]     // Catch:{ all -> 0x03fa }
            if (r4 == 0) goto L_0x0369
            int r6 = r4 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r16 = r16 + r6
            int r6 = r4 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r17 = r17 + r6
            int r6 = r4 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r18 = r18 + r6
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r19 = r19 + r4
            int r20 = r20 + 1
        L_0x0369:
            int r3 = r3 + 1
            goto L_0x0339
        L_0x036c:
            if (r20 != 0) goto L_0x0370
            r3 = 0
            goto L_0x0382
        L_0x0370:
            int r16 = r16 / r20
            int r2 = r16 << 24
            int r17 = r17 / r20
            int r3 = r17 << 16
            r2 = r2 | r3
            int r18 = r18 / r20
            int r3 = r18 << 8
            r2 = r2 | r3
            int r19 = r19 / r20
            r3 = r2 | r19
        L_0x0382:
            if (r3 == 0) goto L_0x0387
            r5[r13] = r3     // Catch:{ all -> 0x03fa }
            goto L_0x0390
        L_0x0387:
            boolean r2 = r1.y     // Catch:{ all -> 0x03fa }
            if (r2 != 0) goto L_0x0390
            if (r7 == 0) goto L_0x0390
            r2 = 1
            r1.y = r2     // Catch:{ all -> 0x03fa }
        L_0x0390:
            int r2 = r1.v     // Catch:{ all -> 0x03fa }
            int r9 = r9 + r2
            int r13 = r13 + 1
            r3 = r32
            r4 = r33
            r6 = r34
            goto L_0x02d6
        L_0x039d:
            r31 = r2
        L_0x039f:
            r32 = r3
            r33 = r4
            r34 = r6
            int r8 = r8 + 1
            r9 = r12
            r2 = r31
            r3 = r32
            r4 = r33
            r6 = r34
            goto L_0x0295
        L_0x03b2:
            boolean r2 = r1.t     // Catch:{ all -> 0x03fa }
            if (r2 == 0) goto L_0x03d8
            int r2 = r0.g     // Catch:{ all -> 0x03fa }
            if (r2 == 0) goto L_0x03bf
            int r0 = r0.g     // Catch:{ all -> 0x03fa }
            r2 = 1
            if (r0 != r2) goto L_0x03d8
        L_0x03bf:
            android.graphics.Bitmap r0 = r1.s     // Catch:{ all -> 0x03fa }
            if (r0 != 0) goto L_0x03c9
            android.graphics.Bitmap r0 = r35.e()     // Catch:{ all -> 0x03fa }
            r1.s = r0     // Catch:{ all -> 0x03fa }
        L_0x03c9:
            android.graphics.Bitmap r8 = r1.s     // Catch:{ all -> 0x03fa }
            r10 = 0
            int r11 = r1.x     // Catch:{ all -> 0x03fa }
            r12 = 0
            r13 = 0
            int r14 = r1.x     // Catch:{ all -> 0x03fa }
            int r15 = r1.w     // Catch:{ all -> 0x03fa }
            r9 = r5
            r8.setPixels(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x03fa }
        L_0x03d8:
            android.graphics.Bitmap r0 = r35.e()     // Catch:{ all -> 0x03fa }
            r10 = 0
            int r11 = r1.x     // Catch:{ all -> 0x03fa }
            r12 = 0
            r13 = 0
            int r14 = r1.x     // Catch:{ all -> 0x03fa }
            int r15 = r1.w     // Catch:{ all -> 0x03fa }
            r8 = r0
            r9 = r5
            r8.setPixels(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x03fa }
            monitor-exit(r35)
            return r0
        L_0x03ec:
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x03fa }
            int r2 = r1.u     // Catch:{ all -> 0x03fa }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x03fa }
            r3 = 0
            r0[r3] = r2     // Catch:{ all -> 0x03fa }
            monitor-exit(r35)
            return r5
        L_0x03fa:
            r0 = move-exception
            monitor-exit(r35)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ig.a():android.graphics.Bitmap");
    }

    private synchronized void a(ii iiVar, byte[] bArr) {
        a(iiVar, ByteBuffer.wrap(bArr));
    }

    private synchronized void a(ii iiVar, ByteBuffer byteBuffer) {
        b(iiVar, byteBuffer);
    }

    private synchronized void b(ii iiVar, ByteBuffer byteBuffer) {
        int highestOneBit = Integer.highestOneBit(1);
        this.u = 0;
        this.c = iiVar;
        this.y = false;
        this.a = -1;
        this.b = 0;
        this.g = byteBuffer.asReadOnlyBuffer();
        this.g.position(0);
        this.g.order(ByteOrder.LITTLE_ENDIAN);
        this.t = false;
        Iterator it = iiVar.e.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((ih) it.next()).g == 3) {
                    this.t = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.v = highestOneBit;
        this.x = iiVar.f / highestOneBit;
        this.w = iiVar.g / highestOneBit;
        this.p = this.r.a(iiVar.f * iiVar.g);
        this.q = this.r.b(this.x * this.w);
    }

    private void a(int[] iArr, ih ihVar, int i2) {
        int i3 = ihVar.d / this.v;
        int i4 = ihVar.b / this.v;
        int i5 = ihVar.c / this.v;
        int i6 = (i4 * this.x) + (ihVar.a / this.v);
        int i7 = (i3 * this.x) + i6;
        while (i6 < i7) {
            int i8 = i6 + i5;
            for (int i9 = i6; i9 < i8; i9++) {
                iArr[i9] = i2;
            }
            i6 += this.x;
        }
    }

    private void b() {
        if (this.j <= this.k) {
            if (this.i == null) {
                this.i = this.r.a(16384);
            }
            this.k = 0;
            this.j = Math.min(this.g.remaining(), 16384);
            this.g.get(this.i, 0, this.j);
        }
    }

    private int c() {
        try {
            b();
            byte[] bArr = this.i;
            int i2 = this.k;
            this.k = i2 + 1;
            return bArr[i2] & 255;
        } catch (Exception unused) {
            this.u = 1;
            return 0;
        }
    }

    private int d() {
        int c2 = c();
        if (c2 > 0) {
            try {
                if (this.h == null) {
                    this.h = this.r.a(255);
                }
                int i2 = this.j - this.k;
                if (i2 >= c2) {
                    System.arraycopy(this.i, this.k, this.h, 0, c2);
                    this.k += c2;
                } else if (this.g.remaining() + i2 >= c2) {
                    System.arraycopy(this.i, this.k, this.h, 0, i2);
                    this.k = this.j;
                    b();
                    int i3 = c2 - i2;
                    System.arraycopy(this.i, 0, this.h, i2, i3);
                    this.k += i3;
                } else {
                    this.u = 1;
                }
            } catch (Exception e2) {
                new Object[1][0] = e2;
                this.u = 1;
            }
        }
        return c2;
    }

    private Bitmap e() {
        Bitmap a2 = this.r.a(this.x, this.w, this.y ? Bitmap.Config.ARGB_4444 : Bitmap.Config.RGB_565);
        if (Build.VERSION.SDK_INT >= 12) {
            a2.setHasAlpha(true);
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public final synchronized int a(byte[] bArr) {
        if (this.l == null) {
            this.l = new ij();
        }
        this.c = this.l.a(bArr).a();
        if (bArr != null) {
            a(this.c, bArr);
        }
        return this.u;
    }
}
