package com.google.android.play.core.assetpacks;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class j implements com.google.android.play.core.internal.ce<i> {
    private final com.google.android.play.core.internal.ce<au> a;
    private final com.google.android.play.core.internal.ce<t> b;
    private final com.google.android.play.core.internal.ce<ar> c;
    private final com.google.android.play.core.internal.ce<com.google.android.play.core.splitinstall.p> d;
    private final com.google.android.play.core.internal.ce<ca> e;
    private final com.google.android.play.core.internal.ce<bo> f;
    private final com.google.android.play.core.internal.ce<be> g;
    private final com.google.android.play.core.internal.ce<Executor> h;
    private final com.google.android.play.core.internal.ce<com.google.android.play.core.common.a> i;

    public j(com.google.android.play.core.internal.ce<au> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, com.google.android.play.core.internal.ce<ar> ceVar3, com.google.android.play.core.internal.ce<com.google.android.play.core.splitinstall.p> ceVar4, com.google.android.play.core.internal.ce<ca> ceVar5, com.google.android.play.core.internal.ce<bo> ceVar6, com.google.android.play.core.internal.ce<be> ceVar7, com.google.android.play.core.internal.ce<Executor> ceVar8, com.google.android.play.core.internal.ce<com.google.android.play.core.common.a> ceVar9) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.g = ceVar7;
        this.h = ceVar8;
        this.i = ceVar9;
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ i a() {
        return new i(this.a.a(), com.google.android.play.core.internal.cc.c(this.b), this.c.a(), this.d.a(), this.e.a(), this.f.a(), this.g.a(), com.google.android.play.core.internal.cc.c(this.h), this.i.a());
    }
}
