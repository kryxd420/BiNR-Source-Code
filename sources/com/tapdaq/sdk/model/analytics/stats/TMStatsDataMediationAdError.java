package com.tapdaq.sdk.model.analytics.stats;

import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.common.TMAdError;

public class TMStatsDataMediationAdError extends TMStatsDataMediationAd {
    private String error;
    private Integer errorcode;

    public TMStatsDataMediationAdError(String str, String str2, Integer num, Long l, String str3, String str4, String str5, String str6, String str7, String str8, Integer num2, String str9) {
        super(str, str2, num, l, str3, str4, str5, str6, str7, str8);
        this.errorcode = num2;
        this.error = str9;
    }

    public TMStatsDataMediationAdError(TDAdRequest tDAdRequest, String str, TMAdError tMAdError) {
        super(tDAdRequest, str);
        this.errorcode = Integer.valueOf(tMAdError.getErrorCode());
        this.error = tMAdError.getErrorMessage();
    }

    public Integer getErrorCode() {
        return this.errorcode;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
