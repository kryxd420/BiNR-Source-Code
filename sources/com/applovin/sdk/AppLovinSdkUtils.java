package com.applovin.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;
import com.applovin.impl.sdk.e.g;
import com.applovin.impl.sdk.e.n;
import java.io.File;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLovinSdkUtils {
    private static final Handler a = new Handler(Looper.getMainLooper());

    private static void a(ImageView imageView) {
        Drawable drawable;
        if (imageView != null && (drawable = imageView.getDrawable()) != null && (drawable instanceof BitmapDrawable)) {
            ((BitmapDrawable) drawable).getBitmap().recycle();
        }
    }

    public static int dpToPx(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static boolean isValidString(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static int pxToDp(Context context, int i) {
        return (int) (((float) i) / context.getResources().getDisplayMetrics().density);
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(false, runnable);
    }

    public static void runOnUiThread(boolean z, Runnable runnable) {
        if (z || Looper.myLooper() != Looper.getMainLooper()) {
            a.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        a.postDelayed(runnable, j);
    }

    public static void safePopulateImageView(Context context, ImageView imageView, int i, int i2) {
        a(imageView);
        Bitmap a2 = n.a(context, i, i2);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Bitmap bitmap) {
        a(imageView);
        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Uri uri, int i) {
        a(imageView);
        Bitmap a2 = n.a(new File(uri.getPath()), i);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
        }
    }

    public static Map<String, String> toMap(JSONObject jSONObject) throws JSONException {
        return g.a(jSONObject);
    }
}
