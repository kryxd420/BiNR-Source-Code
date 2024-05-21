package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "IsReadyToPayRequestCreator")
@SafeParcelable.Reserved({1})
public final class IsReadyToPayRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<IsReadyToPayRequest> CREATOR = new zzr();
    @SafeParcelable.Field(id = 2)
    ArrayList<Integer> zzai;
    @SafeParcelable.Field(id = 4)
    private String zzbu;
    @SafeParcelable.Field(id = 5)
    private String zzbv;
    @SafeParcelable.Field(id = 6)
    ArrayList<Integer> zzbw;
    @SafeParcelable.Field(id = 7)
    boolean zzbx;
    @SafeParcelable.Field(id = 8)
    private String zzby;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (IsReadyToPayRequest.this.zzai == null) {
                IsReadyToPayRequest.this.zzai = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzai.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            Preconditions.checkArgument(collection != null && !collection.isEmpty(), "allowedCardNetworks can't be null or empty. If you want the defaults, leave it unset.");
            if (IsReadyToPayRequest.this.zzai == null) {
                IsReadyToPayRequest.this.zzai = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzai.addAll(collection);
            return this;
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (IsReadyToPayRequest.this.zzbw == null) {
                IsReadyToPayRequest.this.zzbw = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzbw.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection<Integer> collection) {
            Preconditions.checkArgument(collection != null && !collection.isEmpty(), "allowedPaymentMethods can't be null or empty. If you want the default, leave it unset.");
            if (IsReadyToPayRequest.this.zzbw == null) {
                IsReadyToPayRequest.this.zzbw = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzbw.addAll(collection);
            return this;
        }

        public final IsReadyToPayRequest build() {
            return IsReadyToPayRequest.this;
        }

        public final Builder setExistingPaymentMethodRequired(boolean z) {
            IsReadyToPayRequest.this.zzbx = z;
            return this;
        }
    }

    IsReadyToPayRequest() {
    }

    @SafeParcelable.Constructor
    IsReadyToPayRequest(@SafeParcelable.Param(id = 2) ArrayList<Integer> arrayList, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) ArrayList<Integer> arrayList2, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) String str3) {
        this.zzai = arrayList;
        this.zzbu = str;
        this.zzbv = str2;
        this.zzbw = arrayList2;
        this.zzbx = z;
        this.zzby = str3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzai;
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzbw;
    }

    public final boolean isExistingPaymentMethodRequired() {
        return this.zzbx;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzai, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbu, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbv, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbw, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbx);
        SafeParcelWriter.writeString(parcel, 8, this.zzby, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
