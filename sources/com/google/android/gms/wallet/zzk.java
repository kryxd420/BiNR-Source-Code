package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzk implements Parcelable.Creator<FullWallet> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v8, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x007f
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x007a;
                case 3: goto L_0x0075;
                case 4: goto L_0x006b;
                case 5: goto L_0x0066;
                case 6: goto L_0x005c;
                case 7: goto L_0x0052;
                case 8: goto L_0x004d;
                case 9: goto L_0x0043;
                case 10: goto L_0x0039;
                case 11: goto L_0x002f;
                case 12: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.PaymentMethodToken> r2 = com.google.android.gms.wallet.PaymentMethodToken.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r13 = r1
            com.google.android.gms.wallet.PaymentMethodToken r13 = (com.google.android.gms.wallet.PaymentMethodToken) r13
            goto L_0x0010
        L_0x002f:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.InstrumentInfo> r2 = com.google.android.gms.wallet.InstrumentInfo.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r15, r1, r2)
            r12 = r1
            com.google.android.gms.wallet.InstrumentInfo[] r12 = (com.google.android.gms.wallet.InstrumentInfo[]) r12
            goto L_0x0010
        L_0x0039:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.UserAddress> r2 = com.google.android.gms.identity.intents.model.UserAddress.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r11 = r1
            com.google.android.gms.identity.intents.model.UserAddress r11 = (com.google.android.gms.identity.intents.model.UserAddress) r11
            goto L_0x0010
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.UserAddress> r2 = com.google.android.gms.identity.intents.model.UserAddress.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r10 = r1
            com.google.android.gms.identity.intents.model.UserAddress r10 = (com.google.android.gms.identity.intents.model.UserAddress) r10
            goto L_0x0010
        L_0x004d:
            java.lang.String[] r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r15, r1)
            goto L_0x0010
        L_0x0052:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.zza> r2 = com.google.android.gms.wallet.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r8 = r1
            com.google.android.gms.wallet.zza r8 = (com.google.android.gms.wallet.zza) r8
            goto L_0x0010
        L_0x005c:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.zza> r2 = com.google.android.gms.wallet.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r7 = r1
            com.google.android.gms.wallet.zza r7 = (com.google.android.gms.wallet.zza) r7
            goto L_0x0010
        L_0x0066:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x006b:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.ProxyCard> r2 = com.google.android.gms.wallet.ProxyCard.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r5 = r1
            com.google.android.gms.wallet.ProxyCard r5 = (com.google.android.gms.wallet.ProxyCard) r5
            goto L_0x0010
        L_0x0075:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x007a:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x007f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.wallet.FullWallet r15 = new com.google.android.gms.wallet.FullWallet
            r2 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzk.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new FullWallet[i];
    }
}
