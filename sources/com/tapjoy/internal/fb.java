package com.tapjoy.internal;

import com.tapjoy.internal.el;
import com.tapjoy.internal.en;

public final class fb extends el {
    public static final en c = new b();
    public static final fc d = fc.APP;
    public final fc e;
    public final String f;
    public final String g;

    public fb(fc fcVar, String str, String str2, iy iyVar) {
        super(c, iyVar);
        this.e = fcVar;
        this.f = str;
        this.g = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fb)) {
            return false;
        }
        fb fbVar = (fb) obj;
        return a().equals(fbVar.a()) && this.e.equals(fbVar.e) && this.f.equals(fbVar.f) && es.a((Object) this.g, (Object) fbVar.g);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = (((((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode()) * 37) + (this.g != null ? this.g.hashCode() : 0);
        this.b = hashCode;
        return hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", type=");
        sb.append(this.e);
        sb.append(", name=");
        sb.append(this.f);
        if (this.g != null) {
            sb.append(", category=");
            sb.append(this.g);
        }
        StringBuilder replace = sb.replace(0, 2, "EventGroup{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public fc c;
        public String d;
        public String e;

        public final fb b() {
            if (this.c != null && this.d != null) {
                return new fb(this.c, this.d, this.e, super.a());
            }
            throw es.a(this.c, "type", this.d, "name");
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fb fbVar = (fb) obj;
            return fc.ADAPTER.a(1, (Object) fbVar.e) + en.p.a(2, (Object) fbVar.f) + (fbVar.g != null ? en.p.a(3, (Object) fbVar.g) : 0) + fbVar.a().c();
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return b(eoVar);
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fb fbVar = (fb) obj;
            fc.ADAPTER.a(epVar, 1, fbVar.e);
            en.p.a(epVar, 2, fbVar.f);
            if (fbVar.g != null) {
                en.p.a(epVar, 3, fbVar.g);
            }
            epVar.a(fbVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fb.class);
        }

        private static fb b(eo eoVar) {
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
                            aVar.e = (String) en.p.a(eoVar);
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
