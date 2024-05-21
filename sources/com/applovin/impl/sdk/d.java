package com.applovin.impl.sdk;

import com.applovin.impl.sdk.ad.j;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.m;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.LinkedHashSet;
import java.util.List;

public class d extends r {
    d(j jVar) {
        super(jVar);
    }

    /* access modifiers changed from: package-private */
    public com.applovin.impl.sdk.ad.d a(j jVar) {
        return ((AppLovinAdBase) jVar).getAdZone();
    }

    /* access modifiers changed from: package-private */
    public a a(com.applovin.impl.sdk.ad.d dVar) {
        m mVar = new m(dVar, this, this.a);
        mVar.a(true);
        return mVar;
    }

    public void a() {
        for (com.applovin.impl.sdk.ad.d next : com.applovin.impl.sdk.ad.d.b(this.a)) {
            if (!next.d()) {
                h(next);
            }
        }
    }

    public void a(com.applovin.impl.sdk.ad.d dVar, int i) {
        c(dVar, i);
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj, com.applovin.impl.sdk.ad.d dVar, int i) {
        if (obj instanceof m) {
            ((m) obj).a(dVar, i);
        }
        if (obj instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener) obj).failedToReceiveAd(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj, j jVar) {
        ((AppLovinAdLoadListener) obj).adReceived((AppLovinAd) jVar);
    }

    public /* bridge */ /* synthetic */ void a(LinkedHashSet linkedHashSet) {
        super.a((LinkedHashSet<com.applovin.impl.sdk.ad.d>) linkedHashSet);
    }

    public /* bridge */ /* synthetic */ boolean a(com.applovin.impl.sdk.ad.d dVar, Object obj) {
        return super.a(dVar, obj);
    }

    public void adReceived(AppLovinAd appLovinAd) {
        b((j) appLovinAd);
    }

    public /* bridge */ /* synthetic */ void b(com.applovin.impl.sdk.ad.d dVar, int i) {
        super.b(dVar, i);
    }

    public /* bridge */ /* synthetic */ boolean b(com.applovin.impl.sdk.ad.d dVar) {
        return super.b(dVar);
    }

    public /* bridge */ /* synthetic */ j c(com.applovin.impl.sdk.ad.d dVar) {
        return super.c(dVar);
    }

    public /* bridge */ /* synthetic */ j d(com.applovin.impl.sdk.ad.d dVar) {
        return super.d(dVar);
    }

    public /* bridge */ /* synthetic */ j e(com.applovin.impl.sdk.ad.d dVar) {
        return super.e(dVar);
    }

    public /* bridge */ /* synthetic */ void f(com.applovin.impl.sdk.ad.d dVar) {
        super.f(dVar);
    }

    public void failedToReceiveAd(int i) {
    }

    public /* bridge */ /* synthetic */ boolean g(com.applovin.impl.sdk.ad.d dVar) {
        return super.g(dVar);
    }

    public /* bridge */ /* synthetic */ void h(com.applovin.impl.sdk.ad.d dVar) {
        super.h(dVar);
    }

    public /* bridge */ /* synthetic */ void i(com.applovin.impl.sdk.ad.d dVar) {
        super.i(dVar);
    }

    public void onNativeAdsFailedToLoad(int i) {
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
    }
}
