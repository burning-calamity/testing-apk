package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
final class ak extends ag<Void> {
    final int c;
    final String d;
    final int e;
    final /* synthetic */ an f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ak(an anVar, com.google.android.play.core.tasks.i<Void> iVar, int i, String str, int i2) {
        super(anVar, iVar);
        this.f = anVar;
        this.c = i;
        this.d = str;
        this.e = i2;
    }

    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void g(Bundle bundle) {
        this.f.e.b();
        an.a.b("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(bundle.getInt("error_code")));
        int i = this.e;
        if (i > 0) {
            this.f.y(this.c, this.d, i - 1);
        }
    }
}
