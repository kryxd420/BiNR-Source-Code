package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class hv extends hu {
    public static final bl d = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            return new hv(bqVar);
        }
    };
    public ArrayList a = new ArrayList();
    public Map b;
    public float c;

    public hv(bq bqVar) {
        bqVar.h();
        String str = null;
        String str2 = null;
        while (bqVar.j()) {
            String l = bqVar.l();
            if ("layouts".equals(l)) {
                bqVar.a((List) this.a, ie.d);
            } else if ("meta".equals(l)) {
                this.b = bqVar.d();
            } else if ("max_show_time".equals(l)) {
                this.c = (float) bqVar.p();
            } else if ("ad_content".equals(l)) {
                str = bqVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str2 = bqVar.b();
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
        if (this.a != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ie ieVar = (ie) it.next();
                if (ieVar.c != null) {
                    Iterator it2 = ieVar.c.iterator();
                    while (it2.hasNext()) {
                        id idVar = (id) it2.next();
                        if (idVar.i == null) {
                            idVar.i = str;
                        }
                        if (idVar.h == null) {
                            idVar.h = str2;
                        }
                    }
                }
            }
        }
    }
}
