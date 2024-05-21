package com.tapdaq.sdk.model.rewards;

import com.tapdaq.sdk.model.waterfall.TMReward;

public class TDReward {
    private Object custom_json;
    private String eventId;
    private boolean isValid;
    private String name;
    private String tag;
    private int value;

    public TDReward(String str, String str2, boolean z, TMReward tMReward) {
        this.eventId = str;
        this.name = tMReward.getReward_name();
        this.value = tMReward.getReward_value();
        this.tag = str2;
        this.isValid = z;
        this.custom_json = tMReward.getCustom_json();
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public Object getCustom_json() {
        return this.custom_json;
    }
}
