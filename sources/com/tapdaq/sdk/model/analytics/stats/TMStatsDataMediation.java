package com.tapdaq.sdk.model.analytics.stats;

import com.applovin.sdk.AppLovinMediationProvider;
import java.util.Date;

public class TMStatsDataMediation extends TMStatsDataBase {
    private String credentials_type;
    private Long date_created_in_millis;
    private Boolean is_publisher;
    private String network;
    private String version_id;

    public TMStatsDataMediation(String str, String str2, String str3, String str4, String str5, String str6) {
        super(str3, str4, str5);
        this.date_created_in_millis = Long.valueOf(new Date().getTime());
        this.network = str;
        this.is_publisher = Boolean.valueOf(str2.equalsIgnoreCase("publisher"));
        this.credentials_type = str2;
        this.version_id = str6;
    }

    public TMStatsDataMediation(Long l, String str, String str2, String str3, String str4, String str5, String str6) {
        super(str3, str4, str5);
        this.date_created_in_millis = l;
        this.network = str;
        this.is_publisher = Boolean.valueOf(str2.equalsIgnoreCase("publisher"));
        this.credentials_type = this.is_publisher.booleanValue() ? "publisher" : AppLovinMediationProvider.TAPDAQ;
        this.version_id = str6;
    }

    public Long getDateCreatedInMillis() {
        return this.date_created_in_millis;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getCredentialsType() {
        return this.credentials_type;
    }

    public String getVersionId() {
        return this.version_id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataMediation)) {
            return false;
        }
        TMStatsDataMediation tMStatsDataMediation = (TMStatsDataMediation) obj;
        if (!tMStatsDataMediation.getDateCreatedInMillis().equals(getDateCreatedInMillis()) || !tMStatsDataMediation.getNetwork().equalsIgnoreCase(getNetwork()) || !tMStatsDataMediation.getCredentialsType().equalsIgnoreCase(getCredentialsType()) || !tMStatsDataMediation.getVersionId().equalsIgnoreCase(getVersionId())) {
            return false;
        }
        return super.equals(obj);
    }
}
