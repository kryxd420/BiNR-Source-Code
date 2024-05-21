package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.tapjoy.internal.ev;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class Tapjoy {
    public static final String INTENT_EXTRA_PUSH_PAYLOAD = "com.tapjoy.PUSH_PAYLOAD";

    public static String getVersion() {
        return ev.a().b();
    }

    public static void setDebugEnabled(boolean z) {
        ev.a().a(z);
    }

    public static boolean connect(Context context, String str) {
        return ev.a().a(context, str);
    }

    public static boolean connect(Context context, String str, Hashtable hashtable) {
        return ev.a().a(context, str, hashtable, (TJConnectListener) null);
    }

    public static synchronized boolean connect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        boolean a;
        synchronized (Tapjoy.class) {
            a = ev.a().a(context, str, hashtable, tJConnectListener);
        }
        return a;
    }

    public static TJPlacement getPlacement(String str, TJPlacementListener tJPlacementListener) {
        return ev.a().a(str, tJPlacementListener);
    }

    public static void setActivity(Activity activity) {
        ev.a().a(activity);
    }

    public static void getCurrencyBalance(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        ev.a().a(tJGetCurrencyBalanceListener);
    }

    public static void spendCurrency(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        ev.a().a(i, tJSpendCurrencyListener);
    }

    public static void awardCurrency(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        ev.a().a(i, tJAwardCurrencyListener);
    }

    public static void setEarnedCurrencyListener(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        ev.a().a(tJEarnedCurrencyListener);
    }

    @Deprecated
    public static void setCurrencyMultiplier(float f) {
        ev.a().a(f);
    }

    @Deprecated
    public static float getCurrencyMultiplier() {
        return ev.a().c();
    }

    public static void trackPurchase(String str, String str2, double d, String str3) {
        ev.a().a(str, str2, d, str3);
    }

    public static void trackPurchase(String str, String str2, String str3, String str4) {
        ev.a().a(str, str2, str3, str4);
    }

    @Deprecated
    public static void trackPurchase(String str, String str2) {
        ev.a().a(str, str2);
    }

    public static void trackEvent(String str) {
        ev.a().a(str);
    }

    public static void trackEvent(String str, long j) {
        ev.a().a(str, j);
    }

    public static void trackEvent(String str, String str2, long j) {
        ev.a().a(str, str2, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4) {
        ev.a().b(str, str2, str3, str4);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, long j) {
        ev.a().a(str, str2, str3, str4, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j) {
        ev.a().a(str, str2, str3, str4, str5, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        ev.a().a(str, str2, str3, str4, str5, j, str6, j2);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        ev.a().a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, Map map) {
        ev.a().a(str, str2, str3, str4, map);
    }

    public static void startSession() {
        ev.a().d();
    }

    public static void endSession() {
        ev.a().e();
    }

    public static void onActivityStart(Activity activity) {
        ev.a().b(activity);
    }

    public static void onActivityStop(Activity activity) {
        ev.a().c(activity);
    }

    public static void setUserID(String str) {
        setUserID(str, (TJSetUserIDListener) null);
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        ev.a().a(str, tJSetUserIDListener);
    }

    public static void setUserLevel(int i) {
        ev.a().a(i);
    }

    public static void setUserFriendCount(int i) {
        ev.a().b(i);
    }

    public static void setAppDataVersion(String str) {
        ev.a().b(str);
    }

    public static void setUserCohortVariable(int i, String str) {
        ev.a().a(i, str);
    }

    public static Set getUserTags() {
        return ev.a().f();
    }

    public static void setUserTags(Set set) {
        ev.a().a(set);
    }

    public static void clearUserTags() {
        ev.a().g();
    }

    public static void addUserTag(String str) {
        ev.a().c(str);
    }

    public static void removeUserTag(String str) {
        ev.a().d(str);
    }

    public static void setVideoListener(TJVideoListener tJVideoListener) {
        ev.a().a(tJVideoListener);
    }

    public static void actionComplete(String str) {
        ev.a().e(str);
    }

    public static void setGcmSender(String str) {
        ev.a().f(str);
    }

    public static void setDeviceToken(String str) {
        ev.a().i(str);
    }

    public static void setReceiveRemoteNotification(Context context, Map map) {
        ev.a().a(context, map);
    }

    public static boolean isPushNotificationDisabled() {
        return ev.a().h();
    }

    public static void setPushNotificationDisabled(boolean z) {
        ev.a().b(z);
    }

    public static void loadSharedLibrary() {
        try {
            System.loadLibrary("tapjoy");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void setGLSurfaceView(GLSurfaceView gLSurfaceView) {
        ev.a().a(gLSurfaceView);
    }

    public static String getSupportURL() {
        return ev.a().g((String) null);
    }

    public static String getSupportURL(String str) {
        return ev.a().g(str);
    }

    public static String getUserToken() {
        return ev.a().k();
    }

    public static boolean isConnected() {
        return ev.a().i();
    }

    public static void subjectToGDPR(boolean z) {
        ev.a().c(z);
    }

    public static void setUserConsent(String str) {
        ev.a().h(str);
    }

    public static synchronized boolean limitedConnect(Context context, String str, TJConnectListener tJConnectListener) {
        boolean a;
        synchronized (Tapjoy.class) {
            a = ev.a().a(context, str, tJConnectListener);
        }
        return a;
    }

    public static boolean isLimitedConnected() {
        return ev.a().j();
    }

    public static TJPlacement getLimitedPlacement(String str, TJPlacementListener tJPlacementListener) {
        return ev.a().b(str, tJPlacementListener);
    }
}
