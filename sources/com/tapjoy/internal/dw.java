package com.tapjoy.internal;

import android.os.Build;
import android.webkit.WebView;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.model.Cookie;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class dw {
    public dv a = new dv((WebView) null);
    public cu b;
    public df c;
    public int d;
    public double e;

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;

        static {
            d = new int[]{a, b, c};
        }
    }

    public dw() {
        d();
    }

    public void a() {
    }

    /* access modifiers changed from: package-private */
    public final void a(WebView webView) {
        this.a = new dv(webView);
    }

    public final void a(String str) {
        dj.a().a(c(), str, (JSONObject) null);
    }

    public final void a(String str, JSONObject jSONObject) {
        dj.a().a(c(), str, jSONObject);
    }

    public void b() {
        this.a.clear();
    }

    public final WebView c() {
        return (WebView) this.a.get();
    }

    public final void d() {
        this.e = dr.a();
        this.d = a.a;
    }

    public final void a(dc dcVar, cx cxVar) {
        String str = dcVar.f;
        JSONObject jSONObject = new JSONObject();
        dp.a(jSONObject, "environment", TapjoyConstants.TJC_APP_PLACEMENT);
        dp.a(jSONObject, "adSessionType", cxVar.f);
        JSONObject jSONObject2 = new JSONObject();
        dp.a(jSONObject2, "deviceType", Build.MANUFACTURER + "; " + Build.MODEL);
        dp.a(jSONObject2, "osVersion", Integer.toString(Build.VERSION.SDK_INT));
        dp.a(jSONObject2, "os", "Android");
        dp.a(jSONObject, "deviceInfo", jSONObject2);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        dp.a(jSONObject, "supports", jSONArray);
        JSONObject jSONObject3 = new JSONObject();
        dp.a(jSONObject3, "partnerName", cxVar.a.a);
        dp.a(jSONObject3, "partnerVersion", cxVar.a.b);
        dp.a(jSONObject, "omidNativeInfo", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        dp.a(jSONObject4, "libraryVersion", "1.1.0-tapjoy");
        dp.a(jSONObject4, Cookie.APP_ID, di.a().a.getApplicationContext().getPackageName());
        dp.a(jSONObject, TapjoyConstants.TJC_APP_PLACEMENT, jSONObject4);
        if (cxVar.e != null) {
            dp.a(jSONObject, "customReferenceData", cxVar.e);
        }
        JSONObject jSONObject5 = new JSONObject();
        for (db dbVar : Collections.unmodifiableList(cxVar.c)) {
            dp.a(jSONObject5, dbVar.a, dbVar.c);
        }
        dj.a().a(c(), "startSession", str, jSONObject, jSONObject5);
    }

    public final void a(float f) {
        dj.a().a(c(), "setDeviceVolume", Float.valueOf(f));
    }
}
