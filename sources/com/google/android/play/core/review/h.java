package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.ag;

/* JADX INFO: loaded from: classes.dex */
final class h extends g<ReviewInfo> {
    h(i iVar, com.google.android.play.core.tasks.i iVar2) {
        super(iVar, new ag("OnRequestInstallCallback"), iVar2);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.review.g, com.google.android.play.core.internal.ae
    public final void b(Bundle bundle) throws RemoteException {
        super.b(bundle);
        this.b.e((T) ReviewInfo.b((PendingIntent) bundle.get("confirmation_intent")));
    }
}
