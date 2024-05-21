package com.tapjoy.internal;

import android.text.TextUtils;
import com.tapjoy.internal.dw;
import com.tapjoy.internal.ec;
import java.util.Collections;
import java.util.HashSet;
import org.json.JSONObject;

public final class eg extends eb {
    public eg(ec.b bVar, HashSet hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void onPostExecute(String str) {
        dg a;
        if (!TextUtils.isEmpty(str) && (a = dg.a()) != null) {
            for (dc dcVar : Collections.unmodifiableCollection(a.a)) {
                if (this.a.contains(dcVar.f)) {
                    dw dwVar = dcVar.c;
                    if (this.c > dwVar.e) {
                        dwVar.d = dw.a.b;
                        dj.a().b(dwVar.c(), str);
                    }
                }
            }
        }
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (dp.b(this.b, this.e.a())) {
            return null;
        }
        this.e.a(this.b);
        return this.b.toString();
    }
}
