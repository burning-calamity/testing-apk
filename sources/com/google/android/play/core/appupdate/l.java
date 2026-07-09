package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.ag;

/* JADX INFO: loaded from: classes.dex */
class l<T> extends com.google.android.play.core.internal.p {
    final ag a;
    final com.google.android.play.core.tasks.i<T> b;
    final /* synthetic */ o c;

    l(o oVar, ag agVar, com.google.android.play.core.tasks.i<T> iVar) {
        this.c = oVar;
        this.a = agVar;
        this.b = iVar;
    }

    @Override // com.google.android.play.core.internal.q
    public void b(Bundle bundle) throws RemoteException {
        this.c.a.b();
        this.a.d("onRequestInfo", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.q
    public void c(Bundle bundle) throws RemoteException {
        this.c.a.b();
        this.a.d("onCompleteUpdate", new Object[0]);
    }
}
