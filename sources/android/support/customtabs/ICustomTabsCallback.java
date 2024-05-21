package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICustomTabsCallback extends IInterface {
    void extraCallback(String str, Bundle bundle) throws RemoteException;

    void onMessageChannelReady(Bundle bundle) throws RemoteException;

    void onNavigationEvent(int i, Bundle bundle) throws RemoteException;

    void onPostMessage(String str, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ICustomTabsCallback {
        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
        static final int TRANSACTION_extraCallback = 3;
        static final int TRANSACTION_onMessageChannelReady = 4;
        static final int TRANSACTION_onNavigationEvent = 2;
        static final int TRANSACTION_onPostMessage = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICustomTabsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICustomTabsCallback)) {
                return new Proxy(iBinder);
            }
            return (ICustomTabsCallback) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x0087
                r0 = 0
                switch(r3) {
                    case 2: goto L_0x0068;
                    case 3: goto L_0x0049;
                    case 4: goto L_0x002e;
                    case 5: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "android.support.customtabs.ICustomTabsCallback"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x0027
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0027:
                r2.onPostMessage(r3, r0)
                r5.writeNoException()
                return r1
            L_0x002e:
                java.lang.String r3 = "android.support.customtabs.ICustomTabsCallback"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0042
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0042:
                r2.onMessageChannelReady(r0)
                r5.writeNoException()
                return r1
            L_0x0049:
                java.lang.String r3 = "android.support.customtabs.ICustomTabsCallback"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x0061
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0061:
                r2.extraCallback(r3, r0)
                r5.writeNoException()
                return r1
            L_0x0068:
                java.lang.String r3 = "android.support.customtabs.ICustomTabsCallback"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x0080
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0080:
                r2.onNavigationEvent(r3, r0)
                r5.writeNoException()
                return r1
            L_0x0087:
                java.lang.String r3 = "android.support.customtabs.ICustomTabsCallback"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.customtabs.ICustomTabsCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements ICustomTabsCallback {
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

            public void onNavigationEvent(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void extraCallback(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onMessageChannelReady(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPostMessage(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
