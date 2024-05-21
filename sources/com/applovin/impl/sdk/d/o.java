package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.ad.c;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.HashMap;
import java.util.Map;

public class o extends m {
    private final c a;

    public o(c cVar, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super(d.a("adtoken_zone", jVar), appLovinAdLoadListener, "TaskFetchTokenAd", jVar);
        this.a = cVar;
    }

    public i a() {
        return i.p;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> f() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("adtoken", k.e(this.a.a()));
        hashMap.put("adtoken_prefix", k.e(this.a.c()));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public b g() {
        return b.REGULAR_AD_TOKEN;
    }
}
