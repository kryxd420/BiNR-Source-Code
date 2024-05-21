package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import javax.annotation.Nullable;

public abstract class hk {
    long c;
    boolean d;
    public gw e;
    public String f;
    fu g;

    public abstract void a(he heVar, ga gaVar);

    public abstract void b();

    public boolean c() {
        return true;
    }

    static void a(Context context, @Nullable String str) {
        if (!ju.c(str)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception unused) {
            }
        }
    }
}
