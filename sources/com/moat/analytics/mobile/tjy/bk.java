package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class bk implements bc {
    private static final a a;

    static {
        a a2 = a.a();
        try {
            a2 = a.a(WebAdTracker.class.getMethod("track", new Class[0]));
        } catch (NoSuchMethodException e) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
        }
        a = a2;
    }

    bk() {
    }

    public Class a() {
        return WebAdTracker.class;
    }
}
