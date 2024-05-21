package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;

@KeepName
@SafeParcelable.Class(creator = "CommonWalletObjectCreator")
@SafeParcelable.Reserved({1})
public class CommonWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CommonWalletObject> CREATOR = new zzb();
    @SafeParcelable.Field(id = 4)
    String name;
    @SafeParcelable.Field(id = 10)
    int state;
    @SafeParcelable.Field(id = 2)
    String zzce;
    @SafeParcelable.Field(id = 5)
    String zzcg;
    @SafeParcelable.Field(id = 6)
    String zzcj;
    @SafeParcelable.Field(id = 7)
    String zzck;
    @SafeParcelable.Field(id = 8)
    String zzcl;
    @SafeParcelable.Field(id = 9)
    String zzcm;
    @SafeParcelable.Field(id = 3)
    String zzcn;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 11)
    ArrayList<WalletObjectMessage> zzco;
    @SafeParcelable.Field(id = 12)
    TimeInterval zzcp;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 13)
    ArrayList<LatLng> zzcq;
    @SafeParcelable.Field(id = 14)
    String zzcr;
    @SafeParcelable.Field(id = 15)
    String zzcs;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 16)
    ArrayList<LabelValueRow> zzct;
    @SafeParcelable.Field(id = 17)
    boolean zzcu;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 18)
    ArrayList<UriData> zzcv;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 19)
    ArrayList<TextModuleData> zzcw;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 20)
    ArrayList<UriData> zzcx;

    public final class zza {
        private zza() {
        }

        public final zza zza(LatLng latLng) {
            CommonWalletObject.this.zzcq.add(latLng);
            return this;
        }

        public final zza zza(LabelValueRow labelValueRow) {
            CommonWalletObject.this.zzct.add(labelValueRow);
            return this;
        }

        public final zza zza(TextModuleData textModuleData) {
            CommonWalletObject.this.zzcw.add(textModuleData);
            return this;
        }

        public final zza zza(TimeInterval timeInterval) {
            CommonWalletObject.this.zzcp = timeInterval;
            return this;
        }

        public final zza zza(UriData uriData) {
            CommonWalletObject.this.zzcv.add(uriData);
            return this;
        }

        public final zza zza(WalletObjectMessage walletObjectMessage) {
            CommonWalletObject.this.zzco.add(walletObjectMessage);
            return this;
        }

        public final zza zza(String str) {
            CommonWalletObject.this.zzce = str;
            return this;
        }

        public final zza zza(Collection<WalletObjectMessage> collection) {
            CommonWalletObject.this.zzco.addAll(collection);
            return this;
        }

        public final zza zza(boolean z) {
            CommonWalletObject.this.zzcu = z;
            return this;
        }

        public final zza zzb(UriData uriData) {
            CommonWalletObject.this.zzcx.add(uriData);
            return this;
        }

        public final zza zzb(String str) {
            CommonWalletObject.this.zzcn = str;
            return this;
        }

        public final zza zzb(Collection<LatLng> collection) {
            CommonWalletObject.this.zzcq.addAll(collection);
            return this;
        }

        public final zza zzc(int i) {
            CommonWalletObject.this.state = i;
            return this;
        }

        public final zza zzc(String str) {
            CommonWalletObject.this.name = str;
            return this;
        }

        public final zza zzc(Collection<LabelValueRow> collection) {
            CommonWalletObject.this.zzct.addAll(collection);
            return this;
        }

        public final zza zzd(String str) {
            CommonWalletObject.this.zzcg = str;
            return this;
        }

        public final zza zzd(Collection<UriData> collection) {
            CommonWalletObject.this.zzcv.addAll(collection);
            return this;
        }

        public final zza zze(String str) {
            CommonWalletObject.this.zzcj = str;
            return this;
        }

        public final zza zze(Collection<TextModuleData> collection) {
            CommonWalletObject.this.zzcw.addAll(collection);
            return this;
        }

        public final zza zzf(String str) {
            CommonWalletObject.this.zzck = str;
            return this;
        }

        public final zza zzf(Collection<UriData> collection) {
            CommonWalletObject.this.zzcx.addAll(collection);
            return this;
        }

        public final CommonWalletObject zzf() {
            return CommonWalletObject.this;
        }

        public final zza zzg(String str) {
            CommonWalletObject.this.zzcl = str;
            return this;
        }

        public final zza zzh(String str) {
            CommonWalletObject.this.zzcm = str;
            return this;
        }

        public final zza zzi(String str) {
            CommonWalletObject.this.zzcr = str;
            return this;
        }

        public final zza zzj(String str) {
            CommonWalletObject.this.zzcs = str;
            return this;
        }
    }

    CommonWalletObject() {
        this.zzco = ArrayUtils.newArrayList();
        this.zzcq = ArrayUtils.newArrayList();
        this.zzct = ArrayUtils.newArrayList();
        this.zzcv = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
    }

    @SafeParcelable.Constructor
    CommonWalletObject(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) String str8, @SafeParcelable.Param(id = 10) int i, @SafeParcelable.Param(id = 11) ArrayList<WalletObjectMessage> arrayList, @SafeParcelable.Param(id = 12) TimeInterval timeInterval, @SafeParcelable.Param(id = 13) ArrayList<LatLng> arrayList2, @SafeParcelable.Param(id = 14) String str9, @SafeParcelable.Param(id = 15) String str10, @SafeParcelable.Param(id = 16) ArrayList<LabelValueRow> arrayList3, @SafeParcelable.Param(id = 17) boolean z, @SafeParcelable.Param(id = 18) ArrayList<UriData> arrayList4, @SafeParcelable.Param(id = 19) ArrayList<TextModuleData> arrayList5, @SafeParcelable.Param(id = 20) ArrayList<UriData> arrayList6) {
        this.zzce = str;
        this.zzcn = str2;
        this.name = str3;
        this.zzcg = str4;
        this.zzcj = str5;
        this.zzck = str6;
        this.zzcl = str7;
        this.zzcm = str8;
        this.state = i;
        this.zzco = arrayList;
        this.zzcp = timeInterval;
        this.zzcq = arrayList2;
        this.zzcr = str9;
        this.zzcs = str10;
        this.zzct = arrayList3;
        this.zzcu = z;
        this.zzcv = arrayList4;
        this.zzcw = arrayList5;
        this.zzcx = arrayList6;
    }

    public static zza zze() {
        return new zza();
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

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzco;
    }

    public final String getName() {
        return this.name;
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

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzce, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcn, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzcj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzck, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzcl, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzcm, false);
        SafeParcelWriter.writeInt(parcel, 10, this.state);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzco, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzcp, i, false);
        SafeParcelWriter.writeTypedList(parcel, 13, this.zzcq, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzcr, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzcs, false);
        SafeParcelWriter.writeTypedList(parcel, 16, this.zzct, false);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzcu);
        SafeParcelWriter.writeTypedList(parcel, 18, this.zzcv, false);
        SafeParcelWriter.writeTypedList(parcel, 19, this.zzcw, false);
        SafeParcelWriter.writeTypedList(parcel, 20, this.zzcx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
