package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.adunit.AdUnitActivity;
import com.unity3d.ads.adunit.AdUnitSoftwareActivity;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;

public class TMUnityAdsAdapter extends TMAdapter {
    private UnityListener mUnityListener;

    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 13;
    }

    private UnityListener getUnityListener(Activity activity) {
        if (this.mUnityListener != null) {
            this.mUnityListener.destroy();
        }
        this.mUnityListener = new UnityListener(activity);
        return this.mUnityListener;
    }

    public String getSdkVersion() {
        return UnityAds.getVersion();
    }

    public void initialise(Activity activity, TDNetwork tDNetwork) {
        super.initialise(activity, tDNetwork);
        if (isWaitingState()) {
            UnityAds.setDebugMode(TLog.isDebugEnabled());
            UnityAds.initialize(activity, getAppId(), getUnityListener(activity));
        }
    }

    public boolean isInitialised(Context context) {
        return super.isInitialised(context) && UnityAds.isInitialized();
    }

    public boolean hasCredentials(Context context) {
        return getAppId() != null;
    }

    public boolean hasRequiredActivities(Context context) {
        return TDActivityUtil.IsActivityAvailable(context, AdUnitActivity.class) && TDActivityUtil.IsActivityAvailable(context, AdUnitSoftwareActivity.class);
    }

    public boolean canDisplayVideo(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayRewardedVideo(Context context) {
        return isInitialised(context);
    }

    public boolean isAdReady(Activity activity, TDAdRequest tDAdRequest) {
        switch (tDAdRequest.getType()) {
            case 2:
                return UnityAds.isReady(tDAdRequest.getAdUnitId());
            case 3:
                return UnityAds.isReady(tDAdRequest.getAdUnitId());
            default:
                return false;
        }
    }

    public void loadVideo(Activity activity, TDAdRequest tDAdRequest) {
        TMAdError tMAdError;
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isAdReady(activity, tDAdRequest)) {
            getAdEventHandler().OnDidLoad(withTimeout);
        } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.WAITING)) {
            UnityAds.setListener(new UnityListener(activity, withTimeout, true));
        } else {
            if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.DISABLED)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_DISABLED, TapdaqError.UNITYADS_PLACEMENT_STATE_DISABLED_MESSAGE);
            } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.NO_FILL)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_NO_FILL, TapdaqError.UNITYADS_PLACEMENT_STATE_NO_FILL_MESSAGE);
            } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.NOT_AVAILABLE)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_UNAVAILABLE, TapdaqError.UNITYADS_PLACEMENT_STATE_UNAVAILABLE_MESSAGE);
            } else {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_FAILED_TO_LOAD_AD, "Failed to load ad");
            }
            getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, tMAdError);
        }
    }

    public void loadRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        TMAdError tMAdError;
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (isAdReady(activity, tDAdRequest)) {
            getAdEventHandler().OnDidLoad(withTimeout);
        } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.WAITING)) {
            UnityAds.setListener(new UnityListener(activity, withTimeout, true));
        } else {
            if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.DISABLED)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_DISABLED, TapdaqError.UNITYADS_PLACEMENT_STATE_DISABLED_MESSAGE);
            } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.NO_FILL)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_NO_FILL, TapdaqError.UNITYADS_PLACEMENT_STATE_NO_FILL_MESSAGE);
            } else if (UnityAds.getPlacementState().equals(UnityAds.PlacementState.NOT_AVAILABLE)) {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_PLACEMENT_STATE_UNAVAILABLE, TapdaqError.UNITYADS_PLACEMENT_STATE_UNAVAILABLE_MESSAGE);
            } else {
                tMAdError = new TMAdError(TapdaqError.UNITYADS_FAILED_TO_LOAD_AD, "Failed to load ad");
            }
            getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, tMAdError);
        }
    }

    public void showVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (UnityAds.isReady(tDAdRequest.getAdUnitId())) {
            UnityAds.setListener(new UnityListener(activity, tapdaqAd, false));
            UnityAds.show(activity, tDAdRequest.getAdUnitId());
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    public void showRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        super.showRewardedVideo(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (UnityAds.isReady(tDAdRequest.getAdUnitId())) {
            UnityAds.setListener(new UnityListener(activity, tapdaqAd, false));
            UnityAds.show(activity, tDAdRequest.getAdUnitId());
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    private class UnityListener implements IUnityAdsExtendedListener {
        private Activity mActivity;
        private TapdaqAd mAdvert;
        private Boolean mIsLoading;

        private UnityListener(Activity activity) {
            this.mIsLoading = false;
            this.mActivity = activity;
        }

        private UnityListener(Activity activity, TapdaqAd tapdaqAd, boolean z) {
            this.mIsLoading = false;
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
            this.mIsLoading = Boolean.valueOf(z);
        }

        /* access modifiers changed from: package-private */
        public void destroy() {
            this.mActivity = null;
        }

        public void onUnityAdsReady(String str) {
            TLog.debug("onUnityAdsReady: " + str);
        }

        public void onUnityAdsStart(String str) {
            if (this.mActivity != null) {
                TMUnityAdsAdapter.this.getAdEventHandler().OnDidDisplay(this.mActivity, this.mAdvert);
            }
        }

        public void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            if (this.mAdvert != null) {
                boolean z = finishState == UnityAds.FinishState.COMPLETED;
                Activity activity = this.mActivity;
                TMUnityAdsAdapter.this.getAdEventHandler().OnVideoComplete(this.mAdvert);
                if (this.mAdvert.getType() == 3) {
                    TMUnityAdsAdapter.this.getAdEventHandler().OnRewardVerified(activity, this.mAdvert, z);
                }
                TMUnityAdsAdapter.this.getAdEventHandler().OnDidClose(activity, this.mAdvert);
            }
        }

        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            TLog.debug("onUnityAdsError: " + str);
            if (TMUnityAdsAdapter.this.isWaitingState()) {
                TMUnityAdsAdapter.this.setState(this.mActivity, TDAdapterStatus.FAILED);
            }
        }

        public void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2) {
            TLog.debug("onUnityAdsPlacementStateChanged: " + str + " OldState: " + placementState.name() + " NewState: " + placementState2.name());
            if (this.mActivity == null) {
                return;
            }
            if (TMUnityAdsAdapter.this.isWaitingState()) {
                if (placementState2 == UnityAds.PlacementState.WAITING) {
                    TMUnityAdsAdapter.this.setState(this.mActivity, TDAdapterStatus.READY);
                } else {
                    TMUnityAdsAdapter.this.setState(this.mActivity, TDAdapterStatus.FAILED);
                }
                this.mActivity = null;
                this.mAdvert = null;
            } else if (!this.mIsLoading.booleanValue()) {
            } else {
                if (placementState2 == UnityAds.PlacementState.READY) {
                    if (this.mAdvert != null) {
                        TMUnityAdsAdapter.this.getAdEventHandler().OnDidLoad(this.mAdvert);
                    }
                    this.mActivity = null;
                    this.mAdvert = null;
                    return;
                }
                if (this.mAdvert != null) {
                    TMUnityAdsAdapter.this.getAdEventHandler().OnDidFailToLoad(this.mActivity, this.mAdvert, this.mAdvert.getAdRequest(), new TMAdError(0, str));
                }
                this.mActivity = null;
                this.mAdvert = null;
            }
        }

        public void onUnityAdsClick(String str) {
            TMUnityAdsAdapter.this.getAdEventHandler().OnDidClick(this.mAdvert);
        }
    }
}
