package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vungle.warren.AdConfig;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Advertisement implements Memorable, Cloneable {
    public static final int DONE = 3;
    public static final int ERROR = 4;
    public static final String KEY_APP_ICON = "appicon";
    public static final String KEY_POSTROLL = "postroll";
    public static final String KEY_POWERED_BY_VUNGLE = "powervungle";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_VIDEO = "video";
    public static final int LANDSCAPE = 1;
    public static final int NEW = 0;
    public static final int PORTRAIT = 0;
    public static final String POSTROLL_UNZIP = "postrollUnzip";
    public static final int READY = 1;
    public static final int ROTATE = 2;
    private static final String TAG = "Advertisement";
    public static final int TYPE_VUNGLE_LOCAL = 0;
    public static final int TYPE_VUNGLE_MRAID = 1;
    public static final int VIEWING = 2;
    private AdConfig adConfig;
    private final String adMarketId;
    private final String adToken;
    @AdType
    private final int adType;
    private final String appID;
    private final String bidToken;
    private final String campaign;
    private final ArrayList<Checkpoint> checkpoints;
    private final String[] clickUrls;
    private final String[] closeUrls;
    private final int countdown;
    private final int ctaClickArea;
    private final String ctaDestinationUrl;
    private final boolean ctaOverlayEnabled;
    private final boolean ctaShowOnClick;
    private final int ctaTimeEnabled;
    private final int ctaTimeShow;
    private final String ctaUrl;
    private final int delay;
    private final boolean enableMoat;
    private final long expireTime;
    private final String identifier;
    private final String md5;
    private final String moatExtraVast;
    private final String[] muteUrls;
    private final String[] postRollClickUrls;
    private final String[] postRollViewUrls;
    private final String postrollBundleUrl;
    private final boolean requiresNonMarketInstall;
    private final int retryCount;
    private final int showCloseDelay;
    private final int showCloseIncentivized;
    private int state = 0;
    private final String templateId;
    private final Map<String, String> templateSettings;
    private final String templateType;
    private final String templateUrl;
    private final String[] unmuteUrls;
    private final int videoHeight;
    private final String videoIdentifier;
    private final String videoUrl;
    private final int videoWidth;

    public @interface AdType {
    }

    public @interface CacheKey {
    }

    public @interface Orientation {
    }

    public @interface State {
    }

    public Advertisement(byte[] bArr) {
        int i = 0;
        if (bArr.length != 0) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.adConfig = new AdConfig();
            this.adType = wrap.getInt();
            this.expireTime = wrap.getLong();
            this.delay = wrap.getInt();
            this.showCloseDelay = wrap.getInt();
            this.showCloseIncentivized = wrap.getInt();
            this.countdown = wrap.getInt();
            this.videoWidth = wrap.getInt();
            this.videoHeight = wrap.getInt();
            boolean z = true;
            this.ctaOverlayEnabled = wrap.get() == 1;
            this.ctaShowOnClick = wrap.get() == 1;
            this.ctaTimeEnabled = wrap.getInt();
            this.ctaTimeShow = wrap.getInt();
            this.ctaClickArea = wrap.getInt();
            this.retryCount = wrap.getInt();
            this.enableMoat = wrap.get() == 1;
            this.requiresNonMarketInstall = wrap.get() == 1;
            int i2 = wrap.getInt();
            int i3 = wrap.getInt();
            int i4 = wrap.getInt();
            this.adConfig.setFlexViewCloseTime(i3);
            this.adConfig.setOrdinal(i4);
            this.adConfig.setAutoRotate((i2 & 16) == 16);
            this.adConfig.setImmersiveMode((i2 & 4) == 4);
            this.adConfig.setMuted((i2 & 1) == 1);
            HashMap hashMap = new HashMap();
            hashMap.put(AdConfig.FLAG_DIRECT_DOWNLOAD, Boolean.valueOf((i2 & 32) == 32));
            this.adConfig.setExtraSettings(hashMap);
            this.adConfig.setTransitionAnimationEnabled((i2 & 8) != 8 ? false : z);
            this.identifier = MemoryUtils.extractString(wrap);
            this.appID = MemoryUtils.extractString(wrap);
            this.campaign = MemoryUtils.extractString(wrap);
            this.videoUrl = MemoryUtils.extractString(wrap);
            this.md5 = MemoryUtils.extractString(wrap);
            this.postrollBundleUrl = MemoryUtils.extractString(wrap);
            this.ctaDestinationUrl = MemoryUtils.extractString(wrap);
            this.ctaUrl = MemoryUtils.extractString(wrap);
            this.adToken = MemoryUtils.extractString(wrap);
            this.videoIdentifier = MemoryUtils.extractString(wrap);
            this.muteUrls = MemoryUtils.extractStringArray(wrap);
            this.unmuteUrls = MemoryUtils.extractStringArray(wrap);
            this.closeUrls = MemoryUtils.extractStringArray(wrap);
            this.postRollClickUrls = MemoryUtils.extractStringArray(wrap);
            this.postRollViewUrls = MemoryUtils.extractStringArray(wrap);
            this.clickUrls = MemoryUtils.extractStringArray(wrap);
            this.templateUrl = MemoryUtils.extractString(wrap);
            this.templateId = MemoryUtils.extractString(wrap);
            this.templateType = MemoryUtils.extractString(wrap);
            this.moatExtraVast = MemoryUtils.extractString(wrap);
            this.adMarketId = MemoryUtils.extractString(wrap);
            this.bidToken = MemoryUtils.extractString(wrap);
            int i5 = wrap.getInt();
            this.checkpoints = new ArrayList<>(i5);
            while (i < i5) {
                try {
                    this.checkpoints.add(MemoryUtils.extractMemorable(wrap, Checkpoint.class));
                    i++;
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException(e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException(e4);
                }
            }
            this.templateSettings = MemoryUtils.extractStringMap(wrap);
            this.state = wrap.getInt();
            return;
        }
        throw new IllegalArgumentException("Empty array cannot be used to construct Advertisement");
    }

    public boolean isCtaOverlayEnabled() {
        return this.ctaOverlayEnabled;
    }

    public boolean isCtaShowOnClick() {
        return this.ctaShowOnClick;
    }

    public int getCtaTimeEnabled() {
        return this.ctaTimeEnabled * 1000;
    }

    public int getCtaTimeShow() {
        return this.ctaTimeShow * 1000;
    }

    public int getCtaClickArea() {
        if (this.ctaClickArea >= 1) {
            return this.ctaClickArea;
        }
        return 1;
    }

    public boolean isRequiresNonMarketInstall() {
        return this.requiresNonMarketInstall;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x06d3  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x019b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Advertisement(@android.support.annotation.NonNull com.google.gson.JsonObject r14) throws java.lang.IllegalArgumentException {
        /*
            r13 = this;
            r13.<init>()
            r0 = 0
            r13.state = r0
            java.lang.String r1 = "ad_markup"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x06e3
            java.lang.String r1 = "ad_markup"
            com.google.gson.JsonObject r14 = r14.getAsJsonObject(r1)
            java.lang.String r1 = "adType"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x06db
            java.lang.String r1 = "adType"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            int r2 = r1.hashCode()
            r3 = -1852456483(0xffffffff9195c1dd, float:-2.3627532E-28)
            r4 = -1
            r5 = 1
            if (r2 == r3) goto L_0x0041
            r3 = -1851445271(0xffffffff91a52fe9, float:-2.6061937E-28)
            if (r2 == r3) goto L_0x0037
            goto L_0x004b
        L_0x0037:
            java.lang.String r2 = "vungle_mraid"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x004b
            r2 = 1
            goto L_0x004c
        L_0x0041:
            java.lang.String r2 = "vungle_local"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x004b
            r2 = 0
            goto L_0x004c
        L_0x004b:
            r2 = -1
        L_0x004c:
            switch(r2) {
                case 0: goto L_0x0167;
                case 1: goto L_0x006b;
                default: goto L_0x004f;
            }
        L_0x004f:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Unknown Ad Type "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r1 = "! Please add this ad type"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r14.<init>(r0)
            throw r14
        L_0x006b:
            r13.adType = r5
            java.lang.String r1 = ""
            r13.postrollBundleUrl = r1
            java.lang.String r1 = "templateSettings"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x015f
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r13.templateSettings = r1
            java.lang.String r1 = "templateSettings"
            com.google.gson.JsonObject r1 = r14.getAsJsonObject(r1)
            java.lang.String r2 = "normal_replacements"
            boolean r2 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r2)
            if (r2 == 0) goto L_0x00bc
            java.lang.String r2 = "normal_replacements"
            com.google.gson.JsonObject r2 = r1.getAsJsonObject(r2)
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x009c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00bc
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.util.Map<java.lang.String, java.lang.String> r6 = r13.templateSettings
            java.lang.Object r7 = r3.getKey()
            java.lang.Object r3 = r3.getValue()
            com.google.gson.JsonElement r3 = (com.google.gson.JsonElement) r3
            java.lang.String r3 = r3.getAsString()
            r6.put(r7, r3)
            goto L_0x009c
        L_0x00bc:
            java.lang.String r2 = "cacheable_replacements"
            boolean r2 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r2)
            if (r2 == 0) goto L_0x010a
            java.lang.String r2 = "cacheable_replacements"
            com.google.gson.JsonObject r1 = r1.getAsJsonObject(r2)
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00d2:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x010a
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            com.google.gson.JsonElement r3 = (com.google.gson.JsonElement) r3
            java.lang.String r6 = "url"
            boolean r3 = com.vungle.warren.model.JsonUtil.hasNonNull(r3, r6)
            if (r3 == 0) goto L_0x00d2
            java.util.Map<java.lang.String, java.lang.String> r3 = r13.templateSettings
            java.lang.Object r6 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            com.google.gson.JsonElement r2 = (com.google.gson.JsonElement) r2
            com.google.gson.JsonObject r2 = r2.getAsJsonObject()
            java.lang.String r7 = "url"
            com.google.gson.JsonElement r2 = r2.get(r7)
            java.lang.String r2 = r2.getAsString()
            r3.put(r6, r2)
            goto L_0x00d2
        L_0x010a:
            java.lang.String r1 = "templateId"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x0157
            java.lang.String r1 = "templateId"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.templateId = r1
            java.lang.String r1 = "template_type"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x014f
            java.lang.String r1 = "template_type"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.templateType = r1
            java.lang.String r1 = "templateURL"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x0147
            java.lang.String r1 = "templateURL"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.templateUrl = r1
            goto L_0x0193
        L_0x0147:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Template URL missing!"
            r14.<init>(r0)
            throw r14
        L_0x014f:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Template Type missing!"
            r14.<init>(r0)
            throw r14
        L_0x0157:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing templateID!"
            r14.<init>(r0)
            throw r14
        L_0x015f:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing template adConfig!"
            r14.<init>(r0)
            throw r14
        L_0x0167:
            r13.adType = r0
            java.lang.String r1 = "postBundle"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x017c
            java.lang.String r1 = "postBundle"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            goto L_0x017e
        L_0x017c:
            java.lang.String r1 = ""
        L_0x017e:
            r13.postrollBundleUrl = r1
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r13.templateSettings = r1
            java.lang.String r1 = ""
            r13.templateUrl = r1
            java.lang.String r1 = ""
            r13.templateId = r1
            java.lang.String r1 = ""
            r13.templateType = r1
        L_0x0193:
            java.lang.String r1 = "id"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x06d3
            java.lang.String r1 = "id"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.identifier = r1
            java.lang.String r1 = "campaign"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x06cb
            java.lang.String r1 = "campaign"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.campaign = r1
            java.lang.String r1 = "app_id"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x06c3
            java.lang.String r1 = "app_id"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.appID = r1
            java.lang.String r1 = "expiry"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            r2 = 1000(0x3e8, double:4.94E-321)
            if (r1 == 0) goto L_0x0200
            java.lang.String r1 = "expiry"
            com.google.gson.JsonElement r1 = r14.get(r1)
            boolean r1 = r1.isJsonNull()
            if (r1 != 0) goto L_0x0200
            java.lang.String r1 = "expiry"
            com.google.gson.JsonElement r1 = r14.get(r1)
            long r6 = r1.getAsLong()
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x01f8
            r13.expireTime = r6
            goto L_0x0207
        L_0x01f8:
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 / r2
            r13.expireTime = r6
            goto L_0x0207
        L_0x0200:
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 / r2
            r13.expireTime = r6
        L_0x0207:
            java.lang.String r1 = "tpat"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            r2 = 0
            if (r1 == 0) goto L_0x0482
            java.lang.String r1 = "tpat"
            com.google.gson.JsonObject r1 = r14.getAsJsonObject(r1)
            java.util.ArrayList r3 = new java.util.ArrayList
            r6 = 5
            r3.<init>(r6)
            r13.checkpoints = r3
            int r3 = r13.adType
            switch(r3) {
                case 0: goto L_0x025a;
                case 1: goto L_0x022b;
                default: goto L_0x0223;
            }
        L_0x0223:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unknown Ad Type!"
            r14.<init>(r0)
            throw r14
        L_0x022b:
            r3 = 0
        L_0x022c:
            if (r3 >= r6) goto L_0x0289
            int r7 = r3 * 25
            java.util.Locale r8 = java.util.Locale.ENGLISH
            java.lang.String r9 = "checkpoint.%d"
            java.lang.Object[] r10 = new java.lang.Object[r5]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            r10[r0] = r11
            java.lang.String r8 = java.lang.String.format(r8, r9, r10)
            boolean r9 = r1.has(r8)
            if (r9 == 0) goto L_0x0251
            com.vungle.warren.model.Advertisement$Checkpoint r9 = new com.vungle.warren.model.Advertisement$Checkpoint
            com.google.gson.JsonArray r8 = r1.getAsJsonArray(r8)
            byte r7 = (byte) r7
            r9.<init>(r8, r7)
            goto L_0x0252
        L_0x0251:
            r9 = r2
        L_0x0252:
            java.util.ArrayList<com.vungle.warren.model.Advertisement$Checkpoint> r7 = r13.checkpoints
            r7.add(r3, r9)
            int r3 = r3 + 1
            goto L_0x022c
        L_0x025a:
            java.lang.String r3 = "play_percentage"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x0289
            java.lang.String r3 = "play_percentage"
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r3)
            r6 = 0
        L_0x0269:
            int r7 = r3.size()
            if (r6 >= r7) goto L_0x0284
            java.util.ArrayList<com.vungle.warren.model.Advertisement$Checkpoint> r7 = r13.checkpoints
            com.vungle.warren.model.Advertisement$Checkpoint r8 = new com.vungle.warren.model.Advertisement$Checkpoint
            com.google.gson.JsonElement r9 = r3.get(r6)
            com.google.gson.JsonObject r9 = r9.getAsJsonObject()
            r8.<init>((com.google.gson.JsonObject) r9)
            r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0269
        L_0x0284:
            java.util.ArrayList<com.vungle.warren.model.Advertisement$Checkpoint> r3 = r13.checkpoints
            java.util.Collections.sort(r3)
        L_0x0289:
            java.lang.String r3 = "clickUrl"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x02bc
            java.lang.String r3 = "clickUrl"
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r3)
            int r6 = r3.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            r13.clickUrls = r6
            java.util.Iterator r3 = r3.iterator()
            r6 = 0
        L_0x02a4:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x02c0
            java.lang.Object r7 = r3.next()
            com.google.gson.JsonElement r7 = (com.google.gson.JsonElement) r7
            java.lang.String[] r8 = r13.clickUrls
            int r9 = r6 + 1
            java.lang.String r7 = r7.getAsString()
            r8[r6] = r7
            r6 = r9
            goto L_0x02a4
        L_0x02bc:
            java.lang.String[] r3 = new java.lang.String[r0]
            r13.clickUrls = r3
        L_0x02c0:
            java.lang.String r3 = "moat"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x02e7
            java.lang.String r3 = "moat"
            com.google.gson.JsonObject r3 = r1.getAsJsonObject(r3)
            java.lang.String r6 = "is_enabled"
            com.google.gson.JsonElement r6 = r3.get(r6)
            boolean r6 = r6.getAsBoolean()
            r13.enableMoat = r6
            java.lang.String r6 = "extra_vast"
            com.google.gson.JsonElement r3 = r3.get(r6)
            java.lang.String r3 = r3.getAsString()
            r13.moatExtraVast = r3
            goto L_0x02ed
        L_0x02e7:
            r13.enableMoat = r0
            java.lang.String r3 = ""
            r13.moatExtraVast = r3
        L_0x02ed:
            int r3 = r13.adType
            switch(r3) {
                case 0: goto L_0x0305;
                case 1: goto L_0x02fa;
                default: goto L_0x02f2;
            }
        L_0x02f2:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unknown AdType!"
            r14.<init>(r0)
            throw r14
        L_0x02fa:
            java.lang.String r3 = "video.mute"
            java.lang.String r6 = "video.unmute"
            java.lang.String r7 = "video.close"
            java.lang.String r8 = "postroll.click"
            java.lang.String r9 = "postroll.view"
            goto L_0x030f
        L_0x0305:
            java.lang.String r3 = "mute"
            java.lang.String r6 = "unmute"
            java.lang.String r7 = "video_close"
            java.lang.String r8 = "postroll_click"
            java.lang.String r9 = "postroll_view"
        L_0x030f:
            boolean r10 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r3)
            if (r10 == 0) goto L_0x0355
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r3)
            int r10 = r3.size()
            java.lang.String[] r10 = new java.lang.String[r10]
            r13.muteUrls = r10
            r10 = 0
        L_0x0322:
            int r11 = r3.size()
            if (r10 >= r11) goto L_0x0359
            com.google.gson.JsonElement r11 = r3.get(r10)
            if (r11 == 0) goto L_0x034c
            java.lang.String r11 = "null"
            com.google.gson.JsonElement r12 = r3.get(r10)
            java.lang.String r12 = r12.toString()
            boolean r11 = r11.equalsIgnoreCase(r12)
            if (r11 == 0) goto L_0x033f
            goto L_0x034c
        L_0x033f:
            java.lang.String[] r11 = r13.muteUrls
            com.google.gson.JsonElement r12 = r3.get(r10)
            java.lang.String r12 = r12.getAsString()
            r11[r10] = r12
            goto L_0x0352
        L_0x034c:
            java.lang.String[] r11 = r13.muteUrls
            java.lang.String r12 = ""
            r11[r10] = r12
        L_0x0352:
            int r10 = r10 + 1
            goto L_0x0322
        L_0x0355:
            java.lang.String[] r3 = new java.lang.String[r0]
            r13.muteUrls = r3
        L_0x0359:
            boolean r3 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r6)
            if (r3 == 0) goto L_0x039f
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r6)
            int r6 = r3.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            r13.unmuteUrls = r6
            r6 = 0
        L_0x036c:
            int r10 = r3.size()
            if (r6 >= r10) goto L_0x03a3
            com.google.gson.JsonElement r10 = r3.get(r6)
            if (r10 == 0) goto L_0x0396
            java.lang.String r10 = "null"
            com.google.gson.JsonElement r11 = r3.get(r6)
            java.lang.String r11 = r11.toString()
            boolean r10 = r10.equalsIgnoreCase(r11)
            if (r10 == 0) goto L_0x0389
            goto L_0x0396
        L_0x0389:
            java.lang.String[] r10 = r13.unmuteUrls
            com.google.gson.JsonElement r11 = r3.get(r6)
            java.lang.String r11 = r11.getAsString()
            r10[r6] = r11
            goto L_0x039c
        L_0x0396:
            java.lang.String[] r10 = r13.unmuteUrls
            java.lang.String r11 = ""
            r10[r6] = r11
        L_0x039c:
            int r6 = r6 + 1
            goto L_0x036c
        L_0x039f:
            java.lang.String[] r3 = new java.lang.String[r0]
            r13.unmuteUrls = r3
        L_0x03a3:
            boolean r3 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r7)
            if (r3 == 0) goto L_0x03e9
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r7)
            int r6 = r3.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            r13.closeUrls = r6
            r6 = 0
        L_0x03b6:
            int r7 = r3.size()
            if (r6 >= r7) goto L_0x03ed
            com.google.gson.JsonElement r7 = r3.get(r6)
            if (r7 == 0) goto L_0x03e0
            java.lang.String r7 = "null"
            com.google.gson.JsonElement r10 = r3.get(r6)
            java.lang.String r10 = r10.toString()
            boolean r7 = r7.equalsIgnoreCase(r10)
            if (r7 == 0) goto L_0x03d3
            goto L_0x03e0
        L_0x03d3:
            java.lang.String[] r7 = r13.closeUrls
            com.google.gson.JsonElement r10 = r3.get(r6)
            java.lang.String r10 = r10.getAsString()
            r7[r6] = r10
            goto L_0x03e6
        L_0x03e0:
            java.lang.String[] r7 = r13.closeUrls
            java.lang.String r10 = ""
            r7[r6] = r10
        L_0x03e6:
            int r6 = r6 + 1
            goto L_0x03b6
        L_0x03e9:
            java.lang.String[] r3 = new java.lang.String[r0]
            r13.closeUrls = r3
        L_0x03ed:
            boolean r3 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r8)
            if (r3 == 0) goto L_0x0433
            com.google.gson.JsonArray r3 = r1.getAsJsonArray(r8)
            int r6 = r3.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            r13.postRollClickUrls = r6
            r6 = 0
        L_0x0400:
            int r7 = r3.size()
            if (r6 >= r7) goto L_0x0437
            com.google.gson.JsonElement r7 = r3.get(r6)
            if (r7 == 0) goto L_0x042a
            java.lang.String r7 = "null"
            com.google.gson.JsonElement r8 = r3.get(r6)
            java.lang.String r8 = r8.toString()
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto L_0x041d
            goto L_0x042a
        L_0x041d:
            java.lang.String[] r7 = r13.postRollClickUrls
            com.google.gson.JsonElement r8 = r3.get(r6)
            java.lang.String r8 = r8.getAsString()
            r7[r6] = r8
            goto L_0x0430
        L_0x042a:
            java.lang.String[] r7 = r13.postRollClickUrls
            java.lang.String r8 = ""
            r7[r6] = r8
        L_0x0430:
            int r6 = r6 + 1
            goto L_0x0400
        L_0x0433:
            java.lang.String[] r3 = new java.lang.String[r0]
            r13.postRollClickUrls = r3
        L_0x0437:
            boolean r3 = com.vungle.warren.model.JsonUtil.hasNonNull(r1, r9)
            if (r3 == 0) goto L_0x047d
            com.google.gson.JsonArray r1 = r1.getAsJsonArray(r9)
            int r3 = r1.size()
            java.lang.String[] r3 = new java.lang.String[r3]
            r13.postRollViewUrls = r3
            r3 = 0
        L_0x044a:
            int r6 = r1.size()
            if (r3 >= r6) goto L_0x04a7
            com.google.gson.JsonElement r6 = r1.get(r3)
            if (r6 == 0) goto L_0x0474
            java.lang.String r6 = "null"
            com.google.gson.JsonElement r7 = r1.get(r3)
            java.lang.String r7 = r7.toString()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0467
            goto L_0x0474
        L_0x0467:
            java.lang.String[] r6 = r13.postRollViewUrls
            com.google.gson.JsonElement r7 = r1.get(r3)
            java.lang.String r7 = r7.getAsString()
            r6[r3] = r7
            goto L_0x047a
        L_0x0474:
            java.lang.String[] r6 = r13.postRollViewUrls
            java.lang.String r7 = ""
            r6[r3] = r7
        L_0x047a:
            int r3 = r3 + 1
            goto L_0x044a
        L_0x047d:
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.postRollViewUrls = r1
            goto L_0x04a7
        L_0x0482:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r13.checkpoints = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.muteUrls = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.closeUrls = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.unmuteUrls = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.postRollViewUrls = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.postRollClickUrls = r1
            java.lang.String[] r1 = new java.lang.String[r0]
            r13.clickUrls = r1
            r13.enableMoat = r0
            java.lang.String r1 = ""
            r13.moatExtraVast = r1
        L_0x04a7:
            java.lang.String r1 = "delay"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x04bc
            java.lang.String r1 = "delay"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.delay = r1
            goto L_0x04be
        L_0x04bc:
            r13.delay = r0
        L_0x04be:
            java.lang.String r1 = "showClose"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x04d3
            java.lang.String r1 = "showClose"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.showCloseDelay = r1
            goto L_0x04d5
        L_0x04d3:
            r13.showCloseDelay = r0
        L_0x04d5:
            java.lang.String r1 = "showCloseIncentivized"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x04ea
            java.lang.String r1 = "showCloseIncentivized"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.showCloseIncentivized = r1
            goto L_0x04ec
        L_0x04ea:
            r13.showCloseIncentivized = r0
        L_0x04ec:
            java.lang.String r1 = "countdown"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x0501
            java.lang.String r1 = "countdown"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.countdown = r1
            goto L_0x0503
        L_0x0501:
            r13.countdown = r0
        L_0x0503:
            java.lang.String r1 = "url"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x06bb
            java.lang.String r1 = "url"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.videoUrl = r1
            java.lang.String r1 = "videoWidth"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x06b3
            java.lang.String r1 = "videoWidth"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.videoWidth = r1
            java.lang.String r1 = "videoHeight"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x06ab
            java.lang.String r1 = "videoHeight"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.videoHeight = r1
            java.lang.String r1 = "md5"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x0554
            java.lang.String r1 = "md5"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.md5 = r1
            goto L_0x0558
        L_0x0554:
            java.lang.String r1 = ""
            r13.md5 = r1
        L_0x0558:
            java.lang.String r1 = "cta_overlay"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x05da
            java.lang.String r1 = "cta_overlay"
            com.google.gson.JsonObject r1 = r14.getAsJsonObject(r1)
            java.lang.String r3 = "enabled"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x057b
            java.lang.String r3 = "enabled"
            com.google.gson.JsonElement r3 = r1.get(r3)
            boolean r3 = r3.getAsBoolean()
            r13.ctaOverlayEnabled = r3
            goto L_0x057d
        L_0x057b:
            r13.ctaOverlayEnabled = r0
        L_0x057d:
            java.lang.String r3 = "show_onclick"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x0592
            java.lang.String r3 = "show_onclick"
            com.google.gson.JsonElement r3 = r1.get(r3)
            boolean r3 = r3.getAsBoolean()
            r13.ctaShowOnClick = r3
            goto L_0x0594
        L_0x0592:
            r13.ctaShowOnClick = r0
        L_0x0594:
            java.lang.String r3 = "time_enabled"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x05a9
            java.lang.String r3 = "time_enabled"
            com.google.gson.JsonElement r3 = r1.get(r3)
            int r3 = r3.getAsInt()
            r13.ctaTimeEnabled = r3
            goto L_0x05ab
        L_0x05a9:
            r13.ctaTimeEnabled = r4
        L_0x05ab:
            java.lang.String r3 = "click_area"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x05c0
            java.lang.String r3 = "click_area"
            com.google.gson.JsonElement r3 = r1.get(r3)
            int r3 = r3.getAsInt()
            r13.ctaClickArea = r3
            goto L_0x05c2
        L_0x05c0:
            r13.ctaClickArea = r4
        L_0x05c2:
            java.lang.String r3 = "time_show"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x05d7
            java.lang.String r3 = "time_show"
            com.google.gson.JsonElement r1 = r1.get(r3)
            int r1 = r1.getAsInt()
            r13.ctaTimeShow = r1
            goto L_0x05e4
        L_0x05d7:
            r13.ctaTimeShow = r4
            goto L_0x05e4
        L_0x05da:
            r13.ctaOverlayEnabled = r0
            r13.ctaClickArea = r4
            r13.ctaTimeEnabled = r4
            r13.ctaTimeShow = r4
            r13.ctaShowOnClick = r0
        L_0x05e4:
            java.lang.String r1 = "callToActionDest"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x05f7
            java.lang.String r1 = "callToActionDest"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            goto L_0x05f8
        L_0x05f7:
            r1 = r2
        L_0x05f8:
            r13.ctaDestinationUrl = r1
            java.lang.String r1 = "callToActionUrl"
            boolean r1 = com.vungle.warren.model.JsonUtil.hasNonNull(r14, r1)
            if (r1 == 0) goto L_0x060c
            java.lang.String r1 = "callToActionUrl"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r2 = r1.getAsString()
        L_0x060c:
            r13.ctaUrl = r2
            java.lang.String r1 = "retryCount"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x0623
            java.lang.String r1 = "retryCount"
            com.google.gson.JsonElement r1 = r14.get(r1)
            int r1 = r1.getAsInt()
            r13.retryCount = r1
            goto L_0x0625
        L_0x0623:
            r13.retryCount = r5
        L_0x0625:
            java.lang.String r1 = "ad_token"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x06a3
            java.lang.String r1 = "ad_token"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.adToken = r1
            java.lang.String r1 = "video_object_id"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x064e
            java.lang.String r1 = "video_object_id"
            com.google.gson.JsonElement r1 = r14.get(r1)
            java.lang.String r1 = r1.getAsString()
            r13.videoIdentifier = r1
            goto L_0x0652
        L_0x064e:
            java.lang.String r1 = ""
            r13.videoIdentifier = r1
        L_0x0652:
            java.lang.String r1 = "requires_sideloading"
            boolean r1 = r14.has(r1)
            if (r1 == 0) goto L_0x0667
            java.lang.String r0 = "requires_sideloading"
            com.google.gson.JsonElement r0 = r14.get(r0)
            boolean r0 = r0.getAsBoolean()
            r13.requiresNonMarketInstall = r0
            goto L_0x0669
        L_0x0667:
            r13.requiresNonMarketInstall = r0
        L_0x0669:
            java.lang.String r0 = "ad_market_id"
            boolean r0 = r14.has(r0)
            if (r0 == 0) goto L_0x067e
            java.lang.String r0 = "ad_market_id"
            com.google.gson.JsonElement r0 = r14.get(r0)
            java.lang.String r0 = r0.getAsString()
            r13.adMarketId = r0
            goto L_0x0682
        L_0x067e:
            java.lang.String r0 = ""
            r13.adMarketId = r0
        L_0x0682:
            java.lang.String r0 = "bid_token"
            boolean r0 = r14.has(r0)
            if (r0 == 0) goto L_0x0697
            java.lang.String r0 = "bid_token"
            com.google.gson.JsonElement r14 = r14.get(r0)
            java.lang.String r14 = r14.getAsString()
            r13.bidToken = r14
            goto L_0x069b
        L_0x0697:
            java.lang.String r14 = ""
            r13.bidToken = r14
        L_0x069b:
            com.vungle.warren.AdConfig r14 = new com.vungle.warren.AdConfig
            r14.<init>()
            r13.adConfig = r14
            return
        L_0x06a3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "AdToken missing!"
            r14.<init>(r0)
            throw r14
        L_0x06ab:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing video height!"
            r14.<init>(r0)
            throw r14
        L_0x06b3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing video width!"
            r14.<init>(r0)
            throw r14
        L_0x06bb:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing video URL!"
            r14.<init>(r0)
            throw r14
        L_0x06c3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing app Id, cannot process advertisement!"
            r14.<init>(r0)
            throw r14
        L_0x06cb:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing campaign information, cannot process advertisement!"
            r14.<init>(r0)
            throw r14
        L_0x06d3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Missing identifier, cannot process advertisement!"
            r14.<init>(r0)
            throw r14
        L_0x06db:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Advertisement did not contain an adType!"
            r14.<init>(r0)
            throw r14
        L_0x06e3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "JSON does not contain ad markup!"
            r14.<init>(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.model.Advertisement.<init>(com.google.gson.JsonObject):void");
    }

    @AdType
    public int getAdType() {
        return this.adType;
    }

    public List<Checkpoint> getCheckpoints() {
        return this.checkpoints;
    }

    public void configure(AdConfig adConfig2) {
        if (adConfig2 == null) {
            this.adConfig = new AdConfig();
        } else {
            this.adConfig = adConfig2;
        }
    }

    public AdConfig getAdConfig() {
        return this.adConfig;
    }

    @Orientation
    public int getOrientation() {
        return this.videoWidth > this.videoHeight ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Advertisement)) {
            return false;
        }
        Advertisement advertisement = (Advertisement) obj;
        if (getId() == null || advertisement.getId() == null || !advertisement.getId().equals(getId()) || advertisement.adType != this.adType || advertisement.expireTime != this.expireTime || advertisement.delay != this.delay || advertisement.showCloseDelay != this.showCloseDelay || advertisement.showCloseIncentivized != this.showCloseIncentivized || advertisement.countdown != this.countdown || advertisement.videoWidth != this.videoWidth || advertisement.videoHeight != this.videoHeight || advertisement.ctaOverlayEnabled != this.ctaOverlayEnabled || advertisement.ctaShowOnClick != this.ctaShowOnClick || advertisement.ctaTimeEnabled != this.ctaTimeEnabled || advertisement.ctaTimeShow != this.ctaTimeShow || advertisement.ctaClickArea != this.ctaClickArea || advertisement.retryCount != this.retryCount || advertisement.enableMoat != this.enableMoat || advertisement.requiresNonMarketInstall != this.requiresNonMarketInstall || !advertisement.identifier.equals(this.identifier) || !advertisement.campaign.equals(this.campaign) || !advertisement.videoUrl.equals(this.videoUrl) || !advertisement.md5.equals(this.md5) || !advertisement.postrollBundleUrl.equals(this.postrollBundleUrl) || !advertisement.ctaDestinationUrl.equals(this.ctaDestinationUrl) || !advertisement.ctaUrl.equals(this.ctaUrl) || !advertisement.adToken.equals(this.adToken) || !advertisement.videoIdentifier.equals(this.videoIdentifier) || !advertisement.moatExtraVast.equals(this.moatExtraVast) || advertisement.state != this.state || advertisement.muteUrls.length != this.muteUrls.length) {
            return false;
        }
        for (int i = 0; i < this.muteUrls.length; i++) {
            if (!advertisement.muteUrls[i].equals(this.muteUrls[i])) {
                return false;
            }
        }
        if (advertisement.unmuteUrls.length != this.unmuteUrls.length) {
            return false;
        }
        for (int i2 = 0; i2 < this.unmuteUrls.length; i2++) {
            if (!advertisement.unmuteUrls[i2].equals(this.unmuteUrls[i2])) {
                return false;
            }
        }
        if (advertisement.closeUrls.length != this.closeUrls.length) {
            return false;
        }
        for (int i3 = 0; i3 < this.closeUrls.length; i3++) {
            if (!advertisement.closeUrls[i3].equals(this.closeUrls[i3])) {
                return false;
            }
        }
        if (advertisement.postRollClickUrls.length != this.postRollClickUrls.length) {
            return false;
        }
        for (int i4 = 0; i4 < this.postRollClickUrls.length; i4++) {
            if (!advertisement.postRollClickUrls[i4].equals(this.postRollClickUrls[i4])) {
                return false;
            }
        }
        if (advertisement.postRollViewUrls.length != this.postRollViewUrls.length) {
            return false;
        }
        for (int i5 = 0; i5 < this.postRollViewUrls.length; i5++) {
            if (!advertisement.postRollViewUrls[i5].equals(this.postRollViewUrls[i5])) {
                return false;
            }
        }
        if (advertisement.checkpoints.size() != this.checkpoints.size()) {
            return false;
        }
        for (int i6 = 0; i6 < this.checkpoints.size(); i6++) {
            if (!advertisement.checkpoints.get(i6).equals(this.checkpoints.get(i6))) {
                return false;
            }
        }
        if (advertisement.adMarketId.equals(this.adMarketId) && advertisement.bidToken.equals(this.bidToken)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adType));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.expireTime));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.delay));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseDelay));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseIncentivized));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.countdown));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoWidth));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoHeight));
            byteArrayOutputStream.write(this.ctaOverlayEnabled ? 1 : 0);
            byteArrayOutputStream.write(this.ctaShowOnClick ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeEnabled));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeShow));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaClickArea));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.retryCount));
            byteArrayOutputStream.write(this.enableMoat ? 1 : 0);
            byteArrayOutputStream.write(this.requiresNonMarketInstall ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getSettings()));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getFlexViewCloseTime()));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getOrdinal()));
            MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeString(this.appID, byteArrayOutputStream);
            MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
            MemoryUtils.writeString(this.videoUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.md5, byteArrayOutputStream);
            MemoryUtils.writeString(this.postrollBundleUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.ctaDestinationUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.ctaUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
            MemoryUtils.writeString(this.videoIdentifier, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.muteUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.unmuteUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.closeUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.postRollClickUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.postRollViewUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.clickUrls, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateType, byteArrayOutputStream);
            MemoryUtils.writeString(this.moatExtraVast, byteArrayOutputStream);
            MemoryUtils.writeString(this.adMarketId, byteArrayOutputStream);
            MemoryUtils.writeString(this.bidToken, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.checkpoints.size()));
            Iterator<Checkpoint> it = this.checkpoints.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable(it.next(), byteArrayOutputStream);
            }
            MemoryUtils.writeStringMap(this.templateSettings, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.state));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        if (r9.equals("video.mute") != false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c3, code lost:
        if (r9.equals("video_close") != false) goto L_0x00ef;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] getTpatUrls(@android.support.annotation.NonNull java.lang.String r9) {
        /*
            r8 = this;
            int r0 = r8.adType
            r1 = 4
            r2 = 2
            r3 = 3
            r4 = 5
            r5 = -1
            r6 = 1
            r7 = 0
            switch(r0) {
                case 0: goto L_0x00ab;
                case 1: goto L_0x0014;
                default: goto L_0x000c;
            }
        L_0x000c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unknown Advertisement Type!"
            r9.<init>(r0)
            throw r9
        L_0x0014:
            java.lang.String r0 = "checkpoint"
            boolean r0 = r9.startsWith(r0)
            if (r0 == 0) goto L_0x003b
            java.lang.String[] r0 = new java.lang.String[r7]
            java.lang.String r1 = "\\."
            java.lang.String[] r9 = r9.split(r1)
            r9 = r9[r6]
            int r9 = java.lang.Integer.parseInt(r9)
            java.util.ArrayList<com.vungle.warren.model.Advertisement$Checkpoint> r1 = r8.checkpoints
            int r9 = r9 / 25
            java.lang.Object r9 = r1.get(r9)
            com.vungle.warren.model.Advertisement$Checkpoint r9 = (com.vungle.warren.model.Advertisement.Checkpoint) r9
            if (r9 == 0) goto L_0x003a
            java.lang.String[] r0 = r9.getUrls()
        L_0x003a:
            return r0
        L_0x003b:
            int r0 = r9.hashCode()
            switch(r0) {
                case -1663300692: goto L_0x0075;
                case -1293192841: goto L_0x006b;
                case -481751803: goto L_0x0061;
                case -32221499: goto L_0x0057;
                case 906443463: goto L_0x004d;
                case 1621415126: goto L_0x0043;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x007e
        L_0x0043:
            java.lang.String r0 = "postroll.view"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            r1 = 1
            goto L_0x007f
        L_0x004d:
            java.lang.String r0 = "clickUrl"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            r1 = 3
            goto L_0x007f
        L_0x0057:
            java.lang.String r0 = "video.close"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            r1 = 0
            goto L_0x007f
        L_0x0061:
            java.lang.String r0 = "video.unmute"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            r1 = 5
            goto L_0x007f
        L_0x006b:
            java.lang.String r0 = "postroll.click"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            r1 = 2
            goto L_0x007f
        L_0x0075:
            java.lang.String r0 = "video.mute"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x007e
            goto L_0x007f
        L_0x007e:
            r1 = -1
        L_0x007f:
            switch(r1) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x00a5;
                case 2: goto L_0x00a2;
                case 3: goto L_0x009f;
                case 4: goto L_0x009c;
                case 5: goto L_0x0099;
                default: goto L_0x0082;
            }
        L_0x0082:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown TPAT Event "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            throw r0
        L_0x0099:
            java.lang.String[] r9 = r8.unmuteUrls
            return r9
        L_0x009c:
            java.lang.String[] r9 = r8.muteUrls
            return r9
        L_0x009f:
            java.lang.String[] r9 = r8.clickUrls
            return r9
        L_0x00a2:
            java.lang.String[] r9 = r8.postRollClickUrls
            return r9
        L_0x00a5:
            java.lang.String[] r9 = r8.postRollViewUrls
            return r9
        L_0x00a8:
            java.lang.String[] r9 = r8.closeUrls
            return r9
        L_0x00ab:
            int r0 = r9.hashCode()
            switch(r0) {
                case -1964722632: goto L_0x00e4;
                case -840405966: goto L_0x00da;
                case 3363353: goto L_0x00d0;
                case 109635558: goto L_0x00c6;
                case 1370606900: goto L_0x00bd;
                case 1666667655: goto L_0x00b3;
                default: goto L_0x00b2;
            }
        L_0x00b2:
            goto L_0x00ee
        L_0x00b3:
            java.lang.String r0 = "postroll_view"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            r1 = 0
            goto L_0x00ef
        L_0x00bd:
            java.lang.String r0 = "video_close"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            goto L_0x00ef
        L_0x00c6:
            java.lang.String r0 = "postroll_click"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            r1 = 1
            goto L_0x00ef
        L_0x00d0:
            java.lang.String r0 = "mute"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            r1 = 2
            goto L_0x00ef
        L_0x00da:
            java.lang.String r0 = "unmute"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            r1 = 3
            goto L_0x00ef
        L_0x00e4:
            java.lang.String r0 = "click_url"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x00ee
            r1 = 5
            goto L_0x00ef
        L_0x00ee:
            r1 = -1
        L_0x00ef:
            switch(r1) {
                case 0: goto L_0x0118;
                case 1: goto L_0x0115;
                case 2: goto L_0x0112;
                case 3: goto L_0x010f;
                case 4: goto L_0x010c;
                case 5: goto L_0x0109;
                default: goto L_0x00f2;
            }
        L_0x00f2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown TPAT Event "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            throw r0
        L_0x0109:
            java.lang.String[] r9 = r8.clickUrls
            return r9
        L_0x010c:
            java.lang.String[] r9 = r8.closeUrls
            return r9
        L_0x010f:
            java.lang.String[] r9 = r8.unmuteUrls
            return r9
        L_0x0112:
            java.lang.String[] r9 = r8.muteUrls
            return r9
        L_0x0115:
            java.lang.String[] r9 = r8.postRollClickUrls
            return r9
        L_0x0118:
            java.lang.String[] r9 = r8.postRollViewUrls
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.model.Advertisement.getTpatUrls(java.lang.String):java.lang.String[]");
    }

    @NonNull
    public String getId() {
        if (this.identifier == null) {
            return "";
        }
        return this.identifier;
    }

    public String getAdToken() {
        return this.adToken;
    }

    public String getAppID() {
        return this.appID;
    }

    /* access modifiers changed from: package-private */
    public String getUrl() {
        return this.videoUrl;
    }

    public String getCampaign() {
        return this.campaign;
    }

    /* access modifiers changed from: package-private */
    public String getTemplateId() {
        return this.templateId;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public int getShowCloseDelay(boolean z) {
        if (z) {
            return this.showCloseIncentivized * 1000;
        }
        return this.showCloseDelay * 1000;
    }

    public boolean getMoatEnabled() {
        return this.enableMoat;
    }

    public String getMoatVastExtra() {
        return this.moatExtraVast;
    }

    public long getExpireTime() {
        return this.expireTime * 1000;
    }

    public JsonObject getMRAIDArgs() {
        if (this.templateSettings != null) {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry next : this.templateSettings.entrySet()) {
                jsonObject.addProperty((String) next.getKey(), (String) next.getValue());
            }
            return jsonObject;
        }
        throw new IllegalArgumentException("Advertisment does not have MRAID Arguments!");
    }

    @Nullable
    public String getCTAURL(boolean z) {
        switch (this.adType) {
            case 0:
                return z ? this.ctaUrl : this.ctaDestinationUrl;
            case 1:
                return this.ctaUrl;
            default:
                throw new IllegalArgumentException("Unknown AdType " + this.adType);
        }
    }

    public boolean hasPostroll() {
        return !TextUtils.isEmpty(this.postrollBundleUrl);
    }

    public Map<String, String> getDownloadableUrls() {
        HashMap hashMap = new HashMap();
        switch (this.adType) {
            case 0:
                hashMap.put(KEY_VIDEO, this.videoUrl);
                if (!TextUtils.isEmpty(this.postrollBundleUrl)) {
                    hashMap.put(KEY_POSTROLL, this.postrollBundleUrl);
                    break;
                }
                break;
            case 1:
                hashMap.put(KEY_TEMPLATE, this.templateUrl);
                if (this.templateSettings.containsKey("MAIN_VIDEO")) {
                    hashMap.put(KEY_VIDEO, this.templateSettings.get("MAIN_VIDEO"));
                }
                if (this.templateSettings.containsKey("APP_ICON")) {
                    hashMap.put(KEY_APP_ICON, this.templateSettings.get("APP_ICON"));
                }
                if (this.templateSettings.containsKey("POWERED_BY_VUNGLE")) {
                    hashMap.put(KEY_POWERED_BY_VUNGLE, this.templateSettings.get("POWERED_BY_VUNGLE"));
                    break;
                }
                break;
            default:
                throw new IllegalStateException("Advertisement created without adType!");
        }
        return hashMap;
    }

    public void setMraidAssetDir(File file) {
        File file2 = new File(file, KEY_VIDEO);
        if (file2.exists()) {
            Map<String, String> map = this.templateSettings;
            map.put("MAIN_VIDEO", "file://" + file2.getPath());
        }
        File file3 = new File(file, KEY_APP_ICON);
        if (file3.exists()) {
            Map<String, String> map2 = this.templateSettings;
            map2.put("APP_ICON", "file://" + file3.getPath());
        }
        File file4 = new File(file, KEY_POWERED_BY_VUNGLE);
        if (file4.exists()) {
            Map<String, String> map3 = this.templateSettings;
            map3.put("POWERED_BY_VUNGLE", "file://" + file4.getPath());
        }
    }

    public void setState(@State int i) {
        this.state = i;
    }

    @State
    public int getState() {
        return this.state;
    }

    public String getAdMarketId() {
        return this.adMarketId;
    }

    public String getBidToken() {
        return this.bidToken;
    }

    public static class Checkpoint implements Comparable<Checkpoint>, Memorable {
        private final byte percentage;
        private final String[] urls;

        @NonNull
        public String getId() {
            return "checkpoint";
        }

        public Checkpoint(byte[] bArr) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.percentage = wrap.get();
            this.urls = MemoryUtils.extractStringArray(wrap);
        }

        public Checkpoint(JsonObject jsonObject) throws IllegalArgumentException {
            if (jsonObject.has("checkpoint")) {
                this.percentage = (byte) ((int) (jsonObject.get("checkpoint").getAsFloat() * 100.0f));
                if (JsonUtil.hasNonNull(jsonObject, "urls")) {
                    JsonArray asJsonArray = jsonObject.getAsJsonArray("urls");
                    this.urls = new String[asJsonArray.size()];
                    for (int i = 0; i < asJsonArray.size(); i++) {
                        if (asJsonArray.get(i) == null || "null".equalsIgnoreCase(asJsonArray.get(i).toString())) {
                            this.urls[i] = "";
                        } else {
                            this.urls[i] = asJsonArray.get(i).getAsString();
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("Checkpoint missing reporting URL!");
            }
            throw new IllegalArgumentException("Checkpoint missing percentage!");
        }

        public Checkpoint(JsonArray jsonArray, byte b) {
            if (jsonArray.size() != 0) {
                this.urls = new String[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    this.urls[i] = jsonArray.get(i).getAsString();
                }
                this.percentage = b;
                return;
            }
            throw new IllegalArgumentException("Empty URLS!");
        }

        public String[] getUrls() {
            return this.urls;
        }

        public byte getPercentage() {
            return this.percentage;
        }

        public int compareTo(@NonNull Checkpoint checkpoint) {
            return Float.compare((float) this.percentage, (float) checkpoint.percentage);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Checkpoint)) {
                return false;
            }
            Checkpoint checkpoint = (Checkpoint) obj;
            if (checkpoint.percentage != this.percentage || checkpoint.urls.length != this.urls.length) {
                return false;
            }
            for (int i = 0; i < this.urls.length; i++) {
                if (!checkpoint.urls[i].equals(this.urls[i])) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return super.hashCode();
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(this.percentage);
                MemoryUtils.writeStringArray(this.urls, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }
    }

    public Advertisement copy() {
        try {
            return (Advertisement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public String toString() {
        return "Advertisement{adType=" + this.adType + ", identifier='" + this.identifier + '\'' + ", appID='" + this.appID + '\'' + ", expireTime=" + this.expireTime + ", checkpoints=" + this.checkpoints + ", muteUrls=" + Arrays.toString(this.muteUrls) + ", unmuteUrls=" + Arrays.toString(this.unmuteUrls) + ", closeUrls=" + Arrays.toString(this.closeUrls) + ", postRollClickUrls=" + Arrays.toString(this.postRollClickUrls) + ", postRollViewUrls=" + Arrays.toString(this.postRollViewUrls) + ", clickUrls=" + Arrays.toString(this.clickUrls) + ", delay=" + this.delay + ", campaign='" + this.campaign + '\'' + ", showCloseDelay=" + this.showCloseDelay + ", showCloseIncentivized=" + this.showCloseIncentivized + ", countdown=" + this.countdown + ", videoUrl='" + this.videoUrl + '\'' + ", videoWidth=" + this.videoWidth + ", videoHeight=" + this.videoHeight + ", md5='" + this.md5 + '\'' + ", postrollBundleUrl='" + this.postrollBundleUrl + '\'' + ", ctaOverlayEnabled=" + this.ctaOverlayEnabled + ", ctaShowOnClick=" + this.ctaShowOnClick + ", ctaTimeEnabled=" + this.ctaTimeEnabled + ", ctaTimeShow=" + this.ctaTimeShow + ", ctaClickArea=" + this.ctaClickArea + ", ctaDestinationUrl='" + this.ctaDestinationUrl + '\'' + ", ctaUrl='" + this.ctaUrl + '\'' + ", adConfig=" + this.adConfig + ", retryCount=" + this.retryCount + ", adToken='" + this.adToken + '\'' + ", videoIdentifier='" + this.videoIdentifier + '\'' + ", templateUrl='" + this.templateUrl + '\'' + ", templateSettings=" + this.templateSettings + ", templateId='" + this.templateId + '\'' + ", templateType='" + this.templateType + '\'' + ", enableMoat=" + this.enableMoat + ", moatExtraVast='" + this.moatExtraVast + '\'' + ", requiresNonMarketInstall=" + this.requiresNonMarketInstall + ", adMarketId='" + this.adMarketId + '\'' + ", bidToken='" + this.bidToken + '\'' + ", state=" + this.state + '}';
    }
}
