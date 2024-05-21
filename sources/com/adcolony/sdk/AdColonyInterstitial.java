package com.adcolony.sdk;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.adcolony.sdk.aa;
import org.json.JSONObject;

public class AdColonyInterstitial {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    private AdColonyInterstitialListener a;
    private c b;
    private AdColonyAdOptions c;
    private g d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;

    AdColonyInterstitial(String str, AdColonyInterstitialListener adColonyInterstitialListener, String str2) {
        this.a = adColonyInterstitialListener;
        this.j = str2;
        this.f = str;
    }

    public boolean show() {
        if (!a.b()) {
            return false;
        }
        l a2 = a.a();
        if (this.l) {
            new aa.a().a("This ad object has already been shown. Please request a new ad ").a("via AdColony.requestInterstitial.").a(aa.e);
            return false;
        } else if (this.k) {
            new aa.a().a("This ad object has expired. Please request a new ad via AdColony").a(".requestInterstitial.").a(aa.e);
            return false;
        } else if (a2.w()) {
            new aa.a().a("Can not show ad while an interstitial is already active.").a(aa.e);
            return false;
        } else if (a(a2.f().get(this.j))) {
            new aa.a().a("Skipping show()").a(aa.d);
            return false;
        } else {
            JSONObject a3 = y.a();
            y.a(a3, "zone_id", this.j);
            y.b(a3, "type", 0);
            y.a(a3, "id", this.f);
            if (this.c != null) {
                y.a(a3, "pre_popup", this.c.a);
                y.a(a3, "post_popup", this.c.b);
            }
            AdColonyZone adColonyZone = a2.f().get(this.j);
            if (adColonyZone != null && adColonyZone.isRewarded() && a2.i() == null) {
                new aa.a().a("Rewarded ad: show() called with no reward listener set.").a(aa.e);
            }
            new af("AdSession.launch_ad_unit", 1, a3).b();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(AdColonyZone adColonyZone) {
        if (adColonyZone == null) {
            return true;
        }
        if (adColonyZone.getPlayFrequency() <= 1) {
            return false;
        }
        if (adColonyZone.b() == 0) {
            adColonyZone.a(adColonyZone.getPlayFrequency() - 1);
            return false;
        }
        adColonyZone.a(adColonyZone.b() - 1);
        return true;
    }

    public boolean cancel() {
        if (this.b == null) {
            return false;
        }
        Activity c2 = a.c();
        if (c2 != null && !(c2 instanceof AdColonyInterstitialActivity)) {
            return false;
        }
        JSONObject a2 = y.a();
        y.a(a2, "id", this.b.b());
        new af("AdSession.on_request_close", this.b.c(), a2).b();
        return true;
    }

    public AdColonyInterstitialListener getListener() {
        return this.a;
    }

    public void setListener(@NonNull AdColonyInterstitialListener adColonyInterstitialListener) {
        this.a = adColonyInterstitialListener;
    }

    public String getZoneID() {
        return this.j;
    }

    public boolean isExpired() {
        return this.k || this.l;
    }

    public boolean destroy() {
        a.a().m().c().remove(this.f);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        Activity c2 = a.c();
        if (c2 == null || !a.b()) {
            return false;
        }
        a.a().c(true);
        a.a().a(this.b);
        a.a().a(this);
        new aa.a().a("Launching fullscreen Activity via AdColonyInterstitial's launch ").a("method.").a(aa.b);
        c2.startActivity(new Intent(c2, AdColonyInterstitialActivity.class));
        this.l = true;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        if (jSONObject.length() > 0) {
            this.d = new g(jSONObject);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar) {
        this.b = cVar;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.k = z;
    }

    /* access modifiers changed from: package-private */
    public void a(AdColonyAdOptions adColonyAdOptions) {
        this.c = adColonyAdOptions;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.i = i2;
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        this.e = i2;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        if (this.g == null) {
            return "";
        }
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.g = str;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        if (this.h == null) {
            return "";
        }
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.h = str;
    }

    /* access modifiers changed from: package-private */
    public c d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        this.m = z;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.d != null;
    }

    /* access modifiers changed from: package-private */
    public g h() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.m;
    }
}
