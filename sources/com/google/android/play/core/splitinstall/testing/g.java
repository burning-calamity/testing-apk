package com.google.android.play.core.splitinstall.testing;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class g implements Runnable {
    private final FakeSplitInstallManager a;
    private final List b;
    private final List c;
    private final List d;
    private final long e;
    private final /* synthetic */ int f = 1;

    g(FakeSplitInstallManager fakeSplitInstallManager, long j, List list, List list2, List list3) {
        this.a = fakeSplitInstallManager;
        this.e = j;
        this.b = list;
        this.c = list2;
        this.d = list3;
    }

    g(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, List list3, long j) {
        this.a = fakeSplitInstallManager;
        this.b = list;
        this.c = list2;
        this.d = list3;
        this.e = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f != 0) {
            this.a.e(this.e, this.b, this.c, this.d);
        } else {
            this.a.d(this.b, this.c, this.d, this.e);
        }
    }
}
