package com.unity.purchasing.googleplay;

public class IabResult {
    String mMessage;
    int mResponse;

    public IabResult(int i, String str) {
        this.mResponse = i;
        if (str == null || str.trim().length() == 0) {
            this.mMessage = IabHelper.getResponseDesc(i);
            return;
        }
        this.mMessage = str + " (response: " + IabHelper.getResponseDesc(i) + ")";
    }

    public int getResponse() {
        return this.mResponse;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public boolean isSuccess() {
        return this.mResponse == 0;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public String toString() {
        return "{ IabResult: message = " + getMessage() + ", response = " + getResponse() + "}";
    }
}
