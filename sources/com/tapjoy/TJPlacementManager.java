package com.tapjoy;

import android.content.Context;
import com.tapjoy.internal.bc;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.hi;
import com.tapjoy.internal.ju;

public class TJPlacementManager {
    private static final bc a = bc.a();
    private static int b = 0;
    private static int c = 0;
    private static int d = 3;
    private static int e = 3;

    public static TJPlacement createPlacement(Context context, String str, boolean z, TJPlacementListener tJPlacementListener) {
        TJCorePlacement a2 = a(str, (String) null, (String) null, z, false);
        a2.j = z;
        a2.c.setPlacementType(TapjoyConstants.TJC_SDK_PLACEMENT);
        a2.setContext(context);
        return new TJPlacement(a2, tJPlacementListener);
    }

    public static TJPlacement a(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = new TJPlacement(a(str, str2, str3, false, false), tJPlacementListener);
        }
        return tJPlacement;
    }

    public static TJPlacement b(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = new TJPlacement(a(str, str2, str3, false, true), tJPlacementListener);
        }
        return tJPlacement;
    }

    static TJCorePlacement a(String str) {
        TJCorePlacement tJCorePlacement;
        synchronized (a) {
            tJCorePlacement = (TJCorePlacement) a.get(str);
        }
        return tJCorePlacement;
    }

    public static void setCachedPlacementLimit(int i) {
        d = i;
    }

    public static void setPreRenderedPlacementLimit(int i) {
        e = i;
    }

    public static int getCachedPlacementLimit() {
        return d;
    }

    public static int getPreRenderedPlacementLimit() {
        return e;
    }

    public static int getCachedPlacementCount() {
        return b;
    }

    public static int getPreRenderedPlacementCount() {
        return c;
    }

    public static boolean canCachePlacement() {
        return getCachedPlacementCount() < getCachedPlacementLimit();
    }

    public static boolean canPreRenderPlacement() {
        return getPreRenderedPlacementCount() < getPreRenderedPlacementLimit();
    }

    public static void incrementPlacementCacheCount() {
        int i = b + 1;
        b = i;
        if (i > d) {
            b = d;
        }
        printPlacementCacheInformation();
    }

    public static void decrementPlacementCacheCount() {
        int i = b - 1;
        b = i;
        if (i < 0) {
            b = 0;
        }
        printPlacementCacheInformation();
    }

    public static void incrementPlacementPreRenderCount() {
        int i = c + 1;
        c = i;
        if (i > e) {
            c = e;
        }
    }

    public static void decrementPlacementPreRenderCount() {
        int i = c - 1;
        c = i;
        if (i < 0) {
            c = 0;
        }
    }

    public static void printPlacementCacheInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available in placement cache: " + b + " out of " + d);
    }

    public static void printPlacementPreRenderInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available for placement pre-render: " + c + " out of " + e);
    }

    public static void dismissContentShowing(boolean z) {
        if (z) {
            TJAdUnitActivity.a();
        }
        hi.a();
        gz.a();
    }

    static TJCorePlacement a(String str, String str2, String str3, boolean z, boolean z2) {
        TJCorePlacement a2;
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "!SYSTEM!" : "");
        sb.append(!ju.c(str) ? str : "");
        if (ju.c(str2)) {
            str2 = "";
        }
        sb.append(str2);
        if (ju.c(str3)) {
            str3 = "";
        }
        sb.append(str3);
        sb.append(Boolean.toString(z2));
        String sb2 = sb.toString();
        TapjoyLog.d("TJPlacementManager", "TJCorePlacement key=" + sb2);
        synchronized (a) {
            a2 = a(sb2);
            if (a2 == null) {
                a2 = new TJCorePlacement(str, sb2, z2);
                a.put(sb2, a2);
                TapjoyLog.d("TJPlacementManager", "Created TJCorePlacement with GUID: " + a2.d);
            }
        }
        return a2;
    }
}
