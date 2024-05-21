package com.tapjoy.internal;

import java.io.File;

public final class hb implements Runnable {
    final hp a;
    cg b;
    private final Object c = this.a;
    private final Thread d;
    private boolean e;

    public hb(File file) {
        this.a = new hp(file);
        new Object[1][0] = Integer.valueOf(this.a.b());
        this.d = new Thread(this, "5Rocks");
        this.d.start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f6, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x014a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x014b, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0093 A[Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010a A[Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[ExcHandler: InterruptedException (unused java.lang.InterruptedException), SYNTHETIC, Splitter:B:2:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r14 = this;
            r0 = 1
            r1 = 1
        L_0x0002:
            r2 = 0
            r4 = 0
            r5 = r2
        L_0x0006:
            com.tapjoy.internal.cg r7 = r14.b     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r7 == 0) goto L_0x010d
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r7 = r7.b()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r7 <= 0) goto L_0x010d
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 > 0) goto L_0x010d
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r7 = r7.b()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r8 = 10000(0x2710, float:1.4013E-41)
            if (r7 <= r8) goto L_0x002c
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            com.tapjoy.internal.hp r9 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r9 = r9.b()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r9 = r9 - r8
            r7.a(r9)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
        L_0x002c:
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            com.tapjoy.internal.ez r7 = r7.b(r4)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r7 == 0) goto L_0x010d
            com.tapjoy.internal.fl r5 = r7.w     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r8 = 3
            if (r5 == 0) goto L_0x0045
            java.lang.String r5 = r5.G     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r5 != 0) goto L_0x0045
            java.util.concurrent.CountDownLatch r5 = com.tapjoy.internal.hr.c     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5.await(r8, r6)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
        L_0x0045:
            boolean r5 = com.tapjoy.internal.w.c()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r5 != 0) goto L_0x0052
            java.util.concurrent.CountDownLatch r5 = com.tapjoy.internal.hr.b     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5.await(r8, r6)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
        L_0x0052:
            boolean r5 = r14.e     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r6 = 100
            if (r5 != 0) goto L_0x008e
            com.tapjoy.internal.fc r5 = r7.n     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            com.tapjoy.internal.fc r8 = com.tapjoy.internal.fc.APP     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r5 == r8) goto L_0x008e
            com.tapjoy.internal.hp r5 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r5 = r5.b()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r5 >= r6) goto L_0x008e
            java.lang.Long r5 = r7.p     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            long r8 = r5.longValue()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x0075
            goto L_0x008e
        L_0x0075:
            java.lang.Long r5 = r7.p     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            long r8 = r5.longValue()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r10 = 60000(0xea60, double:2.9644E-319)
            long r8 = r8 + r10
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5 = 0
            long r8 = r8 - r12
            long r8 = java.lang.Math.max(r8, r2)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            long r8 = java.lang.Math.min(r8, r10)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            goto L_0x008f
        L_0x008e:
            r8 = r2
        L_0x008f:
            int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r5 > 0) goto L_0x010a
            com.tapjoy.internal.io r5 = new com.tapjoy.internal.io     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5.<init>()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5.a(r7)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            java.lang.Object[] r10 = new java.lang.Object[r0]     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r10[r4] = r7     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r7 = 1
        L_0x00a0:
            if (r7 >= r6) goto L_0x00bf
            com.tapjoy.internal.hp r10 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r10 = r10.b()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r7 >= r10) goto L_0x00bf
            com.tapjoy.internal.hp r10 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            com.tapjoy.internal.ez r10 = r10.b(r7)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r10 == 0) goto L_0x00bf
            boolean r11 = r5.a(r10)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            if (r11 == 0) goto L_0x00bf
            java.lang.Object[] r11 = new java.lang.Object[r0]     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r11[r4] = r10     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r7 = r7 + 1
            goto L_0x00a0
        L_0x00bf:
            int r1 = r1 + 1
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            int r10 = r5.g()     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            r7[r4] = r10     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            r7[r0] = r10     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            com.tapjoy.internal.cg r7 = r14.b     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            r7.a(r5)     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            int r10 = r5.g()     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            r7.a(r10)     // Catch:{ Exception -> 0x00f6, InterruptedException -> 0x014b }
            java.lang.Object[] r1 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x00f2, InterruptedException -> 0x014b }
            int r7 = r5.g()     // Catch:{ Exception -> 0x00f2, InterruptedException -> 0x014b }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x00f2, InterruptedException -> 0x014b }
            r1[r4] = r7     // Catch:{ Exception -> 0x00f2, InterruptedException -> 0x014b }
            r5 = r8
            r1 = 0
            goto L_0x0006
        L_0x00f2:
            r1 = move-exception
            r7 = r1
            r1 = 0
            goto L_0x00f7
        L_0x00f6:
            r7 = move-exception
        L_0x00f7:
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r5 = r5.g()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r6[r4] = r5     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r6[r0] = r7     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r5 = 300000(0x493e0, double:1.482197E-318)
            goto L_0x0006
        L_0x010a:
            r5 = r8
            goto L_0x0006
        L_0x010d:
            com.tapjoy.internal.hp r7 = r14.a     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r7.flush()     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x012e
            java.lang.Object r2 = r14.c     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            monitor-enter(r2)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r14.e = r4     // Catch:{ all -> 0x012b }
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x012b }
            java.lang.Long r7 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x012b }
            r3[r4] = r7     // Catch:{ all -> 0x012b }
            java.lang.Object r3 = r14.c     // Catch:{ all -> 0x012b }
            r3.wait(r5)     // Catch:{ all -> 0x012b }
            monitor-exit(r2)     // Catch:{ all -> 0x012b }
            goto L_0x0002
        L_0x012b:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x012b }
            throw r0     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
        L_0x012e:
            java.lang.Object r2 = r14.c     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            monitor-enter(r2)     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
            r14.e = r4     // Catch:{ all -> 0x0147 }
            com.tapjoy.internal.cg r3 = r14.b     // Catch:{ all -> 0x0147 }
            if (r3 == 0) goto L_0x013f
            com.tapjoy.internal.hp r3 = r14.a     // Catch:{ all -> 0x0147 }
            boolean r3 = r3.c()     // Catch:{ all -> 0x0147 }
            if (r3 == 0) goto L_0x0144
        L_0x013f:
            java.lang.Object r3 = r14.c     // Catch:{ all -> 0x0147 }
            r3.wait()     // Catch:{ all -> 0x0147 }
        L_0x0144:
            monitor-exit(r2)     // Catch:{ all -> 0x0147 }
            goto L_0x0002
        L_0x0147:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0147 }
            throw r0     // Catch:{ InterruptedException -> 0x014b, Exception -> 0x014a }
        L_0x014a:
            return
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hb.run():void");
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        synchronized (this.c) {
            this.e = z;
            this.c.notify();
        }
    }

    public final void a() {
        if (this.b != null && !this.a.c()) {
            a(true);
        }
    }
}
