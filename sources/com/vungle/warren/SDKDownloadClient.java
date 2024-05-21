package com.vungle.warren;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.os.ResultReceiver;
import android.webkit.WebView;
import com.vungle.warren.DirectDownloadAdapter;

@SuppressLint({"RestrictedApi"})
public class SDKDownloadClient {
    /* access modifiers changed from: private */
    public WebView adWebView;
    /* access modifiers changed from: private */
    public ValidationCheck appMarketValidation;
    private ResultReceiver callBackReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) {
        /* access modifiers changed from: protected */
        @SuppressLint({"SetTextI18n"})
        public void onReceiveResult(int i, Bundle bundle) {
            if (i == 6) {
                bundle.getString("PACKAGE_NAME");
                boolean z = bundle.getBoolean(DirectDownloadAdapter.RESULT);
                boolean z2 = bundle.getBoolean(DirectDownloadAdapter.IN_APP_PURCHASE, false);
                if (SDKDownloadClient.this.installStatusCheck != null) {
                    SDKDownloadClient.this.installStatusCheck.isAppInstalled(z, z2);
                }
            } else if (i == 16) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0})");
                }
            } else if (i == 17) {
                float f = ((float) bundle.getInt(DirectDownloadAdapter.PROGRESS)) / 100.0f;
                if (SDKDownloadClient.this.adWebView != null) {
                    WebView access$100 = SDKDownloadClient.this.adWebView;
                    access$100.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0,\"percentage\":" + f + "})");
                }
            } else if (i == 18) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":0})");
                }
            } else if (i == 19) {
                boolean z3 = bundle.getBoolean(DirectDownloadAdapter.RESULT, true);
                if (SDKDownloadClient.this.adWebView == null) {
                    return;
                }
                if (!z3) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":-1})");
                } else {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":1})");
                }
            } else if (i != 26) {
                if (i == 36) {
                    bundle.getString("PACKAGE_NAME");
                    boolean z4 = bundle.getBoolean(DirectDownloadAdapter.RESULT);
                    if (SDKDownloadClient.this.appMarketValidation != null) {
                        SDKDownloadClient.this.appMarketValidation.validateAppPresenceInMarket(z4);
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public InstallStatusCheck installStatusCheck;
    private String pkgName;
    private ResultReceiver sendingReceiver;

    public interface InstallStatusCheck {
        void isAppInstalled(boolean z, boolean z2);
    }

    public interface ValidationCheck {
        void validateAppPresenceInMarket(boolean z);
    }

    public SDKDownloadClient(String str) {
        this.pkgName = str;
    }

    public void setSendingReceiver(ResultReceiver resultReceiver) {
        this.sendingReceiver = resultReceiver;
    }

    public void setAdWebView(WebView webView) {
        this.adWebView = webView;
    }

    public void sendDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(11, bundle);
    }

    public void sendOpenPackageRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(51, bundle);
    }

    public void cancelDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(21, bundle);
    }

    public void installStatusRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(1, bundle);
    }

    public ResultReceiver getCallBackReceiver() {
        return this.callBackReceiver;
    }

    public void sendADDisplayingNotify(boolean z, DirectDownloadAdapter.CONTRACT_TYPE contract_type) {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        bundle.putString(DirectDownloadAdapter.ADTYPE, contract_type.name());
        this.sendingReceiver.send(z ? 71 : 72, bundle);
    }

    /* access modifiers changed from: protected */
    public void sendValidation(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", str);
        this.sendingReceiver.send(31, bundle);
    }

    private void requestDetailOpen() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(61, bundle);
    }

    public void setAppMarketValidation(ValidationCheck validationCheck) {
        this.appMarketValidation = validationCheck;
    }

    public void setInstallStatusCheck(InstallStatusCheck installStatusCheck2) {
        this.installStatusCheck = installStatusCheck2;
    }

    public void cleanUp() {
        this.adWebView = null;
    }
}
