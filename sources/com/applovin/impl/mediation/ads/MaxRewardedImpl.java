package com.applovin.impl.mediation.ads;

import android.app.Activity;
import android.util.Base64;
import com.applovin.impl.mediation.ads.a.b;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.HashMap;

public class MaxRewardedImpl extends b {
    private static WeakReference<Activity> a = new WeakReference<>((Object) null);

    public MaxRewardedImpl(String str, AppLovinSdk appLovinSdk) {
        super(str, "MaxRewardedAd", n.a(appLovinSdk));
        p pVar = this.logger;
        String str2 = this.tag;
        pVar.a(str2, "Created new MaxRewardedAd (" + this + ")");
    }

    public static void updateActivity(Activity activity) {
        if (activity != null) {
            a = new WeakReference<>(activity);
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    /* access modifiers changed from: protected */
    public Activity getActivity() {
        return (Activity) a.get();
    }

    public void loadAd() {
        try {
            p pVar = this.logger;
            String str = this.tag;
            pVar.a(str, "Loading ad for '" + this.adUnitId + "'...");
            if (isReady()) {
                p pVar2 = this.logger;
                String str2 = this.tag;
                pVar2.a(str2, "An ad is already loaded for '" + this.adUnitId + "'");
                h.a(this.adListener, getLoadedAd(), this.sdk);
                return;
            }
            final Activity activity = (Activity) a.get();
            if (activity != null) {
                transitionToState(b.C0003b.LOADING, new Runnable() {
                    public void run() {
                        MaxRewardedImpl.this.sdk.a(activity).loadAd(MaxRewardedImpl.this.adUnitId, MaxAdFormat.REWARDED, MaxRewardedImpl.this.loadRequestBuilder.a(), activity, MaxRewardedImpl.this.listenerWrapper);
                    }
                });
                return;
            }
            this.logger.e(this.tag, "Unable to load rewarded ad because Activity reference was null. Call MaxRewardedAd.updateActivity(...) before requesting more rewarded ads");
            this.listenerWrapper.onAdLoadFailed(this.adUnitId, MaxErrorCodes.NO_ACTIVITY);
        } catch (Throwable th) {
            String encodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("exception", encodeToString);
            this.sdk.q().trackEvent("max_rewarded_load_exception", hashMap);
        }
    }

    public void showAd() {
        try {
            if (!((Boolean) this.sdk.a(a.K)).booleanValue() || (!this.sdk.S().a() && !this.sdk.S().b())) {
                p pVar = this.logger;
                String str = this.tag;
                pVar.a(str, "Showing ad for '" + this.adUnitId + "'...");
                final Activity activity = (Activity) a.get();
                if (activity != null) {
                    transitionToState(b.C0003b.SHOWING, new Runnable() {
                        public void run() {
                            MaxRewardedImpl.this.showFullscreenAd(activity);
                        }
                    });
                    return;
                }
                this.logger.e(this.tag, "Unable to show rewarded ad because Activity reference was null. Call MaxRewardedAd.updateActivity(...) before requesting more rewarded ads");
                this.listenerWrapper.onAdDisplayFailed(getLoadedAd(), MaxErrorCodes.NO_ACTIVITY);
                return;
            }
            this.logger.e(this.tag, "Attempting to show ad when another fullscreen ad is already showing");
            h.a(this.adListener, getLoadedAd(), -23, this.sdk);
        } catch (Throwable th) {
            String encodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("exception", encodeToString);
            this.sdk.q().trackEvent("max_rewarded_show_exception", hashMap);
        }
    }

    public String toString() {
        return "MaxRewarded{adUnitId='" + this.adUnitId + '\'' + ", adListener=" + this.adListener + ", isReady=" + isReady() + '}';
    }
}
