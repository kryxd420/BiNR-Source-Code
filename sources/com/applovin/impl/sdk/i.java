package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Intent;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.e.a;
import com.applovin.impl.sdk.e.e;
import com.applovin.impl.sdk.e.f;
import com.applovin.impl.sdk.e.k;
import com.applovin.impl.sdk.h;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinUserService;
import com.applovin.sdk.AppLovinWebViewActivity;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class i implements h.a, AppLovinWebViewActivity.EventListener {
    /* access modifiers changed from: private */
    public static final AtomicBoolean a = new AtomicBoolean();
    /* access modifiers changed from: private */
    public static WeakReference<AppLovinWebViewActivity> b;
    /* access modifiers changed from: private */
    public final j c;
    /* access modifiers changed from: private */
    public final p d;
    /* access modifiers changed from: private */
    public AppLovinUserService.OnConsentDialogDismissListener e;
    /* access modifiers changed from: private */
    public h f;
    /* access modifiers changed from: private */
    public WeakReference<Activity> g = new WeakReference<>((Object) null);
    /* access modifiers changed from: private */
    public a h;

    i(j jVar) {
        this.c = jVar;
        this.d = jVar.v();
        if (jVar.z() != null) {
            this.g = new WeakReference<>(jVar.z());
        }
        jVar.T().a(new a() {
            public void onActivityStarted(Activity activity) {
                WeakReference unused = i.this.g = new WeakReference(activity);
            }
        });
        this.f = new h(this, jVar);
    }

    private void a(boolean z, long j) {
        f();
        if (z) {
            a(j);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(j jVar) {
        if (!e.a((Class<?>) AppLovinWebViewActivity.class, jVar.x())) {
            this.d.e("AppLovinSdk", "Unable to show consent dialog. Please add <activity android:name=\"com.applovin.sdk.AppLovinWebViewActivity\" android:configChanges=\"keyboardHidden|orientation|screenSize\"/> to your AndroidManifest.xml file.");
            return false;
        } else if (c()) {
            this.d.e("AppLovinSdk", "Consent dialog already showing");
            return false;
        } else if (!f.a(jVar.x(), jVar)) {
            this.d.e("AppLovinSdk", "No internet available, skip showing of consent dialog");
            return false;
        } else if (!((Boolean) jVar.a(b.aj)).booleanValue()) {
            this.d.d("ConsentDialogManager", "Blocked publisher from showing consent dialog");
            return false;
        } else if (k.b((String) jVar.a(b.ak))) {
            return true;
        } else {
            this.d.d("ConsentDialogManager", "AdServer returned empty consent dialog URL");
            return false;
        }
    }

    private void f() {
        this.c.T().b(this.h);
        if (c()) {
            AppLovinWebViewActivity appLovinWebViewActivity = (AppLovinWebViewActivity) b.get();
            b = null;
            if (appLovinWebViewActivity != null) {
                appLovinWebViewActivity.finish();
                if (this.e != null) {
                    this.e.onDismiss();
                    this.e = null;
                }
            }
        }
    }

    public void a() {
        this.d.a("ConsentDialogManager", "User accepted consent alert");
        if (this.g.get() != null) {
            final Activity activity = (Activity) this.g.get();
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() {
                public void run() {
                    i.this.a(activity, (AppLovinUserService.OnConsentDialogDismissListener) null);
                }
            }, ((Long) this.c.a(b.am)).longValue());
        }
    }

    public void a(final long j) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() {
            public void run() {
                i.this.d.a("ConsentDialogManager", "Scheduling repeating consent alert");
                i.this.f.a(j, i.this.c, i.this);
            }
        });
    }

    public void a(final Activity activity, final AppLovinUserService.OnConsentDialogDismissListener onConsentDialogDismissListener) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (i.this.a(i.this.c) && !i.a.getAndSet(true)) {
                    WeakReference unused = i.this.g = new WeakReference(activity);
                    AppLovinUserService.OnConsentDialogDismissListener unused2 = i.this.e = onConsentDialogDismissListener;
                    a unused3 = i.this.h = new a() {
                        public void onActivityStarted(Activity activity) {
                            if (activity instanceof AppLovinWebViewActivity) {
                                if (!i.this.c() || i.b.get() != activity) {
                                    AppLovinWebViewActivity appLovinWebViewActivity = (AppLovinWebViewActivity) activity;
                                    WeakReference unused = i.b = new WeakReference(appLovinWebViewActivity);
                                    appLovinWebViewActivity.loadUrl((String) i.this.c.a(b.ak), i.this);
                                }
                                i.a.set(false);
                            }
                        }
                    };
                    i.this.c.T().a(i.this.h);
                    Intent intent = new Intent(activity, AppLovinWebViewActivity.class);
                    intent.putExtra(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, i.this.c.t());
                    intent.putExtra(AppLovinWebViewActivity.INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON, (Serializable) i.this.c.a(b.al));
                    activity.startActivity(intent);
                } else if (onConsentDialogDismissListener != null) {
                    onConsentDialogDismissListener.onDismiss();
                }
            }
        });
    }

    public void b() {
        this.d.a("ConsentDialogManager", "User rejected consent alert");
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return (b == null || b.get() == null) ? false : true;
    }

    public void onReceivedEvent(String str) {
        boolean booleanValue;
        j jVar;
        b bVar;
        p pVar = this.d;
        pVar.a("ConsentDialogManager", "Received event: " + str);
        if ("accepted".equalsIgnoreCase(str)) {
            AppLovinPrivacySettings.setHasUserConsent(true, this.c.x());
            f();
            return;
        }
        if ("rejected".equalsIgnoreCase(str)) {
            AppLovinPrivacySettings.setHasUserConsent(false, this.c.x());
            booleanValue = ((Boolean) this.c.a(b.an)).booleanValue();
            jVar = this.c;
            bVar = b.as;
        } else if ("closed".equalsIgnoreCase(str)) {
            booleanValue = ((Boolean) this.c.a(b.ao)).booleanValue();
            jVar = this.c;
            bVar = b.at;
        } else if (AppLovinWebViewActivity.EVENT_DISMISSED_VIA_BACK_BUTTON.equalsIgnoreCase(str)) {
            booleanValue = ((Boolean) this.c.a(b.ap)).booleanValue();
            jVar = this.c;
            bVar = b.au;
        } else {
            return;
        }
        a(booleanValue, ((Long) jVar.a(bVar)).longValue());
    }
}
