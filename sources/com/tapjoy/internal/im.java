package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class im extends cc {
    public final String b() {
        return "POST";
    }

    public final String d() {
        return "application/json";
    }

    public Map e() {
        Map e = super.e();
        hd a = hd.a();
        e.put("sdk_ver", a.m + "/Android");
        e.put(TapjoyConstants.TJC_API_KEY, a.l);
        if (ha.a) {
            e.put("debug", true);
        }
        return e;
    }

    /* access modifiers changed from: protected */
    public Object f() {
        try {
            return super.f();
        } catch (Exception e) {
            new Object[1][0] = this;
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object a(bq bqVar) {
        bqVar.s();
        return null;
    }
}
