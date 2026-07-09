package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.play.core.internal.ce;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class z implements ce<File> {
    private final ce<Context> a;

    public z(ce<Context> ceVar) {
        this.a = ceVar;
    }

    @Override // com.google.android.play.core.internal.ce
    @Nullable
    public final /* bridge */ /* synthetic */ File a() {
        return x.c(((y) this.a).a());
    }
}
