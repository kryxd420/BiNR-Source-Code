package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class zzak extends RemoteCreator<zzs> {
    private static zzak zzgl;

    protected zzak() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }

    public static zzl zza(Activity activity, IFragmentWrapper iFragmentWrapper, WalletFragmentOptions walletFragmentOptions, zzo zzo) throws GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        if (isGooglePlayServicesAvailable == 0) {
            try {
                if (zzgl == null) {
                    zzgl = new zzak();
                }
                return ((zzs) zzgl.getRemoteCreatorInstance(activity)).zza(ObjectWrapper.wrap(activity), iFragmentWrapper, walletFragmentOptions, zzo);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (RemoteCreator.RemoteCreatorException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        return queryLocalInterface instanceof zzs ? (zzs) queryLocalInterface : new zzt(iBinder);
    }
}
