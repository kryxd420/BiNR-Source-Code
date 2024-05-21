package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.e.e;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class t {
    private static final String[] a = {TJAdUnitConstants.String.VIDEO_PAUSED, "saved_instance_state"};
    private static final String[] b = {"saved_instance_state", TJAdUnitConstants.String.VIDEO_PAUSED};
    private static final String[] c = {TJAdUnitConstants.String.VIDEO_PAUSED, TJAdUnitConstants.String.VIDEO_STOPPED};
    private static final String[] d = {TJAdUnitConstants.String.VIDEO_PAUSED, "saved_instance_state", TJAdUnitConstants.String.VIDEO_STOPPED, "started"};
    private static final String[] e = {TJAdUnitConstants.String.VIDEO_PAUSED, TJAdUnitConstants.String.VIDEO_STOPPED, "saved_instance_state", "started"};
    private static final String[] f = {"saved_instance_state", TJAdUnitConstants.String.VIDEO_PAUSED, TJAdUnitConstants.String.VIDEO_STOPPED, "started"};
    private final j g;
    private final List<String> h = new ArrayList();
    private final AtomicBoolean i = new AtomicBoolean();
    private final AtomicBoolean j = new AtomicBoolean();
    private final AtomicBoolean k = new AtomicBoolean();
    private volatile boolean l;
    private Date m;
    private Date n;
    private final List<a> o = new ArrayList();
    private final Object p = new Object();

    public interface a {
        void b();

        void c();
    }

    t(j jVar) {
        this.g = jVar;
    }

    private void a(boolean z) {
        this.l = true;
        l();
        if (!z && ((Boolean) this.g.a(b.ec)).booleanValue()) {
            boolean booleanValue = ((Boolean) this.g.a(b.dZ)).booleanValue();
            long millis = TimeUnit.MINUTES.toMillis(((Long) this.g.a(b.eb)).longValue());
            if (this.m == null || System.currentTimeMillis() - this.m.getTime() >= millis) {
                ((EventServiceImpl) this.g.q()).a(TJAdUnitConstants.String.VIDEO_PAUSED, false);
                if (booleanValue) {
                    this.m = new Date();
                }
            }
            if (!booleanValue) {
                this.m = new Date();
            }
        }
    }

    private static boolean a(List<String> list, String[] strArr) {
        int size = list.size();
        int length = strArr.length;
        if (size == 0 || length == 0 || size < strArr.length) {
            return false;
        }
        int i2 = size - length;
        for (int i3 = i2; i3 < length; i3++) {
            if (!list.get(i3).equals(strArr[i3 - i2])) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void f() {
        this.h.add(TJAdUnitConstants.String.VIDEO_PAUSED);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (a(this.h, c)) {
            a(this.k.get());
        }
        this.h.add("saved_instance_state");
    }

    /* access modifiers changed from: private */
    public void h() {
        if (a(this.h, a) || a(this.h, b)) {
            a(this.k.get());
        }
        this.h.add(TJAdUnitConstants.String.VIDEO_STOPPED);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (!this.h.isEmpty()) {
            String str = this.h.get(this.h.size() - 1);
            if (TJAdUnitConstants.String.VIDEO_STOPPED.equals(str) || "saved_instance_state".equals(str)) {
                this.h.add("started");
            } else {
                this.h.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (a(this.h, d) || a(this.h, e) || a(this.h, f)) {
            boolean booleanValue = ((Boolean) this.g.a(b.dZ)).booleanValue();
            long longValue = ((Long) this.g.a(b.ea)).longValue();
            this.l = false;
            m();
            if (!this.k.getAndSet(false)) {
                long millis = TimeUnit.MINUTES.toMillis(longValue);
                if (this.n == null || System.currentTimeMillis() - this.n.getTime() >= millis) {
                    ((EventServiceImpl) this.g.q()).a("resumed", false);
                    if (booleanValue) {
                        this.n = new Date();
                    }
                }
                if (!booleanValue) {
                    this.n = new Date();
                }
                this.g.E().a(g.n);
                this.j.set(true);
            } else {
                return;
            }
        }
        this.h.clear();
    }

    /* access modifiers changed from: private */
    public void k() {
        this.h.clear();
    }

    private void l() {
        ArrayList<a> arrayList;
        synchronized (this.p) {
            arrayList = new ArrayList<>(this.o);
        }
        for (a b2 : arrayList) {
            b2.b();
        }
    }

    private void m() {
        ArrayList<a> arrayList;
        synchronized (this.p) {
            arrayList = new ArrayList<>(this.o);
        }
        for (a c2 : arrayList) {
            c2.c();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        if (context != null && e.b() && ((Boolean) this.g.a(b.dY)).booleanValue() && !this.i.getAndSet(true)) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            ((Application) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    t.this.k();
                }

                public void onActivityDestroyed(Activity activity) {
                    t.this.k();
                }

                public void onActivityPaused(Activity activity) {
                    t.this.f();
                }

                public void onActivityResumed(Activity activity) {
                    t.this.j();
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    t.this.g();
                }

                public void onActivityStarted(Activity activity) {
                    t.this.i();
                }

                public void onActivityStopped(Activity activity) {
                    t.this.h();
                }
            });
        }
    }

    public void a(a aVar) {
        synchronized (this.p) {
            this.o.add(aVar);
        }
    }

    public boolean a() {
        return this.l;
    }

    public void b() {
        this.k.set(true);
    }

    public void b(a aVar) {
        synchronized (this.p) {
            this.o.remove(aVar);
        }
    }

    public void c() {
        this.k.set(false);
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.i.get();
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.j.getAndSet(false);
    }
}
