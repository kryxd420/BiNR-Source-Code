package com.tapdaq.adapters.tapdaq;

import com.tapdaq.sdk.common.TMAdError;

public interface TDResponseHandler {
    void onError(TMAdError tMAdError);

    void onSuccess();
}
