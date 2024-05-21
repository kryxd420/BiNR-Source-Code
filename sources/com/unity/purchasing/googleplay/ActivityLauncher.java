package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;

public class ActivityLauncher implements IActivityLauncher {
    public void startIntentSenderForResult(Activity activity, PendingIntent pendingIntent, int i, Intent intent, String str) throws IntentSender.SendIntentException {
        IntentSender intentSender = pendingIntent.getIntentSender();
        Intent intent2 = new Intent();
        Integer num = 0;
        int intValue = num.intValue();
        Integer num2 = 0;
        Integer num3 = 0;
        activity.startIntentSenderForResult(intentSender, i, intent2, intValue, num2.intValue(), num3.intValue());
    }

    public void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
