package com.integralads.avid.library.adcolony;

import android.content.Context;

public class AvidContext {
    private static final AvidContext a = new AvidContext();
    private String b;

    public String getAvidReleaseDate() {
        return BuildConfig.RELEASE_DATE;
    }

    public String getAvidVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public String getPartnerName() {
        return BuildConfig.SDK_NAME;
    }

    public static AvidContext getInstance() {
        return a;
    }

    public void init(Context context) {
        if (this.b == null) {
            this.b = context.getApplicationContext().getPackageName();
        }
    }

    public String getBundleId() {
        return this.b;
    }
}
