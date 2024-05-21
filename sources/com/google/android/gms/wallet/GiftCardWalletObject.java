package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@SafeParcelable.Class(creator = "GiftCardWalletObjectCreator")
@SafeParcelable.Reserved({1})
public final class GiftCardWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GiftCardWalletObject> CREATOR = new zzo();
    @SafeParcelable.Field(id = 4)
    String pin;
    @SafeParcelable.Field(id = 2)
    CommonWalletObject zzbj = CommonWalletObject.zze().zzf();
    @SafeParcelable.Field(id = 3)
    String zzbk;
    @SafeParcelable.Field(id = 5)
    String zzbl;
    @SafeParcelable.Field(id = 6)
    long zzbm;
    @SafeParcelable.Field(id = 7)
    String zzbn;
    @SafeParcelable.Field(id = 8)
    long zzbo;
    @SafeParcelable.Field(id = 9)
    String zzbp;

    public final class Builder {
        private CommonWalletObject.zza zzbq;

        private Builder() {
            this.zzbq = CommonWalletObject.zze();
        }

        public final Builder addImageModuleDataMainImageUri(UriData uriData) {
            this.zzbq.zza(uriData);
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
            this.zzbq.zzd(collection);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRow(LabelValueRow labelValueRow) {
            this.zzbq.zza(labelValueRow);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
            this.zzbq.zzc(collection);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData uriData) {
            this.zzbq.zzb(uriData);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection<UriData> collection) {
            this.zzbq.zzf(collection);
            return this;
        }

        public final Builder addLocation(LatLng latLng) {
            this.zzbq.zza(latLng);
            return this;
        }

        public final Builder addLocations(Collection<LatLng> collection) {
            this.zzbq.zzb(collection);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage walletObjectMessage) {
            this.zzbq.zza(walletObjectMessage);
            return this;
        }

        public final Builder addMessages(Collection<WalletObjectMessage> collection) {
            this.zzbq.zza(collection);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData textModuleData) {
            this.zzbq.zza(textModuleData);
            return this;
        }

        public final Builder addTextModulesData(Collection<TextModuleData> collection) {
            this.zzbq.zze(collection);
            return this;
        }

        public final GiftCardWalletObject build() {
            Preconditions.checkArgument(!TextUtils.isEmpty(GiftCardWalletObject.this.zzbk), "Card number is required.");
            GiftCardWalletObject.this.zzbj = this.zzbq.zzf();
            Preconditions.checkArgument(!TextUtils.isEmpty(GiftCardWalletObject.this.zzbj.getName()), "Card name is required.");
            Preconditions.checkArgument(!TextUtils.isEmpty(GiftCardWalletObject.this.zzbj.getIssuerName()), "Card issuer name is required.");
            return GiftCardWalletObject.this;
        }

        public final Builder setBalanceCurrencyCode(String str) {
            GiftCardWalletObject.this.zzbn = str;
            return this;
        }

        public final Builder setBalanceMicros(long j) {
            GiftCardWalletObject.this.zzbm = j;
            return this;
        }

        public final Builder setBalanceUpdateTime(long j) {
            GiftCardWalletObject.this.zzbo = j;
            return this;
        }

        public final Builder setBarcodeAlternateText(String str) {
            this.zzbq.zze(str);
            return this;
        }

        public final Builder setBarcodeLabel(String str) {
            this.zzbq.zzh(str);
            return this;
        }

        public final Builder setBarcodeType(String str) {
            this.zzbq.zzf(str);
            return this;
        }

        public final Builder setBarcodeValue(String str) {
            this.zzbq.zzg(str);
            return this;
        }

        public final Builder setCardIdentifier(String str) {
            GiftCardWalletObject.this.zzbl = str;
            return this;
        }

        public final Builder setCardNumber(String str) {
            GiftCardWalletObject.this.zzbk = str;
            return this;
        }

        public final Builder setClassId(String str) {
            this.zzbq.zzb(str);
            return this;
        }

        public final Builder setEventNumber(String str) {
            GiftCardWalletObject.this.zzbp = str;
            return this;
        }

        public final Builder setId(String str) {
            this.zzbq.zza(str);
            return this;
        }

        public final Builder setInfoModuleDataHexBackgroundColor(String str) {
            this.zzbq.zzj(str);
            return this;
        }

        public final Builder setInfoModuleDataHexFontColor(String str) {
            this.zzbq.zzi(str);
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
            this.zzbq.zza(z);
            return this;
        }

        public final Builder setIssuerName(String str) {
            this.zzbq.zzd(str);
            return this;
        }

        public final Builder setPin(String str) {
            GiftCardWalletObject.this.pin = str;
            return this;
        }

        public final Builder setState(int i) {
            this.zzbq.zzc(i);
            return this;
        }

        public final Builder setTitle(String str) {
            this.zzbq.zzc(str);
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval timeInterval) {
            this.zzbq.zza(timeInterval);
            return this;
        }
    }

    GiftCardWalletObject() {
    }

    @SafeParcelable.Constructor
    GiftCardWalletObject(@SafeParcelable.Param(id = 2) CommonWalletObject commonWalletObject, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) long j, @SafeParcelable.Param(id = 7) String str4, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 9) String str5) {
        this.zzbj = commonWalletObject;
        this.zzbk = str;
        this.pin = str2;
        this.zzbm = j;
        this.zzbn = str4;
        this.zzbo = j2;
        this.zzbp = str5;
        this.zzbl = str3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getBalanceCurrencyCode() {
        return this.zzbn;
    }

    public final long getBalanceMicros() {
        return this.zzbm;
    }

    public final long getBalanceUpdateTime() {
        return this.zzbo;
    }

    public final String getBarcodeAlternateText() {
        return this.zzbj.getBarcodeAlternateText();
    }

    public final String getBarcodeLabel() {
        return this.zzbj.getBarcodeLabel();
    }

    public final String getBarcodeType() {
        return this.zzbj.getBarcodeType();
    }

    public final String getBarcodeValue() {
        return this.zzbj.getBarcodeValue();
    }

    public final String getCardIdentifier() {
        return this.zzbl;
    }

    public final String getCardNumber() {
        return this.zzbk;
    }

    public final String getClassId() {
        return this.zzbj.getClassId();
    }

    public final String getEventNumber() {
        return this.zzbp;
    }

    public final String getId() {
        return this.zzbj.getId();
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzbj.getImageModuleDataMainImageUris();
    }

    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzbj.getInfoModuleDataHexBackgroundColor();
    }

    public final String getInfoModuleDataHexFontColor() {
        return this.zzbj.getInfoModuleDataHexFontColor();
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzbj.getInfoModuleDataLabelValueRows();
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzbj.getInfoModuleDataShowLastUpdateTime();
    }

    public final String getIssuerName() {
        return this.zzbj.getIssuerName();
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzbj.getLinksModuleDataUris();
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzbj.getLocations();
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzbj.getMessages();
    }

    public final String getPin() {
        return this.pin;
    }

    public final int getState() {
        return this.zzbj.getState();
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzbj.getTextModulesData();
    }

    public final String getTitle() {
        return this.zzbj.getName();
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzbj.getValidTimeInterval();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzbj, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbk, false);
        SafeParcelWriter.writeString(parcel, 4, this.pin, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbl, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzbm);
        SafeParcelWriter.writeString(parcel, 7, this.zzbn, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzbo);
        SafeParcelWriter.writeString(parcel, 9, this.zzbp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
