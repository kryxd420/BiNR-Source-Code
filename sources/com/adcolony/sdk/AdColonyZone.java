package com.adcolony.sdk;

import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.adcolony.sdk.aa;
import org.json.JSONObject;

public class AdColonyZone {
    public static final int BANNER = 1;
    public static final int INTERSTITIAL = 0;
    public static final int NATIVE = 2;
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    static final int f = 5;
    static final int g = 6;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l = 5;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;

    AdColonyZone(@NonNull String str) {
        this.h = str;
    }

    private int c(int i2) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return i2;
        }
        c();
        return 0;
    }

    private boolean a(boolean z) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return z;
        }
        c();
        return false;
    }

    private String a(String str) {
        return a(str, "");
    }

    private String a(String str, String str2) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return str;
        }
        c();
        return str2;
    }

    public String getZoneID() {
        return a(this.h);
    }

    public int getRemainingViewsUntilReward() {
        return c(this.n);
    }

    public int getRewardAmount() {
        return c(this.q);
    }

    public String getRewardName() {
        return a(this.i);
    }

    public int getViewsPerReward() {
        return c(this.o);
    }

    public int getZoneType() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.l == 0;
    }

    public boolean isValid() {
        return a(this.s);
    }

    public boolean isRewarded() {
        return this.t;
    }

    public int getPlayFrequency() {
        return c(this.p);
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar) {
        JSONObject c2 = afVar.c();
        JSONObject f2 = y.f(c2, "reward");
        this.i = y.b(f2, "reward_name");
        this.q = y.c(f2, "reward_amount");
        this.o = y.c(f2, "views_per_reward");
        this.n = y.c(f2, "views_until_reward");
        this.j = y.b(f2, "reward_name_plural");
        this.k = y.b(f2, "reward_prompt");
        this.t = y.d(c2, "rewarded");
        this.l = y.c(c2, NotificationCompat.CATEGORY_STATUS);
        this.m = y.c(c2, "type");
        this.p = y.c(c2, "play_interval");
        this.h = y.b(c2, "zone_id");
        boolean z = true;
        if (this.l == 1) {
            z = false;
        }
        this.s = z;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.r = i2;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        this.l = i2;
    }

    private void c() {
        new aa.a().a("The AdColonyZone API is not available while AdColony is disabled.").a(aa.g);
    }
}
