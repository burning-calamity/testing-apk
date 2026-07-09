package com.google.android.play.core.assetpacks;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class o implements com.google.android.play.core.internal.ce<t> {
    private final com.google.android.play.core.internal.ce<Context> a;
    private final com.google.android.play.core.internal.ce<an> b;
    private final com.google.android.play.core.internal.ce<cj> c;

    public o(com.google.android.play.core.internal.ce<Context> ceVar, com.google.android.play.core.internal.ce<an> ceVar2, com.google.android.play.core.internal.ce<cj> ceVar3) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ t a() {
        t tVar = (t) (l.b(((p) this.a).a()) == null ? com.google.android.play.core.internal.cc.c(this.b).a() : com.google.android.play.core.internal.cc.c(this.c).a());
        com.google.android.play.core.internal.bh.k(tVar);
        return tVar;
    }
}
