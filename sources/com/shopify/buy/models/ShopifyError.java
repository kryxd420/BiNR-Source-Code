package com.shopify.buy.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class ShopifyError {
    public final String description;
    public final ErrorType errorType;

    public enum ErrorType {
        HTTP("HTTP"),
        GRAPH_QL("GraphQL"),
        USER_ERROR("UserError"),
        NATIVE_PAYMENT_PROCESSING_ERROR("NativePaymentProcessingError");
        
        public final String name;

        private ErrorType(String str) {
            this.name = str;
        }

        @Nullable
        public static ErrorType ofName(@NonNull String str) {
            for (ErrorType errorType : values()) {
                if (errorType.name.equals(str)) {
                    return errorType;
                }
            }
            return null;
        }
    }

    public ShopifyError(ErrorType errorType2, String str) {
        this.errorType = errorType2;
        this.description = str;
    }

    public String toString() {
        return "ShopifyError(errorType = " + this.errorType + ", description = " + this.description + ")";
    }
}
