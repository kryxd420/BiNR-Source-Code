package com.vungle.warren;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapjoy.TJAdUnitConstants;
import com.vungle.warren.DownloadStrategy;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.model.Placement;
import com.vungle.warren.network.Downloader;
import com.vungle.warren.network.FetchDownloader;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.persistence.FilePersistor;
import com.vungle.warren.persistence.GraphicDesigner;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.tasks.CleanupJob;
import com.vungle.warren.tasks.DownloadJob;
import com.vungle.warren.tasks.ReconfigJob;
import com.vungle.warren.tasks.SendReportsJob;
import com.vungle.warren.tasks.VungleJobCreator;
import com.vungle.warren.ui.VungleActivity;
import com.vungle.warren.ui.VungleFlexViewActivity;
import com.vungle.warren.ui.VungleNativeView;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class Vungle {
    private static final String COM_VUNGLE_SDK = "com.vungle.sdk";
    /* access modifiers changed from: private */
    public static final String TAG = Vungle.class.getCanonicalName();
    static final Vungle _instance = new Vungle();
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static AtomicBoolean isDepInit = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static boolean isInitialized = false;
    /* access modifiers changed from: private */
    public static AtomicBoolean isInitializing = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public String appID;
    /* access modifiers changed from: private */
    public Context context;
    private Downloader downloader;
    /* access modifiers changed from: private */
    public HeaderBiddingCallback headerBiddingCallback;
    /* access modifiers changed from: private */
    public InitCallback initCallback;
    /* access modifiers changed from: private */
    public VungleJobRunner jobRunner;
    /* access modifiers changed from: private */
    public Map<String, Boolean> loadOperations = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, Boolean> playOperations = new ConcurrentHashMap();
    private PublisherDirectDownload publisherDirectDownload;
    /* access modifiers changed from: private */
    public boolean shouldTransmitIMEI;
    Storage storage;
    private Consent tempConsent;
    private String tempConsentVersion;
    private String userIMEI;

    public enum Consent {
        OPTED_IN,
        OPTED_OUT
    }

    interface DownloadCallback {
        void onDownloadCompleted(File file, String str);

        void onDownloadFailed(Throwable th, String str);
    }

    private Vungle() {
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String str, @NonNull Context context2, @NonNull InitCallback initCallback2, PublisherDirectDownload publisherDirectDownload2) throws IllegalArgumentException {
        init(str, context2, initCallback2, publisherDirectDownload2);
    }

    public static void init(@NonNull String str, @NonNull Context context2, @NonNull InitCallback initCallback2, PublisherDirectDownload publisherDirectDownload2) throws IllegalArgumentException {
        if (initCallback2 == null) {
            throw new IllegalArgumentException("A valid InitCallback required to ensure API calls are being made after initialize is successful");
        } else if (context2 == null || str == null || str.isEmpty()) {
            initCallback2.onError(new VungleException(6));
        } else if (!(context2 instanceof Application)) {
            initCallback2.onError(new VungleException(7));
        } else if (isInitialized()) {
            Log.d(TAG, "init already complete");
            initCallback2.onSuccess();
        } else if (isInitializing.getAndSet(true)) {
            Log.d(TAG, "init ongoing");
            initCallback2.onError(new VungleException(8));
        } else {
            if (!isDepInit.getAndSet(true)) {
                GraphicDesigner graphicDesigner = new GraphicDesigner(context2.getCacheDir());
                Storage makeInstance = Storage.makeInstance(new FilePersistor(context2.getFilesDir()), graphicDesigner);
                _instance.context = context2;
                _instance.appID = str;
                _instance.downloader = new FetchDownloader(context2);
                _instance.storage = makeInstance;
                _instance.jobRunner = new VungleJobRunner(new VungleJobCreator(_instance.storage, graphicDesigner, new ReconfigJob.ReconfigCall() {
                    public void reConfigVungle() {
                        Vungle.reConfigure();
                    }
                }));
                makeInstance.init(1);
                VungleApiClient.init(context2, str, graphicDesigner.getCacheDirectory().getPath(), makeInstance);
                if (!TextUtils.isEmpty(_instance.userIMEI)) {
                    VungleApiClient.updateIMEI(_instance.userIMEI, _instance.shouldTransmitIMEI);
                }
                if (_instance.tempConsent != null && !TextUtils.isEmpty(_instance.tempConsentVersion)) {
                    updateConsentStatus(_instance.tempConsent, _instance.tempConsentVersion);
                }
                Cookie cookie = (Cookie) _instance.storage.load(Cookie.APP_ID, Cookie.class);
                if (cookie == null) {
                    cookie = new Cookie(Cookie.APP_ID);
                }
                cookie.putValue(Cookie.APP_ID, str);
                _instance.storage.save(cookie);
            }
            _instance.initCallback = initCallback2;
            _instance.publisherDirectDownload = publisherDirectDownload2;
            _instance.configure(initCallback2);
        }
    }

    static void reConfigure() {
        if (isInitialized()) {
            _instance.configure(_instance.initCallback);
        } else {
            init(_instance.appID, _instance.context, _instance.initCallback, _instance.publisherDirectDownload);
        }
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String str, @NonNull Context context2, @NonNull InitCallback initCallback2) throws IllegalArgumentException {
        init(str, context2, initCallback2, (PublisherDirectDownload) null);
    }

    public static void init(@NonNull String str, @NonNull Context context2, @NonNull InitCallback initCallback2) throws IllegalArgumentException {
        init(str, context2, initCallback2, (PublisherDirectDownload) null);
    }

    private void configure(@NonNull final InitCallback initCallback2) {
        VungleApiClient.config(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    initCallback2.onError(new VungleException(2));
                    Vungle.isInitializing.set(false);
                } else if (!response.isSuccessful()) {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    if (retryAfterHeaderValue > 0) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle._instance.appID).setDelay(retryAfterHeaderValue));
                        initCallback2.onError(new VungleException(14));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    initCallback2.onError(new VungleException(3));
                    Vungle.isInitializing.set(false);
                } else {
                    if (!Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).getBoolean("reported", false)) {
                        VungleApiClient.reportNew().enqueue(new Callback<JsonObject>() {
                            public void onFailure(Call<JsonObject> call, Throwable th) {
                            }

                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if (response.isSuccessful()) {
                                    SharedPreferences.Editor edit = Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).edit();
                                    edit.putBoolean("reported", true);
                                    edit.apply();
                                    Log.d(Vungle.TAG, "Saving reported state to shared preferences");
                                }
                            }
                        });
                    }
                    JsonObject body = response.body();
                    JsonArray asJsonArray = body.getAsJsonArray("placements");
                    if (asJsonArray.size() == 0) {
                        initCallback2.onError(new VungleException(0));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    Vungle.this.playOperations.clear();
                    Vungle.this.loadOperations.clear();
                    ArrayList arrayList = new ArrayList();
                    Iterator<JsonElement> it = asJsonArray.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new Placement(it.next().getAsJsonObject()));
                    }
                    Vungle._instance.storage.setValidPlacements(arrayList);
                    if (body.has("gdpr")) {
                        Cookie cookie = (Cookie) Vungle._instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
                        if (cookie == null) {
                            cookie = new Cookie(Cookie.CONSENT_COOKIE);
                            cookie.putValue("consent_status", EnvironmentCompat.MEDIA_UNKNOWN);
                            cookie.putValue("consent_source", "no_interaction");
                            cookie.putValue("timestamp", 0L);
                        }
                        JsonObject asJsonObject = body.getAsJsonObject("gdpr");
                        boolean z = JsonUtil.hasNonNull(asJsonObject, "is_country_data_protected") && asJsonObject.get("is_country_data_protected").getAsBoolean();
                        String asString = JsonUtil.hasNonNull(asJsonObject, "consent_title") ? asJsonObject.get("consent_title").getAsString() : "";
                        String asString2 = JsonUtil.hasNonNull(asJsonObject, "consent_message") ? asJsonObject.get("consent_message").getAsString() : "";
                        String asString3 = JsonUtil.hasNonNull(asJsonObject, "consent_message_version") ? asJsonObject.get("consent_message_version").getAsString() : "";
                        String asString4 = JsonUtil.hasNonNull(asJsonObject, "button_accept") ? asJsonObject.get("button_accept").getAsString() : "";
                        String asString5 = JsonUtil.hasNonNull(asJsonObject, "button_deny") ? asJsonObject.get("button_deny").getAsString() : "";
                        cookie.putValue("is_country_data_protected", Boolean.valueOf(z));
                        if (TextUtils.isEmpty(asString)) {
                            asString = "Targeted Ads";
                        }
                        cookie.putValue("consent_title", asString);
                        if (TextUtils.isEmpty(asString2)) {
                            asString2 = "To receive more relevant ad content based on your interactions with our ads, click \"I Consent\" below. Either way, you will see the same amount of ads.";
                        }
                        cookie.putValue("consent_message", asString2);
                        if (!"publisher".equalsIgnoreCase(cookie.getString("consent_source"))) {
                            if (TextUtils.isEmpty(asString3)) {
                                asString3 = "";
                            }
                            cookie.putValue("consent_message_version", asString3);
                        }
                        if (TextUtils.isEmpty(asString4)) {
                            asString4 = "I Consent";
                        }
                        cookie.putValue("button_accept", asString4);
                        if (TextUtils.isEmpty(asString5)) {
                            asString5 = "I Do Not Consent";
                        }
                        cookie.putValue("button_deny", asString5);
                        Vungle._instance.storage.save(cookie);
                    }
                    if (!body.has("apk_direct_download") || !body.getAsJsonObject("apk_direct_download").has(TJAdUnitConstants.String.ENABLED)) {
                        APKDirectDownloadManager.init(Vungle.this.context);
                        APKDirectDownloadManager.setDirectDownloadStatus(-1);
                    } else {
                        boolean asBoolean = body.getAsJsonObject("apk_direct_download").get(TJAdUnitConstants.String.ENABLED).getAsBoolean();
                        if (asBoolean) {
                            APKDirectDownloadManager.init(Vungle.this.context);
                            APKDirectDownloadManager.setDirectDownloadStatus(asBoolean ? 1 : 0);
                        }
                    }
                    if (body.has("ri")) {
                        Cookie cookie2 = (Cookie) Vungle._instance.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
                        if (cookie2 == null) {
                            cookie2 = new Cookie(Cookie.CONFIG_COOKIE);
                        }
                        cookie2.putValue("isReportIncentivizedEnabled", Boolean.valueOf(body.getAsJsonObject("ri").get(TJAdUnitConstants.String.ENABLED).getAsBoolean()));
                        Vungle._instance.storage.save(cookie2);
                    }
                    if (body.has("attribution_reporting")) {
                        JsonObject asJsonObject2 = body.getAsJsonObject("attribution_reporting");
                        if (asJsonObject2.has("should_transmit_imei")) {
                            boolean unused = Vungle.this.shouldTransmitIMEI = asJsonObject2.get("should_transmit_imei").getAsBoolean();
                        } else {
                            boolean unused2 = Vungle.this.shouldTransmitIMEI = false;
                        }
                    } else {
                        boolean unused3 = Vungle.this.shouldTransmitIMEI = false;
                    }
                    if (body.has("config")) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle.this.appID).setDelay(body.getAsJsonObject("config").get("refresh_time").getAsLong()));
                    }
                    boolean unused4 = Vungle.isInitialized = true;
                    initCallback2.onSuccess();
                    Vungle.isInitializing.set(false);
                    Collection<Placement> loadValidPlacements = Vungle.this.storage.loadValidPlacements();
                    Vungle.this.jobRunner.execute(CleanupJob.makeJobInfo());
                    if (loadValidPlacements != null) {
                        for (Placement next : loadValidPlacements) {
                            if (next.isAutoCached()) {
                                Log.d(Vungle.TAG, "starting jobs for autocached advs");
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(next.getId(), true));
                            }
                        }
                    }
                    Vungle.this.jobRunner.execute(SendReportsJob.makeJobInfo());
                }
            }

            public void onFailure(Call<JsonObject> call, Throwable th) {
                boolean unused = Vungle.isInitialized = false;
                Vungle.isInitializing.set(false);
                Log.e(Vungle.TAG, Log.getStackTraceString(th));
                if (th instanceof HttpException) {
                    initCallback2.onError(new VungleException(3));
                }
                initCallback2.onError(new VungleException(2));
            }
        });
    }

    public static boolean isInitialized() {
        return (!isInitialized || _instance.storage == null || _instance.storage.getValidPlacements() == null || _instance.storage.getValidPlacements().size() <= 0 || _instance.context == null) ? false : true;
    }

    public static void setIncentivizedFields(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized");
            return;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if (cookie == null) {
            cookie = new Cookie(Cookie.INCENTIVIZED_TEXT_COOKIE);
        }
        boolean z = false;
        if (!TextUtils.isEmpty(str2)) {
            cookie.putValue(TJAdUnitConstants.String.TITLE, str2);
            z = true;
        }
        if (!TextUtils.isEmpty(str3)) {
            cookie.putValue("body", str3);
            z = true;
        }
        if (!TextUtils.isEmpty(str4)) {
            cookie.putValue("continue", str4);
            z = true;
        }
        if (!TextUtils.isEmpty(str5)) {
            cookie.putValue(TJAdUnitConstants.String.CLOSE, str5);
            z = true;
        }
        if (!TextUtils.isEmpty(str)) {
            cookie.putValue("userID", str);
            z = true;
        }
        if (z) {
            _instance.storage.save(cookie);
        }
    }

    public static boolean canPlayAd(@NonNull String str) {
        if (isInitialized()) {
            return canPlayAd(_instance.storage.findValidAdvertisementForPlacement(str));
        }
        Log.e(TAG, "Vungle is not initialized");
        return false;
    }

    static boolean canPlayAd(Advertisement advertisement) {
        return advertisement != null && advertisement.getState() == 1 && _instance.storage.hasValidAssets(advertisement);
    }

    public static void playAd(@NonNull String str, AdConfig adConfig, @Nullable PlayAdCallback playAdCallback) {
        final boolean z;
        if (isInitialized()) {
            Placement placement = (Placement) _instance.storage.load(str, Placement.class);
            VungleException vungleException = null;
            if (Boolean.TRUE.equals(_instance.playOperations.get(str)) || Boolean.TRUE.equals(_instance.loadOperations.get(str))) {
                vungleException = new VungleException(8);
            }
            if (placement == null) {
                vungleException = new VungleException(13);
            }
            if (vungleException == null) {
                final Advertisement findValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(str);
                if (!canPlayAd(findValidAdvertisementForPlacement)) {
                    if (findValidAdvertisementForPlacement != null && findValidAdvertisementForPlacement.getState() == 1) {
                        _instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 4);
                        if (placement.isAutoCached()) {
                            _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                        }
                    }
                    if (playAdCallback != null) {
                        playAdCallback.onError(str, new VungleException(10));
                    }
                    z = true;
                } else {
                    findValidAdvertisementForPlacement.configure(adConfig);
                    _instance.storage.save(findValidAdvertisementForPlacement);
                    z = false;
                }
                if (_instance.context != null) {
                    final AdConfig adConfig2 = adConfig;
                    final String str2 = str;
                    final PlayAdCallback playAdCallback2 = playAdCallback;
                    VungleApiClient.willPlayAd(placement.getId(), placement.isAutoCached(), z ? "" : findValidAdvertisementForPlacement.getAdToken()).enqueue(new Callback<JsonObject>() {
                        /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
                        /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
                        /* JADX WARNING: Removed duplicated region for block: B:27:0x0067  */
                        /* JADX WARNING: Removed duplicated region for block: B:32:0x0085  */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void onResponse(retrofit2.Call<com.google.gson.JsonObject> r3, retrofit2.Response<com.google.gson.JsonObject> r4) {
                            /*
                                r2 = this;
                                boolean r3 = r4.isSuccessful()
                                r0 = 0
                                if (r3 == 0) goto L_0x0063
                                java.lang.Object r3 = r4.body()
                                com.google.gson.JsonObject r3 = (com.google.gson.JsonObject) r3
                                java.lang.String r4 = "ad"
                                boolean r4 = r3.has(r4)
                                if (r4 == 0) goto L_0x0063
                                java.lang.String r4 = "ad"
                                com.google.gson.JsonObject r3 = r3.getAsJsonObject(r4)     // Catch:{ IllegalArgumentException -> 0x005c, Exception -> 0x0053, VungleError -> 0x0039 }
                                com.vungle.warren.model.Advertisement r4 = new com.vungle.warren.model.Advertisement     // Catch:{ IllegalArgumentException -> 0x005c, Exception -> 0x0053, VungleError -> 0x0039 }
                                r4.<init>((com.google.gson.JsonObject) r3)     // Catch:{ IllegalArgumentException -> 0x005c, Exception -> 0x0053, VungleError -> 0x0039 }
                                com.vungle.warren.AdConfig r3 = r4     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                r4.configure(r3)     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                com.vungle.warren.Vungle r3 = com.vungle.warren.Vungle._instance     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                com.vungle.warren.Storage r3 = r3.storage     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                java.lang.String r0 = r5     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                r1 = 0
                                r3.saveAndApplyState(r4, r0, r1)     // Catch:{ IllegalArgumentException -> 0x0037, Exception -> 0x0034, VungleError -> 0x0031 }
                                r0 = r4
                                goto L_0x0063
                            L_0x0031:
                                r3 = move-exception
                                r0 = r4
                                goto L_0x003a
                            L_0x0034:
                                r3 = move-exception
                                r0 = r4
                                goto L_0x0054
                            L_0x0037:
                                r0 = r4
                                goto L_0x005c
                            L_0x0039:
                                r3 = move-exception
                            L_0x003a:
                                int r4 = r3.getErrorCode()
                                r1 = 6
                                if (r4 == r1) goto L_0x0049
                                java.lang.String r4 = "Vungle"
                                java.lang.String r1 = "Error using will_play_ad!"
                                android.util.Log.e(r4, r1, r3)
                                goto L_0x0063
                            L_0x0049:
                                java.lang.String r3 = com.vungle.warren.Vungle.TAG
                                java.lang.String r4 = "will_play_ad was disabled by the configuration settings. This is expected."
                                android.util.Log.e(r3, r4)
                                goto L_0x0063
                            L_0x0053:
                                r3 = move-exception
                            L_0x0054:
                                java.lang.String r4 = "Vungle"
                                java.lang.String r1 = "Error using will_play_ad!"
                                android.util.Log.e(r4, r1, r3)
                                goto L_0x0063
                            L_0x005c:
                                java.lang.String r3 = "Vungle"
                                java.lang.String r4 = "Will Play Ad did not respond with a replacement. Move on."
                                android.util.Log.v(r3, r4)
                            L_0x0063:
                                boolean r3 = r6
                                if (r3 == 0) goto L_0x0085
                                if (r0 != 0) goto L_0x007b
                                com.vungle.warren.PlayAdCallback r3 = r7
                                if (r3 == 0) goto L_0x0090
                                com.vungle.warren.PlayAdCallback r3 = r7
                                java.lang.String r4 = r5
                                com.vungle.warren.error.VungleException r0 = new com.vungle.warren.error.VungleException
                                r1 = 1
                                r0.<init>(r1)
                                r3.onError(r4, r0)
                                goto L_0x0090
                            L_0x007b:
                                java.lang.String r3 = r5
                                com.vungle.warren.PlayAdCallback r4 = r7
                                java.lang.String r1 = r5
                                com.vungle.warren.Vungle.renderAd(r3, r4, r1, r0)
                                goto L_0x0090
                            L_0x0085:
                                java.lang.String r3 = r5
                                com.vungle.warren.PlayAdCallback r4 = r7
                                java.lang.String r0 = r5
                                com.vungle.warren.model.Advertisement r1 = r8
                                com.vungle.warren.Vungle.renderAd(r3, r4, r0, r1)
                            L_0x0090:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.Vungle.AnonymousClass3.onResponse(retrofit2.Call, retrofit2.Response):void");
                        }

                        public void onFailure(Call<JsonObject> call, Throwable th) {
                            if (!z) {
                                Vungle.renderAd(str2, playAdCallback2, str2, findValidAdvertisementForPlacement);
                            } else if (playAdCallback2 != null) {
                                playAdCallback2.onError(str2, new VungleException(1));
                            }
                        }
                    });
                }
            } else if (playAdCallback != null) {
                playAdCallback.onError(str, vungleException);
            }
        } else if (playAdCallback != null) {
            playAdCallback.onError(str, new VungleException(9));
        }
    }

    /* access modifiers changed from: private */
    public static void renderAd(@NonNull final String str, @Nullable final PlayAdCallback playAdCallback, final String str2, final Advertisement advertisement) {
        boolean z = true;
        _instance.playOperations.put(str, true);
        VungleActivity.setEventListener(new AdvertisementPresenter.EventListener() {
            int percentViewed = -1;
            boolean succesfulView = false;

            public void onNext(String str, String str2) {
                boolean z = false;
                if (str.equals("start")) {
                    Vungle._instance.storage.saveAndApplyState(advertisement, str, 2);
                    if (playAdCallback != null) {
                        playAdCallback.onAdStart(str);
                    }
                    this.percentViewed = 0;
                    Placement placement = (Placement) Vungle._instance.storage.load(str2, Placement.class);
                    if (placement != null && placement.isAutoCached()) {
                        Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(str2, true));
                    }
                } else if (str.equals("end")) {
                    Log.d(TMMediationNetworks.VUNGLE_NAME, "Cleaning up metadata and assets for placement " + str + " and advertisement " + advertisement.getId());
                    Vungle._instance.storage.saveAndApplyState(advertisement, str, 3);
                    Vungle._instance.playOperations.put(str, false);
                    Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                    if (playAdCallback != null) {
                        PlayAdCallback playAdCallback = playAdCallback;
                        String str3 = str;
                        boolean z2 = this.succesfulView || this.percentViewed >= 80;
                        if (str2 != null && str2.equals("isCTAClicked")) {
                            z = true;
                        }
                        playAdCallback.onAdEnd(str3, z2, z);
                    }
                } else if (str.equals("successfulView")) {
                    this.succesfulView = true;
                } else if (str.startsWith("percentViewed")) {
                    String[] split = str.split(":");
                    if (split.length == 2) {
                        this.percentViewed = Integer.parseInt(split[1]);
                    }
                }
            }

            public void onError(Throwable th) {
                Vungle._instance.storage.saveAndApplyState(advertisement, str, 4);
                Vungle._instance.playOperations.put(str, false);
                if (playAdCallback != null) {
                    playAdCallback.onError(str, th);
                }
            }
        });
        if (advertisement == null || !"flexview".equals(advertisement.getTemplateType())) {
            z = false;
        }
        Intent intent = new Intent(_instance.context, z ? VungleFlexViewActivity.class : VungleActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(VungleActivity.PLACEMENT_EXTRA, str);
        _instance.context.startActivity(intent);
    }

    public static void loadAd(@NonNull String str, @Nullable LoadAdCallback loadAdCallback) {
        loadAd(str, loadAdCallback, _instance.publisherDirectDownload);
    }

    public static void loadAd(@NonNull final String str, @Nullable final LoadAdCallback loadAdCallback, final PublisherDirectDownload publisherDirectDownload2) {
        if (isInitialized()) {
            Placement placement = (Placement) _instance.storage.load(str, Placement.class);
            if (placement == null) {
                if (loadAdCallback != null) {
                    loadAdCallback.onError(str, new IllegalArgumentException("Placement ID " + str + " is not valid. Please check your configuration on the vungle dashboard."));
                }
            } else if (!Boolean.TRUE.equals(_instance.loadOperations.get(str))) {
                final Advertisement findValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(placement.getId());
                if (canPlayAd(findValidAdvertisementForPlacement)) {
                    String str2 = TAG;
                    Log.i(str2, "found already cached valid adv, calling onAdLoad " + str + " callback ");
                    if (placement.isAutoCached()) {
                        _instance.initCallback.onAutoCacheAdAvailable(str);
                    }
                    if (loadAdCallback != null) {
                        loadAdCallback.onAdLoad(str);
                        return;
                    }
                    return;
                }
                String str3 = TAG;
                Log.i(str3, "didn't find cached adv for " + str + " downloading ");
                if (placement.getWakeupTime() > System.currentTimeMillis()) {
                    if (loadAdCallback != null) {
                        loadAdCallback.onError(str, new VungleException(1));
                        String str4 = TAG;
                        Log.w(str4, "Placement " + placement.getId() + " is  snoozed");
                    }
                    if (placement.isAutoCached()) {
                        String str5 = TAG;
                        Log.d(str5, "Placement " + placement.getId() + " is sleeping rescheduling it ");
                        _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true).setDelay(placement.getWakeupTime() - System.currentTimeMillis()));
                        return;
                    }
                    return;
                }
                _instance.loadOperations.put(str, true);
                final AnonymousClass5 r3 = new DownloadCallback() {
                    public void onDownloadCompleted(File file, String str) {
                        Advertisement advertisement;
                        String access$400 = Vungle.TAG;
                        Log.d(access$400, "download completed " + str);
                        if (str == null) {
                            advertisement = null;
                        } else {
                            advertisement = (Advertisement) Vungle._instance.storage.load(str, Advertisement.class);
                        }
                        Placement placement = (Placement) Vungle._instance.storage.load(str, Placement.class);
                        if (advertisement == null || placement == null) {
                            onDownloadFailed(new IllegalStateException("Didn't find adv"), str);
                            return;
                        }
                        Vungle._instance.storage.saveAndApplyState(advertisement, str, 1);
                        Log.d(TMMediationNetworks.VUNGLE_NAME, String.format(Locale.ENGLISH, "Downloaded assets for %s to %s", new Object[]{str, file.getAbsolutePath()}));
                        FileUtility.printDirectoryTree(file);
                        Vungle._instance.loadOperations.put(str, false);
                        if (Vungle._instance.headerBiddingCallback != null) {
                            Vungle._instance.headerBiddingCallback.adAvailableForBidToken(str, advertisement.getBidToken());
                        }
                        if (placement.isAutoCached()) {
                            Vungle._instance.initCallback.onAutoCacheAdAvailable(str);
                        }
                        if (loadAdCallback != null) {
                            loadAdCallback.onAdLoad(str);
                        }
                    }

                    public void onDownloadFailed(Throwable th, String str) {
                        Advertisement advertisement;
                        if (str == null) {
                            advertisement = null;
                        } else {
                            advertisement = (Advertisement) Vungle._instance.storage.load(str, Advertisement.class);
                        }
                        Placement placement = (Placement) Vungle._instance.storage.load(str, Placement.class);
                        if (!(advertisement == null || placement == null)) {
                            Vungle._instance.storage.saveAndApplyState(advertisement, str, 4);
                        }
                        Log.e(TMMediationNetworks.VUNGLE_NAME, "Failed to download assets for " + str + ". Cause:", th);
                        Vungle._instance.loadOperations.put(str, false);
                        if (loadAdCallback != null) {
                            loadAdCallback.onError(str, th);
                        }
                    }
                };
                if (findValidAdvertisementForPlacement == null || findValidAdvertisementForPlacement.getState() != 0) {
                    if (findValidAdvertisementForPlacement != null && findValidAdvertisementForPlacement.getState() == 1) {
                        _instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 4);
                    }
                    String str6 = TAG;
                    Log.d(str6, "No adv for placement " + placement.getId() + " getting new data ");
                    _instance.fetchAdMetadata(str, publisherDirectDownload2, r3, _instance.headerBiddingCallback);
                    return;
                }
                Log.d(TAG, "Found valid adv but not ready - downloading content");
                handler.post(new Runnable() {
                    public void run() {
                        Vungle._instance.downloadAdContent(str, findValidAdvertisementForPlacement, publisherDirectDownload2, r3);
                    }
                });
            } else if (loadAdCallback != null) {
                loadAdCallback.onError(str, new VungleException(8));
            }
        } else if (loadAdCallback != null) {
            loadAdCallback.onError(str, new VungleException(9));
        }
    }

    private static void clearCache() {
        if (isInitialized()) {
            _instance.storage.clearAllData();
            _instance.configure(_instance.initCallback);
            return;
        }
        Log.e(TAG, "Vungle not initialized");
    }

    private void fetchAdMetadata(String str, PublisherDirectDownload publisherDirectDownload2, @NonNull DownloadCallback downloadCallback, HeaderBiddingCallback headerBiddingCallback2) {
        final DownloadCallback downloadCallback2 = downloadCallback;
        final String str2 = str;
        final HeaderBiddingCallback headerBiddingCallback3 = headerBiddingCallback2;
        final PublisherDirectDownload publisherDirectDownload3 = publisherDirectDownload2;
        VungleApiClient.requestAd(str, headerBiddingCallback2 != null).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    downloadCallback2.onDownloadFailed(new VungleException(1), (String) null);
                } else if (!response.isSuccessful()) {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    Placement placement = (Placement) Vungle._instance.storage.load(str2, Placement.class);
                    if (retryAfterHeaderValue <= 0 || placement == null || !placement.isAutoCached()) {
                        Log.e(Vungle.TAG, "Failed to retrieve advertisement information");
                        downloadCallback2.onDownloadFailed(new VungleException(2), (String) null);
                        return;
                    }
                    Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str2, true).setDelay(retryAfterHeaderValue));
                    downloadCallback2.onDownloadFailed(new VungleException(14), (String) null);
                } else {
                    Placement placement2 = (Placement) Vungle.this.storage.load(str2, Placement.class);
                    if (placement2 == null) {
                        Log.e(Vungle.TAG, "Placement metadata not found for requested advertisement.");
                        downloadCallback2.onDownloadFailed(new VungleException(2), (String) null);
                        return;
                    }
                    JsonObject body = response.body();
                    if (body == null || !body.has("ads") || body.get("ads").isJsonNull()) {
                        downloadCallback2.onDownloadFailed(new VungleError(0), (String) null);
                        return;
                    }
                    JsonArray asJsonArray = body.getAsJsonArray("ads");
                    if (asJsonArray == null || asJsonArray.size() == 0) {
                        downloadCallback2.onDownloadFailed(new VungleException(1), (String) null);
                        return;
                    }
                    JsonObject asJsonObject = asJsonArray.get(0).getAsJsonObject();
                    try {
                        Advertisement advertisement = new Advertisement(asJsonObject);
                        if (headerBiddingCallback3 != null) {
                            headerBiddingCallback3.onBidTokenAvailable(str2, advertisement.getBidToken());
                        }
                        Vungle._instance.storage.saveAndApplyState(advertisement, str2, 0);
                        Vungle.this.downloadAdContent(str2, advertisement, publisherDirectDownload3, downloadCallback2);
                    } catch (IllegalArgumentException unused) {
                        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("ad_markup");
                        if (asJsonObject2.has("sleep")) {
                            int asInt = asJsonObject2.get("sleep").getAsInt();
                            placement2.snooze((long) asInt);
                            Vungle.this.storage.save(placement2);
                            if (placement2.isAutoCached()) {
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str2, true).setDelay((long) (asInt * 1000)));
                            }
                        }
                        downloadCallback2.onDownloadFailed(new VungleException(1), (String) null);
                    }
                }
            }

            public void onFailure(Call<JsonObject> call, Throwable th) {
                downloadCallback2.onDownloadFailed(th, (String) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void downloadAdContent(final String str, final Advertisement advertisement, PublisherDirectDownload publisherDirectDownload2, @NonNull final DownloadCallback downloadCallback) {
        DirectDownloadStrategy directDownloadStrategy = (publisherDirectDownload2 == null || TextUtils.isEmpty(advertisement.getAdMarketId())) ? null : new DirectDownloadStrategy(publisherDirectDownload2);
        if (directDownloadStrategy != null) {
            directDownloadStrategy.isApplicationAvailable(advertisement.getAdMarketId(), new DownloadStrategy.VerificationCallback() {
                public void onResult(boolean z) {
                    if (z) {
                        Log.d(Vungle.TAG, "fetchAdMetadata: downloading assets ");
                        Vungle.this.downloadAdAssets(advertisement, downloadCallback, str);
                        return;
                    }
                    downloadCallback.onDownloadFailed(new VungleException(5), (String) null);
                }
            });
        } else {
            downloadAdAssets(advertisement, downloadCallback, str);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0012  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void downloadAdAssets(com.vungle.warren.model.Advertisement r9, com.vungle.warren.Vungle.DownloadCallback r10, java.lang.String r11) {
        /*
            r8 = this;
            java.util.Map r0 = r9.getDownloadableUrls()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0062
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x003c
            java.lang.Object r2 = r1.getValue()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x003c
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            boolean r1 = android.webkit.URLUtil.isValidUrl(r1)
            if (r1 != 0) goto L_0x000c
        L_0x003c:
            com.vungle.warren.error.VungleException r11 = new com.vungle.warren.error.VungleException
            r0 = 11
            r11.<init>(r0)
            r0 = 0
            r10.onDownloadFailed(r11, r0)
            java.lang.String r10 = TAG
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Aborting, Failed to download Ad assets for: "
            r11.append(r0)
            java.lang.String r9 = r9.getId()
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            android.util.Log.e(r10, r9)
            return
        L_0x0062:
            com.vungle.warren.Storage r0 = r8.storage
            java.lang.String r1 = r9.getId()
            java.io.File r0 = r0.getAdvertisementAssetDirectory(r1)
            java.util.Map r1 = r9.getDownloadableUrls()
            int r4 = r1.size()
            com.vungle.warren.Vungle$9 r1 = new com.vungle.warren.Vungle$9
            r2 = r1
            r3 = r8
            r5 = r9
            r6 = r10
            r7 = r11
            r2.<init>(r4, r5, r6, r7)
            r11 = 8
            java.util.Map r2 = r9.getDownloadableUrls()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.util.Set r2 = r2.entrySet()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
        L_0x008c:
            boolean r3 = r2.hasNext()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            if (r3 == 0) goto L_0x011c
            java.lang.Object r3 = r2.next()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r5.<init>()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r6 = r0.getPath()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r5.append(r6)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r6 = java.io.File.separator     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r5.append(r6)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.Object r6 = r3.getKey()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r5.append(r6)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.Object r5 = r3.getValue()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            boolean r5 = android.webkit.URLUtil.isHttpsUrl(r5)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            if (r5 != 0) goto L_0x00e3
            java.lang.Object r5 = r3.getValue()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            boolean r5 = android.webkit.URLUtil.isHttpUrl(r5)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            if (r5 == 0) goto L_0x00d4
            goto L_0x00e3
        L_0x00d4:
            com.vungle.warren.error.VungleError r3 = new com.vungle.warren.error.VungleError     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r4 = 10
            r3.<init>(r4)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r4 = r9.getId()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r10.onDownloadFailed(r3, r4)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            goto L_0x008c
        L_0x00e3:
            com.vungle.warren.network.Downloader r5 = r8.downloader     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.Object r3 = r3.getValue()     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            r5.download(r3, r4, r1)     // Catch:{ IOException -> 0x0106, IllegalStateException -> 0x00ef }
            goto L_0x008c
        L_0x00ef:
            r0 = move-exception
            com.vungle.warren.error.VungleError r1 = new com.vungle.warren.error.VungleError
            r1.<init>(r11)
            java.lang.String r9 = r9.getId()
            r10.onDownloadFailed(r1, r9)
            java.lang.String r9 = TAG
            java.lang.String r10 = android.util.Log.getStackTraceString(r0)
            android.util.Log.e(r9, r10)
            goto L_0x011c
        L_0x0106:
            r0 = move-exception
            com.vungle.warren.error.VungleError r1 = new com.vungle.warren.error.VungleError
            r1.<init>(r11)
            java.lang.String r9 = r9.getId()
            r10.onDownloadFailed(r1, r9)
            java.lang.String r9 = TAG
            java.lang.String r10 = android.util.Log.getStackTraceString(r0)
            android.util.Log.e(r9, r10)
        L_0x011c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.Vungle.downloadAdAssets(com.vungle.warren.model.Advertisement, com.vungle.warren.Vungle$DownloadCallback, java.lang.String):void");
    }

    public static VungleNativeAd getNativeAd(String str, PlayAdCallback playAdCallback) {
        return getNativeAd(str, playAdCallback, (PublisherDirectDownload) null);
    }

    public static VungleNativeAd getNativeAd(final String str, final PlayAdCallback playAdCallback, PublisherDirectDownload publisherDirectDownload2) {
        DirectDownloadAdapter directDownloadAdapter = null;
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, returned VungleNativeAd = null");
            return null;
        }
        Placement placement = (Placement) _instance.storage.load(str, Placement.class);
        if (placement == null) {
            playAdCallback.onError(str, new Throwable("No placement for ID " + str + " found. Please use a valid placement ID"));
            return null;
        }
        final Advertisement findValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(str);
        if (findValidAdvertisementForPlacement == null) {
            Log.e(TAG, "No Advertisement for ID");
            return null;
        } else if (!canPlayAd(findValidAdvertisementForPlacement)) {
            if (findValidAdvertisementForPlacement != null && findValidAdvertisementForPlacement.getState() == 1) {
                _instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 4);
                if (placement.isAutoCached()) {
                    _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                }
            }
            return null;
        } else if (Boolean.TRUE.equals(_instance.playOperations.get(str)) || Boolean.TRUE.equals(_instance.loadOperations.get(str))) {
            playAdCallback.onError(str, new VungleException(8));
            return null;
        } else if (findValidAdvertisementForPlacement.getAdType() != 1) {
            playAdCallback.onError(str, new Throwable(str + " is not an MRAID compatible placement. Please use a valid placement ID"));
            return null;
        } else {
            if (publisherDirectDownload2 != null) {
                directDownloadAdapter = new DirectDownloadAdapter(publisherDirectDownload2, findValidAdvertisementForPlacement.getAdMarketId());
            }
            _instance.playOperations.put(str, true);
            return new VungleNativeView(_instance.context.getApplicationContext(), str, directDownloadAdapter, new AdvertisementPresenter.EventListener() {
                int percentViewed = -1;
                boolean succesfulView = false;

                public void onNext(String str, String str2) {
                    boolean z = false;
                    if (str.equals("start")) {
                        Vungle._instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 2);
                        if (playAdCallback != null) {
                            playAdCallback.onAdStart(str);
                        }
                        this.percentViewed = 0;
                        Placement placement = (Placement) Vungle._instance.storage.load(str, Placement.class);
                        if (placement != null && placement.isAutoCached()) {
                            Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(str, true));
                        }
                    } else if (str.equals("end")) {
                        Log.d(TMMediationNetworks.VUNGLE_NAME, "Cleaning up metadata and assets for placement " + str + " and advertisement " + findValidAdvertisementForPlacement.getId());
                        Vungle._instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 3);
                        Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                        Vungle._instance.playOperations.put(str, false);
                        if (playAdCallback != null) {
                            PlayAdCallback playAdCallback = playAdCallback;
                            String str3 = str;
                            boolean z2 = this.succesfulView || this.percentViewed >= 80;
                            if (str2 != null && str2.equals("isCTAClicked")) {
                                z = true;
                            }
                            playAdCallback.onAdEnd(str3, z2, z);
                        }
                    } else if (str.equals("successfulView")) {
                        this.succesfulView = true;
                    } else if (str.startsWith("percentViewed")) {
                        String[] split = str.split(":");
                        if (split.length == 2) {
                            this.percentViewed = Integer.parseInt(split[1]);
                        }
                    }
                }

                public void onError(Throwable th) {
                    Vungle._instance.playOperations.put(str, false);
                    Vungle._instance.storage.saveAndApplyState(findValidAdvertisementForPlacement, str, 4);
                    if (playAdCallback != null) {
                        playAdCallback.onError(str, th);
                    }
                }
            });
        }
    }

    public static Collection<String> getValidPlacements() {
        if (isInitialized()) {
            return _instance.storage.getValidPlacements();
        }
        Log.e(TAG, "Vungle is not initialized return empty placemetns list");
        return Collections.emptyList();
    }

    static void handleApkDirectDownloads(Context context2) {
        APKDirectDownloadManager.handleDownload(context2);
    }

    public static void updateConsentStatus(@NonNull Consent consent, @NonNull String str) {
        if (!isDepInit.get()) {
            _instance.tempConsent = consent;
            _instance.tempConsentVersion = str;
            return;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            cookie = new Cookie(Cookie.CONSENT_COOKIE);
        }
        cookie.putValue("consent_status", consent == Consent.OPTED_IN ? "opted_in" : "opted_out");
        cookie.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        cookie.putValue("consent_source", "publisher");
        if (str == null) {
            str = "";
        }
        cookie.putValue("consent_message_version", str);
        _instance.storage.save(cookie);
        _instance.tempConsent = null;
        _instance.tempConsentVersion = null;
    }

    public static Consent getConsentStatus() {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, consent is null");
            return null;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return "opted_in".equals(cookie.getString("consent_status")) ? Consent.OPTED_IN : Consent.OPTED_OUT;
    }

    public static String getConsentMessageVersion() {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, please wait initialize or wait until Vungle is intialized to get Consent Message Version");
            return null;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("consent_message_version");
    }

    public static boolean closeFlexViewAd(@NonNull String str) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, can't close flex view ad");
            return false;
        }
        Intent intent = new Intent("AdvertisementBus");
        intent.putExtra(VungleActivity.PLACEMENT_EXTRA, str);
        intent.putExtra(TJAdUnitConstants.String.COMMAND, "closeFlex");
        _instance.context.sendBroadcast(intent);
        return true;
    }

    public static void setUserLegacyID(String str) {
        if (isInitialized() || isInitializing.get()) {
            VungleApiClient.updateIMEI(str, _instance.shouldTransmitIMEI);
        } else {
            _instance.userIMEI = str;
        }
    }

    public static void setHeaderBiddingCallback(HeaderBiddingCallback headerBiddingCallback2) {
        _instance.headerBiddingCallback = headerBiddingCallback2;
    }
}
