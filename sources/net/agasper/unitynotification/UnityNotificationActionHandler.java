package net.agasper.unitynotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class UnityNotificationActionHandler extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("id", 0);
        String stringExtra = intent.getStringExtra("gameObject");
        String stringExtra2 = intent.getStringExtra("handlerMethod");
        String stringExtra3 = intent.getStringExtra("actionId");
        boolean booleanExtra = intent.getBooleanExtra("foreground", true);
        ((NotificationManager) context.getSystemService("notification")).cancel(intExtra);
        if (booleanExtra) {
            Intent intent2 = new Intent(context, UnityPlayerActivity.class);
            intent2.setPackage((String) null);
            intent2.addFlags(805306368);
            context.startActivity(intent2);
        }
        UnityPlayer.UnitySendMessage(stringExtra, stringExtra2, stringExtra3);
    }
}
