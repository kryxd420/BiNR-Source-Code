package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dp {
    static float a = Resources.getSystem().getDisplayMetrics().density;
    private static WindowManager b;
    private static String[] c = {AvidJSONUtil.KEY_X, AvidJSONUtil.KEY_Y, AvidJSONUtil.KEY_WIDTH, AvidJSONUtil.KEY_HEIGHT};

    static class a {
        final float a;
        final float b;

        a(float f, float f2) {
            this.a = f;
            this.b = f2;
        }
    }

    public static void a(Context context) {
        if (context != null) {
            a = context.getResources().getDisplayMetrics().density;
            b = (WindowManager) context.getSystemService("window");
        }
    }

    public static void a(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("adSessionId", str);
        } catch (JSONException e) {
            dq.a("Error with setting ad session id", e);
        }
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            dq.a("JSONException during JSONObject.put for name [" + str + "]", e);
        }
    }

    public static void a(JSONObject jSONObject, List list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        try {
            jSONObject.put(AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR, jSONArray);
        } catch (JSONException e) {
            dq.a("Error with setting friendly obstruction", e);
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put(AvidJSONUtil.KEY_CHILD_VIEWS, optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) ? false : true;
    }

    public static JSONObject a(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AvidJSONUtil.KEY_X, (double) (((float) i) / a));
            jSONObject.put(AvidJSONUtil.KEY_Y, (double) (((float) i2) / a));
            jSONObject.put(AvidJSONUtil.KEY_WIDTH, (double) (((float) i3) / a));
            jSONObject.put(AvidJSONUtil.KEY_HEIGHT, (double) (((float) i4) / a));
        } catch (JSONException e) {
            dq.a("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject) {
        float f;
        float f2 = 0.0f;
        if (Build.VERSION.SDK_INT < 17) {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                float f3 = 0.0f;
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        double optDouble = optJSONObject.optDouble(AvidJSONUtil.KEY_X);
                        double optDouble2 = optJSONObject.optDouble(AvidJSONUtil.KEY_Y);
                        double optDouble3 = optJSONObject.optDouble(AvidJSONUtil.KEY_WIDTH);
                        double optDouble4 = optJSONObject.optDouble(AvidJSONUtil.KEY_HEIGHT);
                        f2 = Math.max(f2, (float) (optDouble + optDouble3));
                        f3 = Math.max(f3, (float) (optDouble2 + optDouble4));
                    }
                }
                f = f3;
                a aVar = new a(f2, f);
                jSONObject.put(AvidJSONUtil.KEY_WIDTH, (double) aVar.a);
                jSONObject.put(AvidJSONUtil.KEY_HEIGHT, (double) aVar.b);
            }
        } else if (b != null) {
            Point point = new Point(0, 0);
            b.getDefaultDisplay().getRealSize(point);
            f2 = ((float) point.x) / a;
            f = ((float) point.y) / a;
            a aVar2 = new a(f2, f);
            jSONObject.put(AvidJSONUtil.KEY_WIDTH, (double) aVar2.a);
            jSONObject.put(AvidJSONUtil.KEY_HEIGHT, (double) aVar2.b);
        }
        f = 0.0f;
        a aVar22 = new a(f2, f);
        try {
            jSONObject.put(AvidJSONUtil.KEY_WIDTH, (double) aVar22.a);
            jSONObject.put(AvidJSONUtil.KEY_HEIGHT, (double) aVar22.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ab A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(@android.support.annotation.NonNull org.json.JSONObject r10, @android.support.annotation.Nullable org.json.JSONObject r11) {
        /*
            r0 = 1
            if (r10 != 0) goto L_0x0006
            if (r11 != 0) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            if (r10 == 0) goto L_0x00ad
            if (r11 != 0) goto L_0x000d
            goto L_0x00ad
        L_0x000d:
            java.lang.String[] r2 = c
            int r3 = r2.length
            r4 = 0
        L_0x0011:
            if (r4 >= r3) goto L_0x0026
            r5 = r2[r4]
            double r6 = r10.optDouble(r5)
            double r8 = r11.optDouble(r5)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0023
            r2 = 0
            goto L_0x0027
        L_0x0023:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0026:
            r2 = 1
        L_0x0027:
            if (r2 == 0) goto L_0x00ac
            java.lang.String r2 = "adSessionId"
            java.lang.String r3 = ""
            java.lang.String r2 = r10.optString(r2, r3)
            java.lang.String r3 = "adSessionId"
            java.lang.String r4 = ""
            java.lang.String r3 = r11.optString(r3, r4)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00ac
            java.lang.String r2 = "isFriendlyObstructionFor"
            org.json.JSONArray r2 = r10.optJSONArray(r2)
            java.lang.String r3 = "isFriendlyObstructionFor"
            org.json.JSONArray r3 = r11.optJSONArray(r3)
            if (r2 != 0) goto L_0x004f
            if (r3 == 0) goto L_0x0074
        L_0x004f:
            boolean r4 = a((org.json.JSONArray) r2, (org.json.JSONArray) r3)
            if (r4 != 0) goto L_0x0057
        L_0x0055:
            r2 = 0
            goto L_0x0075
        L_0x0057:
            r4 = 0
        L_0x0058:
            int r5 = r2.length()
            if (r4 >= r5) goto L_0x0074
            java.lang.String r5 = ""
            java.lang.String r5 = r2.optString(r4, r5)
            java.lang.String r6 = ""
            java.lang.String r6 = r3.optString(r4, r6)
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0071
            goto L_0x0055
        L_0x0071:
            int r4 = r4 + 1
            goto L_0x0058
        L_0x0074:
            r2 = 1
        L_0x0075:
            if (r2 == 0) goto L_0x00ac
            java.lang.String r2 = "childViews"
            org.json.JSONArray r10 = r10.optJSONArray(r2)
            java.lang.String r2 = "childViews"
            org.json.JSONArray r11 = r11.optJSONArray(r2)
            if (r10 != 0) goto L_0x0087
            if (r11 == 0) goto L_0x00a8
        L_0x0087:
            boolean r2 = a((org.json.JSONArray) r10, (org.json.JSONArray) r11)
            if (r2 != 0) goto L_0x008f
        L_0x008d:
            r10 = 0
            goto L_0x00a9
        L_0x008f:
            r2 = 0
        L_0x0090:
            int r3 = r10.length()
            if (r2 >= r3) goto L_0x00a8
            org.json.JSONObject r3 = r10.optJSONObject(r2)
            org.json.JSONObject r4 = r11.optJSONObject(r2)
            boolean r3 = b(r3, r4)
            if (r3 != 0) goto L_0x00a5
            goto L_0x008d
        L_0x00a5:
            int r2 = r2 + 1
            goto L_0x0090
        L_0x00a8:
            r10 = 1
        L_0x00a9:
            if (r10 == 0) goto L_0x00ac
            return r0
        L_0x00ac:
            return r1
        L_0x00ad:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dp.b(org.json.JSONObject, org.json.JSONObject):boolean");
    }
}
