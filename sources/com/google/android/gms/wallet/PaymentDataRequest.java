package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "PaymentDataRequestCreator")
public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentDataRequest> CREATOR = new zzae();
    @SafeParcelable.Field(id = 6)
    ArrayList<Integer> zzbw;
    @SafeParcelable.Field(id = 10)
    String zzby;
    @SafeParcelable.Field(id = 2)
    boolean zzdd;
    @SafeParcelable.Field(id = 4)
    boolean zzde;
    @SafeParcelable.Field(id = 7)
    PaymentMethodTokenizationParameters zzdo;
    @SafeParcelable.Field(id = 1)
    boolean zzdv;
    @SafeParcelable.Field(id = 3)
    CardRequirements zzdw;
    @SafeParcelable.Field(id = 5)
    ShippingAddressRequirements zzdx;
    @SafeParcelable.Field(id = 8)
    TransactionInfo zzdy;
    @SafeParcelable.Field(defaultValue = "true", id = 9)
    boolean zzdz;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (PaymentDataRequest.this.zzbw == null) {
                PaymentDataRequest.this.zzbw = new ArrayList<>();
            }
            PaymentDataRequest.this.zzbw.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(@NonNull Collection<Integer> collection) {
            Preconditions.checkArgument(collection != null && !collection.isEmpty(), "allowedPaymentMethods can't be null or empty!");
            if (PaymentDataRequest.this.zzbw == null) {
                PaymentDataRequest.this.zzbw = new ArrayList<>();
            }
            PaymentDataRequest.this.zzbw.addAll(collection);
            return this;
        }

        public final PaymentDataRequest build() {
            if (PaymentDataRequest.this.zzby == null) {
                Preconditions.checkNotNull(PaymentDataRequest.this.zzbw, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
                Preconditions.checkNotNull(PaymentDataRequest.this.zzdw, "Card requirements must be set!");
                if (PaymentDataRequest.this.zzdo != null) {
                    Preconditions.checkNotNull(PaymentDataRequest.this.zzdy, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
                }
            }
            return PaymentDataRequest.this;
        }

        public final Builder setCardRequirements(@NonNull CardRequirements cardRequirements) {
            PaymentDataRequest.this.zzdw = cardRequirements;
            return this;
        }

        public final Builder setEmailRequired(boolean z) {
            PaymentDataRequest.this.zzdv = z;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            PaymentDataRequest.this.zzdo = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            PaymentDataRequest.this.zzdd = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            PaymentDataRequest.this.zzde = z;
            return this;
        }

        public final Builder setShippingAddressRequirements(@NonNull ShippingAddressRequirements shippingAddressRequirements) {
            PaymentDataRequest.this.zzdx = shippingAddressRequirements;
            return this;
        }

        public final Builder setTransactionInfo(@NonNull TransactionInfo transactionInfo) {
            PaymentDataRequest.this.zzdy = transactionInfo;
            return this;
        }

        public final Builder setUiRequired(boolean z) {
            PaymentDataRequest.this.zzdz = z;
            return this;
        }
    }

    private PaymentDataRequest() {
        this.zzdz = true;
    }

    @SafeParcelable.Constructor
    PaymentDataRequest(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) CardRequirements cardRequirements, @SafeParcelable.Param(id = 4) boolean z3, @SafeParcelable.Param(id = 5) ShippingAddressRequirements shippingAddressRequirements, @SafeParcelable.Param(id = 6) ArrayList<Integer> arrayList, @SafeParcelable.Param(id = 7) PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, @SafeParcelable.Param(id = 8) TransactionInfo transactionInfo, @SafeParcelable.Param(id = 9) boolean z4, @SafeParcelable.Param(id = 10) String str) {
        this.zzdv = z;
        this.zzdd = z2;
        this.zzdw = cardRequirements;
        this.zzde = z3;
        this.zzdx = shippingAddressRequirements;
        this.zzbw = arrayList;
        this.zzdo = paymentMethodTokenizationParameters;
        this.zzdy = transactionInfo;
        this.zzdz = z4;
        this.zzby = str;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzbw;
    }

    @Nullable
    public final CardRequirements getCardRequirements() {
        return this.zzdw;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdo;
    }

    @Nullable
    public final ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zzdx;
    }

    public final TransactionInfo getTransactionInfo() {
        return this.zzdy;
    }

    public final boolean isEmailRequired() {
        return this.zzdv;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzdd;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzde;
    }

    public final boolean isUiRequired() {
        return this.zzdz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzdv);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdd);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdw, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzde);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdx, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbw, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdy, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdz);
        SafeParcelWriter.writeString(parcel, 10, this.zzby, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
