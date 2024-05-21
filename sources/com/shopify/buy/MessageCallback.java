package com.shopify.buy;

import android.support.annotation.NonNull;
import com.shopify.buy.models.ShopifyError;

public interface MessageCallback<T> {
    void onError(@NonNull ShopifyError shopifyError);

    void onResponse(@NonNull T t);
}
