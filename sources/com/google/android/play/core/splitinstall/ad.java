package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.bl;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
final class ad extends com.google.android.play.core.internal.ah {
    final /* synthetic */ Collection a;
    final /* synthetic */ Collection b;
    final /* synthetic */ com.google.android.play.core.tasks.i c;
    final /* synthetic */ au d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ad(au auVar, com.google.android.play.core.tasks.i iVar, Collection collection, Collection collection2, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.d = auVar;
        this.a = collection;
        this.b = collection2;
        this.c = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        ArrayList arrayListI = au.i(this.a);
        arrayListI.addAll(au.j(this.b));
        try {
            ((bl) this.d.a.c()).c(this.d.d, arrayListI, au.l(), new as(this.d, this.c));
        } catch (RemoteException e) {
            au.b.c(e, "startInstall(%s,%s)", this.a, this.b);
            this.c.d(new RuntimeException(e));
        }
    }
}
