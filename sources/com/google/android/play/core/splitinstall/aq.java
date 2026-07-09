package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class aq extends at<SplitInstallSessionState> {
    aq(au auVar, com.google.android.play.core.tasks.i<SplitInstallSessionState> iVar) {
        super(auVar, iVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.splitinstall.at, com.google.android.play.core.internal.bn
    public final void g(int i, Bundle bundle) throws RemoteException {
        super.g(i, bundle);
        this.a.e((T) SplitInstallSessionState.d(bundle));
    }
}
