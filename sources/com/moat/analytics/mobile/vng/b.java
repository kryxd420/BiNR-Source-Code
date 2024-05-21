package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.a.a.a;
import com.moat.analytics.mobile.vng.j;
import java.lang.ref.WeakReference;

abstract class b {
    j a;
    final String b;
    final boolean c;
    boolean d;
    boolean e;
    private WeakReference<View> f;
    private WeakReference<WebView> g;
    private final z h;
    private final boolean i;

    b(@Nullable View view, boolean z, boolean z2) {
        String str;
        p.a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.b = str;
        this.f = new WeakReference<>(view);
        this.i = z;
        this.c = z2;
        this.d = false;
        this.e = false;
        this.h = new z();
    }

    private void g() {
        a.a(this.g);
        p.a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.g.get() != null) {
            if (!this.i && !this.c) {
                this.a = new j((WebView) this.g.get(), j.a.WEBVIEW);
            }
            boolean z = this.a.a;
            StringBuilder sb = new StringBuilder();
            sb.append("Bridge ");
            sb.append(z ? "" : "not ");
            sb.append("installed.");
            p.a(3, "BaseTracker", (Object) this, sb.toString());
            return;
        }
        this.a = null;
        p.a(3, "BaseTracker", (Object) this, "Bridge not installed, WebView is null.");
    }

    /* access modifiers changed from: package-private */
    public abstract String a();

    /* access modifiers changed from: package-private */
    public void a(WebView webView) {
        if (webView != null) {
            this.g = new WeakReference<>(webView);
            if (this.a == null) {
                g();
            }
            if (this.a != null && this.a.a) {
                this.a.a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.a = jVar;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        p.a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        if (!this.e) {
            boolean b2 = this.a.b(this);
            StringBuilder sb = new StringBuilder();
            sb.append("Impression ");
            sb.append(b2 ? "" : "not ");
            sb.append("started.");
            p.a(3, "BaseTracker", (Object) this, sb.toString());
            if (!b2) {
                return b2;
            }
            this.d = true;
            this.e = true;
            return b2;
        }
        p.a(3, "BaseTracker", (Object) this, "startTracking failed, tracker already started");
        p.a("[INFO] ", a() + " already started");
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        p.a(3, "BaseTracker", (Object) this, "Attempting to stop impression.");
        this.d = false;
        boolean c2 = this.a.c(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Impression tracking ");
        sb.append(c2 ? "" : "not ");
        sb.append("stopped.");
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        return c2;
    }

    @CallSuper
    public void changeTargetView(View view) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("changing view to ");
        if (view != null) {
            str = view.getClass().getSimpleName() + "@" + view.hashCode();
        } else {
            str = "null";
        }
        sb.append(str);
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        this.f = new WeakReference<>(view);
    }

    /* access modifiers changed from: package-private */
    public View d() {
        return (View) this.f.get();
    }

    /* access modifiers changed from: package-private */
    public String e() {
        if (d() == null) {
            return "";
        }
        return d().getClass().getSimpleName() + "@" + d().hashCode();
    }

    /* access modifiers changed from: package-private */
    public String f() {
        this.h.a(this.b, d());
        return this.h.a;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void startTracking() {
        boolean z;
        try {
            p.a(3, "BaseTracker", (Object) this, "In startTracking method.");
            z = b();
        } catch (Exception e2) {
            m.a(e2);
            z = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to start tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" startTracking ");
        sb2.append(z ? "succeeded" : "failed");
        sb2.append(" for ");
        sb2.append(e());
        p.a(str, sb2.toString());
    }

    public void stopTracking() {
        boolean z;
        try {
            p.a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            z = c();
        } catch (Exception e2) {
            m.a(e2);
            z = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" stopTracking ");
        sb2.append(z ? "succeeded" : "failed");
        sb2.append(" for ");
        sb2.append(e());
        p.a(str, sb2.toString());
    }
}
