package com.moat.analytics.mobile.vng;

import android.support.annotation.Nullable;
import android.webkit.WebView;

class aa extends b implements WebAdTracker {
    aa(@Nullable WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", (Object) this, "In initialization method.");
        super.a(webView);
        p.a("[SUCCESS] ", a() + " created for " + e());
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "WebAdTracker";
    }
}
