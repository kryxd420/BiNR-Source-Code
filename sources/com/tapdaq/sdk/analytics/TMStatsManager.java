package com.tapdaq.sdk.analytics;

import android.content.Context;
import android.os.Handler;
import com.google.gson.reflect.TypeToken;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TDGson;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.analytics.TMStatsEvent;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataAdTimeout;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataBase;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataIAP;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataMediationAd;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataMediationAdError;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataMediationAdRequest;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataMediationImpression;
import com.tapdaq.sdk.model.analytics.stats.TMStatsDataSDKTimeout;
import com.tapdaq.sdk.model.rewards.TMRewardStatsData;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapdaq.sdk.storage.FileStorageBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TMStatsManager {
    public static final String AD_FAILED_TO_DISPLAY = "ad_failed_to_display";
    public static final String AD_LOAD_TIMEOUT_EVENT = "ad_load_timeout";
    public static final String AD_REQUEST_EVENT = "ad_request";
    public static final String CLICK_EVENT = "click";
    private static String CREDENTIALS_TYPE = null;
    public static final String IAP_EVENT = "in_app_purchase";
    public static final String IMPRESSION_EVENT = "impression";
    private static final int MAX_EVENT_STORE = 10;
    private static final String MEDIATION = "mediation";
    public static final String SDK_INIT_TIMEOUT_EVENT = "sdk_init_timeout";
    public static final String STORED_EVENTS = "StatsEvents";
    public static final String STORED_PENDING_EVENTS = "StatsPendingEvents";
    public static final String VIDEO_COMPLETE_EVENT = "video_complete";
    private Context mContext;
    private List<TMStatsEvent> mEventsCache = new ArrayList();
    private FileStorageBase mFileStorage;
    private Handler mHandler;
    private List<TMStatsEvent> mPendingEventsCache = new ArrayList();

    public TMStatsManager(Context context, Handler handler, FileStorageBase fileStorageBase) {
        this.mContext = context;
        this.mFileStorage = fileStorageBase;
        this.mHandler = handler;
    }

    public TMStatsManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mFileStorage = new FileStorage(context);
        this.mHandler = new Handler(context.getMainLooper());
        sendEvents();
    }

    public static void SetCredentialsType(String str) {
        CREDENTIALS_TYPE = str;
    }

    public void sendEvents() {
        if (!getEvents().isEmpty()) {
            this.mHandler.post(new StatsRunnable(this.mContext, this));
        }
    }

    public void createAdRequest(TDAdRequest tDAdRequest) {
        storeEvent(new TMStatsEvent(MEDIATION, AD_REQUEST_EVENT, new TMStatsDataMediationAdRequest(tDAdRequest, CREDENTIALS_TYPE)), true);
    }

    public void finishAdRequest(TDAdRequest tDAdRequest, boolean z) {
        TMStatsEvent tMStatsEvent;
        Iterator it = new ArrayList(getPendingEvents()).iterator();
        while (true) {
            if (!it.hasNext()) {
                tMStatsEvent = null;
                break;
            }
            tMStatsEvent = (TMStatsEvent) it.next();
            TMStatsDataBase data = tMStatsEvent.getData();
            if ((data instanceof TMStatsDataMediationAdRequest) && ((TMStatsDataMediationAdRequest) data).getWaterfallId().equalsIgnoreCase(tDAdRequest.getWaterfallId())) {
                break;
            }
        }
        if (tMStatsEvent != null) {
            getPendingEvents().remove(tMStatsEvent);
            TMStatsDataBase data2 = tMStatsEvent.getData();
            if (data2 instanceof TMStatsDataMediationAdRequest) {
                if (z) {
                    ((TMStatsDataMediationAdRequest) data2).setFulfilled();
                } else {
                    ((TMStatsDataMediationAdRequest) data2).setReason(tDAdRequest.getNetworkErrorMessage());
                }
                storeEvent(tMStatsEvent);
            }
        }
    }

    public void sendImpression(TDAdRequest tDAdRequest) {
        storeEvent(new TMStatsEvent(MEDIATION, IMPRESSION_EVENT, new TMStatsDataMediationImpression(tDAdRequest, CREDENTIALS_TYPE)));
    }

    public void sendVideoComplete(TDAdRequest tDAdRequest) {
        storeEvent(new TMStatsEvent(MEDIATION, VIDEO_COMPLETE_EVENT, new TMStatsDataMediationAd(tDAdRequest, CREDENTIALS_TYPE)));
    }

    public void sendClick(TDAdRequest tDAdRequest) {
        storeEvent(new TMStatsEvent(MEDIATION, CLICK_EVENT, new TMStatsDataMediationAd(tDAdRequest, CREDENTIALS_TYPE)));
    }

    public void sendIAP(String str, double d, String str2) {
        storeEvent(new TMStatsEvent(MEDIATION, IAP_EVENT, new TMStatsDataIAP(str, Double.valueOf(d), str2)));
    }

    public void sendInitTimeout(String str, String str2, Long l) {
        storeEvent(new TMStatsEvent(MEDIATION, SDK_INIT_TIMEOUT_EVENT, new TMStatsDataSDKTimeout(str, CREDENTIALS_TYPE, str2, l)));
    }

    public void sendMediationAdTimeout(TDAdRequest tDAdRequest, Long l) {
        storeEvent(new TMStatsEvent(MEDIATION, AD_LOAD_TIMEOUT_EVENT, new TMStatsDataAdTimeout(tDAdRequest, CREDENTIALS_TYPE, l)));
    }

    public void sendFailToDisplayError(TDAdRequest tDAdRequest, TMAdError tMAdError) {
        storeEvent(new TMStatsEvent(MEDIATION, AD_FAILED_TO_DISPLAY, new TMStatsDataMediationAdError(tDAdRequest, CREDENTIALS_TYPE, tMAdError)));
    }

    public List<TMStatsEvent> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mEventsCache);
        arrayList.addAll(getStoredEvents(STORED_EVENTS));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<TMStatsEvent> getPendingEvents() {
        return this.mPendingEventsCache;
    }

    /* access modifiers changed from: package-private */
    public void clearEvents() {
        this.mEventsCache.clear();
        this.mFileStorage.deleteFile(STORED_EVENTS, "stats");
    }

    public List<TMStatsEvent> getStoredEvents(String str) {
        String loadFile;
        if (this.mFileStorage.exists(str, "stats") && (loadFile = this.mFileStorage.loadFile(str, "stats")) != null && !loadFile.isEmpty()) {
            try {
                List<TMStatsEvent> list = (List) TDGson.Create().fromJson(loadFile, new TypeToken<ArrayList<TMStatsEvent>>() {
                }.getType());
                if (list != null) {
                    return list;
                }
            } catch (Exception e) {
                TLog.debug(loadFile);
                TLog.error(e);
            }
        }
        return new ArrayList();
    }

    private void storeEvent(TMStatsEvent tMStatsEvent) {
        storeEvent(tMStatsEvent, false);
    }

    private void storeEvent(TMStatsEvent tMStatsEvent, boolean z) {
        if (z) {
            this.mPendingEventsCache.add(tMStatsEvent);
            return;
        }
        this.mEventsCache.add(tMStatsEvent);
        if (this.mEventsCache.size() >= 10) {
            sendEvents();
        }
    }

    public static TMRewardStatsData CreateRewardStat(TDAdRequest tDAdRequest, boolean z) {
        return new TMRewardStatsData(z, tDAdRequest, CREDENTIALS_TYPE);
    }
}
