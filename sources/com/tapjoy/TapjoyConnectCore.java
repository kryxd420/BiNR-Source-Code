package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.internal.ao;
import com.tapjoy.internal.bk;
import com.tapjoy.internal.bq;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fe;
import com.tapjoy.internal.ff;
import com.tapjoy.internal.fl;
import com.tapjoy.internal.fs;
import com.tapjoy.internal.fw;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gi;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.hg;
import com.tapjoy.internal.hr;
import com.tapjoy.internal.ju;
import com.tapjoy.internal.kd;
import com.tapjoy.internal.w;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Document;

public class TapjoyConnectCore {
    private static float A = 1.0f;
    private static int B = 1;
    /* access modifiers changed from: private */
    public static String C = "";
    private static String D = "";
    public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0f;
    private static String E = "";
    private static String F = "";
    private static String G = "";
    private static String H = "";
    private static String I = "";
    private static String J = "";
    private static String K = "";
    private static String L = "";
    private static String M = "";
    private static String N = TapjoyConstants.TJC_PLUGIN_NATIVE;
    private static String O = "";
    private static String P = "";
    private static float Q = 1.0f;
    private static boolean R = false;
    private static String S = "";
    private static String T = "";
    private static String U = "";
    private static String V = "";
    private static String W = null;
    private static long Z = 0;
    protected static int a = 0;
    private static Integer aA;
    private static Long aB;
    private static Long aC;
    private static Long aD;
    private static String aE;
    private static Integer aF;
    private static Double aG;
    private static Double aH;
    private static Long aI;
    private static Integer aJ;
    private static Integer aK;
    private static Integer aL;
    private static String aM;
    private static String aN;
    private static String aO;
    private static String aP = "";
    private static String aQ = "";
    private static String aR = "";
    private static boolean aS = false;
    private static TJConnectListener aT = null;
    private static boolean aU = false;
    private static boolean ab;
    private static PackageManager ac;
    private static TapjoyGpsHelper ad;
    private static Hashtable ae = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    private static String af = "";
    private static Map ag = new ConcurrentHashMap();
    private static String ah;
    private static String ai;
    private static String aj;
    private static String ak;
    private static Integer al;
    private static String am;
    private static String an;
    private static Long ao;
    private static String ap;
    private static Integer aq;
    private static Integer ar;
    private static String as;
    private static String at;
    private static String au;
    private static String av;
    private static String aw;
    private static Set ax;
    private static Integer ay;
    private static Integer az;
    protected static int b = 0;
    protected static String c = "";
    protected static boolean d;
    protected static String e = "";
    protected static String f = "";
    private static Context g;
    private static String h;
    private static TapjoyConnectCore i;
    /* access modifiers changed from: private */
    public static TapjoyURLConnection j;
    private static TJConnectListener k;
    private static TJSetUserIDListener l;
    private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
    private static String n = "";
    private static String o = "";
    private static String p = "";
    private static String q = "";
    private static String r = "";
    private static String s = "";
    private static String t = "";
    private static String u = "";
    private static String v = "";
    private static String w = "";
    private static String x = "";
    private static String y = "";
    private static int z = 1;
    private long X = 0;
    private boolean Y = false;
    private boolean aa = false;

    public static String getConnectURL() {
        return TapjoyConfig.TJC_CONNECT_SERVICE_URL;
    }

    public static TapjoyConnectCore getInstance() {
        return i;
    }

