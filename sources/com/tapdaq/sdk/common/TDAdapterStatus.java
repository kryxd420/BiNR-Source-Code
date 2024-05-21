package com.tapdaq.sdk.common;

public enum TDAdapterStatus {
    NOT_INTEGRATED,
    DISABLED,
    READY,
    FAILED,
    FAILED_ADAPTER_MISMATCH,
    FAILED_MISSING_ACTIVITY,
    FAILED_MISSING_CREDENTIALS,
    TIMEOUT,
    WAITING;
    
    public static final String DISABLED_STR = "Disabled";
    public static final String FAILED_ADAPTER_MISMATCH_STR = "Failed, Adapter version mismatch";
    public static final String FAILED_MISSING_ACTIVITY_STR = "Failed, Missing Activities in AndroidManifest.xml";
    public static final String FAILED_MISSING_CREDENTIALS_STR = "Failed, Missing Credentials";
    public static final String FAILED_STR = "Failed";
    public static final String NOT_INTEGRATED_STR = "Not Integrated";
    public static final String READY_STR = "Ready";
    public static final String TIMEOUT_STR = "Timed out";
    public static final String WAITING_STR = "Waiting";

    public static String GetString(TDAdapterStatus tDAdapterStatus) {
        switch (tDAdapterStatus) {
            case NOT_INTEGRATED:
                return NOT_INTEGRATED_STR;
            case DISABLED:
                return DISABLED_STR;
            case READY:
                return READY_STR;
            case FAILED:
                return FAILED_STR;
            case FAILED_ADAPTER_MISMATCH:
                return FAILED_ADAPTER_MISMATCH_STR;
            case FAILED_MISSING_ACTIVITY:
                return FAILED_MISSING_ACTIVITY_STR;
            case FAILED_MISSING_CREDENTIALS:
                return FAILED_MISSING_CREDENTIALS_STR;
            case TIMEOUT:
                return TIMEOUT_STR;
            case WAITING:
                return WAITING_STR;
            default:
                return "";
        }
    }
}
