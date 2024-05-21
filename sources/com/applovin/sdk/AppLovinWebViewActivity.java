package com.applovin.sdk;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.p;
import java.util.Set;

public class AppLovinWebViewActivity extends Activity {
    public static final String EVENT_DISMISSED_VIA_BACK_BUTTON = "dismissed_via_back_button";
    public static final String INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON = "immersive_mode_on";
    public static final String INTENT_EXTRA_KEY_SDK_KEY = "sdk_key";
    private WebView a;
    /* access modifiers changed from: private */
    public EventListener b;

    public interface EventListener {
        void onReceivedEvent(String str);
    }

    public void loadUrl(String str, EventListener eventListener) {
        this.b = eventListener;
        this.a.loadUrl(str);
    }

    public void onBackPressed() {
        if (this.b != null) {
            this.b.onReceivedEvent(EVENT_DISMISSED_VIA_BACK_BUTTON);
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(INTENT_EXTRA_KEY_SDK_KEY);
        if (!TextUtils.isEmpty(stringExtra)) {
            final AppLovinSdk instance = AppLovinSdk.getInstance(stringExtra, new AppLovinSdkSettings(), getApplicationContext());
            this.a = new WebView(this);
            setContentView(this.a);
            WebSettings settings = this.a.getSettings();
            settings.setSupportMultipleWindows(false);
            settings.setJavaScriptEnabled(true);
            this.a.setVerticalScrollBarEnabled(true);
            this.a.setHorizontalScrollBarEnabled(true);
            this.a.setScrollBarStyle(33554432);
            this.a.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    Uri parse = Uri.parse(str);
                    String scheme = parse.getScheme();
                    String host = parse.getHost();
                    String path = parse.getPath();
                    p logger = instance.getLogger();
                    logger.a("AppLovinWebViewActivity", "Handling url load: " + str);
                    if (!"applovin".equalsIgnoreCase(scheme) || !"com.applovin.sdk".equalsIgnoreCase(host) || AppLovinWebViewActivity.this.b == null) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    if (!path.endsWith("webview_event")) {
                        return true;
                    }
                    Set<String> queryParameterNames = parse.getQueryParameterNames();
                    String str2 = queryParameterNames.isEmpty() ? "" : (String) queryParameterNames.toArray()[0];
                    if (k.b(str2)) {
                        String queryParameter = parse.getQueryParameter(str2);
                        p logger2 = instance.getLogger();
                        logger2.a("AppLovinWebViewActivity", "Parsed WebView event parameter name: " + str2 + " and value: " + queryParameter);
                        AppLovinWebViewActivity.this.b.onReceivedEvent(queryParameter);
                        return true;
                    }
                    instance.getLogger().d("AppLovinWebViewActivity", "Failed to parse WebView event parameter");
                    return true;
                }
            });
            if (getIntent().getBooleanExtra(INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON, false)) {
                getWindow().getDecorView().setSystemUiVisibility(5894);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("No SDK key specified");
    }
}
