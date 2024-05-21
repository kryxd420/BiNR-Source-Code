package com.applovin.impl.sdk;

import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.ad.h;
import com.applovin.impl.sdk.ad.j;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.q;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

abstract class r implements m, AppLovinNativeAdLoadListener {
    protected final j a;
    protected final p b;
    /* access modifiers changed from: private */
    public final Object c = new Object();
    private final Map<d, s> d = new HashMap();
    private final Map<d, s> e = new HashMap();
    /* access modifiers changed from: private */
    public final Map<d, Object> f = new HashMap();
    private final Set<d> g = new HashSet();

    r(j jVar) {
        this.a = jVar;
        this.b = jVar.v();
    }

    private void b(final d dVar, Object obj) {
        synchronized (this.c) {
            if (this.f.containsKey(dVar)) {
                this.b.c("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.f.put(dVar, obj);
        }
        final int intValue = ((Integer) this.a.a(b.aO)).intValue();
        if (intValue > 0) {
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                public void run() {
                    synchronized (r.this.c) {
                        Object obj = r.this.f.get(dVar);
                        if (obj != null) {
                            r.this.f.remove(dVar);
                            p pVar = r.this.b;
                            pVar.d("PreloadManager", "Load callback for zone " + dVar + " timed out after " + intValue + " seconds");
                            r.this.a(obj, dVar, -102);
                        }
                    }
                }
            }, TimeUnit.SECONDS.toMillis((long) intValue));
        }
    }

    private void c(j jVar) {
        i(a(jVar));
    }

    private s j(d dVar) {
        return this.d.get(dVar);
    }

    private s k(d dVar) {
        return this.e.get(dVar);
    }

    private boolean l(d dVar) {
        boolean z;
        synchronized (this.c) {
            s j = j(dVar);
            z = j != null && j.c();
        }
        return z;
    }

    private s m(d dVar) {
        synchronized (this.c) {
            s k = k(dVar);
            if (k != null && k.a() > 0) {
                return k;
            }
            s j = j(dVar);
            return j;
        }
    }

    private boolean n(d dVar) {
        boolean contains;
        synchronized (this.c) {
            contains = this.g.contains(dVar);
        }
        return contains;
    }

    /* access modifiers changed from: package-private */
    public abstract d a(j jVar);

    /* access modifiers changed from: package-private */
    public abstract a a(d dVar);

    /* access modifiers changed from: package-private */
    public abstract void a(Object obj, d dVar, int i);

    /* access modifiers changed from: package-private */
    public abstract void a(Object obj, j jVar);

    public void a(LinkedHashSet<d> linkedHashSet) {
        if (this.f != null && !this.f.isEmpty()) {
            synchronized (this.c) {
                Iterator<d> it = this.f.keySet().iterator();
                while (it.hasNext()) {
                    d next = it.next();
                    if (!next.l() && !linkedHashSet.contains(next)) {
                        Object obj = this.f.get(next);
                        it.remove();
                        p pVar = this.b;
                        pVar.e("AppLovinAdService", "Failed to load ad for zone (" + next.a() + "). Please check that the zone has been added to your AppLovin account and given at least 30 minutes to fully propagate.");
                        a(obj, next, -7);
                    }
                }
            }
        }
    }

