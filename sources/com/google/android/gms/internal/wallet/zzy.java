package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class zzy extends Wallet.zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ MaskedWalletRequest zzgd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(zzw zzw, GoogleApiClient googleApiClient, MaskedWalletRequest maskedWalletRequest, int i) {
        super(googleApiClient);
        this.zzgd = maskedWalletRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(this.zzgd, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
