package com.applovin.impl.mediation.ads;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.d.aa;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.e;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.impl.sdk.u;
import com.applovin.impl.sdk.v;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.TimeUnit;

public class MaxAdViewImpl extends com.applovin.impl.mediation.ads.a.a implements e.a, v.a {
    /* access modifiers changed from: private */
    public final Activity a;
    /* access modifiers changed from: private */
    public final MaxAdView b;
    private MaxAd c;
    /* access modifiers changed from: private */
    public final a d;
    /* access modifiers changed from: private */
    public final c e;
    /* access modifiers changed from: private */
    public final e f;
    /* access modifiers changed from: private */
    public final u g;
    /* access modifiers changed from: private */
    public final v h;
    /* access modifiers changed from: private */
    public final Object i = new Object();
    /* access modifiers changed from: private */
    public com.applovin.impl.mediation.a.b j = null;
    private boolean k;
    private boolean l;
    private boolean m = false;

    private class a extends b {
        private a() {
            super();
        }

        public void onAdLoadFailed(String str, int i) {
            h.a(MaxAdViewImpl.this.adListener, str, i, MaxAdViewImpl.this.sdk);
            MaxAdViewImpl.this.a(i);
        }

        public void onAdLoaded(MaxAd maxAd) {
            com.applovin.impl.mediation.a.a aVar;
            if (maxAd instanceof com.applovin.impl.mediation.e) {
                aVar = ((com.applovin.impl.mediation.e) maxAd).a(MaxAdViewImpl.this.a);
            } else if (maxAd instanceof com.applovin.impl.mediation.a.a) {
                aVar = (com.applovin.impl.mediation.a.a) maxAd;
            } else {
                throw new IllegalArgumentException("Unknown type of loaded ad: " + maxAd.getClass().getName());
            }
            if (aVar instanceof com.applovin.impl.mediation.a.b) {
                com.applovin.impl.mediation.a.b bVar = (com.applovin.impl.mediation.a.b) aVar;
                MaxAdViewImpl.this.a(bVar);
                if (bVar.z()) {
                    long A = bVar.A();
                    p v = MaxAdViewImpl.this.sdk.v();
                    String K = MaxAdViewImpl.this.tag;
                    v.a(K, "Scheduling banner ad refresh " + A + " milliseconds from now for '" + MaxAdViewImpl.this.adUnitId + "'...");
                    MaxAdViewImpl.this.f.a(A);
                }
                h.a(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
                return;
            }
            p R = MaxAdViewImpl.this.logger;
            String Q = MaxAdViewImpl.this.tag;
            R.d(Q, "Not a MediatedAdViewAd received: " + maxAd);
            onAdLoadFailed(MaxAdViewImpl.this.adUnitId, -5201);
        }
    }

    private abstract class b implements MaxAdListener, MaxAdViewAdListener {
        private b() {
        }

