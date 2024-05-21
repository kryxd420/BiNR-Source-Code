package com.moat.analytics.mobile.tjy;

import android.util.Log;
import java.util.Iterator;

class au implements ax {
    final /* synthetic */ as a;

    au(as asVar) {
        this.a = asVar;
    }

    public void a(ar arVar) {
        if (as.c != arVar) {
            synchronized (as.b) {
                if (arVar == ar.ON && as.d) {
                    Log.d("MoatOnOff", "Moat enabled - Version 1.7.10");
                }
                ar unused = as.c = arVar;
                Iterator it = as.b.iterator();
                while (it.hasNext()) {
                    aq aqVar = (aq) it.next();
                    if (arVar == ar.ON) {
                        aqVar.a();
                    } else {
                        aqVar.b();
                    }
                    it.remove();
                }
            }
        }
        this.a.g();
    }
}
