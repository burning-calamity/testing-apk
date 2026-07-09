package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class c implements m {
    private ce<Context> a;
    private ce<au> b;
    private ce<s> c;
    private ce<p> d;
    private ce<av> e;
    private ce<v> f;
    private ce<File> g;
    private ce<FakeSplitInstallManager> h;
    private ce<i> i;
    private ce<SplitInstallManager> j;

    /* synthetic */ c(x xVar) {
        this.a = new y(xVar);
        this.b = cc.b(new aw(this.a, null));
        this.c = cc.b(new aa(xVar));
        this.d = cc.b(com.google.android.play.core.appupdate.g.b(this.a));
        this.e = cc.b(new aw(this.a));
        this.f = cc.b(new w(this.b, this.c, this.d, this.e));
        this.g = cc.b(new z(this.a));
        this.h = cc.b(new com.google.android.play.core.splitinstall.testing.j(this.a, this.g, this.d));
        this.i = cc.b(new j(this.f, this.h, this.g));
        this.j = cc.b(new ab(xVar, this.i));
    }

    @Override // com.google.android.play.core.splitinstall.m
    public final SplitInstallManager a() {
        return this.j.a();
    }

    @Override // com.google.android.play.core.splitinstall.m
    public final File b() {
        return this.g.a();
    }
}
