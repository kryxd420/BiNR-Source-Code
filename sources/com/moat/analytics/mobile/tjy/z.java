package com.moat.analytics.mobile.tjy;

import android.util.Log;
import com.moat.analytics.mobile.tjy.base.functional.a;

class z implements ba {
    final /* synthetic */ ap a;
    final /* synthetic */ String b;
    final /* synthetic */ v c;

    z(v vVar, ap apVar, String str) {
        this.c = vVar;
        this.a = apVar;
        this.b = str;
    }

    public a a() {
        if (this.a.b()) {
            Log.d("MoatFactory", "Creating NativeVideo tracker.");
        }
        return a.a(new ah(this.b, this.c.b, this.a));
    }
}
