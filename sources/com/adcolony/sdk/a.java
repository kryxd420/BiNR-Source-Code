package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import com.adcolony.sdk.aa;
import com.vungle.warren.model.Cookie;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class a {
    static boolean a;
    static boolean b;
    private static WeakReference<Activity> c;
    /* access modifiers changed from: private */
    public static l d;

    a() {
    }

    static void a(final Activity activity, AdColonyAppOptions adColonyAppOptions, boolean z) {
        a(activity);
        b = true;
        if (d == null) {
            d = new l();
            d.a(adColonyAppOptions, z);
        } else {
            d.a(adColonyAppOptions);
        }
        aw.b.execute(new Runnable() {
            public void run() {
                a.d.a((Context) activity, (af) null);
            }
        });
        new aa.a().a("Configuring AdColony").a(aa.c);
        d.b(false);
        d.l().d(true);
        d.l().e(true);
        d.l().f(false);
        d.f = true;
        d.l().a(false);
    }

    static l a() {
        if (!b()) {
            Activity c2 = c();
            if (c2 == null) {
                return new l();
            }
            d = new l();
            JSONObject c3 = y.c(c2.getFilesDir().getAbsolutePath() + "/adc3/AppInfo");
            JSONArray g = y.g(c3, "zoneIds");
            d.a(new AdColonyAppOptions().a(y.b(c3, Cookie.APP_ID)).a(y.a(g)), false);
        }
        return d;
    }

    static boolean b() {
        return d != null;
    }

    static void a(Activity activity) {
        if (activity == null) {
            c.clear();
        } else {
            c = new WeakReference<>(activity);
        }
    }

    static Activity c() {
        if (c == null) {
            return null;
        }
        return (Activity) c.get();
    }

    static boolean d() {
        return (c == null || c.get() == null) ? false : true;
    }

    static boolean e() {
        return a;
    }

    static void a(String str, ah ahVar) {
        a().q().a(str, ahVar);
    }

    static ah a(String str, ah ahVar, boolean z) {
        a().q().a(str, ahVar);
        return ahVar;
    }

    static void b(String str, ah ahVar) {
        a().q().b(str, ahVar);
    }

    static void f() {
        a().q().b();
    }

    static void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = y.a();
        }
        y.a(jSONObject, "m_type", str);
        a().q().a(jSONObject);
    }

    static void a(String str) {
        try {
            af afVar = new af("CustomMessage.send", 0);
            afVar.c().put("message", str);
            afVar.b();
        } catch (JSONException e) {
            new aa.a().a("JSON error from ADC.java's send_custom_message(): ").a(e.toString()).a(aa.h);
        }
    }
}
