package com.tapdaq.sdk.network;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.sdk.BuildConfig;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.Encrypter;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.model.analytics.TMAdClick;
import com.tapdaq.sdk.model.analytics.TMAdSize;
import com.tapdaq.sdk.model.analytics.TMStats;
import com.tapdaq.sdk.model.analytics.TMStatsEvent;
import com.tapdaq.sdk.model.base.TDBaseRequest;
import com.tapdaq.sdk.model.rewards.TMRewardStats;
import com.tapdaq.sdk.model.rewards.TMRewardStatsData;
import com.tapdaq.sdk.model.waterfall.TDWaterfall;
import com.tapdaq.sdk.model.waterfall.TDWaterfallConfig;
import com.tapdaq.sdk.network.HttpClientBase;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TMServiceClient {
    private static String APPLICATION_ID = null;
    private static final String CLICK_URL = "v3/analytics/click";
    private static String CLIENT_KEY = null;
    private static final String CONFIG_URL = "v4/config";
    private static final String REWARD_URL = "v4/analytics/reward";
    private static final String STATS_URL = "v4/analytics/stats";
    private static final String WATERFALL_URL = "v4/waterfall";
    protected Encrypter encrypter;
    private HttpClientBase mClient;

    /* access modifiers changed from: protected */
    public HttpClientBase getHttpClient() {
        return this.mClient;
    }

    public TMServiceClient() {
        this.encrypter = new Encrypter();
        this.mClient = TClient.getInstance();
    }

    public TMServiceClient(HttpClientBase httpClientBase) {
        this.encrypter = new Encrypter();
        this.mClient = httpClientBase;
    }

    public void setCredentials(String str, String str2) {
        APPLICATION_ID = str;
        CLIENT_KEY = str2;
    }

    public void config(Activity activity, HttpClientBase.ResponseHandler responseHandler) {
        TLog.debug("Config");
        try {
            getHttpClient().executePOST("https://ads.tapdaq.com/v4/config", buildHeaders(activity), new JSONObject(new Gson().toJson((Object) new TDBaseRequest(activity))), responseHandler);
        } catch (JSONException e) {
            TLog.error((Exception) e);
            responseHandler.onError(new TMAdError(-1, "JSONException"));
        }
    }

    public void waterfall(Activity activity, TDWaterfallConfig tDWaterfallConfig, HttpClientBase.ResponseHandler responseHandler) {
        TLog.debug("Waterfall");
        try {
            getHttpClient().executePOST("https://ads.tapdaq.com/v4/waterfall", buildHeaders(activity), new JSONObject(new Gson().toJson((Object) new TDWaterfall(activity, tDWaterfallConfig))), responseHandler);
        } catch (JSONException e) {
            TLog.error((Exception) e);
            responseHandler.onError(new TMAdError(-1, "JSONException"));
        }
    }

    public void click(Context context, TMAd tMAd, String str, String str2, TMAdSize tMAdSize, HttpClientBase.ResponseHandler responseHandler) {
        try {
            getHttpClient().executePOST("https://ads.tapdaq.com/v3/analytics/click", buildHeaders(context), new JSONObject(new Gson().toJson((Object) new TMAdClick(context, tMAd, str, str2, tMAdSize))), responseHandler);
        } catch (JSONException e) {
            TLog.error((Exception) e);
        }
    }

    public void stats(Context context, List<TMStatsEvent> list, HttpClientBase.ResponseHandler responseHandler) {
        try {
            getHttpClient().executePOST("https://ads.tapdaq.com/v4/analytics/stats", buildHeaders(context), new JSONObject(new Gson().toJson((Object) new TMStats(context, list))), responseHandler);
        } catch (JSONException e) {
            TLog.error((Exception) e);
            if (responseHandler != null) {
                responseHandler.onError(new TMAdError(0, e.getMessage()));
            }
        }
    }

    public void reward(Context context, TMRewardStatsData tMRewardStatsData, ResponseHandler responseHandler) {
        try {
            JSONObject jSONObject = new JSONObject(new Gson().toJson((Object) new TMRewardStats(context, tMRewardStatsData)));
            responseHandler.setData(jSONObject);
            responseHandler.setUrl("https://ads.tapdaq.com/v4/analytics/reward");
            responseHandler.setAttempts(3);
            performPOSTRequest(context, "https://ads.tapdaq.com/v4/analytics/reward", jSONObject, responseHandler);
        } catch (JSONException e) {
            TLog.error((Exception) e);
        }
    }

    /* access modifiers changed from: package-private */
    public void performPOSTRequest(Context context, String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        getHttpClient().executePOST(str, buildHeaders(context), jSONObject, responseHandler);
    }

    private Map<String, String> buildHeaders(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", getCredentials());
        hashMap.put("X-Tapdaq-SDK-Version", BuildConfig.SDK_IDENTIFIER);
        String pluginVersion = Tapdaq.getInstance().config().getPluginVersion();
        if (pluginVersion != null) {
            hashMap.put("X-Tapdaq-Plugin-Version", pluginVersion);
        }
        return hashMap;
    }

    private String getCredentials() {
        try {
            String Base64 = this.encrypter.Base64(String.format(Locale.ENGLISH, "%s:%s", new Object[]{APPLICATION_ID, CLIENT_KEY}));
            return String.format(Locale.ENGLISH, "Basic %s", new Object[]{Base64});
        } catch (Exception e) {
            TLog.error(e);
            return "";
        }
    }
}
