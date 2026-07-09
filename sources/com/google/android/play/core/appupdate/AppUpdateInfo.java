package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppUpdateInfo {
    public static AppUpdateInfo a(@NonNull String str, int i, @UpdateAvailability int i2, @InstallStatus int i3, @Nullable Integer num, int i4, long j, long j2, long j3, long j4, @Nullable PendingIntent pendingIntent, @Nullable PendingIntent pendingIntent2, @Nullable PendingIntent pendingIntent3, @Nullable PendingIntent pendingIntent4) {
        return new r(str, i, i2, i3, num, i4, j, j2, j3, j4, pendingIntent, pendingIntent2, pendingIntent3, pendingIntent4);
    }

    private final boolean i(AppUpdateOptions appUpdateOptions) {
        return appUpdateOptions.allowAssetPackDeletion() && b() <= c();
    }

    public abstract int availableVersionCode();

    abstract long b();

    public abstract long bytesDownloaded();

    abstract long c();

    @Nullable
    public abstract Integer clientVersionStalenessDays();

    @Nullable
    abstract PendingIntent d();

    @Nullable
    abstract PendingIntent e();

    @Nullable
    abstract PendingIntent f();

    @Nullable
    abstract PendingIntent g();

    @Nullable
    final PendingIntent h(AppUpdateOptions appUpdateOptions) {
        if (appUpdateOptions.appUpdateType() == 0) {
            if (e() != null) {
                return e();
            }
            if (i(appUpdateOptions)) {
                return g();
            }
            return null;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            if (d() != null) {
                return d();
            }
            if (i(appUpdateOptions)) {
                return f();
            }
        }
        return null;
    }

    @InstallStatus
    public abstract int installStatus();

    public boolean isUpdateTypeAllowed(@AppUpdateType int i) {
        return h(AppUpdateOptions.defaultOptions(i)) != null;
    }

    public boolean isUpdateTypeAllowed(@NonNull AppUpdateOptions appUpdateOptions) {
        return h(appUpdateOptions) != null;
    }

    @NonNull
    public abstract String packageName();

    public abstract long totalBytesToDownload();

    @UpdateAvailability
    public abstract int updateAvailability();

    public abstract int updatePriority();
}
