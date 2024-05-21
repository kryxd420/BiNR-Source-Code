package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import javax.annotation.Nullable;

public final class hp implements Flushable {
    final Object a = this;
    ba b;
    private final File c;

    public hp(File file) {
        this.c = file;
        try {
            this.b = ax.a((ba) new g(file, new bg() {
                public final /* bridge */ /* synthetic */ void a(OutputStream outputStream, Object obj) {
                    ez.c.a(outputStream, (Object) (ez) obj);
                }

                public final /* synthetic */ Object b(InputStream inputStream) {
                    return (ez) ez.c.a(inputStream);
                }
            }));
        } catch (Exception unused) {
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.c.delete();
        if (this.b instanceof Closeable) {
            try {
                ((Closeable) this.b).close();
            } catch (Exception unused) {
            }
        }
        this.b = new ay(new LinkedList());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void flush() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.a
            monitor-enter(r0)
            com.tapjoy.internal.ba r1 = r2.b     // Catch:{ all -> 0x0016 }
            boolean r1 = r1 instanceof java.io.Flushable     // Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x0014
            com.tapjoy.internal.ba r1 = r2.b     // Catch:{ Exception -> 0x0011 }
            java.io.Flushable r1 = (java.io.Flushable) r1     // Catch:{ Exception -> 0x0011 }
            r1.flush()     // Catch:{ Exception -> 0x0011 }
            goto L_0x0014
        L_0x0011:
            r2.a()     // Catch:{ all -> 0x0016 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hp.flush():void");
    }

    public final int b() {
        int size;
        synchronized (this.a) {
            try {
                size = this.b.size();
            } catch (Exception unused) {
                a();
                return 0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return size;
    }

    public final boolean c() {
        boolean isEmpty;
        synchronized (this.a) {
            try {
                isEmpty = this.b.isEmpty();
            } catch (Exception unused) {
                a();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
        return isEmpty;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.a
            monitor-enter(r0)
            com.tapjoy.internal.ba r1 = r2.b     // Catch:{ Exception -> 0x000b }
            r1.b(r3)     // Catch:{ Exception -> 0x000b }
            goto L_0x000e
        L_0x0009:
            r3 = move-exception
            goto L_0x0010
        L_0x000b:
            r2.a()     // Catch:{ all -> 0x0009 }
        L_0x000e:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            return
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hp.a(int):void");
    }

    @Nullable
    public final ez b(int i) {
        ez ezVar;
        synchronized (this.a) {
            try {
                ezVar = (ez) this.b.a(i);
            } catch (Exception unused) {
                a();
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return ezVar;
    }
}
