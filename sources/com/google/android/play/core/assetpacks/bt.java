package com.google.android.play.core.assetpacks;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class bt implements bz {
    private final ca a;
    private final List b;
    private final /* synthetic */ int c = 0;

    bt(ca caVar, List list) {
        this.a = caVar;
        this.b = list;
    }

    bt(ca caVar, List list, byte[] bArr) {
        this.a = caVar;
        this.b = list;
    }

    @Override // com.google.android.play.core.assetpacks.bz
    public final Object a() {
        return this.c != 0 ? this.a.j(this.b) : this.a.i(this.b);
    }
}
