package com.tapdaq.sdk.model.config;

public class TDNetworkConfig {
    private long bid_token_timeout_in_milliseconds;
    private boolean enable_sdk_bidding;
    private boolean requires_bid_token;
    private long sdk_timeout_in_milliseconds;

    public boolean isSdkBiddingEnabled() {
        return this.enable_sdk_bidding;
    }

    public boolean isBidTokenRequired() {
        return this.requires_bid_token;
    }

    public long getBidTokenTimeout() {
        return this.bid_token_timeout_in_milliseconds;
    }

    public long getSdkTimeout() {
        return this.sdk_timeout_in_milliseconds;
    }
}
