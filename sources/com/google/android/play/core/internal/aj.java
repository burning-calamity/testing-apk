package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
final class aj extends ah {
    final /* synthetic */ ah a;
    final /* synthetic */ aq b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aj(aq aqVar, com.google.android.play.core.tasks.i iVar, ah ahVar) {
        super(iVar);
        this.b = aqVar;
        this.a = ahVar;
    }

    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        aq.d(this.b, this.a);
    }
}
