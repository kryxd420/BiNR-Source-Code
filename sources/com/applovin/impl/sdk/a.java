package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class a implements Application.ActivityLifecycleCallbacks {
    private final j a;
    private final List<com.applovin.impl.sdk.e.a> b = Collections.synchronizedList(new ArrayList());
    private WeakReference<Activity> c = new WeakReference<>((Object) null);

    public a(j jVar, Context context) {
        this.a = jVar;
        if (context instanceof Activity) {
            this.c = new WeakReference<>((Activity) context);
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    public Activity a() {
        return (Activity) this.c.get();
    }

    public void a(com.applovin.impl.sdk.e.a aVar) {
        this.b.add(aVar);
    }

    public void b(com.applovin.impl.sdk.e.a aVar) {
        this.b.remove(aVar);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        for (com.applovin.impl.sdk.e.a onActivityCreated : new ArrayList(this.b)) {
            onActivityCreated.onActivityCreated(activity, bundle);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        for (com.applovin.impl.sdk.e.a onActivityDestroyed : new ArrayList(this.b)) {
            onActivityDestroyed.onActivityDestroyed(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        for (com.applovin.impl.sdk.e.a onActivityPaused : new ArrayList(this.b)) {
            onActivityPaused.onActivityPaused(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        this.c = new WeakReference<>(activity);
        for (com.applovin.impl.sdk.e.a onActivityResumed : new ArrayList(this.b)) {
            onActivityResumed.onActivityResumed(activity);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        for (com.applovin.impl.sdk.e.a onActivitySaveInstanceState : new ArrayList(this.b)) {
            onActivitySaveInstanceState.onActivitySaveInstanceState(activity, bundle);
        }
    }

    public void onActivityStarted(Activity activity) {
        for (com.applovin.impl.sdk.e.a onActivityStarted : new ArrayList(this.b)) {
            onActivityStarted.onActivityStarted(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        for (com.applovin.impl.sdk.e.a onActivityStopped : new ArrayList(this.b)) {
            onActivityStopped.onActivityStopped(activity);
        }
    }
}
