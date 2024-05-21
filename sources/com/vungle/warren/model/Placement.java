package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.JsonObject;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Placement implements Memorable, Cloneable {
    private static final String TAG = "Placement";
    private final Set<String> advertisementIDs;
    private final boolean autoCached;
    private final String identifier;
    private final boolean incentivized;
    private long wakeupTime;

    public Placement(String str) {
        this.identifier = str;
        this.autoCached = false;
        this.incentivized = false;
        this.advertisementIDs = new LinkedHashSet();
    }

    public Placement(byte[] bArr) {
        if (bArr.length != 0) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.identifier = MemoryUtils.extractString(wrap);
            this.advertisementIDs = new LinkedHashSet(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
            boolean z = false;
            this.incentivized = wrap.get() == 1;
            this.autoCached = wrap.get() == 1 ? true : z;
            this.wakeupTime = wrap.getLong();
            return;
        }
        throw new IllegalArgumentException("Cannot recreate from empty array!");
    }

    public Placement(JsonObject jsonObject) throws IllegalArgumentException {
        if (jsonObject.has("reference_id")) {
            this.identifier = jsonObject.get("reference_id").getAsString();
            boolean z = true;
            this.autoCached = jsonObject.has("is_auto_cached") && jsonObject.get("is_auto_cached").getAsBoolean();
            this.incentivized = (!jsonObject.has("is_incentivized") || !jsonObject.get("is_incentivized").getAsBoolean()) ? false : z;
            this.advertisementIDs = new LinkedHashSet();
            return;
        }
        throw new IllegalArgumentException("Missing placement reference ID, cannot use placement!");
    }

    public void addAdvertisementID(String str) {
        this.advertisementIDs.add(str);
    }

    public boolean removeAdvertisementID(String str) {
        return this.advertisementIDs.remove(str);
    }

    public List<String> getAdvertisementIDs() {
        return new ArrayList(this.advertisementIDs);
    }

    public void snooze(long j) {
        this.wakeupTime = System.currentTimeMillis() + (j * 1000);
    }

    public long getWakeupTime() {
        return this.wakeupTime;
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeStringArray((String[]) this.advertisementIDs.toArray(new String[this.advertisementIDs.size()]), byteArrayOutputStream);
            byteArrayOutputStream.write(this.incentivized ? 1 : 0);
            byteArrayOutputStream.write(this.autoCached ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.wakeupTime));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Placement#toByteArray()", "Failed to write " + this + " to a byte array.");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.identifier;
    }

    public boolean equalsIgnoreAdvertisements(Object obj) {
        if (!(obj instanceof Placement)) {
            return false;
        }
        Placement placement = (Placement) obj;
        if (placement.identifier.equals(this.identifier) && placement.incentivized == this.incentivized && placement.autoCached == this.autoCached) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Placement placement = (Placement) obj;
        if (this.autoCached != placement.autoCached || this.incentivized != placement.incentivized || this.wakeupTime != placement.wakeupTime) {
            return false;
        }
        if (this.identifier == null ? placement.identifier != null : !this.identifier.equals(placement.identifier)) {
            return false;
        }
        if (this.advertisementIDs != null) {
            return this.advertisementIDs.equals(placement.advertisementIDs);
        }
        if (placement.advertisementIDs == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.identifier != null ? this.identifier.hashCode() : 0) * 31;
        if (this.advertisementIDs != null) {
            i = this.advertisementIDs.hashCode();
        }
        return ((((((hashCode + i) * 31) + (this.autoCached ? 1 : 0)) * 31) + (this.incentivized ? 1 : 0)) * 31) + ((int) (this.wakeupTime ^ (this.wakeupTime >>> 32)));
    }

    public boolean isAutoCached() {
        return this.autoCached;
    }

    public boolean isIncentivized() {
        return this.incentivized;
    }

    public Placement copy() {
        try {
            return (Placement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Placement restore(int i, int i2, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new Placement(Arrays.copyOfRange(bArr, 1, bArr.length));
    }

    public String toString() {
        return "Placement{identifier='" + this.identifier + '\'' + ", advertisementIDs=" + this.advertisementIDs + ", autoCached=" + this.autoCached + ", incentivized=" + this.incentivized + ", wakeupTime=" + this.wakeupTime + '}';
    }
}
