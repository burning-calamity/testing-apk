package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    private ce<Context> a;
    private ce<q> b;
    private ce<o> c;
    private ce<a> d;
    private ce<d> e;
    private ce<AppUpdateManager> f;

    /* synthetic */ w(f fVar) {
        this.a = new h(fVar);
        this.b = cc.b(new g(this.a, (char[]) null));
        this.c = cc.b(new p(this.a, this.b));
        this.d = cc.b(new g(this.a, (byte[]) null));
        this.e = cc.b(new e(this.c, this.d, this.a));
        this.f = cc.b(new g(this.e));
    }

    public final AppUpdateManager a() {
        return this.f.a();
    }
}
