package com.tapdaq.sdk.model.config;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TDNetworkCredentials extends HashMap {
    public String getAppKey() {
        return (String) get("app_key");
    }

    public String getAppId() {
        return (String) get(TapjoyConstants.TJC_APP_ID);
    }

    public String getVersionId() {
        return (String) get("version_id");
    }

    public List<String> getAdUnitIDs() {
        if (!containsKey("ad_unit_ids") || !(get("ad_unit_ids") instanceof ArrayList)) {
            return new ArrayList();
        }
        return (ArrayList) get("ad_unit_ids");
    }

    public String getCredentialsString(String str) {
        if (!containsKey(str) || !(get(str) instanceof String)) {
            return null;
        }
        return (String) get(str);
    }
}
