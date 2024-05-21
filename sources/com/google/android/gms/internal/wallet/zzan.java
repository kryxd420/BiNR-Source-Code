package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.Wallet;

final class zzan extends Wallet.zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ CreateWalletObjectsRequest zzet;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzam zzam, GoogleApiClient googleApiClient, CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        super(googleApiClient);
        this.zzet = createWalletObjectsRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(this.zzet, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
