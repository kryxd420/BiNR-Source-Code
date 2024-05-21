package com.vungle.warren;

import com.vungle.warren.DownloadStrategy;
import com.vungle.warren.SDKDownloadClient;

public class DirectDownloadStrategy implements DownloadStrategy {
    private PublisherDirectDownload directDownload;

    public DirectDownloadStrategy(PublisherDirectDownload publisherDirectDownload) {
        this.directDownload = publisherDirectDownload;
    }

    public void isApplicationAvailable(String str, final DownloadStrategy.VerificationCallback verificationCallback) {
        DirectDownloadAdapter directDownloadAdapter = new DirectDownloadAdapter(this.directDownload, str);
        directDownloadAdapter.getSdkDownloadClient().setAppMarketValidation(new SDKDownloadClient.ValidationCheck() {
            public void validateAppPresenceInMarket(boolean z) {
                verificationCallback.onResult(z);
            }
        });
        directDownloadAdapter.getSdkDownloadClient().sendValidation(str);
    }
}
