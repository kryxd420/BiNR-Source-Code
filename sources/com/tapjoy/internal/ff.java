package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class ff extends el {
    public static final en c = new b();
    public final fe d;
    public final ey e;
    public final fl f;

    public ff(fe feVar, ey eyVar, fl flVar) {
        this(feVar, eyVar, flVar, iy.b);
    }

    public ff(fe feVar, ey eyVar, fl flVar, iy iyVar) {
        super(c, iyVar);
        this.d = feVar;
        this.e = eyVar;
        this.f = flVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ff)) {
            return false;
        }
        ff ffVar = (ff) obj;
        return a().equals(ffVar.a()) && es.a((Object) this.d, (Object) ffVar.d) && es.a((Object) this.e, (Object) ffVar.e) && es.a((Object) this.f, (Object) ffVar.f);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int hashCode = ((((a().hashCode() * 37) + (this.d != null ? this.d.hashCode() : 0)) * 37) + (this.e != null ? this.e.hashCode() : 0)) * 37;
        if (this.f != null) {
            i2 = this.f.hashCode();
        }
        int i3 = hashCode + i2;
        this.b = i3;
        return i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.d != null) {
            sb.append(", info=");
            sb.append(this.d);
        }
        if (this.e != null) {
            sb.append(", app=");
            sb.append(this.e);
        }
        if (this.f != null) {
            sb.append(", user=");
            sb.append(this.f);
        }
        StringBuilder replace = sb.replace(0, 2, "InfoSet{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public fe c;
        public ey d;
        public fl e;

        public final ff b() {
            return new ff(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            ff ffVar = (ff) obj;
            int i = 0;
            int a = (ffVar.d != null ? fe.c.a(1, (Object) ffVar.d) : 0) + (ffVar.e != null ? ey.c.a(2, (Object) ffVar.e) : 0);
            if (ffVar.f != null) {
                i = fl.c.a(3, (Object) ffVar.f);
            }
            return a + i + ffVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            ff ffVar = (ff) obj;
            if (ffVar.d != null) {
                fe.c.a(epVar, 1, ffVar.d);
            }
            if (ffVar.e != null) {
                ey.c.a(epVar, 2, ffVar.e);
            }
            if (ffVar.f != null) {
                fl.c.a(epVar, 3, ffVar.f);
            }
            epVar.a(ffVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, ff.class);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            a aVar = new a();
            long a = eoVar.a();
            while (true) {
                int b = eoVar.b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            aVar.c = (fe) fe.c.a(eoVar);
                            break;
                        case 2:
                            aVar.d = (ey) ey.c.a(eoVar);
                            break;
                        case 3:
                            aVar.e = (fl) fl.c.a(eoVar);
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
