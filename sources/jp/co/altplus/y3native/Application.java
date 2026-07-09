package jp.co.altplus.y3native;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class Application {
    public static void createNotificationChannel(String str, String str2) {
    }

    public static void openNotificationSettings() {
        Activity activity = PrivateUtil.getActivity();
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("app_package", activity.getPackageName());
        intent.putExtra("app_uid", activity.getApplicationInfo().uid);
        activity.startActivity(intent);
    }
}
