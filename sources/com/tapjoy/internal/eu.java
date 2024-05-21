package com.tapjoy.internal;

import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitJSBridge;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyUtil;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class eu {
    public static String b = "";
    public TJAdUnitJSBridge a;
    public boolean c = false;
    /* access modifiers changed from: private */
    public cu d;
    /* access modifiers changed from: private */
    public cv e;
    /* access modifiers changed from: private */
    public da f = da.a(TMMediationNetworks.TAPJOY_NAME, Tapjoy.getVersion());
    /* access modifiers changed from: private */
    public df g;

    public eu(TJAdUnitJSBridge tJAdUnitJSBridge) {
        this.a = tJAdUnitJSBridge;
    }

    public final boolean a(JSONObject jSONObject) {
        if (this.a.b == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- WebView is null");
            return false;
        } else if (this.a.a == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- TJAdUnit is null");
            return false;
        } else if (this.a.a.getVideoView() == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- VideoView is null");
            return false;
        } else if (jSONObject == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- json parameter is null");
            return false;
        } else if (!jSONObject.has(TJAdUnitConstants.String.OM_JAVASCRIPT_URL)) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- unable to parse om javascript url from json");
            return false;
        } else {
            try {
                jSONObject.getJSONArray(TJAdUnitConstants.String.VENDORS);
                return true;
            } catch (JSONException unused) {
                TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- unable to parse vendors from json");
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public static List b(JSONArray jSONArray) {
        db dbVar;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString(TJAdUnitConstants.String.VENDOR_JS_URL, (String) null);
                if (optString == null) {
                    TapjoyLog.d("TJOMViewabilityAgent", "Vendor JS URL not found. Skipping.");
                } else {
                    try {
                        URL url = new URL(optString);
                        String optString2 = jSONObject.optString(TJAdUnitConstants.String.VENDOR_NAME, (String) null);
                        String optString3 = jSONObject.optString(TJAdUnitConstants.String.VENDOR_PARAMETERS, (String) null);
                        if (optString3 == null || optString2 == null) {
                            dbVar = db.a(url);
                        } else {
                            dbVar = db.a(optString2, url, optString3);
                        }
                        arrayList.add(dbVar);
                    } catch (Exception unused) {
                        TapjoyLog.d("TJOMViewabilityAgent", "Malformed vendor JS URL. Skipping " + optString);
                    }
                }
            } catch (JSONException unused2) {
                TapjoyLog.d("TJOMViewabilityAgent", "Malformed vendor object. Skipping.");
            }
        }
        return arrayList;
    }

    public static void b(JSONObject jSONObject) {
        String str;
        if (ao.a(b)) {
            String optString = jSONObject.optString(TJAdUnitConstants.String.OM_JAVASCRIPT_URL, (String) null);
            if (optString == null) {
                TapjoyLog.d("TJOMViewabilityAgent", "Open Mediation JavaScript name not found in json.");
                return;
            }
            try {
                TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(optString);
                if (cachedDataForURL == null) {
                    TapjoyCache.getInstance().cacheAssetFromURL(optString, "", 30).get();
                    cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(optString);
                }
                if (cachedDataForURL == null) {
                    str = "";
                } else {
                    str = TapjoyUtil.getFileContents(new File(cachedDataForURL.getLocalFilePath()));
                }
                b = str;
            } catch (Exception unused) {
                TapjoyLog.d("TJOMViewabilityAgent", "Failed downloading Open Mediation JavaScript");
            }
        }
    }
}
