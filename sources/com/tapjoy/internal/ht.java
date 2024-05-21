package com.tapjoy.internal;

import com.applovin.sdk.AppLovinEventParameters;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ht {
    public static String a(fe feVar) {
        bk b = new bk().c().a(TapjoyConstants.TJC_SDK_PLACEMENT).b(feVar.t).a("os_name").b(feVar.k).a("os_ver").b(feVar.l).a("device_id").b(feVar.h).a("device_maker").b(feVar.i).a("device_model").b(feVar.j).a(TapjoyConstants.TJC_PACKAGE_ID).b(feVar.r).a(TapjoyConstants.TJC_PACKAGE_SIGN).b(feVar.s).a("locale").b(feVar.p).a(TapjoyConstants.TJC_DEVICE_TIMEZONE).b(feVar.q);
        if (feVar.m != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY).a((Number) feVar.m);
        }
        if (feVar.n != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH).a((Number) feVar.n);
        }
        if (feVar.o != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT).a((Number) feVar.o);
        }
        if (feVar.g != null) {
            b.a("mac").b(feVar.g);
        }
        if (feVar.u != null) {
            b.a(TapjoyConstants.TJC_DEVICE_COUNTRY_SIM).b(feVar.u);
        }
        if (feVar.v != null) {
            b.a("country_net").b(feVar.v);
        }
        if (feVar.w != null) {
            b.a("imei").b(feVar.w);
        }
        return b.d().toString();
    }

    public static String a(ey eyVar) {
        bk c = new bk().c();
        if (eyVar.e != null) {
            c.a(TapjoyConstants.TJC_PACKAGE_VERSION).b(eyVar.e);
        }
        if (eyVar.f != null) {
            c.a(TapjoyConstants.TJC_PACKAGE_REVISION).a((Number) eyVar.f);
        }
        if (eyVar.g != null) {
            c.a("data_ver").b(eyVar.g);
        }
        if (eyVar.h != null) {
            c.a(TapjoyConstants.TJC_INSTALLER).b(eyVar.h);
        }
        if (eyVar.i != null) {
            c.a(TapjoyConstants.TJC_STORE).b(eyVar.i);
        }
        return c.d().toString();
    }

    public static String a(fl flVar) {
        return a(flVar, (ez) null);
    }

    private static String a(fl flVar, ez ezVar) {
        String b;
        bk c = new bk().c();
        if (flVar.s != null) {
            c.a(TapjoyConstants.TJC_INSTALLED).a((Number) flVar.s);
        }
        if (flVar.t != null) {
            c.a(TapjoyConstants.TJC_REFERRER).b(flVar.t);
        }
        if (flVar.G != null) {
            c.a("idfa").b(flVar.G);
            if (flVar.H != null && flVar.H.booleanValue()) {
                c.a("idfa_optout").a(1);
            }
        } else if (!(ezVar == null || ezVar.r == null || !hg.a.equals(ezVar.r) || (b = hr.b()) == null)) {
            c.a("idfa").b(b);
            if (hr.c()) {
                c.a("idfa_optout").a(1);
            }
        }
        if (flVar.u != null) {
            c.a(TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY).a((long) Math.max(flVar.u.intValue(), 1));
        }
        if (flVar.v != null) {
            c.a(TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY).a((long) Math.max(flVar.v.intValue(), 1));
        }
        if (flVar.w.size() > 0) {
            ArrayList arrayList = new ArrayList(flVar.w.size());
            for (fi fiVar : flVar.w) {
                if (fiVar.h != null) {
                    arrayList.add(fiVar.f);
                }
            }
            if (!arrayList.isEmpty()) {
                c.a("push").a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    c.b((String) it.next());
                }
                c.b();
            }
        }
        c.a("session").c();
        if (flVar.x != null) {
            c.a("total_count").a((Number) flVar.x);
        }
        if (flVar.y != null) {
            c.a("total_length").a((Number) flVar.y);
        }
        if (flVar.z != null) {
            c.a("last_at").a((Number) flVar.z);
        }
        if (flVar.A != null) {
            c.a("last_length").a((Number) flVar.A);
        }
        c.d();
        c.a("purchase").c();
        if (flVar.B != null) {
            c.a("currency").b(flVar.B);
        }
        if (flVar.C != null) {
            c.a("total_count").a((Number) flVar.C);
        }
        if (flVar.D != null) {
            c.a("total_price").a((Number) flVar.D);
        }
        if (flVar.E != null) {
            c.a("last_at").a((Number) flVar.E);
        }
        if (flVar.F != null) {
            c.a("last_price").a((Number) flVar.F);
        }
        c.d();
        if (flVar.I != null) {
            c.a("user_id").b(flVar.I);
        }
        if (flVar.J != null) {
            c.a(TapjoyConstants.TJC_USER_LEVEL).a((Number) flVar.J);
        }
        if (flVar.K != null) {
            c.a(TapjoyConstants.TJC_USER_FRIEND_COUNT).a((Number) flVar.K);
        }
        if (flVar.L != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_1).b(flVar.L);
        }
        if (flVar.M != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_2).b(flVar.M);
        }
        if (flVar.N != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_3).b(flVar.N);
        }
        if (flVar.O != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_4).b(flVar.O);
        }
        if (flVar.P != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_5).b(flVar.P);
        }
        if (flVar.Q.size() > 0) {
            c.a("tags").a((Collection) flVar.Q);
        }
        if (Boolean.TRUE.equals(flVar.R)) {
            c.a("push_optout").a(1);
        }
        return c.d().toString();
    }

    private static String a(ez ezVar, boolean z, boolean z2, boolean z3) {
        bk b = new bk().c().a("type").b(a(ezVar.n)).a("name").b(ezVar.o);
        b.a("time");
        if (ezVar.q != null) {
            b.a((Number) ezVar.p);
            b.a("systime").a((Number) ezVar.q);
        } else if (!w.c() || ezVar.r == null || ezVar.s == null || !hg.a.equals(ezVar.r)) {
            b.a((Number) ezVar.p);
        } else {
            b.a(w.a(ezVar.s.longValue()));
            b.a("systime").a((Number) ezVar.p);
        }
        if (ezVar.t != null) {
            b.a("duration").a((Number) ezVar.t);
        }
        if (!z && ezVar.u != null) {
            b.a(TJAdUnitConstants.String.VIDEO_INFO).a((bo) new bp(a(ezVar.u)));
        }
        if (!z2 && ezVar.v != null) {
            b.a(TapjoyConstants.TJC_APP_PLACEMENT).a((bo) new bp(a(ezVar.v)));
        }
        if (!z3 && ezVar.w != null) {
            b.a("user").a((bo) new bp(a(ezVar.w, ezVar)));
        }
        if (ezVar.y != null) {
            b.a("event_seq").a((Number) ezVar.y);
        }
        if (ezVar.z != null) {
            bk a = b.a("event_prev");
            fb fbVar = ezVar.z;
            bk b2 = new bk().c().a("type").b(a(fbVar.e)).a("name").b(fbVar.f);
            if (fbVar.g != null) {
                b2.a("category").b(fbVar.g);
            }
            a.a((bo) new bp(b2.d().toString()));
        }
        if (ezVar.A != null) {
            bk a2 = b.a("purchase");
            fh fhVar = ezVar.A;
            bk b3 = new bk().c().a("product_id").b(fhVar.h);
            if (fhVar.i != null) {
                b3.a("product_quantity").a((Number) fhVar.i);
            }
            if (fhVar.j != null) {
                b3.a("product_price").a((Number) fhVar.j);
            }
            if (fhVar.k != null) {
                b3.a("product_price_currency").b(fhVar.k);
            }
            if (fhVar.s != null) {
                b3.a("currency_price").b(fhVar.s);
            }
            if (fhVar.l != null) {
                b3.a("product_type").b(fhVar.l);
            }
            if (fhVar.m != null) {
                b3.a("product_title").b(fhVar.m);
            }
            if (fhVar.n != null) {
                b3.a("product_description").b(fhVar.n);
            }
            if (fhVar.o != null) {
                b3.a(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER).b(fhVar.o);
            }
            if (fhVar.p != null) {
                b3.a("transaction_state").a((Number) fhVar.p);
            }
            if (fhVar.q != null) {
                b3.a("transaction_date").a((Number) fhVar.q);
            }
            if (fhVar.r != null) {
                b3.a("campaign_id").b(fhVar.r);
            }
            if (fhVar.t != null) {
                b3.a("receipt").b(fhVar.t);
            }
            if (fhVar.u != null) {
                b3.a(InAppPurchaseMetaData.KEY_SIGNATURE).b(fhVar.u);
            }
            a2.a((bo) new bp(b3.d().toString()));
        }
        if (ezVar.B != null) {
            b.a("exception").b(ezVar.B);
        }
        try {
            if (ezVar.D != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (ezVar.C != null) {
                    bq.b(ezVar.C).a((Map) linkedHashMap);
                }
                fg fgVar = ezVar.D;
                if (fgVar.d != null) {
                    linkedHashMap.put("fq7_change", fgVar.d);
                }
                if (fgVar.e != null) {
                    linkedHashMap.put("fq30_change", fgVar.e);
                }
                if (fgVar.f != null) {
                    linkedHashMap.put(TJAdUnitConstants.PARAM_PUSH_ID, fgVar.f);
                }
                b.a("meta").a((Map) linkedHashMap);
            } else if (ezVar.C != null) {
                b.a("meta").a((bo) new bp(ezVar.C));
            }
        } catch (IOException unused) {
        }
        if (ezVar.I != null) {
            b.a(TJAdUnitConstants.String.USAGE_TRACKER_DIMENSIONS).a((bo) new bp(ezVar.I));
        }
        if (ezVar.J != null) {
            b.a("count").a((Number) ezVar.J);
        }
        if (ezVar.K != null) {
            b.a("first_time").a((Number) ezVar.K);
        }
        if (ezVar.L != null) {
            b.a("last_time").a((Number) ezVar.L);
        }
        if (ezVar.E != null) {
            b.a("category").b(ezVar.E);
        }
        if (ezVar.F != null) {
            b.a("p1").b(ezVar.F);
        }
        if (ezVar.G != null) {
            b.a("p2").b(ezVar.G);
        }
        if (ezVar.H.size() > 0) {
            b.a(TJAdUnitConstants.String.USAGE_TRACKER_VALUES).c();
            for (fd fdVar : ezVar.H) {
                b.a(fdVar.e).a((Number) fdVar.f);
            }
            b.d();
        }
        return b.d().toString();
    }

    public static String a(fa faVar) {
        fe feVar;
        boolean z;
        ey eyVar;
        boolean z2;
        bk a = new bk().a();
        fe feVar2 = null;
        ey eyVar2 = null;
        fl flVar = null;
        for (ez ezVar : faVar.d) {
            boolean z3 = true;
            if (feVar2 == null || !feVar2.equals(ezVar.u)) {
                feVar = ezVar.u;
                z = false;
            } else {
                feVar = feVar2;
                z = true;
            }
            if (eyVar2 == null || !eyVar2.equals(ezVar.v)) {
                eyVar = ezVar.v;
                z2 = false;
            } else {
                eyVar = eyVar2;
                z2 = true;
            }
            if (flVar == null || !flVar.equals(ezVar.w)) {
                flVar = ezVar.w;
                z3 = false;
            }
            a.a((bo) new bp(a(ezVar, z, z2, z3)));
            feVar2 = feVar;
            eyVar2 = eyVar;
        }
        return a.b().toString();
    }

    private static String a(fc fcVar) {
        switch (fcVar) {
            case APP:
                return TapjoyConstants.TJC_APP_PLACEMENT;
            case CAMPAIGN:
                return "campaign";
            case CUSTOM:
                return "custom";
            case USAGES:
                return "usages";
            default:
                throw new RuntimeException();
        }
    }
}
