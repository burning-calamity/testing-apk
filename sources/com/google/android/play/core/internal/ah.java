package com.google.android.play.core.internal;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ah implements Runnable {

    @Nullable
    private final com.google.android.play.core.tasks.i<?> a;

    ah() {
        this.a = null;
    }

    public ah(@Nullable com.google.android.play.core.tasks.i<?> iVar) {
        this.a = iVar;
    }

    protected abstract void a();

    public final void b(Exception exc) {
        com.google.android.play.core.tasks.i<?> iVar = this.a;
        if (iVar != null) {
            iVar.d(exc);
        }
    }

    @Nullable
    final com.google.android.play.core.tasks.i<?> c() {
        return this.a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Exception e) {
            b(e);
        }
    }
}
