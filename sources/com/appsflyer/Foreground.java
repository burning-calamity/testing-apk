package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class Foreground {

    @VisibleForTesting
    public static long CHECK_DELAY = 500;
    public static Listener listener;

    public interface Listener {
        void onBecameBackground(Context context);

        void onBecameForeground(Activity activity);
    }

    /* JADX INFO: renamed from: com.appsflyer.Foreground$3, reason: invalid class name */
    static class AnonymousClass3 implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        final /* synthetic */ Listener f154;

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        boolean f155;

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        boolean f153 = true;

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private Executor f152 = Executors.newSingleThreadExecutor();

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
        }

        AnonymousClass3(Listener listener) {
            this.f154 = listener;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(final Activity activity) {
            this.f152.execute(new Runnable() { // from class: com.appsflyer.Foreground.3.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (!AnonymousClass3.this.f155) {
                        try {
                            AnonymousClass3.this.f154.onBecameForeground(activity);
                        } catch (Exception e) {
                            AFLogger.afErrorLog("Listener thrown an exception: ", e);
                        }
                    }
                    AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                    anonymousClass3.f153 = false;
                    anonymousClass3.f155 = true;
                }
            });
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(@NonNull final Activity activity) {
            this.f152.execute(new Runnable() { // from class: com.appsflyer.Foreground.3.4
                @Override // java.lang.Runnable
                public final void run() {
                    AnonymousClass3.this.f153 = true;
                    final Context applicationContext = activity.getApplicationContext();
                    try {
                        new Timer().schedule(new TimerTask() { // from class: com.appsflyer.Foreground.3.4.3
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public final void run() {
                                if (AnonymousClass3.this.f155 && AnonymousClass3.this.f153) {
                                    AnonymousClass3.this.f155 = false;
                                    try {
                                        AnonymousClass3.this.f154.onBecameBackground(applicationContext);
                                    } catch (Exception e) {
                                        AFLogger.afErrorLog("Listener threw exception! ", e);
                                    }
                                }
                            }
                        }, Foreground.CHECK_DELAY);
                    } catch (Throwable th) {
                        AFLogger.afErrorLog("Background task failed with a throwable: ", th);
                    }
                }
            });
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(@NonNull final Activity activity, Bundle bundle) {
            this.f152.execute(new Runnable() { // from class: com.appsflyer.Foreground.3.1
                @Override // java.lang.Runnable
                public final void run() {
                    AFDeepLinkManager.getInstance().collectIntentsFromActivities(activity.getIntent());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    static void m123(Context context, Listener listener2) {
        listener = listener2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(listener2);
        if (context instanceof Activity) {
            anonymousClass3.onActivityResumed((Activity) context);
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(anonymousClass3);
    }
}
