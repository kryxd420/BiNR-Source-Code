package com.tapdaq.sdk.model.analytics.stats;

import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import java.util.List;

public class TMStatsDataMediationImpression extends TMStatsDataMediationAd {
    private List<TDWaterfallItem> waterfall;

    public TMStatsDataMediationImpression(String str, String str2, List<TDWaterfallItem> list, Integer num, Long l, String str3, String str4, String str5, String str6, String str7, String str8) {
        super(str, str2, num, l, str3, str4, str5, str6, str7, str8);
        this.waterfall = list;
    }

    public TMStatsDataMediationImpression(TDAdRequest tDAdRequest, String str) {
        super(tDAdRequest, str);
        this.waterfall = tDAdRequest.getWaterfall();
    }
}
