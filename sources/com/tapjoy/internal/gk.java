package com.tapjoy.internal;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class gk extends gj {
    private final File b;
    private final hc c;
    private volatile SQLiteDatabase d;
    private long e;
    private long f;
    private long g;

    public gk(File file, hc hcVar) {
        this.b = file;
        this.c = hcVar;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (this.d != null) {
            kd.a(this.d);
            this.d = null;
        }
        super.finalize();
    }

    /* access modifiers changed from: protected */
    /* JADX INFO: finally extract failed */
    public void a(long j) {
        if (this.d == null) {
            this.d = SQLiteDatabase.openOrCreateDatabase(this.b, (SQLiteDatabase.CursorFactory) null);
            int version = this.d.getVersion();
            switch (version) {
                case 0:
                    this.d.beginTransaction();
                    try {
                        this.d.execSQL("CREATE TABLE IF NOT EXISTS UsageStats(name TEXT,dimensions TEXT,count INTEGER,first_time INTEGER,last_time INTEGER,PRIMARY KEY(name, dimensions))");
                        this.d.execSQL("CREATE TABLE IF NOT EXISTS UsageStatValues(stat_id LONG,name TEXT,count INTEGER,avg REAL,max INTEGER,PRIMARY KEY(stat_id, name))");
                        this.d.setVersion(1);
                        this.d.setTransactionSuccessful();
                        break;
                    } finally {
                        this.d.endTransaction();
                    }
                case 1:
                    break;
                default:
                    throw new SQLException("Unknown database version: " + version);
            }
            Cursor rawQuery = this.d.rawQuery("SELECT MIN(first_time), MAX(last_time) FROM UsageStats", (String[]) null);
            try {
                if (rawQuery.moveToNext()) {
                    this.f = rawQuery.getLong(0);
                    this.g = rawQuery.getLong(1);
                }
                rawQuery.close();
                if (this.f > 0 && this.f + 86400000 <= j) {
                    b();
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.d != null) {
            kd.a(this.d);
            this.d = null;
        }
        this.b.delete();
        this.g = 0;
        this.f = 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01d1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01d5, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r23, java.lang.String r25, @javax.annotation.Nullable java.lang.String r26, @javax.annotation.Nullable java.util.Map r27) {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            r0 = r25
            android.database.sqlite.SQLiteDatabase r4 = r1.d
            if (r4 != 0) goto L_0x000b
            return
        L_0x000b:
            long r4 = r1.e
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0018
            r1.g = r2
            r1.e = r2
            goto L_0x0048
        L_0x0018:
            long r4 = r1.e
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            if (r6 < 0) goto L_0x0031
            long r6 = r1.e
            long r6 = r6 + r4
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0031
            long r4 = r1.g
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0048
            r1.g = r2
            goto L_0x0048
        L_0x0031:
            long r6 = r1.e
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0041
            long r6 = r1.g
            long r6 = r6 - r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x0041
            r1.e = r2
            goto L_0x0048
        L_0x0041:
            r22.b()
            r1.g = r2
            r1.e = r2
        L_0x0048:
            if (r26 != 0) goto L_0x004d
            java.lang.String r4 = ""
            goto L_0x004f
        L_0x004d:
            r4 = r26
        L_0x004f:
            android.database.sqlite.SQLiteDatabase r5 = r1.d
            java.lang.String r6 = "SELECT ROWID,count,first_time,last_time FROM UsageStats WHERE name = ? AND dimensions = ?"
            r7 = 2
            java.lang.String[] r8 = new java.lang.String[r7]
            r9 = 0
            r8[r9] = r0
            r10 = 1
            r8[r10] = r4
            android.database.Cursor r5 = r5.rawQuery(r6, r8)
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch:{ all -> 0x01da }
            r6.<init>()     // Catch:{ all -> 0x01da }
            boolean r8 = r5.moveToNext()     // Catch:{ all -> 0x01da }
            r11 = 3
            r12 = 0
            if (r8 == 0) goto L_0x00b7
            long r13 = r5.getLong(r9)     // Catch:{ all -> 0x01da }
            int r0 = r5.getInt(r10)     // Catch:{ all -> 0x01da }
            long r15 = r5.getLong(r7)     // Catch:{ all -> 0x01da }
            long r17 = r5.getLong(r11)     // Catch:{ all -> 0x01da }
            java.lang.String r4 = "count"
            int r0 = r0 + r10
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x01da }
            r6.put(r4, r0)     // Catch:{ all -> 0x01da }
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 >= 0) goto L_0x0094
            java.lang.String r0 = "first_time"
            java.lang.Long r4 = java.lang.Long.valueOf(r23)     // Catch:{ all -> 0x01da }
            r6.put(r0, r4)     // Catch:{ all -> 0x01da }
        L_0x0094:
            int r0 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a1
            java.lang.String r0 = "last_time"
            java.lang.Long r2 = java.lang.Long.valueOf(r23)     // Catch:{ all -> 0x01da }
            r6.put(r0, r2)     // Catch:{ all -> 0x01da }
        L_0x00a1:
            android.database.sqlite.SQLiteDatabase r0 = r1.d     // Catch:{ all -> 0x01da }
            java.lang.String r2 = "UsageStats"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            java.lang.String r4 = "ROWID = "
            r3.<init>(r4)     // Catch:{ all -> 0x01da }
            r3.append(r13)     // Catch:{ all -> 0x01da }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01da }
            r0.update(r2, r6, r3, r12)     // Catch:{ all -> 0x01da }
            goto L_0x00e4
        L_0x00b7:
            java.lang.String r8 = "name"
            r6.put(r8, r0)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = "dimensions"
            r6.put(r0, r4)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = "count"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01da }
            r6.put(r0, r4)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = "first_time"
            java.lang.Long r4 = java.lang.Long.valueOf(r23)     // Catch:{ all -> 0x01da }
            r6.put(r0, r4)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = "last_time"
            java.lang.Long r2 = java.lang.Long.valueOf(r23)     // Catch:{ all -> 0x01da }
            r6.put(r0, r2)     // Catch:{ all -> 0x01da }
            android.database.sqlite.SQLiteDatabase r0 = r1.d     // Catch:{ all -> 0x01da }
            java.lang.String r2 = "UsageStats"
            long r13 = r0.insert(r2, r12, r6)     // Catch:{ all -> 0x01da }
        L_0x00e4:
            if (r27 == 0) goto L_0x01d6
            boolean r2 = r27.isEmpty()     // Catch:{ all -> 0x01da }
            if (r2 != 0) goto L_0x01d6
            java.util.Set r0 = r27.entrySet()     // Catch:{ all -> 0x01da }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x01da }
        L_0x00f4:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x01da }
            if (r2 == 0) goto L_0x01d6
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x01da }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x01da }
            java.lang.Object r3 = r2.getValue()     // Catch:{ all -> 0x01da }
            if (r3 == 0) goto L_0x00f4
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x01da }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x01da }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x01da }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01da }
            long r11 = r2.longValue()     // Catch:{ all -> 0x01da }
            java.lang.String[] r2 = new java.lang.String[r7]     // Catch:{ all -> 0x01da }
            java.lang.String r4 = java.lang.Long.toString(r13)     // Catch:{ all -> 0x01da }
            r2[r9] = r4     // Catch:{ all -> 0x01da }
            r2[r10] = r3     // Catch:{ all -> 0x01da }
            android.database.sqlite.SQLiteDatabase r4 = r1.d     // Catch:{ all -> 0x01da }
            java.lang.String r8 = "SELECT ROWID, * FROM UsageStatValues WHERE stat_id = ? AND name = ?"
            android.database.Cursor r2 = r4.rawQuery(r8, r2)     // Catch:{ all -> 0x01da }
            boolean r4 = r2.moveToNext()     // Catch:{ all -> 0x01d1 }
            if (r4 == 0) goto L_0x018e
            long r3 = r2.getLong(r9)     // Catch:{ all -> 0x01d1 }
            r8 = 3
            int r15 = r2.getInt(r8)     // Catch:{ all -> 0x01d1 }
            r7 = 4
            double r16 = r2.getDouble(r7)     // Catch:{ all -> 0x01d1 }
            r7 = 5
            long r18 = r2.getLong(r7)     // Catch:{ all -> 0x01d1 }
            r6.clear()     // Catch:{ all -> 0x01d1 }
            java.lang.String r7 = "count"
            int r15 = r15 + r10
            java.lang.Integer r8 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x01d1 }
            r6.put(r7, r8)     // Catch:{ all -> 0x01d1 }
            java.lang.String r7 = "avg"
            double r9 = (double) r11
            java.lang.Double.isNaN(r9)
            r8 = 0
            double r9 = r9 - r16
            r20 = r13
            double r13 = (double) r15
            java.lang.Double.isNaN(r13)
            double r9 = r9 / r13
            r8 = 0
            double r16 = r16 + r9
            java.lang.Double r8 = java.lang.Double.valueOf(r16)     // Catch:{ all -> 0x01d1 }
            r6.put(r7, r8)     // Catch:{ all -> 0x01d1 }
            int r7 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r7 <= 0) goto L_0x0175
            java.lang.String r7 = "max"
            java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x01d1 }
            r6.put(r7, r8)     // Catch:{ all -> 0x01d1 }
        L_0x0175:
            android.database.sqlite.SQLiteDatabase r7 = r1.d     // Catch:{ all -> 0x01d1 }
            java.lang.String r8 = "UsageStatValues"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d1 }
            java.lang.String r10 = "ROWID = "
            r9.<init>(r10)     // Catch:{ all -> 0x01d1 }
            r9.append(r3)     // Catch:{ all -> 0x01d1 }
            java.lang.String r3 = r9.toString()     // Catch:{ all -> 0x01d1 }
            r4 = 0
            r7.update(r8, r6, r3, r4)     // Catch:{ all -> 0x01d1 }
            r4 = 1
            r8 = 0
            goto L_0x01c5
        L_0x018e:
            r20 = r13
            r6.clear()     // Catch:{ all -> 0x01d1 }
            java.lang.String r4 = "stat_id"
            java.lang.Long r7 = java.lang.Long.valueOf(r20)     // Catch:{ all -> 0x01d1 }
            r6.put(r4, r7)     // Catch:{ all -> 0x01d1 }
            java.lang.String r4 = "name"
            r6.put(r4, r3)     // Catch:{ all -> 0x01d1 }
            java.lang.String r3 = "count"
            r4 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x01d1 }
            r6.put(r3, r7)     // Catch:{ all -> 0x01d1 }
            java.lang.String r3 = "avg"
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x01d1 }
            r6.put(r3, r7)     // Catch:{ all -> 0x01d1 }
            java.lang.String r3 = "max"
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x01d1 }
            r6.put(r3, r7)     // Catch:{ all -> 0x01d1 }
            android.database.sqlite.SQLiteDatabase r3 = r1.d     // Catch:{ all -> 0x01d1 }
            java.lang.String r7 = "UsageStatValues"
            r8 = 0
            r3.insert(r7, r8, r6)     // Catch:{ all -> 0x01d1 }
        L_0x01c5:
            r2.close()     // Catch:{ all -> 0x01da }
            r12 = r8
            r13 = r20
            r7 = 2
            r9 = 0
            r10 = 1
            r11 = 3
            goto L_0x00f4
        L_0x01d1:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x01da }
            throw r0     // Catch:{ all -> 0x01da }
        L_0x01d6:
            r5.close()
            return
        L_0x01da:
            r0 = move-exception
            r5.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gk.a(long, java.lang.String, java.lang.String, java.util.Map):void");
    }

    private void b() {
        HashMap hashMap;
        Set set = gj.a;
        String str = null;
        Cursor rawQuery = this.d.rawQuery("SELECT ROWID, * FROM UsageStats ORDER BY ROWID ASC", (String[]) null);
        try {
            rawQuery = this.d.rawQuery("SELECT * FROM UsageStatValues ORDER BY stat_id ASC", (String[]) null);
            rawQuery.moveToNext();
            while (rawQuery.moveToNext()) {
                int i = 0;
                long j = rawQuery.getLong(0);
                int i2 = 1;
                String string = rawQuery.getString(1);
                String string2 = rawQuery.getString(2);
                String str2 = string2.isEmpty() ? str : string2;
                int i3 = rawQuery.getInt(3);
                long j2 = rawQuery.getLong(4);
                long j3 = rawQuery.getLong(5);
                if (!rawQuery.isAfterLast()) {
                    hashMap = null;
                    while (true) {
                        if (rawQuery.getLong(i) != j) {
                            break;
                        }
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        String string3 = rawQuery.getString(i2);
                        long j4 = rawQuery.getLong(3);
                        long j5 = rawQuery.getLong(4);
                        hashMap.put(string3, Long.valueOf(j4));
                        hashMap.put(string3 + "_max", Long.valueOf(j5));
                        if (!rawQuery.moveToNext()) {
                            break;
                        }
                        i = 0;
                        i2 = 1;
                    }
                } else {
                    hashMap = null;
                }
                if (set != null) {
                    if (set.contains(string)) {
                        str = null;
                    }
                }
                this.c.a(string, str2, i3, j2, j3, (Map) hashMap);
                str = null;
            }
            rawQuery.close();
            this.d.execSQL("DELETE FROM UsageStats");
            this.d.execSQL("DELETE FROM UsageStatValues");
            this.g = 0;
            this.f = 0;
        } catch (Throwable th) {
            throw th;
        } finally {
            rawQuery.close();
        }
    }
}
