package com.unity.purchasing.common;

public class ProductDescription {
    public final ProductMetadata metadata;
    public final String receipt;
    public final String storeSpecificId;
    public final String transactionId;

    public ProductDescription(String str, ProductMetadata productMetadata, String str2, String str3) {
        this.storeSpecificId = str;
        this.metadata = productMetadata;
        this.receipt = str2;
        this.transactionId = str3;
    }

    public String toString() {
        return "{ProductDescription: storeSpecificId = " + this.storeSpecificId + ", metadata = " + this.metadata + ", receipt = " + this.receipt + ", transactionId = " + this.transactionId + ", }";
    }
}
