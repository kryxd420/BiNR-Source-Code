package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class fe extends el {
    public static final en c = new b();
    public static final Integer d = 0;
    public static final Integer e = 0;
    public static final Integer f = 0;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final String p;
    public final String q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;
    public final String v;
    public final String w;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fe(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, Integer num3, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, iy iyVar) {
        super(c, iyVar);
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = str4;
        this.k = str5;
        this.l = str6;
        this.m = num;
        this.n = num2;
        this.o = num3;
        this.p = str7;
        this.q = str8;
        this.r = str9;
        this.s = str10;
        this.t = str11;
        this.u = str12;
        this.v = str13;
        this.w = str14;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fe)) {
            return false;
        }
        fe feVar = (fe) obj;
        return a().equals(feVar.a()) && es.a((Object) this.g, (Object) feVar.g) && es.a((Object) this.h, (Object) feVar.h) && es.a((Object) this.i, (Object) feVar.i) && es.a((Object) this.j, (Object) feVar.j) && es.a((Object) this.k, (Object) feVar.k) && es.a((Object) this.l, (Object) feVar.l) && es.a((Object) this.m, (Object) feVar.m) && es.a((Object) this.n, (Object) feVar.n) && es.a((Object) this.o, (Object) feVar.o) && es.a((Object) this.p, (Object) feVar.p) && es.a((Object) this.q, (Object) feVar.q) && es.a((Object) this.r, (Object) feVar.r) && es.a((Object) this.s, (Object) feVar.s) && es.a((Object) this.t, (Object) feVar.t) && es.a((Object) this.u, (Object) feVar.u) && es.a((Object) this.v, (Object) feVar.v) && es.a((Object) this.w, (Object) feVar.w);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int hashCode = ((((((((((((((((((((((((((((((((a().hashCode() * 37) + (this.g != null ? this.g.hashCode() : 0)) * 37) + (this.h != null ? this.h.hashCode() : 0)) * 37) + (this.i != null ? this.i.hashCode() : 0)) * 37) + (this.j != null ? this.j.hashCode() : 0)) * 37) + (this.k != null ? this.k.hashCode() : 0)) * 37) + (this.l != null ? this.l.hashCode() : 0)) * 37) + (this.m != null ? this.m.hashCode() : 0)) * 37) + (this.n != null ? this.n.hashCode() : 0)) * 37) + (this.o != null ? this.o.hashCode() : 0)) * 37) + (this.p != null ? this.p.hashCode() : 0)) * 37) + (this.q != null ? this.q.hashCode() : 0)) * 37) + (this.r != null ? this.r.hashCode() : 0)) * 37) + (this.s != null ? this.s.hashCode() : 0)) * 37) + (this.t != null ? this.t.hashCode() : 0)) * 37) + (this.u != null ? this.u.hashCode() : 0)) * 37) + (this.v != null ? this.v.hashCode() : 0)) * 37;
        if (this.w != null) {
            i3 = this.w.hashCode();
        }
        int i4 = hashCode + i3;
        this.b = i4;
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.g != null) {
            sb.append(", mac=");
            sb.append(this.g);
        }
        if (this.h != null) {
            sb.append(", deviceId=");
            sb.append(this.h);
        }
        if (this.i != null) {
            sb.append(", deviceMaker=");
            sb.append(this.i);
        }
        if (this.j != null) {
            sb.append(", deviceModel=");
            sb.append(this.j);
        }
        if (this.k != null) {
            sb.append(", osName=");
            sb.append(this.k);
        }
        if (this.l != null) {
            sb.append(", osVer=");
            sb.append(this.l);
        }
        if (this.m != null) {
            sb.append(", displayD=");
            sb.append(this.m);
        }
        if (this.n != null) {
            sb.append(", displayW=");
            sb.append(this.n);
        }
        if (this.o != null) {
            sb.append(", displayH=");
            sb.append(this.o);
        }
        if (this.p != null) {
            sb.append(", locale=");
            sb.append(this.p);
        }
        if (this.q != null) {
            sb.append(", timezone=");
            sb.append(this.q);
        }
        if (this.r != null) {
            sb.append(", pkgId=");
            sb.append(this.r);
        }
        if (this.s != null) {
            sb.append(", pkgSign=");
            sb.append(this.s);
        }
        if (this.t != null) {
            sb.append(", sdk=");
            sb.append(this.t);
        }
        if (this.u != null) {
            sb.append(", countrySim=");
            sb.append(this.u);
        }
        if (this.v != null) {
            sb.append(", countryNet=");
            sb.append(this.v);
        }
        if (this.w != null) {
            sb.append(", imei=");
            sb.append(this.w);
        }
        StringBuilder replace = sb.replace(0, 2, "Info{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public Integer i;
        public Integer j;
        public Integer k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public String q;
        public String r;
        public String s;

        public final fe b() {
            return new fe(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fe feVar = (fe) obj;
            int i = 0;
            int a = (feVar.g != null ? en.p.a(1, (Object) feVar.g) : 0) + (feVar.h != null ? en.p.a(2, (Object) feVar.h) : 0) + (feVar.i != null ? en.p.a(3, (Object) feVar.i) : 0) + (feVar.j != null ? en.p.a(4, (Object) feVar.j) : 0) + (feVar.k != null ? en.p.a(5, (Object) feVar.k) : 0) + (feVar.l != null ? en.p.a(6, (Object) feVar.l) : 0) + (feVar.m != null ? en.d.a(7, (Object) feVar.m) : 0) + (feVar.n != null ? en.d.a(8, (Object) feVar.n) : 0) + (feVar.o != null ? en.d.a(9, (Object) feVar.o) : 0) + (feVar.p != null ? en.p.a(10, (Object) feVar.p) : 0) + (feVar.q != null ? en.p.a(11, (Object) feVar.q) : 0) + (feVar.r != null ? en.p.a(12, (Object) feVar.r) : 0) + (feVar.s != null ? en.p.a(13, (Object) feVar.s) : 0) + (feVar.t != null ? en.p.a(14, (Object) feVar.t) : 0) + (feVar.u != null ? en.p.a(15, (Object) feVar.u) : 0) + (feVar.v != null ? en.p.a(16, (Object) feVar.v) : 0);
            if (feVar.w != null) {
                i = en.p.a(17, (Object) feVar.w);
            }
            return a + i + feVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fe feVar = (fe) obj;
            if (feVar.g != null) {
                en.p.a(epVar, 1, feVar.g);
            }
            if (feVar.h != null) {
                en.p.a(epVar, 2, feVar.h);
            }
            if (feVar.i != null) {
                en.p.a(epVar, 3, feVar.i);
            }
            if (feVar.j != null) {
                en.p.a(epVar, 4, feVar.j);
            }
            if (feVar.k != null) {
                en.p.a(epVar, 5, feVar.k);
            }
            if (feVar.l != null) {
                en.p.a(epVar, 6, feVar.l);
            }
            if (feVar.m != null) {
                en.d.a(epVar, 7, feVar.m);
            }
            if (feVar.n != null) {
                en.d.a(epVar, 8, feVar.n);
            }
            if (feVar.o != null) {
                en.d.a(epVar, 9, feVar.o);
            }
            if (feVar.p != null) {
                en.p.a(epVar, 10, feVar.p);
            }
            if (feVar.q != null) {
                en.p.a(epVar, 11, feVar.q);
            }
            if (feVar.r != null) {
                en.p.a(epVar, 12, feVar.r);
            }
            if (feVar.s != null) {
                en.p.a(epVar, 13, feVar.s);
            }
            if (feVar.t != null) {
                en.p.a(epVar, 14, feVar.t);
            }
            if (feVar.u != null) {
                en.p.a(epVar, 15, feVar.u);
            }
            if (feVar.v != null) {
                en.p.a(epVar, 16, feVar.v);
            }
            if (feVar.w != null) {
                en.p.a(epVar, 17, feVar.w);
            }
            epVar.a(feVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fe.class);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            a aVar = new a();
            long a = eoVar.a();
            while (true) {
                int b = eoVar.b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            aVar.c = (String) en.p.a(eoVar);
                            break;
                        case 2:
                            aVar.d = (String) en.p.a(eoVar);
                            break;
                        case 3:
                            aVar.e = (String) en.p.a(eoVar);
                            break;
                        case 4:
                            aVar.f = (String) en.p.a(eoVar);
                            break;
                        case 5:
                            aVar.g = (String) en.p.a(eoVar);
                            break;
                        case 6:
                            aVar.h = (String) en.p.a(eoVar);
                            break;
                        case 7:
                            aVar.i = (Integer) en.d.a(eoVar);
                            break;
                        case 8:
                            aVar.j = (Integer) en.d.a(eoVar);
                            break;
                        case 9:
                            aVar.k = (Integer) en.d.a(eoVar);
                            break;
                        case 10:
                            aVar.l = (String) en.p.a(eoVar);
                            break;
                        case 11:
                            aVar.m = (String) en.p.a(eoVar);
                            break;
                        case 12:
                            aVar.n = (String) en.p.a(eoVar);
                            break;
                        case 13:
                            aVar.o = (String) en.p.a(eoVar);
                            break;
                        case 14:
                            aVar.p = (String) en.p.a(eoVar);
                            break;
                        case 15:
                            aVar.q = (String) en.p.a(eoVar);
                            break;
                        case 16:
                            aVar.r = (String) en.p.a(eoVar);
                            break;
                        case 17:
                            aVar.s = (String) en.p.a(eoVar);
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
