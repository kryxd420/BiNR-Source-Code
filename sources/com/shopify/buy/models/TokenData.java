package com.shopify.buy.models;

import android.support.annotation.NonNull;

public class TokenData {
    @NonNull
    public final String token;

    public TokenData(@NonNull String str) {
        this.token = str;
    }
}
