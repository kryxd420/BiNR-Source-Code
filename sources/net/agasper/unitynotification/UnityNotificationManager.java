package net.agasper.unitynotification;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.tapdaq.sdk.TapdaqPlacement;
import com.tapjoy.TJAdUnitConstants;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UnityNotificationManager extends BroadcastReceiver {
    private static Set<String> channels = new HashSet();

    public static void CreateChannel(String str, String str2, String str3, int i, String str4, int i2, int i3, int i4, long[] jArr, String str5) {
        if (Build.VERSION.SDK_INT >= 26) {
            channels.add(str);
            NotificationManager notificationManager = (NotificationManager) UnityPlayer.currentActivity.getSystemService("notification");
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, i);
            notificationChannel.setDescription(str3);
            if (str4 != null) {
                Resources resources = UnityPlayer.currentActivity.getResources();
                int identifier = resources.getIdentifier("raw/" + str4, (String) null, UnityPlayer.currentActivity.getPackageName());
                AudioAttributes build = new AudioAttributes.Builder().setUsage(5).setContentType(4).build();
                notificationChannel.setSound(Uri.parse("android.resource://" + str5 + "/" + identifier), build);
            }
            boolean z = false;
            notificationChannel.enableLights(i2 == 1);
            notificationChannel.setLightColor(i3);
            if (i4 == 1) {
                z = true;
            }
            notificationChannel.enableVibration(z);
            if (jArr == null) {
                jArr = new long[]{1000, 1000};
            }
            notificationChannel.setVibrationPattern(jArr);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @TargetApi(24)
    private static void createChannelIfNeeded(String str, String str2, String str3, boolean z, boolean z2, String str4) {
        if (!channels.contains(str)) {
            channels.add(str);
            CreateChannel(str, str2, str + " notifications", 3, str3, z ? 1 : 0, -16711936, z2 ? 1 : 0, (long[]) null, str4);
        }
    }

    public static void SetNotification(int i, long j, String str, String str2, String str3, int i2, String str4, int i3, int i4, String str5, String str6, int i5, String str7, String str8, ArrayList<NotificationAction> arrayList) {
        String str9;
        int i6 = i;
        int i7 = i3;
        int i8 = i4;
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 26) {
            str9 = str8 == null ? TapdaqPlacement.TDPTagDefault : str8;
            createChannelIfNeeded(str9, str, str4, i8 == 1, i7 == 1, str7);
        } else {
            str9 = str8;
        }
        Activity activity = UnityPlayer.currentActivity;
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(activity, UnityNotificationManager.class);
        intent.putExtra("ticker", str3);
        String str10 = str;
        intent.putExtra(TJAdUnitConstants.String.TITLE, str);
        intent.putExtra("message", str2);
        intent.putExtra("id", i);
        intent.putExtra("color", i5);
        intent.putExtra("sound", i2 == 1);
        intent.putExtra("soundName", str4);
        intent.putExtra("vibrate", i7 == 1);
        if (i8 != 1) {
            z = false;
        }
        intent.putExtra("lights", z);
        intent.putExtra("l_icon", str5);
        intent.putExtra("s_icon", str6);
        intent.putExtra(TJAdUnitConstants.String.BUNDLE, str7);
        intent.putExtra("channel", str9);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("actions", arrayList);
        intent.putExtra("actionsBundle", bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExact(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(activity, i, intent, 134217728));
        } else {
            alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(activity, i, intent, 134217728));
        }
    }

    public static void SetRepeatingNotification(int i, long j, String str, String str2, String str3, long j2, int i2, String str4, int i3, int i4, String str5, String str6, int i5, String str7, String str8, ArrayList<NotificationAction> arrayList) {
        String str9;
        int i6 = i;
        int i7 = i3;
        int i8 = i4;
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 26) {
            str9 = str8 == null ? TapdaqPlacement.TDPTagDefault : str8;
            createChannelIfNeeded(str9, str, str4, i8 == 1, i7 == 1, str7);
        } else {
            str9 = str8;
        }
        Activity activity = UnityPlayer.currentActivity;
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(activity, UnityNotificationManager.class);
        intent.putExtra("ticker", str3);
        String str10 = str;
        intent.putExtra(TJAdUnitConstants.String.TITLE, str);
        intent.putExtra("message", str2);
        intent.putExtra("id", i);
        intent.putExtra("color", i5);
        intent.putExtra("sound", i2 == 1);
        intent.putExtra("soundName", str4);
        intent.putExtra("vibrate", i7 == 1);
        if (i8 != 1) {
            z = false;
        }
        intent.putExtra("lights", z);
        intent.putExtra("l_icon", str5);
        intent.putExtra("s_icon", str6);
        intent.putExtra(TJAdUnitConstants.String.BUNDLE, str7);
        intent.putExtra("channel", str9);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("actions", arrayList);
        intent.putExtra("actionsBundle", bundle);
        alarmManager.setRepeating(0, System.currentTimeMillis() + j, j2, PendingIntent.getBroadcast(activity, i, intent, 0));
    }

    public void onReceive(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        String stringExtra = intent2.getStringExtra("ticker");
        String stringExtra2 = intent2.getStringExtra(TJAdUnitConstants.String.TITLE);
        String stringExtra3 = intent2.getStringExtra("message");
        String stringExtra4 = intent2.getStringExtra("s_icon");
        String stringExtra5 = intent2.getStringExtra("l_icon");
        int intExtra = intent2.getIntExtra("color", 0);
        String stringExtra6 = intent2.getStringExtra(TJAdUnitConstants.String.BUNDLE);
        Boolean valueOf = Boolean.valueOf(intent2.getBooleanExtra("sound", false));
        String stringExtra7 = intent2.getStringExtra("soundName");
        Boolean valueOf2 = Boolean.valueOf(intent2.getBooleanExtra("vibrate", false));
        Boolean valueOf3 = Boolean.valueOf(intent2.getBooleanExtra("lights", false));
        int intExtra2 = intent2.getIntExtra("id", 0);
        String stringExtra8 = intent2.getStringExtra("channel");
        NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
        ArrayList parcelableArrayList = intent2.getBundleExtra("actionsBundle").getParcelableArrayList("actions");
        Resources resources = context.getResources();
        int i = intExtra2;
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(stringExtra6);
        ArrayList arrayList = parcelableArrayList;
        TaskStackBuilder.create(context).addNextIntent(launchIntentForPackage);
        Boolean bool = valueOf3;
        PendingIntent activity = PendingIntent.getActivity(context2, 0, launchIntentForPackage, 134217728);
        if (stringExtra8 == null) {
            stringExtra8 = TapdaqPlacement.TDPTagDefault;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context2, stringExtra8);
        builder.setContentIntent(activity).setAutoCancel(true).setContentTitle(stringExtra2).setContentText(stringExtra3);
        if (Build.VERSION.SDK_INT >= 21) {
            builder.setColor(intExtra);
        }
        if (stringExtra != null && stringExtra.length() > 0) {
            builder.setTicker(stringExtra);
        }
        if (stringExtra4 != null && stringExtra4.length() > 0) {
            builder.setSmallIcon(resources.getIdentifier(stringExtra4, "drawable", context.getPackageName()));
        }
        if (stringExtra5 != null && stringExtra5.length() > 0) {
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, resources.getIdentifier(stringExtra5, "drawable", context.getPackageName())));
        }
        if (valueOf.booleanValue()) {
            if (stringExtra7 != null) {
                int identifier = resources.getIdentifier("raw/" + stringExtra7, (String) null, context.getPackageName());
                builder.setSound(Uri.parse("android.resource://" + stringExtra6 + "/" + identifier));
            } else {
                builder.setSound(RingtoneManager.getDefaultUri(2));
            }
        }
        if (valueOf2.booleanValue()) {
            builder.setVibrate(new long[]{1000, 1000});
        }
        if (bool.booleanValue()) {
            builder.setLights(-16711936, PathInterpolatorCompat.MAX_NUM_POINTS, PathInterpolatorCompat.MAX_NUM_POINTS);
        }
        if (arrayList != null) {
            int i2 = 0;
            while (i2 < arrayList.size()) {
                ArrayList arrayList2 = arrayList;
                NotificationAction notificationAction = (NotificationAction) arrayList2.get(i2);
                builder.addAction((notificationAction.getIcon() == null || notificationAction.getIcon().length() <= 0) ? 0 : resources.getIdentifier(notificationAction.getIcon(), "drawable", context.getPackageName()), notificationAction.getTitle(), buildActionIntent(notificationAction, i2));
                i2++;
                arrayList = arrayList2;
            }
        }
        notificationManager.notify(i, builder.build());
    }

    private static PendingIntent buildActionIntent(NotificationAction notificationAction, int i) {
        Activity activity = UnityPlayer.currentActivity;
        Intent intent = new Intent(activity, UnityNotificationActionHandler.class);
        intent.putExtra("id", i);
        intent.putExtra("gameObject", notificationAction.getGameObject());
        intent.putExtra("handlerMethod", notificationAction.getHandlerMethod());
        intent.putExtra("actionId", notificationAction.getIdentifier());
        intent.putExtra("foreground", notificationAction.isForeground());
        return PendingIntent.getBroadcast(activity, i, intent, 134217728);
    }

    public static void CancelPendingNotification(int i) {
        Activity activity = UnityPlayer.currentActivity;
        ((AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(activity, i, new Intent(activity, UnityNotificationManager.class), 134217728));
    }

    public static void ClearShowingNotifications() {
        ((NotificationManager) UnityPlayer.currentActivity.getSystemService("notification")).cancelAll();
    }
}
