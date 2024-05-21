package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaj extends TaskApiCall<zzad, PaymentData> {
    private final /* synthetic */ PaymentDataRequest zzeg;

    zzaj(PaymentsClient paymentsClient, PaymentDataRequest paymentDataRequest) {
        this.zzeg = paymentDataRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzad) anyClient).zza(this.zzeg, (TaskCompletionSource<PaymentData>) taskCompletionSource);
    }
}
