package com.tapdaq.sdk.common;

import android.app.Activity;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.tasks.TDTaskManager;

public class TMServiceErrorHandler {
    public void ServiceError(Activity activity, String str, final TDAdRequest tDAdRequest) {
        if (tDAdRequest.getListener() instanceof TMAdListener) {
            final TMAdListener tMAdListener = (TMAdListener) tDAdRequest.getListener();
            if (tDAdRequest.getType() == 0 || !tMAdListener.shouldAutoRetry()) {
                TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                    public void run() {
                        TMListenerHandler.DidFailToLoad(tMAdListener, tDAdRequest.getAdError());
                    }
                });
                return;
            }
        }
        TDMediationServiceProvider.getMediationService().loadAd(activity, tDAdRequest);
    }
}
