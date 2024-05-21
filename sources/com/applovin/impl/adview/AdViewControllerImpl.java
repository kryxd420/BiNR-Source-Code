package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.ad.i;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.atomic.AtomicReference;

public class AdViewControllerImpl implements AdViewController {
    private volatile AppLovinAdClickListener A;
    /* access modifiers changed from: private */
    public Context a;
    /* access modifiers changed from: private */
    public ViewGroup b;
    /* access modifiers changed from: private */
    public j c;
    /* access modifiers changed from: private */
    public AppLovinAdServiceImpl d;
    /* access modifiers changed from: private */
    public p e;
    /* access modifiers changed from: private */
    public AppLovinAdSize f;
    private String g;
    /* access modifiers changed from: private */
    public com.applovin.impl.sdk.c.d h;
    private d i;
    private d j;
    /* access modifiers changed from: private */
    public c k;
    private AppLovinAd l;
    private Runnable m;
    private Runnable n;
    /* access modifiers changed from: private */
    public volatile AppLovinAd o = null;
    private volatile AppLovinAd p = null;
    /* access modifiers changed from: private */
    public i q = null;
    /* access modifiers changed from: private */
    public i r = null;
    private final AtomicReference<AppLovinAd> s = new AtomicReference<>();
    private volatile boolean t = false;
    private volatile boolean u = true;
    /* access modifiers changed from: private */
    public volatile boolean v = false;
    private volatile boolean w = false;
    /* access modifiers changed from: private */
    public volatile AppLovinAdLoadListener x;
    private volatile AppLovinAdDisplayListener y;
    /* access modifiers changed from: private */
    public volatile AppLovinAdViewEventListener z;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            if (AdViewControllerImpl.this.k != null) {
                AdViewControllerImpl.this.k.setVisibility(8);
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        public void run() {
            if (AdViewControllerImpl.this.k != null) {
                try {
                    AdViewControllerImpl.this.k.loadDataWithBaseURL("/", "<html></html>", "text/html", (String) null, "");
                } catch (Exception unused) {
                }
            }
        }
    }

    private class c implements Runnable {
        private c() {
        }

        public void run() {
            if (AdViewControllerImpl.this.o == null) {
                return;
            }
            if (AdViewControllerImpl.this.k != null) {
                p b = AdViewControllerImpl.this.e;
                b.a("AppLovinAdView", "Rendering advertisement ad for #" + AdViewControllerImpl.this.o.getAdIdNumber() + "...");
                AdViewControllerImpl.b((View) AdViewControllerImpl.this.k, AdViewControllerImpl.this.o.getSize());
                AdViewControllerImpl.this.k.a(AdViewControllerImpl.this.o);
                if (AdViewControllerImpl.this.o.getSize() != AppLovinAdSize.INTERSTITIAL && !AdViewControllerImpl.this.v && !(AdViewControllerImpl.this.o instanceof i)) {
                    com.applovin.impl.sdk.c.d unused = AdViewControllerImpl.this.h = new com.applovin.impl.sdk.c.d(AdViewControllerImpl.this.o, AdViewControllerImpl.this.c);
                    AdViewControllerImpl.this.h.a();
                    AdViewControllerImpl.this.k.a(AdViewControllerImpl.this.h);
                    if (AdViewControllerImpl.this.o instanceof g) {
                        ((g) AdViewControllerImpl.this.o).setHasShown(true);
                    }
                }
                if (AdViewControllerImpl.this.k.b() != null && (AdViewControllerImpl.this.o instanceof g)) {
                    AdViewControllerImpl.this.k.b().a(((g) AdViewControllerImpl.this.o).s() ? 0 : 1);
                    return;
                }
                return;
            }
            p v = AdViewControllerImpl.this.c.v();
            v.e("AppLovinAdView", "Unable to render advertisement for ad #" + AdViewControllerImpl.this.o.getAdIdNumber() + ". Please make sure you are not calling AppLovinAdView.destroy() prematurely.");
        }
    }

    static class d implements AppLovinAdLoadListener, AppLovinAdUpdateListener {
        private final AppLovinAdService a;
        private final p b;
        private final AdViewControllerImpl c;

