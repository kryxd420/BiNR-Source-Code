package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.k;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class p extends a {
    private final j a;

    public p(j jVar) {
        super("TaskInitializeSdk", jVar);
        this.a = jVar;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.applovin.impl.sdk.b.b<java.lang.Boolean>, com.applovin.impl.sdk.b.b] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.applovin.impl.sdk.b.b<java.lang.Boolean> r3) {
        /*
            r2 = this;
            com.applovin.impl.sdk.j r0 = r2.a
            java.lang.Object r3 = r0.a(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x0021
            com.applovin.sdk.AppLovinAdSize r3 = com.applovin.sdk.AppLovinAdSize.INTERSTITIAL
            com.applovin.sdk.AppLovinAdType r0 = com.applovin.sdk.AppLovinAdType.INCENTIVIZED
            com.applovin.impl.sdk.j r1 = r2.a
            com.applovin.impl.sdk.ad.d r3 = com.applovin.impl.sdk.ad.d.a((com.applovin.sdk.AppLovinAdSize) r3, (com.applovin.sdk.AppLovinAdType) r0, (com.applovin.impl.sdk.j) r1)
            com.applovin.impl.sdk.j r0 = r2.a
            com.applovin.impl.sdk.d r0 = r0.M()
            r0.f(r3)
        L_0x0021:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.d.p.a(com.applovin.impl.sdk.b.b):void");
    }

    private boolean f() {
        if (k.a("android.permission.INTERNET", d())) {
            return true;
        }
        b().v().e(c(), "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    private void g() {
        b bVar = new b(this.a);
        if (((Boolean) this.a.a(b.dO)).booleanValue()) {
            this.a.D().a((a) bVar);
        } else {
            this.a.D().a((a) bVar, q.a.MAIN);
        }
    }

    private void h() {
        this.a.M().a();
        this.a.N().a();
    }

    private void i() {
        j();
        k();
        l();
    }

    private void j() {
        LinkedHashSet<d> b = this.a.P().b();
        if (!b.isEmpty()) {
            a("Scheduling preload(s) for " + b.size() + " zone(s)");
            Iterator it = b.iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (dVar.d()) {
                    this.a.p().preloadAds(dVar);
                } else {
                    this.a.o().preloadAds(dVar);
                }
            }
        }
    }

    private void k() {
        b<Boolean> bVar = b.aR;
        String str = (String) this.a.a(b.aQ);
        boolean z = false;
        if (str.length() > 0) {
            for (String fromString : com.applovin.impl.sdk.e.d.a(str)) {
                AppLovinAdSize fromString2 = AppLovinAdSize.fromString(fromString);
                if (fromString2 != null) {
                    this.a.M().f(d.a(fromString2, AppLovinAdType.REGULAR, this.a));
                    if (AppLovinAdSize.INTERSTITIAL.getLabel().equals(fromString2.getLabel())) {
                        a(bVar);
                        z = true;
                    }
                }
            }
        }
        if (!z) {
            a(bVar);
        }
    }

    private void l() {
        if (((Boolean) this.a.a(b.aS)).booleanValue()) {
            this.a.N().f(d.h(this.a));
        }
    }

    public i a() {
        return i.a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00de, code lost:
        if (r6.a.d() != false) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00e0, code lost:
        r3 = "succeeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00e3, code lost:
        r3 = "failed";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00e5, code lost:
        r2.append(r3);
        r2.append(" in ");
        r2.append(java.lang.System.currentTimeMillis() - r0);
        r2.append("ms");
        a(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0154, code lost:
        if (r6.a.d() == false) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0157, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = "Initializing AppLovin SDK 9.3.0..."
            r6.a(r2)
            r2 = 0
            boolean r3 = r6.f()     // Catch:{ Throwable -> 0x0104 }
            if (r3 == 0) goto L_0x0090
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.c.h r3 = r3.E()     // Catch:{ Throwable -> 0x0104 }
            r3.d()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.c.h r3 = r3.E()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.c.g r4 = com.applovin.impl.sdk.c.g.b     // Catch:{ Throwable -> 0x0104 }
            r3.c(r4)     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.n r3 = r3.O()     // Catch:{ Throwable -> 0x0104 }
            android.content.Context r4 = r6.d()     // Catch:{ Throwable -> 0x0104 }
            r3.c(r4)     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.n r3 = r3.O()     // Catch:{ Throwable -> 0x0104 }
            android.content.Context r4 = r6.d()     // Catch:{ Throwable -> 0x0104 }
            r3.b((android.content.Context) r4)     // Catch:{ Throwable -> 0x0104 }
            r6.h()     // Catch:{ Throwable -> 0x0104 }
            r6.i()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            android.app.Activity r3 = r3.z()     // Catch:{ Throwable -> 0x0104 }
            if (r3 == 0) goto L_0x005b
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            android.app.Activity r3 = r3.z()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r4 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.mediation.MediationServiceImpl r4 = r4.a((android.app.Activity) r3)     // Catch:{ Throwable -> 0x0104 }
            r4.maybeInitialize(r3)     // Catch:{ Throwable -> 0x0104 }
        L_0x005b:
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.c.c r3 = r3.Q()     // Catch:{ Throwable -> 0x0104 }
            r3.a()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            r3.h()     // Catch:{ Throwable -> 0x0104 }
            r6.g()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.k r3 = r3.H()     // Catch:{ Throwable -> 0x0104 }
            r3.e()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            r4 = 1
            r3.a((boolean) r4)     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.network.d r3 = r3.G()     // Catch:{ Throwable -> 0x0104 }
            r3.a()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            com.applovin.sdk.AppLovinEventService r3 = r3.q()     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r4 = "landing"
            r3.trackEvent(r4)     // Catch:{ Throwable -> 0x0104 }
            goto L_0x00a6
        L_0x0090:
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ Throwable -> 0x0104 }
            r3.a((boolean) r2)     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.j r3 = r6.b()     // Catch:{ Throwable -> 0x0104 }
            com.applovin.impl.sdk.p r3 = r3.v()     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r4 = r6.c()     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r5 = "Couldn't initialize the AppLovin SDK due to missing INTERNET permission"
            r3.e(r4, r5)     // Catch:{ Throwable -> 0x0104 }
        L_0x00a6:
            com.applovin.impl.sdk.j r2 = r6.a
            r2.e()
            com.applovin.impl.sdk.j r2 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r3 = com.applovin.impl.sdk.b.b.aq
            java.lang.Object r2 = r2.a(r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00ce
            com.applovin.impl.sdk.j r2 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Long> r3 = com.applovin.impl.sdk.b.b.ar
            java.lang.Object r2 = r2.a(r3)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            com.applovin.impl.sdk.j r4 = r6.a
            r4.a((long) r2)
        L_0x00ce:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "AppLovin SDK 9.3.0 initialization "
            r2.append(r3)
            com.applovin.impl.sdk.j r3 = r6.a
            boolean r3 = r3.d()
            if (r3 == 0) goto L_0x00e3
        L_0x00e0:
            java.lang.String r3 = "succeeded"
            goto L_0x00e5
        L_0x00e3:
            java.lang.String r3 = "failed"
        L_0x00e5:
            r2.append(r3)
            java.lang.String r3 = " in "
            r2.append(r3)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r0
            r2.append(r3)
            java.lang.String r0 = "ms"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r6.a(r0)
            goto L_0x0157
        L_0x0102:
            r2 = move-exception
            goto L_0x0158
        L_0x0104:
            r3 = move-exception
            java.lang.String r4 = "Unable to initialize SDK."
            r6.a(r4, r3)     // Catch:{ all -> 0x0102 }
            com.applovin.impl.sdk.j r3 = r6.a     // Catch:{ all -> 0x0102 }
            r3.a((boolean) r2)     // Catch:{ all -> 0x0102 }
            com.applovin.impl.sdk.j r2 = r6.a     // Catch:{ all -> 0x0102 }
            com.applovin.impl.sdk.c.j r2 = r2.F()     // Catch:{ all -> 0x0102 }
            com.applovin.impl.sdk.c.i r3 = r6.a()     // Catch:{ all -> 0x0102 }
            r2.a(r3)     // Catch:{ all -> 0x0102 }
            com.applovin.impl.sdk.j r2 = r6.a
            r2.e()
            com.applovin.impl.sdk.j r2 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r3 = com.applovin.impl.sdk.b.b.aq
            java.lang.Object r2 = r2.a(r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0144
            com.applovin.impl.sdk.j r2 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Long> r3 = com.applovin.impl.sdk.b.b.ar
            java.lang.Object r2 = r2.a(r3)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            com.applovin.impl.sdk.j r4 = r6.a
            r4.a((long) r2)
        L_0x0144:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "AppLovin SDK 9.3.0 initialization "
            r2.append(r3)
            com.applovin.impl.sdk.j r3 = r6.a
            boolean r3 = r3.d()
            if (r3 == 0) goto L_0x00e3
            goto L_0x00e0
        L_0x0157:
            return
        L_0x0158:
            com.applovin.impl.sdk.j r3 = r6.a
            r3.e()
            com.applovin.impl.sdk.j r3 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r4 = com.applovin.impl.sdk.b.b.aq
            java.lang.Object r3 = r3.a(r4)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x0180
            com.applovin.impl.sdk.j r3 = r6.a
            com.applovin.impl.sdk.b.b<java.lang.Long> r4 = com.applovin.impl.sdk.b.b.ar
            java.lang.Object r3 = r3.a(r4)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            com.applovin.impl.sdk.j r5 = r6.a
            r5.a((long) r3)
        L_0x0180:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "AppLovin SDK 9.3.0 initialization "
            r3.append(r4)
            com.applovin.impl.sdk.j r4 = r6.a
            boolean r4 = r4.d()
            if (r4 == 0) goto L_0x0195
            java.lang.String r4 = "succeeded"
            goto L_0x0197
        L_0x0195:
            java.lang.String r4 = "failed"
        L_0x0197:
            r3.append(r4)
            java.lang.String r4 = " in "
            r3.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r0
            r3.append(r4)
            java.lang.String r0 = "ms"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r6.a(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.d.p.run():void");
    }
}
