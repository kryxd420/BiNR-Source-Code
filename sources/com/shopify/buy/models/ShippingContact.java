package com.shopify.buy.models;

import com.shopify.buy.models.MailingAddressInput;

public class ShippingContact extends MailingAddressInput {
    public final String email;

    public ShippingContact(MailingAddressInput mailingAddressInput, String str) {
        super(newBuilder().email(str).address1(mailingAddressInput.address1).address2(mailingAddressInput.address2).city(mailingAddressInput.city).country(mailingAddressInput.country).firstName(mailingAddressInput.firstName).lastName(mailingAddressInput.lastName).phone(mailingAddressInput.phone).province(mailingAddressInput.province).zip(mailingAddressInput.zip));
        this.email = str;
    }

    private ShippingContact(Builder builder) {
        super(builder);
        this.email = builder.email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends MailingAddressInput.Builder {
        /* access modifiers changed from: private */
        public String email;

        private Builder() {
        }

        public Builder email(String str) {
            this.email = str;
            return this;
        }

        public ShippingContact build() {
            return new ShippingContact(this);
        }
    }
}
