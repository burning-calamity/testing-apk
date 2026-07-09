package com.google.android.play.core.assetpacks;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class ac extends com.google.android.play.core.internal.ah {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ com.google.android.play.core.tasks.i c;
    final /* synthetic */ int d;
    final /* synthetic */ an e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ac(an anVar, com.google.android.play.core.tasks.i iVar, int i, String str, com.google.android.play.core.tasks.i iVar2, int i2) {
        super(iVar);
        this.e = anVar;
        this.a = i;
        this.b = str;
        this.c = iVar2;
        this.d = i2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.e.e.c()).g(this.e.c, an.A(this.a, this.b), an.C(), new ak(this.e, this.c, this.a, this.b, this.d));
        } catch (RemoteException e) {
            an.a.c(e, "notifyModuleCompleted", new Object[0]);
        }
    }
}
