package com.google.android.play.core.tasks;

import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class d<ResultT> implements g<ResultT> {
    private final Executor a;
    private final Object b = new Object();

    @Nullable
    private final OnFailureListener c;

    public d(Executor executor, OnFailureListener onFailureListener) {
        this.a = executor;
        this.c = onFailureListener;
    }

    @Override // com.google.android.play.core.tasks.g
    public final void a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            return;
        }
        synchronized (this.b) {
            if (this.c == null) {
                return;
            }
            this.a.execute(new c(this, task));
        }
    }
}
