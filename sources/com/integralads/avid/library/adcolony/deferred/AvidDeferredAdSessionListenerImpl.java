package com.integralads.avid.library.adcolony.deferred;

import com.integralads.avid.library.adcolony.base.AvidBaseListenerImpl;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;

public class AvidDeferredAdSessionListenerImpl extends AvidBaseListenerImpl implements AvidDeferredAdSessionListener {
    public AvidDeferredAdSessionListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    public void recordReadyEvent() {
        assertSessionIsNotEnded();
        if (!getAvidAdSession().isReady()) {
            getAvidBridgeManager().publishReadyEventForDeferredAdSession();
            getAvidAdSession().onReady();
            return;
        }
        throw new IllegalStateException("The AVID ad session is already ready. Please ensure you are only calling recordReadyEvent once for the deferred AVID ad session.");
    }
}
