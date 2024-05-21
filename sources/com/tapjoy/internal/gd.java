package com.tapjoy.internal;

import android.content.Context;
import com.tapdaq.sdk.common.TDAdapterStatus;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import java.util.Observable;
import java.util.Observer;

abstract class gd {
    volatile a b;

    /* access modifiers changed from: protected */
    public abstract TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj);

    /* access modifiers changed from: protected */
    public abstract String a(Object obj);

    gd() {
    }

    public final boolean c(Object obj) {
        if (!a()) {
            return false;
        }
        a aVar = null;
        synchronized (this) {
            if (this.b == null) {
                aVar = b(obj);
                this.b = aVar;
            }
        }
        if (aVar == null) {
            return false;
        }
        aVar.a();
        return true;
    }

    /* access modifiers changed from: protected */
    public a b(Object obj) {
        return new a(this, obj);
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return !TapjoyConnectCore.isFullScreenViewOpen();
    }

    /* access modifiers changed from: protected */
    public boolean a(Observer observer) {
        if (TapjoyConnectCore.isFullScreenViewOpen()) {
            fw.e.addObserver(observer);
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                return false;
            }
            fw.e.deleteObserver(observer);
        }
        if (hd.a().d()) {
            return true;
        }
        fw.c.addObserver(observer);
        if (!hd.a().d()) {
            return false;
        }
        fw.c.deleteObserver(observer);
        return true;
    }

    class a implements TJPlacementListener, Observer {
        private final Object b;
        private final fm c;
        private volatile boolean d;
        private TJPlacement e;

        public final void onContentDismiss(TJPlacement tJPlacement) {
        }

        public final void onContentShow(TJPlacement tJPlacement) {
        }

        public final void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
        }

        public final void onRequestSuccess(TJPlacement tJPlacement) {
        }

        public final void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
        }

        a(gd gdVar, Object obj) {
            this(obj, new fm(TapjoyConstants.TIMER_INCREMENT));
        }

        a(Object obj, fm fmVar) {
            this.b = obj;
            this.c = fmVar;
        }

        /* access modifiers changed from: package-private */
        public final void a() {
            synchronized (this) {
                if (!this.d) {
                    if (this.c.a()) {
                        a(TDAdapterStatus.TIMEOUT_STR);
                        return;
                    }
                    if (!TapjoyConnectCore.isConnected()) {
                        fw.a.addObserver(this);
                        if (TapjoyConnectCore.isConnected()) {
                            fw.a.deleteObserver(this);
                        } else {
                            return;
                        }
                    }
                    if (this.e == null) {
                        if (!gd.this.a()) {
                            a("Cannot request");
                            return;
                        }
                        this.e = gd.this.a(TapjoyConnectCore.getContext(), this, this.b);
                        this.e.requestContent();
                    } else if (this.e.isContentReady()) {
                        if (gd.this.a((Observer) this)) {
                            this.e.showContent();
                            a((String) null);
                        }
                    }
                }
            }
        }

        private void a(String str) {
            synchronized (this) {
                String a2 = gd.this.a(this.b);
                if (str == null) {
                    TapjoyLog.i("SystemPlacement", "Placement " + a2 + " is presented now");
                } else {
                    TapjoyLog.i("SystemPlacement", "Cannot show placement " + a2 + " now (" + str + ")");
                }
                this.d = true;
                this.e = null;
                fw.a.deleteObserver(this);
                fw.e.deleteObserver(this);
                fw.c.deleteObserver(this);
            }
            gd gdVar = gd.this;
            synchronized (gdVar) {
                if (gdVar.b == this) {
                    gdVar.b = null;
                }
            }
        }

        public final void update(Observable observable, Object obj) {
            a();
        }

        public final void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
            a(tJError.message);
        }

        public final void onContentReady(TJPlacement tJPlacement) {
            a();
        }
    }
}
