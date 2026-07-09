package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class cu implements com.google.android.play.core.internal.ce<ct> {
    private final com.google.android.play.core.internal.ce a;
    private final com.google.android.play.core.internal.ce b;
    private final com.google.android.play.core.internal.ce c;
    private final com.google.android.play.core.internal.ce d;
    private final com.google.android.play.core.internal.ce e;
    private final com.google.android.play.core.internal.ce f;
    private final /* synthetic */ int g = 0;

    public cu(com.google.android.play.core.internal.ce<au> ceVar, com.google.android.play.core.internal.ce<t> ceVar2, com.google.android.play.core.internal.ce<ca> ceVar3, com.google.android.play.core.internal.ce<Executor> ceVar4, com.google.android.play.core.internal.ce<bo> ceVar5, com.google.android.play.core.internal.ce<com.google.android.play.core.common.a> ceVar6) {
        this.a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
    }

    public cu(com.google.android.play.core.internal.ce<String> ceVar, com.google.android.play.core.internal.ce<ar> ceVar2, com.google.android.play.core.internal.ce<bo> ceVar3, com.google.android.play.core.internal.ce<Context> ceVar4, com.google.android.play.core.internal.ce<cv> ceVar5, com.google.android.play.core.internal.ce<Executor> ceVar6, byte[] bArr) {
        this.f = ceVar;
        this.b = ceVar2;
        this.e = ceVar3;
        this.d = ceVar4;
        this.c = ceVar5;
        this.a = ceVar6;
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [com.google.android.play.core.assetpacks.cj, com.google.android.play.core.assetpacks.ct] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ ct a() {
        if (this.g == 0) {
            Object objA = this.a.a();
            com.google.android.play.core.internal.ca caVarC = com.google.android.play.core.internal.cc.c(this.b);
            Object objA2 = this.c.a();
            return new ct((au) objA, caVarC, (ca) objA2, com.google.android.play.core.internal.cc.c(this.d), (bo) this.e.a(), (com.google.android.play.core.common.a) this.f.a());
        }
        String str = (String) this.f.a();
        Object objA3 = this.b.a();
        Object objA4 = this.e.a();
        Context contextA = ((p) this.d).a();
        Object objA5 = this.c.a();
        return new cj(str != null ? new File(contextA.getExternalFilesDir(null), str) : contextA.getExternalFilesDir(null), (ar) objA3, (bo) objA4, contextA, (cv) objA5, com.google.android.play.core.internal.cc.c(this.a));
    }
}
