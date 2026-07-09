package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class p implements ce<o> {
    private final ce<Context> a;
    private final ce<q> b;

    public p(ce<Context> ceVar, ce<q> ceVar2) {
        this.a = ceVar;
        this.b = ceVar2;
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ o a() {
        return new o(((h) this.a).a(), this.b.a());
    }
}
