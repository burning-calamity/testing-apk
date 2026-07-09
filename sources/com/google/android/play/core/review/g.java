package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.ad;
import com.google.android.play.core.internal.ag;

/* JADX INFO: loaded from: classes.dex */
class g<T> extends ad {
    final ag a;
    final com.google.android.play.core.tasks.i<T> b;
    final /* synthetic */ i c;

    g(i iVar, ag agVar, com.google.android.play.core.tasks.i<T> iVar2) {
        this.c = iVar;
        this.a = agVar;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ae
    public void b(Bundle bundle) throws RemoteException {
        this.c.a.b();
        this.a.d("onGetLaunchReviewFlowInfo", new Object[0]);
    }
}
