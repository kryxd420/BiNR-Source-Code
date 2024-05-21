package com.integralads.avid.library.adcolony.session;

import android.view.View;
import com.integralads.avid.library.adcolony.AvidManager;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidManagedAdSession;

public abstract class AbstractAvidManagedAdSession extends AbstractAvidAdSession<View> {
    public void injectJavaScriptResource(String str) {
        InternalAvidManagedAdSession internalAvidManagedAdSession = (InternalAvidManagedAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidManagedAdSession != null) {
            internalAvidManagedAdSession.getJavaScriptResourceInjector().injectJavaScriptResource(str);
        }
    }
}
