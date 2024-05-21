package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.j;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InstallReferrerReceiver extends j {
    public void onReceive(Context context, Intent intent) {
        String a = hd.a(context, intent);
        int a2 = a(context, intent);
        if (intent.getBooleanExtra("fiverocks:verify", false) && isOrderedBroadcast()) {
            setResultCode(a2 + 1);
            if (a != null) {
                try {
                    setResultData("http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=" + URLEncoder.encode(a, "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
    }
}
