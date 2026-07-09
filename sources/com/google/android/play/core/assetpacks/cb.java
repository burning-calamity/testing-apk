package com.google.android.play.core.assetpacks;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class cb implements com.google.android.play.core.internal.ce<ca> {
    private final com.google.android.play.core.internal.ce a;
    private final com.google.android.play.core.internal.ce b;
    private final com.google.android.play.core.internal.ce c;
    private final com.google.android.play.core.internal.ce d;
    private final /* synthetic */ int e = 0;

    public cb(com.google.android.play.core.internal.ce<au> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, com.google.android.play.core.internal.ce<bo> ceVar3, com.google.android.play.core.internal.ce<Executor> ceVar4) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
    }

    public cb(com.google.android.play.core.internal.ce<au> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, com.google.android.play.core.internal.ce<ar> ceVar3, com.google.android.play.core.internal.ce<bo> ceVar4, byte[] bArr) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
    }

    public cb(com.google.android.play.core.internal.ce<ca> ceVar, com.google.android.play.core.internal.ce<au> ceVar2, com.google.android.play.core.internal.ce<bc> ceVar3, com.google.android.play.core.internal.ce<com.google.android.play.core.common.a> ceVar4, char[] cArr) {
        this.c = ceVar;
        this.b = ceVar2;
        this.a = ceVar3;
        this.d = ceVar4;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.play.core.assetpacks.bj, com.google.android.play.core.assetpacks.ca] */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.google.android.play.core.assetpacks.ca, com.google.android.play.core.assetpacks.cd] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ ca a() {
        int i = this.e;
        if (i == 0) {
            Object objA = this.a.a();
            return new ca((au) objA, com.google.android.play.core.internal.cc.c(this.b), (bo) this.c.a(), com.google.android.play.core.internal.cc.c(this.d));
        }
        if (i == 1) {
            return new bj((au) this.a.a(), com.google.android.play.core.internal.cc.c(this.b), com.google.android.play.core.internal.cc.c(this.c), (bo) this.d.a());
        }
        Object objA2 = this.c.a();
        Object objA3 = this.b.a();
        return new cd((ca) objA2, (au) objA3, (bc) this.a.a(), (com.google.android.play.core.common.a) this.d.a());
    }
}
