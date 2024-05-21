package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import android.content.Context;
import com.tapdaq.sdk.TDState;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.analytics.TMStatsManager;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.listeners.TMInitListenerBase;
import com.tapdaq.sdk.model.config.TDConfigResponse;
import com.tapdaq.sdk.model.config.TDNetwork;
import com.tapdaq.sdk.network.ConfigResponseHandler;
import com.tapdaq.sdk.network.TMServiceClient;
import com.tapdaq.sdk.tasks.TDTaskManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TDConfigService implements AdapterListener, CredentialsListener {
    private Map<String, TMAdapter> mAdapters = new HashMap();
    TMInitListenerBase mInitListener;
    private final Map<String, TMAdapter> mPendingServices = new HashMap();
    protected TMServiceClient mServiceClient = new TMServiceClient();
    private TDState mState = TDState.DISABLED;

    public TDConfigService(TMInitListenerBase tMInitListenerBase) {
        this.mInitListener = tMInitListenerBase;
        this.mInitListener.setConfigService(this);
    }

    public void setState(TDState tDState) {
        this.mState = tDState;
    }

    public TDState getState() {
        return this.mState;
    }

    /* access modifiers changed from: package-private */
    public void register(TMAdapter tMAdapter) {
        if (tMAdapter == null) {
            return;
        }
        if (!this.mAdapters.containsKey(tMAdapter.getName())) {
            this.mAdapters.put(tMAdapter.getName(), tMAdapter);
            TLog.info(String.format(Locale.ENGLISH, "Registered Adapter: %s. Network SDK Version: %s", new Object[]{tMAdapter.getName(), tMAdapter.getSdkVersion()}));
            tMAdapter.setAdapterListener(this);
            return;
        }
        TLog.info("Adapter already registered: " + tMAdapter.getName());
    }

    public void initialise(final Activity activity, final String str, final String str2) {
        setState(TDState.WAITING);
        TDTaskManager.getInstance().execute(new Runnable() {
            public void run() {
                if (!TDDeviceInfo.getLimitAdTracking(activity)) {
                    TDDeviceInfo.getAdvertisementId(activity);
                }
                if (activity != null) {
                    TDTaskManager.getInstance().runOnMainThread(activity, new Runnable() {
                        public void run() {
                            TDConfigService.this.mServiceClient.setCredentials(str, str2);
                            TDConfigService.this.mServiceClient.config(activity, new ConfigResponseHandler(activity, TDConfigService.this));
                        }
                    });
                }
                TLog.info("Initialising Tapdaq Version: android-sdk_7.0.1");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public List<TMAdapter> getAllNetworks() {
        return new ArrayList(this.mAdapters.values());
    }

    /* access modifiers changed from: package-private */
    public List<TMAdapter> getAdapters(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        for (TMAdapter next : this.mAdapters.values()) {
            if (next.isInitialised(context) && next.canDisplayAdType(context, i)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public TMAdapter getAdapter(String str) {
        return this.mAdapters.get(str);
    }

    public void onReceivedCredentials(Activity activity, TDConfigResponse tDConfigResponse) {
        if (tDConfigResponse == null || !tDConfigResponse.hasResponse()) {
            TLog.error("No Networks Found");
            this.mInitListener.setMediationNetworksComplete(activity, new TMAdError(40, TapdaqError.NO_MEDIATION_CREDENTIALS_MESSAGE));
            return;
        }
        TDWaterfallService.SetCredentialsType(tDConfigResponse.getCredentialsType());
        TMStatsManager.SetCredentialsType(tDConfigResponse.getCredentialsType());
        ArrayList<TMAdapter> arrayList = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (TDNetwork next : tDConfigResponse.getNetworks()) {
            TMAdapter adapter = getAdapter(TMMediationNetworks.getStylizedName(next.getName()));
            if (adapter != null) {
                this.mPendingServices.put(adapter.getName(), adapter);
                arrayList.add(adapter);
                hashMap.put(adapter.getName(), next);
            }
        }
        if (!arrayList.isEmpty()) {
            for (TMAdapter tMAdapter : arrayList) {
                tMAdapter.initialise(activity, (TDNetwork) hashMap.get(tMAdapter.getName()));
            }
            return;
        }
        TLog.error("No Adapters Found");
        this.mInitListener.setMediationNetworksComplete(activity, new TMAdError(41, TapdaqError.NO_ADAPTERS_REGISTERED_MESSAGE));
    }

    public void onFailedToReceiveCredentials(Context context, TMAdError tMAdError) {
        TLog.error(tMAdError.getErrorMessage());
        if (this.mPendingServices.isEmpty() && this.mInitListener != null) {
            TMAdError tMAdError2 = new TMAdError(40, TapdaqError.NO_MEDIATION_CREDENTIALS_MESSAGE);
            tMAdError2.addSubError("Response", tMAdError);
            this.mInitListener.setMediationNetworksComplete(context, tMAdError2);
        }
    }

    public void onInitSuccess(Activity activity, int i) {
        TLog.debug(String.format(Locale.ENGLISH, "onInitSuccess - %s", new Object[]{TMMediationNetworks.getName(i)}));
        synchronized (this.mPendingServices) {
            this.mPendingServices.remove(TMMediationNetworks.getName(i));
            if (this.mPendingServices.isEmpty()) {
                this.mInitListener.setMediationNetworksComplete(activity, (TMAdError) null);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0065, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onInitFailure(android.app.Activity r6, int r7, com.tapdaq.sdk.common.TMAdError r8) {
        /*
            r5 = this;
            java.util.Locale r0 = java.util.Locale.ENGLISH
            java.lang.String r1 = "onInitFailure - %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = com.tapdaq.sdk.adnetworks.TMMediationNetworks.getName(r7)
            r4 = 0
            r2[r4] = r3
            java.lang.String r0 = java.lang.String.format(r0, r1, r2)
            com.tapdaq.sdk.helpers.TLog.debug(r0)
            java.util.Map<java.lang.String, com.tapdaq.sdk.common.TMAdapter> r0 = r5.mPendingServices
            monitor-enter(r0)
            java.util.Map<java.lang.String, com.tapdaq.sdk.common.TMAdapter> r1 = r5.mPendingServices     // Catch:{ all -> 0x0066 }
            java.lang.String r2 = com.tapdaq.sdk.adnetworks.TMMediationNetworks.getName(r7)     // Catch:{ all -> 0x0066 }
            r1.remove(r2)     // Catch:{ all -> 0x0066 }
            com.tapdaq.sdk.listeners.TMInitListenerBase r1 = r5.mInitListener     // Catch:{ all -> 0x0066 }
            java.lang.String r7 = com.tapdaq.sdk.adnetworks.TMMediationNetworks.getName(r7)     // Catch:{ all -> 0x0066 }
            r1.addError(r7, r8)     // Catch:{ all -> 0x0066 }
            java.util.Map<java.lang.String, com.tapdaq.sdk.common.TMAdapter> r7 = r5.mPendingServices     // Catch:{ all -> 0x0066 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0066 }
            if (r7 == 0) goto L_0x0064
            java.util.Map<java.lang.String, com.tapdaq.sdk.common.TMAdapter> r7 = r5.mAdapters     // Catch:{ all -> 0x0066 }
            java.util.Collection r7 = r7.values()     // Catch:{ all -> 0x0066 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0066 }
        L_0x003c:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0066 }
            if (r8 == 0) goto L_0x0056
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0066 }
            com.tapdaq.sdk.common.TMAdapter r8 = (com.tapdaq.sdk.common.TMAdapter) r8     // Catch:{ all -> 0x0066 }
            boolean r8 = r8.isInitialised(r6)     // Catch:{ all -> 0x0066 }
            if (r8 == 0) goto L_0x003c
            com.tapdaq.sdk.listeners.TMInitListenerBase r7 = r5.mInitListener     // Catch:{ all -> 0x0066 }
            r8 = 0
            r7.setMediationNetworksComplete(r6, r8)     // Catch:{ all -> 0x0066 }
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            return
        L_0x0056:
            com.tapdaq.sdk.listeners.TMInitListenerBase r7 = r5.mInitListener     // Catch:{ all -> 0x0066 }
            com.tapdaq.sdk.common.TMAdError r8 = new com.tapdaq.sdk.common.TMAdError     // Catch:{ all -> 0x0066 }
            r1 = 42
            java.lang.String r2 = "Networks failed to initialise"
            r8.<init>(r1, r2)     // Catch:{ all -> 0x0066 }
            r7.setMediationNetworksComplete(r6, r8)     // Catch:{ all -> 0x0066 }
        L_0x0064:
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            return
        L_0x0066:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapdaq.sdk.adnetworks.TDConfigService.onInitFailure(android.app.Activity, int, com.tapdaq.sdk.common.TMAdError):void");
    }
}
