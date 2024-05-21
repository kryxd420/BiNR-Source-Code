package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.e;
import com.applovin.impl.sdk.d.g;
import com.applovin.impl.sdk.d.n;
import com.applovin.impl.sdk.d.q;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NativeAdServiceImpl implements AppLovinNativeAdService {
    private final j a;
    private final p b;

    NativeAdServiceImpl(j jVar) {
        this.a = jVar;
        this.b = jVar.v();
    }

    /* access modifiers changed from: private */
    public void a(AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            return;
        }
        this.a.D().a((a) new g((List<NativeAdImpl>) Arrays.asList(new NativeAdImpl[]{(NativeAdImpl) appLovinNativeAd}), this.a, (AppLovinNativeAdPrecacheListener) new AppLovinNativeAdPrecacheListener() {
            public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
            }

            public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
            }

            public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
                NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd, i, true);
            }

            public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
                NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd, true);
            }
        }), q.a.CACHING_OTHER);
    }

    /* access modifiers changed from: private */
    public void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List<AppLovinNativeAd> list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, i);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        } else {
            appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, i);
        }
    }

    /* access modifiers changed from: private */
    public void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        } else {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
        }
    }

    private void a(String str, int i, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.D().a((a) new n(str, i, this.a, new AppLovinNativeAdLoadListener() {
            public void onNativeAdsFailedToLoad(int i) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, i);
            }

            public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                NativeAdServiceImpl.this.a(list, appLovinNativeAdLoadListener);
            }
        }), q.a.MAIN);
    }

    /* access modifiers changed from: private */
    public void a(List<AppLovinNativeAd> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int intValue = ((Integer) this.a.a(b.dy)).intValue();
        if (intValue > 0) {
            List list2 = list;
            int size = list2.size();
            if (size != 0) {
                int min = Math.min(intValue, size);
                final List subList = list2.subList(0, min);
                final List subList2 = list2.subList(min, size);
                b(subList, new AppLovinNativeAdLoadListener() {
                    public void onNativeAdsFailedToLoad(int i) {
                        if (appLovinNativeAdLoadListener != null) {
                            appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
                        }
                    }

                    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                        NativeAdServiceImpl.this.c(subList, new AppLovinNativeAdLoadListener() {
                            public void onNativeAdsFailedToLoad(int i) {
                                if (appLovinNativeAdLoadListener != null) {
                                    appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
                                }
                            }

                            public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                                if (appLovinNativeAdLoadListener != null) {
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.addAll(subList);
                                    arrayList.addAll(subList2);
                                    appLovinNativeAdLoadListener.onNativeAdsLoaded(arrayList);
                                }
                            }
                        });
                    }
                });
            } else if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            }
        } else if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
    }

    private void b(List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.D().a((a) new e(list, this.a, (AppLovinNativeAdLoadListener) new AppLovinNativeAdLoadListener() {
            public void onNativeAdsFailedToLoad(int i) {
                if (appLovinNativeAdLoadListener != null) {
                    appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
                }
            }

            public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                if (appLovinNativeAdLoadListener != null) {
                    appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
                }
            }
        }), q.a.CACHING_OTHER);
    }

    /* access modifiers changed from: private */
    public void c(List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.D().a((a) new g(list, this.a, (AppLovinNativeAdLoadListener) new AppLovinNativeAdLoadListener() {
            public void onNativeAdsFailedToLoad(int i) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, i);
            }

            public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, list);
            }
        }), q.a.CACHING_OTHER);
    }

    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("NativeAdService", "Unable to check if ad is preloaded - invalid zone id.");
            return false;
        }
        return this.a.N().g(d.a(str, this.a));
    }

    public void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        loadNativeAds(i, (String) null, appLovinNativeAdLoadListener);
    }

    public void loadNativeAds(int i, String str, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        if (i > 0) {
            this.a.a();
            if (i == 1) {
                d b2 = d.b(str, this.a);
                AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) this.a.N().e(b2);
                if (appLovinNativeAd != null) {
                    a(appLovinNativeAdLoadListener, (List<AppLovinNativeAd>) Arrays.asList(new AppLovinNativeAd[]{appLovinNativeAd}));
                } else {
                    a(str, 1, appLovinNativeAdLoadListener);
                }
                if (((Boolean) this.a.a(b.aT)).booleanValue()) {
                    this.a.N().i(b2);
                    return;
                }
                return;
            }
            a(str, i, appLovinNativeAdLoadListener);
            return;
        }
        p pVar = this.b;
        pVar.e("NativeAdService", "Requested invalid number of native ads: " + i);
    }

    public void loadNextAd(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        loadNativeAds(1, appLovinNativeAdLoadListener);
    }

    public void precacheResources(AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.a.a();
        if (appLovinNativeAd.isImagePrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.a.D().a((a) new e((List<NativeAdImpl>) Arrays.asList(new NativeAdImpl[]{(NativeAdImpl) appLovinNativeAd}), this.a, (AppLovinNativeAdPrecacheListener) new AppLovinNativeAdPrecacheListener() {
            public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
                NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd, i, false);
            }

            public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
                NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd, false);
                NativeAdServiceImpl.this.a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            }

            public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
            }

            public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
            }
        }), q.a.CACHING_OTHER);
    }

    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("NativeAdService", "Unable to preload zone for invalid zone id.");
            return;
        }
        this.a.a();
        d a2 = d.a(str, this.a);
        this.a.M().h(a2);
        this.a.M().i(a2);
    }

    public void preloadAds(d dVar) {
        this.a.M().h(dVar);
        int g = dVar.g();
        if (g == 0 && this.a.M().b(dVar)) {
            g = 1;
        }
        this.a.M().b(dVar, g);
    }

    public String toString() {
        return "NativeAdServiceImpl{}";
    }
}
