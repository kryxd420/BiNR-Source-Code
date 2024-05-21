package com.vungle.warren;

public class DirectDownloadAdapter {
    public static final String ADTYPE = "ADTYPE";
    public static final int CHECK_INSTALL_REQUEST = 1;
    public static final int CHECK_INSTALL_RESPONSE = 6;
    public static final int DETAIL_OPEN_REQUEST = 61;
    public static final int DETAIL_OPEN_RESPONSE = 66;
    public static final String DIRECT_DOWNLOAD_DOWNLOADING_TEXT = "DIRECT_DOWNLOAD_DOWNLOADING_TEXT";
    public static final String DIRECT_DOWNLOAD_IAP_TEXT = "DIRECT_DOWNLOAD_IAP_TEXT";
    public static final String DIRECT_DOWNLOAD_INSTALLING_TEXT = "DIRECT_DOWNLOAD_INSTALLING_TEXT";
    public static final String DIRECT_DOWNLOAD_INSTALL_TEXT = "DIRECT_DOWNLOAD_INSTALL_TEXT";
    public static final String DIRECT_DOWNLOAD_OPEN_TEXT = "DIRECT_DOWNLOAD_OPEN_TEXT";
    public static final int DOWNLOAD_CANCEL_REQUEST = 21;
    public static final int DOWNLOAD_CANCEL_RESPONSE = 26;
    public static final int DOWNLOAD_INSTALL_REQUEST = 11;
    public static final int DOWNLOAD_INSTALL_RESPONSE = 19;
    public static final int DOWNLOAD_START_RESPONSE = 16;
    public static final int INSTALL_START_RESPONSE = 18;
    public static final String IN_APP_PURCHASE = "IN_APP_PURCHASE";
    public static final String PACKAGE_NAME = "PACKAGE_NAME";
    public static final int PACKAGE_OPEN_REQUEST = 51;
    public static final int PACKAGE_OPEN_RESPONSE = 56;
    public static final String PROGRESS = "PROGRESS";
    public static final int PROGRESS_RESPONSE = 17;
    public static final String RESULT = "RESULT";
    public static final int VALIDATION_REQUEST = 31;
    public static final int VALIDATION_RESPONSE = 36;
    public static final int VIDEO_80_OVER_DISPLAYING_NOTI = 72;
    public static final int VIDEO_START_DISPLAYING_NOTI = 71;
    private PublisherDirectDownload pubsDownloadClient;
    private SDKDownloadClient sdkDownloadClient;

    public enum CONTRACT_TYPE {
        CPI,
        CPM,
        CPCV
    }

    public DirectDownloadAdapter(PublisherDirectDownload publisherDirectDownload, String str) {
        this.sdkDownloadClient = new SDKDownloadClient(str);
        this.pubsDownloadClient = publisherDirectDownload;
        this.pubsDownloadClient.setSDKCallbackReceiver(getSdkDownloadClient().getCallBackReceiver());
        this.sdkDownloadClient.setSendingReceiver(getPubsDownloadClient().getPublisherReceiver());
    }

    public SDKDownloadClient getSdkDownloadClient() {
        return this.sdkDownloadClient;
    }

    public PublisherDirectDownload getPubsDownloadClient() {
        return this.pubsDownloadClient;
    }
}
