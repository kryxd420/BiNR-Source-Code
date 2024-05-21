package com.tapdaq.sdk.model.analytics.stats;

public class TMStatsDataBase {
    private String ad_format;
    private String ad_unit;
    private String ad_unit_id;
    private String placement_tag;

    public TMStatsDataBase(String str, String str2, String str3) {
        this.ad_unit = str;
        this.ad_format = str;
        this.ad_unit_id = str2;
        this.placement_tag = str3;
    }

    public String getAdUnit() {
        return this.ad_unit;
    }

    public String getAdUnitId() {
        return this.ad_unit_id;
    }

    public String getPlacementTag() {
        return this.placement_tag;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TMStatsDataBase)) {
            return false;
        }
        TMStatsDataBase tMStatsDataBase = (TMStatsDataBase) obj;
        if ((tMStatsDataBase.getAdUnit() != null || getAdUnit() != null) && !tMStatsDataBase.getAdUnit().equalsIgnoreCase(getAdUnit())) {
            return false;
        }
        return (tMStatsDataBase.getPlacementTag() == null && getPlacementTag() == null) || tMStatsDataBase.getPlacementTag().equalsIgnoreCase(getPlacementTag());
    }
}
