package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceInfo;

public class TMScreenSize {
    private int height;
    private float resolution;
    private int width;

    public TMScreenSize(Context context) {
        this.width = TDDeviceInfo.getWidth(context);
        this.height = TDDeviceInfo.getHeight(context);
        this.resolution = TDDeviceInfo.getResolutionFloat(context);
    }
}
