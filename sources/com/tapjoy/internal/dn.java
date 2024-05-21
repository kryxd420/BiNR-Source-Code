package com.tapjoy.internal;

import android.view.View;
import com.tapjoy.internal.dl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

public final class dn implements dl {
    private final dl a;

    public dn(dl dlVar) {
        this.a = dlVar;
    }

    public final JSONObject a(View view) {
        return dp.a(0, 0, 0, 0);
    }

    public final void a(View view, JSONObject jSONObject, dl.a aVar, boolean z) {
        View rootView;
        ArrayList arrayList = new ArrayList();
        dg a2 = dg.a();
        if (a2 != null) {
            Collection<dc> unmodifiableCollection = Collections.unmodifiableCollection(a2.b);
            IdentityHashMap identityHashMap = new IdentityHashMap((unmodifiableCollection.size() * 2) + 3);
            for (dc c : unmodifiableCollection) {
                View c2 = c.c();
                if (c2 != null && dt.b(c2) && (rootView = c2.getRootView()) != null && !identityHashMap.containsKey(rootView)) {
                    identityHashMap.put(rootView, rootView);
                    float a3 = dt.a(rootView);
                    int size = arrayList.size();
                    while (size > 0 && dt.a((View) arrayList.get(size - 1)) > a3) {
                        size--;
                    }
                    arrayList.add(size, rootView);
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            aVar.a((View) it.next(), this.a, jSONObject);
        }
    }
}
