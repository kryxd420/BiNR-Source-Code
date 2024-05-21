package com.tapdaq.sdk.model.analytics.stats;

import com.tapdaq.sdk.adnetworks.TDAdRequest;
import java.util.Date;

public class TMStatsDataMediationAdRequest extends TMStatsDataMediationAd {
    private Long date_fulfilled_in_millis;
    private String reason;

    public TMStatsDataMediationAdRequest(TDAdRequest tDAdRequest, String str) {
        super(tDAdRequest.getWaterfallId(), tDAdRequest.getWaterfallItem().getDemandType(), tDAdRequest.getWaterfallPosition(), tDAdRequest.getWaterfallItem().getNetwork(), str, tDAdRequest.getWaterfallItem().getAdUnit(), tDAdRequest.getWaterfallItem().getAdUnitId(), tDAdRequest.getPlacement(), tDAdRequest.getWaterfallItem().getVersionId());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TMStatsDataMediationAdRequest(String str, String str2, String str3, Integer num, Long l, String str4, String str5, String str6, String str7, String str8, String str9, Long l2) {
        super(str, str3, num, l, str4, str5, str6, str7, str8, str9);
        this.date_fulfilled_in_millis = l2;
        this.reason = str2;
    }

    public void setFulfilled() {
        this.date_fulfilled_in_millis = Long.valueOf(new Date().getTime());
    }

    public Long getFulfilledDate() {
        return this.date_fulfilled_in_millis;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataMediationAdRequest)) {
            return false;
        }
        TMStatsDataMediationAdRequest tMStatsDataMediationAdRequest = (TMStatsDataMediationAdRequest) obj;
        if ((tMStatsDataMediationAdRequest.getFulfilledDate() == null && getFulfilledDate() == null) || tMStatsDataMediationAdRequest.getFulfilledDate().longValue() == getFulfilledDate().longValue()) {
            return super.equals(obj);
        }
        return false;
    }
}
