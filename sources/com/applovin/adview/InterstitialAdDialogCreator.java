package com.applovin.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

@Deprecated
public interface InterstitialAdDialogCreator {
    AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context);
}
