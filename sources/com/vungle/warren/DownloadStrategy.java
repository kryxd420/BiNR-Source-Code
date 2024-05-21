package com.vungle.warren;

public interface DownloadStrategy {

    public interface VerificationCallback {
        void onResult(boolean z);
    }

    void isApplicationAvailable(String str, VerificationCallback verificationCallback);
}
