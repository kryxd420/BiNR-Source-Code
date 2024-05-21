package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.moat.analytics.mobile.tjy.base.asserts.a;
import java.lang.ref.WeakReference;

class c implements a {
    /* access modifiers changed from: private */
    public final WeakReference a;
    /* access modifiers changed from: private */
    public final WeakReference b;
    private boolean c;
    /* access modifiers changed from: private */
    public final ap d;
    /* access modifiers changed from: private */
    public boolean e;

    c(Activity activity, ap apVar) {
        String str;
        a.a(activity);
        if (apVar.b()) {
            StringBuilder sb = new StringBuilder("Listening to Activity: ");
            if (activity != null) {
                str = activity.getClass() + "@" + activity.hashCode();
            } else {
                str = "null";
            }
            sb.append(str);
            Log.d("MoatActivityState", sb.toString());
        }
        this.a = new WeakReference(activity.getApplication());
        this.b = new WeakReference(activity);
        this.d = apVar;
        this.c = false;
    }

    public boolean a() {
        return this.e;
    }

    public void b() {
        if (!this.c) {
            ((Application) this.a.get()).registerActivityLifecycleCallbacks(new e(this));
        }
    }

    public Activity c() {
        return (Activity) this.b.get();
    }
}
