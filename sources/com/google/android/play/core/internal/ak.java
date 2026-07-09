package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
final class ak extends ah {
    final /* synthetic */ aq a;

    ak(aq aqVar) {
        this.a = aqVar;
    }

    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        if (this.a.l != null) {
            this.a.c.d("Unbind from service.", new Object[0]);
            this.a.b.unbindService(this.a.k);
            this.a.f = false;
            this.a.l = null;
            this.a.k = null;
        }
    }
}
