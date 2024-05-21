package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cookie implements Memorable {
    public static final String APP_ID = "appId";
    public static final String CONFIG_COOKIE = "configSettings";
    public static final String CONSENT_COOKIE = "consentIsImportantToVungle";
    public static final String GOOGLE_AD_ID_COOKIE = "googleAdId";
    public static final String INCENTIVIZED_TEXT_COOKIE = "incentivizedTextSetByPub";
    private Map<String, Boolean> booleans = new HashMap();
    private String identifier;
    private Map<String, Integer> integers = new HashMap();
    private Map<String, Long> longs = new HashMap();
    private Map<String, String> strings = new HashMap();

    public Cookie(String str) {
        this.identifier = str;
    }

    public Cookie(byte[] bArr) {
        if (bArr.length != 0) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.identifier = (String) MemoryUtils.extract(wrap, String.class);
            this.strings = MemoryUtils.extractMap(wrap, String.class);
            this.booleans = MemoryUtils.extractMap(wrap, Boolean.class);
            this.integers = MemoryUtils.extractMap(wrap, Integer.class);
            this.longs = MemoryUtils.extractMap(wrap, Long.class);
            return;
        }
        throw new IllegalArgumentException("Cannot recreated from empty array!");
    }

    public <T> void putValue(String str, T t) {
        if (t instanceof String) {
            this.strings.put(str, (String) t);
        } else if (t instanceof Boolean) {
            this.booleans.put(str, (Boolean) t);
        } else if (t instanceof Integer) {
            this.integers.put(str, (Integer) t);
        } else if (t instanceof Long) {
            this.longs.put(str, (Long) t);
        } else {
            throw new IllegalArgumentException("Value type is not supported!");
        }
    }

    public Integer getInt(String str) {
        return this.integers.get(str);
    }

    public String getString(String str) {
        return this.strings.get(str);
    }

    public Boolean getBoolean(String str) {
        return Boolean.valueOf(this.booleans.get(str) != null ? this.booleans.get(str).booleanValue() : false);
    }

    public Long getLong(String str) {
        return Long.valueOf(this.longs.get(str) != null ? this.longs.get(str).longValue() : 0);
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.write(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeMap(this.strings, byteArrayOutputStream);
            MemoryUtils.writeMap(this.booleans, byteArrayOutputStream);
            MemoryUtils.writeMap(this.integers, byteArrayOutputStream);
            MemoryUtils.writeMap(this.longs, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Cookie#toByteArray()", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.identifier;
    }

    public static Cookie restore(int i, int i2, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new Cookie(Arrays.copyOfRange(bArr, 1, bArr.length));
    }
}
