package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class y implements ce<Context> {
    private final x a;

    public y(x xVar) {
        this.a = xVar;
    }

    @Override // com.google.android.play.core.internal.ce
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Context a() {
        Context contextB = this.a.b();
        bh.k(contextB);
        return contextB;
    }
}
