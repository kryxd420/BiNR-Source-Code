package com.unity3d.player;

import android.util.Log;
import com.adcolony.sdk.AdColonyAppOptions;

final class e {
    protected static boolean a = false;

    protected static void Log(int i, String str) {
        if (!a) {
            if (i == 6) {
                Log.e(AdColonyAppOptions.UNITY, str);
            }
            if (i == 5) {
                Log.w(AdColonyAppOptions.UNITY, str);
            }
        }
    }
}
