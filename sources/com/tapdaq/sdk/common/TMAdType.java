package com.tapdaq.sdk.common;

public class TMAdType {
    public static final int BANNER = 0;
    private static final String BANNER_NAME = "banner";
    public static final int NATIVE = 4;
    private static final String NATIVE_NAME = "native";
    public static final int OFFERWALL = 5;
    private static final String OFFERWALL_NAME = "offerwall";
    public static final int REWARD_INTERSTITIAL = 3;
    private static final String REWARD_INTERSTITIAL_NAME = "rewarded_video_interstitial";
    public static final int STATIC_INTERSTITIAL = 1;
    private static final String STATIC_INTERSTITIAL_NAME = "static_interstitial";
    public static final int VIDEO_INTERSTITIAL = 2;
    private static final String VIDEO_INTERSTITIAL_NAME = "video_interstitial";

    public static String getString(int i) {
        switch (i) {
            case 0:
                return BANNER_NAME;
            case 1:
                return STATIC_INTERSTITIAL_NAME;
            case 2:
                return VIDEO_INTERSTITIAL_NAME;
            case 3:
                return REWARD_INTERSTITIAL_NAME;
            case 4:
                return "native";
            case 5:
                return OFFERWALL_NAME;
            default:
                return "";
        }
    }

    public static String getStylizeName(int i) {
        switch (i) {
            case 0:
                return "Banner";
            case 1:
                return "Static";
            case 2:
                return "Video";
            case 3:
                return "Rewarded";
            case 4:
                return "Native";
            case 5:
                return "Offerwall";
            default:
                return "";
        }
    }

    public static int getTotalTypes() {
        return 6;
    }

    public static int getInt(String str) {
        if (str.equalsIgnoreCase(BANNER_NAME)) {
            return 0;
        }
        if (str.equalsIgnoreCase(STATIC_INTERSTITIAL_NAME)) {
            return 1;
        }
        if (str.equalsIgnoreCase(VIDEO_INTERSTITIAL_NAME)) {
            return 2;
        }
        if (str.equalsIgnoreCase(REWARD_INTERSTITIAL_NAME)) {
            return 3;
        }
        if (str.equalsIgnoreCase("native")) {
            return 4;
        }
        return str.equalsIgnoreCase(OFFERWALL_NAME) ? 5 : -1;
    }
}
