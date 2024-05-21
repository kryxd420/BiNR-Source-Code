package com.integralads.avid.library.adcolony.processing;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidViewProcessor implements IAvidNodeProcessor {
    private final int[] a = new int[2];

    public JSONObject getState(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.a);
        return AvidJSONUtil.getViewState(this.a[0], this.a[1], width, height);
    }

    public void iterateChildren(View view, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || Build.VERSION.SDK_INT < 21) {
                a(viewGroup, jSONObject, iAvidViewWalker);
            } else {
                b(viewGroup, jSONObject, iAvidViewWalker);
            }
        }
    }

    private void a(ViewGroup viewGroup, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            iAvidViewWalker.walkView(viewGroup.getChildAt(i), this, jSONObject);
        }
    }

    @TargetApi(21)
    private void b(ViewGroup viewGroup, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        ArrayList arrayList2 = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                iAvidViewWalker.walkView((View) it2.next(), this, jSONObject);
            }
        }
    }
}
