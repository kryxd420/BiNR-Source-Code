package com.shopify.buy.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PricingLineItems {
    @Nullable
    public final BigDecimal shippingPrice;
    @NonNull
    public final BigDecimal subtotal;
    @NonNull
    public final BigDecimal taxPrice;
    @NonNull
    public final BigDecimal totalPrice;

    public PricingLineItems(@NonNull BigDecimal bigDecimal, @NonNull BigDecimal bigDecimal2, @NonNull BigDecimal bigDecimal3, @Nullable BigDecimal bigDecimal4) {
        this.totalPrice = bigDecimal3.setScale(2, RoundingMode.HALF_EVEN);
        this.subtotal = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        this.taxPrice = bigDecimal2.setScale(2, RoundingMode.HALF_EVEN);
        if (bigDecimal4 != null) {
            this.shippingPrice = bigDecimal4.setScale(2, RoundingMode.HALF_EVEN);
        } else {
            this.shippingPrice = null;
        }
    }
}
