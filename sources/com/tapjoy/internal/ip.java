package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.Map;

public final class ip extends im {
    private final fe c;
    private final ey d;
    private final fl e;
    private final String f;

    public final String c() {
        return "api/v1/tokens";
    }

    private ip(fe feVar, ey eyVar, fl flVar, String str) {
        this.c = feVar;
        this.d = eyVar;
        this.e = flVar;
        this.f = str;
    }

    public ip(ff ffVar, String str) {
        this(ffVar.d, ffVar.e, ffVar.f, str);
    }

    public final Map e() {
        Map e2 = super.e();
        e2.put(TJAdUnitConstants.String.VIDEO_INFO, new bp(ht.a(this.c)));
        e2.put(TapjoyConstants.TJC_APP_PLACEMENT, new bp(ht.a(this.d)));
        e2.put("user", new bp(ht.a(this.e)));
        if (!ao.a(this.f)) {
            e2.put("push_token", this.f);
        }
        return e2;
    }
}
