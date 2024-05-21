package com.shopify.buy.web;

public enum BrowserCheckoutMethod {
    APP_BROWSER,
    EXTERNAL_BROWSER,
    UNKNOWN;

    public static BrowserCheckoutMethod fromRawValue(int i) {
        switch (i) {
            case 0:
                return EXTERNAL_BROWSER;
            case 1:
                return APP_BROWSER;
            default:
                return UNKNOWN;
        }
    }
}
