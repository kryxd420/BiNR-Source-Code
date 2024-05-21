package com.shopify.buy.models;

public class MailingAddressInput {
    public final String address1;
    public final String address2;
    public final String city;
    public final String country;
    public final String firstName;
    public final String lastName;
    public final String phone;
    public final String province;
    public final String zip;

    MailingAddressInput(Builder builder) {
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.city = builder.city;
        this.country = builder.country;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.province = builder.province;
        this.zip = builder.zip;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String address1;
        /* access modifiers changed from: private */
        public String address2;
        /* access modifiers changed from: private */
        public String city;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public String firstName;
        /* access modifiers changed from: private */
        public String lastName;
        /* access modifiers changed from: private */
        public String phone;
        /* access modifiers changed from: private */
        public String province;
        /* access modifiers changed from: private */
        public String zip;

        Builder() {
        }

        public Builder address1(String str) {
            this.address1 = str;
            return this;
        }

        public Builder address2(String str) {
            this.address2 = str;
            return this;
        }

        public Builder city(String str) {
            this.city = str;
            return this;
        }

        public Builder country(String str) {
            this.country = str;
            return this;
        }

        public Builder firstName(String str) {
            this.firstName = str;
            return this;
        }

        public Builder lastName(String str) {
            this.lastName = str;
            return this;
        }

        public Builder phone(String str) {
            this.phone = str;
            return this;
        }

        public Builder province(String str) {
            this.province = str;
            return this;
        }

        public Builder zip(String str) {
            this.zip = str;
            return this;
        }

        public MailingAddressInput build() {
            return new MailingAddressInput(this);
        }
    }
}
