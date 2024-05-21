package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;
import com.applovin.impl.sdk.j;

public abstract class f extends View {
    protected final j a;
    protected final Context b;

    public enum a {
        WhiteXOnOpaqueBlack,
        WhiteXOnTransparentGrey,
        Invisible
    }

    f(j jVar, Context context) {
        super(context);
        this.b = context;
        this.a = jVar;
    }

    public static f a(j jVar, Context context, a aVar) {
        return aVar.equals(a.Invisible) ? new l(jVar, context) : aVar.equals(a.WhiteXOnTransparentGrey) ? new n(jVar, context) : new u(jVar, context);
    }

    public abstract void a(int i);

    public abstract a getStyle();

    public abstract float getViewScale();

    public abstract void setViewScale(float f);
}
