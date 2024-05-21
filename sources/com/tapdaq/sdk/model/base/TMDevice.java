package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TDSystemInfo;
import com.tapjoy.TapjoyConstants;

public class TMDevice {
    private String architecture;
    private TMCarrier carrier;
    private boolean is_phone;
    private String model = TDDeviceInfo.getDeviceModel();
    private String model_type = TDDeviceInfo.getDeviceManufacturer();
    private String operating_system;
    private String operating_system_version;
    private TMScreenSize screen_size;

    public TMDevice(Context context) {
        this.is_phone = !TDDeviceInfo.isTablet(context);
        this.operating_system = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
        this.operating_system_version = TDDeviceInfo.getDeviceAPI();
        this.architecture = TDSystemInfo.getCPUArchitecture();
        this.screen_size = new TMScreenSize(context);
        this.carrier = new TMCarrier(context);
    }
}
