package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.model.config.TDConfigResponse;

public interface CredentialsListener {
    void onFailedToReceiveCredentials(Context context, TMAdError tMAdError);

    void onReceivedCredentials(Activity activity, TDConfigResponse tDConfigResponse);
}
