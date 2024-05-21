package com.tapjoy.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@fx
public final class PluginSupport {
    private PluginSupport() {
    }

    @fx
    public static void trackUsage(String str, String str2, String str3) {
        TreeMap treeMap;
        bq b;
        bq b2;
        try {
            HashMap hashMap = null;
            if (!ao.a(str2)) {
                treeMap = new TreeMap();
                b2 = bq.b(str2);
                b2.a((Map) treeMap);
                b2.close();
            } else {
                treeMap = null;
            }
            if (!ao.a(str3)) {
                hashMap = new HashMap();
                b = bq.b(str3);
                b.h();
                while (b.j()) {
                    hashMap.put(b.l(), Long.valueOf(b.q()));
                }
                b.i();
                b.close();
            }
            gj.a(str, treeMap, (Map) hashMap);
        } catch (Exception unused) {
        } catch (Throwable th) {
            b2.close();
            throw th;
        }
    }
}
