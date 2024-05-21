package com.tapjoy.internal;

import com.tapjoy.internal.kj;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public abstract class ke implements kj {
    /* access modifiers changed from: private */
    public static final Logger a = Logger.getLogger(ke.class.getName());
    private final kj b = new kg() {
        /* access modifiers changed from: protected */
        public final void a() {
            new Executor() {
                public final void execute(Runnable runnable) {
                    new Thread(runnable, ke.this.getClass().getSimpleName()).start();
                }
            }.execute(new Runnable() {
                public final void run() {
                    try {
                        ke.this.b();
                        AnonymousClass1.this.c();
                        if (AnonymousClass1.this.f() == kj.a.RUNNING) {
                            ke.this.d();
                        }
                        ke.this.c();
                        AnonymousClass1.this.d();
                    } catch (Throwable th) {
                        AnonymousClass1.this.a(th);
                        throw jv.a(th);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public final void b() {
            ke.this.a();
        }
    };

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public abstract void d();

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }

    public final ki e() {
        return this.b.e();
    }

    public final kj.a f() {
        return this.b.f();
    }
}
