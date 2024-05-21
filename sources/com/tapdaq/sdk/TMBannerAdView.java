package com.tapdaq.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.adnetworks.TDWaterfallService;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.Utils;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.listeners.TMListenerHandler;
import com.tapdaq.sdk.model.TMAdSize;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapdaq.sdk.tasks.TDTaskManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TMBannerAdView extends FrameLayout {
    private static final int REFRESH_RATE = 30000;
    /* access modifiers changed from: private */
    public BANNER_STATES STATE = BANNER_STATES.EMPTY;
    /* access modifiers changed from: private */
    public int mAdViewId;
    /* access modifiers changed from: private */
    public TMAdapter mAdapter;
    /* access modifiers changed from: private */
    public BannerAdListener mBannerListener;
    protected TDWaterfallService mWaterfallService = new TDWaterfallService();

    private enum BANNER_STATES {
        EMPTY,
        LOADED,
        DESTROYED
    }

    public TMBannerAdView(Context context) {
        super(context);
    }

    public TMBannerAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TMBannerAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean isReady() {
        return getAdView() != null;
    }

    /* access modifiers changed from: protected */
    public View getAdView() {
        return findViewById(this.mAdViewId);
    }

    public void load(Activity activity, TMAdSize tMAdSize, TMAdListener tMAdListener) {
        this.STATE = BANNER_STATES.EMPTY;
        this.mBannerListener = new BannerAdListener(activity, tMAdSize, tMAdListener);
        reload(activity, tMAdSize, TDMediationServiceProvider.getMediationService().getAdapters((Context) activity, tMAdSize), this.mBannerListener);
    }

    public void load(Activity activity, TMAdSize tMAdSize, TMAdapter tMAdapter, TMAdListener tMAdListener) {
        List list;
        this.STATE = BANNER_STATES.EMPTY;
        if (tMAdapter != null) {
            list = Arrays.asList(new TMAdapter[]{tMAdapter});
        } else {
            list = new ArrayList();
        }
        reload(activity, tMAdSize, list, tMAdListener);
    }

    /* access modifiers changed from: private */
    public void reload(final Activity activity, final TMAdSize tMAdSize, List<TMAdapter> list, final TMAdListener tMAdListener) {
        if (!list.isEmpty()) {
            this.mWaterfallService.request(activity, TapdaqPlacement.TDPTagDefault, 0, list, new TDWaterfallService.TDWaterfallCallback() {
                public void onSuccess(TDAdRequest tDAdRequest) {
                    tDAdRequest.setAdListener(tMAdListener);
                    if (tMAdListener instanceof BannerAdListener) {
                        ((BannerAdListener) tMAdListener).setAdRequest(tDAdRequest);
                    }
                    TMBannerAdView.this.load(activity, tMAdSize, tDAdRequest);
                }

                public void onError(TMAdError tMAdError) {
                    TMListenerHandler.DidFailToLoad(tMAdListener, tMAdError);
                }
            });
            return;
        }
        if (tMAdListener instanceof BannerAdListener) {
            tMAdListener = ((BannerAdListener) tMAdListener).getAdListener();
        }
        TMListenerHandler.DidFailToLoad(tMAdListener, new TMAdError(100, TapdaqError.NO_ADAPTERS_AVAILABLE_MESSAGE));
    }

    /* access modifiers changed from: private */
    public void load(final Activity activity, final TMAdSize tMAdSize, final TDAdRequest tDAdRequest) {
        TMAdListenerBase tMAdListenerBase;
        TDWaterfallItem nextNetwork = tDAdRequest.getNextNetwork();
        if (nextNetwork != null) {
            this.mAdapter = TDMediationServiceProvider.getMediationService().getAdapter(TMMediationNetworks.getID(nextNetwork.getNetwork()));
            TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                public void run() {
                    tDAdRequest.setAdapter(TMBannerAdView.this.mAdapter);
                    TMBannerAdView.this.load(activity, tMAdSize, TMBannerAdView.this.mAdapter, tDAdRequest);
                }
            });
            return;
        }
        if (tDAdRequest.getListener() instanceof BannerAdListener) {
            tMAdListenerBase = ((BannerAdListener) tDAdRequest.getListener()).getAdListener();
        } else {
            tMAdListenerBase = tDAdRequest.getListener();
        }
        TMListenerHandler.DidFailToLoad(tMAdListenerBase, new TMAdError(100, TapdaqError.NO_ADAPTERS_AVAILABLE_MESSAGE, tDAdRequest.getAdError().getSubErrors()));
    }

    /* access modifiers changed from: private */
    public void load(final Activity activity, final TMAdSize tMAdSize, TMAdapter tMAdapter, final TDAdRequest tDAdRequest) {
        this.mAdapter = tMAdapter;
        if (this.mAdapter != null) {
            TDMediationServiceProvider.getMediationService().getStatsManager().createAdRequest(tDAdRequest);
            TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                public void run() {
                    if (TMBannerAdView.this.mAdapter != null) {
                        TLog.info(String.format("Load Banner for: %s", new Object[]{TMBannerAdView.this.mAdapter.getName()}));
                        ViewGroup loadAd = TMBannerAdView.this.mAdapter.loadAd(activity, tMAdSize, tDAdRequest);
                        if (loadAd != null) {
                            TMBannerAdView.this.removeAllViews();
                            loadAd.setId(Utils.generateViewId());
                            int unused = TMBannerAdView.this.mAdViewId = loadAd.getId();
                            TMBannerAdView.this.addView(loadAd);
                            return;
                        }
                        return;
                    }
                    tDAdRequest.getListener().didFailToLoad(new TMAdError(1000, "Failed to load ad"));
                }
            });
        }
    }

    public void destroy(Context context) {
        this.STATE = BANNER_STATES.DESTROYED;
        TDTaskManager.getInstance().runOnMainThread(context, new Runnable() {
            public void run() {
                if (TMBannerAdView.this.mAdapter != null) {
                    TMBannerAdView.this.mAdapter.destroyBanner(TMBannerAdView.this.getAdView());
                }
                TMBannerAdView.this.removeAllViews();
                int unused = TMBannerAdView.this.mAdViewId = -1;
                if (TMBannerAdView.this.mBannerListener != null) {
                    TMBannerAdView.this.mBannerListener.destroy();
                }
                BannerAdListener unused2 = TMBannerAdView.this.mBannerListener = null;
            }
        });
    }

    private class BannerAdListener extends TMAdListener {
        /* access modifiers changed from: private */
        public Activity mActivity;
        private TMAdListener mAdListener;
        private TDAdRequest mAdRequest;
        private Handler mHandler = new Handler();
        /* access modifiers changed from: private */
        public TMAdSize mSize;

        BannerAdListener(Activity activity, TMAdSize tMAdSize, TMAdListener tMAdListener) {
            this.mActivity = activity;
            this.mSize = tMAdSize;
            this.mAdListener = tMAdListener;
        }

        /* access modifiers changed from: package-private */
        public void setAdRequest(TDAdRequest tDAdRequest) {
            this.mAdRequest = tDAdRequest;
        }

        /* access modifiers changed from: package-private */
        public TMAdListener getAdListener() {
            return this.mAdListener;
        }

        public void didLoad() {
            switch (TMBannerAdView.this.STATE) {
                case EMPTY:
                    TMListenerHandler.DidLoad(this.mAdListener);
                    break;
                case LOADED:
                    break;
                case DESTROYED:
                    destroy();
                    return;
                default:
                    return;
            }
            if (TMBannerAdView.this.STATE == BANNER_STATES.LOADED) {
                TMListenerHandler.DidRefresh(this.mAdListener);
            } else {
                BANNER_STATES unused = TMBannerAdView.this.STATE = BANNER_STATES.LOADED;
            }
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (TMBannerAdView.this.STATE != BANNER_STATES.DESTROYED) {
                        TMBannerAdView.this.reload(BannerAdListener.this.mActivity, BannerAdListener.this.mSize, TDMediationServiceProvider.getMediationService().getAdapters((Context) BannerAdListener.this.mActivity, BannerAdListener.this.mSize), BannerAdListener.this);
                    }
                }
            }, 30000);
        }

        public void didFailToLoad(TMAdError tMAdError) {
            if (TMBannerAdView.this.mAdapter != null) {
                TMBannerAdView.this.mAdapter.destroyBanner(TMBannerAdView.this.getAdView());
            }
            TMBannerAdView.this.load(this.mActivity, this.mSize, this.mAdRequest);
        }

        public void didClick() {
            TMListenerHandler.DidClick(this.mAdListener);
        }

        /* access modifiers changed from: package-private */
        public void destroy() {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler = null;
            this.mActivity = null;
            this.mAdListener = null;
        }
    }
}
