package com.tapjoy.internal;

import com.tapjoy.internal.kj;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class kg implements kj {
    private final ReentrantLock a = new ReentrantLock();
    private final a b = new a(this, (byte) 0);
    private final a c = new a(this, (byte) 0);
    private kj.a d = kj.a.NEW;
    private boolean e = false;

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void b();

    public final ki e() {
        this.a.lock();
        try {
            if (this.d == kj.a.NEW) {
                this.d = kj.a.STARTING;
                a();
            }
        } catch (Throwable th) {
            this.a.unlock();
            throw th;
        }
        this.a.unlock();
        return this.b;
    }

    private ki g() {
        this.a.lock();
        try {
            if (this.d == kj.a.NEW) {
                this.d = kj.a.TERMINATED;
                this.b.a((Object) kj.a.TERMINATED);
                this.c.a((Object) kj.a.TERMINATED);
            } else if (this.d == kj.a.STARTING) {
                this.e = true;
                this.b.a((Object) kj.a.STOPPING);
            } else if (this.d == kj.a.RUNNING) {
                this.d = kj.a.STOPPING;
                b();
            }
        } catch (Throwable th) {
            this.a.unlock();
            throw th;
        }
        this.a.unlock();
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.a.lock();
        try {
            if (this.d == kj.a.STARTING) {
                this.d = kj.a.RUNNING;
                if (this.e) {
                    g();
                } else {
                    this.b.a((Object) kj.a.RUNNING);
                }
                return;
            }
            IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.d);
            a(illegalStateException);
            throw illegalStateException;
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public final void d() {
        this.a.lock();
        try {
            if (this.d != kj.a.STOPPING) {
                if (this.d != kj.a.RUNNING) {
                    IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + this.d);
                    a(illegalStateException);
                    throw illegalStateException;
                }
            }
            this.d = kj.a.TERMINATED;
            this.c.a((Object) kj.a.TERMINATED);
        } finally {
            this.a.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        jt.a(th);
        this.a.lock();
        try {
            if (this.d == kj.a.STARTING) {
                this.b.a(th);
                this.c.a((Throwable) new Exception("Service failed to start.", th));
            } else if (this.d == kj.a.STOPPING) {
                this.c.a(th);
            } else if (this.d == kj.a.RUNNING) {
                this.c.a((Throwable) new Exception("Service failed while running", th));
            } else if (this.d == kj.a.NEW || this.d == kj.a.TERMINATED) {
                throw new IllegalStateException("Failed while in state:" + this.d, th);
            }
            this.d = kj.a.FAILED;
        } finally {
            this.a.unlock();
        }
    }

    public final kj.a f() {
        kj.a aVar;
        this.a.lock();
        try {
            if (!this.e || this.d != kj.a.STARTING) {
                aVar = this.d;
            } else {
                aVar = kj.a.STOPPING;
            }
            return aVar;
        } finally {
            this.a.unlock();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }

    class a extends kf {
        private a() {
        }

        /* synthetic */ a(kg kgVar, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public kj.a get(long j, TimeUnit timeUnit) {
            try {
                return (kj.a) super.get(j, timeUnit);
            } catch (TimeoutException unused) {
                throw new TimeoutException(kg.this.toString());
            }
        }
    }
}
