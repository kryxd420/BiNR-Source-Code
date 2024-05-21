package com.tapdaq.sdk.model.analytics.stats;

import com.tapdaq.sdk.adnetworks.TDAdRequest;

public class TMStatsDataAdTimeout extends TMStatsDataMediationAd {
    private Long timeout_in_milliseconds;

    public TMStatsDataAdTimeout(TDAdRequest tDAdRequest, String str, Long l) {
        super(tDAdRequest.getWaterfallId(), tDAdRequest.getWaterfallItem().getDemandType(), tDAdRequest.getWaterfallPosition(), tDAdRequest.getWaterfallItem().getNetwork(), str, tDAdRequest.getWaterfallItem().getAdUnit(), tDAdRequest.getWaterfallItem().getAdUnitId(), tDAdRequest.getPlacement(), tDAdRequest.getWaterfallItem().getVersionId());
        this.timeout_in_milliseconds = l;
    }

    public TMStatsDataAdTimeout(String str, String str2, Integer num, Long l, String str3, String str4, String str5, String str6, String str7, String str8, Long l2) {
        super(str, str2, num, l, str3, str4, str5, str6, str7, str8);
        this.timeout_in_milliseconds = l2;
    }

    public Long getTimeoutInMilliseconds() {
        return this.timeout_in_milliseconds;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataAdTimeout) || !((TMStatsDataAdTimeout) obj).getTimeoutInMilliseconds().equals(getTimeoutInMilliseconds())) {
            return false;
        }
        return super.equals(obj);
    }
}
