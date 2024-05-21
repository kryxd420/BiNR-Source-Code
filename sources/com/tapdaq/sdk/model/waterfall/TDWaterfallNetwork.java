package com.tapdaq.sdk.model.waterfall;

import android.content.Context;
import com.tapdaq.sdk.analytics.TDFrequencyTracker;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.model.config.TDNetworkConfig;

public class TDWaterfallNetwork {
    private int ad_display_count;
    private String[] bid_tokens;
    private TDNetworkConfig config;
    private String network;
    private String sdk_version;
    private String version_id;

    public TDWaterfallNetwork(Context context, TMAdapter tMAdapter, int i) {
        this.network = tMAdapter.getNetwork().getName();
        this.sdk_version = tMAdapter.getSdkVersion();
        this.config = tMAdapter.getNetwork().getConfig();
        this.ad_display_count = new TDFrequencyTracker(context).getImpressionCountToday(i, tMAdapter.getID());
        if (tMAdapter.getNetwork().getCredentials() != null) {
            this.version_id = tMAdapter.getNetwork().getCredentials().getVersionId();
        }
        this.bid_tokens = new String[0];
    }

    public String getName() {
        return this.network;
    }
}
