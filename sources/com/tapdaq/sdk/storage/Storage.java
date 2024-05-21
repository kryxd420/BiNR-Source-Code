package com.tapdaq.sdk.storage;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

public class Storage implements StorageBase {
    private static final String TAPDAQ_KEY = "TAPDAQ";
    private SharedPreferences mPref;

    public Storage(Context context) {
        this.mPref = context.getSharedPreferences(TAPDAQ_KEY, 0);
    }

    public boolean contains(String str) {
        return this.mPref.contains(str);
    }

    public void remove(String str) {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.remove(str);
            edit.commit();
        }
    }

    public void clear() {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.clear();
            edit.commit();
        }
    }

    public void putBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.putBoolean(str, z);
            edit.commit();
        }
    }

    public boolean getBoolean(String str) {
        boolean z;
        synchronized (this.mPref) {
            z = this.mPref.getBoolean(str, false);
        }
        return z;
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.putString(str, str2);
            edit.commit();
        }
    }

    public String getString(String str) {
        String string;
        synchronized (this.mPref) {
            string = this.mPref.getString(str, (String) null);
        }
        return string;
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.putInt(str, i);
            edit.commit();
        }
    }

    public int getInt(String str) {
        int i;
        synchronized (this.mPref) {
            i = this.mPref.getInt(str, 0);
        }
        return i;
    }

    public void putLong(String str, long j) {
        SharedPreferences.Editor edit = this.mPref.edit();
        synchronized (edit) {
            edit.putLong(str, j);
            edit.commit();
        }
    }

    public long getLong(String str) {
        long j;
        synchronized (this.mPref) {
            j = this.mPref.getLong(str, 0);
        }
        return j;
    }

    public Map<String, ?> getAll() {
        Map<String, ?> all;
        synchronized (this.mPref) {
            all = this.mPref.getAll();
        }
        return all;
    }
}
