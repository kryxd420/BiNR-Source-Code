package com.tapdaq.sdk.analytics;

import android.content.Context;
import com.tapdaq.sdk.model.analytics.TMStatsEvent;
import com.tapdaq.sdk.network.ResponseHandler;
import com.tapdaq.sdk.network.TMServiceClient;
import java.util.ArrayList;
import java.util.List;

class StatsRunnable implements Runnable {
    private Context mContext;
    private List<TMStatsEvent> mEvents = new ArrayList();
    private TMStatsManager mStatsManager;

    StatsRunnable(Context context, TMStatsManager tMStatsManager) {
        this.mContext = context;
        this.mStatsManager = tMStatsManager;
    }

    public void run() {
        this.mEvents = this.mStatsManager.getEvents();
        this.mStatsManager.clearEvents();
        sendBatchRequest();
    }

    /* access modifiers changed from: package-private */
    public void sendBatchRequest() {
        if (this.mEvents != null && !this.mEvents.isEmpty()) {
            new TMServiceClient().stats(this.mContext, this.mEvents, new StatsResponseHandler(this.mContext, ResponseHandler.STATS, this.mEvents));
        }
        this.mContext = null;
    }
}
