package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDWaterfallService;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.listeners.TapdaqAdEventHandler;
import com.tapdaq.sdk.model.TMAdSize;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapdaq.sdk.model.waterfall.TMReward;
import com.tapdaq.sdk.tasks.TDTaskManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TMService {
    protected List<TDAdRequest> mAdRequests;
    private final TDConfigService mConfigService;
    private TMStatsManager mStatsManager;
    private TDWaterfallService mWaterfallService;

    public CredentialsListener getCredentialsListener() {
        return this.mConfigService;
    }

    TMService(TDConfigService tDConfigService, TDWaterfallService tDWaterfallService, TMStatsManager tMStatsManager) {
        this.mAdRequests = new ArrayList();
        this.mWaterfallService = tDWaterfallService;
        this.mConfigService = tDConfigService;
        this.mStatsManager = tMStatsManager;
    }

    public TMService(TMStatsManager tMStatsManager, TDConfigService tDConfigService) {
        this(tDConfigService, new TDWaterfallService(), tMStatsManager);
    }

    public TDConfigService getConfigService() {
        return this.mConfigService;
    }

    public TMStatsManager getStatsManager() {
        return this.mStatsManager;
    }

    /* access modifiers changed from: package-private */
    public void registerAdapter(TMAdapter tMAdapter) {
        if (tMAdapter == null) {
            TLog.error("Adapter is Null - Unable to Register");
        } else if (tMAdapter.isAdapterVersionCorrect()) {
            tMAdapter.setStatsManager(this.mStatsManager);
            this.mConfigService.register(tMAdapter);
        } else {
            TLog.error(String.format(Locale.ENGLISH, "Adapter Incorrect Version. Tapdaq SDK is %s %s Adapter is %s", new Object[]{Tapdaq.getSDKVersion(), tMAdapter.getName(), tMAdapter.getAdapterVersion()}));
        }
    }

    public TMAdapter getAdapter(int i) {
        for (TMAdapter next : this.mConfigService.getAllNetworks()) {
            if (next.getID() == i) {
                return next;
            }
        }
        return null;
    }

    public List<TMAdapter> getAllAdapters() {
        return this.mConfigService.getAllNetworks();
    }

    public List<TMAdapter> getAdapters(Context context, int i) {
        return this.mConfigService.getAdapters(context, i);
    }

    public List<TMAdapter> getAdapters(Context context, TMAdSize tMAdSize) {
        ArrayList arrayList = new ArrayList();
        for (TMAdapter next : this.mConfigService.getAdapters(context, 0)) {
            if (next.isBannerAvailable(context, tMAdSize)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public boolean canDisplayAdType(Context context, int i) {
        return this.mConfigService.getAdapters(context, i).size() > 0;
    }

    public boolean canDisplayBannerSize(Context context, TMAdSize tMAdSize) {
        List<TMAdapter> adapters = this.mConfigService.getAdapters(context, 0);
        if (adapters != null) {
            for (TMAdapter isBannerAvailable : adapters) {
                if (isBannerAvailable.isBannerAvailable(context, tMAdSize)) {
                    return true;
                }
            }
        }
        return false;
    }

    private TDAdRequest getAdRequest(Activity activity, int i, String str) {
        for (TDAdRequest tDAdRequest : new ArrayList(this.mAdRequests)) {
            if (tDAdRequest.getType() == i && tDAdRequest.getPlacement().equalsIgnoreCase(str) && tDAdRequest.getAdapter() != null) {
                if (activity == null || tDAdRequest.getAdapter().isAdReady(activity, tDAdRequest)) {
                    return tDAdRequest;
                }
            }
        }
        return null;
    }

    public TMReward getReward(Activity activity, String str) {
        TDAdRequest adRequest = getAdRequest(activity, 3, str);
        if (adRequest != null) {
            return adRequest.getReward();
        }
        return null;
    }

    public boolean isReady(Activity activity, int i, String str) {
        TDAdRequest adRequest = getAdRequest(activity, i, str);
        return (adRequest == null || adRequest.getAdapter() == null || !adRequest.getAdapter().isAdReady(activity, adRequest)) ? false : true;
    }

    public void loadAd(Activity activity, int i, String str, TMAdListenerBase tMAdListenerBase) {
        loadAd(activity, i, str, (Object) null, tMAdListenerBase);
    }

    public void loadAd(Activity activity, int i, String str, Object obj, TMAdListenerBase tMAdListenerBase) {
        performWaterfall(activity, str, i, obj, this.mConfigService.getAdapters(activity, i), tMAdListenerBase);
    }

    private void performWaterfall(final Activity activity, String str, int i, final Object obj, List<TMAdapter> list, final TMAdListenerBase tMAdListenerBase) {
        this.mWaterfallService.request(activity, str, i, list, new TDWaterfallService.TDWaterfallCallback() {
            public void onSuccess(TDAdRequest tDAdRequest) {
                tDAdRequest.setAdListener(tMAdListenerBase);
                tDAdRequest.setOptions(obj);
                TMService.this.mAdRequests.add(tDAdRequest);
                TMService.this.loadAd(activity, tDAdRequest);
            }

            public void onError(final TMAdError tMAdError) {
                TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                    public void run() {
                        TMListenerHandler.DidFailToLoad(tMAdListenerBase, tMAdError);
                    }
                });
            }
        });
    }

    public void loadAd(Activity activity, TDAdRequest tDAdRequest) {
        TDWaterfallItem nextNetwork = tDAdRequest.getNextNetwork();
        if (nextNetwork != null) {
            TMAdapter adapter = getAdapter(TMMediationNetworks.getID(nextNetwork.getNetwork()));
            if (adapter != null) {
                TLog.debug(String.format(Locale.ENGLISH, "Load %s Ad With: %s", new Object[]{TMAdType.getString(tDAdRequest.getType()), adapter.getName()}));
                tDAdRequest.setAdapter(adapter);
                this.mStatsManager.createAdRequest(tDAdRequest);
                if (!adapter.isSuspended(activity, tDAdRequest)) {
                    adapter.loadAd(activity, tDAdRequest);
                    return;
                }
                TMAdError tMAdError = new TMAdError(1012, TapdaqError.ADAPTER_ADTYPE_SUSPENDED_MESSAGE);
                new TapdaqAdEventHandler(this.mStatsManager).OnDidFailToLoad(activity, new TapdaqAd(this.mStatsManager, tDAdRequest, adapter.getName()), tDAdRequest, tMAdError);
                return;
            }
            TMListenerHandler.DidFailToLoad(tDAdRequest.getListener(), new TMAdError(TapdaqError.NETWORKS_FAILED_TO_LOAD_AD, TapdaqError.NETWORKS_FAILED_TO_LOAD_AD_MESSAGE, tDAdRequest.getAdError().getSubErrors()));
            this.mAdRequests.remove(tDAdRequest);
            return;
        }
        TMListenerHandler.DidFailToLoad(tDAdRequest.getListener(), new TMAdError(100, TapdaqError.NO_ADAPTERS_AVAILABLE_MESSAGE, tDAdRequest.getAdError().getSubErrors()));
        this.mAdRequests.remove(tDAdRequest);
    }

    public void showAd(Activity activity, int i, String str, String str2, TMAdListenerBase tMAdListenerBase) {
        final TDAdRequest adRequest = getAdRequest(activity, i, str);
        if (adRequest != null) {
            final TMAdapter adapter = adRequest.getAdapter();
            this.mAdRequests.remove(adRequest);
            adRequest.setAdListener(tMAdListenerBase);
            if (adapter != null) {
                TLog.debug(String.format("Show %s for: %s", new Object[]{TMAdType.getString(i), adapter.getName()}));
                final int i2 = i;
                final String str3 = str2;
                final Activity activity2 = activity;
                TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                    public void run() {
                        if (i2 == 3) {
                            adRequest.setHashedUserId(str3);
                        }
                        adapter.showAd(activity2, adRequest);
                    }
                });
                return;
            }
        }
        TMListenerHandler.DidFailToDisplay(tMAdListenerBase, new TMAdError(200, TapdaqError.NO_AD_LOADED_FOR_PLACEMENT_MESSAGE));
    }

    public void refillPlacements(Activity activity) {
        if (activity != null) {
            for (TDAdRequest tDAdRequest : new ArrayList(this.mAdRequests)) {
                if (tDAdRequest.getAdapter() == null || !tDAdRequest.getAdapter().isAdReady(activity, tDAdRequest)) {
                    loadAd(activity, tDAdRequest.getType(), tDAdRequest.getPlacement(), (TMAdListenerBase) null);
                    this.mAdRequests.remove(tDAdRequest);
                }
            }
            return;
        }
        TLog.debug("Activity null - unable to refill");
    }
}
