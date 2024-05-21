package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zzai extends zzaf {
    private final BaseImplementation.ResultHolder<BooleanResult> zzgk;

    public zzai(BaseImplementation.ResultHolder<BooleanResult> resultHolder) {
        this.zzgk = resultHolder;
    }

    public final void zza(Status status, boolean z, Bundle bundle) {
        this.zzgk.setResult(new BooleanResult(status, z));
    }
}
