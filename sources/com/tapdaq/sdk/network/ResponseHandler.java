package com.tapdaq.sdk.network;

import android.content.Context;
import android.os.Handler;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.network.HttpClientBase;
import com.tapjoy.TapjoyConstants;
import java.util.Locale;
import org.json.JSONObject;

public class ResponseHandler implements HttpClientBase.ResponseHandler {
    public static final String CLICK = "Click";
    public static final String REWARD = "Reward";
    public static final String STATS = "Stats";
    protected Context mContext;
    /* access modifiers changed from: private */
    public JSONObject mData;
    private Handler mHandler;
    private int mRemainingAttempts = 0;
    /* access modifiers changed from: private */
    public TMServiceClient mServiceClient;
    private String mType = "Request";
    /* access modifiers changed from: private */
    public String mUrl;

    public ResponseHandler(Context context, TMServiceClient tMServiceClient, String str) {
        this.mContext = context;
        this.mServiceClient = tMServiceClient;
        this.mType = str;
    }

    public ResponseHandler(Context context, String str) {
        this.mContext = context;
        this.mServiceClient = new TMServiceClient();
        this.mType = str;
    }

    public void setData(JSONObject jSONObject) {
        this.mData = jSONObject;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setAttempts(int i) {
        this.mRemainingAttempts = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        TLog.debug(this.mType + " Success");
        this.mContext = null;
    }

    public void onError(TMAdError tMAdError) {
        TLog.debug(this.mType + " Failed");
        TLog.error(tMAdError.getErrorMessage());
        retry();
    }

    private void retry() {
        this.mRemainingAttempts--;
        if (this.mRemainingAttempts <= 0 || this.mContext == null) {
            TLog.debug("All Attempts fail.");
            this.mHandler = null;
            this.mContext = null;
            return;
        }
        TLog.debug(String.format(Locale.ENGLISH, "Remaining attempts %d", new Object[]{Integer.valueOf(this.mRemainingAttempts)}));
        if (this.mHandler == null) {
            this.mHandler = new Handler(this.mContext.getMainLooper());
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                ResponseHandler.this.mServiceClient.performPOSTRequest(ResponseHandler.this.mContext, ResponseHandler.this.mUrl, ResponseHandler.this.mData, ResponseHandler.this);
            }
        }, TapjoyConstants.TIMER_INCREMENT);
    }
}
