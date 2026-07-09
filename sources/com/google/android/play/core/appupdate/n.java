package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.ag;

/* JADX INFO: loaded from: classes.dex */
final class n extends l<AppUpdateInfo> {
    final /* synthetic */ o d;
    private final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    n(o oVar, com.google.android.play.core.tasks.i<AppUpdateInfo> iVar, String str) {
        super(oVar, new ag("OnRequestInstallCallback"), iVar);
        this.d = oVar;
        this.e = str;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.appupdate.l, com.google.android.play.core.internal.q
    public final void b(Bundle bundle) throws RemoteException {
        super.b(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.b.d(new InstallException(bundle.getInt("error.code", -2)));
            return;
        }
        this.b.e((T) AppUpdateInfo.a(this.e, bundle.getInt("version.code", -1), bundle.getInt("update.availability"), bundle.getInt("install.status", 0), bundle.getInt("client.version.staleness", -1) == -1 ? null : Integer.valueOf(bundle.getInt("client.version.staleness")), bundle.getInt("in.app.update.priority", 0), bundle.getLong("bytes.downloaded"), bundle.getLong("total.bytes.to.download"), bundle.getLong("additional.size.required"), this.d.f.a(), (PendingIntent) bundle.getParcelable("blocking.intent"), (PendingIntent) bundle.getParcelable("nonblocking.intent"), (PendingIntent) bundle.getParcelable("blocking.destructive.intent"), (PendingIntent) bundle.getParcelable("nonblocking.destructive.intent")));
    }
}
