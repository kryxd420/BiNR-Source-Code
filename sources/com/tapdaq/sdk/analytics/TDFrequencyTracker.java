package com.tapdaq.sdk.analytics;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tapdaq.sdk.adnetworks.TDAdRequest;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapdaq.sdk.helpers.TDDate;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.storage.FileStorageBase;
import com.tapdaq.sdk.storage.FileStorageProvider;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TDFrequencyTracker {
    private static final long DAY_IN_MILLISECONDS = 86400000;
    private static final String IMPRESSIONS_FILENAME = "Impressions";
    private FileStorageBase fileStorage;

    public TDFrequencyTracker(Context context) {
        this.fileStorage = FileStorageProvider.getInstance(context);
    }

    /* access modifiers changed from: package-private */
    public JSONObject createJsonObject(long j, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DateTime", j);
            jSONObject.put("Type", i);
        } catch (JSONException e) {
            TLog.error((Exception) e);
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public long getExpiryTime() {
        long currentUtcTime = TDDate.getCurrentUtcTime();
        return (currentUtcTime - (currentUtcTime % DAY_IN_MILLISECONDS)) + ((long) TDDate.getCurrentTimezoneOffset());
    }

    /* access modifiers changed from: package-private */
    public List<String> removeExpired(List<String> list) {
        ArrayList arrayList = new ArrayList();
        long expiryTime = getExpiryTime();
        for (String next : list) {
            try {
                JSONObject jSONObject = new JSONObject(next);
                if (jSONObject.has("DateTime") && jSONObject.getLong("DateTime") > expiryTime) {
                    arrayList.add(next);
                }
            } catch (JSONException e) {
                TLog.error((Exception) e);
            }
        }
        return arrayList;
    }

    public List<String> getImpressionsList(int i) {
        String name = TMMediationNetworks.getName(i);
        if (!this.fileStorage.exists(IMPRESSIONS_FILENAME, name)) {
            return new ArrayList();
        }
        return removeExpired((List) new Gson().fromJson(this.fileStorage.loadFile(IMPRESSIONS_FILENAME, name), new TypeToken<List<String>>() {
        }.getType()));
    }

    private List<String> getImpressionsList(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (String next : getImpressionsList(i2)) {
            try {
                JSONObject jSONObject = new JSONObject(next);
                if (jSONObject.has("Type") && ((Integer) jSONObject.get("Type")).intValue() == i) {
                    arrayList.add(next);
                }
            } catch (JSONException e) {
                TLog.error((Exception) e);
            }
        }
        return removeExpired(arrayList);
    }

    private void storeImpressions(List<String> list, int i) {
        this.fileStorage.saveFile(IMPRESSIONS_FILENAME, TMMediationNetworks.getName(i), new Gson().toJson((Object) list), true);
    }

    public void trackImpression(TDAdRequest tDAdRequest) {
        int type = tDAdRequest.getType();
        long currentLocalTime = TDDate.getCurrentLocalTime();
        List<String> impressionsList = getImpressionsList(tDAdRequest.getAdapter().getID());
        impressionsList.add(createJsonObject(currentLocalTime, type).toString());
        storeImpressions(impressionsList, tDAdRequest.getAdapter().getID());
    }

    public int getImpressionCountToday(int i, int i2) {
        return getImpressionsList(i, i2).size();
    }
}
