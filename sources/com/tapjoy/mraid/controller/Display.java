package com.tapjoy.mraid.controller;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.util.ConfigBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;
import java.util.List;

public class Display extends Abstract {
    private WindowManager c;
    private boolean d = false;
    private int e = -1;
    private int f = -1;
    private ConfigBroadcastReceiver g;
    private float h;
    private Context i;

    public Display(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.i = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c = (WindowManager) context.getSystemService("window");
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        this.h = displayMetrics.density;
    }

    @JavascriptInterface
    public void open(String str, boolean z, boolean z2, boolean z3) {
        TapjoyLog.i("MRAID Display", "open: url: " + str + " back: " + z + " forward: " + z2 + " refresh: " + z3);
        if (!URLUtil.isValidUrl(str)) {
            TapjoyLog.i("MRAID Display", "invalid URL");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            List<ResolveInfo> queryIntentActivities = this.i.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities.size() == 1) {
                this.i.startActivity(intent);
            } else if (queryIntentActivities.size() > 1) {
                ((Activity) this.i).startActivity(Intent.createChooser(intent, TJAdUnitConstants.SHARE_CHOOSE_TITLE));
            } else {
                this.a.raiseError("Invalid url", "open");
            }
        } else {
            this.a.open(str, z, z2, z3);
        }
    }

    @JavascriptInterface
    public void useCustomClose(boolean z) {
        if (z) {
            this.a.removeCloseImageButton();
        } else if (!z) {
            this.a.showCloseImageButton();
        }
    }

    @JavascriptInterface
    public void openMap(String str, boolean z) {
        TapjoyLog.d("MRAID Display", "openMap: url: " + str);
        this.a.openMap(str, z);
    }

    @JavascriptInterface
    public void setOrientationProperties(boolean z, String str) {
        TapjoyLog.d("MRAID Display", "setOrientationProperties: allowOrientationChange: " + Boolean.toString(z) + " forceOrientation: " + str);
        this.a.setOrientationProperties(z, str);
    }

    @JavascriptInterface
    public void playAudio(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3) {
        TapjoyLog.d("MRAID Display", "playAudio: url: " + str + " autoPlay: " + z + " controls: " + z2 + " loop: " + z3 + " position: " + z4 + " startStyle: " + str2 + " stopStyle: " + str3);
        if (!URLUtil.isValidUrl(str)) {
            this.a.raiseError("Invalid url", "playAudio");
        } else {
            this.a.playAudio(str, z, z2, z3, z4, str2, str3);
        }
    }

    @JavascriptInterface
    public void playVideo(String str, boolean z, boolean z2, boolean z3, boolean z4, int[] iArr, String str2, String str3) {
        Abstract.Dimensions dimensions;
        int i2;
        String str4 = str;
        TapjoyLog.d("MRAID Display", "playVideo: url: " + str4 + " audioMuted: " + z + " autoPlay: " + z2 + " controls: " + z3 + " loop: " + z4 + " x: " + iArr[0] + " y: " + iArr[1] + " width: " + iArr[2] + " height: " + iArr[3] + " startStyle: " + str2 + " stopStyle: " + str3);
        if (iArr[0] != -1) {
            Abstract.Dimensions dimensions2 = new Abstract.Dimensions();
            dimensions2.x = iArr[0];
            dimensions2.y = iArr[1];
            dimensions2.width = iArr[2];
            dimensions2.height = iArr[3];
            dimensions2.width = (int) Math.ceil((double) (this.h * ((float) dimensions2.width)));
            dimensions2.height = (int) Math.ceil((double) (this.h * ((float) dimensions2.height)));
            dimensions2.x = (int) (((float) dimensions2.x) * this.h);
            dimensions2.y = (int) (((float) dimensions2.y) * this.h);
            if (dimensions2.height < 0) {
                dimensions2.height = this.a.getHeight();
            }
            if (dimensions2.width < 0) {
                dimensions2.width = this.a.getWidth();
            }
            int[] iArr2 = new int[2];
            this.a.getLocationInWindow(iArr2);
            if (dimensions2.x < 0) {
                dimensions2.x = iArr2[0];
            }
            if (dimensions2.y < 0) {
                dimensions2.y = iArr2[1] - 0;
            }
            dimensions = dimensions2;
        } else {
            dimensions = null;
        }
        if (str4.contains("android.resource")) {
            try {
                i2 = R.raw.class.getField(str4.substring(str4.lastIndexOf("/") + 1, str4.lastIndexOf("."))).getInt((Object) null);
            } catch (Exception e2) {
                e2.printStackTrace();
                i2 = 0;
            }
            str4 = "android.resource://" + this.b.getPackageName() + "/" + i2;
        }
        this.a.playVideo(str4, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
    }

    @JavascriptInterface
    public void close() {
        TapjoyLog.d("MRAID Display", TJAdUnitConstants.String.CLOSE);
        this.a.close();
    }

    @JavascriptInterface
    public void show() {
        TapjoyLog.d("MRAID Display", "show");
        this.a.show();
    }

    public boolean isVisible() {
        return this.a.getVisibility() == 0;
    }

    public String dimensions() {
        return "{ \"top\" :" + ((int) (((float) this.a.getTop()) / this.h)) + ",\"left\" :" + ((int) (((float) this.a.getLeft()) / this.h)) + ",\"bottom\" :" + ((int) (((float) this.a.getBottom()) / this.h)) + ",\"right\" :" + ((int) (((float) this.a.getRight()) / this.h)) + "}";
    }

    public int getOrientation() {
        int i2;
        switch (this.c.getDefaultDisplay().getOrientation()) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
            default:
                i2 = -1;
                break;
        }
        TapjoyLog.d("MRAID Display", "getOrientation: " + i2);
        return i2;
    }

    public String getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        return "{ width: " + ((int) Math.ceil((double) (((float) displayMetrics.widthPixels) / displayMetrics.density))) + ", height: " + ((int) Math.ceil((double) (((float) displayMetrics.heightPixels) / displayMetrics.density))) + "}";
    }

    public String getSize() {
        return this.a.getSize();
    }

    public String getMaxSize() {
        if (!this.d) {
            return getScreenSize();
        }
        return "{ width: " + this.e + ", height: " + this.f + "}";
    }

    public void setMaxSize(int i2, int i3) {
        TapjoyLog.d("MRAID Display", "setMaxSize: " + i2 + AvidJSONUtil.KEY_X + i3);
        this.d = true;
        this.e = i2;
        this.f = i3;
    }

    public void onOrientationChanged(int i2) {
        String str = "window.mraidview.fireChangeEvent({ orientation: " + i2 + "});";
        TapjoyLog.d("MRAID Display", str);
        this.a.injectMraidJavaScript(str);
    }

    public void logHTML(String str) {
        TapjoyLog.d("MRAID Display", str);
    }

    public void stopAllListeners() {
        stopConfigurationListener();
        this.g = null;
    }

    public void stopConfigurationListener() {
        try {
            this.b.unregisterReceiver(this.g);
        } catch (Exception unused) {
        }
    }

    public void startConfigurationListener() {
        try {
            if (this.g == null) {
                this.g = new ConfigBroadcastReceiver(this);
            }
            this.b.registerReceiver(this.g, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        } catch (Exception unused) {
        }
    }
}
