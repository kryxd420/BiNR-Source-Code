package com.applovin.impl.mediation.ads;

import android.app.Activity;
import android.util.Base64;
import com.applovin.impl.mediation.ads.a.b;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.e.h;
import com.applovin.impl.sdk.e.n;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.sdk.AppLovinSdk;
import java.nio.charset.Charset;
import java.util.HashMap;

public class MaxInterstitialImpl extends b {
    /* access modifiers changed from: private */
    public final Activity a;

    public MaxInterstitialImpl(String str, AppLovinSdk appLovinSdk, Activity activity) {
        super(str, "MaxInterstitialAd", n.a(appLovinSdk));
        if (activity != null) {
            this.a = activity;
            p pVar = this.logger;
            String str2 = this.tag;
            pVar.a(str2, "Created new MaxInterstitialAd (" + this + ")");
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    /* access modifiers changed from: protected */
    public Activity getActivity() {
        return this.a;
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
            transitionToState(b.C0003b.LOADING, new Runnable() {
                public void run() {
                    MaxInterstitialImpl.this.sdk.a(MaxInterstitialImpl.this.a).loadAd(MaxInterstitialImpl.this.adUnitId, MaxAdFormat.INTERSTITIAL, MaxInterstitialImpl.this.loadRequestBuilder.a(), MaxInterstitialImpl.this.a, MaxInterstitialImpl.this.listenerWrapper);
                }
            });
        } catch (Throwable th) {
            String encodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("exception", encodeToString);
            this.sdk.q().trackEvent("max_inter_load_exception", hashMap);
        }
    }

    public void showAd() {
        try {
            if (!((Boolean) this.sdk.a(a.K)).booleanValue() || (!this.sdk.S().a() && !this.sdk.S().b())) {
                transitionToState(b.C0003b.SHOWING, new Runnable() {
                    public void run() {
                        MaxInterstitialImpl.this.showFullscreenAd(MaxInterstitialImpl.this.a);
                    }
                });
                return;
            }
            this.logger.e(this.tag, "Attempting to show ad when another fullscreen ad is already showing");
            h.a(this.adListener, getLoadedAd(), -23, this.sdk);
        } catch (Throwable th) {
            String encodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("exception", encodeToString);
            this.sdk.q().trackEvent("max_inter_show_exception", hashMap);
        }
    }

    public String toString() {
        return "MaxInterstitial{adUnitId='" + this.adUnitId + '\'' + ", adListener=" + this.adListener + ", isReady=" + isReady() + '}';
    }
}
