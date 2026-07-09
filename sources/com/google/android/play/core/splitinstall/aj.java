package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.bl;

/* JADX INFO: loaded from: classes.dex */
final class aj extends com.google.android.play.core.internal.ah {
    final /* synthetic */ com.google.android.play.core.tasks.i a;
    final /* synthetic */ au b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aj(au auVar, com.google.android.play.core.tasks.i iVar, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.b = auVar;
        this.a = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((bl) this.b.a.c()).f(this.b.d, new ar(this.b, this.a));
        } catch (RemoteException e) {
            au.b.c(e, "getSessionStates", new Object[0]);
            this.a.d(new RuntimeException(e));
        }
    }
}
