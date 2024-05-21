package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment implements ViewModelStoreOwner {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String HOLDER_TAG = "android.arch.lifecycle.state.StateProviderHolderFragment";
    private static final String LOG_TAG = "ViewModelStores";
    private static final HolderFragmentManager sHolderFragmentManager = new HolderFragmentManager();
    private ViewModelStore mViewModelStore = new ViewModelStore();

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        sHolderFragmentManager.holderFragmentCreated(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mViewModelStore.clear();
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        return this.mViewModelStore;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(FragmentActivity fragmentActivity) {
        return sHolderFragmentManager.holderFragmentFor(fragmentActivity);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(Fragment fragment) {
        return sHolderFragmentManager.holderFragmentFor(fragment);
    }

    static class HolderFragmentManager {
        private Application.ActivityLifecycleCallbacks mActivityCallbacks = new EmptyActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedActivityHolders.remove(activity)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + activity);
                }
            }
        };
        private boolean mActivityCallbacksIsAdded = false;
        /* access modifiers changed from: private */
        public Map<Activity, HolderFragment> mNotCommittedActivityHolders = new HashMap();
        /* access modifiers changed from: private */
        public Map<Fragment, HolderFragment> mNotCommittedFragmentHolders = new HashMap();
        private FragmentManager.FragmentLifecycleCallbacks mParentDestroyedCallback = new FragmentManager.FragmentLifecycleCallbacks() {
            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedFragmentHolders.remove(fragment)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + fragment);
                }
            }
        };

        HolderFragmentManager() {
        }

        /* access modifiers changed from: package-private */
        public void holderFragmentCreated(Fragment fragment) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                this.mNotCommittedFragmentHolders.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.mParentDestroyedCallback);
                return;
            }
            this.mNotCommittedActivityHolders.remove(fragment.getActivity());
        }

        private static HolderFragment findHolderFragment(FragmentManager fragmentManager) {
            if (!fragmentManager.isDestroyed()) {
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag(HolderFragment.HOLDER_TAG);
                if (findFragmentByTag == null || (findFragmentByTag instanceof HolderFragment)) {
                    return (HolderFragment) findFragmentByTag;
                }
                throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
            }
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
        }

        private static HolderFragment createHolderFragment(FragmentManager fragmentManager) {
            HolderFragment holderFragment = new HolderFragment();
            fragmentManager.beginTransaction().add((Fragment) holderFragment, HolderFragment.HOLDER_TAG).commitAllowingStateLoss();
            return holderFragment;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment holderFragmentFor(FragmentActivity fragmentActivity) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            HolderFragment findHolderFragment = findHolderFragment(supportFragmentManager);
            if (findHolderFragment != null) {
                return findHolderFragment;
            }
            HolderFragment holderFragment = this.mNotCommittedActivityHolders.get(fragmentActivity);
            if (holderFragment != null) {
                return holderFragment;
            }
            if (!this.mActivityCallbacksIsAdded) {
                this.mActivityCallbacksIsAdded = true;
                fragmentActivity.getApplication().registerActivityLifecycleCallbacks(this.mActivityCallbacks);
            }
            HolderFragment createHolderFragment = createHolderFragment(supportFragmentManager);
            this.mNotCommittedActivityHolders.put(fragmentActivity, createHolderFragment);
            return createHolderFragment;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment holderFragmentFor(Fragment fragment) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            HolderFragment findHolderFragment = findHolderFragment(childFragmentManager);
            if (findHolderFragment != null) {
                return findHolderFragment;
            }
            HolderFragment holderFragment = this.mNotCommittedFragmentHolders.get(fragment);
            if (holderFragment != null) {
                return holderFragment;
            }
            fragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.mParentDestroyedCallback, false);
            HolderFragment createHolderFragment = createHolderFragment(childFragmentManager);
            this.mNotCommittedFragmentHolders.put(fragment, createHolderFragment);
            return createHolderFragment;
        }
    }
}
