package com.google.android.play.core.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class bv {
    private final ConcurrentHashMap<bu, List<Throwable>> a = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    bv() {
    }

    public final List<Throwable> a(Throwable th) {
        while (true) {
            Reference<? extends Throwable> referencePoll = this.b.poll();
            if (referencePoll == null) {
                break;
            }
            this.a.remove(referencePoll);
        }
        List<Throwable> list = this.a.get(new bu(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> listPutIfAbsent = this.a.putIfAbsent(new bu(th, this.b), vector);
        return listPutIfAbsent == null ? vector : listPutIfAbsent;
    }
}
