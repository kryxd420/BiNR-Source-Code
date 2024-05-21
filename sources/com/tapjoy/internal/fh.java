package com.tapjoy.internal;

import com.tapjoy.internal.el;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;

public final class fh extends el {
    public static final en c = new b();
    public static final Integer d = 1;
    public static final Double e = Double.valueOf(0.0d);
    public static final Integer f = 0;
    public static final Long g = 0L;
    public final String h;
    public final Integer i;
    public final Double j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final Integer p;
    public final Long q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fh(String str, Integer num, Double d2, String str2, String str3, String str4, String str5, String str6, Integer num2, Long l2, String str7, String str8, String str9, String str10, iy iyVar) {
        super(c, iyVar);
        this.h = str;
        this.i = num;
        this.j = d2;
        this.k = str2;
        this.l = str3;
        this.m = str4;
        this.n = str5;
        this.o = str6;
        this.p = num2;
        this.q = l2;
        this.r = str7;
        this.s = str8;
        this.t = str9;
        this.u = str10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fh)) {
            return false;
        }
        fh fhVar = (fh) obj;
        return a().equals(fhVar.a()) && this.h.equals(fhVar.h) && es.a((Object) this.i, (Object) fhVar.i) && es.a((Object) this.j, (Object) fhVar.j) && es.a((Object) this.k, (Object) fhVar.k) && es.a((Object) this.l, (Object) fhVar.l) && es.a((Object) this.m, (Object) fhVar.m) && es.a((Object) this.n, (Object) fhVar.n) && es.a((Object) this.o, (Object) fhVar.o) && es.a((Object) this.p, (Object) fhVar.p) && es.a((Object) this.q, (Object) fhVar.q) && es.a((Object) this.r, (Object) fhVar.r) && es.a((Object) this.s, (Object) fhVar.s) && es.a((Object) this.t, (Object) fhVar.t) && es.a((Object) this.u, (Object) fhVar.u);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int hashCode = ((((((((((((((((((((((((((a().hashCode() * 37) + this.h.hashCode()) * 37) + (this.i != null ? this.i.hashCode() : 0)) * 37) + (this.j != null ? this.j.hashCode() : 0)) * 37) + (this.k != null ? this.k.hashCode() : 0)) * 37) + (this.l != null ? this.l.hashCode() : 0)) * 37) + (this.m != null ? this.m.hashCode() : 0)) * 37) + (this.n != null ? this.n.hashCode() : 0)) * 37) + (this.o != null ? this.o.hashCode() : 0)) * 37) + (this.p != null ? this.p.hashCode() : 0)) * 37) + (this.q != null ? this.q.hashCode() : 0)) * 37) + (this.r != null ? this.r.hashCode() : 0)) * 37) + (this.s != null ? this.s.hashCode() : 0)) * 37) + (this.t != null ? this.t.hashCode() : 0)) * 37;
        if (this.u != null) {
            i3 = this.u.hashCode();
        }
        int i4 = hashCode + i3;
        this.b = i4;
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", productId=");
        sb.append(this.h);
        if (this.i != null) {
            sb.append(", productQuantity=");
            sb.append(this.i);
        }
        if (this.j != null) {
            sb.append(", productPrice=");
            sb.append(this.j);
        }
        if (this.k != null) {
            sb.append(", productPriceCurrency=");
            sb.append(this.k);
        }
        if (this.l != null) {
            sb.append(", productType=");
            sb.append(this.l);
        }
        if (this.m != null) {
            sb.append(", productTitle=");
            sb.append(this.m);
        }
        if (this.n != null) {
            sb.append(", productDescription=");
            sb.append(this.n);
        }
        if (this.o != null) {
            sb.append(", transactionId=");
            sb.append(this.o);
        }
        if (this.p != null) {
            sb.append(", transactionState=");
            sb.append(this.p);
        }
        if (this.q != null) {
            sb.append(", transactionDate=");
            sb.append(this.q);
        }
        if (this.r != null) {
            sb.append(", campaignId=");
            sb.append(this.r);
        }
        if (this.s != null) {
            sb.append(", currencyPrice=");
            sb.append(this.s);
        }
        if (this.t != null) {
            sb.append(", receipt=");
            sb.append(this.t);
        }
        if (this.u != null) {
            sb.append(", signature=");
            sb.append(this.u);
        }
        StringBuilder replace = sb.replace(0, 2, "Purchase{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public Integer d;
        public Double e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public Integer k;
        public Long l;
        public String m;
        public String n;
        public String o;
        public String p;

        public final fh b() {
            if (this.c != null) {
                return new fh(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, super.a());
            }
            throw es.a(this.c, InAppPurchaseMetaData.KEY_PRODUCT_ID);
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fh fhVar = (fh) obj;
            int i = 0;
            int a = en.p.a(1, (Object) fhVar.h) + (fhVar.i != null ? en.d.a(2, (Object) fhVar.i) : 0) + (fhVar.j != null ? en.o.a(3, (Object) fhVar.j) : 0) + (fhVar.k != null ? en.p.a(4, (Object) fhVar.k) : 0) + (fhVar.l != null ? en.p.a(5, (Object) fhVar.l) : 0) + (fhVar.m != null ? en.p.a(6, (Object) fhVar.m) : 0) + (fhVar.n != null ? en.p.a(7, (Object) fhVar.n) : 0) + (fhVar.o != null ? en.p.a(8, (Object) fhVar.o) : 0) + (fhVar.p != null ? en.d.a(9, (Object) fhVar.p) : 0) + (fhVar.q != null ? en.i.a(10, (Object) fhVar.q) : 0) + (fhVar.r != null ? en.p.a(11, (Object) fhVar.r) : 0) + (fhVar.s != null ? en.p.a(12, (Object) fhVar.s) : 0) + (fhVar.t != null ? en.p.a(13, (Object) fhVar.t) : 0);
            if (fhVar.u != null) {
                i = en.p.a(14, (Object) fhVar.u);
            }
            return a + i + fhVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fh fhVar = (fh) obj;
            en.p.a(epVar, 1, fhVar.h);
            if (fhVar.i != null) {
                en.d.a(epVar, 2, fhVar.i);
            }
            if (fhVar.j != null) {
                en.o.a(epVar, 3, fhVar.j);
            }
            if (fhVar.k != null) {
                en.p.a(epVar, 4, fhVar.k);
            }
            if (fhVar.l != null) {
                en.p.a(epVar, 5, fhVar.l);
            }
            if (fhVar.m != null) {
                en.p.a(epVar, 6, fhVar.m);
            }
            if (fhVar.n != null) {
                en.p.a(epVar, 7, fhVar.n);
            }
            if (fhVar.o != null) {
                en.p.a(epVar, 8, fhVar.o);
            }
            if (fhVar.p != null) {
                en.d.a(epVar, 9, fhVar.p);
            }
            if (fhVar.q != null) {
                en.i.a(epVar, 10, fhVar.q);
            }
            if (fhVar.r != null) {
                en.p.a(epVar, 11, fhVar.r);
            }
            if (fhVar.s != null) {
                en.p.a(epVar, 12, fhVar.s);
            }
            if (fhVar.t != null) {
                en.p.a(epVar, 13, fhVar.t);
            }
            if (fhVar.u != null) {
                en.p.a(epVar, 14, fhVar.u);
            }
            epVar.a(fhVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fh.class);
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
                            aVar.d = (Integer) en.d.a(eoVar);
                            break;
                        case 3:
                            aVar.e = (Double) en.o.a(eoVar);
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
                            aVar.i = (String) en.p.a(eoVar);
                            break;
                        case 8:
                            aVar.j = (String) en.p.a(eoVar);
                            break;
                        case 9:
                            aVar.k = (Integer) en.d.a(eoVar);
                            break;
                        case 10:
                            aVar.l = (Long) en.i.a(eoVar);
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
