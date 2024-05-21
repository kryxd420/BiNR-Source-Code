package com.applovin.mediation.adapter;

import android.app.Activity;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;

public interface MaxAdapter {

    public interface OnCompletionListener {
        void onCompletion();
    }

    String getAdapterVersion();

    String getSdkVersion();

    void initialize(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity, OnCompletionListener onCompletionListener);

    void onDestroy();
}
