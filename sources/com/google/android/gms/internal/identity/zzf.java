package com.google.android.gms.internal.identity;

import android.app.Activity;

public final class zzf extends zzh {
    private Activity mActivity;
    private final int zzj;

    public zzf(int i, Activity activity) {
        this.zzj = i;
        this.mActivity = activity;
    }

    /* access modifiers changed from: private */
    public final void setActivity(Activity activity) {
        this.mActivity = null;
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r4, android.os.Bundle r5) {
        /*
            r3 = this;
            r0 = 1073741824(0x40000000, float:2.0)
            r1 = 1
            if (r4 != r1) goto L_0x0025
            android.content.Intent r4 = new android.content.Intent
            r4.<init>()
            r4.putExtras(r5)
            android.app.Activity r5 = r3.mActivity
            int r2 = r3.zzj
            android.app.PendingIntent r4 = r5.createPendingResult(r2, r4, r0)
            if (r4 != 0) goto L_0x0018
            return
        L_0x0018:
            r4.send(r1)     // Catch:{ CanceledException -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            java.lang.String r5 = "AddressClientImpl"
            java.lang.String r0 = "Exception settng pending result"
        L_0x0021:
            android.util.Log.w(r5, r0, r4)
            return
        L_0x0025:
            r2 = 0
            if (r5 == 0) goto L_0x0031
            java.lang.String r2 = "com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT"
            android.os.Parcelable r5 = r5.getParcelable(r2)
            r2 = r5
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
        L_0x0031:
            com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
            r5.<init>(r4, r2)
            boolean r4 = r5.hasResolution()
            if (r4 == 0) goto L_0x004a
            android.app.Activity r4 = r3.mActivity     // Catch:{ SendIntentException -> 0x0044 }
            int r0 = r3.zzj     // Catch:{ SendIntentException -> 0x0044 }
            r5.startResolutionForResult(r4, r0)     // Catch:{ SendIntentException -> 0x0044 }
            return
        L_0x0044:
            r4 = move-exception
            java.lang.String r5 = "AddressClientImpl"
            java.lang.String r0 = "Exception starting pending intent"
            goto L_0x0021
        L_0x004a:
            android.app.Activity r4 = r3.mActivity     // Catch:{ CanceledException -> 0x005d }
            int r5 = r3.zzj     // Catch:{ CanceledException -> 0x005d }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ CanceledException -> 0x005d }
            r2.<init>()     // Catch:{ CanceledException -> 0x005d }
            android.app.PendingIntent r4 = r4.createPendingResult(r5, r2, r0)     // Catch:{ CanceledException -> 0x005d }
            if (r4 == 0) goto L_0x005c
            r4.send(r1)     // Catch:{ CanceledException -> 0x005d }
        L_0x005c:
            return
        L_0x005d:
            r4 = move-exception
            java.lang.String r5 = "AddressClientImpl"
            java.lang.String r0 = "Exception setting pending result"
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.identity.zzf.zza(int, android.os.Bundle):void");
    }
}
