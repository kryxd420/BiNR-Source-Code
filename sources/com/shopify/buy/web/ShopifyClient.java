package com.shopify.buy.web;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.shopify.buy.utils.Logger;
import java.lang.ref.WeakReference;

public class ShopifyClient {
    private static ShopifyClient instance;
    @NonNull
    private WeakReference<Activity> activityRef = new WeakReference<>((Object) null);
    private boolean initialized = false;
    @NonNull
    private final Application.ActivityLifecycleCallbacks internalCallbacks = new ActivityLifecycleCallbacksAdapter() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ShopifyClient.this.updateActivityReference(activity);
        }

        public void onActivityDestroyed(Activity activity) {
            ShopifyClient.this.updateActivityReference((Activity) null);
        }
    };

    public static ShopifyClient getInstance() {
        if (instance == null) {
            instance = new ShopifyClient();
        }
        return instance;
    }

    private ShopifyClient() {
    }

    public void init(@NonNull Activity activity) {
        if (!this.initialized) {
            updateActivityReference(activity);
            registerActivityLifecycleCallbacks(this.internalCallbacks);
            this.initialized = true;
        }
    }

    /* access modifiers changed from: private */
    public void updateActivityReference(@Nullable Activity activity) {
        this.activityRef.clear();
        this.activityRef = new WeakReference<>(activity);
    }

    private Application getApplication() {
        Activity activity = getActivity();
        if (activity != null) {
            return (Application) activity.getApplicationContext();
        }
        return null;
    }

    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        Application application = getApplication();
        if (application != null) {
            application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            Logger.error("Can't register activity lifecycle callbacks before calling init().");
        }
    }

    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        Application application = getApplication();
        if (application != null) {
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            Logger.error("Can't unregister activity lifecycle callbacks before calling init().");
        }
    }

    @Nullable
    public Activity getActivity() {
        return (Activity) this.activityRef.get();
    }

    public void startActivity(Intent intent) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            Logger.error("Can't start an activity before calling init().");
        }
    }
}
