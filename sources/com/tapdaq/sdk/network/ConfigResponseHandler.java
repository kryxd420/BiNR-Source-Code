package com.tapdaq.sdk.network;

import android.app.Activity;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.CredentialsListener;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TDGson;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.config.TDConfigResponse;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapdaq.sdk.tasks.TDTaskManager;
import org.json.JSONObject;

public class ConfigResponseHandler implements HttpClientBase.ResponseHandler {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public CredentialsListener mCredListener;

    public ConfigResponseHandler(Activity activity, CredentialsListener credentialsListener) {
        this.mActivity = activity;
        this.mCredListener = credentialsListener;
    }

    public void onSuccess(final JSONObject jSONObject) {
        TLog.debug("Launch Response");
        TDTaskManager.getInstance().execute(new Runnable() {
            public void run() {
                if (jSONObject != null) {
                    try {
                        TLog.debug(jSONObject.toString());
                        TDConfigResponse tDConfigResponse = (TDConfigResponse) TDGson.Create().fromJson(jSONObject.toString(), TDConfigResponse.class);
                        if (tDConfigResponse == null || tDConfigResponse.getNetworks() == null || tDConfigResponse.getNetworks().isEmpty()) {
                            ConfigResponseHandler.this.onError(new TMAdError(110, TapdaqError.NO_NETWORKS_ENABLED_MESSAGE));
                            return;
                        } else {
                            ConfigResponseHandler.this.mCredListener.onReceivedCredentials(ConfigResponseHandler.this.mActivity, tDConfigResponse);
                            return;
                        }
                    } catch (Exception e) {
                        TLog.error(e);
                    }
                }
                ConfigResponseHandler.this.onError(new TMAdError(51, TapdaqError.INVALID_SERVER_RESPONSE_MESSAGE));
            }
        });
    }

    public void onError(TMAdError tMAdError) {
        TLog.debug("ERROR");
        TLog.error(tMAdError.getErrorMessage());
        this.mCredListener.onFailedToReceiveCredentials(this.mActivity, tMAdError);
    }
}
