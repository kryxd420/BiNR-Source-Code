package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.k;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class n {
    private final String a = "FileManager";
    private final j b;
    private final p c;
    private final Object d;

    n(j jVar) {
        this.b = jVar;
        this.c = jVar.v();
        this.d = new Object();
    }

    private long a(long j) {
        return j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    private void a(long j, Context context) {
        p pVar;
        String str;
        String str2;
        long c2 = (long) c();
        if (c2 == -1) {
            pVar = this.c;
            str = "FileManager";
            str2 = "Cache has no maximum size set; skipping drop...";
        } else if (a(j) > c2) {
            this.c.a("FileManager", "Cache has exceeded maximum size; dropping...");
            g(context);
            this.b.E().a(g.f);
            return;
        } else {
            pVar = this.c;
            str = "FileManager";
            str2 = "Cache is present but under size limit; not dropping...";
        }
        pVar.a(str, str2);
    }

    private boolean a() {
        return ((Boolean) this.b.a(b.bs)).booleanValue();
    }

    private long b() {
        long longValue = ((Long) this.b.a(b.bt)).longValue();
        if (longValue < 0 || !a()) {
            return -1;
        }
        return longValue;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|(0)|36|37) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0052, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0056 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0060 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005a A[SYNTHETIC, Splitter:B:33:0x005a] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0039=Splitter:B:18:0x0039, B:24:0x0049=Splitter:B:24:0x0049} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:36:0x0060=Splitter:B:36:0x0060, B:29:0x0056=Splitter:B:29:0x0056} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(java.io.ByteArrayOutputStream r5, java.io.File r6) {
        /*
            r4 = this;
            com.applovin.impl.sdk.p r0 = r4.c
            java.lang.String r1 = "FileManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Writing resource to filesystem: "
            r2.append(r3)
            java.lang.String r3 = r6.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.a(r1, r2)
            java.lang.Object r0 = r4.d
            monitor-enter(r0)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0048, Throwable -> 0x0038 }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0048, Throwable -> 0x0038 }
            r5.writeTo(r2)     // Catch:{ IOException -> 0x0033, Throwable -> 0x0030, all -> 0x002d }
            r5 = 1
            r2.close()     // Catch:{ Exception -> 0x0056 }
            goto L_0x0056
        L_0x002d:
            r5 = move-exception
            r1 = r2
            goto L_0x0058
        L_0x0030:
            r5 = move-exception
            r1 = r2
            goto L_0x0039
        L_0x0033:
            r5 = move-exception
            r1 = r2
            goto L_0x0049
        L_0x0036:
            r5 = move-exception
            goto L_0x0058
        L_0x0038:
            r5 = move-exception
        L_0x0039:
            com.applovin.impl.sdk.p r6 = r4.c     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = "FileManager"
            java.lang.String r3 = "Unknown failure to write file."
            r6.b(r2, r3, r5)     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0055
        L_0x0044:
            r1.close()     // Catch:{ Exception -> 0x0055 }
            goto L_0x0055
        L_0x0048:
            r5 = move-exception
        L_0x0049:
            com.applovin.impl.sdk.p r6 = r4.c     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = "FileManager"
            java.lang.String r3 = "Unable to write data to file."
            r6.b(r2, r3, r5)     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0055
            goto L_0x0044
        L_0x0055:
            r5 = 0
        L_0x0056:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return r5
        L_0x0058:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0060
        L_0x005e:
            r5 = move-exception
            goto L_0x0061
        L_0x0060:
            throw r5     // Catch:{ all -> 0x005e }
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.n.b(java.io.ByteArrayOutputStream, java.io.File):boolean");
    }

    private boolean b(File file) {
        boolean delete;
        p pVar = this.c;
        pVar.a("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.d) {
            try {
                delete = file.delete();
            } catch (Exception e) {
                p pVar2 = this.c;
                pVar2.b("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", e);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return delete;
    }

    private int c() {
        int intValue = ((Integer) this.b.a(b.bu)).intValue();
        if (intValue < 0 || !a()) {
            return -1;
        }
        return intValue;
    }

    private boolean d(Context context) {
        if (e.a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (e.e() && !((Boolean) this.b.a(b.bx)).booleanValue()) {
            return true;
        }
        this.b.v().c("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    private File e(Context context) {
        return d(context) ? new File(context.getExternalFilesDir((String) null), "al") : new File(context.getCacheDir(), "al");
    }

    private long f(Context context) {
        long j;
        boolean z;
        long b2 = b();
        boolean z2 = b2 != -1;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.d) {
            j = 0;
            for (File next : a(context)) {
                if (!z2 || seconds - TimeUnit.MILLISECONDS.toSeconds(next.lastModified()) <= b2) {
                    z = false;
                } else {
                    p pVar = this.c;
                    pVar.a("FileManager", "File " + next.getName() + " has expired, removing...");
                    b(next);
                    z = true;
                }
                if (z) {
                    this.b.E().a(g.e);
                } else {
                    j += next.length();
                }
            }
        }
        return j;
    }

    private void g(Context context) {
        synchronized (this.d) {
            for (File b2 : a(context)) {
                b(b2);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:60|(0)|65|66) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:20|21|22|23|24) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:25|32|33|(2:35|36)|37|38|39) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:26|42|43|(2:45|46)|47|48|49) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:27|52|53|(2:55|56)|57|58|59) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:11|12|13|14|15|16|17|18|19) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0048 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0063 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x008b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00ac */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00b7 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A[SYNTHETIC, Splitter:B:35:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0088 A[SYNTHETIC, Splitter:B:45:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a9 A[SYNTHETIC, Splitter:B:55:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00b1 A[SYNTHETIC, Splitter:B:62:0x00b1] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0043=Splitter:B:17:0x0043, B:37:0x0063=Splitter:B:37:0x0063, B:65:0x00b7=Splitter:B:65:0x00b7, B:22:0x0048=Splitter:B:22:0x0048, B:47:0x008b=Splitter:B:47:0x008b, B:57:0x00ac=Splitter:B:57:0x00ac} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x0055=Splitter:B:32:0x0055, B:42:0x0067=Splitter:B:42:0x0067, B:52:0x008f=Splitter:B:52:0x008f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.ByteArrayOutputStream a(java.io.File r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.applovin.impl.sdk.p r1 = r8.c
            java.lang.String r2 = "FileManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Reading resource from filesystem: "
            r3.append(r4)
            java.lang.String r4 = r9.getName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.a(r2, r3)
            java.lang.Object r1 = r8.d
            monitor-enter(r1)
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x008d, IOException -> 0x0065, Throwable -> 0x0053, all -> 0x0050 }
            r2.<init>(r9)     // Catch:{ FileNotFoundException -> 0x008d, IOException -> 0x0065, Throwable -> 0x0053, all -> 0x0050 }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ FileNotFoundException -> 0x004e, IOException -> 0x004c, Throwable -> 0x004a }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x004e, IOException -> 0x004c, Throwable -> 0x004a }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ FileNotFoundException -> 0x004e, IOException -> 0x004c, Throwable -> 0x004a }
        L_0x0031:
            int r5 = r4.length     // Catch:{ FileNotFoundException -> 0x004e, IOException -> 0x004c, Throwable -> 0x004a }
            r6 = 0
            int r5 = r2.read(r4, r6, r5)     // Catch:{ FileNotFoundException -> 0x004e, IOException -> 0x004c, Throwable -> 0x004a }
            if (r5 < 0) goto L_0x0045
            r3.write(r4, r6, r5)     // Catch:{ Exception -> 0x003d }
            goto L_0x0031
        L_0x003d:
            r3.close()     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            r2.close()     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            return r0
        L_0x0045:
            r2.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            return r3
        L_0x004a:
            r9 = move-exception
            goto L_0x0055
        L_0x004c:
            r3 = move-exception
            goto L_0x0067
        L_0x004e:
            r9 = move-exception
            goto L_0x008f
        L_0x0050:
            r9 = move-exception
            r2 = r0
            goto L_0x00af
        L_0x0053:
            r9 = move-exception
            r2 = r0
        L_0x0055:
            com.applovin.impl.sdk.p r3 = r8.c     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = "FileManager"
            java.lang.String r5 = "Unknown failure to read file."
            r3.b(r4, r5, r9)     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x0063
            r2.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            return r0
        L_0x0065:
            r3 = move-exception
            r2 = r0
        L_0x0067:
            com.applovin.impl.sdk.p r4 = r8.c     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = "FileManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ae }
            r6.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.String r7 = "Failed to read file: "
            r6.append(r7)     // Catch:{ all -> 0x00ae }
            java.lang.String r9 = r9.getName()     // Catch:{ all -> 0x00ae }
            r6.append(r9)     // Catch:{ all -> 0x00ae }
            r6.append(r3)     // Catch:{ all -> 0x00ae }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x00ae }
            r4.a(r5, r9)     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            return r0
        L_0x008d:
            r9 = move-exception
            r2 = r0
        L_0x008f:
            com.applovin.impl.sdk.p r3 = r8.c     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = "FileManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ae }
            r5.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.String r6 = "File not found. "
            r5.append(r6)     // Catch:{ all -> 0x00ae }
            r5.append(r9)     // Catch:{ all -> 0x00ae }
            java.lang.String r9 = r5.toString()     // Catch:{ all -> 0x00ae }
            r3.b(r4, r9)     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x00ac
            r2.close()     // Catch:{ Exception -> 0x00ac }
        L_0x00ac:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            return r0
        L_0x00ae:
            r9 = move-exception
        L_0x00af:
            if (r2 == 0) goto L_0x00b7
            r2.close()     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00b7
        L_0x00b5:
            r9 = move-exception
            goto L_0x00b8
        L_0x00b7:
            throw r9     // Catch:{ all -> 0x00b5 }
        L_0x00b8:
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.n.a(java.io.File):java.io.ByteArrayOutputStream");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: type inference failed for: r10v4, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r10v17 */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:28|29|30|31|(2:34|35)|36|37|(2:40|41)|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r7.c.a("FileManager", "Loaded resource at " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f5, code lost:
        if (r1 == null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00fd, code lost:
        if (r10 != null) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r10.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0102, code lost:
        return r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x00cc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00d4 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00fa */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0152 A[SYNTHETIC, Splitter:B:100:0x0152] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0157 A[SYNTHETIC, Splitter:B:104:0x0157] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d1 A[SYNTHETIC, Splitter:B:34:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d9 A[SYNTHETIC, Splitter:B:40:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013b A[SYNTHETIC, Splitter:B:81:0x013b] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0140 A[SYNTHETIC, Splitter:B:85:0x0140] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0145 A[SYNTHETIC, Splitter:B:89:0x0145] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x014d A[SYNTHETIC, Splitter:B:96:0x014d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.ByteArrayOutputStream a(java.lang.String r8, java.util.List<java.lang.String> r9, boolean r10) {
        /*
            r7 = this;
            r0 = 0
            if (r10 == 0) goto L_0x0022
            boolean r9 = com.applovin.impl.sdk.e.n.a((java.lang.String) r8, (java.util.List<java.lang.String>) r9)
            if (r9 != 0) goto L_0x0022
            com.applovin.impl.sdk.p r9 = r7.c
            java.lang.String r10 = "FileManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Domain is not whitelisted, skipping precache for url: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r9.a(r10, r8)
            return r0
        L_0x0022:
            com.applovin.impl.sdk.j r9 = r7.b
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r10 = com.applovin.impl.sdk.b.b.dI
            java.lang.Object r9 = r9.a(r10)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x004f
            java.lang.String r9 = "https://"
            boolean r9 = r8.contains(r9)
            if (r9 != 0) goto L_0x004f
            com.applovin.impl.sdk.j r9 = r7.b
            com.applovin.impl.sdk.p r9 = r9.v()
            java.lang.String r10 = "FileManager"
            java.lang.String r1 = "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting..."
            r9.c(r10, r1)
            java.lang.String r9 = "http://"
            java.lang.String r10 = "https://"
            java.lang.String r8 = r8.replace(r9, r10)
        L_0x004f:
            com.applovin.impl.sdk.p r9 = r7.c
            java.lang.String r10 = "FileManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Loading "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = "..."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r9.a(r10, r1)
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x011d, all -> 0x0119 }
            r9.<init>()     // Catch:{ IOException -> 0x011d, all -> 0x0119 }
            java.net.URL r10 = new java.net.URL     // Catch:{ IOException -> 0x0116, all -> 0x0113 }
            r10.<init>(r8)     // Catch:{ IOException -> 0x0116, all -> 0x0113 }
            java.net.URLConnection r10 = r10.openConnection()     // Catch:{ IOException -> 0x0116, all -> 0x0113 }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ IOException -> 0x0116, all -> 0x0113 }
            com.applovin.impl.sdk.j r1 = r7.b     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            com.applovin.impl.sdk.b.b<java.lang.Integer> r2 = com.applovin.impl.sdk.b.b.dG     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r10.setConnectTimeout(r1)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            com.applovin.impl.sdk.j r1 = r7.b     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            com.applovin.impl.sdk.b.b<java.lang.Integer> r2 = com.applovin.impl.sdk.b.b.dH     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            java.lang.Object r1 = r1.a(r2)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r10.setReadTimeout(r1)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r1 = 1
            r10.setDefaultUseCaches(r1)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r10.setUseCaches(r1)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r2 = 0
            r10.setAllowUserInteraction(r2)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r10.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            int r1 = r10.getResponseCode()     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r3 = 200(0xc8, float:2.8E-43)
            if (r1 < r3) goto L_0x0105
            r3 = 300(0x12c, float:4.2E-43)
            if (r1 < r3) goto L_0x00b9
            goto L_0x0105
        L_0x00b9:
            java.io.InputStream r1 = r10.getInputStream()     // Catch:{ IOException -> 0x0110, all -> 0x010e }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x0103 }
        L_0x00c1:
            int r4 = r3.length     // Catch:{ IOException -> 0x0103 }
            int r4 = r1.read(r3, r2, r4)     // Catch:{ IOException -> 0x0103 }
            if (r4 < 0) goto L_0x00dd
            r9.write(r3, r2, r4)     // Catch:{ Exception -> 0x00cc }
            goto L_0x00c1
        L_0x00cc:
            r9.close()     // Catch:{ Exception -> 0x00cf }
        L_0x00cf:
            if (r1 == 0) goto L_0x00d4
            r1.close()     // Catch:{ Exception -> 0x00d4 }
        L_0x00d4:
            r9.close()     // Catch:{ Exception -> 0x00d7 }
        L_0x00d7:
            if (r10 == 0) goto L_0x00dc
            r10.disconnect()     // Catch:{ Exception -> 0x00dc }
        L_0x00dc:
            return r0
        L_0x00dd:
            com.applovin.impl.sdk.p r2 = r7.c     // Catch:{ IOException -> 0x0103 }
            java.lang.String r3 = "FileManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0103 }
            r4.<init>()     // Catch:{ IOException -> 0x0103 }
            java.lang.String r5 = "Loaded resource at "
            r4.append(r5)     // Catch:{ IOException -> 0x0103 }
            r4.append(r8)     // Catch:{ IOException -> 0x0103 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0103 }
            r2.a(r3, r4)     // Catch:{ IOException -> 0x0103 }
            if (r1 == 0) goto L_0x00fa
            r1.close()     // Catch:{ Exception -> 0x00fa }
        L_0x00fa:
            r9.close()     // Catch:{ Exception -> 0x00fd }
        L_0x00fd:
            if (r10 == 0) goto L_0x0102
            r10.disconnect()     // Catch:{ Exception -> 0x0102 }
        L_0x0102:
            return r9
        L_0x0103:
            r2 = move-exception
            goto L_0x0121
        L_0x0105:
            r9.close()     // Catch:{ Exception -> 0x0108 }
        L_0x0108:
            if (r10 == 0) goto L_0x010d
            r10.disconnect()     // Catch:{ Exception -> 0x010d }
        L_0x010d:
            return r0
        L_0x010e:
            r8 = move-exception
            goto L_0x014b
        L_0x0110:
            r2 = move-exception
            r1 = r0
            goto L_0x0121
        L_0x0113:
            r8 = move-exception
            r10 = r0
            goto L_0x014b
        L_0x0116:
            r2 = move-exception
            r10 = r0
            goto L_0x0120
        L_0x0119:
            r8 = move-exception
            r9 = r0
            r10 = r9
            goto L_0x014b
        L_0x011d:
            r2 = move-exception
            r9 = r0
            r10 = r9
        L_0x0120:
            r1 = r10
        L_0x0121:
            com.applovin.impl.sdk.p r3 = r7.c     // Catch:{ all -> 0x0149 }
            java.lang.String r4 = "FileManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0149 }
            r5.<init>()     // Catch:{ all -> 0x0149 }
            java.lang.String r6 = "Error loading "
            r5.append(r6)     // Catch:{ all -> 0x0149 }
            r5.append(r8)     // Catch:{ all -> 0x0149 }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x0149 }
            r3.b(r4, r8, r2)     // Catch:{ all -> 0x0149 }
            if (r1 == 0) goto L_0x013e
            r1.close()     // Catch:{ Exception -> 0x013e }
        L_0x013e:
            if (r9 == 0) goto L_0x0143
            r9.close()     // Catch:{ Exception -> 0x0143 }
        L_0x0143:
            if (r10 == 0) goto L_0x0148
            r10.disconnect()     // Catch:{ Exception -> 0x0148 }
        L_0x0148:
            return r0
        L_0x0149:
            r8 = move-exception
            r0 = r1
        L_0x014b:
            if (r0 == 0) goto L_0x0150
            r0.close()     // Catch:{ Exception -> 0x0150 }
        L_0x0150:
            if (r9 == 0) goto L_0x0155
            r9.close()     // Catch:{ Exception -> 0x0155 }
        L_0x0155:
            if (r10 == 0) goto L_0x015a
            r10.disconnect()     // Catch:{ Exception -> 0x015a }
        L_0x015a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.n.a(java.lang.String, java.util.List, boolean):java.io.ByteArrayOutputStream");
    }

    public File a(String str, Context context, boolean z) {
        File file;
        if (!k.b(str)) {
            this.b.v().a("FileManager", "Nothing to look up, skipping...");
            return null;
        }
        p pVar = this.c;
        pVar.a("FileManager", "Looking up cached resource: " + str);
        if (!d(context) && !z) {
            return null;
        }
        if (str.contains("icon")) {
            str = str.replace("/", "_").replace(".", "_");
        }
        synchronized (this.d) {
            File e = e(context);
            file = new File(e, str);
            try {
                e.mkdirs();
            } catch (Exception unused) {
                return null;
            }
        }
        return file;
    }

    public String a(Context context, String str, String str2, List<String> list, boolean z, com.applovin.impl.sdk.c.e eVar) throws MalformedURLException {
        return a(context, str, str2, list, z, false, eVar);
    }

    public String a(Context context, String str, String str2, List<String> list, boolean z, boolean z2, com.applovin.impl.sdk.c.e eVar) throws MalformedURLException {
        if (!k.b(str)) {
            this.b.v().a("FileManager", "Nothing to cache, skipping...");
            return null;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (k.b(lastPathSegment) && k.b(str2)) {
            lastPathSegment = str2 + lastPathSegment;
        }
        File a2 = a(lastPathSegment, context, false);
        if (!a(a2, str, list, z, eVar)) {
            return null;
        }
        this.c.a("FileManager", "Caching succeeded for file " + lastPathSegment);
        return z2 ? Uri.fromFile(a2).toString() : lastPathSegment;
    }

    public List<File> a(Context context) {
        List<File> asList;
        File e = e(context);
        if (!e.isDirectory()) {
            return new ArrayList(0);
        }
        synchronized (this.d) {
            asList = Arrays.asList(e.listFiles());
        }
        return asList;
    }

    public boolean a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        if (file == null) {
            return false;
        }
        p pVar = this.c;
        pVar.a("FileManager", "Caching " + file.getAbsolutePath() + "...");
        if (byteArrayOutputStream == null || byteArrayOutputStream.size() <= 0) {
            p pVar2 = this.c;
            pVar2.c("FileManager", "No data for " + file.getAbsolutePath());
            return false;
        } else if (!b(byteArrayOutputStream, file)) {
            p pVar3 = this.c;
            pVar3.d("FileManager", "Unable to cache " + file.getAbsolutePath());
            return false;
        } else {
            p pVar4 = this.c;
            pVar4.a("FileManager", "Caching completed for " + file);
            return true;
        }
    }

    public boolean a(File file, String str, List<String> list, com.applovin.impl.sdk.c.e eVar) {
        return a(file, str, list, true, eVar);
    }

    public boolean a(File file, String str, List<String> list, boolean z, com.applovin.impl.sdk.c.e eVar) {
        if (file == null || !file.exists() || file.isDirectory()) {
            ByteArrayOutputStream a2 = a(str, list, z);
            if (!(eVar == null || a2 == null)) {
                eVar.a((long) a2.size());
            }
            return a(a2, file);
        }
        p v = this.b.v();
        v.a("FileManager", "File exists for " + str);
        if (eVar == null) {
            return true;
        }
        eVar.b(file.length());
        return true;
    }

    public boolean a(String str, Context context) {
        boolean b2;
        synchronized (this.d) {
            b2 = b(str, context, false);
        }
        return b2;
    }

    public boolean b(Context context) {
        if (((Boolean) this.b.a(b.by)).booleanValue()) {
            try {
                a(".nomedia", context, false);
                File file = new File(e(context), ".nomedia");
                if (file.exists()) {
                    return true;
                }
                p v = this.b.v();
                v.a("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                return file.createNewFile();
            } catch (Exception e) {
                this.b.v().a("FileManager", "Failed to create nomedia file", (Throwable) e);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.d) {
            File a2 = a(str, context, z);
            z2 = a2 != null && a2.exists() && !a2.isDirectory();
        }
        return z2;
    }

    public void c(Context context) {
        try {
            if (!a()) {
                return;
            }
            if (!this.b.c()) {
                this.c.d("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                return;
            }
            this.c.a("FileManager", "Compacting cache...");
            synchronized (this.d) {
                a(f(context), context);
            }
        } catch (Exception e) {
            this.c.b("FileManager", "Caught exception while compacting cache!", e);
            this.b.w().a((b<?>) b.bs, (Object) false);
            this.b.w().a();
        }
    }
}
