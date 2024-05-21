package com.tapdaq.sdk.adnetworks;

import android.app.Activity;
import com.google.gson.GsonBuilder;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.common.TMAdType;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.model.waterfall.TDWaterfallConfig;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItemAdapter;
import com.tapdaq.sdk.model.waterfall.TDWaterfallResponse;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapdaq.sdk.network.TMServiceClient;
import java.util.List;
import org.json.JSONObject;

public class TDWaterfallService {
    private static String CREDENTIALS_TYPE;
    private TMServiceClient mServiceClient;

    public interface TDWaterfallCallback {
        void onError(TMAdError tMAdError);

        void onSuccess(TDAdRequest tDAdRequest);
    }

    public TDWaterfallService() {
        this.mServiceClient = new TMServiceClient();
    }

    public TDWaterfallService(TMServiceClient tMServiceClient) {
        this.mServiceClient = tMServiceClient;
    }

    public static void SetCredentialsType(String str) {
        CREDENTIALS_TYPE = str;
    }

    public void request(Activity activity, final String str, final int i, List<TMAdapter> list, final TDWaterfallCallback tDWaterfallCallback) {
        this.mServiceClient.waterfall(activity, new TDWaterfallConfig(activity, str, TMAdType.getString(i), CREDENTIALS_TYPE, list), new HttpClientBase.ResponseHandler() {
            public void onSuccess(JSONObject jSONObject) {
                TDWaterfallResponse tDWaterfallResponse = (TDWaterfallResponse) new GsonBuilder().registerTypeHierarchyAdapter(TDWaterfallItem.class, new TDWaterfallItemAdapter()).create().fromJson(jSONObject.toString(), TDWaterfallResponse.class);
                if (tDWaterfallResponse == null || tDWaterfallResponse.getWaterfall().isEmpty()) {
                    tDWaterfallCallback.onError(new TMAdError(TapdaqError.EMPTY_WATERFALL, TapdaqError.EMPTY_WATERFALL_MESSAGE));
                    return;
                }
                tDWaterfallCallback.onSuccess(new TDAdRequest(tDWaterfallResponse.getWaterfallId(), i, str, tDWaterfallResponse.getWaterfall(), tDWaterfallResponse.getReward()));
            }

            public void onError(TMAdError tMAdError) {
                tDWaterfallCallback.onError(tMAdError);
            }
        });
    }
}
