package com.applovin.impl.sdk.e;

import com.applovin.impl.sdk.j;
import java.util.Timer;
import java.util.TimerTask;

public class l {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public Timer b;
    private long c;
    private long d;
    /* access modifiers changed from: private */
    public final Runnable e;
    private long f;
    /* access modifiers changed from: private */
    public final Object g = new Object();

    private l(j jVar, Runnable runnable) {
        this.a = jVar;
        this.e = runnable;
    }

    public static l a(long j, j jVar, Runnable runnable) {
        if (j < 0) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Invalid fire time passed in: " + j + ".");
        } else if (runnable != null) {
            l lVar = new l(jVar, runnable);
            lVar.c = System.currentTimeMillis();
            lVar.d = j;
            lVar.b = new Timer();
            lVar.b.schedule(lVar.e(), j);
            return lVar;
        } else {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Runnable is null.");
        }
    }

    private TimerTask e() {
        return new TimerTask() {
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    r0 = 0
                    com.applovin.impl.sdk.e.l r1 = com.applovin.impl.sdk.e.l.this     // Catch:{ Throwable -> 0x001d }
                    java.lang.Runnable r1 = r1.e     // Catch:{ Throwable -> 0x001d }
                    r1.run()     // Catch:{ Throwable -> 0x001d }
                    com.applovin.impl.sdk.e.l r1 = com.applovin.impl.sdk.e.l.this
                    java.lang.Object r1 = r1.g
                    monitor-enter(r1)
                    com.applovin.impl.sdk.e.l r2 = com.applovin.impl.sdk.e.l.this     // Catch:{ all -> 0x0018 }
                    java.util.Timer unused = r2.b = r0     // Catch:{ all -> 0x0018 }
                    monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                    goto L_0x0044
                L_0x0018:
                    r0 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                    throw r0
                L_0x001b:
                    r1 = move-exception
                    goto L_0x0048
                L_0x001d:
                    r1 = move-exception
                    com.applovin.impl.sdk.e.l r2 = com.applovin.impl.sdk.e.l.this     // Catch:{ all -> 0x001b }
                    com.applovin.impl.sdk.j r2 = r2.a     // Catch:{ all -> 0x001b }
                    if (r2 == 0) goto L_0x0037
                    com.applovin.impl.sdk.e.l r2 = com.applovin.impl.sdk.e.l.this     // Catch:{ all -> 0x001b }
                    com.applovin.impl.sdk.j r2 = r2.a     // Catch:{ all -> 0x001b }
                    com.applovin.impl.sdk.p r2 = r2.v()     // Catch:{ all -> 0x001b }
                    java.lang.String r3 = "Timer"
                    java.lang.String r4 = "Encountered error while executing timed task"
                    r2.b(r3, r4, r1)     // Catch:{ all -> 0x001b }
                L_0x0037:
                    com.applovin.impl.sdk.e.l r1 = com.applovin.impl.sdk.e.l.this
                    java.lang.Object r1 = r1.g
                    monitor-enter(r1)
                    com.applovin.impl.sdk.e.l r2 = com.applovin.impl.sdk.e.l.this     // Catch:{ all -> 0x0045 }
                    java.util.Timer unused = r2.b = r0     // Catch:{ all -> 0x0045 }
                    monitor-exit(r1)     // Catch:{ all -> 0x0045 }
                L_0x0044:
                    return
                L_0x0045:
                    r0 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x0045 }
                    throw r0
                L_0x0048:
                    com.applovin.impl.sdk.e.l r2 = com.applovin.impl.sdk.e.l.this
                    java.lang.Object r2 = r2.g
                    monitor-enter(r2)
                    com.applovin.impl.sdk.e.l r3 = com.applovin.impl.sdk.e.l.this     // Catch:{ all -> 0x0056 }
                    java.util.Timer unused = r3.b = r0     // Catch:{ all -> 0x0056 }
                    monitor-exit(r2)     // Catch:{ all -> 0x0056 }
                    throw r1
                L_0x0056:
                    r0 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0056 }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.e.l.AnonymousClass1.run():void");
            }
        };
    }

    public long a() {
        if (this.b == null) {
            return this.d - this.f;
        }
        return this.d - (System.currentTimeMillis() - this.c);
    }

    public void b() {
        synchronized (this.g) {
            if (this.b != null) {
                try {
                    this.b.cancel();
                    this.f = System.currentTimeMillis() - this.c;
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.v().b("Timer", "Encountered error while pausing timer", th);
                        }
                    } catch (Throwable th2) {
                        this.b = null;
                        throw th2;
                    }
                }
                this.b = null;
            }
        }
    }

    public void c() {
        synchronized (this.g) {
            if (this.f > 0) {
                try {
                    this.d -= this.f;
                    if (this.d < 0) {
                        this.d = 0;
                    }
                    this.b = new Timer();
                    this.b.schedule(e(), this.d);
                    this.c = System.currentTimeMillis();
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.v().b("Timer", "Encountered error while resuming timer", th);
                        }
                    } catch (Throwable th2) {
                        this.f = 0;
                        throw th2;
                    }
                }
                this.f = 0;
            }
        }
    }

    public void d() {
        synchronized (this.g) {
            if (this.b != null) {
                try {
                    this.b.cancel();
                    this.b = null;
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.v().b("Timer", "Encountered error while cancelling timer", th);
                        }
                        this.b = null;
                    } catch (Throwable th2) {
                        this.b = null;
                        this.f = 0;
                        throw th2;
                    }
                }
                this.f = 0;
            }
        }
    }
}
