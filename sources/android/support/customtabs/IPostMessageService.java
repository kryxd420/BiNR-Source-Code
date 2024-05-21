package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPostMessageService extends IInterface {
    void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) throws RemoteException;

    void onPostMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IPostMessageService {
        private static final String DESCRIPTOR = "android.support.customtabs.IPostMessageService";
        static final int TRANSACTION_onMessageChannelReady = 2;
        static final int TRANSACTION_onPostMessage = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPostMessageService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPostMessageService)) {
                return new Proxy(iBinder);
            }
            return (IPostMessageService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x0059
                r0 = 0
                switch(r4) {
                    case 2: goto L_0x0036;
                    case 3: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x000f:
                java.lang.String r4 = "android.support.customtabs.IPostMessageService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r4 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r4)
                java.lang.String r7 = r5.readString()
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x002f
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x002f:
                r3.onPostMessage(r4, r7, r0)
                r6.writeNoException()
                return r1
            L_0x0036:
                java.lang.String r4 = "android.support.customtabs.IPostMessageService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r4 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r4)
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0052
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0052:
                r3.onMessageChannelReady(r4, r0)
                r6.writeNoException()
                return r1
            L_0x0059:
                java.lang.String r4 = "android.support.customtabs.IPostMessageService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.customtabs.IPostMessageService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IPostMessageService {
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

            public void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iCustomTabsCallback != null ? iCustomTabsCallback.asBinder() : null);
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

            public void onPostMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iCustomTabsCallback != null ? iCustomTabsCallback.asBinder() : null);
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
        }
    }
}
