package com.applovin.impl.sdk;

import com.applovin.impl.sdk.e.l;
import com.applovin.impl.sdk.o;
import com.applovin.impl.sdk.t;

public class e implements o.a, t.a {
    private l a;
    private final Object b = new Object();
    private final j c;
    /* access modifiers changed from: private */
    public final a d;
    private long e;

    public interface a {
        void onAdRefresh();
    }

    public e(j jVar, a aVar) {
        this.d = aVar;
        this.c = jVar;
    }

    /* access modifiers changed from: private */
    public void j() {
        synchronized (this.b) {
            this.a = null;
            this.c.R().b((t.a) this);
            this.c.S().b((o.a) this);
        }
    }

    public void a(long j) {
        synchronized (this.b) {
            e();
            this.e = j;
            this.a = l.a(j, this.c, new Runnable() {
                public void run() {
                    e.this.j();
                    e.this.d.onAdRefresh();
                }
            });
            this.c.R().a((t.a) this);
            this.c.S().a((o.a) this);
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.A)).booleanValue() && (this.c.S().b() || this.c.R().a())) {
                this.a.b();
            }
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.b) {
            z = this.a != null;
        }
        return z;
    }

    public void b() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.z)).booleanValue()) {
            f();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        if (r2 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        r9.d.onAdRefresh();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r9 = this;
            com.applovin.impl.sdk.j r0 = r9.c
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.a.z
            java.lang.Object r0 = r0.a(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x006a
            java.lang.Object r0 = r9.b
            monitor-enter(r0)
            com.applovin.impl.sdk.j r1 = r9.c     // Catch:{ all -> 0x0067 }
            com.applovin.impl.sdk.o r1 = r1.S()     // Catch:{ all -> 0x0067 }
            boolean r1 = r1.b()     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x002e
            com.applovin.impl.sdk.j r1 = r9.c     // Catch:{ all -> 0x0067 }
            com.applovin.impl.sdk.p r1 = r1.v()     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = "AdRefreshManager"
            java.lang.String r3 = "Waiting for the full screen ad to be dismissed to resume the timer."
            r1.a(r2, r3)     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return
        L_0x002e:
            com.applovin.impl.sdk.e.l r1 = r9.a     // Catch:{ all -> 0x0067 }
            r2 = 0
            if (r1 == 0) goto L_0x005e
            long r3 = r9.e     // Catch:{ all -> 0x0067 }
            long r5 = r9.d()     // Catch:{ all -> 0x0067 }
            r1 = 0
            long r3 = r3 - r5
            com.applovin.impl.sdk.j r1 = r9.c     // Catch:{ all -> 0x0067 }
            com.applovin.impl.sdk.b.b<java.lang.Long> r5 = com.applovin.impl.sdk.b.a.y     // Catch:{ all -> 0x0067 }
            java.lang.Object r1 = r1.a(r5)     // Catch:{ all -> 0x0067 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x0067 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x0067 }
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x0059
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0059
            r9.e()     // Catch:{ all -> 0x0067 }
            r1 = 1
            r2 = 1
            goto L_0x005e
        L_0x0059:
            com.applovin.impl.sdk.e.l r1 = r9.a     // Catch:{ all -> 0x0067 }
            r1.c()     // Catch:{ all -> 0x0067 }
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x006a
            com.applovin.impl.sdk.e$a r0 = r9.d
            r0.onAdRefresh()
            goto L_0x006a
        L_0x0067:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r1
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.c():void");
    }

    public long d() {
        long a2;
        synchronized (this.b) {
            a2 = this.a != null ? this.a.a() : -1;
        }
        return a2;
    }

    public void e() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.d();
                j();
            }
        }
    }

    public void f() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.b();
            }
        }
    }

    public void g() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.c();
            }
        }
    }

    public void h() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.A)).booleanValue()) {
            f();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i() {
        /*
            r4 = this;
            com.applovin.impl.sdk.j r0 = r4.c
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.a.A
            java.lang.Object r0 = r0.a(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r4.b
            monitor-enter(r0)
            com.applovin.impl.sdk.j r1 = r4.c     // Catch:{ all -> 0x0039 }
            com.applovin.impl.sdk.t r1 = r1.R()     // Catch:{ all -> 0x0039 }
            boolean r1 = r1.a()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x002e
            com.applovin.impl.sdk.j r1 = r4.c     // Catch:{ all -> 0x0039 }
            com.applovin.impl.sdk.p r1 = r1.v()     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "AdRefreshManager"
            java.lang.String r3 = "Waiting for the application to enter foreground to resume the timer."
            r1.a(r2, r3)     // Catch:{ all -> 0x0039 }
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            return
        L_0x002e:
            com.applovin.impl.sdk.e.l r1 = r4.a     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0037
            com.applovin.impl.sdk.e.l r1 = r4.a     // Catch:{ all -> 0x0039 }
            r1.c()     // Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            goto L_0x003c
        L_0x0039:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            throw r1
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.i():void");
    }
}
