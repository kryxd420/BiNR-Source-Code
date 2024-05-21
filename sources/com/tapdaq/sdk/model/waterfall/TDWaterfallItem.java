package com.tapdaq.sdk.model.waterfall;

import java.util.Map;

public class TDWaterfallItem {
    protected long ad_load_timeout_in_milliseconds;
    protected String ad_unit;
    protected String ad_unit_id;
    protected Map<String, String> ad_unit_ids;
    private transient boolean attempted = false;
    protected String demand_type;
    protected String network;
    protected Long price_in_millidollars;
    protected long suspend_in_milliseconds;
    protected String version_id;

    public String getDemandType() {
        return this.demand_type;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getAdUnit() {
        return this.ad_unit;
    }

    public String getAdUnitId() {
        return this.ad_unit_id;
    }

    public Map<String, String> getAdUnitIds() {
        return this.ad_unit_ids;
    }

    public long getAdLoadTimeout() {
        return this.ad_load_timeout_in_milliseconds;
    }

    public long getSuspendTime() {
        return this.suspend_in_milliseconds;
    }

    public String getVersionId() {
        return this.version_id;
    }

    public void setAttempted() {
        this.attempted = true;
    }

    public boolean hasAttempted() {
        return this.attempted;
    }
}
