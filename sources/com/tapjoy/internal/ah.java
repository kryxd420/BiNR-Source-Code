package com.tapjoy.internal;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public final class ah extends ag {
    private final AnimationSet b = ((AnimationSet) this.a);

    public ah() {
        super(new AnimationSet(true));
    }

    public final ah a(Animation animation) {
        this.b.addAnimation(animation);
        return this;
    }

    public final /* bridge */ /* synthetic */ Animation a() {
        return this.b;
    }
}
