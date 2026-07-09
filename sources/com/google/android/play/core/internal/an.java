package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class an extends ah {
    final /* synthetic */ IBinder a;
    final /* synthetic */ ap b;

    an(ap apVar, IBinder iBinder) {
        this.b = apVar;
        this.a = iBinder;
    }

    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        aq aqVar = this.b.a;
        aqVar.l = (IInterface) aqVar.h.a(this.a);
        aq.j(this.b.a);
        this.b.a.f = false;
        Iterator it = this.b.a.e.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.b.a.e.clear();
    }
}
