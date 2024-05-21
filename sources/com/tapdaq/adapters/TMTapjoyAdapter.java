package com.tapdaq.adapters;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TapdaqAd;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDActivityUtil;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapdaq.sdk.model.waterfall.TDWaterfallBiddingItem;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJAdUnitActivity;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyAuctionFlags;
import com.tapjoy.mraid.view.ActionHandler;
import com.tapjoy.mraid.view.Browser;
import java.util.HashMap;
import java.util.Hashtable;
import org.json.JSONObject;

public class TMTapjoyAdapter extends TMAdapter {
    private static final String TAPDAQ_ADAPTER_VERSION_NUMBER = "1.0.0";
    private static final String TAPDAQ_NETWORK_NAME = "tapdaq";

    public String getAdapterVersion() {
        return "7.0.1";
    }

    public int getID() {
        return 12;
    }

    public TMTapjoyAdapter setDebuggingEnabled(boolean z) {
        Tapjoy.setDebugEnabled(z);
        return this;
    }

    public String getSdkVersion() {
        return Tapjoy.getVersion();
    }

    public void initialise(final Activity activity, TDNetwork tDNetwork) {
        super.initialise(activity, tDNetwork);
        if (isInitialised(activity)) {
            if (activity != null) {
                setState(activity, TDAdapterStatus.READY);
            }
        } else if (isWaitingState()) {
            Tapjoy.setDebugEnabled(TLog.isDebugEnabled());
            setConsent(activity, Tapdaq.getInstance().isConsentGiven(activity));
            Tapjoy.connect(activity, getAppKey(), (Hashtable) null, new TJConnectListener() {
                public void onConnectSuccess() {
                    TMTapjoyAdapter.this.setState(activity, TDAdapterStatus.READY);
                }

                public void onConnectFailure() {
                    TMTapjoyAdapter.this.setState(activity, TDAdapterStatus.FAILED);
                }
            });
            Tapjoy.setActivity(activity);
        } else {
            TLog.error("Failed to connect to Tapjoy: Activity is null");
            setState(activity, TDAdapterStatus.FAILED);
        }
    }

    public void setUserSubjectToGDPR(Context context, STATUS status) {
        super.setUserSubjectToGDPR(context, status);
        if (status == STATUS.TRUE) {
            Tapjoy.subjectToGDPR(true);
        } else if (status == STATUS.FALSE) {
            Tapjoy.subjectToGDPR(false);
        }
    }

    public void setConsent(Context context, boolean z) {
        super.setConsent(context, z);
        if (Tapdaq.getInstance().config().isUserSubjectToGDPR() == STATUS.TRUE) {
            Tapjoy.subjectToGDPR(true);
            if (Tapdaq.getInstance().config().getConsenStatus() != STATUS.UNKNOWN) {
                Tapjoy.setUserConsent(z ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0");
            }
        } else if (Tapdaq.getInstance().config().isUserSubjectToGDPR() == STATUS.FALSE) {
            Tapjoy.subjectToGDPR(false);
        }
    }

    public boolean isInitialised(Context context) {
        return (Tapjoy.isConnected() && hasCredentials(context) && hasRequiredActivities(context)) || super.isInitialised(context);
    }

    public boolean hasCredentials(Context context) {
        return getAppKey() != null;
    }

    public boolean hasRequiredActivities(Context context) {
        return TDActivityUtil.IsActivityAvailable(context, TJAdUnitActivity.class) && TDActivityUtil.IsActivityAvailable(context, ActionHandler.class) && TDActivityUtil.IsActivityAvailable(context, Browser.class) && TDActivityUtil.IsActivityAvailable(context, TJContentActivity.class);
    }

    public boolean canDisplayInterstitial(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayVideo(Context context) {
        return isInitialised(context);
    }

    public boolean canDisplayRewardedVideo(Context context) {
        return isInitialised(context);
    }

    public boolean isAdReady(Activity activity, TDAdRequest tDAdRequest) {
        return super.isAdReady(activity, tDAdRequest) && ((TJPlacement) getAd(TJPlacement.class, tDAdRequest)).isContentReady();
    }

    public void loadAd(Activity activity, TDAdRequest tDAdRequest) {
        Tapjoy.setActivity(activity);
        TJPlacement placement = Tapjoy.getPlacement(tDAdRequest.getAdUnitId(), new TapjoyListener(activity, new TapdaqAd(getStatsManager(), tDAdRequest, getName()).withTimeout(activity, tDAdRequest)));
        placement.setMediationName("tapdaq");
        placement.setAdapterVersion(TAPDAQ_ADAPTER_VERSION_NUMBER);
        TDWaterfallItem waterfallItem = tDAdRequest.getWaterfallItem();
        if ((waterfallItem instanceof TDWaterfallBiddingItem) && ((TDWaterfallBiddingItem) waterfallItem).getAuctionData() != null) {
            try {
                HashMap auctionData = ((TDWaterfallBiddingItem) waterfallItem).getAuctionData();
                HashMap hashMap = new HashMap();
                JSONObject jSONObject = new JSONObject((String) auctionData.get("ad_id"));
                hashMap.put("id", (String) jSONObject.get("id"));
                hashMap.put(TapjoyAuctionFlags.AUCTION_DATA, (String) jSONObject.get(TapjoyAuctionFlags.AUCTION_DATA));
                placement.setAuctionData(hashMap);
            } catch (Exception e) {
                TLog.error(e);
            }
        }
        placement.requestContent();
    }

    public void showAd(Activity activity, TDAdRequest tDAdRequest) {
        Tapjoy.setActivity(activity);
        TJPlacement tJPlacement = (TJPlacement) getAd(TJPlacement.class, tDAdRequest);
        if (tJPlacement != null) {
            TJPlacementListener listener = tJPlacement.getListener();
            if (listener instanceof TapjoyListener) {
                TapjoyListener tapjoyListener = (TapjoyListener) listener;
                tapjoyListener.setListener(tDAdRequest.getListener());
                tapjoyListener.setActivity(activity);
                tJPlacement.setVideoListener(tapjoyListener);
            }
            tJPlacement.showContent();
            return;
        }
        getAdEventHandler().OnDidFailToDisplay(new TapdaqAd(getStatsManager(), tDAdRequest, getName()), new TMAdError(1020, TapdaqError.ADAPTER_NO_AD_LOADED_MESSAGE));
    }

    private class TapjoyListener implements TJPlacementListener, TJPlacementVideoListener {
        private Activity mActivity;
        /* access modifiers changed from: private */
        public TapdaqAd mAdvert;
        /* access modifiers changed from: private */
        public boolean mHasRewardedUser = false;

        TapjoyListener(Activity activity, TapdaqAd tapdaqAd) {
            this.mActivity = activity;
            this.mAdvert = tapdaqAd;
        }

        /* access modifiers changed from: package-private */
        public void setListener(TMAdListenerBase tMAdListenerBase) {
            this.mAdvert.setAdListener(tMAdListenerBase);
        }

        /* access modifiers changed from: package-private */
        public void setActivity(Activity activity) {
            this.mActivity = activity;
        }

        public void onRequestSuccess(TJPlacement tJPlacement) {
            TLog.debug("onRequestSuccess");
            if (!tJPlacement.isContentAvailable()) {
                final Activity activity = this.mActivity;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        TMTapjoyAdapter.this.getAdEventHandler().OnDidFailToLoad(activity, TapjoyListener.this.mAdvert, TapjoyListener.this.mAdvert.getAdRequest(), new TMAdError(TapdaqError.TAPJOY_NO_CONTENT, TapdaqError.TAPJOY_NO_CONTENT_MESSAGE));
                    }
                });
                this.mActivity = null;
            }
        }

