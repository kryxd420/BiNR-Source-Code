package com.tapjoy.internal;

import com.tapjoy.internal.el;
import java.util.List;

public final class fj extends el {
    public static final en c = new b();
    public final List d;

    public fj(List list) {
        this(list, iy.b);
    }

    public fj(List list, iy iyVar) {
        super(c, iyVar);
        this.d = es.a("pushes", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fj)) {
            return false;
        }
        fj fjVar = (fj) obj;
        return a().equals(fjVar.a()) && this.d.equals(fjVar.d);
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
            sb.append(", pushes=");
            sb.append(this.d);
        }
        StringBuilder replace = sb.replace(0, 2, "PushList{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public List c = es.a();

        public final fj b() {
            return new fj(this.c, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fj fjVar = (fj) obj;
            return fi.c.a().a(1, (Object) fjVar.d) + fjVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fj fjVar = (fj) obj;
            fi.c.a().a(epVar, 1, fjVar.d);
            epVar.a(fjVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fj.class);
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
                    aVar.c.add(fi.c.a(eoVar));
                }
            }
        }
    }
}
