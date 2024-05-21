package com.tapjoy.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class ea {
    final HashMap a = new HashMap();
    final HashMap b = new HashMap();
    final HashSet c = new HashSet();
    final HashSet d = new HashSet();
    final HashSet e = new HashSet();
    boolean f;

    /* access modifiers changed from: package-private */
    public final void a(dc dcVar) {
        for (du duVar : dcVar.b) {
            View view = (View) duVar.get();
            if (view != null) {
                ArrayList arrayList = (ArrayList) this.b.get(view);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.b.put(view, arrayList);
                }
                arrayList.add(dcVar.f);
            }
        }
    }
}
