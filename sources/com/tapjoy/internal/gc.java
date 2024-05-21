package com.tapjoy.internal;

import com.tapdaq.sdk.TapdaqPlacement;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.gn;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

public final class gc extends gn {
    static final Map a = Collections.unmodifiableMap(new HashMap());
    private final gn.a c = a("BuildConfig");
    private final gn.a d = a("ServerFinal");
    private final gn.a e = a("AppRuntime");
    private final gn.a f;
    private final gn.a g;

    gc() {
        this.e.b = new ConcurrentHashMap();
        this.f = a("ConnectFlags");
        this.g = a("ServerDefault");
        gn.a a2 = a("SDKDefault");
        if (!"".isEmpty()) {
            try {
                this.c.b = bq.b("").d();
            } catch (IOException e2) {
                throw new Error("BuildConfig.TJC_CONFIGURATION malformed", e2);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("placement_request_content_retry_timeout", -1);
        hashMap.put("placement_request_content_retry_backoff", Arrays.asList(new Number[]{0L, 500L, Long.valueOf(TapjoyConstants.TIMER_INCREMENT), Double.valueOf(2.0d)}));
        a2.b = hashMap;
    }

    public final void a(@Nullable Map map) {
        Map map2;
        Map map3 = null;
        if (map != null) {
            map3 = (Map) map.get("final");
            map2 = (Map) map.get(TapdaqPlacement.TDPTagDefault);
        } else {
            map2 = null;
        }
        this.d.b = map3;
        this.g.b = map2;
        setChanged();
    }

    public final void a(Hashtable hashtable) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : hashtable.entrySet()) {
            String str = (String) a.get(entry.getKey());
            if (str == null) {
                str = (String) entry.getKey();
            }
            this.e.b.remove(str);
            hashMap.put(str, entry.getValue());
        }
        this.f.b = hashMap;
        setChanged();
    }
}
