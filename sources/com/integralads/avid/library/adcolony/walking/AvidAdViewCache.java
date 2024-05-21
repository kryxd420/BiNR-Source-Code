package com.integralads.avid.library.adcolony.walking;

import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewParent;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidViewUtil;
import com.integralads.avid.library.adcolony.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class AvidAdViewCache {
    private final AvidAdSessionRegistry a;
    private final HashMap<View, String> b = new HashMap<>();
    private final HashMap<View, ArrayList<String>> c = new HashMap<>();
    private final HashSet<View> d = new HashSet<>();
    private final HashSet<String> e = new HashSet<>();
    private final HashSet<String> f = new HashSet<>();
    private boolean g;

    public AvidAdViewCache(AvidAdSessionRegistry avidAdSessionRegistry) {
        this.a = avidAdSessionRegistry;
    }

    public HashSet<String> getVisibleSessionIds() {
        return this.e;
    }

    public HashSet<String> getHiddenSessionIds() {
        return this.f;
    }

    public void prepare() {
        for (InternalAvidAdSession next : this.a.getInternalAvidAdSessions()) {
            View view = next.getView();
            if (next.isActive() && view != null) {
                if (a(view)) {
                    this.e.add(next.getAvidAdSessionId());
                    this.b.put(view, next.getAvidAdSessionId());
                    a(next);
                } else {
                    this.f.add(next.getAvidAdSessionId());
                }
            }
        }
    }

    private boolean a(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            if (!AvidViewUtil.isViewVisible(view)) {
                return false;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.d.addAll(hashSet);
        return true;
    }

    private void a(InternalAvidAdSession internalAvidAdSession) {
        Iterator<AvidView> it = internalAvidAdSession.getObstructionsWhiteList().getWhiteList().iterator();
        while (it.hasNext()) {
            AvidView next = it.next();
            if (!next.isEmpty()) {
                a((View) next.get(), internalAvidAdSession);
            }
        }
    }

    private void a(View view, InternalAvidAdSession internalAvidAdSession) {
        ArrayList arrayList = this.c.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.c.put(view, arrayList);
        }
        arrayList.add(internalAvidAdSession.getAvidAdSessionId());
    }

    public void cleanup() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g = false;
    }

    public void onAdViewProcessed() {
        this.g = true;
    }

    public String getSessionId(View view) {
        if (this.b.size() == 0) {
            return null;
        }
        String str = this.b.get(view);
        if (str != null) {
            this.b.remove(view);
        }
        return str;
    }

    public ArrayList<String> getFriendlySessionIds(View view) {
        if (this.c.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = this.c.get(view);
        if (arrayList != null) {
            this.c.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public ViewType getViewType(View view) {
        if (this.d.contains(view)) {
            return ViewType.ROOT_VIEW;
        }
        return this.g ? ViewType.OBSTRUCTION_VIEW : ViewType.UNDERLYING_VIEW;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public HashMap<View, String> a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public HashMap<View, ArrayList<String>> b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public HashSet<View> c() {
        return this.d;
    }
}
