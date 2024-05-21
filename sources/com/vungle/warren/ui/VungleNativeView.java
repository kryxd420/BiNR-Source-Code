package com.vungle.warren.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.VungleNativeAd;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.presenter.AdvertisementPresenter;

public class VungleNativeView extends WebView implements AdView, VungleNativeAd {
    private static final String TAG = "com.vungle.warren.ui.VungleNativeView";
    private Handler handler;
    private AdvertisementPresenter presenter;
    private final AdvertisementPresenterFactory presenterFactory = new AdvertisementPresenterFactory();

    public View renderNativeView() {
        return this;
    }

    public void setOrientation(int i) {
    }

    public void showCTAOverlay(boolean z, boolean z2, int i) {
    }

    public void updateWindow(boolean z) {
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming Flex");
        this.presenter.play();
    }

    public void onPause() {
        super.onPause();
        this.presenter.stop(false, false);
    }

    public VungleNativeView(Context context, String str, DirectDownloadAdapter directDownloadAdapter, AdvertisementPresenter.EventListener eventListener) {
        super(context);
        setLayerType(2, (Paint) null);
        this.presenter = this.presenterFactory.getAdPresenter(str, (Bundle) null, (String) null);
        if (this.presenter != null) {
            this.presenter.attach(this);
            if (directDownloadAdapter != null) {
                directDownloadAdapter.getSdkDownloadClient().setAdWebView(this);
                this.presenter.setDirectDownloadAdapter(directDownloadAdapter);
            }
            this.presenter.setEventListener(eventListener);
            this.presenter.prepare((Bundle) null);
            this.handler = new Handler();
            prepare((Bundle) null);
        } else if (eventListener != null) {
            eventListener.onError(new VungleException(10));
        }
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void prepare(Bundle bundle) {
        setWebViewClient(this.presenter.getWebViewClient());
        WebSettings settings = getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        addJavascriptInterface(new JavascriptBridge(this.presenter), "Android");
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (Build.VERSION.SDK_INT < 17) {
            setAdVisibility(true);
        } else if (Build.VERSION.SDK_INT <= 19) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT > 19) {
            setVisibility(4);
        }
    }

    public void finishDisplayingAd() {
        loadUrl("about:blank");
        this.presenter.stop(false, true);
    }

    public void setAdVisibility(boolean z) {
        this.presenter.setAdVisibility(z);
    }

    public void playVideo(Uri uri, boolean z) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a video player.");
    }

    public void showWebsite(String str) {
        loadUrl(str);
    }

    public String getWebsiteUrl() {
        return getUrl();
    }

    public void close() {
        if (this.presenter.handleExit((String) null)) {
            finishDisplayingAd();
        }
    }

    public void showCloseButton() {
        throw new UnsupportedOperationException("VungleNativeView does not implement a close button");
    }

    public void open(String str) {
        String str2 = TAG;
        Log.v(str2, "Opening " + str);
        try {
            Intent parseUri = Intent.parseUri(str, 0);
            parseUri.addFlags(268435456);
            getContext().startActivity(parseUri);
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Cannot open url " + e.getLocalizedMessage());
        }
    }

    public void showDialog(String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a dialog.");
    }

    public void setVisibility(boolean z) {
        setVisibility(z ? 0 : 4);
    }
}
