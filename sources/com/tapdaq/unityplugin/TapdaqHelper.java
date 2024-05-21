package com.tapdaq.unityplugin;

import android.util.Log;
import android.widget.PopupWindow;
import com.tapdaq.sdk.common.TMBannerAdSizes;
import com.tapdaq.sdk.model.TMAdSize;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TapdaqHelper {
    public static TMAdSize GetBannerSizeFromTypeString(String str) {
        if (str.equalsIgnoreCase("TDMBannerLarge")) {
            return TMBannerAdSizes.LARGE;
        }
        if (str.equalsIgnoreCase("TDMBannerMedium")) {
            return TMBannerAdSizes.MEDIUM_RECT;
        }
        if (str.equalsIgnoreCase("TDMBannerFull")) {
            return TMBannerAdSizes.FULL;
        }
        if (str.equalsIgnoreCase("TDMBannerLeaderboard")) {
            return TMBannerAdSizes.LEADERBOARD;
        }
        if (str.equalsIgnoreCase("TDMBannerSmartPortrait") || str.equalsIgnoreCase("TDMBannerSmartLandscape")) {
            return TMBannerAdSizes.SMART;
        }
        if (str.equalsIgnoreCase("TDMBannerSkyscraper")) {
            return TMBannerAdSizes.SKYSCRAPER;
        }
        return TMBannerAdSizes.STANDARD;
    }

    public static int GetBannerGravityFromString(String str) {
        return str.equalsIgnoreCase("top") ? 49 : 81;
    }

    public static void setPopUpWindowLayoutType(PopupWindow popupWindow, int i) {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            Method declaredMethod = cls.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(popupWindow, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            Log.w("Tapdaq Unity", String.format("Unable to set popUpWindow window layout type: %s", new Object[]{e.getLocalizedMessage()}));
        } catch (IllegalAccessException e2) {
            Log.w("Tapdaq Unity", String.format("Unable to set popUpWindow window layout type: %s", new Object[]{e2.getLocalizedMessage()}));
        } catch (InvocationTargetException e3) {
            Log.d("Tapdaq Unity", String.format("Unable to set popUpWindow window layout type: %s", new Object[]{e3.getLocalizedMessage()}));
        }
    }
}
