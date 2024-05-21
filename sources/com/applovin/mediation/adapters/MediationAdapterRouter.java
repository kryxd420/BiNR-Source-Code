package com.applovin.mediation.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class MediationAdapterRouter {
    private final Map<String, List<MediationAdapterRouterListenerWrapper>> listeners = new HashMap();
    private final Object listenersLock = new Object();
    private final Set<MaxAdapter> loadedAdapters = new HashSet();
    private final Object loadedAdaptersLock = new Object();
    protected MaxAdapter.OnCompletionListener mOnCompletionListener;
    protected final String mTag = getClass().getSimpleName();
    private final Set<MaxAdapter> showingAdapters = new HashSet();
    private final Object showingAdaptersLock = new Object();

    private final class MediationAdapterRouterListenerWrapper {
        private View mAdView;
        private final MaxAdapter mAdapter;
        private final MaxAdapterListener mListener;
        private final RouterAdLoadType mLoadType;

        MediationAdapterRouterListenerWrapper(MaxAdapter maxAdapter, MaxAdapterListener maxAdapterListener, RouterAdLoadType routerAdLoadType, View view) {
            this.mAdapter = maxAdapter;
            this.mListener = maxAdapterListener;
            this.mLoadType = routerAdLoadType;
            this.mAdView = view;
        }

        /* access modifiers changed from: package-private */
        public View getAdView() {
            return this.mAdView;
        }

        /* access modifiers changed from: package-private */
        public MaxAdapter getAdapter() {
            return this.mAdapter;
        }

        /* access modifiers changed from: package-private */
        public MaxAdapterListener getListener() {
            return this.mListener;
        }

        /* access modifiers changed from: package-private */
        public RouterAdLoadType getLoadType() {
            return this.mLoadType;
        }

        /* access modifiers changed from: package-private */
        public void setAdView(View view) {
            this.mAdView = view;
        }
    }

    private enum RouterAdLoadType {
        INTERSTITIAL,
        REWARDED,
        ADVIEW
    }

    protected MediationAdapterRouter() {
    }

    private void addAdapter(MaxAdapter maxAdapter, MaxAdapterListener maxAdapterListener, String str, RouterAdLoadType routerAdLoadType, View view) {
        synchronized (this.listenersLock) {
            MediationAdapterRouterListenerWrapper mediationAdapterRouterListenerWrapper = new MediationAdapterRouterListenerWrapper(maxAdapter, maxAdapterListener, routerAdLoadType, view);
            List arrayList = this.listeners.get(str) != null ? this.listeners.get(str) : new ArrayList(1);
            arrayList.add(mediationAdapterRouterListenerWrapper);
            this.listeners.put(str, arrayList);
        }
    }

    private void addLoadedAdapter(MaxAdapter maxAdapter) {
        synchronized (this.loadedAdaptersLock) {
            this.loadedAdapters.add(maxAdapter);
        }
    }

    private List<MediationAdapterRouterListenerWrapper> getListenerWrappers(String str) {
        if (this.listeners.containsKey(str)) {
            return new ArrayList(this.listeners.get(str));
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.applovin.mediation.adapters.MediationAdapterRouter.MediationAdapterRouterListenerWrapper> getLoadingListenerWrappers(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r4.listenersLock
            monitor-enter(r1)
            java.util.List r5 = r4.getListenerWrappers(r5)     // Catch:{ all -> 0x0037 }
            if (r5 == 0) goto L_0x0034
            int r2 = r5.size()     // Catch:{ all -> 0x0037 }
            if (r2 <= 0) goto L_0x0034
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0037 }
        L_0x0018:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0037 }
            com.applovin.mediation.adapters.MediationAdapterRouter$MediationAdapterRouterListenerWrapper r2 = (com.applovin.mediation.adapters.MediationAdapterRouter.MediationAdapterRouterListenerWrapper) r2     // Catch:{ all -> 0x0037 }
            com.applovin.mediation.adapter.MaxAdapter r3 = r2.getAdapter()     // Catch:{ all -> 0x0037 }
            boolean r3 = r4.isAdLoaded(r3)     // Catch:{ all -> 0x0037 }
            if (r3 != 0) goto L_0x0018
            r0.add(r2)     // Catch:{ all -> 0x0037 }
            goto L_0x0018
        L_0x0032:
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r0
        L_0x0034:
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            r5 = 0
            return r5
        L_0x0037:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.mediation.adapters.MediationAdapterRouter.getLoadingListenerWrappers(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.applovin.mediation.adapters.MediationAdapterRouter.MediationAdapterRouterListenerWrapper> getShowingListenerWrappers(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r4.listenersLock
            monitor-enter(r1)
            java.util.List r5 = r4.getListenerWrappers(r5)     // Catch:{ all -> 0x0037 }
            if (r5 == 0) goto L_0x0034
            int r2 = r5.size()     // Catch:{ all -> 0x0037 }
            if (r2 <= 0) goto L_0x0034
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0037 }
        L_0x0018:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0037 }
            com.applovin.mediation.adapters.MediationAdapterRouter$MediationAdapterRouterListenerWrapper r2 = (com.applovin.mediation.adapters.MediationAdapterRouter.MediationAdapterRouterListenerWrapper) r2     // Catch:{ all -> 0x0037 }
            com.applovin.mediation.adapter.MaxAdapter r3 = r2.getAdapter()     // Catch:{ all -> 0x0037 }
            boolean r3 = r4.isAdShowing(r3)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0018
            r0.add(r2)     // Catch:{ all -> 0x0037 }
            goto L_0x0018
        L_0x0032:
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r0
        L_0x0034:
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            r5 = 0
            return r5
        L_0x0037:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.mediation.adapters.MediationAdapterRouter.getShowingListenerWrappers(java.lang.String):java.util.List");
    }

    private boolean isAdLoaded(MaxAdapter maxAdapter) {
        boolean contains;
        synchronized (this.loadedAdaptersLock) {
            contains = this.loadedAdapters.contains(maxAdapter);
        }
        return contains;
    }

    private boolean isAdShowing(MaxAdapter maxAdapter) {
        boolean contains;
        synchronized (this.showingAdaptersLock) {
            contains = this.showingAdapters.contains(maxAdapter);
        }
        return contains;
    }

    private void removeLoadedAdapter(MaxAdapter maxAdapter) {
        synchronized (this.loadedAdaptersLock) {
            this.loadedAdapters.remove(maxAdapter);
        }
    }

    private void removeShowingAdapter(MaxAdapter maxAdapter) {
        synchronized (this.showingAdaptersLock) {
            this.showingAdapters.remove(maxAdapter);
        }
    }

    public void addAdViewAdapter(MaxAdapter maxAdapter, MaxAdViewAdapterListener maxAdViewAdapterListener, String str, View view) {
        addAdapter(maxAdapter, maxAdViewAdapterListener, str, RouterAdLoadType.ADVIEW, view);
    }

    public void addInterstitialAdapter(MaxAdapter maxAdapter, MaxInterstitialAdapterListener maxInterstitialAdapterListener, String str) {
        addAdapter(maxAdapter, maxInterstitialAdapterListener, str, RouterAdLoadType.INTERSTITIAL, (View) null);
    }

    public void addRewardedAdapter(MaxAdapter maxAdapter, MaxRewardedAdapterListener maxRewardedAdapterListener, String str) {
        addAdapter(maxAdapter, maxRewardedAdapterListener, str, RouterAdLoadType.REWARDED, (View) null);
    }

    public void addShowingAdapter(MaxAdapter maxAdapter) {
        synchronized (this.showingAdaptersLock) {
            this.showingAdapters.add(maxAdapter);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void initialize(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity, MaxAdapter.OnCompletionListener onCompletionListener);

    /* access modifiers changed from: protected */
    public void log(String str) {
        Log.i(this.mTag, str);
    }

    /* access modifiers changed from: protected */
    public void log(String str, Throwable th) {
        Log.i(this.mTag, str, th);
    }

    /* access modifiers changed from: protected */
    public void onAdClicked(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial clicked");
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdClicked();
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded clicked");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdClicked();
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView clicked");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdClicked();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdDisplayFailed(String str, MaxAdapterError maxAdapterError) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial failed to display with error: " + maxAdapterError.toString());
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdDisplayFailed(maxAdapterError);
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded failed to display with error: " + maxAdapterError.toString());
                    ((MaxRewardedAdapterListener) listener).onRewardedAdDisplayFailed(maxAdapterError);
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView failed to display with error: " + maxAdapterError.toString());
                    ((MaxAdViewAdapterListener) listener).onAdViewAdDisplayFailed(maxAdapterError);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdDisplayed(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial shown");
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdDisplayed();
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded shown");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdDisplayed();
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView shown");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdDisplayed();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdHidden(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial hidden");
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdHidden();
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded hidden");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdHidden();
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView hidden");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdHidden();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdLoadFailed(String str, MaxAdapterError maxAdapterError) {
        List<MediationAdapterRouterListenerWrapper> loadingListenerWrappers = getLoadingListenerWrappers(str);
        if (loadingListenerWrappers != null && loadingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : loadingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial failed to load with error: " + maxAdapterError.toString());
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdLoadFailed(maxAdapterError);
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded failed to load with error: " + maxAdapterError.toString());
                    ((MaxRewardedAdapterListener) listener).onRewardedAdLoadFailed(maxAdapterError);
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView failed to load with error: " + maxAdapterError.toString());
                    ((MaxAdViewAdapterListener) listener).onAdViewAdLoadFailed(maxAdapterError);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdLoaded(String str) {
        List<MediationAdapterRouterListenerWrapper> loadingListenerWrappers = getLoadingListenerWrappers(str);
        if (loadingListenerWrappers != null && loadingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : loadingListenerWrappers) {
                addLoadedAdapter(next.getAdapter());
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.INTERSTITIAL) {
                    log("Interstitial loaded");
                    ((MaxInterstitialAdapterListener) listener).onInterstitialAdLoaded();
                } else if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded loaded");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdLoaded();
                } else if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView loaded");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdLoaded(next.getAdView());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdViewAdCollapsed(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView collapsed");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdCollapsed();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAdViewAdExpanded(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.ADVIEW) {
                    log("AdView expanded");
                    ((MaxAdViewAdapterListener) listener).onAdViewAdExpanded();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRewardedAdVideoCompleted(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded video completed");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdVideoCompleted();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRewardedAdVideoStarted(String str) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded video started");
                    ((MaxRewardedAdapterListener) listener).onRewardedAdVideoStarted();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onUserRewarded(String str, MaxReward maxReward) {
        List<MediationAdapterRouterListenerWrapper> showingListenerWrappers = getShowingListenerWrappers(str);
        if (showingListenerWrappers != null && showingListenerWrappers.size() > 0) {
            for (MediationAdapterRouterListenerWrapper next : showingListenerWrappers) {
                RouterAdLoadType loadType = next.getLoadType();
                MaxAdapterListener listener = next.getListener();
                if (loadType == RouterAdLoadType.REWARDED) {
                    log("Rewarded user with reward: " + maxReward);
                    ((MaxRewardedAdapterListener) listener).onUserRewarded(maxReward);
                }
            }
        }
    }

    public void removeAdapter(MaxAdapter maxAdapter, String str) {
        removeLoadedAdapter(maxAdapter);
        removeShowingAdapter(maxAdapter);
        synchronized (this.listenersLock) {
            List list = this.listeners.get(str);
            if (list != null && list.size() > 0) {
                MediationAdapterRouterListenerWrapper mediationAdapterRouterListenerWrapper = null;
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediationAdapterRouterListenerWrapper mediationAdapterRouterListenerWrapper2 = (MediationAdapterRouterListenerWrapper) it.next();
                    if (mediationAdapterRouterListenerWrapper2.getAdapter() == maxAdapter) {
                        mediationAdapterRouterListenerWrapper = mediationAdapterRouterListenerWrapper2;
                        break;
                    }
                }
                if (mediationAdapterRouterListenerWrapper != null) {
                    list.remove(mediationAdapterRouterListenerWrapper);
                }
            }
        }
    }

    public void updateAdView(View view, String str) {
        synchronized (this.listenersLock) {
            List<MediationAdapterRouterListenerWrapper> listenerWrappers = getListenerWrappers(str);
            if (listenerWrappers != null && listenerWrappers.size() > 0) {
                Iterator<MediationAdapterRouterListenerWrapper> it = listenerWrappers.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediationAdapterRouterListenerWrapper next = it.next();
                    if (next.getAdView() == null) {
                        next.setAdView(view);
                        break;
                    }
                }
            }
        }
    }
}
