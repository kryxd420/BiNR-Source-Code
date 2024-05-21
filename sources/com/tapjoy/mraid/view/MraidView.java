package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.controller.Utility;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class MraidView extends BasicWebView implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final String ACTION_KEY = "action";
    public static final String DIMENSIONS = "expand_dimensions";
    public static final String EXPAND_URL = "expand_url";
    public static final int MRAID_ID = 102;
    public static final String PLAYER_PROPERTIES = "player_properties";
    private static int[] c = {16843039, 16843040};
    private static final String[] d = {".mp4", ".3gp", ".mpg"};
    private static MraidPlayer s;
    /* access modifiers changed from: private */
    public VideoView A;
    /* access modifiers changed from: private */
    public WebChromeClient.CustomViewCallback B;
    /* access modifiers changed from: private */
    public ProgressBar C;
    private Handler D = new Handler() {
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            int i = message.what;
            if (i != 1001) {
                if (i != 1003) {
                    switch (i) {
                        case PointerIconCompat.TYPE_CELL:
                            VIEW_STATE unused = MraidView.this.q = VIEW_STATE.LEFT_BEHIND;
                            break;
                        case PointerIconCompat.TYPE_CROSSHAIR:
                            MraidView.this.playVideoImpl(data);
                            break;
                        case PointerIconCompat.TYPE_TEXT:
                            MraidView.this.playAudioImpl(data);
                            break;
                        case PointerIconCompat.TYPE_VERTICAL_TEXT:
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + data.getString("message") + "\", \"" + data.getString(MraidView.ACTION_KEY) + "\")");
                            break;
                        case 1010:
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) MraidView.this.getLayoutParams();
                            if (marginLayoutParams != null) {
                                MraidView.this.removeCloseImageButton();
                                marginLayoutParams.height = data.getInt("resize_height", marginLayoutParams.height);
                                marginLayoutParams.width = data.getInt("resize_width", marginLayoutParams.width);
                                String str = "window.mraidview.fireChangeEvent({ state: '" + MraidView.this.getState() + "', size: { width: " + ((int) (((float) marginLayoutParams.width) / MraidView.this.i)) + ", height: " + ((int) (((float) marginLayoutParams.height) / MraidView.this.i)) + "}});";
                                TapjoyLog.d("MRAIDView", "resize: injection: " + str);
                                MraidView.this.injectMraidJavaScript(str);
                                MraidView.this.requestLayout();
                                MraidView.c(MraidView.this, data.getString("resize_customClosePostition"));
                                if (MraidView.this.p != PLACEMENT_TYPE.INLINE && MraidView.this.e == customCloseState.OPEN) {
                                    MraidView.this.showCloseImageButton();
                                }
                            }
                            if (MraidView.this.r != null) {
                                MraidView.this.r.onResize();
                                break;
                            }
                            break;
                    }
                } else {
                    MraidView.this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                    MraidView.this.setVisibility(0);
                }
            } else if (AnonymousClass6.a[MraidView.this.q.ordinal()] == 1 && MraidView.this.p != PLACEMENT_TYPE.INLINE) {
                MraidView.f(MraidView.this);
            }
            super.handleMessage(message);
        }
    };
    private boolean E;
    WebViewClient a = new WebViewClient() {
        public final void onLoadResource(WebView webView, String str) {
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (MraidView.this.r != null) {
                MraidView.this.r.onPageStarted(webView, str, bitmap);
            }
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            if (MraidView.this.r != null) {
                MraidView.this.r.onReceivedError(webView, i, str, str2);
            }
            TapjoyLog.d("MRAIDView", "error:" + str);
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onPageFinished(WebView webView, String str) {
            if (MraidView.this.r != null) {
                MraidView.this.r.onPageFinished(webView, str);
            }
            int unused = MraidView.this.l = (int) (((float) MraidView.this.getHeight()) / MraidView.this.i);
            int unused2 = MraidView.this.m = (int) (((float) MraidView.this.getWidth()) / MraidView.this.i);
            MraidView.this.h.init(MraidView.this.i);
            MraidView.this.createCloseImageButton();
            if (MraidView.this.p == PLACEMENT_TYPE.INLINE) {
                MraidView.this.removeCloseImageButton();
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:21|22|23) */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r5 = new android.content.Intent();
            r5.setAction("android.intent.action.VIEW");
            r5.setData(r0);
            r5.addFlags(268435456);
            r4.a.getContext().startActivity(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00af, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b0, code lost:
            return false;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0096 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean shouldOverrideUrlLoading(android.webkit.WebView r5, java.lang.String r6) {
            /*
                r4 = this;
                java.lang.String r0 = "MRAIDView"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "shouldOverrideUrlLoading: "
                r1.<init>(r2)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                com.tapjoy.TapjoyLog.d(r0, r1)
                com.tapjoy.mraid.view.MraidView r0 = com.tapjoy.mraid.view.MraidView.this
                com.tapjoy.mraid.listener.MraidViewListener r0 = r0.r
                r1 = 1
                if (r0 == 0) goto L_0x0029
                com.tapjoy.mraid.view.MraidView r0 = com.tapjoy.mraid.view.MraidView.this
                com.tapjoy.mraid.listener.MraidViewListener r0 = r0.r
                boolean r0 = r0.shouldOverrideUrlLoading(r5, r6)
                if (r0 != r1) goto L_0x0029
                return r1
            L_0x0029:
                android.net.Uri r0 = android.net.Uri.parse(r6)
                r2 = 268435456(0x10000000, float:2.5243549E-29)
                java.lang.String r3 = "mraid"
                boolean r3 = r6.startsWith(r3)     // Catch:{ Exception -> 0x0096 }
                if (r3 == 0) goto L_0x003c
                boolean r5 = super.shouldOverrideUrlLoading(r5, r6)     // Catch:{ Exception -> 0x0096 }
                return r5
            L_0x003c:
                java.lang.String r5 = "tel:"
                boolean r5 = r6.startsWith(r5)     // Catch:{ Exception -> 0x0096 }
                if (r5 == 0) goto L_0x005c
                android.content.Intent r5 = new android.content.Intent     // Catch:{ Exception -> 0x0096 }
                java.lang.String r3 = "android.intent.action.DIAL"
                android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ Exception -> 0x0096 }
                r5.<init>(r3, r6)     // Catch:{ Exception -> 0x0096 }
                r5.addFlags(r2)     // Catch:{ Exception -> 0x0096 }
                com.tapjoy.mraid.view.MraidView r6 = com.tapjoy.mraid.view.MraidView.this     // Catch:{ Exception -> 0x0096 }
                android.content.Context r6 = r6.getContext()     // Catch:{ Exception -> 0x0096 }
                r6.startActivity(r5)     // Catch:{ Exception -> 0x0096 }
                return r1
            L_0x005c:
                java.lang.String r5 = "mailto:"
                boolean r5 = r6.startsWith(r5)     // Catch:{ Exception -> 0x0096 }
                if (r5 == 0) goto L_0x007c
                android.content.Intent r5 = new android.content.Intent     // Catch:{ Exception -> 0x0096 }
                java.lang.String r3 = "android.intent.action.VIEW"
                android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ Exception -> 0x0096 }
                r5.<init>(r3, r6)     // Catch:{ Exception -> 0x0096 }
                r5.addFlags(r2)     // Catch:{ Exception -> 0x0096 }
                com.tapjoy.mraid.view.MraidView r6 = com.tapjoy.mraid.view.MraidView.this     // Catch:{ Exception -> 0x0096 }
                android.content.Context r6 = r6.getContext()     // Catch:{ Exception -> 0x0096 }
                r6.startActivity(r5)     // Catch:{ Exception -> 0x0096 }
                return r1
            L_0x007c:
                android.content.Intent r5 = new android.content.Intent     // Catch:{ Exception -> 0x0096 }
                r5.<init>()     // Catch:{ Exception -> 0x0096 }
                java.lang.String r6 = "android.intent.action.VIEW"
                r5.setAction(r6)     // Catch:{ Exception -> 0x0096 }
                r5.setData(r0)     // Catch:{ Exception -> 0x0096 }
                r5.addFlags(r2)     // Catch:{ Exception -> 0x0096 }
                com.tapjoy.mraid.view.MraidView r6 = com.tapjoy.mraid.view.MraidView.this     // Catch:{ Exception -> 0x0096 }
                android.content.Context r6 = r6.getContext()     // Catch:{ Exception -> 0x0096 }
                r6.startActivity(r5)     // Catch:{ Exception -> 0x0096 }
                return r1
            L_0x0096:
                android.content.Intent r5 = new android.content.Intent     // Catch:{ Exception -> 0x00b0 }
                r5.<init>()     // Catch:{ Exception -> 0x00b0 }
                java.lang.String r6 = "android.intent.action.VIEW"
                r5.setAction(r6)     // Catch:{ Exception -> 0x00b0 }
                r5.setData(r0)     // Catch:{ Exception -> 0x00b0 }
                r5.addFlags(r2)     // Catch:{ Exception -> 0x00b0 }
                com.tapjoy.mraid.view.MraidView r6 = com.tapjoy.mraid.view.MraidView.this     // Catch:{ Exception -> 0x00b0 }
                android.content.Context r6 = r6.getContext()     // Catch:{ Exception -> 0x00b0 }
                r6.startActivity(r5)     // Catch:{ Exception -> 0x00b0 }
                return r1
            L_0x00b0:
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.mraid.view.MraidView.AnonymousClass3.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (TapjoyCache.getInstance() != null) {
                TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(str);
                if (cachedDataForURL == null) {
                    TapjoyLog.d("MRAIDView", "No cached data for " + str);
                } else {
                    WebResourceResponse a2 = MraidView.b(cachedDataForURL);
                    if (a2 != null) {
                        TapjoyLog.d("MRAIDView", "Reading request for " + str + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                        return a2;
                    }
                }
            }
            return super.shouldInterceptRequest(webView, str);
        }
    };
    WebChromeClient b = new WebChromeClient() {
        public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            TapjoyLog.d("MRAIDView", str2);
            return false;
        }

        public final void onCloseWindow(WebView webView) {
            super.onCloseWindow(webView);
            MraidView.f(MraidView.this);
        }

        public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            TapjoyLog.d("MRAIDView", "-- onShowCustomView --");
            super.onShowCustomView(view, customViewCallback);
            WebChromeClient.CustomViewCallback unused = MraidView.this.B = customViewCallback;
            if (view instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) view;
                if ((frameLayout.getFocusedChild() instanceof VideoView) && (MraidView.this.y instanceof Activity)) {
                    Activity activity = (Activity) MraidView.this.y;
                    VideoView unused2 = MraidView.this.A = (VideoView) frameLayout.getFocusedChild();
                    frameLayout.removeView(MraidView.this.A);
                    if (MraidView.this.z == null) {
                        RelativeLayout unused3 = MraidView.this.z = new RelativeLayout(MraidView.this.y);
                        MraidView.this.z.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                        MraidView.this.z.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(13);
                    MraidView.this.A.setLayoutParams(layoutParams);
                    ProgressBar unused4 = MraidView.this.C = new ProgressBar(MraidView.this.y, (AttributeSet) null, 16842874);
                    MraidView.this.C.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.addRule(13);
                    MraidView.this.C.setLayoutParams(layoutParams2);
                    MraidView.this.z.addView(MraidView.this.A);
                    MraidView.this.z.addView(MraidView.this.C);
                    activity.getWindow().addContentView(MraidView.this.z, new ViewGroup.LayoutParams(-1, -1));
                    new Thread(new c()).start();
                    MraidView.this.setVisibility(8);
                    MraidView.this.A.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public final void onPrepared(MediaPlayer mediaPlayer) {
                            TapjoyLog.i("MRAIDView", "** ON PREPARED **");
                            TapjoyLog.i("MRAIDView", "isPlaying: " + mediaPlayer.isPlaying());
                            if (!mediaPlayer.isPlaying()) {
                                mediaPlayer.start();
                            }
                        }
                    });
                    MraidView.this.A.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public final void onCompletion(MediaPlayer mediaPlayer) {
                            TapjoyLog.i("MRAIDView", "** ON COMPLETION **");
                            MraidView.this.videoViewCleanup();
                        }
                    });
                    MraidView.this.A.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                            TapjoyLog.i("MRAIDView", "** ON ERROR **");
                            MraidView.this.videoViewCleanup();
                            return false;
                        }
                    });
                    MraidView.this.A.start();
                }
            }
        }

        public final void onHideCustomView() {
            super.onHideCustomView();
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (MraidView.this.r != null) {
                return MraidView.this.r.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    };
    /* access modifiers changed from: private */
    public customCloseState e = customCloseState.UNKNOWN;
    private boolean f = false;
    private boolean g;
    /* access modifiers changed from: private */
    public Utility h;
    /* access modifiers changed from: private */
    public float i;
    private int j;
    private boolean k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    private int n;
    private int o;
    /* access modifiers changed from: private */
    public PLACEMENT_TYPE p;
    /* access modifiers changed from: private */
    public VIEW_STATE q = VIEW_STATE.DEFAULT;
    /* access modifiers changed from: private */
    public MraidViewListener r;
    private int t = 0;
    private int u = 0;
    private Thread v = null;
    /* access modifiers changed from: private */
    public boolean w = false;
    private int x;
    /* access modifiers changed from: private */
    public Context y;
    /* access modifiers changed from: private */
    public RelativeLayout z;

    public enum Action {
        PLAY_AUDIO,
        PLAY_VIDEO
    }

    public enum PLACEMENT_TYPE {
        INLINE,
        INTERSTITIAL
    }

    public enum VIEW_STATE {
        DEFAULT,
        LEFT_BEHIND,
        OPENED
    }

    public enum customCloseState {
        HIDDEN,
        OPEN,
        UNKNOWN
    }

    public MraidView(Context context, MraidViewListener mraidViewListener) {
        super(context);
        setListener(mraidViewListener);
        this.y = context;
        initialize();
    }

    public void setListener(MraidViewListener mraidViewListener) {
        this.r = mraidViewListener;
    }

    public void removeListener() {
        this.r = null;
    }

    public MraidView(Context context) {
        super(context);
        this.y = context;
        initialize();
    }

    public void setPlacementType(PLACEMENT_TYPE placement_type) {
        if (placement_type.equals(PLACEMENT_TYPE.INLINE) || placement_type.equals(PLACEMENT_TYPE.INTERSTITIAL)) {
            this.p = placement_type;
        } else {
            TapjoyLog.d("MRAIDView", "Incorrect placement type.");
        }
        if (!placement_type.equals(PLACEMENT_TYPE.INLINE)) {
            return;
        }
        if (this.v == null || !this.v.isAlive()) {
            this.v = new Thread(new b());
            this.v.start();
        }
    }

    public PLACEMENT_TYPE getPlacementType() {
        return this.p;
    }

    public void createCloseImageButton() {
        injectMraidJavaScript("window.mraidview.createCss();");
        TapjoyLog.d("MRAIDView", "Creating close button.");
    }

    public void removeCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"hidden\";");
        TapjoyLog.d("MRAIDView", "Removing close button.");
        this.e = customCloseState.HIDDEN;
    }

    public void showCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"visible\";");
        TapjoyLog.d("MRAIDView", "Showing close button.");
        this.e = customCloseState.OPEN;
    }

    public customCloseState getCloseButtonState() {
        return this.e;
    }

    public boolean isMraid() {
        return this.f;
    }

    public void setMaxSize(int i2, int i3) {
        this.h.setMaxSize(i2, i3);
    }

    public void injectMraidJavaScript(String str) {
        if (str != null && this.f) {
            loadUrl("javascript:" + str);
        }
    }

    public void loadUrl(final String str) {
        ((Activity) this.y).runOnUiThread(new Runnable() {
            public final void run() {
                if (!URLUtil.isValidUrl(str)) {
                    MraidView.this.loadDataWithBaseURL((String) null, "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Connection not Established</title></head><h2>Connection Not Properly Established</h2><body></body></html>", "text/html", "utf-8", (String) null);
                } else if (!str.startsWith("javascript:")) {
                    new a(MraidView.this, (byte) 0).execute(new String[]{str});
                } else if (Build.VERSION.SDK_INT >= 19) {
                    try {
                        MraidView.super.evaluateJavascript(str.replaceFirst("javascript:", ""), (ValueCallback) null);
                    } catch (Exception e) {
                        TapjoyLog.e("MRAIDView", "Exception in evaluateJavascript. Device not supported. " + e.toString());
                    }
                } else {
                    MraidView.super.loadUrl(str);
                }
            }
        });
    }

    public void loadUrlStandard(String str) {
        super.loadUrl(str);
    }

    public boolean hasMraidTag(String str) {
        return Pattern.compile("<\\s*script[^>]+mraid\\.js").matcher(str).find();
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        int i2;
        if (str2 != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int indexOf = str2.indexOf("<html>");
            this.f = false;
            int indexOf2 = str2.indexOf("mraid.js");
            if (indexOf2 <= 0 || !hasMraidTag(str2)) {
                stringBuffer.append(str2);
            } else {
                this.f = true;
                int i3 = indexOf2;
                while (true) {
                    if (i3 < 0) {
                        i3 = indexOf2;
                        break;
                    } else if (str2.substring(i3, i3 + 7).equals("<script")) {
                        break;
                    } else {
                        i3--;
                    }
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= str2.length()) {
                        i2 = indexOf2;
                        break;
                    }
                    int i5 = indexOf2 + i4;
                    i2 = i5 + 2;
                    if (str2.substring(i5, i2).equalsIgnoreCase("/>")) {
                        break;
                    }
                    i2 = i5 + 9;
                    if (str2.substring(i5, i2).equalsIgnoreCase("</script>")) {
                        break;
                    }
                    i4++;
                }
                if (indexOf < 0) {
                    TapjoyLog.d("MRAIDView", "wrapping fragment");
                    stringBuffer.append("<html>");
                    stringBuffer.append("<head>");
                    stringBuffer.append("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />");
                    stringBuffer.append("<title>Advertisement</title>");
                    stringBuffer.append("</head>");
                    stringBuffer.append("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">");
                    stringBuffer.append("<div align=\"center\"> ");
                    stringBuffer.append(str2.substring(0, i3));
                    stringBuffer.append("<script type=text/javascript>");
                    String str6 = (String) TapjoyUtil.getResource("mraid.js");
                    if (str6 == null) {
                        str6 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                    }
                    stringBuffer.append(str6);
                    stringBuffer.append("</script>");
                    stringBuffer.append(str2.substring(i2));
                } else {
                    int indexOf3 = str2.indexOf("<head>");
                    if (indexOf3 != -1) {
                        String str7 = (String) TapjoyUtil.getResource("mraid.js");
                        if (str7 == null) {
                            str7 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                        }
                        int i6 = indexOf3 + 6;
                        stringBuffer.append(str2.substring(0, i6));
                        stringBuffer.append("<script type='text/javascript'>");
                        stringBuffer.append(str7);
                        stringBuffer.append("</script>");
                        stringBuffer.append(str2.substring(i6));
                    }
                }
                TapjoyLog.d("MRAIDView", "injected js/mraid.js");
            }
            super.loadDataWithBaseURL(str, stringBuffer.toString(), str3, str4, str5);
        }
    }

    public void clearView() {
        reset();
        super.clearView();
    }

    public void reset() {
        invalidate();
        this.h.deleteOldAds();
        this.h.stopAllListeners();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (this.E) {
            layoutParams.height = this.n;
            layoutParams.width = this.o;
        }
        setVisibility(0);
        requestLayout();
    }

    public MraidView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, c);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize > 0 && dimensionPixelSize2 > 0) {
            this.h.setMaxSize(dimensionPixelSize, dimensionPixelSize2);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[VIEW_STATE.values().length];

        static {
            try {
                a[VIEW_STATE.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static WebResourceResponse b(TapjoyCachedAssetData tapjoyCachedAssetData) {
        if (tapjoyCachedAssetData == null) {
            return null;
        }
        try {
            return new WebResourceResponse(tapjoyCachedAssetData.getMimeType(), "UTF-8", new FileInputStream(tapjoyCachedAssetData.getLocalFilePath()));
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public boolean videoPlaying() {
        return this.A != null;
    }

    public void videoViewCleanup() {
        if (this.z != null) {
            ((ViewGroup) this.z.getParent()).removeView(this.z);
            this.z.setVisibility(8);
            this.z = null;
        }
        try {
            if (this.A != null) {
                this.A.stopPlayback();
            }
            if (this.B != null) {
                this.B.onCustomViewHidden();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.A = null;
        this.B = null;
        setVisibility(0);
        loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('videoend')}catch(e){}");
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void initialize() {
        setPlacementType(PLACEMENT_TYPE.INTERSTITIAL);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.i = displayMetrics.density;
        this.g = false;
        this.h = new Utility(this, getContext());
        addJavascriptInterface(this.h, "MRAIDUtilityControllerBridge");
        setWebViewClient(this.a);
        setWebChromeClient(this.b);
        this.j = getContentViewHeight();
        if (getViewTreeObserver() != null) {
            getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.t = windowManager.getDefaultDisplay().getWidth();
        this.u = windowManager.getDefaultDisplay().getHeight();
        if (getContext() instanceof Activity) {
            this.x = ((Activity) getContext()).getRequestedOrientation();
        }
    }

    class b implements Runnable {
        public b() {
        }

        public final void run() {
            while (!MraidView.this.w) {
                try {
                    Thread.sleep(250);
                    MraidView.m(MraidView.this);
                } catch (Exception unused) {
                }
            }
        }
    }

    class c implements Runnable {
        public c() {
        }

        public final void run() {
            int i = 0;
            while (MraidView.this.A != null && !MraidView.this.A.isPlaying()) {
                try {
                    Thread.sleep(50);
                    i += 50;
                    if (i >= 10000) {
                        break;
                    }
                } catch (Exception unused) {
                }
            }
            ((Activity) MraidView.this.y).runOnUiThread(new Runnable() {
                public final void run() {
                    if (MraidView.this.C != null) {
                        MraidView.this.C.setVisibility(8);
                    }
                    new Thread(new a()).start();
                }
            });
        }

        class a implements Runnable {
            private boolean b = false;

            public a() {
            }

            public final void run() {
                while (MraidView.this.A != null) {
                    try {
                        Thread.sleep(100);
                        if (this.b != MraidView.this.A.isPlaying()) {
                            this.b = MraidView.this.A.isPlaying();
                            String str = this.b ? "videoplay" : "videopause";
                            MraidView mraidView = MraidView.this;
                            mraidView.loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('" + str + "')}catch(e){}");
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void addJavascriptObject(Object obj, String str) {
        addJavascriptInterface(obj, str);
    }

    private int getContentViewHeight() {
        View findViewById = getRootView().findViewById(16908290);
        if (findViewById != null) {
            return findViewById.getHeight();
        }
        return -1;
    }

    public VIEW_STATE getViewState() {
        return this.q;
    }

    public String getState() {
        return this.q.toString().toLowerCase();
    }

    public void resizeOrientation(int i2, int i3, String str, boolean z2) {
        this.t = i2;
        this.u = i3;
        TapjoyLog.d("MRAIDView", "resizeOrientation to dimensions: " + i2 + AvidJSONUtil.KEY_X + i3);
        Message obtainMessage = this.D.obtainMessage(1010);
        Bundle bundle = new Bundle();
        bundle.putInt("resize_width", i2);
        bundle.putInt("resize_height", i3);
        bundle.putBoolean("resize_allowOffScreen", z2);
        bundle.putString("resize_customClosePostition", str);
        obtainMessage.setData(bundle);
        this.D.sendMessage(obtainMessage);
    }

    public void close() {
        this.D.sendEmptyMessage(1001);
    }

    public void show() {
        this.D.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService("connectivity");
    }

    public void open(String str, boolean z2, boolean z3, boolean z4) {
        String str2;
        String str3 = str;
        boolean z5 = true;
        if (a(str)) {
            str2 = str3;
        } else {
            TapjoyHttpURLResponse redirectFromURL = new TapjoyURLConnection().getRedirectFromURL(str);
            TapjoyLog.i("MRAIDView", "redirect: " + redirectFromURL.redirectURL + ", " + redirectFromURL.statusCode);
            if (redirectFromURL == null || redirectFromURL.redirectURL == null || redirectFromURL.redirectURL.length() <= 0 || !a(redirectFromURL.redirectURL)) {
                str2 = null;
                z5 = false;
            } else {
                str2 = redirectFromURL.redirectURL;
            }
        }
        if (z5) {
            Abstract.Dimensions dimensions = new Abstract.Dimensions();
            dimensions.x = 0;
            dimensions.y = 0;
            dimensions.width = getWidth();
            dimensions.height = getHeight();
            playVideo(str2, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
            return;
        }
        TapjoyLog.d("MRAIDView", "Mraid Browser open:" + str);
        Intent intent = new Intent(getContext(), Browser.class);
        intent.putExtra(Browser.URL_EXTRA, str);
        boolean z6 = z2;
        intent.putExtra(Browser.SHOW_BACK_EXTRA, z2);
        intent.putExtra(Browser.SHOW_FORWARD_EXTRA, z3);
        intent.putExtra(Browser.SHOW_REFRESH_EXTRA, z4);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    private static boolean a(String str) {
        for (String endsWith : d) {
            if (str.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public void setOrientationProperties(boolean z2, String str) {
        ((Activity) getContext()).setRequestedOrientation(!z2 ? !str.equals(TJAdUnitConstants.String.LANDSCAPE) : -1);
    }

    public void openMap(String str, boolean z2) {
        TapjoyLog.d("MRAIDView", "Opening Map Url " + str);
        String convert = Utils.convert(str.trim());
        if (z2) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(convert));
                intent.setFlags(268435456);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void playAudioImpl(Bundle bundle) {
        String string = bundle.getString(EXPAND_URL);
        MraidPlayer player = getPlayer();
        player.setPlayData((Abstract.PlayerProperties) bundle.getParcelable(PLAYER_PROPERTIES), string);
        player.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
        ((ViewGroup) getParent()).addView(player);
        player.playAudio();
    }

    public void playAudio(String str, boolean z2, boolean z3, boolean z4, boolean z5, String str2, String str3) {
        Abstract.PlayerProperties playerProperties = new Abstract.PlayerProperties();
        playerProperties.setProperties(false, z2, z3, z5, z4, str2, str3);
        Bundle bundle = new Bundle();
        bundle.putString(ACTION_KEY, Action.PLAY_AUDIO.toString());
        String str4 = str;
        bundle.putString(EXPAND_URL, str);
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e2) {
                e2.printStackTrace();
            }
        } else {
            Message obtainMessage = this.D.obtainMessage(PointerIconCompat.TYPE_TEXT);
            obtainMessage.setData(bundle);
            this.D.sendMessage(obtainMessage);
        }
    }

    public void playVideoImpl(Bundle bundle) {
        Abstract.Dimensions dimensions = (Abstract.Dimensions) bundle.getParcelable(DIMENSIONS);
        String string = bundle.getString(EXPAND_URL);
        MraidPlayer player = getPlayer();
        player.setPlayData((Abstract.PlayerProperties) bundle.getParcelable(PLAYER_PROPERTIES), string);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensions.width, dimensions.height);
        layoutParams.topMargin = dimensions.x;
        layoutParams.leftMargin = dimensions.y;
        player.setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setId(101);
        frameLayout.setPadding(dimensions.x, dimensions.y, 0, 0);
        ((FrameLayout) getRootView().findViewById(16908290)).addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(player);
        setVisibility(4);
        player.setListener(new Player() {
            public final void onPrepared() {
            }

            public final void onError() {
                onComplete();
            }

            public final void onComplete() {
                FrameLayout frameLayout = (FrameLayout) MraidView.this.getRootView().findViewById(101);
                ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
                MraidView.this.setVisibility(0);
            }
        });
        player.playVideo();
    }

    public void playVideo(String str, boolean z2, boolean z3, boolean z4, boolean z5, Abstract.Dimensions dimensions, String str2, String str3) {
        Abstract.Dimensions dimensions2 = dimensions;
        Message obtainMessage = this.D.obtainMessage(PointerIconCompat.TYPE_CROSSHAIR);
        Abstract.PlayerProperties playerProperties = new Abstract.PlayerProperties();
        playerProperties.setProperties(z2, z3, z4, false, z5, str2, str3);
        Bundle bundle = new Bundle();
        String str4 = str;
        bundle.putString(EXPAND_URL, str);
        bundle.putString(ACTION_KEY, Action.PLAY_VIDEO.toString());
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (dimensions2 != null) {
            bundle.putParcelable(DIMENSIONS, dimensions2);
        }
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                intent.setFlags(268435456);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e2) {
                e2.printStackTrace();
            }
        } else if (dimensions2 != null) {
            obtainMessage.setData(bundle);
            this.D.sendMessage(obtainMessage);
        }
    }

    public boolean isPageFinished() {
        return this.g;
    }

    public void onGlobalLayout() {
        boolean z2 = this.k;
        if (!this.k && this.j >= 0 && getContentViewHeight() >= 0 && this.j != getContentViewHeight()) {
            z2 = true;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: true});");
        }
        if (this.k && this.j >= 0 && getContentViewHeight() >= 0 && this.j == getContentViewHeight()) {
            z2 = false;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: false});");
        }
        if (this.j < 0) {
            this.j = getContentViewHeight();
        }
        this.k = z2;
    }

    public String getSize() {
        return "{ width: " + ((int) Math.ceil((double) (((float) getWidth()) / this.i))) + ", height: " + ((int) Math.ceil((double) (((float) getHeight()) / this.i))) + "}";
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        if (!this.E) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            this.n = layoutParams.height;
            this.o = layoutParams.width;
            this.E = true;
        }
        this.w = false;
        if (this.v == null || !this.v.isAlive()) {
            this.v = new Thread(new b());
            this.v.start();
        }
        super.onAttachedToWindow();
    }

    public WebBackForwardList saveState(Bundle bundle) {
        return super.saveState(bundle);
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        return super.restoreState(bundle);
    }

    public void raiseError(String str, String str2) {
        Message obtainMessage = this.D.obtainMessage(PointerIconCompat.TYPE_VERTICAL_TEXT);
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        bundle.putString(ACTION_KEY, str2);
        obtainMessage.setData(bundle);
        this.D.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.w = true;
        this.h.stopAllListeners();
        try {
            if (this.A != null) {
                this.A.stopPlayback();
            }
            if (this.B != null) {
                this.B.onCustomViewHidden();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public MraidPlayer getPlayer() {
        if (s != null) {
            s.releasePlayer();
        }
        MraidPlayer mraidPlayer = new MraidPlayer(getContext());
        s = mraidPlayer;
        return mraidPlayer;
    }

    class a extends AsyncTask {
        TapjoyHttpURLResponse a;
        TapjoyURLConnection b;
        String c;

        private a() {
        }

        /* synthetic */ a(MraidView mraidView, byte b2) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public Void doInBackground(String... strArr) {
            this.c = strArr[0];
            try {
                this.b = new TapjoyURLConnection();
                this.a = this.b.getResponseFromURL(this.c);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            try {
                if (this.a.statusCode != 0) {
                    if (this.a.response != null) {
                        if (this.a.statusCode != 302 || this.a.redirectURL == null || this.a.redirectURL.length() <= 0) {
                            MraidView.this.loadDataWithBaseURL(this.c, this.a.response, "text/html", "utf-8", this.c);
                            return;
                        }
                        TapjoyLog.i("MRAIDView", "302 redirectURL detected: " + this.a.redirectURL);
                        MraidView.this.loadUrlStandard(this.a.redirectURL);
                        return;
                    }
                }
                TapjoyLog.e("MRAIDView", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.NETWORK_ERROR, "Connection not properly established"));
                if (MraidView.this.r != null) {
                    MraidView.this.r.onReceivedError(MraidView.this, 0, "Connection not properly established", this.c);
                }
            } catch (Exception e) {
                TapjoyLog.w("MRAIDView", "error in loadURL " + e);
                e.printStackTrace();
            }
        }
    }

    public void setContext(Context context) {
        this.y = context;
    }

    static /* synthetic */ void c(MraidView mraidView, String str) {
        if (str != null) {
            String str2 = null;
            if (str.equals("top-right")) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals("top-center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals("top-left")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = 1";
            } else if (str.equals("center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals("bottom-right")) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals("bottom-left")) {
                str2 = "document.getElementById(\"closeButton\").style.left = 1;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            } else if (str.equals("bottom-center")) {
                str2 = "document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 -18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            }
            if (str2 != null) {
                mraidView.injectMraidJavaScript(str2);
            } else {
                TapjoyLog.d("MRAIDView", "Reposition of close button failed.");
            }
        }
    }

    static /* synthetic */ void f(MraidView mraidView) {
        try {
            if (mraidView.r != null) {
                mraidView.r.onClose();
            }
            ((ViewGroup) mraidView.getParent()).removeView(mraidView);
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void m(MraidView mraidView) {
        WindowManager windowManager = (WindowManager) mraidView.getContext().getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        if (!(width == mraidView.t && height == mraidView.u) && mraidView.getPlacementType() == PLACEMENT_TYPE.INTERSTITIAL) {
            mraidView.resizeOrientation(width, height, "top-right", true);
        }
    }
}
