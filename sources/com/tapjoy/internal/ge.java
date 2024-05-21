package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;

public final class ge {
    private static final ge b;
    private static ge c;
    public final gc a = new gc();
    private Context d;

    static {
        ge geVar = new ge();
        b = geVar;
        c = geVar;
    }

    public static ge a() {
        return c;
    }

    public static gc b() {
        return c.a;
    }

    ge() {
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0030 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x0050
            android.content.Context r0 = r3.d     // Catch:{ all -> 0x004d }
            if (r0 != 0) goto L_0x0050
            r3.d = r4     // Catch:{ all -> 0x004d }
            android.content.SharedPreferences r4 = r3.c()     // Catch:{ all -> 0x004d }
            android.content.SharedPreferences r0 = r3.c()     // Catch:{ all -> 0x004d }
            java.lang.String r1 = "configurations"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x003d
            com.tapjoy.internal.bq r0 = com.tapjoy.internal.bq.b(r0)     // Catch:{ Exception -> 0x0030 }
            java.util.Map r1 = r0.d()     // Catch:{ all -> 0x002b }
            r0.close()     // Catch:{ Exception -> 0x0030 }
            com.tapjoy.internal.gc r0 = r3.a     // Catch:{ Exception -> 0x0030 }
            r0.a((java.util.Map) r1)     // Catch:{ Exception -> 0x0030 }
            goto L_0x003d
        L_0x002b:
            r1 = move-exception
            r0.close()     // Catch:{ Exception -> 0x0030 }
            throw r1     // Catch:{ Exception -> 0x0030 }
        L_0x0030:
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ all -> 0x004d }
            java.lang.String r0 = "configurations"
            android.content.SharedPreferences$Editor r4 = r4.remove(r0)     // Catch:{ all -> 0x004d }
            r4.apply()     // Catch:{ all -> 0x004d }
        L_0x003d:
            com.tapjoy.internal.ge$1 r4 = new com.tapjoy.internal.ge$1     // Catch:{ all -> 0x004d }
            r4.<init>()     // Catch:{ all -> 0x004d }
            com.tapjoy.internal.gc r0 = r3.a     // Catch:{ all -> 0x004d }
            r0.addObserver(r4)     // Catch:{ all -> 0x004d }
            com.tapjoy.internal.gc r0 = r3.a     // Catch:{ all -> 0x004d }
            r4.update(r0, r2)     // Catch:{ all -> 0x004d }
            goto L_0x0050
        L_0x004d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0050:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ge.a(android.content.Context):void");
    }

    public final SharedPreferences c() {
        return this.d.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
    }
}
