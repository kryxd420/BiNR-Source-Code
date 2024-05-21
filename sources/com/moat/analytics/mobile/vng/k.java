package com.moat.analytics.mobile.vng;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.moat.analytics.mobile.vng.g;
import com.moat.analytics.mobile.vng.w;
import java.lang.ref.WeakReference;

class k extends MoatAnalytics implements w.b {
    boolean a = false;
    boolean b = false;
    boolean c = false;
    @Nullable
    g d;
    WeakReference<Context> e;
    private boolean f = false;
    private String g;

    k() {
    }

    private void a(MoatOptions moatOptions, Application application) {
        if (this.f) {
            p.a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        w.a().b();
        if (moatOptions.loggingEnabled && a(application.getApplicationContext())) {
            this.a = true;
        }
        this.c = moatOptions.disableLocationServices;
        if (application == null) {
            p.a("[ERROR] ", "Moat Analytics SDK didn't start, application was null");
            return;
        }
        this.e = new WeakReference<>(application.getApplicationContext());
        this.f = true;
        this.b = moatOptions.autoTrackGMAInterstitials;
        a.a(application);
        w.a().a((w.b) this);
        if (!moatOptions.disableAdIdCollection) {
            s.a((Context) application);
        }
        p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.2.0 started");
    }

    private static boolean a(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private void d() {
        if (this.d == null) {
            this.d = new g(a.a(), g.a.DISPLAY);
            this.d.a(this.g);
            p.a(3, "Analytics", (Object) this, "Preparing native display tracking with partner code " + this.g);
            p.a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.g);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.f;
    }

    public void b() {
        o.a();
        if (this.g != null) {
            try {
                d();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    public void c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.g = str;
        if (w.a().a != w.d.OFF) {
            try {
                d();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            a(moatOptions, application);
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
