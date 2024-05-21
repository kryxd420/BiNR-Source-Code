package com.shopify.buy.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.shopify.buy.utils.Objects;
import java.math.BigDecimal;

public final class ShippingMethod implements Parcelable {
    public static final Parcelable.Creator<ShippingMethod> CREATOR = new Parcelable.Creator<ShippingMethod>() {
        public ShippingMethod createFromParcel(Parcel parcel) {
            return new ShippingMethod(parcel);
        }

        public ShippingMethod[] newArray(int i) {
            return new ShippingMethod[i];
        }
    };
    public final BigDecimal amount;
    public final String detail;
    public final String identifier;
    public final String label;

    public int describeContents() {
        return 0;
    }

    public ShippingMethod(String str, String str2, String str3, BigDecimal bigDecimal) {
        this.identifier = str;
        this.detail = str2;
        this.label = str3;
        this.amount = bigDecimal.setScale(2, 6);
    }

    private ShippingMethod(Parcel parcel) {
        this.identifier = parcel.readString();
        this.detail = parcel.readString();
        this.label = parcel.readString();
        this.amount = new BigDecimal(parcel.readString());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeString(this.detail);
        parcel.writeString(this.label);
        parcel.writeString(this.amount.toString());
    }

    public int hashCode() {
        return Objects.hash(this.identifier, this.detail, this.label, this.amount);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ShippingMethod)) {
            return false;
        }
        ShippingMethod shippingMethod = (ShippingMethod) obj;
        if (!Objects.equals(this.identifier, shippingMethod.identifier) || !Objects.equals(this.detail, shippingMethod.detail) || !Objects.equals(this.label, shippingMethod.label) || !Objects.equals(this.amount, shippingMethod.amount)) {
            return false;
        }
        return true;
    }
}
