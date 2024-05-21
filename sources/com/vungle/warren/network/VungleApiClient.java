package com.vungle.warren.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moat.analytics.mobile.vng.MoatAnalytics;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.Storage;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.ui.VungleActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VungleApiClient {
    private static String BASE_URL = "https://ads.api.vungle.com/";
    private static String HEADER_UA = (MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "VungleAmazon/6.3.17" : "VungleDroid/6.3.17");
    public static final String MANUFACTURER_AMAZON = "Amazon";
    private static final String TAG = "VungleApiClient";
    /* access modifiers changed from: private */
    public static VungleApiClient _instance;
    private static VungleApi api;
    /* access modifiers changed from: private */
    public static OkHttpClient client;
    private JsonObject appBody;
    private String cacheDir;
    /* access modifiers changed from: private */
    public WeakReference<Context> context;
    /* access modifiers changed from: private */
    public JsonObject deviceBody;
    /* access modifiers changed from: private */
    public boolean enableMoat;
    /* access modifiers changed from: private */
    public boolean limitAdTracking;
    private JsonObject location;
    /* access modifiers changed from: private */
    public String newEndpoint;
    /* access modifiers changed from: private */
    public String reportAdEndpoint;
    /* access modifiers changed from: private */
    public String requestAdEndpoint;
    /* access modifiers changed from: private */
    public Map<String, Long> retryAfterDataMap = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public String riEndpoint;
    private boolean shouldTransmitIMEI;
    private Storage storage;
    /* access modifiers changed from: private */
    public VungleApi timeoutApi;
    private String uaString;
    private JsonObject userBody;
    private String userImei;
    /* access modifiers changed from: private */
    public boolean willPlayAdEnabled;
    /* access modifiers changed from: private */
    public String willPlayAdEndpoint;
    /* access modifiers changed from: private */
    public int willPlayAdTimeout;

    public enum WrapperFramework {
        admob,
        air,
        cocos2dx,
        corona,
        dfp,
        heyzap,
        marmalade,
        mopub,
        unity,
        fyber,
        ironsource,
        upsight,
        appodeal,
        aerserv,
        adtoapp,
        tapdaq,
        none
    }

    private String getConnectionTypeDetail(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHPRD";
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            default:
                return "UNKNOWN";
        }
    }

    private VungleApiClient() {
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                int code;
                Request request = chain.request();
                String encodedPath = request.url().encodedPath();
                Long l = (Long) VungleApiClient.this.retryAfterDataMap.get(encodedPath);
                if (l != null) {
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(l.longValue() - System.currentTimeMillis());
                    if (seconds > 0) {
                        Response.Builder request2 = new Response.Builder().request(request);
                        return request2.addHeader("Retry-After", "" + seconds).code(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL).protocol(Protocol.HTTP_1_1).message("Server is busy").body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"Error\":\"Retry-After\"}")).build();
                    }
                    VungleApiClient.this.retryAfterDataMap.remove(encodedPath);
                }
                Response proceed = chain.proceed(request);
                if (proceed != null && ((code = proceed.code()) == 429 || code == 500 || code == 502 || code == 503)) {
                    String str = proceed.headers().get("Retry-After");
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            long parseLong = Long.parseLong(str);
                            if (parseLong > 0) {
                                VungleApiClient.this.retryAfterDataMap.put(encodedPath, Long.valueOf((parseLong * 1000) + System.currentTimeMillis()));
                            }
                        } catch (NumberFormatException unused) {
                            Log.d(VungleApiClient.TAG, "Retry-After value is not an valid value");
                        }
                    }
                }
                return proceed;
            }
        }).build();
        api = (VungleApi) new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(VungleApi.class);
    }

    public static void updateIMEI(String str, boolean z) {
        _instance.userImei = str;
        _instance.shouldTransmitIMEI = z;
    }

    public static void addWrapperInfo(WrapperFramework wrapperFramework, String str) {
        if (wrapperFramework != null && wrapperFramework != WrapperFramework.none) {
            HEADER_UA += ";" + wrapperFramework;
            if (str != null && !str.isEmpty()) {
                HEADER_UA += "/" + str;
            }
        }
    }

    public static synchronized void init(final Context context2, String str, String str2, Storage storage2) {
        synchronized (VungleApiClient.class) {
            if (_instance == null) {
                _instance = new VungleApiClient();
                _instance.storage = storage2;
                _instance.context = new WeakReference<>(context2);
                _instance.shouldTransmitIMEI = false;
                _instance.cacheDir = str2;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", str);
                jsonObject.addProperty(TJAdUnitConstants.String.BUNDLE, context2.getPackageName());
                try {
                    jsonObject.addProperty("ver", context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName);
                } catch (PackageManager.NameNotFoundException unused) {
                    jsonObject.addProperty("ver", "1.0");
                }
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("make", Build.MANUFACTURER);
                jsonObject2.addProperty("model", Build.MODEL);
                jsonObject2.addProperty("osv", Build.VERSION.RELEASE);
                jsonObject2.addProperty("carrier", ((TelephonyManager) context2.getSystemService("phone")).getNetworkOperatorName());
                jsonObject2.addProperty("os", MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                jsonObject2.addProperty("w", (Number) Integer.valueOf(displayMetrics.widthPixels));
                jsonObject2.addProperty("h", (Number) Integer.valueOf(displayMetrics.heightPixels));
                JsonObject jsonObject3 = new JsonObject();
                jsonObject3.add("vungle", new JsonObject());
                jsonObject2.add("ext", jsonObject3);
                if (Build.VERSION.SDK_INT >= 17) {
                    _instance.uaString = WebSettings.getDefaultUserAgent(context2);
                    jsonObject2.addProperty("ua", _instance.uaString);
                    _instance.deviceBody = jsonObject2;
                } else if (Looper.getMainLooper() == Looper.myLooper()) {
                    _instance.uaString = new WebView(context2.getApplicationContext()).getSettings().getUserAgentString();
                    jsonObject2.addProperty("ua", _instance.uaString);
                    _instance.deviceBody = jsonObject2;
                } else {
                    _instance.deviceBody = jsonObject2;
                    try {
                        _instance.uaString = System.getProperty("http.agent");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            VungleApiClient._instance.deviceBody.addProperty("ua", new WebView(context2.getApplicationContext()).getSettings().getUserAgentString());
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                if (context2.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
                    if (context2.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                        Log.d(TAG, "Location permission was not granted, location information will not be included");
                        new AdvertisingIDTask().execute(new Void[0]);
                        _instance.appBody = jsonObject;
                    }
                }
                LocationManager locationManager = (LocationManager) context2.getSystemService("location");
                List<String> providers = locationManager.getProviders(true);
                if (providers != null) {
                    Location location2 = null;
                    for (String lastKnownLocation : providers) {
                        Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                        if (lastKnownLocation2 != null && (location2 == null || lastKnownLocation2.getAccuracy() < location2.getAccuracy())) {
                            location2 = lastKnownLocation2;
                        }
                    }
                    if (location2 != null) {
                        JsonObject jsonObject4 = new JsonObject();
                        jsonObject4.addProperty("accuracy", String.valueOf(location2.getAccuracy()));
                        jsonObject4.addProperty("latitude", String.valueOf(location2.getLatitude()));
                        jsonObject4.addProperty("longitude", String.valueOf(location2.getLongitude()));
                        jsonObject4.addProperty("speed", String.valueOf(location2.getSpeed()));
                        jsonObject4.addProperty("timestamp", (Number) Long.valueOf(location2.getTime()));
                        _instance.location = jsonObject4;
                    }
                }
                new AdvertisingIDTask().execute(new Void[0]);
                _instance.appBody = jsonObject;
            }
        }
    }

    public static void config(@NonNull final Callback<JsonObject> callback) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, _instance.getDeviceBody());
            jsonObject.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            jsonObject.add("user", _instance.getUserBody());
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("is_auto_cached_enforced", (Boolean) false);
            jsonObject.add("request", jsonObject2);
            VungleApiClient vungleApiClient = _instance;
            api.config(HEADER_UA, jsonObject).enqueue(new Callback<JsonObject>() {
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        callback.onResponse(call, response);
                        return;
                    }
                    JsonObject body = response.body();
                    Log.d(VungleApiClient.TAG, "Config Response: " + body);
                    if (JsonUtil.hasNonNull(body, "sleep")) {
                        String asString = JsonUtil.hasNonNull(body, TJAdUnitConstants.String.VIDEO_INFO) ? body.get(TJAdUnitConstants.String.VIDEO_INFO).getAsString() : "";
                        Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. " + asString);
                        callback.onFailure(call, new VungleException(3));
                    } else if (!JsonUtil.hasNonNull(body, "endpoints")) {
                        Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. ");
                        callback.onFailure(call, new VungleException(3));
                    } else {
                        JsonObject asJsonObject = body.getAsJsonObject("endpoints");
                        HttpUrl parse = HttpUrl.parse(asJsonObject.get("new").getAsString());
                        HttpUrl parse2 = HttpUrl.parse(asJsonObject.get("ads").getAsString());
                        HttpUrl parse3 = HttpUrl.parse(asJsonObject.get("will_play_ad").getAsString());
                        HttpUrl parse4 = HttpUrl.parse(asJsonObject.get("report_ad").getAsString());
                        HttpUrl parse5 = HttpUrl.parse(asJsonObject.get("ri").getAsString());
                        String unused = VungleApiClient._instance.newEndpoint = parse.toString();
                        String unused2 = VungleApiClient._instance.requestAdEndpoint = parse2.toString();
                        String unused3 = VungleApiClient._instance.willPlayAdEndpoint = parse3.toString();
                        String unused4 = VungleApiClient._instance.reportAdEndpoint = parse4.toString();
                        String unused5 = VungleApiClient._instance.riEndpoint = parse5.toString();
                        JsonObject asJsonObject2 = body.getAsJsonObject("will_play_ad");
                        int unused6 = VungleApiClient._instance.willPlayAdTimeout = asJsonObject2.get("request_timeout").getAsInt();
                        boolean unused7 = VungleApiClient._instance.willPlayAdEnabled = asJsonObject2.get(TJAdUnitConstants.String.ENABLED).getAsBoolean();
                        boolean unused8 = VungleApiClient._instance.enableMoat = body.getAsJsonObject("viewability").get("moat").getAsBoolean();
                        callback.onResponse(call, response);
                        if (VungleApiClient._instance.willPlayAdEnabled) {
                            Log.v(VungleApiClient.TAG, "willPlayAd is enabled, generating a timeout client.");
                            VungleApiClient unused9 = VungleApiClient._instance;
                            VungleApi unused10 = VungleApiClient._instance.timeoutApi = (VungleApi) new Retrofit.Builder().client(VungleApiClient.client.newBuilder().readTimeout((long) VungleApiClient._instance.willPlayAdTimeout, TimeUnit.MILLISECONDS).build()).addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.vungle.com/").build().create(VungleApi.class);
                        }
                        if (VungleApiClient.getMoatEnabled()) {
                            MoatOptions moatOptions = new MoatOptions();
                            moatOptions.disableAdIdCollection = true;
                            moatOptions.disableLocationServices = true;
                            moatOptions.loggingEnabled = true;
                            MoatAnalytics.getInstance().start(moatOptions, (Application) ((Context) VungleApiClient._instance.context.get()).getApplicationContext());
                        }
                    }
                }

                public void onFailure(Call<JsonObject> call, Throwable th) {
                    Log.e(VungleApiClient.TAG, "Failed to configure.", th);
                    callback.onFailure(call, th);
                }
            });
        } catch (IllegalStateException e) {
            callback.onFailure((Call<JsonObject>) null, e);
        }
    }

    public static Call<JsonObject> reportNew() throws IllegalStateException {
        if (_instance.newEndpoint != null) {
            HashMap hashMap = new HashMap(2);
            hashMap.put(TapjoyConstants.TJC_APP_ID, _instance.appBody.get("id").getAsString());
            hashMap.put("ifa", _instance.getAdvertIdFromCookie());
            VungleApiClient vungleApiClient = _instance;
            return api.reportNew(HEADER_UA, _instance.newEndpoint, hashMap);
        }
        throw new IllegalStateException("API Client not configured yet! Must call /config first.");
    }

    public static Call<JsonObject> requestAd(String str, boolean z) throws IllegalStateException {
        if (_instance.requestAdEndpoint != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, _instance.getDeviceBody());
            jsonObject.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            jsonObject.add("user", _instance.getUserBody());
            JsonObject jsonObject2 = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(str);
            jsonObject2.add("placements", jsonArray);
            jsonObject2.addProperty("header_bidding", Boolean.valueOf(z));
            jsonObject.add("request", jsonObject2);
            VungleApiClient vungleApiClient = _instance;
            return api.ads(HEADER_UA, _instance.requestAdEndpoint, jsonObject);
        }
        throw new IllegalStateException("API Client not configured yet! Must call /config first.");
    }

    public static Call<JsonObject> willPlayAd(String str, boolean z, String str2) throws IllegalStateException, VungleError {
        if (_instance.willPlayAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        } else if (_instance.willPlayAdEnabled) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, _instance.getDeviceBody());
            jsonObject.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            jsonObject.add("user", _instance.getUserBody());
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("reference_id", str);
            jsonObject3.addProperty("is_auto_cached", Boolean.valueOf(z));
            jsonObject2.add(VungleActivity.PLACEMENT_EXTRA, jsonObject3);
            jsonObject2.addProperty("ad_token", str2);
            jsonObject.add("request", jsonObject2);
            return _instance.timeoutApi.willPlayAd(HEADER_UA, _instance.willPlayAdEndpoint, jsonObject);
        } else {
            throw new VungleError(6);
        }
    }

    public static Call<JsonObject> reportAd(JsonObject jsonObject) {
        if (_instance.reportAdEndpoint != null) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, _instance.getDeviceBody());
            jsonObject2.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            jsonObject2.add("request", jsonObject);
            jsonObject2.add("user", _instance.getUserBody());
            VungleApiClient vungleApiClient = _instance;
            return api.reportAd(HEADER_UA, _instance.reportAdEndpoint, jsonObject2);
        }
        throw new IllegalStateException("API Client not configured yet! Must call /config first.");
    }

    public static Call<JsonObject> ri(JsonObject jsonObject) {
        if (_instance.riEndpoint != null) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, _instance.getDeviceBody());
            jsonObject2.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            jsonObject2.add("request", jsonObject);
            VungleApiClient vungleApiClient = _instance;
            return api.ri(HEADER_UA, _instance.riEndpoint, jsonObject2);
        }
        throw new IllegalStateException("API Client not configured yet! Must call /config first.");
    }

    public static void pingTPAT(final String str) {
        AnonymousClass4 r0 = new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Log.e(VungleApiClient.TAG, "Failed to ping TPAT Url : " + str);
            }
        };
        if (TextUtils.isEmpty(str) || HttpUrl.parse(str) == null) {
            r0.onFailure((Call) null, new IllegalArgumentException("Malformed Url"));
            return;
        }
        if (!TextUtils.isEmpty(_instance.userImei) && _instance.shouldTransmitIMEI) {
            str = str.replace("%imei%", _instance.userImei);
        }
        VungleApiClient vungleApiClient = _instance;
        api.pingTPAT(_instance.uaString, str).enqueue(r0);
    }

    @SuppressLint({"HardwareIds"})
    private JsonObject getDeviceBody() throws IllegalStateException {
        String str;
        boolean z;
        String str2;
        NetworkInfo activeNetworkInfo;
        if (this.context.get() != null) {
            JsonObject jsonObject = new JsonObject();
            String advertIdFromCookie = _instance.getAdvertIdFromCookie();
            boolean z2 = false;
            if (advertIdFromCookie != null) {
                jsonObject.addProperty(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon_advertising_id" : "gaid", advertIdFromCookie);
                this.deviceBody.addProperty("ifa", advertIdFromCookie);
                this.deviceBody.addProperty("lmt", (Number) Integer.valueOf(this.limitAdTracking ? 1 : 0));
            } else {
                this.deviceBody.addProperty("ifa", Settings.Secure.getString(((Context) this.context.get()).getContentResolver(), TapjoyConstants.TJC_ANDROID_ID));
                this.deviceBody.addProperty("lmt", (Number) 0);
            }
            new AdvertisingIDTask().execute(new Void[0]);
            boolean z3 = false;
            for (PackageInfo packageInfo : ((Context) _instance.context.get()).getPackageManager().getInstalledPackages(128)) {
                if (packageInfo.packageName.equalsIgnoreCase("com.google.android.gms")) {
                    z3 = true;
                }
            }
            this.deviceBody.addProperty("is_google_play_services_available", Boolean.valueOf(z3));
            Intent registerReceiver = ((Context) this.context.get()).registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            if (intExtra > 0 && intExtra2 > 0) {
                jsonObject.addProperty("battery_level", (Number) Float.valueOf(((float) intExtra) / ((float) intExtra2)));
            }
            int intExtra3 = registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
            if (intExtra3 == -1) {
                str = "UNKNOWN";
            } else if (intExtra3 == 2 || intExtra3 == 5) {
                int intExtra4 = registerReceiver.getIntExtra("plugged", -1);
                if (intExtra4 != 4) {
                    switch (intExtra4) {
                        case 1:
                            str = "BATTERY_PLUGGED_AC";
                            break;
                        case 2:
                            str = "BATTERY_PLUGGED_USB";
                            break;
                        default:
                            str = "BATTERY_PLUGGED_OTHERS";
                            break;
                    }
                } else {
                    str = "BATTERY_PLUGGED_WIRELESS";
                }
            } else {
                str = "NOT_CHARGING";
            }
            jsonObject.addProperty("battery_state", str);
            if (Build.VERSION.SDK_INT >= 21) {
                PowerManager powerManager = (PowerManager) ((Context) this.context.get()).getSystemService("power");
                jsonObject.addProperty("battery_saver_enabled", (Number) Integer.valueOf((powerManager == null || !powerManager.isPowerSaveMode()) ? 0 : 1));
            }
            if (PermissionChecker.checkCallingOrSelfPermission((Context) _instance.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
                String str3 = "NONE";
                String str4 = "NONE";
                ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) this.context.get()).getSystemService("connectivity");
                if (!(connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
                    switch (activeNetworkInfo.getType()) {
                        case 0:
                            str3 = "MOBILE";
                            str4 = getConnectionTypeDetail(activeNetworkInfo.getSubtype());
                            break;
                        case 1:
                        case 6:
                            str3 = "WIFI";
                            str4 = "WIFI";
                            break;
                        case 7:
                            str3 = "BLUETOOTH";
                            str4 = "BLUETOOTH";
                            break;
                        case 9:
                            str3 = "ETHERNET";
                            str4 = "ETHERNET";
                            break;
                        default:
                            str3 = "UNKNOWN";
                            str4 = "UNKNOWN";
                            break;
                    }
                }
                jsonObject.addProperty(TapjoyConstants.TJC_CONNECTION_TYPE, str3);
                jsonObject.addProperty("connection_type_detail", str4);
                if (Build.VERSION.SDK_INT >= 24) {
                    if (connectivityManager.isActiveNetworkMetered()) {
                        switch (connectivityManager.getRestrictBackgroundStatus()) {
                            case 1:
                                str2 = "DISABLED";
                                break;
                            case 2:
                                str2 = "WHITELISTED";
                                break;
                            case 3:
                                str2 = "ENABLED";
                                break;
                            default:
                                str2 = "UNKNOWN";
                                break;
                        }
                        jsonObject.addProperty("data_saver_status", str2);
                        jsonObject.addProperty("network_metered", (Number) 1);
                    } else {
                        jsonObject.addProperty("data_saver_status", "NOT_APPLICABLE");
                        jsonObject.addProperty("network_metered", (Number) 0);
                    }
                }
            }
            jsonObject.addProperty("locale", Locale.getDefault().toString());
            jsonObject.addProperty("language", Locale.getDefault().getLanguage());
            jsonObject.addProperty("time_zone", TimeZone.getDefault().getID());
            if (this.context.get() != null) {
                AudioManager audioManager = (AudioManager) ((Context) this.context.get()).getSystemService("audio");
                if (audioManager != null) {
                    int streamMaxVolume = audioManager.getStreamMaxVolume(3);
                    int streamVolume = audioManager.getStreamVolume(3);
                    jsonObject.addProperty("volume_level", (Number) Float.valueOf(((float) streamVolume) / ((float) streamMaxVolume)));
                    jsonObject.addProperty("sound_enabled", (Number) Integer.valueOf(streamVolume > 0 ? 1 : 0));
                }
                File file = new File(this.cacheDir);
                boolean z4 = file.exists() && file.isDirectory();
                if (!z4) {
                    if (!file.exists()) {
                        z4 = file.mkdir();
                    } else if (!file.isDirectory() && file.delete()) {
                        z4 = file.mkdir();
                    }
                }
                if (z4) {
                    StatFs statFs = new StatFs(this.cacheDir);
                    long j = -1;
                    if (Build.VERSION.SDK_INT >= 26) {
                        StorageManager storageManager = (StorageManager) ((Context) this.context.get()).getSystemService(StorageManager.class);
                        if (storageManager != null) {
                            try {
                                j = storageManager.getCacheQuotaBytes(storageManager.getUuidForPath(file));
                            } catch (IOException unused) {
                                Log.e(TAG, "Unable to check available bytes");
                            }
                        }
                    } else {
                        j = Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong() : (long) (statFs.getBlockSize() * statFs.getAvailableBlocks());
                    }
                    jsonObject.addProperty("storage_bytes_available", (Number) Long.valueOf(j));
                }
                if (MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                    z = ((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
                } else {
                    z = Build.VERSION.SDK_INT < 23 ? ((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("com.google.android.tv") || !((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen") : ((UiModeManager) ((Context) this.context.get()).getSystemService("uimode")).getCurrentModeType() == 4;
                }
                jsonObject.addProperty("is_tv", Boolean.valueOf(z));
                jsonObject.addProperty("os_api_level", (Number) Integer.valueOf(Build.VERSION.SDK_INT));
                try {
                    if (Build.VERSION.SDK_INT >= 26) {
                        if (((Context) this.context.get()).checkCallingOrSelfPermission("android.permission.REQUEST_INSTALL_PACKAGES") == 0) {
                            z2 = ((Context) this.context.get()).getApplicationContext().getPackageManager().canRequestPackageInstalls();
                        }
                    } else if (Settings.Secure.getInt(((Context) this.context.get()).getContentResolver(), "install_non_market_apps") == 1) {
                        z2 = true;
                    }
                } catch (Settings.SettingNotFoundException e) {
                    Log.e(TAG, "isInstallNonMarketAppsEnabled Settings not found", e);
                }
                jsonObject.addProperty("is_sideload_enabled", Boolean.valueOf(z2));
            } else {
                jsonObject.addProperty("volume_level", (Number) 0);
                jsonObject.addProperty("sound_enabled", (Number) 0);
            }
            jsonObject.addProperty("sd_card_available", (Number) Integer.valueOf(Environment.getExternalStorageState().equals("mounted") ? 1 : 0));
            jsonObject.addProperty("os_name", Build.FINGERPRINT);
            jsonObject.addProperty("vduid", "");
            if (_instance.location != null) {
                jsonObject.add("location", _instance.location);
            }
            this.deviceBody.getAsJsonObject("ext").getAsJsonObject("vungle").add(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE, jsonObject);
            return this.deviceBody;
        }
        throw new IllegalStateException("Context is null, SDK not initialized");
    }

    private JsonObject getUserBody() {
        long j;
        String str;
        String str2;
        String str3;
        if (this.userBody == null) {
            this.userBody = new JsonObject();
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie != null) {
            str2 = cookie.getString("consent_status");
            str = cookie.getString("consent_source");
            j = cookie.getLong("timestamp").longValue();
            str3 = cookie.getString("consent_message_version");
        } else {
            str2 = EnvironmentCompat.MEDIA_UNKNOWN;
            str = "no_interaction";
            j = 0;
            str3 = "";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("consent_status", str2);
        jsonObject.addProperty("consent_source", str);
        jsonObject.addProperty("consent_timestamp", (Number) Long.valueOf(j));
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        jsonObject.addProperty("consent_message_version", str3);
        this.userBody.add("gdpr", jsonObject);
        return this.userBody;
    }

    public static boolean getMoatEnabled() {
        return _instance.enableMoat && Build.VERSION.SDK_INT >= 16;
    }

    private static class AdvertisingIDTask extends AsyncTask<Void, Void, String> {
        private AdvertisingIDTask() {
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Void... voidArr) {
            String str;
            NoClassDefFoundError e;
            String str2;
            String str3 = null;
            try {
                if (VungleApiClient.MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                    try {
                        ContentResolver contentResolver = ((Context) VungleApiClient._instance.context.get()).getContentResolver();
                        VungleApiClient access$100 = VungleApiClient._instance;
                        boolean z = true;
                        if (Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 1) {
                            z = false;
                        }
                        boolean unused = access$100.limitAdTracking = z;
                        str = Settings.Secure.getString(contentResolver, TapjoyConstants.TJC_ADVERTISING_ID);
                        try {
                            VungleApiClient._instance.addAdvertIdInCookie(str);
                        } catch (Settings.SettingNotFoundException e2) {
                            String str4 = str;
                            e = e2;
                            str3 = str4;
                        } catch (Exception unused2) {
                            str3 = str;
                            Log.e(VungleApiClient.TAG, "Cannot load Advertising ID");
                            return str3;
                        }
                    } catch (Settings.SettingNotFoundException e3) {
                        e = e3;
                        Log.w(VungleApiClient.TAG, "Error getting Amazon advertising info", e);
                        return str3;
                    }
                } else {
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo((Context) VungleApiClient._instance.context.get());
                        if (advertisingIdInfo != null) {
                            str2 = advertisingIdInfo.getId();
                            try {
                                boolean unused3 = VungleApiClient._instance.limitAdTracking = advertisingIdInfo.isLimitAdTrackingEnabled();
                                VungleApiClient._instance.deviceBody.addProperty("ifa", str2);
                                VungleApiClient._instance.addAdvertIdInCookie(str2);
                                str = str2;
                            } catch (NoClassDefFoundError e4) {
                                e = e4;
                                try {
                                    Log.e(VungleApiClient.TAG, "Play services Not available: " + e.getLocalizedMessage());
                                    str3 = Settings.Secure.getString(((Context) VungleApiClient._instance.context.get()).getContentResolver(), TapjoyConstants.TJC_ADVERTISING_ID);
                                    VungleApiClient._instance.addAdvertIdInCookie(str3);
                                    return str3;
                                } catch (Exception unused4) {
                                    str3 = str2;
                                    Log.e(VungleApiClient.TAG, "Cannot load Advertising ID");
                                    return str3;
                                }
                            }
                        } else {
                            str = null;
                        }
                    } catch (NoClassDefFoundError e5) {
                        str2 = null;
                        e = e5;
                        Log.e(VungleApiClient.TAG, "Play services Not available: " + e.getLocalizedMessage());
                        str3 = Settings.Secure.getString(((Context) VungleApiClient._instance.context.get()).getContentResolver(), TapjoyConstants.TJC_ADVERTISING_ID);
                        VungleApiClient._instance.addAdvertIdInCookie(str3);
                        return str3;
                    }
                }
                return str;
            } catch (Exception unused5) {
            }
        }
    }

    private String getAdvertIdFromCookie() {
        Cookie cookie = (Cookie) this.storage.load(Cookie.GOOGLE_AD_ID_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("advertId");
    }

    /* access modifiers changed from: private */
    public void addAdvertIdInCookie(String str) {
        Cookie cookie = new Cookie(Cookie.GOOGLE_AD_ID_COOKIE);
        cookie.putValue("advertId", str);
        this.storage.save(cookie);
    }

    public static long getRetryAfterHeaderValue(retrofit2.Response<JsonObject> response) {
        try {
            return Long.parseLong(response.headers().get("Retry-After")) * 1000;
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