    public boolean a(d dVar, Object obj) {
        boolean z;
        synchronized (this.c) {
            if (!n(dVar)) {
                b(dVar, obj);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void b(d dVar, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            i(dVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(j jVar) {
        Object obj;
        p pVar;
        String str;
        String str2;
        d a2 = a(jVar);
        boolean k = a2.k();
        synchronized (this.c) {
            obj = this.f.get(a2);
            this.f.remove(a2);
            this.g.add(a2);
            if (obj != null) {
                if (!k) {
                    pVar = this.b;
                    str = "PreloadManager";
                    str2 = "Additional callback found or dummy ads are enabled; skipping enqueue...";
                    pVar.a(str, str2);
                }
            }
            j(a2).a(jVar);
            pVar = this.b;
            str = "PreloadManager";
            str2 = "Ad enqueued: " + jVar;
            pVar.a(str, str2);
        }
        if (obj != null) {
            this.b.a("PreloadManager", "Called additional callback regarding " + jVar);
            if (k) {
                try {
                    a(obj, (j) new h(a2, this.a));
                } catch (Throwable th) {
                    this.a.v().c("PreloadManager", "Encountered throwable while notifying user callback", th);
                }
            } else {
                a(obj, jVar);
                c(jVar);
            }
        }
        this.b.a("PreloadManager", "Pulled ad from network and saved to preload cache: " + jVar);
    }

    public boolean b(d dVar) {
        return this.f.containsKey(dVar);
    }

    public j c(d dVar) {
        j f2;
        synchronized (this.c) {
            s m = m(dVar);
            f2 = m != null ? m.f() : null;
        }
        return f2;
    }

    /* access modifiers changed from: package-private */
    public void c(d dVar, int i) {
        Object remove;
        p pVar = this.b;
        pVar.a("PreloadManager", "Failed to pre-load an ad of zone " + dVar + ", error code " + i);
        synchronized (this.c) {
            remove = this.f.remove(dVar);
            this.g.add(dVar);
        }
        if (remove != null) {
            try {
                a(remove, dVar, i);
            } catch (Throwable th) {
                this.a.v().c("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    public j d(d dVar) {
        j e2;
        synchronized (this.c) {
            s m = m(dVar);
            e2 = m != null ? m.e() : null;
        }
        return e2;
    }

    public j e(d dVar) {
        j jVar;
        p pVar;
        String str;
        StringBuilder sb;
        String str2;
        h hVar;
        synchronized (this.c) {
            s j = j(dVar);
            jVar = null;
            if (j != null) {
                if (dVar.k()) {
                    s k = k(dVar);
                    if (k.c()) {
                        hVar = new h(dVar, this.a);
                    } else if (j.a() > 0) {
                        k.a(j.e());
                        hVar = new h(dVar, this.a);
                    } else if (k.a() > 0 && ((Boolean) this.a.a(b.cd)).booleanValue()) {
                        hVar = new h(dVar, this.a);
                    }
                    jVar = hVar;
                } else {
                    jVar = j.e();
                }
            }
        }
        if (jVar != null) {
            pVar = this.b;
            str = "PreloadManager";
            sb = new StringBuilder();
            str2 = "Retrieved ad of zone ";
        } else {
            pVar = this.b;
            str = "PreloadManager";
            sb = new StringBuilder();
            str2 = "Unable to retrieve ad of zone ";
        }
        sb.append(str2);
        sb.append(dVar);
        sb.append("...");
        pVar.a(str, sb.toString());
        return jVar;
    }

    public void f(d dVar) {
        if (dVar != null) {
            int i = 0;
            synchronized (this.c) {
                s j = j(dVar);
                if (j != null) {
                    i = j.b() - j.a();
                }
            }
            b(dVar, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean g(com.applovin.impl.sdk.ad.d r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.c
            monitor-enter(r0)
            com.applovin.impl.sdk.s r1 = r4.k(r5)     // Catch:{ all -> 0x0032 }
            com.applovin.impl.sdk.j r2 = r4.a     // Catch:{ all -> 0x0032 }
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r3 = com.applovin.impl.sdk.b.b.ce     // Catch:{ all -> 0x0032 }
            java.lang.Object r2 = r2.a(r3)     // Catch:{ all -> 0x0032 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x0032 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0032 }
            r3 = 1
            if (r2 == 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            int r1 = r1.a()     // Catch:{ all -> 0x0032 }
            if (r1 <= 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r3
        L_0x0022:
            com.applovin.impl.sdk.s r5 = r4.j(r5)     // Catch:{ all -> 0x0032 }
            if (r5 == 0) goto L_0x002f
            boolean r5 = r5.d()     // Catch:{ all -> 0x0032 }
            if (r5 != 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r3 = 0
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r3
        L_0x0032:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.r.g(com.applovin.impl.sdk.ad.d):boolean");
    }

    public void h(d dVar) {
        synchronized (this.c) {
            s j = j(dVar);
            if (j != null) {
                j.a(dVar.e());
            } else {
                this.d.put(dVar, new s(dVar.e()));
            }
            s k = k(dVar);
            if (k != null) {
                k.a(dVar.f());
            } else {
                this.e.put(dVar, new s(dVar.f()));
            }
        }
    }

    public void i(d dVar) {
        if (((Boolean) this.a.a(b.aP)).booleanValue() && !l(dVar)) {
            p pVar = this.b;
            pVar.a("PreloadManager", "Preloading ad for zone " + dVar + "...");
            this.a.D().a(a(dVar), q.a.MAIN, 500);
        }
    }
}
