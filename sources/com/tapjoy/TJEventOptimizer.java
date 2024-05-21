package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapjoy.TapjoyErrorMessage;
import java.util.concurrent.CountDownLatch;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJEventOptimizer extends WebView {
    /* access modifiers changed from: private */
    public static String a = "TJEventOptimizer";
    /* access modifiers changed from: private */
    public static TJEventOptimizer b;
    /* access modifiers changed from: private */
    public static CountDownLatch c;
    private Context d;
    private TJAdUnitJSBridge e;

    /* synthetic */ TJEventOptimizer(Context context, byte b2) {
        this(context);
    }

    private TJEventOptimizer(Context context) {
        super(context);
        this.d = context;
        this.e = new TJAdUnitJSBridge(this.d, (WebView) this);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new b(this, (byte) 0));
        setWebChromeClient(new a(this, (byte) 0));
        loadUrl(TapjoyConnectCore.getHostURL() + TJAdUnitConstants.EVENTS_PROXY_PATH + TapjoyUtil.convertURLParams(TapjoyConnectCore.getGenericURLParams(), true));
    }

    public static void init(final Context context) {
        TapjoyLog.d(a, "Initializing event optimizer");
        c = new CountDownLatch(1);
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    TJEventOptimizer unused = TJEventOptimizer.b = new TJEventOptimizer(context, (byte) 0);
                } catch (Exception e) {
                    TapjoyLog.w(TJEventOptimizer.a, e.getMessage());
                }
                TJEventOptimizer.c.countDown();
            }
        });
        c.await();
        if (b == null) {
            throw new RuntimeException("Failed to init TJEventOptimizer");
        }
    }

    public static TJEventOptimizer getInstance() {
        return b;
    }

    class b extends WebViewClient {
        private b() {
        }

        /* synthetic */ b(TJEventOptimizer tJEventOptimizer, byte b) {
            this();
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TapjoyLog.e(TJEventOptimizer.a, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error encountered when instantiating a WebViewClient"));
        }

        public final void onPageFinished(WebView webView, String str) {
            TapjoyLog.d(TJEventOptimizer.a, "boostrap html loaded successfully");
        }
    }

    class a extends WebChromeClient {
        private a() {
        }

        /* synthetic */ a(TJEventOptimizer tJEventOptimizer, byte b) {
            this();
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String a2 = TJEventOptimizer.a;
            TapjoyLog.d(a2, "JS CONSOLE: " + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
    }
}
