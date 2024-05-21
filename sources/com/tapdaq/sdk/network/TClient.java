package com.tapdaq.sdk.network;

import android.content.Context;
import com.tapdaq.sdk.network.HttpClientBase;
import java.util.Map;
import org.json.JSONObject;

public class TClient implements HttpClientBase {
    private static TClient mInstance;

    public static synchronized TClient getInstance() {
        TClient tClient;
        synchronized (TClient.class) {
            if (mInstance == null) {
                mInstance = new TClient();
            }
            tClient = mInstance;
        }
        return tClient;
    }

    public void executeImageGET(Context context, String str, int i, int i2, HttpClientBase.ResponseImageHandler responseImageHandler) {
        new HttpImageAsyncTask(context, i, i2, responseImageHandler).execute(new String[]{str});
    }

    public void executeFileGet(String str, HttpClientBase.ResponseFileHandler responseFileHandler) {
        new HttpFileAsyncTask(responseFileHandler).execute(new String[]{str});
    }

    public void executePOST(String str, Map<String, String> map, JSONObject jSONObject, HttpClientBase.ResponseHandler responseHandler) {
        new HttpJsonAsyncTask(map, jSONObject != null ? jSONObject.toString() : null, responseHandler).execute(new String[]{str});
    }
}
