package com.applovin.mediation;

public class MaxAdFormat {
    public static final MaxAdFormat BANNER = new MaxAdFormat("BANNER");
    public static final MaxAdFormat INTERSTITIAL = new MaxAdFormat("INTER");
    public static final MaxAdFormat LEADER = new MaxAdFormat("LEADER");
    public static final MaxAdFormat MREC = new MaxAdFormat("MREC");
    public static final MaxAdFormat NATIVE = new MaxAdFormat("NATIVE");
    public static final MaxAdFormat REWARDED = new MaxAdFormat("REWARDED");
    private final String a;

    private MaxAdFormat(String str) {
        this.a = str;
    }

    public String getLabel() {
        return this.a;
    }

    public String toString() {
        return "MaxAdFormat{label='" + this.a + '\'' + '}';
    }
}
