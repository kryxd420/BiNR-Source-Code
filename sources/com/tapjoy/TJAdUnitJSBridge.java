package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.moat.analytics.mobile.tjy.MoatAdEventType;
import com.moat.analytics.mobile.tjy.MoatFactory;
import com.moat.analytics.mobile.tjy.ReactiveVideoTracker;
import com.moat.analytics.mobile.tjy.ReactiveVideoTrackerPlugin;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.ao;
import com.tapjoy.internal.eu;
import com.tapjoy.internal.ju;
import com.tapjoy.mraid.view.MraidView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge implements TJWebViewJSInterfaceListener {
    public TJAdUnit a;
    public boolean allowRedirect;
    public WebView b;
    final ConcurrentLinkedQueue c;
    public boolean closeRequested;
    public boolean customClose;
    private TJWebViewJSInterface d;
    public boolean didLaunchOtherActivity;
    private TJAdUnitJSBridge e;
    /* access modifiers changed from: private */
    public Context f;
    /* access modifiers changed from: private */
    public TJAdUnitActivity g;
    /* access modifiers changed from: private */
    public TJSplitWebView h;
    private ProgressDialog i;
    /* access modifiers changed from: private */
    public View j;
    private boolean k;
    /* access modifiers changed from: private */
    public ReactiveVideoTracker l;
    private HashMap m;
    private Handler n;
    private eu o;
    public String otherActivityCallbackID;
    public String splitWebViewCallbackID;

    public interface AdUnitAsyncTaskListner {
        void onComplete(boolean z);
    }

    public void destroy() {
    }

    public TJAdUnitJSBridge(Context context, TJAdUnit tJAdUnit) {
        this(context, (WebView) tJAdUnit.getWebView());
        this.a = tJAdUnit;
    }

    public TJAdUnitJSBridge(Context context, WebView webView) {
        this.j = null;
        this.didLaunchOtherActivity = false;
        this.allowRedirect = true;
        this.otherActivityCallbackID = null;
        this.customClose = false;
        this.closeRequested = false;
        this.splitWebViewCallbackID = null;
        this.o = new eu(this);
        this.c = new ConcurrentLinkedQueue();
        TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
        this.f = context;
        this.b = webView;
        this.e = this;
        if (this.b == null) {
            TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
            return;
        }
        this.d = new TJWebViewJSInterface(this.b, this);
        this.b.addJavascriptInterface(this.d, TJAdUnitConstants.JAVASCRIPT_INTERFACE_ID);
        setEnabled(true);
    }

    public void onDispatchMethod(String str, JSONObject jSONObject) {
        String str2;
        if (this.k) {
            try {
                str2 = jSONObject.optString(TJAdUnitConstants.String.CALLBACK_ID, (String) null);
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(TJAdUnitConstants.String.DATA);
                    Method method = TJAdUnitJSBridge.class.getMethod(str, new Class[]{JSONObject.class, String.class});
                    TapjoyLog.d("TJAdUnitJSBridge", "Dispatching method: " + method + " with data=" + jSONObject2 + "; callbackID=" + str2);
                    method.invoke(this.e, new Object[]{jSONObject2, str2});
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    invokeJSCallback(str2, Boolean.FALSE);
                }
            } catch (Exception e3) {
                e = e3;
                str2 = null;
                e.printStackTrace();
                invokeJSCallback(str2, Boolean.FALSE);
            }
        } else {
            TapjoyLog.d("TJAdUnitJSBridge", "Bridge currently disabled. Adding " + str + " to message queue");
            this.c.add(new Pair(str, jSONObject));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void alert(org.json.JSONObject r8, final java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r0 = "TJAdUnitJSBridge"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "alert_method: "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.tapjoy.TapjoyLog.d(r0, r1)
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            r2 = 1
            r3 = 0
            java.lang.String r4 = "title"
            java.lang.String r4 = r8.getString(r4)     // Catch:{ Exception -> 0x0030 }
            java.lang.String r0 = "message"
            java.lang.String r0 = r8.getString(r0)     // Catch:{ Exception -> 0x002e }
            java.lang.String r1 = "buttons"
            org.json.JSONArray r8 = r8.getJSONArray(r1)     // Catch:{ Exception -> 0x002c }
            goto L_0x0040
        L_0x002c:
            r8 = move-exception
            goto L_0x0033
        L_0x002e:
            r8 = move-exception
            goto L_0x0032
        L_0x0030:
            r8 = move-exception
            r4 = r0
        L_0x0032:
            r0 = r1
        L_0x0033:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            r1[r3] = r5
            r7.invokeJSCallback((java.lang.String) r9, (java.lang.Object[]) r1)
            r8.printStackTrace()
            r8 = 0
        L_0x0040:
            com.tapjoy.TJAdUnitActivity r1 = r7.g
            if (r1 == 0) goto L_0x00be
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 21
            if (r5 < r6) goto L_0x005f
            android.app.AlertDialog$Builder r5 = new android.app.AlertDialog$Builder
            r6 = 16974394(0x103023a, float:2.4062497E-38)
            r5.<init>(r1, r6)
            android.app.AlertDialog$Builder r1 = r5.setTitle(r4)
            android.app.AlertDialog$Builder r0 = r1.setMessage(r0)
            android.app.AlertDialog r0 = r0.create()
            goto L_0x0070
        L_0x005f:
            android.app.AlertDialog$Builder r5 = new android.app.AlertDialog$Builder
            r5.<init>(r1)
            android.app.AlertDialog$Builder r1 = r5.setTitle(r4)
            android.app.AlertDialog$Builder r0 = r1.setMessage(r0)
            android.app.AlertDialog r0 = r0.create()
        L_0x0070:
            if (r8 == 0) goto L_0x00b4
            int r1 = r8.length()
            if (r1 != 0) goto L_0x0079
            goto L_0x00b4
        L_0x0079:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
        L_0x007f:
            int r4 = r8.length()
            if (r2 >= r4) goto L_0x00aa
            switch(r2) {
                case 0: goto L_0x008c;
                case 1: goto L_0x008a;
                default: goto L_0x0088;
            }
        L_0x0088:
            r4 = -1
            goto L_0x008d
        L_0x008a:
            r4 = -3
            goto L_0x008d
        L_0x008c:
            r4 = -2
        L_0x008d:
            java.lang.String r5 = r8.getString(r2)     // Catch:{ Exception -> 0x0095 }
            r1.add(r5)     // Catch:{ Exception -> 0x0095 }
            goto L_0x0099
        L_0x0095:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0099:
            java.lang.Object r5 = r1.get(r2)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            com.tapjoy.TJAdUnitJSBridge$1 r6 = new com.tapjoy.TJAdUnitJSBridge$1
            r6.<init>(r9)
            r0.setButton(r4, r5, r6)
            int r2 = r2 + 1
            goto L_0x007f
        L_0x00aa:
            r0.setCancelable(r3)
            r0.setCanceledOnTouchOutside(r3)
            r0.show()
            return
        L_0x00b4:
            java.lang.Object[] r8 = new java.lang.Object[r2]
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r8[r3] = r0
            r7.invokeJSCallback((java.lang.String) r9, (java.lang.Object[]) r8)
            return
        L_0x00be:
            java.lang.String r8 = "TJAdUnitJSBridge"
            java.lang.String r9 = "Cannot alert -- TJAdUnitActivity is null"
            com.tapjoy.TapjoyLog.d(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnitJSBridge.alert(org.json.JSONObject, java.lang.String):void");
    }

    public void checkAppInstalled(JSONObject jSONObject, String str) {
        String str2;
        try {
            str2 = jSONObject.getString(TJAdUnitConstants.String.BUNDLE);
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = "";
        }
        if (str2 != null && str2.length() > 0) {
            for (ApplicationInfo applicationInfo : this.f.getPackageManager().getInstalledApplications(0)) {
                if (applicationInfo.packageName.equals(str2)) {
                    invokeJSCallback(str, Boolean.TRUE);
                    return;
                }
            }
        }
        invokeJSCallback(str, Boolean.FALSE);
    }

    public void getInstalledAppData(JSONObject jSONObject, String str) {
        PackageManager packageManager = this.f.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        JSONArray jSONArray = new JSONArray();
        for (ApplicationInfo next : installedApplications) {
            if ((next.flags & 1) != 1) {
                HashMap hashMap = new HashMap();
                String str2 = next.packageName;
                hashMap.put("packageName", str2);
                hashMap.put("targetSdk", Integer.valueOf(next.targetSdkVersion));
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(str2, 4096);
                    hashMap.put("installTime", new Date(packageInfo.firstInstallTime));
                    hashMap.put("updateTime", new Date(packageInfo.lastUpdateTime));
                    hashMap.put("versionName", packageInfo.versionName);
                    hashMap.put("versionCode", Integer.valueOf(packageInfo.versionCode));
                    jSONArray.put(new JSONObject(hashMap));
                } catch (Exception unused) {
                }
            }
        }
        invokeJSCallback(str, jSONArray);
    }

    public void closeRequested(Boolean bool) {
        this.closeRequested = true;
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.FORCE_CLOSE, bool);
        invokeJSAdunitMethod(TJAdUnitConstants.String.CLOSE_REQUESTED, (Map) hashMap);
    }

    public void getVolume(JSONObject jSONObject, String str) {
        HashMap volumeArgs = getVolumeArgs();
        if (volumeArgs != null) {
            invokeJSCallback(str, (Map) volumeArgs);
            return;
        }
        invokeJSCallback(str, false);
    }

    public void onVolumeChanged() {
        invokeJSAdunitMethod(TJAdUnitConstants.String.VOLUME_CHANGED, (Map) getVolumeArgs());
    }

    public HashMap getVolumeArgs() {
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            return null;
        }
        String format = String.format("%.2f", new Object[]{Float.valueOf(this.a.getVolume())});
        boolean isMuted = this.a.isMuted();
        TapjoyLog.d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + format + "; isMuted=" + isMuted);
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.CURRENT_VOLUME, format);
        hashMap.put(TJAdUnitConstants.String.IS_MUTED, Boolean.valueOf(isMuted));
        return hashMap;
    }

    public void dismiss(JSONObject jSONObject, String str) {
        TJAdUnitActivity tJAdUnitActivity = this.g;
        if (tJAdUnitActivity != null) {
            invokeJSCallback(str, true);
            tJAdUnitActivity.finish();
            return;
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
        invokeJSCallback(str, false);
    }

    public void display() {
        invokeJSAdunitMethod(TJAdUnitConstants.String.DISPLAY, new Object[0]);
    }

    public void displayRichMedia(final JSONObject jSONObject, String str) {
        boolean z;
        String str2;
        try {
            z = jSONObject.getBoolean(TJAdUnitConstants.String.INLINE);
        } catch (Exception unused) {
            z = false;
        }
        try {
            str2 = jSONObject.getString(TJAdUnitConstants.String.HTML);
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = null;
        }
        if (str2 == null) {
            invokeJSCallback(str, Boolean.FALSE);
        } else if (z) {
            ((Activity) this.f).runOnUiThread(new Runnable() {
                public final void run() {
                    String str;
                    try {
                        str = jSONObject.getString(TJAdUnitConstants.String.HTML);
                    } catch (Exception e) {
                        e.printStackTrace();
                        str = null;
                    }
                    String str2 = str;
                    if (!(TJAdUnitJSBridge.this.j == null || TJAdUnitJSBridge.this.j.getParent() == null)) {
                        ((ViewGroup) TJAdUnitJSBridge.this.j.getParent()).removeView(TJAdUnitJSBridge.this.j);
                    }
                    MraidView mraidView = new MraidView(TJAdUnitJSBridge.this.f);
                    TJAdUnitJSBridge.this.b.getSettings().setJavaScriptEnabled(true);
                    mraidView.setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
                    mraidView.setLayoutParams(new ViewGroup.LayoutParams(640, 100));
                    mraidView.setInitialScale(100);
                    mraidView.setBackgroundColor(0);
                    mraidView.loadDataWithBaseURL((String) null, str2, "text/html", "utf-8", (String) null);
                    int width = ((WindowManager) ((Activity) TJAdUnitJSBridge.this.f).getSystemService("window")).getDefaultDisplay().getWidth();
                    View unused = TJAdUnitJSBridge.this.j = TapjoyUtil.scaleDisplayAd(mraidView, width);
                    View a2 = TJAdUnitJSBridge.this.j;
                    double d = (double) width;
                    Double.isNaN(d);
                    ((Activity) TJAdUnitJSBridge.this.f).addContentView(a2, new ViewGroup.LayoutParams(width, (int) ((d / 640.0d) * 100.0d)));
                }
            });
        } else {
            TJPlacementData tJPlacementData = new TJPlacementData(TapjoyConnectCore.getHostURL(), str2, str);
            TJAdUnitActivity tJAdUnitActivity = this.g;
            if (tJAdUnitActivity != null) {
                Intent intent = new Intent(tJAdUnitActivity, TJAdUnitActivity.class);
                intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJPlacementData);
                tJAdUnitActivity.startActivityForResult(intent, TJAdUnitConstants.MRAID_REQUEST_CODE);
            }
        }
    }

    public void displayStoreURL(JSONObject jSONObject, String str) {
        displayURL(jSONObject, str);
    }

    public void displayURL(JSONObject jSONObject, String str) {
        try {
            String optString = jSONObject.optString(TJAdUnitConstants.String.STYLE);
            final String string = jSONObject.getString("url");
            final JSONObject optJSONObject = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_LAYOUT);
            final JSONArray optJSONArray = jSONObject.optJSONArray(TJAdUnitConstants.String.SPLIT_VIEW_EXIT_HOSTS);
            if (TJAdUnitConstants.String.STYLE_SPLIT.equals(optString)) {
                final String str2 = str;
                TapjoyUtil.runOnMainThread(new Runnable() {
                    public final void run() {
                        if (TJAdUnitJSBridge.this.b != null) {
                            if (TJAdUnitJSBridge.this.h == null) {
                                ViewParent parent = TJAdUnitJSBridge.this.b.getParent();
                                if (parent instanceof ViewGroup) {
                                    TJSplitWebView unused = TJAdUnitJSBridge.this.h = new TJSplitWebView(TJAdUnitJSBridge.this.f, optJSONObject, optJSONArray, TJAdUnitJSBridge.this);
                                    ((ViewGroup) parent).addView(TJAdUnitJSBridge.this.h, new RelativeLayout.LayoutParams(-1, -1));
                                }
                            } else {
                                TJAdUnitJSBridge.this.h.setExitHosts(optJSONArray);
                                TJAdUnitJSBridge.this.h.applyLayoutOption(optJSONObject);
                            }
                            if (TJAdUnitJSBridge.this.h != null) {
                                TJAdUnitJSBridge.this.splitWebViewCallbackID = str2;
                                TJAdUnitJSBridge.this.h.loadUrl(string);
                                return;
                            }
                        }
                        TJSplitWebView unused2 = TJAdUnitJSBridge.this.h = null;
                        TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                        TJAdUnitJSBridge.this.invokeJSCallback(str2, Boolean.FALSE);
                    }
                });
                return;
            }
            this.didLaunchOtherActivity = true;
            this.otherActivityCallbackID = str;
            this.f.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.TRUE);
            e2.printStackTrace();
        }
    }

    public void clearCache(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            TapjoyCache.getInstance().clearTapjoyCache();
            invokeJSCallback(str, Boolean.TRUE);
            return;
        }
        invokeJSCallback(str, Boolean.FALSE);
    }

    public void setPrerenderLimit(JSONObject jSONObject, String str) {
        try {
            TJPlacementManager.setPreRenderedPlacementLimit(jSONObject.getInt(TJAdUnitConstants.String.TJC_PLACEMENT_PRE_RENDERED_LIMIT));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception unused) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void setEventPreloadLimit(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            try {
                TJPlacementManager.setCachedPlacementLimit(jSONObject.getInt(TJAdUnitConstants.String.TJC_PLACEMENT_CACHE_LIMIT));
                invokeJSCallback(str, Boolean.TRUE);
            } catch (Exception unused) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
                invokeJSCallback(str, Boolean.FALSE);
            }
        } else {
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void removeAssetFromCache(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(string)));
                return;
            }
            invokeJSCallback(str, Boolean.FALSE);
        } catch (Exception unused) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void cacheAsset(JSONObject jSONObject, String str) {
        Long l2;
        String str2 = "";
        try {
            String string = jSONObject.getString("url");
            try {
                str2 = jSONObject.getString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID);
            } catch (Exception unused) {
            }
            try {
                l2 = Long.valueOf(jSONObject.getLong(TapjoyConstants.TJC_TIME_TO_LIVE));
            } catch (Exception unused2) {
                l2 = 0L;
            }
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().cacheAssetFromURL(string, str2, l2.longValue()));
                return;
            }
            invokeJSCallback(str, Boolean.FALSE);
        } catch (Exception unused3) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void cachePathForURL(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().getPathOfCachedURL(string));
                return;
            }
            invokeJSCallback(str, "");
        } catch (Exception unused) {
            invokeJSCallback(str, "");
        }
    }

    public void getCachedAssets(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            invokeJSCallback(str, TapjoyCache.getInstance().cachedAssetsToJSON());
            return;
        }
        invokeJSCallback(str, "");
    }

    public void contentReady(JSONObject jSONObject, String str) {
        if (this.a != null) {
            this.a.fireContentReady();
            invokeJSCallback(str, true);
            return;
        }
        invokeJSCallback(str, false);
    }

    public void setOrientation(JSONObject jSONObject, String str) {
        int i2;
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, false);
            return;
        }
        try {
            String string = jSONObject.getString("orientation");
            if (!string.equals(TJAdUnitConstants.String.LANDSCAPE)) {
                if (!string.equals(TJAdUnitConstants.String.LANDSCAPE_LEFT)) {
                    i2 = string.equals(TJAdUnitConstants.String.LANDSCAPE_RIGHT) ? 8 : 1;
                    this.a.setOrientation(i2);
                    invokeJSCallback(str, true);
                }
            }
            i2 = 0;
            this.a.setOrientation(i2);
            invokeJSCallback(str, true);
        } catch (Exception unused) {
            invokeJSCallback(str, false);
        }
    }

    public void unsetOrientation(JSONObject jSONObject, String str) {
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, false);
            return;
        }
        try {
            this.a.unsetOrientation();
            invokeJSCallback(str, true);
        } catch (Exception unused) {
            invokeJSCallback(str, false);
        }
    }

    public void setBackgroundColor(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString(TJAdUnitConstants.String.BACKGROUND_COLOR);
            if (this.a != null) {
                this.a.setBackgroundColor(string, new AdUnitAsyncTaskListner() {
                    public final void onComplete(boolean z) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
                return;
            }
            invokeJSCallback(str, false);
        } catch (Exception unused) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
            invokeJSCallback(str, false);
        }
    }

    public void setBackgroundWebViewContent(JSONObject jSONObject, final String str) {
        TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
        try {
            String string = jSONObject.getString(TJAdUnitConstants.String.BACKGROUND_CONTENT);
            if (this.a != null) {
                this.a.setBackgroundContent(string, new AdUnitAsyncTaskListner() {
                    public final void onComplete(boolean z) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
                return;
            }
            invokeJSCallback(str, false);
        } catch (Exception unused) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
            invokeJSCallback(str, false);
        }
    }

    public void displayVideo(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString("url");
            if (string.length() <= 0 || string == "") {
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
            this.a.loadVideoUrl(string, new AdUnitAsyncTaskListner() {
                public final void onComplete(boolean z) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                }
            });
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            e2.printStackTrace();
        }
    }

    public void playVideo(JSONObject jSONObject, String str) {
        if (this.a != null) {
            invokeJSCallback(str, Boolean.valueOf(this.a.playVideo()));
        }
    }

    public void pauseVideo(JSONObject jSONObject, String str) {
        if (this.a != null) {
            invokeJSCallback(str, Boolean.valueOf(this.a.pauseVideo()));
        }
    }

    public void clearVideo(JSONObject jSONObject, final String str) {
        if (this.a != null) {
            this.a.clearVideo(new AdUnitAsyncTaskListner() {
                public final void onComplete(boolean z) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                }
            });
        }
    }

    public void setVideoMute(JSONObject jSONObject, String str) {
        try {
            this.a.a(jSONObject.getBoolean(TJAdUnitConstants.String.ENABLED));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (JSONException unused) {
            TapjoyLog.d("TJAdUnitJSBridge", "Failed to parse 'enabled' from json params.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void log(JSONObject jSONObject, String str) {
        try {
            TapjoyLog.d("TJAdUnitJSBridge", "Logging message=" + jSONObject.getString("message"));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            e2.printStackTrace();
        }
    }

    public void openApp(JSONObject jSONObject, String str) {
        try {
            this.f.startActivity(this.f.getPackageManager().getLaunchIntentForPackage(jSONObject.getString(TJAdUnitConstants.String.BUNDLE)));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            e2.printStackTrace();
        }
    }

    @TargetApi(19)
    public void nativeEval(final JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 19) {
                        TJAdUnitJSBridge.this.b.evaluateJavascript(jSONObject.getString(TJAdUnitConstants.String.COMMAND), (ValueCallback) null);
                    } else {
                        WebView webView = TJAdUnitJSBridge.this.b;
                        webView.loadUrl("javascript:" + jSONObject.getString(TJAdUnitConstants.String.COMMAND));
                    }
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                } catch (Exception unused) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                }
            }
        });
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|(3:4|5|6)|7|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void present(org.json.JSONObject r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0049 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r3 = "visible"
            java.lang.String r3 = r6.getString(r3)     // Catch:{ Exception -> 0x0049 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r4 = "transparent"
            java.lang.String r4 = r6.getString(r4)     // Catch:{ Exception -> 0x001e }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x001e }
            r2 = r4
        L_0x001e:
            java.lang.String r4 = "customClose"
            java.lang.String r6 = r6.getString(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = r6.booleanValue()     // Catch:{ Exception -> 0x002e }
            r5.customClose = r6     // Catch:{ Exception -> 0x002e }
        L_0x002e:
            com.tapjoy.TJAdUnitJSBridge$a r6 = new com.tapjoy.TJAdUnitJSBridge$a     // Catch:{ Exception -> 0x0049 }
            android.webkit.WebView r4 = r5.b     // Catch:{ Exception -> 0x0049 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x0049 }
            r4 = 2
            java.lang.Boolean[] r4 = new java.lang.Boolean[r4]     // Catch:{ Exception -> 0x0049 }
            r4[r1] = r3     // Catch:{ Exception -> 0x0049 }
            r4[r0] = r2     // Catch:{ Exception -> 0x0049 }
            r6.execute(r4)     // Catch:{ Exception -> 0x0049 }
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0049 }
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x0049 }
            r6[r1] = r2     // Catch:{ Exception -> 0x0049 }
            r5.invokeJSCallback((java.lang.String) r7, (java.lang.Object[]) r6)     // Catch:{ Exception -> 0x0049 }
            return
        L_0x0049:
            r6 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            r0[r1] = r2
            r5.invokeJSCallback((java.lang.String) r7, (java.lang.Object[]) r0)
            r6.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnitJSBridge.present(org.json.JSONObject, java.lang.String):void");
    }

    public void triggerEvent(JSONObject jSONObject, String str) {
        if (this.a != null) {
            try {
                String string = jSONObject.getString("eventName");
                if (string.equals("start")) {
                    this.a.fireOnVideoStart();
                } else if (string.equals(TJAdUnitConstants.String.VIDEO_COMPLETE)) {
                    this.a.fireOnVideoComplete();
                } else if (string.equals(TJAdUnitConstants.String.VIDEO_ERROR)) {
                    this.a.fireOnVideoError("Error while trying to play video.");
                }
            } catch (Exception unused) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
            }
        }
    }

    public void invokeJSAdunitMethod(String str, Object... objArr) {
        this.d.callback(new ArrayList(Arrays.asList(objArr)), str, (String) null);
    }

    public void invokeJSAdunitMethod(String str, Map map) {
        this.d.callback(map, str, (String) null);
    }

    public void invokeJSCallback(String str, Object... objArr) {
        if (ju.c(str)) {
            TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
            return;
        }
        this.d.callback(new ArrayList(Arrays.asList(objArr)), "", str);
    }

    public void invokeJSCallback(String str, Map map) {
        this.d.callback(map, "", str);
    }

    public void flushBacklogMessageQueue() {
        while (true) {
            Pair pair = (Pair) this.c.poll();
            if (pair != null) {
                onDispatchMethod((String) pair.first, (JSONObject) pair.second);
            } else {
                return;
            }
        }
    }

    public void flushMessageQueue() {
        this.d.flushMessageQueue();
    }

    public void setAllowRedirect(JSONObject jSONObject, String str) {
        boolean z;
        try {
            z = jSONObject.getBoolean(TJAdUnitConstants.String.ENABLED);
        } catch (Exception unused) {
            z = true;
        }
        this.allowRedirect = z;
        invokeJSCallback(str, Boolean.TRUE);
    }

    public void setAdUnitActivity(TJAdUnitActivity tJAdUnitActivity) {
        this.g = tJAdUnitActivity;
    }

    public void setSpinnerVisible(JSONObject jSONObject, String str) {
        try {
            boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.VISIBLE);
            String optString = jSONObject.optString(TJAdUnitConstants.String.TITLE);
            String optString2 = jSONObject.optString("message");
            TJAdUnitActivity tJAdUnitActivity = this.g;
            if (tJAdUnitActivity != null) {
                if (z) {
                    this.i = ProgressDialog.show(tJAdUnitActivity, optString, optString2);
                } else if (this.i != null) {
                    this.i.dismiss();
                }
                invokeJSCallback(str, Boolean.TRUE);
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
            invokeJSCallback(str, Boolean.FALSE);
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            e2.printStackTrace();
        }
    }

    public void setCloseButtonVisible(JSONObject jSONObject, String str) {
        try {
            final boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.VISIBLE);
            TapjoyUtil.runOnMainThread(new Runnable() {
                public final void run() {
                    TJAdUnitActivity d = TJAdUnitJSBridge.this.g;
                    if (d != null) {
                        d.setCloseButtonVisibility(z);
                    } else {
                        TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonVisible -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(str, true);
        } catch (Exception e2) {
            invokeJSCallback(str, false);
            e2.printStackTrace();
        }
    }

    public void setCloseButtonClickable(JSONObject jSONObject, String str) {
        try {
            final boolean optBoolean = jSONObject.optBoolean(TJAdUnitConstants.String.CLICKABLE);
            TapjoyUtil.runOnMainThread(new Runnable() {
                public final void run() {
                    TJAdUnitActivity d = TJAdUnitJSBridge.this.g;
                    if (d != null) {
                        d.setCloseButtonClickable(optBoolean);
                    } else {
                        TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonClickable -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(str, true);
        } catch (Exception e2) {
            invokeJSCallback(str, false);
            e2.printStackTrace();
        }
    }

    public void shouldClose(JSONObject jSONObject, String str) {
        TJAdUnitActivity tJAdUnitActivity = this.g;
        try {
            Boolean.valueOf(false);
            if (Boolean.valueOf(jSONObject.getString(TJAdUnitConstants.String.CLOSE)).booleanValue() && tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            if (tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            e2.printStackTrace();
        }
        this.closeRequested = false;
    }

    public void setLoggingLevel(JSONObject jSONObject, String str) {
        try {
            TapjoyAppSettings.getInstance().saveLoggingLevel(String.valueOf(jSONObject.getString(TJAdUnitConstants.String.LOGGING_LEVEL)));
        } catch (Exception e2) {
            TapjoyLog.d("TJAdUnitJSBridge", "setLoggingLevel exception " + e2.getLocalizedMessage());
            invokeJSCallback(str, false);
            e2.printStackTrace();
        }
    }

    public void clearLoggingLevel(JSONObject jSONObject, String str) {
        TapjoyAppSettings.getInstance().clearLoggingLevel();
    }

    public void attachVolumeListener(JSONObject jSONObject, String str) {
        try {
            boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.ATTACH);
            int optInt = jSONObject.optInt(TJAdUnitConstants.String.INTERVAL, TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL);
            if (optInt > 0) {
                this.a.attachVolumeListener(z, optInt);
                invokeJSCallback(str, true);
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + optInt);
            invokeJSCallback(str, false);
        } catch (Exception e2) {
            TapjoyLog.d("TJAdUnitJSBridge", "attachVolumeListener exception " + e2.toString());
            invokeJSCallback(str, false);
            e2.printStackTrace();
        }
    }

    public void initMoatVideoTracker(JSONObject jSONObject, String str) {
        TJAdUnitActivity tJAdUnitActivity = this.g;
        if (tJAdUnitActivity == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
            invokeJSCallback(str, false);
            return;
        }
        try {
            this.l = (ReactiveVideoTracker) MoatFactory.create(tJAdUnitActivity).createCustomTracker(new ReactiveVideoTrackerPlugin(jSONObject.getString(TJAdUnitConstants.String.PARTNER_CODE)));
            if (this.m == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
                this.m = new HashMap();
                this.m.put(TJAdUnitConstants.String.VIDEO_FIRST_QUARTILE, MoatAdEventType.AD_EVT_FIRST_QUARTILE);
                this.m.put(TJAdUnitConstants.String.VIDEO_MIDPOINT, MoatAdEventType.AD_EVT_MID_POINT);
                this.m.put(TJAdUnitConstants.String.VIDEO_THIRD_QUARTILE, MoatAdEventType.AD_EVT_THIRD_QUARTILE);
                this.m.put(TJAdUnitConstants.String.VIDEO_COMPLETE, MoatAdEventType.AD_EVT_COMPLETE);
                this.m.put(TJAdUnitConstants.String.VIDEO_PAUSED, MoatAdEventType.AD_EVT_PAUSED);
                this.m.put(TJAdUnitConstants.String.VIDEO_PLAYING, MoatAdEventType.AD_EVT_PLAYING);
                this.m.put("start", MoatAdEventType.AD_EVT_START);
                this.m.put(TJAdUnitConstants.String.VIDEO_STOPPED, MoatAdEventType.AD_EVT_STOPPED);
                this.m.put(TJAdUnitConstants.String.VIDEO_SKIPPED, MoatAdEventType.AD_EVT_SKIPPED);
                this.m.put(TJAdUnitConstants.String.VOLUME_CHANGED, MoatAdEventType.AD_EVT_VOLUME_CHANGE);
                this.m.put(TJAdUnitConstants.String.ENTER_FULL_SCREEN, MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
                this.m.put(TJAdUnitConstants.String.EXIT_FULL_SCREEN, MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
            }
            this.n = new Handler(Looper.getMainLooper());
            invokeJSCallback(str, true);
        } catch (Exception e2) {
            TapjoyLog.d("TJAdUnitJSBridge", "initMoatVideoTracker exception " + e2.toString());
            invokeJSCallback(str, false);
        }
    }

    public void startMoatVideoTracker(JSONObject jSONObject, final String str) {
        try {
            final Integer valueOf = Integer.valueOf(jSONObject.getInt(TJAdUnitConstants.String.VIDEO_LENGTH));
            final HashMap hashMap = new HashMap();
            JSONObject jSONObject2 = jSONObject.getJSONObject(TJAdUnitConstants.String.AD_IDS);
            if (jSONObject2 != null) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject2.getString(next));
                }
            }
            this.n.post(new Runnable() {
                public final void run() {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(TJAdUnitJSBridge.this.l != null ? TJAdUnitJSBridge.this.l.trackVideoAd(hashMap, valueOf, TJAdUnitJSBridge.this.b) : false));
                }
            });
        } catch (Exception e2) {
            TapjoyLog.d("TJAdUnitJSBridge", "startMoatVideoTracker exception " + e2.toString());
            invokeJSCallback(str, false);
        }
    }

    public void triggerMoatVideoEvent(JSONObject jSONObject, String str) {
        try {
            Integer valueOf = Integer.valueOf(jSONObject.getInt(TJAdUnitConstants.String.CURRENT_VIDEO_TIME));
            String string = jSONObject.getString("eventName");
            MoatAdEventType moatAdEventType = null;
            if (this.m != null) {
                moatAdEventType = (MoatAdEventType) this.m.get(string);
            }
            if (moatAdEventType == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "eventName:" + string + " has no matching MOAT event");
                invokeJSCallback(str, false);
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Sending MOAT event: " + moatAdEventType);
            final MoatAdEvent moatAdEvent = new MoatAdEvent(moatAdEventType, valueOf);
            this.n.post(new Runnable() {
                public final void run() {
                    if (TJAdUnitJSBridge.this.l != null) {
                        TJAdUnitJSBridge.this.l.dispatchEvent(moatAdEvent);
                    }
                }
            });
            invokeJSCallback(str, true);
        } catch (Exception e2) {
            TapjoyLog.d("TJAdUnitJSBridge", "triggerMoatVideoEvent exception " + e2.toString());
            invokeJSCallback(str, false);
        }
    }

    public void initViewabilityTracker(JSONObject jSONObject, String str) {
        eu euVar = this.o;
        if (!euVar.a(jSONObject)) {
            euVar.a.invokeJSCallback(str, false);
            return;
        }
        eu.b(jSONObject);
        if (ao.a(eu.b)) {
            euVar.a.invokeJSCallback(str, false);
            return;
        }
        TapjoyUtil.runOnMainThread(new Runnable(jSONObject, str) {
            final /* synthetic */ JSONObject a;
            final /* synthetic */ String b;

            {
                this.a = r2;
                this.b = r3;
            }

            public final void run() {
                try {
                    if (!eu.this.c) {
                        boolean unused = eu.this.c = cn.a(cn.a(), eu.this.a.b.getContext());
                    }
                    if (eu.this.c) {
                        TapjoyLog.d("TJOMViewabilityAgent", "initialized");
                        cx a2 = cx.a(eu.this.f, eu.b, eu.b(this.a.optJSONArray(TJAdUnitConstants.String.VENDORS)), "");
                        cv unused2 = eu.this.e = cv.a(cw.a(cz.a, cz.a), a2);
                        eu.this.e.a(eu.this.a.a.getWebView());
                        df unused3 = eu.this.g = df.a(eu.this.e);
                        cu unused4 = eu.this.d = cu.a(eu.this.e);
                        eu.this.a.invokeJSCallback(this.b, true);
                        return;
                    }
                    TapjoyLog.d("TJOMViewabilityAgent", "Failed to initialize");
                    eu.this.a.invokeJSCallback(this.b, false);
                } catch (Exception e) {
                    TapjoyLog.d("TJOMViewabilityAgent", "Failed to init with exception: " + e.getMessage());
                    eu.this.a.invokeJSCallback(this.b, false);
                }
            }
        });
    }

    public void startViewabilityTracker(JSONObject jSONObject, String str) {
        eu euVar = this.o;
        if (!euVar.c) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not start -- TJOMViewabilityAgent is not initialized");
            euVar.a.invokeJSCallback(str, false);
            return;
        }
        euVar.a.invokeJSCallback(str, true);
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    eu.this.e.a();
                } catch (Exception e) {
                    TapjoyLog.d("TJOMViewabilityAgent", "Failed to start with exception: " + e.getMessage());
                }
            }
        });
    }

    public void triggerViewabilityEvent(JSONObject jSONObject, String str) {
        eu euVar = this.o;
        if (!euVar.c) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- TJOMViewabilityAgent is not initialized");
            euVar.a.invokeJSCallback(str, false);
        } else if (jSONObject == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- json parameter is null");
            euVar.a.invokeJSCallback(str, false);
        } else {
            String optString = jSONObject.optString("eventName", (String) null);
            if (optString == null) {
                TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: params json did not contain 'eventName'");
                euVar.a.invokeJSCallback(str, false);
                return;
            }
            TapjoyUtil.runOnMainThread(new Runnable(optString, str) {
                final /* synthetic */ String a;
                final /* synthetic */ String b;

                {
                    this.a = r2;
                    this.b = r3;
                }

                public final void run() {
                    try {
                        if (this.a.equals(TJAdUnitConstants.String.VIDEO_RENDERED)) {
                            eu.this.g.a(de.a(dd.d));
                            eu.this.d.a();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_BUFFER_START)) {
                            eu.this.g.g();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_BUFFER_END)) {
                            eu.this.g.h();
                        } else if (this.a.equals("start")) {
                            eu.this.g.a((float) eu.this.a.a.getVideoView().getDuration(), eu.this.a.a.getVolume());
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_FIRST_QUARTILE)) {
                            eu.this.g.a();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_MIDPOINT)) {
                            eu.this.g.b();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_THIRD_QUARTILE)) {
                            eu.this.g.c();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_PAUSED)) {
                            eu.this.g.e();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_PLAYING)) {
                            eu.this.g.f();
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_SKIPPED)) {
                            eu.this.g.i();
                        } else if (this.a.equals(TJAdUnitConstants.String.VOLUME_CHANGED)) {
                            eu.this.g.a(eu.this.a.a.getVolume());
                        } else if (this.a.equals(TJAdUnitConstants.String.VIDEO_COMPLETE)) {
                            eu.this.g.d();
                            eu.this.e.b();
                            cv unused = eu.this.e = null;
                        } else {
                            TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: event name '" + this.a + "' not found");
                            eu.this.a.invokeJSCallback(this.b, false);
                            return;
                        }
                        TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: event name '" + this.a + "'");
                        eu.this.a.invokeJSCallback(this.b, true);
                    } catch (Exception e) {
                        TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent exception:" + e.getMessage());
                        eu.this.a.invokeJSCallback(this.b, false);
                    }
                }
            });
        }
    }

    public void startUsageTrackingEvent(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for startUsageTrackingEvent");
                invokeJSCallback(str, false);
                return;
            }
            if (this.a != null) {
                this.a.startAdContentTracking(string, jSONObject);
                invokeJSCallback(str, true);
                return;
            }
            invokeJSCallback(str, false);
        } catch (JSONException e2) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to startUsageTrackingEvent. Invalid parameters: " + e2);
        }
    }

    public void endUsageTrackingEvent(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for endUsageTrackingEvent");
                invokeJSCallback(str, false);
                return;
            }
            if (this.a != null) {
                this.a.endAdContentTracking(string, jSONObject);
                invokeJSCallback(str, true);
                return;
            }
            invokeJSCallback(str, false);
        } catch (JSONException e2) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to endUsageTrackingEvent. Invalid parameters: " + e2);
        }
    }

    public void sendUsageTrackingEvent(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for sendUsageTrackingEvent");
                invokeJSCallback(str, false);
                return;
            }
            if (this.a != null) {
                this.a.sendAdContentTracking(string, jSONObject);
                invokeJSCallback(str, true);
                return;
            }
            invokeJSCallback(str, false);
        } catch (JSONException e2) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to sendUsageTrackingEvent. Invalid parameters: " + e2);
        }
    }

    public void hasSplitView(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                    return;
                }
                TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
            }
        });
    }

    public void dismissSplitView(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    if (str != null) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                    }
                    if (TJAdUnitJSBridge.this.splitWebViewCallbackID != null) {
                        TJAdUnitJSBridge.this.invokeJSCallback(TJAdUnitJSBridge.this.splitWebViewCallbackID, Boolean.TRUE);
                        TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                    }
                    ((ViewGroup) TJAdUnitJSBridge.this.h.getParent()).removeView(TJAdUnitJSBridge.this.h);
                    TJSplitWebView unused = TJAdUnitJSBridge.this.h = null;
                } else if (str != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                }
            }
        });
    }

    public void getSplitViewURL(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, TJAdUnitJSBridge.this.h.getLastUrl());
                    return;
                }
                TJAdUnitJSBridge.this.invokeJSCallback(str, JSONObject.NULL);
            }
        });
    }

    public void setEnabled(boolean z) {
        this.k = z;
        if (this.k) {
            flushBacklogMessageQueue();
        }
    }

    @TargetApi(11)
    class a extends AsyncTask {
        WebView a;

        /* access modifiers changed from: protected */
        public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return (Boolean[]) objArr;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Boolean[] boolArr = (Boolean[]) obj;
            final boolean booleanValue = boolArr[0].booleanValue();
            final boolean booleanValue2 = boolArr[1].booleanValue();
            if (TJAdUnitJSBridge.this.f instanceof Activity) {
                TapjoyUtil.runOnMainThread(new Runnable() {
                    public final void run() {
                        if (booleanValue) {
                            a.this.a.setVisibility(0);
                            if (booleanValue2) {
                                if (a.this.a.getParent() instanceof RelativeLayout) {
                                    ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(0);
                                    ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(0);
                                }
                                if (Build.VERSION.SDK_INT >= 11) {
                                    a.this.a.setLayerType(1, (Paint) null);
                                    return;
                                }
                                return;
                            }
                            if (a.this.a.getParent() instanceof RelativeLayout) {
                                ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(255);
                                ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(-1);
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                a.this.a.setLayerType(0, (Paint) null);
                                return;
                            }
                            return;
                        }
                        a.this.a.setVisibility(4);
                        if (a.this.a.getParent() instanceof RelativeLayout) {
                            ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(0);
                            ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(0);
                        }
                    }
                });
            } else {
                TapjoyLog.e("TJAdUnitJSBridge", "Unable to present offerwall. No Activity context provided.");
            }
        }

        public a(WebView webView) {
            this.a = webView;
        }
    }

    public void onVideoReady(int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_READY_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_DURATION, Integer.valueOf(i2));
        hashMap.put(TJAdUnitConstants.String.VIDEO_WIDTH, Integer.valueOf(i3));
        hashMap.put(TJAdUnitConstants.String.VIDEO_HEIGHT, Integer.valueOf(i4));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoStarted(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_START_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i2));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoProgress(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_PROGRESS_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i2));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoPaused(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_PAUSE_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i2));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoCompletion() {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_COMPLETE_EVENT);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoInfo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_INFO_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_INFO, str);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }

    public void onVideoError(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_ERROR_EVENT);
        hashMap.put(TJAdUnitConstants.String.VIDEO_ERROR, str);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, (Map) hashMap);
    }
}
