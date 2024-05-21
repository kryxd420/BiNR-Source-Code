package com.tapdaq.sdk.model.analytics.stats;

import com.tapdaq.sdk.adnetworks.TDAdRequest;

public class TMStatsDataMediationAd extends TMStatsDataMediation {
    private String demand_type;
    private String shared_id;
    private String waterfall_id;
    private Integer waterfall_position;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TMStatsDataMediationAd(String str, String str2, Integer num, Long l, String str3, String str4, String str5, String str6, String str7, String str8) {
        super(l, str3, str4, str5, str6, str7, str8);
        String str9 = str;
        this.waterfall_id = str9;
        this.shared_id = str9;
        this.demand_type = str2;
        this.waterfall_position = num;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TMStatsDataMediationAd(String str, String str2, Integer num, String str3, String str4, String str5, String str6, String str7, String str8) {
        super(str3, str4, str5, str6, str7, str8);
        String str9 = str;
        this.waterfall_id = str9;
        this.shared_id = str9;
        this.demand_type = str2;
        this.waterfall_position = num;
    }

    public TMStatsDataMediationAd(TDAdRequest tDAdRequest, String str) {
        super(tDAdRequest.getWaterfallItem().getNetwork(), str, tDAdRequest.getWaterfallItem().getAdUnit(), tDAdRequest.getWaterfallItem().getAdUnitId(), tDAdRequest.getPlacement(), tDAdRequest.getWaterfallItem().getVersionId());
        String waterfallId = tDAdRequest.getWaterfallId();
        this.waterfall_id = waterfallId;
        this.shared_id = waterfallId;
        this.demand_type = tDAdRequest.getWaterfallItem().getDemandType();
        this.waterfall_position = tDAdRequest.getWaterfallPosition();
    }

    public String getWaterfallId() {
        return this.waterfall_id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataMediationAd) || !getWaterfallId().equalsIgnoreCase(((TMStatsDataMediationAd) obj).getWaterfallId())) {
            return false;
        }
        return super.equals(obj);
    }
}
