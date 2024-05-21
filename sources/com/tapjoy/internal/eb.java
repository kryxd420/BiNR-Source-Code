package com.tapjoy.internal;

import com.tapjoy.internal.ec;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class eb extends ec {
    protected final HashSet a;
    protected final JSONObject b;
    protected final double c;

    public eb(ec.b bVar, HashSet hashSet, JSONObject jSONObject, double d) {
        super(bVar);
        this.a = new HashSet(hashSet);
        this.b = jSONObject;
        this.c = d;
    }
}
