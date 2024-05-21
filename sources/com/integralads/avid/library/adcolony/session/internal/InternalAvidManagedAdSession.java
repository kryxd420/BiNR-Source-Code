package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidTrackingWebViewManager;

public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager a = new AvidTrackingWebViewManager(this.b);
    private final WebView b;

    public InternalAvidManagedAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.b = new WebView(context.getApplicationContext());
    }

    public WebView getWebView() {
        return this.b;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.a;
    }

    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.a.loadHTML();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidTrackingWebViewManager avidTrackingWebViewManager) {
        this.a = avidTrackingWebViewManager;
    }
}
