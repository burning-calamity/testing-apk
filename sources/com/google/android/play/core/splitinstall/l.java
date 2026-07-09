package com.google.android.play.core.splitinstall;

import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class l implements e {
    public static final l a = new l();
    private static final AtomicReference<f> b = new AtomicReference<>(null);

    private l() {
    }

    @Override // com.google.android.play.core.splitinstall.e
    @Nullable
    public final f a() {
        return b.get();
    }

    public final void b(f fVar) {
        b.set(fVar);
    }
}
