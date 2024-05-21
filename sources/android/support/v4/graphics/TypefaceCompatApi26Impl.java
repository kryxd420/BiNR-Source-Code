package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName(FONT_FAMILY_CLASS);
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method4 = cls.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
            method3 = cls.getMethod(ADD_FONT_FROM_BUFFER_METHOD, new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
            method2 = cls.getMethod(FREEZE_METHOD, new Class[0]);
            method = cls.getMethod(ABORT_CREATION_METHOD, new Class[0]);
            method5 = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(cls, 1).getClass(), Integer.TYPE, Integer.TYPE});
            method5.setAccessible(true);
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            cls = null;
            method5 = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        sFontFamilyCtor = constructor;
        sFontFamily = cls;
        sAddFontFromAssetManager = method4;
        sAddFontFromBuffer = method3;
        sFreeze = method2;
        sAbortCreation = method;
        sCreateFromFamiliesWithDefault = method5;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3) {
        try {
            return ((Boolean) sAddFontFromAssetManager.invoke(obj, new Object[]{context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), null})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) sAddFontFromBuffer.invoke(obj, new Object[]{byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance(sFontFamily, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{newInstance, -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object obj) {
        try {
            return ((Boolean) sFreeze.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean abortCreation(Object obj) {
        try {
            return ((Boolean) sAbortCreation.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        Object newFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            if (!addFontFromAssetManager(context, newFamily, fontFileResourceEntry.getFileName(), 0, fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic() ? 1 : 0)) {
                abortCreation(newFamily);
                return null;
            }
        }
        if (!freeze(newFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(newFamily);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r7 = r11;
        r11 = r10;
        r10 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r9, @android.support.annotation.Nullable android.os.CancellationSignal r10, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r11, int r12) {
        /*
            r8 = this;
            int r0 = r11.length
            r1 = 1
            r2 = 0
            if (r0 >= r1) goto L_0x0006
            return r2
        L_0x0006:
            boolean r0 = isFontFamilyPrivateAPIAvailable()
            if (r0 != 0) goto L_0x005c
            android.support.v4.provider.FontsContractCompat$FontInfo r11 = r8.findBestInfo(r11, r12)
            android.content.ContentResolver r9 = r9.getContentResolver()
            android.net.Uri r12 = r11.getUri()     // Catch:{ IOException -> 0x005b }
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r9 = r9.openFileDescriptor(r12, r0, r10)     // Catch:{ IOException -> 0x005b }
            android.graphics.Typeface$Builder r10 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            java.io.FileDescriptor r12 = r9.getFileDescriptor()     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            r10.<init>(r12)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            int r12 = r11.getWeight()     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            android.graphics.Typeface$Builder r10 = r10.setWeight(r12)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            boolean r11 = r11.isItalic()     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            android.graphics.Typeface$Builder r10 = r10.setItalic(r11)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            android.graphics.Typeface r10 = r10.build()     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            if (r9 == 0) goto L_0x0040
            r9.close()     // Catch:{ IOException -> 0x005b }
        L_0x0040:
            return r10
        L_0x0041:
            r10 = move-exception
            r11 = r2
            goto L_0x004a
        L_0x0044:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r11 = move-exception
            r7 = r11
            r11 = r10
            r10 = r7
        L_0x004a:
            if (r9 == 0) goto L_0x005a
            if (r11 == 0) goto L_0x0057
            r9.close()     // Catch:{ Throwable -> 0x0052 }
            goto L_0x005a
        L_0x0052:
            r9 = move-exception
            r11.addSuppressed(r9)     // Catch:{ IOException -> 0x005b }
            goto L_0x005a
        L_0x0057:
            r9.close()     // Catch:{ IOException -> 0x005b }
        L_0x005a:
            throw r10     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            return r2
        L_0x005c:
            java.util.Map r9 = android.support.v4.provider.FontsContractCompat.prepareFontData(r9, r11, r10)
            java.lang.Object r10 = newFamily()
            int r12 = r11.length
            r0 = 0
            r3 = 0
        L_0x0067:
            if (r0 >= r12) goto L_0x0092
            r4 = r11[r0]
            android.net.Uri r5 = r4.getUri()
            java.lang.Object r5 = r9.get(r5)
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            if (r5 != 0) goto L_0x0078
            goto L_0x008f
        L_0x0078:
            int r3 = r4.getTtcIndex()
            int r6 = r4.getWeight()
            boolean r4 = r4.isItalic()
            boolean r3 = addFontFromBuffer(r10, r5, r3, r6, r4)
            if (r3 != 0) goto L_0x008e
            abortCreation(r10)
            return r2
        L_0x008e:
            r3 = 1
        L_0x008f:
            int r0 = r0 + 1
            goto L_0x0067
        L_0x0092:
            if (r3 != 0) goto L_0x0098
            abortCreation(r10)
            return r2
        L_0x0098:
            boolean r9 = freeze(r10)
            if (r9 != 0) goto L_0x009f
            return r2
        L_0x009f:
            android.graphics.Typeface r9 = createFromFamiliesWithDefault(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, i, str, i2);
        }
        Object newFamily = newFamily();
        if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1)) {
            abortCreation(newFamily);
            return null;
        } else if (!freeze(newFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(newFamily);
        }
    }
}
