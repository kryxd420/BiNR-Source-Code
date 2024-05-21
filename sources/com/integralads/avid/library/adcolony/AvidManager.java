package com.integralads.avid.library.adcolony;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.AvidLoader;
import com.integralads.avid.library.adcolony.AvidStateWatcher;
import com.integralads.avid.library.adcolony.activity.AvidActivityStack;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.adcolony.session.AbstractAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;

public class AvidManager implements AvidLoader.AvidLoaderListener, AvidStateWatcher.AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager a = new AvidManager();
    private static Context b;

    public static AvidManager getInstance() {
        return a;
    }

    public void init(Context context) {
        if (b == null) {
            b = context.getApplicationContext();
            AvidStateWatcher.getInstance().init(b);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(b);
        }
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistry.getInstance().registerAvidAdSession(abstractAvidAdSession, internalAvidAdSession);
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findAvidAdSessionById(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
    }

    public void registerActivity(Activity activity) {
        AvidActivityStack.getInstance().addActivity(activity);
    }

    private void a() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void b() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        b = null;
    }

    private boolean c() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void d() {
        AvidAdSessionRegistry.getInstance().setListener((AvidAdSessionRegistryListener) null);
        for (InternalAvidAdSession avidBridgeManager : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            avidBridgeManager.getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    public void onAvidLoaded() {
        if (c()) {
            d();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                a();
            }
        }
    }

    public void onAppStateChanged(boolean z) {
        if (z) {
            AvidTreeWalker.getInstance().start();
        } else {
            AvidTreeWalker.getInstance().pause();
        }
    }

    public void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.isEmpty() && !AvidBridge.isAvidJsReady()) {
            AvidLoader.getInstance().setListener(this);
            AvidLoader.getInstance().registerAvidLoader(b);
        }
    }

    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.hasActiveSessions() || !AvidBridge.isAvidJsReady()) {
            b();
        } else {
            a();
        }
    }

    @VisibleForTesting
    static void a(AvidManager avidManager) {
        a = avidManager;
    }
}
