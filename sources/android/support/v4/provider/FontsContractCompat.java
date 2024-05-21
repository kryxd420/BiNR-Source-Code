package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import com.applovin.sdk.AppLovinEventTypes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    };
    /* access modifiers changed from: private */
    public static final Object sLock = new Object();
    /* access modifiers changed from: private */
    @GuardedBy("sLock")
    public static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>> sPendingReplies = new SimpleArrayMap<>();
    /* access modifiers changed from: private */
    public static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    private FontsContractCompat() {
    }

    /* access modifiers changed from: private */
    public static Typeface getFontInternal(Context context, FontRequest fontRequest, int i) {
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, (CancellationSignal) null, fontRequest);
            if (fetchFonts.getStatusCode() == 0) {
                return TypefaceCompat.createFromFontInfo(context, (CancellationSignal) null, fetchFonts.getFonts(), i);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFontSync(final Context context, final FontRequest fontRequest, @Nullable final TextView textView, int i, int i2, final int i3) {
        final String str = fontRequest.getIdentifier() + "-" + i3;
        Typeface typeface = sTypefaceCache.get(str);
        if (typeface != null) {
            return typeface;
        }
        boolean z = i == 0;
        if (z && i2 == -1) {
            return getFontInternal(context, fontRequest, i3);
        }
        AnonymousClass1 r1 = new Callable<Typeface>() {
            public Typeface call() throws Exception {
                Typeface access$000 = FontsContractCompat.getFontInternal(context, fontRequest, i3);
                if (access$000 != null) {
                    FontsContractCompat.sTypefaceCache.put(str, access$000);
                }
                return access$000;
            }
        };
        if (z) {
            try {
                return (Typeface) sBackgroundThread.postAndWait(r1, i2);
            } catch (InterruptedException unused) {
                return null;
            }
        } else {
            final WeakReference weakReference = new WeakReference(textView);
            AnonymousClass2 r5 = new SelfDestructiveThread.ReplyCallback<Typeface>() {
                public void onReply(Typeface typeface) {
                    if (((TextView) weakReference.get()) != null) {
                        textView.setTypeface(typeface, i3);
                    }
                }
            };
            synchronized (sLock) {
                if (sPendingReplies.containsKey(str)) {
                    sPendingReplies.get(str).add(r5);
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(r5);
                sPendingReplies.put(str, arrayList);
                sBackgroundThread.postAndReply(r1, new SelfDestructiveThread.ReplyCallback<Typeface>() {
                    public void onReply(Typeface typeface) {
                        ArrayList arrayList;
                        synchronized (FontsContractCompat.sLock) {
                            arrayList = (ArrayList) FontsContractCompat.sPendingReplies.get(str);
                            FontsContractCompat.sPendingReplies.remove(str);
                        }
                        for (int i = 0; i < arrayList.size(); i++) {
                            ((SelfDestructiveThread.ReplyCallback) arrayList.get(i)).onReply(typeface);
                        }
                    }
                });
                return null;
            }
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontResultStatus {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, (CancellationSignal) null, fontRequest);
                    if (fetchFonts.getStatusCode() != 0) {
                        switch (fetchFonts.getStatusCode()) {
                            case 1:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-2);
                                    }
                                });
                                return;
                            case 2:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            default:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                        }
                    } else {
                        FontInfo[] fonts = fetchFonts.getFonts();
                        if (fonts == null || fonts.length == 0) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(1);
                                }
                            });
                            return;
                        }
                        int length = fonts.length;
                        int i = 0;
                        while (i < length) {
                            FontInfo fontInfo = fonts[i];
                            if (fontInfo.getResultCode() != 0) {
                                final int resultCode = fontInfo.getResultCode();
                                if (resultCode < 0) {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    });
                                    return;
                                } else {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                        }
                                    });
                                    return;
                                }
                            } else {
                                i++;
                            }
                        }
                        final Typeface buildTypeface = FontsContractCompat.buildTypeface(context, (CancellationSignal) null, fonts);
                        if (buildTypeface == null) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                                }
                            });
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    handler2.post(new Runnable() {
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, (FontInfo[]) null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Nullable
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    @VisibleForTesting
    @NonNull
    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Cursor query;
        Uri withAppendedId;
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme(AppLovinEventTypes.USER_VIEWED_CONTENT).authority(str2).build();
        Uri build2 = new Uri.Builder().scheme(AppLovinEventTypes.USER_VIEWED_CONTENT).authority(str2).appendPath("file").build();
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                query = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null, cancellationSignal);
            } else {
                query = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null);
            }
            cursor = query;
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex(Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex(Columns.FILE_ID);
                int columnIndex4 = cursor.getColumnIndex(Columns.TTC_INDEX);
                int columnIndex5 = cursor.getColumnIndex(Columns.WEIGHT);
                int columnIndex6 = cursor.getColumnIndex(Columns.ITALIC);
                while (cursor.moveToNext()) {
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        withAppendedId = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        withAppendedId = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    arrayList2.add(new FontInfo(withAppendedId, i2, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i));
                }
                arrayList = arrayList2;
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
