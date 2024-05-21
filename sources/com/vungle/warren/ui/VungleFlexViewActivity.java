package com.vungle.warren.ui;

import android.os.Build;
import android.util.Log;

public class VungleFlexViewActivity extends VungleActivity {
    private static final String TAG = "VungleFlexViewActivity";

    /* access modifiers changed from: protected */
    public boolean canRotate() {
        boolean z = getApplication().getApplicationInfo().targetSdkVersion >= 27 && Build.VERSION.SDK_INT == 26;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("allow rotation = ");
        sb.append(!z);
        Log.d(str, sb.toString());
        return !z;
    }
}
