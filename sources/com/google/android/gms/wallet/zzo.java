package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzo implements Parcelable.Creator<GiftCardWalletObject> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r17)
            r2 = 0
            r4 = 0
            r10 = r2
            r13 = r10
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r12 = r9
            r15 = r12
        L_0x0011:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0053
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r17)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x0049;
                case 3: goto L_0x0044;
                case 4: goto L_0x003f;
                case 5: goto L_0x003a;
                case 6: goto L_0x0035;
                case 7: goto L_0x0030;
                case 8: goto L_0x002b;
                case 9: goto L_0x0026;
                default: goto L_0x0022;
            }
        L_0x0022:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0011
        L_0x0026:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0011
        L_0x002b:
            long r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0011
        L_0x0030:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0011
        L_0x0035:
            long r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0011
        L_0x003a:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0011
        L_0x003f:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0011
        L_0x0044:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0011
        L_0x0049:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.wobs.CommonWalletObject> r3 = com.google.android.gms.wallet.wobs.CommonWalletObject.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r6 = r2
            com.google.android.gms.wallet.wobs.CommonWalletObject r6 = (com.google.android.gms.wallet.wobs.CommonWalletObject) r6
            goto L_0x0011
        L_0x0053:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.wallet.GiftCardWalletObject r0 = new com.google.android.gms.wallet.GiftCardWalletObject
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r12, r13, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzo.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GiftCardWalletObject[i];
    }
}
