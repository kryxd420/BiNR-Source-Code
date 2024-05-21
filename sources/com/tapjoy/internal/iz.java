package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public final class iz {
    static final Logger a = Logger.getLogger(iz.class.getName());

    private iz() {
    }

    public static ix a(jg jgVar) {
        if (jgVar != null) {
            return new jb(jgVar);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static iw a(jf jfVar) {
        if (jfVar != null) {
            return new ja(jfVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static jf a(final OutputStream outputStream) {
        final jh jhVar = new jh();
        if (outputStream != null) {
            return new jf() {
                public final void a(iv ivVar, long j) {
                    ji.a(ivVar.b, 0, j);
                    while (j > 0) {
                        jhVar.a();
                        jc jcVar = ivVar.a;
                        int min = (int) Math.min(j, (long) (jcVar.c - jcVar.b));
                        outputStream.write(jcVar.a, jcVar.b, min);
                        jcVar.b += min;
                        long j2 = (long) min;
                        j -= j2;
                        ivVar.b -= j2;
                        if (jcVar.b == jcVar.c) {
                            ivVar.a = jcVar.a();
                            jd.a(jcVar);
                        }
                    }
                }

                public final void flush() {
                    outputStream.flush();
                }

                public final void close() {
                    outputStream.close();
                }

                public final String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("out == null");
    }

    public static jg a(final InputStream inputStream) {
        final jh jhVar = new jh();
        if (inputStream != null) {
            return new jg() {
                public final long b(iv ivVar, long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            jhVar.a();
                            jc c = ivVar.c(1);
                            int read = inputStream.read(c.a, c.c, (int) Math.min(j, (long) (8192 - c.c)));
                            if (read == -1) {
                                return -1;
                            }
                            c.c += read;
                            long j2 = (long) read;
                            ivVar.b += j2;
                            return j2;
                        } catch (AssertionError e) {
                            if (iz.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public final void close() {
                    inputStream.close();
                }

                public final String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("in == null");
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
