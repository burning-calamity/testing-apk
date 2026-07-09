package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
final class bw extends bt {
    private final bv a = new bv();

    bw() {
    }

    @Override // com.google.android.play.core.internal.bt
    public final void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.a.a(th).add(th2);
    }
}
