package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.mraid.view.MraidView;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Report implements Memorable {
    private long adDuration;
    private final long adStartTime;
    private final String adToken;
    private final String adType;
    private final String advertisementID;
    private final String appId;
    private final String campaign;
    private final ArrayList<String> clickedThrough;
    private final ArrayList<String> errors;
    private final boolean incentivized;
    private final int ordinal;
    private final String placementId;
    private final String templateId;
    private final int ttDownload;
    private final String url;
    private final ArrayList<UserAction> userActions;
    private final String userID;
    private int videoViewed;
    private boolean wasCTAClicked;

    public Report(byte[] bArr) {
        if (bArr.length != 0) {
            this.advertisementID = "";
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.placementId = MemoryUtils.extractString(wrap);
            this.adToken = MemoryUtils.extractString(wrap);
            this.appId = MemoryUtils.extractString(wrap);
            int i = 0;
            boolean z = true;
            this.incentivized = wrap.get() == 1;
            this.adStartTime = wrap.getLong();
            this.url = MemoryUtils.extractString(wrap);
            this.adDuration = wrap.getLong();
            this.ttDownload = wrap.getInt();
            this.campaign = MemoryUtils.extractString(wrap);
            this.videoViewed = wrap.getInt();
            this.adType = MemoryUtils.extractString(wrap);
            this.templateId = MemoryUtils.extractString(wrap);
            this.wasCTAClicked = wrap.get() != 1 ? false : z;
            this.clickedThrough = new ArrayList<>(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
            this.errors = new ArrayList<>(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
            int i2 = wrap.getInt();
            this.userActions = new ArrayList<>(i2);
            while (i < i2) {
                try {
                    this.userActions.add(MemoryUtils.extractMemorable(wrap, UserAction.class));
                    i++;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    throw new RuntimeException(e2);
                } catch (InstantiationException e3) {
                    e3.printStackTrace();
                    throw new RuntimeException(e3);
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                    throw new RuntimeException(e4);
                }
            }
            this.userID = MemoryUtils.extractString(wrap);
            this.ordinal = wrap.getInt();
            return;
        }
        throw new IllegalArgumentException("Cannot create Report from empty array");
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long j) {
        this(advertisement, placement, j, (String) null);
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long j, @Nullable String str) {
        this.placementId = placement.getId();
        this.adToken = advertisement.getAdToken();
        this.advertisementID = advertisement.getId();
        this.appId = advertisement.getAppID();
        this.incentivized = placement.isIncentivized();
        this.adStartTime = j;
        this.url = advertisement.getUrl();
        this.ttDownload = -1;
        this.campaign = advertisement.getCampaign();
        switch (advertisement.getAdType()) {
            case 0:
                this.adType = "vungle_local";
                break;
            case 1:
                this.adType = "vungle_mraid";
                break;
            default:
                throw new IllegalArgumentException("Unknown ad type, cannot process!");
        }
        this.templateId = advertisement.getTemplateId();
        this.clickedThrough = new ArrayList<>();
        this.errors = new ArrayList<>();
        this.userActions = new ArrayList<>();
        if (str == null) {
            this.userID = "";
        } else {
            this.userID = str;
        }
        this.ordinal = advertisement.getAdConfig().getOrdinal();
    }

    public void recordAction(String str, String str2, long j) {
        this.userActions.add(new UserAction(str, str2, j));
        this.clickedThrough.add(str);
        if (str.equals("download")) {
            this.wasCTAClicked = true;
        }
    }

    public void recordError(String str) {
        this.errors.add(str);
    }

    public void recordProgress(int i) {
        this.videoViewed = i;
    }

    public void setAdDuration(int i) {
        this.adDuration = (long) i;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public String getAdvertisementID() {
        return this.advertisementID;
    }

    public boolean isCTAClicked() {
        return this.wasCTAClicked;
    }

    public String getUserID() {
        return this.userID;
    }

    public JsonObject toReportBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("placement_reference_id", this.placementId);
        jsonObject.addProperty("ad_token", this.adToken);
        jsonObject.addProperty(TapjoyConstants.TJC_APP_ID, this.appId);
        jsonObject.addProperty("incentivized", (Number) Integer.valueOf(this.incentivized ? 1 : 0));
        jsonObject.addProperty("adStartTime", (Number) Long.valueOf(this.adStartTime));
        jsonObject.addProperty("url", this.url);
        jsonObject.addProperty("adDuration", (Number) Long.valueOf(this.adDuration));
        jsonObject.addProperty("ttDownload", (Number) Integer.valueOf(this.ttDownload));
        jsonObject.addProperty("campaign", this.campaign);
        jsonObject.addProperty("adType", this.adType);
        jsonObject.addProperty("templateId", this.templateId);
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("startTime", (Number) Long.valueOf(this.adStartTime));
        if (this.videoViewed > 0) {
            jsonObject2.addProperty("videoViewed", (Number) Integer.valueOf(this.videoViewed));
            jsonObject2.addProperty(TJAdUnitConstants.String.VIDEO_LENGTH, (Number) Long.valueOf(this.adDuration));
        }
        JsonArray jsonArray2 = new JsonArray();
        Iterator<UserAction> it = this.userActions.iterator();
        while (it.hasNext()) {
            jsonArray2.add((JsonElement) it.next().toJson());
        }
        jsonObject2.add("userActions", jsonArray2);
        jsonArray.add((JsonElement) jsonObject2);
        jsonObject.add("plays", jsonArray);
        JsonArray jsonArray3 = new JsonArray();
        Iterator<String> it2 = this.errors.iterator();
        while (it2.hasNext()) {
            jsonArray3.add(it2.next());
        }
        jsonObject.add("errors", jsonArray3);
        JsonArray jsonArray4 = new JsonArray();
        Iterator<String> it3 = this.clickedThrough.iterator();
        while (it3.hasNext()) {
            jsonArray4.add(it3.next());
        }
        jsonObject.add("clickedThrough", jsonArray4);
        if (this.incentivized && !TextUtils.isEmpty(this.userID)) {
            jsonObject.addProperty("user", this.userID);
        }
        if (this.ordinal > 0) {
            jsonObject.addProperty("ordinal_view", (Number) Integer.valueOf(this.ordinal));
        }
        return jsonObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Report)) {
            return false;
        }
        Report report = (Report) obj;
        if (!report.placementId.equals(this.placementId) || !report.adToken.equals(this.adToken) || !report.appId.equals(this.appId) || report.incentivized != this.incentivized || report.adStartTime != this.adStartTime || !report.url.equals(this.url) || report.adDuration != this.adDuration || report.ttDownload != this.ttDownload || !report.campaign.equals(this.campaign) || !report.adType.equals(this.adType) || !report.templateId.equals(this.templateId) || report.wasCTAClicked != this.wasCTAClicked || !report.userID.equals(this.userID) || report.clickedThrough.size() != this.clickedThrough.size()) {
            return false;
        }
        for (int i = 0; i < this.clickedThrough.size(); i++) {
            if (!report.clickedThrough.get(i).equals(this.clickedThrough.get(i))) {
                return false;
            }
        }
        if (report.errors.size() != this.errors.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.errors.size(); i2++) {
            if (!report.errors.get(i2).equals(this.errors.get(i2))) {
                return false;
            }
        }
        if (report.userActions.size() != this.userActions.size()) {
            return false;
        }
        for (int i3 = 0; i3 < this.userActions.size(); i3++) {
            if (!report.userActions.get(i3).equals(this.userActions.get(i3))) {
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
            MemoryUtils.writeString(this.placementId, byteArrayOutputStream);
            MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
            MemoryUtils.writeString(this.appId, byteArrayOutputStream);
            byteArrayOutputStream.write(this.incentivized ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adStartTime));
            MemoryUtils.writeString(this.url, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adDuration));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ttDownload));
            MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoViewed));
            MemoryUtils.writeString(this.adType, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
            byteArrayOutputStream.write(this.wasCTAClicked ? 1 : 0);
            MemoryUtils.writeStringArray((String[]) this.clickedThrough.toArray(new String[this.clickedThrough.size()]), byteArrayOutputStream);
            MemoryUtils.writeStringArray((String[]) this.errors.toArray(new String[this.errors.size()]), byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.userActions.size()));
            Iterator<UserAction> it = this.userActions.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable(it.next(), byteArrayOutputStream);
            }
            MemoryUtils.writeString(this.userID, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ordinal));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Report.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.placementId + "_" + this.adStartTime;
    }

    public long getAdStartTime() {
        return this.adStartTime;
    }

    public static Report restore(int i, int i2, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new Report(Arrays.copyOfRange(bArr, 1, bArr.length));
    }

    public static class UserAction implements Memorable {
        private final String action;
        private final long timestamp;
        private final String value;

        public UserAction(byte[] bArr) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.action = MemoryUtils.extractString(wrap);
            this.value = MemoryUtils.extractString(wrap);
            this.timestamp = wrap.getLong();
        }

        public UserAction(String str, String str2, long j) {
            this.action = str;
            this.value = str2;
            this.timestamp = j;
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                MemoryUtils.writeString(this.action, byteArrayOutputStream);
                MemoryUtils.writeString(this.value, byteArrayOutputStream);
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.timestamp));
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Report.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UserAction)) {
                return false;
            }
            UserAction userAction = (UserAction) obj;
            if (userAction.action.equals(this.action) && userAction.value.equals(this.value) && userAction.timestamp == this.timestamp) {
                return true;
            }
            return false;
        }

        @NonNull
        public String getId() {
            return "" + this.timestamp;
        }

        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(MraidView.ACTION_KEY, this.action);
            if (!this.value.isEmpty()) {
                jsonObject.addProperty("value", this.value);
            }
            jsonObject.addProperty("timestamp_millis", (Number) Long.valueOf(this.timestamp));
            return jsonObject;
        }
    }
}
