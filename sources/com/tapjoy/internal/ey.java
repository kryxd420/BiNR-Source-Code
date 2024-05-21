package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class ey extends el {
    public static final en c = new b();
    public static final Integer d = 0;
    public final String e;
    public final Integer f;
    public final String g;
    public final String h;
    public final String i;

    public ey(String str, Integer num, String str2, String str3, String str4, iy iyVar) {
        super(c, iyVar);
        this.e = str;
        this.f = num;
        this.g = str2;
        this.h = str3;
        this.i = str4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ey)) {
            return false;
        }
        ey eyVar = (ey) obj;
        return a().equals(eyVar.a()) && es.a((Object) this.e, (Object) eyVar.e) && es.a((Object) this.f, (Object) eyVar.f) && es.a((Object) this.g, (Object) eyVar.g) && es.a((Object) this.h, (Object) eyVar.h) && es.a((Object) this.i, (Object) eyVar.i);
    }

    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int hashCode = ((((((((a().hashCode() * 37) + (this.e != null ? this.e.hashCode() : 0)) * 37) + (this.f != null ? this.f.hashCode() : 0)) * 37) + (this.g != null ? this.g.hashCode() : 0)) * 37) + (this.h != null ? this.h.hashCode() : 0)) * 37;
        if (this.i != null) {
            i3 = this.i.hashCode();
        }
        int i4 = hashCode + i3;
        this.b = i4;
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.e != null) {
            sb.append(", pkgVer=");
            sb.append(this.e);
        }
        if (this.f != null) {
            sb.append(", pkgRev=");
            sb.append(this.f);
        }
        if (this.g != null) {
            sb.append(", dataVer=");
            sb.append(this.g);
        }
        if (this.h != null) {
            sb.append(", installer=");
            sb.append(this.h);
        }
        if (this.i != null) {
            sb.append(", store=");
            sb.append(this.i);
        }
        StringBuilder replace = sb.replace(0, 2, "App{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public Integer d;
        public String e;
        public String f;
        public String g;

        public final ey b() {
            return new ey(this.c, this.d, this.e, this.f, this.g, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            ey eyVar = (ey) obj;
            int i = 0;
            int a = (eyVar.e != null ? en.p.a(1, (Object) eyVar.e) : 0) + (eyVar.f != null ? en.d.a(2, (Object) eyVar.f) : 0) + (eyVar.g != null ? en.p.a(3, (Object) eyVar.g) : 0) + (eyVar.h != null ? en.p.a(4, (Object) eyVar.h) : 0);
            if (eyVar.i != null) {
                i = en.p.a(5, (Object) eyVar.i);
            }
            return a + i + eyVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            ey eyVar = (ey) obj;
            if (eyVar.e != null) {
                en.p.a(epVar, 1, eyVar.e);
            }
            if (eyVar.f != null) {
                en.d.a(epVar, 2, eyVar.f);
            }
            if (eyVar.g != null) {
                en.p.a(epVar, 3, eyVar.g);
            }
            if (eyVar.h != null) {
                en.p.a(epVar, 4, eyVar.h);
            }
            if (eyVar.i != null) {
                en.p.a(epVar, 5, eyVar.i);
            }
            epVar.a(eyVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, ey.class);
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
                            aVar.e = (String) en.p.a(eoVar);
                            break;
                        case 4:
                            aVar.f = (String) en.p.a(eoVar);
                            break;
                        case 5:
                            aVar.g = (String) en.p.a(eoVar);
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
