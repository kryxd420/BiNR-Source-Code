package com.tapdaq.sdk;

import java.util.regex.Pattern;

public class TapdaqPlacement {
    public static final String TDPTagBOOTUP = "bootup";
    public static final String TDPTagDefault = "default";
    public static final String TDPTagGameCenter = "game_center";
    public static final String TDPTagGameOver = "game_over";
    public static final String TDPTagHomeScreen = "home_screen";
    public static final String TDPTagIAPStore = "iap_store";
    public static final String TDPTagItemStore = "item_store";
    public static final String TDPTagLeaderBoard = "leaderboard";
    public static final String TDPTagLevelComplete = "level_complete";
    public static final String TDPTagLevelStart = "start";
    public static final String TDPTagMainMenu = "main_menu";
    public static final String TDPTagPause = "pause";
    public static final String TDPTagQuit = "quit";
    public static final String TDPTagSettings = "settings";

    public static boolean isValidTag(String str) {
        if (str != null) {
            return Pattern.compile("^[A-Za-z0-9\\\\._-]+$").matcher(str).matches();
        }
        return false;
    }
}
