package com.applovin.sdk;

public interface AppLovinSdkConfiguration {

    public enum ConsentDialogState {
        UNKNOWN,
        APPLIES,
        DOES_NOT_APPLY
    }

    ConsentDialogState getConsentDialogState();
}
