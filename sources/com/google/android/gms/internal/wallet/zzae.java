package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wallet.WalletConstants;
import java.lang.ref.WeakReference;

final class zzae extends zzaf {
    private final int zzaa;
    private final WeakReference<Activity> zzgi;

    public zzae(Activity activity, int i) {
        this.zzgi = new WeakReference<>(activity);
        this.zzaa = i;
    }

    public final void zza(int i, Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle should not be null");
        Activity activity = (Activity) this.zzgi.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
            return;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.zzaa);
            } catch (IntentSender.SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 75);
            sb.append("Create Wallet Objects confirmation UI will not be shown connection result: ");
            sb.append(valueOf);
            Log.e("WalletClientImpl", sb.toString());
            Intent intent = new Intent();
            intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, WalletConstants.ERROR_CODE_UNKNOWN);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzaa, intent, 1073741824);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                createPendingResult.send(1);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r5v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r3, com.google.android.gms.wallet.FullWallet r4, android.os.Bundle r5) {
        /*
            r2 = this;
            java.lang.ref.WeakReference<android.app.Activity> r0 = r2.zzgi
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            if (r0 != 0) goto L_0x0012
            java.lang.String r3 = "WalletClientImpl"
            java.lang.String r4 = "Ignoring onFullWalletLoaded, Activity has gone"
            android.util.Log.d(r3, r4)
            return
        L_0x0012:
            r1 = 0
            if (r5 == 0) goto L_0x001e
            java.lang.String r1 = "com.google.android.gms.wallet.EXTRA_PENDING_INTENT"
            android.os.Parcelable r5 = r5.getParcelable(r1)
            r1 = r5
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1
        L_0x001e:
            com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
            r5.<init>(r3, r1)
            boolean r1 = r5.hasResolution()
            if (r1 == 0) goto L_0x0038
            int r3 = r2.zzaa     // Catch:{ SendIntentException -> 0x002f }
            r5.startResolutionForResult(r0, r3)     // Catch:{ SendIntentException -> 0x002f }
            return
        L_0x002f:
            r3 = move-exception
            java.lang.String r4 = "WalletClientImpl"
            java.lang.String r5 = "Exception starting pending intent"
            android.util.Log.w(r4, r5, r3)
            return
        L_0x0038:
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            boolean r5 = r5.isSuccess()
            if (r5 == 0) goto L_0x004a
            r3 = -1
            java.lang.String r5 = "com.google.android.gms.wallet.EXTRA_FULL_WALLET"
            r1.putExtra(r5, r4)
            goto L_0x0057
        L_0x004a:
            r4 = 408(0x198, float:5.72E-43)
            if (r3 != r4) goto L_0x0050
            r4 = 0
            goto L_0x0051
        L_0x0050:
            r4 = 1
        L_0x0051:
            java.lang.String r5 = "com.google.android.gms.wallet.EXTRA_ERROR_CODE"
            r1.putExtra(r5, r3)
            r3 = r4
        L_0x0057:
            int r4 = r2.zzaa
            r5 = 1073741824(0x40000000, float:2.0)
            android.app.PendingIntent r4 = r0.createPendingResult(r4, r1, r5)
            if (r4 != 0) goto L_0x0069
            java.lang.String r3 = "WalletClientImpl"
            java.lang.String r4 = "Null pending result returned for onFullWalletLoaded"
            android.util.Log.w(r3, r4)
            return
        L_0x0069:
            r4.send(r3)     // Catch:{ CanceledException -> 0x006d }
            return
        L_0x006d:
            r3 = move-exception
            java.lang.String r4 = "WalletClientImpl"
            java.lang.String r5 = "Exception setting pending result"
            android.util.Log.w(r4, r5, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.wallet.zzae.zza(int, com.google.android.gms.wallet.FullWallet, android.os.Bundle):void");
    }

    /* JADX WARNING: type inference failed for: r5v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r3, com.google.android.gms.wallet.MaskedWallet r4, android.os.Bundle r5) {
        /*
            r2 = this;
            java.lang.ref.WeakReference<android.app.Activity> r0 = r2.zzgi
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            if (r0 != 0) goto L_0x0012
            java.lang.String r3 = "WalletClientImpl"
            java.lang.String r4 = "Ignoring onMaskedWalletLoaded, Activity has gone"
            android.util.Log.d(r3, r4)
            return
        L_0x0012:
            r1 = 0
            if (r5 == 0) goto L_0x001e
            java.lang.String r1 = "com.google.android.gms.wallet.EXTRA_PENDING_INTENT"
            android.os.Parcelable r5 = r5.getParcelable(r1)
            r1 = r5
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1
        L_0x001e:
            com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
            r5.<init>(r3, r1)
            boolean r1 = r5.hasResolution()
            if (r1 == 0) goto L_0x0038
            int r3 = r2.zzaa     // Catch:{ SendIntentException -> 0x002f }
            r5.startResolutionForResult(r0, r3)     // Catch:{ SendIntentException -> 0x002f }
            return
        L_0x002f:
            r3 = move-exception
            java.lang.String r4 = "WalletClientImpl"
            java.lang.String r5 = "Exception starting pending intent"
            android.util.Log.w(r4, r5, r3)
            return
        L_0x0038:
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            boolean r5 = r5.isSuccess()
            if (r5 == 0) goto L_0x004a
            r3 = -1
            java.lang.String r5 = "com.google.android.gms.wallet.EXTRA_MASKED_WALLET"
            r1.putExtra(r5, r4)
            goto L_0x0057
        L_0x004a:
            r4 = 408(0x198, float:5.72E-43)
            if (r3 != r4) goto L_0x0050
            r4 = 0
            goto L_0x0051
        L_0x0050:
            r4 = 1
        L_0x0051:
            java.lang.String r5 = "com.google.android.gms.wallet.EXTRA_ERROR_CODE"
            r1.putExtra(r5, r3)
            r3 = r4
        L_0x0057:
            int r4 = r2.zzaa
            r5 = 1073741824(0x40000000, float:2.0)
            android.app.PendingIntent r4 = r0.createPendingResult(r4, r1, r5)
            if (r4 != 0) goto L_0x0069
            java.lang.String r3 = "WalletClientImpl"
            java.lang.String r4 = "Null pending result returned for onMaskedWalletLoaded"
            android.util.Log.w(r3, r4)
            return
        L_0x0069:
            r4.send(r3)     // Catch:{ CanceledException -> 0x006d }
            return
        L_0x006d:
            r3 = move-exception
            java.lang.String r4 = "WalletClientImpl"
            java.lang.String r5 = "Exception setting pending result"
            android.util.Log.w(r4, r5, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.wallet.zzae.zza(int, com.google.android.gms.wallet.MaskedWallet, android.os.Bundle):void");
    }

    public final void zza(int i, boolean z, Bundle bundle) {
        Activity activity = (Activity) this.zzgi.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(WalletConstants.EXTRA_IS_USER_PREAUTHORIZED, z);
        PendingIntent createPendingResult = activity.createPendingResult(this.zzaa, intent, 1073741824);
        if (createPendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }
        try {
            createPendingResult.send(-1);
        } catch (PendingIntent.CanceledException e) {
            Log.w("WalletClientImpl", "Exception setting pending result", e);
        }
    }
}
