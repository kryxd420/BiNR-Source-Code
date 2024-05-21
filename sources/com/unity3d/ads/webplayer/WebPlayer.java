package com.unity3d.ads.webplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.misc.ViewUtilities;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebPlayer extends WebView {
    private Map<String, String> _erroredSettings;
    /* access modifiers changed from: private */
    public Method _evaluateJavascript = null;
    private JSONObject _eventSettings;

    public WebPlayer(Context context) {
        super(context);
    }

    public WebPlayer(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        super(context);
        WebSettings settings = getSettings();
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                this._evaluateJavascript = WebView.class.getMethod("evaluateJavascript", new Class[]{String.class, ValueCallback.class});
            } catch (NoSuchMethodException e) {
                DeviceLog.exception("Method evaluateJavascript not found", e);
                this._evaluateJavascript = null;
            }
        }
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(2);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.OFF);
        settings.setRenderPriority(WebSettings.RenderPriority.NORMAL);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setInitialScale(0);
        setBackgroundColor(0);
        ViewUtilities.setBackground(this, new ColorDrawable(0));
        setBackgroundResource(0);
        setSettings(jSONObject, jSONObject2);
        setWebViewClient(new WebPlayerClient());
        setWebChromeClient(new WebPlayerChromeClient());
        setDownloadListener(new WebPlayerDownloadListener());
        addJavascriptInterface(new WebPlayerBridgeInterface(), "webplayerbridge");
    }

    public void setEventSettings(JSONObject jSONObject) {
        this._eventSettings = jSONObject;
    }

    public void setSettings(JSONObject jSONObject, JSONObject jSONObject2) {
        if (this._erroredSettings != null) {
            this._erroredSettings.clear();
        }
        setTargetSettings(getSettings(), jSONObject);
        setTargetSettings(this, jSONObject2);
    }

    public Map<String, String> getErroredSettings() {
        return this._erroredSettings;
    }

    private Object setTargetSettings(Object obj, JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(next);
                    obj.getClass().getMethod(next, getTypes(jSONArray)).invoke(obj, getValues(jSONArray));
                } catch (Exception e) {
                    addErroredSetting(next, e.getMessage());
                    DeviceLog.exception("Setting errored", e);
                }
            }
        }
        return obj;
    }

    public void invokeJavascript(String str) {
        Utilities.runOnUiThread(new JavaScriptInvocation(str, this));
    }

    public void sendEvent(JSONArray jSONArray) {
        invokeJavascript("javascript:window.nativebridge.receiveEvent(" + jSONArray.toString() + ")");
    }

    private class JavaScriptInvocation implements Runnable {
        private String _jsString = null;
        private WebView _webView = null;

        public JavaScriptInvocation(String str, WebView webView) {
            this._jsString = str;
            this._webView = webView;
        }

        public void run() {
            if (this._jsString != null) {
                try {
                    if (Build.VERSION.SDK_INT >= 19) {
                        WebPlayer.this._evaluateJavascript.invoke(this._webView, new Object[]{this._jsString, null});
                        return;
                    }
                    WebPlayer.this.loadUrl(this._jsString);
                } catch (Exception e) {
                    DeviceLog.exception("Error while processing JavaScriptString", e);
                }
            } else {
                DeviceLog.error("Could not process JavaScript, the string is NULL");
            }
        }
    }

    private Class<?>[] getTypes(JSONArray jSONArray) throws JSONException, ClassNotFoundException {
        if (jSONArray == null) {
            return null;
        }
        Class<?>[] clsArr = new Class[jSONArray.length()];
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.get(i) instanceof JSONObject) {
                    Class<?> cls = Class.forName(((JSONObject) jSONArray.get(i)).getString("className"));
                    if (cls != null) {
                        clsArr[i] = cls;
                    }
                } else {
                    clsArr[i] = getPrimitiveClass(jSONArray.get(i).getClass());
                }
            }
        }
        return clsArr;
    }

    public Class<?> getPrimitiveClass(Class<?> cls) {
        String name = cls.getName();
        if (name.equals("java.lang.Byte")) {
            return Byte.TYPE;
        }
        if (name.equals("java.lang.Short")) {
            return Short.TYPE;
        }
        if (name.equals("java.lang.Integer")) {
            return Integer.TYPE;
        }
        if (name.equals("java.lang.Long")) {
            return Long.TYPE;
        }
        if (name.equals("java.lang.Character")) {
            return Character.TYPE;
        }
        if (name.equals("java.lang.Float")) {
            return Float.TYPE;
        }
        if (name.equals("java.lang.Double")) {
            return Double.TYPE;
        }
        if (name.equals("java.lang.Boolean")) {
            return Boolean.TYPE;
        }
        return name.equals("java.lang.Void") ? Void.TYPE : cls;
    }

    private Object[] getValues(JSONArray jSONArray) throws JSONException, ClassNotFoundException, NoSuchMethodException {
        Class<?> cls;
        if (jSONArray == null) {
            return null;
        }
        Object[] objArr = new Object[jSONArray.length()];
        Object[] objArr2 = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.get(i) instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                Object obj = jSONObject.get("value");
                String string = jSONObject.getString("type");
                String string2 = jSONObject.has("className") ? jSONObject.getString("className") : null;
                if (!(string2 == null || !string.equals("Enum") || (cls = Class.forName(string2)) == null)) {
                    objArr2[i] = Enum.valueOf(cls, (String) obj);
                }
            } else {
                objArr2[i] = jSONArray.get(i);
            }
        }
        if (jSONArray != null) {
            System.arraycopy(objArr2, 0, objArr, 0, jSONArray.length());
        }
        return objArr;
    }

    private void addErroredSetting(String str, String str2) {
        if (this._erroredSettings == null) {
            this._erroredSettings = new HashMap();
        }
        this._erroredSettings.put(str, str2);
    }

    /* access modifiers changed from: private */
    public boolean shouldCallSuper(String str) {
        try {
            if (this._eventSettings == null || !this._eventSettings.has(str) || !this._eventSettings.getJSONObject(str).has("callSuper")) {
                return true;
            }
            return this._eventSettings.getJSONObject(str).getBoolean("callSuper");
        } catch (Exception e) {
            DeviceLog.exception("Error getting super call status", e);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldSendEvent(String str) {
        try {
            if (this._eventSettings == null || !this._eventSettings.has(str) || !this._eventSettings.getJSONObject(str).has("sendEvent")) {
                return false;
            }
            return this._eventSettings.getJSONObject(str).getBoolean("sendEvent");
        } catch (Exception e) {
            DeviceLog.exception("Error getting send event status", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public <T> T getReturnValue(String str, Class<T> cls, T t) {
        try {
            if (this._eventSettings != null && this._eventSettings.has(str) && this._eventSettings.getJSONObject(str).has("returnValue")) {
                return cls.cast(this._eventSettings.getJSONObject(str).get("returnValue"));
            }
        } catch (Exception e) {
            DeviceLog.exception("Error getting default return value", e);
        }
        return t;
    }

    /* access modifiers changed from: private */
    public boolean hasReturnValue(String str) {
        try {
            if (this._eventSettings == null || !this._eventSettings.has(str) || !this._eventSettings.getJSONObject(str).has("returnValue")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            DeviceLog.exception("Error getting default return value", e);
            return false;
        }
    }

    private class WebPlayerClient extends WebViewClient {
        private WebPlayerClient() {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (WebPlayer.this.shouldCallSuper("onPageStarted")) {
                super.onPageStarted(webView, str, bitmap);
            }
            if (WebPlayer.this.shouldSendEvent("onPageStarted")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_STARTED, str);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (WebPlayer.this.shouldCallSuper("onPageFinished")) {
                super.onPageFinished(webView, str);
            }
            if (WebPlayer.this.shouldSendEvent("onPageFinished")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_FINISHED, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (WebPlayer.this.shouldCallSuper("onReceivedError")) {
                super.onReceivedError(webView, i, str, str2);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedError")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.ERROR, str2, str);
            }
        }

        @TargetApi(25)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (WebPlayer.this.shouldCallSuper("onReceivedError")) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedError")) {
                String str = "";
                if (!(webResourceError == null || webResourceError.getDescription() == null)) {
                    str = webResourceError.getDescription().toString();
                }
                String str2 = "";
                if (!(webResourceRequest == null || webResourceRequest.getUrl() == null)) {
                    str2 = webResourceRequest.getUrl().toString();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.ERROR, str2, str);
            }
        }

        public void onLoadResource(WebView webView, String str) {
            if (WebPlayer.this.shouldCallSuper("onLoadResource")) {
                super.onLoadResource(webView, str);
            }
            if (WebPlayer.this.shouldSendEvent("onLoadResource")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.LOAD_RESOUCE, str);
            }
        }

        @TargetApi(14)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (WebPlayer.this.shouldCallSuper("onReceivedSslError")) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedSslError")) {
                String str = "";
                if (sslError != null) {
                    str = sslError.getUrl();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SSL_ERROR, str);
            }
        }

        @TargetApi(21)
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            if (WebPlayer.this.shouldCallSuper("onReceivedClientCertRequest")) {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedClientCertRequest")) {
                String str = "";
                int i = -1;
                if (clientCertRequest != null) {
                    str = clientCertRequest.getHost();
                    i = clientCertRequest.getPort();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CLIENT_CERT_REQUEST, str, Integer.valueOf(i));
            }
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            if (WebPlayer.this.shouldCallSuper("onReceivedHttpAuthRequest")) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedHttpAuthRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HTTP_AUTH_REQUEST, str, str2);
            }
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            if (WebPlayer.this.shouldCallSuper("onScaleChanged")) {
                super.onScaleChanged(webView, f, f2);
            }
            if (WebPlayer.this.shouldSendEvent("onScaleChanged")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SCALE_CHANGED, Float.valueOf(f), Float.valueOf(f2));
            }
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (WebPlayer.this.shouldCallSuper("onReceivedLoginRequest")) {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedLoginRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.LOGIN_REQUEST, str, str2, str3);
            }
        }

        @TargetApi(21)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (WebPlayer.this.shouldCallSuper("onReceivedHttpError")) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedHttpError")) {
                String str = "";
                if (!(webResourceRequest == null || webResourceRequest.getUrl() == null)) {
                    str = webResourceRequest.getUrl().toString();
                }
                int i = -1;
                String str2 = "";
                if (webResourceResponse != null) {
                    i = webResourceResponse.getStatusCode();
                    str2 = webResourceResponse.getReasonPhrase();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HTTP_ERROR, str, str2, Integer.valueOf(i));
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        @android.annotation.TargetApi(21)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r8, android.webkit.WebResourceRequest r9) {
            /*
                r7 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "shouldOverrideUrlLoading"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r8 = super.shouldOverrideUrlLoading(r8, r9)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r8)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r2 = "shouldOverrideUrlLoading"
                boolean r8 = r8.shouldSendEvent(r2)
                r2 = 1
                if (r8 == 0) goto L_0x0040
                com.unity3d.ads.webview.WebViewApp r8 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r3 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r4 = com.unity3d.ads.webplayer.WebPlayerEvent.SHOULD_OVERRIDE_URL_LOADING
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]
                android.net.Uri r6 = r9.getUrl()
                java.lang.String r6 = r6.toString()
                r5[r0] = r6
                java.lang.String r9 = r9.getMethod()
                r5[r2] = r9
                r8.sendEvent(r3, r4, r5)
            L_0x0040:
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r9 = "shouldOverrideUrlLoading"
                boolean r8 = r8.hasReturnValue(r9)
                if (r8 == 0) goto L_0x005b
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r9 = "shouldOverrideUrlLoading"
                java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                java.lang.Object r8 = r8.getReturnValue(r9, r0, r1)
                r1 = r8
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x005b:
                boolean r8 = r1.booleanValue()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerClient.shouldOverrideUrlLoading(android.webkit.WebView, android.webkit.WebResourceRequest):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r7, java.lang.String r8) {
            /*
                r6 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "shouldOverrideUrlLoading"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r7 = super.shouldOverrideUrlLoading(r7, r8)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r2 = "shouldOverrideUrlLoading"
                boolean r7 = r7.shouldSendEvent(r2)
                r2 = 1
                if (r7 == 0) goto L_0x0031
                com.unity3d.ads.webview.WebViewApp r7 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r3 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r4 = com.unity3d.ads.webplayer.WebPlayerEvent.SHOULD_OVERRIDE_URL_LOADING
                java.lang.Object[] r5 = new java.lang.Object[r2]
                r5[r0] = r8
                r7.sendEvent(r3, r4, r5)
            L_0x0031:
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r8 = "shouldOverrideUrlLoading"
                boolean r7 = r7.hasReturnValue(r8)
                if (r7 == 0) goto L_0x004c
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r8 = "shouldOverrideUrlLoading"
                java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                java.lang.Object r7 = r7.getReturnValue(r8, r0, r1)
                r1 = r7
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x004c:
                boolean r7 = r1.booleanValue()
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public void onPageCommitVisible(WebView webView, String str) {
            if (WebPlayer.this.shouldCallSuper("onPageCommitVisible")) {
                super.onPageCommitVisible(webView, str);
            }
            if (WebPlayer.this.shouldSendEvent("onPageCommitVisible")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_COMMIT_VISIBLE, str);
            }
        }

        @TargetApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebResourceResponse shouldInterceptRequest = WebPlayer.this.shouldCallSuper("shouldInterceptRequest") ? super.shouldInterceptRequest(webView, webResourceRequest) : null;
            if (WebPlayer.this.shouldSendEvent("shouldInterceptRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOULD_INTERCEPT_REQUEST, webResourceRequest.getUrl().toString());
            }
            return shouldInterceptRequest;
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            if (WebPlayer.this.shouldCallSuper("onFormResubmission")) {
                super.onFormResubmission(webView, message, message2);
            }
            if (WebPlayer.this.shouldSendEvent("onFormResubmission")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.FORM_RESUBMISSION, new Object[0]);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideKeyEvent(android.webkit.WebView r8, android.view.KeyEvent r9) {
            /*
                r7 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "shouldOverrideKeyEvent"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r8 = super.shouldOverrideKeyEvent(r8, r9)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r8)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r2 = "shouldOverrideKeyEvent"
                boolean r8 = r8.shouldSendEvent(r2)
                r2 = 1
                if (r8 == 0) goto L_0x0044
                com.unity3d.ads.webview.WebViewApp r8 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r3 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r4 = com.unity3d.ads.webplayer.WebPlayerEvent.SHOULD_OVERRIDE_KEY_EVENT
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]
                int r6 = r9.getKeyCode()
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                r5[r0] = r6
                int r9 = r9.getAction()
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                r5[r2] = r9
                r8.sendEvent(r3, r4, r5)
            L_0x0044:
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r9 = "shouldOverrideKeyEvent"
                boolean r8 = r8.hasReturnValue(r9)
                if (r8 == 0) goto L_0x005f
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r9 = "shouldOverrideKeyEvent"
                java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                java.lang.Object r8 = r8.getReturnValue(r9, r0, r1)
                r1 = r8
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x005f:
                boolean r8 = r1.booleanValue()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerClient.shouldOverrideKeyEvent(android.webkit.WebView, android.view.KeyEvent):boolean");
        }

        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (WebPlayer.this.shouldCallSuper("onUnhandledKeyEvent")) {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
            if (WebPlayer.this.shouldSendEvent("onUnhandledKeyEvent")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.UNHANDLED_KEY_EVENT, Integer.valueOf(keyEvent.getKeyCode()), Integer.valueOf(keyEvent.getAction()));
            }
        }
    }

    @TargetApi(21)
    private class WebPlayerChromeClient extends WebChromeClient {
        private WebPlayerChromeClient() {
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            if (WebPlayer.this.shouldCallSuper("onGeolocationPermissionsShowPrompt")) {
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }
            if (WebPlayer.this.shouldSendEvent("onGeolocationPermissionsShowPrompt")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.GEOLOCATION_PERMISSIONS_SHOW, str);
            }
        }

        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (WebPlayer.this.shouldCallSuper("onPermissionRequest")) {
                super.onPermissionRequest(permissionRequest);
            }
            if (WebPlayer.this.shouldSendEvent("onPermissionRequest")) {
                String str = "";
                if (!(permissionRequest == null || permissionRequest.getOrigin() == null)) {
                    str = permissionRequest.getOrigin().toString();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PERMISSION_REQUEST, str);
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            if (WebPlayer.this.shouldCallSuper("onProgressChanged")) {
                super.onProgressChanged(webView, i);
            }
            if (WebPlayer.this.shouldSendEvent("onProgressChanged")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PROGRESS_CHANGED, Integer.valueOf(i));
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (WebPlayer.this.shouldCallSuper("onReceivedTitle")) {
                super.onReceivedTitle(webView, str);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedTitle")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_TITLE, str);
            }
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            if (WebPlayer.this.shouldCallSuper("onReceivedIcon")) {
                super.onReceivedIcon(webView, bitmap);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedIcon")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_ICON, new Object[0]);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            if (WebPlayer.this.shouldCallSuper("onReceivedTouchIconUrl")) {
                super.onReceivedTouchIconUrl(webView, str, z);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedTouchIconUrl")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_TOUCH_ICON_URL, str, Boolean.valueOf(z));
            }
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            if (WebPlayer.this.shouldCallSuper("onShowCustomView")) {
                super.onShowCustomView(view, customViewCallback);
            }
            if (WebPlayer.this.shouldSendEvent("onShowCustomView")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOW_CUSTOM_VIEW, new Object[0]);
            }
        }

        public void onHideCustomView() {
            if (WebPlayer.this.shouldCallSuper("onHideCustomView")) {
                super.onHideCustomView();
            }
            if (WebPlayer.this.shouldSendEvent("onHideCustomView")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HIDE_CUSTOM_VIEW, new Object[0]);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onCreateWindow(android.webkit.WebView r6, boolean r7, boolean r8, android.os.Message r9) {
            /*
                r5 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onCreateWindow"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r6 = super.onCreateWindow(r6, r7, r8, r9)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r6)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r2 = "onCreateWindow"
                boolean r6 = r6.shouldSendEvent(r2)
                if (r6 == 0) goto L_0x003f
                com.unity3d.ads.webview.WebViewApp r6 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r3 = com.unity3d.ads.webplayer.WebPlayerEvent.CREATE_WINDOW
                r4 = 3
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
                r4[r0] = r7
                r7 = 1
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
                r4[r7] = r8
                r7 = 2
                r4[r7] = r9
                r6.sendEvent(r2, r3, r4)
            L_0x003f:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onCreateWindow"
                boolean r6 = r6.hasReturnValue(r7)
                if (r6 == 0) goto L_0x005a
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onCreateWindow"
                java.lang.Class<java.lang.Boolean> r8 = java.lang.Boolean.class
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r0)
                java.lang.Object r6 = r6.getReturnValue(r7, r8, r9)
                r1 = r6
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x005a:
                boolean r6 = r1.booleanValue()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onCreateWindow(android.webkit.WebView, boolean, boolean, android.os.Message):boolean");
        }

        public void onRequestFocus(WebView webView) {
            if (WebPlayer.this.shouldCallSuper("onRequestFocus")) {
                super.onRequestFocus(webView);
            }
            if (WebPlayer.this.shouldSendEvent("onRequestFocus")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.REQUEST_FOCUS, new Object[0]);
            }
        }

        public void onCloseWindow(WebView webView) {
            if (WebPlayer.this.shouldCallSuper("onCloseWindow")) {
                super.onCloseWindow(webView);
            }
            if (WebPlayer.this.shouldSendEvent("onCloseWindow")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CLOSE_WINDOW, new Object[0]);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onJsAlert(android.webkit.WebView r7, java.lang.String r8, java.lang.String r9, android.webkit.JsResult r10) {
            /*
                r6 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onJsAlert"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r7 = super.onJsAlert(r7, r8, r9, r10)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r2 = "onJsAlert"
                boolean r7 = r7.shouldSendEvent(r2)
                r2 = 1
                if (r7 == 0) goto L_0x0037
                com.unity3d.ads.webview.WebViewApp r7 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r3 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r4 = com.unity3d.ads.webplayer.WebPlayerEvent.JS_ALERT
                r5 = 3
                java.lang.Object[] r5 = new java.lang.Object[r5]
                r5[r0] = r8
                r5[r2] = r9
                r8 = 2
                r5[r8] = r10
                r7.sendEvent(r3, r4, r5)
            L_0x0037:
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r8 = "onJsAlert"
                boolean r7 = r7.hasReturnValue(r8)
                if (r7 == 0) goto L_0x0052
                com.unity3d.ads.webplayer.WebPlayer r7 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r8 = "onJsAlert"
                java.lang.Class<java.lang.Boolean> r9 = java.lang.Boolean.class
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r2)
                java.lang.Object r7 = r7.getReturnValue(r8, r9, r10)
                r1 = r7
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x0052:
                boolean r7 = r1.booleanValue()
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onJsAlert(android.webkit.WebView, java.lang.String, java.lang.String, android.webkit.JsResult):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onJsConfirm(android.webkit.WebView r6, java.lang.String r7, java.lang.String r8, android.webkit.JsResult r9) {
            /*
                r5 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onJsConfirm"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r6 = super.onJsConfirm(r6, r7, r8, r9)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r6)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r9 = "onJsConfirm"
                boolean r6 = r6.shouldSendEvent(r9)
                r9 = 1
                if (r6 == 0) goto L_0x0034
                com.unity3d.ads.webview.WebViewApp r6 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r3 = com.unity3d.ads.webplayer.WebPlayerEvent.JS_CONFIRM
                r4 = 2
                java.lang.Object[] r4 = new java.lang.Object[r4]
                r4[r0] = r7
                r4[r9] = r8
                r6.sendEvent(r2, r3, r4)
            L_0x0034:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onJsConfirm"
                boolean r6 = r6.hasReturnValue(r7)
                if (r6 == 0) goto L_0x004f
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onJsConfirm"
                java.lang.Class<java.lang.Boolean> r8 = java.lang.Boolean.class
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
                java.lang.Object r6 = r6.getReturnValue(r7, r8, r9)
                r1 = r6
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x004f:
                boolean r6 = r1.booleanValue()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onJsConfirm(android.webkit.WebView, java.lang.String, java.lang.String, android.webkit.JsResult):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onJsPrompt(android.webkit.WebView r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, android.webkit.JsPromptResult r10) {
            /*
                r5 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onJsPrompt"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r6 = super.onJsPrompt(r6, r7, r8, r9, r10)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r6)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r10 = "onJsPrompt"
                boolean r6 = r6.shouldSendEvent(r10)
                r10 = 1
                if (r6 == 0) goto L_0x0037
                com.unity3d.ads.webview.WebViewApp r6 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r2 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r3 = com.unity3d.ads.webplayer.WebPlayerEvent.JS_PROMPT
                r4 = 3
                java.lang.Object[] r4 = new java.lang.Object[r4]
                r4[r0] = r7
                r4[r10] = r8
                r7 = 2
                r4[r7] = r9
                r6.sendEvent(r2, r3, r4)
            L_0x0037:
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onJsPrompt"
                boolean r6 = r6.hasReturnValue(r7)
                if (r6 == 0) goto L_0x0052
                com.unity3d.ads.webplayer.WebPlayer r6 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onJsPrompt"
                java.lang.Class<java.lang.Boolean> r8 = java.lang.Boolean.class
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)
                java.lang.Object r6 = r6.getReturnValue(r7, r8, r9)
                r1 = r6
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x0052:
                boolean r6 = r1.booleanValue()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onJsPrompt(android.webkit.WebView, java.lang.String, java.lang.String, java.lang.String, android.webkit.JsPromptResult):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onConsoleMessage(android.webkit.ConsoleMessage r8) {
            /*
                r7 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onConsoleMessage"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r1 = super.onConsoleMessage(r8)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onConsoleMessage"
                boolean r2 = r2.shouldSendEvent(r3)
                r3 = 1
                if (r2 == 0) goto L_0x0039
                java.lang.String r2 = ""
                if (r8 == 0) goto L_0x002a
                java.lang.String r2 = r8.message()
            L_0x002a:
                com.unity3d.ads.webview.WebViewApp r8 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r4 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r5 = com.unity3d.ads.webplayer.WebPlayerEvent.CONSOLE_MESSAGE
                java.lang.Object[] r6 = new java.lang.Object[r3]
                r6[r0] = r2
                r8.sendEvent(r4, r5, r6)
            L_0x0039:
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r0 = "onConsoleMessage"
                boolean r8 = r8.hasReturnValue(r0)
                if (r8 == 0) goto L_0x0054
                com.unity3d.ads.webplayer.WebPlayer r8 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r0 = "onConsoleMessage"
                java.lang.Class<java.lang.Boolean> r1 = java.lang.Boolean.class
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)
                java.lang.Object r8 = r8.getReturnValue(r0, r1, r2)
                r1 = r8
                java.lang.Boolean r1 = (java.lang.Boolean) r1
            L_0x0054:
                boolean r8 = r1.booleanValue()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onConsoleMessage(android.webkit.ConsoleMessage):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Boolean} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onShowFileChooser(android.webkit.WebView r5, android.webkit.ValueCallback<android.net.Uri[]> r6, android.webkit.WebChromeClient.FileChooserParams r7) {
            /*
                r4 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                com.unity3d.ads.webplayer.WebPlayer r2 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r3 = "onShowFileChooser"
                boolean r2 = r2.shouldCallSuper(r3)
                if (r2 == 0) goto L_0x0017
                boolean r5 = super.onShowFileChooser(r5, r6, r7)
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)
            L_0x0017:
                com.unity3d.ads.webplayer.WebPlayer r5 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onShowFileChooser"
                boolean r5 = r5.shouldSendEvent(r7)
                if (r5 == 0) goto L_0x002e
                com.unity3d.ads.webview.WebViewApp r5 = com.unity3d.ads.webview.WebViewApp.getCurrentApp()
                com.unity3d.ads.webview.WebViewEventCategory r7 = com.unity3d.ads.webview.WebViewEventCategory.WEBPLAYER
                com.unity3d.ads.webplayer.WebPlayerEvent r2 = com.unity3d.ads.webplayer.WebPlayerEvent.SHOW_FILE_CHOOSER
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r5.sendEvent(r7, r2, r0)
            L_0x002e:
                com.unity3d.ads.webplayer.WebPlayer r5 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onShowFileChooser"
                boolean r5 = r5.hasReturnValue(r7)
                if (r5 == 0) goto L_0x0054
                com.unity3d.ads.webplayer.WebPlayer r5 = com.unity3d.ads.webplayer.WebPlayer.this
                java.lang.String r7 = "onShowFileChooser"
                java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
                r1 = 1
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                java.lang.Object r5 = r5.getReturnValue(r7, r0, r1)
                r1 = r5
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r5 = r1.booleanValue()
                if (r5 == 0) goto L_0x0054
                r5 = 0
                r6.onReceiveValue(r5)
            L_0x0054:
                boolean r5 = r1.booleanValue()
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads.webplayer.WebPlayer.WebPlayerChromeClient.onShowFileChooser(android.webkit.WebView, android.webkit.ValueCallback, android.webkit.WebChromeClient$FileChooserParams):boolean");
        }
    }

    private class WebPlayerDownloadListener implements DownloadListener {
        private WebPlayerDownloadListener() {
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            if (WebPlayer.this.shouldSendEvent("onDownloadStart")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.DOWNLOAD_START, str, str2, str3, str4, Long.valueOf(j));
            }
        }
    }
}
