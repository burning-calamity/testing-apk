package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class am extends ag<AssetPackStates> {
    private final List<String> c;
    private final bo d;

    am(an anVar, com.google.android.play.core.tasks.i<AssetPackStates> iVar, bo boVar, List<String> list) {
        super(anVar, iVar);
        this.d = boVar;
        this.c = list;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void b(int i, Bundle bundle) {
        super.b(i, bundle);
        this.a.e((T) AssetPackStates.c(bundle, this.d, this.c));
    }
}
