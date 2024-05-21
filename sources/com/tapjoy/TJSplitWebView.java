package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.mraid.view.BasicWebView;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class TJSplitWebView extends RelativeLayout {
    private BasicWebView a;
    private double[] b = {0.0d, 0.0d, 0.0d, 0.0d};
    private double[] c = {0.0d, 0.0d, 0.0d, 0.0d};
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public HashSet f;
    private TJAdUnitJSBridge g;

    public TJSplitWebView(final Context context, JSONObject jSONObject, JSONArray jSONArray, TJAdUnitJSBridge tJAdUnitJSBridge) {
        super(context);
        this.g = tJAdUnitJSBridge;
        setLayoutOption(jSONObject);
        setExitHosts(jSONArray);
        this.a = new BasicWebView(context);
        this.a.setBackgroundColor(-1);
        WebSettings settings = this.a.getSettings();
        if (settings != null) {
            settings.setUseWideViewPort(true);
        }
        this.a.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                TapjoyLog.d("TJSplitWebView", "shouldOverrideUrlLoading: " + str);
                Uri parse = Uri.parse(str);
                if (parse != null) {
                    String host = parse.getHost();
                    String scheme = parse.getScheme();
                    if (!(scheme == null || host == null || ((!scheme.equals("http") && !scheme.equals("https")) || (TJSplitWebView.this.f != null && TJSplitWebView.this.f.contains(host))))) {
                        String unused = TJSplitWebView.this.e = str;
                        return false;
                    }
                }
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", parse));
                    TJSplitWebView.this.a();
                    return true;
                } catch (Exception e) {
                    TapjoyLog.e("TJSplitWebView", e.getMessage());
                    return true;
                }
            }

            public final void onReceivedError(WebView webView, int i, String str, String str2) {
                TapjoyLog.d("TJSplitWebView", "onReceivedError: " + str2 + " firstUrl:" + TJSplitWebView.this.d);
                if (str2.equals(TJSplitWebView.this.d)) {
                    TJSplitWebView.this.a();
                }
            }
        });
        addView(this.a);
    }

    public void applyLayoutOption(JSONObject jSONObject) {
        setLayoutOption(jSONObject);
        a(getWidth(), getHeight());
    }

    public void setExitHosts(JSONArray jSONArray) {
        if (jSONArray == null) {
            this.f = null;
            return;
        }
        this.f = new HashSet();
        for (int i = 0; i <= jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                this.f.add(optString);
            }
        }
    }

    public void loadUrl(String str) {
        if (this.a != null) {
            this.d = str;
            this.e = str;
            this.a.loadUrl(str);
        }
    }

    public String getLastUrl() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        this.g.dismissSplitView((JSONObject) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public void setLayoutOption(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject(TJAdUnitConstants.String.LANDSCAPE);
            if (optJSONObject != null) {
                this.c[0] = optJSONObject.optDouble(AvidJSONUtil.KEY_WIDTH, 0.0d);
                this.c[1] = optJSONObject.optDouble(AvidJSONUtil.KEY_HEIGHT, 0.0d);
                this.c[2] = optJSONObject.optDouble("left", 0.0d);
                this.c[3] = optJSONObject.optDouble("top", 0.0d);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("portrait");
            if (optJSONObject2 != null) {
                this.b[0] = optJSONObject2.optDouble(AvidJSONUtil.KEY_WIDTH, 0.0d);
                this.b[1] = optJSONObject2.optDouble(AvidJSONUtil.KEY_HEIGHT, 0.0d);
                this.b[2] = optJSONObject2.optDouble("left", 0.0d);
                this.b[3] = optJSONObject2.optDouble("top", 0.0d);
            }
        }
    }

    private void a(int i, int i2) {
        double[] dArr;
        if (i <= i2) {
            dArr = this.b;
        } else {
            dArr = this.c;
        }
        double d2 = (double) i;
        double d3 = dArr[0];
        Double.isNaN(d2);
        int i3 = (int) (d3 * d2);
        double d4 = (double) i2;
        double d5 = dArr[1];
        Double.isNaN(d4);
        int i4 = (int) (d5 * d4);
        double d6 = dArr[2];
        Double.isNaN(d2);
        int i5 = (int) (d2 * d6);
        double d7 = dArr[3];
        Double.isNaN(d4);
        int i6 = (int) (d4 * d7);
        int i7 = (i - i3) - i5;
        int i8 = (i2 - i4) - i6;
        if (i3 == 0 || i4 == 0) {
            this.a.setVisibility(4);
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i5, i6, i7, i8);
        this.a.setLayoutParams(layoutParams);
        this.a.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        a(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        super.onMeasure(i, i2);
    }
}
