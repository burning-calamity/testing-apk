package com.google.android.play.core.assetpacks;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class e implements Runnable {
    private final i a;
    private final /* synthetic */ int b = 0;

    e(i iVar) {
        this.a = iVar;
    }

    e(i iVar, byte[] bArr) {
        this.a = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.b != 0) {
            this.a.d();
        } else {
            this.a.c();
        }
    }
}
