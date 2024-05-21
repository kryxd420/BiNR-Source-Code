package com.tapdaq.sdk.model.analytics.stats;

public class TMStatsDataSDKTimeout extends TMStatsDataMediation {
    private Long timeout_in_milliseconds;

    public TMStatsDataSDKTimeout(String str, String str2, String str3, Long l) {
        super(str, str2, (String) null, (String) null, (String) null, str3);
        this.timeout_in_milliseconds = l;
    }

    public TMStatsDataSDKTimeout(Long l, String str, String str2, String str3, Long l2) {
        super(l, str, str2, (String) null, (String) null, (String) null, str3);
        this.timeout_in_milliseconds = l2;
    }

    public Long getTimeoutInMilliseconds() {
        return this.timeout_in_milliseconds;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataSDKTimeout) || !((TMStatsDataSDKTimeout) obj).getTimeoutInMilliseconds().equals(getTimeoutInMilliseconds())) {
            return false;
        }
        return super.equals(obj);
    }
}
