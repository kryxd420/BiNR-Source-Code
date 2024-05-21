package com.adcolony.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import com.adcolony.sdk.aa;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.mraid.controller.Abstract;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

class d {
    private HashMap<String, c> a;
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, AdColonyInterstitial> b;
    private HashMap<String, ba> c;
    private HashMap<String, AdColonyNativeAdViewListener> d;
    /* access modifiers changed from: private */
    public HashMap<String, az> e;
    private HashMap<String, f> f;

    /* access modifiers changed from: package-private */
    public void a(String str, ba baVar, AdColonyAdSize adColonyAdSize) {
    }

    d() {
    }

    /* access modifiers changed from: private */
    public boolean d(af afVar) {
        final JSONObject c2 = afVar.c();
        final String b2 = y.b(c2, "id");
        final ba remove = this.c.remove(b2);
        final AdColonyNativeAdViewListener remove2 = this.d.remove(b2);
        if (remove == null && remove2 == null) {
            a(afVar.d(), b2);
            return false;
        }
        final Activity c3 = a.c();
        if (c3 == null) {
            return false;
        }
        final af afVar2 = afVar;
        aw.a((Runnable) new Runnable() {
            public void run() {
                az azVar;
                if (remove != null) {
                    azVar = new az(c3, afVar2, remove);
                    d.this.e.put(b2, azVar);
                } else {
                    azVar = new AdColonyNativeAdView(c3, afVar2, remove2);
                    d.this.e.put(b2, azVar);
                }
                azVar.setAdvertiserName(y.b(c2, "name"));
                azVar.setTitle(y.b(c2, TJAdUnitConstants.String.TITLE));
                azVar.setDescription(y.b(c2, "description"));
                azVar.setImageFilepath(y.b(c2, "thumb_filepath"));
                azVar.b();
                if (remove != null) {
                    remove.a(azVar);
                } else {
                    remove2.onRequestFilled((AdColonyNativeAdView) azVar);
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public boolean e(af afVar) {
        String b2 = y.b(afVar.c(), "id");
        final ba remove = this.c.remove(b2);
        final AdColonyNativeAdViewListener remove2 = this.d.remove(b2);
        if (remove == null && remove2 == null) {
            a(afVar.d(), b2);
            return false;
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                boolean z = remove == null;
                String str = z ? remove2.a : remove.a;
                AdColonyZone adColonyZone = a.a().f().get(str);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                    adColonyZone.b(6);
                }
                if (z) {
                    remove2.onRequestNotFilled(adColonyZone);
                } else {
                    remove.a(adColonyZone);
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.a = new HashMap<>();
        this.b = new ConcurrentHashMap<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        a.a("AdContainer.create", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.j(afVar);
            }
        });
        a.a("AdContainer.destroy", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.k(afVar);
            }
        });
        a.a("AdContainer.move_view_to_index", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.l(afVar);
            }
        });
        a.a("AdContainer.move_view_to_front", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.m(afVar);
            }
        });
        a.a("AdSession.finish_fullscreen_ad", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.i(afVar);
            }
        });
        a.a("AdSession.start_fullscreen_ad", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.h(afVar);
            }
        });
        a.a("AdSession.native_ad_view_available", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.d(afVar);
            }
        });
        a.a("AdSession.native_ad_view_unavailable", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.e(afVar);
            }
        });
        a.a("AdSession.expiring", (ah) new ah() {
            public void a(af afVar) {
                d.this.a(afVar);
            }
        });
        a.a("AdSession.audio_stopped", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) d.this.b.get(y.b(afVar.c(), "id"));
                        if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
                            adColonyInterstitial.getListener().onAudioStopped(adColonyInterstitial);
                        }
                    }
                });
            }
        });
        a.a("AdSession.audio_started", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) d.this.b.get(y.b(afVar.c(), "id"));
                        if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
                            adColonyInterstitial.getListener().onAudioStarted(adColonyInterstitial);
                        }
                    }
                });
            }
        });
        a.a("AudioPlayer.create", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.n(afVar);
            }
        });
        a.a("AudioPlayer.destroy", (ah) new ah() {
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    boolean unused = d.this.o(afVar);
                }
            }
        });
        a.a("AudioPlayer.play", (ah) new ah() {
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    boolean unused = d.this.p(afVar);
                }
            }
        });
        a.a("AudioPlayer.pause", (ah) new ah() {
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    boolean unused = d.this.q(afVar);
                }
            }
        });
        a.a("AudioPlayer.stop", (ah) new ah() {
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    boolean unused = d.this.r(afVar);
                }
            }
        });
        a.a("AdSession.interstitial_available", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.g(afVar);
            }
        });
        a.a("AdSession.interstitial_unavailable", (ah) new ah() {
            public void a(af afVar) {
                d.this.b(afVar);
            }
        });
        a.a("AdSession.has_audio", (ah) new ah() {
            public void a(af afVar) {
                boolean unused = d.this.f(afVar);
            }
        });
        a.a("WebView.prepare", (ah) new ah() {
            public void a(af afVar) {
                JSONObject a2 = y.a();
                y.a(a2, "success", true);
                afVar.a(a2).b();
            }
        });
        a.a("AdSession.iap_event", (ah) new ah() {
            public void a(af afVar) {
                JSONObject c = afVar.c();
                if (y.c(c, "type") == 2) {
                    az azVar = (az) d.this.e.get(y.b(c, "id"));
                    JSONObject f = y.f(c, "v4iap");
                    JSONArray g = y.g(f, "product_ids");
                    if (azVar != null && f != null && g.length() > 0) {
                        ((AdColonyNativeAdViewListener) azVar.getListener()).onIAPEvent((AdColonyNativeAdView) azVar, y.c(g, 0), y.c(f, "engagement_type"));
                    }
                }
            }
        });
        a.a("AdSession.native_ad_view_finished", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        az azVar = (az) d.this.e.get(y.b(afVar.c(), "id"));
                        if (azVar != null && azVar.getListener() != null && (azVar instanceof AdColonyNativeAdView)) {
                            ((AdColonyNativeAdViewListener) azVar.getListener()).onNativeVideoFinished((AdColonyNativeAdView) azVar);
                        }
                    }
                });
            }
        });
        a.a("AdSession.native_ad_view_started", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        az azVar = (az) d.this.e.get(y.b(afVar.c(), "id"));
                        if (azVar != null && azVar.getListener() != null && (azVar instanceof AdColonyNativeAdView)) {
                            ((AdColonyNativeAdViewListener) azVar.getListener()).onNativeVideoStarted((AdColonyNativeAdView) azVar);
                        }
                    }
                });
            }
        });
        a.a("AdSession.destroy_native_ad_view", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        JSONObject c = afVar.c();
                        az azVar = (az) d.this.e.get(y.b(c, "id"));
                        if (azVar != null) {
                            azVar.a();
                            afVar.a(c).b();
                        }
                    }
                });
            }
        });
        a.a("AdSession.expanded", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        afVar.a(afVar.c()).b();
                    }
                });
            }
        });
        a.a("AdSession.native_ad_muted", (ah) new ah() {
            public void a(final af afVar) {
                aw.a((Runnable) new Runnable() {
                    public void run() {
                        JSONObject c = afVar.c();
                        az azVar = (az) d.this.e.get(y.b(c, "id"));
                        boolean d = y.d(c, "muted");
                        e listener = azVar != null ? azVar.getListener() : null;
                        if ((azVar instanceof AdColonyNativeAdView) && listener != null) {
                            if (d) {
                                ((AdColonyNativeAdViewListener) listener).onAudioStopped((AdColonyNativeAdView) azVar);
                            } else {
                                ((AdColonyNativeAdViewListener) listener).onAudioStarted((AdColonyNativeAdView) azVar);
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean a(af afVar) {
        final AdColonyInterstitialListener adColonyInterstitialListener;
        JSONObject c2 = afVar.c();
        String b2 = y.b(c2, "id");
        if (y.c(c2, "type") != 0) {
            return true;
        }
        final AdColonyInterstitial remove = this.b.remove(b2);
        if (remove == null) {
            adColonyInterstitialListener = null;
        } else {
            adColonyInterstitialListener = remove.getListener();
        }
        if (adColonyInterstitialListener == null) {
            a(afVar.d(), b2);
            return false;
        } else if (!a.d()) {
            return false;
        } else {
            aw.a((Runnable) new Runnable() {
                public void run() {
                    remove.a(true);
                    adColonyInterstitialListener.onExpiring(remove);
                    o r = a.a().r();
                    if (r.b() != null) {
                        r.b().dismiss();
                        r.a((AlertDialog) null);
                    }
                }
            });
            return true;
        }
    }

    /* access modifiers changed from: private */
    public boolean f(af afVar) {
        String b2 = y.b(afVar.c(), "id");
        JSONObject a2 = y.a();
        y.a(a2, "id", b2);
        Activity c2 = a.c();
        if (c2 == null) {
            y.a(a2, "has_audio", false);
            afVar.a(a2).b();
            return false;
        }
        boolean a3 = aw.a(aw.a((Context) c2));
        double b3 = aw.b(aw.a((Context) c2));
        y.a(a2, "has_audio", a3);
        y.a(a2, AvidVideoPlaybackListenerImpl.VOLUME, b3);
        afVar.a(a2).b();
        return a3;
    }

    /* access modifiers changed from: private */
    public boolean g(af afVar) {
        final AdColonyInterstitialListener adColonyInterstitialListener;
        String b2 = y.b(afVar.c(), "id");
        final AdColonyInterstitial adColonyInterstitial = this.b.get(b2);
        if (adColonyInterstitial == null) {
            adColonyInterstitialListener = null;
        } else {
            adColonyInterstitialListener = adColonyInterstitial.getListener();
        }
        if (adColonyInterstitialListener == null) {
            a(afVar.d(), b2);
            return false;
        } else if (!a.d()) {
            return false;
        } else {
            adColonyInterstitial.a(y.f(afVar.c(), "ias"));
            adColonyInterstitial.a(y.b(afVar.c(), "ad_id"));
            adColonyInterstitial.b(y.b(afVar.c(), "creative_id"));
            if (adColonyInterstitial.g()) {
                adColonyInterstitial.h().b();
            }
            aw.a((Runnable) new Runnable() {
                public void run() {
                    adColonyInterstitialListener.onRequestFilled(adColonyInterstitial);
                }
            });
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(af afVar) {
        final AdColonyInterstitialListener adColonyInterstitialListener;
        String b2 = y.b(afVar.c(), "id");
        final AdColonyInterstitial remove = this.b.remove(b2);
        if (remove == null) {
            adColonyInterstitialListener = null;
        } else {
            adColonyInterstitialListener = remove.getListener();
        }
        if (adColonyInterstitialListener == null) {
            a(afVar.d(), b2);
            return false;
        } else if (!a.d()) {
            return false;
        } else {
            aw.a((Runnable) new Runnable() {
                public void run() {
                    AdColonyZone adColonyZone = a.a().f().get(remove.getZoneID());
                    if (adColonyZone == null) {
                        adColonyZone = new AdColonyZone(remove.getZoneID());
                        adColonyZone.b(6);
                    }
                    adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
                }
            });
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(b2);
        f fVar = this.f.get(b2);
        if (cVar != null && fVar != null) {
            return true;
        }
        new aa.a().a("Invalid AudioPlayer message!").a(aa.g);
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, AdColonyNativeAdViewListener adColonyNativeAdViewListener, AdColonyAdSize adColonyAdSize, AdColonyAdOptions adColonyAdOptions) {
        float o = a.a().n().o();
        String e2 = aw.e();
        JSONObject a2 = y.a();
        y.a(a2, "zone_id", str);
        y.b(a2, "type", 2);
        y.b(a2, AvidJSONUtil.KEY_WIDTH, (int) (((float) adColonyAdSize.a) * o));
        y.b(a2, AvidJSONUtil.KEY_HEIGHT, (int) (((float) adColonyAdSize.b) * o));
        y.a(a2, "id", e2);
        adColonyNativeAdViewListener.a = str;
        if (!(adColonyAdOptions == null || adColonyAdOptions.d == null)) {
            y.a(a2, "options", adColonyAdOptions.d);
        }
        this.d.put(e2, adColonyNativeAdViewListener);
        new af("AdSession.on_request", 1, a2).b();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, AdColonyInterstitialListener adColonyInterstitialListener, AdColonyAdOptions adColonyAdOptions) {
        String e2 = aw.e();
        l a2 = a.a();
        JSONObject a3 = y.a();
        y.a(a3, "zone_id", str);
        y.a(a3, Abstract.FULL_SCREEN, true);
        y.b(a3, AvidJSONUtil.KEY_WIDTH, a2.c.p());
        y.b(a3, AvidJSONUtil.KEY_HEIGHT, a2.c.q());
        y.b(a3, "type", 0);
        y.a(a3, "id", e2);
        new aa.a().a("AdSession request with id = ").a(e2).a(aa.b);
        AdColonyInterstitial adColonyInterstitial = new AdColonyInterstitial(e2, adColonyInterstitialListener, str);
        this.b.put(e2, adColonyInterstitial);
        if (!(adColonyAdOptions == null || adColonyAdOptions.d == null)) {
            adColonyInterstitial.a(adColonyAdOptions);
            y.a(a3, "options", adColonyAdOptions.d);
        }
        new aa.a().a("Requesting AdColony interstitial advertisement.").a(aa.a);
        new af("AdSession.on_request", 1, a3).b();
    }

    /* access modifiers changed from: private */
    public boolean h(af afVar) {
        Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        JSONObject c3 = afVar.c();
        l a2 = a.a();
        String b2 = y.b(c3, "id");
        AdColonyInterstitial adColonyInterstitial = this.b.get(b2);
        az azVar = this.e.get(b2);
        int a3 = y.a(c3, "orientation", -1);
        boolean z = azVar != null;
        if (adColonyInterstitial != null || z) {
            JSONObject a4 = y.a();
            y.a(a4, "id", b2);
            if (adColonyInterstitial != null) {
                adColonyInterstitial.a(y.c(a4, "module_id"));
                adColonyInterstitial.b(a3);
                adColonyInterstitial.a();
            } else if (z) {
                azVar.b = a3;
                a2.a(azVar.getExpandedContainer());
                a2.a(azVar);
                c2.startActivity(new Intent(c2, AdColonyAdViewActivity.class));
            }
            return true;
        }
        a(afVar.d(), b2);
        return false;
    }

    /* access modifiers changed from: private */
    public boolean i(af afVar) {
        final AdColonyInterstitialListener adColonyInterstitialListener;
        JSONObject c2 = afVar.c();
        int c3 = y.c(c2, NotificationCompat.CATEGORY_STATUS);
        if (c3 == 5 || c3 == 1 || c3 == 0 || c3 == 6) {
            return false;
        }
        String b2 = y.b(c2, "id");
        final AdColonyInterstitial remove = this.b.remove(b2);
        if (remove == null) {
            adColonyInterstitialListener = null;
        } else {
            adColonyInterstitialListener = remove.getListener();
        }
        if (adColonyInterstitialListener == null) {
            a(afVar.d(), b2);
            return false;
        }
        aw.a((Runnable) new Runnable() {
            public void run() {
                a.a().c(false);
                adColonyInterstitialListener.onClosed(remove);
            }
        });
        remove.a((c) null);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean j(af afVar) {
        Activity c2 = a.c();
        if (c2 == null) {
            return false;
        }
        JSONObject c3 = afVar.c();
        String b2 = y.b(c3, "ad_session_id");
        c cVar = new c(c2, b2);
        cVar.b(afVar);
        if (this.a.containsKey(b2)) {
            az azVar = this.e.get(b2);
            if (azVar == null) {
                return false;
            }
            azVar.setExpandedContainer(cVar);
            return true;
        }
        new aa.a().a("Inserting container into hash map tied to ad session id: ").a(b2).a(aa.b);
        this.a.put(b2, cVar);
        if (y.c(c3, AvidJSONUtil.KEY_WIDTH) != 0) {
            cVar.a(false);
        } else if (this.b.get(b2) == null) {
            a(afVar.d(), b2);
            return false;
        } else {
            this.b.get(b2).a(cVar);
        }
        JSONObject a2 = y.a();
        y.a(a2, "success", true);
        afVar.a(a2).b();
        return true;
    }

    /* access modifiers changed from: private */
    public boolean k(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(b2);
        if (cVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        a(cVar);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(final c cVar) {
        aw.a((Runnable) new Runnable() {
            public void run() {
                for (int i = 0; i < cVar.n().size(); i++) {
                    a.b(cVar.o().get(i), cVar.n().get(i));
                }
                cVar.o().clear();
                cVar.n().clear();
                cVar.removeAllViews();
                cVar.d = null;
                cVar.c = null;
                new aa.a().a("Destroying container tied to ad_session_id = ").a(cVar.b()).a(aa.d);
                for (u b2 : cVar.g().values()) {
                    b2.b();
                }
                for (ay next : cVar.h().values()) {
                    if (!next.g()) {
                        a.a().a(next.a());
                        next.loadUrl("about:blank");
                        next.clearCache(true);
                        next.removeAllViews();
                        next.a(true);
                    }
                }
                new aa.a().a("Stopping and releasing all media players associated with ").a("VideoViews tied to ad_session_id = ").a(cVar.b()).a(aa.d);
                for (ax next2 : cVar.e().values()) {
                    next2.d();
                    next2.g();
                }
                cVar.e().clear();
                cVar.f().clear();
                cVar.h().clear();
                cVar.g().clear();
                cVar.k().clear();
                cVar.m().clear();
                cVar.j().clear();
                cVar.l().clear();
                cVar.a = true;
            }
        });
        az azVar = this.e.get(cVar.b());
        if (azVar == null || azVar.c()) {
            new aa.a().a("Removing ad 4").a(aa.b);
            this.a.remove(cVar.b());
            cVar.c = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        new aa.a().a("Message '").a(str).a("' sent with invalid id: ").a(str2).a(aa.g);
    }

    /* access modifiers changed from: private */
    public boolean l(af afVar) {
        JSONObject c2 = afVar.c();
        String d2 = afVar.d();
        String b2 = y.b(c2, "ad_session_id");
        int c3 = y.c(c2, "view_id");
        c cVar = this.a.get(b2);
        View view = cVar.m().get(Integer.valueOf(c3));
        if (cVar == null) {
            a(d2, b2);
            return false;
        } else if (view == null) {
            a(d2, "" + c3);
            return false;
        } else {
            view.bringToFront();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public boolean m(af afVar) {
        az azVar;
        JSONObject c2 = afVar.c();
        String d2 = afVar.d();
        String b2 = y.b(c2, "ad_session_id");
        int c3 = y.c(c2, "view_id");
        c cVar = this.a.get(b2);
        if (cVar == null) {
            a(d2, b2);
            return false;
        }
        if (cVar.d() == 0 && y.c(c2, "id") == 1 && (azVar = this.e.get(b2)) != null && azVar.getExpandedContainer() != null) {
            cVar = azVar.getExpandedContainer();
        }
        View view = cVar.m().get(Integer.valueOf(c3));
        if (view == null) {
            a(d2, "" + c3);
            return false;
        }
        cVar.removeView(view);
        cVar.addView(view, view.getLayoutParams());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean n(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(b2);
        if (cVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        f fVar = this.f.get(b2);
        if (fVar == null) {
            fVar = new f(b2, cVar.c());
            this.f.put(b2, fVar);
        }
        fVar.a(afVar);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean o(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(b2);
        if (fVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        fVar.d(afVar);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean p(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(b2);
        if (fVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        fVar.c(afVar);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean q(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(b2);
        if (fVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        fVar.b(afVar);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean r(af afVar) {
        String b2 = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(b2);
        if (fVar == null) {
            a(afVar.d(), b2);
            return false;
        }
        fVar.e(afVar);
        return true;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, c> b() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public ConcurrentHashMap<String, AdColonyInterstitial> c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, ba> d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, AdColonyNativeAdViewListener> e() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, az> f() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, f> g() {
        return this.f;
    }
}
