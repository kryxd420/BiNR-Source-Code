package com.tapjoy.internal;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJCurrency;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.vungle.warren.ui.VungleActivity;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

class ew extends ev {
    private boolean c = false;
    private String d = "";
    /* access modifiers changed from: private */
    public TJCurrency e = null;
    /* access modifiers changed from: private */
    public TapjoyCache f = null;

    public final String b() {
        return "12.1.0";
    }

    ew() {
    }

    public final void a(boolean z) {
        TapjoyLog.setDebugEnabled(z);
    }

    public final boolean a(Context context, String str) {
        return a(context, str, (Hashtable) null, (TJConnectListener) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007d, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(final android.content.Context r3, java.lang.String r4, java.util.Hashtable r5, final com.tapjoy.TJConnectListener r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r5 == 0) goto L_0x001c
            java.lang.String r0 = "TJC_OPTION_ENABLE_LOGGING"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = "true"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0019 }
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyLog.setDebugEnabled(r0)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x0019:
            r3 = move-exception
            goto L_0x00ae
        L_0x001c:
            java.lang.String r0 = "event"
            com.tapjoy.TapjoyConnectCore.setSDKType(r0)     // Catch:{ all -> 0x0019 }
            r0 = 0
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r4 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r5 = com.tapjoy.TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR     // Catch:{ all -> 0x0019 }
            java.lang.String r1 = "The application context is NULL"
            r4.<init>(r5, r1)     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r3, (com.tapjoy.TapjoyErrorMessage) r4)     // Catch:{ all -> 0x0019 }
            if (r6 == 0) goto L_0x0037
            r6.onConnectFailure()     // Catch:{ all -> 0x0019 }
        L_0x0037:
            monitor-exit(r2)
            return r0
        L_0x0039:
            com.tapjoy.FiveRocksIntegration.a()     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyAppSettings.init(r3)     // Catch:{ TapjoyIntegrationException -> 0x0096, TapjoyException -> 0x007e }
            com.tapjoy.internal.ew$1 r1 = new com.tapjoy.internal.ew$1     // Catch:{ TapjoyIntegrationException -> 0x0096, TapjoyException -> 0x007e }
            r1.<init>(r3, r6)     // Catch:{ TapjoyIntegrationException -> 0x0096, TapjoyException -> 0x007e }
            com.tapjoy.TapjoyConnectCore.requestTapjoyConnect(r3, r4, r5, r1)     // Catch:{ TapjoyIntegrationException -> 0x0096, TapjoyException -> 0x007e }
            r4 = 1
            r2.c = r4     // Catch:{ all -> 0x0019 }
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0019 }
            r1 = 14
            if (r6 >= r1) goto L_0x0058
            java.lang.String r3 = "TapjoyAPI"
            java.lang.String r5 = "Automatic session tracking is not available on this device."
            com.tapjoy.TapjoyLog.i(r3, r5)     // Catch:{ all -> 0x0019 }
            goto L_0x007c
        L_0x0058:
            if (r5 == 0) goto L_0x006f
            java.lang.String r6 = "TJC_OPTION_DISABLE_AUTOMATIC_SESSION_TRACKING"
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x0019 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0019 }
            if (r5 == 0) goto L_0x006f
            java.lang.String r6 = "true"
            boolean r5 = r5.equalsIgnoreCase(r6)     // Catch:{ all -> 0x0019 }
            if (r5 == 0) goto L_0x006f
            r0 = 1
        L_0x006f:
            if (r0 != 0) goto L_0x0075
            com.tapjoy.internal.fp.a((android.content.Context) r3)     // Catch:{ all -> 0x0019 }
            goto L_0x007c
        L_0x0075:
            java.lang.String r3 = "TapjoyAPI"
            java.lang.String r5 = "Automatic session tracking is disabled."
            com.tapjoy.TapjoyLog.i(r3, r5)     // Catch:{ all -> 0x0019 }
        L_0x007c:
            monitor-exit(r2)
            return r4
        L_0x007e:
            r3 = move-exception
            java.lang.String r4 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r5 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.SDK_ERROR     // Catch:{ all -> 0x0019 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0019 }
            r5.<init>(r1, r3)     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r4, (com.tapjoy.TapjoyErrorMessage) r5)     // Catch:{ all -> 0x0019 }
            if (r6 == 0) goto L_0x0094
            r6.onConnectFailure()     // Catch:{ all -> 0x0019 }
        L_0x0094:
            monitor-exit(r2)
            return r0
        L_0x0096:
            r3 = move-exception
            java.lang.String r4 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r5 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR     // Catch:{ all -> 0x0019 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0019 }
            r5.<init>(r1, r3)     // Catch:{ all -> 0x0019 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r4, (com.tapjoy.TapjoyErrorMessage) r5)     // Catch:{ all -> 0x0019 }
            if (r6 == 0) goto L_0x00ac
            r6.onConnectFailure()     // Catch:{ all -> 0x0019 }
        L_0x00ac:
            monitor-exit(r2)
            return r0
        L_0x00ae:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ew.a(android.content.Context, java.lang.String, java.util.Hashtable, com.tapjoy.TJConnectListener):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(final android.content.Context r4, java.lang.String r5, final com.tapjoy.TJConnectListener r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "event"
            com.tapjoy.TapjoyConnectCore.setSDKType(r0)     // Catch:{ all -> 0x0059 }
            r0 = 0
            if (r4 != 0) goto L_0x001e
            java.lang.String r4 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r5 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "The application context is NULL"
            r5.<init>(r1, r2)     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r4, (com.tapjoy.TapjoyErrorMessage) r5)     // Catch:{ all -> 0x0059 }
            if (r6 == 0) goto L_0x001c
            r6.onConnectFailure()     // Catch:{ all -> 0x0059 }
        L_0x001c:
            monitor-exit(r3)
            return r0
        L_0x001e:
            com.tapjoy.internal.ew$2 r1 = new com.tapjoy.internal.ew$2     // Catch:{ TapjoyIntegrationException -> 0x0041, TapjoyException -> 0x0029 }
            r1.<init>(r4, r6)     // Catch:{ TapjoyIntegrationException -> 0x0041, TapjoyException -> 0x0029 }
            com.tapjoy.TapjoyConnectCore.requestLimitedTapjoyConnect(r4, r5, r1)     // Catch:{ TapjoyIntegrationException -> 0x0041, TapjoyException -> 0x0029 }
            r4 = 1
            monitor-exit(r3)
            return r4
        L_0x0029:
            r4 = move-exception
            java.lang.String r5 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.SDK_ERROR     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0059 }
            r1.<init>(r2, r4)     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x0059 }
            if (r6 == 0) goto L_0x003f
            r6.onConnectFailure()     // Catch:{ all -> 0x0059 }
        L_0x003f:
            monitor-exit(r3)
            return r0
        L_0x0041:
            r4 = move-exception
            java.lang.String r5 = "TapjoyAPI"
            com.tapjoy.TapjoyErrorMessage r1 = new com.tapjoy.TapjoyErrorMessage     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyErrorMessage$ErrorType r2 = com.tapjoy.TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0059 }
            r1.<init>(r2, r4)     // Catch:{ all -> 0x0059 }
            com.tapjoy.TapjoyLog.e((java.lang.String) r5, (com.tapjoy.TapjoyErrorMessage) r1)     // Catch:{ all -> 0x0059 }
            if (r6 == 0) goto L_0x0057
            r6.onConnectFailure()     // Catch:{ all -> 0x0059 }
        L_0x0057:
            monitor-exit(r3)
            return r0
        L_0x0059:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ew.a(android.content.Context, java.lang.String, com.tapjoy.TJConnectListener):boolean");
    }

    public final TJPlacement a(String str, TJPlacementListener tJPlacementListener) {
        return TJPlacementManager.a(str, "", "", tJPlacementListener);
    }

    public final TJPlacement b(String str, TJPlacementListener tJPlacementListener) {
        return TJPlacementManager.b(str, "", "", tJPlacementListener);
    }

    public final void a(Activity activity) {
        if (activity != null) {
            b.a(activity);
        } else {
            TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Cannot set activity to NULL"));
        }
    }

    public final void a(float f2) {
        if (k("setCurrencyMultiplier")) {
            TapjoyConnectCore.getInstance().setCurrencyMultiplier(f2);
        }
    }

    public final float c() {
        if (k("getCurrencyMultiplier")) {
            return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
        }
        return 1.0f;
    }

    public final void e(String str) {
        if (j("actionComplete")) {
            TapjoyConnectCore.getInstance().actionComplete(str);
        }
    }

    public final void a(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        if (this.e != null && j("getCurrencyBalance")) {
            this.e.getCurrencyBalance(tJGetCurrencyBalanceListener);
        }
    }

    public final void a(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (this.e != null && j("spendCurrency")) {
            this.e.spendCurrency(i, tJSpendCurrencyListener);
        }
    }

    public final void a(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (this.e != null && j("awardCurrency")) {
            this.e.awardCurrency(i, tJAwardCurrencyListener);
        }
    }

    public final void a(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        if (this.e != null && j("setEarnedCurrencyListener")) {
            this.e.setEarnedCurrencyListener(tJEarnedCurrencyListener);
        }
    }

    public final void a(TJVideoListener tJVideoListener) {
        if (k("setVideoListener")) {
            TJAdUnit.publisherVideoListener = tJVideoListener;
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        gr.a(str, str2, str3, str4);
    }

    public final void a(String str, String str2) {
        gr.a(str, (String) null, (String) null, str2);
    }

    public final void a(String str) {
        gr.a((String) null, str, (String) null, (String) null, 0);
    }

    public final void a(String str, long j) {
        gr.a((String) null, str, (String) null, (String) null, j);
    }

    public final void a(String str, String str2, long j) {
        gr.a(str, str2, (String) null, (String) null, j);
    }

    public final void b(String str, String str2, String str3, String str4) {
        gr.a(str, str2, str3, str4, 0);
    }

    public final void a(String str, String str2, String str3, String str4, long j) {
        gr.a(str, str2, str3, str4, j);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j) {
        gr.a(str, str2, str3, str4, str5, j, (String) null, 0, (String) null, 0);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        gr.a(str, str2, str3, str4, str5, j, str6, j2, (String) null, 0);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        gr.a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    public final void d() {
        if (k("startSession")) {
            if (Build.VERSION.SDK_INT >= 14) {
                fp.a();
            }
            TapjoyConnectCore.getInstance().appResume();
            gr.a();
        }
    }

    public final void e() {
        if (k("endSession")) {
            if (Build.VERSION.SDK_INT >= 14) {
                fp.a();
            }
            hd.a().n = false;
            TapjoyConnectCore.getInstance().appPause();
            gr.b();
        }
    }

    public final void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 14) {
            fp.a();
        }
        hd.a().n = true;
        gr.a(activity);
    }

    public final void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 14) {
            fp.a();
        }
        gr.b(activity);
    }

    public final void a(String str, TJSetUserIDListener tJSetUserIDListener) {
        if (k("setUserID")) {
            TapjoyConnectCore.setUserID(str, tJSetUserIDListener);
            hd a = hd.a();
            if (a.d("setUserId")) {
                a.f.b(gy.a(str));
            }
        } else if (tJSetUserIDListener != null) {
            tJSetUserIDListener.onSetUserIDFailure(this.d);
        }
    }

    public final Set f() {
        return hd.a().c();
    }

    public final void a(Set set) {
        hd.a().a(set);
    }

    public final void g() {
        hd.a().a((Set) null);
    }

    public final void c(String str) {
        if (!ju.c(str)) {
            hd a = hd.a();
            Set c2 = a.c();
            if (c2.add(str)) {
                a.a(c2);
            }
        }
    }

    public final void d(String str) {
        if (!ju.c(str)) {
            hd a = hd.a();
            Set c2 = a.c();
            if (c2.remove(str)) {
                a.a(c2);
            }
        }
    }

    public final boolean h() {
        hd a = hd.a();
        if (!a.d("isPushNotificationDisabled")) {
            return false;
        }
        boolean f2 = a.f.f();
        ha.a("isPushNotificationDisabled = {}", Boolean.valueOf(f2));
        return f2;
    }

    public final void b(boolean z) {
        String str;
        Object[] objArr;
        String str2;
        String str3;
        hd a = hd.a();
        if (a.d("setPushNotificationDisabled")) {
            boolean a2 = a.f.a(z);
            char c2 = 1;
            if (a2) {
                str2 = "setPushNotificationDisabled({}) called";
                str = Boolean.valueOf(z);
                objArr = new Object[1];
                c2 = 0;
            } else {
                str2 = "setPushNotificationDisabled({}) called, but it is already {}";
                objArr = new Object[2];
                objArr[0] = Boolean.valueOf(z);
                str = z ? "disabled" : TJAdUnitConstants.String.ENABLED;
            }
            objArr[c2] = str;
            ha.a(str2, objArr);
            if (a2 && a.k && !ju.c(a.d)) {
                if (a.o != null) {
                    str3 = null;
                } else {
                    hf b = hf.b(a.e);
                    str3 = ju.b(b.b.b(b.a));
                }
                a.a(str3);
            }
        }
    }

    public final boolean i() {
        return this.a;
    }

    public final boolean j() {
        return this.b;
    }

    public final String g(String str) {
        if (j("getSupportURL")) {
            return TapjoyConnectCore.getSupportURL(str);
        }
        return null;
    }

    public final String k() {
        if (j("getUserToken")) {
            return TapjoyConnectCore.getUserToken();
        }
        return null;
    }

    public final void i(String str) {
        hd.a().a(str);
    }

    public final void a(Context context, Map map) {
        hd a = hd.a();
        if (a.e == null) {
            a.b(context);
        }
        hf.b(a.e);
        Context context2 = a.e;
        boolean z = true;
        new Object[1][0] = map;
        String str = (String) map.get("fiverocks");
        if (str == null) {
            return;
        }
        if (hg.a(context2).f()) {
            hd.a(context2).b(str);
            return;
        }
        String str2 = (String) map.get(TJAdUnitConstants.String.TITLE);
        String str3 = (String) map.get("message");
        if (str3 != null) {
            String str4 = (String) map.get("rich");
            String str5 = (String) map.get("sound");
            String str6 = (String) map.get("payload");
            String str7 = (String) map.get("always");
            boolean z2 = "true".equals(str7) || Boolean.TRUE.equals(str7);
            String str8 = (String) map.get("repeatable");
            if (!"true".equals(str8) && !Boolean.TRUE.equals(str8)) {
                z = false;
            }
            String str9 = (String) map.get(VungleActivity.PLACEMENT_EXTRA);
            int b = hf.b(map.get("nid"));
            String str10 = (String) map.get("channel_id");
            if (z2 || !hd.a(context2).d()) {
                Notification a2 = hf.a(context2, str, ju.a(str2), str3, hf.a((Object) str4), hf.a((Object) str5), str6, str9, b, str10);
                if (hd.a(context2).a(context2, str, z)) {
                    hf.a(context2, b, a2);
                }
            }
        }
    }

    private boolean j(String str) {
        if (this.a) {
            return true;
        }
        TapjoyLog.w("TapjoyAPI", "Can not call " + str + " because Tapjoy SDK has not successfully connected.");
        return false;
    }

    private boolean k(String str) {
        if (this.c) {
            return true;
        }
        this.d = "Can not call " + str + " because Tapjoy SDK is not initialized.";
        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, this.d));
        return false;
    }

    public final void c(boolean z) {
        gi a = gi.a();
        a.a = Boolean.valueOf(z);
        if (!a.b()) {
            a.c = true;
        }
    }

    public final void h(String str) {
        gi a = gi.a();
        if (!ao.a(str)) {
            a.b = str;
            if (!a.c()) {
                a.c = true;
            }
        }
    }

    public final void a(String str, String str2, double d2, String str3) {
        hd a = hd.a();
        if (a.c("trackPurchase")) {
            String str4 = str;
            String a2 = gy.a(str, "trackPurchase", InAppPurchaseMetaData.KEY_PRODUCT_ID);
            if (a2 != null) {
                String str5 = str2;
                String a3 = gy.a(str2, "trackPurchase", "currencyCode");
                if (a3 == null) {
                    return;
                }
                if (a3.length() != 3) {
                    ha.a("trackPurchase", "currencyCode", "invalid currency code");
                    return;
                }
                a.g.a(a2, a3.toUpperCase(Locale.US), d2, (String) null, (String) null, gy.b(str3));
                ha.a("trackPurchase called");
            }
        }
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        hd a = hd.a();
        if (a.c("trackEvent") && !ju.c(str2)) {
            LinkedHashMap b = jy.b();
            if (map != null && map.size() > 0) {
                for (Map.Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    if (key == null) {
                        if (ha.a) {
                            aa.a(TMMediationNetworks.TAPJOY_NAME, "{}: {} must not be null", "trackEvent", "key in values map");
                            return;
                        }
                        return;
                    } else if (key instanceof String) {
                        String a2 = gy.a((String) key, "trackEvent", "key in values map");
                        if (a2 != null) {
                            Object value = entry.getValue();
                            if (value instanceof Number) {
                                b.put(a2, Long.valueOf(((Number) value).longValue()));
                            } else {
                                ha.a("trackEvent", "value in values map", "must be a long");
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
            a.g.a(str, str2, str3, str4, b);
            ha.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    public final void a(int i) {
        hd a = hd.a();
        if (a.d("setUserLevel")) {
            ha.a("setUserLevel({}) called", Integer.valueOf(i));
            a.f.a(i >= 0 ? Integer.valueOf(i) : null);
        }
    }

    public final void b(int i) {
        hd a = hd.a();
        if (a.d("setUserFriendCount")) {
            ha.a("setUserFriendCount({}) called", Integer.valueOf(i));
            a.f.b(i >= 0 ? Integer.valueOf(i) : null);
        }
    }

    public final void b(String str) {
        hd a = hd.a();
        if (a.d("setAppDataVersion")) {
            a.f.a(gy.a(str));
        }
    }

    public final void a(int i, String str) {
        hd a = hd.a();
        if (a.d("setUserCohortVariable")) {
            boolean z = i > 0 && i <= 5;
            if (ha.a && !z) {
                ha.b("setCohortVariable: variableIndex is out of range");
            }
            if (z) {
                ha.a("setUserCohortVariable({}, {}) called", Integer.valueOf(i), str);
                a.f.a(i, gy.a(str));
            }
        }
    }

    public final void f(String str) {
        hd a = hd.a();
        ha.a("setGcmSender({}) called", str);
        a.d = ju.a(str);
        a.b();
    }

    public final void a(GLSurfaceView gLSurfaceView) {
        hd.a();
        hd.a(gLSurfaceView);
    }
}
