package com.tapdaq.sdk.network;

import android.content.Context;
import android.graphics.Bitmap;
import com.tapdaq.sdk.common.TMAdError;
import java.util.Map;
import org.json.JSONObject;

public interface HttpClientBase {

    public interface ResponseFileHandler {
        void onError(Exception exc);

        void onSuccess(byte[] bArr);
    }

    public interface ResponseHandler {
        void onError(TMAdError tMAdError);

        void onSuccess(JSONObject jSONObject);
    }

    public interface ResponseImageHandler {
        void onError(Exception exc);

        void onSuccess(Bitmap bitmap);
    }

    public interface ResponseStringHandler {
        void onError(Exception exc);

        void onSuccess(String str);
    }

    void executeFileGet(String str, ResponseFileHandler responseFileHandler);

    void executeImageGET(Context context, String str, int i, int i2, ResponseImageHandler responseImageHandler);

    void executePOST(String str, Map<String, String> map, JSONObject jSONObject, ResponseHandler responseHandler);
}
