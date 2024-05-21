package com.tapdaq.sdk.analytics;

import android.content.Context;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TDGson;
import com.tapdaq.sdk.model.analytics.TMStatsEvent;
import com.tapdaq.sdk.network.ResponseHandler;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapdaq.sdk.storage.FileStorageBase;
import java.util.List;
import org.json.JSONObject;

public class StatsResponseHandler extends ResponseHandler {
    private List<TMStatsEvent> mEvents;
    private FileStorageBase mFileStorage;

    StatsResponseHandler(Context context, String str, List<TMStatsEvent> list) {
        super(context, str);
        this.mEvents = list;
        this.mFileStorage = new FileStorage(context);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
    }

    public void onError(TMAdError tMAdError) {
        super.onError(tMAdError);
        storeEventsOnDisk();
    }

    private void storeEventsOnDisk() {
        this.mFileStorage.saveFileAsync(TMStatsManager.STORED_EVENTS, "stats", TDGson.Create().toJson((Object) this.mEvents), false);
    }
}
