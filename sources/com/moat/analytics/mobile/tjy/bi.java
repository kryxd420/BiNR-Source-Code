package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyAuctionFlags;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.HashMap;
import java.util.Map;

class bi implements bh, m {
    private View a;
    private final WebView b;
    private boolean c;
    private final l d;
    private final a e;
    private final ap f;
    private a g;

    bi(View view, WebView webView, boolean z, a aVar, ap apVar) {
        this(view, webView, z, new n(webView.getContext(), apVar), aVar, apVar);
    }

    bi(View view, WebView webView, boolean z, l lVar, a aVar, ap apVar) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(view);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(webView);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(aVar);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(lVar);
        if (apVar.b()) {
            Log.d("MoatViewTracker", "In initialization method.");
        }
        this.e = aVar;
        this.a = view;
        this.b = webView;
        this.c = z;
        this.d = lVar;
        this.f = apVar;
        this.g = a.a();
    }

    private static String a(Rect rect) {
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right - rect.left;
        int i4 = rect.bottom - rect.top;
        StringBuilder sb = new StringBuilder("{\"x\":");
        sb.append(i);
        sb.append(',');
        sb.append('\"');
        sb.append("y\":");
        sb.append(i2);
        sb.append(',');
        sb.append('\"');
        sb.append("w\":");
        sb.append(i3);
        sb.append(',');
        sb.append('\"');
        sb.append("h\":");
        sb.append(i4);
        sb.append('}');
        return String.valueOf(sb);
    }

    private static String a(Map map, boolean z) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (sb.length() > 1) {
                sb.append(',');
            }
            sb.append('\"');
            sb.append(str);
            sb.append('\"');
            sb.append(':');
            if (!z) {
                sb.append(str2);
            } else {
                sb.append('\"');
                sb.append(str2);
                sb.append('\"');
            }
        }
        sb.append("}");
        return String.valueOf(sb);
    }

    private void a(Map map, String str, Rect rect) {
        map.put(str, a(b(rect)));
    }

    private Rect b(Rect rect) {
        float f2 = j().density;
        if (f2 == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f2), Math.round(((float) rect.top) / f2), Math.round(((float) rect.right) / f2), Math.round(((float) rect.bottom) / f2));
    }

    private Rect c(Rect rect) {
        Rect k = k();
        if (!this.a.getGlobalVisibleRect(k)) {
            k = k();
        }
        k.left = Math.min(Math.max(0, k.left), rect.right);
        k.right = Math.min(Math.max(0, k.right), rect.right);
        k.top = Math.min(Math.max(0, k.top), rect.bottom);
        k.bottom = Math.min(Math.max(0, k.bottom), rect.bottom);
        return k;
    }

    private String g() {
        if (this.g.c()) {
            return (String) this.g.b();
        }
        String str = "_unknown_";
        try {
            Context context = this.b.getContext();
            String charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
            try {
                this.g = a.a(charSequence);
                return charSequence;
            } catch (Exception e2) {
                String str2 = charSequence;
                e = e2;
                str = str2;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            return str;
        }
    }

    private boolean h() {
        return this.a.isShown() && !this.e.a();
    }

    private Rect i() {
        DisplayMetrics j = j();
        return new Rect(0, 0, j.widthPixels, j.heightPixels);
    }

    private DisplayMetrics j() {
        return this.a.getContext().getResources().getDisplayMetrics();
    }

    private Rect k() {
        return new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public String a() {
        HashMap hashMap = new HashMap();
        try {
            Rect i = i();
            Rect c2 = c(i);
            Rect e2 = e();
            a(hashMap, "screen", i);
            a(hashMap, TJAdUnitConstants.String.VISIBLE, c2);
            a(hashMap, "maybe", c2);
            a(hashMap, "view", e2);
            hashMap.put("inFocus", String.valueOf(h() ? 1 : 0));
            DisplayMetrics j = j();
            StringBuilder sb = new StringBuilder();
            sb.append(j.density);
            hashMap.put("dr", sb.toString());
            return a(hashMap, false);
        } catch (Exception unused) {
            return "{}";
        }
    }

    public void a(View view) {
        String str;
        if (this.f.b()) {
            StringBuilder sb = new StringBuilder("changing view to ");
            if (view != null) {
                str = view.getClass().getSimpleName() + "@" + view.hashCode();
            } else {
                str = "null";
            }
            sb.append(str);
            Log.d("MoatViewTracker", sb.toString());
        }
        this.a = view;
    }

    public String b() {
        try {
            return a(f(), true);
        } catch (Exception unused) {
            return "{}";
        }
    }

    public boolean c() {
        if (this.f.b()) {
            Log.d("MoatViewTracker", "Attempting bridge installation.");
        }
        boolean a2 = this.d.a(this.b, this);
        if (this.f.b()) {
            StringBuilder sb = new StringBuilder("Bridge ");
            sb.append(a2 ? "" : "not ");
            sb.append("installed.");
            Log.d("MoatViewTracker", sb.toString());
        }
        return a2;
    }

    public void d() {
        this.d.a();
    }

    public Rect e() {
        int[] iArr = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.a.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, this.a.getWidth() + i, this.a.getHeight() + i2);
    }

    public Map f() {
        HashMap hashMap = new HashMap();
        String g2 = g();
        String num = Integer.toString(Build.VERSION.SDK_INT);
        String str = this.c ? TapjoyAuctionFlags.AUCTION_TYPE_FIRST_PRICE : "0";
        hashMap.put("versionHash", "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
        hashMap.put("appName", g2);
        hashMap.put("namespace", "TJY");
        hashMap.put(MediationMetaData.KEY_VERSION, "1.7.10");
        hashMap.put("deviceOS", num);
        hashMap.put("isNative", str);
        return hashMap;
    }
}