    public static void requestTapjoyConnect(Context context, String str) {
        requestTapjoyConnect(context, str, (Hashtable) null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable) {
        requestTapjoyConnect(context, str, hashtable, (TJConnectListener) null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        try {
            fs fsVar = new fs(str);
            if (fsVar.a == fs.a.SDK_ANDROID) {
                h = str;
                v = fsVar.b;
                L = fsVar.c;
                M = fsVar.d;
                if (hashtable != null) {
                    ae.putAll(hashtable);
                    ge.b().a(hashtable);
                }
                hd.a(context).j = str;
                k = tJConnectListener;
                if (i == null) {
                    i = new TapjoyConnectCore();
                }
                TapjoyConnectCore tapjoyConnectCore = i;
                try {
                    a(context);
                    new Thread(new Runnable() {
                        public final void run() {
                            TapjoyConnectCore.a();
                            TapjoyConnectCore.this.completeConnectCall();
                        }
                    }).start();
                    tapjoyConnectCore.aa = true;
                } catch (TapjoyIntegrationException e2) {
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e2.getMessage()));
                    d();
                    fw.b.notifyObservers(Boolean.FALSE);
                } catch (TapjoyException e3) {
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e3.getMessage()));
                    d();
                    fw.b.notifyObservers(Boolean.FALSE);
                }
            } else {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
        } catch (IllegalArgumentException e4) {
            throw new TapjoyIntegrationException(e4.getMessage());
        }
    }

    public static void requestLimitedTapjoyConnect(Context context, String str, TJConnectListener tJConnectListener) {
        try {
            fs fsVar = new fs(str);
            if (fsVar.a == fs.a.SDK_ANDROID) {
                aP = fsVar.b;
                aQ = fsVar.c;
                if (i == null) {
                    i = new TapjoyConnectCore();
                }
                aT = tJConnectListener;
                TapjoyConnectCore tapjoyConnectCore = i;
                try {
                    a(context);
                    new Thread(new Runnable() {
                        public final void run() {
                            TapjoyConnectCore.a();
                            TapjoyConnectCore.this.completeLimitedConnectCall();
                        }
                    }).start();
                } catch (TapjoyIntegrationException e2) {
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e2.getMessage()));
                    e();
                    fw.b.notifyObservers(Boolean.FALSE);
                } catch (TapjoyException e3) {
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e3.getMessage()));
                    e();
                    fw.b.notifyObservers(Boolean.FALSE);
                }
                TapjoyLog.d("TapjoyConnect", "requestTapjoyConnect function complete");
                return;
            }
            throw new IllegalArgumentException("The given API key was not for Android.");
        } catch (IllegalArgumentException e4) {
            TapjoyLog.d("TapjoyConnect", e4.getMessage());
            throw new TapjoyIntegrationException(e4.getMessage());
        }
    }

    private static void d() {
        if (!ju.c(M)) {
            hd.a().a(g, h, "12.1.0", TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, M, L);
        }
        if (k != null) {
            k.onConnectFailure();
        }
    }

    private static void e() {
        if (aT != null) {
            aT.onConnectFailure();
        }
    }

    public void appPause() {
        this.Y = true;
    }

    public void appResume() {
        if (this.Y) {
            o();
            this.Y = false;
        }
    }

    public static Map getURLParams() {
        Map genericURLParams = getGenericURLParams();
        genericURLParams.putAll(getTimeStampAndVerifierParams());
        return genericURLParams;
    }

    public static Map getLimitedURLParams() {
        Map limitedGenericURLParams = getLimitedGenericURLParams();
        limitedGenericURLParams.putAll(getLimitedTimeStampAndVerifierParams());
        return limitedGenericURLParams;
    }

    public static Map getGenericURLParams() {
        Map f2 = f();
        TapjoyUtil.safePut(f2, TapjoyConstants.TJC_APP_ID, v, true);
        return f2;
    }

    public static Map getLimitedGenericURLParams() {
        Map f2 = f();
        TapjoyUtil.safePut(f2, TapjoyConstants.TJC_APP_ID, aP, true);
        TapjoyUtil.safePut(f2, TapjoyConstants.TJC_APP_GROUP_ID, aR, true);
        TapjoyUtil.safePut(f2, TapjoyConstants.TJC_LIMITED, "true", true);
        return f2;
    }

    private static Map f() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PLUGIN, N, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_SDK_TYPE, O, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_ID, v, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_VERSION, x, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_REVISION, TapjoyRevision.GIT_REVISION, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_BRIDGE_VERSION, y, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_VERSION_NAME, w, true);
        hashMap2.putAll(hashMap3);
        HashMap hashMap4 = new HashMap();
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_NAME, r, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_PLATFORM, D, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, u, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_MANUFACTURER, s, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_TYPE_NAME, t, true);
        StringBuilder sb = new StringBuilder();
        sb.append(B);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_SCREEN_LAYOUT_SIZE, sb.toString(), true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_STORE_NAME, K, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_STORE_VIEW, String.valueOf(R), true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_CARRIER_NAME, E, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_CARRIER_COUNTRY_CODE, F, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_MOBILE_NETWORK_CODE, H, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_MOBILE_COUNTRY_CODE, G, true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, Locale.getDefault().getCountry(), true);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_LANGUAGE, Locale.getDefault().getLanguage(), true);
        I = getConnectionType();
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_CONNECTION_TYPE, I, true);
        J = getConnectionSubType();
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_CONNECTION_SUBTYPE, J, true);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z);
        TapjoyUtil.safePut(hashMap4, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, sb2.toString(), true);
        hashMap2.putAll(hashMap4);
        HashMap hashMap5 = new HashMap();
        if (m()) {
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_ADVERTISING_ID, c, true);
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_AD_TRACKING_ENABLED, String.valueOf(d), true);
        }
        if (!n()) {
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_ANDROID_ID, n, true);
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, p, true);
        }
        TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_INSTALL_ID, q, true);
        TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_USER_ID, C, true);
        TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_ADVERTISING_ID_CHECK_DISABLED, e, true);
        TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_PERSISTENT_ID_DISABLED, f, true);
        if (a != 0) {
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_PACKAGED_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(a), true);
        }
        if (b != 0) {
            TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_DEVICE_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(b), true);
        }
        if (o == null || o.length() == 0 || System.currentTimeMillis() - Z > TapjoyConstants.SESSION_ID_INACTIVITY_TIME) {
            o = o();
        } else {
            Z = System.currentTimeMillis();
        }
        TapjoyUtil.safePut(hashMap5, TapjoyConstants.TJC_SESSION_ID, o, true);
        hashMap2.putAll(hashMap5);
        HashMap hashMap6 = new HashMap();
        TapjoyUtil.safePut(hashMap6, TapjoyConstants.TJC_APP_GROUP_ID, S, true);
        TapjoyUtil.safePut(hashMap6, TapjoyConstants.TJC_STORE, T, true);
        TapjoyUtil.safePut(hashMap6, TapjoyConstants.TJC_ANALYTICS_API_KEY, U, true);
        TapjoyUtil.safePut(hashMap6, TapjoyConstants.TJC_MANAGED_DEVICE_ID, V, true);
        hashMap2.putAll(hashMap6);
        gi a2 = gi.a();
        HashMap hashMap7 = new HashMap();
        if (a2.a != null) {
            TapjoyUtil.safePut(hashMap7, "gdpr", a2.a.booleanValue() ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0", true);
        }
        if (!ao.a(a2.b)) {
            TapjoyUtil.safePut(hashMap7, "cgdpr", a2.b, true);
        }
        hashMap2.putAll(hashMap7);
        if (!(TapjoyCache.getInstance() == null || TapjoyCache.getInstance().getCachedOfferIDs() == null || TapjoyCache.getInstance().getCachedOfferIDs().length() <= 0)) {
            TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CACHED_OFFERS, TapjoyCache.getInstance().getCachedOfferIDs(), true);
        }
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CURRENCY_MULTIPLIER, Float.toString(Q), true);
        hashMap.putAll(hashMap2);
        HashMap hashMap8 = new HashMap();
        h();
        HashMap hashMap9 = new HashMap();
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_ANALYTICS_ID, ah, true);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_PACKAGE_ID, ai, true);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_PACKAGE_SIGN, aj, true);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY, aJ);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH, aK);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT, aL);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_DEVICE_COUNTRY_SIM, aM, true);
        TapjoyUtil.safePut(hashMap9, TapjoyConstants.TJC_DEVICE_TIMEZONE, aN, true);
        hashMap8.putAll(hashMap9);
        HashMap hashMap10 = new HashMap();
        TapjoyUtil.safePut(hashMap10, TapjoyConstants.TJC_PACKAGE_VERSION, ak, true);
        TapjoyUtil.safePut(hashMap10, TapjoyConstants.TJC_PACKAGE_REVISION, al);
        TapjoyUtil.safePut(hashMap10, TapjoyConstants.TJC_PACKAGE_DATA_VERSION, am, true);
        TapjoyUtil.safePut(hashMap10, TapjoyConstants.TJC_INSTALLER, an, true);
        if (ju.c(K)) {
            TapjoyUtil.safePut(hashMap10, TapjoyConstants.TJC_STORE_NAME, aO, true);
        }
        hashMap8.putAll(hashMap10);
        hashMap8.putAll(g());
        hashMap.putAll(hashMap8);
        return hashMap;
    }

    public static Map getTimeStampAndVerifierParams() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String a2 = a(currentTimeMillis);
        HashMap hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, "timestamp", String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_VERIFIER, a2, true);
        return hashMap;
    }

    public static Map getLimitedTimeStampAndVerifierParams() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String b2 = b(currentTimeMillis);
        HashMap hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, "timestamp", String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_VERIFIER, b2, true);
        return hashMap;
    }

    private static Map g() {
        HashMap hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_INSTALLED, ao);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_REFERRER, ap, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_LEVEL, aq);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_FRIEND_COUNT, ar);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_1, as, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_2, at, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_3, au, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_4, av, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_5, aw, true);
        int i2 = 0;
        for (String safePut : ax) {
            StringBuilder sb = new StringBuilder("user_tags[");
            int i3 = i2 + 1;
            sb.append(i2);
            sb.append("]");
            TapjoyUtil.safePut(hashMap, sb.toString(), safePut, true);
            i2 = i3;
        }
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY, ay);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY, az);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_COUNT, aA);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_LENGTH, aB);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_AT, aC);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_LENGTH, aD);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_CURRENCY, aE, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_COUNT, aF);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_PRICE, aG);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_PRICE, aH);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_AT, aI);
        return hashMap;
    }

    private static boolean a(Context context) {
        WifiInfo connectionInfo;
        g = context;
        ac = context.getPackageManager();
        gi.a().a(context);
        ge.a().a(context);
        ad = new TapjoyGpsHelper(g);
        if (j == null) {
            j = new TapjoyURLConnection();
        }
        if (ae == null) {
            ae = new Hashtable();
        }
        j();
        int identifier = g.getResources().getIdentifier("raw/tapjoy_config", (String) null, g.getPackageName());
        Properties properties = new Properties();
        try {
            properties.load(g.getResources().openRawResource(identifier));
            a(properties);
        } catch (Exception unused) {
        }
        if (ju.c(getConnectFlagValue("unit_test_mode"))) {
            k();
        }
        String string = Settings.Secure.getString(g.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        n = string;
        if (string != null) {
            n = n.toLowerCase();
        }
        try {
            boolean z2 = false;
            w = ac.getPackageInfo(g.getPackageName(), 0).versionName;
            t = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
            D = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
            r = Build.MODEL;
            s = Build.MANUFACTURER;
            u = Build.VERSION.RELEASE;
            x = "12.1.0";
            y = TapjoyConstants.TJC_BRIDGE_VERSION_NUMBER;
            try {
                if (Build.VERSION.SDK_INT > 3) {
                    TapjoyDisplayMetricsUtil tapjoyDisplayMetricsUtil = new TapjoyDisplayMetricsUtil(g);
                    z = tapjoyDisplayMetricsUtil.getScreenDensityDPI();
                    A = tapjoyDisplayMetricsUtil.getScreenDensityScale();
                    B = tapjoyDisplayMetricsUtil.getScreenLayoutSize();
                }
            } catch (Exception e2) {
                TapjoyLog.e("TapjoyConnect", "Error getting screen density/dimensions/layout: " + e2.toString());
            }
            if (f("android.permission.ACCESS_WIFI_STATE")) {
                try {
                    WifiManager wifiManager = (WifiManager) g.getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
                    if (!(wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null)) {
                        String macAddress = connectionInfo.getMacAddress();
                        p = macAddress;
                        if (macAddress != null) {
                            p = p.replace(":", "").toLowerCase();
                        }
                    }
                } catch (Exception e3) {
                    TapjoyLog.e("TapjoyConnect", "Error getting device mac address: " + e3.toString());
                }
            } else {
                TapjoyLog.d("TapjoyConnect", "*** ignore macAddress");
            }
            TelephonyManager telephonyManager = (TelephonyManager) g.getSystemService("phone");
            if (telephonyManager != null) {
                E = telephonyManager.getNetworkOperatorName();
                F = telephonyManager.getNetworkCountryIso();
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator != null && (networkOperator.length() == 5 || networkOperator.length() == 6)) {
                    G = networkOperator.substring(0, 3);
                    H = networkOperator.substring(3);
                }
            }
            SharedPreferences sharedPreferences = g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
            String string2 = sharedPreferences.getString(TapjoyConstants.PREF_INSTALL_ID, "");
            q = string2;
            if (string2 == null || q.length() == 0) {
                try {
                    q = TapjoyUtil.SHA256(UUID.randomUUID().toString() + System.currentTimeMillis());
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(TapjoyConstants.PREF_INSTALL_ID, q);
                    edit.apply();
                } catch (Exception e4) {
                    TapjoyLog.e("TapjoyConnect", "Error generating install id: " + e4.toString());
                }
            }
            if (getConnectFlagValue(TapjoyConnectFlag.STORE_NAME) != null && getConnectFlagValue(TapjoyConnectFlag.STORE_NAME).length() > 0) {
                K = getConnectFlagValue(TapjoyConnectFlag.STORE_NAME);
                if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(K)) {
                    TapjoyLog.w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + K);
                }
            }
            try {
                String str = K;
                Intent intent = new Intent("android.intent.action.VIEW");
                if (str.length() <= 0) {
                    intent.setData(Uri.parse("market://details"));
                    if (ac.queryIntentActivities(intent, 0).size() > 0) {
                        z2 = true;
                    }
                } else if (str.equals(TapjoyConnectFlag.STORE_GFAN)) {
                    z2 = e("com.mappn.gfan");
                } else if (str.equals(TapjoyConnectFlag.STORE_SKT)) {
                    z2 = e("com.skt.skaf.TSCINSTALL");
                }
                R = z2;
            } catch (Exception e5) {
                TapjoyLog.e("TapjoyConnect", "Error trying to detect store intent on devicee: " + e5.toString());
            }
            h();
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).length() > 0) {
                f = getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).length() > 0) {
                e = getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.USER_ID) != null && getConnectFlagValue(TapjoyConnectFlag.USER_ID).length() > 0) {
                TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getConnectFlagValue(TapjoyConnectFlag.USER_ID));
                setUserID(getConnectFlagValue(TapjoyConnectFlag.USER_ID), (TJSetUserIDListener) null);
            }
            P = TapjoyUtil.getRedirectDomain(getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL));
            if (ae != null) {
                i();
            }
            return true;
        } catch (PackageManager.NameNotFoundException e6) {
            throw new TapjoyException(e6.getMessage());
        }
    }

    private static void h() {
        ff a2 = hd.a(g).a(true);
        fe feVar = a2.d;
        ah = feVar.h;
        ai = feVar.r;
        aj = feVar.s;
        aJ = feVar.m;
        aK = feVar.n;
        aL = feVar.o;
        aM = feVar.u;
        aN = feVar.q;
        ey eyVar = a2.e;
        ak = eyVar.e;
        al = eyVar.f;
        am = eyVar.g;
        an = eyVar.h;
        aO = eyVar.i;
        fl flVar = a2.f;
        ao = flVar.s;
        ap = flVar.t;
        aq = flVar.J;
        ar = flVar.K;
        as = flVar.L;
        at = flVar.M;
        au = flVar.N;
        av = flVar.O;
        aw = flVar.P;
        ax = new HashSet(flVar.Q);
        ay = flVar.u;
        az = flVar.v;
        aA = flVar.x;
        aB = flVar.y;
        aC = flVar.z;
        aD = flVar.A;
        aE = flVar.B;
        aF = flVar.C;
        aG = flVar.D;
        aH = flVar.F;
        aI = flVar.E;
    }

    private static void i() {
        TapjoyLog.i("TapjoyConnect", "Connect Flags:");
        TapjoyLog.i("TapjoyConnect", "--------------------");
        for (Map.Entry entry : ae.entrySet()) {
            TapjoyLog.i("TapjoyConnect", "key: " + ((String) entry.getKey()) + ", value: " + Uri.encode(entry.getValue().toString()));
        }
        TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL) + "]");
        TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + P + "]");
        TapjoyLog.i("TapjoyConnect", "--------------------");
    }

    private static void j() {
        try {
            if (ac != null) {
                ApplicationInfo applicationInfo = ac.getApplicationInfo(g.getPackageName(), 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    TapjoyLog.d("TapjoyConnect", "No metadata present.");
                    return;
                }
                for (String str : TapjoyConnectFlag.FLAG_ARRAY) {
                    String string = applicationInfo.metaData.getString("tapjoy." + str);
                    if (string != null) {
                        TapjoyLog.d("TapjoyConnect", "Found manifest flag: " + str + ", " + string);
                        a(str, string);
                    }
                }
                TapjoyLog.d("TapjoyConnect", "Metadata successfully loaded");
            }
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + e2.toString()));
        }
    }

    private static void a(Properties properties) {
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            try {
                String str = (String) keys.nextElement();
                a(str, (String) properties.get(str));
            } catch (ClassCastException unused) {
                TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0135, code lost:
        throw new com.tapjoy.TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + ((java.lang.String) m.get(r3)));
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x011a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void k() {
        /*
            android.content.pm.PackageManager r0 = ac     // Catch:{ NameNotFoundException -> 0x022f }
            android.content.Context r1 = g     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ NameNotFoundException -> 0x022f }
            r2 = 1
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch:{ NameNotFoundException -> 0x022f }
            android.content.pm.ActivityInfo[] r0 = r0.activities     // Catch:{ NameNotFoundException -> 0x022f }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ NameNotFoundException -> 0x022f }
            if (r0 == 0) goto L_0x0136
            java.util.Iterator r0 = r0.iterator()     // Catch:{ NameNotFoundException -> 0x022f }
        L_0x0019:
            boolean r1 = r0.hasNext()     // Catch:{ NameNotFoundException -> 0x022f }
            if (r1 == 0) goto L_0x0136
            java.lang.Object r1 = r0.next()     // Catch:{ NameNotFoundException -> 0x022f }
            android.content.pm.ActivityInfo r1 = (android.content.pm.ActivityInfo) r1     // Catch:{ NameNotFoundException -> 0x022f }
            java.util.Vector r3 = m     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r4 = r1.name     // Catch:{ NameNotFoundException -> 0x022f }
            boolean r3 = r3.contains(r4)     // Catch:{ NameNotFoundException -> 0x022f }
            if (r3 == 0) goto L_0x0019
            java.util.Vector r3 = m     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r4 = r1.name     // Catch:{ NameNotFoundException -> 0x022f }
            int r3 = r3.indexOf(r4)     // Catch:{ NameNotFoundException -> 0x022f }
            java.util.Vector r4 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.util.Vector r4 = new java.util.Vector     // Catch:{ ClassNotFoundException -> 0x011a }
            r4.<init>()     // Catch:{ ClassNotFoundException -> 0x011a }
            int r5 = r1.configChanges     // Catch:{ ClassNotFoundException -> 0x011a }
            r6 = 128(0x80, float:1.794E-43)
            r5 = r5 & r6
            if (r5 == r6) goto L_0x0053
            java.lang.String r5 = "orientation"
            r4.add(r5)     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x0053:
            int r5 = r1.configChanges     // Catch:{ ClassNotFoundException -> 0x011a }
            r6 = 32
            r5 = r5 & r6
            if (r5 == r6) goto L_0x005f
            java.lang.String r5 = "keyboardHidden"
            r4.add(r5)     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x005f:
            int r5 = r4.size()     // Catch:{ ClassNotFoundException -> 0x011a }
            if (r5 == 0) goto L_0x00b7
            int r0 = r4.size()     // Catch:{ ClassNotFoundException -> 0x011a }
            if (r0 != r2) goto L_0x0091
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.<init>()     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = r4.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = " property is not specified in manifest configChanges for "
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.util.Vector r2 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r1 = r1.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            r0.<init>(r1)     // Catch:{ ClassNotFoundException -> 0x011a }
            throw r0     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x0091:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.<init>()     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = r4.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = " properties are not specified in manifest configChanges for "
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.util.Vector r2 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r1 = r1.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            r0.<init>(r1)     // Catch:{ ClassNotFoundException -> 0x011a }
            throw r0     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x00b7:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ ClassNotFoundException -> 0x011a }
            r5 = 13
            if (r4 < r5) goto L_0x00df
            int r4 = r1.configChanges     // Catch:{ ClassNotFoundException -> 0x011a }
            r5 = 1024(0x400, float:1.435E-42)
            r4 = r4 & r5
            if (r4 == r5) goto L_0x00df
            java.lang.String r4 = "TapjoyConnect"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r6 = "WARNING -- screenSize property is not specified in manifest configChanges for "
            r5.<init>(r6)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.util.Vector r6 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Object r6 = r6.get(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ ClassNotFoundException -> 0x011a }
            r5.append(r6)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r5 = r5.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            com.tapjoy.TapjoyLog.w(r4, r5)     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x00df:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ ClassNotFoundException -> 0x011a }
            r5 = 11
            if (r4 < r5) goto L_0x0113
            java.lang.String r4 = r1.name     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r5 = "com.tapjoy.TJAdUnitActivity"
            boolean r4 = r4.equals(r5)     // Catch:{ ClassNotFoundException -> 0x011a }
            if (r4 == 0) goto L_0x0113
            int r1 = r1.flags     // Catch:{ ClassNotFoundException -> 0x011a }
            r4 = 512(0x200, float:7.175E-43)
            r1 = r1 & r4
            if (r1 != r4) goto L_0x00f7
            goto L_0x0113
        L_0x00f7:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = "'hardwareAccelerated' property not specified in manifest for "
            r1.<init>(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.util.Vector r2 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.append(r2)     // Catch:{ ClassNotFoundException -> 0x011a }
            java.lang.String r1 = r1.toString()     // Catch:{ ClassNotFoundException -> 0x011a }
            r0.<init>(r1)     // Catch:{ ClassNotFoundException -> 0x011a }
            throw r0     // Catch:{ ClassNotFoundException -> 0x011a }
        L_0x0113:
            java.util.Vector r1 = m     // Catch:{ ClassNotFoundException -> 0x011a }
            r1.remove(r3)     // Catch:{ ClassNotFoundException -> 0x011a }
            goto L_0x0019
        L_0x011a:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r2 = "[ClassNotFoundException] Could not find dependency class "
            r1.<init>(r2)     // Catch:{ NameNotFoundException -> 0x022f }
            java.util.Vector r2 = m     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NameNotFoundException -> 0x022f }
            r1.append(r2)     // Catch:{ NameNotFoundException -> 0x022f }
            java.lang.String r1 = r1.toString()     // Catch:{ NameNotFoundException -> 0x022f }
            r0.<init>(r1)     // Catch:{ NameNotFoundException -> 0x022f }
            throw r0     // Catch:{ NameNotFoundException -> 0x022f }
        L_0x0136:
            java.util.Vector r0 = m
            int r0 = r0.size()
            if (r0 == 0) goto L_0x0196
            java.util.Vector r0 = m
            int r0 = r0.size()
            if (r0 != r2) goto L_0x016e
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Missing "
            r1.<init>(r2)
            java.util.Vector r2 = m
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = " dependency class in manifest: "
            r1.append(r2)
            java.util.Vector r2 = m
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x016e:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Missing "
            r1.<init>(r2)
            java.util.Vector r2 = m
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = " dependency classes in manifest: "
            r1.append(r2)
            java.util.Vector r2 = m
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0196:
            l()
            java.lang.String r0 = "com.tapjoy.TJAdUnitJSBridge"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0227 }
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ NoSuchMethodException -> 0x021f }
            r2 = 0
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            r1[r2] = r3     // Catch:{ NoSuchMethodException -> 0x021f }
            java.lang.String r2 = "closeRequested"
            r0.getMethod(r2, r1)     // Catch:{ NoSuchMethodException -> 0x021f }
            java.lang.String r0 = "mraid.js"
            java.lang.Object r0 = com.tapjoy.TapjoyUtil.getResource(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x01bd
            java.lang.String r0 = "js/mraid.js"
            android.content.Context r1 = g
            java.lang.String r0 = com.tapjoy.TapjoyUtil.copyTextFromJarIntoString(r0, r1)
        L_0x01bd:
            if (r0 != 0) goto L_0x01f1
            android.content.Context r1 = g     // Catch:{ Exception -> 0x01f1 }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = "mraid"
            java.lang.String r3 = "raw"
            android.content.Context r4 = g     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x01f1 }
            int r1 = r1.getIdentifier(r2, r3, r4)     // Catch:{ Exception -> 0x01f1 }
            android.content.Context r2 = g     // Catch:{ Exception -> 0x01f1 }
            android.content.res.Resources r2 = r2.getResources()     // Catch:{ Exception -> 0x01f1 }
            java.io.InputStream r1 = r2.openRawResource(r1)     // Catch:{ Exception -> 0x01f1 }
            int r2 = r1.available()     // Catch:{ Exception -> 0x01f1 }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x01f1 }
            r1.read(r2)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x01f1 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r0 = "mraid.js"
            com.tapjoy.TapjoyUtil.setResource(r0, r1)     // Catch:{ Exception -> 0x01f0 }
        L_0x01f0:
            r0 = r1
        L_0x01f1:
            if (r0 == 0) goto L_0x0217
            java.lang.String r0 = "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
            java.lang.String r0 = getConnectFlagValue(r0)
            if (r0 == 0) goto L_0x0211
            java.lang.String r0 = "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
            java.lang.String r0 = getConnectFlagValue(r0)
            java.lang.String r1 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0211
            java.lang.String r0 = "TapjoyConnect"
            java.lang.String r1 = "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services."
            com.tapjoy.TapjoyLog.i(r0, r1)
            return
        L_0x0211:
            com.tapjoy.TapjoyGpsHelper r0 = ad
            r0.checkGooglePlayIntegration()
            return
        L_0x0217:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.String r1 = "ClassNotFoundException: mraid.js was not found."
            r0.<init>(r1)
            throw r0
        L_0x021f:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.String r1 = "Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information."
            r0.<init>(r1)
            throw r0
        L_0x0227:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.String r1 = "ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found."
            r0.<init>(r1)
            throw r0
        L_0x022f:
            com.tapjoy.TapjoyIntegrationException r0 = new com.tapjoy.TapjoyIntegrationException
            java.lang.String r1 = "NameNotFoundException: Could not find package."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyConnectCore.k():void");
    }

    private static void l() {
        Vector vector = new Vector();
        for (String str : TapjoyConstants.dependencyPermissions) {
            if (!f(str)) {
                vector.add(str);
            }
        }
        if (vector.size() == 0) {
            Vector vector2 = new Vector();
            for (String str2 : TapjoyConstants.optionalPermissions) {
                if (!f(str2)) {
                    vector2.add(str2);
                }
            }
            if (vector2.size() == 0) {
                return;
            }
            if (vector2.size() == 1) {
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
                return;
            }
            TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
        } else if (vector.size() == 1) {
            throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + vector.toString());
        } else {
            throw new TapjoyIntegrationException("Missing " + vector.size() + " permissions in manifest: " + vector.toString());
        }
    }

    private static boolean m() {
        return c != null && c.length() > 0;
    }

    private static boolean n() {
        return m() && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).equals("true");
    }

    private static boolean a(String str, boolean z2) {
        bq bqVar;
        long j2;
        bq bqVar2 = null;
        try {
            bqVar = bq.b(str);
            try {
                Map d2 = bqVar.d();
                String a2 = ju.a((String) d2.get(TapjoyConstants.TJC_APP_GROUP_ID));
                String a3 = ju.a((String) d2.get(TapjoyConstants.TJC_STORE));
                String a4 = ju.a((String) d2.get(TapjoyConstants.TJC_ANALYTICS_API_KEY));
                String a5 = ju.a((String) d2.get(TapjoyConstants.TJC_MANAGED_DEVICE_ID));
                String a6 = ju.a((String) d2.get(TapjoyConstants.TJC_PACKAGE_NAMES));
                Object obj = d2.get("cache_max_age");
                fs fsVar = new fs(a4);
                if (fsVar.a == fs.a.RPC_ANALYTICS) {
                    String a7 = fs.a(fsVar.b);
                    String str2 = fsVar.c;
                    if (a2 == null) {
                        a2 = a7;
                    }
                    Object obj2 = obj;
                    hd.a().a(g, a4, "12.1.0", TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, a7, str2);
                    S = a2;
                    T = a3;
                    U = a4;
                    V = a5;
                    ArrayList arrayList = new ArrayList();
                    if (a6 != null) {
                        for (String trim : a6.split(",")) {
                            String trim2 = trim.trim();
                            if (trim2.length() > 0) {
                                arrayList.add(trim2);
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        a((List) arrayList);
                    }
                    bqVar.close();
                    if (!z2) {
                        if (obj2 instanceof String) {
                            try {
                                j2 = Long.parseLong(((String) obj2).trim());
                            } catch (NumberFormatException unused) {
                            }
                        } else {
                            if (obj2 instanceof Number) {
                                j2 = ((Number) obj2).longValue();
                            }
                            j2 = 0;
                        }
                        if (j2 <= 0) {
                            TapjoyAppSettings.getInstance().removeConnectResult();
                        } else {
                            TapjoyAppSettings.getInstance().saveConnectResultAndParams(str, q(), (j2 * 1000) + w.b());
                        }
                        ge a8 = ge.a();
                        Object obj3 = d2.get(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS);
                        if (obj3 instanceof Map) {
                            try {
                                a8.a.a((Map) obj3);
                                a8.c().edit().putString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, bk.a(obj3)).apply();
                            } catch (Exception unused2) {
                            }
                        } else if (obj3 == null) {
                            a8.a.a((Map) null);
                            a8.c().edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).apply();
                        }
                    }
                    kd.a((Closeable) null);
                    return true;
                }
                throw new IOException("Invalid analytics_api_key");
            } catch (IOException e2) {
                e = e2;
                bqVar2 = bqVar;
                TapjoyLog.v("TapjoyConnect", e.getMessage());
                kd.a(bqVar2);
                return false;
            } catch (RuntimeException e3) {
                e = e3;
                bqVar2 = bqVar;
                TapjoyLog.v("TapjoyConnect", e.getMessage());
                kd.a(bqVar2);
                return false;
            } catch (Throwable th) {
                th = th;
                kd.a(bqVar);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            TapjoyLog.v("TapjoyConnect", e.getMessage());
            kd.a(bqVar2);
            return false;
        } catch (RuntimeException e5) {
            e = e5;
            TapjoyLog.v("TapjoyConnect", e.getMessage());
            kd.a(bqVar2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bqVar = bqVar2;
            kd.a(bqVar);
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: com.tapjoy.internal.bq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r8v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r8v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean c(java.lang.String r8) {
        /*
            r0 = 0
            com.tapjoy.internal.bq r8 = com.tapjoy.internal.bq.b(r8)     // Catch:{ IOException -> 0x007c, RuntimeException -> 0x006e, all -> 0x0069 }
            java.util.Map r1 = r8.d()     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r2 = "app_group_id"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r2 = com.tapjoy.internal.ju.a(r2)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r3 = "store"
            java.lang.Object r3 = r1.get(r3)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r3 = com.tapjoy.internal.ju.a(r3)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r4 = "analytics_api_key"
            java.lang.Object r4 = r1.get(r4)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r4 = com.tapjoy.internal.ju.a(r4)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r5 = "managed_device_id"
            java.lang.Object r1 = r1.get(r5)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r1 = com.tapjoy.internal.ju.a(r1)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            com.tapjoy.internal.fs r5 = new com.tapjoy.internal.fs     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            com.tapjoy.internal.fs$a r4 = r5.a     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            com.tapjoy.internal.fs$a r6 = com.tapjoy.internal.fs.a.RPC_ANALYTICS     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            if (r4 != r6) goto L_0x005b
            java.lang.String r4 = r5.b     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r4 = com.tapjoy.internal.fs.a(r4)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            if (r2 != 0) goto L_0x004d
            r2 = r4
        L_0x004d:
            aR = r2     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            T = r3     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            V = r1     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            r8.close()     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            com.tapjoy.internal.kd.a(r0)
            r8 = 1
            return r8
        L_0x005b:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            java.lang.String r1 = "Invalid analytics_api_key"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
            throw r0     // Catch:{ IOException -> 0x0067, RuntimeException -> 0x0065 }
        L_0x0063:
            r0 = move-exception
            goto L_0x008e
        L_0x0065:
            r0 = move-exception
            goto L_0x0072
        L_0x0067:
            r0 = move-exception
            goto L_0x0080
        L_0x0069:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
            goto L_0x008e
        L_0x006e:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
        L_0x0072:
            java.lang.String r1 = "TapjoyConnect"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0063 }
            com.tapjoy.TapjoyLog.v(r1, r0)     // Catch:{ all -> 0x0063 }
            goto L_0x0089
        L_0x007c:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
        L_0x0080:
            java.lang.String r1 = "TapjoyConnect"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0063 }
            com.tapjoy.TapjoyLog.v(r1, r0)     // Catch:{ all -> 0x0063 }
        L_0x0089:
            com.tapjoy.internal.kd.a(r8)
            r8 = 0
            return r8
        L_0x008e:
            com.tapjoy.internal.kd.a(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyConnectCore.c(java.lang.String):boolean");
    }

    private static synchronized void a(List list) {
        synchronized (TapjoyConnectCore.class) {
            af = "";
            for (ApplicationInfo next : ac.getInstalledApplications(0)) {
                if ((next.flags & 1) != 1 && list.contains(next.packageName)) {
                    TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + next.packageName);
                    if (af.length() > 0) {
                        af += ",";
                    }
                    af += next.packageName;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: com.tapjoy.internal.bq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v12 */
    /* JADX WARNING: type inference failed for: r4v16 */
    /* JADX WARNING: type inference failed for: r4v18 */
    /* JADX WARNING: type inference failed for: r4v19 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean d(java.lang.String r4) {
        /*
            r0 = 0
            com.tapjoy.internal.bq r4 = com.tapjoy.internal.bq.b(r4)     // Catch:{ IOException -> 0x003d, RuntimeException -> 0x002f, all -> 0x002a }
            boolean r1 = r4.a()     // Catch:{ IOException -> 0x0028, RuntimeException -> 0x0026 }
            if (r1 == 0) goto L_0x001d
            r4.s()     // Catch:{ IOException -> 0x0028, RuntimeException -> 0x0026 }
            java.lang.String r1 = "TapjoyConnect"
            java.lang.String r2 = "Successfully sent completed Pay-Per-Action to Tapjoy server."
            com.tapjoy.TapjoyLog.d(r1, r2)     // Catch:{ IOException -> 0x0028, RuntimeException -> 0x0026 }
            r4.close()     // Catch:{ IOException -> 0x0028, RuntimeException -> 0x0026 }
            com.tapjoy.internal.kd.a(r0)
            r4 = 1
            return r4
        L_0x001d:
            r4.close()     // Catch:{ IOException -> 0x0028, RuntimeException -> 0x0026 }
            com.tapjoy.internal.kd.a(r0)
            goto L_0x004d
        L_0x0024:
            r0 = move-exception
            goto L_0x005d
        L_0x0026:
            r0 = move-exception
            goto L_0x0033
        L_0x0028:
            r0 = move-exception
            goto L_0x0041
        L_0x002a:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L_0x005d
        L_0x002f:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L_0x0033:
            java.lang.String r1 = "TapjoyConnect"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0024 }
            com.tapjoy.TapjoyLog.v(r1, r0)     // Catch:{ all -> 0x0024 }
            goto L_0x004a
        L_0x003d:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L_0x0041:
            java.lang.String r1 = "TapjoyConnect"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0024 }
            com.tapjoy.TapjoyLog.v(r1, r0)     // Catch:{ all -> 0x0024 }
        L_0x004a:
            com.tapjoy.internal.kd.a(r4)
        L_0x004d:
            java.lang.String r4 = "TapjoyConnect"
            com.tapjoy.TapjoyErrorMessage r0 = new com.tapjoy.TapjoyErrorMessage
            com.tapjoy.TapjoyErrorMessage$ErrorType r1 = com.tapjoy.TapjoyErrorMessage.ErrorType.SDK_ERROR
            java.lang.String r2 = "Completed Pay-Per-Action call failed."
            r0.<init>(r1, r2)
            com.tapjoy.TapjoyLog.e((java.lang.String) r4, (com.tapjoy.TapjoyErrorMessage) r0)
            r4 = 0
            return r4
        L_0x005d:
            com.tapjoy.internal.kd.a(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyConnectCore.d(java.lang.String):boolean");
    }

    public void release() {
        i = null;
        j = null;
        TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
    }

    public static String getAppID() {
        return v;
    }

    public static String getLimitedAppID() {
        return aP;
    }

    public static String getUserID() {
        return C;
    }

    public static String getHostURL() {
        return getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL);
    }

    public static String getPlacementURL() {
        return getConnectFlagValue(TapjoyConnectFlag.PLACEMENT_URL);
    }

    public static String getRedirectDomain() {
        return P;
    }

    public static String getCarrierName() {
        return E;
    }

    public static String getConnectionType() {
        String str = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                int type = connectivityManager.getActiveNetworkInfo().getType();
                str = (type == 1 || type == 6) ? TapjoyConstants.TJC_CONNECTION_TYPE_WIFI : TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
                TapjoyLog.d("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                TapjoyLog.d("TapjoyConnect", "connection_type: " + str);
            }
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + e2.toString());
        }
        return str;
    }

    public static String getConnectionSubType() {
        String str;
        Exception e2;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            str = connectivityManager.getActiveNetworkInfo().getSubtypeName();
            try {
                TapjoyLog.d("TapjoyConnect", "connection_sub_type: " + str);
            } catch (Exception e3) {
                e2 = e3;
            }
            return str;
        } catch (Exception e4) {
            Exception exc = e4;
            str = "";
            e2 = exc;
            TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + e2.toString());
            return str;
        }
    }

    private static boolean e(String str) {
        for (ApplicationInfo applicationInfo : ac.getInstalledApplications(0)) {
            if (applicationInfo.packageName.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private static String o() {
        TapjoyLog.i("TapjoyConnect", "generating sessionID...");
        String str = null;
        try {
            String SHA256 = TapjoyUtil.SHA256((System.currentTimeMillis() / 1000) + v);
            try {
                Z = System.currentTimeMillis();
                return SHA256;
            } catch (Exception e2) {
                String str2 = SHA256;
                e = e2;
                str = str2;
                TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + e.toString());
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + e.toString());
            return str;
        }
    }

    public static Context getContext() {
        return g;
    }

    private static String p() {
        if (n()) {
            return c;
        }
        boolean z2 = true;
        if (p != null && p.length() > 0) {
            return p;
        }
        if (m()) {
            return c;
        }
        if (n == null || n.length() <= 0) {
            z2 = false;
        }
        if (z2) {
            return n;
        }
        TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
        return null;
    }

    private static String a(long j2) {
        try {
            return TapjoyUtil.SHA256(v + ":" + p() + ":" + j2 + ":" + L);
        } catch (Exception e2) {
            TapjoyErrorMessage.ErrorType errorType = TapjoyErrorMessage.ErrorType.SDK_ERROR;
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(errorType, "Error in computing verifier value -- " + e2.toString()));
            return "";
        }
    }

    private static String b(long j2) {
        try {
            return TapjoyUtil.SHA256(aP + ":" + p() + ":" + j2 + ":" + aQ);
        } catch (Exception e2) {
            TapjoyErrorMessage.ErrorType errorType = TapjoyErrorMessage.ErrorType.SDK_ERROR;
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(errorType, "Error in computing verifier value -- " + e2.toString()));
            return "";
        }
    }

    public static String getAwardCurrencyVerifier(long j2, int i2, String str) {
        try {
            return TapjoyUtil.SHA256(v + ":" + p() + ":" + j2 + ":" + L + ":" + i2 + ":" + str);
        } catch (Exception e2) {
            TapjoyErrorMessage.ErrorType errorType = TapjoyErrorMessage.ErrorType.SDK_ERROR;
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(errorType, "Error in computing awardCurrencyVerifier -- " + e2.toString()));
            return "";
        }
    }

    private static String a(long j2, String str) {
        try {
            return TapjoyUtil.SHA256(v + ":" + p() + ":" + j2 + ":" + L + ":" + str);
        } catch (Exception e2) {
            TapjoyErrorMessage.ErrorType errorType = TapjoyErrorMessage.ErrorType.SDK_ERROR;
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(errorType, "Error in computing packageNamesVerifier -- " + e2.toString()));
            return "";
        }
    }

    public boolean isInitialized() {
        return this.aa;
    }

    public static void setPlugin(String str) {
        N = str;
    }

    public static void setSDKType(String str) {
        O = str;
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        C = str;
        l = tJSetUserIDListener;
        TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
        new Thread(new Runnable() {
            public final void run() {
                TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.C);
                TapjoyURLConnection c = TapjoyConnectCore.j;
                TapjoyHttpURLResponse responseFromURL = c.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_USER_ID_URL_PATH, TapjoyConnectCore.getURLParams());
                TapjoyConnectCore.a(responseFromURL.response != null ? TapjoyConnectCore.a(responseFromURL.response) : false);
            }
        }).start();
    }

    public static void viewDidClose(String str) {
        TapjoyLog.d("TapjoyConnect", "viewDidClose: " + str);
        ag.remove(str);
        fw.e.notifyObservers();
    }

    public static void viewWillOpen(String str, int i2) {
        TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + str);
        ag.put(str, Integer.valueOf(i2));
    }

    public static boolean isViewOpen() {
        TapjoyLog.d("TapjoyConnect", "isViewOpen: " + ag.size());
        return !ag.isEmpty();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isFullScreenViewOpen() {
        /*
            java.util.Map r0 = ag
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0020
            java.lang.Object r1 = r0.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            switch(r1) {
                case 1: goto L_0x001e;
                case 2: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x000a
        L_0x001e:
            r0 = 1
            return r0
        L_0x0020:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TapjoyConnectCore.isFullScreenViewOpen():boolean");
    }

    public static void setViewShowing(boolean z2) {
        if (z2) {
            ag.put("", 1);
        } else {
            ag.clear();
        }
    }

    private static void a(String str, String str2) {
        if ((str.equals(TapjoyConnectFlag.SERVICE_URL) || str.equals(TapjoyConnectFlag.PLACEMENT_URL)) && !str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        ae.put(str, str2);
    }

    private static boolean f(String str) {
        return ac.checkPermission(str, g.getPackageName()) == 0;
    }

    public void actionComplete(String str) {
        TapjoyLog.i("TapjoyConnect", "actionComplete: " + str);
        Map f2 = f();
        TapjoyUtil.safePut(f2, TapjoyConstants.TJC_APP_ID, str, true);
        f2.putAll(getTimeStampAndVerifierParams());
        TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + f2);
        new Thread(new PPAThread(f2)).start();
    }

    public void completeConnectCall() {
        boolean z2;
        String connectResult;
        TapjoyLog.d("TapjoyConnect", "starting connect call...");
        String str = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            str = getHostURL();
        }
        if (isConnected() || (connectResult = TapjoyAppSettings.getInstance().getConnectResult(q(), w.b())) == null || !a(connectResult, true)) {
            z2 = false;
        } else {
            TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
            ab = true;
            if (k != null) {
                k.onConnectSuccess();
            }
            fw.a.notifyObservers();
            z2 = true;
        }
        TapjoyURLConnection tapjoyURLConnection = j;
        TapjoyHttpURLResponse responseFromURL = tapjoyURLConnection.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, getURLParams());
        if (responseFromURL == null || responseFromURL.statusCode != 200) {
            if (!z2) {
                d();
            }
            fw.b.notifyObservers(Boolean.FALSE);
            return;
        }
        if (a(responseFromURL.response, false)) {
            TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
            ab = true;
            for (Map.Entry entry : getGenericURLParams().entrySet()) {
                TapjoyLog.d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
            }
            if (!z2) {
                if (k != null) {
                    k.onConnectSuccess();
                }
                fw.a.notifyObservers();
            }
            fw.b.notifyObservers(Boolean.TRUE);
        } else {
            if (!z2) {
                d();
            }
            fw.b.notifyObservers(Boolean.FALSE);
        }
        if (af.length() > 0) {
            Map genericURLParams = getGenericURLParams();
            TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_PACKAGE_NAMES, af, true);
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String a2 = a(currentTimeMillis, af);
            TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(currentTimeMillis), true);
            TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, a2, true);
            TapjoyURLConnection tapjoyURLConnection2 = new TapjoyURLConnection();
            TapjoyHttpURLResponse responseFromURL2 = tapjoyURLConnection2.getResponseFromURL(getHostURL() + TapjoyConstants.TJC_SDK_LESS_CONNECT, genericURLParams);
            if (responseFromURL2 != null && responseFromURL2.statusCode == 200) {
                TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
            }
        }
    }

    public void completeLimitedConnectCall() {
        String str = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            str = getHostURL();
        }
        Map limitedURLParams = getLimitedURLParams();
        TapjoyURLConnection tapjoyURLConnection = j;
        TapjoyHttpURLResponse responseFromURL = tapjoyURLConnection.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, limitedURLParams);
        if (responseFromURL == null || responseFromURL.statusCode != 200) {
            e();
            fw.b.notifyObservers(Boolean.FALSE);
        } else if (c(responseFromURL.response)) {
            TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
            aS = true;
            for (Map.Entry entry : getLimitedGenericURLParams().entrySet()) {
                TapjoyLog.d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
            }
            if (aT != null) {
                aT.onConnectSuccess();
            }
            fw.a.notifyObservers();
            fw.b.notifyObservers(Boolean.TRUE);
        } else {
            e();
            fw.b.notifyObservers(Boolean.FALSE);
        }
    }

    public class PPAThread implements Runnable {
        private Map b;

        public PPAThread(Map map) {
            this.b = map;
        }

        public void run() {
            TapjoyURLConnection c = TapjoyConnectCore.j;
            TapjoyHttpURLResponse responseFromURL = c.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, this.b);
            if (responseFromURL.response != null) {
                boolean unused = TapjoyConnectCore.d(responseFromURL.response);
            }
        }
    }

    public void setCurrencyMultiplier(float f2) {
        TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + f2);
        Q = f2;
    }

    public float getCurrencyMultiplier() {
        return Q;
    }

    public static String getConnectFlagValue(String str) {
        if (ae == null || ae.get(str) == null) {
            return "";
        }
        return ae.get(str).toString();
    }

    public static String getSecretKey() {
        return L;
    }

    public static String getAndroidID() {
        return n;
    }

    public static String getSha1MacAddress() {
        try {
            return TapjoyUtil.SHA1(p);
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + e2.toString());
            return null;
        }
    }

    public static String getMacAddress() {
        return p;
    }

    public static float getDeviceScreenDensityScale() {
        return A;
    }

    public static String getSupportURL(String str) {
        if (str == null) {
            str = v;
        }
        return getHostURL() + "support_requests/new?currency_id=" + str + "&app_id=" + v + "&udid=" + V + "&language_code=" + Locale.getDefault().getLanguage();
    }

    public static String getUserToken() {
        return V;
    }

    public static boolean isConnected() {
        return ab;
    }

    public static boolean isLimitedConnected() {
        return aS;
    }

    public static boolean isUnitTestMode() {
        return getConnectFlagValue("unit_test_mode") == "true";
    }

    private static String q() {
        String str = v + w + x + c + q;
        try {
            return TapjoyUtil.SHA1(str);
        } catch (Exception unused) {
            return str;
        }
    }

    static /* synthetic */ void a() {
        if (!aU) {
            ad.loadAdvertisingId();
            if (ad.isGooglePlayServicesAvailable() && ad.isGooglePlayManifestConfigured()) {
                b = ad.getDeviceGooglePlayServicesVersion();
                a = ad.getPackagedGooglePlayServicesVersion();
            }
            if (ad.isAdIdAvailable()) {
                d = ad.isAdTrackingEnabled();
                c = ad.getAdvertisingId();
                hd a2 = hd.a();
                String str = c;
                boolean z2 = !d;
                hg hgVar = a2.f;
                String a3 = hgVar.c.A.a();
                hgVar.b.q = str;
                hgVar.b.r = Boolean.valueOf(z2);
                hgVar.c.A.a(str);
                hgVar.c.B.a(z2);
                hr.a(str, z2);
                if (!ju.c(a3) && !str.equals(a3)) {
                    hgVar.c.a(false);
                }
            }
            if (n()) {
                TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
            }
            aU = true;
        }
    }

    static /* synthetic */ boolean a(String str) {
        Document buildDocument = TapjoyUtil.buildDocument(str);
        if (buildDocument == null) {
            return true;
        }
        String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("PackageNames"));
        if (nodeTrimValue != null && nodeTrimValue.length() > 0) {
            Vector vector = new Vector();
            int i2 = 0;
            while (true) {
                int indexOf = nodeTrimValue.indexOf(44, i2);
                if (indexOf == -1) {
                    break;
                }
                TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i2, indexOf).trim());
                vector.add(nodeTrimValue.substring(i2, indexOf).trim());
                i2 = indexOf + 1;
            }
            TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i2).trim());
            vector.add(nodeTrimValue.substring(i2).trim());
            a((List) vector);
        }
        String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
        if (nodeTrimValue2 == null || !nodeTrimValue2.equals("true")) {
            return false;
        }
        return true;
    }

    static /* synthetic */ void a(boolean z2) {
        if (z2) {
            TapjoyLog.i("TapjoyConnect", "Set userID is successful");
            if (l != null) {
                l.onSetUserIDSuccess();
                return;
            }
            return;
        }
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to set userID"));
        if (l != null) {
            l.onSetUserIDFailure("Failed to set userID");
        }
    }
}
