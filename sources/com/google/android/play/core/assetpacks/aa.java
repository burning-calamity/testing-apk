package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class aa extends com.google.android.play.core.internal.ah {
    final /* synthetic */ List a;
    final /* synthetic */ Map b;
    final /* synthetic */ com.google.android.play.core.tasks.i c;
    final /* synthetic */ as d;
    final /* synthetic */ an e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aa(an anVar, com.google.android.play.core.tasks.i iVar, List list, Map map, com.google.android.play.core.tasks.i iVar2, as asVar) {
        super(iVar);
        this.e = anVar;
        this.a = list;
        this.b = map;
        this.c = iVar2;
        this.d = asVar;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        ArrayList arrayListK = an.k(this.a);
        try {
            com.google.android.play.core.internal.t tVar = (com.google.android.play.core.internal.t) this.e.e.c();
            String str = this.e.c;
            Bundle bundleM = an.m(this.b);
            an anVar = this.e;
            tVar.k(str, arrayListK, bundleM, new al(anVar, this.c, anVar.d, this.d));
        } catch (RemoteException e) {
            an.a.c(e, "getPackStates(%s)", this.a);
            this.c.d(new RuntimeException(e));
        }
    }
}
