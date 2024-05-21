package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Persistor {

    public interface MigrationCallback {
        void onDowngrade(int i, int i2);

        void onUpgrade(int i, int i2);
    }

    public interface Transformation<T> {
        @Nullable
        T transform(int i, int i2, @NonNull byte[] bArr);
    }

    void clearCache();

    boolean delete(Memorable memorable);

    @NonNull
    <T extends Memorable> List<T> extractAll(Class<T> cls);

    @Nullable
    <T extends Memorable> T extractFrom(File file, Class<T> cls) throws IOException;

    @Nullable
    <T extends Memorable> T find(String str, Class<T> cls);

    @NonNull
    <T extends Memorable> List<T> findAll(@NonNull Set<String> set, @NonNull Class<T> cls);

    File getStorageDirectory() throws IllegalStateException;

    void init(int i, MigrationCallback migrationCallback);

    <T extends Memorable> void migrateData(int i, int i2, @NonNull Class<T> cls, @NonNull Transformation<T> transformation);

    boolean save(Memorable memorable);

    boolean save(List<Memorable> list);
}