        d(AdViewControllerImpl adViewControllerImpl, j jVar) {
            if (adViewControllerImpl == null) {
                throw new IllegalArgumentException("No view specified");
            } else if (jVar != null) {
                this.b = jVar.v();
                this.a = jVar.o();
                this.c = adViewControllerImpl;
            } else {
                throw new IllegalArgumentException("No sdk specified");
            }
        }

        private AdViewControllerImpl a() {
            return this.c;
        }

        public void adReceived(AppLovinAd appLovinAd) {
            AdViewControllerImpl a2 = a();
            if (a2 != null) {
                a2.a(appLovinAd);
            } else {
                this.b.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was received");
            }
        }

        public void adUpdated(AppLovinAd appLovinAd) {
            AdViewControllerImpl a2 = a();
            if (a2 != null) {
                a2.a(appLovinAd);
                return;
            }
            this.a.removeAdUpdateListener(this, appLovinAd.getSize());
            this.b.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
        }

        public void failedToReceiveAd(int i) {
            AdViewControllerImpl a2 = a();
            if (a2 != null) {
                a2.a(i);
            }
        }
    }

    private void a(AppLovinAdView appLovinAdView, j jVar, AppLovinAdSize appLovinAdSize, String str, Context context) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (jVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (appLovinAdSize != null) {
            this.c = jVar;
            this.d = jVar.o();
            this.e = jVar.v();
            this.f = appLovinAdSize;
            this.g = str;
            this.a = context;
            this.b = appLovinAdView;
            this.l = new i();
            this.i = new d(this, jVar);
            this.n = new a();
            this.m = new c();
            this.j = new d(this, jVar);
            a(appLovinAdSize);
        } else {
            throw new IllegalArgumentException("No ad size specified");
        }
    }

