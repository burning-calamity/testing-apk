package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;

/* JADX INFO: loaded from: classes.dex */
final class k extends ah {
    final /* synthetic */ com.google.android.play.core.tasks.i a;
    final /* synthetic */ String b;
    final /* synthetic */ o c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    k(o oVar, com.google.android.play.core.tasks.i iVar, com.google.android.play.core.tasks.i iVar2, String str) {
        super(iVar);
        this.c = oVar;
        this.a = iVar2;
        this.b = str;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.o) this.c.a.c()).d(this.c.d, o.j(), new m(this.c, this.a));
        } catch (RemoteException e) {
            o.b.c(e, "completeUpdate(%s)", this.b);
            this.a.d(new RuntimeException(e));
        }
    }
}
