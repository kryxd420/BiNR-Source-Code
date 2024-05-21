package com.google.android.gms.wallet.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.FragmentWrapper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.internal.wallet.zzak;
import com.google.android.gms.internal.wallet.zzl;
import com.google.android.gms.internal.wallet.zzp;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.R;
import com.tapjoy.TJAdUnitConstants;

@TargetApi(12)
public final class WalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public boolean zzfc = false;
    /* access modifiers changed from: private */
    public WalletFragmentOptions zzfg;
    /* access modifiers changed from: private */
    public WalletFragmentInitParams zzfh;
    /* access modifiers changed from: private */
    public MaskedWalletRequest zzfi;
    /* access modifiers changed from: private */
    public MaskedWallet zzfj;
    /* access modifiers changed from: private */
    public Boolean zzfk;
    /* access modifiers changed from: private */
    public zzb zzfp;
    /* access modifiers changed from: private */
    public final FragmentWrapper zzfq = FragmentWrapper.wrap(this);
    private final zzc zzfr = new zzc();
    /* access modifiers changed from: private */
    public zza zzfs = new zza(this);
    /* access modifiers changed from: private */
    public final Fragment zzft = this;

    public interface OnStateChangedListener {
        void onStateChanged(WalletFragment walletFragment, int i, int i2, Bundle bundle);
    }

    static class zza extends zzp {
        private OnStateChangedListener zzfu;
        private final WalletFragment zzfv;

        zza(WalletFragment walletFragment) {
            this.zzfv = walletFragment;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            if (this.zzfu != null) {
                this.zzfu.onStateChanged(this.zzfv, i, i2, bundle);
            }
        }

        public final void zza(OnStateChangedListener onStateChangedListener) {
            this.zzfu = onStateChangedListener;
        }
    }

    private static class zzb implements LifecycleDelegate {
        private final zzl zzfn;

        private zzb(zzl zzl) {
            this.zzfn = zzl;
        }

        /* access modifiers changed from: private */
        public final int getState() {
            try {
                return this.zzfn.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.zzfn.initialize(walletFragmentInitParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void onActivityResult(int i, int i2, Intent intent) {
            try {
                this.zzfn.onActivityResult(i, i2, intent);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void setEnabled(boolean z) {
            try {
                this.zzfn.setEnabled(z);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.zzfn.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            try {
                this.zzfn.updateMaskedWalletRequest(maskedWalletRequest);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                this.zzfn.onCreate(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) ObjectWrapper.unwrap(this.zzfn.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onDestroy() {
        }

        public final void onDestroyView() {
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzfn.zza(ObjectWrapper.wrap(activity), (WalletFragmentOptions) bundle.getParcelable("extraWalletFragmentOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onLowMemory() {
        }

        public final void onPause() {
            try {
                this.zzfn.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzfn.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzfn.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzfn.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzfn.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class zzc extends DeferredLifecycleHelper<zzb> implements View.OnClickListener {
        private zzc() {
        }

        /* access modifiers changed from: protected */
        public final void createDelegate(OnDelegateCreatedListener<zzb> onDelegateCreatedListener) {
            Activity activity = WalletFragment.this.zzft.getActivity();
            if (WalletFragment.this.zzfp == null && WalletFragment.this.zzfc && activity != null) {
                try {
                    zzb unused = WalletFragment.this.zzfp = new zzb(zzak.zza(activity, WalletFragment.this.zzfq, WalletFragment.this.zzfg, WalletFragment.this.zzfs));
                    WalletFragmentOptions unused2 = WalletFragment.this.zzfg = null;
                    onDelegateCreatedListener.onDelegateCreated(WalletFragment.this.zzfp);
                    if (WalletFragment.this.zzfh != null) {
                        WalletFragment.this.zzfp.initialize(WalletFragment.this.zzfh);
                        WalletFragmentInitParams unused3 = WalletFragment.this.zzfh = null;
                    }
                    if (WalletFragment.this.zzfi != null) {
                        WalletFragment.this.zzfp.updateMaskedWalletRequest(WalletFragment.this.zzfi);
                        MaskedWalletRequest unused4 = WalletFragment.this.zzfi = null;
                    }
                    if (WalletFragment.this.zzfj != null) {
                        WalletFragment.this.zzfp.updateMaskedWallet(WalletFragment.this.zzfj);
                        MaskedWallet unused5 = WalletFragment.this.zzfj = null;
                    }
                    if (WalletFragment.this.zzfk != null) {
                        WalletFragment.this.zzfp.setEnabled(WalletFragment.this.zzfk.booleanValue());
                        Boolean unused6 = WalletFragment.this.zzfk = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused7) {
                }
            }
        }

        /* access modifiers changed from: protected */
        public final void handleGooglePlayUnavailable(FrameLayout frameLayout) {
            WalletFragmentStyle fragmentStyle;
            Button button = new Button(WalletFragment.this.zzft.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (!(WalletFragment.this.zzfg == null || (fragmentStyle = WalletFragment.this.zzfg.getFragmentStyle()) == null)) {
                DisplayMetrics displayMetrics = WalletFragment.this.zzft.getResources().getDisplayMetrics();
                i2 = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                i = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(i2, i));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        public final void onClick(View view) {
            Activity activity = WalletFragment.this.zzft.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), activity, -1);
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        walletFragment.zzft.setArguments(bundle);
        return walletFragment;
    }

    public final int getState() {
        if (this.zzfp != null) {
            return this.zzfp.getState();
        }
        return 0;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        if (this.zzfp != null) {
            this.zzfp.initialize(walletFragmentInitParams);
            this.zzfh = null;
        } else if (this.zzfh == null) {
            this.zzfh = walletFragmentInitParams;
            if (this.zzfi != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzfj != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.zzfp != null) {
            this.zzfp.onActivityResult(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        WalletFragmentOptions walletFragmentOptions;
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzfh != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.zzfh = walletFragmentInitParams;
            }
            if (this.zzfi == null) {
                this.zzfi = (MaskedWalletRequest) bundle.getParcelable("maskedWalletRequest");
            }
            if (this.zzfj == null) {
                this.zzfj = (MaskedWallet) bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.zzfg = (WalletFragmentOptions) bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey(TJAdUnitConstants.String.ENABLED)) {
                this.zzfk = Boolean.valueOf(bundle.getBoolean(TJAdUnitConstants.String.ENABLED));
            }
        } else if (!(this.zzft.getArguments() == null || (walletFragmentOptions = (WalletFragmentOptions) this.zzft.getArguments().getParcelable("extraWalletFragmentOptions")) == null)) {
            walletFragmentOptions.zza(this.zzft.getActivity());
            this.zzfg = walletFragmentOptions;
        }
        this.zzfc = true;
        this.zzfr.onCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzfr.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzfc = false;
    }

    public final void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        if (this.zzfg == null) {
            this.zzfg = WalletFragmentOptions.zza((Context) activity, attributeSet);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", this.zzfg);
        this.zzfr.onInflate(activity, bundle2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.zzfr.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.zzfr.onResume();
        FragmentManager fragmentManager = this.zzft.getActivity().getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            fragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzft.getActivity(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), this.zzft.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfr.onSaveInstanceState(bundle);
        if (this.zzfh != null) {
            bundle.putParcelable("walletFragmentInitParams", this.zzfh);
            this.zzfh = null;
        }
        if (this.zzfi != null) {
            bundle.putParcelable("maskedWalletRequest", this.zzfi);
            this.zzfi = null;
        }
        if (this.zzfj != null) {
            bundle.putParcelable("maskedWallet", this.zzfj);
            this.zzfj = null;
        }
        if (this.zzfg != null) {
            bundle.putParcelable("walletFragmentOptions", this.zzfg);
            this.zzfg = null;
        }
        if (this.zzfk != null) {
            bundle.putBoolean(TJAdUnitConstants.String.ENABLED, this.zzfk.booleanValue());
            this.zzfk = null;
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzfr.onStart();
    }

    public final void onStop() {
        super.onStop();
        this.zzfr.onStop();
    }

    public final void setEnabled(boolean z) {
        Boolean valueOf;
        if (this.zzfp != null) {
            this.zzfp.setEnabled(z);
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        this.zzfk = valueOf;
    }

    public final void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.zzfs.zza(onStateChangedListener);
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.zzfp != null) {
            this.zzfp.updateMaskedWallet(maskedWallet);
            this.zzfj = null;
            return;
        }
        this.zzfj = maskedWallet;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        if (this.zzfp != null) {
            this.zzfp.updateMaskedWalletRequest(maskedWalletRequest);
            this.zzfi = null;
            return;
        }
        this.zzfi = maskedWalletRequest;
    }
}
