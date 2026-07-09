package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
public final class bf extends RuntimeException {
    public bf(String str) {
        super(str);
    }

    public bf(Throwable th) {
        super("Failed to initialize FileStorage", th);
    }
}
