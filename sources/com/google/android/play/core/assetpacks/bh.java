package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class bh implements a {
    private com.google.android.play.core.internal.ce<b> A;
    private final l a;
    private com.google.android.play.core.internal.ce<Context> b;
    private com.google.android.play.core.internal.ce<cv> c;
    private com.google.android.play.core.internal.ce<au> d;
    private com.google.android.play.core.internal.ce<an> f;
    private com.google.android.play.core.internal.ce<String> g;
    private com.google.android.play.core.internal.ce<ca> j;
    private com.google.android.play.core.internal.ce<bj> l;
    private com.google.android.play.core.internal.ce<dd> m;
    private com.google.android.play.core.internal.ce<co> n;
    private com.google.android.play.core.internal.ce<ct> p;
    private com.google.android.play.core.internal.ce<cx> q;
    private com.google.android.play.core.internal.ce<cd> s;
    private com.google.android.play.core.internal.ce<bl> t;
    private com.google.android.play.core.internal.ce<cj> w;
    private com.google.android.play.core.internal.ce<com.google.android.play.core.splitinstall.p> x;
    private com.google.android.play.core.internal.ce<i> y;
    private com.google.android.play.core.internal.ce<AssetPackManager> z;
    private com.google.android.play.core.internal.ce<bo> e = com.google.android.play.core.internal.cc.b(bp.a);
    private com.google.android.play.core.internal.ce<t> h = new com.google.android.play.core.internal.cb();
    private com.google.android.play.core.internal.ce<Executor> i = com.google.android.play.core.internal.cc.b(m.a);
    private com.google.android.play.core.internal.ce<ar> k = new com.google.android.play.core.internal.cb();
    private com.google.android.play.core.internal.ce<com.google.android.play.core.common.a> o = com.google.android.play.core.internal.cc.b(com.google.android.play.core.common.c.b());
    private com.google.android.play.core.internal.ce<bc> r = com.google.android.play.core.internal.cc.b(new cp(this.h, (byte[]) null));
    private com.google.android.play.core.internal.ce<be> u = com.google.android.play.core.internal.cc.b(bf.a);
    private com.google.android.play.core.internal.ce<Executor> v = com.google.android.play.core.internal.cc.b(r.a);

    /* synthetic */ bh(l lVar) {
        this.a = lVar;
        this.b = new p(lVar);
        this.c = com.google.android.play.core.internal.cc.b(new cp(this.b, (char[]) null));
        this.d = com.google.android.play.core.internal.cc.b(new n(this.b, this.c, (short[]) null));
        this.f = com.google.android.play.core.internal.cc.b(new n(this.b, this.e, (char[]) null));
        this.g = com.google.android.play.core.internal.cc.b(new q(this.b));
        this.j = com.google.android.play.core.internal.cc.b(new cb(this.d, this.h, this.e, this.i));
        this.l = com.google.android.play.core.internal.cc.b(new cb(this.d, this.h, this.k, this.e, (byte[]) null));
        this.m = com.google.android.play.core.internal.cc.b(new cp(this.d, (short[]) null));
        this.n = com.google.android.play.core.internal.cc.b(new cp(this.d));
        this.p = com.google.android.play.core.internal.cc.b(new cu(this.d, this.h, this.j, this.i, this.e, this.o));
        this.q = com.google.android.play.core.internal.cc.b(new n(this.d, this.h, (int[]) null));
        this.s = com.google.android.play.core.internal.cc.b(new cb(this.j, this.d, this.r, this.o, (char[]) null));
        this.t = com.google.android.play.core.internal.cc.b(new bm(this.j, this.h, this.l, this.m, this.n, this.p, this.q, this.s));
        com.google.android.play.core.internal.cb.b(this.k, com.google.android.play.core.internal.cc.b(new bm(this.b, this.j, this.t, this.h, this.e, this.u, this.i, this.v, null)));
        this.w = com.google.android.play.core.internal.cc.b(new cu(this.g, this.k, this.e, this.b, this.c, this.i, null));
        com.google.android.play.core.internal.cb.b(this.h, com.google.android.play.core.internal.cc.b(new o(this.b, this.f, this.w)));
        this.x = com.google.android.play.core.internal.cc.b(com.google.android.play.core.appupdate.g.b(this.b));
        this.y = com.google.android.play.core.internal.cc.b(new j(this.d, this.h, this.k, this.x, this.j, this.e, this.u, this.i, this.o));
        this.z = com.google.android.play.core.internal.cc.b(new n(this.y, this.b));
        this.A = com.google.android.play.core.internal.cc.b(new n(this.b, this.d, (byte[]) null));
    }

    @Override // com.google.android.play.core.assetpacks.a
    public final AssetPackManager a() {
        return this.z.a();
    }

    @Override // com.google.android.play.core.assetpacks.a
    public final void b(AssetPackExtractionService assetPackExtractionService) {
        assetPackExtractionService.a = this.A.a();
    }

    @Override // com.google.android.play.core.assetpacks.a
    public final void c(ExtractionForegroundService extractionForegroundService) {
        extractionForegroundService.a = p.c(this.a);
        extractionForegroundService.b = this.y.a();
    }
}
