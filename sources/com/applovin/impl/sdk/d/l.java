package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.b;
import com.applovin.impl.sdk.ad.d;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class l extends m {
    private final List<String> a;

    public l(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener, j jVar) {
        super(d.a(a(list), jVar), appLovinAdLoadListener, "TaskFetchMultizoneAd", jVar);
        this.a = Collections.unmodifiableList(list);
    }

    private static String a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        throw new IllegalArgumentException("No zone identifiers specified");
    }

    public i a() {
        return i.m;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> f() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("zone_ids", k.e(com.applovin.impl.sdk.e.d.a((Collection<String>) this.a, this.a.size())));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public b g() {
        return b.APPLOVIN_MULTIZONE;
    }
}
