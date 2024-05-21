package com.tapdaq.sdk.model.config;

public class TDNetwork {
    private TDNetworkConfig config;
    private TDNetworkCredentials credentials;
    private String network;

    public String getName() {
        return this.network;
    }

    public TDNetworkConfig getConfig() {
        return this.config;
    }

    public TDNetworkCredentials getCredentials() {
        return this.credentials;
    }
}
