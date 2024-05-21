package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;

public class InternalAvidManagedVideoAdSession extends InternalAvidManagedAdSession {
    private AvidVideoPlaybackListenerImpl a = new AvidVideoPlaybackListenerImpl(this, getAvidBridgeManager());

    public InternalAvidManagedVideoAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public AvidVideoPlaybackListenerImpl getAvidVideoPlaybackListener() {
        return this.a;
    }

    public SessionType getSessionType() {
        return SessionType.MANAGED_VIDEO;
    }

    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }

    public void onEnd() {
        this.a.destroy();
        super.onEnd();
    }
}
