package com.google.android.play.core.splitinstall.testing;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class h implements com.google.android.play.core.splitinstall.d {
    final /* synthetic */ List a;
    final /* synthetic */ List b;
    final /* synthetic */ long c;
    final /* synthetic */ boolean d;
    final /* synthetic */ List e;
    final /* synthetic */ FakeSplitInstallManager f;

    h(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, long j, boolean z, List list3) {
        this.f = fakeSplitInstallManager;
        this.a = list;
        this.b = list2;
        this.c = j;
        this.d = z;
        this.e = list3;
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void a() {
        this.f.v(this.a, this.b, this.c);
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void b() {
        if (this.d) {
            return;
        }
        this.f.u(this.e, this.a, this.b, this.c, true);
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void c(int i) {
        this.f.w(i);
    }
}
