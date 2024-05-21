package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.d.a;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.j;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;

public class PostbackServiceImpl implements AppLovinPostbackService {
    private final j a;

    public PostbackServiceImpl(j jVar) {
        this.a = jVar;
    }

    public void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackRequest(f.b(this.a).a(str).a(false).a(), appLovinPostbackListener);
    }

    public void dispatchPostbackRequest(f fVar, q.a aVar, AppLovinPostbackListener appLovinPostbackListener) {
        this.a.D().a((a) new com.applovin.impl.sdk.d.j(fVar, aVar, this.a, appLovinPostbackListener), aVar);
    }

    public void dispatchPostbackRequest(f fVar, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackRequest(fVar, q.a.POSTBACKS, appLovinPostbackListener);
    }

    public String toString() {
        return "PostbackService{}";
    }
}
