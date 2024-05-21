package com.tapdaq.sdk.model.waterfall;

import android.content.Context;
import com.tapdaq.sdk.model.base.TDBaseRequest;

public class TDWaterfall extends TDBaseRequest {
    private TDCompliance compliance = new TDCompliance();
    private TDWaterfallConfig waterfall_config;

    public TDWaterfall(Context context, TDWaterfallConfig tDWaterfallConfig) {
        super(context);
        this.waterfall_config = tDWaterfallConfig;
    }

    public TDWaterfallConfig getWaterfallConfig() {
        return this.waterfall_config;
    }
}
