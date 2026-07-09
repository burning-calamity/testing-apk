package com.google.android.play.core.assetpacks;

import android.R;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import androidx.core.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public class ExtractionForegroundService extends Service {
    Context a;
    i b;
    NotificationManager c;
    private final com.google.android.play.core.internal.ag d = new com.google.android.play.core.internal.ag("ExtractionForegroundService");

    private final synchronized void a(Intent intent) {
        int intExtra;
        String stringExtra = intent.getStringExtra("notification_title");
        String stringExtra2 = intent.getStringExtra("notification_subtext");
        long longExtra = intent.getLongExtra("notification_timeout", 600000L);
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("notification_on_click_intent");
        Notification.Builder timeoutAfter = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this.a, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(longExtra) : new Notification.Builder(this.a).setPriority(-2);
        if (pendingIntent != null) {
            timeoutAfter.setContentIntent(pendingIntent);
        }
        Notification.Builder contentTitle = timeoutAfter.setSmallIcon(R.drawable.stat_sys_download).setOngoing(false).setContentTitle(stringExtra == null ? "Downloading additional file" : stringExtra);
        if (stringExtra2 == null) {
            stringExtra = "Transferring";
        }
        contentTitle.setSubText(stringExtra);
        if (Build.VERSION.SDK_INT >= 21 && (intExtra = intent.getIntExtra("notification_color", 0)) != 0) {
            timeoutAfter.setColor(intExtra).setVisibility(-1);
        }
        Notification notificationBuild = timeoutAfter.build();
        this.d.d("Starting foreground installation service.", new Object[0]);
        this.b.a(true);
        if (Build.VERSION.SDK_INT >= 26) {
            c(intent.getStringExtra("notification_channel_name"));
        }
        startForeground(-1883842196, notificationBuild);
    }

    private final synchronized void b() {
        this.d.d("Stopping service.", new Object[0]);
        this.b.a(false);
        stopForeground(true);
        stopSelf();
    }

    @TargetApi(MotionEventCompat.AXIS_SCROLL)
    private final synchronized void c(@Nullable String str) {
        if (str == null) {
            str = "File downloads by Play";
        }
        this.c.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", str, 2));
    }

    @Override // android.app.Service
    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        ck.j(getApplicationContext()).c(this);
        this.c = (NotificationManager) this.a.getSystemService("notification");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        a(intent);
        int intExtra = intent.getIntExtra("action_type", 0);
        if (intExtra == 2) {
            b();
        } else {
            this.d.b("Unknown action type received: %d", Integer.valueOf(intExtra));
        }
        return 2;
    }
}
