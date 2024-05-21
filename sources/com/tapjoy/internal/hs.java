package com.tapjoy.internal;

import java.util.Arrays;
import javax.annotation.Nullable;

abstract class hs implements gp {
    private static final String[] a;

    hs() {
    }

    static {
        String[] strArr = {"reward", "purchase", "custom_action"};
        a = strArr;
        Arrays.sort(strArr);
    }

    public final void a(gq gqVar) {
        if (this instanceof gt) {
            gt gtVar = (gt) this;
            gqVar.a(gtVar.a(), gtVar.b());
        } else if (this instanceof gu) {
            gu guVar = (gu) this;
            gqVar.a(guVar.a(), guVar.b(), guVar.c(), guVar.d());
        }
    }

    public static boolean a(String str) {
        return Arrays.binarySearch(a, str) >= 0;
    }

    @Nullable
    public static hs a(String str, bq bqVar) {
        if ("reward".equals(str)) {
            return (hs) bqVar.a(ic.a);
        }
        if ("purchase".equals(str)) {
            return (hs) bqVar.a(ia.a);
        }
        return null;
    }
}
