package com.tapjoy.internal;

import com.tapjoy.internal.el;
import java.util.List;

public final class fl extends el {
    public static final en c = new b();
    public static final Long d = 0L;
    public static final Integer e = 0;
    public static final Integer f = 0;
    public static final Integer g = 0;
    public static final Long h = 0L;
    public static final Long i = 0L;
    public static final Long j = 0L;
    public static final Integer k = 0;
    public static final Double l = Double.valueOf(0.0d);
    public static final Long m = 0L;
    public static final Double n = Double.valueOf(0.0d);
    public static final Boolean o = false;
    public static final Integer p = 0;
    public static final Integer q = 0;
    public static final Boolean r = false;
    public final Long A;
    public final String B;
    public final Integer C;
    public final Double D;
    public final Long E;
    public final Double F;
    public final String G;
    public final Boolean H;
    public final String I;
    public final Integer J;
    public final Integer K;
    public final String L;
    public final String M;
    public final String N;
    public final String O;
    public final String P;
    public final List Q;
    public final Boolean R;
    public final Long s;
    public final String t;
    public final Integer u;
    public final Integer v;
    public final List w;
    public final Integer x;
    public final Long y;
    public final Long z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fl(Long l2, String str, Integer num, Integer num2, List list, Integer num3, Long l3, Long l4, Long l5, String str2, Integer num4, Double d2, Long l6, Double d3, String str3, Boolean bool, String str4, Integer num5, Integer num6, String str5, String str6, String str7, String str8, String str9, List list2, Boolean bool2, iy iyVar) {
        super(c, iyVar);
        this.s = l2;
        this.t = str;
        this.u = num;
        this.v = num2;
        List list3 = list;
        this.w = es.a("pushes", list);
        this.x = num3;
        this.y = l3;
        this.z = l4;
        this.A = l5;
        this.B = str2;
        this.C = num4;
        this.D = d2;
        this.E = l6;
        this.F = d3;
        this.G = str3;
        this.H = bool;
        this.I = str4;
        this.J = num5;
        this.K = num6;
        this.L = str5;
        this.M = str6;
        this.N = str7;
        this.O = str8;
        this.P = str9;
        this.Q = es.a("tags", list2);
        this.R = bool2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fl)) {
            return false;
        }
        fl flVar = (fl) obj;
        return a().equals(flVar.a()) && es.a((Object) this.s, (Object) flVar.s) && es.a((Object) this.t, (Object) flVar.t) && es.a((Object) this.u, (Object) flVar.u) && es.a((Object) this.v, (Object) flVar.v) && this.w.equals(flVar.w) && es.a((Object) this.x, (Object) flVar.x) && es.a((Object) this.y, (Object) flVar.y) && es.a((Object) this.z, (Object) flVar.z) && es.a((Object) this.A, (Object) flVar.A) && es.a((Object) this.B, (Object) flVar.B) && es.a((Object) this.C, (Object) flVar.C) && es.a((Object) this.D, (Object) flVar.D) && es.a((Object) this.E, (Object) flVar.E) && es.a((Object) this.F, (Object) flVar.F) && es.a((Object) this.G, (Object) flVar.G) && es.a((Object) this.H, (Object) flVar.H) && es.a((Object) this.I, (Object) flVar.I) && es.a((Object) this.J, (Object) flVar.J) && es.a((Object) this.K, (Object) flVar.K) && es.a((Object) this.L, (Object) flVar.L) && es.a((Object) this.M, (Object) flVar.M) && es.a((Object) this.N, (Object) flVar.N) && es.a((Object) this.O, (Object) flVar.O) && es.a((Object) this.P, (Object) flVar.P) && this.Q.equals(flVar.Q) && es.a((Object) this.R, (Object) flVar.R);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((a().hashCode() * 37) + (this.s != null ? this.s.hashCode() : 0)) * 37) + (this.t != null ? this.t.hashCode() : 0)) * 37) + (this.u != null ? this.u.hashCode() : 0)) * 37) + (this.v != null ? this.v.hashCode() : 0)) * 37) + this.w.hashCode()) * 37) + (this.x != null ? this.x.hashCode() : 0)) * 37) + (this.y != null ? this.y.hashCode() : 0)) * 37) + (this.z != null ? this.z.hashCode() : 0)) * 37) + (this.A != null ? this.A.hashCode() : 0)) * 37) + (this.B != null ? this.B.hashCode() : 0)) * 37) + (this.C != null ? this.C.hashCode() : 0)) * 37) + (this.D != null ? this.D.hashCode() : 0)) * 37) + (this.E != null ? this.E.hashCode() : 0)) * 37) + (this.F != null ? this.F.hashCode() : 0)) * 37) + (this.G != null ? this.G.hashCode() : 0)) * 37) + (this.H != null ? this.H.hashCode() : 0)) * 37) + (this.I != null ? this.I.hashCode() : 0)) * 37) + (this.J != null ? this.J.hashCode() : 0)) * 37) + (this.K != null ? this.K.hashCode() : 0)) * 37) + (this.L != null ? this.L.hashCode() : 0)) * 37) + (this.M != null ? this.M.hashCode() : 0)) * 37) + (this.N != null ? this.N.hashCode() : 0)) * 37) + (this.O != null ? this.O.hashCode() : 0)) * 37) + (this.P != null ? this.P.hashCode() : 0)) * 37) + this.Q.hashCode()) * 37;
        if (this.R != null) {
            i3 = this.R.hashCode();
        }
        int i4 = hashCode + i3;
        this.b = i4;
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.s != null) {
            sb.append(", installed=");
            sb.append(this.s);
        }
        if (this.t != null) {
            sb.append(", referrer=");
            sb.append(this.t);
        }
        if (this.u != null) {
            sb.append(", fq7=");
            sb.append(this.u);
        }
        if (this.v != null) {
            sb.append(", fq30=");
            sb.append(this.v);
        }
        if (!this.w.isEmpty()) {
            sb.append(", pushes=");
            sb.append(this.w);
        }
        if (this.x != null) {
            sb.append(", sessionTotalCount=");
            sb.append(this.x);
        }
        if (this.y != null) {
            sb.append(", sessionTotalDuration=");
            sb.append(this.y);
        }
        if (this.z != null) {
            sb.append(", sessionLastTime=");
            sb.append(this.z);
        }
        if (this.A != null) {
            sb.append(", sessionLastDuration=");
            sb.append(this.A);
        }
        if (this.B != null) {
            sb.append(", purchaseCurrency=");
            sb.append(this.B);
        }
        if (this.C != null) {
            sb.append(", purchaseTotalCount=");
            sb.append(this.C);
        }
        if (this.D != null) {
            sb.append(", purchaseTotalPrice=");
            sb.append(this.D);
        }
        if (this.E != null) {
            sb.append(", purchaseLastTime=");
            sb.append(this.E);
        }
        if (this.F != null) {
            sb.append(", purchaseLastPrice=");
            sb.append(this.F);
        }
        if (this.G != null) {
            sb.append(", idfa=");
            sb.append(this.G);
        }
        if (this.H != null) {
            sb.append(", idfaOptout=");
            sb.append(this.H);
        }
        if (this.I != null) {
            sb.append(", userId=");
            sb.append(this.I);
        }
        if (this.J != null) {
            sb.append(", userLevel=");
            sb.append(this.J);
        }
        if (this.K != null) {
            sb.append(", friendCount=");
            sb.append(this.K);
        }
        if (this.L != null) {
            sb.append(", uv1=");
            sb.append(this.L);
        }
        if (this.M != null) {
            sb.append(", uv2=");
            sb.append(this.M);
        }
        if (this.N != null) {
            sb.append(", uv3=");
            sb.append(this.N);
        }
        if (this.O != null) {
            sb.append(", uv4=");
            sb.append(this.O);
        }
        if (this.P != null) {
            sb.append(", uv5=");
            sb.append(this.P);
        }
        if (!this.Q.isEmpty()) {
            sb.append(", tags=");
            sb.append(this.Q);
        }
        if (this.R != null) {
            sb.append(", pushOptout=");
            sb.append(this.R);
        }
        StringBuilder replace = sb.replace(0, 2, "User{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public List A = es.a();
        public Boolean B;
        public Long c;
        public String d;
        public Integer e;
        public Integer f;
        public List g = es.a();
        public Integer h;
        public Long i;
        public Long j;
        public Long k;
        public String l;
        public Integer m;
        public Double n;
        public Long o;
        public Double p;
        public String q;
        public Boolean r;
        public String s;
        public Integer t;
        public Integer u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;

        public final fl b() {
            return new fl(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fl flVar = (fl) obj;
            int i = 0;
            int a = (flVar.s != null ? en.i.a(1, (Object) flVar.s) : 0) + (flVar.t != null ? en.p.a(2, (Object) flVar.t) : 0) + (flVar.u != null ? en.d.a(13, (Object) flVar.u) : 0) + (flVar.v != null ? en.d.a(14, (Object) flVar.v) : 0) + fi.c.a().a(15, (Object) flVar.w) + (flVar.x != null ? en.d.a(16, (Object) flVar.x) : 0) + (flVar.y != null ? en.i.a(17, (Object) flVar.y) : 0) + (flVar.z != null ? en.i.a(18, (Object) flVar.z) : 0) + (flVar.A != null ? en.i.a(19, (Object) flVar.A) : 0) + (flVar.B != null ? en.p.a(20, (Object) flVar.B) : 0) + (flVar.C != null ? en.d.a(3, (Object) flVar.C) : 0) + (flVar.D != null ? en.o.a(21, (Object) flVar.D) : 0) + (flVar.E != null ? en.i.a(4, (Object) flVar.E) : 0) + (flVar.F != null ? en.o.a(22, (Object) flVar.F) : 0) + (flVar.G != null ? en.p.a(23, (Object) flVar.G) : 0) + (flVar.H != null ? en.c.a(24, (Object) flVar.H) : 0) + (flVar.I != null ? en.p.a(5, (Object) flVar.I) : 0) + (flVar.J != null ? en.d.a(6, (Object) flVar.J) : 0) + (flVar.K != null ? en.d.a(7, (Object) flVar.K) : 0) + (flVar.L != null ? en.p.a(8, (Object) flVar.L) : 0) + (flVar.M != null ? en.p.a(9, (Object) flVar.M) : 0) + (flVar.N != null ? en.p.a(10, (Object) flVar.N) : 0) + (flVar.O != null ? en.p.a(11, (Object) flVar.O) : 0) + (flVar.P != null ? en.p.a(12, (Object) flVar.P) : 0) + en.p.a().a(26, (Object) flVar.Q);
            if (flVar.R != null) {
                i = en.c.a(25, (Object) flVar.R);
            }
            return a + i + flVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fl flVar = (fl) obj;
            if (flVar.s != null) {
                en.i.a(epVar, 1, flVar.s);
            }
            if (flVar.t != null) {
                en.p.a(epVar, 2, flVar.t);
            }
            if (flVar.u != null) {
                en.d.a(epVar, 13, flVar.u);
            }
            if (flVar.v != null) {
                en.d.a(epVar, 14, flVar.v);
            }
            fi.c.a().a(epVar, 15, flVar.w);
            if (flVar.x != null) {
                en.d.a(epVar, 16, flVar.x);
            }
            if (flVar.y != null) {
                en.i.a(epVar, 17, flVar.y);
            }
            if (flVar.z != null) {
                en.i.a(epVar, 18, flVar.z);
            }
            if (flVar.A != null) {
                en.i.a(epVar, 19, flVar.A);
            }
            if (flVar.B != null) {
                en.p.a(epVar, 20, flVar.B);
            }
            if (flVar.C != null) {
                en.d.a(epVar, 3, flVar.C);
            }
            if (flVar.D != null) {
                en.o.a(epVar, 21, flVar.D);
            }
            if (flVar.E != null) {
                en.i.a(epVar, 4, flVar.E);
            }
            if (flVar.F != null) {
                en.o.a(epVar, 22, flVar.F);
            }
            if (flVar.G != null) {
                en.p.a(epVar, 23, flVar.G);
            }
            if (flVar.H != null) {
                en.c.a(epVar, 24, flVar.H);
            }
            if (flVar.I != null) {
                en.p.a(epVar, 5, flVar.I);
            }
            if (flVar.J != null) {
                en.d.a(epVar, 6, flVar.J);
            }
            if (flVar.K != null) {
                en.d.a(epVar, 7, flVar.K);
            }
            if (flVar.L != null) {
                en.p.a(epVar, 8, flVar.L);
            }
            if (flVar.M != null) {
                en.p.a(epVar, 9, flVar.M);
            }
            if (flVar.N != null) {
                en.p.a(epVar, 10, flVar.N);
            }
            if (flVar.O != null) {
                en.p.a(epVar, 11, flVar.O);
            }
            if (flVar.P != null) {
                en.p.a(epVar, 12, flVar.P);
            }
            en.p.a().a(epVar, 26, flVar.Q);
            if (flVar.R != null) {
                en.c.a(epVar, 25, flVar.R);
            }
            epVar.a(flVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fl.class);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            a aVar = new a();
            long a = eoVar.a();
            while (true) {
                int b = eoVar.b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            aVar.c = (Long) en.i.a(eoVar);
                            break;
                        case 2:
                            aVar.d = (String) en.p.a(eoVar);
                            break;
                        case 3:
                            aVar.m = (Integer) en.d.a(eoVar);
                            break;
                        case 4:
                            aVar.o = (Long) en.i.a(eoVar);
                            break;
                        case 5:
                            aVar.s = (String) en.p.a(eoVar);
                            break;
                        case 6:
                            aVar.t = (Integer) en.d.a(eoVar);
                            break;
                        case 7:
                            aVar.u = (Integer) en.d.a(eoVar);
                            break;
                        case 8:
                            aVar.v = (String) en.p.a(eoVar);
                            break;
                        case 9:
                            aVar.w = (String) en.p.a(eoVar);
                            break;
                        case 10:
                            aVar.x = (String) en.p.a(eoVar);
                            break;
                        case 11:
                            aVar.y = (String) en.p.a(eoVar);
                            break;
                        case 12:
                            aVar.z = (String) en.p.a(eoVar);
                            break;
                        case 13:
                            aVar.e = (Integer) en.d.a(eoVar);
                            break;
                        case 14:
                            aVar.f = (Integer) en.d.a(eoVar);
                            break;
                        case 15:
                            aVar.g.add(fi.c.a(eoVar));
                            break;
                        case 16:
                            aVar.h = (Integer) en.d.a(eoVar);
                            break;
                        case 17:
                            aVar.i = (Long) en.i.a(eoVar);
                            break;
                        case 18:
                            aVar.j = (Long) en.i.a(eoVar);
                            break;
                        case 19:
                            aVar.k = (Long) en.i.a(eoVar);
                            break;
                        case 20:
                            aVar.l = (String) en.p.a(eoVar);
                            break;
                        case 21:
                            aVar.n = (Double) en.o.a(eoVar);
                            break;
                        case 22:
                            aVar.p = (Double) en.o.a(eoVar);
                            break;
                        case 23:
                            aVar.q = (String) en.p.a(eoVar);
                            break;
                        case 24:
                            aVar.r = (Boolean) en.c.a(eoVar);
                            break;
                        case 25:
                            aVar.B = (Boolean) en.c.a(eoVar);
                            break;
                        case 26:
                            aVar.A.add(en.p.a(eoVar));
                            break;
                        default:
                            ek c = eoVar.c();
                            aVar.a(b, c, c.a().a(eoVar));
                            break;
                    }
                } else {
                    eoVar.a(a);
                    return aVar.b();
                }
            }
        }
    }
}
