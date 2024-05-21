package com.tapdaq.sdk.adnetworks;

public class TDMediatedNativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private Integer mAdChoicesPosition;
    private Boolean mAllowMultipleImages;
    private Integer mOrientation;
    private Boolean mReturnUrlsForImageAssets;
    private Boolean mStartVideoMuted;

    public TDMediatedNativeAdOptions setAdChoicesPosition(Integer num) {
        this.mAdChoicesPosition = num;
        return this;
    }

    public TDMediatedNativeAdOptions setImageOrienation(Integer num) {
        this.mOrientation = num;
        return this;
    }

    public TDMediatedNativeAdOptions setStartVideoMuted(Boolean bool) {
        this.mStartVideoMuted = bool;
        return this;
    }

    public TDMediatedNativeAdOptions setAllowMultipleImages(Boolean bool) {
        this.mAllowMultipleImages = bool;
        return this;
    }

    public TDMediatedNativeAdOptions setReturnUrlsForImageAssets(Boolean bool) {
        this.mReturnUrlsForImageAssets = bool;
        return this;
    }

    public Integer getAdChoicesPosition() {
        return this.mAdChoicesPosition;
    }

    public Integer getOrientation() {
        return this.mOrientation;
    }

    public Boolean startVideoMuted() {
        return this.mStartVideoMuted;
    }

    public Boolean allowMultipleImages() {
        return this.mAllowMultipleImages;
    }

    public Boolean returnUrlsForImageAssets() {
        return this.mReturnUrlsForImageAssets;
    }
}
