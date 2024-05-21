package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.wallet.Wallet;

final class zzap extends Api.AbstractClientBuilder<zzad, Wallet.WalletOptions> {
    zzap() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Wallet.WalletOptions walletOptions = (Wallet.WalletOptions) obj;
        if (walletOptions == null) {
            walletOptions = new Wallet.WalletOptions((zzap) null);
        }
        return new zzad(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, walletOptions.theme, walletOptions.zzer);
    }
}
