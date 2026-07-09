package com.google.android.play.core.review;

import android.os.RemoteException;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.internal.ac;
import com.google.android.play.core.internal.ah;

/* JADX INFO: loaded from: classes.dex */
final class f extends ah {
    final /* synthetic */ com.google.android.play.core.tasks.i a;
    final /* synthetic */ i b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(i iVar, com.google.android.play.core.tasks.i iVar2, com.google.android.play.core.tasks.i iVar3) {
        super(iVar2);
        this.b = iVar;
        this.a = iVar3;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((ac) this.b.a.c()).c(this.b.c, PlayCoreVersion.b("review"), new h(this.b, this.a));
        } catch (RemoteException e) {
            i.b.c(e, "error requesting in-app review for %s", this.b.c);
            this.a.d(new RuntimeException(e));
        }
    }
}
