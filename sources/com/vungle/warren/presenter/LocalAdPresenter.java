package com.vungle.warren.presenter;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.moat.analytics.mobile.vng.MoatFactory;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.Storage;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.VungleWebClient;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalAdPresenter implements AdvertisementPresenter {
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static final String EXTRA_IN_POST = "in_post_roll";
    private static final String EXTRA_REPORT = "saved_report";
    private static final String TAG = "LocalAdPresenter";
    private HashMap<String, String> adIds;
    /* access modifiers changed from: private */
    public AdView adView;
    /* access modifiers changed from: private */
    public Advertisement advertisement;
    private File assetDir;
    private AdvertisementPresenter.EventListener bus;
    private Advertisement.Checkpoint checkpoint;
    private byte checkpointReached;
    private String dialogBody = "If you exit now, you will not get your reward";
    private String dialogClose = "Close";
    private String dialogContinue = "Continue";
    private String dialogTitle = "Are you sure?";
    private boolean directDownloadApkEnabled;
    private int duration;
    private Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean inPost;
    private AtomicBoolean isDestroying = new AtomicBoolean(false);
    private Queue<Pair<Integer, MoatAdEventType>> moatQuartileTrackers;
    private boolean muted;
    private Placement placement;
    private Report report;
    private AtomicBoolean sendReportIncentivized = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Storage storage;
    /* access modifiers changed from: private */
    public boolean userExitEnabled;
    private String userID;
    private ReactiveVideoTracker videoTracker;
    private VideoView videoView;
    private VungleWebClient webClient;

    public void notifyPropertiesChanged() {
    }

    public void setAdVisibility(boolean z) {
    }

    public void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter) {
    }

    public LocalAdPresenter(Advertisement advertisement2, Placement placement2, Storage storage2, File file, String str) {
        this.advertisement = advertisement2;
        this.placement = placement2;
        this.storage = storage2;
        this.userID = str;
        this.assetDir = file;
    }

    public void setEventListener(AdvertisementPresenter.EventListener eventListener) {
        this.bus = eventListener;
    }

    public void reportError(String str) {
        this.report.recordError(str);
        this.storage.save(this.report);
        if (this.inPost || !this.advertisement.hasPostroll()) {
            if (this.bus != null) {
                this.bus.onError(new Throwable(str));
            }
            this.adView.close();
            return;
        }
        playPost();
    }

    public void reportAction(String str, @Nullable String str2) {
        if (str.equals(TJAdUnitConstants.String.VIDEO_LENGTH)) {
            this.duration = Integer.parseInt(str2);
            this.report.setAdDuration(this.duration);
            this.storage.save(this.report);
            return;
        }
        if (str.equals("mute")) {
            for (String pingTPAT : this.advertisement.getTpatUrls("mute")) {
                VungleApiClient.pingTPAT(pingTPAT);
            }
        }
        if (str.equals("unmute")) {
            for (String pingTPAT2 : this.advertisement.getTpatUrls("unmute")) {
                VungleApiClient.pingTPAT(pingTPAT2);
            }
        }
        if (str.equals("video_close")) {
            for (String pingTPAT3 : this.advertisement.getTpatUrls("video_close")) {
                VungleApiClient.pingTPAT(pingTPAT3);
            }
        }
        this.report.recordAction(str, str2, System.currentTimeMillis());
        this.storage.save(this.report);
    }

    public WebViewClient getWebViewClient() {
        if (this.webClient == null) {
            this.webClient = new VungleWebClient(this.advertisement, this.placement, (DirectDownloadAdapter) null);
        }
        return this.webClient;
    }

    public void initializeViewabilityTracker(int i, VideoView videoView2) {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null && this.adIds != null) {
            this.videoView = videoView2;
            Log.d(TAG, "initializeViewabilityTracker");
            this.videoTracker.trackVideoAd(this.adIds, Integer.valueOf(i), videoView2);
        }
    }

    public void stopViewabilityTracker() {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null) {
            int currentPosition = this.videoView != null ? this.videoView.getCurrentPosition() : 0;
            Log.d(TAG, "stopViewabilityTracker: " + currentPosition);
            this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED, Integer.valueOf(currentPosition)));
            this.videoTracker.stopTracking();
            Log.d(TAG, "stopViewabilityTracker: Success !!");
        }
    }

    public void attach(AdView adView2) {
        this.isDestroying.set(false);
        this.adView = adView2;
        int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            this.muted = (settings & 1) == 1;
            this.userExitEnabled = (settings & 2) == 2;
            this.directDownloadApkEnabled = (settings & 32) == 32;
        }
        int i = 4;
        if ((this.advertisement.getAdConfig().getSettings() & 16) != 16) {
            switch (this.advertisement.getOrientation()) {
                case 0:
                    i = 1;
                    break;
                case 1:
                    i = 0;
                    break;
                case 2:
                    break;
                default:
                    i = -1;
                    break;
            }
        }
        Log.d(TAG, "requested orientation " + i);
        adView2.setOrientation(i);
    }

    /* access modifiers changed from: private */
    public void playPost() {
        if (isPostRollPresent()) {
            File file = new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP);
            File file2 = new File(file.getPath() + File.separator + "index.html");
            if (!file2.exists()) {
                if (this.bus != null) {
                    this.bus.onError(new VungleException(10));
                }
                this.adView.close();
                return;
            }
            this.adView.showWebsite("file://" + file2.getPath());
        }
        for (String pingTPAT : this.advertisement.getTpatUrls("postroll_view")) {
            VungleApiClient.pingTPAT(pingTPAT);
        }
        this.inPost = true;
    }

    /* access modifiers changed from: private */
    public boolean isPostRollPresent() {
        return new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP).exists();
    }

    public void prepare(Bundle bundle) {
        String str;
        String str2;
        String str3 = null;
        if (this.bus != null) {
            this.bus.onNext("start", (String) null);
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if (cookie == null) {
            str = null;
        } else {
            str = cookie.getString("userID");
        }
        this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), str);
        if (this.advertisement.getCheckpoints() != null && !this.advertisement.getCheckpoints().isEmpty()) {
            this.checkpoint = this.advertisement.getCheckpoints().get(0);
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            MoatOptions moatOptions = new MoatOptions();
            moatOptions.disableAdIdCollection = true;
            moatOptions.disableLocationServices = true;
            moatOptions.loggingEnabled = false;
            this.moatQuartileTrackers = new LinkedList();
            this.moatQuartileTrackers.add(new Pair(0, MoatAdEventType.AD_EVT_START));
            this.moatQuartileTrackers.add(new Pair(25, MoatAdEventType.AD_EVT_FIRST_QUARTILE));
            this.moatQuartileTrackers.add(new Pair(50, MoatAdEventType.AD_EVT_MID_POINT));
            this.moatQuartileTrackers.add(new Pair(75, MoatAdEventType.AD_EVT_THIRD_QUARTILE));
            this.adIds = new HashMap<>();
            if (!this.advertisement.getMoatVastExtra().isEmpty()) {
                this.adIds.put("zMoatVASTIDs", this.advertisement.getMoatVastExtra());
            }
            String appID = this.advertisement.getAppID();
            String appID2 = this.advertisement.getAppID();
            if (appID2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(appID2.substring(3));
                    appID = jSONObject.isNull(TapjoyConstants.TJC_APP_ID) ? null : jSONObject.optString(TapjoyConstants.TJC_APP_ID, (String) null);
                } catch (JSONException e) {
                    Log.e(TAG, "JsonException : ", e);
                }
            }
            String campaign = this.advertisement.getCampaign();
            int indexOf = campaign.indexOf(124);
            if (indexOf != -1) {
                int i = indexOf + 1;
                int indexOf2 = campaign.indexOf(124, i);
                str2 = campaign.substring(0, indexOf);
                if (indexOf2 != -1) {
                    str3 = campaign.substring(i, indexOf2);
                }
            } else {
                str2 = null;
            }
            HashMap<String, String> hashMap = this.adIds;
            if (appID == null || appID.isEmpty()) {
                appID = this.advertisement.getId();
            }
            hashMap.put("level1", appID);
            HashMap<String, String> hashMap2 = this.adIds;
            if (str2 == null || str2.isEmpty()) {
                str2 = this.advertisement.getId();
            }
            hashMap2.put("level2", str2);
            HashMap<String, String> hashMap3 = this.adIds;
            if (str3 == null || str3.isEmpty()) {
                str3 = this.advertisement.getId();
            }
            hashMap3.put("level3", str3);
            this.adIds.put("level4", this.placement.getId());
            Cookie cookie2 = (Cookie) this.storage.load(Cookie.APP_ID, Cookie.class);
            if (cookie2 != null && !TextUtils.isEmpty(cookie2.getString(Cookie.APP_ID))) {
                this.adIds.put("slicer1", cookie2.getString(Cookie.APP_ID));
            }
            this.videoTracker = (ReactiveVideoTracker) MoatFactory.create().createCustomTracker(new ReactiveVideoTrackerPlugin("vunglenativevideo893259554489"));
        }
        this.adView.updateWindow(false);
        if (this.advertisement.isCtaOverlayEnabled()) {
            if (this.advertisement.isCtaShowOnClick()) {
                this.adView.showCTAOverlay(this.advertisement.isCtaShowOnClick(), true, this.advertisement.getCtaClickArea());
            } else {
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), false, LocalAdPresenter.this.advertisement.getCtaClickArea());
                    }
                }, (long) this.advertisement.getCtaTimeShow());
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), true, LocalAdPresenter.this.advertisement.getCtaClickArea());
                    }
                }, (long) this.advertisement.getCtaTimeEnabled());
            }
        }
        final Cookie cookie3 = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie3 == null || !cookie3.getBoolean("is_country_data_protected").booleanValue() || !EnvironmentCompat.MEDIA_UNKNOWN.equals(cookie3.getString("consent_status"))) {
            play();
            return;
        }
        AnonymousClass3 r6 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String str = "opted_out_by_timeout";
                switch (i) {
                    case -2:
                        str = "opted_out";
                        break;
                    case -1:
                        str = "opted_in";
                        break;
                }
                cookie3.putValue("consent_status", str);
                cookie3.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                cookie3.putValue("consent_source", "vungle_modal");
                LocalAdPresenter.this.storage.save(cookie3);
                LocalAdPresenter.this.play();
            }
        };
        cookie3.putValue("consent_status", "opted_out_by_timeout");
        cookie3.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        cookie3.putValue("consent_source", "vungle_modal");
        this.storage.save(cookie3);
        this.adView.showDialog(cookie3.getString("consent_title"), cookie3.getString("consent_message"), cookie3.getString("button_accept"), cookie3.getString("button_deny"), r6);
    }

    public boolean handleExit(String str) {
        if (this.inPost) {
            closeAndReport();
            return true;
        } else if (!this.userExitEnabled) {
            return false;
        } else {
            if (this.placement.isIncentivized()) {
                Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
                String str2 = this.dialogTitle;
                String str3 = this.dialogBody;
                String str4 = this.dialogContinue;
                String str5 = this.dialogClose;
                if (cookie != null) {
                    str2 = cookie.getString(TJAdUnitConstants.String.TITLE) == null ? this.dialogTitle : cookie.getString(TJAdUnitConstants.String.TITLE);
                    str3 = cookie.getString("body") == null ? this.dialogBody : cookie.getString("body");
                    str4 = cookie.getString("continue") == null ? this.dialogContinue : cookie.getString("continue");
                    str5 = cookie.getString(TJAdUnitConstants.String.CLOSE) == null ? this.dialogClose : cookie.getString(TJAdUnitConstants.String.CLOSE);
                }
                this.adView.showDialog(str2, str3, str4, str5, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -2) {
                            LocalAdPresenter.this.reportAction("video_close", (String) null);
                            if (!LocalAdPresenter.this.advertisement.hasPostroll()) {
                                LocalAdPresenter.this.closeAndReport();
                            } else if (LocalAdPresenter.this.isPostRollPresent()) {
                                LocalAdPresenter.this.playPost();
                            } else {
                                LocalAdPresenter.this.closeAndReport();
                            }
                        }
                    }
                });
                return false;
            }
            reportAction("video_close", (String) null);
            if (!this.advertisement.hasPostroll()) {
                closeAndReport();
                return true;
            } else if (!isPostRollPresent()) {
                return false;
            } else {
                playPost();
                return false;
            }
        }
    }

    private boolean isWebPageBlank() {
        String websiteUrl = this.adView.getWebsiteUrl();
        return TextUtils.isEmpty(websiteUrl) || "about:blank".equalsIgnoreCase(websiteUrl);
    }

    public void play() {
        if (!this.inPost) {
            this.adView.playVideo(Uri.fromFile(new File(this.assetDir.getPath() + File.separator + Advertisement.KEY_VIDEO)), this.muted);
            int showCloseDelay = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
            if (showCloseDelay > 0) {
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        boolean unused = LocalAdPresenter.this.userExitEnabled = true;
                        if (!LocalAdPresenter.this.inPost) {
                            LocalAdPresenter.this.adView.showCloseButton();
                        }
                    }
                }, (long) showCloseDelay);
                return;
            }
            this.userExitEnabled = true;
            this.adView.showCloseButton();
        } else if (isWebPageBlank()) {
            playPost();
        }
    }

    public void stop(boolean z, boolean z2) {
        if (z || !z2) {
            if (this.inPost || z2) {
                this.adView.showWebsite("about:blank");
            }
        } else if (!this.isDestroying.getAndSet(true)) {
            String str = null;
            reportAction(TJAdUnitConstants.String.CLOSE, (String) null);
            this.handler.removeCallbacksAndMessages((Object) null);
            if (this.bus != null) {
                AdvertisementPresenter.EventListener eventListener = this.bus;
                if (this.report.isCTAClicked()) {
                    str = "isCTAClicked";
                }
                eventListener.onNext("end", str);
            }
            this.adView.close();
        }
    }

    public void reportMute(boolean z) {
        if (z) {
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
            }
        } else if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
        }
    }

    public void onProgressUpdate(int i) {
        if (this.bus != null) {
            this.bus.onNext("percentViewed:" + i, (String) null);
        }
        List<Advertisement.Checkpoint> checkpoints = this.advertisement.getCheckpoints();
        if (i == 100) {
            int size = checkpoints.size() - 1;
            if (size > 0) {
                Advertisement.Checkpoint checkpoint2 = checkpoints.get(size);
                if (checkpoint2.getPercentage() == 100) {
                    for (String pingTPAT : checkpoint2.getUrls()) {
                        VungleApiClient.pingTPAT(pingTPAT);
                    }
                }
            }
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                if (this.videoView != null) {
                    this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE, Integer.valueOf(this.videoView.getCurrentPosition())));
                }
                this.videoTracker.stopTracking();
            }
            if (this.advertisement.hasPostroll()) {
                if (isPostRollPresent()) {
                    playPost();
                } else {
                    closeAndReport();
                }
            }
        }
        this.report.recordProgress((int) (((float) this.duration) * (((float) i) / 100.0f)));
        this.storage.save(this.report);
        if (this.checkpoint != null && i > this.checkpoint.getPercentage()) {
            for (String pingTPAT2 : this.checkpoint.getUrls()) {
                VungleApiClient.pingTPAT(pingTPAT2);
            }
            this.checkpoint = null;
            if (this.checkpointReached < checkpoints.size() - 1) {
                byte b = (byte) (this.checkpointReached + 1);
                this.checkpointReached = b;
                this.checkpoint = checkpoints.get(b);
            }
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && !this.moatQuartileTrackers.isEmpty() && i >= ((Integer) this.moatQuartileTrackers.peek().first).intValue()) {
            this.videoTracker.dispatchEvent(new MoatAdEvent((MoatAdEventType) this.moatQuartileTrackers.poll().second, Integer.valueOf(i)));
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
        if (this.placement.isIncentivized() && i > 75 && cookie != null && cookie.getBoolean("isReportIncentivizedEnabled").booleanValue() && !this.sendReportIncentivized.getAndSet(true)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("placement_reference_id", new JsonPrimitive(this.placement.getId()));
            jsonObject.add(TapjoyConstants.TJC_APP_ID, new JsonPrimitive(this.advertisement.getAppID()));
            jsonObject.add("adStartTime", new JsonPrimitive((Number) Long.valueOf(this.report.getAdStartTime())));
            jsonObject.add("user", new JsonPrimitive(this.report.getUserID()));
            VungleApiClient.ri(jsonObject).enqueue(new Callback<JsonObject>() {
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d(LocalAdPresenter.TAG, "send RI success");
                }

                public void onFailure(Call<JsonObject> call, Throwable th) {
                    Log.d(LocalAdPresenter.TAG, "send RI Failure");
                }
            });
        }
    }

    public void generateSaveState(Bundle bundle) {
        if (bundle != null) {
            this.storage.save(this.report);
            bundle.putString(EXTRA_REPORT, this.report.getId());
            bundle.putBoolean(EXTRA_INCENTIVIZED_SENT, this.sendReportIncentivized.get());
            bundle.putBoolean(EXTRA_IN_POST, this.inPost);
        }
    }

    public void restoreFromSave(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getBoolean(EXTRA_INCENTIVIZED_SENT, false)) {
                this.sendReportIncentivized.set(true);
            }
            String string = bundle.getString(EXTRA_REPORT);
            this.report = TextUtils.isEmpty(string) ? null : (Report) this.storage.load(string, Report.class);
            if (this.report == null) {
                this.adView.close();
            } else {
                this.inPost = bundle.getBoolean(EXTRA_IN_POST, false);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleAction(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = r7.hashCode()
            r1 = -314498168(0xffffffffed412388, float:-3.7358476E27)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x002a
            r1 = 94756344(0x5a5ddf8, float:1.5598064E-35)
            if (r0 == r1) goto L_0x0020
            r1 = 1427818632(0x551ac888, float:1.06366291E13)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "download"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "close"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "privacy"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            switch(r0) {
                case 0: goto L_0x00ca;
                case 1: goto L_0x004f;
                case 2: goto L_0x00cd;
                default: goto L_0x0038;
            }
        L_0x0038:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown action "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L_0x004f:
            r7 = 0
            com.vungle.warren.model.Advertisement r0 = r6.advertisement     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r1 = "postroll_click"
            java.lang.String[] r0 = r0.getTpatUrls(r1)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            int r1 = r0.length     // Catch:{ ActivityNotFoundException -> 0x00bf }
            r4 = 0
        L_0x005a:
            if (r4 >= r1) goto L_0x0064
            r5 = r0[r4]     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.network.VungleApiClient.pingTPAT(r5)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            int r4 = r4 + 1
            goto L_0x005a
        L_0x0064:
            com.vungle.warren.model.Advertisement r0 = r6.advertisement     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r1 = "click_url"
            java.lang.String[] r0 = r0.getTpatUrls(r1)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            int r1 = r0.length     // Catch:{ ActivityNotFoundException -> 0x00bf }
            r4 = 0
        L_0x006e:
            if (r4 >= r1) goto L_0x0078
            r5 = r0[r4]     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.network.VungleApiClient.pingTPAT(r5)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            int r4 = r4 + 1
            goto L_0x006e
        L_0x0078:
            java.lang.String r0 = "download"
            r6.reportAction(r0, r7)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            r6.closeAndReport()     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.model.Advertisement r0 = r6.advertisement     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r0 = r0.getCTAURL(r3)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.model.Advertisement r1 = r6.advertisement     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r1 = r1.getCTAURL(r2)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            if (r2 != 0) goto L_0x0095
            com.vungle.warren.network.VungleApiClient.pingTPAT(r1)     // Catch:{ ActivityNotFoundException -> 0x00bf }
        L_0x0095:
            boolean r1 = r6.directDownloadApkEnabled     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.model.Advertisement r2 = r6.advertisement     // Catch:{ ActivityNotFoundException -> 0x00bf }
            boolean r2 = r2.isRequiresNonMarketInstall()     // Catch:{ ActivityNotFoundException -> 0x00bf }
            boolean r1 = com.vungle.warren.download.APKDirectDownloadManager.isDirectDownloadEnabled(r1, r2)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            if (r1 == 0) goto L_0x00a7
            com.vungle.warren.download.APKDirectDownloadManager.download(r0)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            goto L_0x00cd
        L_0x00a7:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r2 = "android.intent.action.VIEW"
            r1.<init>(r2)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            r1.setData(r0)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            com.vungle.warren.ui.AdView r0 = r6.adView     // Catch:{ ActivityNotFoundException -> 0x00bf }
            java.lang.String r1 = r1.toUri(r3)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            r0.open(r1)     // Catch:{ ActivityNotFoundException -> 0x00bf }
            goto L_0x00cd
        L_0x00bf:
            android.os.Handler r0 = r6.handler
            r0.removeCallbacksAndMessages(r7)
            com.vungle.warren.ui.AdView r7 = r6.adView
            r7.close()
            goto L_0x00cd
        L_0x00ca:
            r6.closeAndReport()
        L_0x00cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.presenter.LocalAdPresenter.handleAction(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void closeAndReport() {
        reportAction(TJAdUnitConstants.String.CLOSE, (String) null);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.adView.close();
    }
}
