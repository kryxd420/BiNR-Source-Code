package com.tapdaq.unityplugin;

import android.app.Activity;
import android.support.v4.view.PointerIconCompat;
import android.widget.PopupWindow;
import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqConfig;
import com.tapdaq.sdk.helpers.TDDeviceInfo;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.helpers.TLogLevel;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMAdListenerBase;
import com.tapdaq.sdk.model.TMAdSize;
import com.tapdaq.unityplugin.listeners.BannerAdListener;
import com.tapdaq.unityplugin.listeners.InterstitialAdListener;
import com.tapdaq.unityplugin.listeners.OfferwallListener;
import com.tapdaq.unityplugin.listeners.RewardAdListener;
import com.tapdaq.unityplugin.listeners.TDInitListener;
import com.tapdaq.unityplugin.listeners.VideoAdListener;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TapdaqUnity {
    static TMBannerAdView bannerAdView;
    static String bannerType;
    static PopupWindow popupWindow;

    public static Activity CurrentActivity() {
        return UnityPlayer.currentActivity;
    }

    public static void InitiateTapdaq(String str, String str2, String str3, boolean z, boolean z2, String str4, int i, int i2, int i3) {
        if (z) {
            TLog.setLoggingLevel(TLogLevel.DEBUG);
        } else {
            TLog.setLoggingLevel(TLogLevel.INFO);
        }
        TLog.debug("Initializing Tapdaq");
        final String str5 = str4;
        final boolean z3 = z2;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final String str6 = str3;
        final String str7 = str;
        final String str8 = str2;
        CurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                TapdaqConfig tapdaqConfig = new TapdaqConfig();
                tapdaqConfig.setPluginVersion(str5);
                tapdaqConfig.setAutoReloadAds(z3);
                tapdaqConfig.setUserSubjectToGDPR(STATUS.valueOf(i4));
                boolean z = false;
                if (STATUS.valueOf(i5) != STATUS.UNKNOWN) {
                    tapdaqConfig.setConsentGiven(STATUS.valueOf(i5) == STATUS.TRUE);
                }
                if (STATUS.valueOf(i6) != STATUS.UNKNOWN) {
                    if (STATUS.valueOf(i6) == STATUS.TRUE) {
                        z = true;
                    }
                    tapdaqConfig.setIsAgeRestrictedUser(z);
                }
                Tapdaq.getInstance().initialize(TapdaqUnity.CurrentActivity(), str7, str8, TapdaqUnity.SetTestDevices(tapdaqConfig, str6), new TDInitListener());
            }
        });
    }

    public static boolean IsInitialised() {
        return Tapdaq.getInstance().IsInitialised();
    }

    public static TapdaqConfig SetTestDevices(TapdaqConfig tapdaqConfig, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("adMobDevices")) {
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("adMobDevices");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                tapdaqConfig.registerTestDevices(1, arrayList);
            }
            if (jSONObject.has("facebookDevices")) {
                ArrayList arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject.getJSONArray("facebookDevices");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList2.add(jSONArray2.getString(i2));
                }
                tapdaqConfig.registerTestDevices(4, arrayList2);
            }
        } catch (JSONException e) {
            TLog.error((Exception) e);
        }
        return tapdaqConfig;
    }

    public static void SetUserSubjectToGDPR(final int i) {
        CurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                Tapdaq.getInstance().setUserSubjectToGDPR(TapdaqUnity.CurrentActivity(), STATUS.valueOf(i));
            }
        });
    }

    public static int IsUserSubjectToGDPR() {
        return Tapdaq.getInstance().config(CurrentActivity()).isUserSubjectToGDPR().getValue();
    }

    public static void SetConsentGiven(final boolean z) {
        CurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                Tapdaq.getInstance().setContentGiven(TapdaqUnity.CurrentActivity(), z);
            }
        });
    }

    public static boolean IsConsentGiven() {
        return Tapdaq.getInstance().isConsentGiven(CurrentActivity());
    }

    public static void SetAgeRestrictedUser(final boolean z) {
        CurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                Tapdaq.getInstance().setIsAgeRestrictedUser(TapdaqUnity.CurrentActivity(), z);
            }
        });
    }

    public static boolean IsAgeRestrictedUser() {
        return Tapdaq.getInstance().isAgeRestrictedUser(CurrentActivity());
    }

    public static void SetAdMobContentRating(String str) {
        Tapdaq.getInstance().config().setAdMobContentRating(str);
    }

    public static String GetAdMobContentRating() {
        return Tapdaq.getInstance().config().getAdMobContentRating();
    }

    public static void OnResume() {
        Tapdaq.getInstance().onResume(CurrentActivity());
    }

    public static void OnPause() {
        Tapdaq.getInstance().onPause(CurrentActivity());
    }

    public static void ShowMediationDebugger() {
        Tapdaq.getInstance().startTestActivity(CurrentActivity());
    }

    public static void LoadInterstitial(String str) {
        Tapdaq.getInstance().loadInterstitial(CurrentActivity(), str, new InterstitialAdListener(str));
    }

    public static void ShowInterstitial(String str) {
        Tapdaq.getInstance().showInterstitial(CurrentActivity(), str, new InterstitialAdListener(str));
    }

    public static boolean IsInterstitialReady(String str) {
        return Tapdaq.getInstance().isInterstitialReady(CurrentActivity(), str);
    }

    public static void LoadVideo(String str) {
        Tapdaq.getInstance().loadVideo(CurrentActivity(), str, new VideoAdListener(str));
    }

    public static void ShowVideo(String str) {
        Tapdaq.getInstance().showVideo(CurrentActivity(), str, new VideoAdListener(str));
    }

    public static boolean IsVideoReady(String str) {
        return Tapdaq.getInstance().isVideoReady(CurrentActivity(), str);
    }

    public static void LoadRewardedVideo(String str) {
        Tapdaq.getInstance().loadRewardedVideo(CurrentActivity(), str, new RewardAdListener(str));
    }

    public static void ShowRewardedVideo(String str, String str2) {
        Tapdaq.getInstance().showRewardedVideo(CurrentActivity(), str, str2, (TMAdListenerBase) new RewardAdListener(str));
    }

    public static boolean IsRewardedVideoReady(String str) {
        return Tapdaq.getInstance().isRewardedVideoReady(CurrentActivity(), str);
    }

    public static void CreateBanner(TMAdSize tMAdSize) {
        bannerAdView = new TMBannerAdView(CurrentActivity());
        CreatePopupWindow(tMAdSize);
    }

    private static void CreatePopupWindow(TMAdSize tMAdSize) {
        float resolutionFloat = TDDeviceInfo.getResolutionFloat(CurrentActivity());
        float width = (float) TDDeviceInfo.getWidth(CurrentActivity());
        TLog.debug("Creating PopupWindow with size : : " + tMAdSize.width + "," + tMAdSize.height + " and scale = " + resolutionFloat + " and width = " + width);
        float f = tMAdSize.width == 0 ? width : ((float) tMAdSize.width) * resolutionFloat;
        if (f <= width) {
            width = f;
        }
        popupWindow = new PopupWindow(bannerAdView, (int) width, (int) (((float) tMAdSize.height) * resolutionFloat));
        popupWindow.getContentView().setSystemUiVisibility(CurrentActivity().getWindow().getAttributes().flags);
        TapdaqHelper.setPopUpWindowLayoutType(popupWindow, PointerIconCompat.TYPE_HAND);
    }

    public static void ShowBanner(final String str) {
        if (bannerAdView == null) {
            LoadBannerOfType(bannerType);
        }
        CurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                TapdaqUnity.bannerAdView.setVisibility(0);
                if (!TapdaqUnity.popupWindow.isShowing()) {
                    TapdaqUnity.popupWindow.showAtLocation(TapdaqUnity.CurrentActivity().getWindow().getDecorView().getRootView(), TapdaqHelper.GetBannerGravityFromString(str), 0, 0);
                }
            }
        });
    }

    public static void HideBanner() {
        if (bannerAdView != null) {
            CurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (TapdaqUnity.bannerAdView != null) {
                        TapdaqUnity.bannerAdView.setVisibility(8);
                        TapdaqUnity.bannerAdView.destroy(TapdaqUnity.CurrentActivity());
                        TapdaqUnity.bannerAdView = null;
                        TapdaqUnity.popupWindow.dismiss();
                    }
                }
            });
        }
    }

    public static void LoadBanner() {
        LoadBannerOfType("");
    }

    public static void LoadBannerOfType(String str) {
        bannerType = str;
        TMAdSize GetBannerSizeFromTypeString = TapdaqHelper.GetBannerSizeFromTypeString(str);
        if (bannerAdView == null) {
            CreateBanner(GetBannerSizeFromTypeString);
        }
        bannerAdView.load(CurrentActivity(), GetBannerSizeFromTypeString, (TMAdListener) new BannerAdListener());
    }

    public static boolean IsBannerReady() {
        if (bannerAdView != null) {
            return bannerAdView.isReady();
        }
        return false;
    }

    public static void LoadOfferwall() {
        Tapdaq.getInstance().loadOfferwall(CurrentActivity(), new OfferwallListener());
    }

    public static void ShowOfferwall() {
        Tapdaq.getInstance().showOfferwall(CurrentActivity(), new OfferwallListener());
    }

    public static boolean IsOfferwallReady() {
        return Tapdaq.getInstance().isOfferwallReady(CurrentActivity());
    }

    public static void SendIAP(String str, String str2, String str3, double d, String str4, String str5) {
        Tapdaq.getInstance().sendIAP(CurrentActivity(), str, str2, str3, d, str4, str5);
    }

    public static String GetRewardId(String str) {
        return Tapdaq.getInstance().getRewardId(CurrentActivity(), str);
    }
}
