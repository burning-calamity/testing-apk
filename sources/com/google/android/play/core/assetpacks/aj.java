package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
final class aj extends ag<Void> {
    final /* synthetic */ an c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aj(an anVar, com.google.android.play.core.tasks.i<Void> iVar) {
        super(anVar, iVar);
        this.c = anVar;
    }

    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void d(Bundle bundle, Bundle bundle2) {
        super.d(bundle, bundle2);
        if (!this.c.g.compareAndSet(true, false)) {
            an.a.e("Expected keepingAlive to be true, but was false.", new Object[0]);
        }
        if (bundle.getBoolean("keep_alive")) {
            this.c.j();
        }
    }
}
