package com.tapdaq.sdk.model.analytics.stats;

import java.util.Date;

public class TMStatsDataIAP extends TMStatsDataBase {
    private Long date_created_in_millis;
    private String product_locale;
    private String product_name;
    private Double product_price;

    public TMStatsDataIAP(String str, Double d, String str2) {
        super((String) null, (String) null, (String) null);
        this.date_created_in_millis = Long.valueOf(new Date().getTime());
        this.product_name = str;
        this.product_price = d;
        this.product_locale = str2;
    }

    public TMStatsDataIAP(long j, String str, Double d, String str2) {
        super((String) null, (String) null, (String) null);
        this.date_created_in_millis = Long.valueOf(j);
        this.product_name = str;
        this.product_price = d;
        this.product_locale = str2;
    }

    /* access modifiers changed from: package-private */
    public Long getDateCreatedInMillis() {
        return this.date_created_in_millis;
    }

    public String getProductName() {
        return this.product_name;
    }

    public Double getProductPrice() {
        return this.product_price;
    }

    public String getProductLocale() {
        return this.product_locale;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataIAP)) {
            return false;
        }
        TMStatsDataIAP tMStatsDataIAP = (TMStatsDataIAP) obj;
        if (!tMStatsDataIAP.getDateCreatedInMillis().equals(getDateCreatedInMillis()) || !tMStatsDataIAP.getProductName().equals(getProductName()) || !tMStatsDataIAP.getProductPrice().equals(getProductPrice()) || !tMStatsDataIAP.getProductLocale().equals(getProductLocale())) {
            return false;
        }
        return super.equals(obj);
    }
}
