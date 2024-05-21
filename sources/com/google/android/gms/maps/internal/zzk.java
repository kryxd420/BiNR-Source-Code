package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzk extends zza implements IMapViewDelegate {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IGoogleMapDelegate getMap() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.obtainAndWriteInterfaceToken()
            r1 = 1
            android.os.Parcel r0 = r4.transactAndReadException(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IGoogleMapDelegate
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.maps.internal.IGoogleMapDelegate r1 = (com.google.android.gms.maps.internal.IGoogleMapDelegate) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzg r2 = new com.google.android.gms.maps.internal.zzg
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzk.getMap():com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final void getMapAsync(zzap zzap) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzap);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    public final IObjectWrapper getView() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }

    public final void onExitAmbient() throws RemoteException {
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
    }

    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken());
    }

    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
    }

    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken());
    }
}
