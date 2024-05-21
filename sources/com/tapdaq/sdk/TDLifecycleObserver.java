package com.tapdaq.sdk;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.helpers.TLog;

public class TDLifecycleObserver implements LifecycleObserver {
    private static TDLifecycleObserver mInstance;

    public static void RegisterCallbacks() {
        if (mInstance == null) {
            mInstance = new TDLifecycleObserver();
        }
        ProcessLifecycleOwner.get().getLifecycle().addObserver(mInstance);
    }

    public static void RemoveCallbacks() {
        if (mInstance != null) {
            ProcessLifecycleOwner.get().getLifecycle().removeObserver(mInstance);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onForeground() {
        TLog.debug("onForeground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground() {
        TLog.debug("onBackground");
        TMStatsManager statsManager = TDMediationServiceProvider.getMediationService().getStatsManager();
        if (statsManager != null) {
            statsManager.sendEvents();
        }
    }
}
