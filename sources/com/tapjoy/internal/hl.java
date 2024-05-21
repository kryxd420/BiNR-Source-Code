package com.tapjoy.internal;

import com.tapjoy.internal.in;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class hl implements ci {
    public final hd a;
    Set b = null;
    private final Map c = Collections.synchronizedMap(new HashMap());
    private final Map d = jy.a();

    public hl(hd hdVar) {
        this.a = hdVar;
    }

    /* access modifiers changed from: private */
    public void a(cd cdVar, in.a aVar) {
        if (cdVar instanceof in) {
            if (aVar.b != null) {
                List list = aVar.b;
                synchronized (this) {
                    this.b = list instanceof Collection ? new HashSet(jw.a(list)) : jz.a(list.iterator());
                }
            }
            in inVar = (in) cdVar;
            String str = inVar.c;
            boolean z = inVar.d;
            this.d.remove(str);
            if (!z) {
                this.c.put(str, aVar.a);
            }
            hk hkVar = aVar.a;
            he heVar = this.a.p;
            if (hkVar instanceof hj) {
                ha.a("No content for \"{}\"", str);
                heVar.a(str);
                return;
            }
            ha.a("New content for \"{}\" is ready", str);
            if (z) {
                hkVar.a(heVar, new ga());
            } else {
                heVar.b(str);
            }
        } else {
            throw new IllegalStateException(cdVar.getClass().getName());
        }
    }

    public final void a(cd cdVar) {
        a(cdVar, new in.a(new hj(), (List) null));
    }
}
