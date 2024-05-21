package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyUtil;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class hx {
    public static final hx a = new hx();
    public Context b;
    public SharedPreferences c = null;
    public SharedPreferences d = null;
    ExecutorService e = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private File f;

    public static boolean a(long j) {
        return j >= 3600;
    }

    private hx() {
    }

    public final void a() {
        this.e.submit(new Runnable() {
            public final void run() {
                if (hx.this.b != null) {
                    hx.this.c();
                }
            }
        });
    }

    public final File a(URL url) {
        if (this.b == null) {
            return null;
        }
        synchronized (this) {
            String b2 = b(url);
            File a2 = a(b2);
            if (!a2.exists()) {
                return null;
            }
            long b3 = w.b();
            long j = this.c.getLong(b2, 0);
            if (j >= b3) {
                Object[] objArr = {b2, url};
                return a2;
            }
            Object[] objArr2 = {b2, Long.valueOf(b3), Long.valueOf(j)};
            if (j != 0) {
                this.c.edit().remove(b2).commit();
                this.d.edit().remove(b2).commit();
            }
            a2.delete();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String b(URL url) {
        String str;
        String string;
        String url2 = url.toString();
        String convertToHex = TapjoyUtil.convertToHex(ck.a(url2.getBytes()));
        String string2 = this.d.getString(convertToHex, (String) null);
        if (string2 == null) {
            this.d.edit().putString(convertToHex, url2).commit();
            return convertToHex;
        } else if (string2.equals(url2)) {
            return convertToHex;
        } else {
            int i = 0;
            do {
                i++;
                str = convertToHex + "_" + i;
                string = this.d.getString(str, (String) null);
                if (string == null || string.equals(url2)) {
                    this.d.edit().putString(str, url2).commit();
                }
                i++;
                str = convertToHex + "_" + i;
                string = this.d.getString(str, (String) null);
                break;
            } while (string.equals(url2));
            this.d.edit().putString(str, url2).commit();
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    public final File b() {
        File file = this.f;
        if (file == null) {
            file = new File(this.b.getCacheDir(), "tapjoy_mm_cache");
            this.f = file;
        }
        if (!file.isDirectory()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* access modifiers changed from: package-private */
    public final File a(String str) {
        return new File(b(), str);
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        long b2 = w.b();
        File[] listFiles = b().listFiles();
        HashMap hashMap = new HashMap();
        if (listFiles != null) {
            for (File file : listFiles) {
                hashMap.put(file.getName(), file);
            }
        }
        SharedPreferences.Editor edit = this.c.edit();
        SharedPreferences.Editor edit2 = this.d.edit();
        HashMap hashMap2 = new HashMap(this.c.getAll());
        HashMap hashMap3 = new HashMap(this.d.getAll());
        Iterator it = hashMap2.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            String str = (String) ((Map.Entry) it.next()).getKey();
            if (!hashMap3.containsKey(str)) {
                it.remove();
                edit.remove(str);
                new Object[1][0] = str;
                z = true;
            }
        }
        Iterator it2 = hashMap3.entrySet().iterator();
        while (it2.hasNext()) {
            String str2 = (String) ((Map.Entry) it2.next()).getKey();
            if (!hashMap2.containsKey(str2)) {
                it2.remove();
                edit2.remove(str2);
                new Object[1][0] = str2;
                z = true;
            }
        }
        Iterator it3 = hashMap2.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            String str3 = (String) entry.getKey();
            if (((Long) entry.getValue()).longValue() < b2) {
                it3.remove();
                edit.remove(str3);
                edit2.remove(str3);
                z = true;
            } else {
                hashMap.remove(str3);
            }
        }
        for (Map.Entry value : hashMap.entrySet()) {
            File file2 = (File) value.getValue();
            file2.delete();
            new Object[1][0] = file2;
        }
        if (hashMap2.size() > 30) {
            int size = hashMap2.size() - 30;
            LinkedList linkedList = new LinkedList(hashMap2.entrySet());
            Collections.sort(linkedList, new Comparator() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return ((Long) ((Map.Entry) obj).getValue()).compareTo((Long) ((Map.Entry) obj2).getValue());
                }
            });
            Iterator it4 = linkedList.iterator();
            for (int i = 0; i < size && it4.hasNext(); i++) {
                Map.Entry entry2 = (Map.Entry) it4.next();
                String str4 = (String) entry2.getKey();
                edit.remove(str4);
                edit2.remove(str4);
                File a2 = a(str4);
                a2.delete();
                Object[] objArr = {a2, (Long) entry2.getValue()};
            }
            z = true;
        }
        if (z) {
            edit.commit();
            edit2.commit();
        }
    }
}
