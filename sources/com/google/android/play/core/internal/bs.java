package com.google.android.play.core.internal;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class bs extends br {
    private final br a;
    private final long b;
    private final long c;

    public bs(br brVar, long j, long j2) {
        this.a = brVar;
        long jD = d(j);
        this.b = jD;
        this.c = d(jD + j2);
    }

    private final long d(long j) {
        if (j < 0) {
            return 0L;
        }
        return j > this.a.a() ? this.a.a() : j;
    }

    @Override // com.google.android.play.core.internal.br
    public final long a() {
        return this.c - this.b;
    }

    @Override // com.google.android.play.core.internal.br
    protected final InputStream b(long j, long j2) throws IOException {
        long jD = d(this.b);
        return this.a.b(jD, d(j2 + jD) - jD);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
