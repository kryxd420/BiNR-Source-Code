package com.vungle.warren.ui;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.JsonObject;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.SDKDownloadClient;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import java.util.Locale;

public class VungleWebClient extends WebViewClient {
    private MRAIDDelegate MRAIDDelegate;
    private Advertisement advertisement;
    private boolean collectConsent;
    private DirectDownloadAdapter directDownloadAdapter;
    private String gdprAccept;
    private String gdprBody;
    private String gdprDeny;
    private String gdprTitle;
    private Boolean isViewable;
    private WebView loadedWebView;
    private Placement placement;
    private boolean ready = false;

    public interface MRAIDDelegate {
        boolean processCommand(String str, JsonObject jsonObject);
    }

    public VungleWebClient(Advertisement advertisement2, Placement placement2, DirectDownloadAdapter directDownloadAdapter2) {
        this.advertisement = advertisement2;
        this.placement = placement2;
        this.directDownloadAdapter = directDownloadAdapter2;
    }

    public VungleWebClient(Advertisement advertisement2, Placement placement2) {
        this.advertisement = advertisement2;
        this.placement = placement2;
    }

    public void setConsentStatus(boolean z, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.collectConsent = z;
        this.gdprTitle = str;
        this.gdprBody = str2;
        this.gdprAccept = str3;
        this.gdprDeny = str4;
    }

    public void setMRAIDDelegate(MRAIDDelegate mRAIDDelegate) {
        this.MRAIDDelegate = mRAIDDelegate;
    }

    public boolean shouldOverrideUrlLoading(final WebView webView, String str) {
        Log.d(TMMediationNetworks.VUNGLE_NAME, "MRAID Command " + str);
        Uri parse = Uri.parse(str);
        if (!parse.getScheme().equals("mraid")) {
            return false;
        }
        String host = parse.getHost();
        if (host.equals("propertiesChangeCompleted") && !this.ready) {
            final JsonObject mRAIDArgs = this.advertisement.getMRAIDArgs();
            if (this.directDownloadAdapter != null) {
                this.directDownloadAdapter.getSdkDownloadClient().setInstallStatusCheck(new SDKDownloadClient.InstallStatusCheck() {
                    public void isAppInstalled(boolean z, boolean z2) {
                        mRAIDArgs.addProperty("isDirectDownload", (Boolean) true);
                        mRAIDArgs.addProperty("isDisplayIAP", Boolean.valueOf(z2));
                        mRAIDArgs.addProperty("isInstalled", Boolean.valueOf(z));
                        mRAIDArgs.addProperty("locale", Locale.getDefault().toString());
                        mRAIDArgs.addProperty("language", Locale.getDefault().getLanguage());
                        WebView webView = webView;
                        webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mRAIDArgs + ")");
                    }
                });
                this.directDownloadAdapter.getSdkDownloadClient().installStatusRequest();
            } else {
                webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mRAIDArgs + ")");
            }
            this.ready = true;
        } else if (this.MRAIDDelegate != null) {
            JsonObject jsonObject = new JsonObject();
            for (String next : parse.getQueryParameterNames()) {
                jsonObject.addProperty(next, parse.getQueryParameter(next));
            }
            if (this.MRAIDDelegate.processCommand(host, jsonObject)) {
                webView.loadUrl("javascript:window.vungle.mraidBridge.notifyCommandComplete()");
            }
        }
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        switch (this.advertisement.getAdType()) {
            case 0:
                webView.loadUrl("javascript:vungleInit({\"privacyPolicyEnabled\": \"true\"})");
                webView.loadUrl("javascript:function actionClicked(action){Android.performAction(action);};");
                return;
            case 1:
                this.loadedWebView = webView;
                this.loadedWebView.setVisibility(0);
                notifyPropertiesChange(true);
                return;
            default:
                throw new IllegalArgumentException("Unknown Client Type!");
        }
    }

    public void setReady(boolean z) {
        this.ready = z;
    }

    public void notifyPropertiesChange(boolean z) {
        if (this.loadedWebView != null) {
            JsonObject jsonObject = new JsonObject();
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty(AvidJSONUtil.KEY_WIDTH, (Number) Integer.valueOf(this.loadedWebView.getWidth()));
            jsonObject2.addProperty(AvidJSONUtil.KEY_HEIGHT, (Number) Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(AvidJSONUtil.KEY_X, (Number) 0);
            jsonObject3.addProperty(AvidJSONUtil.KEY_Y, (Number) 0);
            jsonObject3.addProperty(AvidJSONUtil.KEY_WIDTH, (Number) Integer.valueOf(this.loadedWebView.getWidth()));
            jsonObject3.addProperty(AvidJSONUtil.KEY_HEIGHT, (Number) Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject jsonObject4 = new JsonObject();
            jsonObject4.addProperty("sms", (Boolean) false);
            jsonObject4.addProperty("tel", (Boolean) false);
            jsonObject4.addProperty("calendar", (Boolean) false);
            jsonObject4.addProperty("storePicture", (Boolean) false);
            jsonObject4.addProperty("inlineVideo", (Boolean) false);
            jsonObject.add("maxSize", jsonObject2);
            jsonObject.add("screenSize", jsonObject2);
            jsonObject.add("defaultPosition", jsonObject3);
            jsonObject.add("currentPosition", jsonObject3);
            jsonObject.add("supports", jsonObject4);
            jsonObject.addProperty("placementType", this.advertisement.getTemplateType());
            if (this.isViewable != null) {
                jsonObject.addProperty("isViewable", this.isViewable);
            }
            jsonObject.addProperty("os", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
            jsonObject.addProperty("osVersion", Integer.toString(Build.VERSION.SDK_INT));
            jsonObject.addProperty("incentivized", Boolean.valueOf(this.placement.isIncentivized()));
            jsonObject.addProperty("enableBackImmediately", Boolean.valueOf(this.advertisement.getShowCloseDelay(this.placement.isIncentivized()) == 0));
            jsonObject.addProperty(MediationMetaData.KEY_VERSION, "1.0");
            if (this.collectConsent) {
                jsonObject.addProperty("consentRequired", (Boolean) true);
                jsonObject.addProperty("consentTitleText", this.gdprTitle);
                jsonObject.addProperty("consentBodyText", this.gdprBody);
                jsonObject.addProperty("consentAcceptButtonText", this.gdprAccept);
                jsonObject.addProperty("consentDenyButtonText", this.gdprDeny);
            } else {
                jsonObject.addProperty("consentRequired", (Boolean) false);
            }
            Log.d("VungleWebClient", "loadJsjavascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + z + ")");
            WebView webView = this.loadedWebView;
            webView.loadUrl("javascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + z + ")");
        }
    }

    public void setAdVisibility(boolean z) {
        if (this.loadedWebView != null) {
            this.isViewable = Boolean.valueOf(z);
            notifyPropertiesChange(false);
        }
    }
}
