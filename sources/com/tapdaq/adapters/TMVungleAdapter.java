package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapdaq.sdk.tasks.TDTaskManager;
import com.vungle.warren.AdConfig;
import com.vungle.warren.BuildConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.ui.VungleActivity;
import java.util.Locale;

public class TMVungleAdapter extends TMAdapter {
    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 14;
    }

    public String getSdkVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public void initialise(final Activity activity, final TDNetwork tDNetwork) {
        super.initialise(activity, tDNetwork);
        if (isWaitingState()) {
            try {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Vungle.init(tDNetwork.getCredentials().getAppId(), activity.getApplicationContext(), new InitCallback() {
                            public void onSuccess() {
                                TMVungleAdapter.this.setConsent(activity, Tapdaq.getInstance().isConsentGiven(activity));
                                TMVungleAdapter.this.setState(activity, TDAdapterStatus.READY);
                            }

                            public void onError(Throwable th) {
                                TMVungleAdapter.this.setState(activity, TDAdapterStatus.FAILED);
                            }

                            public void onAutoCacheAdAvailable(String str) {
                                TLog.debug("onAutoCacheAdAvailable: " + str);
                            }
                        });
                    }
                });
            } catch (Exception e) {
                TLog.error(e);
                setState(activity, TDAdapterStatus.FAILED);
            }
        }
    }

    public void setConsent(Context context, boolean z) {
        super.setConsent(context, z);
        if (Vungle.isInitialized()) {
            Vungle.updateConsentStatus(z ? Vungle.Consent.OPTED_IN : Vungle.Consent.OPTED_OUT, TDDeviceInfo.getBundleVersion(context));
        }
    }

    public boolean isInitialised(Context context) {
        return super.isInitialised(context) && Vungle.isInitialized();
    }

    public boolean hasCredentials(Context context) {
        return getAppId() != null;
    }

    public boolean hasRequiredActivities(Context context) {
        return TDActivityUtil.IsActivityAvailable(context, VungleActivity.class);
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
                return Vungle.canPlayAd(tDAdRequest.getAdUnitId());
            case 3:
                return Vungle.canPlayAd(tDAdRequest.getAdUnitId());
            default:
                return false;
        }
    }

    public void loadVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (tDAdRequest.getAdUnitId() != null) {
            Vungle.loadAd(tDAdRequest.getAdUnitId(), new VungleEventListener(activity, withTimeout));
            return;
        }
        getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(1001, TapdaqError.ADAPTER_AD_CONFIG_ID_MISSING_MESSAGE));
    }

    public void loadRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        TapdaqAd withTimeout = new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest);
        if (tDAdRequest.getAdUnitId() != null) {
            Vungle.loadAd(tDAdRequest.getAdUnitId(), new VungleEventListener(activity, withTimeout));
            return;
        }
        getAdEventHandler().OnDidFailToLoad(activity, withTimeout, tDAdRequest, new TMAdError(1001, TapdaqError.ADAPTER_AD_CONFIG_ID_MISSING_MESSAGE));
    }

    public void showVideo(Activity activity, TDAdRequest tDAdRequest) {
        super.showVideo(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            Vungle.playAd(tDAdRequest.getAdUnitId(), new AdConfig(), new VungleEventListener(activity, tapdaqAd));
        } else {
            getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
        }
    }

    public void showRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
        super.showRewardedVideo(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            AdConfig adConfig = new AdConfig();
            adConfig.setBackButtonImmediatelyEnabled(false);
            Vungle.playAd(tDAdRequest.getAdUnitId(), adConfig, new VungleEventListener(activity, tapdaqAd));
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(tapdaqAd, new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    private class VungleEventListener implements LoadAdCallback, PlayAdCallback {
        private Activity mActivity;
        /* access modifiers changed from: private */
        public TapdaqAd mAdvert;

        VungleEventListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void onAdLoad(String str) {
            TLog.debug(String.format(Locale.ENGLISH, "onAdLoad %s", new Object[]{str}));
            this.mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    TMVungleAdapter.this.getAdEventHandler().OnDidLoad(VungleEventListener.this.mAdvert);
                }
            });
            this.mActivity = null;
        }

        public void onError(String str, final Throwable th) {
            TLog.debug(String.format(Locale.ENGLISH, "onError %s - %s", new Object[]{str, th.getMessage()}));
            final Activity activity = this.mActivity;
            TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                public void run() {
                    TMVungleAdapter.this.getAdEventHandler().OnDidFailToLoad(activity, VungleEventListener.this.mAdvert, VungleEventListener.this.mAdvert.getAdRequest(), new TMAdError(TapdaqError.VUNGLE_FAILED_TO_LOAD_AD, th.getMessage()));
                }
            });
            this.mActivity = null;
        }

        public void onAdEnd(@NonNull String str, boolean z, boolean z2) {
            if (!(this.mAdvert == null || this.mActivity == null)) {
                Activity activity = this.mActivity;
                if (z) {
                    TMVungleAdapter.this.getAdEventHandler().OnVideoComplete(this.mAdvert);
                }
                final TapdaqAd tapdaqAd = this.mAdvert;
                final boolean z3 = z2;
                final Activity activity2 = activity;
                final boolean z4 = z;
                TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                    public void run() {
                        if (z3) {
                            TMVungleAdapter.this.getAdEventHandler().OnDidClick(tapdaqAd);
                        }
                        if (tapdaqAd.getType() == 3) {
                            TMVungleAdapter.this.getAdEventHandler().OnRewardVerified(activity2, tapdaqAd, z4);
                        }
                        TMVungleAdapter.this.getAdEventHandler().OnDidClose(activity2, tapdaqAd);
                    }
                });
            }
            this.mActivity = null;
            this.mAdvert = null;
        }

        public void onAdStart(@NonNull String str) {
            if (this.mAdvert != null && this.mActivity != null) {
                final Activity activity = this.mActivity;
                TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                    public void run() {
                        TMVungleAdapter.this.getAdEventHandler().OnDidDisplay(activity, VungleEventListener.this.mAdvert);
                    }
                });
            }
        }
    }
}
