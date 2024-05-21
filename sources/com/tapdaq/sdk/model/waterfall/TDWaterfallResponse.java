package com.tapdaq.sdk.model.waterfall;

import java.util.List;

public class TDWaterfallResponse {
    protected String credentials_type;
    protected TMReward reward;
    protected List<TDWaterfallItem> waterfall;
    protected String waterfall_id;

    public String getWaterfallId() {
        return this.waterfall_id;
    }

    public List<TDWaterfallItem> getWaterfall() {
        return this.waterfall;
    }

    public TMReward getReward() {
        return this.reward;
    }
}
