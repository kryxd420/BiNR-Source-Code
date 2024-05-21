package com.integralads.avid.library.adcolony.activity;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.Window;
import com.integralads.avid.library.adcolony.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvidActivityStack {
    private static AvidActivityStack a = new AvidActivityStack();
    private final ArrayList<AvidActivity> b = new ArrayList<>();

    public static AvidActivityStack getInstance() {
        return a;
    }

    public void addActivity(Activity activity) {
        if (a(activity) == null) {
            this.b.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        ArrayList arrayList = new ArrayList();
        Iterator<AvidActivity> it = this.b.iterator();
        View view = null;
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (a(next)) {
                it.remove();
            } else {
                View b2 = b(next);
                if (b2 != null) {
                    view = b2;
                }
            }
        }
        if (view != null) {
            arrayList.add(view);
        }
        return arrayList;
    }

    public void cleanup() {
        this.b.clear();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public AvidActivity a(Activity activity) {
        Iterator<AvidActivity> it = this.b.iterator();
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (next.contains(activity)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public List<AvidActivity> a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean a(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
        if (activity == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    @VisibleForTesting
    private View b(AvidActivity avidActivity) {
        Window window;
        Activity activity = (Activity) avidActivity.get();
        if (activity == null || (window = activity.getWindow()) == null || !activity.hasWindowFocus()) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null || !decorView.isShown()) {
            return null;
        }
        return decorView;
    }

    @VisibleForTesting
    static void a(AvidActivityStack avidActivityStack) {
        a = avidActivityStack;
    }
}
