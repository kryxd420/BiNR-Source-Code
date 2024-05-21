package com.tapjoy.internal;

import android.support.annotation.VisibleForTesting;
import com.tapjoy.internal.ec;
import org.json.JSONObject;

public final class eh implements ec.b {
    final ed a;
    private JSONObject b;

    public eh(ed edVar) {
        this.a = edVar;
    }

    @VisibleForTesting
    public final JSONObject a() {
        return this.b;
    }

    @VisibleForTesting
    public final void a(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public final void b() {
        this.a.a(new ee(this));
    }
}
