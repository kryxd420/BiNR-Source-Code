package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class fd extends el {
    public static final en c = new b();
    public static final Long d = 0L;
    public final String e;
    public final Long f;

    public fd(String str, Long l) {
        this(str, l, iy.b);
    }

    public fd(String str, Long l, iy iyVar) {
        super(c, iyVar);
        this.e = str;
        this.f = l;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fd)) {
            return false;
        }
        fd fdVar = (fd) obj;
        return a().equals(fdVar.a()) && this.e.equals(fdVar.e) && this.f.equals(fdVar.f);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = (((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode();
        this.b = hashCode;
        return hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", name=");
        sb.append(this.e);
        sb.append(", value=");
        sb.append(this.f);
        StringBuilder replace = sb.replace(0, 2, "EventValue{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public Long d;

        public final fd b() {
            if (this.c != null && this.d != null) {
                return new fd(this.c, this.d, super.a());
            }
            throw es.a(this.c, "name", this.d, "value");
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fd fdVar = (fd) obj;
            return en.p.a(1, (Object) fdVar.e) + en.i.a(2, (Object) fdVar.f) + fdVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fd fdVar = (fd) obj;
            en.p.a(epVar, 1, fdVar.e);
            en.i.a(epVar, 2, fdVar.f);
            epVar.a(fdVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fd.class);
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
