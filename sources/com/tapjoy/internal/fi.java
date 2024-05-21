package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class fi extends el {
    public static final en c = new b();
    public static final Long d = 0L;
    public static final Long e = 0L;
    public final String f;
    public final Long g;
    public final Long h;

    public fi(String str, Long l) {
        this(str, l, (Long) null, iy.b);
    }

    public fi(String str, Long l, Long l2, iy iyVar) {
        super(c, iyVar);
        this.f = str;
        this.g = l;
        this.h = l2;
    }

    public final a b() {
        a aVar = new a();
        aVar.c = this.f;
        aVar.d = this.g;
        aVar.e = this.h;
        aVar.a(a());
        return aVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fi)) {
            return false;
        }
        fi fiVar = (fi) obj;
        return a().equals(fiVar.a()) && this.f.equals(fiVar.f) && this.g.equals(fiVar.g) && es.a((Object) this.h, (Object) fiVar.h);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = (((((a().hashCode() * 37) + this.f.hashCode()) * 37) + this.g.hashCode()) * 37) + (this.h != null ? this.h.hashCode() : 0);
        this.b = hashCode;
        return hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", id=");
        sb.append(this.f);
        sb.append(", received=");
        sb.append(this.g);
        if (this.h != null) {
            sb.append(", clicked=");
            sb.append(this.h);
        }
        StringBuilder replace = sb.replace(0, 2, "Push{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public Long d;
        public Long e;

        public final fi b() {
            if (this.c != null && this.d != null) {
                return new fi(this.c, this.d, this.e, super.a());
            }
            throw es.a(this.c, "id", this.d, "received");
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fi fiVar = (fi) obj;
            return en.p.a(1, (Object) fiVar.f) + en.i.a(2, (Object) fiVar.g) + (fiVar.h != null ? en.i.a(3, (Object) fiVar.h) : 0) + fiVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fi fiVar = (fi) obj;
            en.p.a(epVar, 1, fiVar.f);
            en.i.a(epVar, 2, fiVar.g);
            if (fiVar.h != null) {
                en.i.a(epVar, 3, fiVar.h);
            }
            epVar.a(fiVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fi.class);
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
                            aVar.d = (Long) en.i.a(eoVar);
                            break;
                        case 3:
                            aVar.e = (Long) en.i.a(eoVar);
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
