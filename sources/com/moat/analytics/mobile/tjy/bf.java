package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class bf implements ba {
    final /* synthetic */ a a;
    final /* synthetic */ ap b;
    final /* synthetic */ ReactiveVideoTrackerPlugin c;

    bf(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin, a aVar, ap apVar) {
        this.c = reactiveVideoTrackerPlugin;
        this.a = aVar;
        this.b = apVar;
    }

    public a a() {
        return a.a(new bd(this.c.a, this.a, this.b));
    }
}
