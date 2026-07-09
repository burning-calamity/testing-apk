package com.google.android.play.core.appupdate.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.appupdate.a;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FakeAppUpdateManager implements AppUpdateManager {
    private final a a;
    private final Context b;
    private final List<Integer> c = new ArrayList();

    @InstallStatus
    private int d = 0;

    @InstallErrorCode
    private int e = 0;
    private boolean f = false;
    private int g = 0;

    @Nullable
    private Integer h = null;
    private int i = 0;
    private long j = 0;
    private long k = 0;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;

    @Nullable
    @AppUpdateType
    private Integer o;

    public FakeAppUpdateManager(Context context) {
        this.a = new a(context);
        this.b = context;
    }

    private final boolean a(AppUpdateInfo appUpdateInfo, AppUpdateOptions appUpdateOptions) {
        int i;
        if (!appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) && (!AppUpdateOptions.defaultOptions(appUpdateOptions.appUpdateType()).equals(appUpdateOptions) || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions.appUpdateType()))) {
            return false;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            this.m = true;
            i = 1;
        } else {
            this.l = true;
            i = 0;
        }
        this.o = i;
        return true;
    }

    @UpdateAvailability
    private final int b() {
        if (!this.f) {
            return 1;
        }
        int i = this.d;
        return (i == 0 || i == 4 || i == 5 || i == 6) ? 2 : 3;
    }

    private final void c() {
        this.a.i(InstallState.a(this.d, this.j, this.k, this.e, this.b.getPackageName()));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<Void> completeUpdate() {
        int i = this.e;
        if (i != 0) {
            return Tasks.b(new InstallException(i));
        }
        int i2 = this.d;
        if (i2 != 11) {
            return i2 == 3 ? Tasks.b(new InstallException(-8)) : Tasks.b(new InstallException(-7));
        }
        this.d = 3;
        this.n = true;
        Integer num = 0;
        if (num.equals(this.o)) {
            c();
        }
        return Tasks.a(null);
    }

    public void downloadCompletes() {
        int i = this.d;
        if (i == 2 || i == 1) {
            this.d = 11;
            this.j = 0L;
            this.k = 0L;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
                return;
            }
            Integer num2 = 1;
            if (num2.equals(this.o)) {
                completeUpdate();
            }
        }
    }

    public void downloadFails() {
        int i = this.d;
        if (i == 1 || i == 2) {
            this.d = 5;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
            this.o = null;
            this.m = false;
            this.d = 0;
        }
    }

    public void downloadStarts() {
        if (this.d == 1) {
            this.d = 2;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<AppUpdateInfo> getAppUpdateInfo() {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        PendingIntent broadcast;
        PendingIntent pendingIntent3;
        PendingIntent broadcast2;
        PendingIntent broadcast3;
        int i = this.e;
        if (i != 0) {
            return Tasks.b(new InstallException(i));
        }
        if (b() == 2 && this.e == 0) {
            if (this.c.contains(0)) {
                broadcast2 = PendingIntent.getBroadcast(this.b, 0, new Intent(), 0);
                broadcast3 = PendingIntent.getBroadcast(this.b, 0, new Intent(), 0);
            } else {
                broadcast2 = null;
                broadcast3 = null;
            }
            if (this.c.contains(1)) {
                PendingIntent broadcast4 = PendingIntent.getBroadcast(this.b, 0, new Intent(), 0);
                pendingIntent2 = broadcast2;
                broadcast = PendingIntent.getBroadcast(this.b, 0, new Intent(), 0);
                pendingIntent = broadcast4;
            } else {
                pendingIntent2 = broadcast2;
                pendingIntent = null;
                broadcast = null;
            }
            pendingIntent3 = broadcast3;
        } else {
            pendingIntent = null;
            pendingIntent2 = null;
            broadcast = null;
            pendingIntent3 = null;
        }
        return Tasks.a(AppUpdateInfo.a(this.b.getPackageName(), this.g, b(), this.d, this.h, this.i, this.j, this.k, 0L, 0L, pendingIntent, pendingIntent2, broadcast, pendingIntent3));
    }

    @Nullable
    @AppUpdateType
    public Integer getTypeForUpdateInProgress() {
        return this.o;
    }

    public void installCompletes() {
        if (this.d == 3) {
            this.d = 4;
            this.f = false;
            this.g = 0;
            this.h = null;
            this.i = 0;
            this.j = 0L;
            this.k = 0L;
            this.m = false;
            this.n = false;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
            this.o = null;
            this.d = 0;
        }
    }

    public void installFails() {
        if (this.d == 3) {
            this.d = 5;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
            this.o = null;
            this.n = false;
            this.m = false;
            this.d = 0;
        }
    }

    public boolean isConfirmationDialogVisible() {
        return this.l;
    }

    public boolean isImmediateFlowVisible() {
        return this.m;
    }

    public boolean isInstallSplashScreenVisible() {
        return this.n;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.a.f(installStateUpdatedListener);
    }

    public void setBytesDownloaded(long j) {
        if (this.d != 2 || j > this.k) {
            return;
        }
        this.j = j;
        Integer num = 0;
        if (num.equals(this.o)) {
            c();
        }
    }

    public void setClientVersionStalenessDays(@Nullable Integer num) {
        if (this.f) {
            this.h = num;
        }
    }

    public void setInstallErrorCode(@InstallErrorCode int i) {
        this.e = i;
    }

    public void setTotalBytesToDownload(long j) {
        if (this.d == 2) {
            this.k = j;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
        }
    }

    public void setUpdateAvailable(int i) {
        this.f = true;
        this.c.clear();
        this.c.add(0);
        this.c.add(1);
        this.g = i;
    }

    public void setUpdateAvailable(int i, @AppUpdateType int i2) {
        this.f = true;
        this.c.clear();
        this.c.add(Integer.valueOf(i2));
        this.g = i;
    }

    public void setUpdateNotAvailable() {
        this.f = false;
        this.h = null;
    }

    public void setUpdatePriority(int i) {
        if (this.f) {
            this.i = i;
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        return a(appUpdateInfo, appUpdateOptions) ? Tasks.a(-1) : Tasks.b(new InstallException(-6));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, @AppUpdateType int i, Activity activity, int i2) {
        return a(appUpdateInfo, AppUpdateOptions.newBuilder(i).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, @AppUpdateType int i, IntentSenderForResultStarter intentSenderForResultStarter, int i2) {
        return a(appUpdateInfo, AppUpdateOptions.newBuilder(i).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i) {
        return a(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i) {
        return a(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.a.g(installStateUpdatedListener);
    }

    public void userAcceptsUpdate() {
        if (this.l || this.m) {
            this.l = false;
            this.d = 1;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
        }
    }

    public void userCancelsDownload() {
        int i = this.d;
        if (i == 1 || i == 2) {
            this.d = 6;
            Integer num = 0;
            if (num.equals(this.o)) {
                c();
            }
            this.o = null;
            this.m = false;
            this.d = 0;
        }
    }

    public void userRejectsUpdate() {
        if (this.l || this.m) {
            this.l = false;
            this.m = false;
            this.o = null;
            this.d = 0;
        }
    }
}
