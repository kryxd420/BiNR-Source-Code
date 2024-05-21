package com.tapdaq.sdk.listeners;

import android.content.Context;
import com.tapdaq.sdk.TDState;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDConfigService;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.tasks.TDTaskManager;

public abstract class TMInitListenerBase {
    private TDConfigService mConfigService;
    /* access modifiers changed from: private */
    public TMAdError mError = new TMAdError(10, TapdaqError.TAPDAQ_NOT_INIITIALISED_MESSAGE);

    public abstract void didFailToInitialise(TMAdError tMAdError);

    public abstract void didInitialise();

    public void setConfigService(TDConfigService tDConfigService) {
        this.mConfigService = tDConfigService;
    }

    public final void setMediationNetworksComplete(Context context, TMAdError tMAdError) {
        TLog.debug("Mediation Networks Complete");
        if (tMAdError == null) {
            this.mConfigService.setState(TDState.SUCCESS);
        } else {
            this.mConfigService.setState(TDState.FAILED);
            this.mError.addSubError("Mediation", tMAdError);
        }
        performCallback(context);
    }

    public void addError(String str, TMAdError tMAdError) {
        this.mError.addSubError(str, tMAdError);
    }

    private void performCallback(Context context) {
        switch (this.mConfigService.getState()) {
            case FAILED:
                TDTaskManager.getInstance().runOnMainThread(context, new Runnable() {
                    public void run() {
                        TMInitListenerBase.this.didFailToInitialise(TMInitListenerBase.this.mError);
                    }
                });
                return;
            case SUCCESS:
                TDTaskManager.getInstance().runOnMainThread(context, new Runnable() {
                    public void run() {
                        TMInitListenerBase.this.didInitialise();
                    }
                });
                return;
            default:
                return;
        }
    }
}
