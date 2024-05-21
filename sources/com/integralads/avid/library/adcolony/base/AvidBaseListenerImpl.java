package com.integralads.avid.library.adcolony.base;

import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;

public abstract class AvidBaseListenerImpl {
    private InternalAvidAdSession a;
    private AvidBridgeManager b;

    public AvidBaseListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        this.a = internalAvidAdSession;
        this.b = avidBridgeManager;
    }

    public void destroy() {
        this.a = null;
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public InternalAvidAdSession getAvidAdSession() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public AvidBridgeManager getAvidBridgeManager() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void assertSessionIsNotEnded() {
        if (this.a == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
