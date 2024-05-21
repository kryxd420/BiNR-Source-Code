package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.n;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

public class e extends f {
    public e(List<NativeAdImpl> list, j jVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", list, jVar, appLovinNativeAdLoadListener);
    }

    public e(List<NativeAdImpl> list, j jVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", list, jVar, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        c("Unable to cache image resource");
        a(nativeAdImpl, !f.a(d(), this.b) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES);
        return false;
    }

    public i a() {
        return i.j;
    }

    /* access modifiers changed from: protected */
    public void a(NativeAdImpl nativeAdImpl) {
        if (this.a != null) {
            this.a.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }

    /* access modifiers changed from: protected */
    public void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.a != null) {
            this.a.onNativeAdImagePrecachingFailed(nativeAdImpl, i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(NativeAdImpl nativeAdImpl, n nVar) {
        a("Beginning native ad image caching for #" + nativeAdImpl.getAdId());
        if (((Boolean) this.b.a(b.bz)).booleanValue()) {
            String a = a(nativeAdImpl.getSourceIconUrl(), nVar, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setIconUrl(a);
            String a2 = a(nativeAdImpl.getSourceImageUrl(), nVar, nativeAdImpl.getResourcePrefixes());
            if (a2 == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setImageUrl(a2);
            return true;
        }
        a("Resource caching is disabled, skipping...");
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
