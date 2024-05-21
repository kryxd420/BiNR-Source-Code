package com.moat.analytics.mobile.tjy;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.LinkedHashMap;
import java.util.Map;

class af implements NativeDisplayTracker {
    private WebView a;
    private bh b;
    private final String c;
    private final ap d;
    private boolean e;

    public af(View view, String str, a aVar, ap apVar) {
        if (apVar.b()) {
            Log.d("MoatNativeDispTracker", "Initializing.");
        }
        this.c = str;
        this.a = new WebView(view.getContext());
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.d = apVar;
        this.b = new bi(view, this.a, true, aVar, apVar);
        this.e = false;
    }

    private static String a(int i, int i2, String str, String str2) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n<div id=\"mianahwvc\" style=\"width:" + i + "px;height:" + i2 + "px;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js#" + str2 + "\" type=\"text/javascript\"></script>\n</div>\n</body>\n</html>";
    }

    private static String a(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            String str = "moatClientLevel" + i;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = "moatClientSlicer" + i2;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str4 : linkedHashMap.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(str4);
            sb.append('=');
            sb.append((String) linkedHashMap.get(str4));
        }
        return String.valueOf(sb);
    }

    private void a() {
        if (!this.e) {
            a("Shutting down.");
            this.b.d();
            this.a.loadUrl("about:blank");
            this.a.destroy();
            this.a = null;
            this.b = null;
            this.e = true;
        }
    }

    private void a(String str) {
        if (this.d.b()) {
            Log.d("MoatNativeDispTracker", String.format("id = %s, message = %s", new Object[]{Integer.valueOf(hashCode()), str}));
        }
    }

    public void stopTracking() {
        a("Called stopTracking.");
        a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean track(java.util.Map r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L_0x004a
            boolean r1 = r6.isEmpty()     // Catch:{ Exception -> 0x0047 }
            if (r1 == 0) goto L_0x000a
            goto L_0x004a
        L_0x000a:
            com.moat.analytics.mobile.tjy.bh r1 = r5.b     // Catch:{ Exception -> 0x0047 }
            boolean r1 = r1.c()     // Catch:{ Exception -> 0x0047 }
            if (r1 == 0) goto L_0x0056
            com.moat.analytics.mobile.tjy.bh r0 = r5.b     // Catch:{ Exception -> 0x0045 }
            android.graphics.Rect r0 = r0.e()     // Catch:{ Exception -> 0x0045 }
            int r2 = r0.width()     // Catch:{ Exception -> 0x0045 }
            int r0 = r0.height()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r6 = a((java.util.Map) r6)     // Catch:{ Exception -> 0x0045 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0045 }
            java.lang.String r4 = "Parsed ad ids = "
            r3.<init>(r4)     // Catch:{ Exception -> 0x0045 }
            r3.append(r6)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0045 }
            r5.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = r5.c     // Catch:{ Exception -> 0x0045 }
            java.lang.String r6 = a(r2, r0, r3, r6)     // Catch:{ Exception -> 0x0045 }
            android.webkit.WebView r0 = r5.a     // Catch:{ Exception -> 0x0045 }
            java.lang.String r2 = "text/html"
            java.lang.String r3 = "utf-8"
            r0.loadData(r6, r2, r3)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0056
        L_0x0045:
            r6 = move-exception
            goto L_0x0053
        L_0x0047:
            r6 = move-exception
            r1 = 0
            goto L_0x0053
        L_0x004a:
            java.lang.String r6 = "adIdMap is null or empty. Shutting down."
            r5.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0047 }
            r5.a()     // Catch:{ Exception -> 0x0047 }
            return r0
        L_0x0053:
            com.moat.analytics.mobile.tjy.base.exception.a.a(r6)
        L_0x0056:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Attempt to start tracking ad was "
            r6.<init>(r0)
            if (r1 == 0) goto L_0x0062
            java.lang.String r0 = ""
            goto L_0x0064
        L_0x0062:
            java.lang.String r0 = "un"
        L_0x0064:
            r6.append(r0)
            java.lang.String r0 = "successful."
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.a((java.lang.String) r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.tjy.af.track(java.util.Map):boolean");
    }
}
