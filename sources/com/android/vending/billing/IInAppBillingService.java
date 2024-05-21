package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IInAppBillingService extends IInterface {
    int consumePurchase(int i, String str, String str2) throws RemoteException;

    int consumePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException;

    Bundle getBuyIntent(int i, String str, String str2, String str3, String str4) throws RemoteException;

    Bundle getBuyIntentExtraParams(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException;

    Bundle getBuyIntentToReplaceSkus(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException;

    Bundle getPurchaseHistory(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException;

    Bundle getPurchases(int i, String str, String str2, String str3) throws RemoteException;

    Bundle getPurchasesExtraParams(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException;

    Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException;

    int isBillingSupported(int i, String str, String str2) throws RemoteException;

    int isBillingSupportedExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException;

    int isPromoEligible(int i, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IInAppBillingService {
        private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingService";
        static final int TRANSACTION_consumePurchase = 5;
        static final int TRANSACTION_consumePurchaseExtraParams = 12;
        static final int TRANSACTION_getBuyIntent = 3;
        static final int TRANSACTION_getBuyIntentExtraParams = 8;
        static final int TRANSACTION_getBuyIntentToReplaceSkus = 7;
        static final int TRANSACTION_getPurchaseHistory = 9;
        static final int TRANSACTION_getPurchases = 4;
        static final int TRANSACTION_getPurchasesExtraParams = 11;
        static final int TRANSACTION_getSkuDetails = 2;
        static final int TRANSACTION_isBillingSupported = 1;
        static final int TRANSACTION_isBillingSupportedExtraParams = 10;
        static final int TRANSACTION_isPromoEligible = 6;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInAppBillingService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IInAppBillingService)) {
                return new Proxy(iBinder);
            }
            return (IInAppBillingService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
            /*
                r10 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r11 == r0) goto L_0x0229
                r0 = 0
                r2 = 0
                switch(r11) {
                    case 1: goto L_0x020d;
                    case 2: goto L_0x01d9;
                    case 3: goto L_0x01ab;
                    case 4: goto L_0x0182;
                    case 5: goto L_0x0166;
                    case 6: goto L_0x014a;
                    case 7: goto L_0x0118;
                    case 8: goto L_0x00da;
                    case 9: goto L_0x00a0;
                    case 10: goto L_0x0075;
                    case 11: goto L_0x003b;
                    case 12: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r11 = super.onTransact(r11, r12, r13, r14)
                return r11
            L_0x0010:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r2 = r12.readString()
                int r3 = r12.readInt()
                if (r3 == 0) goto L_0x0030
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r0.createFromParcel(r12)
                r0 = r12
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0030:
                int r11 = r10.consumePurchaseExtraParams(r11, r14, r2, r0)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x003b:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r4 = r12.readInt()
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                java.lang.String r7 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x005f
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x005f:
                r8 = r0
                r3 = r10
                android.os.Bundle r11 = r3.getPurchasesExtraParams(r4, r5, r6, r7, r8)
                r13.writeNoException()
                if (r11 == 0) goto L_0x0071
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x0074
            L_0x0071:
                r13.writeInt(r2)
            L_0x0074:
                return r1
            L_0x0075:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r2 = r12.readString()
                int r3 = r12.readInt()
                if (r3 == 0) goto L_0x0095
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r0.createFromParcel(r12)
                r0 = r12
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0095:
                int r11 = r10.isBillingSupportedExtraParams(r11, r14, r2, r0)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x00a0:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r4 = r12.readInt()
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                java.lang.String r7 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00c4
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00c4:
                r8 = r0
                r3 = r10
                android.os.Bundle r11 = r3.getPurchaseHistory(r4, r5, r6, r7, r8)
                r13.writeNoException()
                if (r11 == 0) goto L_0x00d6
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x00d9
            L_0x00d6:
                r13.writeInt(r2)
            L_0x00d9:
                return r1
            L_0x00da:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r4 = r12.readInt()
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                java.lang.String r7 = r12.readString()
                java.lang.String r8 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0102
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0102:
                r9 = r0
                r3 = r10
                android.os.Bundle r11 = r3.getBuyIntentExtraParams(r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                if (r11 == 0) goto L_0x0114
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x0117
            L_0x0114:
                r13.writeInt(r2)
            L_0x0117:
                return r1
            L_0x0118:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r4 = r12.readInt()
                java.lang.String r5 = r12.readString()
                java.util.ArrayList r6 = r12.createStringArrayList()
                java.lang.String r7 = r12.readString()
                java.lang.String r8 = r12.readString()
                java.lang.String r9 = r12.readString()
                r3 = r10
                android.os.Bundle r11 = r3.getBuyIntentToReplaceSkus(r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                if (r11 == 0) goto L_0x0146
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x0149
            L_0x0146:
                r13.writeInt(r2)
            L_0x0149:
                return r1
            L_0x014a:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r12 = r12.readString()
                int r11 = r10.isPromoEligible(r11, r14, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0166:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r12 = r12.readString()
                int r11 = r10.consumePurchase(r11, r14, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0182:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r0 = r12.readString()
                java.lang.String r12 = r12.readString()
                android.os.Bundle r11 = r10.getPurchases(r11, r14, r0, r12)
                r13.writeNoException()
                if (r11 == 0) goto L_0x01a7
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x01aa
            L_0x01a7:
                r13.writeInt(r2)
            L_0x01aa:
                return r1
            L_0x01ab:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r4 = r12.readInt()
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                java.lang.String r7 = r12.readString()
                java.lang.String r8 = r12.readString()
                r3 = r10
                android.os.Bundle r11 = r3.getBuyIntent(r4, r5, r6, r7, r8)
                r13.writeNoException()
                if (r11 == 0) goto L_0x01d5
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x01d8
            L_0x01d5:
                r13.writeInt(r2)
            L_0x01d8:
                return r1
            L_0x01d9:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r3 = r12.readString()
                int r4 = r12.readInt()
                if (r4 == 0) goto L_0x01f9
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r0.createFromParcel(r12)
                r0 = r12
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x01f9:
                android.os.Bundle r11 = r10.getSkuDetails(r11, r14, r3, r0)
                r13.writeNoException()
                if (r11 == 0) goto L_0x0209
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                goto L_0x020c
            L_0x0209:
                r13.writeInt(r2)
            L_0x020c:
                return r1
            L_0x020d:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.lang.String r14 = r12.readString()
                java.lang.String r12 = r12.readString()
                int r11 = r10.isBillingSupported(r11, r14, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0229:
                java.lang.String r11 = "com.android.vending.billing.IInAppBillingService"
                r13.writeString(r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.vending.billing.IInAppBillingService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IInAppBillingService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int isBillingSupported(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntent(int i, String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchases(int i, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int consumePurchase(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int isPromoEligible(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntentToReplaceSkus(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntentExtraParams(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchaseHistory(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int isBillingSupportedExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchasesExtraParams(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int consumePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
