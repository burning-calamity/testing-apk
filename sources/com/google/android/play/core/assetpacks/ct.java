package com.google.android.play.core.assetpacks;

import java.io.File;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class ct {
    private final au a;
    private final com.google.android.play.core.internal.ca<t> b;
    private final ca c;
    private final com.google.android.play.core.internal.ca<Executor> d;
    private final bo e;
    private final com.google.android.play.core.common.a f;

    ct(au auVar, com.google.android.play.core.internal.ca<t> caVar, ca caVar2, com.google.android.play.core.internal.ca<Executor> caVar3, bo boVar, com.google.android.play.core.common.a aVar) {
        this.a = auVar;
        this.b = caVar;
        this.c = caVar2;
        this.d = caVar3;
        this.e = boVar;
        this.f = aVar;
    }

    public final void a(final cq cqVar) {
        Executor executorA;
        Runnable runnableA;
        File fileJ = this.a.j(cqVar.k, cqVar.a, cqVar.b);
        File fileP = this.a.p(cqVar.k, cqVar.a, cqVar.b);
        if (!fileJ.exists() || !fileP.exists()) {
            throw new bk(String.format("Cannot find pack files to move for pack %s.", cqVar.k), cqVar.j);
        }
        File fileF = this.a.f(cqVar.k, cqVar.a, cqVar.b);
        fileF.mkdirs();
        if (!fileJ.renameTo(fileF)) {
            throw new bk("Cannot move merged pack files to final location.", cqVar.j);
        }
        new File(this.a.f(cqVar.k, cqVar.a, cqVar.b), "merge.tmp").delete();
        File fileG = this.a.g(cqVar.k, cqVar.a, cqVar.b);
        fileG.mkdirs();
        if (!fileP.renameTo(fileG)) {
            throw new bk("Cannot move metadata files to final location.", cqVar.j);
        }
        if (this.f.a()) {
            executorA = this.d.a();
            runnableA = new Runnable(this, cqVar) { // from class: com.google.android.play.core.assetpacks.cr
                private final ct a;
                private final cq b;

                {
                    this.a = this;
                    this.b = cqVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.a.b(this.b);
                }
            };
        } else {
            executorA = this.d.a();
            au auVar = this.a;
            auVar.getClass();
            runnableA = cs.a(auVar);
        }
        executorA.execute(runnableA);
        this.c.f(cqVar.k, cqVar.a, cqVar.b);
        this.e.a(cqVar.k);
        this.b.a().f(cqVar.j, cqVar.k);
    }

    final /* synthetic */ void b(cq cqVar) {
        this.a.r(cqVar.k, cqVar.a, cqVar.b);
    }
}