        public void onAdClicked(MaxAd maxAd) {
            h.d(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }

        public void onAdCollapsed(MaxAd maxAd) {
            h.h(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }

        public void onAdDisplayFailed(MaxAd maxAd, int i) {
            h.a(MaxAdViewImpl.this.adListener, maxAd, i, MaxAdViewImpl.this.sdk);
        }

        public void onAdDisplayed(MaxAd maxAd) {
            h.b(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }

        public void onAdExpanded(MaxAd maxAd) {
            h.g(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }

        public void onAdHidden(MaxAd maxAd) {
            h.c(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }
    }

    private class c extends b {
        private c() {
            super();
        }

        public void onAdLoadFailed(String str, int i) {
            p Y = MaxAdViewImpl.this.logger;
            String X = MaxAdViewImpl.this.tag;
            Y.a(X, "Failed to pre-cache ad for refresh with error code " + i);
            MaxAdViewImpl.this.a(i);
        }

        public void onAdLoaded(MaxAd maxAd) {
            MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Successfully pre-cached ad for refresh");
            MaxAdViewImpl.this.a(maxAd);
        }
    }

    public MaxAdViewImpl(String str, MaxAdView maxAdView, j jVar, Activity activity) {
        super(str, "MaxAdView", jVar);
        if (activity != null) {
            this.a = activity;
            this.b = maxAdView;
            this.d = new a();
            this.e = new c();
            this.f = new e(jVar, this);
            this.g = new u(maxAdView, jVar);
            this.h = new v(maxAdView, jVar, this);
            p pVar = this.logger;
            String str2 = this.tag;
            pVar.a(str2, "Created new MaxAdView (" + this + ")");
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    /* access modifiers changed from: private */
    public void a() {
        com.applovin.impl.mediation.a.b bVar;
        MaxAdView maxAdView = this.b;
        if (maxAdView != null) {
            maxAdView.removeAllViews();
        }
        this.h.a();
        synchronized (this.i) {
            bVar = this.j;
        }
        if (bVar != null) {
            this.sdk.a(this.a).destroyAd(bVar);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.sdk.b((com.applovin.impl.sdk.b.b) com.applovin.impl.sdk.b.a.x).contains(String.valueOf(i2))) {
            p v = this.sdk.v();
            String str = this.tag;
            v.a(str, "Ignoring banner ad refresh for error code '" + i2 + "'...");
            return;
        }
        this.k = true;
        long longValue = ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.w)).longValue();
        if (longValue >= 0) {
            p v2 = this.sdk.v();
            String str2 = this.tag;
            v2.a(str2, "Scheduling failed banner ad refresh " + longValue + " milliseconds from now for '" + this.adUnitId + "'...");
            this.f.a(longValue);
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        if (n.a(j2, ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.H)).longValue())) {
            p pVar = this.logger;
            String str = this.tag;
            pVar.a(str, "Undesired flags matched - current: " + Long.toBinaryString(j2) + ", undesired: " + Long.toBinaryString(j2));
            this.logger.a(this.tag, "Waiting for refresh timer to manually fire request");
            this.k = true;
            return;
        }
        this.logger.a(this.tag, "No undesired viewability flags matched - scheduling viewability");
        this.k = false;
        b();
    }

    /* access modifiers changed from: private */
    public void a(AnimatorListenerAdapter animatorListenerAdapter) {
        if (this.j != null) {
            View j2 = this.j.j();
            j2.animate().alpha(0.0f).setDuration(((Long) this.sdk.a(com.applovin.impl.sdk.b.a.C)).longValue()).setListener(animatorListenerAdapter).start();
            return;
        }
        animatorListenerAdapter.onAnimationEnd((Animator) null);
    }

    private static void a(View view, com.applovin.impl.mediation.a.b bVar) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int i2 = -1;
        int applyDimension = bVar.h() == -1 ? -1 : (int) TypedValue.applyDimension(1, (float) bVar.h(), displayMetrics);
        if (bVar.i() != -1) {
            i2 = (int) TypedValue.applyDimension(1, (float) bVar.i(), displayMetrics);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = applyDimension;
        layoutParams.height = i2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void a(final com.applovin.impl.mediation.a.b bVar) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                String str;
                String str2;
                p pVar;
                if (bVar.j() != null) {
                    final MaxAdView l = MaxAdViewImpl.this.b;
                    if (l != null) {
                        MaxAdViewImpl.this.a((AnimatorListenerAdapter) new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                MaxAdViewImpl.this.a();
                                if (bVar.p()) {
                                    MaxAdViewImpl.this.h.a((Context) MaxAdViewImpl.this.a, bVar);
                                }
                                MaxAdViewImpl.this.a(bVar, l);
                                synchronized (MaxAdViewImpl.this.i) {
                                    com.applovin.impl.mediation.a.b unused = MaxAdViewImpl.this.j = bVar;
                                }
                                MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Scheduling impression for ad manually...");
                                MaxAdViewImpl.this.sdk.a(MaxAdViewImpl.this.a).maybeScheduleRawAdImpressionPostback(bVar);
                                AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                                    public void run() {
                                        long a2 = MaxAdViewImpl.this.g.a(bVar);
                                        if (!bVar.p()) {
                                            MaxAdViewImpl.this.a(bVar, a2);
                                        }
                                        MaxAdViewImpl.this.a(a2);
                                    }
                                }, bVar.k());
                            }
                        });
                        return;
                    }
                    pVar = MaxAdViewImpl.this.logger;
                    str2 = MaxAdViewImpl.this.tag;
                    str = "Max ad view does not have a parent View";
                } else {
                    pVar = MaxAdViewImpl.this.logger;
                    str2 = MaxAdViewImpl.this.tag;
                    str = "Max ad does not have a loaded ad view";
                }
                pVar.d(str2, str);
                MaxAdViewImpl.this.d.onAdDisplayFailed(bVar, -5201);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.a.b bVar, long j2) {
        this.logger.a(this.tag, "Scheduling viewability impression for ad...");
        this.sdk.a(this.a).maybeScheduleViewabilityAdImpressionPostback(bVar, j2);
    }

    /* access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.a.b bVar, MaxAdView maxAdView) {
        View j2 = bVar.j();
        j2.setAlpha(0.0f);
        a(j2, bVar);
        maxAdView.setBackgroundColor(0);
        maxAdView.addView(j2);
        j2.animate().alpha(1.0f).setDuration(((Long) this.sdk.a(com.applovin.impl.sdk.b.a.B)).longValue()).start();
    }

    /* access modifiers changed from: private */
    public void a(MaxAd maxAd) {
        if (this.l) {
            this.l = false;
            p pVar = this.logger;
            String str = this.tag;
            pVar.a(str, "Rendering precache request ad: " + maxAd.getAdUnitId() + "...");
            this.d.onAdLoaded(maxAd);
            return;
        }
        this.c = maxAd;
    }

