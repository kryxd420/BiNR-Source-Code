package com.unity3d.ads.api;

import com.unity3d.ads.adunit.AdUnitError;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.webplayer.WebPlayerError;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebPlayer {
    private static JSONObject _webPlayerEventSettings;
    private static JSONObject _webPlayerSettings;
    private static JSONObject _webSettings;

    public static JSONObject getWebPlayerSettings() {
        return _webPlayerSettings;
    }

    public static JSONObject getWebSettings() {
        return _webSettings;
    }

    public static JSONObject getWebPlayerEventSettings() {
        return _webPlayerEventSettings;
    }

    @WebViewExposed
    public static void setUrl(final String str, WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
                        AdUnit.getAdUnitActivity().getWebPlayer().loadUrl(str);
                    }
                }
            });
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void setData(final String str, final String str2, final String str3, WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
                        AdUnit.getAdUnitActivity().getWebPlayer().loadData(str, str2, str3);
                    }
                }
            });
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void setDataWithUrl(final String str, final String str2, final String str3, final String str4, WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
                        AdUnit.getAdUnitActivity().getWebPlayer().loadDataWithBaseURL(str, str2, str3, str4, (String) null);
                    }
                }
            });
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void setSettings(JSONObject jSONObject, JSONObject jSONObject2, WebViewCallback webViewCallback) {
        _webSettings = jSONObject;
        _webPlayerSettings = jSONObject2;
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setEventSettings(JSONObject jSONObject, WebViewCallback webViewCallback) {
        _webPlayerEventSettings = jSONObject;
        if (!(AdUnit.getAdUnitActivity() == null || AdUnit.getAdUnitActivity().getWebPlayer() == null)) {
            AdUnit.getAdUnitActivity().getWebPlayer().setEventSettings(jSONObject);
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void clearSettings(WebViewCallback webViewCallback) {
        _webSettings = null;
        _webPlayerSettings = null;
        _webPlayerEventSettings = null;
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getErroredSettings(WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
            Map<String, String> erroredSettings = AdUnit.getAdUnitActivity().getWebPlayer().getErroredSettings();
            JSONObject jSONObject = new JSONObject();
            try {
                for (Map.Entry next : erroredSettings.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            } catch (Exception e) {
                DeviceLog.exception("Error forming JSON object", e);
            }
            webViewCallback.invoke(jSONObject);
        } else {
            webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void sendEvent(JSONArray jSONArray, WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (AdUnit.getAdUnitActivity().getWebPlayer() != null) {
            AdUnit.getAdUnitActivity().getWebPlayer().sendEvent(jSONArray);
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
        }
    }
}
