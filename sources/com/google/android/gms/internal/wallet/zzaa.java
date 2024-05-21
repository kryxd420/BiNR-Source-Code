package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet;

final class zzaa extends Wallet.zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ String zzgf;
    private final /* synthetic */ String zzgg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaa(zzw zzw, GoogleApiClient googleApiClient, String str, String str2, int i) {
        super(googleApiClient);
        this.zzgf = str;
        this.zzgg = str2;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(this.zzgf, this.zzgg, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
