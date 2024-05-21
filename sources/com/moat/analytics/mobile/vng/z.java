package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class z {
    String a = "{}";
    private a b = new a();
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i = new HashMap();

    private static class a {
        Rect a = new Rect(0, 0, 0, 0);
        double b = 0.0d;
        double c = 0.0d;

        a() {
        }
    }

    z() {
    }

    @VisibleForTesting
    static int a(Rect rect, Set<Rect> set) {
        int i2 = 0;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList<Rect> arrayList = new ArrayList<>();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() {
            /* renamed from: a */
            public int compare(Rect rect, Rect rect2) {
                return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i3 = 0;
        while (i2 < arrayList2.size() - 1) {
            int i4 = i2 + 1;
            if (!((Integer) arrayList2.get(i2)).equals(arrayList2.get(i4))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i2)).intValue(), rect.top, ((Integer) arrayList2.get(i4)).intValue(), rect.bottom);
                int i5 = rect.top;
                for (Rect rect4 : arrayList) {
                    if (Rect.intersects(rect4, rect3)) {
                        if (rect4.bottom > i5) {
                            i3 += rect3.width() * (rect4.bottom - Math.max(i5, rect4.top));
                            i5 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i2 = i4;
        }
        return i3;
    }

    private static Rect a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect a(View view) {
        return view != null ? h(view) : new Rect(0, 0, 0, 0);
    }

    private static a a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        a aVar = new a();
        int b2 = b(rect);
        if (view != null && z && z2 && !z3 && b2 > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (view.getGlobalVisibleRect(rect2)) {
                int b3 = b(rect2);
                if (b3 < b2) {
                    p.b(2, "VisibilityInfo", (Object) null, "Ad is clipped");
                }
                HashSet hashSet = new HashSet();
                if (view.getRootView() instanceof ViewGroup) {
                    aVar.a = rect2;
                    boolean a2 = a(rect2, view, hashSet);
                    if (a2) {
                        aVar.c = 1.0d;
                    }
                    if (!a2) {
                        int a3 = a(rect2, (Set<Rect>) hashSet);
                        if (a3 > 0) {
                            double d2 = (double) a3;
                            double d3 = (double) b3;
                            Double.isNaN(d3);
                            Double.isNaN(d2);
                            aVar.c = d2 / (d3 * 1.0d);
                        }
                        double d4 = (double) (b3 - a3);
                        double d5 = (double) b2;
                        Double.isNaN(d5);
                        Double.isNaN(d4);
                        aVar.b = d4 / (d5 * 1.0d);
                    }
                }
            }
        }
        return aVar;
    }

    private static Map<String, String> a(Rect rect) {
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_X, String.valueOf(rect.left));
        hashMap.put(AvidJSONUtil.KEY_Y, String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Map<String, String> a(Rect rect, DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }

    private static JSONObject a(Location location) {
        Map<String, String> b2 = b(location);
        if (b2 == null) {
            return null;
        }
        return new JSONObject(b2);
    }

    private static boolean a(Rect rect, View view, Set<Rect> set) {
        View rootView = view.getRootView();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(rootView);
        p.b(2, "VisibilityInfo", view, "starting covering rect search");
        int i2 = 0;
        boolean z = false;
        while (!arrayDeque.isEmpty() && i2 < 250) {
            i2++;
            View view2 = (View) arrayDeque.pollLast();
            if (view2.equals(view)) {
                p.b(2, "VisibilityInfo", rect, "found target");
                z = true;
            } else if (g(view2)) {
                if ((view2 instanceof ViewGroup) && !(view2 instanceof ListView)) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        arrayDeque.add(viewGroup.getChildAt(childCount));
                    }
                }
                if (Build.VERSION.SDK_INT < 21 ? z : !(view2.getElevation() <= view.getElevation() && (!z || view2.getElevation() != view.getElevation()))) {
                    Rect h2 = h(view2);
                    if (h2.setIntersect(rect, h2)) {
                        p.b(2, "VisibilityInfo", view2, "Covered by " + view2.getClass().getSimpleName() + "-" + h2.toString());
                        set.add(h2);
                        if (h2.contains(rect)) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private static int b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect b(Rect rect, DisplayMetrics displayMetrics) {
        float f2 = displayMetrics.density;
        if (f2 == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f2), Math.round(((float) rect.top) / f2), Math.round(((float) rect.right) / f2), Math.round(((float) rect.bottom) / f2));
    }

    private static Map<String, String> b(Location location) {
        if (location == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", Double.toString(location.getLatitude()));
        hashMap.put("longitude", Double.toString(location.getLongitude()));
        hashMap.put("timestamp", Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }

    private static boolean b(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean c(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean d(View view) {
        return view == null || !view.isShown();
    }

    private static float e(View view) {
        if (view == null) {
            return 0.0f;
        }
        return f(view);
    }

    private static float f(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean g(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static Rect h(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3);
    }

    private static DisplayMetrics i(View view) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 17 || a.a == null || (activity = (Activity) a.a.get()) == null) {
            return view.getContext().getResources().getDisplayMetrics();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0102 A[Catch:{ Exception -> 0x0150 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0107 A[Catch:{ Exception -> 0x0150 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x014d A[SYNTHETIC, Splitter:B:41:0x014d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r12, android.view.View r13) {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "{}"
            if (r13 == 0) goto L_0x0156
            android.util.DisplayMetrics r2 = i(r13)     // Catch:{ Exception -> 0x0150 }
            boolean r3 = b((android.view.View) r13)     // Catch:{ Exception -> 0x0150 }
            boolean r4 = c(r13)     // Catch:{ Exception -> 0x0150 }
            boolean r5 = d(r13)     // Catch:{ Exception -> 0x0150 }
            float r6 = e(r13)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r7 = "dr"
            float r8 = r2.density     // Catch:{ Exception -> 0x0150 }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ Exception -> 0x0150 }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r7 = "dv"
            double r8 = com.moat.analytics.mobile.vng.s.a()     // Catch:{ Exception -> 0x0150 }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ Exception -> 0x0150 }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r7 = "adKey"
            r0.put(r7, r12)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r12 = "isAttached"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0150 }
            r0.put(r12, r7)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r12 = "inFocus"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0150 }
            r0.put(r12, r7)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r12 = "isHidden"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0150 }
            r0.put(r12, r7)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r12 = "opacity"
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0150 }
            r0.put(r12, r6)     // Catch:{ Exception -> 0x0150 }
            android.graphics.Rect r12 = a((android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x0150 }
            android.graphics.Rect r6 = a((android.view.View) r13)     // Catch:{ Exception -> 0x0150 }
            com.moat.analytics.mobile.vng.z$a r13 = a(r13, r6, r3, r4, r5)     // Catch:{ Exception -> 0x0150 }
            org.json.JSONObject r3 = r11.c     // Catch:{ Exception -> 0x0150 }
            r4 = 1
            if (r3 == 0) goto L_0x0092
            double r7 = r13.b     // Catch:{ Exception -> 0x0150 }
            com.moat.analytics.mobile.vng.z$a r3 = r11.b     // Catch:{ Exception -> 0x0150 }
            double r9 = r3.b     // Catch:{ Exception -> 0x0150 }
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x0092
            android.graphics.Rect r3 = r13.a     // Catch:{ Exception -> 0x0150 }
            com.moat.analytics.mobile.vng.z$a r5 = r11.b     // Catch:{ Exception -> 0x0150 }
            android.graphics.Rect r5 = r5.a     // Catch:{ Exception -> 0x0150 }
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x0150 }
            if (r3 == 0) goto L_0x0092
            double r7 = r13.c     // Catch:{ Exception -> 0x0150 }
            com.moat.analytics.mobile.vng.z$a r3 = r11.b     // Catch:{ Exception -> 0x0150 }
            double r9 = r3.c     // Catch:{ Exception -> 0x0150 }
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r3 = 0
            goto L_0x00a4
        L_0x0092:
            r11.b = r13     // Catch:{ Exception -> 0x0150 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0150 }
            com.moat.analytics.mobile.vng.z$a r5 = r11.b     // Catch:{ Exception -> 0x0150 }
            android.graphics.Rect r5 = r5.a     // Catch:{ Exception -> 0x0150 }
            java.util.Map r5 = a((android.graphics.Rect) r5, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x0150 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0150 }
            r11.c = r3     // Catch:{ Exception -> 0x0150 }
            r3 = 1
        L_0x00a4:
            java.lang.String r5 = "coveredPercent"
            double r7 = r13.c     // Catch:{ Exception -> 0x0150 }
            java.lang.Double r13 = java.lang.Double.valueOf(r7)     // Catch:{ Exception -> 0x0150 }
            r0.put(r5, r13)     // Catch:{ Exception -> 0x0150 }
            org.json.JSONObject r13 = r11.g     // Catch:{ Exception -> 0x0150 }
            if (r13 == 0) goto L_0x00bb
            android.graphics.Rect r13 = r11.e     // Catch:{ Exception -> 0x0150 }
            boolean r13 = r12.equals(r13)     // Catch:{ Exception -> 0x0150 }
            if (r13 != 0) goto L_0x00c9
        L_0x00bb:
            r11.e = r12     // Catch:{ Exception -> 0x0150 }
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Exception -> 0x0150 }
            java.util.Map r12 = a((android.graphics.Rect) r12, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x0150 }
            r13.<init>(r12)     // Catch:{ Exception -> 0x0150 }
            r11.g = r13     // Catch:{ Exception -> 0x0150 }
            r3 = 1
        L_0x00c9:
            org.json.JSONObject r12 = r11.f     // Catch:{ Exception -> 0x0150 }
            if (r12 == 0) goto L_0x00d5
            android.graphics.Rect r12 = r11.d     // Catch:{ Exception -> 0x0150 }
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x0150 }
            if (r12 != 0) goto L_0x00e3
        L_0x00d5:
            r11.d = r6     // Catch:{ Exception -> 0x0150 }
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x0150 }
            java.util.Map r13 = a((android.graphics.Rect) r6, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x0150 }
            r12.<init>(r13)     // Catch:{ Exception -> 0x0150 }
            r11.f = r12     // Catch:{ Exception -> 0x0150 }
            r3 = 1
        L_0x00e3:
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.i     // Catch:{ Exception -> 0x0150 }
            if (r12 == 0) goto L_0x00ef
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.i     // Catch:{ Exception -> 0x0150 }
            boolean r12 = r0.equals(r12)     // Catch:{ Exception -> 0x0150 }
            if (r12 != 0) goto L_0x00f2
        L_0x00ef:
            r11.i = r0     // Catch:{ Exception -> 0x0150 }
            r3 = 1
        L_0x00f2:
            com.moat.analytics.mobile.vng.o r12 = com.moat.analytics.mobile.vng.o.a()     // Catch:{ Exception -> 0x0150 }
            android.location.Location r12 = r12.b()     // Catch:{ Exception -> 0x0150 }
            android.location.Location r13 = r11.h     // Catch:{ Exception -> 0x0150 }
            boolean r13 = com.moat.analytics.mobile.vng.o.a((android.location.Location) r12, (android.location.Location) r13)     // Catch:{ Exception -> 0x0150 }
            if (r13 != 0) goto L_0x0105
            r11.h = r12     // Catch:{ Exception -> 0x0150 }
            r3 = 1
        L_0x0105:
            if (r3 == 0) goto L_0x014d
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Exception -> 0x0150 }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.i     // Catch:{ Exception -> 0x0150 }
            r13.<init>(r0)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r0 = "screen"
            org.json.JSONObject r2 = r11.g     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r2)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r0 = "view"
            org.json.JSONObject r2 = r11.f     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r2)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r0 = "visible"
            org.json.JSONObject r2 = r11.c     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r2)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r0 = "maybe"
            org.json.JSONObject r2 = r11.c     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r2)     // Catch:{ Exception -> 0x0150 }
            java.lang.String r0 = "visiblePercent"
            com.moat.analytics.mobile.vng.z$a r2 = r11.b     // Catch:{ Exception -> 0x0150 }
            double r2 = r2.b     // Catch:{ Exception -> 0x0150 }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r2)     // Catch:{ Exception -> 0x0150 }
            if (r12 == 0) goto L_0x0142
            java.lang.String r0 = "location"
            org.json.JSONObject r12 = a((android.location.Location) r12)     // Catch:{ Exception -> 0x0150 }
            r13.accumulate(r0, r12)     // Catch:{ Exception -> 0x0150 }
        L_0x0142:
            java.lang.String r12 = r13.toString()     // Catch:{ Exception -> 0x0150 }
            r11.a = r12     // Catch:{ Exception -> 0x0149 }
            goto L_0x0156
        L_0x0149:
            r13 = move-exception
            r1 = r12
            r12 = r13
            goto L_0x0151
        L_0x014d:
            java.lang.String r12 = r11.a     // Catch:{ Exception -> 0x0150 }
            goto L_0x0156
        L_0x0150:
            r12 = move-exception
        L_0x0151:
            com.moat.analytics.mobile.vng.m.a(r12)
            r11.a = r1
        L_0x0156:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.vng.z.a(java.lang.String, android.view.View):void");
    }
}
