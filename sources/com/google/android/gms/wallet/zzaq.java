package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaq extends TaskApiCall<zzad, AutoResolvableVoidResult> {
    private final /* synthetic */ CreateWalletObjectsRequest zzet;

    zzaq(WalletObjectsClient walletObjectsClient, CreateWalletObjectsRequest createWalletObjectsRequest) {
        this.zzet = createWalletObjectsRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzad) anyClient).zza(this.zzet, (TaskCompletionSource<AutoResolvableVoidResult>) taskCompletionSource);
    }
}
