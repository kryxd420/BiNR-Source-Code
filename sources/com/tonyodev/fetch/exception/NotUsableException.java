package com.tonyodev.fetch.exception;

public final class NotUsableException extends RuntimeException {
    private final int errorCode;

    public NotUsableException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
