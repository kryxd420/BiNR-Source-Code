package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public abstract class en {
    public static final en c = new en(ek.VARINT, Boolean.class) {
        public final /* bridge */ /* synthetic */ int a(Object obj) {
            return 1;
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(((Boolean) obj).booleanValue() ? 1 : 0);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            int d = eoVar.d();
            if (d == 0) {
                return Boolean.FALSE;
            }
            if (d == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", new Object[]{Integer.valueOf(d)}));
        }
    };
    public static final en d = new en(ek.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                return ep.a(intValue);
            }
            return 10;
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                epVar.c(intValue);
            } else {
                epVar.c((long) intValue);
            }
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Integer.valueOf(eoVar.d());
        }
    };
    public static final en e = new en(ek.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            return ep.a(((Integer) obj).intValue());
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(((Integer) obj).intValue());
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Integer.valueOf(eoVar.d());
        }
    };
    public static final en f = new en(ek.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            return ep.a(ep.b(((Integer) obj).intValue()));
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(ep.b(((Integer) obj).intValue()));
        }

        public final /* synthetic */ Object a(eo eoVar) {
            int d = eoVar.d();
            return Integer.valueOf((-(d & 1)) ^ (d >>> 1));
        }
    };
    public static final en g;
    public static final en h;
    public static final en i = new en(ek.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return ep.a(((Long) obj).longValue());
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(((Long) obj).longValue());
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Long.valueOf(eoVar.e());
        }
    };
    public static final en j = new en(ek.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return ep.a(((Long) obj).longValue());
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(((Long) obj).longValue());
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Long.valueOf(eoVar.e());
        }
    };
    public static final en k = new en(ek.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return ep.a(ep.b(((Long) obj).longValue()));
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.c(ep.b(((Long) obj).longValue()));
        }

        public final /* synthetic */ Object a(eo eoVar) {
            long e = eoVar.e();
            return Long.valueOf((-(e & 1)) ^ (e >>> 1));
        }
    };
    public static final en l;
    public static final en m;
    public static final en n = new en(ek.FIXED32, Float.class) {
        public final /* bridge */ /* synthetic */ int a(Object obj) {
            return 4;
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.d(Float.floatToIntBits(((Float) obj).floatValue()));
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Float.valueOf(Float.intBitsToFloat(eoVar.f()));
        }
    };
    public static final en o = new en(ek.FIXED64, Double.class) {
        public final /* bridge */ /* synthetic */ int a(Object obj) {
            return 8;
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.d(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return Double.valueOf(Double.longBitsToDouble(eoVar.g()));
        }
    };
    public static final en p = new en(ek.LENGTH_DELIMITED, String.class) {
        public final /* synthetic */ int a(Object obj) {
            int i;
            String str = (String) obj;
            int length = str.length();
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                if (charAt >= 128) {
                    if (charAt < 2048) {
                        i3 += 2;
                    } else if (charAt < 55296 || charAt > 57343) {
                        i3 += 3;
                    } else if (charAt <= 56319 && (i = i2 + 1) < length && str.charAt(i) >= 56320 && str.charAt(i) <= 57343) {
                        i3 += 4;
                        i2 = i;
                    }
                    i2++;
                }
                i3++;
                i2++;
            }
            return i3;
        }

        public final /* synthetic */ void a(ep epVar, Object obj) {
            epVar.a.b((String) obj);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return eoVar.a.c(eoVar.h());
        }
    };
    public static final en q = new en(ek.LENGTH_DELIMITED, iy.class) {
        public final /* synthetic */ int a(Object obj) {
            return ((iy) obj).c();
        }

        public final /* bridge */ /* synthetic */ void a(ep epVar, Object obj) {
            epVar.a((iy) obj);
        }

        public final /* synthetic */ Object a(eo eoVar) {
            return eoVar.a.b(eoVar.h());
        }
    };
    final Class a;
    en b;
    private final ek r;

    public abstract int a(Object obj);

    public abstract Object a(eo eoVar);

    public abstract void a(ep epVar, Object obj);

    public en(ek ekVar, Class cls) {
        this.r = ekVar;
        this.a = cls;
    }

    public int a(int i2, Object obj) {
        int a2 = a(obj);
        if (this.r == ek.LENGTH_DELIMITED) {
            a2 += ep.a(a2);
        }
        return a2 + ep.a(ep.a(i2, ek.VARINT));
    }

    public void a(ep epVar, int i2, Object obj) {
        epVar.c(ep.a(i2, this.r));
        if (this.r == ek.LENGTH_DELIMITED) {
            epVar.c(a(obj));
        }
        a(epVar, obj);
    }

    private void a(iw iwVar, Object obj) {
        em.a(obj, "value == null");
        em.a(iwVar, "sink == null");
        a(new ep(iwVar), obj);
    }

    public final byte[] b(Object obj) {
        em.a(obj, "value == null");
        iv ivVar = new iv();
        try {
            a((iw) ivVar, obj);
            return ivVar.g();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void a(OutputStream outputStream, Object obj) {
        em.a(obj, "value == null");
        em.a(outputStream, "stream == null");
        iw a2 = iz.a(iz.a(outputStream));
        a(a2, obj);
        a2.a();
    }

    public final Object a(byte[] bArr) {
        em.a(bArr, "bytes == null");
        iv ivVar = new iv();
        if (bArr != null) {
            return a((ix) ivVar.a(bArr, 0, bArr.length));
        }
        throw new IllegalArgumentException("source == null");
    }

    public final Object a(InputStream inputStream) {
        em.a(inputStream, "stream == null");
        return a(iz.a(iz.a(inputStream)));
    }

    private Object a(ix ixVar) {
        em.a(ixVar, "source == null");
        return a(new eo(ixVar));
    }

    public static String c(Object obj) {
        return obj.toString();
    }

    static {
        AnonymousClass10 r0 = new en(ek.FIXED32, Integer.class) {
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 4;
            }

            public final /* synthetic */ void a(ep epVar, Object obj) {
                epVar.d(((Integer) obj).intValue());
            }

            public final /* synthetic */ Object a(eo eoVar) {
                return Integer.valueOf(eoVar.f());
            }
        };
        g = r0;
        h = r0;
        AnonymousClass14 r02 = new en(ek.FIXED64, Long.class) {
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 8;
            }

            public final /* synthetic */ void a(ep epVar, Object obj) {
                epVar.d(((Long) obj).longValue());
            }

            public final /* synthetic */ Object a(eo eoVar) {
                return Long.valueOf(eoVar.g());
            }
        };
        l = r02;
        m = r02;
    }

    public final en a() {
        en enVar = this.b;
        if (enVar != null) {
            return enVar;
        }
        AnonymousClass6 r0 = new en(this.r, List.class) {
            public final /* synthetic */ int a(int i, Object obj) {
                List list = (List) obj;
                int size = list.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += en.this.a(i, list.get(i3));
                }
                return i2;
            }

            public final /* synthetic */ void a(ep epVar, int i, Object obj) {
                List list = (List) obj;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    en.this.a(epVar, i, list.get(i2));
                }
            }

            public final /* synthetic */ Object a(eo eoVar) {
                return Collections.singletonList(en.this.a(eoVar));
            }

            public final /* synthetic */ void a(ep epVar, Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }

            public final /* synthetic */ int a(Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }
        };
        this.b = r0;
        return r0;
    }

    public static final class a extends IllegalArgumentException {
        public final int a;

        a(int i, Class cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.a = i;
        }
    }
}
