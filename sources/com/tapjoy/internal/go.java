package com.tapjoy.internal;

public final class go {
    public long a;
    public long b;
    public long c;

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.lang.String r21, int r22) {
        /*
            r20 = this;
            r1 = r20
            r0 = 0
            r2 = 0
            java.net.DatagramSocket r3 = new java.net.DatagramSocket     // Catch:{ Exception -> 0x00fc, all -> 0x00f3 }
            r3.<init>()     // Catch:{ Exception -> 0x00fc, all -> 0x00f3 }
            r4 = r22
            r3.setSoTimeout(r4)     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            java.net.InetAddress r2 = java.net.InetAddress.getByName(r21)     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r4 = 48
            byte[] r5 = new byte[r4]     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            java.net.DatagramPacket r6 = new java.net.DatagramPacket     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r7 = 123(0x7b, float:1.72E-43)
            r6.<init>(r5, r4, r2, r7)     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r2 = 27
            r5[r0] = r2     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            long r9 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r11 = 1000(0x3e8, double:4.94E-321)
            long r13 = r7 / r11
            long r15 = r13 * r11
            r2 = 0
            long r15 = r7 - r15
            r17 = 2208988800(0x83aa7e80, double:1.091385478E-314)
            long r13 = r13 + r17
            r2 = 24
            long r11 = r13 >> r2
            int r11 = (int) r11     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            byte r11 = (byte) r11     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r12 = 40
            r5[r12] = r11     // Catch:{ Exception -> 0x00f0, all -> 0x00ec }
            r11 = 41
            r17 = 16
            r19 = r3
            long r2 = r13 >> r17
            int r2 = (int) r2
            byte r2 = (byte) r2
            r5[r11] = r2     // Catch:{ Exception -> 0x00e8, all -> 0x00e3 }
            r2 = 42
            r3 = 8
            long r0 = r13 >> r3
            int r0 = (int) r0
            byte r0 = (byte) r0
            r5[r2] = r0     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r0 = 43
            r1 = 0
            long r13 = r13 >> r1
            int r1 = (int) r13     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            byte r1 = (byte) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r5[r0] = r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r0 = 4294967296(0x100000000, double:2.121995791E-314)
            long r15 = r15 * r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r15 = r15 / r0
            r0 = 44
            r1 = 24
            long r13 = r15 >> r1
            int r1 = (int) r13     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            byte r1 = (byte) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r5[r0] = r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r0 = 45
            long r1 = r15 >> r17
            int r1 = (int) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            byte r1 = (byte) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r5[r0] = r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r0 = 46
            long r1 = r15 >> r3
            int r1 = (int) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            byte r1 = (byte) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r5[r0] = r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r0 = 47
            double r1 = java.lang.Math.random()     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r13 = 4643176031446892544(0x406fe00000000000, double:255.0)
            double r1 = r1 * r13
            int r1 = (int) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            byte r1 = (byte) r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r5[r0] = r1     // Catch:{ Exception -> 0x00de, all -> 0x00d8 }
            r1 = r19
            r1.send(r6)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            java.net.DatagramPacket r0 = new java.net.DatagramPacket     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            r0.<init>(r5, r4)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            r1.receive(r0)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            r0 = 0
            long r9 = r2 - r9
            long r7 = r7 + r9
            r0 = 24
            long r13 = b(r5, r0)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            r0 = 32
            long r15 = b(r5, r0)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            long r4 = b(r5, r12)     // Catch:{ Exception -> 0x00e0, all -> 0x00d6 }
            r0 = 0
            long r11 = r4 - r15
            long r9 = r9 - r11
            long r15 = r15 - r13
            long r4 = r4 - r7
            long r15 = r15 + r4
            r4 = 2
            long r15 = r15 / r4
            r0 = 0
            long r7 = r7 + r15
            r4 = r20
            r4.a = r7     // Catch:{ Exception -> 0x00fe, all -> 0x00d4 }
            r4.b = r2     // Catch:{ Exception -> 0x00fe, all -> 0x00d4 }
            r4.c = r9     // Catch:{ Exception -> 0x00fe, all -> 0x00d4 }
            r1.close()
            r0 = 1
            return r0
        L_0x00d4:
            r0 = move-exception
            goto L_0x00f6
        L_0x00d6:
            r0 = move-exception
            goto L_0x00db
        L_0x00d8:
            r0 = move-exception
            r1 = r19
        L_0x00db:
            r4 = r20
            goto L_0x00f6
        L_0x00de:
            r1 = r19
        L_0x00e0:
            r4 = r20
            goto L_0x00fe
        L_0x00e3:
            r0 = move-exception
            r4 = r1
            r1 = r19
            goto L_0x00f6
        L_0x00e8:
            r4 = r1
            r1 = r19
            goto L_0x00fe
        L_0x00ec:
            r0 = move-exception
            r4 = r1
            r1 = r3
            goto L_0x00f6
        L_0x00f0:
            r4 = r1
            r1 = r3
            goto L_0x00fe
        L_0x00f3:
            r0 = move-exception
            r4 = r1
            r1 = r2
        L_0x00f6:
            if (r1 == 0) goto L_0x00fb
            r1.close()
        L_0x00fb:
            throw r0
        L_0x00fc:
            r4 = r1
            r1 = r2
        L_0x00fe:
            if (r1 == 0) goto L_0x0103
            r1.close()
        L_0x0103:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.go.a(java.lang.String, int):boolean");
    }

    private static long a(byte[] bArr, int i) {
        byte b2 = bArr[i];
        byte b3 = bArr[i + 1];
        byte b4 = bArr[i + 2];
        byte b5 = bArr[i + 3];
        byte b6 = b2 & true;
        int i2 = b2;
        if (b6 == true) {
            i2 = (b2 & Byte.MAX_VALUE) + 128;
        }
        byte b7 = b3 & true;
        int i3 = b3;
        if (b7 == true) {
            i3 = (b3 & Byte.MAX_VALUE) + 128;
        }
        byte b8 = b4 & true;
        int i4 = b4;
        if (b8 == true) {
            i4 = (b4 & Byte.MAX_VALUE) + 128;
        }
        byte b9 = b5 & true;
        int i5 = b5;
        if (b9 == true) {
            i5 = (b5 & Byte.MAX_VALUE) + 128;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    private static long b(byte[] bArr, int i) {
        return ((a(bArr, i) - 2208988800L) * 1000) + ((a(bArr, i + 4) * 1000) / 4294967296L);
    }
}
