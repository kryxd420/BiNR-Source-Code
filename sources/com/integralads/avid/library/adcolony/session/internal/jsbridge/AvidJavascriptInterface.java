package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;

public class AvidJavascriptInterface {
    public static final String AVID_OBJECT = "avid";
    private final InternalAvidAdSessionContext a;
    private final Handler b = new Handler();
    /* access modifiers changed from: private */
    public AvidJavascriptInterfaceCallback c;

    public interface AvidJavascriptInterfaceCallback {
        void onAvidAdSessionContextInvoked();
    }

    public AvidJavascriptInterface(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.a = internalAvidAdSessionContext;
    }

    public AvidJavascriptInterfaceCallback getCallback() {
        return this.c;
    }

    public void setCallback(AvidJavascriptInterfaceCallback avidJavascriptInterfaceCallback) {
        this.c = avidJavascriptInterfaceCallback;
    }

    @JavascriptInterface
    public String getAvidAdSessionContext() {
        this.b.post(new a());
        return this.a.getStubContext().toString();
    }

    class a implements Runnable {
        a() {
        }

        public void run() {
            if (AvidJavascriptInterface.this.c != null) {
                AvidJavascriptInterface.this.c.onAvidAdSessionContextInvoked();
                AvidJavascriptInterfaceCallback unused = AvidJavascriptInterface.this.c = null;
            }
        }
    }
}
