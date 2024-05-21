package com.tapdaq.adapters.tapdaq;

import android.content.Context;
import com.tapdaq.adapters.tapdaq.ads.TDAdInterstitial;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.adapters.tapdaq.model.TMQueueID;
import com.tapdaq.adapters.tapdaq.queues.TMAdQueue;
import com.tapdaq.adapters.tapdaq.queues.TMQueueManager;
import com.tapdaq.adapters.tapdaq.storage.TMCache;
import com.tapdaq.sdk.CreativeType;
import com.tapdaq.sdk.TapdaqConfig;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import java.util.ArrayList;
import java.util.List;

public class TDAdService {
    private TMQueueManager mQueueManager;

    public TDAdService(Context context, List<TMQueueID> list, TDResponseHandler tDResponseHandler) {
        this.mQueueManager = new TMQueueManager(tDResponseHandler);
        this.mQueueManager.requestQueues(context, list);
    }

    public List<String> getPlacements(CreativeType creativeType) {
        if (this.mQueueManager != null) {
            return this.mQueueManager.getPlacements(creativeType);
        }
        return new ArrayList();
    }

    public TDAdInterstitial loadInterstitial(Context context, TapdaqConfig tapdaqConfig, String str, TMAdListenerBase tMAdListenerBase) {
        if (this.mQueueManager == null) {
            return null;
        }
        CreativeType creativeType = CreativeType.INTERSTITIAL_PORTRAIT;
        if (!TDDeviceInfo.isDevicePortrait(context)) {
            creativeType = CreativeType.INTERSTITIAL_LANDSCAPE;
        }
        TMAdQueue queue = this.mQueueManager.getQueue(str, creativeType.name());
        if (queue == null || queue.getAd(context) == null) {
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(TapdaqError.NO_AD_AVAILABLE, TapdaqError.NO_AD_AVAILABLE_MESSAGE));
            return null;
        }
        TMAd ad = queue.getAd(context);
        if (ad == null) {
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(TapdaqError.FREQUENCY_CAP_MET, TapdaqError.FREQUENCY_CAP_MET_MESSAGE));
            return null;
        } else if (ad.getVideoUrl() != null && TDActivityUtil.IsActivityAvailable(context, TMVideoInterstitialActivity.class)) {
            new TMCache().cacheVideo(context, ad.getVideoUrl(), tMAdListenerBase);
            return new TDAdInterstitial(ad);
        } else if (TDActivityUtil.IsActivityAvailable(context, TMInterstitialActivity.class)) {
            new TMCache().cache(context, ad.getImageUrl(), tMAdListenerBase);
            return new TDAdInterstitial(ad);
        } else {
            TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(310, TapdaqError.TAPDAQ_INTERSTITIAL_MISSING_MANIFEST_MESSAGE));
            return null;
        }
    }
}
