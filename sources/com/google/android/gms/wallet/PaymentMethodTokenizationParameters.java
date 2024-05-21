package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PaymentMethodTokenizationParametersCreator")
@SafeParcelable.Reserved({1})
public final class PaymentMethodTokenizationParameters extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentMethodTokenizationParameters> CREATOR = new zzah();
    @SafeParcelable.Field(id = 2)
    int zzeb;
    @SafeParcelable.Field(id = 3)
    Bundle zzed = new Bundle();

    public final class Builder {
        private Builder() {
        }

        public final Builder addParameter(@NonNull String str, @NonNull String str2) {
            Preconditions.checkNotEmpty(str, "Tokenization parameter name must not be empty");
            Preconditions.checkNotEmpty(str2, "Tokenization parameter value must not be empty");
            PaymentMethodTokenizationParameters.this.zzed.putString(str, str2);
            return this;
        }

        public final PaymentMethodTokenizationParameters build() {
            return PaymentMethodTokenizationParameters.this;
        }

        public final Builder setPaymentMethodTokenizationType(int i) {
            PaymentMethodTokenizationParameters.this.zzeb = i;
            return this;
        }
    }

    private PaymentMethodTokenizationParameters() {
    }

    @SafeParcelable.Constructor
    PaymentMethodTokenizationParameters(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) Bundle bundle) {
        this.zzeb = i;
        this.zzed = bundle;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final Bundle getParameters() {
        return new Bundle(this.zzed);
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzeb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzeb);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzed, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
