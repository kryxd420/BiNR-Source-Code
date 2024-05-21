package com.google.android.gms.internal.identity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzh extends zzb implements zzg {
    public zzh() {
        super("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zza(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
