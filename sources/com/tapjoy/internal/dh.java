package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import java.util.Collections;

public final class dh {
    @SuppressLint({"StaticFieldLeak"})
    private static dh f = new dh();
    public Context a;
    public BroadcastReceiver b;
    public boolean c;
    public boolean d;
    public a e;

    public interface a {
        void a(boolean z);
    }

    private dh() {
    }

    public static dh a() {
        return f;
    }

    public final boolean b() {
        return !this.d;
    }

    public final void c() {
        boolean z = !this.d;
        for (dc dcVar : Collections.unmodifiableCollection(dg.a().a)) {
            dw dwVar = dcVar.c;
            if (dwVar.a.get() != null) {
                dj.a().a(dwVar.c(), "setState", z ? "foregrounded" : "backgrounded");
            }
        }
    }

    static /* synthetic */ void a(dh dhVar, boolean z) {
        if (dhVar.d != z) {
            dhVar.d = z;
            if (dhVar.c) {
                dhVar.c();
                if (dhVar.e != null) {
                    dhVar.e.a(dhVar.b());
                }
            }
        }
    }
}
