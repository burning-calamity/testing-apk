package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class bm implements com.google.android.play.core.internal.ce<bl> {
    private final com.google.android.play.core.internal.ce a;
    private final com.google.android.play.core.internal.ce b;
    private final com.google.android.play.core.internal.ce c;
    private final com.google.android.play.core.internal.ce d;
    private final com.google.android.play.core.internal.ce e;
    private final com.google.android.play.core.internal.ce f;
    private final com.google.android.play.core.internal.ce g;
    private final com.google.android.play.core.internal.ce h;
    private final /* synthetic */ int i = 0;

    public bm(com.google.android.play.core.internal.ce<ca> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, com.google.android.play.core.internal.ce<bj> ceVar3, com.google.android.play.core.internal.ce<dd> ceVar4, com.google.android.play.core.internal.ce<co> ceVar5, com.google.android.play.core.internal.ce<ct> ceVar6, com.google.android.play.core.internal.ce<cx> ceVar7, com.google.android.play.core.internal.ce<cd> ceVar8) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.g = ceVar7;
        this.h = ceVar8;
    }

    public bm(com.google.android.play.core.internal.ce<Context> ceVar, com.google.android.play.core.internal.ce<ca> ceVar2, com.google.android.play.core.internal.ce<bl> ceVar3, com.google.android.play.core.internal.ce<t> ceVar4, com.google.android.play.core.internal.ce<bo> ceVar5, com.google.android.play.core.internal.ce<be> ceVar6, com.google.android.play.core.internal.ce<Executor> ceVar7, com.google.android.play.core.internal.ce<Executor> ceVar8, byte[] bArr) {
        this.a = ceVar;
        this.g = ceVar2;
        this.h = ceVar3;
        this.b = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.c = ceVar7;
        this.d = ceVar8;
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [com.google.android.play.core.assetpacks.ar, com.google.android.play.core.assetpacks.bl] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ bl a() {
        if (this.i == 0) {
            Object objA = this.a.a();
            return new bl((ca) objA, com.google.android.play.core.internal.cc.c(this.b), (bj) this.c.a(), (dd) this.d.a(), (co) this.e.a(), (ct) this.f.a(), (cx) this.g.a(), (cd) this.h.a());
        }
        Context contextA = ((p) this.a).a();
        Object objA2 = this.g.a();
        Object objA3 = this.h.a();
        com.google.android.play.core.internal.ca caVarC = com.google.android.play.core.internal.cc.c(this.b);
        Object objA4 = this.e.a();
        Object objA5 = this.f.a();
        return new ar(contextA, (ca) objA2, (bl) objA3, caVarC, (bo) objA4, (be) objA5, com.google.android.play.core.internal.cc.c(this.c), com.google.android.play.core.internal.cc.c(this.d));
    }
}
