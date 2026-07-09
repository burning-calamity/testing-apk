package com.google.android.play.core.internal;

import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class as implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ com.google.android.play.core.splitinstall.d b;
    final /* synthetic */ at c;

    as(at atVar, List list, com.google.android.play.core.splitinstall.d dVar) {
        this.c = atVar;
        this.a = list;
        this.b = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.c.c.c(this.a)) {
                at.c(this.c, this.b);
            } else {
                at.d(this.c, this.a, this.b);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error checking verified files.", e);
            this.b.c(-11);
        }
    }
}
