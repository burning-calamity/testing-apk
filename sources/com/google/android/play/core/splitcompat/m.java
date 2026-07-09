package com.google.android.play.core.splitcompat;

import android.content.Context;
import com.google.android.play.core.splitinstall.s;

/* JADX INFO: loaded from: classes.dex */
final class m implements Runnable {
    final /* synthetic */ Context a;

    m(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s.b(this.a).e(true);
    }
}
