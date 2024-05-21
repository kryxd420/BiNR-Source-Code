package com.tapdaq.sdk.common;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.TKEYS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.AdapterListener;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.listeners.TapdaqAdEventHandler;
import com.tapdaq.sdk.model.TMAdSize;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapdaq.sdk.storage.StorageBase;
import com.tapdaq.sdk.storage.StorageProvider;
import com.tapdaq.sdk.tasks.TDTaskManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TMAdapter {
    private TapdaqAdEventHandler mAdEventHandler;
    private final int mAdapterID;
    protected Map<String, Object> mAds;
    /* access modifiers changed from: protected */
    public Map<String, Boolean> mFailReward;
    private Handler mHandler;
    protected TDNetwork mNetwork;
    private AdapterListener mServiceListener;
    private TMStatsManager mStatsManager;
    private TDAdapterStatus mStatus;
    private List<String> mTestDevices;

    public boolean canDisplayInterstitial(Context context) {
        return false;
    }

    public boolean canDisplayNative(Context context) {
        return false;
    }

    public boolean canDisplayOfferwall(Context context) {
        return false;
    }

    public boolean canDisplayRewardedVideo(Context context) {
        return false;
    }

    public boolean canDisplayVideo(Context context) {
        return false;
    }

    public void destroyBanner(View view) {
    }

    public String getAdapterVersion() {
        return "";
    }

    public String getSdkVersion() {
        return "";
    }

    public boolean hasCredentials(Context context) {
        return true;
    }

    public boolean hasRequiredActivities(Context context) {
        return true;
    }

    public boolean isBannerAvailable(Context context, TMAdSize tMAdSize) {
        return false;
    }

    public boolean isExpired(Long l) {
        return false;
    }

    public ViewGroup loadAd(Activity activity, TMAdSize tMAdSize, TDAdRequest tDAdRequest) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void loadInterstitial(Activity activity, TDAdRequest tDAdRequest) {
    }

    public void loadNativeAd(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void loadOfferwall(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void loadRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void loadVideo(Activity activity, TDAdRequest tDAdRequest) {
    }

    public void onPause(Context context) {
    }

    public void onResume(Context context) {
    }

    public void registerView(View view, TDMediatedNativeAd tDMediatedNativeAd) {
    }

    public void sendIAP(Context context, String str, String str2, String str3, String str4) {
    }

    public void setConsent(Context context, boolean z) {
    }

    public void setIsAgeRestrictedUser(Context context, boolean z) {
    }

    public void setUserSubjectToGDPR(Context context, STATUS status) {
    }

    /* access modifiers changed from: protected */
    public void showInterstitial(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void showOfferwall(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void showRewardedVideo(Activity activity, TDAdRequest tDAdRequest) {
    }

    /* access modifiers changed from: protected */
    public void showVideo(Activity activity, TDAdRequest tDAdRequest) {
    }

    public TMAdapter() {
        this.mStatus = TDAdapterStatus.DISABLED;
        this.mTestDevices = new ArrayList();
        this.mFailReward = new HashMap();
        this.mAds = new HashMap();
        this.mAdapterID = -1;
    }

    public TMAdapter(int i) {
        this.mStatus = TDAdapterStatus.DISABLED;
        this.mTestDevices = new ArrayList();
        this.mFailReward = new HashMap();
        this.mAds = new HashMap();
        this.mAdapterID = i;
        setState((Activity) null, TDAdapterStatus.NOT_INTEGRATED);
    }

    public TMAdapter(TMStatsManager tMStatsManager, Handler handler) {
        this.mStatus = TDAdapterStatus.DISABLED;
        this.mTestDevices = new ArrayList();
        this.mFailReward = new HashMap();
        this.mAds = new HashMap();
        this.mAdapterID = -1;
        this.mStatsManager = tMStatsManager;
        this.mHandler = handler;
    }

    public void initialise(Activity activity, TDNetwork tDNetwork) {
        this.mNetwork = tDNetwork;
        if (activity != null) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(activity.getMainLooper());
            }
            if (!hasCredentials(activity)) {
                setState(activity, TDAdapterStatus.FAILED_MISSING_CREDENTIALS);
            } else if (!hasRequiredActivities(activity)) {
                setState(activity, TDAdapterStatus.FAILED_MISSING_ACTIVITY);
            } else {
                setState(activity, TDAdapterStatus.WAITING);
            }
        }
    }

    public TDNetwork getNetwork() {
        return this.mNetwork;
    }

    public final String getName() {
        return TMMediationNetworks.getName(getID());
    }

    public int getID() {
        return this.mAdapterID;
    }

    public boolean isAdapterVersionCorrect() {
        boolean equalsIgnoreCase = getAdapterVersion().equalsIgnoreCase(Tapdaq.getSDKVersion());
        if (!equalsIgnoreCase) {
            setState((Activity) null, TDAdapterStatus.FAILED_ADAPTER_MISMATCH);
        }
        return equalsIgnoreCase;
    }

    public final TDAdapterStatus getStatus() {
        return this.mStatus;
    }

    /* access modifiers changed from: protected */
    public boolean isWaitingState() {
        return this.mStatus == TDAdapterStatus.WAITING;
    }

    /* access modifiers changed from: protected */
    public void setState(final Activity activity, TDAdapterStatus tDAdapterStatus) {
        this.mStatus = tDAdapterStatus;
        switch (tDAdapterStatus) {
            case READY:
                this.mServiceListener.onInitSuccess(activity, getID());
                return;
            case FAILED:
            case FAILED_MISSING_CREDENTIALS:
            case FAILED_MISSING_ACTIVITY:
                AdapterListener adapterListener = this.mServiceListener;
                int id = getID();
                adapterListener.onInitFailure(activity, id, new TMAdError(TapdaqError.ADAPTER_SDK_FAILED_TO_INITIALISE, "Ad network SDK failed to initialise - " + getName()));
                return;
            case TIMEOUT:
                AdapterListener adapterListener2 = this.mServiceListener;
                int id2 = getID();
                adapterListener2.onInitFailure(activity, id2, new TMAdError(TapdaqError.ADAPTER_SDK_TIMED_OUT, "Ad network SDK initialisation timed out - " + getName()));
                if (this.mStatsManager != null) {
                    String str = null;
                    if (!(getNetwork() == null || getNetwork().getCredentials() == null)) {
                        str = getNetwork().getCredentials().getVersionId();
                    }
                    this.mStatsManager.sendInitTimeout(getName(), str, Long.valueOf(getSdkTimeout()));
                    return;
                }
                return;
            case WAITING:
                if (getSdkTimeout() > 0 && this.mHandler != null) {
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (TMAdapter.this.isWaitingState()) {
                                TMAdapter.this.setState(activity, TDAdapterStatus.TIMEOUT);
                            }
                        }
                    }, getSdkTimeout());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public String getAppId() {
        if (getNetwork() == null || getNetwork().getCredentials() == null || getNetwork().getCredentials().getAppId() == null) {
            return null;
        }
        return getNetwork().getCredentials().getAppId();
    }

    /* access modifiers changed from: protected */
    public String getAppKey() {
        if (getNetwork() == null || getNetwork().getCredentials() == null || getNetwork().getCredentials().getAppKey() == null) {
            return null;
        }
        return getNetwork().getCredentials().getAppKey();
    }

    public long getSdkTimeout() {
        if (this.mNetwork == null || this.mNetwork.getConfig() == null) {
            return 60000;
        }
        return this.mNetwork.getConfig().getSdkTimeout();
    }

    public TMAdapter setTestDevices(Context context, List<String> list) {
        this.mTestDevices = list;
        return this;
    }

    /* access modifiers changed from: protected */
    public String[] getTestDevices(Context context) {
        if (this.mTestDevices != null) {
            return (String[]) this.mTestDevices.toArray(new String[this.mTestDevices.size()]);
        }
        return new String[0];
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.mServiceListener = adapterListener;
    }

    public void setStatsManager(TMStatsManager tMStatsManager) {
        this.mStatsManager = tMStatsManager;
    }

    /* access modifiers changed from: protected */
    public TMStatsManager getStatsManager() {
        return this.mStatsManager;
    }

    /* access modifiers changed from: protected */
    public TapdaqAdEventHandler getAdEventHandler() {
        if (this.mAdEventHandler != null) {
            return this.mAdEventHandler;
        }
        this.mAdEventHandler = new TapdaqAdEventHandler(getStatsManager());
        return this.mAdEventHandler;
    }

    public boolean isInitialised(Context context) {
        return this.mStatus == TDAdapterStatus.READY && context != null && hasCredentials(context) && hasRequiredActivities(context);
    }

    public boolean isSuspended(Context context, TDAdRequest tDAdRequest) {
        String format = String.format(Locale.ENGLISH, TKEYS.AD_LOAD_TIMEOUT_FORMAT, new Object[]{getName(), Integer.valueOf(tDAdRequest.getType())});
        StorageBase instance = StorageProvider.getInstance(context);
        if (instance.contains(format)) {
            if (new Date().getTime() - instance.getLong(format) < tDAdRequest.getWaterfallItem().getSuspendTime()) {
                return true;
            }
        }
        return false;
    }

    public boolean canDisplayAdType(Context context, int i) {
        switch (i) {
            case 0:
                if (isBannerAvailable(context, TMBannerAdSizes.STANDARD) || isBannerAvailable(context, TMBannerAdSizes.MEDIUM_RECT) || isBannerAvailable(context, TMBannerAdSizes.LEADERBOARD) || isBannerAvailable(context, TMBannerAdSizes.LARGE) || isBannerAvailable(context, TMBannerAdSizes.FULL) || isBannerAvailable(context, TMBannerAdSizes.SMART) || isBannerAvailable(context, TMBannerAdSizes.SKYSCRAPER)) {
                    return true;
                }
                return false;
            case 1:
                return canDisplayInterstitial(context);
            case 2:
                return canDisplayVideo(context);
            case 3:
                return canDisplayRewardedVideo(context);
            case 4:
                return canDisplayNative(context);
            case 5:
                return canDisplayOfferwall(context);
            default:
                return false;
        }
    }

    public <T> T getAd(Class<T> cls, TDAdRequest tDAdRequest) {
        T t = this.mAds.get(tDAdRequest.getWaterfallId());
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    public void storeAd(Object obj, TDAdRequest tDAdRequest) {
        this.mAds.put(tDAdRequest.getWaterfallId(), obj);
    }

    public boolean isAdReady(Activity activity, TDAdRequest tDAdRequest) {
        return this.mAds.containsKey(tDAdRequest.getWaterfallId());
    }

    public void removeAd(TDAdRequest tDAdRequest) {
        if (this.mAds.containsKey(tDAdRequest.getWaterfallId())) {
            this.mAds.remove(tDAdRequest.getWaterfallId());
        }
    }

    public void loadAd(final Activity activity, final TDAdRequest tDAdRequest) {
        TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
            public void run() {
                switch (tDAdRequest.getType()) {
                    case 1:
                        TMAdapter.this.loadInterstitial(activity, tDAdRequest);
                        return;
                    case 2:
                        TMAdapter.this.loadVideo(activity, tDAdRequest);
                        return;
                    case 3:
                        TMAdapter.this.loadRewardedVideo(activity, tDAdRequest);
                        return;
                    case 4:
                        TMAdapter.this.loadNativeAd(activity, tDAdRequest);
                        return;
                    case 5:
                        TMAdapter.this.loadOfferwall(activity, tDAdRequest);
                        return;
                    default:
                        TLog.debug("Unknown AdType Load Attempt");
                        return;
                }
            }
        });
    }

    public void showAd(Activity activity, TDAdRequest tDAdRequest) {
        int type = tDAdRequest.getType();
        if (type != 5) {
            switch (type) {
                case 1:
                    showInterstitial(activity, tDAdRequest);
                    return;
                case 2:
                    showVideo(activity, tDAdRequest);
                    return;
                case 3:
                    showRewardedVideo(activity, tDAdRequest);
                    return;
                default:
                    TLog.debug("Unknown AdType Show Attempt");
                    TMListenerHandler.DidFailToDisplay(tDAdRequest.getListener(), new TMAdError(TapdaqError.UNABLE_TO_DISPLAY_UNKNOWN_AD_TYPE, TapdaqError.UNABLE_TO_DISPLAY_UNKNOWN_AD_TYPE_MESSAGE));
                    return;
            }
        } else {
            showOfferwall(activity, tDAdRequest);
        }
    }
}
