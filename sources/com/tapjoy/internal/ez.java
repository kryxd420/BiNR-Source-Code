package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.internal.el;
import com.tapjoy.internal.en;
import java.util.List;

public final class ez extends el {
    public static final en c = new b();
    public static final fc d = fc.APP;
    public static final Long e = 0L;
    public static final Long f = 0L;
    public static final Long g = 0L;
    public static final Long h = 0L;
    public static final Integer i = 0;
    public static final Integer j = 0;
    public static final Integer k = 0;
    public static final Long l = 0L;
    public static final Long m = 0L;
    public final fh A;
    public final String B;
    public final String C;
    public final fg D;
    public final String E;
    public final String F;
    public final String G;
    public final List H;
    public final String I;
    public final Integer J;
    public final Long K;
    public final Long L;
    public final fc n;
    public final String o;
    public final Long p;
    public final Long q;
    public final String r;
    public final Long s;
    public final Long t;
    public final fe u;
    public final ey v;
    public final fl w;
    public final Integer x;
    public final Integer y;
    public final fb z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ez(fc fcVar, String str, Long l2, Long l3, String str2, Long l4, Long l5, fe feVar, ey eyVar, fl flVar, Integer num, Integer num2, fb fbVar, fh fhVar, String str3, String str4, fg fgVar, String str5, String str6, String str7, List list, String str8, Integer num3, Long l6, Long l7, iy iyVar) {
        super(c, iyVar);
        this.n = fcVar;
        this.o = str;
        this.p = l2;
        this.q = l3;
        this.r = str2;
        this.s = l4;
        this.t = l5;
        this.u = feVar;
        this.v = eyVar;
        this.w = flVar;
        this.x = num;
        this.y = num2;
        this.z = fbVar;
        this.A = fhVar;
        this.B = str3;
        this.C = str4;
        this.D = fgVar;
        this.E = str5;
        this.F = str6;
        this.G = str7;
        this.H = es.a(TJAdUnitConstants.String.USAGE_TRACKER_VALUES, list);
        this.I = str8;
        this.J = num3;
        this.K = l6;
        this.L = l7;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ez)) {
            return false;
        }
        ez ezVar = (ez) obj;
        return a().equals(ezVar.a()) && this.n.equals(ezVar.n) && this.o.equals(ezVar.o) && this.p.equals(ezVar.p) && es.a((Object) this.q, (Object) ezVar.q) && es.a((Object) this.r, (Object) ezVar.r) && es.a((Object) this.s, (Object) ezVar.s) && es.a((Object) this.t, (Object) ezVar.t) && es.a((Object) this.u, (Object) ezVar.u) && es.a((Object) this.v, (Object) ezVar.v) && es.a((Object) this.w, (Object) ezVar.w) && es.a((Object) this.x, (Object) ezVar.x) && es.a((Object) this.y, (Object) ezVar.y) && es.a((Object) this.z, (Object) ezVar.z) && es.a((Object) this.A, (Object) ezVar.A) && es.a((Object) this.B, (Object) ezVar.B) && es.a((Object) this.C, (Object) ezVar.C) && es.a((Object) this.D, (Object) ezVar.D) && es.a((Object) this.E, (Object) ezVar.E) && es.a((Object) this.F, (Object) ezVar.F) && es.a((Object) this.G, (Object) ezVar.G) && this.H.equals(ezVar.H) && es.a((Object) this.I, (Object) ezVar.I) && es.a((Object) this.J, (Object) ezVar.J) && es.a((Object) this.K, (Object) ezVar.K) && es.a((Object) this.L, (Object) ezVar.L);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((a().hashCode() * 37) + this.n.hashCode()) * 37) + this.o.hashCode()) * 37) + this.p.hashCode()) * 37) + (this.q != null ? this.q.hashCode() : 0)) * 37) + (this.r != null ? this.r.hashCode() : 0)) * 37) + (this.s != null ? this.s.hashCode() : 0)) * 37) + (this.t != null ? this.t.hashCode() : 0)) * 37) + (this.u != null ? this.u.hashCode() : 0)) * 37) + (this.v != null ? this.v.hashCode() : 0)) * 37) + (this.w != null ? this.w.hashCode() : 0)) * 37) + (this.x != null ? this.x.hashCode() : 0)) * 37) + (this.y != null ? this.y.hashCode() : 0)) * 37) + (this.z != null ? this.z.hashCode() : 0)) * 37) + (this.A != null ? this.A.hashCode() : 0)) * 37) + (this.B != null ? this.B.hashCode() : 0)) * 37) + (this.C != null ? this.C.hashCode() : 0)) * 37) + (this.D != null ? this.D.hashCode() : 0)) * 37) + (this.E != null ? this.E.hashCode() : 0)) * 37) + (this.F != null ? this.F.hashCode() : 0)) * 37) + (this.G != null ? this.G.hashCode() : 0)) * 37) + this.H.hashCode()) * 37) + (this.I != null ? this.I.hashCode() : 0)) * 37) + (this.J != null ? this.J.hashCode() : 0)) * 37) + (this.K != null ? this.K.hashCode() : 0)) * 37;
        if (this.L != null) {
            i3 = this.L.hashCode();
        }
        int i4 = hashCode + i3;
        this.b = i4;
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", type=");
        sb.append(this.n);
        sb.append(", name=");
        sb.append(this.o);
        sb.append(", time=");
        sb.append(this.p);
        if (this.q != null) {
            sb.append(", systemTime=");
            sb.append(this.q);
        }
        if (this.r != null) {
            sb.append(", instanceId=");
            sb.append(this.r);
        }
        if (this.s != null) {
            sb.append(", elapsedRealtime=");
            sb.append(this.s);
        }
        if (this.t != null) {
            sb.append(", duration=");
            sb.append(this.t);
        }
        if (this.u != null) {
            sb.append(", info=");
            sb.append(this.u);
        }
        if (this.v != null) {
            sb.append(", app=");
            sb.append(this.v);
        }
        if (this.w != null) {
            sb.append(", user=");
            sb.append(this.w);
        }
        if (this.x != null) {
            sb.append(", xxx_session_seq=");
            sb.append(this.x);
        }
        if (this.y != null) {
            sb.append(", eventSeq=");
            sb.append(this.y);
        }
        if (this.z != null) {
            sb.append(", eventPrev=");
            sb.append(this.z);
        }
        if (this.A != null) {
            sb.append(", purchase=");
            sb.append(this.A);
        }
        if (this.B != null) {
            sb.append(", exception=");
            sb.append(this.B);
        }
        if (this.C != null) {
            sb.append(", metaBase=");
            sb.append(this.C);
        }
        if (this.D != null) {
            sb.append(", meta=");
            sb.append(this.D);
        }
        if (this.E != null) {
            sb.append(", category=");
            sb.append(this.E);
        }
        if (this.F != null) {
            sb.append(", p1=");
            sb.append(this.F);
        }
        if (this.G != null) {
            sb.append(", p2=");
            sb.append(this.G);
        }
        if (!this.H.isEmpty()) {
            sb.append(", values=");
            sb.append(this.H);
        }
        if (this.I != null) {
            sb.append(", dimensions=");
            sb.append(this.I);
        }
        if (this.J != null) {
            sb.append(", count=");
            sb.append(this.J);
        }
        if (this.K != null) {
            sb.append(", firstTime=");
            sb.append(this.K);
        }
        if (this.L != null) {
            sb.append(", lastTime=");
            sb.append(this.L);
        }
        StringBuilder replace = sb.replace(0, 2, "Event{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public Long A;
        public fc c;
        public String d;
        public Long e;
        public Long f;
        public String g;
        public Long h;
        public Long i;
        public fe j;
        public ey k;
        public fl l;
        public Integer m;
        public Integer n;
        public fb o;
        public fh p;
        public String q;
        public String r;
        public fg s;
        public String t;
        public String u;
        public String v;
        public List w = es.a();
        public String x;
        public Integer y;
        public Long z;

        public final ez b() {
            if (this.c == null || this.d == null || this.e == null) {
                throw es.a(this.c, "type", this.d, "name", this.e, "time");
            }
            ez ezVar = r2;
            ez ezVar2 = new ez(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, super.a());
            return ezVar;
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            ez ezVar = (ez) obj;
            int i = 0;
            int a = fc.ADAPTER.a(1, (Object) ezVar.n) + en.p.a(2, (Object) ezVar.o) + en.i.a(3, (Object) ezVar.p) + (ezVar.q != null ? en.i.a(19, (Object) ezVar.q) : 0) + (ezVar.r != null ? en.p.a(20, (Object) ezVar.r) : 0) + (ezVar.s != null ? en.i.a(21, (Object) ezVar.s) : 0) + (ezVar.t != null ? en.i.a(4, (Object) ezVar.t) : 0) + (ezVar.u != null ? fe.c.a(5, (Object) ezVar.u) : 0) + (ezVar.v != null ? ey.c.a(6, (Object) ezVar.v) : 0) + (ezVar.w != null ? fl.c.a(7, (Object) ezVar.w) : 0) + (ezVar.x != null ? en.d.a(8, (Object) ezVar.x) : 0) + (ezVar.y != null ? en.d.a(9, (Object) ezVar.y) : 0) + (ezVar.z != null ? fb.c.a(10, (Object) ezVar.z) : 0) + (ezVar.A != null ? fh.c.a(11, (Object) ezVar.A) : 0) + (ezVar.B != null ? en.p.a(12, (Object) ezVar.B) : 0) + (ezVar.C != null ? en.p.a(13, (Object) ezVar.C) : 0) + (ezVar.D != null ? fg.c.a(18, (Object) ezVar.D) : 0) + (ezVar.E != null ? en.p.a(14, (Object) ezVar.E) : 0) + (ezVar.F != null ? en.p.a(15, (Object) ezVar.F) : 0) + (ezVar.G != null ? en.p.a(16, (Object) ezVar.G) : 0) + fd.c.a().a(17, (Object) ezVar.H) + (ezVar.I != null ? en.p.a(22, (Object) ezVar.I) : 0) + (ezVar.J != null ? en.d.a(23, (Object) ezVar.J) : 0) + (ezVar.K != null ? en.i.a(24, (Object) ezVar.K) : 0);
            if (ezVar.L != null) {
                i = en.i.a(25, (Object) ezVar.L);
            }
            return a + i + ezVar.a().c();
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return b(eoVar);
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            ez ezVar = (ez) obj;
            fc.ADAPTER.a(epVar, 1, ezVar.n);
            en.p.a(epVar, 2, ezVar.o);
            en.i.a(epVar, 3, ezVar.p);
            if (ezVar.q != null) {
                en.i.a(epVar, 19, ezVar.q);
            }
            if (ezVar.r != null) {
                en.p.a(epVar, 20, ezVar.r);
            }
            if (ezVar.s != null) {
                en.i.a(epVar, 21, ezVar.s);
            }
            if (ezVar.t != null) {
                en.i.a(epVar, 4, ezVar.t);
            }
            if (ezVar.u != null) {
                fe.c.a(epVar, 5, ezVar.u);
            }
            if (ezVar.v != null) {
                ey.c.a(epVar, 6, ezVar.v);
            }
            if (ezVar.w != null) {
                fl.c.a(epVar, 7, ezVar.w);
            }
            if (ezVar.x != null) {
                en.d.a(epVar, 8, ezVar.x);
            }
            if (ezVar.y != null) {
                en.d.a(epVar, 9, ezVar.y);
            }
            if (ezVar.z != null) {
                fb.c.a(epVar, 10, ezVar.z);
            }
            if (ezVar.A != null) {
                fh.c.a(epVar, 11, ezVar.A);
            }
            if (ezVar.B != null) {
                en.p.a(epVar, 12, ezVar.B);
            }
            if (ezVar.C != null) {
                en.p.a(epVar, 13, ezVar.C);
            }
            if (ezVar.D != null) {
                fg.c.a(epVar, 18, ezVar.D);
            }
            if (ezVar.E != null) {
                en.p.a(epVar, 14, ezVar.E);
            }
            if (ezVar.F != null) {
                en.p.a(epVar, 15, ezVar.F);
            }
            if (ezVar.G != null) {
                en.p.a(epVar, 16, ezVar.G);
            }
            fd.c.a().a(epVar, 17, ezVar.H);
            if (ezVar.I != null) {
                en.p.a(epVar, 22, ezVar.I);
            }
            if (ezVar.J != null) {
                en.d.a(epVar, 23, ezVar.J);
            }
            if (ezVar.K != null) {
                en.i.a(epVar, 24, ezVar.K);
            }
            if (ezVar.L != null) {
                en.i.a(epVar, 25, ezVar.L);
            }
            epVar.a(ezVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, ez.class);
        }

        private static ez b(eo eoVar) {
            a aVar = new a();
            long a = eoVar.a();
            while (true) {
                int b = eoVar.b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            try {
                                aVar.c = (fc) fc.ADAPTER.a(eoVar);
                                break;
                            } catch (en.a e) {
                                aVar.a(b, ek.VARINT, Long.valueOf((long) e.a));
                                break;
                            }
                        case 2:
                            aVar.d = (String) en.p.a(eoVar);
                            break;
                        case 3:
                            aVar.e = (Long) en.i.a(eoVar);
                            break;
                        case 4:
                            aVar.i = (Long) en.i.a(eoVar);
                            break;
                        case 5:
                            aVar.j = (fe) fe.c.a(eoVar);
                            break;
                        case 6:
                            aVar.k = (ey) ey.c.a(eoVar);
                            break;
                        case 7:
                            aVar.l = (fl) fl.c.a(eoVar);
                            break;
                        case 8:
                            aVar.m = (Integer) en.d.a(eoVar);
                            break;
                        case 9:
                            aVar.n = (Integer) en.d.a(eoVar);
                            break;
                        case 10:
                            aVar.o = (fb) fb.c.a(eoVar);
                            break;
                        case 11:
                            aVar.p = (fh) fh.c.a(eoVar);
                            break;
                        case 12:
                            aVar.q = (String) en.p.a(eoVar);
                            break;
                        case 13:
                            aVar.r = (String) en.p.a(eoVar);
                            break;
                        case 14:
                            aVar.t = (String) en.p.a(eoVar);
                            break;
                        case 15:
                            aVar.u = (String) en.p.a(eoVar);
                            break;
                        case 16:
                            aVar.v = (String) en.p.a(eoVar);
                            break;
                        case 17:
                            aVar.w.add(fd.c.a(eoVar));
                            break;
                        case 18:
                            aVar.s = (fg) fg.c.a(eoVar);
                            break;
                        case 19:
                            aVar.f = (Long) en.i.a(eoVar);
                            break;
                        case 20:
                            aVar.g = (String) en.p.a(eoVar);
                            break;
                        case 21:
                            aVar.h = (Long) en.i.a(eoVar);
                            break;
                        case 22:
                            aVar.x = (String) en.p.a(eoVar);
                            break;
                        case 23:
                            aVar.y = (Integer) en.d.a(eoVar);
                            break;
                        case 24:
                            aVar.z = (Long) en.i.a(eoVar);
                            break;
                        case 25:
                            aVar.A = (Long) en.i.a(eoVar);
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
