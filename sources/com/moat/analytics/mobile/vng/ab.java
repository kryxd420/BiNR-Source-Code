package com.moat.analytics.mobile.vng;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

class ab {

    private static class a implements Iterable<View> {
        /* access modifiers changed from: private */
        public final ViewGroup a;

        /* renamed from: com.moat.analytics.mobile.vng.ab$a$a  reason: collision with other inner class name */
        private class C0008a implements Iterator<View> {
            private int b;

            private C0008a() {
                this.b = -1;
            }

            /* renamed from: a */
            public View next() {
                this.b++;
                return a.this.a.getChildAt(this.b);
            }

            public boolean hasNext() {
                return this.b + 1 < a.this.a.getChildCount();
            }

            public void remove() {
                throw new UnsupportedOperationException("Not implemented. Under development.");
            }
        }

        private a(ViewGroup viewGroup) {
            this.a = viewGroup;
        }

        public Iterator<View> iterator() {
            return new C0008a();
        }
    }

    ab() {
    }

    @NonNull
    static com.moat.analytics.mobile.vng.a.b.a<WebView> a(ViewGroup viewGroup) {
        WebView webView;
        View next;
        if (viewGroup instanceof WebView) {
            return com.moat.analytics.mobile.vng.a.b.a.a((WebView) viewGroup);
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
                Iterator<View> it = new a((ViewGroup) linkedList.poll()).iterator();
                while (true) {
                    if (it.hasNext()) {
                        next = it.next();
                        if (next instanceof WebView) {
                            if (webView != null) {
                                break;
                            }
                            webView = (WebView) next;
                        }
                        if (next instanceof ViewGroup) {
                            ViewGroup viewGroup2 = (ViewGroup) next;
                            if (!hashSet.contains(viewGroup2)) {
                                hashSet.add(viewGroup2);
                                linkedList.add(viewGroup2);
                            }
                        }
                    }
                }
            }
            p.a(3, "WebViewHound", (Object) next, "Ambiguous ad container: multiple WebViews reside within it.");
            p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
        }
        return com.moat.analytics.mobile.vng.a.b.a.b(webView);
    }
}
