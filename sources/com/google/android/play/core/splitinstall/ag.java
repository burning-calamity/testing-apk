package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.bl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class ag extends com.google.android.play.core.internal.ah {
    final /* synthetic */ List a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ au c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ag(au auVar, com.google.android.play.core.tasks.i iVar, List list, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = auVar;
        this.a = list;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((bl) this.c.a.c()).i(this.c.d, au.j(this.a), au.l(), new an(this.c, this.b));
        } catch (RemoteException e) {
            au.b.c(e, "deferredLanguageInstall(%s)", this.a);
            this.b.d(new RuntimeException(e));
        }
    }
}
