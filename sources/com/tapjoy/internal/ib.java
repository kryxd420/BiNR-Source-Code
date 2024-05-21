package com.tapjoy.internal;

import android.graphics.Bitmap;
import com.tapjoy.internal.as;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public final class ib {
    public static final bl e = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            return new ib(bqVar);
        }
    };
    private static final aq f;
    public URL a;
    public Bitmap b;
    public byte[] c;
    public ii d;

    static {
        aq auVar = new au();
        if (!(auVar instanceof av)) {
            auVar = new as.a((at) auVar);
        }
        f = auVar;
    }

    public ib(URL url) {
        this.a = url;
    }

    public final boolean a() {
        return (this.b == null && this.c == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0099, code lost:
        r12 = java.lang.Long.parseLong(r8.substring(8));
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r14 = this;
            com.tapjoy.internal.gc r0 = com.tapjoy.internal.ge.b()
            java.lang.String r1 = "mm_external_cache_enabled"
            r2 = 1
            boolean r0 = r0.a(r1, r2)
            r1 = r0 ^ 1
            if (r1 == 0) goto L_0x0020
            com.tapjoy.internal.aq r2 = f
            java.net.URL r3 = r14.a
            java.lang.Object r2 = r2.a(r3)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            r14.b = r2
            android.graphics.Bitmap r2 = r14.b
            if (r2 == 0) goto L_0x0020
            return
        L_0x0020:
            if (r0 == 0) goto L_0x0063
            com.tapjoy.internal.hx r2 = com.tapjoy.internal.hx.a
            java.net.URL r3 = r14.a
            java.io.File r2 = r2.a((java.net.URL) r3)
            if (r2 == 0) goto L_0x0063
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0043, all -> 0x003e }
            r4.<init>(r2)     // Catch:{ IOException -> 0x0043, all -> 0x003e }
            r14.a(r4)     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            com.tapjoy.internal.kd.a(r4)
            goto L_0x0046
        L_0x0039:
            r0 = move-exception
            r3 = r4
            goto L_0x003f
        L_0x003c:
            r3 = r4
            goto L_0x0043
        L_0x003e:
            r0 = move-exception
        L_0x003f:
            com.tapjoy.internal.kd.a(r3)
            throw r0
        L_0x0043:
            com.tapjoy.internal.kd.a(r3)
        L_0x0046:
            android.graphics.Bitmap r3 = r14.b
            if (r3 != 0) goto L_0x0053
            byte[] r3 = r14.c
            if (r3 == 0) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            r2.delete()
            goto L_0x0063
        L_0x0053:
            if (r1 == 0) goto L_0x0062
            android.graphics.Bitmap r0 = r14.b
            if (r0 == 0) goto L_0x0062
            com.tapjoy.internal.aq r0 = f
            java.net.URL r1 = r14.a
            android.graphics.Bitmap r2 = r14.b
            r0.a(r1, r2)
        L_0x0062:
            return
        L_0x0063:
            java.net.URL r2 = r14.a
            java.net.URLConnection r2 = com.tapjoy.internal.fn.a((java.net.URL) r2)
            r3 = 0
            java.lang.String r5 = "Cache-Control"
            java.lang.String r5 = r2.getHeaderField(r5)
            boolean r6 = com.tapjoy.internal.ju.c(r5)
            if (r6 != 0) goto L_0x009e
            java.lang.String r6 = ","
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r7 = 0
        L_0x007f:
            if (r7 >= r6) goto L_0x009e
            r8 = r5[r7]
            java.lang.String r8 = r8.trim()
            java.lang.String r9 = "max-age="
            boolean r9 = r8.startsWith(r9)
            if (r9 == 0) goto L_0x009b
            r5 = 8
            java.lang.String r5 = r8.substring(r5)
            long r5 = java.lang.Long.parseLong(r5)     // Catch:{ NumberFormatException -> 0x009e }
            r12 = r5
            goto L_0x009f
        L_0x009b:
            int r7 = r7 + 1
            goto L_0x007f
        L_0x009e:
            r12 = r3
        L_0x009f:
            java.io.InputStream r2 = r2.getInputStream()
            java.io.ByteArrayInputStream r11 = r14.a(r2)
            com.tapjoy.internal.kd.a(r2)
            com.tapjoy.internal.hx r2 = com.tapjoy.internal.hx.a
            boolean r2 = com.tapjoy.internal.hx.a((long) r12)
            if (r2 == 0) goto L_0x00cf
            if (r0 == 0) goto L_0x00cf
            android.graphics.Bitmap r0 = r14.b
            if (r0 != 0) goto L_0x00bc
            byte[] r0 = r14.c
            if (r0 == 0) goto L_0x00cf
        L_0x00bc:
            com.tapjoy.internal.hx r9 = com.tapjoy.internal.hx.a
            java.net.URL r10 = r14.a
            android.content.Context r0 = r9.b
            if (r0 == 0) goto L_0x00cf
            java.util.concurrent.ExecutorService r0 = r9.e
            com.tapjoy.internal.hx$2 r2 = new com.tapjoy.internal.hx$2
            r8 = r2
            r8.<init>(r10, r11, r12)
            r0.submit(r2)
        L_0x00cf:
            if (r1 == 0) goto L_0x00de
            android.graphics.Bitmap r0 = r14.b
            if (r0 == 0) goto L_0x00de
            com.tapjoy.internal.aq r0 = f
            java.net.URL r1 = r14.a
            android.graphics.Bitmap r2 = r14.b
            r0.a(r1, r2)
        L_0x00de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ib.b():void");
    }

    private ByteArrayInputStream a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        kb.a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ij ijVar = new ij();
        ijVar.a(byteArray);
        ii a2 = ijVar.a();
        if (a2.b == 0) {
            this.c = byteArray;
            this.d = a2;
        } else {
            s sVar = s.a;
            this.b = s.a((InputStream) byteArrayInputStream);
            byteArrayInputStream.reset();
        }
        return byteArrayInputStream;
    }

    ib(bq bqVar) {
        if (bqVar.k() == bv.STRING) {
            this.a = bqVar.e();
            return;
        }
        bqVar.h();
        String l = bqVar.l();
        while (bqVar.j()) {
            if ("url".equals(l)) {
                this.a = bqVar.e();
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
    }
}
