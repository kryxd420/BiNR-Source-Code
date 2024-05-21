package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import com.tapdaq.sdk.common.TMAdError;

public interface AdapterListener {
    void onInitFailure(Activity activity, int i, TMAdError tMAdError);

    void onInitSuccess(Activity activity, int i);
}
