package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "WalletObjectMessageCreator")
@SafeParcelable.Reserved({1})
public final class WalletObjectMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WalletObjectMessage> CREATOR = new zzn();
    @SafeParcelable.Field(id = 2)
    String zzgz;
    @SafeParcelable.Field(id = 3)
    String zzha;
    @SafeParcelable.Field(id = 4)
    TimeInterval zzhe;
    @SafeParcelable.Field(id = 5)
    UriData zzhf;
    @SafeParcelable.Field(id = 6)
    UriData zzhg;

    public final class Builder {
        private Builder() {
        }

        public final WalletObjectMessage build() {
            return WalletObjectMessage.this;
        }

        public final Builder setActionUri(UriData uriData) {
            WalletObjectMessage.this.zzhf = uriData;
            return this;
        }

        public final Builder setBody(String str) {
            WalletObjectMessage.this.zzha = str;
            return this;
        }

        public final Builder setDisplayInterval(TimeInterval timeInterval) {
            WalletObjectMessage.this.zzhe = timeInterval;
            return this;
        }

        public final Builder setHeader(String str) {
            WalletObjectMessage.this.zzgz = str;
            return this;
        }

        public final Builder setImageUri(UriData uriData) {
            WalletObjectMessage.this.zzhg = uriData;
            return this;
        }
    }

    WalletObjectMessage() {
    }

    @SafeParcelable.Constructor
    WalletObjectMessage(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) TimeInterval timeInterval, @SafeParcelable.Param(id = 5) UriData uriData, @SafeParcelable.Param(id = 6) UriData uriData2) {
        this.zzgz = str;
        this.zzha = str2;
        this.zzhe = timeInterval;
        this.zzhf = uriData;
        this.zzhg = uriData2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final UriData getActionUri() {
        return this.zzhf;
    }

    public final String getBody() {
        return this.zzha;
    }

    public final TimeInterval getDisplayInterval() {
        return this.zzhe;
    }

    public final String getHeader() {
        return this.zzgz;
    }

    public final UriData getImageUri() {
        return this.zzhg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzha, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzhe, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzhf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
