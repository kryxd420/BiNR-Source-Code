package com.integralads.avid.library.adcolony;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.integralads.avid.library.adcolony.processing.AvidProcessorFactory;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.utils.AvidViewUtil;
import com.integralads.avid.library.adcolony.walking.AvidAdViewCache;
import com.integralads.avid.library.adcolony.walking.AvidStatePublisher;
import com.integralads.avid.library.adcolony.walking.ViewType;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AvidTreeWalker implements IAvidNodeProcessor.IAvidViewWalker {
    private static final int a = 200;
    private static AvidTreeWalker b = new AvidTreeWalker();
    /* access modifiers changed from: private */
    public static a c;
    /* access modifiers changed from: private */
    public static final Runnable k = new Runnable() {
        public void run() {
            if (AvidTreeWalker.c != null) {
                AvidTreeWalker.c.sendEmptyMessage(0);
                AvidTreeWalker.c.postDelayed(AvidTreeWalker.k, 200);
            }
        }
    };
    private List<AvidTreeWalkerTimeLogger> d = new ArrayList();
    private int e;
    private AvidProcessorFactory f = new AvidProcessorFactory();
    private AvidAdViewCache g = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private AvidStatePublisher h = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());
    private double i;
    private double j;

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    public static AvidTreeWalker getInstance() {
        return b;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (!this.d.contains(avidTreeWalkerTimeLogger)) {
            this.d.add(avidTreeWalkerTimeLogger);
        }
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.d.contains(avidTreeWalkerTimeLogger)) {
            this.d.remove(avidTreeWalkerTimeLogger);
        }
    }

    public void start() {
        g();
        d();
    }

    public void stop() {
        pause();
        this.d.clear();
        this.h.cleanupCache();
    }

    public void pause() {
        h();
    }

    /* access modifiers changed from: private */
    public void d() {
        e();
        a();
        f();
    }

    private void e() {
        this.e = 0;
        this.i = AvidTimestamp.getCurrentTime();
    }

    private void f() {
        this.j = AvidTimestamp.getCurrentTime();
        a((long) (this.j - this.i));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a() {
        this.g.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.f.getRootProcessor();
        if (this.g.getHiddenSessionIds().size() > 0) {
            this.h.publishEmptyState(rootProcessor.getState((View) null), this.g.getHiddenSessionIds(), currentTime);
        }
        if (this.g.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState((View) null);
            a((View) null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.h.publishState(state, this.g.getVisibleSessionIds(), currentTime);
        } else {
            this.h.cleanupCache();
        }
        this.g.cleanup();
    }

    public void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject) {
        ViewType viewType;
        if (AvidViewUtil.isViewVisible(view) && (viewType = this.g.getViewType(view)) != ViewType.UNDERLYING_VIEW) {
            JSONObject state = iAvidNodeProcessor.getState(view);
            AvidJSONUtil.addChildState(jSONObject, state);
            if (!a(view, state)) {
                b(view, state);
                a(view, iAvidNodeProcessor, state, viewType);
            }
            this.e++;
        }
    }

    private void a(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean a(View view, JSONObject jSONObject) {
        String sessionId = this.g.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.g.onAdViewProcessed();
        return true;
    }

    private void b(View view, JSONObject jSONObject) {
        ArrayList<String> friendlySessionIds = this.g.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void a(long j2) {
        if (this.d.size() > 0) {
            for (AvidTreeWalkerTimeLogger onTreeProcessed : this.d) {
                onTreeProcessed.onTreeProcessed(this.e, j2);
            }
        }
    }

    private void g() {
        if (c == null) {
            c = new a();
            c.postDelayed(k, 200);
        }
    }

    private void h() {
        if (c != null) {
            c.removeCallbacks(k);
            c = null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidAdViewCache avidAdViewCache) {
        this.g = avidAdViewCache;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidStatePublisher avidStatePublisher) {
        this.h = avidStatePublisher;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(AvidProcessorFactory avidProcessorFactory) {
        this.f = avidProcessorFactory;
    }

    private static class a extends Handler {
        private a() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            AvidTreeWalker.getInstance().d();
        }
    }
}
