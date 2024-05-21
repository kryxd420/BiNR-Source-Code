package com.tapdaq.sdk.common;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.tapdaq.sdk.TapdaqError;
import com.tapdaq.sdk.model.TMAdSize;

public class TMBannerAdSizes {
    public static TMAdSize FULL = new TMAdSize(468, 60, "FULL");
    public static TMAdSize LARGE = new TMAdSize(320, 100, "LARGE");
    public static TMAdSize LEADERBOARD = new TMAdSize(728, 90, "LEADERBOARD");
    public static TMAdSize MEDIUM_RECT = new TMAdSize(TapdaqError.NO_AD_AVAILABLE, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "MEDIUM_RECT");
    public static TMAdSize SKYSCRAPER = new TMAdSize(160, FetchConst.PRIORITY_NORMAL, "SKYSCRAPER");
    public static TMAdSize SMART = new TMAdSize(0, 50, "SMART");
    public static TMAdSize STANDARD = new TMAdSize(320, 50, "STANDARD");

    public static TMAdSize get(String str) {
        if (str == null) {
            return null;
        }
        if (str.equalsIgnoreCase(STANDARD.name)) {
            return STANDARD;
        }
        if (str.equalsIgnoreCase(MEDIUM_RECT.name)) {
            return MEDIUM_RECT;
        }
        if (str.equalsIgnoreCase(LARGE.name)) {
            return LARGE;
        }
        if (str.equalsIgnoreCase(FULL.name)) {
            return FULL;
        }
        if (str.equalsIgnoreCase(LEADERBOARD.name)) {
            return LEADERBOARD;
        }
        if (str.equalsIgnoreCase(SMART.name)) {
            return SMART;
        }
        if (str.equalsIgnoreCase(SKYSCRAPER.name)) {
            return SKYSCRAPER;
        }
        return null;
    }
}
