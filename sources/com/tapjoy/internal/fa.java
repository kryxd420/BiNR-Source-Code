package com.tapjoy.internal;

import com.tapjoy.internal.el;
import java.util.List;

public final class fa extends el {
    public static final en c = new b();
    public final List d;

    public fa(List list, iy iyVar) {
        super(c, iyVar);
        this.d = es.a("events", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fa)) {
            return false;
        }
        fa faVar = (fa) obj;
        return a().equals(faVar.a()) && this.d.equals(faVar.d);
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
            sb.append(", events=");
            sb.append(this.d);
        }
        StringBuilder replace = sb.replace(0, 2, "EventBatch{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public List c = es.a();

        public final fa b() {
            return new fa(this.c, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fa faVar = (fa) obj;
            return ez.c.a().a(1, (Object) faVar.d) + faVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fa faVar = (fa) obj;
            ez.c.a().a(epVar, 1, faVar.d);
            epVar.a(faVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fa.class);
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
                    aVar.c.add(ez.c.a(eoVar));
                }
            }
        }
    }
}
