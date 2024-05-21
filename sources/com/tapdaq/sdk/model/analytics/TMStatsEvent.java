package com.tapdaq.sdk.model.analytics;

import com.tapdaq.sdk.model.analytics.stats.TMStatsDataBase;
import java.util.Date;
import java.util.UUID;

public class TMStatsEvent {
    private TMStatsDataBase data;
    private Long date_created = Long.valueOf(new Date().getTime() / 1000);
    private String event_group;
    private String event_id = UUID.randomUUID().toString();
    private String event_name;

    public TMStatsEvent(String str, String str2, TMStatsDataBase tMStatsDataBase) {
        this.event_group = str;
        this.event_name = str2;
        this.data = tMStatsDataBase;
    }

    public long getDate_created() {
        return this.date_created.longValue();
    }

    public String getEvent_group() {
        return this.event_group;
    }

    public String getEvent_name() {
        return this.event_name;
    }

    public TMStatsDataBase getData() {
        return this.data;
    }
}
