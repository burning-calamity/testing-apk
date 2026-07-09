package com.google.android.play.core.splitinstall;

import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    private static final AtomicReference<n> a = new AtomicReference<>(null);

    @Nullable
    static n a() {
        return a.get();
    }

    public static void b(n nVar) {
        a.compareAndSet(null, nVar);
    }
}
