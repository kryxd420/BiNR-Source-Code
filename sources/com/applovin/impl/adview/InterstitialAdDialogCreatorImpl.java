package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    private static final Object a = new Object();
    private static WeakReference<k> b = new WeakReference<>((Object) null);
    private static WeakReference<Context> c = new WeakReference<>((Object) null);

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context) {
        k kVar;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        synchronized (a) {
            kVar = (k) b.get();
            if (kVar != null && kVar.isShowing()) {
                if (c.get() == context) {
                    appLovinSdk.getLogger().c("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
                }
            }
            kVar = new k(appLovinSdk, context);
            b = new WeakReference<>(kVar);
            c = new WeakReference<>(context);
        }
        return kVar;
    }
}
