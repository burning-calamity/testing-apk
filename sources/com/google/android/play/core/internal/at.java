package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.play.core.splitcompat.SplitCompat;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class at implements com.google.android.play.core.splitinstall.f {
    private final Context a;
    private final com.google.android.play.core.splitcompat.c b;
    private final av c;
    private final Executor d;
    private final com.google.android.play.core.splitcompat.p e;

    public at(Context context, Executor executor, av avVar, com.google.android.play.core.splitcompat.c cVar, com.google.android.play.core.splitcompat.p pVar) {
        this.a = context;
        this.b = cVar;
        this.c = avVar;
        this.d = executor;
        this.e = pVar;
    }

    static /* synthetic */ void c(at atVar, com.google.android.play.core.splitinstall.d dVar) {
        try {
            if (SplitCompat.a(com.google.android.play.core.splitcompat.p.c(atVar.a))) {
                Log.i("SplitCompat", "Splits installed.");
                dVar.a();
            } else {
                Log.e("SplitCompat", "Emulating splits failed.");
                dVar.c(-12);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error emulating splits.", e);
            dVar.c(-12);
        }
    }

    static /* synthetic */ void d(at atVar, List list, com.google.android.play.core.splitinstall.d dVar) {
        Integer numE = atVar.e(list);
        if (numE == null) {
            return;
        }
        if (numE.intValue() == 0) {
            dVar.b();
        } else {
            dVar.c(numE.intValue());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00fc A[Catch: all -> 0x001b, TryCatch #8 {all -> 0x001b, blocks: (B:5:0x0016, B:12:0x0024, B:13:0x002d, B:15:0x0033, B:17:0x005b, B:22:0x006e, B:24:0x007a, B:33:0x0099, B:46:0x00b0, B:45:0x00ad, B:20:0x0068, B:47:0x00b1, B:48:0x00b6, B:49:0x00c0, B:51:0x00c8, B:53:0x00d0, B:54:0x00de, B:56:0x00e2, B:58:0x00ee, B:70:0x0113, B:61:0x00f8, B:62:0x00fc, B:64:0x0103), top: B:96:0x0016, outer: #5, inners: #11 }] */
    @com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Integer e(java.util.List<android.content.Intent> r15) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.at.e(java.util.List):java.lang.Integer");
    }

    @Override // com.google.android.play.core.splitinstall.f
    public final void a(List<Intent> list, com.google.android.play.core.splitinstall.d dVar) {
        if (!SplitCompat.b()) {
            throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
        }
        this.d.execute(new as(this, list, dVar));
    }
}
