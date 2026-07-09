package com.google.android.play.core.assetpacks;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class p implements com.google.android.play.core.internal.ce<Context> {
    private final l a;

    public p(l lVar) {
        this.a = lVar;
    }

    public static Context c(l lVar) {
        Context contextA = lVar.a();
        com.google.android.play.core.internal.bh.k(contextA);
        return contextA;
    }

    @Override // com.google.android.play.core.internal.ce
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Context a() {
        return c(this.a);
    }
}
