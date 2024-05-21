package com.tapdaq.sdk.model.waterfall;

import android.content.Context;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.common.TMAdapter;
import java.util.ArrayList;
import java.util.List;

public class TDWaterfallConfig {
    private String ad_unit;
    private String credentials_type;
    private List<TDWaterfallNetwork> enabled_networks = new ArrayList();
    private String placement_tag;

    public TDWaterfallConfig(Context context, String str, String str2, String str3, List<TMAdapter> list) {
        this.placement_tag = str;
        this.ad_unit = str2;
        this.credentials_type = str3;
        for (TMAdapter tDWaterfallNetwork : list) {
            this.enabled_networks.add(new TDWaterfallNetwork(context, tDWaterfallNetwork, TMAdType.getInt(str2)));
        }
    }

    public List<TDWaterfallNetwork> getNetworks() {
        return this.enabled_networks;
    }

    public String getPlacementTag() {
        return this.placement_tag;
    }

    public String getAdUnit() {
        return this.ad_unit;
    }
}