    /* access modifiers changed from: private */
    public void a(AppLovinAdSize appLovinAdSize) {
        b bVar;
        try {
            this.k = new c(this.i, this.c, this.a);
            this.k.setBackgroundColor(0);
            this.k.setWillNotCacheDrawing(false);
            this.b.setBackgroundColor(0);
            this.b.addView(this.k);
            b((View) this.k, appLovinAdSize);
            if (this.c.g()) {
                if (!this.t && ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.el)).booleanValue()) {
                    a(this.n);
                }
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.em)).booleanValue()) {
                    bVar = new b();
                }
                this.t = true;
            }
            if (!this.t) {
                a(this.n);
            }
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fn)).booleanValue()) {
                bVar = new b();
            }
            this.t = true;
            a((Runnable) bVar);
            this.t = true;
        } catch (Throwable th) {
            p pVar = this.e;
            pVar.e("AppLovinAdView", "Failed to create AdView: " + th.getMessage());
        }
    }

    private void a(Runnable runnable) {
        AppLovinSdkUtils.runOnUiThread(runnable);
    }

    private void b() {
        if (this.e != null) {
            this.e.a("AppLovinAdView", "Destroying...");
        }
        if (this.d != null) {
            this.d.removeAdUpdateListener(this.j, getSize());
        }
        if (this.k != null) {
            try {
                ViewParent parent = this.k.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.k);
                }
                this.k.removeAllViews();
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fa)).booleanValue()) {
                    this.k.loadUrl("about:blank");
                    this.k.onPause();
                    this.k.destroyDrawingCache();
                }
            } catch (Throwable th) {
                this.e.a("AppLovinAdView", "Unable to destroy ad view", th);
            }
            this.k.destroy();
            this.k = null;
        }
        this.v = true;
    }

    /* access modifiers changed from: private */
    public static void b(View view, AppLovinAdSize appLovinAdSize) {
        if (view != null) {
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            int i2 = -1;
            int applyDimension = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getWidth(), displayMetrics);
            if (!appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
                i2 = appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getHeight(), displayMetrics);
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
    }

    private void c() {
        a((Runnable) new Runnable() {
            public void run() {
                if (AdViewControllerImpl.this.q != null) {
                    p b = AdViewControllerImpl.this.e;
                    b.a("AppLovinAdView", "Detaching expanded ad: " + AdViewControllerImpl.this.q.a());
                    i unused = AdViewControllerImpl.this.r = AdViewControllerImpl.this.q;
                    i unused2 = AdViewControllerImpl.this.q = null;
                    AdViewControllerImpl.this.a(AdViewControllerImpl.this.f);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        a((Runnable) new Runnable() {
            public void run() {
                com.applovin.impl.sdk.ad.a aVar;
                if (AdViewControllerImpl.this.r != null || AdViewControllerImpl.this.q != null) {
                    if (AdViewControllerImpl.this.r != null) {
                        aVar = AdViewControllerImpl.this.r.a();
                        AdViewControllerImpl.this.r.dismiss();
                        i unused = AdViewControllerImpl.this.r = null;
                    } else {
                        aVar = AdViewControllerImpl.this.q.a();
                        AdViewControllerImpl.this.q.dismiss();
                        i unused2 = AdViewControllerImpl.this.q = null;
                    }
                    h.b(AdViewControllerImpl.this.z, (AppLovinAd) aVar, (AppLovinAdView) AdViewControllerImpl.this.b, AdViewControllerImpl.this.c);
                }
            }
        });
    }

    private void e() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.q == null && this.r == null) {
            p pVar = this.e;
            pVar.a("AppLovinAdView", "Ad: " + this.o + " closed.");
            a(this.n);
            h.b(this.y, this.o, this.c);
            this.o = null;
        } else if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cA)).booleanValue()) {
            contractAd();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final int i2) {
        if (!this.v) {
            this.d.addAdUpdateListener(this.j, this.f);
            a(this.n);
        }
        a((Runnable) new Runnable() {
            public void run() {
                try {
                    if (AdViewControllerImpl.this.x != null) {
                        AdViewControllerImpl.this.x.failedToReceiveAd(i2);
                    }
                } catch (Throwable th) {
                    AdViewControllerImpl.this.e.c("AppLovinAdView", "Exception while running app load  callback", th);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.w = true;
            if (!this.v) {
                this.d.addAdUpdateListener(this.j, this.f);
                renderAd(appLovinAd);
            } else {
                this.s.set(appLovinAd);
                this.e.a("AppLovinAdView", "Ad view has paused when an ad was received, ad saved for later");
            }
            a((Runnable) new Runnable() {
                public void run() {
                    try {
                        if (AdViewControllerImpl.this.x != null) {
                            AdViewControllerImpl.this.x.adReceived(appLovinAd);
                        }
                    } catch (Throwable th) {
                        p b2 = AdViewControllerImpl.this.e;
                        b2.e("AppLovinAdView", "Exception while running ad load callback: " + th.getMessage());
                    }
                }
            });
            return;
        }
        this.e.d("AppLovinAdView", "No provided when to the view controller");
        a(-1);
    }

    /* access modifiers changed from: package-private */
    public void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        String str;
        String str2;
        p pVar;
        h.a(this.A, appLovinAd, this.c);
        if (appLovinAdView == null) {
            pVar = this.e;
            str2 = "AppLovinAdView";
            str = "Unable to process ad click - AppLovinAdView destroyed prematurely";
        } else if (appLovinAd instanceof g) {
            this.d.trackAndLaunchClick(appLovinAd, appLovinAdView, this, uri);
            return;
        } else {
            pVar = this.e;
            str2 = "AppLovinAdView";
            str = "Unable to process ad click - EmptyAd is not supported.";
        }
        pVar.d(str2, str);
    }

    public void contractAd() {
        a((Runnable) new Runnable() {
            public void run() {
                AdViewControllerImpl.this.d();
                if (AdViewControllerImpl.this.b != null && AdViewControllerImpl.this.k != null && AdViewControllerImpl.this.k.getParent() == null) {
                    AdViewControllerImpl.this.b.addView(AdViewControllerImpl.this.k);
                    AdViewControllerImpl.b((View) AdViewControllerImpl.this.k, AdViewControllerImpl.this.o.getSize());
                }
            }
        });
    }

    public void destroy() {
        if (!(this.k == null || this.q == null)) {
            contractAd();
        }
        b();
    }

    public void dismissInterstitialIfRequired() {
        if ((this.a instanceof AppLovinInterstitialActivity) && (this.o instanceof g)) {
            boolean z2 = ((g) this.o).F() == g.a.DISMISS;
            AppLovinInterstitialActivity appLovinInterstitialActivity = (AppLovinInterstitialActivity) this.a;
            if (z2 && appLovinInterstitialActivity.getPoststitialWasDisplayed()) {
                appLovinInterstitialActivity.dismiss();
            }
        }
    }

    public void expandAd() {
        a((Runnable) new Runnable() {
            public void run() {
                if (AdViewControllerImpl.this.q == null && (AdViewControllerImpl.this.o instanceof com.applovin.impl.sdk.ad.a) && AdViewControllerImpl.this.k != null) {
                    com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) AdViewControllerImpl.this.o;
                    Activity a2 = AdViewControllerImpl.this.a instanceof Activity ? (Activity) AdViewControllerImpl.this.a : n.a((View) AdViewControllerImpl.this.k, AdViewControllerImpl.this.c);
                    if (a2 != null) {
                        if (AdViewControllerImpl.this.b != null) {
                            AdViewControllerImpl.this.b.removeView(AdViewControllerImpl.this.k);
                        }
                        i unused = AdViewControllerImpl.this.q = new i(aVar, AdViewControllerImpl.this.k, a2, AdViewControllerImpl.this.c);
                        AdViewControllerImpl.this.q.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialogInterface) {
                                AdViewControllerImpl.this.contractAd();
                            }
                        });
                        AdViewControllerImpl.this.q.show();
                        h.a(AdViewControllerImpl.this.z, AdViewControllerImpl.this.o, (AppLovinAdView) AdViewControllerImpl.this.b, AdViewControllerImpl.this.c);
                        if (AdViewControllerImpl.this.h != null) {
                            AdViewControllerImpl.this.h.d();
                            return;
                        }
                        return;
                    }
                    AdViewControllerImpl.this.e.e("AppLovinAdView", "Unable to expand ad. No Activity found.");
                    Uri g = aVar.g();
                    if (g != null && ((Boolean) AdViewControllerImpl.this.c.a(com.applovin.impl.sdk.b.b.cH)).booleanValue()) {
                        AdViewControllerImpl.this.d.trackAndLaunchClick(aVar, AdViewControllerImpl.this.getParentView(), AdViewControllerImpl.this, g);
                        if (AdViewControllerImpl.this.h != null) {
                            AdViewControllerImpl.this.h.b();
                        }
                    }
                    AdViewControllerImpl.this.k.a("javascript:al_onFailedExpand();");
                }
            }
        });
    }

    public AppLovinAdViewEventListener getAdViewEventListener() {
        return this.z;
    }

    public c getAdWebView() {
        return this.k;
    }

    public AppLovinAd getCurrentAd() {
        return this.o;
    }

    public AppLovinAdView getParentView() {
        return (AppLovinAdView) this.b;
    }

    public j getSdk() {
        return this.c;
    }

    public AppLovinAdSize getSize() {
        return this.f;
    }

    public String getZoneId() {
        return this.g;
    }

    public void initializeAdView(AppLovinAdView appLovinAdView, Context context, AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (context == null) {
            Log.e("AppLovinAdView", "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        } else {
            if (appLovinAdSize == null && (appLovinAdSize = com.applovin.impl.sdk.e.b.a(attributeSet)) == null) {
                appLovinAdSize = AppLovinAdSize.BANNER;
            }
            AppLovinAdSize appLovinAdSize2 = appLovinAdSize;
            if (appLovinSdk == null) {
                appLovinSdk = AppLovinSdk.getInstance(context);
            }
            if (appLovinSdk != null && !appLovinSdk.hasCriticalErrors()) {
                a(appLovinAdView, n.a(appLovinSdk), appLovinAdSize2, str, context);
                if (com.applovin.impl.sdk.e.b.b(attributeSet)) {
                    loadNextAd();
                }
            }
        }
    }

    public boolean isAdReadyToDisplay() {
        return !TextUtils.isEmpty(this.g) ? this.d.hasPreloadedAdForZoneId(this.g) : this.d.hasPreloadedAd(this.f);
    }

    public boolean isAutoDestroy() {
        return this.u;
    }

    public void loadNextAd() {
        if (this.c == null || this.j == null || this.a == null || !this.t) {
            Log.i("AppLovinAdView", "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.d.loadNextAd(this.g, this.f, this.j);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.o instanceof g) {
            webView.setVisibility(0);
            try {
                if (this.o != this.p && this.y != null) {
                    this.p = this.o;
                    h.a(this.y, this.o, this.c);
                }
            } catch (Throwable th) {
                this.e.c("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.t) {
            if (this.o != this.l) {
                h.b(this.y, this.o, this.c);
            }
            if (this.k == null || this.q == null) {
                this.e.a("AppLovinAdView", "onDetachedFromWindowCalled without an expanded ad present");
            } else {
                this.e.a("AppLovinAdView", "onDetachedFromWindowCalled with expanded ad present");
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cz)).booleanValue()) {
                    contractAd();
                } else {
                    c();
                }
            }
            if (this.u) {
                b();
            }
        }
    }

    public void onVisibilityChanged(int i2) {
        if (!this.t || !this.u) {
            return;
        }
        if (i2 == 8 || i2 == 4) {
            pause();
        } else if (i2 == 0) {
            resume();
        }
    }

    public void pause() {
        if (this.t) {
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
                this.d.removeAdUpdateListener(this.j, getSize());
            }
            AppLovinAd appLovinAd = this.o;
            renderAd(this.l);
            if (appLovinAd != null) {
                this.s.set(appLovinAd);
            }
            this.v = true;
        }
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, (String) null);
    }

    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (appLovinAd != null) {
            n.c(appLovinAd, this.c);
            if (this.t) {
                AppLovinAd b2 = n.b(appLovinAd, this.c);
                if (b2 != null && b2 != this.o) {
                    p pVar = this.e;
                    pVar.a("AppLovinAdView", "Rendering ad #" + b2.getAdIdNumber() + " (" + b2.getSize() + ")");
                    if (!(this.o instanceof i)) {
                        h.b(this.y, this.o, this.c);
                        if (!(b2 instanceof i) && b2.getSize() != AppLovinAdSize.INTERSTITIAL) {
                            e();
                        }
                    }
                    this.s.set((Object) null);
                    this.p = null;
                    this.o = b2;
                    if ((appLovinAd instanceof g) && !this.v && (this.f == AppLovinAdSize.BANNER || this.f == AppLovinAdSize.MREC || this.f == AppLovinAdSize.LEADER)) {
                        this.c.o().trackImpression((g) appLovinAd);
                    }
                    boolean z2 = b2 instanceof i;
                    if (!z2 && this.q != null) {
                        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cy)).booleanValue()) {
                            d();
                            this.e.a("AppLovinAdView", "Fade out the old ad scheduled");
                        } else {
                            c();
                        }
                    }
                    if (!z2 || (this.q == null && this.r == null)) {
                        a(this.m);
                    } else {
                        this.e.a("AppLovinAdView", "Ignoring empty ad render with expanded ad");
                    }
                } else if (b2 == null) {
                    this.e.c("AppLovinAdView", "Unable to render ad. Ad is null. Internal inconsistency error.");
                } else {
                    p pVar2 = this.e;
                    pVar2.c("AppLovinAdView", "Ad #" + b2.getAdIdNumber() + " is already showing, ignoring");
                }
            } else {
                Log.i("AppLovinAdView", "Unable to render ad: AppLovinAdView is not initialized.");
            }
        } else {
            throw new IllegalArgumentException("No ad specified");
        }
    }

    public void resume() {
        if (this.t) {
            if (this.w && ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
                this.d.addAdUpdateListener(this.j, this.f);
            }
            AppLovinAd andSet = this.s.getAndSet((Object) null);
            if (andSet != null) {
                renderAd(andSet);
            }
            this.v = false;
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.A = appLovinAdClickListener;
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.y = appLovinAdDisplayListener;
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.x = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
    }

    public void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener) {
        this.z = appLovinAdViewEventListener;
    }

    public void setAutoDestroy(boolean z2) {
        this.u = z2;
    }

    public void setStatsManagerHelper(com.applovin.impl.sdk.c.d dVar) {
        if (this.k != null) {
            this.k.a(dVar);
        }
    }
}