    /* access modifiers changed from: private */
    public void a(final MaxAdListener maxAdListener) {
        if (d()) {
            this.logger.e(this.tag, "Unable to load new ad; ad is already destroyed");
            h.a(this.adListener, this.adUnitId, -1, this.sdk);
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (MaxAdViewImpl.this.j != null) {
                    MaxAdViewImpl.this.loadRequestBuilder.a("visible_ad_ad_unit_id", MaxAdViewImpl.this.j.getAdUnitId()).a("viewability_flags", String.valueOf(MaxAdViewImpl.this.g.a(MaxAdViewImpl.this.j)));
                } else {
                    MaxAdViewImpl.this.loadRequestBuilder.a("visible_ad_ad_unit_id").a("viewability_flags");
                }
                p g = MaxAdViewImpl.this.logger;
                String e = MaxAdViewImpl.this.tag;
                g.a(e, "Loading banner ad for '" + MaxAdViewImpl.this.adUnitId + "' and notifying " + maxAdListener + "...");
                MaxAdViewImpl.this.sdk.a(MaxAdViewImpl.this.a).loadAd(MaxAdViewImpl.this.adUnitId, MaxAdFormat.BANNER, MaxAdViewImpl.this.loadRequestBuilder.a(), MaxAdViewImpl.this.a, maxAdListener);
            }
        });
    }

    private void b() {
        if (c()) {
            long longValue = ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.I)).longValue();
            p pVar = this.logger;
            String str = this.tag;
            pVar.a(str, "Scheduling refresh precache request in " + TimeUnit.MICROSECONDS.toSeconds(longValue) + " seconds...");
            this.sdk.D().a((com.applovin.impl.sdk.d.a) new aa(this.sdk, new Runnable() {
                public void run() {
                    MaxAdViewImpl.this.a((MaxAdListener) MaxAdViewImpl.this.e);
                }
            }), com.applovin.impl.mediation.c.c.a(MaxAdFormat.BANNER, q.a.MEDIATION_MAIN, this.sdk), longValue);
        }
    }

    private boolean c() {
        return ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.I)).longValue() > 0;
    }

    private boolean d() {
        boolean z;
        synchronized (this.i) {
            z = this.m;
        }
        return z;
    }

    public void destroy() {
        a();
        synchronized (this.i) {
            this.m = true;
        }
        this.f.e();
    }

    public void loadAd() {
        p pVar = this.logger;
        String str = this.tag;
        pVar.a(str, "" + this + " Loading ad for " + this.adUnitId + "...");
        if (d()) {
            this.logger.e(this.tag, "Unable to load new ad; ad is already destroyed");
            h.a(this.adListener, this.adUnitId, -1, this.sdk);
        } else if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.a.J)).booleanValue() || !this.f.a()) {
            a((MaxAdListener) this.d);
        } else {
            p pVar2 = this.logger;
            String str2 = this.tag;
            pVar2.e(str2, "Unable to load a new ad. An ad refresh has already been scheduled in " + TimeUnit.MILLISECONDS.toSeconds(this.f.d()) + " seconds.");
        }
    }

    public void onAdRefresh() {
        String str;
        String str2;
        p pVar;
        this.l = false;
        if (this.c != null) {
            p pVar2 = this.logger;
            String str3 = this.tag;
            pVar2.a(str3, "Refreshing for cached ad: " + this.c.getAdUnitId() + "...");
            this.d.onAdLoaded(this.c);
            this.c = null;
            return;
        }
        if (!c()) {
            pVar = this.logger;
            str2 = this.tag;
            str = "Refreshing ad from network...";
        } else if (this.k) {
            pVar = this.logger;
            str2 = this.tag;
            str = "Refreshing ad from network due to viewability requirements not met for refresh request...";
        } else {
            this.logger.d(this.tag, "Ignoring attempt to refresh ad - either still waiting for precache or did not attempt request due to visibility requirement not met");
            this.l = true;
            return;
        }
        pVar.a(str2, str);
        loadAd();
    }

    public void onLogVisibilityImpression() {
        a(this.j, this.g.a(this.j));
    }

    public void startAutoRefresh() {
        this.f.g();
        p pVar = this.logger;
        String str = this.tag;
        pVar.a(str, "Resumed autorefresh with remaining time: " + this.f.d());
    }

    public void stopAutoRefresh() {
        p pVar = this.logger;
        String str = this.tag;
        pVar.a(str, "Pausing autorefresh with remaining time: " + this.f.d());
        this.f.f();
    }

    public String toString() {
        return "MaxAdView{adUnitId='" + this.adUnitId + '\'' + ", adListener=" + this.adListener + ", isDestroyed=" + d() + '}';
    }
}
