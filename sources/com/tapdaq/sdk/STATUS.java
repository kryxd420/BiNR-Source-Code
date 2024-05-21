package com.tapdaq.sdk;

import android.support.v4.os.EnvironmentCompat;

public enum STATUS {
    FALSE(0),
    TRUE(1),
    UNKNOWN(2);
    
    private int value;

    private STATUS(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static STATUS valueOf(int i) {
        switch (i) {
            case 0:
                return FALSE;
            case 1:
                return TRUE;
            default:
                return UNKNOWN;
        }
    }

    public String toString() {
        switch (this.value) {
            case 0:
                return "false";
            case 1:
                return "true";
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }
}
