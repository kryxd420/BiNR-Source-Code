package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzc implements Parcelable.Creator<CardInfo> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r8 = r6
            r7 = 0
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x003e
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0039;
                case 2: goto L_0x0034;
                case 3: goto L_0x002f;
                case 4: goto L_0x002a;
                case 5: goto L_0x0020;
                default: goto L_0x001c;
            }
        L_0x001c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r1)
            goto L_0x000b
        L_0x0020:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.UserAddress> r2 = com.google.android.gms.identity.intents.model.UserAddress.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r2)
            r8 = r1
            com.google.android.gms.identity.intents.model.UserAddress r8 = (com.google.android.gms.identity.intents.model.UserAddress) r8
            goto L_0x000b
        L_0x002a:
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r1)
            goto L_0x000b
        L_0x002f:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            goto L_0x000b
        L_0x0034:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            goto L_0x000b
        L_0x0039:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            goto L_0x000b
        L_0x003e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r0)
            com.google.android.gms.wallet.CardInfo r10 = new com.google.android.gms.wallet.CardInfo
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CardInfo[i];
    }
}
