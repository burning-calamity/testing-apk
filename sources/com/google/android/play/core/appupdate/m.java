package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.ag;

/* JADX INFO: loaded from: classes.dex */
final class m extends l<Void> {
    m(o oVar, com.google.android.play.core.tasks.i<Void> iVar) {
        super(oVar, new ag("OnCompleteUpdateCallback"), iVar);
    }

    @Override // com.google.android.play.core.appupdate.l, com.google.android.play.core.internal.q
    public final void c(Bundle bundle) throws RemoteException {
        super.c(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.b.d(new InstallException(bundle.getInt("error.code", -2)));
        } else {
            this.b.e(null);
        }
    }
}
