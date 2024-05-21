package com.tonyodev.fetch.exception;

public final class EnqueueException extends RuntimeException {
    private int errorCode;

    public EnqueueException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
