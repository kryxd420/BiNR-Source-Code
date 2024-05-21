package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.impl.sdk.b.b;

public class p {
    private final j a;

    p(j jVar) {
        this.a = jVar;
    }

    private boolean a() {
        return this.a.w().d();
    }

    public void a(String str, Boolean bool, String str2) {
        a(str, bool, str2, (Throwable) null);
    }

    public void a(String str, Boolean bool, String str2, Throwable th) {
        if (a()) {
            Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
        }
        if (bool.booleanValue() && ((Boolean) this.a.a(b.eH)).booleanValue() && this.a.I() != null) {
            this.a.I().a(str2, th);
        }
    }

    public void a(String str, String str2) {
        if (a()) {
            Log.d("AppLovinSdk", "[" + str + "] " + str2);
        }
    }

    public void a(String str, String str2, Throwable th) {
        if (a()) {
            Log.w("AppLovinSdk", "[" + str + "] " + str2, th);
        }
    }

    public void b(String str, String str2) {
        if (a()) {
            Log.i("AppLovinSdk", "[" + str + "] " + str2);
        }
    }

    public void b(String str, String str2, Throwable th) {
        a(str, true, str2, th);
    }

    public void c(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    public void c(String str, String str2, Throwable th) {
        Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
    }

    public void d(String str, String str2) {
        b(str, str2, (Throwable) null);
    }

    public void e(String str, String str2) {
        c(str, str2, (Throwable) null);
    }
}
