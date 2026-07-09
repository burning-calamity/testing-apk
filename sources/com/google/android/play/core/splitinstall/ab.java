package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class ab implements ce<SplitInstallManager> {
    private final x a;
    private final ce<i> b;

    public ab(x xVar, ce<i> ceVar) {
        this.a = xVar;
        this.b = ceVar;
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ SplitInstallManager a() {
        i iVarA = this.b.a();
        bh.k(iVarA);
        return iVarA;
    }
}
