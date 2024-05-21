package com.vungle.warren;

import android.support.v4.os.ResultReceiver;

public interface PublisherDirectDownload {
    ResultReceiver getPublisherReceiver();

    void setSDKCallbackReceiver(ResultReceiver resultReceiver);
}
