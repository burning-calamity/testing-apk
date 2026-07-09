package com.google.android.play.core.tasks;

import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class b<ResultT> implements g<ResultT> {
    private final Executor a;
    private final Object b = new Object();

    @Nullable
    private final OnCompleteListener<ResultT> c;

    public b(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        this.a = executor;
        this.c = onCompleteListener;
    }

    @Override // com.google.android.play.core.tasks.g
    public final void a(Task<ResultT> task) {
        synchronized (this.b) {
            if (this.c == null) {
                return;
            }
            this.a.execute(new a(this, task));
        }
    }
}
