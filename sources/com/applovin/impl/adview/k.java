package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.a.a;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class k implements AppLovinInterstitialAdDialog {
    public static volatile boolean b = false;
    public static volatile boolean c = false;
    private static final Map<String, k> d = Collections.synchronizedMap(new HashMap());
    private static volatile boolean o;
    protected final j a;
    private final String e;
    private final c f;
    private final WeakReference<Context> g;
    /* access modifiers changed from: private */
    public volatile AppLovinAdLoadListener h;
    private volatile AppLovinAdDisplayListener i;
    private volatile AppLovinAdVideoPlaybackListener j;
    private volatile AppLovinAdClickListener k;
    private volatile g l;
    private volatile g.b m;
    /* access modifiers changed from: private */
    public volatile h n;

    k(AppLovinSdk appLovinSdk, Context context) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (context != null) {
            this.a = n.a(appLovinSdk);
            this.e = UUID.randomUUID().toString();
            this.f = new c();
            this.g = new WeakReference<>(context);
            b = true;
            c = false;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    public static k a(String str) {
        return d.get(str);
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (k.this.h != null) {
                    k.this.h.failedToReceiveAd(i2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        Intent intent = new Intent(context, AppLovinInterstitialActivity.class);
        intent.putExtra(j.KEY_WRAPPER_ID, this.e);
        AppLovinInterstitialActivity.lastKnownWrapper = this;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        if (context instanceof Activity) {
            try {
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(0, 0);
            } catch (Throwable th) {
                this.a.v().b("InterstitialAdDialogWrapper", "Unable to remove pending transition animations", th);
            }
        } else {
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        a(true);
    }

    private void a(g gVar, final Context context) {
        d.put(this.e, this);
        this.l = gVar;
        this.m = this.l != null ? this.l.m() : g.b.DEFAULT;
        if (!n.a(gVar, context, this.a)) {
            this.a.E().a(com.applovin.impl.sdk.c.g.o);
            if (this.l instanceof a) {
                com.applovin.impl.a.k c2 = ((a) this.l).c();
                if (c2 != null) {
                    p v = this.a.v();
                    v.d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad. Setting videoUri to source: " + c2.a());
                    c2.a(c2.a());
                } else {
                    this.a.v().d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad and source uri not found. Failing ad show.");
                }
            } else if (this.l instanceof com.applovin.impl.sdk.ad.a) {
                com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) this.l;
                if (!aVar.ai() || !aVar.d()) {
                    p v2 = this.a.v();
                    v2.d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for ad server ad: " + aVar.getAdIdNumber() + " and could not restore video stream url. Failing ad show.");
                } else {
                    p v3 = this.a.v();
                    v3.d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for ad server ad. Restored video uri to video stream url: " + aVar.e());
                }
            }
            a((AppLovinAd) gVar);
            return;
        }
        if (e.a((Class<?>) AppLovinInterstitialActivity.class, context)) {
            long max = Math.max(0, ((Long) this.a.a(b.db)).longValue());
            p v4 = this.a.v();
            v4.a("InterstitialAdDialogWrapper", "Presenting ad with delay of " + max);
            new Handler(context.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    k.this.a(context);
                }
            }, max);
            return;
        }
        this.a.v().e("AppLovinInterstitialAdDialog", "Unable to show ad. Please make sure you have AppLovinInterstitialActivity declared in your Android Manifest: <activity android:name=\"com.applovin.adview.AppLovinInterstitialActivity\" android:configChanges=\"orientation|screenSize\"/>");
        a((AppLovinAd) gVar);
    }

    private void a(AppLovinAd appLovinAd) {
        if (this.i != null) {
            this.i.adHidden(appLovinAd);
        }
        o = false;
    }

    /* access modifiers changed from: private */
    public void b(final AppLovinAd appLovinAd) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (k.this.h != null) {
                    k.this.h.adReceived(appLovinAd);
                }
            }
        });
    }

    private Context h() {
        if (this.g != null) {
            return (Context) this.g.get();
        }
        return null;
    }

    public j a() {
        return this.a;
    }

    public void a(h hVar) {
        this.n = hVar;
    }

    /* access modifiers changed from: protected */
    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.o().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }

    public void a(boolean z) {
        o = z;
    }

    public AppLovinAd b() {
        return this.l;
    }

    public AppLovinAdVideoPlaybackListener c() {
        return this.j;
    }

    public AppLovinAdDisplayListener d() {
        return this.i;
    }

    public void dismiss() {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (!((Boolean) k.this.a.a(b.ff)).booleanValue() || k.this.n != null) {
                    k.this.n.dismiss();
                }
            }
        });
    }

    public AppLovinAdClickListener e() {
        return this.k;
    }

    public g.b f() {
        return this.m;
    }

    public void g() {
        b = false;
        c = true;
        d.remove(this.e);
        if (this.l != null && this.l.P()) {
            this.n = null;
        }
    }

    public boolean isAdReadyToDisplay() {
        return this.a.o().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    public boolean isShowing() {
        return o;
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.k = appLovinAdClickListener;
        this.f.a(appLovinAdClickListener);
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.i = appLovinAdDisplayListener;
        this.f.a(appLovinAdDisplayListener);
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.h = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.j = appLovinAdVideoPlaybackListener;
    }

    public void show() {
        show((String) null);
    }

    public void show(final String str) {
        a((AppLovinAdLoadListener) new AppLovinAdLoadListener() {
            public void adReceived(AppLovinAd appLovinAd) {
                k.this.b(appLovinAd);
                k.this.showAndRender(appLovinAd, str);
            }

            public void failedToReceiveAd(int i) {
                k.this.a(i);
            }
        });
    }

    public void showAndRender(AppLovinAd appLovinAd) {
        showAndRender(appLovinAd, (String) null);
    }

    public void showAndRender(AppLovinAd appLovinAd, String str) {
        p pVar;
        String str2;
        String str3;
        if (isShowing() && !((Boolean) this.a.a(b.fe)).booleanValue()) {
            this.a.v().e("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
        } else if (!n.a(appLovinAd, this.a)) {
            a(appLovinAd);
        } else {
            Context h2 = h();
            if (h2 != null) {
                AppLovinAd b2 = n.b(appLovinAd, this.a);
                if (b2 == null) {
                    pVar = this.a.v();
                    str3 = "InterstitialAdDialogWrapper";
                    str2 = "Failed to show ad: " + appLovinAd;
                } else if (b2 instanceof g) {
                    a((g) b2, h2);
                    return;
                } else {
                    this.a.v().d("InterstitialAdDialogWrapper", "Failed to show interstitial: unknown ad type provided: '" + b2 + "'");
                    a(b2);
                    return;
                }
            } else {
                pVar = this.a.v();
                str3 = "InterstitialAdDialogWrapper";
                str2 = "Failed to show interstitial: stale activity reference provided";
            }
            pVar.d(str3, str2);
            a(appLovinAd);
        }
    }

    public String toString() {
        return "AppLovinInterstitialAdDialog{}";
    }
}
