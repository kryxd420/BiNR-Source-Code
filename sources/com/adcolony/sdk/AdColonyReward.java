package com.adcolony.sdk;

import org.json.JSONObject;

public class AdColonyReward {
    private int a;
    private String b;
    private String c;
    private boolean d;

    AdColonyReward(af afVar) {
        JSONObject c2 = afVar.c();
        this.a = y.c(c2, "reward_amount");
        this.b = y.b(c2, "reward_name");
        this.d = y.d(c2, "success");
        this.c = y.b(c2, "zone_id");
    }

    public int getRewardAmount() {
        return this.a;
    }

    public String getRewardName() {
        return this.b;
    }

    public String getZoneID() {
        return this.c;
    }

    public boolean success() {
        return this.d;
    }
}
