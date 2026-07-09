package com.google.android.play.core.assetpacks;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class s implements com.google.android.play.core.internal.ce<Executor> {
    private final /* synthetic */ int a = 0;

    public s() {
    }

    public s(byte[] bArr) {
    }

    public s(char[] cArr) {
    }

    public s(short[] sArr) {
    }

    public static Executor b() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(k.a);
        com.google.android.play.core.internal.bh.k(executorServiceNewSingleThreadExecutor);
        return executorServiceNewSingleThreadExecutor;
    }

    public static Executor c() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(k.b);
        com.google.android.play.core.internal.bh.k(executorServiceNewSingleThreadExecutor);
        return executorServiceNewSingleThreadExecutor;
    }

    public static be d() {
        return new be();
    }

    public static bo e() {
        return new bo();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.play.core.assetpacks.be, java.util.concurrent.Executor] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.android.play.core.assetpacks.bo, java.util.concurrent.Executor] */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ Executor a() {
        int i = this.a;
        return i != 0 ? i != 1 ? i != 2 ? e() : d() : c() : b();
    }
}
