package com.tapdaq.sdk.model.analytics;

import android.content.Context;
import com.tapdaq.sdk.model.base.TDBaseRequest;
import java.util.List;

public class TMStats extends TDBaseRequest {
    private List<TMStatsEvent> stats;

    public TMStats(Context context, List<TMStatsEvent> list) {
        super(context);
        this.stats = list;
    }

    public List<TMStatsEvent> getStatsEvents() {
        return this.stats;
    }
}
