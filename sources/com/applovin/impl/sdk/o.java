package com.applovin.impl.sdk;

import com.applovin.impl.sdk.b.b;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class o {
    /* access modifiers changed from: private */
    public final j a;
    /* access modifiers changed from: private */
    public final AtomicBoolean b = new AtomicBoolean();
    private final List<a> c = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public long d;
    private final Object e = new Object();
    /* access modifiers changed from: private */
    public final AtomicBoolean f = new AtomicBoolean();
    /* access modifiers changed from: private */
    public long g;

    interface a {
        void h();

        void i();
    }

    o(j jVar) {
        this.a = jVar;
    }

    public void a(a aVar) {
        this.c.add(aVar);
    }

    public void a(boolean z) {
        synchronized (this.e) {
            this.f.set(z);
            if (z) {
                this.g = System.currentTimeMillis();
                p v = this.a.v();
                v.a("FullScreenAdTracker", "Setting fullscreen ad pending display: " + this.g);
                final long longValue = ((Long) this.a.a(b.cI)).longValue();
                if (longValue >= 0) {
                    AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                        public void run() {
                            if (o.this.a() && System.currentTimeMillis() - o.this.g >= longValue) {
                                o.this.a.v().a("FullScreenAdTracker", "Resetting \"pending display\" state...");
                                o.this.f.set(false);
                            }
                        }
                    }, longValue);
                }
            } else {
                this.g = 0;
                p v2 = this.a.v();
                v2.a("FullScreenAdTracker", "Setting fullscreen ad not pending display: " + System.currentTimeMillis());
            }
        }
    }

    public boolean a() {
        return this.f.get();
    }

    public void b(a aVar) {
        this.c.remove(aVar);
    }

    public boolean b() {
        return this.b.get();
    }

    public void c() {
        if (this.b.compareAndSet(false, true)) {
            this.d = System.currentTimeMillis();
            p v = this.a.v();
            v.a("FullScreenAdTracker", "Setting fullscreen ad displayed: " + this.d);
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ((a) it.next()).h();
            }
            final long longValue = ((Long) this.a.a(b.cJ)).longValue();
            if (longValue >= 0) {
                AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                    public void run() {
                        if (o.this.b.get() && System.currentTimeMillis() - o.this.d >= longValue) {
                            o.this.a.v().a("FullScreenAdTracker", "Resetting \"display\" state...");
                            o.this.d();
                        }
                    }
                }, longValue);
            }
        }
    }

    public void d() {
        if (this.b.compareAndSet(true, false)) {
            p v = this.a.v();
            v.a("FullScreenAdTracker", "Setting fullscreen ad hidden: " + System.currentTimeMillis());
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ((a) it.next()).i();
            }
        }
    }
}
