package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class cd {
    public static ExecutorService a;
    public static cg b;
    private Future c;

    public abstract Object a(URI uri, InputStream inputStream);

    public abstract String b();

    public abstract String c();

    public String d() {
        return null;
    }

    public Map a() {
        return Collections.emptyMap();
    }

    public Map e() {
        return new LinkedHashMap();
    }

    public Object f() {
        return b.a(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0022 A[SYNTHETIC, Splitter:B:14:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(@javax.annotation.Nullable com.tapjoy.internal.ci r3, java.util.concurrent.ExecutorService r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.concurrent.Future r0 = r2.c     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0010
            java.util.concurrent.Future r0 = r2.c     // Catch:{ all -> 0x002c }
            boolean r0 = r0.isDone()     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            java.lang.String r1 = "Call has not completed"
            if (r0 == 0) goto L_0x0022
            com.tapjoy.internal.cf r0 = new com.tapjoy.internal.cf     // Catch:{ all -> 0x002c }
            r0.<init>(r2, r3)     // Catch:{ all -> 0x002c }
            java.util.concurrent.Future r3 = r4.submit(r0)     // Catch:{ all -> 0x002c }
            r2.c = r3     // Catch:{ all -> 0x002c }
            monitor-exit(r2)
            return
        L_0x0022:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x002c }
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x002c }
            r3.<init>(r4)     // Catch:{ all -> 0x002c }
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.cd.a(com.tapjoy.internal.ci, java.util.concurrent.ExecutorService):void");
    }
}
