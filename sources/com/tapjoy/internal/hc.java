package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fb;
import com.tapjoy.internal.fh;
import java.util.Map;
import javax.annotation.Nullable;

public final class hc {
    final hg a;
    final hb b;
    long c;
    private int d = 1;
    private final fb.a e = new fb.a();

    hc(hg hgVar, hb hbVar) {
        this.a = hgVar;
        this.b = hbVar;
    }

    public final void a(String str, String str2, double d2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        double d3;
        hg hgVar = this.a;
        synchronized (hgVar) {
            SharedPreferences.Editor a2 = hgVar.c.a();
            int i = 1;
            if (str2.equals(hgVar.c.l.a())) {
                i = 1 + hgVar.c.m.b();
                hgVar.c.m.a(a2, i);
                d3 = hgVar.c.n.a() + d2;
                hgVar.c.n.a(a2, d3);
                a2.apply();
            } else {
                hgVar.c.l.a(a2, str2);
                hgVar.c.m.a(a2, 1);
                hgVar.c.n.a(a2, d2);
                hgVar.c.o.a(a2);
                hgVar.c.p.a(a2);
                a2.apply();
                hgVar.b.l = str2;
                hgVar.b.o = null;
                hgVar.b.p = null;
                d3 = d2;
            }
            hgVar.b.m = Integer.valueOf(i);
            hgVar.b.n = Double.valueOf(d3);
        }
        ez.a a3 = a(fc.APP, "purchase");
        fh.a aVar = new fh.a();
        aVar.c = str;
        if (str2 != null) {
            aVar.f = str2;
        }
        aVar.e = Double.valueOf(d2);
        if (str5 != null) {
            aVar.m = str5;
        }
        if (str3 != null) {
            aVar.o = str3;
        }
        if (str4 != null) {
            aVar.p = str4;
        }
        a3.p = aVar.b();
        a(a3);
        hg hgVar2 = this.a;
        long longValue = a3.e.longValue();
        synchronized (hgVar2) {
            SharedPreferences.Editor a4 = hgVar2.c.a();
            hgVar2.c.o.a(a4, longValue);
            hgVar2.c.p.a(a4, d2);
            a4.apply();
            hgVar2.b.o = Long.valueOf(longValue);
            hgVar2.b.p = Double.valueOf(d2);
        }
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        ez.a a2 = a(fc.CUSTOM, str2);
        a2.t = str;
        a2.u = str3;
        a2.v = str4;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                a2.w.add(new fd((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(a2);
    }

    public final void a(String str, String str2, int i, long j, long j2, Map map) {
        ez.a a2 = a(fc.USAGES, str);
        a2.x = str2;
        a2.y = Integer.valueOf(i);
        a2.z = Long.valueOf(j);
        a2.A = Long.valueOf(j2);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                a2.w.add(new fd((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(a2);
    }

    public final ez.a a(fc fcVar, String str) {
        ff b2 = this.a.b();
        ez.a aVar = new ez.a();
        aVar.g = hg.a;
        aVar.c = fcVar;
        aVar.d = str;
        if (w.c()) {
            aVar.e = Long.valueOf(w.b());
            aVar.f = Long.valueOf(System.currentTimeMillis());
        } else {
            aVar.e = Long.valueOf(System.currentTimeMillis());
            aVar.h = Long.valueOf(SystemClock.elapsedRealtime());
        }
        aVar.j = b2.d;
        aVar.k = b2.e;
        aVar.l = b2.f;
        return aVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:12|13|15|16|17|18|19|20) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006a A[SYNTHETIC, Splitter:B:37:0x006a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(com.tapjoy.internal.ez.a r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.tapjoy.internal.fc r0 = r5.c     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fc r1 = com.tapjoy.internal.fc.USAGES     // Catch:{ all -> 0x0075 }
            if (r0 == r1) goto L_0x0033
            int r0 = r4.d     // Catch:{ all -> 0x0075 }
            int r1 = r0 + 1
            r4.d = r1     // Catch:{ all -> 0x0075 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0075 }
            r5.n = r0     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fb$a r0 = r4.e     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fc r0 = r0.c     // Catch:{ all -> 0x0075 }
            if (r0 == 0) goto L_0x0021
            com.tapjoy.internal.fb$a r0 = r4.e     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fb r0 = r0.b()     // Catch:{ all -> 0x0075 }
            r5.o = r0     // Catch:{ all -> 0x0075 }
        L_0x0021:
            com.tapjoy.internal.fb$a r0 = r4.e     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fc r1 = r5.c     // Catch:{ all -> 0x0075 }
            r0.c = r1     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fb$a r0 = r4.e     // Catch:{ all -> 0x0075 }
            java.lang.String r1 = r5.d     // Catch:{ all -> 0x0075 }
            r0.d = r1     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fb$a r0 = r4.e     // Catch:{ all -> 0x0075 }
            java.lang.String r1 = r5.t     // Catch:{ all -> 0x0075 }
            r0.e = r1     // Catch:{ all -> 0x0075 }
        L_0x0033:
            com.tapjoy.internal.hb r0 = r4.b     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.ez r5 = r5.b()     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.hp r1 = r0.a     // Catch:{ Exception -> 0x0073 }
            java.lang.Object r2 = r1.a     // Catch:{ Exception -> 0x0073 }
            monitor-enter(r2)     // Catch:{ Exception -> 0x0073 }
            com.tapjoy.internal.ba r3 = r1.b     // Catch:{ Exception -> 0x0046 }
            r3.add(r5)     // Catch:{ Exception -> 0x0046 }
            goto L_0x004e
        L_0x0044:
            r5 = move-exception
            goto L_0x0071
        L_0x0046:
            r1.a()     // Catch:{ all -> 0x0044 }
            com.tapjoy.internal.ba r1 = r1.b     // Catch:{ Exception -> 0x004e }
            r1.add(r5)     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            monitor-exit(r2)     // Catch:{ all -> 0x0044 }
            com.tapjoy.internal.cg r1 = r0.b     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x006a
            boolean r1 = com.tapjoy.internal.ha.a     // Catch:{ all -> 0x0075 }
            if (r1 != 0) goto L_0x0064
            com.tapjoy.internal.fc r5 = r5.n     // Catch:{ all -> 0x0075 }
            com.tapjoy.internal.fc r1 = com.tapjoy.internal.fc.CUSTOM     // Catch:{ all -> 0x0075 }
            if (r5 == r1) goto L_0x005e
            goto L_0x0064
        L_0x005e:
            r5 = 0
            r0.a(r5)     // Catch:{ all -> 0x0075 }
            monitor-exit(r4)
            return
        L_0x0064:
            r5 = 1
            r0.a(r5)     // Catch:{ all -> 0x0075 }
            monitor-exit(r4)
            return
        L_0x006a:
            com.tapjoy.internal.hp r5 = r0.a     // Catch:{ all -> 0x0075 }
            r5.flush()     // Catch:{ all -> 0x0075 }
            monitor-exit(r4)
            return
        L_0x0071:
            monitor-exit(r2)     // Catch:{ all -> 0x0044 }
            throw r5     // Catch:{ Exception -> 0x0073 }
        L_0x0073:
            monitor-exit(r4)
            return
        L_0x0075:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hc.a(com.tapjoy.internal.ez$a):void");
    }
}
