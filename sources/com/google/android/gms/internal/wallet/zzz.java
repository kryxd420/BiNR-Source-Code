package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class zzz extends Wallet.zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ FullWalletRequest zzge;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzz(zzw zzw, GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i) {
        super(googleApiClient);
        this.zzge = fullWalletRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(this.zzge, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
