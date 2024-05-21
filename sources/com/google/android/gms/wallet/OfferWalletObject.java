package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
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

@SafeParcelable.Class(creator = "OfferWalletObjectCreator")
public final class OfferWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OfferWalletObject> CREATOR = new zzab();
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
    private final int versionCode;
    @SafeParcelable.Field(id = 4)
    CommonWalletObject zzbj;
    @SafeParcelable.Field(id = 2)
    String zzce;
    @SafeParcelable.Field(id = 3)
    String zzdq;

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

        public final OfferWalletObject build() {
            OfferWalletObject.this.zzbj = this.zzbq.zzf();
            return OfferWalletObject.this;
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

        public final Builder setClassId(String str) {
            this.zzbq.zzb(str);
            return this;
        }

        public final Builder setId(String str) {
            this.zzbq.zza(str);
            OfferWalletObject.this.zzce = str;
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

        public final Builder setRedemptionCode(String str) {
            OfferWalletObject.this.zzdq = str;
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

    OfferWalletObject() {
        this.versionCode = 3;
    }

    @SafeParcelable.Constructor
    OfferWalletObject(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) CommonWalletObject commonWalletObject) {
        this.versionCode = i;
        this.zzdq = str2;
        if (i < 3) {
            this.zzbj = CommonWalletObject.zze().zza(str).zzf();
        } else {
            this.zzbj = commonWalletObject;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public final String getClassId() {
        return this.zzbj.getClassId();
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

    public final String getRedemptionCode() {
        return this.zzdq;
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

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, this.zzce, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdq, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbj, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
