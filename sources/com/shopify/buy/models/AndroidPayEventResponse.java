package com.shopify.buy.models;

import java.util.List;

public final class AndroidPayEventResponse {
    public final String countryCode;
    public final String currencyCode;
    public final String merchantName;
    public final PricingLineItems pricingLineItems;
    public final boolean requiresShipping;
    public final List<ShippingMethod> shippingMethods;

    public AndroidPayEventResponse(String str, PricingLineItems pricingLineItems2, String str2, String str3, boolean z, List<ShippingMethod> list) {
        this.merchantName = str;
        this.pricingLineItems = pricingLineItems2;
        this.currencyCode = str2;
        this.countryCode = str3;
        this.requiresShipping = z;
        this.shippingMethods = list;
    }
}
