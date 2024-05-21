package com.unity3d.ads.api;

import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class Listener {
    @WebViewExposed
    public static void sendReadyEvent(final String str, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsReady(str);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendStartEvent(final String str, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsStart(str);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendFinishEvent(final String str, final String str2, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsFinish(str, UnityAds.FinishState.valueOf(str2));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendClickEvent(final String str, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null && (UnityAds.getListener() instanceof IUnityAdsExtendedListener)) {
                    ((IUnityAdsExtendedListener) UnityAds.getListener()).onUnityAdsClick(str);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendPlacementStateChangedEvent(final String str, final String str2, final String str3, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null && (UnityAds.getListener() instanceof IUnityAdsExtendedListener)) {
                    ((IUnityAdsExtendedListener) UnityAds.getListener()).onUnityAdsPlacementStateChanged(str, UnityAds.PlacementState.valueOf(str2), UnityAds.PlacementState.valueOf(str3));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendErrorEvent(final String str, final String str2, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsError(UnityAds.UnityAdsError.valueOf(str), str2);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
}
