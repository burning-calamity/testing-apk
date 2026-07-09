package com.google.android.play.core.assetpacks;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class ad extends com.google.android.play.core.internal.ah {
    final /* synthetic */ int a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ an c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ad(an anVar, com.google.android.play.core.tasks.i iVar, int i, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = anVar;
        this.a = i;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.c.e.c()).h(this.c.c, an.B(this.a), an.C(), new ag(this.c, this.b, (int[]) null));
        } catch (RemoteException e) {
            an.a.c(e, "notifySessionFailed", new Object[0]);
        }
    }
}
