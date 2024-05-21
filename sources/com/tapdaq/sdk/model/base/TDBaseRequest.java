package com.tapdaq.sdk.model.base;

import android.content.Context;
import com.tapdaq.sdk.helpers.Utils;
import java.util.Date;

public class TDBaseRequest {
    private static String SESSIONID;
    private TMApp app;
    private long date_created = (new Date().getTime() / 1000);
    private TMDeviceUser device_user;
    private String session_id;

    public TDBaseRequest(Context context) {
        if (SESSIONID == null) {
            String generateUUID = Utils.generateUUID();
            SESSIONID = generateUUID;
            this.session_id = generateUUID;
        } else {
            this.session_id = SESSIONID;
        }
        this.device_user = new TMDeviceUser(context);
        this.app = new TMApp(context);
    }
}
