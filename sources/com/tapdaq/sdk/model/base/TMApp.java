package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceInfo;

public class TMApp {
    private String bundle_id;
    private String current_orientation;
    private String release_version;
    private String user_agent = System.getProperty("http.agent");

    public TMApp(Context context) {
        this.bundle_id = TDDeviceInfo.getBundleID(context);
        this.release_version = TDDeviceInfo.getBundleVersion(context);
        this.current_orientation = TDDeviceInfo.getDeviceOrientation(context);
    }
}
