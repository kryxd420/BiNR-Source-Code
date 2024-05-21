package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;

public class zzbz {
    private static final String TAG = "zzbz";
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzcj;
    private static zze zzck;

    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.maps.internal.zze zza(android.content.Context r3) throws com.google.android.gms.common.GooglePlayServicesNotAvailableException {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            com.google.android.gms.maps.internal.zze r0 = zzck
            if (r0 == 0) goto L_0x000a
            com.google.android.gms.maps.internal.zze r3 = zzck
            return r3
        L_0x000a:
            r0 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r0 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r3, r0)
            if (r0 != 0) goto L_0x0061
            java.lang.String r0 = TAG
            java.lang.String r1 = "Making Creator dynamically"
            android.util.Log.i(r0, r1)
            android.content.Context r0 = zzb(r3)
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.String r1 = "com.google.android.gms.maps.internal.CreatorImpl"
            java.lang.Object r0 = zza(r0, r1)
            android.os.IBinder r0 = (android.os.IBinder) r0
            if (r0 != 0) goto L_0x002e
            r0 = 0
            goto L_0x0042
        L_0x002e:
            java.lang.String r1 = "com.google.android.gms.maps.internal.ICreator"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.zze
            if (r2 == 0) goto L_0x003c
            r0 = r1
            com.google.android.gms.maps.internal.zze r0 = (com.google.android.gms.maps.internal.zze) r0
            goto L_0x0042
        L_0x003c:
            com.google.android.gms.maps.internal.zzf r1 = new com.google.android.gms.maps.internal.zzf
            r1.<init>(r0)
            r0 = r1
        L_0x0042:
            zzck = r0
            com.google.android.gms.maps.internal.zze r0 = zzck     // Catch:{ RemoteException -> 0x005a }
            android.content.Context r3 = zzb(r3)     // Catch:{ RemoteException -> 0x005a }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ RemoteException -> 0x005a }
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r3)     // Catch:{ RemoteException -> 0x005a }
            int r1 = com.google.android.gms.common.GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE     // Catch:{ RemoteException -> 0x005a }
            r0.zza((com.google.android.gms.dynamic.IObjectWrapper) r3, (int) r1)     // Catch:{ RemoteException -> 0x005a }
            com.google.android.gms.maps.internal.zze r3 = zzck
            return r3
        L_0x005a:
            r3 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r0 = new com.google.android.gms.maps.model.RuntimeRemoteException
            r0.<init>(r3)
            throw r0
        L_0x0061:
            com.google.android.gms.common.GooglePlayServicesNotAvailableException r3 = new com.google.android.gms.common.GooglePlayServicesNotAvailableException
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzbz.zza(android.content.Context):com.google.android.gms.maps.internal.zze");
    }

    private static <T> T zza(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException unused) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to instantiate the dynamic class ".concat(valueOf) : new String("Unable to instantiate the dynamic class "));
        } catch (IllegalAccessException unused2) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf2.length() != 0 ? "Unable to call the default constructor of ".concat(valueOf2) : new String("Unable to call the default constructor of "));
        }
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return zza(((ClassLoader) Preconditions.checkNotNull(classLoader)).loadClass(str));
        } catch (ClassNotFoundException unused) {
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to find dynamic class ".concat(valueOf) : new String("Unable to find dynamic class "));
        }
    }

    @Nullable
    private static Context zzb(Context context) {
        if (zzcj != null) {
            return zzcj;
        }
        Context zzc = zzc(context);
        zzcj = zzc;
        return zzc;
    }

    @Nullable
    private static Context zzc(Context context) {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to load maps module, use legacy", th);
            return GooglePlayServicesUtil.getRemoteContext(context);
        }
    }
}
