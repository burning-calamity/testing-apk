package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
final class al extends ag<AssetPackStates> {
    private final bo c;
    private final as d;

    al(an anVar, com.google.android.play.core.tasks.i<AssetPackStates> iVar, bo boVar, as asVar) {
        super(anVar, iVar);
        this.c = boVar;
        this.d = asVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void f(Bundle bundle, Bundle bundle2) {
        super.f(bundle, bundle2);
        this.a.e((T) AssetPackStates.d(bundle, this.c, this.d));
    }
}
