package com.applovin.impl.sdk;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.n;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;

public class v {
    private final j a;
    private final Object b = new Object();
    private final Rect c = new Rect();
    private final Handler d;
    private final Runnable e;
    private final ViewTreeObserver.OnPreDrawListener f;
    private WeakReference<ViewTreeObserver> g;
    /* access modifiers changed from: private */
    public View h;
    private int i;
    private long j;
    private long k = Long.MIN_VALUE;

    public interface a {
        void onLogVisibilityImpression();
    }

    public v(final MaxAdView maxAdView, j jVar, final a aVar) {
        this.a = jVar;
        this.d = new Handler(Looper.getMainLooper());
        this.e = new Runnable() {
            public void run() {
                if (v.this.h != null) {
                    if (v.this.b(maxAdView, v.this.h)) {
                        v.this.a();
                        aVar.onLogVisibilityImpression();
                        return;
                    }
                    v.this.b();
                }
            }
        };
        this.f = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                v.this.b();
                return true;
            }
        };
    }

    private void a(Context context, View view) {
        View a2 = n.a(context, view);
        if (a2 == null) {
            this.a.v().a("VisibilityTracker", "Unable to set view tree observer due to no root view.");
            return;
        }
        ViewTreeObserver viewTreeObserver = a2.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            this.a.v().c("VisibilityTracker", "Unable to set view tree observer since the view tree observer is not alive.");
            return;
        }
        this.g = new WeakReference<>(viewTreeObserver);
        viewTreeObserver.addOnPreDrawListener(this.f);
    }

    private boolean a(View view, View view2) {
        return view2 != null && view2.getVisibility() == 0 && view.getParent() != null && view2.getWidth() > 0 && view2.getHeight() > 0 && view2.getGlobalVisibleRect(this.c) && ((long) (AppLovinSdkUtils.pxToDp(view2.getContext(), this.c.width()) * AppLovinSdkUtils.pxToDp(view2.getContext(), this.c.height()))) >= ((long) this.i);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.d.postDelayed(this.e, ((Long) this.a.a(b.cr)).longValue());
    }

    /* access modifiers changed from: private */
    public boolean b(View view, View view2) {
        if (!a(view, view2)) {
            return false;
        }
        if (this.k == Long.MIN_VALUE) {
            this.k = SystemClock.uptimeMillis();
        }
        return SystemClock.uptimeMillis() - this.k >= this.j;
    }

    public void a() {
        synchronized (this.b) {
            this.d.removeMessages(0);
            if (this.g != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.g.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this.f);
                }
                this.g.clear();
            }
            this.k = Long.MIN_VALUE;
            this.h = null;
        }
    }

    public void a(Context context, com.applovin.impl.mediation.a.b bVar) {
        synchronized (this.b) {
            a();
            this.h = bVar.j();
            this.i = bVar.o();
            this.j = bVar.q();
            a(context, this.h);
        }
    }
}
