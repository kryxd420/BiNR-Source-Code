package com.applovin.mediation.adapter;

public class MaxAdapterError {
    public static final MaxAdapterError AD_EXPIRED = new MaxAdapterError(ERROR_CODE_AD_EXPIRED);
    public static final MaxAdapterError AD_NOT_READY = new MaxAdapterError(ERROR_CODE_AD_NOT_READY);
    public static final MaxAdapterError BAD_REQUEST = new MaxAdapterError(ERROR_CODE_BAD_REQUEST);
    public static final int ERROR_CODE_AD_EXPIRED = -5213;
    public static final int ERROR_CODE_AD_NOT_READY = -5205;
    public static final int ERROR_CODE_BAD_REQUEST = -5203;
    public static final int ERROR_CODE_INTERNAL_ERROR = -5209;
    public static final int ERROR_CODE_INVALID_CONFIGURATION = -5202;
    public static final int ERROR_CODE_INVALID_LOAD_STATE = -5201;
    public static final int ERROR_CODE_NOT_INITIALIZED = -5204;
    public static final int ERROR_CODE_NO_CONNECTION = -5207;
    public static final int ERROR_CODE_NO_FILL = 204;
    public static final int ERROR_CODE_REWARD_ERROR = -5302;
    public static final int ERROR_CODE_SERVER_ERROR = -5208;
    public static final int ERROR_CODE_SIGNAL_COLLECTION_NOT_SUPPORTED = -5211;
    public static final int ERROR_CODE_SIGNAL_COLLECTION_TIMEOUT = -5210;
    public static final int ERROR_CODE_TIMEOUT = -5206;
    public static final int ERROR_CODE_UNSPECIFIED = -5200;
    public static final int ERROR_CODE_WEBVIEW_ERROR = -5212;
    public static final MaxAdapterError INTERNAL_ERROR = new MaxAdapterError(ERROR_CODE_INTERNAL_ERROR);
    public static final MaxAdapterError NOT_INITIALIZED = new MaxAdapterError(ERROR_CODE_NOT_INITIALIZED);
    public static final MaxAdapterError NO_FILL = new MaxAdapterError(204);
    public static final MaxAdapterError REWARD_ERROR = new MaxAdapterError(ERROR_CODE_REWARD_ERROR);
    public static final MaxAdapterError UNSPECIFIED = new MaxAdapterError(ERROR_CODE_UNSPECIFIED);
    private final String adapterError;
    private final int errorCode;

    public MaxAdapterError(int i) {
        this(i, "0");
    }

    public MaxAdapterError(int i, int i2) {
        this(i, Integer.toString(i2));
    }

    public MaxAdapterError(int i, String str) {
        this.errorCode = i;
        this.adapterError = str;
    }

    public String getAdapterError() {
        return this.adapterError;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String toString() {
        return "MaxAdapterError{errorCode=" + this.errorCode + ", adapterError='" + this.adapterError + '\'' + '}';
    }
}
