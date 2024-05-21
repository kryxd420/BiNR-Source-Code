package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.nio.ByteBuffer;

public final class ik extends ImageView implements Runnable {
    /* access modifiers changed from: private */
    public ig a;
    /* access modifiers changed from: private */
    public Bitmap b;
    private final Handler c = new Handler(Looper.getMainLooper());
    private boolean d;
    private boolean e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public Thread g;
    private b h = null;
    private long i = -1;
    private a j = null;
    private final Runnable k = new Runnable() {
        public final void run() {
            if (ik.this.b != null && !ik.this.b.isRecycled()) {
                ik.this.setImageBitmap(ik.this.b);
            }
        }
    };
    private final Runnable l = new Runnable() {
        public final void run() {
            Bitmap unused = ik.this.b = null;
            ig unused2 = ik.this.a = null;
            Thread unused3 = ik.this.g = null;
            boolean unused4 = ik.this.f = false;
        }
    };

    public interface a {
    }

    public interface b {
        Bitmap a();
    }

    public ik(Context context) {
        super(context);
    }

    public final void a(ii iiVar, byte[] bArr) {
        try {
            this.a = new ig(new il(), iiVar, ByteBuffer.wrap(bArr));
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e2) {
            this.a = null;
            new Object[1][0] = e2;
        }
    }

    public final void setBytes(byte[] bArr) {
        this.a = new ig();
        try {
            this.a.a(bArr);
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e2) {
            this.a = null;
            new Object[1][0] = e2;
        }
    }

    public final long getFramesDisplayDuration() {
        return this.i;
    }

    public final void setFramesDisplayDuration(long j2) {
        this.i = j2;
    }

    public final void a() {
        this.d = true;
        e();
    }

    public final void b() {
        this.d = false;
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
    }

    private void d() {
        boolean z;
        if (this.a.a != 0) {
            ig igVar = this.a;
            if (-1 >= igVar.c.c) {
                z = false;
            } else {
                igVar.a = -1;
                z = true;
            }
            if (z && !this.d) {
                this.e = true;
                e();
            }
        }
    }

    public final void c() {
        this.d = false;
        this.e = false;
        this.f = true;
        b();
        this.c.post(this.l);
    }

    public final int getGifWidth() {
        return this.a.c.f;
    }

