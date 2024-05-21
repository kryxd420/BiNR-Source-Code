package com.applovin.impl.mediation.ads.a;

import com.applovin.impl.mediation.f;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import com.applovin.mediation.MaxAdListener;

public abstract class a {
    protected MaxAdListener adListener = null;
    /* access modifiers changed from: protected */
    public final String adUnitId;
    /* access modifiers changed from: protected */
    public final f.a loadRequestBuilder;
    protected final p logger;
    /* access modifiers changed from: protected */
    public final j sdk;
    protected final String tag;

    protected a(String str, String str2, j jVar) {
        this.adUnitId = str;
        this.sdk = jVar;
        this.tag = str2;
        this.logger = jVar.v();
        this.loadRequestBuilder = new f.a();
    }

    public String getAdUnitId() {
        return this.adUnitId;
    }

    public void setExtraParameter(String str, String str2) {
        if (str != null) {
            this.loadRequestBuilder.a(str, str2);
            return;
        }
        throw new IllegalArgumentException("No key specified");
    }

    public void setListener(MaxAdListener maxAdListener) {
        this.adListener = maxAdListener;
    }
}
