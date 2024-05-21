package com.applovin.impl.sdk.a;

import android.app.Activity;
import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.d.ab;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.d.x;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.SoftReference;
import java.util.Map;

public class a {
    protected final j a;
    protected final AppLovinAdServiceImpl b;
    /* access modifiers changed from: private */
    public AppLovinAd c;
    private String d;
    private SoftReference<AppLovinAdLoadListener> e;
    private final Object f = new Object();
    private volatile String g;
    /* access modifiers changed from: private */
    public ab h;
    /* access modifiers changed from: private */
    public volatile boolean i = false;
    /* access modifiers changed from: private */
    public SoftReference<AppLovinInterstitialAdDialog> j;

    /* renamed from: com.applovin.impl.sdk.a.a$a  reason: collision with other inner class name */
    private class C0004a implements AppLovinAdLoadListener {
        /* access modifiers changed from: private */
        public final AppLovinAdLoadListener b;

        C0004a(AppLovinAdLoadListener appLovinAdLoadListener) {
            this.b = appLovinAdLoadListener;
        }

        public void adReceived(final AppLovinAd appLovinAd) {
            AppLovinAd unused = a.this.c = appLovinAd;
            if (this.b != null) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            C0004a.this.b.adReceived(appLovinAd);
                        } catch (Throwable th) {
                            a.this.a.v().c("AppLovinIncentivizedInterstitial", "Unable to notify ad listener about a newly loaded ad", th);
                        }
                    }
                });
            }
        }

        public void failedToReceiveAd(final int i) {
            if (this.b != null) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            C0004a.this.b.failedToReceiveAd(i);
                        } catch (Throwable th) {
                            a.this.a.v().c("AppLovinIncentivizedInterstitial", "Unable to notify listener about ad load failure", th);
                        }
                    }
                });
            }
        }
    }

    private class b implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
        private final Context b;
        private final AppLovinAdDisplayListener c;
        private final AppLovinAdClickListener d;
        private final AppLovinAdVideoPlaybackListener e;
        private final AppLovinAdRewardListener f;

        private b(Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
            this.c = appLovinAdDisplayListener;
            this.d = appLovinAdClickListener;
            this.e = appLovinAdVideoPlaybackListener;
            this.f = appLovinAdRewardListener;
            this.b = context;
        }

        private void a(g gVar) {
            int i;
            String str;
            String a2 = a.this.e();
            if (!k.b(a2) || !a.this.i) {
                a.this.h.a(true);
                if (a.this.i) {
                    str = "network_timeout";
                    i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
                } else {
                    str = "user_closed_video";
                    i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
                }
                e.a().a(gVar, str);
                if (a.this.i) {
                    a.this.a(a2, this.b);
                }
                h.a(this.f, (AppLovinAd) gVar, i, a.this.a);
            } else {
                a.this.a(a2, this.b);
            }
            a.this.a((AppLovinAd) gVar);
            h.b(this.c, (AppLovinAd) gVar, a.this.a);
            if (!gVar.af().getAndSet(true)) {
                a.this.a.D().a((com.applovin.impl.sdk.d.a) new x(gVar, a.this.a), q.a.REWARD);
            }
        }

        public void adClicked(AppLovinAd appLovinAd) {
            h.a(this.d, appLovinAd, a.this.a);
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            h.a(this.c, appLovinAd, a.this.a);
        }

        public void adHidden(AppLovinAd appLovinAd) {
            if (appLovinAd instanceof com.applovin.impl.sdk.ad.h) {
                appLovinAd = ((com.applovin.impl.sdk.ad.h) appLovinAd).a();
            }
            if (appLovinAd instanceof g) {
                a((g) appLovinAd);
                return;
            }
            p v = a.this.a.v();
            v.d("IncentivizedAdController", "Something is terribly wrong. Received `adHidden` callback for invalid ad of type: " + appLovinAd);
        }

        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        }

        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("quota_exceeded");
            h.b(this.f, appLovinAd, map, a.this.a);
        }

        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("rejected");
            h.c(this.f, appLovinAd, map, a.this.a);
        }

        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("accepted");
            h.a(this.f, appLovinAd, map, a.this.a);
        }

        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
            a.this.a("network_timeout");
            h.a(this.f, appLovinAd, i, a.this.a);
        }

        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            h.a(this.e, appLovinAd, a.this.a);
        }

        public void videoPlaybackEnded(AppLovinAd appLovinAd, double d2, boolean z) {
            h.a(this.e, appLovinAd, d2, z, a.this.a);
            boolean unused = a.this.i = z;
        }
    }

    public a(String str, AppLovinSdk appLovinSdk) {
        this.a = n.a(appLovinSdk);
        this.b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.d = str;
    }

    private void a(AppLovinAdBase appLovinAdBase, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        p v;
        String str;
        String str2;
        StringBuilder sb;
        String str3;
        if (!appLovinAdBase.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            v = this.a.v();
            str = "IncentivizedAdController";
            sb = new StringBuilder();
            sb.append("Failed to render an ad of type ");
            sb.append(appLovinAdBase.getType());
            str3 = " in an Incentivized Ad interstitial.";
        } else if (!n.a((AppLovinAd) appLovinAdBase, this.a)) {
            a((AppLovinAd) appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
            return;
        } else {
            g gVar = appLovinAdBase instanceof com.applovin.impl.sdk.ad.h ? (g) this.a.M().c(appLovinAdBase.getAdZone()) : (g) appLovinAdBase;
            if (!n.a(gVar, context, this.a)) {
                this.a.E().a(com.applovin.impl.sdk.c.g.o);
                if (gVar instanceof com.applovin.impl.sdk.ad.a) {
                    com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) gVar;
                    if (!aVar.ai() || !aVar.d()) {
                        v = this.a.v();
                        str = "IncentivizedAdController";
                        sb = new StringBuilder();
                        sb.append("Cached video removed from local filesystem for ad server ad: ");
                        sb.append(aVar.getAdIdNumber());
                        str3 = " and could not restore video stream url. Failing ad show.";
                    } else {
                        p v2 = this.a.v();
                        v2.d("IncentivizedAdController", "Cached video removed from local filesystem for ad server ad. Restored video uri to video stream url: " + aVar.e());
                    }
                } else {
                    v = this.a.v();
                    str = "IncentivizedAdController";
                    str2 = "Failed to render an ad: video cache has been removed.";
                    v.d(str, str2);
                    a((AppLovinAd) appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                }
            }
            final AppLovinAdBase appLovinAdBase2 = appLovinAdBase;
            final Context context2 = context;
            final AppLovinAdRewardListener appLovinAdRewardListener2 = appLovinAdRewardListener;
            final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener2 = appLovinAdVideoPlaybackListener;
            final AppLovinAdDisplayListener appLovinAdDisplayListener2 = appLovinAdDisplayListener;
            final AppLovinAdClickListener appLovinAdClickListener2 = appLovinAdClickListener;
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    AppLovinAd b2 = n.b(appLovinAdBase2, a.this.a);
                    if (b2 != null) {
                        AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(a.this.a.L(), context2);
                        b bVar = new b(context2, appLovinAdRewardListener2, appLovinAdVideoPlaybackListener2, appLovinAdDisplayListener2, appLovinAdClickListener2);
                        create.setAdDisplayListener(bVar);
                        create.setAdVideoPlaybackListener(bVar);
                        create.setAdClickListener(bVar);
                        create.showAndRender(b2);
                        SoftReference unused = a.this.j = new SoftReference(create);
                        if (b2 instanceof g) {
                            a.this.a((g) b2, (AppLovinAdRewardListener) bVar);
                            return;
                        }
                        return;
                    }
                    a.this.a((AppLovinAd) appLovinAdBase2, appLovinAdVideoPlaybackListener2, appLovinAdDisplayListener2);
                }
            };
            boolean booleanValue = ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.bY)).booleanValue();
            if (booleanValue && (context instanceof Activity)) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing()) {
                    c.a().a(this.a).a(activity).a(this).a(appLovinAdRewardListener).a((Runnable) r0).a().a((AppLovinAd) appLovinAdBase);
                    return;
                }
            }
            if (booleanValue) {
                this.a.v().e("IncentivizedAdController", "Unable to show Incentivized Ad prompt. Must pass in an active Activity context.");
            }
            this.a.E().a(com.applovin.impl.sdk.c.g.j);
            r0.run();
            return;
        }
        sb.append(str3);
        str2 = sb.toString();
        v.d(str, str2);
        a((AppLovinAd) appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
    }

    /* access modifiers changed from: private */
    public void a(g gVar, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.h = new ab(gVar, appLovinAdRewardListener, this.a);
        this.a.D().a((com.applovin.impl.sdk.d.a) this.h, q.a.REWARD);
    }

    /* access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd) {
        if (this.c != null) {
            if (this.c instanceof com.applovin.impl.sdk.ad.h) {
                if (appLovinAd != ((com.applovin.impl.sdk.ad.h) this.c).a()) {
                    return;
                }
            } else if (appLovinAd != this.c) {
                return;
            }
            this.c = null;
        }
    }

    private void a(AppLovinAd appLovinAd, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAd == null) {
            appLovinAd = this.c;
        }
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) appLovinAd;
        if (appLovinAdBase != null) {
            a(appLovinAdBase, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            return;
        }
        this.a.v().e("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
        d();
    }

    /* access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.a.E().a(com.applovin.impl.sdk.c.g.m);
        h.a(appLovinAdVideoPlaybackListener, appLovinAd, 0.0d, false, this.a);
        h.b(appLovinAdDisplayListener, appLovinAd, this.a);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        synchronized (this.f) {
            this.g = str;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Context context) {
        if (str != null && ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.bZ)).booleanValue()) {
            new b(this.a, context, str).a();
        }
    }

    private void b(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.loadNextIncentivizedAd(this.d, appLovinAdLoadListener);
    }

    private void d() {
        AppLovinAdLoadListener appLovinAdLoadListener;
        if (this.e != null && (appLovinAdLoadListener = this.e.get()) != null) {
            appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
        }
    }

    /* access modifiers changed from: private */
    public String e() {
        String str;
        synchronized (this.f) {
            str = this.g;
        }
        return str;
    }

    private AppLovinAdRewardListener f() {
        return new AppLovinAdRewardListener() {
            public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                a.this.a.v().a("IncentivizedAdController", "User declined to view");
            }

            public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
                p v = a.this.a.v();
                v.a("IncentivizedAdController", "User over quota: " + map);
            }

            public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
                p v = a.this.a.v();
                v.a("IncentivizedAdController", "Reward rejected: " + map);
            }

            public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
                p v = a.this.a.v();
                v.a("IncentivizedAdController", "Reward validated: " + map);
            }

            public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
                p v = a.this.a.v();
                v.a("IncentivizedAdController", "Reward validation failed: " + i);
            }
        };
    }

    public void a(AppLovinAd appLovinAd, Context context, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAdRewardListener == null) {
            appLovinAdRewardListener = f();
        }
        a(appLovinAd, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    /* access modifiers changed from: package-private */
    public void a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        h.a(appLovinAdRewardListener, appLovinAd, this.a);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.v().a("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.e = new SoftReference<>(appLovinAdLoadListener);
        if (a()) {
            this.a.v().e("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.c);
                return;
            }
            return;
        }
        b((AppLovinAdLoadListener) new C0004a(appLovinAdLoadListener));
    }

    public boolean a() {
        return this.c != null;
    }

    public String b() {
        return this.d;
    }

    public void c() {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (this.j != null && (appLovinInterstitialAdDialog = this.j.get()) != null) {
            appLovinInterstitialAdDialog.dismiss();
        }
    }
}
