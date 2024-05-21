package com.tapdaq.sdk.model.rewards;

import com.tapdaq.sdk.adnetworks.TDAdRequest;
import java.util.UUID;

public final class TMRewardStatsData {
    private String ad_unit_id;
    private String credentials_type;
    private Object custom_json;
    private String demand_type;
    private String event_id = UUID.randomUUID().toString();
    private String network;
    private String placement_tag;
    private String reward_name;
    private boolean reward_valid;
    private int reward_value;
    private String shared_id;
    private String version_id;
    private String waterfall_id;

    public TMRewardStatsData(boolean z, TDAdRequest tDAdRequest, String str) {
        this.reward_valid = z;
        this.network = tDAdRequest.getWaterfallItem().getNetwork();
        this.placement_tag = tDAdRequest.getPlacement();
        this.reward_name = tDAdRequest.getReward().getReward_name();
        this.reward_value = tDAdRequest.getReward().getReward_value();
        this.custom_json = tDAdRequest.getReward().getCustom_json();
        this.version_id = tDAdRequest.getReward().getVersion_id();
        String waterfallId = tDAdRequest.getWaterfallId();
        this.shared_id = waterfallId;
        this.waterfall_id = waterfallId;
        this.ad_unit_id = tDAdRequest.getWaterfallItem().getAdUnitId();
        this.demand_type = tDAdRequest.getWaterfallItem().getDemandType();
        this.credentials_type = str;
    }

    public String getEvent_id() {
        return this.event_id;
    }

    public boolean isReward_valid() {
        return this.reward_valid;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getPlacement_Tag() {
        return this.placement_tag;
    }

    public String getReward_name() {
        return this.reward_name;
    }

    public int getReward_value() {
        return this.reward_value;
    }

    public String getVersion_id() {
        return this.version_id;
    }

    public String getShared_id() {
        return this.shared_id;
    }

    public Object getCustom_json() {
        return this.custom_json;
    }
}
