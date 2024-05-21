package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class i {
    private static final i a = new i();
    /* access modifiers changed from: private */
    public final Map<j, String> b = new WeakHashMap();
    /* access modifiers changed from: private */
    public final Map<b, String> c = new WeakHashMap();
    private final ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
    /* access modifiers changed from: private */
    public ScheduledFuture<?> e;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> f;

    private i() {
    }

    static i a() {
        return a;
    }

    private void a(final Context context) {
        if (this.f == null || this.f.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (i.this.b.isEmpty()) {
                            i.this.f.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    private void b(final Context context) {
        if (this.e == null || this.e.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.e = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.c.isEmpty()) {
                            p.a(3, "JSUpdateLooper", (Object) i.this, "No more active trackers");
                            i.this.e.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, (long) w.a().d, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "addActiveTracker" + bVar.hashCode());
            if (this.c != null && !this.c.containsKey(bVar)) {
                this.c.put(bVar, "");
                b(context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, j jVar) {
        if (this.b != null && jVar != null) {
            this.b.put(jVar, "");
            a(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "removeActiveTracker" + bVar.hashCode());
            if (this.c != null) {
                this.c.remove(bVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        if (jVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "removeSetupNeededBridge" + jVar.hashCode());
            if (this.b != null) {
                this.b.remove(jVar);
            }
        }
    }
}
