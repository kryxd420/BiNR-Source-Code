package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqConfig;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdImage;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.common.TMBannerAdSizes;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.listeners.TMRewardAdListenerBase;
import com.tapdaq.sdk.model.TMAdSize;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapdaq.sdk.model.waterfall.TMReward;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TMAppLovinAdapter extends TMAdapter {
    /* access modifiers changed from: private */
    public Map<Object, Object> mRewardData = new HashMap();
    private AppLovinSdk mSdkInstance;

    /* access modifiers changed from: private */
    public String getError(int i) {
        switch (i) {
            case -900:
                return "INVALID_URL";
            case -800:
                return "INVALID_RESPONSE";
            case AppLovinErrorCodes.NATIVE_AD_IMPRESSION_ALREADY_TRACKED:
                return "NATIVE_AD_IMPRESSION_ALREADY_TRACKED";
            case AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD:
                return "UNABLE_TO_PREPARE_NATIVE_AD";
            case AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO:
                return "INCENTIVIZED_USER_CLOSED_VIDEO";
            case AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT:
                return "INCENTIVIZED_SERVER_TIMEOUT";
            case AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR:
                return "INCENTIVIZED_UNKNOWN_SERVER_ERROR";
            case AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED:
                return "INCENTIVIZED_NO_AD_PRELOADED";
            case AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES:
                return "UNABLE_TO_PRECACHE_VIDEO_RESOURCES";
            case AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES:
                return "UNABLE_TO_PRECACHE_IMAGE_RESOURCES";
            case AppLovinErrorCodes.UNABLE_TO_PRECACHE_RESOURCES:
                return "UNABLE_TO_PRECACHE_RESOURCES";
            case AppLovinErrorCodes.NO_NETWORK:
                return "NO_NETWORK";
            case -102:
                return "FETCH_AD_TIMEOUT";
            case AppLovinErrorCodes.SDK_DISABLED:
                return "SDK_DISABLED";
            case AppLovinErrorCodes.INVALID_AD_TOKEN:
                return "INVALID_AD_TOKEN";
            case AppLovinErrorCodes.INVALID_ZONE:
                return "INVALID_ZONE";
            case AppLovinErrorCodes.UNABLE_TO_RENDER_AD:
                return "UNABLE_TO_RENDER_AD";
            case -1:
                return "UNSPECIFIED_ERROR";
            case 204:
                return "NO_FILL";
            default:
                return "Unknown Applovin Error";
        }
    }

    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 2;
    }

    /* access modifiers changed from: private */
    public AppLovinSdk getSdk(Context context) {
        if (this.mSdkInstance == null && hasCredentials(context)) {
            AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
            appLovinSdkSettings.setVerboseLogging(TLog.isDebugEnabled());
            this.mSdkInstance = AppLovinSdk.getInstance(getAppKey(), appLovinSdkSettings, context);
        }
        return this.mSdkInstance;
    }

    public String getSdkVersion() {
        try {
            return (String) AppLovinSdk.class.getDeclaredField("VERSION").get(String.class);
        } catch (Exception e) {
            TLog.error(e);
            return super.getSdkVersion();
        }
    }

    public void initialise(Activity activity, TDNetwork tDNetwork) {
        super.initialise(activity, tDNetwork);
        if (isWaitingState() && getSdk(activity) != null) {
            TapdaqConfig config = Tapdaq.getInstance().config();
            setConsent(activity, config.isConsentGiven());
            setIsAgeRestrictedUser(activity, config.isAgeRestrictedUser());
            setState(activity, TDAdapterStatus.READY);
        }
    }

    public void setConsent(Context context, boolean z) {
        super.setConsent(context, z);
        if (Tapdaq.getInstance().config().isUserSubjectToGDPR() == STATUS.TRUE && Tapdaq.getInstance().config().getConsenStatus() != STATUS.UNKNOWN) {
            AppLovinPrivacySettings.setHasUserConsent(z, context);
        }
    }

    public void setIsAgeRestrictedUser(Context context, boolean z) {
        super.setIsAgeRestrictedUser(context, z);
        if (Tapdaq.getInstance().config().getAgeRestrictedUserStatus() != STATUS.UNKNOWN) {
            AppLovinPrivacySettings.setIsAgeRestrictedUser(z, context);
        }
    }

    public boolean isInitialised(Context context) {
        return super.isInitialised(context) && getSdk(context) != null;
    }

    public boolean hasCredentials(Context context) {
        return getAppKey() != null;
    }

    public boolean isBannerAvailable(Context context, TMAdSize tMAdSize) {
        return isInitialised(context) && getAdSize(tMAdSize) != null;
    }

    public boolean canDisplayInterstitial(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayVideo(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayRewardedVideo(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayNative(Context context) {
        return isInitialised(context);
    }

    public void sendIAP(Context context, String str, String str2, String str3, String str4) {
        super.sendIAP(context, str, str2, str3, str4);
        TLog.debug(String.format(Locale.ENGLISH, "sendIAP - Network: %s - Data: %s - Signature: %s - Price: %s - Currency: %s", new Object[]{getName(), str, str2, str3, str4}));
        AppLovinEventService eventService = getSdk(context).getEventService();
        HashMap hashMap = new HashMap();
        hashMap.put("amount", str3);
        hashMap.put("currency", str4);
        hashMap.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, str);
        hashMap.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, str2);
        eventService.trackEvent("iap", hashMap);
    }

    public boolean isAdReady(Activity activity, TDAdRequest tDAdRequest) {
        switch (tDAdRequest.getType()) {
            case 1:
            case 2:
                return super.isAdReady(activity, tDAdRequest);
            case 3:
                AppLovinIncentivizedInterstitial appLovinIncentivizedInterstitial = (AppLovinIncentivizedInterstitial) getAd(AppLovinIncentivizedInterstitial.class, tDAdRequest);
                if (appLovinIncentivizedInterstitial != null) {
                    return appLovinIncentivizedInterstitial.isAdReadyToDisplay();
                }
                return false;
            default:
                return false;
        }
    }

    public void loadNativeAd(Activity activity, TDAdRequest tDAdRequest) {
        getSdk(activity).getNativeAdService().loadNativeAds(1, new ApplovinNativeAdListener(activity, new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest)));
    }

    public void registerView(View view, TDMediatedNativeAd tDMediatedNativeAd) {
        if (tDMediatedNativeAd.getNativeAd() instanceof AppLovinNativeAd) {
            final AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) tDMediatedNativeAd.getNativeAd();
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    appLovinNativeAd.launchClickTarget(view.getContext());
                }
            });
        }
    }

    public ViewGroup loadAd(Activity activity, TMAdSize tMAdSize, TDAdRequest tDAdRequest) {
        AppLovinAdView appLovinAdView;
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isBannerAvailable(activity, tMAdSize)) {
            AppLovinAdSize adSize = getAdSize(tMAdSize);
            if (tDAdRequest.getAdUnitId() == null || tDAdRequest.getAdUnitId().isEmpty()) {
                appLovinAdView = new AppLovinAdView(getSdk(activity), adSize, (Context) activity);
            } else {
                appLovinAdView = new AppLovinAdView(getSdk(activity), adSize, tDAdRequest.getAdUnitId(), activity);
            }
            appLovinAdView.setLayoutParams(new FrameLayout.LayoutParams(-1, AppLovinSdkUtils.dpToPx(activity, adSize.getHeight())));
            AdLoadListener adLoadListener = new AdLoadListener(activity, withTimeout);
            AdDisplayListener adDisplayListener = new AdDisplayListener(activity, withTimeout);
            appLovinAdView.setAdClickListener(adDisplayListener);
            appLovinAdView.setAdDisplayListener(adDisplayListener);
            appLovinAdView.setAdLoadListener(adLoadListener);
            appLovinAdView.loadNextAd();
            return appLovinAdView;
        }
        getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(1010, TapdaqError.ADAPTER_BANNER_SIZE_UNAVAILABLE_MESSAGE));
        return null;
    }

    public void loadInterstitial(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isInitialised(activity)) {
            AppLovinAdService adService = getSdk(activity).getAdService();
            if (tDAdRequest.getAdUnitId() == null || tDAdRequest.getAdUnitId().isEmpty()) {
                adService.loadNextAd(AppLovinAdSize.INTERSTITIAL, new AdLoadListener(activity, withTimeout));
            } else {
                adService.loadNextAdForZoneId(tDAdRequest.getAdUnitId(), new AdLoadListener(activity, withTimeout));
            }
        } else {
            getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(TapdaqError.ADAPTER_SDK_NOT_INITIALISED, TapdaqError.ADAPTER_SDK_NOT_INITIALISED_MESSAGE));
        }
    }

    public void loadVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isInitialised(activity)) {
            AppLovinAdService adService = getSdk(activity).getAdService();
            if (tDAdRequest.getAdUnitId() == null || tDAdRequest.getAdUnitId().isEmpty()) {
                adService.loadNextAd(AppLovinAdSize.INTERSTITIAL, new AdLoadListener(activity, withTimeout));
            } else {
                adService.loadNextAdForZoneId(tDAdRequest.getAdUnitId(), new AdLoadListener(activity, withTimeout));
            }
        } else {
            getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(TapdaqError.ADAPTER_SDK_NOT_INITIALISED, TapdaqError.ADAPTER_SDK_NOT_INITIALISED_MESSAGE));
        }
    }

    public void loadRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        AppLovinIncentivizedInterstitial appLovinIncentivizedInterstitial;
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isInitialised(activity)) {
            if (tDAdRequest.getAdUnitId() == null || tDAdRequest.getAdUnitId().isEmpty()) {
                appLovinIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(getSdk(activity));
            } else {
                appLovinIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(tDAdRequest.getAdUnitId(), getSdk(activity));
            }
            appLovinIncentivizedInterstitial.preload(new AdLoadListener(activity, withTimeout));
            storeAd(appLovinIncentivizedInterstitial, tDAdRequest);
            return;
        }
        getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(TapdaqError.ADAPTER_SDK_NOT_INITIALISED, TapdaqError.ADAPTER_SDK_NOT_INITIALISED_MESSAGE));
    }

    public void showInterstitial(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(getSdk(activity), activity);
            AppLovinAd appLovinAd = (AppLovinAd) getAd(AppLovinAd.class, tDAdRequest);
            if (appLovinAd != null) {
                AdDisplayListener adDisplayListener = new AdDisplayListener(activity, tapdaqAd);
                create.setAdDisplayListener(adDisplayListener);
                create.setAdClickListener(adDisplayListener);
                create.showAndRender(appLovinAd);
                removeAd(tDAdRequest);
                return;
            }
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    public void showVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(getSdk(activity), activity);
            AdDisplayListener adDisplayListener = new AdDisplayListener(activity, tapdaqAd);
            AppLovinAd appLovinAd = (AppLovinAd) getAd(AppLovinAd.class, tDAdRequest);
            if (appLovinAd != null) {
                create.setAdDisplayListener(adDisplayListener);
                create.setAdClickListener(adDisplayListener);
                create.showAndRender(appLovinAd);
                removeAd(tDAdRequest);
                return;
            }
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
            return;
        }
        TLog.error("Applovin cannot display ad");
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    public void showRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        super.showRewardedVideo(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            AppLovinIncentivizedInterstitial appLovinIncentivizedInterstitial = (AppLovinIncentivizedInterstitial) getAd(AppLovinIncentivizedInterstitial.class, tDAdRequest);
            if (appLovinIncentivizedInterstitial == null || !appLovinIncentivizedInterstitial.isAdReadyToDisplay()) {
                getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
                return;
            }
            AdDisplayListener adDisplayListener = new AdDisplayListener(activity, tapdaqAd);
            appLovinIncentivizedInterstitial.show(activity, adDisplayListener, adDisplayListener, adDisplayListener, adDisplayListener);
            removeAd(tDAdRequest);
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    public void destroyBanner(View view) {
        super.destroyBanner(view);
        if (view != null && (view instanceof AppLovinAdView)) {
            ((AppLovinAdView) view).destroy();
        }
    }

    private AppLovinAdSize getAdSize(TMAdSize tMAdSize) {
        if (tMAdSize.name.equals(TMBannerAdSizes.STANDARD.name)) {
            return AppLovinAdSize.BANNER;
        }
        if (tMAdSize.name.equals(TMBannerAdSizes.MEDIUM_RECT.name)) {
            return AppLovinAdSize.MREC;
        }
        return null;
    }

    private class AdLoadListener implements AppLovinAdLoadListener {
        private Activity mActivity;
        /* access modifiers changed from: private */
        public TapdaqAd mAdvert;

        private AdLoadListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void adReceived(final AppLovinAd appLovinAd) {
            TLog.debug("Applovin adReceived " + this.mAdvert.getTypeString());
            if (this.mAdvert.getType() != 3) {
                TMAppLovinAdapter.this.storeAd(appLovinAd, this.mAdvert.getAdRequest());
            }
            final Activity activity = this.mActivity;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (appLovinAd != null) {
                        TMAppLovinAdapter.this.getAdEventHandler().OnDidLoad(AdLoadListener.this.mAdvert);
                        return;
                    }
                    TMAppLovinAdapter.this.getAdEventHandler().OnDidFailToLoad(activity, AdLoadListener.this.mAdvert, AdLoadListener.this.mAdvert.getAdRequest(), new TMAdError(1000, "Failed to load ad"));
                }
            });
            this.mActivity = null;
        }

        public void failedToReceiveAd(int i) {
            TLog.debug("failedToReceiveAd " + this.mAdvert.getTypeString());
            if (this.mAdvert.getType() == 3) {
                TMAppLovinAdapter.this.removeAd(this.mAdvert.getAdRequest());
            }
            final TMAdError tMAdError = new TMAdError(i, TMAppLovinAdapter.this.getError(i));
            final Activity activity = this.mActivity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        TMAppLovinAdapter.this.getAdEventHandler().OnDidFailToLoad(activity, AdLoadListener.this.mAdvert, AdLoadListener.this.mAdvert.getAdRequest(), tMAdError);
                    }
                });
            }
            this.mActivity = null;
        }
    }

    private class AdDisplayListener implements AppLovinAdVideoPlaybackListener, AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdRewardListener {
        private Activity mActivity;
        private TapdaqAd mAdvert;

        public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        }

        public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        }

        private AdDisplayListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            TLog.debug("Applovin videoPlaybackBegan");
        }

        public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
            TLog.debug("Applovin videoPlaybackEnded");
            if (z) {
                TMAppLovinAdapter.this.getAdEventHandler().OnVideoComplete(this.mAdvert);
            }
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            TMAppLovinAdapter.this.getAdEventHandler().OnDidDisplay(this.mActivity, this.mAdvert);
        }

        public void adHidden(AppLovinAd appLovinAd) {
            TMAppLovinAdapter.this.removeAd(this.mAdvert.getAdRequest());
            Activity activity = this.mActivity;
            if (this.mAdvert.getType() == 3 && !TMAppLovinAdapter.this.mRewardData.isEmpty()) {
                String str = (String) (TMAppLovinAdapter.this.mRewardData.containsKey("NAME") ? TMAppLovinAdapter.this.mRewardData.get("NAME") : "");
                int intValue = ((Integer) (TMAppLovinAdapter.this.mRewardData.containsKey("AMOUNT") ? TMAppLovinAdapter.this.mRewardData.get("AMOUNT") : 0)).intValue();
                boolean booleanValue = ((Boolean) (TMAppLovinAdapter.this.mRewardData.containsKey("VALID") ? TMAppLovinAdapter.this.mRewardData.get("VALID") : false)).booleanValue();
                TMReward reward = this.mAdvert.getAdRequest().getReward();
                if (reward == null || reward.getReward_name() == null || reward.getReward_value() == 0) {
                    this.mAdvert.getAdRequest().setReward(new TMReward(str, intValue));
                }
                TMAppLovinAdapter.this.getAdEventHandler().OnRewardVerified(activity, this.mAdvert, booleanValue);
                TMAppLovinAdapter.this.mRewardData.clear();
            }
            TMAppLovinAdapter.this.getAdEventHandler().OnDidClose(activity, this.mAdvert);
            this.mActivity = null;
        }

        public void adClicked(AppLovinAd appLovinAd) {
            TMAppLovinAdapter.this.getAdEventHandler().OnDidClick(this.mAdvert);
        }

        public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
            int i;
            String str;
            Exception e;
            try {
                str = (String) map.get("currency");
                try {
                    i = (int) Double.parseDouble((String) map.get("amount"));
                } catch (Exception e2) {
                    e = e2;
                    TLog.error(e);
                    i = -1;
                    TMAppLovinAdapter.this.mRewardData.put("NAME", str);
                    TMAppLovinAdapter.this.mRewardData.put("AMOUNT", Integer.valueOf(i));
                    TMAppLovinAdapter.this.mRewardData.put("VALID", true);
                }
            } catch (Exception e3) {
                str = null;
                e = e3;
                TLog.error(e);
                i = -1;
                TMAppLovinAdapter.this.mRewardData.put("NAME", str);
                TMAppLovinAdapter.this.mRewardData.put("AMOUNT", Integer.valueOf(i));
                TMAppLovinAdapter.this.mRewardData.put("VALID", true);
            }
            TMAppLovinAdapter.this.mRewardData.put("NAME", str);
            TMAppLovinAdapter.this.mRewardData.put("AMOUNT", Integer.valueOf(i));
            TMAppLovinAdapter.this.mRewardData.put("VALID", true);
        }

        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
            TMAppLovinAdapter.this.mRewardData.put("VALID", false);
        }

        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
            if (this.mAdvert.getAdRequest().getListener() instanceof TMRewardAdListenerBase) {
                TMListenerHandler.OnUserDeclined((TMRewardAdListenerBase) this.mAdvert.getAdRequest().getListener());
            }
        }
    }

    private class ApplovinNativeAdListener implements AppLovinNativeAdLoadListener {
        private Activity mActivity;
        /* access modifiers changed from: private */
        public TapdaqAd mAdvert;

        private ApplovinNativeAdListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
            final Activity activity = this.mActivity;
            this.mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) list.get(0);
                    TDApplovinNativeAd tDApplovinNativeAd = new TDApplovinNativeAd(TMAppLovinAdapter.this.getID(), ApplovinNativeAdListener.this.mAdvert, TMAppLovinAdapter.this.getAdEventHandler());
                    tDApplovinNativeAd.setNativeAd(appLovinNativeAd);
                    tDApplovinNativeAd.setTitle(appLovinNativeAd.getTitle());
                    tDApplovinNativeAd.setBody(appLovinNativeAd.getDescriptionText());
                    tDApplovinNativeAd.setCaption(appLovinNativeAd.getCaptionText());
                    tDApplovinNativeAd.setStarRating((double) appLovinNativeAd.getStarRating());
                    tDApplovinNativeAd.setCallToAction(appLovinNativeAd.getCtaText());
                    if (appLovinNativeAd.getVideoUrl() != null) {
                        TDApplovinNativeMediaView tDApplovinNativeMediaView = new TDApplovinNativeMediaView(activity);
                        tDApplovinNativeMediaView.setNativeAd(appLovinNativeAd, TMAppLovinAdapter.this.getSdk(activity).getPostbackService());
                        tDApplovinNativeAd.setVideoUrl(appLovinNativeAd.getVideoUrl());
                        tDApplovinNativeAd.setMediaView(tDApplovinNativeMediaView);
                        tDApplovinNativeAd.setVideoController(new TDApplovinNativeVideoController(tDApplovinNativeAd, tDApplovinNativeMediaView.getMediaPlayer()));
                    }
                    Drawable drawable = null;
                    if (appLovinNativeAd.isImagePrecached()) {
                        ImageView imageView = new ImageView(activity);
                        AppLovinSdkUtils.safePopulateImageView(imageView, Uri.parse(appLovinNativeAd.getImageUrl()), 350);
                        drawable = imageView.getDrawable();
                    }
                    tDApplovinNativeAd.setImages(Arrays.asList(new TDMediatedNativeAdImage[]{new TDMediatedNativeAdImage(drawable, appLovinNativeAd.getImageUrl())}));
                    if (appLovinNativeAd.getIconUrl() != null) {
                        ImageView imageView2 = new ImageView(activity);
                        AppLovinSdkUtils.safePopulateImageView(imageView2, Uri.parse(appLovinNativeAd.getIconUrl()), 50);
                        tDApplovinNativeAd.setAppIconView(imageView2);
                    }
                    TMAppLovinAdapter.this.getAdEventHandler().OnDidLoad(ApplovinNativeAdListener.this.mAdvert, tDApplovinNativeAd);
                }
            });
            this.mActivity = null;
        }

        public void onNativeAdsFailedToLoad(int i) {
            final TMAdError tMAdError = new TMAdError(i, TMAppLovinAdapter.this.getError(i));
            final Activity activity = this.mActivity;
            final TapdaqAd tapdaqAd = this.mAdvert;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    TMAppLovinAdapter.this.getAdEventHandler().OnDidFailToLoad(activity, tapdaqAd, tapdaqAd.getAdRequest(), tMAdError);
                }
            });
            this.mActivity = null;
            this.mAdvert = null;
        }
    }
}
