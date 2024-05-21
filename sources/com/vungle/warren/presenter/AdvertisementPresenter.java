package com.vungle.warren.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.JavascriptBridge;

public interface AdvertisementPresenter extends JavascriptBridge.ActionHandler {

    public interface EventListener {
        void onError(Throwable th);

        void onNext(String str, String str2);
    }

    void attach(AdView adView);

    void generateSaveState(Bundle bundle);

    WebViewClient getWebViewClient();

    boolean handleExit(String str);

    void initializeViewabilityTracker(int i, VideoView videoView);

    void notifyPropertiesChanged();

    void onProgressUpdate(int i);

    void play();

    void prepare(Bundle bundle);

    void reportAction(String str, @Nullable String str2);

    void reportError(String str);

    void restoreFromSave(Bundle bundle);

    void setAdVisibility(boolean z);

    void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter);

    void setEventListener(EventListener eventListener);

    void stop(boolean z, boolean z2);

    void stopViewabilityTracker();
}
