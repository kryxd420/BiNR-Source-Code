package com.tonyodev.fetch;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.tonyodev.fetch.exception.EnqueueException;

final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String COLUMN_DOWNLOADED_BYTES = "_written_bytes";
    private static final String COLUMN_ERROR = "_error";
    private static final String COLUMN_FILEPATH = "_file_path";
    private static final String COLUMN_FILE_SIZE = "_file_size";
    private static final String COLUMN_HEADERS = "_headers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRIORITY = "_priority";
    private static final String COLUMN_STATUS = "_status";
    private static final String COLUMN_URL = "_url";
    private static final String DB_NAME = "com_tonyodev_fetch.db";
    static final int EMPTY_COLUMN_VALUE = -1;
    static final int INDEX_COLUMN_DOWNLOADED_BYTES = 5;
    static final int INDEX_COLUMN_ERROR = 7;
    static final int INDEX_COLUMN_FILEPATH = 2;
    static final int INDEX_COLUMN_FILE_SIZE = 6;
    static final int INDEX_COLUMN_HEADERS = 4;
    static final int INDEX_COLUMN_ID = 0;
    static final int INDEX_COLUMN_PRIORITY = 8;
    static final int INDEX_COLUMN_STATUS = 3;
    static final int INDEX_COLUMN_URL = 1;
    private static final String TABLE_NAME = "requests";
    private static final int VERSION = 2;
    private static DatabaseHelper databaseHelper;
    private final SQLiteDatabase db = getWritableDatabase();
    private boolean loggingEnabled = true;

    /* access modifiers changed from: package-private */
    public String getInsertStatementClose() {
        return ";";
    }

    /* access modifiers changed from: package-private */
    public String getInsertStatementOpen() {
        return "INSERT INTO requests ( _id, _url, _file_path, _status, _headers, _written_bytes, _file_size, _error, _priority ) VALUES ";
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE requests ( _id INTEGER PRIMARY KEY NOT NULL, _url TEXT NOT NULL, _file_path TEXT NOT NULL, _status INTEGER NOT NULL, _headers TEXT NOT NULL, _written_bytes INTEGER NOT NULL, _file_size INTEGER NOT NULL, _error INTEGER NOT NULL, _priority INTEGER NOT NULL, unique( _file_path ) )");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1) {
            sQLiteDatabase.execSQL("CREATE UNIQUE INDEX table_unique ON requests ( _file_path)");
        }
    }

    static synchronized DatabaseHelper getInstance(Context context) {
        synchronized (DatabaseHelper.class) {
            if (databaseHelper != null) {
                DatabaseHelper databaseHelper2 = databaseHelper;
                return databaseHelper2;
            } else if (context != null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext());
                DatabaseHelper databaseHelper3 = databaseHelper;
                return databaseHelper3;
            } else {
                throw new NullPointerException("Context cannot be null");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean insert(long j, String str, String str2, int i, String str3, long j2, long j3, int i2, int i3) {
        return insert(getInsertStatementOpen() + getRowInsertStatement(j, str, str2, i, str3, j2, j3, i2, i3) + getInsertStatementClose());
    }

    /* access modifiers changed from: package-private */
    public String getRowInsertStatement(long j, String str, String str2, int i, String str3, long j2, long j3, int i2, int i3) {
        return "( " + j + ", " + DatabaseUtils.sqlEscapeString(str) + ", " + DatabaseUtils.sqlEscapeString(str2) + ", " + i + ", " + DatabaseUtils.sqlEscapeString(str3) + ", " + j2 + ", " + j3 + ", " + i3 + ", " + i2 + " )";
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean insert(String str) {
        if (str == null) {
            return false;
        }
        try {
            this.db.beginTransaction();
            this.db.execSQL(str);
            this.db.setTransactionSuccessful();
            this.db.endTransaction();
            return true;
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            throw new EnqueueException(e.getMessage(), ErrorUtils.getCode(e.getMessage()));
        } catch (Exception e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
            throw new EnqueueException(e2.getMessage(), ErrorUtils.getCode(e2.getMessage()));
        } catch (SQLiteException e3) {
            if (this.loggingEnabled) {
                e3.printStackTrace();
            }
            throw new EnqueueException(e3.getMessage(), ErrorUtils.getCode(e3.getMessage()));
        } catch (Throwable th) {
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9 A[SYNTHETIC, Splitter:B:32:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b0 A[SYNTHETIC, Splitter:B:37:0x00b0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean pause(long r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            r1.beginTransaction()     // Catch:{ SQLiteException -> 0x004d }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x004d }
            r2.<init>()     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "UPDATE requests SET _status = 902 WHERE _id = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r2.append(r6)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "_status"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " != "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r3 = 903(0x387, float:1.265E-42)
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "_status"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " != "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r3 = 904(0x388, float:1.267E-42)
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x004d }
            r1.execSQL(r2)     // Catch:{ SQLiteException -> 0x004d }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x004d }
            goto L_0x0055
        L_0x004b:
            r6 = move-exception
            goto L_0x00b4
        L_0x004d:
            r1 = move-exception
            boolean r2 = r5.loggingEnabled     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x0055
            r1.printStackTrace()     // Catch:{ all -> 0x004b }
        L_0x0055:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x009f }
            r2.endTransaction()     // Catch:{ SQLiteException -> 0x009f }
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x009f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x009f }
            r3.<init>()     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r4 = "SELECT _id FROM requests WHERE _id = "
            r3.append(r4)     // Catch:{ SQLiteException -> 0x009f }
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = " AND "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = "_status"
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = " = "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            r6 = 902(0x386, float:1.264E-42)
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = r3.toString()     // Catch:{ SQLiteException -> 0x009f }
            android.database.Cursor r6 = r2.rawQuery(r6, r1)     // Catch:{ SQLiteException -> 0x009f }
            if (r6 == 0) goto L_0x0097
            int r7 = r6.getCount()     // Catch:{ SQLiteException -> 0x0094, all -> 0x0091 }
            if (r7 <= 0) goto L_0x0097
            r7 = 1
            r0 = 1
            goto L_0x0097
        L_0x0091:
            r7 = move-exception
            r1 = r6
            goto L_0x00ae
        L_0x0094:
            r7 = move-exception
            r1 = r6
            goto L_0x00a0
        L_0x0097:
            if (r6 == 0) goto L_0x00ac
            r6.close()     // Catch:{ all -> 0x004b }
            goto L_0x00ac
        L_0x009d:
            r7 = move-exception
            goto L_0x00ae
        L_0x009f:
            r7 = move-exception
        L_0x00a0:
            boolean r6 = r5.loggingEnabled     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x00a7
            r7.printStackTrace()     // Catch:{ all -> 0x009d }
        L_0x00a7:
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ all -> 0x004b }
        L_0x00ac:
            monitor-exit(r5)
            return r0
        L_0x00ae:
            if (r1 == 0) goto L_0x00b3
            r1.close()     // Catch:{ all -> 0x004b }
        L_0x00b3:
            throw r7     // Catch:{ all -> 0x004b }
        L_0x00b4:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.DatabaseHelper.pause(long):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9 A[SYNTHETIC, Splitter:B:32:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b0 A[SYNTHETIC, Splitter:B:37:0x00b0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean resume(long r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            r1.beginTransaction()     // Catch:{ SQLiteException -> 0x004d }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x004d }
            r2.<init>()     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "UPDATE requests SET _status = 900 WHERE _id = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r2.append(r6)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "_status"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " != "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r3 = 903(0x387, float:1.265E-42)
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = "_status"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r3 = " != "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            r3 = 904(0x388, float:1.267E-42)
            r2.append(r3)     // Catch:{ SQLiteException -> 0x004d }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x004d }
            r1.execSQL(r2)     // Catch:{ SQLiteException -> 0x004d }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x004d }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x004d }
            goto L_0x0055
        L_0x004b:
            r6 = move-exception
            goto L_0x00b4
        L_0x004d:
            r1 = move-exception
            boolean r2 = r5.loggingEnabled     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x0055
            r1.printStackTrace()     // Catch:{ all -> 0x004b }
        L_0x0055:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x009f }
            r2.endTransaction()     // Catch:{ SQLiteException -> 0x009f }
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x009f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x009f }
            r3.<init>()     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r4 = "SELECT _id FROM requests WHERE _id = "
            r3.append(r4)     // Catch:{ SQLiteException -> 0x009f }
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = " AND "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = "_status"
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = " = "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            r6 = 900(0x384, float:1.261E-42)
            r3.append(r6)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r6 = r3.toString()     // Catch:{ SQLiteException -> 0x009f }
            android.database.Cursor r6 = r2.rawQuery(r6, r1)     // Catch:{ SQLiteException -> 0x009f }
            if (r6 == 0) goto L_0x0097
            int r7 = r6.getCount()     // Catch:{ SQLiteException -> 0x0094, all -> 0x0091 }
            if (r7 <= 0) goto L_0x0097
            r7 = 1
            r0 = 1
            goto L_0x0097
        L_0x0091:
            r7 = move-exception
            r1 = r6
            goto L_0x00ae
        L_0x0094:
            r7 = move-exception
            r1 = r6
            goto L_0x00a0
        L_0x0097:
            if (r6 == 0) goto L_0x00ac
            r6.close()     // Catch:{ all -> 0x004b }
            goto L_0x00ac
        L_0x009d:
            r7 = move-exception
            goto L_0x00ae
        L_0x009f:
            r7 = move-exception
        L_0x00a0:
            boolean r6 = r5.loggingEnabled     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x00a7
            r7.printStackTrace()     // Catch:{ all -> 0x009d }
        L_0x00a7:
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ all -> 0x004b }
        L_0x00ac:
            monitor-exit(r5)
            return r0
        L_0x00ae:
            if (r1 == 0) goto L_0x00b3
            r1.close()     // Catch:{ all -> 0x004b }
        L_0x00b3:
            throw r7     // Catch:{ all -> 0x004b }
        L_0x00b4:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.DatabaseHelper.resume(long):boolean");
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean updateStatus(long j, int i, int i2) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            SQLiteDatabase sQLiteDatabase = this.db;
            sQLiteDatabase.execSQL("UPDATE requests SET _status = " + i + ", " + COLUMN_ERROR + " = " + i2 + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean updateFileBytes(long j, long j2, long j3) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            SQLiteDatabase sQLiteDatabase = this.db;
            sQLiteDatabase.execSQL("UPDATE requests SET _file_size = " + j3 + ", " + COLUMN_DOWNLOADED_BYTES + " = " + j2 + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean delete(long j) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            SQLiteDatabase sQLiteDatabase = this.db;
            sQLiteDatabase.execSQL("DELETE FROM requests WHERE _id = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean deleteAll() {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("DELETE FROM requests");
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean setPriority(long j, int i) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            SQLiteDatabase sQLiteDatabase = this.db;
            sQLiteDatabase.execSQL("UPDATE requests SET _priority = " + i + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0090 A[Catch:{ all -> 0x0089 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0095 A[SYNTHETIC, Splitter:B:32:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c A[SYNTHETIC, Splitter:B:37:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean retry(long r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x0039 }
            r1.beginTransaction()     // Catch:{ SQLiteException -> 0x0039 }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0039 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.String r3 = "UPDATE requests SET _status = 900, _error = -1 WHERE _id = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x0039 }
            r2.append(r6)     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.String r3 = "_status"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.String r3 = " = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x0039 }
            r3 = 904(0x388, float:1.267E-42)
            r2.append(r3)     // Catch:{ SQLiteException -> 0x0039 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x0039 }
            r1.execSQL(r2)     // Catch:{ SQLiteException -> 0x0039 }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x0039 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0039 }
            goto L_0x0041
        L_0x0037:
            r6 = move-exception
            goto L_0x00a0
        L_0x0039:
            r1 = move-exception
            boolean r2 = r5.loggingEnabled     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0041
            r1.printStackTrace()     // Catch:{ all -> 0x0037 }
        L_0x0041:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x008b }
            r2.endTransaction()     // Catch:{ SQLiteException -> 0x008b }
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x008b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x008b }
            r3.<init>()     // Catch:{ SQLiteException -> 0x008b }
            java.lang.String r4 = "SELECT _id FROM requests WHERE _id = "
            r3.append(r4)     // Catch:{ SQLiteException -> 0x008b }
            r3.append(r6)     // Catch:{ SQLiteException -> 0x008b }
            java.lang.String r6 = " AND "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x008b }
            java.lang.String r6 = "_status"
            r3.append(r6)     // Catch:{ SQLiteException -> 0x008b }
            java.lang.String r6 = " = "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x008b }
            r6 = 900(0x384, float:1.261E-42)
            r3.append(r6)     // Catch:{ SQLiteException -> 0x008b }
            java.lang.String r6 = r3.toString()     // Catch:{ SQLiteException -> 0x008b }
            android.database.Cursor r6 = r2.rawQuery(r6, r1)     // Catch:{ SQLiteException -> 0x008b }
            if (r6 == 0) goto L_0x0083
            int r7 = r6.getCount()     // Catch:{ SQLiteException -> 0x0080, all -> 0x007d }
            if (r7 <= 0) goto L_0x0083
            r7 = 1
            r0 = 1
            goto L_0x0083
        L_0x007d:
            r7 = move-exception
            r1 = r6
            goto L_0x009a
        L_0x0080:
            r7 = move-exception
            r1 = r6
            goto L_0x008c
        L_0x0083:
            if (r6 == 0) goto L_0x0098
            r6.close()     // Catch:{ all -> 0x0037 }
            goto L_0x0098
        L_0x0089:
            r7 = move-exception
            goto L_0x009a
        L_0x008b:
            r7 = move-exception
        L_0x008c:
            boolean r6 = r5.loggingEnabled     // Catch:{ all -> 0x0089 }
            if (r6 == 0) goto L_0x0093
            r7.printStackTrace()     // Catch:{ all -> 0x0089 }
        L_0x0093:
            if (r1 == 0) goto L_0x0098
            r1.close()     // Catch:{ all -> 0x0037 }
        L_0x0098:
            monitor-exit(r5)
            return r0
        L_0x009a:
            if (r1 == 0) goto L_0x009f
            r1.close()     // Catch:{ all -> 0x0037 }
        L_0x009f:
            throw r7     // Catch:{ all -> 0x0037 }
        L_0x00a0:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.DatabaseHelper.retry(long):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0095 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009a A[SYNTHETIC, Splitter:B:32:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a1 A[SYNTHETIC, Splitter:B:37:0x00a1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean updateUrl(long r6, java.lang.String r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x003c }
            r1.beginTransaction()     // Catch:{ SQLiteException -> 0x003c }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x003c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x003c }
            r2.<init>()     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r3 = "UPDATE requests SET _url = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r3 = android.database.DatabaseUtils.sqlEscapeString(r8)     // Catch:{ SQLiteException -> 0x003c }
            r2.append(r3)     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r3 = " WHERE "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r3 = "_id"
            r2.append(r3)     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r3 = " = "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x003c }
            r2.append(r6)     // Catch:{ SQLiteException -> 0x003c }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x003c }
            r1.execSQL(r2)     // Catch:{ SQLiteException -> 0x003c }
            android.database.sqlite.SQLiteDatabase r1 = r5.db     // Catch:{ SQLiteException -> 0x003c }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x003c }
            goto L_0x0044
        L_0x0039:
            r6 = move-exception
            goto L_0x00a5
        L_0x003c:
            r1 = move-exception
            boolean r2 = r5.loggingEnabled     // Catch:{ all -> 0x0039 }
            if (r2 == 0) goto L_0x0044
            r1.printStackTrace()     // Catch:{ all -> 0x0039 }
        L_0x0044:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x0090 }
            r2.endTransaction()     // Catch:{ SQLiteException -> 0x0090 }
            android.database.sqlite.SQLiteDatabase r2 = r5.db     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0090 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r4 = "SELECT _id FROM requests WHERE _id = "
            r3.append(r4)     // Catch:{ SQLiteException -> 0x0090 }
            r3.append(r6)     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r6 = " AND "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r6 = "_url"
            r3.append(r6)     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r6 = " = "
            r3.append(r6)     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r6 = android.database.DatabaseUtils.sqlEscapeString(r8)     // Catch:{ SQLiteException -> 0x0090 }
            r3.append(r6)     // Catch:{ SQLiteException -> 0x0090 }
            java.lang.String r6 = r3.toString()     // Catch:{ SQLiteException -> 0x0090 }
            android.database.Cursor r6 = r2.rawQuery(r6, r1)     // Catch:{ SQLiteException -> 0x0090 }
            if (r6 == 0) goto L_0x0088
            int r7 = r6.getCount()     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            if (r7 <= 0) goto L_0x0088
            r7 = 1
            r0 = 1
            goto L_0x0088
        L_0x0082:
            r7 = move-exception
            r1 = r6
            goto L_0x009f
        L_0x0085:
            r7 = move-exception
            r1 = r6
            goto L_0x0091
        L_0x0088:
            if (r6 == 0) goto L_0x009d
            r6.close()     // Catch:{ all -> 0x0039 }
            goto L_0x009d
        L_0x008e:
            r7 = move-exception
            goto L_0x009f
        L_0x0090:
            r7 = move-exception
        L_0x0091:
            boolean r6 = r5.loggingEnabled     // Catch:{ all -> 0x008e }
            if (r6 == 0) goto L_0x0098
            r7.printStackTrace()     // Catch:{ all -> 0x008e }
        L_0x0098:
            if (r1 == 0) goto L_0x009d
            r1.close()     // Catch:{ all -> 0x0039 }
        L_0x009d:
            monitor-exit(r5)
            return r0
        L_0x009f:
            if (r1 == 0) goto L_0x00a4
            r1.close()     // Catch:{ all -> 0x0039 }
        L_0x00a4:
            throw r7     // Catch:{ all -> 0x0039 }
        L_0x00a5:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonyodev.fetch.DatabaseHelper.updateUrl(long, java.lang.String):boolean");
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor get(long j) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = this.db;
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return sQLiteDatabase.rawQuery("SELECT * FROM requests WHERE _id = " + j, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor get() {
        try {
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests", (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor get(long[] jArr) {
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            sb.append('(');
            if (jArr.length > 0) {
                for (long append : jArr) {
                    sb.append(append);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(')');
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _id IN " + sb.toString(), (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor getByStatus(int i) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = this.db;
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return sQLiteDatabase.rawQuery("SELECT * FROM requests WHERE _status = " + i, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor getByUrlAndFilePath(String str, String str2) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = this.db;
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return sQLiteDatabase.rawQuery("SELECT * FROM requests WHERE _url = " + DatabaseUtils.sqlEscapeString(str) + " AND " + COLUMN_FILEPATH + " = " + DatabaseUtils.sqlEscapeString(str2) + " LIMIT 1", (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized Cursor getNextPendingRequest() {
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 AND _priority = 601 LIMIT 1", (String[]) null);
        if (rawQuery != null && rawQuery.getCount() > 0) {
            return rawQuery;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 LIMIT 1", (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean hasPendingRequests() {
        boolean z;
        Cursor rawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _status = 900 LIMIT 1", (String[]) null);
        z = false;
        if (rawQuery != null && rawQuery.getCount() > 0) {
            z = true;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void verifyOK() {
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = 900 WHERE _status = 901");
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void clean() {
        Cursor rawQuery = this.db.rawQuery("SELECT _id, _file_path FROM requests WHERE _status = 903", (String[]) null);
        if (rawQuery != null) {
            if (rawQuery.getCount() < 1) {
                rawQuery.close();
                return;
            }
            try {
                this.db.beginTransaction();
                rawQuery.moveToFirst();
                while (!rawQuery.isAfterLast()) {
                    String string = rawQuery.getString(rawQuery.getColumnIndex(COLUMN_FILEPATH));
                    if (string != null && !Utils.fileExist(string)) {
                        long j = rawQuery.getLong(rawQuery.getColumnIndex(COLUMN_ID));
                        SQLiteDatabase sQLiteDatabase = this.db;
                        sQLiteDatabase.execSQL("UPDATE requests SET _status = 904, _error = -111 WHERE _id = " + j);
                    }
                    rawQuery.moveToNext();
                }
                this.db.setTransactionSuccessful();
            } catch (SQLiteException e) {
                if (this.loggingEnabled) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
            try {
                this.db.endTransaction();
            } catch (SQLiteException e2) {
                if (this.loggingEnabled) {
                    e2.printStackTrace();
                }
            }
            rawQuery.close();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
    }
}
