package com.vungle.warren.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.utility.ViewUtility;

public class VungleWebViewActivity extends Activity {
    public static final String INTENT_URL = "intent_url";
    public static final String TAG = "VungleWebViewActivity";
    private String url;
    /* access modifiers changed from: private */
    public WebView wvMain;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(16777216, 16777216);
        Resources resources = getResources();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        relativeLayout.setLayoutParams(layoutParams);
        int applyDimension = (int) TypedValue.applyDimension(1, 42.0f, resources.getDisplayMetrics());
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, applyDimension);
        relativeLayout2.setBackgroundColor(getResources().getColor(17170446));
        relativeLayout2.setLayoutParams(layoutParams2);
        int applyDimension2 = (int) TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.close, this));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(applyDimension2, applyDimension2);
        layoutParams3.addRule(11);
        layoutParams3.setMargins(applyDimension3, applyDimension3, applyDimension3, applyDimension3);
        imageView.setLayoutParams(layoutParams3);
        relativeLayout2.addView(imageView);
        relativeLayout.addView(relativeLayout2, layoutParams2);
        this.wvMain = new WebView(this);
        this.wvMain.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.setMargins(0, applyDimension, 0, 0);
        relativeLayout.addView(this.wvMain, layoutParams4);
        setContentView(relativeLayout, layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VungleWebViewActivity.this.finish();
            }
        });
        if (getIntent() != null) {
            this.url = getIntent().getStringExtra(INTENT_URL);
            configWebview();
            loadURL();
            return;
        }
        Log.e(TAG, "No url passed.");
        finish();
    }

    private void loadURL() {
        try {
            this.wvMain.loadUrl(this.url);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            finish();
        }
    }

    @TargetApi(16)
    private void configWebview() {
        WebSettings settings = this.wvMain.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(8388608);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(2);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDefaultTextEncodingName("utf-8");
        this.wvMain.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                VungleWebViewActivity.this.wvMain.loadUrl(str);
                return true;
            }
        });
        this.wvMain.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (APKDirectDownloadManager.isApkUrl(str)) {
                    Toast.makeText(VungleWebViewActivity.this, LocaleString.getLocaleText(1), 1).show();
                    APKDirectDownloadManager.download(str);
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                VungleWebViewActivity.this.startActivity(intent);
            }
        });
        this.wvMain.setLayerType(1, (Paint) null);
    }
}
