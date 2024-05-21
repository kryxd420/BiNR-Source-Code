package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAdViewActivity;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialActivity;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.listeners.TMRewardAdListenerBase;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapjoy.TapjoyAuctionFlags;
import java.util.List;

public class TMAdColonyAdapter extends TMAdapter {
    private AdColonyAppOptions mOptions = new AdColonyAppOptions();

    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 0;
    }

    public String getSdkVersion() {
        return AdColony.getSDKVersion();
    }

    public void initialise(Activity activity, TDNetwork tDNetwork) {
        super.initialise(activity, tDNetwork);
        if (isWaitingState()) {
            List<String> adUnitIDs = getNetwork().getCredentials().getAdUnitIDs();
            setConsent(activity, Tapdaq.getInstance().config().isConsentGiven());
            AdColony.configure(activity, this.mOptions, getAppId(), (String[]) adUnitIDs.toArray(new String[adUnitIDs.size()]));
            setState(activity, TDAdapterStatus.READY);
        }
    }

    public void setUserSubjectToGDPR(Context context, STATUS status) {
        super.setUserSubjectToGDPR(context, status);
        if (Tapdaq.getInstance().config().isUserSubjectToGDPR() == STATUS.TRUE) {
            this.mOptions.setGDPRRequired(true);
        } else if (Tapdaq.getInstance().config().isUserSubjectToGDPR() == STATUS.FALSE) {
            this.mOptions.setGDPRRequired(false);
        }
        if (isInitialised(context)) {
            AdColony.setAppOptions(this.mOptions);
        }
    }

    public void setConsent(Context context, boolean z) {
        super.setConsent(context, z);
        STATUS isUserSubjectToGDPR = Tapdaq.getInstance().config().isUserSubjectToGDPR();
        if (isUserSubjectToGDPR != STATUS.UNKNOWN) {
            if (Tapdaq.getInstance().config().getConsenStatus() != STATUS.UNKNOWN) {
                this.mOptions.setGDPRConsentString(z ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0");
            }
            if (isUserSubjectToGDPR == STATUS.TRUE) {
                this.mOptions.setGDPRRequired(true);
            } else if (isUserSubjectToGDPR == STATUS.FALSE) {
                this.mOptions.setGDPRRequired(false);
            }
            if (isInitialised(context)) {
                AdColony.setAppOptions(this.mOptions);
            }
        }
    }

    public boolean hasRequiredActivities(Context context) {
        return TDActivityUtil.IsActivityAvailable(context, AdColonyInterstitialActivity.class) && TDActivityUtil.IsActivityAvailable(context, AdColonyAdViewActivity.class);
    }

    public boolean hasCredentials(Context context) {
        return getAppId() != null;
    }

    public boolean canDisplayVideo(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayRewardedVideo(Context context) {
        return isInitialised(context);
    }

    public boolean isAdReady(Activity activity, TDAdRequest tDAdRequest) {
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) getAd(AdColonyInterstitial.class, tDAdRequest);
        return adColonyInterstitial != null && !adColonyInterstitial.isExpired();
    }

    public void loadVideo(Activity activity, TDAdRequest tDAdRequest) {
        AdColony.requestInterstitial(tDAdRequest.getAdUnitId(), new AdColonyListener(activity, new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest)));
    }

    public void loadRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        AdColony.requestInterstitial(tDAdRequest.getAdUnitId(), new AdColonyListener(activity, new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest)), new AdColonyAdOptions().enableConfirmationDialog(false).enableResultsDialog(false));
    }

    public void showVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) getAd(AdColonyInterstitial.class, tDAdRequest);
        removeAd(tDAdRequest);
        if (adColonyInterstitial == null) {
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
        } else if (!adColonyInterstitial.isExpired()) {
            adColonyInterstitial.setListener(new AdColonyListener(activity, tapdaqAd));
            adColonyInterstitial.show();
        } else {
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED_MESSAGE));
        }
    }

    public void showRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        super.showRewardedVideo(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) getAd(AdColonyInterstitial.class, tDAdRequest);
        removeAd(tDAdRequest);
        if (adColonyInterstitial == null) {
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
        } else if (!adColonyInterstitial.isExpired()) {
            this.mFailReward.put(tDAdRequest.getWaterfallId(), true);
            adColonyInterstitial.setListener(new AdColonyListener(activity, tapdaqAd));
            if (tDAdRequest.getListener() instanceof TMRewardAdListenerBase) {
                AdColony.setRewardListener(new TMAdColonyRewardListener(activity, tapdaqAd));
            }
            adColonyInterstitial.show();
        } else {
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(TapdaqError.AD_EXPIRED, TapdaqError.AD_EXPIRED_MESSAGE));
        }
    }

    private class AdColonyListener extends AdColonyInterstitialListener {
        private Activity mActivity;
        private TapdaqAd mAdvert;

        AdColonyListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
            TMAdColonyAdapter.this.storeAd(adColonyInterstitial, this.mAdvert.getAdRequest());
            TMAdColonyAdapter.this.getAdEventHandler().OnDidLoad(this.mAdvert);
            this.mActivity = null;
            this.mAdvert = null;
        }

        public void onRequestNotFilled(AdColonyZone adColonyZone) {
            super.onRequestNotFilled(adColonyZone);
            TMAdColonyAdapter.this.getAdEventHandler().OnDidFailToLoad(this.mActivity, this.mAdvert, this.mAdvert.getAdRequest(), new TMAdError(TapdaqError.ADCOLONY_FAILED_TO_LOAD_AD, "Failed to load ad"));
            this.mActivity = null;
            this.mAdvert = null;
        }

        public void onOpened(AdColonyInterstitial adColonyInterstitial) {
            super.onOpened(adColonyInterstitial);
            TMAdColonyAdapter.this.getAdEventHandler().OnDidDisplay(this.mActivity, this.mAdvert);
        }

        public void onClosed(AdColonyInterstitial adColonyInterstitial) {
            super.onClosed(adColonyInterstitial);
            Activity activity = this.mActivity;
            if (TMAdColonyAdapter.this.mFailReward.containsKey(this.mAdvert.getSharedId()) && ((Boolean) TMAdColonyAdapter.this.mFailReward.get(this.mAdvert.getSharedId())).booleanValue() && (this.mAdvert.getAdRequest().getListener() instanceof TMRewardAdListenerBase)) {
                TMAdColonyAdapter.this.getAdEventHandler().OnRewardVerified(activity, this.mAdvert, false);
            }
            TMAdColonyAdapter.this.getAdEventHandler().OnDidClose(activity, this.mAdvert);
            this.mActivity = null;
            this.mAdvert = null;
        }

        public void onIAPEvent(AdColonyInterstitial adColonyInterstitial, String str, int i) {
            super.onIAPEvent(adColonyInterstitial, str, i);
        }

        public void onExpiring(AdColonyInterstitial adColonyInterstitial) {
            super.onExpiring(adColonyInterstitial);
        }

        public void onLeftApplication(AdColonyInterstitial adColonyInterstitial) {
            super.onLeftApplication(adColonyInterstitial);
        }

        public void onClicked(AdColonyInterstitial adColonyInterstitial) {
            super.onClicked(adColonyInterstitial);
            TMAdColonyAdapter.this.getAdEventHandler().OnDidClick(this.mAdvert);
        }
    }

    private class TMAdColonyRewardListener implements AdColonyRewardListener {
        private Activity mActivity;
        private TapdaqAd mAdvert;

        TMAdColonyRewardListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void onReward(AdColonyReward adColonyReward) {
            TMAdColonyAdapter.this.mFailReward.put(this.mAdvert.getSharedId(), false);
            TMAdColonyAdapter.this.getAdEventHandler().OnRewardVerified(this.mActivity, this.mAdvert, adColonyReward.success());
            this.mActivity = null;
        }
    }
}
