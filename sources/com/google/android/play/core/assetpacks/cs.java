package com.google.android.play.core.assetpacks;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class cs implements Runnable {
    private final au a;

    private cs(au auVar) {
        this.a = auVar;
    }

    static Runnable a(au auVar) {
        return new cs(auVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.s();
    }
}