        public void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
            TLog.debug("onRequestFailure");
            TMTapjoyAdapter.this.getAdEventHandler().OnDidFailToLoad(this.mActivity, this.mAdvert, this.mAdvert.getAdRequest(), new TMAdError(tJError.code, tJError.message));
        }

        public void onContentReady(TJPlacement tJPlacement) {
            TLog.debug("onContentReady");
            TMTapjoyAdapter.this.storeAd(tJPlacement, this.mAdvert.getAdRequest());
            if (this.mActivity != null) {
                this.mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        TMTapjoyAdapter.this.getAdEventHandler().OnDidLoad(TapjoyListener.this.mAdvert);
                    }
                });
            }
            this.mActivity = null;
        }

        public void onContentShow(TJPlacement tJPlacement) {
            TLog.debug("onContentShow");
            if (this.mActivity != null) {
                final Activity activity = this.mActivity;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        TMTapjoyAdapter.this.getAdEventHandler().OnDidDisplay(activity, TapjoyListener.this.mAdvert);
                    }
                });
            }
        }

        public void onContentDismiss(TJPlacement tJPlacement) {
            TLog.debug("onContentDismiss");
            if (this.mActivity != null) {
                final Activity activity = this.mActivity;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        if (TapjoyListener.this.mAdvert.getType() == 3 && !TapjoyListener.this.mHasRewardedUser) {
                            TMTapjoyAdapter.this.getAdEventHandler().OnRewardVerified(activity, TapjoyListener.this.mAdvert, false);
                        }
                        TMTapjoyAdapter.this.getAdEventHandler().OnDidClose(activity, TapjoyListener.this.mAdvert);
                    }
                });
                TMTapjoyAdapter.this.removeAd(this.mAdvert.getAdRequest());
                this.mActivity = null;
            }
        }

        public void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
            TLog.debug("onPurchaseRequest");
        }

        public void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
            TLog.debug("onRewardRequest");
        }

        public void onVideoStart(TJPlacement tJPlacement) {
            TLog.debug("onVideoStart");
        }

        public void onVideoError(TJPlacement tJPlacement, String str) {
            TLog.debug("onVideoError " + str);
            TMTapjoyAdapter.this.getAdEventHandler().OnDidFailToDisplay(this.mAdvert, new TMAdError(TapdaqError.TAPJOY_DISPLAY_ERROR, TapdaqError.TAPJOY_DISPLAY_ERROR_MESSAGE));
        }

        public void onVideoComplete(TJPlacement tJPlacement) {
            TLog.debug("onVideoComplete");
            this.mHasRewardedUser = true;
            if (this.mActivity != null) {
                TMTapjoyAdapter.this.getAdEventHandler().OnVideoComplete(this.mAdvert);
                if (this.mAdvert.getType() == 3) {
                    final Activity activity = this.mActivity;
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            TMTapjoyAdapter.this.getAdEventHandler().OnRewardVerified(activity, TapjoyListener.this.mAdvert, true);
                        }
                    });
                }
            }
        }
    }
}
