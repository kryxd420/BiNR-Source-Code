package com.applovin.sdk;

import android.app.Activity;

public interface AppLovinUserService {

    public interface OnConsentDialogDismissListener {
        void onDismiss();
    }

    void showConsentDialog(Activity activity, OnConsentDialogDismissListener onConsentDialogDismissListener);
}
