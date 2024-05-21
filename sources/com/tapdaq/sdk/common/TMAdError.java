package com.tapdaq.sdk.common;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class TMAdError {
    private int code;
    private String message;
    private Map<String, TMAdError> subErrors;

    public TMAdError() {
        this.subErrors = new HashMap();
        this.code = -1;
        this.message = "Unknown Error";
    }

    public TMAdError(int i, String str) {
        this.subErrors = new HashMap();
        this.code = i;
        this.message = str;
    }

    public TMAdError(int i, String str, Map<String, TMAdError> map) {
        this.subErrors = new HashMap();
        this.code = i;
        this.message = str;
        this.subErrors.putAll(map);
    }

    public void addSubError(String str, TMAdError tMAdError) {
        this.subErrors.put(str, tMAdError);
    }

    public String getErrorMessage() {
        return this.message;
    }

    public int getErrorCode() {
        return this.code;
    }

    public Map<String, TMAdError> getSubErrors() {
        return this.subErrors;
    }

    public String toString() {
        return new Gson().toJson((Object) this);
    }
}
