package com.google.android.play.core.splitcompat;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class n implements Runnable {
    final /* synthetic */ SplitCompat a;

    n(SplitCompat splitCompat) {
        this.a = splitCompat;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.a.b.a();
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e);
        }
    }
}
