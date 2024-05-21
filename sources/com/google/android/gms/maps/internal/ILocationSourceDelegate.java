package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {

    public static abstract class zza extends zzb implements ILocationSourceDelegate {
        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }

        /* JADX WARNING: type inference failed for: r2v2, types: [android.os.IInterface] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
            /*
                r0 = this;
                switch(r1) {
                    case 1: goto L_0x0009;
                    case 2: goto L_0x0005;
                    default: goto L_0x0003;
                }
            L_0x0003:
                r1 = 0
                return r1
            L_0x0005:
                r0.deactivate()
                goto L_0x0028
            L_0x0009:
                android.os.IBinder r1 = r2.readStrongBinder()
                if (r1 != 0) goto L_0x0011
                r1 = 0
                goto L_0x0025
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IOnLocationChangeListener"
                android.os.IInterface r2 = r1.queryLocalInterface(r2)
                boolean r4 = r2 instanceof com.google.android.gms.maps.internal.zzah
                if (r4 == 0) goto L_0x001f
                r1 = r2
                com.google.android.gms.maps.internal.zzah r1 = (com.google.android.gms.maps.internal.zzah) r1
                goto L_0x0025
            L_0x001f:
                com.google.android.gms.maps.internal.zzai r2 = new com.google.android.gms.maps.internal.zzai
                r2.<init>(r1)
                r1 = r2
            L_0x0025:
                r0.activate(r1)
            L_0x0028:
                r3.writeNoException()
                r1 = 1
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.ILocationSourceDelegate.zza.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void activate(zzah zzah) throws RemoteException;

    void deactivate() throws RemoteException;
}
