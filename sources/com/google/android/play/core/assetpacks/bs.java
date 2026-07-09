package com.google.android.play.core.assetpacks;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class bs implements bz {
    private final ca a;
    private final int b;
    private final /* synthetic */ int c = 0;

    bs(ca caVar, int i) {
        this.a = caVar;
        this.b = i;
    }

    bs(ca caVar, int i, byte[] bArr) {
        this.a = caVar;
        this.b = i;
    }

    @Override // com.google.android.play.core.assetpacks.bz
    public final Object a() {
        if (this.c != 0) {
            this.a.n(this.b);
            return null;
        }
        this.a.o(this.b);
        return null;
    }
}
