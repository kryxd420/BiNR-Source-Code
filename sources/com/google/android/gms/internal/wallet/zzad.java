package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolvableVoidResult;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;

public final class zzad extends GmsClient<zzq> {
    private final int environment;
    private final int theme;
    private final String zzci;
    private final boolean zzer;
    private final Context zzgh;

    public zzad(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, int i, int i2, boolean z) {
        super(context, looper, 4, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzgh = context;
        this.environment = i;
        this.zzci = clientSettings.getAccountName();
        this.theme = i2;
        this.zzer = z;
    }

    private final Bundle zzd() {
        int i = this.environment;
        String packageName = this.zzgh.getPackageName();
        String str = this.zzci;
        int i2 = this.theme;
        boolean z = this.zzer;
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", i);
        bundle.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", z);
        bundle.putString("androidPackageName", packageName);
        if (!TextUtils.isEmpty(str)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(str, AccountType.GOOGLE));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", i2);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        return queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzr(iBinder);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    public final boolean requiresAccount() {
        return true;
    }

    public final void zza(CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        zzae zzae = new zzae((Activity) this.zzgh, i);
        try {
            ((zzq) getService()).zza(createWalletObjectsRequest, zzd(), (zzu) zzae);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzae.zza(8, Bundle.EMPTY);
        }
    }

    public final void zza(CreateWalletObjectsRequest createWalletObjectsRequest, TaskCompletionSource<AutoResolvableVoidResult> taskCompletionSource) {
        Bundle zzd = zzd();
        zzd.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzah zzah = new zzah(taskCompletionSource);
        try {
            ((zzq) getService()).zza(createWalletObjectsRequest, zzd, (zzu) zzah);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzah.zza(8, Bundle.EMPTY);
        }
    }

    public final void zza(FullWalletRequest fullWalletRequest, int i) {
        zzae zzae = new zzae((Activity) this.zzgh, i);
        try {
            ((zzq) getService()).zza(fullWalletRequest, zzd(), (zzu) zzae);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", e);
            zzae.zza(8, (FullWallet) null, Bundle.EMPTY);
        }
    }

    public final void zza(IsReadyToPayRequest isReadyToPayRequest, BaseImplementation.ResultHolder<BooleanResult> resultHolder) {
        zzai zzai = new zzai(resultHolder);
        try {
            ((zzq) getService()).zza(isReadyToPayRequest, zzd(), (zzu) zzai);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzai.zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    public final void zza(IsReadyToPayRequest isReadyToPayRequest, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzag zzag = new zzag(taskCompletionSource);
        try {
            ((zzq) getService()).zza(isReadyToPayRequest, zzd(), (zzu) zzag);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzag.zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    public final void zza(MaskedWalletRequest maskedWalletRequest, int i) {
        Bundle zzd = zzd();
        zzae zzae = new zzae((Activity) this.zzgh, i);
        try {
            ((zzq) getService()).zza(maskedWalletRequest, zzd, (zzu) zzae);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", e);
            zzae.zza(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    public final void zza(PaymentDataRequest paymentDataRequest, TaskCompletionSource<PaymentData> taskCompletionSource) {
        Bundle zzd = zzd();
        zzd.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzaj zzaj = new zzaj(taskCompletionSource);
        try {
            ((zzq) getService()).zza(paymentDataRequest, zzd, (zzu) zzaj);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting payment data", e);
            zzaj.zza(Status.RESULT_INTERNAL_ERROR, (PaymentData) null, Bundle.EMPTY);
        }
    }

    public final void zza(String str, String str2, int i) {
        Bundle zzd = zzd();
        zzae zzae = new zzae((Activity) this.zzgh, i);
        try {
            ((zzq) getService()).zza(str, str2, zzd, zzae);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", e);
            zzae.zza(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    public final void zzb(int i) {
        Bundle zzd = zzd();
        zzae zzae = new zzae((Activity) this.zzgh, i);
        try {
            ((zzq) getService()).zza(zzd, zzae);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", e);
            zzae.zza(8, false, Bundle.EMPTY);
        }
    }
}
