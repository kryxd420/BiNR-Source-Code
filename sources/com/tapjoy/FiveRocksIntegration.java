package com.tapjoy;

import com.tapjoy.internal.bc;
import com.tapjoy.internal.gp;
import com.tapjoy.internal.gq;
import com.tapjoy.internal.gs;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.he;

public class FiveRocksIntegration {
    /* access modifiers changed from: private */
    public static bc a = new bc();

    public static void addPlacementCallback(String str, TJPlacement tJPlacement) {
        synchronized (a) {
            a.put(str, tJPlacement);
        }
    }

    public static void a() {
        hd a2 = hd.a();
        if (!a2.c) {
            a2.c = true;
        }
        AnonymousClass1 r0 = new gs() {
            public final void a(String str) {
            }

            public final void d(String str) {
            }

            public final void b(String str) {
                TJPlacement tJPlacement;
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.a != null) {
                    tJPlacement.a.onContentReady(tJPlacement);
                }
            }

            public final void c(String str) {
                TJPlacement tJPlacement;
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.a != null) {
                    tJPlacement.a.onContentShow(tJPlacement);
                }
            }

            public final void a(String str, gp gpVar) {
                if (gpVar != null) {
                    gpVar.a(e(str));
                }
            }

            public final void a(String str, String str2, gp gpVar) {
                TJPlacement tJPlacement;
                if (gpVar != null) {
                    gpVar.a(e(str));
                }
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null) {
                    TapjoyConnectCore.viewDidClose(str2);
                    if (tJPlacement.a != null) {
                        tJPlacement.a.onContentDismiss(tJPlacement);
                    }
                }
            }

            private gq e(final String str) {
                return new gq() {
                    public final void a(final String str, String str2) {
                        TJPlacement tJPlacement;
                        synchronized (FiveRocksIntegration.a) {
                            tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement != null && tJPlacement.a != null) {
                            tJPlacement.a.onPurchaseRequest(tJPlacement, new TJActionRequest() {
                                public final void cancelled() {
                                }

                                public final void completed() {
                                }

                                public final String getToken() {
                                    return null;
                                }

                                public final String getRequestId() {
                                    return str;
                                }
                            }, str2);
                        }
                    }

                    public final void a(final String str, String str2, int i, final String str3) {
                        TJPlacement tJPlacement;
                        synchronized (FiveRocksIntegration.a) {
                            tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement != null && tJPlacement.a != null) {
                            tJPlacement.a.onRewardRequest(tJPlacement, new TJActionRequest() {
                                public final void cancelled() {
                                }

                                public final void completed() {
                                }

                                public final String getRequestId() {
                                    return str;
                                }

                                public final String getToken() {
                                    return str3;
                                }
                            }, str2, i);
                        }
                    }
                };
            }
        };
        hd.a().p = he.a((gs) r0);
    }
}
