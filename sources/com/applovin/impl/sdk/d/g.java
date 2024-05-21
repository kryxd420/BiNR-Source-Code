package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.n;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

public class g extends f {
    public g(List<NativeAdImpl> list, j jVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", list, jVar, appLovinNativeAdLoadListener);
    }

    public g(List<NativeAdImpl> list, j jVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", list, jVar, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        c("Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        a(nativeAdImpl, !f.a(d(), this.b) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES);
        return false;
    }

    public i a() {
        return i.k;
    }

    /* access modifiers changed from: protected */
    public void a(NativeAdImpl nativeAdImpl) {
        if (this.a != null) {
            this.a.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }

    /* access modifiers changed from: protected */
    public void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.a != null) {
            this.a.onNativeAdVideoPrecachingFailed(nativeAdImpl, i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(NativeAdImpl nativeAdImpl, n nVar) {
        if (!k.b(nativeAdImpl.getSourceVideoUrl())) {
            return true;
        }
        a("Beginning native ad video caching" + nativeAdImpl.getAdId());
        if (((Boolean) this.b.a(b.bz)).booleanValue()) {
            String a = a(nativeAdImpl.getSourceVideoUrl(), nVar, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setVideoUrl(a);
        } else {
            a("Resource caching is disabled, skipping...");
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
