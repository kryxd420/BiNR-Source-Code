package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.e;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.n;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import java.util.List;

abstract class f extends a {
    protected final AppLovinNativeAdPrecacheListener a;
    private final List<NativeAdImpl> c;
    private final AppLovinNativeAdLoadListener d;
    private int e;

    f(String str, List<NativeAdImpl> list, j jVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(str, jVar);
        this.c = list;
        this.d = appLovinNativeAdLoadListener;
        this.a = null;
    }

    f(String str, List<NativeAdImpl> list, j jVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super(str, jVar);
        if (list != null) {
            this.c = list;
            this.d = null;
            this.a = appLovinNativeAdPrecacheListener;
            return;
        }
        throw new IllegalArgumentException("Native ads cannot be null");
    }

    private void a(int i) {
        if (this.d != null) {
            this.d.onNativeAdsFailedToLoad(i);
        }
    }

    private void a(List<AppLovinNativeAd> list) {
        if (this.d != null) {
            this.d.onNativeAdsLoaded(list);
        }
    }

    /* access modifiers changed from: protected */
    public String a(String str, n nVar, List<String> list) {
        if (!k.b(str)) {
            a("Asked to cache file with null/empty URL, nothing to do.");
            return null;
        } else if (!com.applovin.impl.sdk.e.n.a(str, list)) {
            a("Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        } else {
            try {
                String a2 = nVar.a(d(), str, (String) null, list, true, true, (e) null);
                if (a2 != null) {
                    return a2;
                }
                c("Unable to cache icon resource " + str);
                return null;
            } catch (Exception e2) {
                a("Unable to cache icon resource " + str, e2);
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(NativeAdImpl nativeAdImpl);

    /* access modifiers changed from: protected */
    public abstract void a(NativeAdImpl nativeAdImpl, int i);

    /* access modifiers changed from: protected */
    public abstract boolean a(NativeAdImpl nativeAdImpl, n nVar);

    public void run() {
        List list;
        for (NativeAdImpl next : this.c) {
            a("Beginning resource caching phase...");
            if (a(next, this.b.O())) {
                this.e++;
                a(next);
            } else {
                d("Unable to cache resources");
            }
        }
        try {
            if (this.e == this.c.size()) {
                list = this.c;
            } else if (((Boolean) this.b.a(b.dx)).booleanValue()) {
                d("Mismatch between successful populations and requested size");
                a(-6);
                return;
            } else {
                list = this.c;
            }
            a((List<AppLovinNativeAd>) list);
        } catch (Throwable th) {
            b().v().c(c(), "Encountered exception while notifying publisher code", th);
        }
    }
}
