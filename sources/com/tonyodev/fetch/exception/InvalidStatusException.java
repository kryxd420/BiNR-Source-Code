package com.tonyodev.fetch.exception;

public final class InvalidStatusException extends RuntimeException {
    private final int errorCode;

    public InvalidStatusException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
