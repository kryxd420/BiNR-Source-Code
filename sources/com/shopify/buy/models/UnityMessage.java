package com.shopify.buy.models;

import java.util.UUID;

public final class UnityMessage {
    public final String content;
    public final String identifier;

    private UnityMessage(String str) {
        this(UUID.randomUUID().toString(), str);
    }

    private UnityMessage(String str, String str2) {
        this.identifier = str;
        this.content = str2;
    }

    public static UnityMessage fromContent(String str) {
        return new UnityMessage(str);
    }
}
