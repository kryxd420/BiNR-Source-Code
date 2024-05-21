package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;

public class InternalAvidDisplayAdSession extends InternalAvidHtmlAdSession {
    public InternalAvidDisplayAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public SessionType getSessionType() {
        return SessionType.DISPLAY;
    }

    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
