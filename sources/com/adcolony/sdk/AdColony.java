package com.adcolony.sdk;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.os.EnvironmentCompat;
import com.adcolony.sdk.aa;
import com.adcolony.sdk.aw;
import com.applovin.sdk.AppLovinEventParameters;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.vungle.warren.model.Cookie;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

public class AdColony {
    static ExecutorService a = Executors.newSingleThreadExecutor();

    static boolean a(String str, ba baVar, int i, int i2) {
        return true;
    }

    public static boolean disable() {
        if (!a.e()) {
            return false;
        }
        Activity c = a.c();
        if (c != null && (c instanceof b)) {
            c.finish();
        }
        final l a2 = a.a();
        for (final AdColonyInterstitial next : a2.m().c().values()) {
            aw.a((Runnable) new Runnable() {
                public void run() {
                    AdColonyInterstitialListener listener = next.getListener();
                    next.a(true);
                    if (listener != null) {
                        listener.onExpiring(next);
                    }
                }
            });
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList();
                Iterator<ai> it = a2.q().c().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ai aiVar = (ai) it2.next();
                    a2.a(aiVar.a());
                    if (aiVar instanceof ay) {
                        ay ayVar = (ay) aiVar;
                        if (!ayVar.g()) {
                            ayVar.loadUrl("about:blank");
                            ayVar.clearCache(true);
                            ayVar.removeAllViews();
                            ayVar.a(true);
                        }
                    }
                }
            }
        });
        a.a().a(true);
        return true;
    }

    public static boolean configure(Activity activity, @NonNull String str, @NonNull String... strArr) {
        return configure(activity, (AdColonyAppOptions) null, str, strArr);
    }

    public static boolean configure(Activity activity, AdColonyAppOptions adColonyAppOptions, @NonNull String str, @NonNull String... strArr) {
        Activity c;
        if (ak.a(0, (Bundle) null)) {
            new aa.a().a("Cannot configure AdColony; configuration mechanism requires 5 ").a("seconds between attempts.").a(aa.e);
            return false;
        }
        if (activity == null && (c = a.c()) != null) {
            activity = c;
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (adColonyAppOptions == null) {
            adColonyAppOptions = new AdColonyAppOptions();
        }
        if (a.b() && !y.d(a.a().d().d(), "reconfigurable")) {
            l a2 = a.a();
            if (!a2.d().a().equals(str)) {
                new aa.a().a("Ignoring call to AdColony.configure, as the app id does not ").a("match what was used during the initial configuration.").a(aa.e);
                return false;
            } else if (aw.a(strArr, a2.d().b())) {
                new aa.a().a("Ignoring call to AdColony.configure, as the same zone ids ").a("were used during the previous configuration.").a(aa.e);
                return false;
            }
        }
        adColonyAppOptions.a(str);
        adColonyAppOptions.a(strArr);
        adColonyAppOptions.e();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        long currentTimeMillis = System.currentTimeMillis();
        String format = simpleDateFormat.format(new Date(currentTimeMillis));
        boolean z = true;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && !strArr[i].equals("")) {
                z = false;
            }
        }
        if (str.equals("") || z) {
            new aa.a().a("AdColony.configure() called with an empty app or zone id String.").a(aa.g);
            return false;
        }
        a.a = true;
        if (Build.VERSION.SDK_INT < 14) {
            new aa.a().a("The minimum API level for the AdColony SDK is 14.").a(aa.e);
            a.a(activity, adColonyAppOptions, true);
        } else {
            a.a(activity, adColonyAppOptions, false);
        }
        String str2 = a.a().o().c() + "/adc3/AppInfo";
        JSONObject a3 = y.a();
        if (new File(str2).exists()) {
            a3 = y.c(str2);
        }
        JSONObject a4 = y.a();
        if (y.b(a3, Cookie.APP_ID).equals(str)) {
            y.a(a4, "zoneIds", y.a(y.g(a3, "zoneIds"), strArr, true));
            y.a(a4, Cookie.APP_ID, str);
        } else {
            y.a(a4, "zoneIds", y.a(strArr));
            y.a(a4, Cookie.APP_ID, str);
        }
        y.h(a4, str2);
        new aa.a().a("Configure: Total Time (ms): ").a("" + (System.currentTimeMillis() - currentTimeMillis)).a(" and started at " + format).a(aa.f);
        return true;
    }

    public static AdColonyZone getZone(@NonNull String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.getZone() as AdColony has not yet been ").a("configured.").a(aa.e);
            return null;
        }
        HashMap<String, AdColonyZone> f = a.a().f();
        if (f.containsKey(str)) {
            return f.get(str);
        }
        AdColonyZone adColonyZone = new AdColonyZone(str);
        a.a().f().put(str, adColonyZone);
        return adColonyZone;
    }

    public static boolean notifyIAPComplete(@NonNull String str, @NonNull String str2) {
        return notifyIAPComplete(str, str2, (String) null, -1.0d);
    }

    public static boolean notifyIAPComplete(@NonNull String str, @NonNull String str2, String str3, @FloatRange(from = 0.0d) double d) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to notifyIAPComplete as AdColony has not yet been ").a("configured.").a(aa.e);
            return false;
        } else if (!aw.d(str) || !aw.d(str2)) {
            new aa.a().a("Ignoring call to notifyIAPComplete as one of the passed Strings ").a("is greater than ").a(128).a(" characters.").a(aa.e);
            return false;
        } else {
            if (str3 != null && str3.length() > 3) {
                new aa.a().a("You are trying to report an IAP event with a currency String ").a("containing more than 3 characters.").a(aa.e);
            }
            final double d2 = d;
            final String str4 = str3;
            final String str5 = str;
            final String str6 = str2;
            a.execute(new Runnable() {
                public void run() {
                    AdColony.a();
                    JSONObject a2 = y.a();
                    if (d2 >= 0.0d) {
                        y.a(a2, InAppPurchaseMetaData.KEY_PRICE, d2);
                    }
                    if (str4 != null && str4.length() <= 3) {
                        y.a(a2, "currency_code", str4);
                    }
                    y.a(a2, "product_id", str5);
                    y.a(a2, AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, str6);
                    new af("AdColony.on_iap_report", 1, a2).b();
                }
            });
            return true;
        }
    }

    public static boolean requestNativeAdView(@NonNull String str, @NonNull AdColonyNativeAdViewListener adColonyNativeAdViewListener, @NonNull AdColonyAdSize adColonyAdSize) {
        return requestNativeAdView(str, adColonyNativeAdViewListener, adColonyAdSize, (AdColonyAdOptions) null);
    }

    public static boolean requestNativeAdView(@NonNull final String str, @NonNull final AdColonyNativeAdViewListener adColonyNativeAdViewListener, @NonNull final AdColonyAdSize adColonyAdSize, final AdColonyAdOptions adColonyAdOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to requestNativeAdView as AdColony has not yet been").a(" configured.").a(aa.e);
            a(adColonyNativeAdViewListener, str);
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", str);
        if (ak.a(1, bundle)) {
            a(adColonyNativeAdViewListener, str);
            return false;
        }
        try {
            a.execute(new Runnable() {
                public void run() {
                    l a2 = a.a();
                    if (a2.g() || a2.h()) {
                        AdColony.b();
                        AdColony.a(adColonyNativeAdViewListener, str);
                    }
                    if (!AdColony.a() && a.d()) {
                        AdColony.a(adColonyNativeAdViewListener, str);
                    }
                    if (a2.f().get(str) == null) {
                        new AdColonyZone(str);
                        new aa.a().a("Zone info for ").a(str).a(" doesn't exist in hashmap").a(aa.b);
                    }
                    a2.m().a(str, adColonyNativeAdViewListener, adColonyAdSize, adColonyAdOptions);
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            a(adColonyNativeAdViewListener, str);
            return false;
        }
    }

    public static boolean setAppOptions(@NonNull final AdColonyAppOptions adColonyAppOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.setAppOptions() as AdColony has not yet").a(" been configured.").a(aa.e);
            return false;
        }
        a.a().b(adColonyAppOptions);
        adColonyAppOptions.e();
        try {
            a.execute(new Runnable() {
                public void run() {
                    AdColony.a();
                    JSONObject a2 = y.a();
                    y.a(a2, "options", adColonyAppOptions.d());
                    new af("Options.set_options", 1, a2).b();
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public static AdColonyAppOptions getAppOptions() {
        if (!a.e()) {
            return null;
        }
        return a.a().d();
    }

    public static boolean setRewardListener(@NonNull AdColonyRewardListener adColonyRewardListener) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.setRewardListener() as AdColony has not").a(" yet been configured.").a(aa.e);
            return false;
        }
        a.a().a(adColonyRewardListener);
        return true;
    }

    public static boolean removeRewardListener() {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.removeRewardListener() as AdColony has ").a("not yet been configured.").a(aa.e);
            return false;
        }
        a.a().a((AdColonyRewardListener) null);
        return true;
    }

    public static String getSDKVersion() {
        if (!a.e()) {
            return "";
        }
        return a.a().n().C();
    }

    public static AdColonyRewardListener getRewardListener() {
        if (!a.e()) {
            return null;
        }
        return a.a().i();
    }

    public static boolean addCustomMessageListener(@NonNull AdColonyCustomMessageListener adColonyCustomMessageListener, final String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.addCustomMessageListener as AdColony ").a("has not yet been configured.").a(aa.e);
            return false;
        } else if (!aw.d(str)) {
            new aa.a().a("Ignoring call to AdColony.addCustomMessageListener.").a(aa.e);
            return false;
        } else {
            try {
                a.a().z().put(str, adColonyCustomMessageListener);
                a.execute(new Runnable() {
                    public void run() {
                        AdColony.a();
                        JSONObject a2 = y.a();
                        y.a(a2, "type", str);
                        new af("CustomMessage.register", 1, a2).b();
                    }
                });
                return true;
            } catch (RejectedExecutionException unused) {
                return false;
            }
        }
    }

    public static AdColonyCustomMessageListener getCustomMessageListener(@NonNull String str) {
        if (!a.e()) {
            return null;
        }
        return a.a().z().get(str);
    }

    public static boolean removeCustomMessageListener(@NonNull final String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.removeCustomMessageListener as AdColony").a(" has not yet been configured.").a(aa.e);
            return false;
        }
        a.a().z().remove(str);
        a.execute(new Runnable() {
            public void run() {
                AdColony.a();
                JSONObject a2 = y.a();
                y.a(a2, "type", str);
                new af("CustomMessage.unregister", 1, a2).b();
            }
        });
        return true;
    }

    public static boolean clearCustomMessageListeners() {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.clearCustomMessageListeners as AdColony").a(" has not yet been configured.").a(aa.e);
            return false;
        }
        a.a().z().clear();
        a.execute(new Runnable() {
            public void run() {
                AdColony.a();
                for (String a : a.a().z().keySet()) {
                    JSONObject a2 = y.a();
                    y.a(a2, "type", a);
                    new af("CustomMessage.unregister", 1, a2).b();
                }
            }
        });
        return true;
    }

    public static boolean requestInterstitial(@NonNull String str, @NonNull AdColonyInterstitialListener adColonyInterstitialListener) {
        return requestInterstitial(str, adColonyInterstitialListener, (AdColonyAdOptions) null);
    }

    public static boolean requestInterstitial(@NonNull final String str, @NonNull final AdColonyInterstitialListener adColonyInterstitialListener, final AdColonyAdOptions adColonyAdOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.requestInterstitial as AdColony has not").a(" yet been configured.").a(aa.e);
            adColonyInterstitialListener.onRequestNotFilled(new AdColonyZone(str));
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", str);
        if (ak.a(1, bundle)) {
            AdColonyZone adColonyZone = a.a().f().get(str);
            if (adColonyZone == null) {
                adColonyZone = new AdColonyZone(str);
                aa.a a2 = new aa.a().a("Zone info for ");
                a2.a(str + " doesn't exist in hashmap").a(aa.b);
            }
            adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
            return false;
        }
        try {
            a.execute(new Runnable() {
                public void run() {
                    l a2 = a.a();
                    if (a2.g() || a2.h()) {
                        AdColony.b();
                        AdColony.a(adColonyInterstitialListener, str);
                    } else if (AdColony.a() || !a.d()) {
                        final AdColonyZone adColonyZone = a2.f().get(str);
                        if (adColonyZone == null) {
                            adColonyZone = new AdColonyZone(str);
                            aa.a a3 = new aa.a().a("Zone info for ");
                            a3.a(str + " doesn't exist in hashmap").a(aa.b);
                        }
                        if (adColonyZone.getZoneType() != 2) {
                            a2.m().a(str, adColonyInterstitialListener, adColonyAdOptions);
                            return;
                        }
                        Activity c2 = a.c();
                        if (c2 != null) {
                            c2.runOnUiThread(new Runnable() {
                                public void run() {
                                    adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
                                }
                            });
                        }
                    } else {
                        AdColony.a(adColonyInterstitialListener, str);
                    }
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            a(adColonyInterstitialListener, str);
            return false;
        }
    }

    static boolean a() {
        aw.a aVar = new aw.a(15.0d);
        l a2 = a.a();
        while (!a2.A() && !aVar.b()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException unused) {
            }
        }
        return a2.A();
    }

    static boolean a(final AdColonyInterstitialListener adColonyInterstitialListener, final String str) {
        if (adColonyInterstitialListener == null || !a.d()) {
            return false;
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                AdColonyZone adColonyZone = a.a().f().get(str);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                }
                adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
            }
        });
        return false;
    }

    static boolean a(final AdColonyNativeAdViewListener adColonyNativeAdViewListener, final String str) {
        if (adColonyNativeAdViewListener == null || !a.d()) {
            return false;
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                AdColonyZone adColonyZone;
                if (!a.b()) {
                    adColonyZone = null;
                } else {
                    adColonyZone = a.a().f().get(str);
                }
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                }
                adColonyNativeAdViewListener.onRequestNotFilled(adColonyZone);
            }
        });
        return false;
    }

    static void a(Activity activity, AdColonyAppOptions adColonyAppOptions) {
        if (adColonyAppOptions != null && activity != null) {
            String a2 = aw.a(activity);
            String b = aw.b();
            int c = aw.c();
            String h = a.a().c.h();
            String str = "none";
            if (a.a().p().a()) {
                str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
            } else if (a.a().p().b()) {
                str = TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("sessionId", EnvironmentCompat.MEDIA_UNKNOWN);
            hashMap.put("advertiserId", EnvironmentCompat.MEDIA_UNKNOWN);
            hashMap.put("countryLocale", Locale.getDefault().getDisplayLanguage() + " (" + Locale.getDefault().getDisplayCountry() + ")");
            hashMap.put("countryLocalShort", a.a().c.u());
            hashMap.put("manufacturer", a.a().c.w());
            hashMap.put("model", a.a().c.x());
            hashMap.put("osVersion", a.a().c.y());
            hashMap.put("carrierName", h);
            hashMap.put("networkType", str);
            hashMap.put(TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
            hashMap.put("appName", a2);
            hashMap.put("appVersion", b);
            hashMap.put("appBuildNumber", Integer.valueOf(c));
            hashMap.put(Cookie.APP_ID, "" + adColonyAppOptions.a());
            hashMap.put("apiLevel", Integer.valueOf(a.a().c.r()));
            hashMap.put("sdkVersion", a.a().c.C());
            hashMap.put("controllerVersion", EnvironmentCompat.MEDIA_UNKNOWN);
            hashMap.put("zoneIds", adColonyAppOptions.c());
            JSONObject mediationInfo = adColonyAppOptions.getMediationInfo();
            JSONObject pluginInfo = adColonyAppOptions.getPluginInfo();
            if (!y.b(mediationInfo, "mediation_network").equals("")) {
                hashMap.put("mediationNetwork", y.b(mediationInfo, "mediation_network"));
                hashMap.put("mediationNetworkVersion", y.b(mediationInfo, "mediation_network_version"));
            }
            if (!y.b(pluginInfo, TapjoyConstants.TJC_PLUGIN).equals("")) {
                hashMap.put(TapjoyConstants.TJC_PLUGIN, y.b(pluginInfo, TapjoyConstants.TJC_PLUGIN));
                hashMap.put("pluginVersion", y.b(pluginInfo, "plugin_version"));
            }
            ac.a((HashMap<String, Object>) hashMap);
        }
    }

    static void b() {
        new aa.a().a("The AdColony API is not available while AdColony is disabled.").a(aa.g);
    }

    static String c() {
        return a.a().n().c();
    }

    static String d() {
        return a.a().n().b();
    }
}
