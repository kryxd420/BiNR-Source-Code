package com.shopify.buy.models;

public enum WalletError {
    AUTHENTICATION_FAILURE("AUTHENTICATION_FAILURE"),
    BUYER_ACCOUNT_ERROR("BUYER_ACCOUNT_ERROR"),
    INVALID_PARAMETERS("INVALID_PARAMETERS"),
    INVALID_TRANSACTION("INVALID_TRANSACTION"),
    MERCHANT_ACCOUNT_ERROR("MERCHANT_ACCOUNT_ERROR"),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE"),
    SPENDING_LIMIT_EXCEEDED("SPENDING_LIMIT_EXCEEDED"),
    UNKNOWN("UNKNOWN_WALLET_ERROR"),
    UNSUPPORTED_API_VERSION("UNSUPPORTED_API_VERSION"),
    NON_WALLET("UNKNOWN_NON_WALLET_ERROR");
    
    private final String name;

    private WalletError(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}