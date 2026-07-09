package com.google.android.play.core.splitcompat;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class l implements com.google.android.play.core.splitinstall.n {
    final /* synthetic */ SplitCompat a;

    l(SplitCompat splitCompat) {
        this.a = splitCompat;
    }

    @Override // com.google.android.play.core.splitinstall.n
    public final Set<String> a() {
        return this.a.f();
    }
}
