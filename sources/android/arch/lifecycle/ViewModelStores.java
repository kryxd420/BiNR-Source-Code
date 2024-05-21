package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelStores {
    private ViewModelStores() {
    }

    @MainThread
    @NonNull
    public static ViewModelStore of(@NonNull FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof ViewModelStoreOwner) {
            return ((ViewModelStoreOwner) fragmentActivity).getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragmentActivity).getViewModelStore();
    }

    @MainThread
    @NonNull
    public static ViewModelStore of(@NonNull Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return ((ViewModelStoreOwner) fragment).getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
