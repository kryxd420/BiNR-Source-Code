package com.adcolony.sdk;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyAppOptions {
    public static final String ADMARVEL = "AdMarvel";
    public static final String ADMOB = "AdMob";
    public static final String ADOBEAIR = "Adobe AIR";
    public static final String AERSERVE = "AerServe";
    public static final int ALL = 2;
    public static final String APPODEAL = "Appodeal";
    public static final String COCOS2DX = "Cocos2d-x";
    public static final String CORONA = "Corona";
    public static final String FUSEPOWERED = "Fuse Powered";
    public static final String FYBER = "Fyber";
    public static final String IRONSOURCE = "ironSource";
    public static final int LANDSCAPE = 1;
    public static final String MOPUB = "MoPub";
    public static final int PORTRAIT = 0;
    @Deprecated
    public static final int SENSOR = 2;
    public static final String UNITY = "Unity";
    String a = "";
    String[] b;
    JSONArray c = y.b();
    JSONObject d = y.a();
    AdColonyUserMetadata e;

    public AdColonyAppOptions() {
        setOriginStore("google");
        if (a.b()) {
            l a2 = a.a();
            if (a2.e()) {
                a(a2.d().a);
                a(a2.d().b);
            }
        }
    }

    public AdColonyAppOptions setGDPRRequired(boolean z) {
        setOption("gdpr_required", z);
        return this;
    }

    public boolean getGDPRRequired() {
        return y.d(this.d, "gdpr_required");
    }

    public AdColonyAppOptions setGDPRConsentString(@NonNull String str) {
        y.a(this.d, "consent_string", str);
        return this;
    }

    public String getGDPRConsentString() {
        return y.b(this.d, "consent_string");
    }

    public AdColonyAppOptions setAppVersion(@NonNull String str) {
        if (aw.d(str)) {
            setOption(TapjoyConstants.TJC_APP_VERSION_NAME, str);
        }
        return this;
    }

    public String getAppVersion() {
        return y.b(this.d, TapjoyConstants.TJC_APP_VERSION_NAME);
    }

    public AdColonyAppOptions setUserID(@NonNull String str) {
        if (aw.d(str)) {
            setOption("user_id", str);
        }
        return this;
    }

    public String getUserID() {
        return y.b(this.d, "user_id");
    }

    public AdColonyAppOptions setOption(@NonNull String str, boolean z) {
        if (aw.d(str)) {
            y.a(this.d, str, z);
        }
        return this;
    }

    public Object getOption(@NonNull String str) {
        return y.a(this.d, str);
    }

    public AdColonyAppOptions setOption(@NonNull String str, double d2) {
        if (aw.d(str)) {
            y.a(this.d, str, d2);
        }
        return this;
    }

    public AdColonyAppOptions setOption(@NonNull String str, @NonNull String str2) {
        if (str != null && aw.d(str) && aw.d(str2)) {
            y.a(this.d, str, str2);
        }
        return this;
    }

    public AdColonyAppOptions setOriginStore(@NonNull String str) {
        if (aw.d(str)) {
            setOption("origin_store", str);
        }
        return this;
    }

    public String getOriginStore() {
        return y.b(this.d, "origin_store");
    }

    public AdColonyAppOptions setRequestedAdOrientation(@IntRange(from = 0, to = 2) int i) {
        setOption("orientation", (double) i);
        return this;
    }

    public int getRequestedAdOrientation() {
        return y.a(this.d, "orientation", -1);
    }

    public AdColonyAppOptions setAppOrientation(@IntRange(from = 0, to = 2) int i) {
        setOption("app_orientation", (double) i);
        return this;
    }

    public int getAppOrientation() {
        return y.a(this.d, "app_orientation", -1);
    }

    public AdColonyAppOptions setUserMetadata(@NonNull AdColonyUserMetadata adColonyUserMetadata) {
        this.e = adColonyUserMetadata;
        y.a(this.d, "user_metadata", adColonyUserMetadata.c);
        return this;
    }

    public AdColonyAppOptions setTestModeEnabled(boolean z) {
        y.a(this.d, "test_mode", z);
        return this;
    }

    public boolean getTestModeEnabled() {
        return y.d(this.d, "test_mode");
    }

    public AdColonyAppOptions setMultiWindowEnabled(boolean z) {
        y.a(this.d, "multi_window_enabled", z);
        return this;
    }

    public boolean getMultiWindowEnabled() {
        return y.d(this.d, "multi_window_enabled");
    }

    public AdColonyUserMetadata getUserMetadata() {
        return this.e;
    }

    public AdColonyAppOptions setMediationNetwork(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) && aw.d(str2)) {
            y.a(this.d, "mediation_network", str);
            y.a(this.d, "mediation_network_version", str2);
        }
        return this;
    }

    public JSONObject getMediationInfo() {
        JSONObject a2 = y.a();
        y.a(a2, "name", y.b(this.d, "mediation_network"));
        y.a(a2, MediationMetaData.KEY_VERSION, y.b(this.d, "mediation_network_version"));
        return a2;
    }

    public AdColonyAppOptions setPlugin(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) && aw.d(str2)) {
            y.a(this.d, TapjoyConstants.TJC_PLUGIN, str);
            y.a(this.d, "plugin_version", str2);
        }
        return this;
    }

    public JSONObject getPluginInfo() {
        JSONObject a2 = y.a();
        y.a(a2, "name", y.b(this.d, TapjoyConstants.TJC_PLUGIN));
        y.a(a2, MediationMetaData.KEY_VERSION, y.b(this.d, "plugin_version"));
        return a2;
    }

    public AdColonyAppOptions setKeepScreenOn(boolean z) {
        y.a(this.d, "keep_screen_on", z);
        return this;
    }

    public boolean getKeepScreenOn() {
        return y.d(this.d, "keep_screen_on");
    }

    public static AdColonyAppOptions getMoPubAppOptions(@NonNull String str) {
        AdColonyAppOptions mediationNetwork = new AdColonyAppOptions().setMediationNetwork("MoPub", "1.0");
        if (str == null || str.isEmpty()) {
            return mediationNetwork;
        }
        String[] split = str.split(",");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String[] split2 = split[i].split(":");
            if (split2.length == 2) {
                String str2 = split2[0];
                char c2 = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != 109770977) {
                    if (hashCode == 351608024 && str2.equals(MediationMetaData.KEY_VERSION)) {
                        c2 = 1;
                    }
                } else if (str2.equals(TapjoyConstants.TJC_STORE)) {
                    c2 = 0;
                }
                switch (c2) {
                    case 0:
                        mediationNetwork.setOriginStore(split2[1]);
                        break;
                    case 1:
                        mediationNetwork.setAppVersion(split2[1]);
                        break;
                    default:
                        Log.e("AdColonyMoPub", "AdColony client options in wrong format - please check your MoPub dashboard");
                        return mediationNetwork;
                }
                i++;
            } else {
                Log.e("AdColonyMoPub", "AdColony client options not recognized - please check your MoPub dashboard");
                return null;
            }
        }
        return mediationNetwork;
    }

    /* access modifiers changed from: package-private */
    public AdColonyAppOptions a(String str) {
        if (str == null) {
            return this;
        }
        this.a = str;
        y.a(this.d, TapjoyConstants.TJC_APP_ID, str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public AdColonyAppOptions a(String... strArr) {
        if (strArr == null) {
            return this;
        }
        this.b = strArr;
        this.c = y.b();
        for (String a2 : strArr) {
            y.a(this.c, a2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public String[] b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public JSONArray c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public JSONObject d() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (y.i(this.d, "use_forced_controller")) {
            ADCVMModule.a = y.d(this.d, "use_forced_controller");
        }
        if (y.i(this.d, "use_staging_launch_server")) {
            l.e = y.d(this.d, "use_staging_launch_server") ? "https://adc3-launch-server-staging.herokuapp.com/v4/launch" : "https://adc3-launch.adcolony.com/v4/launch";
        }
    }
}
