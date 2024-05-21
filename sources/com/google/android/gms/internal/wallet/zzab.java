package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;

final class zzab extends Wallet.zza<BooleanResult> {
    zzab(zzw zzw, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new BooleanResult(status, false);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(IsReadyToPayRequest.newBuilder().build(), (BaseImplementation.ResultHolder<BooleanResult>) this);
    }
}
