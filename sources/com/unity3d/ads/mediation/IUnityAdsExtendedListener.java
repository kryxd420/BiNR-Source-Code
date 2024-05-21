package com.unity3d.ads.mediation;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public interface IUnityAdsExtendedListener extends IUnityAdsListener {
    void onUnityAdsClick(String str);

    void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2);
}
