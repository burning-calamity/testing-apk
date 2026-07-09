package com.google.android.play.core.assetpacks;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class cp implements com.google.android.play.core.internal.ce<co> {
    private final com.google.android.play.core.internal.ce a;
    private final /* synthetic */ int b = 0;

    public cp(com.google.android.play.core.internal.ce<au> ceVar) {
        this.a = ceVar;
    }

    public cp(com.google.android.play.core.internal.ce<t> ceVar, byte[] bArr) {
        this.a = ceVar;
    }

    public cp(com.google.android.play.core.internal.ce<Context> ceVar, char[] cArr) {
        this.a = ceVar;
    }

    public cp(com.google.android.play.core.internal.ce<au> ceVar, short[] sArr) {
        this.a = ceVar;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.play.core.assetpacks.bc, com.google.android.play.core.assetpacks.co] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.play.core.assetpacks.co, com.google.android.play.core.assetpacks.cv] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.google.android.play.core.assetpacks.co, com.google.android.play.core.assetpacks.dd] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ co a() {
        int i = this.b;
        return i != 0 ? i != 1 ? i != 2 ? new dd((au) this.a.a()) : new cv(((p) this.a).a()) : new bc(com.google.android.play.core.internal.cc.c(this.a)) : new co((au) this.a.a());
    }
}
