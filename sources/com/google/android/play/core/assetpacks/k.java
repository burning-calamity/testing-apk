package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class k implements ThreadFactory {
    private final /* synthetic */ int c = 0;
    static final ThreadFactory b = new k(null);
    static final ThreadFactory a = new k();

    private k() {
    }

    private k(byte[] bArr) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return this.c != 0 ? new Thread(runnable, "AssetPackBackgroundExecutor") : new Thread(runnable, "UpdateListenerExecutor");
    }
}
