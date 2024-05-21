package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.j;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Map;
import org.json.JSONObject;

public class n extends m {
    private final int a;
    private final AppLovinNativeAdLoadListener c;

    public n(String str, int i, j jVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(d.b(str, jVar), (AppLovinAdLoadListener) null, "TaskFetchNextNativeAd", jVar);
        this.a = i;
        this.c = appLovinNativeAdLoadListener;
    }

    public i a() {
        return i.o;
    }

    /* access modifiers changed from: protected */
    public a a(JSONObject jSONObject) {
        return new u(jSONObject, this.b, this.c);
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (this.c != null) {
            this.c.onNativeAdsFailedToLoad(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> f() {
        Map<String, String> f = super.f();
        f.put("slot_count", Integer.toString(this.a));
        return f;
    }

    /* access modifiers changed from: protected */
    public String h() {
        return ((String) this.b.a(b.aD)) + "4.0/nad";
    }

    /* access modifiers changed from: protected */
    public String i() {
        return ((String) this.b.a(b.aE)) + "4.0/nad";
    }
}
