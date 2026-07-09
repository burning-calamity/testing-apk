package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class y extends com.google.android.play.core.internal.ah {
    final /* synthetic */ List a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ an c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    y(an anVar, com.google.android.play.core.tasks.i iVar, List list, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = anVar;
        this.a = list;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.c.e.c()).d(this.c.c, an.k(this.a), an.C(), new ag(this.c, this.b, (byte[]) null));
        } catch (RemoteException e) {
            an.a.c(e, "cancelDownloads(%s)", this.a);
        }
    }
}
