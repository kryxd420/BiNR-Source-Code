package com.tapdaq.sdk.model.base;

import android.content.Context;
import java.util.GregorianCalendar;
import java.util.TimeZone;

class TMTimezone {
    private String location;
    private String offset;

    TMTimezone(Context context) {
        TimeZone timeZone = new GregorianCalendar().getTimeZone();
        this.offset = timeZone.getDisplayName(false, 0);
        this.location = timeZone.getID();
    }
}
