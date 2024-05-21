package com.tonyodev.fetch.exception;

public class DownloadInterruptedException extends RuntimeException {
    private int errorCode;

    public DownloadInterruptedException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
