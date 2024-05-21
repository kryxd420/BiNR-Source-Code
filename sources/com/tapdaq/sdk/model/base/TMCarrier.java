package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceNetwork;
import com.tapdaq.sdk.helpers.TLog;

public class TMCarrier {
    int country_code;
    String name;
    int network_code;

    public TMCarrier(Context context) {
        this.name = TDDeviceNetwork.getMobileNetworkName(context);
        try {
            String mobileCountryCode = TDDeviceNetwork.getMobileCountryCode(context);
            if (!mobileCountryCode.isEmpty()) {
                this.country_code = Integer.parseInt(mobileCountryCode);
            }
            String mobileNetworkCode = TDDeviceNetwork.getMobileNetworkCode(context);
            if (!mobileNetworkCode.isEmpty()) {
                this.network_code = Integer.parseInt(mobileNetworkCode);
            }
        } catch (Exception e) {
            TLog.error(e);
        }
    }
}
