package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.AvidBridge;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.weakreference.AvidView;

public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManager.AvidBridgeManagerListener {
    private final InternalAvidAdSessionContext a;
    private AvidBridgeManager b = new AvidBridgeManager(this.a);
    private AvidWebViewManager c;
    private AvidView<T> d;
    private AvidDeferredAdSessionListenerImpl e;
    private InternalAvidAdSessionListener f;
    private boolean g;
    private boolean h;
    private final ObstructionsWhiteList i;
    private a j;
    private double k;

    enum a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onViewRegistered() {
    }

    /* access modifiers changed from: protected */
    public void onViewUnregistered() {
    }

    public InternalAvidAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        this.a = new InternalAvidAdSessionContext(context, str, getSessionType().toString(), getMediaType().toString(), externalAvidAdSessionContext);
        this.b.setListener(this);
        this.c = new AvidWebViewManager(this.a, this.b);
        this.d = new AvidView<>(null);
        this.g = !externalAvidAdSessionContext.isDeferred();
        if (!this.g) {
            this.e = new AvidDeferredAdSessionListenerImpl(this, this.b);
        }
        this.i = new ObstructionsWhiteList();
        c();
    }

    public String getAvidAdSessionId() {
        return this.a.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.a.getAvidAdSessionContext();
    }

    public T getView() {
        return (View) this.d.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.e;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.f;
    }

    public void setListener(InternalAvidAdSessionListener internalAvidAdSessionListener) {
        this.f = internalAvidAdSessionListener;
    }

    public boolean isEmpty() {
        return this.d.isEmpty();
    }

    public boolean isActive() {
        return this.h;
    }

    public boolean isReady() {
        return this.g;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.b;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.i;
    }

    public void registerAdView(T t) {
        if (!doesManageView(t)) {
            c();
            this.d.set(t);
            onViewRegistered();
            sessionStateCanBeChanged();
        }
    }

    public void unregisterAdView(T t) {
        if (doesManageView(t)) {
            c();
            cleanupViewState();
            this.d.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.d.contains(view);
    }

    public void onEnd() {
        cleanupViewState();
        if (this.e != null) {
            this.e.destroy();
        }
        this.b.destroy();
        this.c.destroy();
        this.g = false;
        sessionStateCanBeChanged();
        if (this.f != null) {
            this.f.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.g = true;
        sessionStateCanBeChanged();
    }

    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean z) {
        if (isActive()) {
            this.b.publishAppState(z ? AvidBridge.APP_STATE_ACTIVE : AvidBridge.APP_STATE_INACTIVE);
        }
    }

    public void publishNativeViewStateCommand(String str, double d2) {
        if (d2 > this.k) {
            this.b.callAvidbridge(str);
            this.j = a.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String str, double d2) {
        if (d2 > this.k && this.j != a.AD_STATE_HIDDEN) {
            this.b.callAvidbridge(str);
            this.j = a.AD_STATE_HIDDEN;
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupViewState() {
        if (isActive()) {
            this.b.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    /* access modifiers changed from: protected */
    public void updateWebViewManager() {
        this.c.setWebView(getWebView());
    }

    /* access modifiers changed from: protected */
    public void sessionStateCanBeChanged() {
        boolean z = this.b.isActive() && this.g && !isEmpty();
        if (this.h != z) {
            setActive(z);
        }
    }

    /* access modifiers changed from: protected */
    public void setActive(boolean z) {
        this.h = z;
        if (this.f == null) {
            return;
        }
        if (z) {
            this.f.sessionHasBecomeActive(this);
        } else {
            this.f.sessionHasResignedActive(this);
        }
    }

    private void c() {
        this.k = AvidTimestamp.getCurrentTime();
        this.j = a.AD_STATE_IDLE;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidBridgeManager avidBridgeManager) {
        this.b = avidBridgeManager;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public a a() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public double b() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidWebViewManager avidWebViewManager) {
        this.c = avidWebViewManager;
    }
}
