package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
final class by extends bt {
    by() {
    }

    @Override // com.google.android.play.core.internal.bt
    public final void a(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }
}
