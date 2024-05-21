package com.applovin.mediation;

public class MaxReward {
    public static final int DEFAULT_AMOUNT = 0;
    public static final String DEFAULT_LABEL = "";
    private final String a;
    private final int b;

    private MaxReward(int i, String str) {
        if (i >= 0) {
            this.a = str;
            this.b = i;
            return;
        }
        throw new IllegalArgumentException("Reward amount must be greater than or equal to 0");
    }

    public static MaxReward create(int i, String str) {
        return new MaxReward(i, str);
    }

    public static MaxReward createDefault() {
        return create(0, "");
    }

    public final int getAmount() {
        return this.b;
    }

    public final String getLabel() {
        return this.a;
    }

    public String toString() {
        return "MaxReward{amount=" + this.b + ", label='" + this.a + '\'' + '}';
    }
}
