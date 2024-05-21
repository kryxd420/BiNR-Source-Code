package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzae implements Parcelable.Creator<PaymentDataRequest> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r17)
            r2 = 0
            r3 = 0
            r4 = 1
            r8 = r3
            r10 = r8
            r11 = r10
            r12 = r11
            r13 = r12
            r15 = r13
            r6 = 0
            r7 = 0
            r9 = 0
            r14 = 1
        L_0x0013:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x006e
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r17)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0069;
                case 2: goto L_0x0064;
                case 3: goto L_0x005a;
                case 4: goto L_0x0055;
                case 5: goto L_0x004b;
                case 6: goto L_0x0046;
                case 7: goto L_0x003c;
                case 8: goto L_0x0032;
                case 9: goto L_0x002d;
                case 10: goto L_0x0028;
                default: goto L_0x0024;
            }
        L_0x0024:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0013
        L_0x0028:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0013
        L_0x002d:
            boolean r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0013
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.TransactionInfo> r3 = com.google.android.gms.wallet.TransactionInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            com.google.android.gms.wallet.TransactionInfo r13 = (com.google.android.gms.wallet.TransactionInfo) r13
            goto L_0x0013
        L_0x003c:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.PaymentMethodTokenizationParameters> r3 = com.google.android.gms.wallet.PaymentMethodTokenizationParameters.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r12 = r2
            com.google.android.gms.wallet.PaymentMethodTokenizationParameters r12 = (com.google.android.gms.wallet.PaymentMethodTokenizationParameters) r12
            goto L_0x0013
        L_0x0046:
            java.util.ArrayList r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntegerList(r0, r2)
            goto L_0x0013
        L_0x004b:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.ShippingAddressRequirements> r3 = com.google.android.gms.wallet.ShippingAddressRequirements.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            com.google.android.gms.wallet.ShippingAddressRequirements r10 = (com.google.android.gms.wallet.ShippingAddressRequirements) r10
            goto L_0x0013
        L_0x0055:
            boolean r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0013
        L_0x005a:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.CardRequirements> r3 = com.google.android.gms.wallet.CardRequirements.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r8 = r2
            com.google.android.gms.wallet.CardRequirements r8 = (com.google.android.gms.wallet.CardRequirements) r8
            goto L_0x0013
        L_0x0064:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0013
        L_0x0069:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0013
        L_0x006e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.wallet.PaymentDataRequest r0 = new com.google.android.gms.wallet.PaymentDataRequest
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzae.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PaymentDataRequest[i];
    }
}
