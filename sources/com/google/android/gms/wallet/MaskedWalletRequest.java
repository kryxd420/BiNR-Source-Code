package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "MaskedWalletRequestCreator")
@SafeParcelable.Reserved({1})
public final class MaskedWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new zzz();
    @SafeParcelable.Field(id = 17)
    ArrayList<Integer> zzai;
    @SafeParcelable.Field(id = 7)
    String zzao;
    @SafeParcelable.Field(id = 2)
    String zzax;
    @SafeParcelable.Field(id = 9)
    Cart zzbh;
    @SafeParcelable.Field(id = 3)
    boolean zzdd;
    @SafeParcelable.Field(id = 4)
    boolean zzde;
    @SafeParcelable.Field(id = 5)
    boolean zzdf;
    @SafeParcelable.Field(id = 6)
    String zzdg;
    @SafeParcelable.Field(id = 8)
    String zzdh;
    @SafeParcelable.Field(id = 10)
    private boolean zzdi;
    @SafeParcelable.Field(id = 11)
    boolean zzdj;
    @SafeParcelable.Field(id = 12)
    private CountrySpecification[] zzdk;
    @SafeParcelable.Field(defaultValue = "true", id = 13)
    boolean zzdl;
    @SafeParcelable.Field(defaultValue = "true", id = 14)
    boolean zzdm;
    @SafeParcelable.Field(id = 15)
    ArrayList<CountrySpecification> zzdn;
    @SafeParcelable.Field(id = 16)
    PaymentMethodTokenizationParameters zzdo;
    @SafeParcelable.Field(id = 18)
    String zzh;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (MaskedWalletRequest.this.zzai == null) {
                MaskedWalletRequest.this.zzai = new ArrayList<>();
            }
            MaskedWalletRequest.this.zzai.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzai == null) {
                    MaskedWalletRequest.this.zzai = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzai.addAll(collection);
            }
            return this;
        }

        public final Builder addAllowedCountrySpecificationForShipping(CountrySpecification countrySpecification) {
            if (MaskedWalletRequest.this.zzdn == null) {
                MaskedWalletRequest.this.zzdn = new ArrayList<>();
            }
            MaskedWalletRequest.this.zzdn.add(countrySpecification);
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzdn == null) {
                    MaskedWalletRequest.this.zzdn = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzdn.addAll(collection);
            }
            return this;
        }

        public final MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public final Builder setAllowDebitCard(boolean z) {
            MaskedWalletRequest.this.zzdm = z;
            return this;
        }

        public final Builder setAllowPrepaidCard(boolean z) {
            MaskedWalletRequest.this.zzdl = z;
            return this;
        }

        public final Builder setCart(Cart cart) {
            MaskedWalletRequest.this.zzbh = cart;
            return this;
        }

        public final Builder setCountryCode(String str) {
            MaskedWalletRequest.this.zzh = str;
            return this;
        }

        public final Builder setCurrencyCode(String str) {
            MaskedWalletRequest.this.zzao = str;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String str) {
            MaskedWalletRequest.this.zzdg = str;
            return this;
        }

        @Deprecated
        public final Builder setIsBillingAgreement(boolean z) {
            MaskedWalletRequest.this.zzdj = z;
            return this;
        }

        public final Builder setMerchantName(String str) {
            MaskedWalletRequest.this.zzdh = str;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            MaskedWalletRequest.this.zzax = str;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            MaskedWalletRequest.this.zzdo = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            MaskedWalletRequest.this.zzdd = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            MaskedWalletRequest.this.zzde = z;
            return this;
        }

        @Deprecated
        public final Builder setUseMinimalBillingAddress(boolean z) {
            MaskedWalletRequest.this.zzdf = z;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.zzdl = true;
        this.zzdm = true;
    }

    @SafeParcelable.Constructor
    MaskedWalletRequest(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) boolean z2, @SafeParcelable.Param(id = 5) boolean z3, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) String str4, @SafeParcelable.Param(id = 9) Cart cart, @SafeParcelable.Param(id = 10) boolean z4, @SafeParcelable.Param(id = 11) boolean z5, @SafeParcelable.Param(id = 12) CountrySpecification[] countrySpecificationArr, @SafeParcelable.Param(id = 13) boolean z6, @SafeParcelable.Param(id = 14) boolean z7, @SafeParcelable.Param(id = 15) ArrayList<CountrySpecification> arrayList, @SafeParcelable.Param(id = 16) PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, @SafeParcelable.Param(id = 17) ArrayList<Integer> arrayList2, @SafeParcelable.Param(id = 18) String str5) {
        this.zzax = str;
        this.zzdd = z;
        this.zzde = z2;
        this.zzdf = z3;
        this.zzdg = str2;
        this.zzao = str3;
        this.zzdh = str4;
        this.zzbh = cart;
        this.zzdi = z4;
        this.zzdj = z5;
        this.zzdk = countrySpecificationArr;
        this.zzdl = z6;
        this.zzdm = z7;
        this.zzdn = arrayList;
        this.zzdo = paymentMethodTokenizationParameters;
        this.zzai = arrayList2;
        this.zzh = str5;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final boolean allowDebitCard() {
        return this.zzdm;
    }

    public final boolean allowPrepaidCard() {
        return this.zzdl;
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzai;
    }

    public final ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.zzdn;
    }

    public final CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzdk;
    }

    public final Cart getCart() {
        return this.zzbh;
    }

    public final String getCountryCode() {
        return this.zzh;
    }

    public final String getCurrencyCode() {
        return this.zzao;
    }

    public final String getEstimatedTotalPrice() {
        return this.zzdg;
    }

    public final String getMerchantName() {
        return this.zzdh;
    }

    public final String getMerchantTransactionId() {
        return this.zzax;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdo;
    }

    @Deprecated
    public final boolean isBillingAgreement() {
        return this.zzdj;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzdd;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzde;
    }

    @Deprecated
    public final boolean useMinimalBillingAddress() {
        return this.zzdf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzax, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzdd);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzde);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdf);
        SafeParcelWriter.writeString(parcel, 6, this.zzdg, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdh, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzbh, i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzdi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzdj);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzdk, i, false);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzdl);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzdm);
        SafeParcelWriter.writeTypedList(parcel, 15, this.zzdn, false);
        SafeParcelWriter.writeParcelable(parcel, 16, this.zzdo, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 17, this.zzai, false);
        SafeParcelWriter.writeString(parcel, 18, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
