package com.tapdaq.sdk.helpers;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TDDate {
    public static long getDaysInMilliseconds(int i) {
        return TimeUnit.MILLISECONDS.convert((long) i, TimeUnit.DAYS);
    }

    public static long getCurrentUtcTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static int getCurrentTimezoneOffset() {
        return Calendar.getInstance().getTimeZone().getRawOffset();
    }

    public static long getCurrentLocalTime() {
        return getCurrentUtcTime() + ((long) getCurrentTimezoneOffset());
    }
}
