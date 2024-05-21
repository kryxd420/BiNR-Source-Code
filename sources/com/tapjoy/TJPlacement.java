package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.internal.fr;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.ga;
import com.tapjoy.internal.gj;
import com.tapjoy.internal.gw;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.hi;
import com.tapjoy.internal.ju;
import com.vungle.warren.ui.VungleActivity;
import java.util.HashMap;
import java.util.UUID;

public class TJPlacement {
    TJPlacementListener a;
    private TJCorePlacement b;
    private TJPlacementListener c;
    private TJPlacementVideoListener d;
    private String e;
    public String pushId;

    @Deprecated
    public TJPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        TJCorePlacement a2 = TJPlacementManager.a(str);
        a2 = a2 == null ? TJPlacementManager.a(str, "", "", false, false) : a2;
        a2.setContext(context);
        a(a2, tJPlacementListener);
    }

    TJPlacement(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        a(tJCorePlacement, tJPlacementListener);
    }

    private void a(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        this.b = tJCorePlacement;
        this.e = UUID.randomUUID().toString();
        this.c = tJPlacementListener;
        this.a = tJPlacementListener != null ? (TJPlacementListener) fr.a(tJPlacementListener, TJPlacementListener.class) : null;
        FiveRocksIntegration.addPlacementCallback(getName(), this);
    }

    public TJPlacementListener getListener() {
        return this.c;
    }

    public void setVideoListener(TJPlacementVideoListener tJPlacementVideoListener) {
        this.d = tJPlacementVideoListener;
    }

    public TJPlacementVideoListener getVideoListener() {
        return this.d;
    }

    public String getName() {
        return this.b.getPlacementData() != null ? this.b.getPlacementData().getPlacementName() : "";
    }

    public boolean isLimited() {
        return this.b.isLimited();
    }

    public boolean isContentReady() {
        boolean isContentReady = this.b.isContentReady();
        ga gaVar = this.b.f;
        if (isContentReady) {
            gaVar.a(4);
        } else {
            gaVar.a(2);
        }
        return isContentReady;
    }

    public boolean isContentAvailable() {
        this.b.f.a(1);
        return this.b.isContentAvailable();
    }

    public void setMediationId(String str) {
        this.b.p = str;
    }

    public void requestContent() {
        boolean z;
        String str;
        String name = getName();
        TapjoyLog.i("TJPlacement", "requestContent() called for placement " + name);
        gj.a("TJPlacement.requestContent").a(VungleActivity.PLACEMENT_EXTRA, (Object) name).a("placement_type", (Object) this.b.c.getPlacementType());
        if (!isLimited()) {
            z = TapjoyConnectCore.isConnected();
        } else {
            z = TapjoyConnectCore.isLimitedConnected();
        }
        if (!z) {
            gj.b("TJPlacement.requestContent").b("not connected").c();
            a(new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
        } else if (this.b.getContext() == null) {
            gj.b("TJPlacement.requestContent").b("no context").c();
            a(new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
        } else if (ju.c(name)) {
            gj.b("TJPlacement.requestContent").b("invalid name").c();
            a(new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
        } else {
            try {
                this.b.a(this);
            } finally {
                str = "TJPlacement.requestContent";
                gj.d(str);
            }
        }
    }

    public void showContent() {
        String str;
        int i;
        String name = getName();
        TapjoyLog.i("TJPlacement", "showContent() called for placement " + name);
        TJCorePlacement tJCorePlacement = this.b;
        gj.a("TJPlacement.showContent").a(VungleActivity.PLACEMENT_EXTRA, (Object) tJCorePlacement.c.getPlacementName()).a("placement_type", (Object) tJCorePlacement.c.getPlacementType()).a("content_type", (Object) tJCorePlacement.a());
        ga gaVar = tJCorePlacement.f;
        gaVar.a(8);
        fu fuVar = gaVar.a;
        if (fuVar != null) {
            fuVar.a();
        }
        if (!this.b.isContentAvailable()) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
            gj.b("TJPlacement.showContent").b("no content").c();
            return;
        }
        try {
            TJCorePlacement tJCorePlacement2 = this.b;
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                TapjoyLog.w(TJCorePlacement.a, "Only one view can be presented at a time.");
                gj.b("TJPlacement.showContent").b("another content showing").c();
            } else {
                if (TapjoyConnectCore.isViewOpen()) {
                    TapjoyLog.w(TJCorePlacement.a, "Will close N2E content.");
                    TJPlacementManager.dismissContentShowing(false);
                }
                tJCorePlacement2.a("SHOW", this);
                gj.a d2 = gj.d("TJPlacement.showContent");
                if (tJCorePlacement2.g.isPrerendered()) {
                    d2.a("prerendered", (Object) true);
                }
                if (tJCorePlacement2.isContentReady()) {
                    d2.a("content_ready", (Object) true);
                }
                tJCorePlacement2.f.d = d2;
                String uuid = UUID.randomUUID().toString();
                if (tJCorePlacement2.i != null) {
                    tJCorePlacement2.i.f = uuid;
                    if (tJCorePlacement2.i == null) {
                        i = 1;
                    } else if (tJCorePlacement2.i instanceof gz) {
                        i = 3;
                    } else {
                        i = tJCorePlacement2.i instanceof hi ? 2 : 0;
                    }
                    TapjoyConnectCore.viewWillOpen(uuid, i);
                    tJCorePlacement2.i.e = new gw(uuid) {
                        final /* synthetic */ String a;

                        {
                            this.a = r2;
                        }

                        public final void a(Context context, String str, String str2) {
                            if (str2 == null) {
                                TJCorePlacement.this.c.setRedirectURL(str);
                            } else {
                                TJCorePlacement.this.c.setBaseURL(str);
                                TJCorePlacement.this.c.setHttpResponse(str2);
                            }
                            TJCorePlacement.this.c.setHasProgressSpinner(true);
                            TJCorePlacement.this.c.setContentViewId(this.a);
                            Intent intent = new Intent(TJCorePlacement.this.b, TJAdUnitActivity.class);
                            intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, TJCorePlacement.this.c);
                            intent.setFlags(268435456);
                            context.startActivity(intent);
                        }
                    };
                    hd.a((Runnable) new Runnable() {
                        public final void run() {
                            TJCorePlacement.this.i.a(hd.a().p, TJCorePlacement.this.f);
                        }
                    });
                } else {
                    tJCorePlacement2.c.setContentViewId(uuid);
                    Intent intent = new Intent(tJCorePlacement2.b, TJAdUnitActivity.class);
                    intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJCorePlacement2.c);
                    intent.setFlags(268435456);
                    tJCorePlacement2.b.startActivity(intent);
                }
                tJCorePlacement2.e = 0;
                tJCorePlacement2.k = false;
                tJCorePlacement2.l = false;
            }
        } finally {
            str = "TJPlacement.showContent";
            gj.d(str);
        }
    }

    public void setAuctionData(HashMap hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            TapjoyLog.d("TJPlacement", "auctionData can not be null or empty");
            return;
        }
        TJCorePlacement tJCorePlacement = this.b;
        tJCorePlacement.q = hashMap;
        String b2 = tJCorePlacement.b();
        if (!ju.c(b2)) {
            tJCorePlacement.c.setAuctionMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + b2 + "/bid_content?");
            return;
        }
        TapjoyLog.i(TJCorePlacement.a, "Placement auction data can not be set for a null app ID");
    }

    public void setMediationName(String str) {
        TapjoyLog.d("TJPlacement", "setMediationName=" + str);
        if (!ju.c(str)) {
            Context context = this.b != null ? this.b.getContext() : null;
            this.b = TJPlacementManager.a(getName(), str, "", false, isLimited());
            TJCorePlacement tJCorePlacement = this.b;
            tJCorePlacement.o = str;
            tJCorePlacement.m = str;
            tJCorePlacement.c.setPlacementType(str);
            String b2 = tJCorePlacement.b();
            if (!ju.c(b2)) {
                tJCorePlacement.c.setMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + b2 + "/mediation_content?");
            } else {
                TapjoyLog.i(TJCorePlacement.a, "Placement mediation name can not be set for a null app ID");
            }
            if (context != null) {
                this.b.setContext(context);
            }
        }
    }

    public void setAdapterVersion(String str) {
        this.b.n = str;
    }

    public static void dismissContent() {
        TJPlacementManager.dismissContentShowing("true".equals(TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL")));
    }

    public String getGUID() {
        return this.e;
    }

    private void a(TJError tJError) {
        this.b.a(this, TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, tJError);
    }
}
