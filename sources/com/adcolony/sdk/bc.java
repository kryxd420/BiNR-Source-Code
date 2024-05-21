package com.adcolony.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

class bc {
    private static int a;
    private static int b;
    private static int c;
    private static int d;

    bc() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x0136 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0137  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float a(android.view.View r10, android.content.Context r11, boolean r12, boolean r13, boolean r14) {
        /*
            r0 = 0
            if (r10 == 0) goto L_0x0140
            if (r11 != 0) goto L_0x0007
            goto L_0x0140
        L_0x0007:
            int r1 = r10.getVisibility()
            if (r1 != 0) goto L_0x013f
            float r1 = a((android.view.View) r10)
            int r1 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r1 != 0) goto L_0x0017
            goto L_0x013f
        L_0x0017:
            if (r12 != 0) goto L_0x001a
            return r0
        L_0x001a:
            if (r14 == 0) goto L_0x002a
            boolean r12 = r11 instanceof android.app.Activity
            if (r12 == 0) goto L_0x002a
            r12 = r11
            android.app.Activity r12 = (android.app.Activity) r12
            boolean r12 = r12.hasWindowFocus()
            if (r12 != 0) goto L_0x002a
            return r0
        L_0x002a:
            int r12 = r10.getHeight()
            r14 = 1120403456(0x42c80000, float:100.0)
            r1 = 2
            r2 = 0
            r3 = 1
            if (r12 <= 0) goto L_0x00bf
            int r12 = r10.getWidth()
            if (r12 <= 0) goto L_0x00bf
            int r12 = r10.getHeight()
            int r4 = r10.getWidth()
            int r12 = r12 * r4
            float r12 = (float) r12
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            boolean r5 = r10.getGlobalVisibleRect(r4)
            int[] r6 = new int[r1]
            r6 = {-1, -1} // fill-array
            r10.getLocationInWindow(r6)
            int[] r7 = new int[r1]
            r7 = {-1, -1} // fill-array
            r8 = r6[r2]
            int r9 = r10.getWidth()
            int r8 = r8 + r9
            r7[r2] = r8
            r8 = r6[r3]
            int r9 = r10.getHeight()
            int r8 = r8 + r9
            r7[r3] = r8
            int r8 = a((android.content.Context) r11)
            int r11 = b((android.content.Context) r11)
            r9 = r7[r2]
            if (r9 < 0) goto L_0x00be
            r7 = r7[r3]
            if (r7 < 0) goto L_0x00be
            r7 = r6[r2]
            if (r7 > r11) goto L_0x00be
            r11 = r6[r3]
            if (r11 > r8) goto L_0x00be
            int r11 = r4.top
            if (r11 != 0) goto L_0x0090
            r11 = r6[r3]
            int r8 = r8 / r1
            if (r11 <= r8) goto L_0x0090
            goto L_0x00be
        L_0x0090:
            if (r5 == 0) goto L_0x013e
            int r11 = r4.height()
            int r1 = r4.width()
            int r11 = r11 * r1
            float r11 = (float) r11
            int r1 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x013e
            if (r13 == 0) goto L_0x00b0
            float r10 = a(r10, r4, r11, r2)     // Catch:{ Exception -> 0x00b0 }
            int r13 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r13 <= 0) goto L_0x00b0
            int r13 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x00b0
            float r11 = r11 - r10
        L_0x00b0:
            float r11 = r11 / r12
            float r11 = r11 * r14
            int r10 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r10 >= 0) goto L_0x00b8
            return r0
        L_0x00b8:
            int r10 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x00bd
            return r14
        L_0x00bd:
            return r11
        L_0x00be:
            return r0
        L_0x00bf:
            android.view.ViewGroup$LayoutParams r12 = r10.getLayoutParams()
            int r12 = r12.height
            r4 = -2
            if (r12 != r4) goto L_0x013e
            int[] r12 = new int[r1]
            r12 = {-1, -1} // fill-array
            r10.getLocationInWindow(r12)
            int[] r4 = new int[r1]
            r4 = {-1, -1} // fill-array
            r5 = r12[r2]
            int r6 = r10.getWidth()
            int r5 = r5 + r6
            r4[r2] = r5
            r5 = r12[r3]
            int r5 = r5 + r3
            r4[r3] = r5
            android.graphics.Rect r5 = new android.graphics.Rect
            r6 = r12[r2]
            r7 = r12[r3]
            r8 = r4[r2]
            r9 = r4[r3]
            r5.<init>(r6, r7, r8, r9)
            int r6 = a((android.content.Context) r11)
            int r11 = b((android.content.Context) r11)
            r7 = r4[r2]
            if (r7 < 0) goto L_0x013d
            r4 = r4[r3]
            if (r4 < 0) goto L_0x013d
            r2 = r12[r2]
            if (r2 > r11) goto L_0x013d
            r11 = r12[r3]
            if (r11 > r6) goto L_0x013d
            int r11 = r5.top
            if (r11 != 0) goto L_0x0112
            r11 = r12[r3]
            int r6 = r6 / r1
            if (r11 <= r6) goto L_0x0112
            goto L_0x013d
        L_0x0112:
            int r11 = r5.height()
            int r12 = r5.width()
            int r11 = r11 * r12
            float r11 = (float) r11
            if (r13 == 0) goto L_0x012e
            float r10 = a(r10, r5, r11, r3)     // Catch:{ Exception -> 0x012e }
            int r12 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r12 <= 0) goto L_0x012e
            int r12 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r12 > 0) goto L_0x012e
            float r10 = r11 - r10
            goto L_0x012f
        L_0x012e:
            r10 = r11
        L_0x012f:
            float r10 = r10 / r11
            float r10 = r10 * r14
            int r11 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r11 >= 0) goto L_0x0137
            return r0
        L_0x0137:
            int r11 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r11 <= 0) goto L_0x013c
            return r14
        L_0x013c:
            return r10
        L_0x013d:
            return r0
        L_0x013e:
            return r0
        L_0x013f:
            return r0
        L_0x0140:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.bc.a(android.view.View, android.content.Context, boolean, boolean, boolean):float");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0131, code lost:
        r11 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0071 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static float a(android.view.View r11, android.graphics.Rect r12, float r13, boolean r14) {
        /*
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.util.LinkedList r1 = new java.util.LinkedList
            r1.<init>()
            java.util.LinkedList r2 = new java.util.LinkedList
            r2.<init>()
            java.util.LinkedList r3 = new java.util.LinkedList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r2.add(r12)
            android.view.ViewParent r5 = r11.getParent()
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            android.view.View r6 = r11.getRootView()
            android.content.Context r11 = r11.getContext()     // Catch:{ Exception -> 0x0034 }
            android.app.Activity r11 = (android.app.Activity) r11     // Catch:{ Exception -> 0x0034 }
            r7 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r11 = r11.findViewById(r7)     // Catch:{ Exception -> 0x0034 }
            goto L_0x0035
        L_0x0034:
            r11 = 0
        L_0x0035:
            r7 = 0
            if (r5 == 0) goto L_0x006f
            android.view.ViewParent r8 = r5.getParent()
            if (r8 == r6) goto L_0x006f
            int r8 = r5.getVisibility()
            if (r8 != 0) goto L_0x006e
            float r8 = a((android.view.View) r5)
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x004d
            goto L_0x006e
        L_0x004d:
            if (r11 == 0) goto L_0x0064
            if (r14 == 0) goto L_0x0064
            if (r5 == r11) goto L_0x0064
            android.view.ViewGroup$LayoutParams r7 = r5.getLayoutParams()
            int r7 = r7.height
            if (r7 == 0) goto L_0x0063
            android.view.ViewGroup$LayoutParams r7 = r5.getLayoutParams()
            int r7 = r7.width
            if (r7 != 0) goto L_0x0064
        L_0x0063:
            return r13
        L_0x0064:
            r0.addFirst(r5)
            android.view.ViewParent r5 = r5.getParent()
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            goto L_0x0035
        L_0x006e:
            return r13
        L_0x006f:
            if (r5 != 0) goto L_0x0072
            return r13
        L_0x0072:
            java.util.Iterator r11 = r0.iterator()
        L_0x0076:
            boolean r0 = r11.hasNext()
            r5 = 0
            r6 = 1
            if (r0 == 0) goto L_0x00d7
            java.lang.Object r0 = r11.next()
            android.view.View r0 = (android.view.View) r0
            android.view.ViewParent r8 = r0.getParent()
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            if (r8 != 0) goto L_0x008d
            return r13
        L_0x008d:
            java.lang.String r9 = "viewpager"
            java.lang.Class r10 = r8.getClass()
            java.lang.String r10 = r10.getSimpleName()
            boolean r9 = r9.equalsIgnoreCase(r10)
            if (r9 == 0) goto L_0x009e
            goto L_0x0076
        L_0x009e:
            int r0 = r8.indexOfChild(r0)
            int r9 = r8.getChildCount()
            int r9 = r9 - r6
            if (r0 >= r9) goto L_0x0076
        L_0x00a9:
            int r0 = r0 + 1
            int r6 = r8.getChildCount()
            if (r0 >= r6) goto L_0x0076
            android.view.View r6 = r8.getChildAt(r0)
            boolean r9 = c(r6)
            if (r9 != 0) goto L_0x00cd
            int r9 = r6.getVisibility()
            if (r9 != 0) goto L_0x00a9
            float r9 = a((android.view.View) r6)
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00a9
            r3.addFirst(r6)
            goto L_0x00a9
        L_0x00cd:
            java.util.ArrayList r6 = b((android.view.View) r6)
            if (r6 == 0) goto L_0x00a9
            r3.addAll(r5, r6)
            goto L_0x00a9
        L_0x00d7:
            java.util.Iterator r11 = r3.iterator()
            r0 = 0
        L_0x00dc:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0131
            java.lang.Object r3 = r11.next()
            android.view.View r3 = (android.view.View) r3
            int r8 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r8 < 0) goto L_0x00ed
            goto L_0x0131
        L_0x00ed:
            if (r3 == 0) goto L_0x0104
            java.lang.Object r8 = r3.getTag()     // Catch:{ Exception -> 0x0104 }
            if (r8 == 0) goto L_0x0104
            java.lang.Object r8 = r3.getTag()     // Catch:{ Exception -> 0x0104 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0104 }
            java.lang.String r9 = "BTN_CLOSE"
            boolean r8 = r8.contains(r9)     // Catch:{ Exception -> 0x0104 }
            if (r8 == 0) goto L_0x0104
            goto L_0x00dc
        L_0x0104:
            android.graphics.Rect r8 = new android.graphics.Rect
            r8.<init>()
            boolean r3 = r3.getGlobalVisibleRect(r8)
            if (r3 != 0) goto L_0x0110
            goto L_0x00dc
        L_0x0110:
            if (r14 == 0) goto L_0x0117
            int r3 = r8.top
            int r3 = r3 + r6
            r8.top = r3
        L_0x0117:
            boolean r3 = r8.intersect(r12)
            if (r3 == 0) goto L_0x00dc
            r1.add(r8)
            int r0 = r8.width()
            int r3 = r8.height()
            int r0 = r0 * r3
            float r0 = (float) r0
            int r3 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x00dc
            r11 = 1
            goto L_0x0132
        L_0x0131:
            r11 = 0
        L_0x0132:
            if (r11 == 0) goto L_0x0135
            return r13
        L_0x0135:
            boolean r11 = r1.isEmpty()
            if (r11 != 0) goto L_0x01b9
            int r11 = r1.size()
            if (r11 != r6) goto L_0x0142
            return r0
        L_0x0142:
            java.util.Iterator r11 = r1.iterator()
        L_0x0146:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0190
            java.lang.Object r12 = r11.next()
            android.graphics.Rect r12 = (android.graphics.Rect) r12
            r4.clear()
            r4.addAll(r2)
            r14 = 0
        L_0x0159:
            int r1 = r4.size()
            if (r14 >= r1) goto L_0x0146
            java.lang.Object r1 = r4.get(r14)
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            boolean r3 = r12.intersect(r1)
            if (r3 == 0) goto L_0x018d
            java.lang.Object r3 = r4.get(r14)
            r2.remove(r3)
            r3 = 1
        L_0x0173:
            r8 = 9
            if (r3 >= r8) goto L_0x018d
            android.graphics.Rect r8 = a(r1, r12, r3)
            int r9 = r8.height()
            if (r9 <= 0) goto L_0x018a
            int r9 = r8.width()
            if (r9 <= 0) goto L_0x018a
            r2.add(r8)
        L_0x018a:
            int r3 = r3 + 1
            goto L_0x0173
        L_0x018d:
            int r14 = r14 + 1
            goto L_0x0159
        L_0x0190:
            boolean r11 = r2.isEmpty()
            if (r11 != 0) goto L_0x01b9
            java.util.Iterator r11 = r2.iterator()
        L_0x019a:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x01b3
            java.lang.Object r12 = r11.next()
            android.graphics.Rect r12 = (android.graphics.Rect) r12
            int r14 = r12.width()
            int r12 = r12.height()
            int r14 = r14 * r12
            float r12 = (float) r14
            float r7 = r7 + r12
            goto L_0x019a
        L_0x01b3:
            int r11 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r11 >= 0) goto L_0x01b9
            float r13 = r13 - r7
            return r13
        L_0x01b9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.bc.a(android.view.View, android.graphics.Rect, float, boolean):float");
    }

    static Rect a(Rect rect, Rect rect2, int i) {
        Rect rect3 = new Rect();
        switch (i) {
            case 1:
                rect3.set(rect.left, rect.top, rect2.left, rect2.top);
                break;
            case 2:
                rect3.set(rect2.left, rect.top, rect2.right, rect2.top);
                break;
            case 3:
                rect3.set(rect2.right, rect.top, rect.right, rect2.top);
                break;
            case 4:
                rect3.set(rect2.right, rect2.top, rect.right, rect2.bottom);
                break;
            case 5:
                rect3.set(rect2.right, rect2.bottom, rect.right, rect.bottom);
                break;
            case 6:
                rect3.set(rect2.left, rect2.bottom, rect2.right, rect.bottom);
                break;
            case 7:
                rect3.set(rect.left, rect2.bottom, rect2.left, rect.bottom);
                break;
            case 8:
                rect3.set(rect.left, rect2.top, rect2.left, rect2.bottom);
                break;
        }
        return rect3;
    }

    @SuppressLint({"NewApi"})
    static float a(View view) {
        if (view == null) {
            return 0.0f;
        }
        if (a() < 11) {
            return 1.0f;
        }
        return view.getAlpha();
    }

    private static ArrayList<View> b(View view) {
        if (!(view instanceof ViewGroup) || view.getVisibility() != 0 || a(view) == 0.0f) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        ArrayList<View> arrayList = new ArrayList<>();
        linkedList.add((ViewGroup) view);
        ListIterator listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            ViewGroup viewGroup = (ViewGroup) listIterator.next();
            listIterator.remove();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0 && a(childAt) != 0.0f) {
                    if (childAt instanceof ViewGroup) {
                        if (c(childAt)) {
                            listIterator.add((ViewGroup) childAt);
                            listIterator.previous();
                        } else {
                            arrayList.add(childAt);
                        }
                    } else if (!c(childAt)) {
                        arrayList.add(childAt);
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private static boolean c(View view) {
        if (view == null) {
            return false;
        }
        if (view.getBackground() == null || (a() > 18 && view.getBackground().getAlpha() == 0)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static int a(Context context) {
        int i;
        int i2 = context != null ? context.getResources().getConfiguration().orientation : -1;
        if (i2 == 2 && a > 0) {
            return a;
        }
        if (i2 == 1 && c > 0) {
            return c;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (a() >= 13) {
                i = a.a(windowManager);
            } else {
                i = windowManager.getDefaultDisplay().getHeight();
            }
            if (i2 == 2) {
                a = i;
            } else if (i2 == 1) {
                c = i;
            }
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    public static int b(Context context) {
        int i;
        int i2 = context != null ? context.getResources().getConfiguration().orientation : -1;
        if (i2 == 2 && b > 0) {
            return b;
        }
        if (i2 == 1 && d > 0) {
            return d;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (a() >= 13) {
                i = b.a(windowManager);
            } else {
                i = windowManager.getDefaultDisplay().getWidth();
            }
            if (i2 == 2) {
                b = i;
            } else if (i2 == 1) {
                d = i;
            }
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int a() {
        return Build.VERSION.SDK_INT;
    }

    private static class b {
        private b() {
        }

        @SuppressLint({"NewApi"})
        public static int a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        }
    }

    private static class a {
        private a() {
        }

        @SuppressLint({"NewApi"})
        public static int a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.y;
        }
    }
}
