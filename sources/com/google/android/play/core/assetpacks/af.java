package com.google.android.play.core.assetpacks;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class af extends com.google.android.play.core.internal.ah {
    final /* synthetic */ com.google.android.play.core.tasks.i a;
    final /* synthetic */ an b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    af(an anVar, com.google.android.play.core.tasks.i iVar, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.b = anVar;
        this.a = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.b.f.c()).i(this.b.c, an.C(), new aj(this.b, this.a));
        } catch (RemoteException e) {
            an.a.c(e, "keepAlive", new Object[0]);
        }
    }
}
