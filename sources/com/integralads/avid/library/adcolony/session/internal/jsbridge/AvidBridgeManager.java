package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.AvidBridge;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidBridgeManager {
    public static final int VIDEO_EVENT_TAG = 1;
    private final InternalAvidAdSessionContext a;
    private boolean b;
    private AvidWebView c;
    private boolean d;
    private AvidBridgeManagerListener e;
    private final ArrayList<AvidEvent> f = new ArrayList<>();

    public interface AvidBridgeManagerListener {
        void avidBridgeManagerDidInjectAvidJs();
    }

    public AvidBridgeManager(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.a = internalAvidAdSessionContext;
        this.c = new AvidWebView((WebView) null);
    }

    public boolean isActive() {
        return this.b;
    }

    public void setListener(AvidBridgeManagerListener avidBridgeManagerListener) {
        this.e = avidBridgeManagerListener;
    }

    public void onAvidJsReady() {
        a();
    }

    public void setWebView(WebView webView) {
        if (this.c.get() != webView) {
            this.c.set(webView);
            this.b = false;
            if (AvidBridge.isAvidJsReady()) {
                a();
            }
        }
    }

    public void destroy() {
        setWebView((WebView) null);
    }

    public void callAvidbridge(String str) {
        this.c.injectFormattedJavaScript(str);
    }

    public void publishReadyEventForDeferredAdSession() {
        this.d = true;
        b();
    }

    public void publishNativeViewState(String str) {
        callAvidbridge(AvidCommand.setNativeViewState(str));
    }

    public void publishAppState(String str) {
        callAvidbridge(AvidCommand.setAppState(str));
    }

    public void publishVideoEvent(String str, JSONObject jSONObject) {
        if (isActive()) {
            a(str, jSONObject);
        } else {
            this.f.add(new AvidEvent(1, str, jSONObject));
        }
    }

    private void a() {
        if (!this.c.isEmpty()) {
            this.b = true;
            this.c.injectJavaScript(AvidBridge.getAvidJs());
            c();
            b();
            e();
            d();
        }
    }

    private void b() {
        if (isActive() && this.d) {
            callAvidbridge(AvidCommand.publishReadyEventForDeferredAdSession());
        }
    }

    private void a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject != null ? jSONObject.toString() : null;
        if (!TextUtils.isEmpty(jSONObject2)) {
            callAvidbridge(AvidCommand.publishVideoEvent(str, jSONObject2));
        } else {
            callAvidbridge(AvidCommand.publishVideoEvent(str));
        }
    }

    private void c() {
        callAvidbridge(AvidCommand.setAvidAdSessionContext(this.a.getFullContext().toString()));
    }

    private void d() {
        if (this.e != null) {
            this.e.avidBridgeManagerDidInjectAvidJs();
        }
    }

    private void e() {
        Iterator<AvidEvent> it = this.f.iterator();
        while (it.hasNext()) {
            AvidEvent next = it.next();
            a(next.getType(), next.getData());
        }
        this.f.clear();
    }
}
