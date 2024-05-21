package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.vungle.warren.persistence.Persistor;
import com.vungle.warren.utility.FileUtility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FilePersistor implements Persistor {
    /* access modifiers changed from: private */
    public static final String TAG = "FilePersistor";
    static final String VUNGLE = "vungle";
    private static final String V_PREFIX = "V";
    private Integer currentVersion;
    private File filesDir;

    private interface TransformationReader {
        @NonNull
        byte[] readBytes(@NonNull File file);
    }

    public FilePersistor(File file) {
        this.filesDir = new File(file, VUNGLE);
    }

    public File getStorageDirectory() throws IllegalStateException {
        return makeWorkingDir();
    }

    @NonNull
    private File makeWorkingDir() {
        checkInitialized();
        File file = this.filesDir;
        File file2 = new File(file, V_PREFIX + this.currentVersion);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file2;
    }

    private void checkInitialized() {
        if (this.filesDir == null || this.currentVersion == null) {
            throw new IllegalStateException("Working dir is null");
        }
    }

    public void init(int i, Persistor.MigrationCallback migrationCallback) {
        int i2;
        this.currentVersion = Integer.valueOf(i);
        checkInitialized();
        Version version = (Version) find(Version.ID, Version.class);
        if (version == null || version.getVersion() != i) {
            if (version == null) {
                i2 = 0;
            } else {
                i2 = version.getVersion();
            }
            if (i2 > i) {
                String str = TAG;
                Log.e(str, "downgrade is not supported performing destructive migration, old version = " + i2 + " current = " + i);
                migrationCallback.onDowngrade(i2, i);
            } else {
                migrationCallback.onUpgrade(i2, i);
            }
            save((Memorable) new Version(i, "upgrade/new", System.currentTimeMillis()));
            scanAndDeleteNonRelevant();
            return;
        }
        scanAndDeleteNonRelevant();
    }

    private void scanAndDeleteNonRelevant() {
        final File makeWorkingDir = makeWorkingDir();
        File[] listFiles = this.filesDir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return !makeWorkingDir.getName().equals(file.getName());
            }
        });
        if (listFiles == null) {
            Log.d(TAG, "nothing was found for deletion during scanning");
            return;
        }
        for (File delete : listFiles) {
            try {
                FileUtility.delete(delete);
            } catch (IOException e) {
                Log.d(TAG, "error deletion during scanning " + e.getLocalizedMessage());
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:36:0x0097=Splitter:B:36:0x0097, B:31:0x008f=Splitter:B:31:0x008f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean save(com.vungle.warren.persistence.Memorable r7) {
        /*
            r6 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " Saving "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            java.io.File r0 = r6.getStorageDirectory()
            r1 = 0
            if (r0 == 0) goto L_0x00a2
            boolean r0 = r0.isDirectory()
            if (r0 != 0) goto L_0x0025
            goto L_0x00a2
        L_0x0025:
            r0 = 0
            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.io.File r3 = r6.getStorageDirectory()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r5 = r7.getId()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            r4.append(r5)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r5 = "."
            r4.append(r5)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.Class r5 = r7.getClass()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r5 = r5.getSimpleName()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            r4.append(r5)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r4 = r4.toString()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            r2.<init>(r3, r4)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            boolean r3 = r2.exists()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            if (r3 == 0) goto L_0x0064
            boolean r3 = r2.delete()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            if (r3 == 0) goto L_0x005c
            goto L_0x0064
        L_0x005c:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r2 = "Failed to delete previous version of memorable file!"
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            throw r7     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
        L_0x0064:
            boolean r3 = r2.createNewFile()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            if (r3 == 0) goto L_0x0084
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            byte[] r7 = r7.toByteArray()     // Catch:{ FileNotFoundException -> 0x0081, IOException -> 0x007e, all -> 0x007b }
            r3.write(r7)     // Catch:{ FileNotFoundException -> 0x0081, IOException -> 0x007e, all -> 0x007b }
            r7 = 1
            com.vungle.warren.utility.FileUtility.closeQuietly(r3)
            return r7
        L_0x007b:
            r7 = move-exception
            r0 = r3
            goto L_0x009e
        L_0x007e:
            r7 = move-exception
            r0 = r3
            goto L_0x008f
        L_0x0081:
            r7 = move-exception
            r0 = r3
            goto L_0x0097
        L_0x0084:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            java.lang.String r2 = "Failed to create file for memorable!"
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
            throw r7     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008e }
        L_0x008c:
            r7 = move-exception
            goto L_0x009e
        L_0x008e:
            r7 = move-exception
        L_0x008f:
            r7.printStackTrace()     // Catch:{ all -> 0x008c }
            com.vungle.warren.utility.FileUtility.closeQuietly(r0)
            return r1
        L_0x0096:
            r7 = move-exception
        L_0x0097:
            r7.printStackTrace()     // Catch:{ all -> 0x008c }
            com.vungle.warren.utility.FileUtility.closeQuietly(r0)
            return r1
        L_0x009e:
            com.vungle.warren.utility.FileUtility.closeQuietly(r0)
            throw r7
        L_0x00a2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.persistence.FilePersistor.save(com.vungle.warren.persistence.Memorable):boolean");
    }

    public boolean save(@NonNull List<Memorable> list) {
        if (list == null) {
            return false;
        }
        boolean z = true;
        for (Memorable save : list) {
            z &= save(save);
        }
        return z;
    }

    @NonNull
    public <T extends Memorable> List<T> extractAll(@NonNull Class<T> cls) {
        return extractAll(cls, (FilenameFilter) null);
    }

    @NonNull
    private <T extends Memorable> List<T> extractAll(@NonNull final Class<T> cls, @Nullable final FilenameFilter filenameFilter) {
        File storageDirectory = getStorageDirectory();
        if (storageDirectory == null || !storageDirectory.isDirectory() || !storageDirectory.exists()) {
            return Collections.emptyList();
        }
        File[] listFiles = storageDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                if (!(filenameFilter == null ? true : filenameFilter.accept(file, str)) || !str.endsWith(cls.getSimpleName())) {
                    return false;
                }
                return true;
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : listFiles) {
            if (file != null && file.exists()) {
                try {
                    T extractFrom = extractFrom(file, cls);
                    if (extractFrom != null) {
                        arrayList.add(extractFrom);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.vungle.warren.persistence.Memorable> T extractFrom(@android.support.annotation.NonNull java.io.File r6, @android.support.annotation.NonNull java.lang.Class<T> r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            byte[] r6 = com.vungle.warren.utility.FileUtility.extractBytes(r6)     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            int r1 = r6.length     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            if (r1 != 0) goto L_0x0009
            return r0
        L_0x0009:
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            java.lang.Class<byte[]> r3 = byte[].class
            r4 = 0
            r2[r4] = r3     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            java.lang.reflect.Constructor r7 = r7.getDeclaredConstructor(r2)     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            r1[r4] = r6     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            java.lang.Object r6 = r7.newInstance(r1)     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            com.vungle.warren.persistence.Memorable r6 = (com.vungle.warren.persistence.Memorable) r6     // Catch:{ FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x0020 }
            return r6
        L_0x0020:
            r6 = move-exception
            r6.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.persistence.FilePersistor.extractFrom(java.io.File, java.lang.Class):com.vungle.warren.persistence.Memorable");
    }

    public <T extends Memorable> T find(@NonNull String str, @NonNull Class<T> cls) {
        if (!str.contains(".")) {
            str = str + "." + cls.getSimpleName();
        }
        File file = new File(getStorageDirectory() + "/" + str);
        if (!file.exists()) {
            return null;
        }
        try {
            return extractFrom(file, cls);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public <T extends Memorable> List<T> findAll(@NonNull Set<String> set, Class<T> cls) {
        if (set == null || set.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String find : set) {
            T find2 = find(find, cls);
            if (find2 != null) {
                arrayList.add(find2);
            }
        }
        return arrayList;
    }

    public boolean delete(Memorable memorable) {
        String str = TAG;
        Log.d(str, " Delete " + memorable);
        File file = new File(getStorageDirectory() + File.separator + memorable.getId() + "." + memorable.getClass().getSimpleName());
        return file.exists() && file.delete();
    }

    public void clearCache() {
        File file = this.filesDir;
        if (file.exists()) {
            try {
                FileUtility.delete(file);
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
            }
        }
        makeWorkingDir();
    }

    public <T extends Memorable> void migrateData(int i, int i2, @NonNull final Class<T> cls, @NonNull Persistor.Transformation<T> transformation) {
        if (i < 1) {
            AnonymousClass3 r0 = new TransformationReader() {
                public byte[] readBytes(@NonNull File file) {
                    byte[] bArr;
                    try {
                        bArr = FileUtility.extractBytes(file);
                    } catch (IOException e) {
                        String access$000 = FilePersistor.TAG;
                        Log.e(access$000, "Failed restore " + file.getName() + " " + e.getLocalizedMessage());
                        bArr = null;
                    }
                    if (bArr == null || bArr.length <= 1) {
                        return new byte[0];
                    }
                    return Arrays.copyOfRange(bArr, 1, bArr.length);
                }
            };
            File[] listFiles = this.filesDir.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.endsWith(cls.getSimpleName());
                }
            });
            if (listFiles == null) {
                Log.e(TAG, "Cannot read files during migration for " + cls.getSimpleName());
                return;
            }
            for (File file : listFiles) {
                try {
                    byte[] readBytes = r0.readBytes(file);
                    FileUtility.delete(file);
                    if (readBytes.length != 0) {
                        try {
                            Memorable memorable = (Memorable) transformation.transform(i, i2, readBytes);
                            if (memorable != null) {
                                save(memorable);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Version implements Memorable {
        public static final String ID = "Data";
        private final String reason;
        private final long timestamp;
        private final int versionInt;

        @NonNull
        public String getId() {
            return ID;
        }

        public Version(int i, @NonNull String str, long j) {
            this.versionInt = i;
            this.reason = str;
            this.timestamp = j;
        }

        public Version(byte[] bArr) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.versionInt = wrap.getInt();
            this.reason = MemoryUtils.extractString(wrap);
            this.timestamp = wrap.getLong();
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.versionInt));
                MemoryUtils.write(this.reason, byteArrayOutputStream);
                MemoryUtils.write(Long.valueOf(this.timestamp), byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Version#toByteArray()", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        public int getVersion() {
            return this.versionInt;
        }
    }
}
