package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class ai extends ag<List<String>> {
    final /* synthetic */ an c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ai(an anVar, com.google.android.play.core.tasks.i<List<String>> iVar) {
        super(anVar, iVar);
        this.c = anVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void c(List<Bundle> list) {
        super.c(list);
        this.a.e((T) an.v(this.c, list));
    }
}
