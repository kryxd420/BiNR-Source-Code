package com.tapjoy.internal;

import com.tapjoy.internal.el;

public final class fg extends el {
    public static final en c = new b();
    public final String d;
    public final String e;
    public final String f;

    public fg(String str, String str2, String str3) {
        this(str, str2, str3, iy.b);
    }

    public fg(String str, String str2, String str3, iy iyVar) {
        super(c, iyVar);
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fg)) {
            return false;
        }
        fg fgVar = (fg) obj;
        return a().equals(fgVar.a()) && es.a((Object) this.d, (Object) fgVar.d) && es.a((Object) this.e, (Object) fgVar.e) && es.a((Object) this.f, (Object) fgVar.f);
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
            sb.append(", fq7Change=");
            sb.append(this.d);
        }
        if (this.e != null) {
            sb.append(", fq30Change=");
            sb.append(this.e);
        }
        if (this.f != null) {
            sb.append(", pushId=");
            sb.append(this.f);
        }
        StringBuilder replace = sb.replace(0, 2, "Meta{");
        replace.append('}');
        return replace.toString();
    }

    public static final class a extends el.a {
        public String c;
        public String d;
        public String e;

        public final fg b() {
            return new fg(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends en {
        public final /* synthetic */ int a(Object obj) {
            fg fgVar = (fg) obj;
            int i = 0;
            int a = (fgVar.d != null ? en.p.a(1, (Object) fgVar.d) : 0) + (fgVar.e != null ? en.p.a(2, (Object) fgVar.e) : 0);
            if (fgVar.f != null) {
                i = en.p.a(3, (Object) fgVar.f);
            }
            return a + i + fgVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            fg fgVar = (fg) obj;
            if (fgVar.d != null) {
                en.p.a(epVar, 1, fgVar.d);
            }
            if (fgVar.e != null) {
                en.p.a(epVar, 2, fgVar.e);
            }
            if (fgVar.f != null) {
                en.p.a(epVar, 3, fgVar.f);
            }
            epVar.a(fgVar.a());
        }

        b() {
            super(ek.LENGTH_DELIMITED, fg.class);
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
