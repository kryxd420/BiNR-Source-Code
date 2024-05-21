package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.tapdaq.sdk.TKEYS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TapdaqAdEventHandler;
import com.tapdaq.sdk.storage.StorageProvider;
import java.util.Date;
import java.util.Locale;

public class TapdaqAd {
    private TDAdRequest mAdRequest;
    private AD_STATUS mAdStatus = AD_STATUS.WAITING;
    private String mNetwork;
    /* access modifiers changed from: private */
    public TMStatsManager mStatsManager;

    public enum AD_STATUS {
        FAILED,
        LOADED,
        WAITING
    }

    public void setState(AD_STATUS ad_status) {
        this.mAdStatus = ad_status;
    }

    public AD_STATUS getState() {
        return this.mAdStatus;
    }

    public boolean isWaiting() {
        return this.mAdStatus == AD_STATUS.WAITING;
    }

    public TDAdRequest getAdRequest() {
        return this.mAdRequest;
    }

    public String getSharedId() {
        return this.mAdRequest.getWaterfallId();
    }

    public String getVersionId() {
        return this.mAdRequest.getWaterfallItem().getVersionId();
    }

    public String getPlacementTag() {
        return this.mAdRequest.getPlacement();
    }

    public String getNetwork() {
        return this.mNetwork;
    }

    public int getType() {
        return this.mAdRequest.getType();
    }

    public String getTypeString() {
        return TMAdType.getString(getType());
    }

    public void setAdListener(TMAdListenerBase tMAdListenerBase) {
        this.mAdRequest.setAdListener(tMAdListenerBase);
    }

    public TapdaqAd(TMStatsManager tMStatsManager, TDAdRequest tDAdRequest, String str) {
        this.mStatsManager = tMStatsManager;
        this.mAdRequest = tDAdRequest;
        this.mNetwork = str;
    }

    public TapdaqAd withTimeout(final Activity activity, final TDAdRequest tDAdRequest) {
        if (activity != null && tDAdRequest.getLoadTimeout() > 0) {
            new Handler(activity.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    if (TapdaqAd.this.isWaiting()) {
                        TapdaqAd.this.storeTimeout(activity);
                        TMAdError tMAdError = new TMAdError(1011, TapdaqError.ADAPTER_SDK_AD_REQUEST_TIMED_OUT_MESSAGE);
                        tDAdRequest.getAdError().addSubError(TapdaqAd.this.getNetwork(), tMAdError);
                        new TapdaqAdEventHandler(TapdaqAd.this.mStatsManager).OnDidFailToLoad(activity, TapdaqAd.this, tDAdRequest, tMAdError);
                    }
                }
            }, tDAdRequest.getLoadTimeout());
        }
        return this;
    }

    public void reloadAd(Activity activity, TDAdRequest tDAdRequest) {
        if (activity != null && Tapdaq.getInstance().config().shouldAutoReloadAds()) {
            switch (tDAdRequest.getType()) {
                case 1:
                    Tapdaq.getInstance().loadInterstitial(activity, tDAdRequest.getPlacement(), tDAdRequest.getListener());
                    return;
                case 2:
                    Tapdaq.getInstance().loadVideo(activity, tDAdRequest.getPlacement(), tDAdRequest.getListener());
                    return;
                case 3:
                    Tapdaq.getInstance().loadRewardedVideo(activity, tDAdRequest.getPlacement(), tDAdRequest.getListener());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void storeTimeout(Context context) {
        StorageProvider.getInstance(context).putLong(String.format(Locale.ENGLISH, TKEYS.AD_LOAD_TIMEOUT_FORMAT, new Object[]{getNetwork(), Integer.valueOf(getType())}), new Date().getTime());
    }
}