    public final int getGifHeight() {
        return this.a.c.g;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0081 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0008  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c1 A[ADDED_TO_REGION, EDGE_INSN: B:65:0x00c1->B:56:0x00c1 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r11 = this;
        L_0x0000:
            boolean r0 = r11.d
            if (r0 != 0) goto L_0x0008
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x00c3
        L_0x0008:
            com.tapjoy.internal.ig r0 = r11.a
            com.tapjoy.internal.ii r1 = r0.c
            int r1 = r1.c
            r2 = -1
            r3 = 1
            r4 = 0
            if (r1 > 0) goto L_0x0015
        L_0x0013:
            r0 = 0
            goto L_0x003d
        L_0x0015:
            int r1 = r0.a
            com.tapjoy.internal.ii r5 = r0.c
            int r5 = r5.c
            int r5 = r5 - r3
            if (r1 != r5) goto L_0x0023
            int r1 = r0.b
            int r1 = r1 + r3
            r0.b = r1
        L_0x0023:
            com.tapjoy.internal.ii r1 = r0.c
            int r1 = r1.m
            if (r1 == r2) goto L_0x0032
            int r1 = r0.b
            com.tapjoy.internal.ii r5 = r0.c
            int r5 = r5.m
            if (r1 <= r5) goto L_0x0032
            goto L_0x0013
        L_0x0032:
            int r1 = r0.a
            int r1 = r1 + r3
            com.tapjoy.internal.ii r5 = r0.c
            int r5 = r5.c
            int r1 = r1 % r5
            r0.a = r1
            r0 = 1
        L_0x003d:
            r5 = 0
            long r7 = java.lang.System.nanoTime()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            com.tapjoy.internal.ig r1 = r11.a     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            android.graphics.Bitmap r1 = r1.a()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            r11.b = r1     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            com.tapjoy.internal.ik$b r1 = r11.h     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            if (r1 == 0) goto L_0x0057
            com.tapjoy.internal.ik$b r1 = r11.h     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            android.graphics.Bitmap r1 = r1.a()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            r11.b = r1     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
        L_0x0057:
            long r9 = java.lang.System.nanoTime()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0075, IllegalArgumentException -> 0x006e }
            r1 = 0
            long r9 = r9 - r7
            r7 = 1000000(0xf4240, double:4.940656E-318)
            long r7 = r9 / r7
            android.os.Handler r1 = r11.c     // Catch:{ ArrayIndexOutOfBoundsException -> 0x006c, IllegalArgumentException -> 0x006a }
            java.lang.Runnable r9 = r11.k     // Catch:{ ArrayIndexOutOfBoundsException -> 0x006c, IllegalArgumentException -> 0x006a }
            r1.post(r9)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x006c, IllegalArgumentException -> 0x006a }
            goto L_0x007b
        L_0x006a:
            r1 = move-exception
            goto L_0x0070
        L_0x006c:
            r1 = move-exception
            goto L_0x0077
        L_0x006e:
            r1 = move-exception
            r7 = r5
        L_0x0070:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r4] = r1
            goto L_0x007b
        L_0x0075:
            r1 = move-exception
            r7 = r5
        L_0x0077:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r4] = r1
        L_0x007b:
            r11.e = r4
            boolean r1 = r11.d
            if (r1 == 0) goto L_0x00c1
            if (r0 != 0) goto L_0x0084
            goto L_0x00c1
        L_0x0084:
            com.tapjoy.internal.ig r0 = r11.a     // Catch:{ InterruptedException -> 0x00bc }
            com.tapjoy.internal.ii r1 = r0.c     // Catch:{ InterruptedException -> 0x00bc }
            int r1 = r1.c     // Catch:{ InterruptedException -> 0x00bc }
            if (r1 <= 0) goto L_0x00aa
            int r1 = r0.a     // Catch:{ InterruptedException -> 0x00bc }
            if (r1 >= 0) goto L_0x0091
            goto L_0x00aa
        L_0x0091:
            int r1 = r0.a     // Catch:{ InterruptedException -> 0x00bc }
            if (r1 < 0) goto L_0x00a9
            com.tapjoy.internal.ii r3 = r0.c     // Catch:{ InterruptedException -> 0x00bc }
            int r3 = r3.c     // Catch:{ InterruptedException -> 0x00bc }
            if (r1 >= r3) goto L_0x00a9
            com.tapjoy.internal.ii r0 = r0.c     // Catch:{ InterruptedException -> 0x00bc }
            java.util.List r0 = r0.e     // Catch:{ InterruptedException -> 0x00bc }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ InterruptedException -> 0x00bc }
            com.tapjoy.internal.ih r0 = (com.tapjoy.internal.ih) r0     // Catch:{ InterruptedException -> 0x00bc }
            int r2 = r0.i     // Catch:{ InterruptedException -> 0x00bc }
            r4 = r2
            goto L_0x00aa
        L_0x00a9:
            r4 = -1
        L_0x00aa:
            long r0 = (long) r4     // Catch:{ InterruptedException -> 0x00bc }
            long r0 = r0 - r7
            int r0 = (int) r0     // Catch:{ InterruptedException -> 0x00bc }
            if (r0 <= 0) goto L_0x00bc
            long r1 = r11.i     // Catch:{ InterruptedException -> 0x00bc }
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00b8
            long r0 = r11.i     // Catch:{ InterruptedException -> 0x00bc }
            goto L_0x00b9
        L_0x00b8:
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x00bc }
        L_0x00b9:
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x00bc }
        L_0x00bc:
            boolean r0 = r11.d
            if (r0 != 0) goto L_0x0000
            goto L_0x00c3
        L_0x00c1:
            r11.d = r4
        L_0x00c3:
            boolean r0 = r11.f
            if (r0 == 0) goto L_0x00ce
            android.os.Handler r0 = r11.c
            java.lang.Runnable r1 = r11.l
            r0.post(r1)
        L_0x00ce:
            r0 = 0
            r11.g = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ik.run():void");
    }

    public final b getOnFrameAvailable() {
        return this.h;
    }

    public final void setOnFrameAvailable(b bVar) {
        this.h = bVar;
    }

    public final a getOnAnimationStop() {
        return this.j;
    }

    public final void setOnAnimationStop(a aVar) {
        this.j = aVar;
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    private void e() {
        if ((this.d || this.e) && this.a != null && this.g == null) {
            this.g = new Thread(this);
            this.g.start();
        }
    }
}
