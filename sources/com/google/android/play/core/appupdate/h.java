package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class h implements ce<Context> {
    private final f a;

    public h(f fVar) {
        this.a = fVar;
    }

    @Override // com.google.android.play.core.internal.ce
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Context a() {
        Context contextA = this.a.a();
        bh.k(contextA);
        return contextA;
    }
}
