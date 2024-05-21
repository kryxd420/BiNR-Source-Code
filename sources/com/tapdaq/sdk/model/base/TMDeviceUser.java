package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TDSystemInfo;

public class TMDeviceUser {
    private String country;
    private TMDevice device;
    private boolean do_not_track;
    private String idfa;
    private String ip_address;
    private String locale;
    private TMTimezone timezone;
    private boolean volume_enabled;

    public TMDeviceUser(Context context) {
        this.country = TDDeviceInfo.getCountry(context);
        this.locale = TDDeviceInfo.getLocale(context);
        this.do_not_track = TDDeviceInfo.getLimitAdTracking(context);
        if (!this.do_not_track) {
            this.idfa = TDDeviceInfo.getAdvertisementId(context);
        }
        this.ip_address = TDSystemInfo.getDeviceipWiFiData(context);
        this.volume_enabled = TDSystemInfo.isVolumeEnabled(context);
        this.timezone = new TMTimezone(context);
        this.device = new TMDevice(context);
    }
}
