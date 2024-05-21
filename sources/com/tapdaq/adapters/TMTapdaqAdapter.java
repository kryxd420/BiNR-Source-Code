package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.tapdaq.adapters.tapdaq.TDAdService;
import com.tapdaq.adapters.tapdaq.TDResponseHandler;
import com.tapdaq.adapters.tapdaq.TMInterstitialActivity;
import com.tapdaq.adapters.tapdaq.TMVideoInterstitialActivity;
import com.tapdaq.adapters.tapdaq.ads.TDAdInterstitial;
import com.tapdaq.adapters.tapdaq.model.TMQueueID;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.model.config.TDNetwork;
import java.util.List;
import java.util.Locale;

public class TMTapdaqAdapter extends TMAdapter {
    private TDAdService mAdService;
    private List<TMQueueID> mQueueIds;

    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 11;
    }

    public String getSdkVersion() {
        return "7.0.1";
    }

    public void setQueueIds(List<TMQueueID> list) {
        this.mQueueIds = list;
    }

    public boolean hasRequiredActivities(Context context) {
        return TDActivityUtil.IsActivityAvailable(context, TMInterstitialActivity.class) && TDActivityUtil.IsActivityAvailable(context, TMVideoInterstitialActivity.class);
    }

    public void initialise(final Activity activity, TDNetwork tDNetwork) {
        super.initialise(activity, getNetwork());
        if (activity == null) {
            return;
        }
        if (this.mQueueIds == null || this.mQueueIds.isEmpty()) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    TMTapdaqAdapter.this.setState(activity, TDAdapterStatus.FAILED);
                }
            });
        } else {
            this.mAdService = new TDAdService(activity, this.mQueueIds, new TDResponseHandler() {
                public void onSuccess() {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            TMTapdaqAdapter.this.setState(activity, TDAdapterStatus.READY);
                        }
                    });
                }

                public void onError(TMAdError tMAdError) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            TMTapdaqAdapter.this.setState(activity, TDAdapterStatus.FAILED);
                        }
                    });
                }
            });
        }
    }

    public boolean isInitialised(Context context) {
        return getStatus() == TDAdapterStatus.READY && hasRequiredActivities(context) && this.mQueueIds != null && !this.mQueueIds.isEmpty() && this.mAdService != null;
    }

    public boolean canDisplayInterstitial(Context context) {
        return isInitialised(context);
    }

    public void loadInterstitial(Activity activity, final TDAdRequest tDAdRequest) {
        super.loadInterstitial(activity, tDAdRequest);
        if (this.mAdService != null) {
            storeAd(this.mAdService.loadInterstitial(activity, Tapdaq.getInstance().config(), tDAdRequest.getPlacement(), new TapdaqAdListener(activity, new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest))), tDAdRequest);
            return;
        }
        new Handler(activity.getMainLooper()).post(new Runnable() {
            public void run() {
                tDAdRequest.getListener().didFailToLoad(new TMAdError(TapdaqError.NO_AD_AVAILABLE, TapdaqError.NO_AD_AVAILABLE_MESSAGE));
            }
        });
    }

    public void showInterstitial(Activity activity, TDAdRequest tDAdRequest) {
        super.showInterstitial(activity, tDAdRequest);
        TapdaqAd tapdaqAd = new TapdaqAd(getStatsManager(), tDAdRequest, getName());
        if (isAdReady(activity, tDAdRequest)) {
            ((TDAdInterstitial) getAd(TDAdInterstitial.class, tDAdRequest)).show(activity, new TapdaqAdListener(activity, tapdaqAd));
            removeAd(tDAdRequest);
        }
    }

    private class TapdaqAdListener extends TMAdListener {
        private Activity mActivity;
        private TapdaqAd mAdvert;

        TapdaqAdListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        public void didLoad() {
            super.didLoad();
            TLog.debug(String.format(Locale.ENGLISH, "%s - didLoad", new Object[]{TMTapdaqAdapter.this.getName()}));
            TMTapdaqAdapter.this.getAdEventHandler().OnDidLoad(this.mAdvert);
        }

        public void didDisplay() {
            super.didDisplay();
            TLog.debug(String.format(Locale.ENGLISH, "%s - didDisplay", new Object[]{TMTapdaqAdapter.this.getName()}));
            TMTapdaqAdapter.this.getAdEventHandler().OnDidDisplay(this.mActivity, this.mAdvert);
        }

        public void didClick() {
            super.didClick();
            TLog.debug(String.format(Locale.ENGLISH, "%s - didClick", new Object[]{TMTapdaqAdapter.this.getName()}));
            TMTapdaqAdapter.this.getAdEventHandler().OnDidClick(this.mAdvert);
        }

        public void didClose() {
            super.didClose();
            TLog.debug(String.format(Locale.ENGLISH, "%s - didClose", new Object[]{TMTapdaqAdapter.this.getName()}));
            TMTapdaqAdapter.this.getAdEventHandler().OnDidClose(this.mActivity, this.mAdvert);
        }

        public void didFailToLoad(TMAdError tMAdError) {
            super.didFailToLoad(tMAdError);
            TLog.debug(String.format(Locale.ENGLISH, "%s - didFailToLoad", new Object[]{TMTapdaqAdapter.this.getName()}));
            TMTapdaqAdapter.this.getAdEventHandler().OnDidFailToLoad(this.mActivity, this.mAdvert, this.mAdvert.getAdRequest(), tMAdError);
        }
    }
}
