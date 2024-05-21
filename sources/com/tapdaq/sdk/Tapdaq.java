package com.tapdaq.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tapdaq.sdk.adnetworks.TDConfigService;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAdOptions;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.adnetworks.TMAdapterRegistry;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapdaq.sdk.adnetworks.TMService;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.TMReachability;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMInitListener;
import com.tapdaq.sdk.listeners.TMInitListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.listeners.TMRewardAdListenerBase;
import com.tapdaq.sdk.listeners.TMVideoAdListenerBase;
import com.tapdaq.sdk.model.waterfall.TMReward;
import com.tapdaq.sdk.tasks.TDTaskManager;
import com.unity.purchasing.googleplay.IabHelper;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class Tapdaq {
    private static Tapdaq mInstance;
    private TapdaqConfig mConfig;

    public static String getSDKVersion() {
        return "7.0.1";
    }

    private Tapdaq() {
    }

    public static synchronized Tapdaq getInstance() {
        Tapdaq tapdaq;
        synchronized (Tapdaq.class) {
            if (mInstance == null) {
                mInstance = new Tapdaq();
            }
            tapdaq = mInstance;
        }
        return tapdaq;
    }

    public void initialize(Activity activity, String str, String str2, TapdaqConfig tapdaqConfig, TMInitListenerBase tMInitListenerBase) {
        List<String> testDevices;
        if (getState() != TDState.WAITING && getState() != TDState.SUCCESS) {
            if (tMInitListenerBase == null) {
                tMInitListenerBase = new TMInitListener();
            }
            if (activity == null || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
                if (activity == null) {
                    TLog.info("Failed to initialise TAPDAQ. Activity is null");
                    tMInitListenerBase.didFailToInitialise(new TMAdError(22, TapdaqError.TAPDAQ_ACTIVITY_MISSING_MESSAGE));
                } else if (str == null || str.isEmpty()) {
                    TLog.info("Failed to initialise TAPDAQ. Application ID is null or empty");
                    tMInitListenerBase.didFailToInitialise(new TMAdError(21, TapdaqError.TAPDAQ_CREDENTIALS_MISSING_MESSAGE));
                } else if (str2 == null || str2.isEmpty()) {
                    TLog.info("Failed to initialise TAPDAQ. Client Key is null or empty");
                    tMInitListenerBase.didFailToInitialise(new TMAdError(21, TapdaqError.TAPDAQ_CREDENTIALS_MISSING_MESSAGE));
                }
            } else if (TMReachability.IsNetworkAvailable(activity)) {
                this.mConfig = tapdaqConfig == null ? new TapdaqConfig() : tapdaqConfig;
                TDLifecycleObserver.RegisterCallbacks();
                TDConfigService configService = TDMediationServiceProvider.create(activity, tMInitListenerBase).getConfigService();
                TMAdapterRegistry.RegisterAdapters();
                for (int i = 0; i < TMMediationNetworks.getTotalNetworks(); i++) {
                    if (!(tapdaqConfig == null || (testDevices = tapdaqConfig.getTestDevices(i)) == null)) {
                        setTestDevices(activity, i, testDevices);
                    }
                }
                configService.initialise(activity, str, str2);
            } else {
                tMInitListenerBase.didFailToInitialise(new TMAdError(52, TapdaqError.INTERNET_UNAVAILABLE_MESSAGE));
            }
        } else if (getState() == TDState.SUCCESS) {
            TLog.info("Tapdaq already initialised");
            if (tMInitListenerBase != null) {
                tMInitListenerBase.didInitialise();
            }
        } else {
            TLog.info("Tapdaq initialising already in progress");
        }
    }

    public TDState getState() {
        TDConfigService configService;
        TMService mediationService = TDMediationServiceProvider.getMediationService();
        if (mediationService == null || (configService = mediationService.getConfigService()) == null) {
            return TDState.DISABLED;
        }
        return configService.getState();
    }

    public boolean IsInitialised() {
        return getState() == TDState.SUCCESS;
    }

    private void setTestDevices(Context context, int i, List<String> list) {
        TMAdapter adapter = TDMediationServiceProvider.getMediationService().getAdapter(i);
        if (adapter != null) {
            adapter.setTestDevices(context, list);
            return;
        }
        TLog.error(String.format(Locale.ENGLISH, "Adapter not found %s - cannot add test devices", new Object[]{TMMediationNetworks.getName(i)}));
    }

    public void startTestActivity(Activity activity) {
        if (activity == null) {
            TLog.error("Error launching TestActivity, activity provided is null");
        } else if (TDActivityUtil.IsActivityAvailable(activity, TMTestActivity.class)) {
            activity.startActivity(new Intent(activity, TMTestActivity.class));
        }
    }

    public boolean isInterstitialReady(Activity activity, String str) {
        if (!IsInitialised()) {
            return false;
        }
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        return TDMediationServiceProvider.getMediationService().isReady(activity, 1, str);
    }

    public void loadInterstitial(Activity activity, TMAdListenerBase tMAdListenerBase) {
        loadInterstitial(activity, (String) null, tMAdListenerBase);
    }

    public void loadInterstitial(Activity activity, String str, TMAdListenerBase tMAdListenerBase) {
        if (IsInitialised()) {
            if (str == null || str.isEmpty()) {
                str = TapdaqPlacement.TDPTagDefault;
            }
            TDMediationServiceProvider.getMediationService().loadAd(activity, 1, str, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE));
    }

    public boolean isVideoReady(Activity activity, String str) {
        if (!IsInitialised()) {
            return false;
        }
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        return TDMediationServiceProvider.getMediationService().isReady(activity, 2, str);
    }

    public void loadVideo(Activity activity, TMAdListenerBase tMAdListenerBase) {
        loadVideo(activity, (String) null, tMAdListenerBase);
    }

    public void loadVideo(Activity activity, String str, TMAdListenerBase tMAdListenerBase) {
        if (IsInitialised()) {
            if (str == null || str.isEmpty()) {
                str = TapdaqPlacement.TDPTagDefault;
            }
            TDMediationServiceProvider.getMediationService().loadAd(activity, 2, str, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE));
    }

    public boolean isRewardedVideoReady(Activity activity, String str) {
        if (!IsInitialised()) {
            return false;
        }
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        return TDMediationServiceProvider.getMediationService().isReady(activity, 3, str);
    }

    public void loadRewardedVideo(Activity activity, TMAdListenerBase tMAdListenerBase) {
        loadRewardedVideo(activity, (String) null, tMAdListenerBase);
    }

    public void loadRewardedVideo(Activity activity, String str, TMAdListenerBase tMAdListenerBase) {
        if (IsInitialised()) {
            if (str == null || str.isEmpty()) {
                str = TapdaqPlacement.TDPTagDefault;
            }
            TDMediationServiceProvider.getMediationService().loadAd(activity, 3, str, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE));
    }

    public void loadOfferwall(Activity activity, TMAdListenerBase tMAdListenerBase) {
        if (IsInitialised()) {
            TDMediationServiceProvider.getMediationService().loadAd(activity, 5, TapdaqPlacement.TDPTagDefault, tMAdListenerBase);
        } else {
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE));
        }
    }

    public void loadMediatedNativeAd(Activity activity, String str, TMAdListenerBase tMAdListenerBase) {
        loadMediatedNativeAd(activity, str, (TDMediatedNativeAdOptions) null, tMAdListenerBase);
    }

    public void loadMediatedNativeAd(Activity activity, String str, TDMediatedNativeAdOptions tDMediatedNativeAdOptions, TMAdListenerBase tMAdListenerBase) {
        if (IsInitialised()) {
            TDMediationServiceProvider.getMediationService().loadAd(activity, 4, str, tDMediatedNativeAdOptions, tMAdListenerBase);
        } else {
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE));
        }
    }

    public void showInterstitial(Activity activity, TMAdListenerBase tMAdListenerBase) {
        showInterstitial(activity, (String) null, tMAdListenerBase);
    }

    public void showInterstitial(Activity activity, String str, TMAdListenerBase tMAdListenerBase) {
        showInterstitial(activity, str, false, tMAdListenerBase);
    }

    public void showInterstitial(Activity activity, String str, boolean z, TMAdListenerBase tMAdListenerBase) {
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        String str2 = str;
        if (isInterstitialReady(activity, str2)) {
            TMListenerHandler.WillDisplay(tMAdListenerBase);
            TDMediationServiceProvider.getMediationService().showAd(activity, 1, str2, (String) null, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToDisplay(tMAdListenerBase, new TMAdError(200, TapdaqError.NO_AD_LOADED_FOR_PLACEMENT_MESSAGE));
    }

    public void showVideo(Activity activity, TMVideoAdListenerBase tMVideoAdListenerBase) {
        showVideo(activity, (String) null, tMVideoAdListenerBase);
    }

    public void showVideo(Activity activity, String str, TMVideoAdListenerBase tMVideoAdListenerBase) {
        showVideo(activity, str, false, tMVideoAdListenerBase);
    }

    public void showVideo(Activity activity, String str, boolean z, TMVideoAdListenerBase tMVideoAdListenerBase) {
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        String str2 = str;
        if (isVideoReady(activity, str2)) {
            TMListenerHandler.WillDisplay(tMVideoAdListenerBase);
            TDMediationServiceProvider.getMediationService().showAd(activity, 2, str2, (String) null, tMVideoAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToDisplay(tMVideoAdListenerBase, new TMAdError(200, TapdaqError.NO_AD_LOADED_FOR_PLACEMENT_MESSAGE));
    }

    public void showRewardedVideo(Activity activity, TMRewardAdListenerBase tMRewardAdListenerBase) {
        showRewardedVideo(activity, (String) null, tMRewardAdListenerBase);
    }

    public void showRewardedVideo(Activity activity, String str, TMRewardAdListenerBase tMRewardAdListenerBase) {
        showRewardedVideo(activity, str, (String) null, (TMAdListenerBase) tMRewardAdListenerBase);
    }

    @Deprecated
    public void showRewardedVideo(Activity activity, String str, boolean z, TMAdListenerBase tMAdListenerBase) {
        showRewardedVideo(activity, str, (String) null, tMAdListenerBase);
    }

    public void showRewardedVideo(Activity activity, String str, String str2, TMAdListenerBase tMAdListenerBase) {
        if (str == null || str.isEmpty()) {
            str = TapdaqPlacement.TDPTagDefault;
        }
        String str3 = str;
        if (isRewardedVideoReady(activity, str3)) {
            TMListenerHandler.WillDisplay(tMAdListenerBase);
            TDMediationServiceProvider.getMediationService().showAd(activity, 3, str3, str2, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToDisplay(tMAdListenerBase, new TMAdError(200, TapdaqError.NO_AD_LOADED_FOR_PLACEMENT_MESSAGE));
    }

    public boolean isOfferwallReady(Activity activity) {
        if (IsInitialised()) {
            return TDMediationServiceProvider.getMediationService().isReady(activity, 5, TapdaqPlacement.TDPTagDefault);
        }
        return false;
    }

    public void showOfferwall(Activity activity, TMAdListenerBase tMAdListenerBase) {
        if (isOfferwallReady(activity)) {
            TMListenerHandler.WillDisplay(tMAdListenerBase);
            TDMediationServiceProvider.getMediationService().showAd(activity, 5, TapdaqPlacement.TDPTagDefault, (String) null, tMAdListenerBase);
            return;
        }
        TMListenerHandler.DidFailToDisplay(tMAdListenerBase, new TMAdError(200, TapdaqError.NO_AD_LOADED_FOR_PLACEMENT_MESSAGE));
    }

    @Deprecated
    public void sendIAP(Context context, String str, double d, String str2) {
        if (IsInitialised()) {
            TDMediationServiceProvider.getMediationService().getStatsManager().sendIAP(str, d, str2);
        }
    }

    public void sendIAP(Context context, Intent intent, String str, double d, String str2, String str3) {
        String str4;
        String str5;
        if (intent != null) {
            String stringExtra = intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA);
            str4 = intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE);
            str5 = stringExtra;
        } else {
            str5 = null;
            str4 = null;
        }
        sendIAP(context, str5, str4, str, d, str2, str3);
    }

    public void sendIAP(Context context, String str, String str2, String str3, double d, String str4, String str5) {
        if (IsInitialised()) {
            TMService mediationService = TDMediationServiceProvider.getMediationService();
            mediationService.getStatsManager().sendIAP(str3, d, str5);
            if (str != null && str2 != null) {
                for (TMAdapter sendIAP : mediationService.getAllAdapters()) {
                    sendIAP.sendIAP(context, str, str2, Double.toString(d), str4);
                }
            }
        }
    }

    @Deprecated
    public String getRewardId(String str) {
        return getRewardId((Activity) null, str);
    }

    public String getRewardId(Activity activity, String str) {
        TMReward reward;
        if (!IsInitialised() || (reward = TDMediationServiceProvider.getMediationService().getReward(activity, str)) == null) {
            return null;
        }
        Object custom_json = reward.getCustom_json();
        if (!(custom_json instanceof Map)) {
            return null;
        }
        Map map = (Map) custom_json;
        if (!map.containsKey("reward_id")) {
            return null;
        }
        Object obj = map.get("reward_id");
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void setUserSubjectToGDPR(Context context, STATUS status) {
        config().setUserSubjectToGDPR(status);
    }

    public STATUS isUserSubjectToGDPR(Context context) {
        return config().isUserSubjectToGDPR();
    }

    public void setContentGiven(Context context, boolean z) {
        List<TMAdapter> allAdapters;
        config().setConsentGiven(z);
        if (IsInitialised() && (allAdapters = TDMediationServiceProvider.getMediationService().getAllAdapters()) != null) {
            for (TMAdapter consent : allAdapters) {
                consent.setConsent(context, z);
            }
        }
    }

    public boolean isConsentGiven(Context context) {
        return config().isConsentGiven();
    }

    public void setIsAgeRestrictedUser(Context context, boolean z) {
        List<TMAdapter> allAdapters;
        config().setIsAgeRestrictedUser(z);
        if (IsInitialised() && (allAdapters = TDMediationServiceProvider.getMediationService().getAllAdapters()) != null) {
            for (TMAdapter isAgeRestrictedUser : allAdapters) {
                isAgeRestrictedUser.setIsAgeRestrictedUser(context, z);
            }
        }
    }

    public boolean isAgeRestrictedUser(Context context) {
        return config().isAgeRestrictedUser();
    }

    @Deprecated
    public TapdaqConfig config(Context context) {
        return config();
    }

    public TapdaqConfig config() {
        if (this.mConfig == null) {
            this.mConfig = new TapdaqConfig();
        }
        return this.mConfig;
    }

    public void onResume(Context context) {
        if (IsInitialised()) {
            for (TMAdapter onResume : TDMediationServiceProvider.getMediationService().getAllAdapters()) {
                onResume.onResume(context);
            }
        }
    }

    public void onPause(Context context) {
        if (IsInitialised()) {
            for (TMAdapter onPause : TDMediationServiceProvider.getMediationService().getAllAdapters()) {
                onPause.onPause(context);
            }
        }
    }

    public void onTerminate(Context context) {
        destroy();
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        TDTaskManager.getInstance().destroy();
        TDMediationServiceProvider.setMediationService((TMService) null);
        TDLifecycleObserver.RemoveCallbacks();
        this.mConfig = null;
        mInstance = null;
    }
}
