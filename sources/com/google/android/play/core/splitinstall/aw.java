package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class aw implements ce<av> {
    private final ce a;
    private final /* synthetic */ int b = 0;

    public aw(ce<Context> ceVar) {
        this.a = ceVar;
    }

    public aw(ce<Context> ceVar, byte[] bArr) {
        this.a = ceVar;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.play.core.splitinstall.au, com.google.android.play.core.splitinstall.av] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ av a() {
        return this.b != 0 ? new au(((y) this.a).a()) : new av(((y) this.a).a());
    }
}
