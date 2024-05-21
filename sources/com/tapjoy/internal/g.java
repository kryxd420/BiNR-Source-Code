package com.tapjoy.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tapjoy.TapjoyAuctionFlags;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public final class g extends aw implements ba, Closeable {
    private SQLiteDatabase a;
    private final bg b;
    private int c;

    public g(File file, bg bgVar) {
        this.a = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        this.b = bgVar;
        if (this.a.getVersion() != 1) {
            this.a.beginTransaction();
            try {
                this.a.execSQL("CREATE TABLE IF NOT EXISTS List(value BLOB)");
                this.a.setVersion(1);
                this.a.setTransactionSuccessful();
            } finally {
                this.a.endTransaction();
            }
        }
        this.c = a();
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        close();
        super.finalize();
    }

    public final void close() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }

    private int a() {
        Cursor cursor = null;
        try {
            Cursor rawQuery = this.a.rawQuery("SELECT COUNT(1) FROM List", (String[]) null);
            try {
                if (rawQuery.moveToNext()) {
                    int i = rawQuery.getInt(0);
                    a(rawQuery);
                    return i;
                }
                a(rawQuery);
                return 0;
            } catch (Throwable th) {
                Cursor cursor2 = rawQuery;
                th = th;
                cursor = cursor2;
                a(cursor);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            a(cursor);
            throw th;
        }
    }

    public final int size() {
        return this.c;
    }

    public final void clear() {
        this.a.delete("List", TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE, (String[]) null);
        this.c = 0;
    }

    public final boolean offer(Object obj) {
        jt.a(obj);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.b.a(byteArrayOutputStream, obj);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            kd.a(byteArrayOutputStream);
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", byteArray);
            if (this.a.insert("List", (String) null, contentValues) == -1) {
                return false;
            }
            this.c++;
            return true;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (Throwable th) {
            kd.a(byteArrayOutputStream);
            throw th;
        }
    }

    public final Object poll() {
        if (this.c <= 0) {
            return null;
        }
        Object peek = peek();
        b(1);
        return peek;
    }

    public final Object peek() {
        if (this.c > 0) {
            return a(0);
        }
        return null;
    }

    public final Object a(int i) {
        Cursor cursor;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException();
        }
        try {
            cursor = this.a.rawQuery("SELECT value FROM List ORDER BY rowid LIMIT " + i + ",1", (String[]) null);
            try {
                if (cursor.moveToNext()) {
                    byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(0));
                    Object b2 = this.b.b(byteArrayInputStream);
                    kd.a(byteArrayInputStream);
                    a(cursor);
                    return b2;
                }
                throw new NoSuchElementException();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } catch (Throwable th2) {
                th = th2;
                a(cursor);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            a(cursor);
            throw th;
        }
    }

    public final void b(int i) {
        if (i <= 0 || i > this.c) {
            throw new IndexOutOfBoundsException();
        } else if (i == this.c) {
            clear();
        } else {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase = this.a;
                StringBuilder sb = new StringBuilder("SELECT rowid FROM List ORDER BY rowid LIMIT ");
                sb.append(i - 1);
                sb.append(",1");
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
                try {
                    if (rawQuery.moveToNext()) {
                        long j = rawQuery.getLong(0);
                        rawQuery.close();
                        SQLiteDatabase sQLiteDatabase2 = this.a;
                        int delete = sQLiteDatabase2.delete("List", "rowid <= " + j, (String[]) null);
                        this.c = this.c - delete;
                        if (delete == i) {
                            a((Cursor) null);
                            return;
                        }
                        throw new IllegalStateException("Try to delete " + i + ", but deleted " + delete);
                    }
                    throw new IllegalStateException();
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    a(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                a(cursor);
                throw th;
            }
        }
    }

    private static Cursor a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        cursor.close();
        return null;
    }
}
