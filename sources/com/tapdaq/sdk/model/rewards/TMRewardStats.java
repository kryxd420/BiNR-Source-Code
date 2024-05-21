package com.tapdaq.sdk.model.rewards;

import android.content.Context;
import com.tapdaq.sdk.model.base.TDBaseRequest;

public class TMRewardStats extends TDBaseRequest {
    private TMRewardStatsData reward;

    public TMRewardStats(Context context, TMRewardStatsData tMRewardStatsData) {
        super(context);
        this.reward = tMRewardStatsData;
    }
}
