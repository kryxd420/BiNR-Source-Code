package com.tapdaq.sdk.adnetworks;

import android.graphics.drawable.Drawable;

public class TDMediatedNativeAdImage {
    private Drawable mDrawable;
    private String mUrl;

    public TDMediatedNativeAdImage(Drawable drawable, String str) {
        this.mDrawable = drawable;
        this.mUrl = str;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
