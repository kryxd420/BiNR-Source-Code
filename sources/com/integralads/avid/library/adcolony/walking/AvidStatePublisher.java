package com.integralads.avid.library.adcolony.walking;

import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTaskQueue;
import com.integralads.avid.library.adcolony.walking.async.AvidCleanupAsyncTask;
import com.integralads.avid.library.adcolony.walking.async.AvidEmptyPublishAsyncTask;
import com.integralads.avid.library.adcolony.walking.async.AvidPublishAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidStatePublisher implements AvidAsyncTask.StateProvider {
    private final AvidAdSessionRegistry a;
    private JSONObject b;
    private final AvidAsyncTaskQueue c;

    public AvidStatePublisher(AvidAdSessionRegistry avidAdSessionRegistry, AvidAsyncTaskQueue avidAsyncTaskQueue) {
        this.a = avidAdSessionRegistry;
        this.c = avidAsyncTaskQueue;
    }

    public void publishState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.c.submitTask(new AvidPublishAsyncTask(this, this.a, hashSet, jSONObject, d));
    }

    public void publishEmptyState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.c.submitTask(new AvidEmptyPublishAsyncTask(this, this.a, hashSet, jSONObject, d));
    }

    public void cleanupCache() {
        this.c.submitTask(new AvidCleanupAsyncTask(this));
    }

    @VisibleForTesting
    public JSONObject getPreviousState() {
        return this.b;
    }

    @VisibleForTesting
    public void setPreviousState(JSONObject jSONObject) {
        this.b = jSONObject;
    }
}
