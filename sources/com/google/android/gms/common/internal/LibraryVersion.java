package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap<>();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ae  */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getVersion(@android.support.annotation.NonNull java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, r0)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r8.zzen
            boolean r0 = r0.containsKey(r9)
            if (r0 == 0) goto L_0x0016
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r8.zzen
            java.lang.Object r9 = r0.get(r9)
            java.lang.String r9 = (java.lang.String) r9
            return r9
        L_0x0016:
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "/%s.properties"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x008d }
            r4 = 0
            r3[r4] = r9     // Catch:{ IOException -> 0x008d }
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ IOException -> 0x008d }
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r3 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r2 = r3.getResourceAsStream(r2)     // Catch:{ IOException -> 0x008d }
            if (r2 == 0) goto L_0x006e
            r0.load(r2)     // Catch:{ IOException -> 0x008d }
            java.lang.String r2 = "version"
            java.lang.String r0 = r0.getProperty(r2, r1)     // Catch:{ IOException -> 0x008d }
            com.google.android.gms.common.internal.GmsLogger r1 = zzel     // Catch:{ IOException -> 0x0069 }
            java.lang.String r2 = "LibraryVersion"
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x0069 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x0069 }
            int r3 = r3 + 12
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0069 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x0069 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0069 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0069 }
            r4.append(r9)     // Catch:{ IOException -> 0x0069 }
            java.lang.String r3 = " version is "
            r4.append(r3)     // Catch:{ IOException -> 0x0069 }
            r4.append(r0)     // Catch:{ IOException -> 0x0069 }
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x0069 }
            r1.v(r2, r3)     // Catch:{ IOException -> 0x0069 }
            r1 = r0
            goto L_0x00ac
        L_0x0069:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x008e
        L_0x006e:
            com.google.android.gms.common.internal.GmsLogger r0 = zzel     // Catch:{ IOException -> 0x008d }
            java.lang.String r2 = "LibraryVersion"
            java.lang.String r3 = "Failed to get app version for libraryName: "
            java.lang.String r4 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x008d }
            int r5 = r4.length()     // Catch:{ IOException -> 0x008d }
            if (r5 == 0) goto L_0x0083
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException -> 0x008d }
            goto L_0x0089
        L_0x0083:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x008d }
            r4.<init>(r3)     // Catch:{ IOException -> 0x008d }
            r3 = r4
        L_0x0089:
            r0.e(r2, r3)     // Catch:{ IOException -> 0x008d }
            goto L_0x00ac
        L_0x008d:
            r0 = move-exception
        L_0x008e:
            com.google.android.gms.common.internal.GmsLogger r2 = zzel
            java.lang.String r3 = "LibraryVersion"
            java.lang.String r4 = "Failed to get app version for libraryName: "
            java.lang.String r5 = java.lang.String.valueOf(r9)
            int r6 = r5.length()
            if (r6 == 0) goto L_0x00a3
            java.lang.String r4 = r4.concat(r5)
            goto L_0x00a9
        L_0x00a3:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r4)
            r4 = r5
        L_0x00a9:
            r2.e(r3, r4, r0)
        L_0x00ac:
            if (r1 != 0) goto L_0x00b9
            java.lang.String r1 = "UNKNOWN"
            com.google.android.gms.common.internal.GmsLogger r0 = zzel
            java.lang.String r2 = "LibraryVersion"
            java.lang.String r3 = ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used"
            r0.d(r2, r3)
        L_0x00b9:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r8.zzen
            r0.put(r9, r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
