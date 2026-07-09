package com.google.android.play.core.assetpacks;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class bl {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("ExtractorLooper");
    private final ca b;
    private final bj c;
    private final dd d;
    private final co e;
    private final ct f;
    private final cx g;
    private final com.google.android.play.core.internal.ca<t> h;
    private final cd i;
    private final AtomicBoolean j = new AtomicBoolean(false);

    bl(ca caVar, com.google.android.play.core.internal.ca<t> caVar2, bj bjVar, dd ddVar, co coVar, ct ctVar, cx cxVar, cd cdVar) {
        this.b = caVar;
        this.h = caVar2;
        this.c = bjVar;
        this.d = ddVar;
        this.e = coVar;
        this.f = ctVar;
        this.g = cxVar;
        this.i = cdVar;
    }

    private final void b(int i, Exception exc) {
        try {
            this.b.p(i);
            this.b.g(i);
        } catch (bk unused) {
            a.b("Error during error handling: %s", exc.getMessage());
        }
    }

    final void a() {
        a.a("Run extractor loop", new Object[0]);
        if (!this.j.compareAndSet(false, true)) {
            a.e("runLoop already looping; return", new Object[0]);
            return;
        }
        while (true) {
            cc ccVarA = null;
            try {
                ccVarA = this.i.a();
            } catch (bk e) {
                a.b("Error while getting next extraction task: %s", e.getMessage());
                if (e.a >= 0) {
                    this.h.a().g(e.a);
                    b(e.a, e);
                }
            }
            if (ccVarA == null) {
                this.j.set(false);
                return;
            }
            try {
                if (ccVarA instanceof bi) {
                    this.c.a((bi) ccVarA);
                } else if (ccVarA instanceof dc) {
                    this.d.a((dc) ccVarA);
                } else if (ccVarA instanceof cn) {
                    this.e.a((cn) ccVarA);
                } else if (ccVarA instanceof cq) {
                    this.f.a((cq) ccVarA);
                } else if (ccVarA instanceof cw) {
                    this.g.a((cw) ccVarA);
                } else {
                    a.b("Unknown task type: %s", ccVarA.getClass().getName());
                }
            } catch (Exception e2) {
                a.b("Error during extraction task: %s", e2.getMessage());
                this.h.a().g(ccVarA.j);
                b(ccVarA.j, e2);
            }
        }
    }
}
