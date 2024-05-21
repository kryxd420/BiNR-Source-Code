package com.tapdaq.sdk.listeners;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.analytics.TDFrequencyTracker;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMServiceErrorHandler;
import com.tapdaq.sdk.model.rewards.TDReward;
import com.tapdaq.sdk.model.rewards.TMRewardStatsData;
import com.tapdaq.sdk.network.ResponseHandler;
import com.tapdaq.sdk.network.TMServiceClient;

public class TapdaqAdEventHandler {
    private final TMServiceClient mServiceClient;
    private final TMServiceErrorHandler mServiceErrorHandler;
    private TMStatsManager mStatsManager;

    public TapdaqAdEventHandler(TMStatsManager tMStatsManager) {
        this.mStatsManager = tMStatsManager;
        this.mServiceErrorHandler = new TMServiceErrorHandler();
        this.mServiceClient = new TMServiceClient();
    }

    public TapdaqAdEventHandler(TMStatsManager tMStatsManager, TMServiceErrorHandler tMServiceErrorHandler, TMServiceClient tMServiceClient) {
        this.mStatsManager = tMStatsManager;
        this.mServiceErrorHandler = tMServiceErrorHandler;
        this.mServiceClient = tMServiceClient;
    }

    public void OnDidLoad(TapdaqAd tapdaqAd) {
        OnDidLoad(tapdaqAd, (TDMediatedNativeAd) null);
    }

    public void OnDidLoad(TapdaqAd tapdaqAd, TDMediatedNativeAd tDMediatedNativeAd) {
        if (this.mStatsManager != null) {
            this.mStatsManager.finishAdRequest(tapdaqAd.getAdRequest(), true);
        }
        if (tapdaqAd.isWaiting()) {
            tapdaqAd.setState(TapdaqAd.AD_STATUS.LOADED);
            if (tDMediatedNativeAd != null) {
                TMListenerHandler.DidLoad(tDMediatedNativeAd, tapdaqAd.getAdRequest().getListener());
            } else {
                TMListenerHandler.DidLoad(tapdaqAd.getAdRequest().getListener());
            }
        }
    }

    public void OnDidFailToLoad(Activity activity, TapdaqAd tapdaqAd, TDAdRequest tDAdRequest, TMAdError tMAdError) {
        if (activity != null) {
            if (tapdaqAd.isWaiting()) {
                tapdaqAd.setState(TapdaqAd.AD_STATUS.FAILED);
                tDAdRequest.getAdError().addSubError(tapdaqAd.getNetwork(), tMAdError);
            }
            if (this.mStatsManager != null) {
                if (tMAdError.getErrorCode() == 1011) {
                    this.mStatsManager.sendMediationAdTimeout(tapdaqAd.getAdRequest(), Long.valueOf(tDAdRequest.getLoadTimeout()));
                }
                this.mStatsManager.finishAdRequest(tapdaqAd.getAdRequest(), false);
            }
            this.mServiceErrorHandler.ServiceError(activity, tapdaqAd.getNetwork(), tDAdRequest);
        }
    }

    public void OnDidDisplay(Context context, TapdaqAd tapdaqAd) {
        TMListenerHandler.DidDisplay(tapdaqAd.getAdRequest().getListener());
        if (this.mStatsManager != null) {
            this.mStatsManager.sendImpression(tapdaqAd.getAdRequest());
        }
        if (context != null) {
            new TDFrequencyTracker(context).trackImpression(tapdaqAd.getAdRequest());
        }
    }

    public void OnDidFailToDisplay(TapdaqAd tapdaqAd, TMAdError tMAdError) {
        TMListenerHandler.DidFailToDisplay(tapdaqAd.getAdRequest().getListener(), tMAdError);
        if (this.mStatsManager != null) {
            this.mStatsManager.sendFailToDisplayError(tapdaqAd.getAdRequest(), tMAdError);
        }
    }

    public void OnVideoComplete(TapdaqAd tapdaqAd) {
        if (this.mStatsManager != null) {
            this.mStatsManager.sendVideoComplete(tapdaqAd.getAdRequest());
        }
    }

    public void OnRewardVerified(Context context, TapdaqAd tapdaqAd, boolean z) {
        TMRewardStatsData CreateRewardStat = TMStatsManager.CreateRewardStat(tapdaqAd.getAdRequest(), z);
        TDReward tDReward = new TDReward(CreateRewardStat.getEvent_id(), tapdaqAd.getPlacementTag(), z, tapdaqAd.getAdRequest().getReward());
        if (tapdaqAd.getAdRequest().getListener() instanceof TMRewardAdListenerBase) {
            TMListenerHandler.DidVerify((TMRewardAdListenerBase) tapdaqAd.getAdRequest().getListener(), tDReward);
        }
        if (context != null && this.mServiceClient != null) {
            this.mServiceClient.reward(context, CreateRewardStat, new ResponseHandler(context, ResponseHandler.REWARD));
        }
    }

    public void OnDidClick(TapdaqAd tapdaqAd) {
        TMListenerHandler.DidClick(tapdaqAd.getAdRequest().getListener());
        if (this.mStatsManager != null) {
            this.mStatsManager.sendClick(tapdaqAd.getAdRequest());
        }
    }

    public void OnDidClose(Activity activity, TapdaqAd tapdaqAd) {
        TDMediationServiceProvider.getMediationService().refillPlacements(activity);
        tapdaqAd.reloadAd(activity, tapdaqAd.getAdRequest());
        TMListenerHandler.DidClose(tapdaqAd.getAdRequest().getListener());
    }
}
