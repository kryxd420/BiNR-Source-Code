package com.tapjoy.internal;

import com.tapjoy.internal.dw;
import com.tapjoy.internal.ec;
import java.util.Collections;
import java.util.HashSet;
import org.json.JSONObject;

public final class ef extends eb {
    public ef(ec.b bVar, HashSet hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void onPostExecute(String str) {
        dg a = dg.a();
        if (a != null) {
            for (dc dcVar : Collections.unmodifiableCollection(a.a)) {
                if (this.a.contains(dcVar.f)) {
                    dw dwVar = dcVar.c;
                    if (this.c > dwVar.e && dwVar.d != dw.a.c) {
                        dwVar.d = dw.a.c;
                        dj.a().b(dwVar.c(), str);
                    }
                }
            }
        }
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.b.toString();
    }
}
