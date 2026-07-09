package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class z extends com.google.android.play.core.internal.ah {
    final /* synthetic */ Map a;
    final /* synthetic */ com.google.android.play.core.tasks.i b;
    final /* synthetic */ an c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    z(an anVar, com.google.android.play.core.tasks.i iVar, Map map, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.c = anVar;
        this.a = map;
        this.b = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.c.e.c()).e(this.c.c, an.m(this.a), new ai(this.c, this.b));
        } catch (RemoteException e) {
            an.a.c(e, "syncPacks", new Object[0]);
            this.b.d(new RuntimeException(e));
        }
    }
}
