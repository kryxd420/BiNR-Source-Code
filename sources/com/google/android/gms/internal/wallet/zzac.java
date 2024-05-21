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

final class zzac extends Wallet.zza<BooleanResult> {
    private final /* synthetic */ IsReadyToPayRequest zzef;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(zzw zzw, GoogleApiClient googleApiClient, IsReadyToPayRequest isReadyToPayRequest) {
        super(googleApiClient);
        this.zzef = isReadyToPayRequest;
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
        zzad.zza(this.zzef, (BaseImplementation.ResultHolder<BooleanResult>) this);
    }
}
