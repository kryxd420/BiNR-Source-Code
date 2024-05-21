package com.google.android.gms.wallet;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet;

public class WalletObjectsClient extends GoogleApi<Wallet.WalletOptions> {
    WalletObjectsClient(@NonNull Activity activity, @Nullable Wallet.WalletOptions walletOptions) {
        super(activity, Wallet.API, walletOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<AutoResolvableVoidResult> createWalletObjects(@NonNull CreateWalletObjectsRequest createWalletObjectsRequest) {
        return doWrite(new zzaq(this, createWalletObjectsRequest));
    }
}
