package com.google.android.play.core.assetpacks;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class ae extends com.google.android.play.core.internal.ah {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ com.google.android.play.core.tasks.i e;
    final /* synthetic */ an f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ae(an anVar, com.google.android.play.core.tasks.i iVar, int i, String str, String str2, int i2, com.google.android.play.core.tasks.i iVar2) {
        super(iVar);
        this.f = anVar;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = iVar2;
    }

    @Override // com.google.android.play.core.internal.ah
    protected final void a() {
        try {
            ((com.google.android.play.core.internal.t) this.f.e.c()).j(this.f.c, an.r(this.a, this.b, this.c, this.d), an.C(), new ah(this.f, this.e));
        } catch (RemoteException e) {
            an.a.b("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.b, this.c, Integer.valueOf(this.d), Integer.valueOf(this.a));
            this.e.d(new RuntimeException(e));
        }
    }
}
