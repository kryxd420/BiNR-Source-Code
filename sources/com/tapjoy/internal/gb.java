package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.internal.gd;
import com.tapjoy.internal.ho;
import java.util.Observer;

public final class gb extends ho {
    private final gd b = new gd() {
        /* access modifiers changed from: protected */
        public final boolean a() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            ho.a aVar = (ho.a) obj;
            TJPlacement createPlacement = TJPlacementManager.createPlacement(TapjoyConnectCore.getContext(), aVar.b, false, tJPlacementListener);
            createPlacement.pushId = aVar.a;
            return createPlacement;
        }

        /* access modifiers changed from: protected */
        public final /* bridge */ /* synthetic */ String a(Object obj) {
            ho.a aVar = (ho.a) obj;
            if (aVar != null) {
                return aVar.b;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ gd.a b(Object obj) {
            ho.a aVar = (ho.a) obj;
            return new gd.a(aVar, aVar.d);
        }

        /* access modifiers changed from: protected */
        public final boolean a(Observer observer) {
            if (TapjoyConnectCore.isViewOpen()) {
                TJPlacementManager.dismissContentShowing(true);
            }
            return super.a(observer);
        }
    };

    public static void a() {
    }

    static {
        ho.a((ho) new gb());
    }

    private gb() {
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        return this.b.b != null;
    }

    /* access modifiers changed from: protected */
    public final void a(ho.a aVar) {
        this.b.c(aVar);
    }
}
