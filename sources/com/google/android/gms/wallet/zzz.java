package com.google.android.gms.wallet;

import android.os.Parcelable;

public final class zzz implements Parcelable.Creator<MaskedWalletRequest> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r24) {
        /*
            r23 = this;
            r0 = r24
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r24)
            r2 = 1
            r3 = 0
            r4 = 0
            r6 = r4
            r10 = r6
            r11 = r10
            r12 = r11
            r13 = r12
            r16 = r13
            r19 = r16
            r20 = r19
            r21 = r20
            r22 = r21
            r7 = 0
            r8 = 0
            r9 = 0
            r14 = 0
            r15 = 0
            r17 = 1
            r18 = 1
        L_0x0021:
            int r2 = r24.dataPosition()
            if (r2 >= r1) goto L_0x009e
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r24)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x0099;
                case 3: goto L_0x0094;
                case 4: goto L_0x008f;
                case 5: goto L_0x008a;
                case 6: goto L_0x0085;
                case 7: goto L_0x0080;
                case 8: goto L_0x007b;
                case 9: goto L_0x0071;
                case 10: goto L_0x006c;
                case 11: goto L_0x0067;
                case 12: goto L_0x005c;
                case 13: goto L_0x0057;
                case 14: goto L_0x0052;
                case 15: goto L_0x004b;
                case 16: goto L_0x0040;
                case 17: goto L_0x003b;
                case 18: goto L_0x0036;
                default: goto L_0x0032;
            }
        L_0x0032:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0021
        L_0x0036:
            java.lang.String r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0021
        L_0x003b:
            java.util.ArrayList r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntegerList(r0, r2)
            goto L_0x0021
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.PaymentMethodTokenizationParameters> r3 = com.google.android.gms.wallet.PaymentMethodTokenizationParameters.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r20 = r2
            com.google.android.gms.wallet.PaymentMethodTokenizationParameters r20 = (com.google.android.gms.wallet.PaymentMethodTokenizationParameters) r20
            goto L_0x0021
        L_0x004b:
            android.os.Parcelable$Creator<com.google.android.gms.identity.intents.model.CountrySpecification> r3 = com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR
            java.util.ArrayList r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0021
        L_0x0052:
            boolean r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x0057:
            boolean r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x005c:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.CountrySpecification> r3 = com.google.android.gms.wallet.CountrySpecification.CREATOR
            java.lang.Object[] r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r0, r2, r3)
            r16 = r2
            com.google.android.gms.wallet.CountrySpecification[] r16 = (com.google.android.gms.wallet.CountrySpecification[]) r16
            goto L_0x0021
        L_0x0067:
            boolean r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x006c:
            boolean r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x0071:
            android.os.Parcelable$Creator<com.google.android.gms.wallet.Cart> r3 = com.google.android.gms.wallet.Cart.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            com.google.android.gms.wallet.Cart r13 = (com.google.android.gms.wallet.Cart) r13
            goto L_0x0021
        L_0x007b:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0021
        L_0x0080:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0021
        L_0x0085:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0021
        L_0x008a:
            boolean r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x008f:
            boolean r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x0094:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x0099:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0021
        L_0x009e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.wallet.MaskedWalletRequest r0 = new com.google.android.gms.wallet.MaskedWalletRequest
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wallet.zzz.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MaskedWalletRequest[i];
    }
}
