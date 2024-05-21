package com.tapjoy.internal;

import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.gj;
import com.vungle.warren.ui.VungleActivity;
import java.util.HashMap;
import java.util.Map;

public abstract class fu {
    private static final String c = "fu";
    public final Map a = new HashMap();
    public final Map b = new HashMap();

    protected fu(String str, String str2, String str3) {
        this.a.put(VungleActivity.PLACEMENT_EXTRA, str);
        this.a.put("placement_type", str2);
        this.a.put("content_type", str3);
    }

    /* access modifiers changed from: protected */
    public final gj.a a(String str, Map map, Map map2) {
        gj.a b2 = gj.e(str).a().a(this.a).a(map).b(map2);
        this.b.put(str, b2);
        return b2;
    }

    public final void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    /* access modifiers changed from: protected */
    public final gj.a b(String str, Map map, Map map2) {
        gj.a aVar = !ao.a(str) ? (gj.a) this.b.remove(str) : null;
        if (aVar == null) {
            String str2 = c;
            TapjoyLog.e(str2, "Error when calling endTrackingEvent -- " + str + " tracking has not been started.");
        } else {
            aVar.a(this.a).a(map).b(map2).b().c();
        }
        return aVar;
    }

    public final gj.a a() {
        return a("Content.rendered", (Map) null, (Map) null);
    }

    public final gj.a b() {
        return b("Content.rendered", (Map) null, (Map) null);
    }
}
