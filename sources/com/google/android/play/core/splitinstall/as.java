package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class as extends at<Integer> {
    as(au auVar, com.google.android.play.core.tasks.i<Integer> iVar) {
        super(auVar, iVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.splitinstall.at, com.google.android.play.core.internal.bn
    public final void i(int i, Bundle bundle) throws RemoteException {
        super.i(i, bundle);
        this.a.e((T) Integer.valueOf(i));
    }
}
