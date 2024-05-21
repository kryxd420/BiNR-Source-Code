package com.integralads.avid.library.adcolony.registration;

import android.view.View;
import com.integralads.avid.library.adcolony.session.AbstractAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry a = new AvidAdSessionRegistry();
    private final HashMap<String, InternalAvidAdSession> b = new HashMap<>();
    private final HashMap<String, AbstractAvidAdSession> c = new HashMap<>();
    private AvidAdSessionRegistryListener d;
    private int e = 0;

    public static AvidAdSessionRegistry getInstance() {
        return a;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.d;
    }

    public void setListener(AvidAdSessionRegistryListener avidAdSessionRegistryListener) {
        this.d = avidAdSessionRegistryListener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        this.c.put(abstractAvidAdSession.getAvidAdSessionId(), abstractAvidAdSession);
        this.b.put(abstractAvidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.c.size() == 1 && this.d != null) {
            this.d.registryHasSessionsChanged(this);
        }
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.b.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.c.values();
    }

    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.e > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return this.c.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return this.b.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession next : this.b.values()) {
            if (next.doesManageView(view)) {
                return next;
            }
        }
        return null;
    }

    public void sessionDidEnd(InternalAvidAdSession internalAvidAdSession) {
        this.c.remove(internalAvidAdSession.getAvidAdSessionId());
        this.b.remove(internalAvidAdSession.getAvidAdSessionId());
        internalAvidAdSession.setListener((InternalAvidAdSessionListener) null);
        if (this.c.size() == 0 && this.d != null) {
            this.d.registryHasSessionsChanged(this);
        }
    }

    public void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession) {
        this.e++;
        if (this.e == 1 && this.d != null) {
            this.d.registryHasActiveSessionsChanged(this);
        }
    }

    public void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession) {
        this.e--;
        if (this.e == 0 && this.d != null) {
            this.d.registryHasActiveSessionsChanged(this);
        }
    }
}
