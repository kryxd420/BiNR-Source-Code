package com.tapjoy.internal;

import android.graphics.Point;
import android.os.SystemClock;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public final class hy extends hu {
    public static final bl n = new bl() {
        public final /* synthetic */ Object a(bq bqVar) {
            return new hy(bqVar);
        }
    };
    @Nullable
    public ib a;
    @Nullable
    public ib b;
    public ib c;
    @Nullable
    public Point d;
    @Nullable
    public ib e;
    @Nullable
    public ib f;
    public String g;
    @Nullable
    public gp h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public Map k;
    public long l;
    @Nullable
    public hz m;

    public hy() {
    }

    hy(bq bqVar) {
        bqVar.h();
        String str = null;
        String str2 = null;
        while (bqVar.j()) {
            String l2 = bqVar.l();
            if ("frame".equals(l2)) {
                bqVar.h();
                while (bqVar.j()) {
                    String l3 = bqVar.l();
                    if ("portrait".equals(l3)) {
                        this.a = (ib) ib.e.a(bqVar);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(l3)) {
                        this.b = (ib) ib.e.a(bqVar);
                    } else if ("close_button".equals(l3)) {
                        this.c = (ib) ib.e.a(bqVar);
                    } else if ("close_button_offset".equals(l3)) {
                        this.d = (Point) bm.a.a(bqVar);
                    } else {
                        bqVar.s();
                    }
                }
                bqVar.i();
            } else if ("creative".equals(l2)) {
                bqVar.h();
                while (bqVar.j()) {
                    String l4 = bqVar.l();
                    if ("portrait".equals(l4)) {
                        this.e = (ib) ib.e.a(bqVar);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(l4)) {
                        this.f = (ib) ib.e.a(bqVar);
                    } else {
                        bqVar.s();
                    }
                }
                bqVar.i();
            } else if ("url".equals(l2)) {
                this.g = bqVar.b();
            } else if (hs.a(l2)) {
                this.h = hs.a(l2, bqVar);
            } else if ("mappings".equals(l2)) {
                bqVar.h();
                while (bqVar.j()) {
                    String l5 = bqVar.l();
                    if ("portrait".equals(l5)) {
                        bqVar.a((List) this.i, hw.h);
                    } else if (TJAdUnitConstants.String.LANDSCAPE.equals(l5)) {
                        bqVar.a((List) this.j, hw.h);
                    } else {
                        bqVar.s();
                    }
                }
                bqVar.i();
            } else if ("meta".equals(l2)) {
                this.k = bqVar.d();
            } else if ("ttl".equals(l2)) {
                this.l = SystemClock.elapsedRealtime() + ((long) (bqVar.p() * 1000.0d));
            } else if ("no_more_today".equals(l2)) {
                this.m = (hz) hz.d.a(bqVar);
            } else if ("ad_content".equals(l2)) {
                str2 = bqVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l2)) {
                str = bqVar.b();
            } else {
                bqVar.s();
            }
        }
        bqVar.i();
        if (this.g == null) {
            this.g = "";
        }
        if (this.i != null) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                hw hwVar = (hw) it.next();
                if (hwVar.f == null) {
                    hwVar.f = str2;
                }
                if (hwVar.e == null) {
                    hwVar.e = str;
                }
            }
        }
        if (this.j != null) {
            Iterator it2 = this.j.iterator();
            while (it2.hasNext()) {
                hw hwVar2 = (hw) it2.next();
                if (hwVar2.f == null) {
                    hwVar2.f = str2;
                }
                if (hwVar2.e == null) {
                    hwVar2.e = str;
                }
            }
        }
    }

    public final boolean a() {
        return (this.c == null || this.a == null || this.e == null) ? false : true;
    }

    public final boolean b() {
        return (this.c == null || this.b == null || this.f == null) ? false : true;
    }
}
