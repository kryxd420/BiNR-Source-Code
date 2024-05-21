package com.tapjoy.internal;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.tapjoy.internal.dl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.tapjoy.internal.do  reason: invalid class name */
public final class Cdo implements dl {
    private final int[] a = new int[2];

    public final JSONObject a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.a);
        return dp.a(this.a[0], this.a[1], width, height);
    }

    public final void a(View view, JSONObject jSONObject, dl.a aVar, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            if (!z || Build.VERSION.SDK_INT < 21) {
                while (i < viewGroup.getChildCount()) {
                    aVar.a(viewGroup.getChildAt(i), this, jSONObject);
                    i++;
                }
                return;
            }
            HashMap hashMap = new HashMap();
            while (i < viewGroup.getChildCount()) {
                View childAt = viewGroup.getChildAt(i);
                ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
                }
                arrayList.add(childAt);
                i++;
            }
            ArrayList arrayList2 = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList2);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
                while (it2.hasNext()) {
                    aVar.a((View) it2.next(), this, jSONObject);
                }
            }
        }
    }
}
