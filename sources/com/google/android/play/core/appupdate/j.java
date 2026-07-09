package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;

/* JADX INFO: loaded from: classes.dex */
final class j extends ah {
    final /* synthetic */ String a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ o c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    j(o oVar, com.google.android.play.core.tasks.i iVar, String str, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = oVar;
        this.a = str;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.o) this.c.a.c()).c(this.c.d, o.d(this.c, this.a), new n(this.c, this.b, this.a));
        } catch (RemoteException e) {
            o.b.c(e, "requestUpdateInfo(%s)", this.a);
            this.b.d(new RuntimeException(e));
        }
    }
}
