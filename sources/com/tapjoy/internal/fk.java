package com.tapjoy.internal;

import com.tapjoy.internal.el;
import java.util.List;

public final class fk extends el {
    public static final en c = new b();
    public final List d;

    public fk(List list) {
        this(list, iy.b);
    }

    public fk(List list, iy iyVar) {
        super(c, iyVar);
        this.d = es.a("elements", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fk)) {
            return false;
        }
        fk fkVar = (fk) obj;
        return a().equals(fkVar.a()) && this.d.equals(fkVar.d);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = (a().hashCode() * 37) + this.d.hashCode();
        this.b = hashCode;
        return hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.d.isEmpty()) {
            sb.append(", elements=");
            sb.append(this.d);
        }
        StringBuilder replace = sb.replace(0, 2, "StringList{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public List c = es.a();

        public final fk b() {
            return new fk(this.c, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fk fkVar = (fk) obj;
            return en.p.a().a(1, (Object) fkVar.d) + fkVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fk fkVar = (fk) obj;
            en.p.a().a(epVar, 1, fkVar.d);
            epVar.a(fkVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fk.class);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            a aVar = new a();
            long a = eoVar.a();
            while (true) {
                int b = eoVar.b();
                if (b == -1) {
                    eoVar.a(a);
                    return aVar.b();
                } else if (b != 1) {
                    ek c = eoVar.c();
                    aVar.a(b, c, c.a().a(eoVar));
                } else {
                    aVar.c.add(en.p.a(eoVar));
                }
            }
        }
    }
}
