package com.tapjoy.internal;

import android.app.Activity;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;

public final class gr {
    public static void a(Activity activity) {
        hd a = hd.a();
        if (ha.a((Object) activity, "onActivityStart: The given activity was null")) {
            ha.c("onActivityStart");
            b.a(activity.getApplication());
            b.b(activity);
            if (a.c("onActivityStart") && a.e()) {
                gx.b(activity);
            }
        }
    }

    public static void b(Activity activity) {
        hd a = hd.a();
        if (ha.a((Object) activity, "onActivityStop: The given activity was null")) {
            ha.c("onActivityStop");
            b.c(activity);
            if (a.c("onActivityStop") && !b.b()) {
                a.h.a();
            }
        }
    }

    public static void a() {
        hd a = hd.a();
        if (a.c("startSession") && a.e()) {
            gx.b((Activity) null);
        }
    }

    public static void b() {
        hd a = hd.a();
        if (a.c("endSession")) {
            a.h.a();
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j) {
        hd a = hd.a();
        if (a.d("trackEvent") && ha.a((Object) str2, "trackEvent: name was null")) {
            LinkedHashMap linkedHashMap = null;
            if (j != 0) {
                linkedHashMap = jy.b();
                linkedHashMap.put("value", Long.valueOf(j));
            }
            a.g.a(str, str2, str3, str4, linkedHashMap);
            ha.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, linkedHashMap);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        String str8 = str2;
        String str9 = str5;
        String str10 = str6;
        String str11 = str7;
        hd a = hd.a();
        if (a.d("trackEvent") && ha.a((Object) str2, "trackEvent: name was null")) {
            LinkedHashMap b = jy.b();
            if (!(str9 == null || j == 0)) {
                b.put(str5, Long.valueOf(j));
            }
            if (!(str10 == null || j2 == 0)) {
                b.put(str10, Long.valueOf(j2));
            }
            if (!(str11 == null || j3 == 0)) {
                b.put(str11, Long.valueOf(j3));
            }
            if (b.isEmpty()) {
                b = null;
            }
            a.g.a(str, str2, str3, str4, b);
            ha.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str8, str3, str4, b);
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        hd a = hd.a();
        if (a.c("trackPurchase")) {
            try {
                e eVar = new e(str);
                String b = gy.b(eVar.a);
                String b2 = gy.b(eVar.f);
                if (b == null || b2 == null) {
                    ha.a("trackPurchase", "skuDetails", "insufficient fields");
                } else if (b2.length() != 3) {
                    ha.a("trackPurchase", "skuDetails", "invalid currency code");
                } else {
                    String b3 = gy.b(str2);
                    String b4 = gy.b(str3);
                    if (b3 != null) {
                        if (b4 != null) {
                            try {
                                f fVar = new f(b3);
                                if (ju.c(fVar.a) || ju.c(fVar.b) || ju.c(fVar.c) || fVar.d == 0) {
                                    ha.a("trackPurchase", "purchaseData", "insufficient fields");
                                }
                            } catch (IOException unused) {
                                ha.a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                            }
                        } else {
                            ha.a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
                        }
                    } else if (b4 != null) {
                        ha.a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                    }
                    String upperCase = b2.toUpperCase(Locale.US);
                    String b5 = gy.b(str4);
                    hc hcVar = a.g;
                    double d = (double) eVar.g;
                    Double.isNaN(d);
                    hcVar.a(b, upperCase, d / 1000000.0d, b3, b4, b5);
                    if (b3 == null || b4 == null) {
                        ha.a("trackPurchase without purchaseData called");
                    } else {
                        ha.a("trackPurchase with purchaseData called");
                    }
                }
            } catch (IOException unused2) {
                ha.a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            }
        }
    }
}
