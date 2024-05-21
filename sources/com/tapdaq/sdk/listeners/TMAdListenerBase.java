package com.tapdaq.sdk.listeners;

import com.tapdaq.sdk.adnetworks.TDMediatedNativeAd;
import com.tapdaq.sdk.common.TMAdError;
import java.util.Map;

public interface TMAdListenerBase {
    void didClick();

    void didClose();

    void didCustomEvent(Map<Object, Object> map);

    void didDisplay();

    void didFailToDisplay(TMAdError tMAdError);

    void didFailToLoad(TMAdError tMAdError);

    void didLoad();

    void didLoad(TDMediatedNativeAd tDMediatedNativeAd);

    void didRefresh();

    void willDisplay();
}
