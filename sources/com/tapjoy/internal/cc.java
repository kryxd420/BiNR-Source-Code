package com.tapjoy.internal;

import android.support.v4.app.NotificationCompat;
import com.tapjoy.TJAdUnitConstants;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class cc extends cd {
    public abstract Object a(bq bqVar);

    public final Map a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Accept", "application/json");
        return linkedHashMap;
    }

    public final Object a(URI uri, InputStream inputStream) {
        bq a = bq.a(inputStream);
        a.a("BASE_URI", uri);
        int i = 0;
        try {
            a.h();
            Object obj = null;
            String str = null;
            while (a.j()) {
                String l = a.l();
                if (NotificationCompat.CATEGORY_STATUS.equals(l)) {
                    i = a.r();
                } else if ("message".equals(l)) {
                    str = a.m();
                } else if (TJAdUnitConstants.String.DATA.equals(l)) {
                    obj = a(a);
                } else {
                    a.s();
                }
            }
            a.i();
            if (i == 200) {
                return obj;
            }
            throw new ce(i, str);
        } finally {
            a.close();
        }
    }
}
