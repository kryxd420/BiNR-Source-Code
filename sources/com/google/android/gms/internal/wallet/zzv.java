package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.zzar;

public abstract class zzv extends zzb implements zzu {
    public zzv() {
        super("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza(parcel.readInt(), (MaskedWallet) zzc.zza(parcel, MaskedWallet.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 2:
                zza(parcel.readInt(), (FullWallet) zzc.zza(parcel, FullWallet.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 3:
                zza(parcel.readInt(), zzc.zza(parcel), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 4:
                zza(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 6:
                zzb(parcel.readInt(), zzc.zza(parcel), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 7:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (zzf) zzc.zza(parcel, zzf.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 8:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 9:
                zza((Status) zzc.zza(parcel, Status.CREATOR), zzc.zza(parcel), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 10:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (zzh) zzc.zza(parcel, zzh.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 11:
                zzb((Status) zzc.zza(parcel, Status.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 12:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (zzar) zzc.zza(parcel, zzar.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 13:
                zzc((Status) zzc.zza(parcel, Status.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 14:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (PaymentData) zzc.zza(parcel, PaymentData.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            case 15:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (zzj) zzc.zza(parcel, zzj.CREATOR), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
