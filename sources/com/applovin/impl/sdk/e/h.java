package com.applovin.impl.sdk.e;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.j;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class h {
    public static void a(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final j jVar) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdViewEventListener.adOpenedFullscreen(h.b(appLovinAd), appLovinAdView);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen opened event", th);
                    }
                }
            });
        }
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final int i, final j jVar) {
        if (maxAd != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdDisplayFailed(maxAd, i);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad failing to display", th);
                    }
                }
            });
        }
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdLoaded(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being loaded", th);
                    }
                }
            });
        }
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final MaxReward maxReward, final j jVar) {
        if (maxAd != null && maxAdListener != null && (maxAdListener instanceof MaxRewardedAdListener)) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        ((MaxRewardedAdListener) maxAdListener).onUserRewarded(maxAd, maxReward);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about user being rewarded", th);
                    }
                }
            });
        }
    }

    public static void a(final MaxAdListener maxAdListener, final String str, final int i, final j jVar) {
        if (str != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdLoadFailed(str, i);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad failing to load", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdClickListener appLovinAdClickListener, final AppLovinAd appLovinAd, final j jVar) {
        if (appLovinAd != null && appLovinAdClickListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdClickListener.adClicked(h.b(appLovinAd));
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final j jVar) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdDisplayListener.adDisplayed(h.b(appLovinAd));
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final int i, final j jVar) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdRewardListener.validationRequestFailed(h.b(appLovinAd), i);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request failing", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final j jVar) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdRewardListener.userDeclinedToViewAd(h.b(appLovinAd));
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about user declining to view ad", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final j jVar) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdRewardListener.userRewardVerified(h.b(appLovinAd), map);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about successful reward validation request", th);
                    }
                }
            });
        }
    }

    public static void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, double d, boolean z, j jVar) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener2 = appLovinAdVideoPlaybackListener;
            final AppLovinAd appLovinAd2 = appLovinAd;
            final double d2 = d;
            final boolean z2 = z;
            final j jVar2 = jVar;
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdVideoPlaybackListener2.videoPlaybackEnded(h.b(appLovinAd2), d2, z2);
                    } catch (Throwable th) {
                        jVar2.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback ended", th);
                    }
                }
            });
        }
    }

    public static void a(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAd appLovinAd, final j jVar) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdVideoPlaybackListener.videoPlaybackBegan(h.b(appLovinAd));
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback began", th);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static AppLovinAd b(AppLovinAd appLovinAd) {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) appLovinAd;
        return appLovinAdBase.getDummyAd() != null ? appLovinAdBase.getDummyAd() : appLovinAd;
    }

    public static void b(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final j jVar) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdViewEventListener.adClosedFullscreen(h.b(appLovinAd), appLovinAdView);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen closed event", th);
                    }
                }
            });
        }
    }

    public static void b(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdDisplayed(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
                    }
                }
            });
        }
    }

    public static void b(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final j jVar) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdDisplayListener.adHidden(h.b(appLovinAd));
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
                    }
                }
            });
        }
    }

    public static void b(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final j jVar) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdRewardListener.userOverQuota(h.b(appLovinAd), map);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about exceeding quota", th);
                    }
                }
            });
        }
    }

    public static void c(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final j jVar) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdViewEventListener.adLeftApplication(h.b(appLovinAd), appLovinAdView);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about application leave event", th);
                    }
                }
            });
        }
    }

    public static void c(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdHidden(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
                    }
                }
            });
        }
    }

    public static void c(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final j jVar) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        appLovinAdRewardListener.userRewardRejected(h.b(appLovinAd), map);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request being rejected", th);
                    }
                }
            });
        }
    }

    public static void d(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        maxAdListener.onAdClicked(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
                    }
                }
            });
        }
    }

    public static void e(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null && (maxAdListener instanceof MaxRewardedAdListener)) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        ((MaxRewardedAdListener) maxAdListener).onRewardedVideoStarted(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about rewarded video starting", th);
                    }
                }
            });
        }
    }

    public static void f(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null && (maxAdListener instanceof MaxRewardedAdListener)) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        ((MaxRewardedAdListener) maxAdListener).onRewardedVideoCompleted(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about rewarded video completing", th);
                    }
                }
            });
        }
    }

    public static void g(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null && (maxAdListener instanceof MaxAdViewAdListener)) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        ((MaxAdViewAdListener) maxAdListener).onAdExpanded(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being expanded", th);
                    }
                }
            });
        }
    }

    public static void h(final MaxAdListener maxAdListener, final MaxAd maxAd, final j jVar) {
        if (maxAd != null && maxAdListener != null && (maxAdListener instanceof MaxAdViewAdListener)) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        ((MaxAdViewAdListener) maxAdListener).onAdCollapsed(maxAd);
                    } catch (Throwable th) {
                        jVar.v().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being collapsed", th);
                    }
                }
            });
        }
    }
}
