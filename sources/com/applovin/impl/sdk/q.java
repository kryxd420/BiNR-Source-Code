package com.applovin.impl.sdk;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.ad.j;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.n;
import com.applovin.impl.sdk.e.k;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinAd;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class q extends r {
    q(j jVar) {
        super(jVar);
    }

    /* access modifiers changed from: package-private */
    public d a(j jVar) {
        return ((NativeAdImpl) jVar).getAdZone();
    }

    /* access modifiers changed from: package-private */
    public a a(d dVar) {
        return new n((String) null, 1, this.a, this);
    }

    public void a() {
        h(d.h(this.a));
    }

    public void a(d dVar, int i) {
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj, d dVar, int i) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsFailedToLoad(i);
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj, j jVar) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = (AppLovinNativeAdLoadListener) obj;
        appLovinNativeAdLoadListener.onNativeAdsLoaded(Arrays.asList(new AppLovinNativeAd[]{(AppLovinNativeAd) jVar}));
    }

    public /* bridge */ /* synthetic */ void a(LinkedHashSet linkedHashSet) {
        super.a((LinkedHashSet<d>) linkedHashSet);
    }

    public /* bridge */ /* synthetic */ boolean a(d dVar, Object obj) {
        return super.a(dVar, obj);
    }

    public void adReceived(AppLovinAd appLovinAd) {
    }

    public /* bridge */ /* synthetic */ void b(d dVar, int i) {
        super.b(dVar, i);
    }

    public /* bridge */ /* synthetic */ boolean b(d dVar) {
        return super.b(dVar);
    }

    public /* bridge */ /* synthetic */ j c(d dVar) {
        return super.c(dVar);
    }

    public /* bridge */ /* synthetic */ j d(d dVar) {
        return super.d(dVar);
    }

    public /* bridge */ /* synthetic */ j e(d dVar) {
        return super.e(dVar);
    }

    public /* bridge */ /* synthetic */ void f(d dVar) {
        super.f(dVar);
    }

    public void failedToReceiveAd(int i) {
    }

    public /* bridge */ /* synthetic */ boolean g(d dVar) {
        return super.g(dVar);
    }

    public /* bridge */ /* synthetic */ void h(d dVar) {
        super.h(dVar);
    }

    public /* bridge */ /* synthetic */ void i(d dVar) {
        super.i(dVar);
    }

    public void onNativeAdsFailedToLoad(int i) {
        c(d.h(this.a), i);
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        AppLovinNativeAd appLovinNativeAd = list.get(0);
        if (((Boolean) this.a.a(b.bw)).booleanValue()) {
            this.a.p().precacheResources(appLovinNativeAd, new AppLovinNativeAdPrecacheListener() {
                public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
                    q.this.c(d.h(q.this.a), i);
                }

                public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
                    if (!k.b(appLovinNativeAd.getVideoUrl())) {
                        q.this.b((j) appLovinNativeAd);
                    }
                }

                public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
                    p pVar = q.this.b;
                    pVar.c("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
                    q.this.b((j) appLovinNativeAd);
                }

                public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
                    q.this.b((j) appLovinNativeAd);
                }
            });
        } else {
            b((j) appLovinNativeAd);
        }
    }
}
