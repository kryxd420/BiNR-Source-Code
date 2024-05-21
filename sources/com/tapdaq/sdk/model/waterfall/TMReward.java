package com.tapdaq.sdk.model.waterfall;

import com.tapdaq.sdk.helpers.TLog;
import java.util.HashMap;
import java.util.Map;

public class TMReward {
    private Object custom_json;
    private String reward_name;
    private int reward_value;
    private String version_id;

    public TMReward(String str, int i) {
        this.reward_name = str;
        this.reward_value = i;
    }

    public String getVersion_id() {
        return this.version_id;
    }

    public String getReward_name() {
        return this.reward_name;
    }

    public int getReward_value() {
        return this.reward_value;
    }

    public Object getCustom_json() {
        return this.custom_json != null ? this.custom_json : new HashMap();
    }

    public void addCustomData(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            if (this.custom_json == null) {
                this.custom_json = new HashMap();
            }
            if (this.custom_json instanceof Map) {
                ((Map) this.custom_json).put(obj, obj2);
            } else {
                TLog.error("Custom json not a Map, unable to add data");
            }
        }
    }
}
