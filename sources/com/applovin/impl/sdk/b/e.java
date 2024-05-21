package com.applovin.impl.sdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;
import java.util.Set;

public final class e {
    private static SharedPreferences b;
    private final j a;
    private final SharedPreferences c;

    public e(j jVar) {
        this.a = jVar;
        this.c = jVar.x().getSharedPreferences("com.applovin.sdk.preferences." + jVar.t(), 0);
    }

    private static SharedPreferences a(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("com.applovin.sdk.shared", 0);
        }
        return b;
    }

    private static <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences, j jVar) {
        T t2;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (sharedPreferences.contains(str)) {
                if (Boolean.class.equals(cls)) {
                    t2 = Boolean.valueOf(t != null ? sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()) : sharedPreferences.getBoolean(str, false));
                } else if (Float.class.equals(cls)) {
                    t2 = Float.valueOf(t != null ? sharedPreferences.getFloat(str, ((Float) t).floatValue()) : sharedPreferences.getFloat(str, 0.0f));
                } else if (Integer.class.equals(cls)) {
                    t2 = Integer.valueOf(t != null ? sharedPreferences.getInt(str, ((Integer) t).intValue()) : sharedPreferences.getInt(str, 0));
                } else if (Long.class.equals(cls)) {
                    t2 = Long.valueOf(t != null ? sharedPreferences.getLong(str, ((Long) t).longValue()) : sharedPreferences.getLong(str, 0));
                } else {
                    t2 = String.class.equals(cls) ? sharedPreferences.getString(str, (String) t) : Set.class.isAssignableFrom(cls) ? sharedPreferences.getStringSet(str, (Set) t) : t;
                }
                if (t2 != null) {
                    return cls.cast(t2);
                }
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return t;
            }
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return t;
        } catch (Throwable th) {
            if (jVar != null) {
                p v = jVar.v();
                v.b("SharedPreferencesManager", "Error getting value for key: " + str, th);
            }
            return t;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static void a(SharedPreferences.Editor editor, j jVar) {
        if (jVar == null || !((Boolean) jVar.a(b.ad)).booleanValue()) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static <T> void a(d<T> dVar, Context context) {
        a(a(context).edit().remove(dVar.a()), (j) null);
    }

    public static <T> void a(d<T> dVar, T t, Context context) {
        a(dVar.a(), t, a(context), (SharedPreferences.Editor) null, (j) null);
    }

    private static <T> void a(String str, T t, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, j jVar) {
        boolean z = false;
        boolean z2 = editor != null;
        if (!z2) {
            editor = sharedPreferences.edit();
        }
        if (t instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            editor.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof Integer) {
            editor.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            editor.putLong(str, ((Long) t).longValue());
        } else if (t instanceof String) {
            editor.putString(str, (String) t);
        } else if (t instanceof Set) {
            editor.putStringSet(str, (Set) t);
        } else {
            if (jVar != null) {
                p v = jVar.v();
                v.d("SharedPreferencesManager", "Unable to put default value of invalid type: " + t);
            }
            if (z && !z2) {
                a(editor, jVar);
                return;
            }
        }
        z = true;
        if (z) {
        }
    }

    public static <T> T b(d<T> dVar, T t, Context context) {
        return a(dVar.a(), t, (Class) dVar.b(), a(context), (j) null);
    }

    public <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return a(str, t, cls, sharedPreferences, this.a);
    }

    public void a(SharedPreferences sharedPreferences) {
        a(sharedPreferences.edit().clear(), this.a);
    }

    public <T> void a(d<T> dVar) {
        a(this.c.edit().remove(dVar.a()), this.a);
    }

    public <T> void a(d<T> dVar, T t) {
        a(dVar, t, this.c);
    }

    public <T> void a(d<T> dVar, T t, SharedPreferences sharedPreferences) {
        a(dVar.a(), t, sharedPreferences);
    }

    public <T> void a(String str, T t, SharedPreferences.Editor editor) {
        a(str, t, (SharedPreferences) null, editor, this.a);
    }

    public <T> void a(String str, T t, SharedPreferences sharedPreferences) {
        a(str, t, sharedPreferences, (SharedPreferences.Editor) null, this.a);
    }

    public <T> T b(d<T> dVar, T t) {
        return b(dVar, t, this.c);
    }

    public <T> T b(d<T> dVar, T t, SharedPreferences sharedPreferences) {
        return a(dVar.a(), t, dVar.b(), sharedPreferences);
    }
}
