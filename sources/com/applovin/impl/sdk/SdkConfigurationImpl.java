package com.applovin.impl.sdk;

import com.applovin.impl.sdk.b.b;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class SdkConfigurationImpl implements AppLovinSdkConfiguration {
    private final j a;

    public SdkConfigurationImpl(j jVar) {
        this.a = jVar;
    }

    public AppLovinSdkConfiguration.ConsentDialogState getConsentDialogState() {
        String str = (String) this.a.a(b.fp);
        return "applies".equalsIgnoreCase(str) ? AppLovinSdkConfiguration.ConsentDialogState.APPLIES : "does_not_apply".equalsIgnoreCase(str) ? AppLovinSdkConfiguration.ConsentDialogState.DOES_NOT_APPLY : AppLovinSdkConfiguration.ConsentDialogState.UNKNOWN;
    }

    public String toString() {
        return "AppLovinSdkConfiguration{consentDialogState=" + getConsentDialogState() + '}';
    }
}
