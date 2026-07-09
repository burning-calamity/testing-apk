package jp.co.altplus.y3native;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.player.UnityPlayer;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class LocalNotification extends BroadcastReceiver {
    static final String DEFAULT_CHANNEL_ID = "LocalNotification";
    static final String FLAG_FIRE_ANY_TIME = "FLAG_FIRE_ANY_TIME";

    public static void initialize() {
    }

    public static boolean isPermitted() {
        return NotificationManagerCompat.from(PrivateUtil.getContext()).areNotificationsEnabled();
    }

    public static int register(String[] strArr, int i, int i2) {
        String str;
        Bundle bundle = new Bundle();
        for (int i3 = 0; i3 < strArr.length; i3 += 2) {
            str = strArr[i3];
            switch (str) {
                case "CHANNEL_ID":
                case "TICKER":
                case "MESSAGE":
                case "TITLE":
                case "GROUP":
                case "FLAGS":
                case "PRIORITY":
                    bundle.putString(str, strArr[i3 + 1]);
                    break;
                default:
                    Debug.LogWarning("@LocalNotification.register(delay:" + i + ", slotIndex:" + i2 + ") key:" + str + " is not supported yet.");
                    break;
            }
        }
        bundle.putInt("SLOT_INDEX", i2);
        Context context = PrivateUtil.getContext();
        Intent intent = new Intent(context, (Class<?>) LocalNotification.class);
        intent.putExtra("USER_INFO", bundle);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i2, intent, 134217728);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(13, i);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, calendar.getTimeInMillis(), broadcast);
        return i2;
    }

    public static void cancel(int i) {
        if (PrivateUtil.getActivity() == null) {
            return;
        }
        Context context = PrivateUtil.getContext();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, (Class<?>) LocalNotification.class), DriveFile.MODE_WRITE_ONLY);
        if (broadcast == null) {
            Debug.LogWarning("@LocalNotification.cancel(slotIndex:" + i + ") sender is null.");
            return;
        }
        alarmManager.cancel(broadcast);
        broadcast.cancel();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("USER_INFO");
        bundleExtra.getString("CHANNEL_ID", DEFAULT_CHANNEL_ID);
        String string = bundleExtra.getString("TITLE", "title");
        String string2 = bundleExtra.getString("TICKER", string);
        String string3 = bundleExtra.getString("MESSAGE", "");
        String string4 = bundleExtra.getString("PRIORITY", "DEFAULT");
        boolean z = false;
        int i = string4 == "LOW" ? -1 : string4 == "MIN" ? -2 : string4 == "HIGH" ? 1 : string4 == "MAX" ? 2 : 0;
        int i2 = bundleExtra.getInt("SLOT_INDEX");
        boolean zContains = Arrays.asList(bundleExtra.getString("FLAGS", "").split(",")).contains(FLAG_FIRE_ANY_TIME);
        cancel(i2);
        if (!zContains) {
            if (UnityPlayer.currentActivity != null) {
                UnityPlayer.currentActivity.getWindow();
                UnityPlayer.currentActivity.getWindow().getDecorView();
                if (UnityPlayer.currentActivity.getWindow().getDecorView().getVisibility() == 0) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
        }
        PackageManager packageManager = context.getPackageManager();
        PendingIntent activity = PendingIntent.getActivity(context, -1, packageManager.getLaunchIntentForPackage(context.getPackageName()), 134217728);
        try {
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), packageManager.getApplicationInfo(context.getPackageName(), 128).icon);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentIntent(activity);
            builder.setTicker(string2);
            builder.setContentTitle(string);
            builder.setContentText(string3);
            builder.setSmallIcon(android.R.drawable.ic_menu_info_details);
            builder.setLargeIcon(bitmapDecodeResource);
            builder.setWhen(System.currentTimeMillis());
            builder.setDefaults(7);
            builder.setAutoCancel(true);
            if (Build.VERSION.SDK_INT >= 20) {
                String string5 = bundleExtra.getString("GROUP", "");
                if (string5.length() != 0) {
                    builder.setGroup(string5);
                }
            }
            builder.setPriority(i);
            ((NotificationManager) context.getSystemService("notification")).notify(i2, builder.build());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
