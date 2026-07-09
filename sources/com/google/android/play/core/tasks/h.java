package com.google.android.play.core.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
final class h<ResultT> {
    private final Object a = new Object();
    private Queue<g<ResultT>> b;
    private boolean c;

    h() {
    }

    public final void a(g<ResultT> gVar) {
        synchronized (this.a) {
            if (this.b == null) {
                this.b = new ArrayDeque();
            }
            this.b.add(gVar);
        }
    }

    public final void b(Task<ResultT> task) {
        g<ResultT> gVarPoll;
        synchronized (this.a) {
            if (this.b != null && !this.c) {
                this.c = true;
                while (true) {
                    synchronized (this.a) {
                        gVarPoll = this.b.poll();
                        if (gVarPoll == null) {
                            this.c = false;
                            return;
                        }
                    }
                    gVarPoll.a(task);
                }
            }
        }
    }
}
