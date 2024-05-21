package com.tapdaq.sdk.model.config;

import java.util.ArrayList;
import java.util.List;

public class TDConfigResponse {
    private String credentials_type;
    private List<TDNetwork> networks;

    public String getCredentialsType() {
        return this.credentials_type;
    }

    public List<TDNetwork> getNetworks() {
        if (this.networks != null) {
            return this.networks;
        }
        return new ArrayList();
    }

    public boolean hasResponse() {
        return (this.credentials_type == null || this.networks == null) ? false : true;
    }
}
