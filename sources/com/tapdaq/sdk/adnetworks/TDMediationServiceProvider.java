package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.listeners.TMInitListener;
import com.tapdaq.sdk.listeners.TMInitListenerBase;

public class TDMediationServiceProvider {
    private static TMService mService;

    public static void setMediationService(TMService tMService) {
        mService = tMService;
    }

    public static TMService create(Activity activity, TMInitListenerBase tMInitListenerBase) {
        if (mService == null) {
            mService = new TMService(new TMStatsManager(activity), new TDConfigService(tMInitListenerBase));
        }
        return mService;
    }

    public static TMService getMediationService() {
        if (mService == null) {
            return new TMService((TMStatsManager) null, new TDConfigService(new TMInitListener()));
        }
        return mService;
    }
}
