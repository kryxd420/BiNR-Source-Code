package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class MemoryUtils {
    @Deprecated
    public static void writeString(@Nullable String str, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (str == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        byte[] bytes = str.getBytes();
        byteArrayOutputStream.write(toBytes(bytes.length));
        byteArrayOutputStream.write(bytes);
    }

    public static <T> void write(@Nullable T t, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byte[] bArr;
        if (t == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        if (t instanceof String) {
            bArr = ((String) t).getBytes();
        } else if (t instanceof Number) {
            if (t instanceof Integer) {
                bArr = toBytes(((Integer) t).intValue());
            } else if (t instanceof Long) {
                bArr = toBytes(((Long) t).longValue());
            } else {
                throw new IllegalArgumentException("Value type not supported!");
            }
        } else if (t instanceof Boolean) {
            bArr = new byte[]{((Boolean) t).booleanValue() ? (byte) 1 : 0};
        } else {
            throw new IllegalArgumentException("Value type not supported!");
        }
        byteArrayOutputStream.write(toBytes(bArr.length));
        byteArrayOutputStream.write(bArr);
    }

    @NonNull
    public static String extractString(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return new String(bArr);
    }

    @Nullable
    public static <T> T extract(ByteBuffer byteBuffer, Class<T> cls) {
        int i = byteBuffer.getInt();
        if (i == 0) {
            if (cls == String.class) {
                return "";
            }
            return null;
        } else if (cls == String.class) {
            byte[] bArr = new byte[i];
            byteBuffer.get(bArr);
            return new String(bArr);
        } else if (cls == Integer.class) {
            return Integer.valueOf(byteBuffer.getInt());
        } else {
            if (cls == Long.class) {
                return Long.valueOf(byteBuffer.getLong());
            }
            if (cls == Boolean.class) {
                boolean z = true;
                if (byteBuffer.get() != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
            throw new IllegalArgumentException("Class type " + cls.getCanonicalName() + " not supported!");
        }
    }

    public static void writeStringArray(@NonNull String[] strArr, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(strArr.length));
        for (String writeString : strArr) {
            writeString(writeString, byteArrayOutputStream);
        }
    }

    public static <T> void writeArray(@NonNull T[] tArr, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(tArr.length));
        for (T write : tArr) {
            write(write, byteArrayOutputStream);
        }
    }

    public static String[] extractStringArray(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = extractString(byteBuffer);
        }
        return strArr;
    }

    public static <T> T[] extractArray(ByteBuffer byteBuffer, Class<T> cls) {
        int i = byteBuffer.getInt();
        T[] tArr = (Object[]) Array.newInstance(cls, i);
        for (int i2 = 0; i2 < i; i2++) {
            tArr[i2] = extract(byteBuffer, cls);
        }
        return tArr;
    }

    public static void writeStringMap(@NonNull Map<String, String> map, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            strArr[i] = (String) next.getKey();
            strArr2[i] = (String) next.getValue();
            i++;
        }
        writeStringArray(strArr, byteArrayOutputStream);
        writeStringArray(strArr2, byteArrayOutputStream);
    }

    public static <T> void writeMap(@NonNull Map<String, T> map, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        Object[] objArr = null;
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            strArr[i] = (String) next.getKey();
            if (objArr == null) {
                objArr = (Object[]) Array.newInstance(next.getValue().getClass(), size);
            }
            objArr[i] = next.getValue();
            i++;
        }
        writeArray(strArr, byteArrayOutputStream);
        if (objArr == null) {
            objArr = new Object[0];
        }
        writeArray(objArr, byteArrayOutputStream);
    }

    public static Map<String, String> extractStringMap(ByteBuffer byteBuffer) {
        String[] extractStringArray = extractStringArray(byteBuffer);
        String[] extractStringArray2 = extractStringArray(byteBuffer);
        HashMap hashMap = new HashMap(extractStringArray.length);
        for (int i = 0; i < extractStringArray.length; i++) {
            hashMap.put(extractStringArray[i], extractStringArray2[i]);
        }
        return hashMap;
    }

    public static <T> Map<String, T> extractMap(ByteBuffer byteBuffer, Class<T> cls) {
        String[] strArr = (String[]) extractArray(byteBuffer, String.class);
        Object[] extractArray = extractArray(byteBuffer, cls);
        HashMap hashMap = new HashMap(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            hashMap.put(strArr[i], extractArray[i]);
        }
        return hashMap;
    }

    public static <T extends Memorable> void writeMemorable(@Nullable T t, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (t == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        byte[] byteArray = t.toByteArray();
        byteArrayOutputStream.write(toBytes(byteArray.length));
        byteArrayOutputStream.write(byteArray);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends com.vungle.warren.persistence.Memorable> T extractMemorable(java.nio.ByteBuffer r4, java.lang.Class<T> r5) throws java.lang.NoSuchMethodException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException, java.lang.InstantiationException {
        /*
            int r0 = r4.getInt()
            if (r0 != 0) goto L_0x0008
            r4 = 0
            return r4
        L_0x0008:
            byte[] r0 = new byte[r0]
            r4.get(r0)
            r4 = 1
            java.lang.Class[] r1 = new java.lang.Class[r4]
            java.lang.Class<byte[]> r2 = byte[].class
            r3 = 0
            r1[r3] = r2
            java.lang.reflect.Constructor r5 = r5.getDeclaredConstructor(r1)
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r0
            java.lang.Object r4 = r5.newInstance(r4)
            com.vungle.warren.persistence.Memorable r4 = (com.vungle.warren.persistence.Memorable) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.persistence.MemoryUtils.extractMemorable(java.nio.ByteBuffer, java.lang.Class):com.vungle.warren.persistence.Memorable");
    }

    public static byte[] toBytes(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static byte[] toBytes(long j) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return bArr;
    }
}
