package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class f {
    private static final Map<String, f> a = new HashMap(1);
    private static final Object b = new Object();
    private final Map<String, Integer> c = new HashMap();
    private final Object d = new Object();

    private f() {
    }

    public static f a(String str) {
        f fVar;
        synchronized (b) {
            if (!a.containsKey(str)) {
                a.put(str, new f());
            }
            fVar = a.get(str);
        }
        return fVar;
    }

    public Integer b(String str) {
        Integer num;
        synchronized (this.d) {
            if (this.c.containsKey(str)) {
                this.c.put(str, Integer.valueOf(this.c.get(str).intValue() + 1));
            } else {
                this.c.put(str, 1);
            }
            num = this.c.get(str);
        }
        return num;
    }
}
