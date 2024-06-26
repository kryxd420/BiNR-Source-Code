package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class zzd implements Parcelable.Creator<WalletFragmentInitParams> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        MaskedWalletRequest maskedWalletRequest = null;
        MaskedWallet maskedWallet = null;
        int i = -1;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    maskedWalletRequest = (MaskedWalletRequest) SafeParcelReader.createParcelable(parcel, readHeader, MaskedWalletRequest.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    maskedWallet = (MaskedWallet) SafeParcelReader.createParcelable(parcel, readHeader, MaskedWallet.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new WalletFragmentInitParams(str, maskedWalletRequest, i, maskedWallet);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WalletFragmentInitParams[i];
    }
}
