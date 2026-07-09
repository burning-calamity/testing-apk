package com.google.android.play.core.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.aq;
import com.google.android.play.core.internal.bp;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* JADX INFO: loaded from: classes.dex */
final class o {
    private static final ag b = new ag("AppUpdateService");
    private static final Intent c = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");

    @Nullable
    aq<com.google.android.play.core.internal.o> a;
    private final String d;
    private final Context e;
    private final q f;

    public o(Context context, q qVar) {
        this.d = context.getPackageName();
        this.e = context;
        this.f = qVar;
        if (bp.a(context)) {
            this.a = new aq<>(com.google.android.play.core.splitcompat.p.c(context), b, "AppUpdateService", c, i.a);
        }
    }

    static /* synthetic */ Bundle d(o oVar, String str) {
        Integer numValueOf;
        Bundle bundle = new Bundle();
        bundle.putAll(j());
        bundle.putString("package.name", str);
        try {
            numValueOf = Integer.valueOf(oVar.e.getPackageManager().getPackageInfo(oVar.e.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            b.b("The current version of the app could not be retrieved", new Object[0]);
            numValueOf = null;
        }
        if (numValueOf != null) {
            bundle.putInt("app.version.code", numValueOf.intValue());
        }
        return bundle;
    }

    private static <T> Task<T> i() {
        b.b("onError(%d)", -9);
        return Tasks.b(new InstallException(-9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle j() {
        Bundle bundle = new Bundle();
        bundle.putAll(PlayCoreVersion.b("app_update"));
        bundle.putInt("playcore.version.code", 11000);
        return bundle;
    }

    public final Task<AppUpdateInfo> a(String str) {
        if (this.a == null) {
            return i();
        }
        b.d("requestUpdateInfo(%s)", str);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new j(this, iVar, str, iVar));
        return iVar.c();
    }

    public final Task<Void> b(String str) {
        if (this.a == null) {
            return i();
        }
        b.d("completeUpdate(%s)", str);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new k(this, iVar, iVar, str));
        return iVar.c();
    }
}
