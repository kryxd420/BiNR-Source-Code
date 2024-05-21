package com.karman.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class LaunchURLActivity extends Activity {
    private static String sz_LaunchURL;

    public static String getLaunchURL() {
        return sz_LaunchURL;
    }

    private static void setLaunchURL(String str) {
        if (str != null && !str.equals("")) {
            sz_LaunchURL = str;
            Log.d("KARMAN", "Launch URL Set to " + sz_LaunchURL);
        }
    }

    public void onStart() {
        super.onStart();
        Log.d("KARMAN", "Launch URL Activity Start");
        setLaunchURL(getIntent().getDataString());
        goToMainActivity();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("KARMAN", "Launch URL Activity New Intent");
        setLaunchURL(intent.getDataString());
        goToMainActivity();
    }

    private void goToMainActivity() {
        Log.d("KARMAN", "Launch URL Go To Main Activity");
        try {
            String string = getApplicationContext().getPackageManager().getApplicationInfo(getApplicationContext().getPackageName(), 128).metaData.getString("LUMainActivityName");
            Log.d("KARMAN", "Launch URL Going to Main Activity with name: " + string);
            startActivity(new Intent(this, Class.forName(string)));
        } catch (Exception e) {
            Log.d("KARMAN", "Launch URL Going to Main Activity Failed with Exception " + e.toString());
            onBackPressed();
        }
    }
}
