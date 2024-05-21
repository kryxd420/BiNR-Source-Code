package com.tapdaq.sdk.adnetworks;

public class TMMediationNetworks {
    public static final int ADCOLONY = 0;
    public static final String ADCOLONY_NAME = "AdColony";
    public static final int AD_MOB = 1;
    public static final String AD_MOB_NAME = "AdMob";
    public static final int APPLOVIN = 2;
    public static final String APPLOVIN_NAME = "AppLovin";
    public static final int CHARTBOOST = 3;
    public static final String CHARTBOOST_NAME = "Chartboost";
    public static final int FACEBOOK = 4;
    public static final String FACEBOOK_NAME = "Facebook";
    public static final int HYPRMX = 5;
    public static final String HYPRMX_NAME = "HyprMX";
    public static final int INMOBI = 6;
    public static final String INMOBI_NAME = "InMobi";
    public static final int IRONSOURCE = 7;
    public static final String IRONSOURCE_NAME = "IronSource";
    public static final int KIIP = 8;
    public static final String KIIP_NAME = "Kiip";
    public static final int MOPUB = 9;
    public static final String MOPUB_NAME = "MoPub";
    public static final int RECEPTIV = 10;
    public static final String RECEPTIV_NAME = "Receptiv";
    public static final int TAPDAQ = 11;
    public static final String TAPDAQ_NAME = "Tapdaq";
    public static final int TAPJOY = 12;
    public static final String TAPJOY_NAME = "Tapjoy";
    public static final int UNITY_ADS = 13;
    public static final String UNITY_ADS_NAME = "UnityAds";
    public static final int VUNGLE = 14;
    public static final String VUNGLE_NAME = "Vungle";
    public static final int YOUAPPI = 15;
    public static final String YOUAPPI_NAME = "YouAppi";
    public static final int ZPLAY = 16;
    public static final String ZPLAY_NAME = "ZPlay";

    public static String getName(int i) {
        switch (i) {
            case 0:
                return ADCOLONY_NAME;
            case 1:
                return "AdMob";
            case 2:
                return APPLOVIN_NAME;
            case 3:
                return CHARTBOOST_NAME;
            case 4:
                return FACEBOOK_NAME;
            case 5:
                return HYPRMX_NAME;
            case 6:
                return INMOBI_NAME;
            case 7:
                return IRONSOURCE_NAME;
            case 8:
                return KIIP_NAME;
            case 9:
                return "MoPub";
            case 10:
                return RECEPTIV_NAME;
            case 11:
                return TAPDAQ_NAME;
            case 12:
                return TAPJOY_NAME;
            case 13:
                return UNITY_ADS_NAME;
            case 14:
                return VUNGLE_NAME;
            case 15:
                return YOUAPPI_NAME;
            case 16:
                return ZPLAY_NAME;
            default:
                return "";
        }
    }

    public static int getTotalNetworks() {
        return 17;
    }

    public static int getID(String str) {
        if (str.equalsIgnoreCase(ADCOLONY_NAME)) {
            return 0;
        }
        if (str.equalsIgnoreCase("AdMob")) {
            return 1;
        }
        if (str.equalsIgnoreCase(APPLOVIN_NAME)) {
            return 2;
        }
        if (str.equalsIgnoreCase(CHARTBOOST_NAME)) {
            return 3;
        }
        if (str.equalsIgnoreCase(FACEBOOK_NAME)) {
            return 4;
        }
        if (str.equalsIgnoreCase(HYPRMX_NAME)) {
            return 5;
        }
        if (str.equalsIgnoreCase(INMOBI_NAME)) {
            return 6;
        }
        if (str.equalsIgnoreCase(IRONSOURCE_NAME)) {
            return 7;
        }
        if (str.equalsIgnoreCase(KIIP_NAME)) {
            return 8;
        }
        if (str.equalsIgnoreCase("MoPub")) {
            return 9;
        }
        if (str.equalsIgnoreCase(RECEPTIV_NAME)) {
            return 10;
        }
        if (str.equalsIgnoreCase(TAPDAQ_NAME)) {
            return 11;
        }
        if (str.equalsIgnoreCase(TAPJOY_NAME)) {
            return 12;
        }
        if (str.equalsIgnoreCase(UNITY_ADS_NAME)) {
            return 13;
        }
        if (str.equalsIgnoreCase(VUNGLE_NAME)) {
            return 14;
        }
        if (str.equalsIgnoreCase(YOUAPPI_NAME)) {
            return 15;
        }
        return str.equalsIgnoreCase(ZPLAY_NAME) ? 16 : -1;
    }

    public static String getStylizedName(String str) {
        for (int i = 0; i < getTotalNetworks(); i++) {
            if (getName(i).equalsIgnoreCase(str)) {
                return getName(i);
            }
        }
        return null;
    }
}
