package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends TaskApiCall<zzad, Boolean> {
    private final /* synthetic */ IsReadyToPayRequest zzef;

    zzai(PaymentsClient paymentsClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzef = isReadyToPayRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzad) anyClient).zza(this.zzef, (TaskCompletionSource<Boolean>) taskCompletionSource);
    }
}
