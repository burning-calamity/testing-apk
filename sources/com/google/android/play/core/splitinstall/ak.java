package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.bl;

/* JADX INFO: loaded from: classes.dex */
final class ak extends com.google.android.play.core.internal.ah {
    final /* synthetic */ int a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ au c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ak(au auVar, com.google.android.play.core.tasks.i iVar, int i, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = auVar;
        this.a = i;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((bl) this.c.a.c()).d(this.c.d, this.a, au.l(), new al(this.c, this.b));
        } catch (RemoteException e) {
            au.b.c(e, "cancelInstall(%d)", Integer.valueOf(this.a));
            this.b.d(new RuntimeException(e));
        }
    }
}
