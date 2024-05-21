package com.shopify.buy.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class NativePayment {
    @NonNull
    public final MailingAddressInput billingContact;
    @NonNull
    public final String identifier;
    @Nullable
    public final ShippingContact shippingContact;
    @NonNull
    public final TokenData tokenData;

    private NativePayment(StepBuilder.Builder builder) {
        this.billingContact = builder.billingContact;
        this.shippingContact = builder.shippingContact;
        this.identifier = builder.identifier;
        this.tokenData = builder.tokenData;
    }

    public static StepBuilder.IdentifierStep newBuilder() {
        return new StepBuilder.Builder();
    }

    public static final class StepBuilder {

        public interface BillingContactStep {
            BuildStep billingContact(@NonNull MailingAddressInput mailingAddressInput);
        }

        public interface BuildStep {
            NativePayment build();

            BuildStep shippingContact(@Nullable ShippingContact shippingContact);
        }

        public interface IdentifierStep {
            TokenDataStep identifier(@NonNull String str);
        }

        public interface TokenDataStep {
            BillingContactStep tokenData(@NonNull TokenData tokenData);
        }

        private StepBuilder() {
        }

        public static final class Builder implements BillingContactStep, IdentifierStep, TokenDataStep, BuildStep {
            /* access modifiers changed from: private */
            public MailingAddressInput billingContact;
            /* access modifiers changed from: private */
            public String identifier;
            /* access modifiers changed from: private */
            public ShippingContact shippingContact;
            /* access modifiers changed from: private */
            public TokenData tokenData;

            private Builder() {
            }

            public TokenDataStep identifier(@NonNull String str) {
                this.identifier = str;
                return this;
            }

            public BillingContactStep tokenData(@NonNull TokenData tokenData2) {
                this.tokenData = tokenData2;
                return this;
            }

            public BuildStep billingContact(@NonNull MailingAddressInput mailingAddressInput) {
                this.billingContact = mailingAddressInput;
                return this;
            }

            public BuildStep shippingContact(@Nullable ShippingContact shippingContact2) {
                this.shippingContact = shippingContact2;
                return this;
            }

            public NativePayment build() {
                return new NativePayment(this);
            }
        }
    }
}
