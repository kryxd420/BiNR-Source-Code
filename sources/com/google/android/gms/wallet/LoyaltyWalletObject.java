package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "LoyaltyWalletObjectCreator")
@SafeParcelable.Reserved({1})
public final class LoyaltyWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new zzv();
    @SafeParcelable.Field(id = 12)
    int state;
    @SafeParcelable.Field(id = 2)
    String zzce;
    @SafeParcelable.Field(id = 3)
    String zzcf;
    @SafeParcelable.Field(id = 4)
    String zzcg;
    @SafeParcelable.Field(id = 5)
    String zzch;
    @SafeParcelable.Field(id = 6)
    String zzci;
    @SafeParcelable.Field(id = 7)
    String zzcj;
    @SafeParcelable.Field(id = 8)
    String zzck;
    @SafeParcelable.Field(id = 9)
    String zzcl;
    @SafeParcelable.Field(id = 10)
    String zzcm;
    @SafeParcelable.Field(id = 11)
    String zzcn;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 13)
    ArrayList<WalletObjectMessage> zzco;
    @SafeParcelable.Field(id = 14)
    TimeInterval zzcp;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 15)
    ArrayList<LatLng> zzcq;
    @SafeParcelable.Field(id = 16)
    String zzcr;
    @SafeParcelable.Field(id = 17)
    String zzcs;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 18)
    ArrayList<LabelValueRow> zzct;
    @SafeParcelable.Field(id = 19)
    boolean zzcu;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 20)
    ArrayList<UriData> zzcv;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 21)
    ArrayList<TextModuleData> zzcw;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 22)
    ArrayList<UriData> zzcx;
    @SafeParcelable.Field(id = 23)
    LoyaltyPoints zzcy;

    public final class Builder {
        private Builder() {
        }

        public final Builder addImageModuleDataMainImageUri(UriData uriData) {
            LoyaltyWalletObject.this.zzcv.add(uriData);
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
            LoyaltyWalletObject.this.zzcv.addAll(collection);
            return this;
        }

        public final Builder addInfoModuleDataLabeValueRow(LabelValueRow labelValueRow) {
            LoyaltyWalletObject.this.zzct.add(labelValueRow);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
            LoyaltyWalletObject.this.zzct.addAll(collection);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData uriData) {
            LoyaltyWalletObject.this.zzcx.add(uriData);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection<UriData> collection) {
            LoyaltyWalletObject.this.zzcx.addAll(collection);
            return this;
        }

        public final Builder addLocation(LatLng latLng) {
            LoyaltyWalletObject.this.zzcq.add(latLng);
            return this;
        }

        public final Builder addLocations(Collection<LatLng> collection) {
            LoyaltyWalletObject.this.zzcq.addAll(collection);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage walletObjectMessage) {
            LoyaltyWalletObject.this.zzco.add(walletObjectMessage);
            return this;
        }

        public final Builder addMessages(Collection<WalletObjectMessage> collection) {
            LoyaltyWalletObject.this.zzco.addAll(collection);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData textModuleData) {
            LoyaltyWalletObject.this.zzcw.add(textModuleData);
            return this;
        }

        public final Builder addTextModulesData(Collection<TextModuleData> collection) {
            LoyaltyWalletObject.this.zzcw.addAll(collection);
            return this;
        }

        public final LoyaltyWalletObject build() {
            return LoyaltyWalletObject.this;
        }

        public final Builder setAccountId(String str) {
            LoyaltyWalletObject.this.zzcf = str;
            return this;
        }

        public final Builder setAccountName(String str) {
            LoyaltyWalletObject.this.zzci = str;
            return this;
        }

        public final Builder setBarcodeAlternateText(String str) {
            LoyaltyWalletObject.this.zzcj = str;
            return this;
        }

        public final Builder setBarcodeLabel(String str) {
            LoyaltyWalletObject.this.zzcm = str;
            return this;
        }

        public final Builder setBarcodeType(String str) {
            LoyaltyWalletObject.this.zzck = str;
            return this;
        }

        public final Builder setBarcodeValue(String str) {
            LoyaltyWalletObject.this.zzcl = str;
            return this;
        }

        public final Builder setClassId(String str) {
            LoyaltyWalletObject.this.zzcn = str;
            return this;
        }

        public final Builder setId(String str) {
            LoyaltyWalletObject.this.zzce = str;
            return this;
        }

        public final Builder setInfoModuleDataHexBackgroundColor(String str) {
            LoyaltyWalletObject.this.zzcs = str;
            return this;
        }

        public final Builder setInfoModuleDataHexFontColor(String str) {
            LoyaltyWalletObject.this.zzcr = str;
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
            LoyaltyWalletObject.this.zzcu = z;
            return this;
        }

        public final Builder setIssuerName(String str) {
            LoyaltyWalletObject.this.zzcg = str;
            return this;
        }

        public final Builder setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
            LoyaltyWalletObject.this.zzcy = loyaltyPoints;
            return this;
        }

        public final Builder setProgramName(String str) {
            LoyaltyWalletObject.this.zzch = str;
            return this;
        }

        public final Builder setState(int i) {
            LoyaltyWalletObject.this.state = i;
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval timeInterval) {
            LoyaltyWalletObject.this.zzcp = timeInterval;
            return this;
        }
    }

    LoyaltyWalletObject() {
        this.zzco = ArrayUtils.newArrayList();
        this.zzcq = ArrayUtils.newArrayList();
        this.zzct = ArrayUtils.newArrayList();
        this.zzcv = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
    }

    @SafeParcelable.Constructor
    LoyaltyWalletObject(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) String str8, @SafeParcelable.Param(id = 10) String str9, @SafeParcelable.Param(id = 11) String str10, @SafeParcelable.Param(id = 12) int i, @SafeParcelable.Param(id = 13) ArrayList<WalletObjectMessage> arrayList, @SafeParcelable.Param(id = 14) TimeInterval timeInterval, @SafeParcelable.Param(id = 15) ArrayList<LatLng> arrayList2, @SafeParcelable.Param(id = 16) String str11, @SafeParcelable.Param(id = 17) String str12, @SafeParcelable.Param(id = 18) ArrayList<LabelValueRow> arrayList3, @SafeParcelable.Param(id = 19) boolean z, @SafeParcelable.Param(id = 20) ArrayList<UriData> arrayList4, @SafeParcelable.Param(id = 21) ArrayList<TextModuleData> arrayList5, @SafeParcelable.Param(id = 22) ArrayList<UriData> arrayList6, @SafeParcelable.Param(id = 23) LoyaltyPoints loyaltyPoints) {
        this.zzce = str;
        this.zzcf = str2;
        this.zzcg = str3;
        this.zzch = str4;
        this.zzci = str5;
        this.zzcj = str6;
        this.zzck = str7;
        this.zzcl = str8;
        this.zzcm = str9;
        this.zzcn = str10;
        this.state = i;
        this.zzco = arrayList;
        this.zzcp = timeInterval;
        this.zzcq = arrayList2;
        this.zzcr = str11;
        this.zzcs = str12;
        this.zzct = arrayList3;
        this.zzcu = z;
        this.zzcv = arrayList4;
        this.zzcw = arrayList5;
        this.zzcx = arrayList6;
        this.zzcy = loyaltyPoints;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getAccountId() {
        return this.zzcf;
    }

    public final String getAccountName() {
        return this.zzci;
    }

    public final String getBarcodeAlternateText() {
        return this.zzcj;
    }

    public final String getBarcodeLabel() {
        return this.zzcm;
    }

    public final String getBarcodeType() {
        return this.zzck;
    }

    public final String getBarcodeValue() {
        return this.zzcl;
    }

    public final String getClassId() {
        return this.zzcn;
    }

    public final String getId() {
        return this.zzce;
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzcv;
    }

    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzcs;
    }

    public final String getInfoModuleDataHexFontColor() {
        return this.zzcr;
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzct;
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzcu;
    }

    public final String getIssuerName() {
        return this.zzcg;
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzcx;
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzcq;
    }

    public final LoyaltyPoints getLoyaltyPoints() {
        return this.zzcy;
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzco;
    }

    public final String getProgramName() {
        return this.zzch;
    }

    public final int getState() {
        return this.state;
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzcw;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzcp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzce, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcf, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzch, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzci, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcj, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzck, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzcl, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzcm, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzcn, false);
        SafeParcelWriter.writeInt(parcel, 12, this.state);
        SafeParcelWriter.writeTypedList(parcel, 13, this.zzco, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzcp, i, false);
        SafeParcelWriter.writeTypedList(parcel, 15, this.zzcq, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzcr, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzcs, false);
        SafeParcelWriter.writeTypedList(parcel, 18, this.zzct, false);
        SafeParcelWriter.writeBoolean(parcel, 19, this.zzcu);
        SafeParcelWriter.writeTypedList(parcel, 20, this.zzcv, false);
        SafeParcelWriter.writeTypedList(parcel, 21, this.zzcw, false);
        SafeParcelWriter.writeTypedList(parcel, 22, this.zzcx, false);
        SafeParcelWriter.writeParcelable(parcel, 23, this.zzcy, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
