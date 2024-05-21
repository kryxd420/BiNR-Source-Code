package com.applovin.impl.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.k;
import com.applovin.sdk.AppLovinVariableService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class VariableServiceImpl implements AppLovinVariableService {
    private final j a;
    private final AtomicBoolean b = new AtomicBoolean();
    /* access modifiers changed from: private */
    public AppLovinVariableService.OnVariablesUpdateListener c;
    private Bundle d;
    private final Object e = new Object();

    VariableServiceImpl(j jVar) {
        this.a = jVar;
        String str = (String) jVar.a(d.e);
        if (k.b(str)) {
            updateVariables(g.a(str, jVar));
        }
    }

    private Object a(String str, Object obj, Class<?> cls) {
        if (TextUtils.isEmpty(str)) {
            this.a.v().e("AppLovinVariableService", "Unable to retrieve variable value for empty name");
            return obj;
        }
        synchronized (this.e) {
            if (this.d == null) {
                this.a.v().e("AppLovinVariableService", "Unable to retrieve variable value, none retrieved from server yet. Please set a listener to be notified when values are retrieved from the server.");
                return obj;
            } else if (cls.equals(String.class)) {
                String string = this.d.getString(str, (String) obj);
                return string;
            } else if (cls.equals(Boolean.class)) {
                Boolean valueOf = Boolean.valueOf(this.d.getBoolean(str, ((Boolean) obj).booleanValue()));
                return valueOf;
            } else {
                throw new IllegalStateException("Unable to retrieve variable value for " + str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.e
            monitor-enter(r0)
            com.applovin.sdk.AppLovinVariableService$OnVariablesUpdateListener r1 = r4.c     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x001f
            android.os.Bundle r1 = r4.d     // Catch:{ all -> 0x0021 }
            if (r1 != 0) goto L_0x000c
            goto L_0x001f
        L_0x000c:
            android.os.Bundle r1 = r4.d     // Catch:{ all -> 0x0021 }
            java.lang.Object r1 = r1.clone()     // Catch:{ all -> 0x0021 }
            android.os.Bundle r1 = (android.os.Bundle) r1     // Catch:{ all -> 0x0021 }
            r2 = 1
            com.applovin.impl.sdk.VariableServiceImpl$1 r3 = new com.applovin.impl.sdk.VariableServiceImpl$1     // Catch:{ all -> 0x0021 }
            r3.<init>(r1)     // Catch:{ all -> 0x0021 }
            com.applovin.sdk.AppLovinSdkUtils.runOnUiThread(r2, r3)     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return
        L_0x0021:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.VariableServiceImpl.a():void");
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) a(str, Boolean.valueOf(z), Boolean.class)).booleanValue();
    }

    public String getString(String str) {
        return getString(str, (String) null);
    }

    public String getString(String str, String str2) {
        return (String) a(str, str2, String.class);
    }

    public void setOnVariablesUpdateListener(AppLovinVariableService.OnVariablesUpdateListener onVariablesUpdateListener) {
        this.c = onVariablesUpdateListener;
        synchronized (this.e) {
            if (onVariablesUpdateListener != null) {
                try {
                    if (this.d != null && this.b.compareAndSet(false, true)) {
                        this.a.v().a("AppLovinVariableService", "Setting initial listener");
                        a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public String toString() {
        return "VariableService{variables=" + this.d + ", listener=" + this.c + '}';
    }

    public void updateVariables(JSONObject jSONObject) {
        this.a.v().a("AppLovinVariableService", "Updating variables...");
        synchronized (this.e) {
            this.d = g.b(jSONObject);
            a();
            this.a.a(d.e, jSONObject.toString());
        }
    }
}
