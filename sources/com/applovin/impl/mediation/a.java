package com.applovin.impl.mediation;

import android.app.Activity;
import android.os.Bundle;
import com.applovin.impl.mediation.a.c;
import com.applovin.impl.sdk.j;
import com.applovin.impl.sdk.p;

public class a extends com.applovin.impl.sdk.e.a {
    private final com.applovin.impl.sdk.a a;
    private final p b;
    private C0002a c;
    private c d;
    private int e;
    private boolean f;

    /* renamed from: com.applovin.impl.mediation.a$a  reason: collision with other inner class name */
    public interface C0002a {
        void a(c cVar);
    }

    a(j jVar) {
        this.b = jVar.v();
        this.a = jVar.T();
    }

    public void a() {
        this.b.a("AdActivityObserver", "Cancelling...");
        this.a.b(this);
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = false;
    }

    public void a(c cVar, C0002a aVar) {
        p pVar = this.b;
        pVar.a("AdActivityObserver", "Starting for ad " + cVar.getAdUnitId() + "...");
        a();
        this.c = aVar;
        this.d = cVar;
        this.a.a(this);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.f) {
            this.f = true;
        }
        this.e++;
        this.b.a("AdActivityObserver", "Created Activity: " + activity + ", counter is " + this.e);
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.f) {
            this.e--;
            this.b.a("AdActivityObserver", "Destroyed Activity: " + activity + ", counter is " + this.e);
            if (this.e <= 0) {
                this.b.a("AdActivityObserver", "Last ad Activity destroyed");
                if (this.c != null) {
                    this.b.a("AdActivityObserver", "Invoking callback...");
                    this.c.a(this.d);
                }
                a();
            }
        }
    }
}
