package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzi implements Parcelable.Creator<LoyaltyPoints> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        LoyaltyPointsBalance loyaltyPointsBalance = null;
        TimeInterval timeInterval = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 5) {
                switch (fieldId) {
                    case 2:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 3:
                        loyaltyPointsBalance = (LoyaltyPointsBalance) SafeParcelReader.createParcelable(parcel, readHeader, LoyaltyPointsBalance.CREATOR);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                timeInterval = (TimeInterval) SafeParcelReader.createParcelable(parcel, readHeader, TimeInterval.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LoyaltyPoints(str, loyaltyPointsBalance, timeInterval);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LoyaltyPoints[i];
    }
}
