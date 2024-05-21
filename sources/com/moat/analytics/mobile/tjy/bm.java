package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

class bm implements bl {
    bm() {
    }

    public a a(ViewGroup viewGroup) {
        WebView webView;
        if (viewGroup instanceof WebView) {
            return a.a((WebView) viewGroup);
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(viewGroup);
        HashSet hashSet = new HashSet();
        int i = 0;
        loop0:
        while (true) {
            webView = null;
            while (!linkedList.isEmpty() && i < 100) {
                i++;
                Iterator it = new bo((ViewGroup) linkedList.poll()).iterator();
                while (true) {
                    if (it.hasNext()) {
                        View view = (View) it.next();
                        if (view instanceof WebView) {
                            if (webView != null) {
                                break;
                            }
                            webView = (WebView) view;
                        }
                        if (view instanceof ViewGroup) {
                            ViewGroup viewGroup2 = (ViewGroup) view;
                            if (!hashSet.contains(viewGroup2)) {
                                hashSet.add(viewGroup2);
                                linkedList.add(viewGroup2);
                            }
                        }
                    }
                }
            }
            Log.e("MoatWebViewHound", "Ambiguous ad container: multiple WebViews reside within it.");
        }
        return a.b(webView);
    }
}
