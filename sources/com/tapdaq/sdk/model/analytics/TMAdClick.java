package com.tapdaq.sdk.model.analytics;

import android.content.Context;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.model.base.TDBaseRequest;

public class TMAdClick extends TDBaseRequest {
    private TMAdvert advert;

    public TMAdClick(Context context, TMAd tMAd, String str, String str2, TMAdSize tMAdSize) {
        super(context);
        this.advert = new TMAdvert(context, tMAd, str, str2, tMAdSize);
    }
}
